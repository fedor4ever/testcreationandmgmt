/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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
package com.nokia.s60tools.testdrop.util;

import java.io.File;

import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.testrun.TestRun;
import com.nokia.s60tools.testdrop.testrun.ValidationResult;
import com.nokia.s60tools.testdrop.testrun.ValidationResult.ValidationProblem;

/**
 * A helper class that says if a test run (emulator) fulfills conditions to succeed.
 *
 */
public class TestRunValidator {
	
	/**
	 * Checks if test run contains path to existing atsinterface.exe..
	 * Checks if test run contains path to existing test module's dll.
	 * Checks if test run contains path to existing testframework.ini.
	 * 
	 * @param testRun
	 * 			test run to be checked
	 * @return
	 * 			result of a check
	 */
	public static ValidationResult validateTestRun(TestRun testRun) {
		File atsInterfaceFile = new File(testRun.getATSInterfaceFilePath());
		if (!atsInterfaceFile.exists()) {
			return new ValidationResult(ValidationProblem.NO_ATSINTERFACE_FILE, Messages
					.getString("TestRunValidator.noAtsInterfaceFile"));
		}
		
		if (testRun.getModuleType() != ModuleType.TESTCOMBINER) {
			File testModuleFile = new File(testRun.getTestModuleFilePath());
			if (!testModuleFile.exists()) {
				return new ValidationResult(ValidationProblem.NO_TEST_MODULE_FILE, Messages
						.getString("TestRunValidator.noTestModuleFile"));
			}
		}
		
		File testFrameworkIniFile = new File(testRun.getIniRootPath() + "\\testframework.ini");
		if (!testFrameworkIniFile.exists()) {
			return new ValidationResult(ValidationProblem.NO_INI_FILE, Messages
					.getString("TestRunValidator.noIniFile"));
		}
		
		return new ValidationResult(ValidationProblem.NO_PROBLEM, null);
	}
}
