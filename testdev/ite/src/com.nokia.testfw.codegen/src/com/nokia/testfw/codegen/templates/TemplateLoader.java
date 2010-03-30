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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.nokia.testfw.codegen.StringResourceLoader;

public class TemplateLoader {

	public static String TEMPLATES_DIR = "templates/";

	public static Map<String, String> load(String template) throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		String templatePath = template.replaceAll("\\\\", "/");
		String lTemplateRoot = TEMPLATES_DIR + templatePath;
		if (!lTemplateRoot.endsWith("/")) {
			lTemplateRoot += "/";
		}

		URL lSourceRootURL = TemplateLoader.class.getProtectionDomain()
				.getCodeSource().getLocation();

		File lSourceRoot = new File(lSourceRootURL.getFile());

		if (lSourceRoot.isFile()) {
			JarFile jf = new JarFile(lSourceRoot);
			Enumeration<JarEntry> resources = jf.entries();
			while (resources.hasMoreElements()) {
				JarEntry je = (JarEntry) resources.nextElement();

				// find a file that matches this string from anywhere in my
				// jar file
				if (je.getName().startsWith(lTemplateRoot)) {
					if (je.isDirectory()) {
						continue;
					}
					String filename = je.getName();
					InputStream is = jf.getInputStream(jf.getEntry(filename));
					String pattern = StringResourceLoader.getString(is);
					String lpath = filename.substring(lTemplateRoot.length());
					result.put(lpath, pattern);
					is.close();
				}
			}
		} else {// for test in dev env
			File templateRootDir = new File(lSourceRoot, lTemplateRoot);
			lTemplateRoot = templateRootDir.getAbsolutePath() + "/";
			if (templateRootDir.exists() && templateRootDir.isDirectory()) {
				result.putAll(searchTemplates(templateRootDir, lTemplateRoot));
			}
		}
		return result;
	}

	public static Map<String, String> searchTemplates(File root)
			throws IOException {
		String lTemplateRoot = root.getPath() + File.separator;
		return searchTemplates(root, lTemplateRoot);
	}

	private static Map<String, String> searchTemplates(File root,
			String filterPath) throws IOException {
		Map<String, String> lPathPatternMap = new HashMap<String, String>();
		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				lPathPatternMap.putAll(searchTemplates(file, filterPath));
			} else {
				InputStream lInputStream = new FileInputStream(file);
				String pattern = StringResourceLoader.getString(lInputStream);
				String lPath = file.getAbsolutePath().substring(
						filterPath.length());
				lPath = lPath.replaceAll("\\\\", "/");
				lPathPatternMap.put(lPath, pattern);
			}
		}
		return lPathPatternMap;
	}
}
