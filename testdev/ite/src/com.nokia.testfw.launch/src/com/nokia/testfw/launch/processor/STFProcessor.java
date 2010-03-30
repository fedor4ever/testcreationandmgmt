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

/**
 * @author ferao
 * 
 */
package com.nokia.testfw.launch.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.utils.STFScriptUtils;
import com.nokia.testfw.launch.LaunchConfigurationConstants;
import com.nokia.testfw.launch.TFWLaunchPlugin;
import com.nokia.testfw.launch.monitor.TailFileInputStream;
import com.nokia.testfw.resultview.model.IDataProcessor;

public class STFProcessor implements IDataProcessor {

	public static final String DEFAULT_LOG = "c:\\logs\\TestFramework\\testengine\\testengine.html";
	public static final String START_TEST_CASE = ".... Starting testcase";
	public static final String END_TEST_CASE = "finished with verdict";
	public static final String END_CASE_TIME_DELIMITER = ".... TestCase ";
	public static final String INFO_START_FLAG = "[";
	public static final String INFO_END_FLAG = "]";
	public static final String RESULT_MESSAGE = "Message";
	public static final String FILE = "FILE";
	public static final String LINE = "LINE";
	public static final String TEST_START = "############################################################";
	public static final String TEST_FINISHED = "---------------- Log Ended ----------------";
	public static final String DATE_FORMAT = "dd.MMM.yyyy HH:mm:ss.SSS";

	public void close() throws IOException {
		if (iInputStream != null) {
			iInputStream.close();
		}
	}

	public void initTestResult(TestRunResult result, ILaunchConfiguration config) {
		ArrayList<String> testCases = STFScriptUtils
				.getTestCasesFromScript(getScriptPath(config));
		for (String caseName : testCases) {
			addTestCase(result, caseName);
		}
	}

	public ILaunchConfiguration createRetestLaunchConfiguration(
			ILaunchConfiguration oldConfiguration, TestResult[] retestResults) {
		return oldConfiguration;
	}

	public InputStream getInputStream(ILaunchConfiguration config) {
		if (iInputStream == null)
			iInputStream = createTailFileInputStream(getLogPath(config));
		;
		return iInputStream;
	}

	public boolean isDealType(ILaunchConfiguration config) {
		if (config == null)
			return false;

		// test whether the launch defines the STF attributes
		String idStr = null;
		try {
			idStr = config.getAttribute(
					LaunchConfigurationConstants.SCRIPT_HOST_PATH,
					(String) null);
		} catch (CoreException e) {
			TFWLaunchPlugin.log(e);
		}
		if (idStr == null)
			return false;
		return true;
	}

	public void process(TestRunResult result, String info) {
		BufferedReader reader = new BufferedReader(new StringReader(info));
		String line;
		int index;
		try {
			while ((line = reader.readLine()) != null) {
				if ((index = line.indexOf(TEST_START)) > -1) {
					result.testStarted();
				} else if ((index = line.indexOf(START_TEST_CASE)) > -1) {
					String lInfo = line.substring(
							index + START_TEST_CASE.length()).trim();
					startTestCase(result, lInfo, getDate(
							line.substring(0, index)).getTime());
				} else if ((index = line.indexOf(END_TEST_CASE)) > -1) {
					int delimiterIndex = line.indexOf(END_CASE_TIME_DELIMITER);
					double time = -1;
					if (delimiterIndex > -1) {
						time = getDate(line.substring(0, delimiterIndex))
								.getTime();
					}
					String lInfo = line.substring(
							delimiterIndex + END_CASE_TIME_DELIMITER.length())
							.trim();
					endTestCase(result, lInfo, time);
				} else if ((index = line.indexOf(TEST_FINISHED)) > -1) {
					result.testFinished();
				}
			}
		} catch (IOException e) {
			TFWLaunchPlugin.log(e);
		}
	}

