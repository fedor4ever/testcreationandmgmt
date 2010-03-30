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
package com.nokia.testfw.core.model;

import java.util.ArrayList;
import java.util.Collection;

import com.nokia.testfw.core.model.TestResult.TestStatus;

/**
 * A test suite is a collection of test cases that are intended to be used 
 * to test software program to show that it has some specified set of behaviours.
 * @see TestCase
 *
 */
public class TestSuite extends Test {

	protected Collection<TestCase> testCases = new ArrayList<TestCase>();
	protected TestSuiteResult suiteResult = new TestSuiteResult();
	
	/**
	 * @return the suiteResult
	 */
	public TestSuiteResult getSuiteResult() {
		return suiteResult;
	}

	/**
	 * get all test cases or suite defined in this test suite
	 * @return testcases
	 */
	public Collection<TestCase> getTestCases() {
		return testCases;
	}
	
	/**
	 * add a new test case
	 * @param tescase
	 */
	public void addTestCase(TestCase testcase) {
		testCases.add(testcase);
		suiteResult.testCount ++;
		testcase.setSuite(this);
	}
	
	public void updateResult(TestStatus status) {
		if (status == TestStatus.FAILURE) {
			suiteResult.failedTestCount++;
		} else if (status == TestStatus.SKIP) {
			suiteResult.skippedTestCount++;
		} else if (status == TestStatus.SUCCESS) {
			suiteResult.passedTestCount++;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[testsuite]");
		if (name != null) {
		  sb.append(" name:" + name);
		}
//		if (title != null) {
//			  sb.append(" title:" + title);
//		}
		for (Test c : testCases) {
			sb.append(c.toString());
		}
		return sb.toString();
	}
	
}
