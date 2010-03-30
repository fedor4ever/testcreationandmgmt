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

import java.util.LinkedHashSet;
import java.util.Set;

public class TestSuiteResult extends TestResult {
	private LinkedHashSet<TestResult> children = new LinkedHashSet<TestResult>();

	public TestSuiteResult(String name) {
		super(name, "TestSuite");
	}

	/**
	 * Returns a (possibly empty) array of TestResult which are the children of
	 * the test suite.
	 * 
	 * @return
	 */
	public Set<TestResult> getChildren() {
		return children;
	}

	public TestResult getChild(String name) {
		for (TestResult child : children) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

	public void addTestResult(TestResult result) {
		children.add(result);
		result.iParent = this;
	}

	/**
	 * @return the time
	 */
	@Override
	public double getTime() {
		boolean started = false;
		double timecount = 0;
		for (TestResult child : children) {
			double time = child.getTime();
			if (time >= 0) {
				started = true;
				timecount += child.getTime();
			}
		}
		if (started) {
			iTime = timecount;
		}
		return iTime;
	}
}
