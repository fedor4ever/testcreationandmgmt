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
package com.nokia.testfw.launch.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.launch.LaunchConfigurationConstants;
import com.nokia.testfw.launch.TFWLaunchPlugin;
import com.nokia.testfw.launch.monitor.TailFileInputStream;
import com.nokia.testfw.resultview.model.IDataProcessor;

/**
 * @author k21wang
 * 
 */
public class SUTProcessor implements IDataProcessor {

	public final static String LIST_TEST_CASE = "TestCase";

	public final static String START_TEST_CASE = "StartCase";

	public final static String END_TEST_CASE = "EndCase";

	public final static String TEST_FAILED = "Test Failed";

	public final static String RESULT = "Result";

	public final static String TIME = "Time";

	public final static String FILE = "File";

	public final static String LINE = "Line";

	public final static String MSG = "Msg";

	public final static String REASON = "Reason";

	public final static String TEST_STARTED = "SymbianUnitTest v";

	public final static String TEST_FINISHED = "SymbianUnitTest finished";

	private String iLogPath = null;

	private InputStream iInputStream = null;

	private TestCaseResult iLastTestCaseResult;

	public void process(TestRunResult result, String info) {

		BufferedReader reader = new BufferedReader(new StringReader(info));
		String line;
		int index;
		try {
			while ((line = reader.readLine()) != null) {
				if ((index = line.indexOf(LIST_TEST_CASE)) > -1) {
					String lInfo = line.substring(
							index + LIST_TEST_CASE.length()).trim();
					addTestCase(result, lInfo);
				} else if ((index = line.indexOf(START_TEST_CASE)) > -1) {
					String lInfo = line.substring(
							index + START_TEST_CASE.length()).trim();
					iLastTestCaseResult = startTestCase(result, lInfo);
				} else if ((index = line.indexOf(END_TEST_CASE)) > -1) {
					String lInfo = line.substring(
							index + END_TEST_CASE.length()).trim();
					endTestCase(result, lInfo);
				} else if ((index = line.indexOf(TEST_FAILED)) > -1) {
					String lInfo = line.substring(index + TEST_FAILED.length())
							.trim();
					failedTestCase(result, lInfo);
				} else if ((index = line.indexOf(TEST_STARTED)) > -1) {
					result.testStarted();
				} else if ((index = line.indexOf(TEST_FINISHED)) > -1) {
					result.testFinished();
				}
			}
		} catch (IOException e) {
			TFWLaunchPlugin.log(e);
		}

	}

	private TestCaseResult addTestCase(TestRunResult result, String info) {
		String lTestCaseName = getValue(info);
		return result.addTestCase(null, lTestCaseName);
	}

	private TestCaseResult startTestCase(TestRunResult result, String info) {
		String lTestCaseName = getValue(info);
		return result.updateCaseStatus(null, lTestCaseName, TestStatus.STARTED,
				-1);
	}

	private TestCaseResult endTestCase(TestRunResult result, String info) {
		if (iLastTestCaseResult == null)
			return null;
		int index;
		TestStatus status = null;
		if ((index = info.indexOf(RESULT)) > -1) {
			String lcontent = info.substring(index + RESULT.length());
			String lresult = getValue(lcontent);
			if ("pass".equalsIgnoreCase(lresult)) {
				status = TestStatus.SUCCESS;
			} else {
				status = TestStatus.FAILURE;
			}
		}
		double time = 0;
		if ((index = info.indexOf(TIME)) > -1) {
			String lcontent = info.substring(index + TIME.length());
			String ltime = getValue(lcontent);
			time = Double.valueOf(ltime) / 1000;
		}
		return result.updateCaseStatus(null, iLastTestCaseResult.getName(),
				status, time);
	}

