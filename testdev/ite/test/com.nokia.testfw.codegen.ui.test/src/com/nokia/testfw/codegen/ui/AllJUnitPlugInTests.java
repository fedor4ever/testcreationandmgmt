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
package com.nokia.testfw.codegen.ui;

import com.nokia.testfw.codegen.ui.preferences.PreferenceUtilTest;
import com.nokia.testfw.codegen.ui.preferences.TESTFWPropertiesPreferencePageTest;
import com.nokia.testfw.codegen.ui.preferences.TESTFWTemplatePreferencePageTest;
import com.nokia.testfw.codegen.ui.templates.EclipseTemplateBuilderTest;
import com.nokia.testfw.codegen.ui.templates.TemplateBuilderFactoryTest;
import com.nokia.testfw.codegen.ui.wizard.NewTestCaseWizardTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJUnitPlugInTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"JUNIT Plugin Test for com.nokia.testfw.codegen");
		// $JUnit-BEGIN$
		suite.addTestSuite(CodegenUIPluginTest.class);
		// preference
		suite.addTestSuite(PreferenceUtilTest.class);
		suite.addTestSuite(TESTFWPropertiesPreferencePageTest.class);
		suite.addTestSuite(TESTFWTemplatePreferencePageTest.class);
		// templates
		suite.addTestSuite(EclipseTemplateBuilderTest.class);
		suite.addTestSuite(TemplateBuilderFactoryTest.class);
		// wizard
		suite.addTestSuite(NewTestCaseWizardTest.class);
		// $JUnit-END$
		return suite;
	}

}
