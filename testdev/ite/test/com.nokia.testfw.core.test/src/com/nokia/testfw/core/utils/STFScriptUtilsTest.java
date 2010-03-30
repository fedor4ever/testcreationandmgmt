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
package com.nokia.testfw.core.utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * @author xiaoma
 *
 */
public class STFScriptUtilsTest extends TestCase {
	static final String TEST_SCRIPT_FILE = "stfscript.cfg";
	public void testLoadTestCase() {
		ArrayList<String> testCases;
		try {
			if (new File(TEST_SCRIPT_FILE).exists()) {
				testCases = STFScriptUtils.getTestCasesFromScript(TEST_SCRIPT_FILE);
			} else {
				InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream(TEST_SCRIPT_FILE);
                testCases = STFScriptUtils.getTestCasesFromScript(fileStream);
			}
			assertNotNull(testCases);
			assertEquals(testCases.size(), 12);
			assertEquals(testCases.get(0), "timeout (abort case)");
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
