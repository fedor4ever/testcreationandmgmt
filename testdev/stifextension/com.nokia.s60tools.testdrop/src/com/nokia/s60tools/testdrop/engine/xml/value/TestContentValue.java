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


package com.nokia.s60tools.testdrop.engine.xml.value;

import java.util.List;

import org.eclipse.core.runtime.IPath;

import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.resources.Messages;

/**
 * Contains all data for creating a test drop
 * 
 */
public class TestContentValue {

	private final String projectName;
	private final String[] filesToIncludeNames;
	private final List<TargetDeviceValue> targets;
	private final String testBinaryVariant;
	private final IPath testBinarySrcPath;
	private final IPath testScriptSrcPath;
	private final String harness;
	private final ModuleType testType;
	private final String componentPath;
	private final String scriptPathInsideDrop;
	private final TestResultPropertyValue resultProperty;

	/**
	 * Constructs class with mandatory parameter
	 * 
	 * @param projectName
	 *            project name value
	 * @param targets
	 *            contain list of targets
	 * @param testBinaryVariant
	 *            test binary variant e.g. ARMV5_UREL
	 * @param testBinarySrcPath
	 *            test binary path and file name
	 * @param testScriptSrcPath
	 *            test script path and file name
	 * @param componentPath
	 *            component path
	 * @param scriptPathInsideDrop
	 *            test script path inside test drop
	 * @param harness
	 *            test harness type
	 * @param testType
	 *            test type
	 * @param resultProperty
	 *            test results properties
	 */
	public TestContentValue(String projectName, String cfgFileName,
			String[] filesToIncludeNames,
			List<TargetDeviceValue> targets, String testBinaryVariant,
			IPath testBinarySrcPath, IPath testScriptSrcPath,
			String componentPath, String scriptPathInsideDrop, String harness,
			ModuleType testType, TestResultPropertyValue resultProperty)
			throws IllegalArgumentException {
		this.projectName = projectName;
		this.targets = targets;
		this.testBinaryVariant = testBinaryVariant;
		this.testBinarySrcPath = testBinarySrcPath;
		this.testScriptSrcPath = testScriptSrcPath;
		this.componentPath = componentPath;
		this.scriptPathInsideDrop = scriptPathInsideDrop;
		this.harness = harness;
		this.testType = testType;
		this.resultProperty = resultProperty;
		this.filesToIncludeNames = filesToIncludeNames;
		if (testType == ModuleType.TESTCLASS) {
			checkTestClassValidity();
		}
		else if (testType == ModuleType.HARDCODED) {
			checkHardcodedValidity();
		}

	}

	/**
	 * Check constructor's arguments
	 * 
	 * @throws IllegalArgumentException,
	 *             if arguments were either null or empty
	 */
	private void checkTestClassValidity() throws IllegalArgumentException {
		if (projectName == null || targets == null || testBinaryVariant == null
				|| testBinarySrcPath == null || testScriptSrcPath == null
				|| componentPath == null || scriptPathInsideDrop == null
				|| harness == null || testType == null) {
			throw new IllegalArgumentException(Messages
					.getString("TestContentValue.argumetsCannotBeNull")); 
		}
		// Test type can be empty
		if (projectName.length() == 0 || targets.isEmpty()
				|| testBinaryVariant.length() == 0
				|| testBinarySrcPath.isEmpty() || testScriptSrcPath.isEmpty()
				|| componentPath.length() == 0
				|| scriptPathInsideDrop.length() == 0 || harness.length() == 0) {
			throw new IllegalArgumentException(Messages
					.getString("TestContentValue.argumetsCannotBeEmpty")); 
		}
	}
	
	/**
	 * Checks if hardcoded test module is valid
	 * 
	 * @throws IllegalArgumentException
	 * 				if some elements are missing
	 */
	private void checkHardcodedValidity() throws IllegalArgumentException {
		if (projectName == null || targets == null || testBinaryVariant == null
				|| testBinarySrcPath == null
				|| componentPath == null
				|| harness == null || testType == null) {
			throw new IllegalArgumentException(Messages
					.getString("TestContentValue.argumetsCannotBeNull")); 
		}
		// Test type can be empty
		if (projectName.length() == 0 || targets.isEmpty()
				|| testBinaryVariant.length() == 0
				|| testBinarySrcPath.isEmpty()
				|| componentPath.length() == 0
				|| scriptPathInsideDrop.length() == 0 || harness.length() == 0) {
			throw new IllegalArgumentException(Messages
					.getString("TestContentValue.argumetsCannotBeEmpty")); 
		}
	}

	/**
	 * Returns project name
	 * 
	 * @return project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Returns target devices list
	 * 
	 * @return target device list
	 */
	public List<TargetDeviceValue> getTargets() {
		return targets;
	}

	/**
	 * Returns test binary variant value
	 * 
	 * @return test binary variant value
	 */
	public String getTestBinaryVariant() {
		return testBinaryVariant;
	}

	/**
	 * Returns test binary source path
	 * 
	 * @return test binary source path
	 */
	public IPath getTestBinarySrcPath() {
		return testBinarySrcPath;
	}

	/**
	 * Returns test script source path
	 * 
	 * @return test script source path
	 */
	public IPath getTestScriptSrcPath() {
		return testScriptSrcPath;
	}

	/**
	 * Returns test harness value
	 * 
	 * @return test harness value
	 */
	public String getHarness() {
		return harness;
	}

	/**
	 * Returns test type
	 * 
	 * @return test type
	 */
	public ModuleType getTestType() {
		return testType;
	}

	/**
	 * Returns component path
	 * 
	 * @return component path
	 */
	public String getComponentPath() {
		return componentPath;
	}

	/**
	 * Returns test script path inside test drop
	 * 
	 * @return test script path inside test drop
	 */
	public String getScriptPathInsideDrop() {
		return scriptPathInsideDrop;
	}

	/**
	 * Returns test result property value
	 * 
	 * @return test result property value
	 */
	public TestResultPropertyValue getTestResultPropertyValue() {
		return resultProperty;
	}
	
	/**
	 * Returns names of files that need to be included
	 * 
	 * @return array of file names
	 */
	public String[] getFilesToIncludeNames() {
		return filesToIncludeNames;
	}
}
