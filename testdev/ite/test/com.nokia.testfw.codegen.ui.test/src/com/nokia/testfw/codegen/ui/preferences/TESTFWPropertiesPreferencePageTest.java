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

import com.nokia.testfw.test.framework.PreferenceTestCase;
import com.nokia.testfw.test.utils.TestUtils;

public class TESTFWPropertiesPreferencePageTest extends PreferenceTestCase {

	TESTFWPropertiesPreferencePage page;

	@Override
	protected String getPageId() {
		return "com.nokia.testfw.codegen.ui.preferences.TESTFWPropertiesPreferencePage";
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		page = (TESTFWPropertiesPreferencePage) dialog.getSelectedPage();
	}

	public void testUID3MaxValue() {
		assertTrue(page.isValid());
		String oldValue = page.iUID3MaxValue.getStringValue();
		page.iUID3MaxValue.setStringValue("abcdefg");
		assertFalse(page.isValid());
		TestUtils.delay(3000);
		page.iUID3MaxValue.setStringValue(oldValue);
		assertTrue(page.isValid());
		TestUtils.delay(3000);
	}

	public void testUID3MinValue() {
		assertTrue(page.isValid());
		String oldValue = page.iUID3MinValue.getStringValue();
		page.iUID3MinValue.setStringValue("abcdefg");
		assertFalse(page.isValid());
		TestUtils.delay(3000);
		page.iUID3MinValue.setStringValue(oldValue);
		assertTrue(page.isValid());
		TestUtils.delay(3000);
	}

	public void testTestFolderName() {
		assertTrue(page.isValid());
		String oldValue = page.iTestFolderName.getStringValue();
		page.iTestFolderName.setStringValue("");
		assertFalse(page.isValid());
		TestUtils.delay(3000);
		page.iTestFolderName.setStringValue(oldValue);
		assertTrue(page.isValid());
		TestUtils.delay(3000);
	}

	public void testAutherName() {
		assertTrue(page.isValid());
		String oldValue = page.iAutherName.getStringValue();
		page.iAutherName.setStringValue("");
		assertTrue(page.isValid());
		TestUtils.delay(3000);
		page.iAutherName.setStringValue(oldValue);
		assertTrue(page.isValid());
		TestUtils.delay(3000);
	}
}
