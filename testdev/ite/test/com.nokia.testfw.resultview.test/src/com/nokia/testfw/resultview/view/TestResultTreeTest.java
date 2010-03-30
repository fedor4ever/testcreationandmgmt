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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestSuiteResult;
import com.nokia.testfw.core.model.result.TestResult.TestStatus;
import com.nokia.testfw.test.framework.ControlTestCase;
import com.nokia.testfw.test.utils.TestUtils;

/**
 * @author xiaoma
 * 
 */
public class TestResultTreeTest extends ControlTestCase {

	TestResultTree resultTree;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nokia.testfw.test.framework.ControlTestCase#createTestControl(org
	 * .eclipse.swt.widgets.Composite)
	 */
	protected Control createTestControl(Composite parent) {
		resultTree = new TestResultTree(parent);
		return resultTree.getTree();
	}

	/**
	 * test init the tree with empty result
	 */
	public void testTree() {

		TestRunResult runResult = new TestRunResult();
		runResult.addTestCase("suite1", "case1");
		runResult.addTestCase("suite1", "case2");
		runResult.addTestCase("suite1", "case3");
		runResult.addTestCase("suite2", "case1");
		runResult.updateCaseStatus("suite1", "case1", TestStatus.FAILURE, 10);
		runResult.updateCaseStatus("suite1", "case2", TestStatus.SUCCESS, 100);
		runResult.updateCaseStatus("suite1", "case3", TestStatus.SKIP, 9);
		resultTree.init(runResult);
		TestUtils.delay(2000);
		assertEquals(resultTree.getTree().getItemCount(), 2);
		TreeItem item = resultTree.getTree().getItem(0);
		assertEquals(item.getItemCount(), 3);

		TestSuiteResult parent = new TestSuiteResult("New Test Suite");
		TestCaseResult result = new TestCaseResult("New Test Case");
		parent.addTestResult(result);
		resultTree.addTestCase(result);
		assertEquals(resultTree.getTree().getItemCount(), 3);
		result.setStatus(TestStatus.SUCCESS);
		resultTree.update(result);

		resultTree.reset();
		assertEquals(resultTree.getTree().getItemCount(), 0);
	}
}
