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
import com.nokia.testfw.resultview.view.TestProgressBar;


/**
 * @author xiaoma
 *
 */
public class ProgressBarTest extends ControlTestCase {

	TestProgressBar progressBar;
	/* (non-Javadoc)
	 * @see com.nokia.testfw.test.framework.CompositeTestCase#createTestComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createTestControl(Composite parent) {
		
		progressBar = new TestProgressBar(parent);
		return progressBar;
	}
	
	public void testUpdate() {
		int maxLen = 10;
	    progressBar.setTestCounts(maxLen);
	    for (int i = 0 ; i < maxLen; i++) {
	        progressBar.step(true);
	        TestUtils.delay(200);
	    }
	    assertEquals(progressBar.totalCount, maxLen);
	    assertEquals(progressBar.currentCount, maxLen);
	    //test reset
	    progressBar.reset();
	    assertEquals(progressBar.currentCount, 0);
	    TestUtils.delay(2000); 
	    progressBar.setTestCounts(5);
	    TestUtils.delay(2000);	    
	}
	
	public void testFailed() {
		int maxLen = 6;
		progressBar.setTestCounts(maxLen);
		//two passed caess
		progressBar.step(true);
		progressBar.step(true);
		TestUtils.delay(2000);
		//one failed case, the color of the progress bar should change.
		progressBar.step(false);
		progressBar.step(false);
		progressBar.step(false);
		TestUtils.delay(2000);
		progressBar.stopTest();
		TestUtils.delay(2000);
		
	}
	
}
