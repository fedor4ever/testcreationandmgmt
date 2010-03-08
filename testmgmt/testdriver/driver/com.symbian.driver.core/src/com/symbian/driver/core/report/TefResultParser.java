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

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.report.BaseReport;
import com.symbian.driver.report.ReportFactory;
import com.symbian.driver.report.TefReport;
import com.symbian.driver.report.TefTestCase;
import com.symbian.driver.report.TefTestCaseResult;
import com.symbian.driver.report.TefTestCaseSummary;
import com.symbian.driver.report.TefTestRunWsProgram;
import com.symbian.driver.report.TefTestRunWsProgramResult;
import com.symbian.driver.report.TefTestRunWsProgramSummary;
import com.symbian.driver.report.TefTestStep;
import com.symbian.driver.report.TefTestStepResult;
import com.symbian.driver.report.TefTestStepSummary;

/**
 * @author Engineering Tools
 * 
 */
public class TefResultParser implements ResultParser {

	// Patterns for Count
	private static final Pattern TEST_CASE_COUNT_PATTERN = Pattern.compile("START_TESTCASE COUNT" + ResultParser.DOUBLEDOT, Pattern.CASE_INSENSITIVE);
	private static final Pattern TEST_STEP_COUNT_PATTERN = Pattern.compile("RUN_TEST_STEP COUNT" + ResultParser.DOUBLEDOT, Pattern.CASE_INSENSITIVE);
	private static final Pattern RUN_WS_PROGRAM_COUNT_PATTERN = Pattern.compile("RUN_WS_PROGRAM COUNT" + ResultParser.DOUBLEDOT, Pattern.CASE_INSENSITIVE);
	
