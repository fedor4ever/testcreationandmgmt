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


package com.symbian.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

import com.symbian.driver.core.cmdline.BuildCmdLine;
import com.symbian.driver.core.cmdline.CleanCmdLine;
import com.symbian.driver.core.cmdline.ConfigCmdLine;
import com.symbian.driver.core.cmdline.EmulatorpackageCmdLine;
import com.symbian.driver.core.cmdline.ImportCmdLine;
import com.symbian.driver.core.cmdline.InstallCmdLine;
import com.symbian.driver.core.cmdline.InstallPackageCmdLine;
import com.symbian.driver.core.cmdline.MasterCmdLine;
import com.symbian.driver.core.cmdline.PackageCmdLine;
import com.symbian.driver.core.cmdline.PrintCmdLine;
import com.symbian.driver.core.cmdline.RbuildCmdLine;
import com.symbian.driver.core.cmdline.ResultLocationCmdLine;
import com.symbian.driver.core.cmdline.RunCmdLine;
import com.symbian.driver.core.cmdline.ValidateCmdLine;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.cmdline.CancelCmdLine;
import com.symbian.driver.remoting.cmdline.CleanRemoteCmdLine;
import com.symbian.driver.remoting.cmdline.JobstatusCmdLine;
import com.symbian.driver.remoting.cmdline.MasterstatusCmdLine;
import com.symbian.driver.remoting.cmdline.ResultsCmdLine;
import com.symbian.driver.remoting.cmdline.RunremoteCmdLine;
import com.symbian.utils.JarUtils;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdLineSet;
import com.symbian.utils.config.ConfigUtils;

/**
 * Main class from for TestDriver.
 * 
 * @author EngineeringTools
 */
public class TestDriver implements IPlatformRunnable {

	/** Generic logger. */
	private static Logger LOGGER;

	/** Generic settings/configuration. */
	private static TDConfig CONFIG = TDConfig.getInstance();

	/**
	 * @param args
	 *            The arguments to the main program
	 */

	public Object run(Object args) throws Exception {

		Bundle bundle = Platform.getBundle("com.symbian.driver.core");
		Dictionary lDic = bundle.getHeaders();
		String lTile = (String) lDic.get(Constants.BUNDLE_NAME);
		String lVendor = (String) lDic.get(Constants.BUNDLE_VENDOR);
		String lVersion = (String) lDic.get(Constants.BUNDLE_VERSION);

		if (lVersion != null) {
			ConfigUtils.setVersion(lVersion);
		}
		if (lTile != null) {
			ConfigUtils.setName(lTile);
		}
		if (lVendor != null) {
			ConfigUtils.setCopyright(lVendor);
		}

		/* load TD plugins settings, the may wish the default ones */
		CONFIG.completeConfigFromPlugins();

		/* remove -pdelaunch from the options */
		ArrayList<String> aList = new ArrayList<String>(0);
		String[] raw = (String[]) args;
		for (int i = 0; i < raw.length; i++) {
			if (!raw[i].equalsIgnoreCase("-pdelaunch")) {
				aList.add(raw[i]);
			}
		}
		main((String[]) aList.toArray(new String[aList.size()]));
		return null;
	}

	public void stop() {
	}

	public static void main(final String[] args) {
		try {
			// Initialise LOGGING
			try {
				LogManager.getLogManager().reset();
				LogManager.getLogManager().readConfiguration();
			} catch (IOException lIOException) {
				System.err.println("ERROR: Failed to load logging.properties: " + lIOException.getMessage());
				LogManager.getLogManager().getLogger("").addHandler(new ConsoleHandler());
			} finally {
				LOGGER = Logger.getLogger(TestDriver.class.getName());
			}

			// Print out arguments used
			StringBuffer lArgBuffer = new StringBuffer();
			for (int i = 0; i < args.length; i++) {
				lArgBuffer.append(args[i] + " ");
			}
			LOGGER.fine("Running with arguments: " + lArgBuffer.toString());

			// Initialise Security Policy
			String lSecurityLiteral = "java.security.policy";

			File lSecurity = JarUtils.extractResource(TestDriver.class, "/resource/securitypolicy.txt");

			if (!lSecurity.isFile()) {
				throw new Exception("The security path variable are incorrect. Please contact TD_Support:\n\t"
						+ lSecurity.getAbsolutePath());
			}

			String lOldSecurity = System.setProperty(lSecurityLiteral, lSecurity.getCanonicalPath());

			LOGGER.finer("Changed JVM " + lSecurityLiteral + ": " + lOldSecurity + " to " + System.getProperty(lSecurityLiteral));

			// create the command set, it comes by default with "help" and
			// "version"
			CmdLineSet lTestDriverCmdSet = new CmdLineSet();

			// add all the commands to the TestDriver command set
			lTestDriverCmdSet.add(new BuildCmdLine());
			lTestDriverCmdSet.add(new CancelCmdLine());
			lTestDriverCmdSet.add(new CleanRemoteCmdLine());
			lTestDriverCmdSet.add(new ConfigCmdLine());
			lTestDriverCmdSet.add(new CleanCmdLine());
			lTestDriverCmdSet.add(new EmulatorpackageCmdLine());
			lTestDriverCmdSet.add(new ImportCmdLine());
			lTestDriverCmdSet.add(new InstallCmdLine());
			lTestDriverCmdSet.add(new InstallPackageCmdLine());
			lTestDriverCmdSet.add(new JobstatusCmdLine());
			lTestDriverCmdSet.add(new MasterCmdLine());
			lTestDriverCmdSet.add(new MasterstatusCmdLine());
			lTestDriverCmdSet.add(new PackageCmdLine());
			lTestDriverCmdSet.add(new PrintCmdLine());
			lTestDriverCmdSet.add(new RbuildCmdLine());
			lTestDriverCmdSet.add(new ResultsCmdLine());
			lTestDriverCmdSet.add(new RunCmdLine());
			lTestDriverCmdSet.add(new RunremoteCmdLine());
			lTestDriverCmdSet.add(new ResultLocationCmdLine());
			lTestDriverCmdSet.add(new ValidateCmdLine());

			lTestDriverCmdSet.setNameVersionCopyRight(CONFIG.getName(), CONFIG.getVersion(), CONFIG.getCopyright());

			// parse the command and process the Operation associated with it
			Object lCommand = lTestDriverCmdSet.processCommand(args);

			if (lCommand == null) {
				LOGGER.log(Level.INFO, "STATUS: TestDriver finished with failure.");
				System.exit(-1);
			} else {
				LOGGER.log(Level.INFO, "STATUS: TestDriver finished with success.");
				
				if (CONFIG.getPreference(TDConfig.UCC_IP_ADDRESS) != null
						&& !CONFIG.getPreference(TDConfig.UCC_IP_ADDRESS).equalsIgnoreCase("")) {
					LOGGER.fine("Exiting for UCC with code: " + CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER));
					System.exit(CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER));

				} else {
					if (((CmdLine)lCommand).getCommand().equalsIgnoreCase("master")) {
						return;
					} else {
						System.exit(0);
					}
				}
			}

		} catch (Exception lException) {
			if (LOGGER != null) {
				LOGGER.log(Level.INFO, "STATUS: TestDriver failed: " + lException.getMessage(), lException);
			} else {
				System.err.println("STATUS: TestDriver failed: " + lException.getMessage());
			}
			System.exit(-1);
		}
	}
}
