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
package com.nokia.testfw.resultview;

import com.nokia.testfw.resultview.model.TestRunSessionTest;
import com.nokia.testfw.resultview.view.CounterPanelTest;
import com.nokia.testfw.resultview.view.ProgressBarTest;
import com.nokia.testfw.resultview.view.TestResultTreeTest;
import com.nokia.testfw.resultview.view.TestRunViewPartTest;
import com.nokia.testfw.resultview.view.FailureTraceTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiaoma
 * 
 */
public class AllJUnitPlugInTests extends TestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"com.nokia.testfw.resultview.junitplugin");
		suite.addTestSuite(TestRunSessionTest.class);
		suite.addTestSuite(CounterPanelTest.class);
		suite.addTestSuite(ProgressBarTest.class);
		suite.addTestSuite(TestResultTreeTest.class);
		suite.addTestSuite(TestRunViewPartTest.class);
		suite.addTestSuite(FailureTraceTest.class);
		return suite;
	}
}
