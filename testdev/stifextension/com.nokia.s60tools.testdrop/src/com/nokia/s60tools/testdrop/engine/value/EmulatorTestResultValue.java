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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.Path;

import com.nokia.s60tools.testdrop.util.TestCaseResult;

/**
 * Test result value of a test run (emulator)
 *
 */
public class EmulatorTestResultValue implements TestResultValue {
	
	/**
	 * test result comes in a form of xml. The below tags are used while
	 * parsing the xml file
	 */
	private final String testModuleNameStartTag = "<ModuleName>";
	private final String testModuleNameEndTag = "</ModuleName>";
	private final String configStartTag = "<Config>";
	private final String configEndTag = "</Config>";
	private final String testCaseStartTimeStartTag = "<StartTime>";
	private final String testCaseStartTimeEndTag = "</StartTime>";
	private final String testCaseEndTimeStartTag = "<EndTime>";
	private final String testCaseEndTimeEndTag = "</EndTime>";
	private final String passedStartTag = "<Passed>";
	private final String passedEndTag = "</Passed>";
	private final String failedStartTag = "<Failed>";
	private final String failedEndTag = "</Failed>";
	private final String crashedStartTag = "<Crashed>";
	private final String crashedEndTag = "</Crashed>";
	private final String timeoutStartTag = "<Timeout>";
	private final String timeoutEndTag = "</Timeout>";
	private final String totalStartTag = "<Total>";
	private final String totalEndTag = "</Total>";
	private final String testCaseStartTag = "<TestCase>";
	private final String testCaseEndTag = "</TestCase>";
	private final String titleStartTag = "<Title>";
	private final String titleEndTag = "</Title>";
	private final String resultStartTag = "<Result>";
	private final String resultEndTag = "</Result>";
	
	private final int SELECTION_SIZE = 4;
	
	private String result = null;
	private String testName = null;
	private String time = null;
	private String firstTestCaseStartTime = null;
	private String lastTestCaseEndTime = null;
	private Calendar calendar = null;
	private float passRate = 0;
	private int current;
	private int[] selectionIndexes;
	private ArrayList<TestCaseResult> testCasesResultsList = null;
	
	private static int globalTestRunId = 0;
	private int testRunId;
	
	/**
	 * A constructor for initializing an EmulatorTestResultValue with result (xml format)
	 * and xml file creation date
	 * 
	 * @param result
	 * 			result of a test run in an xml form
	 * @param creationDate
	 * 			xml file creation date
	 */
	public EmulatorTestResultValue(String result, Date creationDate) {
		this.result = result;
		selectionIndexes = new int[SELECTION_SIZE];
		
		calendar = Calendar.getInstance();
		calendar.setTime(creationDate);
		
		testRunId = globalTestRunId++;
		
		int nameStartIndex = result.indexOf(testModuleNameStartTag) + testModuleNameStartTag.length();
		int nameEndIndex = result.indexOf(testModuleNameEndTag);
		testName = result.substring(nameStartIndex, nameEndIndex);
		if (testName.equals("testcombiner")) {
			int configStartIndex = result.indexOf(configStartTag) + configStartTag.length();
			int configEndIndex = result.indexOf(configEndTag);
			if (configStartIndex > -1 && configEndIndex > -1) {
				Path pathToConfig = new Path(result.substring(configStartIndex, configEndIndex));
				String configFileName = pathToConfig.lastSegment();
				testName += "(" + configFileName + ") ";
			}
		}
	}
	
	/**
	 * A method for extracting the test result creation date
	 * 
	 * @return
	 * 			test result creation date in a form of String
	 */
	public String getEndTime() {
		if (time == null) {
			time = calendar.get(Calendar.YEAR) + "-" + normalizeDateItem(calendar.get(Calendar.MONTH) + 1) 
				+ "-" + normalizeDateItem(calendar.get(Calendar.DAY_OF_MONTH)) + " "
				+ normalizeDateItem(calendar.get(Calendar.HOUR_OF_DAY)) + ":" 
				+ normalizeDateItem(calendar.get(Calendar.MINUTE)) + ":"
				+ normalizeDateItem(calendar.get(Calendar.SECOND));
		}
		return time;
	}
	
