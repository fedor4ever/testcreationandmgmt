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


package com.nokia.s60tools.testdrop.engine.value;

/**
 * Keeps hardware test results
 * 
 */
public class HardwareTestResultValue implements TestResultValue {
	
	private final String TEST_NAME_START_TAG = "<tr><td><b>Test run:&nbsp;</b></td><td>"; 
	private final String TEST_NAME_END_TAG = "</td></tr>"; 
	private final String END_TIME_START_TAG = "<tr><td><b>End time:&nbsp;</b></td><td>"; 
	private final String END_TIME_END_TAG = "</td></tr>"; 
	private final String PASSRATE_START_TAG = "<td class=\"tableData\">Number of test cases</td>"; 
	private final String PASSRATE_COLUMN_START_TAG = "<td class=\"tableData\" align=\"right\">"; 
	private final String PASSRATE_COLUMN_END_TAG = "</td>"; 
	private final String TABLE_END_TAG = "</table>";
	private final String OVERALL_RESULT_LABEL = "Overall result:";
	private final int PASSRATE_POSITION = 4;
	private final int SELECTION_SIZE = 4;
	
	/**
	 * ATS server does not produce a useful passrate bar. That's why it is added.
	 * Such bar shows user immidiately the overall result in the most accessible way
	 */
	private final String PASSRATE_BAR_INJECTION = "<tr><td><b>Passed/Failed ratio:</b></td><td><table border=\"0\""
					+ " cellspacing=\"0\" cellpadding=\"0\"><tr><td bgcolor=\"#90ff90\" width=\"[passedWidth]\" "
					+ "height=\"15\"></td><td bgcolor=\"#ff9090\" width=\"[failedWidth]\" height=\"15\"></td></tr>"
					+ "</table></td></tr>";
	private final String PASSED_WIDTH_TAG = "[passedWidth]";
	private final String FAILED_WIDTH_TAG = "[failedWidth]";

	private String endTime;
	private String result;
	private int[] selectionIndexes;
	private int current;
	private float passrate;
	private String testName;
	private int testRunId;
	private String passrateString;

	/**
	 * Constructor
	 * 
	 * @param result
	 *            test result content
	 * @param testRunId
	 *            test run id
	 */
	public HardwareTestResultValue(String result, int testRunId) {
		this.result = result;
		this.testRunId = testRunId;
		selectionIndexes = new int[SELECTION_SIZE];
		passrate = -1;
		injectPassrateBarIntoResult();
	}
	
	/**
	 * Method for adding passrate bar to html page that was downloaded from ATS server
	 *
	 */
	private void injectPassrateBarIntoResult() {
		int indexOfOverallResult = this.result.indexOf(OVERALL_RESULT_LABEL);
		if (indexOfOverallResult != -1) {
			int indexOfTableEndTag = this.result.indexOf(TABLE_END_TAG, indexOfOverallResult);
			String stringToInject = PASSRATE_BAR_INJECTION;
			stringToInject = stringToInject.replace(PASSED_WIDTH_TAG, Integer.toString((int)(getPassrate() * 1.5)));
			stringToInject = stringToInject.replace(FAILED_WIDTH_TAG, Integer.toString(150 - (int)(getPassrate() * 1.5)));
			String finalResultString = this.result.substring(0, indexOfTableEndTag);
			finalResultString += stringToInject + this.result.substring(indexOfTableEndTag);
			this.result = finalResultString;
		}
	}

	/**
	 * Gets end time from results
	 * 
	 * @return end time from results
	 */
	public String getEndTime() {
		if (endTime == null) {
			endTime = result.substring(result.indexOf(END_TIME_START_TAG));
			endTime = endTime.substring(END_TIME_START_TAG.length(), endTime
					.indexOf(END_TIME_END_TAG));
		}
		return endTime;
	}

	/**
	 * Gets passrate from passrate string
	 * 
	 * @return passrate per cent from passrate string
	 */
	public float getPassrate() {
		if (passrate == -1) {
			String pass = getPassrateString();
			pass = pass.substring(pass.indexOf("(") + 1, pass.indexOf(")") - 1);  
			passrate = Float.valueOf(pass);
		}
		return passrate;
	}

	/**
	 * Gets passrate from results
	 * 
	 * @return passrate from results
	 */
	public String getPassrateString() {
		if (passrateString == null) {
			String pass = result.substring(result.indexOf(PASSRATE_START_TAG));
			int founded = 0;
			while (true) {
				int index = pass.indexOf(PASSRATE_COLUMN_END_TAG);
				if (index != -1) {
					pass = pass.substring(index
							+ PASSRATE_COLUMN_END_TAG.length());
					founded++;
				}
				if (founded == PASSRATE_POSITION) {
					if (pass.indexOf(PASSRATE_COLUMN_START_TAG) > 0) {
						pass = pass.substring(pass
								.indexOf(PASSRATE_COLUMN_START_TAG));
					}
					pass = pass.substring(PASSRATE_COLUMN_START_TAG.length(),
							pass.indexOf(PASSRATE_COLUMN_END_TAG));
					passrateString = pass;
					break;
				}
			}
		}
		return passrateString;
	}

	/**
	 * Get test name from results
	 * 
	 * @return test name from results
	 */
	public String getTestName() {
		if (testName == null) {
			testName = result.substring(result.indexOf(TEST_NAME_START_TAG));
			testName = testName.substring(TEST_NAME_START_TAG.length(),
					testName.indexOf(TEST_NAME_END_TAG));
		}
		return testName;
	}

	/**
	 * Returns string that contain test name and test run id
	 * 
	 * @return string that contain test name and test run id
	 */
	public String getTestNameAndTestId() {
		return getTestName() + "(" + testRunId + ")";  
	}

	/**
	 * Return test results
	 * 
	 * @return test results
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Adds selection index, which tell the position in the
	 * table view
	 * 
	 * @param index
	 *            selected index
	 */
	public void addSelectionIndex(int index) {
		if (current == (SELECTION_SIZE)) {
			current = 0;
		}
		selectionIndexes[current++] = index;
	}

	/**
	 * Resolves if currently selected index is from the test results or not
	 * 
	 * @param index
	 *            selected index
	 * @return true if selected index found in selected index table otherwise
	 *         false
	 */
	public boolean isSelected(int index) {
		for (int i = 0; i < selectionIndexes.length; i++) {
			if (selectionIndexes[i] == index) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets a new test run id
	 * 
	 * @param testRunId
	 *            a new test run id
	 */
	public void setTestRunId(int testRunId) {
		this.testRunId = testRunId;
	}

	/**
	 * Returns test run id
	 * 
	 * @return test run id
	 */
	public int getTestRunId() {
		return testRunId;
	}
}
