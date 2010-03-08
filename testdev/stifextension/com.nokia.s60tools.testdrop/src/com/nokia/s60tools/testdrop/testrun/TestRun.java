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
package com.nokia.s60tools.testdrop.testrun;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.EpocEngineHelper;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.sdk.core.ISDKManager;
import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.carbide.cpp.sdk.core.SDKCorePlugin;
import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.util.TestModuleTypeRecognizer;

/**
 * A class representing a test run (emulator). Contain all information needed
 * to perform an atsinterface test
 *
 */
public class TestRun {
	
	private ModuleType moduleType;
	private IProject selectedProject;
	private IFile[] selectedCfgFiles;
	private String devicePath;
	private String[] scriptFilesPaths;
	
	private static final String RELATIVE_EPOC_INI_DIR_PATH = "\\epoc32\\data";
	private static final String TEST_FRAMEWORK_DIR_PATH = "C:\\Testframework\\";
	private static final String RELATIVE_INI_ROOT_PATH = "\\epoc32\\winscw\\c\\TestFramework";
	private static final String CONFIG_DIRECTORY_NAME = "\\conf";
	private static final String BUILD_VARIANT_PLACEHOLDER = "[build_variant]";
	private static final String UDEB_BUILD_PATH_PART = "udeb";
	private static final String UREL_BUILD_PATH_PART = "urel";
	private static final String RELATIVE_PATH_TO_ATS_INTERFACE_TEMPLATE = "\\epoc32\\release\\winscw\\[build_variant]\\atsinterface.exe";
	
	private String relativeTestModulesPath; // = "\\epoc32\\release\\winscw\\urel\\";
	private String relativePathToAtsinterface;
	
	/**
	 * A constructor for constructing a test run of hardcoded, normal, testclass or STIFUnit module type
	 * 
	 * @param selectedProject
	 * 			selected project of any type of hardcoded, normal, testclass or STIFUnit
	 * @param deviceName
	 * 			name of device for which the selected test module is prepared
	 */
	public TestRun(IProject selectedProject, String deviceName) {
		this.selectedProject = selectedProject;
		selectedCfgFiles = null;
		
		ISDKManager manager = SDKCorePlugin.getSDKManager();
		List<ISymbianSDK> sdkList = manager.getSDKList();
		Iterator<ISymbianSDK> iterator = sdkList.iterator();
		while(iterator.hasNext()){
			ISymbianSDK sdk = iterator.next();
			if(sdk.getUniqueId().equals(deviceName)) {
				devicePath = sdk.getEPOCROOT();
				break;
			}
		}
		
		moduleType = TestModuleTypeRecognizer.recognizeModuleType(selectedProject);
		if (moduleType == ModuleType.TESTCLASS || moduleType == ModuleType.NORMAL) {
			findScriptFilesPaths();
		}
		
		setPaths(selectedProject);
	}

	/**
	 * A constructor for constructing a test run of test combiner type.
	 * Method takes an array of files. In fact currently only one cfg file at one
	 * time is supported. As a result only the [0] element will be used
	 * 
	 * @param selectedCfgFiles
	 * 			selected cfg files that should be run by test combiner
	 * @param deviceName
	 * 			name of device for which a test run is being prepared
	 */
	public TestRun(IFile[] selectedCfgFiles, String deviceName, boolean debugVariantActive) {
		this.selectedCfgFiles = selectedCfgFiles;
		selectedProject = null;
		
		ISDKManager manager = SDKCorePlugin.getSDKManager();
		List<ISymbianSDK> sdkList = manager.getSDKList();
		Iterator<ISymbianSDK> iterator = sdkList.iterator();
		while(iterator.hasNext()){
			ISymbianSDK sdk = iterator.next();
			if(sdk.getUniqueId().equals(deviceName)) {
				devicePath = sdk.getEPOCROOT();
				break;
			}
		}
		
		moduleType = ModuleType.TESTCOMBINER;
		
		setPathsForTestCombiner(debugVariantActive);
	}


	/**
	 * Paths to the test module and to atsinterface.exe are set depending on location
	 * of the selected project
	 * 
	 * @param selectedProject
	 * 		the project that was selected or a project that contains selected cfg file
	 */
	private void setPaths(IProject selectedProject) {
		ICarbideProjectInfo carbideProjectInfo = CarbideBuilderPlugin.getBuildManager()
				.getProjectInfo(selectedProject);
		Iterator<IPath> mmpIterator = EpocEngineHelper.getMMPFilesForProject(carbideProjectInfo)
				.iterator();
		IPath binaryPath;
		while (mmpIterator.hasNext()) {
			IPath mmpPath = mmpIterator.next();
			binaryPath = EpocEngineHelper.getHostPathForExecutable(carbideProjectInfo
					.getDefaultConfiguration(), mmpPath);
			if (binaryPath.toFile().exists()) {
				relativeTestModulesPath = binaryPath.toOSString();
				if (relativeTestModulesPath.toLowerCase().indexOf(UDEB_BUILD_PATH_PART) > -1) {
					relativePathToAtsinterface = RELATIVE_PATH_TO_ATS_INTERFACE_TEMPLATE
						.replace(BUILD_VARIANT_PLACEHOLDER, UDEB_BUILD_PATH_PART);
				}
				else if (relativeTestModulesPath.toLowerCase().indexOf(UREL_BUILD_PATH_PART) > -1) {
					relativePathToAtsinterface = RELATIVE_PATH_TO_ATS_INTERFACE_TEMPLATE
						.replace(BUILD_VARIANT_PLACEHOLDER, UREL_BUILD_PATH_PART);
				}
				break;
			}
		}
	}
	
