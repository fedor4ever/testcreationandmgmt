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
package com.nokia.testfw.launch;

import com.nokia.testfw.launch.processor.SUTProcessorTest;
import com.nokia.testfw.launch.processor.STFProcessorTest;
import com.nokia.testfw.launch.ui.STFEmulationConfigTagGroupTest;
import com.nokia.testfw.launch.ui.SUTEmulationConfigTagGroupTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJUnitPlugInTests extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"com.nokia.testfw.launch.junitplugin");
		suite.addTestSuite(SUTProcessorTest.class);
		suite.addTestSuite(SUTEmulationConfigTagGroupTest.class);
		suite.addTestSuite(SUTEmulationLaunchTest.class);
		suite.addTestSuite(STFProcessorTest.class);
		suite.addTestSuite(STFEmulationConfigTagGroupTest.class);
		suite.addTestSuite(STFEmulationLaunchTest.class);
		return suite;
	}
}
