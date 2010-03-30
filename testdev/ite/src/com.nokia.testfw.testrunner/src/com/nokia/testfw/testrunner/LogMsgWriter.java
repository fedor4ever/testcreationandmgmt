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
package com.nokia.testfw.testrunner;

import java.io.*;

public class LogMsgWriter {
	public static final String DEFAULT_LOG_FILE = "";
	public static final String FMT_START_TEST = "StartTest[%s]\n";
	public static final String END_TEST = "EndTest\n";
	public static final String FMT_LIST_CASE = "ListCase[%s]\n";
	public static final String FMT_START_CASE = "StartCase[%s]\n";
	public static final String FMT_TEST_FAILED_MSG = "TestFailedMsg[%s]\n";
	public static final String FMT_LONG_TEST_FAILED_MSG = "TestFailedMsg[%s]File[%s]Line[%d]\n";
	public static final String FMT_END_CASE = "EndCase Result[%s]Time[%d]ms\n";
	public static final String PASSED = "passed";
	public static final String FAILED = "failed";
	
	public LogMsgWriter() {
		try {
			logger = new FileWriter(DEFAULT_LOG_FILE, true);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public LogMsgWriter(String logName) {
		try {
			logger = new FileWriter(logName, true);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	// Methods to write log.
	public void writeStartTest(String msg) {
		try {
			String line = String.format(FMT_START_CASE, msg);
			write(line);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void writeEndTest() {
		try {
			write(END_TEST);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void writeCaseList(String[] caseNames) {
		try {
			for (int i = 0; i < caseNames.length; ++i) {
				String line = String.format(FMT_LIST_CASE, caseNames[i]);
				write(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void writeStartCase(String msg) {
		try {
			String line = String.format(FMT_START_CASE, msg);
			write(line);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void writeTestFailureMsg(String msg) {
		try {
			String line = String.format(FMT_TEST_FAILED_MSG, msg);
			write(line);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void writeLongTestFailureMsg(String msg, String fileName, int lineNo) {
		try {
			String line = String.format(FMT_LONG_TEST_FAILED_MSG, msg, fileName, lineNo);
			write(line);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void writeEndCaseResult(boolean passed, int timeSpent) {
		try {
			String line = String.format(FMT_END_CASE, passed ? PASSED : FAILED, timeSpent);
			write(line);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	// This should be called explicitly.
	public void close() {
		try {
			logger.flush();
			logger.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	private void write(String msg) throws IOException {
		logger.write(msg);
		logger.flush();
	}

	private FileWriter logger = null;
}
