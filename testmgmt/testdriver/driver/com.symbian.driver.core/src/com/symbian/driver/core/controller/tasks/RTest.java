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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.CmdSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.Task;
import com.symbian.driver.core.CrashException;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.CoreDumpProxy;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.utils.Epoc;
import com.symbian.utils.Utils;

public class RTest implements IVisitor {

	/** The logger for the Visitor class. */
	private static final Logger LOGGER = Logger.getLogger(CmdSymbian.class.getName());

	/** Generic settings/configuration. */
	private final TDConfig CONFIG = TDConfig.getInstance();

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();

	/** EMF Object for RTest. */
	private final Rtest iRtest;
	
	/**
	 * @param aRtest
	 */
	public RTest(Rtest aRtest) {
		iRtest = aRtest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IVisitor#execute(com.symbian.driver.Task,
	 *      com.symbian.driver.core.processors.PreProcessor)
	 */
	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		try {
			String lRDebugBase = CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT)
					+ ModelUtils.getBaseDirectory((Task) iRtest.eContainer().eContainer(), CONFIG
							.getPreferenceInteger(TDConfig.RUN_NUMBER));
			lRDebugBase = lRDebugBase.replaceAll("\\\\+", "\\\\");

			File lRDebugRTestFile = new File(lRDebugBase + "RDebug.log");

			if (!lRDebugRTestFile.getParentFile().isDirectory() && !lRDebugRTestFile.getParentFile().mkdirs()) {
				throw new IOException("Could not create RDebug parent folder: " + lRDebugRTestFile);
			}

			// Redirect RDebug to RTest results file.
			aSymbianDevice.setRDebugOuput(lRDebugRTestFile);

			// get rid of $: from symbian path
			String lSymbianPath = iRtest.getSymbianPath();
			lSymbianPath = lSymbianPath.replace("$:", ILiterals.C);

			DeviceCommsProxy lDeviceProxy = null;
			try {
				lDeviceProxy = DeviceCommsProxy.getInstance();
				List<String> lArgs = new LinkedList<String>();
				lSymbianPath = lSymbianPath.replaceAll("\\s+", " ");
				int lArgSpace = lSymbianPath.indexOf(' ');
				
				if (lArgSpace != -1) {
					lArgs.add(lSymbianPath.substring(lArgSpace + 1, lSymbianPath.length()));
					lSymbianPath = lSymbianPath.substring(0, lArgSpace);
				}
				
				//monitor RTest application before run
				boolean lRecovery = false;
				boolean lPCDSStarted = false;
				try{
					String recovery = CONFIG.getPreference(TDConfig.RECOVERY);
					boolean isEmulator = Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM));
					//Crash recovery not supported for Emulator!
					lRecovery = "on".equalsIgnoreCase(recovery) && !isEmulator;
					if(lRecovery) {
						boolean lSuccess = CoreDumpProxy.getInstance().monitorApp(lSymbianPath);
						if(lSuccess)
							LOGGER.info("Monitor crash on applications: " + lSymbianPath);
						else
							LOGGER.warning("Failed to monitor RTest application!");
						
						// Start the PCDS - Process Core Dump Server, if configured
						if(CoreDumpProxy.getInstance().startServer()) {
								lPCDSStarted = true;
								LOGGER.info("PCDS started successfully!");
						}else
								LOGGER.warning("Failed to start PCDS server!");
						}
				}catch(Exception e) {
					LOGGER.warning("Unable to create PCDS proxy. Crash recovery disabled!");
				}
				
				// Run RTest
				int lTimeout;
				if (!aTask.isSetTimeout()) {
					lTimeout = 0;
				} else {
					lTimeout = aTask.getTimeout() <= 0 ? -1 : aTask.getTimeout() * 1000;
				}
				if (!lDeviceProxy.createSymbianProcess().runCommand(lSymbianPath, lArgs, lTimeout, true))
					iExceptions.put(new Exception("Failed launch Rtest."), ESeverity.ERROR);
				
				//if crash happens, retrieve crash data
				try {
					if(lRecovery) {
						SimpleDateFormat lFormat = new SimpleDateFormat("yyyyMMddhhmmss");
						String lTimeStamp = lFormat.format(new Date());
						String lCrashDataPath = lRDebugBase + File.separator 
												+ "crashdata" + File.separator + lTimeStamp;
						//use well-formed path
						if(CoreDumpProxy.getInstance().isAppCrash()) {
							CrashException lException = new CrashException();
							//app crash
							boolean lSuccess = CoreDumpProxy.getInstance().getCrashData(lSymbianPath, lCrashDataPath);
							if(lSuccess) {
								LOGGER.info("Crash data stored at: " + lCrashDataPath);
								lException.setCoreDumpUrl(new File(lCrashDataPath).toURI().toURL());
							}else
								LOGGER.warning("Failed to get crash data.");
							iExceptions.put(lException, ESeverity.INFO);
						}
						
						// Stop PCDS if it's running
						if(lPCDSStarted)
								if(CoreDumpProxy.getInstance().stopServer())
									LOGGER.info("PCDS stopped successfully!");
								else
									LOGGER.info("Failed to stop PCDS!");
					}
				} catch (Exception e) {
					LOGGER.log(Level.WARNING, "Unable to create PCDS proxy. Recovery disabled!",e);
				}

				// Retrieve any result file
				if (iRtest.getResultFile() != null) {
					try {
							Utils.copy(lRDebugRTestFile,new File(iRtest.getResultFile()));
							if (!lRDebugRTestFile.delete()){
								LOGGER.finest("Failed to delete the RDebug.log file.");
							}
					} catch (IOException e) {
						LOGGER.log(Level.WARNING,"Failed to retrieve the RTest result file.");
						iRtest.setResultFile(lRDebugRTestFile.getCanonicalPath());
						LOGGER.log(Level.WARNING,"The Rtest result file is stored in "+lRDebugRTestFile.getCanonicalPath());
					}
				}else {
					iRtest.setResultFile(lRDebugRTestFile.getCanonicalPath());
				}   
			} catch (Exception lException) {
				iExceptions.put(lException, ESeverity.ERROR);
			}
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, lParseException.getMessage(), lParseException);
			iExceptions.put(lParseException, ESeverity.ERROR);
		} catch (IOException lIOException) {
			LOGGER.log(Level.WARNING, "RTest " + iRtest.getSymbianPath() + " RDebug failed.", lIOException);
			iExceptions.put(lIOException, ESeverity.ERROR);
		} finally {
			// if it's emulator, set a delay to make sure epocwind.out is
			// populated.
			try {
				if (Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM))) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// ignore
					}
				}
			} catch (IllegalArgumentException e) {
				// ignore
			} catch (ParseException e) {
				// ignore
			}
			// Reset RDebug output to normal output
			aSymbianDevice.restoreRDebugOutput();
		}

		return iExceptions;
	}

}
