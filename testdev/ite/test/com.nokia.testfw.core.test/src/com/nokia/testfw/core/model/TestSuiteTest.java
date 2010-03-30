/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of the License "Symbian Foundation License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
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

import com.nokia.testfw.core.model.TestResult.TestStatus;

/**
 * @author xiaoma
 *
 */
public class TestSuiteTest extends junit.framework.TestCase {
	
	TestSuite suite;
	
	protected void setUp() {
		suite = new TestSuite();
    }
	
	protected void tearDown() {
		suite = null;
	}
	
	public void testAddTestCase() {
		TestCase t1 = new TestCase("case1");
	    suite.addTestCase(t1);
	    TestCase t2 = new TestCase("case1");
	    suite.addTestCase(t2);
	    assertEquals(suite.getTestCases().size(), 2);
	    suite.toString();
	}

	public void testSuiteResult() {
		TestCase t1 = new TestCase("case1");
	    suite.addTestCase(t1);
	    TestCase t2 = new TestCase("case2");
	    suite.addTestCase(t2);
	    TestCase t3 = new TestCase("case3");
	    suite.addTestCase(t3);
	    TestCase t4 = new TestCase("case4");
	    suite.addTestCase(t4);
	    TestCase t5 = new TestCase("case5");
	    suite.addTestCase(t5);
	    
	    updateStatus(t1, TestStatus.SUCCESS);
	    updateStatus(t2, TestStatus.SUCCESS);
	    updateStatus(t3, TestStatus.FAILURE);
	    updateStatus(t4, TestStatus.SKIP);
	    
	    //check the counter, should be 
	    assertEquals(suite.getSuiteResult().getTestCount(), 5);
	    assertEquals(suite.getSuiteResult().getPassedTestCount(), 2);
	    assertEquals(suite.getSuiteResult().getFailedTestCount(), 1);
	    assertEquals(suite.getSuiteResult().getSkippedTestCount(), 1);
	}
	
	private void updateStatus(TestCase t, TestStatus status)
	{
	    t.start();
	    try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    t.stop(status, null);
	}
}
