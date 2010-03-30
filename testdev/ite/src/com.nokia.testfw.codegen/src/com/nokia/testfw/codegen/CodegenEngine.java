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
package com.nokia.testfw.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.nokia.testfw.codegen.model.IClassNode;
import com.nokia.testfw.codegen.model.IMethodNode;
import com.nokia.testfw.codegen.model.IProjectNode;
import com.nokia.testfw.codegen.templates.TemplateBuilder;
import com.nokia.testfw.codegen.templates.TemplateBuilderFactory;

public class CodegenEngine {

	VelocityContext iContext;
	VelocityEngine iEngine;
	String iOutputPath;
	boolean isInit = false;
	boolean writeToFile = true;
	Map<String, ChangeFileContent> iResultMap;
	public final static String REPEAT = "repeat";
	public final static String PLAIN = "plain";

	/**
	 * @return the writeToFile
	 */
	public boolean isWriteToFile() {
		return writeToFile;
	}

	/**
	 * @param writeToFile
	 *            the writeToFile to set
	 */
	public void setWriteToFile(boolean writeToFile) {
		this.writeToFile = writeToFile;
	}

	@SuppressWarnings("unchecked")
	public CodegenEngine(String outputPath, Map data) {

		iOutputPath = outputPath;
		iContext = new VelocityContext(data);
		iEngine = new VelocityEngine();
	}

	public void init() throws Exception {
		Properties p = new Properties();
		p.put("resource.loader", "string");
		p.put("string.resource.loader.class", StringResourceLoader.class
				.getName());
		iEngine.init(p);
		isInit = true;
	}

	public Map<String, ChangeFileContent> generate(String template)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		if (isInit == false) {
			throw new IllegalStateException(
					"CodegenEngine must be initialized.");
		}

		TemplateBuilder lTemplateBuilder = null;
		lTemplateBuilder = TemplateBuilderFactory.newInstance()
				.newTemplateBuilder();

		Map<String, String> lPathTemplateMap = lTemplateBuilder.build(template);
		collectBuildInfo();

		iResultMap = new TreeMap<String, ChangeFileContent>();

