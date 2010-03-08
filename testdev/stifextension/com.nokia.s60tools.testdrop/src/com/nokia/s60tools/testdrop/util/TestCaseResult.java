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

/**
 * The class for storing result of a test case (emulator)
 *
 */
public class TestCaseResult {
	
	private final String testCaseName;
	private final Result result;
	
	private final static String passedLowerCase = "passed";
	private final static String failedLowerCase = "failed";
	private final static String crashedLowerCase = "crashed";
	private final static String timeoutLowerCase = "timeout";
	
	/**
	 * Constructor
	 * 
	 * @param testCaseName
	 * 				Name of test case
	 * @param result
	 * 				Result of test case as a Result object
	 */
	public TestCaseResult(String testCaseName, Result result) {
		this.testCaseName = testCaseName;
		this.result = result;
	}
	
	/**
	 * Constructor
	 * 
	 * @param testCaseName
	 * 				Test case name
	 * @param result
	 * 				Result of test case as a string
	 */
	public TestCaseResult(String testCaseName, String result) {
		this.testCaseName = testCaseName;
		String givenResult = result.toLowerCase();
		if (givenResult.equals(passedLowerCase)) {
			this.result = Result.PASSED;
		}
		else if (givenResult.equals(failedLowerCase)) {
			this.result = Result.FAILED;
		}
		else if (givenResult.equals(crashedLowerCase)) {
			this.result = Result.CRASHED;
		}
		else if (givenResult.equals(timeoutLowerCase)) {
			this.result = Result.TIMEOUT;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Getter for test case name
	 * 
	 * @return
	 * 		Test case name
	 */
	public String getTestCaseName() {
		return testCaseName;
	}
	
	/**
	 * Getter for result
	 * 
	 * @return
	 * 		Result as Result object
	 */
	public Result getResult() {
		return result;
	}
	
	/**
	 * Getter for result
	 * 
	 * @return
	 * 		Result as string
	 */
	public String getResultAsString() {
		switch (result) {
		case PASSED :
			return passedLowerCase;
		case FAILED :
			return failedLowerCase;
		case CRASHED :
			return crashedLowerCase;
		case TIMEOUT :
			return timeoutLowerCase;
		}
		return null;
	}
	
	/**
	 * Enum for describing the overall result of test case
	 *
	 */
	public enum Result {
		PASSED,
		FAILED,
		CRASHED,
		TIMEOUT
	}
}
