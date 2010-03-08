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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.client.TestClient;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;
import com.symbian.utils.cmdline.argscheckers.NumericData;

/**
 * Class representing the command line 'results'.
 * 
 * @author EngineeringTools
 */
public class ResultsCmdLine extends CmdLine {
	
	/**
	 * Standard constructor. Sets the switches -j,-c and --srv
	 */
	public ResultsCmdLine() {
		super("results", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) throws ParseException {
				TDConfig CONFIG = TDConfig.getInstance();
				CONFIG.setConfigFromCmdLine(aCmd);
				TestClient lClient = new TestClient();

				int lTestJobId = CONFIG.getPreferenceInteger(TDConfig.JOB_ID);
				if (CONFIG.getPreference(TDConfig.SERVER) != null) {
					lClient.setServerAddress(CONFIG.getPreference(TDConfig.SERVER));
				}
				File lResultsFolder = CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT);
				if (lClient.connectToMaster()) {
					lClient.getTestJobResults(lResultsFolder.getAbsolutePath(), lTestJobId);
				}
				return null;
			}
			
		}, "display the results from a remote job");
		
		NumericData lNumericCheck = new NumericData();
		addSwitch("j", true, "Unique Job ID. (Preference jobId)", false, lNumericCheck);
		addSwitch("c", true, "Path where to collect test results. (Variable ${resultRoot}, Preference resultroot)", false, true);
		addSwitch("srv", false, "Remote host/IP address and service name.  (Preference server)", false, true);
	}

	/**
	 * Implements additional constraints on switches.
	 * 
	 * @param aCommandLine
	 *            {@inheritDoc}
	 */
	public void checkAdditionalConstraints(final CommandLine aCommandLine) {
		if (aCommandLine.hasOption("j")) {
			if (Integer.parseInt(aCommandLine.getOptionValue("j")) < 1) {
				LOGGER.severe("Job ID must be a positive number");
			}
		}
		if (aCommandLine.hasOption("c")) {
			File resultsPath = new File(aCommandLine.getOptionValue("c"));
			if (!resultsPath.exists()) {
				try {
					resultsPath.mkdirs();
				} catch (SecurityException se) {
					LOGGER.severe("Can not create " + resultsPath + ". Please check permissions." + se.getMessage());
				}
			} else if (!resultsPath.isDirectory() || !resultsPath.canWrite()) {
				LOGGER.severe("Results path " + resultsPath + " is not a valid directory.");
			}
		}
	}

}
