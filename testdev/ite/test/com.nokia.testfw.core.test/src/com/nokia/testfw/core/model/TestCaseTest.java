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

import junit.framework.TestCase;

/**
 * @author xiaoma
 *
 */
public class TestCaseTest extends TestCase {
    com.nokia.testfw.core.model.TestCase testCase;
    
    protected void setUp() {
		testCase = new com.nokia.testfw.core.model.TestCase("Test1");
    }
	
	protected void tearDown() {
		testCase = null;
	}
	
	public void testProperty() {
		testCase.addProperty("key1", "value1");
		testCase.addProperty("key2", "value2");
		assertEquals("value1", testCase.getProperty("key1"));
		assertEquals("value2", testCase.getProperties().get("key2"));
	}
	
	public void testAttributes() {
		assertEquals("Test1", testCase.getIdentifier());
		testCase.setIdentifier("Test2");
		assertEquals("Test2", testCase.getIdentifier());
		
		String version="v0.1";
		testCase.setVersion(version);
		assertEquals(version, testCase.getVersion());
		
		String desc="model test caes";
		testCase.setDescription(desc);
		assertEquals(desc, testCase.getDescription());
		
		String spec="REQ001";
		testCase.setSpecRef(spec);
		assertEquals(spec, testCase.getSpecRef());
		
		String purpose = "reason1";
		testCase.setPurpose(purpose);
		assertEquals(purpose, testCase.getPurpose());
		testCase.toString();
		
	}
	
	public void testEqual() {
		testCase.setIdentifier("case1");
		com.nokia.testfw.core.model.TestCase case1 = new com.nokia.testfw.core.model.TestCase("case1");
		com.nokia.testfw.core.model.TestCase case2 = new com.nokia.testfw.core.model.TestCase("case2");
		assertTrue(testCase.equals(case1));
		assertFalse(testCase.equals(case2));
	}
	
	public void testStartStop() {
		//test success case
		com.nokia.testfw.core.model.TestSuite suite = new com.nokia.testfw.core.model.TestSuite();
		testCase.setSuite(suite);
		testCase.getSuite();
		testCase.start();
		testCase.stop(TestStatus.SUCCESS, null);
		
		//check test result
		TestResult result = testCase.getResult();
		result.toString();
		assertTrue(result.getStartTime() != 0);
		assertTrue(result.getEndTime() != 0);
		assertTrue(result.status == TestStatus.SUCCESS);
	
		//test failure case
		com.nokia.testfw.core.model.TestCase case2 = new com.nokia.testfw.core.model.TestCase("case2");
		case2.setSuite(suite);
		case2.start();
		case2.stop(TestStatus.FAILURE, "assert error");
		//check test result
		result = case2.getResult();
		assertTrue(result.getStartTime() != 0);
		assertTrue(result.getEndTime() != 0);
		assertTrue(result.status == TestStatus.FAILURE);
	    assertNotNull(result.getFailure());
		
	}
	
    
}
