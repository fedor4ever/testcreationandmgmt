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
package com.nokia.testfw.core.model.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;

import com.nokia.testfw.core.StfPlugin;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;

/**
 * Result for one test run. It has summary counters and detail result for each
 * test case.
 * <P>
 * 
 * This result will be updated during test execution.
 * 
 * <P>
 * 
 */
public class TestRunResult {
	int testCount = 0;
	int passedTestCount = 0;
	int failedTestCount = 0;
	int skippedTestCount = 0;

	LinkedHashMap<String, TestResult> root;
	ArrayList<TestResultListener> listeners;

	public TestRunResult() {
		root = new LinkedHashMap<String, TestResult>();
		listeners = new ArrayList<TestResultListener>();
	}

	public int getTestCount() {
		return testCount;
	}

	public int getPassedTestCount() {
		return passedTestCount;
	}

	public int getfailedTestCount() {
		return failedTestCount;
	}

	public int getSkippedTestCount() {
		return skippedTestCount;
	}

	/**
	 * add a suite to the result.
	 * 
	 * @param suiteName
	 * @return
	 */
	public synchronized TestSuiteResult addTestSuite(String suiteName) {
		String[] suites = suiteName.split("\\.");

		TestResult testResult;
		TestSuiteResult parentSuite = null;
		for (String suite : suites) {
			if (parentSuite == null) {
				testResult = root.get(new TestSuiteResult(suite)
						.getUniqueName());
			} else {
				testResult = parentSuite.getChild(suite);
			}
			if ((testResult == null)
					|| !(testResult instanceof TestSuiteResult)) {
				testResult = new TestSuiteResult(suite);
				if (parentSuite == null) {
					root.put(testResult.getUniqueName(), testResult);
				} else {
					parentSuite.addTestResult(testResult);
				}
			}
			parentSuite = (TestSuiteResult) testResult;
		}

		return parentSuite;
	}

	/**
	 * add a test case to the result
	 * 
	 * @param suiteName
	 *            , maybe null if no associated suite
	 * @param caseName
	 *            , test case name
	 * @return
	 */
	public synchronized TestCaseResult addTestCase(String suiteName,
			String caseName) {
		TestCaseResult result = new TestCaseResult(caseName);
		if (suiteName == null) {
			// top level result
			root.put(result.getUniqueName(), result);
		} else {
			TestSuiteResult suite = addTestSuite(suiteName);
			suite.addTestResult(result);
		}
		testCount++;
		for (TestResultListener listener : listeners) {
			listener.addTestCase(result);
		}
		return result;
	}

	/**
	 * add a suite to the result.
	 * 
	 * @param suiteName
	 * @return
	 */
	public TestSuiteResult getTestSuite(String suiteName) {
		String[] suites = suiteName.split("\\.");

		TestResult testResult;
		TestSuiteResult parentSuite = null;
		for (String suite : suites) {
			if (parentSuite == null) {
				testResult = root.get(new TestSuiteResult(suite)
						.getUniqueName());
			} else {
				testResult = parentSuite.getChild(suite);
			}
			if ((testResult != null) && (testResult instanceof TestSuiteResult)) {
				parentSuite = (TestSuiteResult) testResult;
			} else {
				return null;
			}
		}

		return parentSuite;
	}

	private TestCaseResult getTestCaseResult(String suiteName, String caseName) {
		if (suiteName == null) {
			return (TestCaseResult) root.get((new TestCaseResult(caseName))
					.getUniqueName());
		} else {
			TestSuiteResult suite = getTestSuite(suiteName);
			if (suite != null) {
				TestResult result = suite.getChild(caseName);
				if (result instanceof TestCaseResult) {
					return (TestCaseResult) result;
				}
			}
			return null;
		}
	}