	/**
	 * A method for extracting start time of a test run
	 * 
	 * @return
	 * 			start time of the first test case as a String
	 */
	public String getFirstTestCaseStartTime() {
		if (firstTestCaseStartTime == null) {
			int indexOfStartTag = result.indexOf(testCaseStartTimeStartTag);
			int indexOfEndTag = result.indexOf(testCaseStartTimeEndTag);
			
			String time = result.substring(indexOfStartTag + testCaseStartTimeStartTag.length(), indexOfEndTag);
			time = removeSecondsPostfix(time);
			DateFormat startTime = new SimpleDateFormat("H:m:s a");
			try {
				startTime.parse(time);
			}
			catch (ParseException ex) {
			}
			Calendar startTimeCalendar = startTime.getCalendar();
			firstTestCaseStartTime = calendar.get(Calendar.YEAR) + "-" 
				+ normalizeDateItem(calendar.get(Calendar.MONTH)  + 1) 
				+ "-" + normalizeDateItem(calendar.get(Calendar.DAY_OF_MONTH))
				+ " " +  normalizeDateItem(startTimeCalendar.get(Calendar.HOUR_OF_DAY))
				+ ":" +  normalizeDateItem(startTimeCalendar.get(Calendar.MINUTE))
				+ ":" +  normalizeDateItem(startTimeCalendar.get(Calendar.SECOND));
		}
		return firstTestCaseStartTime;
	}
	
	/**
	 * A method for extracting end time of the last test case
	 * 
	 * @return
	 * 			last test case end time as a String
	 */
	public String getLastTestCaseEndTime() {
		if (lastTestCaseEndTime == null) {
			int lastIndexOfStartTag = result.lastIndexOf(testCaseEndTimeStartTag);
			int lastIndexOfEndTag = result.lastIndexOf(testCaseEndTimeEndTag);
			
			String time = result.substring(lastIndexOfStartTag + testCaseEndTimeStartTag.length(), lastIndexOfEndTag - 1);
			time = removeSecondsPostfix(time);
			DateFormat endTime = new SimpleDateFormat("H:m:s a");
			try {
				endTime.parse(time);
			}
			catch (ParseException ex) {
			}
			Calendar endTimeCalendar = endTime.getCalendar();
			lastTestCaseEndTime = calendar.get(Calendar.YEAR) + "-" 
				+ normalizeDateItem(calendar.get(Calendar.MONTH) + 1) 
				+ "-" + normalizeDateItem(calendar.get(Calendar.DAY_OF_MONTH))
				+ " " + normalizeDateItem(endTimeCalendar.get(Calendar.HOUR_OF_DAY))
				+ ":" + normalizeDateItem(endTimeCalendar.get(Calendar.MINUTE))
				+ ":" + normalizeDateItem(endTimeCalendar.get(Calendar.SECOND));
		}
		return lastTestCaseEndTime;
	}
	
	/**
	 * Method adds a zero at the beginning of a number if needed
	 * 
	 * @param toNormalize
	 * 			a number to be expanded if needed
	 * @return
	 * 			the expanded number
	 */
	private String normalizeDateItem(int toNormalize) {
		if (toNormalize < 10) {
			return "0" + toNormalize;
		}
		return toNormalize + "";
	}
	
	/**
	 * Extracting passrate as a float value
	 * 
	 * @return
	 * 			passrate as float value
	 */
	public float getPassrate() {
		int passed = getPassed();
		int total = getTotal();
		passRate = (float)passed / (float)total;
		return (float)((int)(passRate * 1000)) / 10f;
	}
	
	/**
	 * Passrate as String
	 * 
	 * @return
	 * 		passrate as String
	 */
	public String getPassrateString() {
		return getPassed() + "/" + getTotal() + " (" + Float.toString(getPassrate()) + "%)";
	}
	
	/**
	 * @return 
	 * 		testName
	 */
	public String getTestName() {
		return testName;
	}
	
	/**
	 * Adds index of test result value which is needed to display detailed view
	 * when user chooses this test result value from the apropriate view
	 */
	public void addSelectionIndex(int index) {
		if (current == (SELECTION_SIZE)) {
			current = 0;
		}
		selectionIndexes[current++] = index;
	}
	
	/**
	 * @return
	 * 		result as String
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * @return
	 * 			A "title" String of a test result. Contains a name of a test run and also an id
	 * 			to make the title more unique
	 */
	public String getTestNameAndTestId() {
		return getTestName() + "(" + Integer.toString(testRunId) + ")";
	}
	
	/**
	 * Checks if the chosen test result value is already selected (displayed)
	 * 
	 * @return
	 * 			true if is already selected
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
	 * Values from emulator differ from those from ATS server. The value of seconds in "times" returned by
	 * atsinterface are too detailed and need to be truncated 
	 * 
	 * @param time
	 * 			value to be modified
	 * @return
	 * 			modified time value as String
	 */
	private String removeSecondsPostfix(String time) {
		int indexOfDot = time.indexOf(".");
		int indexOfAmPm = time.length() - 2;
		
		String resultTime = time.substring(0, indexOfDot) + " " + time.substring(indexOfAmPm, time.length());
		return resultTime;
	}
	
	/**
	 * @return
	 * 		Number of passed test cases
	 */
	public int getPassed() {
		int passed = Integer.parseInt(result.substring(result.indexOf(passedStartTag) + passedStartTag.length(), 
				result.indexOf(passedEndTag)));
		return passed;
	}
	
