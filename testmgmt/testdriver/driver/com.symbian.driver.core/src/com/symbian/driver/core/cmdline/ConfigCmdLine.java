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



package com.symbian.driver.core.cmdline;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;
import com.symbian.utils.cmdline.argscheckers.AlphaNumericData;
import com.symbian.utils.cmdline.argscheckers.NumericData;

/**
 * @author EngineeringTools
 * 
 */
public class ConfigCmdLine extends CmdLine {
		
	/**
	 * Standard constructor.
	 */
	public ConfigCmdLine() {
		super("config", new CmdOperational() {

			public Object run(CmdLine aCmd) throws ParseException {
				try {
					if (aCmd.getCommandLine().iterator().hasNext()) {
						TDConfig.getInstance().setConfigFromCmdLine(aCmd);
					}
					
					TDConfig.getInstance().printConfig(false);
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, lIOException.getMessage(), lIOException);
					return null;
				}
					
				return aCmd;
			}
			
		}, "Configure TestDriver.");
		
		AlphaNumericData lAlphaNumericCheck = new AlphaNumericData();
		NumericData lNumericData = new NumericData();
		addSwitch("e", true, "Location of the EPOC drive. (Variable: ${epocroot}, Preference epocRoot)", false, true);
		addSwitch("x", true, "Location of where TestDriver v1 saved its XML files. (Variable: ${xmlroot}, Preference xmlRoot)", false, true);
		addSwitch("repos", false, "Location of where to save the repository for TestDriver. (Variable: ${repositoryroot}, Preference repositoryRoot)", false, true);
		addSwitch("c", true, "Location of where to save the results & logs. (Variable: ${resultroot}, Preference resultRoot)", false, true);
		addSwitch("source", false, "Location of the directory containing the source files. (Variable: ${sourceroot}, Preference sourceRoot)", false, true);
		addSwitch("platsec", false, "Platform Security (PlatSec): ON, OFF. (Preference platsec) DEPRECATED", false, true);
		addSwitch("bldmake", false, "If bldmake bldfiles is run: ON, OFF. (Preference bldmake)", false, true);
		addSwitch("bldclean", false, "If clean is run with bldmake.bat and abld.bat: ON, OFF. (Preference clean)", false, true);
		addSwitch("testexec", false, "Transfer Test Execute Framework (TEF) Dependencies: ON, OFF. (Preference testExecute)", false, true);
		addSwitch("import", false, "Import the configuration from a preference file.", false, true);
		addSwitch("export", false, "Export the configuration to a preference file.", false, true);
		addSwitch("clear", false, "Clear the configuration backstore", false, false);
		addSwitch("server", false, "SERVER: Remote server name. (Preference server)", false, true);
		addSwitch("service", false, "SERVER: Remote service name. (Preference service)", false, true);
		addSwitch("jobs", false, "SERVER: Remote jobs folder. (Preferences jobsid)", false, true);
		addSwitch("port", false, "SERVER: Port to peform rom flashing with trgtest.exe. (Preference trgtest)", false, true);
		addSwitch("t", true, "Transport protocol used to link with hardware board. (Preference transport)", false, true);
		addSwitch("uid", false, "The UID range to use with TestDriver in the format 0xFIRST:0xLAST, where FIRST and LAST are hexadecimal numbers. (Preference uidFirst, uidLast)", false, true);
		addSwitch("key", false, "The key to use when signing TestDriver sis files. (Preference key)", false, true);
		addSwitch("cert", false, "The certificate to use when signing TestDriver sis files. (Preference cert)", false, true);
		addSwitch("testtimelimit", false, "The total test time limit. (Preference testtimelimit)", false, lNumericData);
		addSwitch("tefport", false, "COM port to recieve TEF logs over", false, lAlphaNumericCheck);
		addSwitch("testbuild", false, "Set if test building", false, true);
		addSwitch("sisroot", false, "Specify the location of the sis packages on Device", false, true);
		addSwitch("sbs", false, "The SBS version: v1 or v2. (Preference sbs)", false, true);
		
		// Old Build Commands
        addSwitch("b", true, "The build target variant/build: udeb, urel. (Preference variant)", false, lAlphaNumericCheck);
        addSwitch("s", true, "Address of root task to run. (Preference entryPointAdress)", false, true);
        addSwitch("p", true, "The build target platform: armv5, arm4, thumb, winscw, wins. (Preference platform)", false, lAlphaNumericCheck);
        addSwitch("sysbin", false, "Allows copying to SYS/BIN with STATLite. (Preference sysbin) DEPRECATED: use --statlite", false, true);
        addSwitch("statlite", false, "Using STAT lite. (Preference statlite)", false, true);
        addSwitch("teflite", false, "Using TEF lite. (Preference teflite)", false, true);
        
        // Old Run Commands
        addSwitch("buildNumber", false, "OS build number. (Preference buildNumber)", false, true);
        addSwitch("k", true, "Kernel EKA1/EKA2. (Preference kernel)", false, true);
        addSwitch("commdb", false, "Intiatialise CommDB with WinTAP for the emulator. Use ON/OVERWRITE/OFF. (Preference commdb)", false, true);
		addSwitch("recovery", false, "Trigger to support crash recovery. Use ON/OFF. (Preference recovery)", false, true);
        
        // Old Server Commands
        addSwitch("tp", false, "Test package absolute path. (Preference testPackage)", false, true);
        addSwitch("j", true, "Unique Job ID. (Preference jobId)", false, true);
        addSwitch("srv", false, "Remote host/IP address and service name. (Preference server)", false, true);
        addSwitch("m", true, "Results reception mode sync/async. (Preference mode)", false, true);
        addSwitch("i", true, "Working directory. (Preference workingPath)", false, true);
        addSwitch("r", true, "ROM image. (Preference ROM)", false, true);
        addSwitch("cl", false, "Unique client name. (Preference client)", false, true);
        addSwitch("plugin", false, "Plugin config setting in the form PluginId:Key=Value", false, true);
        addSwitch("commplugin", false, "Communication plugin ID (defaults to STAT)", false, true);
		addSwitch("sigalgorithm", false, "Signature Algorithm RSA/DSA (default to RSA).", false, true);
				
	}
	
	/**
	 * Implements additional constraints on switches.
	 * 
	 * @param aCommandLine
	 *            {@inheritDoc}
	 */
	public void checkAdditionalConstraints(final CommandLine aCommandLine) {
		if (aCommandLine.hasOption("cert") != aCommandLine.hasOption("key")) {
			LOGGER.severe("Certificate and key should be specified as a pair or none.");
		} else if (aCommandLine.hasOption("cert")) {
			File lCert = new File(aCommandLine.getOptionValue("cert"));
			if (!lCert.isFile() || !lCert.canRead()) {
				LOGGER.severe("Certificate file is not accessible.");
			}
			File lKey = new File(aCommandLine.getOptionValue("key"));
			if (!lKey.isFile() || !lKey.canRead()) {
				LOGGER.severe("Key file is not accessible.");
			}
		}

		if (aCommandLine.hasOption("uid") 
				&& aCommandLine.getOptionValue("uid").indexOf(':') == -1) {
			LOGGER.severe("UID should be specified as a pair separated by a ':' 0xFIRST:0xLAST");
		}
	}
}
