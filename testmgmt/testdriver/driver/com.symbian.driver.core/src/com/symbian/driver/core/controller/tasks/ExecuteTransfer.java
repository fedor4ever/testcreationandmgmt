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

package com.symbian.driver.core.controller.tasks;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.naming.TimeLimitExceededException;

import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.utils.Utils;

/**
 * @author EnginneeringTools
 */
public class ExecuteTransfer implements Execute {

	/** Logger. */
	private static Logger LOGGER = Logger.getLogger(ExecuteTransfer.class.getName());

	// General fields
	/** The host path of the file. */
	private File iPCPath = null;

	/** The device path of the file. */
	private String iSymbianPath = null;
	
	/** The repository. */
	private File iRepository = null;

	/** The output buffer. */
	private StringBuffer iOutputBuffer = new StringBuffer();
	
	/** Literal for end of line: ie \r\n or \n. */
	public static final String EOL = System.getProperty("line.separator");
	
	/**
	 * Private constructor for a ExecuteTransfer File. Only handles the host and device
	 * path and the location of the stat working directory.
	 * 
	 * @param aHostPath
	 *            The location on the host of the file.
	 * @param aDevicePath
	 *            The location on the device of the file
	 * @param aPrintStream 
	 * @throws IOException
	 *             If The paramters are incorrect.
	 */
	public ExecuteTransfer(final File aHostPath, final String aDevicePath) throws IOException {
		LOGGER.entering("ExecuteTransfer", "Constructor", new Object[] { aHostPath, aDevicePath });
		
		// Host path
		if (aHostPath == null) {
			LOGGER.log(Level.WARNING, "Host path is NULL");
			throw new IOException("Host path is NULL");
		} else if (!aHostPath.isFile()) {
			LOGGER.log(Level.WARNING, "PC path is does not exists or is not a file: " + aHostPath.getCanonicalPath());
			throw new IOException("PC path is does not exist or is not a file: " + aHostPath.getCanonicalPath());
		}

		// Device Path
		if (aDevicePath == null) {
			LOGGER.log(Level.WARNING, "The device path is NULL");
			throw new IOException("The device path is NULL");
		} else if (!Pattern.compile("[a-zA-Z$]:\\\\([^\\\\/:\\*\\?]+\\\\)*[^\\\\/:\\*\\?]+").matcher(aDevicePath).find()) {
			LOGGER.log(Level.WARNING, "The device path is invalid: " + iSymbianPath);
			throw new IOException("The device path is invalid: " + iSymbianPath);
		}

		LOGGER.fine("Adding file \"" + aHostPath.getAbsolutePath() + "\" to device at \"" + aDevicePath + "\"");

		iPCPath = aHostPath;
		iSymbianPath = aDevicePath;
	}

	/**
	 * Adds a file to the transfer list.
	 * 
	 * @param aRepository
	 *            teh repository to move the
	 * @throws IOException
	 */
	public void fileToRepository(final File aRepository) throws IOException {
		LOGGER.entering("ExecuteTransfer", "fileToRepository", aRepository);

		if (aRepository.isFile()) {
			throw new IOException("The repository is already a file. It must be a directory.");
		} else if (!aRepository.exists()) {
			if (!aRepository.mkdirs()) {
				throw new IOException("Could not create the repository directory.");
			}
		}

		File lCopyTo = new File(aRepository.getCanonicalPath() + File.separator + iPCPath.getName());

		// Cope with duplicate 
		if (lCopyTo.isFile()) {
			lCopyTo = new File(lCopyTo + "_" + Math.random() * 1000);
		}
		
		Utils.copy(iPCPath, lCopyTo);

		iPCPath = lCopyTo;
		iRepository = aRepository;

		LOGGER.exiting("ExecuteTransfer", "fileToRepository");
	}

	/**
	 * @return The string to add to the package file containing the host and
	 *         device path correctly formatted.
	 */
	public String fileToSIS() {
		String lReturnString = "\"" + iPCPath + "\"-\"" + iSymbianPath + "\"" + EOL;
		LOGGER.fine("SIS pkg line is: " + lReturnString);
		
		return lReturnString;
	}

	/**
	 * Install with Platform Security Off. If Platform Security is ON this task
	 * will fail.
	 * 
	 * @see com.symbian.driver.core.controller.tasks.Execute#doTask(boolean, int, boolean)
	 * 
	 * @param aDoCleanUp
	 *            For transfer this boolean is ignored as it doesn't make sense
	 *            to tranfer a file and then immediatly delete/uninstall the
	 *            file.

	 * @throws IOException
	 * @throws JStatException 
	 * @throws JStatException 
	 * @throws TimeLimitExceededException 
	 */
	public boolean doTask(final boolean aDoCleanUp, final int aTimeout, boolean captureOutput) throws IOException, TimeLimitExceededException {
		LOGGER.entering("ExecuteTransfer", "doTask", Boolean.toString(aDoCleanUp));
		boolean lReturn = true;
		if (!iPCPath.isFile()) {
			iPCPath = new File(iRepository.getCanonicalPath() + File.separator + iPCPath.getName());
			if (!iPCPath.isFile()) {
				throw new IOException("PC path can not be found");
			}
		}
			
			LOGGER.fine("JSTAT Sending file: " + iSymbianPath);
			DeviceCommsProxy lDeviceProxy = null;
			try {
				lDeviceProxy = DeviceCommsProxy.getInstance();
			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Could not load comms proxy." + lException.getMessage());
				return false;
			}
			if (!lDeviceProxy.createSymbianTransfer().send(iPCPath, new File(iSymbianPath))) {
				lReturn = false;
			}
			return lReturn;
		}

	/**
	 * Uninstalls the file for Platform Security off.
	 * 
	 * @param aWaitForTimeout This parameter is ignored for ExecuteTransfer as it doesn't make sense
	 * @return The result of the Uninstall.
	 * @throws JStatException
	 * @throws TimeLimitExceededException 
	 */
	public boolean doCleanUp(final boolean aWaitForTimeout) throws TimeLimitExceededException {
		LOGGER.entering("ExecuteTransfer", "doCleanUp");
		
			LOGGER.fine("JSTAT Deleting file: " + iSymbianPath);
			DeviceCommsProxy lDeviceProxy = null;
			try {
				lDeviceProxy = lDeviceProxy.getInstance();
			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Could not load comms proxy." + lException.getMessage());
				return false;
			}
			return lDeviceProxy.createSymbianTransfer().delete(new File(iSymbianPath), false);
	}

	/**
	 * @return The path to the file to transfer on the PC.
	 */
	public File getPCPath() {
		return iPCPath;
	}

	/**
	 * @return The path of the file to transfer on the Symbian device (Hardware
	 *         or Emulator)
	 */
	public String getSymbianPath() {
		return iSymbianPath;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ExecuteTransfer:\n\t- PC Path: " + iPCPath + "\n\t- Symbian Path: " + iSymbianPath;
	}

	
	/**
	 * @see com.symbian.driver.core.controller.tasks.Execute#isRunning()
	 */
	public boolean isRunning() {
		return false;
	}

	public String getOutput() {
		return iOutputBuffer.toString();
	}
}
