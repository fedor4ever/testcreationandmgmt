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


package com.symbian.tdep.templates.carbide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class Utility {

	// All bld.inf tags
	private final static Set<String> BLD_KEY_SET = new HashSet<String>();
	static {
		BLD_KEY_SET.add("PRJ_EXPORTS");
		BLD_KEY_SET.add("PRJ_EXTENSIONS");
		BLD_KEY_SET.add("PRJ_MMPFILES");
		BLD_KEY_SET.add("PRJ_PLATFORMS");
		BLD_KEY_SET.add("PRJ_TESTEXPORTS");
		BLD_KEY_SET.add("PRJ_TESTEXTENSIONS");
		BLD_KEY_SET.add("PRJ_TESTMMPFILES");
	}

	private final static String BLD_PRJ_MMPFILES = "PRJ_MMPFILES";
	private final static String BLD_PRJ_TESTMMPFILES = "PRJ_TESTMMPFILES";

	public final static String MMP_USERINCLUDE = "USERINCLUDE";
	public final static String MMP_SYSTEMINCLUDE = "SYSTEMINCLUDE";
	public final static String MMP_LIBRARY = "LIBRARY";

	// get mmp files in bld.inf
	public static List<IPath> getMMPFiles(IPath bldFile) {
		List<IPath> files = new ArrayList<IPath>();
		try {
			// Open bld.inf
			LineNumberReader reader = new LineNumberReader(new FileReader(
					bldFile.toFile()));

			String line;
			boolean findMMPPending = false;
			while ((line = reader.readLine()) != null) {
				String key = line.trim().toUpperCase().replaceAll("\\s", " ");
				if (key.startsWith("#INCLUDE ")) {
					String[] split = key.split("\\s");
					if (split.length > 1) {
						String path = split[1].replaceAll("\"", "");
						IPath subFile = bldFile.removeLastSegments(1).append(
								path);
						if (subFile.toFile().exists()) {
							files.addAll(getMMPFiles(subFile));
						}
					}
				} else {
					if (BLD_KEY_SET.contains(key)) {
						if (key.equals(BLD_PRJ_MMPFILES)) {
							findMMPPending = true;
						} else {
							findMMPPending = false;
						}
					} else {
						if (findMMPPending) {
							String[] fragment = line.trim().split("\\s+");
							if (fragment[0].toLowerCase().endsWith(".mmp")) {
								IPath mmpFile = bldFile.removeLastSegments(1)
										.append(fragment[0]);
								files.add(mmpFile);
							}
						}
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					Utility.class.getName(), IStatus.ERROR,
					"Exception while reading bld.inf.", e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		return files;
	}

	// Add new mmp to bld.inf
	public static boolean addNewTestMMP(File bldFile, String mmpName) {
		ByteArrayOutputStream buff = new ByteArrayOutputStream((int) (bldFile
				.length() + 100));
		PrintWriter writer = new PrintWriter(buff);

		try {
			LineNumberReader reader = new LineNumberReader(new FileReader(
					bldFile));
			String line;
			boolean findTestMMP = false;
			boolean findTestMMPPending = false;
			while ((line = reader.readLine()) != null) {

				String key = line.trim().toUpperCase();
				if (BLD_KEY_SET.contains(key)) {
					writer.println(line);
					if (key.equals(BLD_PRJ_TESTMMPFILES)) {
						findTestMMP = true;
						findTestMMPPending = true;
						writer.println(mmpName);
					} else {
						findTestMMPPending = false;
					}
				} else {
					if (findTestMMPPending) {
						if (!line.trim().equals(mmpName)) {// Filter mmpName
							writer.println(line);
						}
					} else {// Excepting PRJ_TESTMMPFILES
						writer.println(line);
					}
				}
			}
			reader.close();

			// PRJ_TESTMMPFILES don't exist
			if (findTestMMP == false) {
				writer.println();
				writer.println(BLD_PRJ_TESTMMPFILES);
				writer.println(mmpName);
			}
			writer.close();

			buff.writeTo(new FileOutputStream(bldFile));
			buff.close();
			return true;
		} catch (IOException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					Utility.class.getName(), IStatus.ERROR,
					"Exception while reading bld.inf.", e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		return false;
	}

	// Get "user include"/"system include"/"library" information from MMP
	public static Map<String, Set<String>> catchIncludeFromMMP(IPath mmpFile,
			IProject project) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Set<String> userSet = new HashSet<String>();
		Set<String> systemSet = new HashSet<String>();
		Set<String> librarySet = new HashSet<String>();
		try {
			LineNumberReader reader = new LineNumberReader(new FileReader(
					mmpFile.toFile()));
			IPath lBasePath = mmpFile.removeLastSegments(1);
			IPath lBaseRelativePath = lBasePath.removeFirstSegments(
					lBasePath.matchingFirstSegments(project.getLocation()))
					.setDevice(null);

			String line;
			Set<String> lSubSet;
			while ((line = reader.readLine()) != null) {
				String[] fragment = line.trim().split("\\s+");
				if (fragment.length > 1) {
					if (fragment[0].equals(MMP_USERINCLUDE)) {
						lSubSet = userSet;
					} else if (fragment[0].equals(MMP_SYSTEMINCLUDE)) {
						lSubSet = systemSet;
					} else if (fragment[0].equals("LIBRARY")) {
						lSubSet = librarySet;
					} else {
						continue;
					}
					for (int i = 1; i < fragment.length; i++) {
						if (fragment[0].equals(MMP_USERINCLUDE)) {
							IPath tmpPath = lBaseRelativePath
									.append(fragment[i]);
							if (tmpPath.isEmpty()) {
								fragment[i] = ".";
							} else {
								fragment[i] = tmpPath.toString();
							}
						}
						lSubSet.add(fragment[i]);
					}
				}
			}
			map.put(MMP_USERINCLUDE, userSet);
			map.put(MMP_SYSTEMINCLUDE, systemSet);
			map.put(MMP_LIBRARY, librarySet);
		} catch (IOException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					Utility.class.getName(), IStatus.ERROR,
					"Exception while reading " + mmpFile.toOSString(), e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}
		return map;
	}
}

// Find file in file tree
class FindFileVisitor implements IResourceVisitor {

	private String iTargetFile;
	private Set<IResource> result = new HashSet<IResource>();

	public FindFileVisitor(String filename) {
		iTargetFile = filename;
	}

	public boolean visit(IResource res) {
		if (res instanceof IProject || res instanceof IFolder) {
			return true;
		} else {
			if (iTargetFile.equals(res.getName())) {
				result.add(res);
			}
			return false;
		}
	}

	public Set<IResource> getResult() {
		return result;
	}
}
