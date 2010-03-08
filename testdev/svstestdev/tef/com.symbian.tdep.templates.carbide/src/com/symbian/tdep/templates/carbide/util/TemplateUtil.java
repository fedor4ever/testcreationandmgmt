/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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



package com.symbian.tdep.templates.carbide.util;

import org.eclipse.jface.text.templates.Template;

import com.symbian.tdep.templates.carbide.TefTemplatesCarbidePlugin;
import com.symbian.tdep.templates.carbide.contenttype.FileTemplateContextType;
import com.symbian.tdep.templates.carbide.preferences.PreferenceConstants;

public class TemplateUtil {

	public static final String NAME_BLOCK_SERVER_H = "BasenameBlockServer.h";

	public static final String NAME_BLOCK_SERVER_CPP = "BasenameBlockServer.cpp";

	public static final String NAME_BLOCK_CONTROLLER_H = "BasenameBlockController.h";

	public static final String NAME_BLOCK_CONTROLLER_CPP = "BasenameBlockController.cpp";

	public static Template findTemplate(String templateName) {

		Template[] templates = TefTemplatesCarbidePlugin.getDefault()
				.getTemplateStore().getTemplates(
						FileTemplateContextType.FILE_TEMPLATE_CONTEXT_TYPE);

		for (Template template : templates) {
			if (template.getName().equals(templateName))
				return template;
		}

		return null;
	}

	public static String getClassWrapperName(String name) {

		String pattern = TefTemplatesCarbidePlugin.getDefault()
				.getPreferenceStore().getString(
						PreferenceConstants.CLASS_WRAPPER_PATTERN).trim();

		return pattern.replace("${className}", name);
	}
	
	public static String getMethodWrapperName(String name){
		String pattern = TefTemplatesCarbidePlugin.getDefault()
				.getPreferenceStore().getString(
						PreferenceConstants.METHOD_WRAPPER_PATTERN).trim();

		return pattern.replace("${methodName}", name);
	}
	
}
