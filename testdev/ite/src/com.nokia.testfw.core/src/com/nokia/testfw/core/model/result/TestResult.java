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

import java.text.NumberFormat;

public abstract class TestResult {

	public enum TestStatus {
		SUCCESS, FAILURE, SKIP, STARTED, NOTSTART;
	}

	protected static final NumberFormat TIMEFORMAT;

	static {
		TIMEFORMAT = NumberFormat.getNumberInstance();
		TIMEFORMAT.setGroupingUsed(true);
		TIMEFORMAT.setMinimumFractionDigits(3);
		TIMEFORMAT.setMaximumFractionDigits(3);
		TIMEFORMAT.setMinimumIntegerDigits(1);
	}

	/**
	 * the test status;
	 */
	protected TestStatus iStatus = TestStatus.NOTSTART;

	/**
	 * the parent suite
	 */
	protected TestSuiteResult iParent;

	/**
	 * test result name
	 */
	protected final String iName;

	/**
	 * test result type
	 */
	private final String iType;

	/**
	 * time spend on the test case(suite), in second
	 */
	protected double iTime = Double.NEGATIVE_INFINITY;

	/**
	 * the message about the result
	 */
	protected String iMessage;

	/**
	 * the file where testcase(suite) defined
	 */
	protected String iFile;

	/**
	 * the line number in the file
	 */
	protected int iLine;

	/**
	 * the column number in the file
	 */
	protected int iColumn;

	public TestResult(String name, String type) {
		this(name, type, TestStatus.NOTSTART);
	}

	public TestResult(String name, String type, TestStatus status) {
		iName = name;
		iType = type;
		iStatus = status;
		iParent = null;
		iFile = null;
		iLine = -1;
		iColumn = -1;
	}

	/**
	 * @return the parent
	 */
	public TestSuiteResult getParent() {
		return iParent;
	}

	/**
	 * @return the iMessage
	 */
	public String getMessage() {
		return iMessage;
	}

	/**
	 * @param message
	 *            the iMessage to set
	 */
	public void setMessage(String message) {
		iMessage = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return iName;
	}

	public String getDesc() {
		if (getTime() >= 0
				&& (iStatus == TestStatus.SUCCESS || iStatus == TestStatus.FAILURE)) {
			return iName + "(" + getTimeString() + ")";
		} else {
			return iName;
		}
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return iFile;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.iFile = file;
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return iLine;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(int line) {
		this.iLine = line;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return iColumn;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.iColumn = column;
	}

	/**
	 * if this test case is defined in a test suite, the unique name is
	 * suitename.casename, otherwise it is just case name
	 * 
	 * @return
	 */
	public String getUniqueName() {
		StringBuilder sb = new StringBuilder();
		if (iParent == null) {
			sb.append(iType).append(": ").append(iName);
		} else {
			sb.append(iParent.getUniqueName()).append(".").append(iName);
		}
		return sb.toString();
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return iTime;
	}

	/**
	 * @return the time string
	 */
	public String getTimeString() {
		return TIMEFORMAT.format(getTime()) + " s";
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(double time) {
		this.iTime = time;
	}

	/**
	 * @return the iStatus
	 */
	public TestStatus getStatus() {
		return iStatus;
	}

	/**
	 * @param status
	 *            the iStatus to set
	 */
	public void setStatus(TestStatus status) {
		iStatus = status;
	}

	/**
	 * this test case is identified by suite name and case name
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (getUniqueName().equals(((TestResult) obj).getUniqueName())) {
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getUniqueName());
		if (getTime() >= 0) {
			sb.append(" ").append(getTimeString());
		}
		sb.append(" ").append(iStatus);
		return sb.toString();
	}
}
