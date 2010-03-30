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
package com.nokia.testfw.codegen;

import com.nokia.testfw.codegen.model.ClassNodeImplTest;
import com.nokia.testfw.codegen.model.MethodNodeImplTest;
import com.nokia.testfw.codegen.model.ProjectNodeImplTest;
import com.nokia.testfw.codegen.templates.CMDTemplateBuilderTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllPureJUnitTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"JUNIT Test for com.nokia.testfw.codegen");
		// $JUnit-BEGIN$
		suite.addTestSuite(CodegenEngineTest.class);
		suite.addTestSuite(StringResourceLoaderTest.class);
		suite.addTestSuite(ClassNodeImplTest.class);
		suite.addTestSuite(MethodNodeImplTest.class);
		suite.addTestSuite(ProjectNodeImplTest.class);
		suite.addTestSuite(CMDTemplateBuilderTest.class);
		// $JUnit-END$
		return suite;
	}

}
