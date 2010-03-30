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

package com.nokia.testfw.codegen.ui.preferences;

import java.io.File;

import junit.framework.TestCase;

public class PreferenceUtilTest extends TestCase {

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	public void testGetTestFolderName() {
		String folder = PreferenceUtil.getTestFolderName(null);
		assertNotNull(folder);
		String folder2 = PreferenceUtil.getTestFolderName("base");
		assertTrue(folder2.equals("base" + File.separator + folder));
	}

	public void testGetRandomAppUIDAndValidateAppUIDText() {
		String uid = PreferenceUtil.getRandomAppUID();
		assertNotNull(uid);
		assertTrue(PreferenceUtil.validateAppUIDText(uid));
	}

	public void testCreateCanonicalHexString() {
		String hexString = PreferenceUtil.createCanonicalHexString(10);
		assertEquals(hexString, "0x0000000A");
	}

}
