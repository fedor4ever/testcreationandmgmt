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

package com.nokia.testfw.codegen.ui.templates;

import java.util.Map;

import org.eclipse.jface.text.templates.Template;

import junit.framework.TestCase;

public class EclipseTemplateBuilderTest extends TestCase {

	EclipseTemplateBuilder builder;

	protected void setUp() throws Exception {
		builder = new EclipseTemplateBuilder();
	}

	public void testGetPathTemplateMap() {
		Map<String, Template> map = EclipseTemplateBuilder
				.getPathTemplateMap("SymbianUnitTest");
		assertNotNull(map);
		assertTrue(map.size() > 0);
	}

	public void testFindAllOriTemplates() {
		Map<String, Template> map = EclipseTemplateBuilder
				.findAllOriTemplates();
		assertNotNull(map);
		assertTrue(map.size() > 0);
	}

	public void testBulid() {
		Map<String, String> map = builder.build("SymbianUnitTest");
		assertNotNull(map);
		assertTrue(map.size() > 0);
	}

}
