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
package com.nokia.testfw.core;

import com.nokia.testfw.core.model.TestCaseTest;
import com.nokia.testfw.core.model.TestSuiteTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiaoma
 *
 */
public class AllPureJUnitTests extends TestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("com.nokia.testfw.core.junit");
		suite.addTestSuite(TestCaseTest.class);
		suite.addTestSuite(TestSuiteTest.class);
		return suite;
	}
}
