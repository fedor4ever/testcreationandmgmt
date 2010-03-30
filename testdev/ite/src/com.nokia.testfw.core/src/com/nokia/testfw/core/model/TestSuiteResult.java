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
package com.nokia.testfw.core.model;

/**
 * @author xiaoma
 *
 */
public class TestSuiteResult extends TestResult {
	protected int testCount;
	protected int passedTestCount;
	protected int failedTestCount;
	protected int skippedTestCount;
	
	public int getTestCount() {
		return testCount;
	}
	
	public int getPassedTestCount() {
		return passedTestCount;
	}
	
	public int getFailedTestCount() {
		return failedTestCount;
	}
	
	public int getSkippedTestCount() {
		return skippedTestCount;
	}
	
}
