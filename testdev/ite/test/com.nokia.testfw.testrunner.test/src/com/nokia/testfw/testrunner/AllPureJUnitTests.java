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
package com.nokia.testfw.testrunner;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiaoma
 * 
 */
public class AllPureJUnitTests extends TestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("com.nokia.testfw.testrunner.junit");
		// suite.addTestSuite(TailFileInputStreamTest.class);
		return suite;
	}

}
