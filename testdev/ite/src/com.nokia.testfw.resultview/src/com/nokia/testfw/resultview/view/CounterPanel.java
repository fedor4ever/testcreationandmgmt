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
package com.nokia.testfw.resultview.view;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.resultview.ResultViewPlugin;

public class CounterPanel extends Composite {
	private Image passedIcon = ResultViewPlugin.getImage("ovr16/success.gif"); //$NON-NLS-1$
	private Image failedIcon = ResultViewPlugin.getImage("ovr16/failure.gif"); //$NON-NLS-1$

	protected CLabel suiteLabel;
	protected CLabel passedLabel;
	protected CLabel failedLabel;
	int totalTests;
	int runedTests;
	int passed;
	int failed;

	public CounterPanel(Composite parent) {
		super(parent, SWT.WRAP);

		GridLayout gl = new GridLayout(1, false);
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		GridData gd = new GridData(GridData.FILL_BOTH);
		setLayout(gl);
		setLayoutData(gd);
		createCounters();
	}

	public void dispose() {
		// if (!passedIcon.isDisposed()) {
		// passedIcon.dispose();
		// }
		// if (!failedIcon.isDisposed()) {
		// failedIcon.dispose();
		// }
		super.dispose();
	}

	private void createCounters() {
		Composite comp = new Composite(this, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginWidth = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 5;
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comp.setLayout(gridLayout);

		suiteLabel = createLabel(comp, "Runs: 0/0", null);
		passedLabel = createLabel(comp, "Passed: 0", passedIcon);
		failedLabel = createLabel(comp, "Failed: 0", failedIcon);

	}

	private CLabel createLabel(Composite parent, String text, Image image) {
		CLabel label = new CLabel(parent, SWT.NONE);
		if (null != image) {
			label.setImage(image);
		}
		label.setText(text);
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL));
		label.setFont(JFaceResources.getDialogFont());

		return label;
	}

	/**
	 * start counter by set total tests
	 */
	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
		runedTests = 0;
		redrawCounter();
	}

	/**
	 * update counter by adding a passed test to the counter
	 */
	public void addPassedTest() {
		passed++;
		runedTests++;
		redrawCounter();

	}

	/**
	 * update counter by adding a failed test to the counter
	 */
	public void addFailedTest() {
		failed++;
		runedTests++;
		redrawCounter();
	}

	/**
	 * reset all counters to 0 and redraw counter
	 */
	public void reset() {
		totalTests = 0;
		runedTests = 0;
		passed = 0;
		failed = 0;
		redrawCounter();
	}

	/**
	 * update counters to assigned value and redraw
	 * 
	 * @param totalTests
	 * @param runnedTests
	 * @param passed
	 * @param failed
	 */
	public void setCounter(int totalTests, int runnedTests, int passed,
			int failed) {
		this.totalTests = totalTests;
		this.runedTests = runnedTests;
		this.passed = passed;
		this.failed = failed;
		redrawCounter();
	}

	public void addCounter() {
		totalTests++;
		redrawCounter();
	}

	private void redrawCounter() {
		suiteLabel.setText("Runs: " + runedTests + "/" + totalTests);
		passedLabel.setText("Passed: " + passed);
		failedLabel.setText("Failed: " + failed);
	}

	/**
	 * initialize the progress bar with a test result
	 * 
	 * @param result
	 * @see TestRunResult
	 */
	public void init(TestRunResult result) {
		totalTests = result.getTestCount();
		runedTests = result.getPassedTestCount() + result.getfailedTestCount();
		passed = result.getPassedTestCount();
		failed = result.getfailedTestCount();
		redrawCounter();
	}

}
