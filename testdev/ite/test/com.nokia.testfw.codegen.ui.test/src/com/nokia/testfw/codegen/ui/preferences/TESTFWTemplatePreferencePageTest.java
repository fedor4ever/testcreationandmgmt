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

public class TESTFWTemplatePreferencePageTest extends PreferenceTestCase {

	public void testPerform() {

		TestUtils.delay(5000);
	}

	@Override
	protected String getPageId() {
		return "com.nokia.testfw.codegen.ui.preferences.TESTFWTemplatePreferencePage";
	}

}
