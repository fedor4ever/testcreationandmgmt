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



package com.symbian.driver.remoting.cmdline;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.client.TestClient;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;
import com.symbian.utils.cmdline.argscheckers.AlphaNumericData;

/**
 * Class representing the command line 'runremote'.
 * 
 * @author EngineeringTools
 */
public class RunremoteCmdLine extends CmdLine {

	/**
	 * Standard constructor. Sets the switches
	 * -m,--tp,-i,-r,--srv,--cl,--platsec,-t
	 */
	public RunremoteCmdLine() {
		super("runremote", new CmdOperational() {

			public synchronized Object run(final CmdLine aCmd) throws ParseException {

				//
				// because some of the settings are for the remote system
				// CONFIG.setConfigFromCmdLine(aCmd) can not be used here.
				// instead set only the relevant ones.
				
				//Remote system settings. these should be added to the package manifest.
				String lTransport = null;
				boolean lSysbin = false;
				boolean lTestexec = false;
				String lRdebug = null;	
				boolean ltefLite = false;
				
				if (aCmd.getCommandLine().hasOption("t")) {
					lTransport = aCmd.getCommandLine().getOptionValue("t");
				}
				
				if (aCmd.getCommandLine().hasOption("sysbin")){
				String lSysbinstring = aCmd.getCommandLine().getOptionValue("sysbin");
				lSysbin = lSysbinstring.equalsIgnoreCase("true") || lSysbinstring.equalsIgnoreCase("on") ? true : false;
				}

				//depreciate the setting of "sysbin"
				if (aCmd.getCommandLine().hasOption("statlite")){
					String lStatLitestring = aCmd.getCommandLine().getOptionValue("statlite");
					lSysbin = lStatLitestring.equalsIgnoreCase("true") || lStatLitestring.equalsIgnoreCase("on") ? true : false;
					}
				
				if (aCmd.getCommandLine().hasOption("teflite")){
					String lTefLitestring = aCmd.getCommandLine().getOptionValue("teflite");
					if (lTefLitestring.equalsIgnoreCase("auto"))
						ltefLite = lSysbin;
					else
						ltefLite = lTefLitestring.equalsIgnoreCase("true") || lTefLitestring.equalsIgnoreCase("on") ? true : false;
					}
				
				if (aCmd.getCommandLine().hasOption("testexec")){
					String lTestexecstring = aCmd.getCommandLine().getOptionValue("testexec");
					lTestexec = lTestexecstring.equalsIgnoreCase("true") || lTestexecstring.equalsIgnoreCase("on") ? true : false;
				}
				
				if (aCmd.getCommandLine().hasOption("l")) {
					 lRdebug = aCmd.getCommandLine().getOptionValue("l");
				}
				
				TDConfig CONFIG = TDConfig.getInstance();
				
				//Local system settings
				if (aCmd.getCommandLine().hasOption("m")) {
					CONFIG.setPreference(TDConfig.MODE, aCmd.getCommandLine().getOptionValue("m"));
				}
				if (aCmd.getCommandLine().hasOption("platsec")) {
					String lPlatsecstring = aCmd.getCommandLine().getOptionValue("platsec");
					CONFIG.setPreferenceBoolean(TDConfig.PLATSEC, (lPlatsecstring.equalsIgnoreCase("true") || lPlatsecstring.equalsIgnoreCase("on")) ? true : false);
				}
				if (aCmd.getCommandLine().hasOption("cl")) {
					CONFIG.setPreference(TDConfig.CLIENT, aCmd.getCommandLine().getOptionValue("cl"));
				}
				if (aCmd.getCommandLine().hasOption("srv")) {
					CONFIG.setPreference(TDConfig.SERVER, aCmd.getCommandLine().getOptionValue("srv"));
				}
				
				
				String lMode = CONFIG.getPreference(TDConfig.MODE);
				boolean lPlatsec = CONFIG.isPreference(TDConfig.PLATSEC);
				String lClientName = CONFIG.getPreference(TDConfig.CLIENT);
				
				String lWorkingPath = null;
				String lTestPackage = null;
				File lRomFile = null;
				

				try {
					if (aCmd.getCommandLine().hasOption("i")) {
						CONFIG.setPreferenceFile(TDConfig.WORKING_PATH, new File(aCmd.getCommandLine().getOptionValue("i")).getCanonicalFile());
					}
					if (aCmd.getCommandLine().hasOption("tp")) {
						CONFIG.setPreferenceFile(TDConfig.TEST_PACKAGE, new File(aCmd.getCommandLine().getOptionValue("tp")).getCanonicalFile());
					}
					if (aCmd.getCommandLine().hasOption("r")) {
						//optional
						CONFIG.setPreference(TDConfig.ROM, aCmd.getCommandLine().getOptionValue("r"));
						lRomFile = new File(CONFIG.getPreference(TDConfig.ROM));
					}
					lWorkingPath = CONFIG.getPreferenceFile(TDConfig.WORKING_PATH).getCanonicalPath();
					lTestPackage = CONFIG.getPreferenceFile(TDConfig.TEST_PACKAGE).getName();
					
				} catch (IOException lException) {
					LOGGER.log(Level.WARNING, lException.getMessage(), lException);
				}

				LOGGER.info("Updating package: " + "working path = " + lWorkingPath 
						+ " , testPackage = " + lTestPackage 
						+ " , romFile = " + lRomFile 
						+ " , platsec = " + lPlatsec 
						+ " , client = " + lClientName 
						+ " , transport = " + lTransport
						+ " , Sysbin = " + lSysbin
						+ " , Rdebug = " + lRdebug
						+ " , Testexec = " + lTestexec
						+ " , teflite = " + ltefLite);

				// update the package : add the image if specified, update the
				// manifest and repackage

				if (lRomFile != null && !lRomFile.isFile()) {
					throw new ParseException("Rom File incorrect: " + lRomFile.toString());
				}
				
				//create a new client
				
				TestClient lClient = new TestClient();

				String newPackage = null;
					newPackage = lClient.updatePackage(lWorkingPath, lTestPackage, lRomFile == null ? null : lRomFile
							.getName(), lPlatsec, lSysbin, lTestexec, lTransport, lRdebug, ltefLite);

				if (newPackage != null) {
					// package update di not fail

					// get client to create a new test job and hold a reference
					// to it
					lClient.createJob(lWorkingPath, newPackage);

					// ask client to connect to master
					// if client fails to connect to master, it will die

					if (lClient.connectToMaster()) {
						// we have a connection
						if (lMode.equalsIgnoreCase("sync")) {
							// synchronuous mode : the client keeps an eye of
							// the job.
							// register client with master
							if (lClient.registerWithMaster(lClientName)) {
								// submit job to master
								if (lClient.submitJobToMaster()) {
									// monitor the job
									lClient.monitorSubmission();
								}
							}
						} else { // its async, so just submit the job
							lClient.registerWithMaster(lClientName);
							lClient.submitJobToMaster();
						}
					}
				}
				return null;
			}

		}, "run a test package remotely.");
		
		AlphaNumericData lAlphaNumericCheck = new AlphaNumericData();
		addSwitch("m", true, "Results reception mode sync/async. (Preference mode)", false, true);
		addSwitch("tp", false, "Test package name. (Preference testPackage)", false, true);
		addSwitch("i", true, "Working directory.(Preference workingPath)", false, true);
		addSwitch("r", true, "ROM image. (Preference ROM)", false, true);
		addSwitch("srv", false, "Remote host name/IP address and the name of the remote service. (Preference server)", false, true);
		addSwitch("cl", false, "Unique client name. (Preference client)", false, true);
		addSwitch("platsec", false, "Platsec ON/OFF (Preference platsec)  DEPRECATED", false, true);
		addSwitch("testexec", false, "ExecuteTransfer Test Execute Framework (TEF) Dependencies: ON, OFF. (Preference testexecute)", false, true);
		addSwitch("sysbin", false, "Allows copying to SYS/BIN with STATLite. (Preference sysbin)  DEPRECATED: use --statlite", false, true);
        addSwitch("statlite", false, "Using STAT lite. (Preference statlite)", false, true);
        addSwitch("teflite", false, "Using TEF lite. (Preference teflite)", false, true);
		addSwitch("t", true, "Transport protocol used to link with hardware board. (Preference transport)", false, lAlphaNumericCheck);
		addSwitch("l", true, "Location of where RDebug logs should be saved. (Preference rdebug)", false, true);		
	}

	/**
	 * Implements additional constraints on switches.
	 * 
	 * @param aCommandLine
	 *            {@inheritDoc}
	 * @throws ParseException
	 *             {@inheritDoc}
	 */
	public void checkAdditionalConstraints(final CommandLine aCommandLine) throws ParseException {
		if (aCommandLine.hasOption("m")) {
			String lMode = aCommandLine.getOptionValue("m");
			if (!lMode.equalsIgnoreCase("sync") && !lMode.equalsIgnoreCase("async")) {
				throw new ParseException("Synchrinization should be either sync or async.");
			}
		}
	}

}
