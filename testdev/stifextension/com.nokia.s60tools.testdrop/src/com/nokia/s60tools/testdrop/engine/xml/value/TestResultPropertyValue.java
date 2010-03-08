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


package com.nokia.s60tools.testdrop.engine.xml.value;

import java.io.File;

/**
 * Contains all data for creating test results
 * 
 */
public class TestResultPropertyValue {
	private final String REPORT_TYPE = "STIF_COMPONENT_REPORT_ALL_CASES"; 
	private File testResulPath;

	/**
	 * Constructor
	 * 
	 * @param testResulPath
	 *            place where test results are located
	 */
	public TestResultPropertyValue(File testResulPath) {
		this.testResulPath = testResulPath;
	}

	/**
	 * Sets a new test result path
	 * 
	 * @param testResulPath
	 */
	public void setTestResulPath(File testResulPath) {
		this.testResulPath = testResulPath;
	}

	/**
	 * Return test result path
	 * 
	 * @return test result path
	 */
	public File getTestResulPath() {
		return testResulPath;
	}

	/**
	 * Return test report type
	 * 
	 * @return test report type
	 */
	public String getReportType() {
		return REPORT_TYPE;
	}
}