		for (String path : lPathTemplateMap.keySet()) {
			VelocityContext lContext = iContext;
			if (path.endsWith(".mmp")) {
				String buildBaseDir = iOutputPath + File.separator;
				if (path.lastIndexOf("/") > 0) {
					buildBaseDir += path.substring(0, path.lastIndexOf("/"));
				}
				buildBaseDir = new File(buildBaseDir).getCanonicalPath();
				lContext = adjustBuildInfo(buildBaseDir, lContext);
			}

			String pattern = lPathTemplateMap.get(path);
			dealSingleTemplate(pattern, path, lContext);
		}
		return iResultMap;
	}

	@SuppressWarnings("unchecked")
	private void dealSingleTemplate(String pattern, final String path,
			VelocityContext aContext) throws ResourceNotFoundException,
			ParseErrorException, Exception {
		boolean containClassName = false;
		boolean containMethodName = false;

		if (path.indexOf("${class_name}") > 0) {
			containClassName = true;
		}
		if (path.indexOf("${method_name}") > 0) {
			containMethodName = true;
		}

		String filepath;
		if (!containClassName && !containMethodName) {
			filepath = parse(path, aContext);
			String content = getContent(filepath, pattern, aContext);
			writeToFile(filepath, content);
		} else {
			IProjectNode lProjectNode = (IProjectNode) aContext
					.get("project_object");
			Set<IClassNode> lClassNodeSet = (Set<IClassNode>) lProjectNode
					.getChildren();
			VelocityContext lContext = (VelocityContext) aContext.clone();
			for (IClassNode lClassNode : lClassNodeSet) {
				lContext.put("class_name", lClassNode.getName());
				lContext.put("class_object", lClassNode);
				if (containMethodName) {
					Set<IMethodNode> lMethodNodeSet = (Set<IMethodNode>) lClassNode
							.getChildren();
					for (IMethodNode lMethodNode : lMethodNodeSet) {
						lContext.put("method_name", lMethodNode.getName());
						lContext.put("method_object", lMethodNode);

						filepath = parse(path, lContext);
						String content = getContent(filepath, pattern, lContext);
						writeToFile(filepath, content);
					}
				} else {
					filepath = parse(path, lContext);
					String content = getContent(filepath, pattern, lContext);
					writeToFile(filepath, content);
				}
			}
		}
	}

	private String getContent(String filepath, String pattern,
			VelocityContext aContext) throws ResourceNotFoundException,
			ParseErrorException, Exception {
		ChangeFileContent lChangeFileContent = new ChangeFileContent();
		lChangeFileContent.setFilePath(filepath);
		lChangeFileContent.setOldContent(readFromFile(filepath));
		if (lChangeFileContent.getOldContent() == null) {
			StringBuilder content = new StringBuilder();
			Map<String, String> lFragmentsMap = getFragments(pattern);
			for (String fragment : lFragmentsMap.keySet()) {
				String type = lFragmentsMap.get(fragment);
				if (REPEAT.equals(type)) {
					if (fragment.indexOf("${RepeatCounter}") == -1) {
						content.append(parse(fragment, aContext));
					} else {
						aContext.remove("RepeatCounter");
						String workcontent = parse(fragment, aContext);
						while (workcontent.indexOf("${RepeatCounter}") != -1) {
							String lines[] = workcontent.split("\n");
							StringBuilder sb = new StringBuilder();
							boolean findCounter = false;
							for (int i = 0; i < lines.length; i++) {
								lines[i] += "\n";
								if (findCounter == false
										&& lines[i].indexOf("${RepeatCounter}") != -1) {
									findCounter = true;
									String identityLine = lines[i].replace(
											"${RepeatCounter}", "");

									int counter = 1;
									while (workcontent.indexOf(identityLine) != -1) {
										counter++;
										identityLine = lines[i].replace(
												"${RepeatCounter}", Integer
														.toString(counter));
									}
									sb.append(identityLine);
								} else {
									sb.append(lines[i]);
								}
							}
							workcontent = sb.toString();
						}
						content.append(workcontent);
					}
				} else {
					aContext.put("RepeatCounter", "");
					content.append(parse(fragment, aContext));
				}
			}
			lChangeFileContent.setNewContent(content.toString());
		} else {
			String content = lChangeFileContent.getOldContent();
			Map<String, String> lRepeatSectionMap = getRepeatSections(pattern);
			for (String mark : lRepeatSectionMap.keySet()) {
				String section = lRepeatSectionMap.get(mark);

				int startIndex = content.indexOf(mark + " Begin");
				if (startIndex == -1) {
					throw new IllegalStateException("Cannot find mark: " + mark
							+ " Begin");
				}
				startIndex = content.indexOf("\n", startIndex) + 1;
				int endIndex = content.indexOf(mark + " End");
				if (endIndex == -1) {
					throw new IllegalStateException("Cannot find mark: " + mark
							+ " End");
				}
				String subcontent = content.substring(0, endIndex);
				endIndex = subcontent.lastIndexOf("\n") + 1;
				if ((startIndex > endIndex)) {
					throw new IllegalStateException("Illegal mark definition: "
							+ mark);
				}
				String workcontent = content.substring(startIndex, endIndex);

				if (section.indexOf("${RepeatCounter}") == -1) {
					String appendent = parse(section, aContext);

					Set<String> appendentSet = new LinkedHashSet<String>();
					String lines[] = appendent.split("\n");
					for (int i = 0; i < lines.length; i++) {
						lines[i] += "\n";
						if (workcontent.indexOf(lines[i]) == -1) {
							appendentSet.add(lines[i]);
						}
					}
					if (appendentSet.size() > 0) {
						StringBuilder sb = new StringBuilder();
						for (Iterator<String> itr = appendentSet.iterator(); itr
								.hasNext();) {
							sb.append(itr.next());
						}
						workcontent = workcontent + "\n" + sb.toString();
					}
				} else {
					aContext.remove("RepeatCounter");
					String appendent = parse(section, aContext);

					while (appendent.indexOf("${RepeatCounter}") != -1) {
						String lines[] = appendent.split("\n");
						StringBuilder sb = new StringBuilder();
						boolean findCounter = false;

						for (int i = 0; i < lines.length; i++) {
							lines[i] += "\n";
							if (findCounter == false
									&& lines[i].indexOf("${RepeatCounter}") != -1) {
								findCounter = true;
								String identityLine = lines[i].replace(
										"${RepeatCounter}", "");

								int counter = 1;
								while (workcontent.indexOf(identityLine) != -1
										|| appendent.indexOf(identityLine) != -1) {
									counter++;
									identityLine = lines[i].replace(
											"${RepeatCounter}", Integer
													.toString(counter));
								}
								sb.append(identityLine);
							} else {
								sb.append(lines[i]);
							}
						}
						appendent = sb.toString();
					}
					if (appendent.trim().length() > 0) {
						workcontent = workcontent + "\n" + appendent;
					}
				}

				content = content.substring(0, startIndex) + workcontent
						+ content.substring(endIndex);
			}
			lChangeFileContent.setNewContent(content);
		}
		iResultMap.put(filepath, lChangeFileContent);
		return lChangeFileContent.getNewContent();
	}

	private String pretreat(String pattern) {
		return pattern.replaceAll("#", "\\\\#").replaceAll("//!!", "#");
	}

	private String posttreat(String pattern) {
		return pattern.replaceAll("\\\\#", "#");
	}

	private String parse(String pattern, VelocityContext context)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		String result = null;
		StringWriter writer = new StringWriter();
		Template t = iEngine.getTemplate(pretreat(pattern));
		t.merge(context, writer);
		result = posttreat(writer.toString());
		return result;
	}

	private String readFromFile(String path) throws IOException {
		String filepath = iOutputPath + File.separator + path;
		File outputFile = new File(filepath);
		if (outputFile.exists()) {
			InputStream lInputStream = new FileInputStream(outputFile);
			return StringResourceLoader.getString(lInputStream);
		}
		return null;
	}

	private void writeToFile(String path, String content) throws IOException {
		if (!writeToFile)
			return;
		String filepath = iOutputPath + File.separator + path;
		File outputFile = new File(filepath);
		if (!outputFile.exists()) {
			filepath = outputFile.getCanonicalPath();
			String parentpath = filepath.substring(0, filepath
					.lastIndexOf(File.separator));
			File parentDir = new File(parentpath);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			outputFile.createNewFile();
		}
		PrintWriter writer;
		writer = new PrintWriter(new FileWriter(outputFile));
		writer.write(content);
		writer.flush();
		writer.close();
	}

	@SuppressWarnings("unchecked")
	private void collectBuildInfo() {
		Set<String> lTestedIncludeSet = new TreeSet<String>();
		IProjectNode lProjectNode = (IProjectNode) iContext
				.get("project_object");

		for (IClassNode lClassNode : (Set<IClassNode>) lProjectNode
				.getChildren()) {
			String lDeclLocation = lClassNode.getDeclLocation();
			if (lDeclLocation != null) {
				lTestedIncludeSet.add(lDeclLocation.substring(0, lDeclLocation
						.lastIndexOf(File.separator)));
			}
		}

		lProjectNode.getUserIncludes().addAll(lTestedIncludeSet);
		iContext.put("user_include", lProjectNode.getUserIncludes());
		iContext.put("system_include", lProjectNode.getSystemIncludes());
		iContext.put("library", lProjectNode.getLibrarys());

		if (iContext.get("UID3") == null) {
			iContext.put("UID3", "0xE" + (new Random()).nextInt(9999999));
		}
	}

	@SuppressWarnings("unchecked")
	private VelocityContext adjustBuildInfo(String baseDir,
			VelocityContext aContext) {
		VelocityContext lContext = (VelocityContext) aContext.clone();
		Set<String> lTestedIncludeSet = new TreeSet<String>();
		for (String path : (Set<String>) lContext.get("user_include")) {
			lTestedIncludeSet.add(genRelativePath(baseDir, path));
		}
		lContext.put("user_include", lTestedIncludeSet);

		// Map<String, Set<String>> lTestedSourceMap = new TreeMap<String,
		// Set<String>>();
		// Map<String, Set<String>> originalMap = (Map<String, Set<String>>)
		// lContext
		// .get("sourcepath_map");
		// for (String path : originalMap.keySet()) {
		// Set fileSet = originalMap.get(path);
		// lTestedSourceMap.put(genRelativePath(baseDir, path), fileSet);
		// }
		// lContext.put("sourcepath_map", lTestedSourceMap);

		return lContext;
	}

	// Return relative path
	private String genRelativePath(String base, String target) {
		String separator = File.separator;
		if ("\\".equals(separator)) {
			separator = "\\\\";
		}
		String[] baseSegment = base.split(separator);
		String[] targetSegment = target.split(separator);
		int counter = 0;
		for (; counter < baseSegment.length; counter++) {
			if (counter < targetSegment.length
					&& baseSegment[counter].equals(targetSegment[counter])) {
				continue;
			} else {
				break;
			}
		}

		if (counter == 0) {
			return target;
		}
		if (counter == baseSegment.length && counter == targetSegment.length) {
			return ".";
		}

		String path = genAncestor(baseSegment.length - counter);
		for (int i = counter; i < targetSegment.length; i++) {
			if ("".equals(path)) {
				path += targetSegment[i];
			} else {
				path += "/" + targetSegment[i];
			}
		}
		return path;
	}

	// Return ancestor layer by ..
	private String genAncestor(int deep) {
		String result = "";
		if (deep > 0) {
			result = "..";
			for (int i = 1; i < deep; i++) {
				result += "/..";
			}
		}
		return result;
	}

	private Map<String, String> getFragments(String pattern) {
		Map<String, String> fragmentsMap = new LinkedHashMap<String, String>();

		String lines[] = pattern.split("\n");
		StringBuilder fragment = new StringBuilder();

		for (int count = 0; count < lines.length;) {
			String line = lines[count].trim();
			if (line.startsWith("//!!//!![Repeat Section Begin]::")) {
				String endline = line.replace(
						"//!!//!![Repeat Section Begin]::",
						"//!!//!![Repeat Section End]::");
				if (fragment.length() > 0) {
					fragmentsMap.put(fragment.toString(), PLAIN);
				}
				fragment = new StringBuilder(line = lines[count]).append("\n");
				count++;

				while (count < lines.length) {
					line = lines[count].trim();
					fragment.append(lines[count]).append("\n");
					if (line.equals(endline)) {
						break;
					}
					count++;
				}
				if (fragment.length() > 0) {
					fragmentsMap.put(fragment.toString(), REPEAT);
				}
				fragment = new StringBuilder();
			} else {
				fragment.append(lines[count]).append("\n");
			}
			count++;
		}
		if (fragment.length() > 0) {
			fragmentsMap.put(fragment.toString(), PLAIN);
		}
		return fragmentsMap;
	}

	private Map<String, String> getRepeatSections(String pattern) {
		Map<String, String> repeatSectionMap = new LinkedHashMap<String, String>();
		String lines[] = pattern.split("\n");
		for (int count = 0; count < lines.length; count++) {
			String line = lines[count].trim();
			if (line.startsWith("//!!//!![Repeat Section Begin]::")) {
				String mark = line.substring("//!!//!![Repeat Section Begin]::"
						.length());
				StringBuilder repeatSection = new StringBuilder();
				int sectionIndex = count + 1;
				String endline = line.replace(
						"//!!//!![Repeat Section Begin]::",
						"//!!//!![Repeat Section End]::");
				while (sectionIndex < lines.length) {
					if (lines[sectionIndex].trim().equals(endline)) {
						break;
					}
					repeatSection.append(lines[sectionIndex]).append("\n");
					sectionIndex++;
				}
				if (repeatSection.length() > 0) {
					repeatSectionMap.put(mark, repeatSection.toString());
				}
			}
		}
		return repeatSectionMap;
	}
}
