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

package com.symbian.driver.core.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.symbian.driver.Rtest;
import com.symbian.driver.report.BaseReport;
import com.symbian.driver.report.GenericReport;
import com.symbian.driver.report.GenericResult;
import com.symbian.driver.report.ReportPackage;

/**
 * @author EngineeringTools
 *
 */
public class RTestResultParser implements ResultParser {
	
	// Patterns for RTest
	private static final Pattern RTEST_COMPLETE_PATTERN = Pattern.compile("test completed O.K.");
	private static final Pattern RTEST_FAIL_PATTERN = Pattern.compile("RTEST: Checkpoint-fail");
	
	private final Rtest iRTestObject;
	private final String iTestName;
	private final GenericReport iRTestReport;
	
	/**
	 * @param aEObject
	 * @param aTestReport
	 */
	public RTestResultParser(final EObject aEObject, BaseReport aTestReport) {
		iRTestObject = (Rtest) aEObject;
		iRTestReport = (GenericReport) aTestReport;
		iTestName = iRTestObject.getSymbianPath();
		iRTestReport.setName(iTestName);
		
		LOGGER.info("Parsing the RTest result file for: " + iRTestObject.getSymbianPath());
	}
	
	/**
	 * @throws IOException 
	 * @see com.symbian.driver.core.report.ResultParser#parseResults()
	 */
	public void parseResults(final int aResultRootLength) throws IOException {
		
		String lResultFile = iRTestObject.getResultFile();
			
			if (new File(lResultFile).isFile()) {
				iRTestReport.setLog(lResultFile);
				
				// Parse Results line by lne
				BufferedReader lBufferedReader = new BufferedReader(new FileReader(lResultFile));
				
				String lResultsLine = null;
				while ((lResultsLine = lBufferedReader.readLine()) != null) {
					Matcher lMatchTestComplete = RTEST_COMPLETE_PATTERN.matcher(lResultsLine);
					Matcher lMatchCheckpointFail = RTEST_FAIL_PATTERN.matcher(lResultsLine);
		
					try {
						if (lMatchTestComplete.find()) {
							iRTestReport.setResult(GenericResult.PASS_LITERAL);
						} else if (lMatchCheckpointFail.find()) {
							iRTestReport.setResult(GenericResult.ERROR_LITERAL);
						}
					} catch (Throwable lThrowable) {
						LOGGER.log(Level.SEVERE, "Could not parse line: " + lResultsLine, lThrowable);
					}
				}
			}
	}
	
	public void createEmptyReport() {
		iRTestReport.setLog(iRTestObject.getResultFile());
		iRTestReport.setResult(GenericResult.ERROR_LITERAL);
	}

	public void createSummary(EMap aInfo) {
		if (aInfo != null) {
			try {
				boolean lIsRTestPass = iRTestReport.getResult() == GenericResult.PASS_LITERAL;
				boolean lIsRTestSet = iRTestReport.eIsSet(ReportPackage.eINSTANCE.getGenericReport_Result());
				
				String lAttemptedTestStep = (String) aInfo.get(ATTEMPTED_TEST_STEP);
				aInfo.put(ATTEMPTED_TEST_STEP, new Integer(
						lAttemptedTestStep == null ? 0 : Integer.parseInt(lAttemptedTestStep)
						+ 1).toString());
				
				String lPassedTestStep = (String) aInfo.get(PASSED_TEST_STEP);
				aInfo.put(PASSED_TEST_STEP, new Integer(
						lPassedTestStep == null ? 0 : Integer.parseInt(lPassedTestStep)
						+ (lIsRTestPass ? 1 : 0)).toString());
				
				String lFailedTestStep = (String) aInfo.get(FAILED_TEST_STEP);
				aInfo.put(FAILED_TEST_STEP, new Integer(
						lFailedTestStep == null ? 0 : Integer.parseInt(lFailedTestStep)
						+ (!lIsRTestPass && lIsRTestSet ? 1 : 0)).toString());
				
				String lUnkownTestStep = (String) aInfo.get(UNKOWN_TEST_STEP);
				aInfo.put(UNKOWN_TEST_STEP, new Integer(
						lUnkownTestStep == null ? 0 : Integer.parseInt(lUnkownTestStep)
						+ (lIsRTestSet ? 0 : 1)).toString());
			} catch (Throwable lThrowable) {
				LOGGER.log(Level.SEVERE, "Could not create Summary for RTest.", lThrowable);
			}
		}
	}
}