	// Common Patterns for TestCase and TestStep and RunProgram
	private static final Pattern PASS_PATTERN = Pattern.compile("pass" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	private static final Pattern FAIL_PATTERN = Pattern.compile("fail" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	private static final Pattern SKIP_PATTERN = Pattern.compile("SKIPPED_SELECTIVELY" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	
	// Common Patterns for TestStep and RunProgram
	private static final Pattern ABORT_PATTERN = Pattern.compile("abort" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	private static final Pattern PANIC_PATTERN = Pattern.compile("panic" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	private static final Pattern UNKOWN_PATTERN = Pattern.compile("unknown" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	private static final Pattern UNEXECUTED_PATTERN = Pattern.compile("unexecuted" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	
	// Patterns for TestStep
	private static final Pattern RUN_TEST_STEP_PATTERN = Pattern.compile("RUN_TEST_STEP\\s+\\d+\\s+\\w+\\s+(\\w+).+\\*{3}Result\\s*=\\s*(\\w+)", Pattern.CASE_INSENSITIVE);
	
	// Patterns for TestCase
	private static final Pattern END_TEST_CASE_PATTERN = Pattern.compile("END_TESTCASE\\s+(.+)\\*{3}TestCaseResult\\s*=\\s*(\\w+)", Pattern.CASE_INSENSITIVE);
	private static final Pattern SKIPPED_TEST_CASE_PATTERN = Pattern.compile("(START_TESTCASE|START_SYNCHRONISED_TESTCASE)\\s+(.+)\\*{3}TestCaseResult\\s*=\\s*(SKIPPED_SELECTIVELY)", Pattern.CASE_INSENSITIVE);
	private static final Pattern INCONCLUSIVE_CASE_PATTERN = Pattern.compile("inconclusive" + ResultParser.EQUALS, Pattern.CASE_INSENSITIVE);
	
	// Patterns for RunProgram
	private static final Pattern RUN_WS_PROGRAM_PATTERN = Pattern.compile("RUN_WS_PROGRAM\\s+\\d+\\s+([^\\s]+)\\s+\\*{3}Result\\s*=\\s*(\\w+)");
	
	// Flags for Summaries
	private static final Pattern SUMMARY_FLAG = Pattern.compile(">SUMMARY:");
	private static final Pattern STEP_SUMMARY_FLAG = Pattern.compile(">TEST\\s+STEP\\s+SUMMARY:");
	private static final Pattern CASE_SUMMARY_FLAG = Pattern.compile(">TEST\\s+CASE\\s+SUMMARY:");
	private static final Pattern RUN_PROGRAM_FLAG = Pattern.compile(">RUN_PROGRAM\\s+SUMMARY:");
		
	private final TestExecuteScript iTefObject;
	private final String iTestName;
	private final TefReport iTefReport;
	private File iResultFile = null;

	/**
	 * @param aObject
	 * @param aTestReport
	 */
	public TefResultParser(EObject aObject, BaseReport aTestReport) {
		iTefObject = (TestExecuteScript) aObject;
		iTefReport = (TefReport) aTestReport;
		iTestName = new File(iTefObject.getSymbianPath()).getName().replaceAll("\\.script", "");
		iTefReport.setName(iTestName);
		
		LOGGER.info("Parsing the TEF Test result file for: " + iTefObject.getSymbianPath());
	}

	/**
	 * @throws IOException 
	 * @throws ParseException 
	 * @see com.symbian.driver.core.report.ResultParser#parseResults()
	 */
	public void parseResults(final int aResultRootLength) throws IOException, ParseException {
		String extention = ".htm";
		//only if the loggin channel is exclusively chosen to be serial...
		String lTefLogChannel = TDConfig.getInstance().getLogChannel();
		if(lTefLogChannel != null && lTefLogChannel.compareToIgnoreCase("serial")==0)
		{
			extention = ".log" ; 
		}

		iResultFile = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT),
				ModelUtils.getBaseDirectory(
						(Task) (iTefObject.eContainer().eContainer()), 
						TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER)) + iTestName.concat(extention));
		
		String lRelativeResultPath = "." + iResultFile.toString().substring(aResultRootLength, iResultFile.toString().length());
		
		iTefReport.setLog(lRelativeResultPath);
		
		// Setup specific TEFLog stuff
		TefTestCaseSummary lTefTestCaseSummary = null;
		TefTestStepSummary lTefTestStepSummary = null;
		TefTestRunWsProgramSummary lTefTestRunWsProgramSummary = null;
		
		boolean lStepFlag = false;
		boolean lCaseFlag = false;
		boolean lRunProgramFlag = false;
		
		iTefReport.setTefTestStepSummary(lTefTestStepSummary);
		
		// Parse Results line by lne
		BufferedReader lBufferedReader = new BufferedReader(new FileReader(iResultFile));
		
		String lResultsLine = null;
		while ((lResultsLine = lBufferedReader.readLine()) != null) {
			
			// Matchers Count
			Matcher lTestCaseCountMatch = TEST_CASE_COUNT_PATTERN.matcher(lResultsLine);
			Matcher lTestStepCountMatch = TEST_STEP_COUNT_PATTERN.matcher(lResultsLine);
			Matcher lRunWsProgramCountMatch = RUN_WS_PROGRAM_COUNT_PATTERN.matcher(lResultsLine);
			
			// Matchers Common for TestCase and TestStep and RunProgram
			Matcher lPassMatch = PASS_PATTERN.matcher(lResultsLine);
			Matcher lFailMatch = FAIL_PATTERN.matcher(lResultsLine);
			Matcher lSkipMatch = SKIP_PATTERN.matcher(lResultsLine);
			
			// Matchers Common for TestStep and RunProgram
			Matcher lPanicMatch = PANIC_PATTERN.matcher(lResultsLine);
			Matcher lUnexecutedMatch = UNEXECUTED_PATTERN.matcher(lResultsLine);
			Matcher lUnknownMatch = UNKOWN_PATTERN.matcher(lResultsLine);
			Matcher lAbortMatch = ABORT_PATTERN.matcher(lResultsLine);
			
			// Matchers for TestStep
			Matcher lRunTestStepMatch = RUN_TEST_STEP_PATTERN.matcher(lResultsLine);
			
			// Matchers for TestCase
			Matcher lEndTestCaseMatch = END_TEST_CASE_PATTERN.matcher(lResultsLine);
			Matcher lInconclusiveMatch = INCONCLUSIVE_CASE_PATTERN.matcher(lResultsLine);
			Matcher lSkippedMatch = SKIPPED_TEST_CASE_PATTERN.matcher(lResultsLine);
			
			// Matchers for RunProgram
			Matcher lRunWsProgramMatch = RUN_WS_PROGRAM_PATTERN.matcher(lResultsLine);
			
			// Matchers for Summaries Flags
			Matcher lSummaryFlagMatch = SUMMARY_FLAG.matcher(lResultsLine);
			Matcher lStepFlagMatch = STEP_SUMMARY_FLAG.matcher(lResultsLine);
			Matcher lCaseFlagMatch = CASE_SUMMARY_FLAG.matcher(lResultsLine);
			Matcher lRunProgramFlagMatch = RUN_PROGRAM_FLAG.matcher(lResultsLine);
			
			try {
				
				////////////////////////////////
				// Set Counts
				////////////////////////////////
				if (lTestCaseCountMatch.find()) {
					
					// Count Summary: TEF Case
					lTefTestCaseSummary = createTefTestCaseSummary();
					lTefTestCaseSummary.setCount(Integer.parseInt(lTestCaseCountMatch.group(1).trim()));
					
				} else if (lTestStepCountMatch.find()) {
					
					// Count Summary: TEF Step
					lTefTestStepSummary = createTefTestStepSummary();
					lTefTestStepSummary.setCount(Integer.parseInt(lTestStepCountMatch.group(1).trim()));
					
				} else if (lRunWsProgramCountMatch.find()) {
					
					// Count Summary: TEF Run Program
					lTefTestRunWsProgramSummary = createRunWsProgramSummary();
					lTefTestRunWsProgramSummary.setCount(Integer.parseInt(lRunWsProgramCountMatch.group(1).trim()));
						
				}
				////////////////////////////////
				// Set Common for STEP, CASE and RUN_PROGRAM
				////////////////////////////////
				else if (lPassMatch.find()) {
					
					// Pass match: For STEP, CASE and RUN_PROGRAM
					int lPass = Integer.parseInt(lPassMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setPass(lPass);
					} else if (lCaseFlag && lTefTestCaseSummary != null) {
						lTefTestCaseSummary.setPass(lPass);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setPass(lPass);
					}
					
				} else if (lFailMatch.find()) {
					
					// Fail match: For STEP, CASE and RUN_PROGRAM
					int lFail = Integer.parseInt(lFailMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setFail(lFail);
					} else if (lCaseFlag && lTefTestCaseSummary != null) {
						lTefTestCaseSummary.setFail(lFail);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setFail(lFail);
					}
					
				} else if (lInconclusiveMatch.find()) {
					
					// Inconclusive match: For STEP, CASE and RUN_PROGRAM
					int lInconclusive = Integer.parseInt(lInconclusiveMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setInconclusive(lInconclusive);
					} else if (lCaseFlag && lTefTestCaseSummary != null) {
						lTefTestCaseSummary.setInconclusive(lInconclusive);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setInconclusive(lInconclusive);
					}
				} else if (lSkipMatch.find()) {

					// SKIPPED_SELECTIVELY match: For CASE
					int lSkipped = Integer.parseInt(lSkipMatch.group(1).trim());
					
					if (lCaseFlag && lTefTestCaseSummary != null) {
						lTefTestCaseSummary.setSkippedSelectively(lSkipped);
					}
				}
				////////////////////////////////
				// Set Common for STEP and RUN_PROGRAM
				////////////////////////////////
				else if (lPanicMatch.find()) {
					
					// Panic match: For STEP and RUN_PROGRAM
					int lPanic = Integer.parseInt(lPanicMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setPanic(lPanic);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setPanic(lPanic);
					}
					
				} else if (lUnexecutedMatch.find()) {
					
					// Unexecuted match: For STEP and RUN_PROGRAM
					int lUnexecuted = Integer.parseInt(lUnexecutedMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setUnexecuted(lUnexecuted);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setUnexecuted(lUnexecuted);
					}
					
				} else if (lUnknownMatch.find()) {
					
					// Unknown match: For STEP and RUN_PROGRAM
					int lUnknown = Integer.parseInt(lUnknownMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setUnknown(lUnknown);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setUnknown(lUnknown);
					}
					
				} else if (lAbortMatch.find()) {
					
					// Abort match: For STEP and RUN_PROGRAM
					int lAbort = Integer.parseInt(lAbortMatch.group(1).trim());
					
					if (lStepFlag && lTefTestStepSummary != null) {
						lTefTestStepSummary.setAbort(lAbort);
					} else if (lRunProgramFlag && lTefTestRunWsProgramSummary != null) {
						lTefTestRunWsProgramSummary.setAbort(lAbort);
					}
					
				} 
				////////////////////////////////
				// Set Induvidual Results
				////////////////////////////////
				else if (lRunTestStepMatch.find()) {
					
					// Parse Induvidual STEP
					String lName = lRunTestStepMatch.group(1).trim();
					String lResult = lRunTestStepMatch.group(2).trim().toLowerCase();
					
					TefTestStep lTefTestStep = ReportFactory.eINSTANCE.createTefTestStep();
					lTefTestStepSummary = createTefTestStepSummary();
					lTefTestStepSummary.getTestCase().add(lTefTestStep);
					
					lTefTestStep.setName(lName);
					lTefTestStep.setResult(TefTestStepResult.get(lResult));
					
				} else if (lEndTestCaseMatch.find()) {
					
					// Parse Induvidual CASE
					String lName = lEndTestCaseMatch.group(1).trim();
					String lResult = lEndTestCaseMatch.group(2).trim().toLowerCase();
					
					TefTestCase lTefTestCase = ReportFactory.eINSTANCE.createTefTestCase();
					if (lTefTestCaseSummary == null ) {
					lTefTestCaseSummary = createTefTestCaseSummary();
					}
					lTefTestCaseSummary.getTestCase().add(lTefTestCase);
					
					lTefTestCase.setName(lName);
					lTefTestCase.setResult(TefTestCaseResult.get(lResult));
				} else if (lSkippedMatch.find()) {
					
					// Parse Induvidual CASE
					String lName = lSkippedMatch.group(2).trim();
					String lResult = lSkippedMatch.group(3).trim().toLowerCase();
					
					TefTestCase lTefTestCase = ReportFactory.eINSTANCE.createTefTestCase();
					if (lTefTestCaseSummary == null ) {
						lTefTestCaseSummary = createTefTestCaseSummary();
					}
					lTefTestCaseSummary.getTestCase().add(lTefTestCase);
					
					lTefTestCase.setName(lName);
					lTefTestCase.setResult(TefTestCaseResult.get(lResult));
					
				}  else if (lRunWsProgramMatch.find()) {
					
					// Parse Induvidual PROGRAM
					String lName = lRunWsProgramMatch.group(1).trim();
					String lResult = lRunWsProgramMatch.group(2).trim().toLowerCase();
					
					TefTestRunWsProgram lRunWsProgram = ReportFactory.eINSTANCE.createTefTestRunWsProgram();
					lTefTestRunWsProgramSummary = createRunWsProgramSummary();
					lTefTestRunWsProgramSummary.getTestCase().add(lRunWsProgram);
					
					lRunWsProgram.setName(lName);
					lRunWsProgram.setResult(TefTestRunWsProgramResult.get(lResult));
					
				}
				////////////////////////////////
				// Summary Flag matchers
				////////////////////////////////
				else if (lSummaryFlagMatch.find() || lStepFlagMatch.find()) {
					lStepFlag = true;
					lCaseFlag = false;
					lRunProgramFlag = false;
				} else if (lCaseFlagMatch.find()) {
					lCaseFlag = true;
					lStepFlag = false;
					lRunProgramFlag = false;
				} else if (lRunProgramFlagMatch.find()) {
					lRunProgramFlag = true;
					lStepFlag = false;
					lCaseFlag = false;
				}
				
			} catch (Throwable lThrowable) {
				LOGGER.log(Level.SEVERE, "Could not parse line: \"" + lResultsLine + "\"; because of: " + lThrowable.getMessage(), lThrowable);
			}
		}
		
		// Ensure that if nothing worked at least something is 0.
		if (lTefTestStepSummary != null
				|| (lTefTestCaseSummary == null && lTefTestRunWsProgramSummary == null && lTefTestStepSummary == null)) {
			TefTestStepSummary lTefStep = createTefTestStepSummary();
			
			if (lTefStep.getPass() == 0 &&
					lTefStep.getFail() == 0 && 
					lTefStep.getPanic() == 0 && 
					lTefStep.getUnexecuted() == 0 && 
					lTefStep.getUnknown() == 0 &&
					lTefStep.getAbort() == 0) {
				lTefStep.setUnknown(1);
			}
		}
	}
	
	/**
	 * Returns the TefTestCaseSummary EMF object. If it doesn't exist it will create a new EMF object.
	 * 
	 * @return The EMF TefTestStepSumarry object.
	 */
	private TefTestStepSummary createTefTestStepSummary() {
		TefTestStepSummary lTefTestStepSummary = iTefReport.getTefTestStepSummary();
		
		if (lTefTestStepSummary == null) {
			lTefTestStepSummary = ReportFactory.eINSTANCE.createTefTestStepSummary();
			iTefReport.setTefTestStepSummary(lTefTestStepSummary);
		}
		
		return lTefTestStepSummary;
	}

	/**
	 * Returns the TefTestCaseSummary EMF object. If it doesn't exist it will create a new EMF object.
	 * 
	 * @return The EMF TefTestCaseSumarry object.
	 */
	private TefTestCaseSummary createTefTestCaseSummary() {
		TefTestCaseSummary lTefTestCaseSummary = iTefReport.getTefTestCaseSummary();
		
		if (lTefTestCaseSummary == null) {
			lTefTestCaseSummary = ReportFactory.eINSTANCE.createTefTestCaseSummary();
			iTefReport.setTefTestCaseSummary(lTefTestCaseSummary);
		}
		
		return lTefTestCaseSummary;
	}
	
	/**
	 * Returns the TefTestCaseSummary EMF object. If it doesn't exist it will create a new EMF object.
	 * 
	 * @return The EMF TefTestRunWsProgram object.
	 */
	private TefTestRunWsProgramSummary createRunWsProgramSummary() {
		TefTestRunWsProgramSummary lTefTestRunWsProgramSummary = iTefReport.getTefTestRunWsProgramSummary();
		
		if (lTefTestRunWsProgramSummary == null) {
			lTefTestRunWsProgramSummary = ReportFactory.eINSTANCE.createTefTestRunWsProgramSummary();
			iTefReport.setTefTestRunWsProgramSummary(lTefTestRunWsProgramSummary);
		}
		
		return lTefTestRunWsProgramSummary;
	}

	/**
	 * @see com.symbian.driver.core.report.ResultParser#createEmptyReport()
	 */
	public void createEmptyReport() {
		if (iResultFile != null) {
			iTefReport.setLog(iResultFile.toString());
		} else {
			iTefReport.setLog(".");
		}
		
		TefTestStepSummary lTefStep = createTefTestStepSummary();
		
		lTefStep.setCount(1);
		lTefStep.setPass(0);
		lTefStep.setFail(0);
		lTefStep.setPanic(0);
		lTefStep.setUnexecuted(0);
		lTefStep.setUnknown(1);
		lTefStep.setAbort(0);
	}

	public void createSummary(EMap aInfo) {
		if (aInfo != null) {
			try {
				TefTestStepSummary lStepReport = createTefTestStepSummary();
				
				String lAttemptedTestCase = (String) aInfo.get(ATTEMPTED_TEST_STEP);
				aInfo.put(ATTEMPTED_TEST_STEP, new Integer(
						(lAttemptedTestCase == null ? 0 : Integer.parseInt(lAttemptedTestCase))
						+ lStepReport.getCount()).toString());
				
				String lPassedTestCase = (String) aInfo.get(PASSED_TEST_STEP);
				aInfo.put(PASSED_TEST_STEP, new Integer(
						(lPassedTestCase == null ? 0 : Integer.parseInt(lPassedTestCase))
						+ lStepReport.getPass()).toString());
				
				String lFailedTestCase =(String) aInfo.get(FAILED_TEST_STEP);
				aInfo.put(FAILED_TEST_STEP, new Integer(
						(lFailedTestCase == null ? 0 : Integer.parseInt(lFailedTestCase))
						+ lStepReport.getFail() + lStepReport.getAbort()
						+ lStepReport.getPanic() + lStepReport.getUnexecuted()).toString());
				
				String lUnkownTestCase =(String) aInfo.get(UNKOWN_TEST_STEP);
				aInfo.put(UNKOWN_TEST_STEP, new Integer(
						(lUnkownTestCase == null ? 0 : Integer.parseInt(lUnkownTestCase))
						+ lStepReport.getUnknown()).toString());
			} catch (Throwable lThrowable) {
				LOGGER.log(Level.SEVERE, "Could not create Summary for TEF Test.", lThrowable);
			}
		}
	}
}
