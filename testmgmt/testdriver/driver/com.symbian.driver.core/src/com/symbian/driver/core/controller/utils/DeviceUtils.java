/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of "Eclipse Public License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.eclipse.org/legal/epl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description: 
*
*/

package com.symbian.driver.core.controller.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.Task;
import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.nativeprocesshandler.ProcessHandler;

/**
 * @author EngineeringTools
 * 
 */
public class DeviceUtils {
	
	// Flag for poll stop
	private static boolean PollStop = false;

	public static void stopPoll(){
		PollStop = true;
	}
	
	public static boolean pollStopped(){
		return PollStop;
	}
	
	public static void resetPoll(){
		PollStop = false;
	}
	
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(ControllerUtils.class.getName());

	/**
	 * @param aEpoc
	 * @return 
	 * @throws InterruptedException
	 * @throws TimeLimitExceededException
	 * @throws JStatException
	 * @throws ParseException
	 */
	public static final boolean poll(ExecuteOnHost aEpoc) throws ParseException {
		return poll(aEpoc, 20);
	}

	/**
	 * @param aEpoc
	 * @param aNumberRetries
	 * @return 
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws TimeLimitExceededException
	 */
	public static final boolean poll(ExecuteOnHost aEpoc, final int aNumberRetries) throws ParseException {
		LOGGER.info("Polling the Symbian device.");

		int lAttempts = 0;
		boolean lDeviceInfo = false;
		while (!lDeviceInfo && lAttempts < aNumberRetries) {

			if(PollStop == true){
				break;					
			}

			try {
				Thread.sleep(TimeOut.RESTART_WAIT);
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.FINE, "Timer was interrupted while polling.", lInterruptedException);
			}

			try {

				// Check if emulator is still running
				if (aEpoc != null && !aEpoc.isRunning()) {
					LOGGER.log(Level.SEVERE, "Emulator stopped prematurely.");
					return false;
				}

				if (DeviceCommsProxy.getInstance().isDeviceAlive()) {
					lDeviceInfo = true;
					break;
				} else {
					LOGGER.info("Waiting for the Symbian device to startup, attempt #: " + lAttempts);
				}

			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Failed to get comms instance.", lException);
			}

			lAttempts++;

		}

		if (!lDeviceInfo) {
			LOGGER.log(Level.SEVERE, "The Symbian Device did not respond after " + lAttempts
					+ " attempts. Please check your CommDB file for WinTap and accessiblity of your Symbian Device (HW or SW).");
			return false;
		}

		LOGGER.info("Polling of the Symbian Device finished succefully. Using transport at: "
				+ TDConfig.getInstance().getPreference(TDConfig.TRANSPORT));
		return true;
	}

	/**
	 * @param aSymbianPath
	 * @param aPCPath
	 * @param aMove
	 * @return boolean success/fail
	 * @throws TimeLimitExceededException
	 * @throws ParseException
	 * @throws JStatException
	 */
	public static final boolean retrieveFilesave(final String aSymbianPath, final String aPCPath, final boolean aDelete, final Task aTask)
			throws TimeLimitExceededException {

		boolean lResult = false;

		DeviceCommsProxy lDeviceProxy = null;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
			if (lDeviceProxy.createSymbianTransfer().retrieve(new File(aSymbianPath), new File(aPCPath))) {
				lResult = true;
				if (aDelete && !lDeviceProxy.createSymbianTransfer().delete(new File(aSymbianPath), false)) {
					LOGGER.log(Level.SEVERE, "could not delete " + aSymbianPath);
				}
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage());
		}
		return lResult;
	}

	/**
	 * @throws ParseException
	 * @throws JStatException
	 * @throws TimeLimitExceededException
	 * @throws JStatException
	 * @throws TimeLimitExceededException
	 */
	public static final void killAllEpocs() {
		// Get current running emulator process
		int[] lStatapiPidList = ProcessHandler.getPidList("statapi.exe");
		int[] lEpocPidList = ProcessHandler.getPidList("epoc.exe");
		int[] lEshellPidList = ProcessHandler.getPidList("eshell.exe");

		// Kill current running emulator process using JStat
		if (lStatapiPidList.length != 0) {
			try {
				DeviceUtils.restartBoard();
			} catch (Exception lException) {
				// we will try to kill all processes anyway
			}
		}

		// Kill of all emulator processes using
		for (int lIter = 0; lIter < lStatapiPidList.length; lIter++) {
			LOGGER.fine("Trying to kill StatApi.exe with PID: " + lStatapiPidList[lIter]);
			ProcessHandler.kill(lStatapiPidList[lIter]);
		}

		for (int lIter = 0; lIter < lEpocPidList.length; lIter++) {
			LOGGER.fine("Trying to kill Epoc.exe with PID: " + lEpocPidList[lIter]);
			ProcessHandler.kill(lEpocPidList[lIter]);
		}

		for (int lIter = 0; lIter < lEshellPidList.length; lIter++) {
			LOGGER.fine("Trying to kill Eshell.exe with PID: " + lEshellPidList[lIter]);
			ProcessHandler.kill(lEshellPidList[lIter]);
		}

		if (lStatapiPidList.length != 0 || lEpocPidList.length != 0 || lEshellPidList.length != 0) {
			LOGGER.info("Emulator was still running. Killing all instances of Epoc.exe and/or StatApi.exe");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "While killing the emulator the thread was interupted.", lInterruptedException);
			}
		}
	}

	/**
	 * @param aSymbianPath
	 * @param aPCPath
	 * @param aMove
	 * @throws TimeLimitExceededException
	 * @throws ParseException
	 * @throws JStatException
	 */
	public static final boolean retrieveFile(final String aSymbianPath, final String aPCPath, final boolean aDelete, final Task aTask) {
		boolean lResult = false;

		DeviceCommsProxy lDeviceProxy = null;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
			if (lDeviceProxy.createSymbianTransfer().retrieve(new File(aSymbianPath), new File(aPCPath))) {
				lResult = true;
				if (aDelete && !lDeviceProxy.createSymbianTransfer().delete(new File(aSymbianPath), false)) {
					LOGGER.log(Level.SEVERE, "could not delete " + aSymbianPath);
				}
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage());
		}

		return lResult;
	}

	/**
	 * Utils method to send file from PC to device
	 * @param aPCPath, the source file path on PC
	 * @param aSymbianPath, the target path on symbian device
	 */
	public static final boolean sendFile(final String aPCPath, final String aSymbianPath) {
		DeviceCommsProxy lDeviceProxy = null;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
			if (lDeviceProxy.createSymbianTransfer().send(new File(aPCPath), new File(aSymbianPath))) {
				return true;
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage());
		}
		return false;
		
	}
	
	public static boolean restartBoard() {
		DeviceCommsProxy lDeviceProxy;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
			if (lDeviceProxy.rebootDevice()) {
				return true;
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage());
			return false;
		}
		return false;
	}
}
