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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class representing the command line "install".
 * 
 * @author EngineeringTools
 * 
 */
public class InstallCmdLine extends CmdLine {
	
	
	/**
	 * Standard constructor.
	 */
	public InstallCmdLine() {
		super("install", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) {
				String lInput = null;
				BufferedReader lInputBuffer = new BufferedReader(new InputStreamReader(System.in));
				TDConfig CONFIG = TDConfig.getInstance();
				try {
					System.out.print("Please enter the drive (eg. j:) of your epoc32 tree:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, new File(lInput));
					}
					System.out.print("Please enter the path to your test XML:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceFile(TDConfig.XML_ROOT, new File(lInput));
					}
					System.out.print("Please enter the path to your repository:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceFile(TDConfig.REPOSITORY_ROOT, new File(lInput));
					}
					System.out.print("Please enter the path to where you want to store the results:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceFile(TDConfig.RESULT_ROOT, new File(lInput));
					}
					System.out.print("Please enter the root path for your source:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceFile(TDConfig.SOURCE_ROOT, new File(lInput));
					}
					System.out.print("Please specify ON / OFF for PlatSec:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceBoolean(TDConfig.PLATSEC, lInput.equalsIgnoreCase("on") || lInput.equalsIgnoreCase("true"));
					}
					System.out.print("Please specify ON / OFF for TestExecute:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceBoolean(TDConfig.TEST_EXECUTE, lInput.equalsIgnoreCase("on") || lInput.equalsIgnoreCase("true"));
					}
					System.out.print("Please specify ON / OFF to run bldmake when running build:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceBoolean(TDConfig.BLDMAKE, lInput.equalsIgnoreCase("on") || lInput.equalsIgnoreCase("true"));
					}
					System.out.print("Please specify ON / OFF to run clean when running build:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreferenceBoolean(TDConfig.CLEAN, lInput.equalsIgnoreCase("on") || lInput.equalsIgnoreCase("true"));
					}
					System.out.print("Please specify the default platform:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreference(TDConfig.PLATFORM, lInput);
					}
					System.out.print("Please specify the default variant/build:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreference(TDConfig.VARIANT, lInput);
					}
					System.out.print("Please specify the default transport:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreference(TDConfig.TRANSPORT, lInput);
					}
					System.out.print("Please specify the default UCC IP address:");
					lInput = lInputBuffer.readLine();
					if (lInput.length() != 0) {
						CONFIG.setPreference(TDConfig.UCC_IP_ADDRESS, lInput);
					}
					return aCmd;

				} catch (ParseException lParseException) {
					LOGGER.log(Level.SEVERE, lParseException.getMessage(), lParseException);
				} catch (IOException lException) {
					LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
				}

				return null;
			}
			
		}, "Install TestDriver. (DEPRECATED)");
	}

}
