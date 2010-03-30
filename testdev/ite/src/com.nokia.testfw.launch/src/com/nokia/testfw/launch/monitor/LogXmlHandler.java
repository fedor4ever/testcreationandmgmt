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
package com.nokia.testfw.launch.monitor;

import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.IStatus;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestSuiteResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.launch.TFWLaunchPlugin;

/**
 * @author xiaoma
 * 
 */
public class LogXmlHandler extends DefaultHandler {

	TestRunResult result;
	TestSuiteResult curSuiteResult;
	TestCaseResult curCaseResult;
	boolean isDefinition = false;
	String suiteStartTime;
	String caseStartTime;
	boolean isSuite = false;
	boolean isCase = false;
	boolean isStartTime = false;
	boolean isFinishTime = false;

	public LogXmlHandler(TestRunResult result) {
		this.result = result;
		curSuiteResult = null;
		curCaseResult = null;
	}

	public void startDocument() throws SAXException {
		TFWLaunchPlugin.log(IStatus.INFO, "start process result xml log");
		result.testStarted();
	}

	public void endDocument() throws SAXException {
		TFWLaunchPlugin.log(IStatus.INFO, "end process result xml log");
		result.testFinished();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("testsuite")) {
			processTestSuiteStart(attributes);
			isSuite = true;
		} else if (qName.equals("test")) {
			processTestStart(attributes);
			isCase = true;
		} else if (qName.equals("status")) {
			processTestStatus(attributes);
		} else if (qName.equals("location")) {
			processLocation(attributes);
		} else if (qName.equals("definition")) {
			isDefinition = true;
		} else if (qName.equals("started")) {
			isStartTime = true;
		} else if (qName.equals("finished")) {
			isFinishTime = true;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("testsuite")) {
			curSuiteResult = null;
			isSuite = false;
		} else if (qName.equals("test")) {
			curCaseResult = null;
			isCase = false;
		} else if (qName.equals("definition")) {
			isDefinition = false;
		} else if (qName.equals("started")) {
			isStartTime = false;
		} else if (qName.equals("finished")) {
			isFinishTime = false;
		}
	}

	private void processTestSuiteStart(Attributes attributes) {
		String name = attributes.getValue("name");
		curSuiteResult = result.addTestSuite(name);
	}

	private void processTestStart(Attributes attributes) {
		String suiteName = null;
		if (curSuiteResult != null) {
			suiteName = curSuiteResult.getName();
		}
		String caseName = attributes.getValue("name");
		curCaseResult = result.addTestCase(suiteName, caseName);
		// start test case
		result.updateCaseStatus(suiteName, caseName, TestStatus.STARTED, 0);

	}

	private void processTestStatus(Attributes attributes) {
		String type = attributes.getValue("type");
		TestStatus status = null;
		if (type.equals("pass")) {
			status = TestStatus.SUCCESS;
		} else if (type.equals("crash")) {
			status = TestStatus.FAILURE;
		} else if (type.equals("fail")) {
			status = TestStatus.FAILURE;
		} else {
			System.err.println("unknow status status:" + type);
		}

		result.updateCaseStatus(curSuiteResult.getName(), curCaseResult
				.getName(), status, 0);
		// the current schema has problem
	}

	private void processLocation(Attributes attributes) {
		if (!isDefinition) {
			return;
		}
		TestResult tr = null;
		if (curCaseResult != null) {
			tr = curCaseResult;
		} else if (curSuiteResult != null) {
			tr = curSuiteResult;
		}
		String file = attributes.getValue("file");
		String line = attributes.getValue("line");
		String col = attributes.getValue("column");

		if (tr != null) {
			tr.setFile(file);
			if (line != null) {
				tr.setLine(Integer.parseInt(line));
			}
			if (col != null) {
				tr.setColumn(Integer.parseInt(col));
			}
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (isStartTime) {
			String time = new String(ch, start, length);
			// processStartTime
			if (isCase) {
				caseStartTime = time;
			} else if (isSuite) {
				suiteStartTime = time;
			}
		} else if (isFinishTime) {

			try {
				// process finish time
				String time = new String(ch, start, length);
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				if (isCase) {
					int duration = (int) (formatter.parse(time).getTime() - formatter
							.parse(caseStartTime).getTime()) / 1000;
					curCaseResult.setTime(duration);
				} else if (isSuite) {
					int duration = (int) (formatter.parse(time).getTime() - formatter
							.parse(suiteStartTime).getTime()) / 1000;
					curSuiteResult.setTime(duration);
				}
			} catch (Exception e) {
				TFWLaunchPlugin.log(e);
				e.printStackTrace();
			}
		}
	}

}