	private TestCaseResult failedTestCase(TestRunResult result, String info) {
		if (iLastTestCaseResult == null)
			return null;
		int index;
		if ((index = info.indexOf(FILE)) > -1) {
			String lcontent = info.substring(index + FILE.length());
			String file = getValue(lcontent);
			iLastTestCaseResult.setFile(file);
		}

		if ((index = info.indexOf(LINE)) > -1) {
			String lcontent = info.substring(index + LINE.length());
			String line = getValue(lcontent);
			iLastTestCaseResult.setLine(Integer.valueOf(line));
		}
		String message = null;
		if ((index = info.indexOf(MSG)) > -1) {
			String lcontent = info.substring(index + MSG.length());
			message = getValue(lcontent);
		}
		if ((index = info.indexOf(REASON)) > -1) {
			String lcontent = info.substring(index + REASON.length());
			message = getValue(lcontent);
		}
		iLastTestCaseResult.setMessage(message);
		return iLastTestCaseResult;
	}

	private String getValue(String content) {
		int startIndex = content.indexOf('[');
		int endIndex = content.indexOf(']');
		if (startIndex < endIndex) {
			return content.substring(startIndex + 1, endIndex);
		} else {
			return null;
		}
	}

	public InputStream getInputStream(ILaunchConfiguration config) {
		if (iInputStream == null) {
			String location = getLogPath(config);
			File logFile = new File(location);
			if (!logFile.exists()) {
				try {
					logFile.getParentFile().mkdirs();
					logFile.createNewFile();
				} catch (IOException e) {
					TFWLaunchPlugin.log(e);
				}
			}
			iInputStream = createTailFileInputStream(location);
		}
		return iInputStream;
	}

	private String getLogPath(ILaunchConfiguration config) {
		if (iLogPath == null) {
			try {
				String epocRoot = config.getAttribute(
						"com.nokia.cdt.debug.cw.symbian.Epoc_Root",
						(String) null);
				File logFile = new File(epocRoot
						+ "/epoc32/winscw/c/logs/sut/sut_log.txt");
				iLogPath = logFile.getAbsolutePath();
			} catch (CoreException e) {
				TFWLaunchPlugin.log(e);
			}
		}
		return iLogPath;
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

	public void close() throws IOException {
		if (iInputStream != null) {
			iInputStream.close();
		}
	}

	public boolean isDealType(ILaunchConfiguration config) {
		if (config == null)
			return false;

		// test whether the launch defines the SymbianUnitTest attributes
		String dllStr = null;
		try {
			dllStr = config.getAttribute(LaunchConfigurationConstants.DLLNAME,
					(String) null);
		} catch (CoreException e) {
			TFWLaunchPlugin.log(e);
		}
		if (dllStr == null)
			return false;
		return true;
	}

	public ILaunchConfiguration createRetestLaunchConfiguration(
			ILaunchConfiguration oldConfiguration, TestResult[] retestResults) {
		ILaunchConfigurationWorkingCopy newConfiguration = null;
		try {
			newConfiguration = oldConfiguration.copy("retest");
			StringBuilder lTestCases = new StringBuilder();
			lTestCases.append(retestResults[0].getName());
			for (int i = 1; i < retestResults.length; i++) {
				lTestCases.append(",").append(retestResults[i].getName());
			}
			newConfiguration.setAttribute(
					LaunchConfigurationConstants.TESTCASENAME, lTestCases
							.toString());
			String lArguments = newConfiguration.getAttribute(
					"org.eclipse.cdt.launch.PROGRAM_ARGUMENTS", (String) null);

			StringBuilder lArgumentsBuilder = new StringBuilder();
			for (String segment : lArguments.split("\\s")) {
				if (!segment.startsWith("-c=")) {
					if (lArgumentsBuilder.length() == 0) {
						lArgumentsBuilder.append(segment);
					} else {
						lArgumentsBuilder.append(" ").append(segment);
					}
				}
			}
			lArgumentsBuilder.append(" -c=").append(lTestCases.toString());
			newConfiguration.setAttribute(
					"org.eclipse.cdt.launch.PROGRAM_ARGUMENTS",
					lArgumentsBuilder.toString());
		} catch (CoreException e) {
			TFWLaunchPlugin.log(e);
		}
		return newConfiguration;
	}

	public void initTestResult(TestRunResult result, ILaunchConfiguration config) {
	}
}
