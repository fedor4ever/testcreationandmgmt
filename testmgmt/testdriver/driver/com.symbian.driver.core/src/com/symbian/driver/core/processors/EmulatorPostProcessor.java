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

package com.symbian.driver.core.processors;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.controller.utils.FileUpdateListener;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;

/**
 * @author EngineeringTools
 *
 */
public class EmulatorPostProcessor extends Thread implements PostProcessor {

	private ExecuteOnHost iEpoc = null;
	
	private File iEthertapPddFile = null;

	private File iWinscwDir = null;
	
	private FileUpdateListener iListener = null;
	
	/**
	 * @param aEpoc
	 * @param aEthertapPddFile
	 * @param aWinscwDir
	 * @param aEpocIniFile 
	 */
	public EmulatorPostProcessor(ExecuteOnHost aEpoc, File aEthertapPddFile, File aWinscwDir, FileUpdateListener aListener) {
		super();
		
		// Process
		iEpoc = aEpoc;
		
		// Files
		iEthertapPddFile = aEthertapPddFile;
		iWinscwDir = aWinscwDir;
		iListener = aListener;
	}
	
	
	public void run() {
		LOGGER.info("Stopping the emulator. Please wait while the emulator restores the emulator to its orginal state.");
		try {
			Runtime.getRuntime().removeShutdownHook(this);
		} catch (Exception lException) {
			LOGGER.fine("Didn't succefully deregister the shutdownhook.");
		}
		
		// Move RDebug to the results folder
		if (iListener != null) {
			LOGGER.fine("Killing RDebug.");
			iListener.setMlife(false);
		}
		
		try {
			if (iEpoc != null && !iEpoc.doCleanUp(false)) {
				LOGGER.warning("Could not stop the epoc emulator.");
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.WARNING, "Could not stop the epoc emulator.", lTimeLimitExceededException);
		}
		
		try {
			DeviceCommsProxy.getInstance().stop(true);
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE," Error " , lException);
		}
		
		DeviceUtils.killAllEpocs();
		
		String lCommDBInput;
		try {
			lCommDBInput = TDConfig.getInstance().getPreference(TDConfig.COMMDB).toLowerCase();
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get configuration for CommDat.", lParseException);
			lCommDBInput = "off";
		}
		
		
		
		if (lCommDBInput.equalsIgnoreCase("on")
				|| lCommDBInput.equalsIgnoreCase("true")
				|| lCommDBInput.startsWith("overwrite")) {
			
			// Restore Files
			restoreFile(iEthertapPddFile);
			
			
			// Restore Old WinTap CED
			LOGGER.info("Restoring old CommDB via Commandline.");
			try {
				Thread.sleep(2000);
				
				new ExecuteOnHost(iWinscwDir, "ced.exe -i c:\\cedout.cfg").doTask(true, 0, false);
			} catch (TimeLimitExceededException lTimeLimitExceededException) {
				LOGGER.log(Level.WARNING, "Could not restore the CommDB settings due to timeout.", lTimeLimitExceededException);
			} catch (IOException lIOException) {
				LOGGER.log(Level.WARNING, "Could not restore the CommDB settings due to I/O exception.", lIOException);
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "Could not restore the CommDB settings due to Thread Interrupt.", lInterruptedException);
			}
		}
		
		LOGGER.info("Emulator stopped and stopping TestDriver.");
	}

	/**
	 * @param lNewFile
	 */
	public static final void restoreFile(File lNewFile) {
		LOGGER.info("Restoring old file: " + lNewFile);
		if (lNewFile.isFile()) {
			try {
				if (lNewFile.delete()
						&& !new File(lNewFile.getCanonicalPath() + BACKUP).renameTo(lNewFile)) {
					LOGGER.log(Level.SEVERE, "Could not restore file: " + lNewFile.toString());
				}
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Could not restore file: " + lNewFile.toString(), lIOException);
			}
		} else {
			LOGGER.log(Level.WARNING, "Could not find file to restore: " + lNewFile.toString());
		}
	}
}
