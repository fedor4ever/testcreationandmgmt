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

import junit.framework.TestCase;

public class CodegenUIPluginTest extends TestCase {

	CodegenUIPlugin plugin;

	protected void setUp() throws Exception {
		plugin = CodegenUIPlugin.getDefault();
	}

	public void testGetTemplateStore() {
		assertNotNull(plugin.getTemplateStore());
	}

	public void testGetContextTypeRegistry() {
		assertNotNull(plugin.getContextTypeRegistry());
	}

}
