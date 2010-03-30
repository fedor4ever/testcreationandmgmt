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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.internal.content.ContentTypeManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateStore;

import com.nokia.testfw.codegen.templates.TemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateLoader;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;

@SuppressWarnings("restriction")
public class EclipseTemplateBuilder implements TemplateBuilder {

	private static Map<String, Template> iPath2TemplateMap;

	public static Map<String, Template> getPathTemplateMap(String template) {
		Map<String, Template> lPathTemplateMap = new HashMap<String, Template>();
		String lTemplatePath = template + "/";

		TemplateStore lTemplateStore = CodegenUIPlugin.getDefault()
				.getTemplateStore();

		TemplatePersistenceData[] lTemplateDataArray = lTemplateStore
				.getTemplateData(false);

		for (TemplatePersistenceData data : lTemplateDataArray) {
			String path = data.getId();
			if (path.startsWith(lTemplatePath)) {
				if (data.isEnabled()) {
					lPathTemplateMap.put(
							path.substring(lTemplatePath.length()), data
									.getTemplate());
				}
			}
		}

		return lPathTemplateMap;
	}

	public static Map<String, Template> findAllOriTemplates() {
		if (iPath2TemplateMap == null) {
			iPath2TemplateMap = new HashMap<String, Template>();

			try {
				Map<String, String> lPath2PatternMap = TemplateLoader.load("");
				for (String lpath : lPath2PatternMap.keySet()) {
					String name = lpath.substring(lpath.lastIndexOf("/") + 1);
					String description = lpath;
					String pattern = lPath2PatternMap.get(lpath);

					ContentTypeManager lContentTypeManager = ContentTypeManager
							.getInstance();
					IContentType type = lContentTypeManager
							.findContentTypeFor(name);
					String contextTypeId;
					if (type != null) {
						contextTypeId = (new StringBuilder(String.valueOf(type
								.getId()))).append(".contenttype_context")
								.toString();
					} else {
						contextTypeId = CodeGenTemplateContextType.FILE_TEMPLATE_CONTEXT_TYPE;
					}

					Template lTemplate = new Template(name, description,
							contextTypeId, pattern, true);
					iPath2TemplateMap.put(lpath, lTemplate);
				}
			} catch (IOException e) {
				IStatus lStatus = new Status(IStatus.ERROR,
						EclipseTemplateBuilder.class.getName(),
						"Exception was thrown while loading templates.", e);
				CodegenUIPlugin.getDefault().getLog().log(lStatus);
			}
		}
		return iPath2TemplateMap;

	}

	public Map<String, String> build(String path) {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, org.eclipse.jface.text.templates.Template> lTemplatePathMap = getPathTemplateMap(path);

		for (String lpath : lTemplatePathMap.keySet()) {
			org.eclipse.jface.text.templates.Template lEclipseTemplate = lTemplatePathMap
					.get(lpath);
			String pattern = lEclipseTemplate.getPattern();
			result.put(lpath, pattern);
		}
		return result;
	}
}