	/**
	 * update test case status and execution time. notify all listener about the
	 * test case state change
	 * 
	 * @param suiteName
	 *            , test suite name
	 * @param caseName
	 *            , test case name
	 * @param status
	 *            , the test case status
	 * @param time
	 *            , will be ignored if set to -1
	 * @see TestStatus
	 * @see TestResultListener
	 */
	public synchronized TestCaseResult updateCaseStatus(String suiteName,
			String caseName, TestStatus status, double time) {
		TestCaseResult result = getTestCaseResult(suiteName, caseName);
		if (result != null) {
			updateCaseStatus(result, status, time);
		} else {
			StringBuilder sb = new StringBuilder("unknown test case: ");
			if (suiteName != null) {
				sb.append(suiteName).append(".");
			}
			sb.append(caseName);
			StfPlugin.log(IStatus.ERROR, sb.toString());
		}
		return result;
	}

	/**
	 * update test case status and execution time. notify all listener about the
	 * test case state change
	 * 
	 * @param result
	 *            , test case result
	 * @param status
	 *            , the test case status
	 * @param time
	 *            , will be ignored if set to -1
	 * @see TestStatus
	 * @see TestResultListener
	 */
	public synchronized TestCaseResult updateCaseStatus(TestCaseResult result,
			TestStatus status, double time) {
		result.setStatus(status);
		if (time >= 0) {
			result.setTime(time);
		}
		switch (status) {
		case SUCCESS:
			passedTestCount++;
			break;
		case FAILURE:
			failedTestCount++;
			break;
		case SKIP:
			skippedTestCount++;
			break;
		case STARTED:
			break;
		default:
			StfPlugin.log(IStatus.ERROR, "unknown teststatus:" + status);
		}
		updateSuiteResult(result, status);
		for (TestResultListener listener : listeners) {
			listener.testCaseStateChange(result, status);
		}
		return result;
	}

	private void updateSuiteResult(TestResult result, TestStatus status) {
		TestSuiteResult suite = result.iParent;
		if (suite == null) {
			return;
		}
		if (status == TestStatus.STARTED) {
			if (suite.getStatus() == TestStatus.NOTSTART) {
				suite.setStatus(TestStatus.STARTED);
			}
		} else if (status == TestStatus.FAILURE) {
			suite.setStatus(status);
		} else if (status == TestStatus.SKIP) {
			if (suite.getStatus() != TestStatus.FAILURE) {
				suite.setStatus(status);
			}
		} else if (status == TestStatus.SUCCESS) {
			// set the suite to pass only all cases passed
			boolean suitePassed = true;
			for (TestResult test : suite.getChildren()) {
				if (test.getStatus() != TestStatus.SUCCESS) {
					suitePassed = false;
					break;
				}
			}// end of for
			if (suitePassed) {
				suite.setStatus(TestStatus.SUCCESS);
			}
		}
		updateSuiteResult(suite, suite.getStatus());
	}

	/**
	 * add a new test result listener
	 * 
	 * @param listener
	 *            , the listener
	 */
	public void addResultListener(TestResultListener listener) {
		listeners.add(listener);
	}

	/**
	 * remove a test listener from the result
	 * 
	 * @param listener
	 *            , the listener to remove
	 */
	public void removeResultListener(TestResultListener listener) {
		listeners.remove(listener);
	}

	/**
	 * notify all listeners that test has started
	 * 
	 * @see TestResultListener
	 */
	public void testStarted() {
		for (TestResultListener listener : listeners) {
			listener.testStarted();
		}
	}

	/**
	 * notify all listeners the test has finished.
	 * 
	 * @see TestResultListener
	 */
	public void testFinished() {
		for (TestResultListener listener : listeners) {
			listener.testFinished();
		}
	}

	public TestResult[] getResults() {
		return root.values().toArray(new TestResult[0]);
	}

	public Set<TestCaseResult> getFailedResults() {
		return gatherFailedTestCaseResult(root.values());
	}

	private Set<TestCaseResult> gatherFailedTestCaseResult(
			Collection<TestResult> results) {
		Set<TestCaseResult> failedCaseSet = new HashSet<TestCaseResult>();
		for (TestResult result : results) {
			if (result instanceof TestCaseResult) {
				if (result.getStatus() == TestStatus.FAILURE) {
					failedCaseSet.add((TestCaseResult) result);
				}
			} else {
				failedCaseSet
						.addAll(gatherFailedTestCaseResult(((TestSuiteResult) result)
								.getChildren()));
			}
		}
		return failedCaseSet;
	}
}
