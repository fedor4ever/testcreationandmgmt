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

public class TemplateBuilderFactory {

	public static String TEMPLATEBUILDERCLASS = "com.nokia.testfw.codegen.templates.TemplateBuilder";
	public static String DEFAULTTEMPLATEBUILDERCLASS = CMDTemplateBuilder.class
			.getName();

	public String iTemplateBuilderClassName;

	public static TemplateBuilderFactory newInstance() {
		String lClassName = System.getProperty(TEMPLATEBUILDERCLASS,
				DEFAULTTEMPLATEBUILDERCLASS);
		return new TemplateBuilderFactory(lClassName);
	}

	private TemplateBuilderFactory(String templateClassName) {
		iTemplateBuilderClassName = templateClassName;
	}

	public TemplateBuilder newTemplateBuilder() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		return (TemplateBuilder) Class.forName(iTemplateBuilderClassName)
				.newInstance();
	}
}