	/** 
	 * @return
	 * 		String - Number of passed test cases followed by a passrate
	 */
	public String getPassedAsString() {
		int passed = getPassed();
		int total = getTotal();
		float passedToTotal = (float)passed / (float)total;
		String percentage = passed + "/" + total
			+ " (" + Float.toString((float)((int)(passedToTotal * 1000)) / 10f) + "%)"; 
		
		return percentage;
	}
	
	/**
	 * @return
	 * 		Number of failed test cases
	 */
	public int getFailed() {
		int failed = Integer.parseInt(result.substring(result.indexOf(failedStartTag) + failedStartTag.length(), 
				result.indexOf(failedEndTag)));
		return failed;
	}
	
	/**
	 * @return
	 *		String - number of failed test cases followed by failrate string
	 */
	public String getFailedAsString() {
		int failed = getFailed();
		int total = getTotal();
		float failedToTotal = (float)failed / (float)total;
		String percentage = failed + "/" + total
			+ " (" + Float.toString((float)((int)(failedToTotal * 1000)) / 10f) + "%)"; 
		
		return percentage;
	}
	
	/**
	 * @return
	 * 		Number of crashed test cases
	 */
	public int getCrashed() {
		int crashed = Integer.parseInt(result.substring(result.indexOf(crashedStartTag) + crashedStartTag.length(), 
				result.indexOf(crashedEndTag)));
		return crashed;
	}
	
	/**
	 * @return
	 * 		Number of crashed test cases as string followed by a crash rate string
	 */
	public String getCrashedAsString() {
		int crashed = getCrashed();
		int total = getTotal();
		float crashedToTotal = (float)crashed / (float)total;
		String percentage = crashed + "/" + total
			+ " (" + Float.toString((float)((int)(crashedToTotal * 1000)) / 10f) + "%)"; 
		
		return percentage;
	}
	
	/**
	 * @return
	 * 		Number of timeouted test cases
	 */
	public int getTimeouted() {
		int timeouted = Integer.parseInt(result.substring(result.indexOf(timeoutStartTag) + timeoutStartTag.length(), 
				result.indexOf(timeoutEndTag)));
		return timeouted;
	}
	
	/**
	 * @return
	 * 		Number of timeouted test cases as String followed by a percentage of failed test cases
	 */
	public String getTimeoutedAsString() {
		int timeouted = getTimeouted();
		int total = getTotal();
		float timeoutedToTotal = (float)timeouted / (float)total;
		String percentage = timeouted + "/" + total
			+ " (" + Float.toString((float)((int)(timeoutedToTotal * 1000)) / 10f) + "%)"; 
		
		return percentage;
	}
	
	/**
	 * @return
	 * 		Total number of test cases
	 */
	public int getTotal() {
		int total = Integer.parseInt(result.substring(result.indexOf(totalStartTag) + totalStartTag.length(), 
				result.indexOf(totalEndTag)));
		return total;
	}
	
	/**
	 * Method for retrieving a list of test cases from a test module
	 * 
	 * @return
	 * 		a list of test cases from a test module
	 */
	public ArrayList<TestCaseResult> getTestCasesResults() {
		if (testCasesResultsList == null) {
			testCasesResultsList = new ArrayList<TestCaseResult>();
			int currentIndex = 0;
			while (currentIndex < result.length()) {
				int testCaseStartIndex = result.indexOf(testCaseStartTag, currentIndex);
				int testCaseEndIndex = result.indexOf(testCaseEndTag, currentIndex);
				if (testCaseStartIndex < 0 || testCaseEndIndex < 0) {
					break;
				}
				currentIndex = testCaseEndIndex + testCaseEndTag.length();
				int indexOfTitleStart = result.indexOf(titleStartTag, testCaseStartIndex);
				int indexOfResultStart = result.indexOf(resultStartTag, testCaseStartIndex);
				if (indexOfTitleStart < testCaseEndIndex && indexOfResultStart < testCaseEndIndex) {
					int indexOfTitleEnd = result.indexOf(titleEndTag, indexOfTitleStart);
					int indexOfResultEnd = result.indexOf(resultEndTag, indexOfResultStart);
					if (indexOfTitleEnd > testCaseEndIndex || indexOfResultEnd > testCaseEndIndex) {
						break;
					}
					String testCaseTitle = result.substring(indexOfTitleStart + titleStartTag.length(),
							indexOfTitleEnd);
					String testCaseResultString = result.substring(indexOfResultStart + resultStartTag.length(),
							indexOfResultEnd);
					TestCaseResult testCaseResult = new TestCaseResult(testCaseTitle, testCaseResultString);
					testCasesResultsList.add(testCaseResult);
				}
			}
		}
		return testCasesResultsList;
	}
	
	/**
	 * @return
	 * 		Overall result as String ("passed"/"failed")
	 */
	public String getOverallResult() {
		if (getPassrate() < 100) {
			return "failed";
		}
		else {
			return "passed";
		}
	}
}