	/**
	 * Paths to the test module and to atsinterface.exe are set depending on the selected
	 * variant
	 * 
	 * @param debugVariantActive
	 * 		says if debug or release version of modules should be used
	 */
	private void setPathsForTestCombiner(boolean debugVariantActive) {
		if (debugVariantActive) {
			relativePathToAtsinterface = RELATIVE_PATH_TO_ATS_INTERFACE_TEMPLATE
				.replace(BUILD_VARIANT_PLACEHOLDER, UDEB_BUILD_PATH_PART);
		}
		else {
			relativePathToAtsinterface = RELATIVE_PATH_TO_ATS_INTERFACE_TEMPLATE
				.replace(BUILD_VARIANT_PLACEHOLDER, UREL_BUILD_PATH_PART);
		}
	}
	
	
	/**
	 * @return
	 * 		a path to atsinterface.exe
	 */
	public String getATSInterfaceFilePath() {
		return devicePath + relativePathToAtsinterface;
	}
	
	/**
	 * @return
	 * 		a path to test module file
	 */
	public String getTestModuleFilePath() {
		if (selectedProject == null) {
			return null;
		}
		return relativeTestModulesPath; 
	}
	
	/**
	 * @return
	 * 		a path to epoc.ini file (to add "textshell" value)
	 */
	public String getEpocIniRootPath() {
		return devicePath + RELATIVE_EPOC_INI_DIR_PATH;
	}
	
	/**
	 * @return
	 * 		path to device for which the test run is prepared
	 */
	public String getDevicePath() {
		return devicePath;
	}
	
	/**
	 * @return
	 * 		type of the test module that will be run by this test run
	 */
	public ModuleType getModuleType() {
		return moduleType;
	}
	
	/**
	 * @return
	 * 		name of the module
	 */
	public String getModuleName() {
		return selectedProject.getName();
	}
	
	/**
	 * @return
	 * 		array of paths to script files
	 */
	public String[] getScriptFilesPaths() {
		return scriptFilesPaths;
	}
	
	/**
	 * @return
	 * 		path to TestFramework.ini file
	 */
	public String getIniRootPath() {
		return devicePath + RELATIVE_INI_ROOT_PATH;
	}
	
	/**
	 * If module is testclass or normal it can take a script file,.
	 * Method explores "conf" directory of the test module and takes 
	 * all .cfg files
	 */
	private void findScriptFilesPaths() {
		IPath projectPath = selectedProject.getLocation();
		File confDir = new File(projectPath.toString() + CONFIG_DIRECTORY_NAME);
		File[] cfgFiles = null;
		if (moduleType == ModuleType.TESTCLASS) {
			cfgFiles = confDir.listFiles(new FilenameFilter(){
				public boolean accept(File directory, String filename) {
					if (filename.endsWith(".cfg")){
						return true;
					}
					return false;
				}
			});
		}
		else if (moduleType == ModuleType.NORMAL) {
			cfgFiles = confDir.listFiles(new FilenameFilter(){
				public boolean accept(File directory, String filename) {
					if (filename.equals("Distribution.Policy.S60")){
						return false;
					}
					return true;
				}
			});
		}
		scriptFilesPaths = new String[cfgFiles.length];
		for (int i = 0; i < cfgFiles.length; i++) {
			scriptFilesPaths[i] = TEST_FRAMEWORK_DIR_PATH + cfgFiles[i].getName();
		}
	}
	
	/**
	 * @return
	 * 		path to project directory
	 */
	public String getProjectPath() {
		if (selectedProject == null) {
			return null;
		}
		return selectedProject.getLocation().toString();
	}
	
	/**
	 * @return
	 * 		an array of cfg files for test combiner as IFiles
	 */
	public IFile[] getTestCombinerScriptFilesAsIFiles() {
		return selectedCfgFiles;
	}
	
	/**
	 * @return
	 * 		an array of cfg files for test combiner as Strings
	 */
	public String[] getTestCombinerScriptFiles() {
		String[] scriptFiles = new String[selectedCfgFiles.length];
		for (int i = 0; i < selectedCfgFiles.length; i++) {
			scriptFiles[i] = TEST_FRAMEWORK_DIR_PATH + selectedCfgFiles[i].getName();
		}
		return scriptFiles;
	}
}
