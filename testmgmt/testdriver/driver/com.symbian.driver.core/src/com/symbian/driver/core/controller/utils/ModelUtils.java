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



package com.symbian.driver.core.controller.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.symbian.driver.Task;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.core.environment.TDConfig;

public class ModelUtils {
	
	private static Map<String, Set<Task>> sEclipsingStack = new HashMap<String, Set<Task>>();

	/** Logger. */
	private static final Logger LOGGER = Logger.getLogger(ExecuteTransferSet.class.getName());
	/** tcs extension */
	private static final String iTCSenxtension = ".tcs";

	/**
	 * clear the eclipsing stack
	 * 
	 * @param none
	 * @return void
	 */
	public static void resetStack() {
		sEclipsingStack.clear();
	}
	
	/**
	 * Checks if the file is eclipsing a file in one of the parent nodes.
	 * 
	 * @param aFile
	 * @param aTaskNode
	 * @return The Task node where the file is eclipsing with.
	 */
	public static Task isFileEclipsing(String aFile, Task aTaskNode) {
		if (aFile == null || aFile.length() < 1 || aTaskNode == null) {
			LOGGER.warning("File eclipsing check received invalid file/task name.");
			return null;
		}
	
		String lFile = aFile.toLowerCase();
	
		if (sEclipsingStack.containsKey(lFile.toLowerCase())) {
			Set<Task> lNode = sEclipsingStack.get(lFile);
			if (lNode != null && !lNode.isEmpty()) {
	
				for (Task lNextObject : lNode) {
					if (EcoreUtil.isAncestor(lNextObject, aTaskNode)) {
						return lNextObject;
					}
				}
	
				addNode(lFile.toLowerCase(), aTaskNode, lNode);
				return null;
			}
		}
	
		addNode(lFile.toLowerCase(), aTaskNode, new HashSet<Task>());
		return null;
	}

	/**
	 * @param aFile
	 * @param aTaskNode
	 * @param lNodeSet
	 */
	private static void addNode(String aFile, Task aTaskNode, Set<Task> lNodeSet) {
		if (lNodeSet.add(aTaskNode)) {
			sEclipsingStack.put(aFile, lNodeSet);
		} else {
			LOGGER.warning("Could not check file for eclipsing: " + aFile + ", to " + aTaskNode);
		}
	}

