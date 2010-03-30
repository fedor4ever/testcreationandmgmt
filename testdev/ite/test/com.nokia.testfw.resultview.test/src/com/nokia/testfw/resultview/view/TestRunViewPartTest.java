/*
 * Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
 * All rights reserved.
 * This component and the accompanying materials are made available
 * under the terms of the License "Symbian Foundation License v1.0"
 * which accompanies this distribution, and is available
 * at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
 *
 * Initial Contributors:
 * Nokia Corporation - initial contribution.
 *
 * Contributors:
 *
 * Description:
 *
 */
package com.nokia.testfw.resultview.view;

import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.test.framework.ViewPartTest;
import com.nokia.testfw.test.utils.TestUtils;
import com.nokia.testfw.resultview.model.TestRunSession;
import com.nokia.testfw.resultview.view.TestRunnerViewPart;

/**
 * @author xiaoma
 * 
 */
public class TestRunViewPartTest extends ViewPartTest {
	TestRunSession iSession;

	public void setUp() throws Exception {
		super.setUp();
		iSession = new TestRunSession(null, null);
		iSession.addTestCase("suite1", "case1");
		iSession.addTestCase("suite1", "case2");
		iSession.addTestCase("suite1", "case3");
		iSession.addTestCase("suite2", "case1");

	}

	protected String getViewId() {
		return "com.nokia.testfw.resultview.testrunnerview";
	}

	public void testViewInit() {
		((TestRunnerViewPart) viewPart).addTestRunSession(iSession);
		TestUtils.delay(1000);
		TestResultTree tree = ((TestRunnerViewPart) viewPart).resultTree;
		assertEquals(tree.getTree().getItemCount(), 2);
	}

	public void testDyncUpdate() {
		((TestRunnerViewPart) viewPart).clear();
		((TestRunnerViewPart) viewPart).addTestRunSession(iSession);
		TestUtils.delay(1000);
		iSession.updateCaseStatus("suite1", "case1", TestStatus.STARTED, -1);
		TestUtils.delay(1000);
		iSession.updateCaseStatus("suite1", "case1", TestStatus.SUCCESS, 10);
		TestUtils.delay(1000);
		iSession.updateCaseStatus("suite1", "case2", TestStatus.SUCCESS, 100);
		TestUtils.delay(1000);
		iSession.updateCaseStatus("suite1", "case3", TestStatus.SKIP, 9);
		TestUtils.delay(1000);
		iSession.updateCaseStatus("suite2", "case1", TestStatus.FAILURE, 1000);
		TestUtils.delay(1000);
		iSession.testFinished();
		TestUtils.delay(1000);
		// check counter
		assertEquals(((TestRunnerViewPart) viewPart).counterPanel.suiteLabel
				.getText(), "Runs: 3/4");
		assertEquals(((TestRunnerViewPart) viewPart).counterPanel.passedLabel
				.getText(), "Passed: 2");
		assertEquals(((TestRunnerViewPart) viewPart).counterPanel.failedLabel
				.getText(), "Failed: 1");
		TestUtils.delay(1000);
	}

}
