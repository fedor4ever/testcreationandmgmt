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
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.resultview.ResultViewPlugin;

/**
 * @author k21wang
 * 
 */
public class TestRunSession extends TestRunResult {
	/**
	 * The launch, or <code>null</code> if this session was run externally.
	 */
	private final ILaunch iLaunch;

	private String iName;

	public ILaunch getLaunch() {
		return iLaunch;
	}

	private final IDataProcessor iProcessor;

	private boolean iClosed = false;

	public void close() {
		iClosed = true;
		try {
			if (iProcessor != null)
				iProcessor.close();
		} catch (IOException e) {
			ResultViewPlugin.log(e);
		}
	}

	public TestRunSession(ILaunch launch, IDataProcessor processor) {
		super();
		iLaunch = launch;
		iProcessor = processor;
		iStartTime = DateFormat.getDateTimeInstance().format(new Date());
	}

	public void init(ILaunchConfiguration configuration) {
		iProcessor.initTestResult(this, configuration);
	}

	public void update(String info) {
		iProcessor.process(this, info);
	}

	/**
	 * @return <code>true</code> if the session has been stopped or terminated
	 */
	public boolean isClosed() {
		return iClosed;
	}

	/**
	 * @return the iLogPath
	 */
	public InputStream getInputStream() {
		return iProcessor.getInputStream(iLaunch.getLaunchConfiguration());
	}

	public ILaunchConfiguration getRetestLaunchConfiguration(
			ILaunchConfiguration configuration, TestResult[] results) {
		return iProcessor.createRetestLaunchConfiguration(configuration,
				results);
	}

	String iStartTime;

	public String getStartTime() {
		return iStartTime;
	}

	public String getName() {
		if (iName == null) {
			iName = "[" + iLaunch.getLaunchConfiguration().getName()
					+ "] start time: " + getStartTime();
		}
		return iName;
	}

	public void testFinished() {
		close();
		super.testFinished();
	}
}
