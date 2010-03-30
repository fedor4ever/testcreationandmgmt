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

import com.nokia.testfw.test.framework.ControlTestCase;
import com.nokia.testfw.test.utils.TestUtils;
import com.nokia.testfw.resultview.view.CounterPanel;

/**
 * @author xiaoma
 *
 */
public class CounterPanelTest extends ControlTestCase {

	CounterPanel counterPanel;
	
	/* (non-Javadoc)
	 * @see com.nokia.testfw.test.framework.CompositeTestCase#createTestComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createTestControl(Composite parent) {
		counterPanel = new CounterPanel(parent);
		return counterPanel;
	}
	
	public void testCounter() {
		counterPanel.setCounter(10, 5, 3, 2);
		TestUtils.delay(2000);
		assertEquals(counterPanel.suiteLabel.getText(), "Runs: 5/10");
		assertEquals(counterPanel.passedLabel.getText(), "Passed: 3");
		assertEquals(counterPanel.failedLabel.getText(), "Failed: 2");
		//test reset
		counterPanel.setCounter(1000, 900, 800, 100);
		counterPanel.reset();
		TestUtils.delay(2000);
		assertEquals(counterPanel.suiteLabel.getText(), "Runs: 0/0");
		assertEquals(counterPanel.passedLabel.getText(), "Passed: 0");
		assertEquals(counterPanel.failedLabel.getText(), "Failed: 0");
		//test update counter on the fly
		counterPanel.setTotalTests(20);
		assertEquals(counterPanel.suiteLabel.getText(), "Runs: 0/20");
		assertEquals(counterPanel.passedLabel.getText(), "Passed: 0");
		assertEquals(counterPanel.failedLabel.getText(), "Failed: 0");
		TestUtils.delay(2000);
		counterPanel.addPassedTest();
		counterPanel.addPassedTest();
		TestUtils.delay(2000);
		assertEquals(counterPanel.suiteLabel.getText(), "Runs: 2/20");
		assertEquals(counterPanel.passedLabel.getText(), "Passed: 2");
		assertEquals(counterPanel.failedLabel.getText(), "Failed: 0");
		counterPanel.addFailedTest();
		assertEquals(counterPanel.suiteLabel.getText(), "Runs: 3/20");
		assertEquals(counterPanel.passedLabel.getText(), "Passed: 2");
		assertEquals(counterPanel.failedLabel.getText(), "Failed: 1");
		TestUtils.delay(2000);
	}
	

}
