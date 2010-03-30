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
package com.nokia.testfw.resultview.view;

import java.util.Hashtable;
import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.nokia.testfw.core.model.result.TestCaseResult;
import com.nokia.testfw.core.model.result.TestResult;
import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.core.model.result.TestSuiteResult;
import com.nokia.testfw.resultview.ResultViewPlugin;

public class TestResultTree {
	private static final Image passedIcon = ResultViewPlugin
			.getImage("obj16/testok.gif"); //$NON-NLS-1$
	private static final Image failedIcon = ResultViewPlugin
			.getImage("obj16/testfail.gif"); //$NON-NLS-1$
	private static final Image testIcon = ResultViewPlugin
			.getImage("obj16/test.gif"); //$NON-NLS-1$
	private static final Image skipIcon = ResultViewPlugin
			.getImage("obj16/testskip.gif"); //$NON-NLS-1$
	private static final Image runIcon = ResultViewPlugin
			.getImage("obj16/testrun.gif"); //$NON-NLS-1$

	Tree tree;
	Hashtable<String, TreeItem> treeItems;

	public TestResultTree(Composite parent) {
		treeItems = new Hashtable<String, TreeItem>();
		tree = new Tree(parent, SWT.V_SCROLL | SWT.SINGLE);
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);

		tree.setLayoutData(gridData);
	}

	public Tree getTree() {
		return tree;
	}

	/**
	 * initial the tree with empty test result
	 */
	public void init(TestRunResult runResult) {
		// create a root node
		for (TestResult result : runResult.getResults()) {
			createTreeItems(null, result);
		}
	}

	/**
	 * update one test case result
	 * 
	 * @param result
	 */
	public void update(TestCaseResult result) {
		// since it is using same result object, just redraw the tree
		TreeItem item = treeItems.get(result.getUniqueName());
		updateTreeItem(item);
		// make sure to show this item in the tree
		tree.showItem(item);
		// update parent suite is any
		TestSuiteResult suite;
		TestResult child = result;
		while ((suite = child.getParent()) != null) {
			updateTreeItem(treeItems.get(suite.getUniqueName()));
			child = suite;
		}
	}

	public boolean addTestCase(TestCaseResult result) {
		if (treeItems.get(result.getUniqueName()) != null)
			return false;

		TestSuiteResult parent = result.getParent();
		if (parent == null) {
			createNewTreeItem(null, result);
		} else {
			Stack<TestResult> stack = new Stack<TestResult>();
			TreeItem parentItem = null;
			stack.push(result);
			while (parent != null) {
				parentItem = treeItems.get(parent.getUniqueName());
				if (parentItem == null) {
					stack.push(parent);
				} else {
					break;
				}
				parent = parent.getParent();
			}
			while (!stack.isEmpty()) {
				TestResult test = stack.pop();
				parentItem = createNewTreeItem(parentItem, test);
			}
		}
		return true;
	}

	/**
	 * reset the result tree and clean it is contents
	 */
	public void reset() {
		tree.removeAll();
		treeItems.clear();
	}

	/**
	 * create one tree item to show a testcase(suite) result
	 * 
	 * @param testResult
	 */
	protected void createTreeItems(TreeItem parent, TestResult testResult) {
		TreeItem item = createNewTreeItem(parent, testResult);
		// create items for its children is any
		if (testResult instanceof TestSuiteResult) {
			TestSuiteResult suite = (TestSuiteResult) testResult;
			for (TestResult child : suite.getChildren()) {
				createTreeItems(item, child);
			}
		}
	}

	private TreeItem createNewTreeItem(TreeItem parentItem, TestResult result) {
		TreeItem treeItem = null;
		if (parentItem == null) {
			treeItem = new TreeItem(tree, SWT.NONE);
		} else {
			treeItem = new TreeItem(parentItem, SWT.NONE);
		}
		treeItem.setData(result);
		treeItem.setExpanded(true);
		updateTreeItem(treeItem);
		// set to show all items. may change to only show suite later
		tree.showItem(treeItem);
		treeItems.put(result.getUniqueName(), treeItem);
		return treeItem;
	}

	private void updateTreeItem(TreeItem item) {
		TestResult result = (TestResult) item.getData();
		item.setText(result.getDesc());

		switch (result.getStatus()) {
		case SUCCESS:
			item.setImage(passedIcon);
			break;
		case FAILURE:
			item.setImage(failedIcon);
			break;
		case SKIP:
			item.setImage(skipIcon);
			break;
		case STARTED:
			item.setImage(runIcon);
			break;
		default:
			item.setImage(testIcon);
		}

	}

	// // handle the double click selection
	// private void setupSelection() {
	// tree.addListener(SWT.Selection, new Listener() {
	//
	// public void handleEvent(Event event) {
	// TreeItem item = tree.getSelection()[0];
	// if (item == null) {
	// return;
	// }
	// TestResult result = (TestResult) item.getData();
	// if (result == null) {
	// ResultViewPlugin.log(IStatus.WARNING,
	// "can't find selected test result");
	// }
	// // find the file
	// if (result.getFile() == null) {
	// ResultViewPlugin.log(IStatus.OK,
	// "no locaiton information associated with the test");
	// return;
	// }
	// // find file in workspace
	// WorkspaceUtils.openFile(result.getFile(), result.getLine(),
	// result.getColumn());
	// }
	//
	// });
	// }
}
