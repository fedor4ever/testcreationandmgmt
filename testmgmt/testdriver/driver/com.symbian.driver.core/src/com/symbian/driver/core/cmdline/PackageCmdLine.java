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

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.packaging.build.Builder;
import com.symbian.driver.remoting.packaging.build.BuilderFactory;
import com.symbian.utils.Epoc;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class representing the commnad line 'package'.
 * 
 * @author EngineeringTools
 */
public class PackageCmdLine extends CmdLine {
	
	/**
	 * Standard constructor. Sets the switches -p,-b,-s and --tp.
	 */
	public PackageCmdLine() {
		super("package", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) throws ParseException {
				TDConfig CONFIG = TDConfig.getInstance();
				CONFIG.setConfigFromCmdLine(aCmd);
				String lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);

				if (Epoc.isEka1(lBuildNumber)) {
					CONFIG.setPreference(TDConfig.KERNEL, "EKA1");
				} else {
					CONFIG.setPreference(TDConfig.KERNEL, "EKA2");
				}

				File testPackage = CONFIG.getPreferenceFile(TDConfig.TEST_PACKAGE);

				BuilderFactory bldFact = BuilderFactory.newInstance();
				Builder bld = bldFact.newBuilder(Builder.PACKAGE);
				
				bld.Build(testPackage);

				return aCmd;
			}
			
		}, "build a test package.");
		
		addSwitch("p", true, "The build target platform: armv5, arm4, thumb, winscw, wins. (Preference platform)", false, true);
		addSwitch("b", true, "The build target variant/build: udeb, urel. (Preference variant)", false, true);
		addSwitch("s", true, "Address of root task to run. (Preference entryPointAddress)", false, true);
		addSwitch("tp", false, "Test package absolute path. (Preference testPackage)", false, true);
	}

}
