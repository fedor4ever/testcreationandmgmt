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


package com.nokia.s60tools.testdrop.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.nokia.s60tools.testdrop.engine.value.HardwareTestResultValue;
import com.nokia.s60tools.testdrop.engine.value.TestResultValue;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Factory class for finding and adding test results
 * 
 */
public class TestResultFactory implements Runnable {

	private List<TestResultValue> testResults;
	private File testResultPath;
	private long startTime;
	private List<Integer> testRunIds;

	/**
	 * Constructor
	 * 
	 * @param testResultPath
	 *            place where test result files found
	 * @throws IOException
	 *             if file reading fails
	 */
	public TestResultFactory(File testResultPath, long startTime) {
		testResults = new ArrayList<TestResultValue>();
		if (startTime == 0) {
			this.startTime = System.currentTimeMillis();
		} else {
			this.startTime = startTime;
		}

		this.testResultPath = testResultPath;
		testRunIds = new ArrayList<Integer>();
	}

	/**
	 * Gets test result from file path
	 * 
	 * @param testResultPath
	 *            place where test result files found
	 * @throws IOException
	 *             if file reading fails
	 */
	private TestResultValue getTestResultFromFilePath() throws IOException {
		TestResultValue retResult = null;
		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.indexOf("[") != -1 && name.indexOf("]") != -1 
						&& name.indexOf("(") != -1 && name.indexOf(")") != -1
						&& name.indexOf(".html") != -1) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] allFiles = testResultPath.listFiles(filter);
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < allFiles.length; i++) {
			File file = allFiles[i];
			if (file.isFile()) {
				files.add(file);
			}
		}

		if (!files.isEmpty()) {
			Iterator<File> filesIterator = files.iterator();
			while (filesIterator.hasNext()) {
				File file = (File) filesIterator.next();
				try {
					String fileName = file.getName();
					String idString = fileName.substring(
							fileName.indexOf("(") + 1, fileName.indexOf(")"));
					int id = Integer.valueOf(idString);

					if (testRunIds.size() > 0 && id == testRunIds.get(0)) {

						String resultFile = getTestResultsFromFile(file);
						TestResultValue testResultValueFile = new HardwareTestResultValue(
								resultFile, id);

						String resultServer = TestDropFactory
								.getResultFromServer(id, null);
						TestResultValue testResultValueServer = new HardwareTestResultValue(
								resultServer, id);
						if (isResultSame(testResultValueFile,
								testResultValueServer)) {
							retResult = testResultValueFile;
							testRunIds.remove(testRunIds.get(0));
							break;
						}
					}

				} catch (NumberFormatException ex) { // is not correct file
					ex.printStackTrace();
				}
			}
		}

		return retResult;

	}

	/**
	 * Reads test result from file
	 * 
	 * @param file
	 *            where results found
	 * @return return result content
	 * @throws IOException
	 *             if file reading fails
	 */
	private String getTestResultsFromFile(File file) throws IOException {
		BufferedReader in = null;
		StringBuffer buf = null;

		try {
			buf = new StringBuffer();
			in = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = in.readLine()) != null) {
				buf.append(line);
			}
			return buf.toString();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LogExceptionHandler.log(e.getMessage());
				}
			}
		}
	}

	/**
	 * Checks if the test result given by parameter is a new one
	 * 
	 * @param testResultValue
	 *            TestResultValue
	 * @return true if testResultValue was a new one in test result list
	 *         otherwise false
	 */
	private boolean isNewResult(TestResultValue testResultValue) {
		String dateString = testResultValue.getEndTime();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = dateFormatter.parse(dateString);
			if (date.getTime() > startTime) {
				return true;
			}
		} catch (ParseException e) {
			LogExceptionHandler.log(e.getMessage());
		}
		return false;
	}

	/**
	 * Resolves is firstResult and secondResult are the same
	 * 
	 * @param firstResult
	 *            TestResultValue
	 * @param secondResult
	 *            TestResultValue
	 * @return true if first and seconedResult are same otherwise false
	 */
	private boolean isResultSame(TestResultValue firstResult,
			TestResultValue secondResult) {
		if (firstResult.getTestName().equals(secondResult.getTestName())
				&& firstResult.getEndTime().equals(secondResult.getEndTime())
				&& firstResult.getPassrateString().equals(
						secondResult.getPassrateString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds test result to test results list
	 * 
	 * @param testResultValue
	 *            a new test result
	 */
	public void addTestResultToListShortedByTime(TestResultValue testResultValue) {
		int i = 0;
		int size = testResults.size();

		if (!isNewResult(testResultValue) && size != 0) {		// size == 0 means that the view could be opened a moment ago.
			return;												// In fact running a test drop takes some time ==> the result is older than the view window
		}														// As a result isNewResult will return false. So the first result would not be shown
		if (size == 0) {
			testResults.add(testResultValue);
		}
		while (i < size) {
			String newDateString = testResultValue.getEndTime();
			String selectedDateString = testResults.get(i).getEndTime();
			DateFormat dateFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"); 
			try {
				Date newdate = dateFormatter.parse(newDateString);
				long newDateTime = newdate.getTime();
				Date selectedDate = dateFormatter.parse(selectedDateString);
				long selectedDateTime = selectedDate.getTime();
				if (newDateTime > selectedDateTime) {
					testResults.add(i, testResultValue);
					break;
				}
				i++;
			} catch (ParseException e) {
				LogExceptionHandler.log(e.getMessage());
			}
		}
	}

	/**
	 * Adds test result to test results list
	 * 
	 * @param testRunId
	 *            test run id
	 */
	public void addTestResultToListShortedByTime(int testRunId) {
		testRunIds.add(new Integer(testRunId));
		Thread thread = new Thread(this);
		thread.start();

	}

	/**
	 * Returns test results list
	 * 
	 * @return test results list
	 * @throws IOException
	 */
	public List<TestResultValue> getTestResults() throws IOException {
		return testResults;
	}

	/**
	 * Returns test result path
	 * 
	 * @return test result path
	 */
	public File getTestResultPath() {
		return testResultPath;
	}

	/**
	 * Sets a new test result path
	 * 
	 * @param testResultPath
	 *            a new test result path
	 */
	public void setTestResultPath(File testResultPath) {
		this.testResultPath = testResultPath;
	}

	/**
	 * Waiting for test results to be ready to display.
	 */
	public void run() {
		TestResultValue testResultValue = null;
		try {
			while ((testResultValue = getTestResultFromFilePath()) == null) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {

				}
			}
			addTestResultToListShortedByTime(testResultValue);
			Runnable cachedDialogRunnable = new Runnable() {
				public void run() {
					StartUp.showResulView();
				}
			};
			TestDropPlugin.getDefaultDisplay().asyncExec(cachedDialogRunnable);

		} catch (IOException e) {
			LogExceptionHandler.log(e.getMessage());
		}

	}
}
