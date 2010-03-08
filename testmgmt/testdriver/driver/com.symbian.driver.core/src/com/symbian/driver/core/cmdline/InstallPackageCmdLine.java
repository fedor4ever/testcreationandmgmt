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
import com.symbian.driver.remoting.packaging.installer.Installer;
import com.symbian.driver.remoting.packaging.installer.InstallerFactory;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class representing the command line 'installpackage'.
 * 
 * @author EngineeringTools
 */

public class InstallPackageCmdLine extends CmdLine {
	
	/**
	 * Standard constructor. Set the switch --tp.
	 */
	public InstallPackageCmdLine() {
		super("installpackage", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) throws ParseException {
				TDConfig.getInstance().setConfigFromCmdLine(aCmd);
				File lPackage = TDConfig.getInstance().getPreferenceFile(TDConfig.TEST_PACKAGE);

				InstallerFactory instFact = InstallerFactory.newInstance();
				Installer inst = instFact.newInstaller(Installer.PACKAGE);

				inst.Install(lPackage);

				return aCmd;
			}
			
		}, "install a test package locally.");
		
		addSwitch("tp", false, "Test package absolute path. (Preference testPackage)", true, true);
		addSwitch("buildNumber", false, "Buid Number to run package with. (Preference buildNumber)", false, true);
	}
}
