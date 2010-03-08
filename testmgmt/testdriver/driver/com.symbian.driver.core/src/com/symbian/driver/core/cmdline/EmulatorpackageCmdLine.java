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

import java.util.logging.Level;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.packaging.build.Builder;
import com.symbian.driver.remoting.packaging.build.BuilderFactory;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class representing the command line "emulatorpackage".
 * 
 * @author Engineering Tools
 * 
 */
public class EmulatorpackageCmdLine extends CmdLine {
	/**
	 * Standard constructor. Sets switches p,b,s,tp.
	 */
	public EmulatorpackageCmdLine() {
		super("emulatorpackage", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) {
				TDConfig.getInstance().setConfigFromCmdLine(aCmd);
				
				BuilderFactory lBldFact = BuilderFactory.newInstance();
				Builder lBld = lBldFact.newBuilder(Builder.EMULATOR_IMAGE);

				try {
					LOGGER.fine("Starting emulator packaging...");
					
					lBld.Build(TDConfig.getInstance().getPreferenceFile(TDConfig.TEST_PACKAGE));
					return aCmd;
				} catch (ParseException lParseException) {
					LOGGER.log(Level.SEVERE, lParseException.getMessage(), lParseException);
				}


				return null;
			}
			
		}, "Package the emulator image to be sent to a remote machine (DEPRECATED).");

		/*		addSwitch("p", true, "target platform eg. arm4", false, true);
		addSwitch("b", true, "target platform variant urel/udeb", false, true);
		addSwitch("s", true, "test/suite address", false, true);*/
		addSwitch("tp", false, "Test package absolute path. (Preference testPackage)", true, true);
	}
}
