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

package com.symbian.driver.core.environment;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.Epoc;

/**
 * @author Development Tools
 * 
 */
public final class ILiterals {

	private static final Logger LOGGER = Logger.getLogger(ILiterals.class.getName());
	public static String C = "c:";
	static {
		try {
			String lBuild = TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER);
			if (Epoc.is93plus(lBuild)) {
				C = "$:";
			}
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Config error: " , lParseException);
			//just go with the default c:
		}
	}
	
	public static String SYS_BIN = C + "\\sys\\bin\\";

	public static String SYS_DATA = C + "\\system\\data\\";

	public static String BLD_INF = "bld.inf";

	public static String WHAT = " -what";
	
	public static String SBS_WHAT = " --what";

	public static String EPOC32 = "epoc32";

	public static String RELEASE = "RELEASE";

	public static String SYSTEM = "system";

	public static String DATA = "data";

	public static String TARGET = "target ";

	public static String BUILD = "build ";

	public static String TEST = "test ";

	public static String REALLYCLEAN = "reallyclean";

	public static String BLDFILES = "bldfiles";

	public static String CLEAN = "clean";

	public static String BLDMAKE_BAT = "bldmake.bat ";

	public static String ABLD_BAT = "abld.bat ";
	
	public static String SBS_BAT = "sbs.bat ";

	public static String EXPORT = " export";

	public static String MAKEFILE = " makefile";

	public static String SIS = ".sis";

	public static String PKG = ".pkg";
	
	public static String TEST_RESULT = "TestResults.htm";
	
	public static String TEST_EXECUTE = "TestExecute.exe";

}