	// private methods
	private String getLogPath(ILaunchConfiguration config) {
		if (iLogFile == null) {
			try {
				String epocRoot = config.getAttribute(
						"com.nokia.cdt.debug.cw.symbian.Epoc_Root",
						(String) null);
				File logFile = new File(
						epocRoot
								+ "/epoc32/winscw/c/logs/TestFramework/testengine/testengine.html");
				File logDir = new File(epocRoot
						+ "/epoc32/winscw/c/logs/TestFramework/testengine");
				if (!logDir.exists()) {
					logDir.mkdir();
				}
				try {
					if (!logFile.exists()) {
						logFile.createNewFile();
					}
				} catch (IOException e) {
					TFWLaunchPlugin.log(e);
				}
				iLogFile = logFile.getAbsolutePath();
			} catch (CoreException e) {
				TFWLaunchPlugin.log(e);
			}
		}
		return iLogFile;
	}

	private String getScriptPath(ILaunchConfiguration config) {
		String scriptPath = null;
		try {
			String tmp = config.getAttribute(
					LaunchConfigurationConstants.SCRIPT_HOST_PATH,
					(String) null);
			File scriptFile = new File(tmp);
			scriptPath = scriptFile.getAbsolutePath();
		} catch (CoreException e) {
			TFWLaunchPlugin.log(e);
		}
		return scriptPath;
	}

	private TailFileInputStream createTailFileInputStream(String filepath) {
		TailFileInputStream tailStream = null;
		try {
			tailStream = new TailFileInputStream(filepath);
		} catch (IOException e) {
			TFWLaunchPlugin.log(e);
		}
		return tailStream;
	}

	private TestCaseResult startTestCase(TestRunResult result, String info,
			double time) {
		return result.updateCaseStatus(null, getValue(info),
				TestStatus.STARTED, time);
	}

	private TestCaseResult endTestCase(TestRunResult result, String info,
			double time) {
		TestStatus status = null;

		// Get case name.
		String caseName = getValue(info);

		// Get error code.
		int errIndex = info.indexOf(END_TEST_CASE);
		if ("0".equalsIgnoreCase(getValue(info.substring(errIndex
				+ END_TEST_CASE.length())))) {
			status = TestStatus.SUCCESS;
		} else {
			status = TestStatus.FAILURE;
		}

		// Get error msg.
		int msgIndex = info.indexOf(RESULT_MESSAGE);
		String msg = info.substring(msgIndex + RESULT_MESSAGE.length());

		double timeSpent = -1;
		TestResult[] caseResult = result.getResults();
		for (TestResult cur : caseResult) {
			if (cur.getName().equalsIgnoreCase(caseName)) {
				timeSpent = (time - cur.getTime()) / 1000;
				failedTestCase(cur, msg);
			}
		}

		return result.updateCaseStatus(null, caseName, status, timeSpent);
	}

	private String getValue(String content) {
		int startIndex = content.indexOf(INFO_START_FLAG);
		int endIndex = content.indexOf(INFO_END_FLAG);
		if (startIndex < endIndex) {
			return content.substring(startIndex + 1, endIndex);
		} else {
			return null;
		}
	}

	private Date getDate(String info) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			date = dateFormat.parse(info);
		} catch (java.text.ParseException e) {
			TFWLaunchPlugin.log(e);
		}
		return date;
	}

	private TestCaseResult addTestCase(TestRunResult result, String info) {
		// String lTestCaseName = getValue(info);
		return result.addTestCase(null, info);
	}

	private void failedTestCase(TestResult result, String msg) {
		int index;
		if ((index = msg.indexOf(FILE)) > -1) {
			String lcontent = msg.substring(index + FILE.length());
			String file = getValue(lcontent);
			result.setFile(file);
			if ((index = msg.indexOf(LINE)) > -1) {
				lcontent = msg.substring(index + LINE.length());
				String line = getValue(lcontent);
				result.setLine(Integer.valueOf(line));
			}
		} else {
			String message = msg.substring(msg.indexOf(INFO_START_FLAG)
					+ INFO_START_FLAG.length(), msg.lastIndexOf(INFO_END_FLAG));
			result.setMessage(message);
		}
	}

	// private members
	private String iLogFile = null;
	private InputStream iInputStream = null;
}
