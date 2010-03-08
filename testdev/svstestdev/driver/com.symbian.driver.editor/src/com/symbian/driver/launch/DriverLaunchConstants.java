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


package com.symbian.driver.launch;

/**
 * Constant definitions for plug-in com.symbian.driver.preferences
 */
public class DriverLaunchConstants {

	////////////
	// Main Tab
	
	// Driver Options
	public static final String DRIVER = "driver";
	public static final String RUN_TYPE = "runType";
	public static final int BUILD = 0;
	public static final int RUN = 1;
	public static final int BUILD_RUN = 2;
	
	// Build Options
	public static final String PLATFORM = "platform";
	public static final String ARMv5 = "armv5";
	public static final String ARM4 = "armv4";
	public static final String WINSCW = "winscw";
	public static final String WINS = "wins";
	public static final String THUMB = "thumb";
	public static final String VARIANT = "variant";
	public static final String UREL = "urel";
	public static final String UDEB = "udeb";
	
	// Transport Options
	
	public static final String SERIAL = "serial";
	public static final String BT = "bt";
	public static final String USB = "usb";
	public static final String TCP = "tcp";
	
	public static final String IP_ADDRESS = "ip";
	public static final String PORT = "port";
	
	
	////////////
	// Arguments Tab
	
	public static final String RBUILD = "rbuild";
	public static final String BLDMAKE = "bldmake";
	public static final String CLEAN = "clean";
	public static final String SYS_BIN = "sysBin";
	public static final String TEF_DEPS = "tefDep";

	public static final String TRANSPORT = "transport";
	public static final String KERNEL = "kernel";
	public static final String RDEBUG = "rdebug";
	public static final String COMMDB = "commdb";
	public static final String COMMDB_OVERWRITE = "commdbOverwrite";
	public static final String ENTRY_POINT_ADDRESS = "suite";
	
	public static final String ON = "on";
	public static final String OFF = "off";
	public static final String OVERWRITE = "overwrite";
	
	
	public static final String EPOC_ROOT = "epocRoot";
	public static final String RESULT_ROOT = "resultRoot";
	public static final String REPOSITORY_ROOT = "repositoryRoot";
	public static final String SOURCE_ROOT = "sourceRoot";

	
	//Launch config type ID
	public static final String LAUNCH_CONFIG_TYPE = "com.symbian.driver.editor.launchConfigurationType";

}
