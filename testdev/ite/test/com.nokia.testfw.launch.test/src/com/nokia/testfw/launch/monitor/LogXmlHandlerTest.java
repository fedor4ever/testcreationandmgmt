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
package com.nokia.testfw.launch.monitor;

import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestSuiteResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;

import junit.framework.TestCase;

/**
 * @author xiaoma
 * 
 */
public class LogXmlHandlerTest extends TestCase {

	static final String TEST_LOG_FILE = "/resource/stflog1.xml";
	static final String TEST_LOG_FILE2 = "/resource/stflog2.xml";
	InputStream is = null;
	TestRunResult result;

	protected void setUp() throws Exception {
		result = new TestRunResult();

		DefaultHandler handler = new LogXmlHandler(result);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			is = this.getClass().getClassLoader().getResourceAsStream(
					TEST_LOG_FILE);
			parser.parse(is, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void tearDown() throws Exception {
		is.close();
		result = null;
	}

	public void testParse() {
		TestResult[] results = result.getResults();
		assertTrue(results[0] instanceof TestSuiteResult);
		TestSuiteResult suiteResult = (TestSuiteResult) results[0];
		assertEquals(suiteResult.getName(), "AllTests");

		TestResult caseResult = suiteResult.getChildren().iterator().next();
		assertEquals(caseResult.getName(), "TestFirstMethod");

		statusTest(caseResult);
		locationTest(caseResult, suiteResult);
		startEndTimeTest(caseResult, suiteResult);
	}

	public void statusTest(TestResult caseResult) {
		assertEquals(caseResult.getStatus(), TestStatus.FAILURE);
		assertEquals(result.getfailedTestCount(), 1);
	}

	public void locationTest(TestResult caseResult, TestSuiteResult suiteResult) {
		assertEquals(caseResult.getFile(), "toplevelsuite.cpp");
		assertEquals(caseResult.getLine(), 46);
		assertEquals(caseResult.getColumn(), 16);

		assertEquals(suiteResult.getFile(), "toplevelsuite.cpp");
		assertEquals(suiteResult.getLine(), 14);
		assertEquals(suiteResult.getColumn(), 8);
	}

	public void startEndTimeTest(TestResult caseResult,
			TestSuiteResult suiteResult) {
		assertEquals(caseResult.getTime(), 2.0);
		assertEquals(suiteResult.getTime(), 2.0);
	}

	public void testPartialLog() {

		TestRunResult result2 = new TestRunResult();
		DefaultHandler handler = new LogXmlHandler(result2);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			is = this.getClass().getClassLoader().getResourceAsStream(
					TEST_LOG_FILE2);
			parser.parse(is, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}

		TestResult[] results = result2.getResults();
		assertTrue(results[0] instanceof TestSuiteResult);
		TestSuiteResult suiteResult = (TestSuiteResult) results[0];
		assertEquals(suiteResult.getName(), "default");

		Iterator<TestResult> iter = suiteResult.getChildren().iterator();
		assertEquals(((TestResult) iter.next()).getName(), "FirstTest");
		assertEquals(((TestResult) iter.next()).getName(), "SecondTest");
		assertEquals(((TestResult) iter.next()).getName(), "ThirdTest");
		assertEquals(((TestResult) iter.next()).getName(), "FourthTest");
		assertEquals(((TestResult) iter.next()).getName(), "FifthTest");
	}
}
