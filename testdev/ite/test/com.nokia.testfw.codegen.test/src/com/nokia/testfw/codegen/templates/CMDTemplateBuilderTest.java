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

package com.nokia.testfw.codegen.templates;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

public class CMDTemplateBuilderTest extends TestCase {

	CMDTemplateBuilder builder;

	protected void setUp() throws Exception {
		builder = new CMDTemplateBuilder();
	}

	public void testSUTBulid() {
		try {
			Map<String, String> map = builder.build("SymbianUnitTest");
			assertTrue(map.size() > 0);
		} catch (IOException e) {
			fail();
		}
	}
}
