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

import com.nokia.testfw.codegen.templates.CMDTemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateBuilderFactory;

import junit.framework.TestCase;

public class TemplateBuilderFactoryTest extends TestCase {

	public void testNewCMDTemplateBuilder() {
		System.setProperty(TemplateBuilderFactory.TEMPLATEBUILDERCLASS,
				CMDTemplateBuilder.class.getName());
		TemplateBuilderFactory factory = TemplateBuilderFactory.newInstance();
		TemplateBuilder builder = null;
		try {
			builder = factory.newTemplateBuilder();
		} catch (Exception e) {
			e.printStackTrace();
			fail("creating CMDTemplateBuilder failed.");
		}
		assertTrue(builder instanceof CMDTemplateBuilder);
		System.setProperty(TemplateBuilderFactory.TEMPLATEBUILDERCLASS,
				TemplateBuilderFactory.DEFAULTTEMPLATEBUILDERCLASS);
	}
}
