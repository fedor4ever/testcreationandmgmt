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

import com.nokia.testfw.core.model.result.TestResult.TestStatus;

/**
 * Listener for test result updates
 * 
 * @author xiaoma
 * @see TestRunResult
 */
public interface TestResultListener {

	public void testStarted();

	public void testFinished();

	public void addTestCase(TestCaseResult result);

	public void testCaseStateChange(TestCaseResult result, TestStatus status);
}
