/*
* Copyright (c) 2005 Nokia Corporation and/or its subsidiary(-ies).
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
* Description:  A tool that parse command line parser.
*
*/


package com.nokia.testfw.testrunner;

public class SymbianUnitTestCommandLineParser {
	
	public static final String TEST_CASE_KEY = "-cases=";
	public static final String TEST_CASE_KEY_SHORT = "-c=";
	public static final String TESTS_KEY = "-tests=";
	public static final String TESTS_KEY_SHORT = "-t=";
	public static final String EPOC_ROOT = "-epocroot=";
	public static final String EPOC_ROOT_SHORT = "-e=";
	public static final String ALLOC_KEY = "-alloc";
	public static final String ALLOC_KEY_SHORT = "-a";
	public static final String OUTPUT_KEY = "-output=";
	public static final String OUTPUT_KEY_SHORT = "-o=";
//	public static final String NO_PROMPT_KEY = "-noprompt";
	public static final String TIMEOUT_KEY = "-timeout=";
	public static final String TIMEOUT_KEY_SHORT = "-to=";
	public static final String DEFAULT_OUTPUT_FORMAT = "html";
	public static final String OUTPUT_FILE_NAME = "SymbianUnitTestResults";
	public static final String DEVICE_KEY = "-device";
	public static final String DEVICE_KEY_SHORT = "-d";
	
	public static final int SYMBIAN_UNIT_TEST_DEFAULT_TIMEOUT = 30;
	
	public static String findArgument(String[] arguments, String key, String shortKey) {
		boolean found = false;
		String value = null;
		
		if (arguments == null)
			throw new NullPointerException();
		
		for (int i = 0; i < arguments.length; ++i) {
			if (arguments[i].compareTo(key) == 0 || arguments[i].compareTo(shortKey) == 0) {
				int equalsPos = arguments[i].indexOf('=');
				if (equalsPos > 0 & equalsPos < arguments[i].length() - 1) {
					value = arguments[i].substring(equalsPos + 1);
					found = true;
				}
			}
		}
		return found? value : null;
	}
	
	public static boolean hasArgument(String[] arguments, String key, String shortKey){
		boolean found = false;
		for (int i = 0; i < arguments.length; ++i){
			if (arguments[i].compareTo(key) == 0 || arguments[i].compareTo(shortKey) == 0){
				found = true;
			}
		}
		return found;
	}

	public static String[] getTestCaseNames(String[] arguments){
			return findArgument(arguments, TEST_CASE_KEY, TEST_CASE_KEY_SHORT).split(",");
	}
	
	public static String[] getTestDllNames(String[] arguments){
		return findArgument(arguments, TESTS_KEY, TESTS_KEY_SHORT).split(",");
	}
	
	public static int getTimeout(String[] arguments) {
		String timeOut = findArgument(arguments, TIMEOUT_KEY, TIMEOUT_KEY_SHORT);
		if (timeOut == null)
			return SYMBIAN_UNIT_TEST_DEFAULT_TIMEOUT;
		else
			return Integer.parseInt(timeOut);
	}
	
	public static String getOutputFormat(String[] arguments){
		String outputFormat = findArgument(arguments, OUTPUT_KEY, OUTPUT_KEY_SHORT);
		if (outputFormat == null)
			outputFormat = DEFAULT_OUTPUT_FORMAT;
		return outputFormat;
	}
	
	public static String getOutputFileName(){
		return OUTPUT_FILE_NAME;
	}
	
	public static boolean getMemoryAllocationFailureSimulation(String[] arguments){
		return hasArgument(arguments, ALLOC_KEY, ALLOC_KEY_SHORT);
	}

	public static String getEpocRoot(String[] arguments){
		return findArgument(arguments, EPOC_ROOT, EPOC_ROOT_SHORT);
	}

	public static boolean getRunOnDevice(String[] arguments){
		return hasArgument(arguments, DEVICE_KEY, DEVICE_KEY_SHORT);
	}
}
