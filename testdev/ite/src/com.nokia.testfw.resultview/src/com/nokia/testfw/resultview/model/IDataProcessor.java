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

package com.nokia.testfw.resultview.model;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.debug.core.ILaunchConfiguration;

import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;

public interface IDataProcessor {

	public boolean isDealType(ILaunchConfiguration config);
	
	public void initTestResult(TestRunResult result,
			ILaunchConfiguration config);

	public InputStream getInputStream(ILaunchConfiguration config);

	public void process(TestRunResult result, String info);

	public void close() throws IOException;

	public ILaunchConfiguration createRetestLaunchConfiguration(
			ILaunchConfiguration configuration, TestResult[] retestResults);
}
