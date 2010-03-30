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

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CMDTemplateBuilder implements TemplateBuilder {

	public Map<String, String> build(String path) throws IOException {
		String templatePath = path.replaceAll("\\\\", "/");
		if (!templatePath.endsWith("/")) {
			templatePath += "/";
		}

		Map<String, String> result = TemplateLoader.load(path);

		if (result.size() == 0) {
			File templateRootDir = new File(templatePath);
			if (templateRootDir.exists() && templateRootDir.isDirectory()) {
				result.putAll(TemplateLoader.searchTemplates(templateRootDir));
			} else {
				throw new IOException("Can not find templat: " + path);
			}
		}
		return result;
	}

}
