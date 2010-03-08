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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import com.nokia.s60tools.testdrop.engine.value.EmulatorTestResultValue;
import com.nokia.s60tools.testdrop.engine.value.TestResultValue;
import com.nokia.s60tools.testdrop.resources.Messages;

/**
 * The class that produces a test result in a form simmilar to 
 * how ATS produces it. Data from testreport.xml file (from atsinterface.exe - emulator)
 * is inserted into a html template.
 *
 */
public class TestReportModifier {
	
	private final static String testRunPlaceholder = "[test run]";
	private final static String emulatorTimePlaceholder = "[emulator time]";
	private final static String startTimePlaceholder = "[start time]";
	private final static String endTimePlaceholder = "[end time]";
	private final static String overallResultPlaceholder = "[overall result]";
	private final static String totalPlaceholder = "[total]";
	private final static String passedPlaceholder = "[passed]";
	private final static String failedPlaceholder = "[failed]";
	private final static String crashedPlaceholder = "[crashed]";
	private final static String timeoutedPlaceholder = "[timeout]";
	private final static String testCaseResultPlaceholder = "<!-- test cases results -->";
	private final static String testModulePlaceholder = "[testmodule]";
	private final static String testModuleResultPlaceholder = "[testmoduleresult]";
	private final static String passedWidthPlaceholder = "[passedWidth]";
	private final static String failedWidthPlaceholder = "[failedWidth]";
	
	private final static String templatePath = "/templates/TestReport.html";
	private final static String testCaseLine = "<div class=\"[result]\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&raquo;&nbsp;[testcase]&nbsp;</div>";
	
	private final static String resultPlaceholder = "[result]";
	private final static String testCasePlaceholder = "[testcase]";
	
	private final static String statusPassed = "PASSED";
	private final static String statusFailed = "FAILED";
	
	private final static String faultyTemplateExceptionMessage = Messages
			.getString("TestReportModifier.faultyTemplateExceptionMessage");
	
	/**
	 * Method that takes data from testreport.xml and puts it into the template.
	 * This is needed to display results in emulator in a form simmilar to what
	 * ATS server produces
	 * 
	 * @param testResultValue
	 * 			test report in xml form
	 * @return
	 * 			test report as html
	 * @throws ParseException
	 * 			is thrown when the template does not contain all expected 
	 * 			placeholders
	 */
	public static String transformReport(TestResultValue testResultValue) throws ParseException {
		if (!(testResultValue instanceof EmulatorTestResultValue)) {
			throw new IllegalArgumentException();
		}
		EmulatorTestResultValue emulatorTestResultValue = (EmulatorTestResultValue)testResultValue;
		String htmlReport = "";
		try {
			InputStream inputStream = TestReportModifier.class.getResourceAsStream(templatePath);
			BufferedReader templateReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			while ((line = templateReader.readLine()) != null) {
				htmlReport += line;
			}
			templateReader.close();
		}
		catch (IOException ex) {
			return null;
		}
		
		if (htmlReport.indexOf(testRunPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(testRunPlaceholder, emulatorTestResultValue.getTestNameAndTestId());
		
		if (htmlReport.indexOf(emulatorTimePlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(emulatorTimePlaceholder, emulatorTestResultValue.getEndTime());
		
		if (htmlReport.indexOf(startTimePlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(startTimePlaceholder, emulatorTestResultValue.getFirstTestCaseStartTime());
			
		if (htmlReport.indexOf(endTimePlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(endTimePlaceholder, emulatorTestResultValue.getLastTestCaseEndTime());
		
		if (htmlReport.indexOf(overallResultPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		if (testResultValue.getPassrate() == 100) {
			htmlReport = htmlReport.replace(overallResultPlaceholder, statusPassed);
		}
		else {
			htmlReport = htmlReport.replace(overallResultPlaceholder, statusFailed);
		}
		
		if (htmlReport.indexOf(totalPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(totalPlaceholder, Integer.toString(emulatorTestResultValue.getTotal()));
		
		if (htmlReport.indexOf(passedPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(passedPlaceholder, emulatorTestResultValue.getPassedAsString());
		
		if (htmlReport.indexOf(failedPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(failedPlaceholder, emulatorTestResultValue.getFailedAsString());
		
		if (htmlReport.indexOf(crashedPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(crashedPlaceholder, emulatorTestResultValue.getCrashedAsString());
		
		if (htmlReport.indexOf(timeoutedPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(timeoutedPlaceholder, emulatorTestResultValue.getTimeoutedAsString());
		
		if (htmlReport.indexOf(testModulePlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(testModulePlaceholder, emulatorTestResultValue.getTestName());
		
		if (htmlReport.indexOf(testModuleResultPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(testModuleResultPlaceholder, emulatorTestResultValue.getOverallResult());
		
		if (htmlReport.indexOf(passedWidthPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(passedWidthPlaceholder, Integer.toString
				((int)(emulatorTestResultValue.getPassrate() * 1.5)));
		
		if (htmlReport.indexOf(failedWidthPlaceholder) < 0) {
			throw new ParseException(faultyTemplateExceptionMessage, 0);
		}
		htmlReport = htmlReport.replace(failedWidthPlaceholder, Integer.toString
				((int)(150 - emulatorTestResultValue.getPassrate() * 1.5)));

		
		ArrayList<TestCaseResult> testCasesResults = emulatorTestResultValue.getTestCasesResults();
		Iterator<TestCaseResult> testCasesIterator = testCasesResults.iterator();
		TestCaseResult testCaseResult = null;
		StringBuffer buffer = new StringBuffer(htmlReport);
		while (testCasesIterator.hasNext()) {
			testCaseResult = testCasesIterator.next();
			String filledTestCaseLine = testCaseLine.replace(resultPlaceholder, testCaseResult.getResultAsString());
			filledTestCaseLine = filledTestCaseLine.replace(testCasePlaceholder, testCaseResult.getTestCaseName());
			
			int indexOfTestCaseResultPlaceholder = buffer.indexOf(testCaseResultPlaceholder);
			if (indexOfTestCaseResultPlaceholder < 0) {
				break;
			}
			buffer.insert(indexOfTestCaseResultPlaceholder, filledTestCaseLine);
		}
		htmlReport = buffer.toString();
		return htmlReport;
	}
}