	/**
	 * Gets the result/repository path.
	 * 
	 * Example: 03258_Symbian_OSv9/armv6/udeb/5/
	 * 
	 * @param aTask
	 *            The current task being visited.
	 * @param aRunNumber
	 *            The run number to use in the base directory. If the runNumber
	 *            is less than 0 than this won't be used.
	 * @return The directory structure consisting of
	 *         <code>/${buildNumber}/${platform}/${variant}/${task}1/.../${task}n/</code>
	 * @throws ParseException 
	 */
	public static final String getBaseDirectory(final int aRunNumber) throws ParseException {
		return File.separator + TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER)
				+ File.separator + TDConfig.getInstance().getPreference(TDConfig.PLATFORM)
				+ File.separator + TDConfig.getInstance().getPreference(TDConfig.VARIANT)
				+ File.separator + (aRunNumber < 0 ? "" : (aRunNumber + File.separator));
	}

	/**
	 * Gets the result/repository path.
	 * 
	 * Example: 03258_Symbian_OSv9/armv6/udeb/5/A/B/C/
	 * 
	 * @param aTask
	 *            The current task being visited.
	 * @param aRunNumber
	 *            The run number to use in the base directory. If the runNumber
	 *            is less than 0 than this won't be used.
	 * @return The directory structure consisting of
	 *         <code>/${buildNumber}/${platform}/${variant}/${task}1/.../${task}n/</code>
	 * @throws ParseException 
	 */
	public static final String getBaseDirectory(final Task aTask, final int aRunNumber) throws ParseException {
		return getBaseDirectory(aRunNumber)
			+ ( aTask == null ? "" : aTask.getAddress())
			+ File.separator;
	}

	/**
	 * The EPOC release directory.
	 * 
	 * Example: epoc32/RELEASE/WINSCW/udeb/
	 * 
	 * @return The directory structure consisting of
	 *         <code>/epoc32/RELEASE/${platform}/${variant}/</code>
	 * @throws ParseException If it could not get the platform or variant form the configuration.
	 */
	public static final String getEpoc32RelPlatVar() throws ParseException {
		return com.symbian.driver.core.environment.ILiterals.EPOC32 + File.separator
			+ com.symbian.driver.core.environment.ILiterals.RELEASE + File.separator
			+ TDConfig.getInstance().getPreference(TDConfig.PLATFORM) + File.separator
			+ TDConfig.getInstance().getPreference(TDConfig.VARIANT) + File.separator;
	}

	/**
	 * Subsitutes the variables, ${platform}, ${build}, ${epocroot}, ${repositoryroot}, ${resultroot} and
	 * ${sourceroot} in a path.
	 * 
	 * @param aPath
	 *            The path to check and subsitute the variables.
	 * @return The path with the subistituted variables for the actual values.
	 * @throws ParseException 
	 */
	public static final String subsituteVariables(String aPath) {
		return subsituteVariables(aPath, null);
	}

	/**
	 * Subsitutes the variables, ${platform}, ${build}, ${epocroot}, ${repositoryroot}, ${resultroot} and
	 * ${sourceroot} in a path.
	 * 
	 * @param aPath
	 *            The path to check and subsitute the variables.
	 * @param aTask 
	 * @return The path with the subistituted variables for the actual values.
	 * @throws ParseException 
	 */
	public static final String subsituteVariables(String aPath, Task aTask) {
		if (aPath != null) {
			try {								
				aPath = aPath.replaceAll("\\$\\{platform\\}", TDConfig.getInstance().getPreference(TDConfig.PLATFORM));
				aPath = aPath.replaceAll("\\$\\{build\\}", TDConfig.getInstance().getPreference(TDConfig.VARIANT));
				aPath = aPath.replaceAll("\\$\\{epocroot\\}", (TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT).toString() + File.separator).replaceAll("\\\\", "\\\\\\\\"));
				aPath = aPath.replaceAll("\\$\\{sourceroot\\}", (TDConfig.getInstance().getPreferenceFile(TDConfig.SOURCE_ROOT).toString() + File.separator).replaceAll("\\\\", "\\\\\\\\"));
				aPath = aPath.replaceAll("\\$\\{xmlroot\\}", (TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT).toString() + File.separator).replaceAll("\\\\", "\\\\\\\\"));
				aPath = aPath.replaceAll("\\$\\{repositoryroot\\}", (TDConfig.getInstance().getPreferenceFile(TDConfig.REPOSITORY_ROOT).toString() + File.separator).replaceAll("\\\\", "\\\\\\\\"));
				
				if (aTask == null) {
					aPath = aPath.replaceAll("\\$\\{resultroot\\}", (TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT).toString() + File.separator).replaceAll("\\\\", "\\\\\\\\"));
				} else {
					File lPCResultPath = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT),
							getBaseDirectory(aTask,
							TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER)) + File.separator);
					
					aPath = aPath.replaceAll("\\$\\{resultroot\\}", lPCResultPath.toString().replaceAll("\\\\", "\\\\\\\\"));
				}
				
			} catch (ParseException lParseException) {
				ControllerUtils.LOGGER.log(Level.WARNING, "Could not get the configuration while subsituting variables in a path.", lParseException);
			}
		}
		aPath = aPath.replaceAll("\\\\+", "\\\\");
		return aPath;
	}
	
	/**
	 * Subsitutes a normal path for a path with TestDriver Variables.
	 * 
	 * @param aPath
	 * @return
	 */
	public static final String substitutePaths(String aPath) {
		if (aPath != null) {
			try {
				TDConfig CONFIG = TDConfig.getInstance();
				aPath = aPath.replaceAll(CONFIG.getPreference(TDConfig.PLATFORM), "\\$\\{platform\\}");
				aPath = aPath.replaceAll(CONFIG.getPreference(TDConfig.VARIANT), "\\$\\{build\\}");
				aPath = aPath.replaceAll(CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{epocroot\\}");
				aPath = aPath.replaceAll(CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{sourceroot\\}");
				aPath = aPath.replaceAll(CONFIG.getPreferenceFile(TDConfig.XML_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{xmlroot\\}");
				aPath = aPath.replaceAll(CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{repositoryroot\\}");
				aPath = aPath.replaceAll(CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{resultroot\\}");
				
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreference(TDConfig.PLATFORM), "\\$\\{platform\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreference(TDConfig.VARIANT), "\\$\\{build\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{epocroot\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreferenceFile(TDConfig.SOURCE_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{sourceroot\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{xmlroot\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreferenceFile(TDConfig.REPOSITORY_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{repositoryroot\\}");
				aPath = aPath.replaceAll(TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT).toString().replaceAll("\\\\", "\\\\\\\\"), "\\$\\{resultroot\\}");
				
			} catch (ParseException lParseException) {
				ControllerUtils.LOGGER.log(Level.WARNING, "Could not get the configuration while subsituting variables in a path.", lParseException);
			}
			
		}
		
		return aPath;
	}

	/**
	 * Utility class that checks any PCPath, and subsitutes variables and
	 * wildcards as appropriate.
	 * 
	 * Please make certain that any corresponding Symbian Path is also changed
	 * when using wildcards.
	 * 
	 * @param aPCPath
	 *            The orginal IBM compatible PC Path.
	 * @param aWildcardSupported
	 *            <code>true</code> if wildcards should be supported,
	 *            <code>false</code> otherwise.
	 * @return The validated absaloute file paths, if
	 *         <code>aWildcardVariable</code> is <code>false</code>, or set
	 *         of files if <code>aWildcardVariable</code> is
	 *         <code>false</code>.
	 * 
	 * @throws IOException
	 *             If there was an error accessing the files or directories.
	 */
	public static final File[] checkPCPath(final String aPCPath, final boolean aWildcardSupported) throws IOException {
		String lPCPathLiteral = subsituteVariables(aPCPath);
		File lPCPathFile = new File(lPCPathLiteral);
		if (aWildcardSupported && lPCPathLiteral.indexOf("*") >= 0) {
			File lParentDir = lPCPathFile.getParentFile();
			// Setup the regular expression
			String lRegExpName = lPCPathFile.getName().replaceAll("\\.", "\\\\.").replaceAll("\\*", ".\\*");
			
			lRegExpName = "^" + lRegExpName + "$";
			
			final Pattern lFileNamePattern = Pattern.compile(lRegExpName);
			
			return lParentDir.listFiles(new FileFilter() {
	
				public boolean accept(File aPathname) {
					
					Matcher lMatcher = lFileNamePattern.matcher(aPathname.getName());
					if (lMatcher.find()) {
						return true;
					}
					return false;
				}
				
			});
		}
			
		// Regular file support
		if (lPCPathFile == null || !lPCPathFile.exists()) {
			ControllerUtils.LOGGER.warning("Cannot find file: " + lPCPathLiteral +  ". Please use absolute paths, using the variables: ${epocroot}, ${sourceroot}, ${xmlroot}, ${build} and ${platform}.");
			TDConfig CONFIG = TDConfig.getInstance();
			try {
				// Source Root support
				lPCPathFile = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.SOURCE_ROOT) + File.separator + lPCPathLiteral);
				if (lPCPathFile == null || !lPCPathFile.exists()) {
					
					// XML Root support
					lPCPathFile = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT) + File.separator + lPCPathLiteral);
					if (lPCPathFile == null || !lPCPathFile.exists()) {
						
						// EPOC Root support
						lPCPathFile = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT) + File.separator + lPCPathLiteral);
						if (lPCPathFile == null || !lPCPathFile.exists()) {
							
							// Can't find file
							throw new IOException("The PC path is incorrect: " + lPCPathLiteral);
							
						}
					}
				}
			} catch (ParseException lParseException) {
				ControllerUtils.LOGGER.log(Level.WARNING, lParseException.getMessage(), lParseException);
			} catch (IOException lIOException){
				ControllerUtils.LOGGER.log(Level.WARNING,lIOException.getMessage(),lIOException);
			}
		}
		
		// Return the one correct file
		return new File[] { lPCPathFile };
	}

	public static boolean isTestCasesFile(String aTarget) {
		if (aTarget.toLowerCase().endsWith(iTCSenxtension)) {
			return true;
		}
		return false;
	}
	/**
	 * Utility method to check if the test cases file exist. 
	 * when path is relative it's relative to the location of aTestScript
	 * @param aTarget the test cases file
	 * @param aTestScript the script file
	 * @return the canonical form of the path
	 * @throws IOException
	 */
	public static File checkTCFile(String aTarget, File aTestScript) throws IOException {
		aTarget = subsituteVariables(aTarget);
		File lTargetFile = new File(aTarget);
		if (!lTargetFile.isFile()) {
			LOGGER.warning("Cannot find Test Cases file: " + lTargetFile +  ". Please use absolute paths, using the variables: ${epocroot}, ${sourceroot}, ${xmlroot}, ${build} and ${platform}.");
			throw new IOException("The file \"" + aTarget + "\" does not exist.");
		}
		
		return lTargetFile;
	}
}
