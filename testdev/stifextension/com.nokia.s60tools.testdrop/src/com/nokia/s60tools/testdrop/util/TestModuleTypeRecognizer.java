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
package com.nokia.s60tools.testdrop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import org.eclipse.core.resources.IProject;

import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob.ModuleType;
import com.nokia.s60tools.testdrop.resources.Messages;


/**
 *
 * Class intended for distinguishing between various module types
 */
public class TestModuleTypeRecognizer {
	
	private static final String groupDirectoryName = "\\group\\";
	private static final String sourceDirectoryName = "\\src\\";
	private static final String kcasesArrayDeclaration = "const KCases[]";
	private static final String unitTestDefineTag = "STIF_TESTDEFINE";
	
	/**
	 * The method which recognizes module type of the given project
	 * 
	 * @param project
	 * 			selected project
	 * @return
	 * 			type of module
	 */
	public static ModuleType recognizeModuleType(IProject project) {
		String path = project.getLocation().toString() + groupDirectoryName;
		File groupDir = new File(path);
		File[] mmpFiles = groupDir.listFiles(new FilenameFilter(){
			public boolean accept(File directory, String filename) {
				if (filename.endsWith(".mmp")){
					return true;
				}
				return false;
			}
		});
		
		ModuleType moduleType = null;
		try {
			if (mmpFiles.length > 0){
				File mmpFile = mmpFiles[0];
				FileReader mmpReader = new FileReader(mmpFile);
				BufferedReader mmpBufReader = new BufferedReader(mmpReader);
				String line = "";
				while((line = mmpBufReader.readLine()) != null){
					if(line.indexOf("UID") > -1){
						int indexOfUID = line.lastIndexOf("0x");
						String uid = line.substring(indexOfUID);
						if(uid.equals("0x101FB3E7")){
							moduleType = distinguishNormalHardcodedSTIFUnitModule(project);
						}
						if(uid.equals("0x101FB3E3")){
							moduleType = ModuleType.TESTCLASS;
						}
						break;
					}
				}
				mmpBufReader.close();
			}
		}
		catch (IOException ex) {
			LogExceptionHandler
			.showNotifyDialog(Messages
					.getString("TestModuleTypeRecognizer.cannotFindMmpFile"));
		}
		return moduleType;
	}
	
	/**
	 * Helper method for distinguishing between normal, hardcoded and STIFUnit modules
	 * 
	 * @param project
	 * 			project to distinguish
	 * @return
	 * 			type of module
	 */
	private static ModuleType distinguishNormalHardcodedSTIFUnitModule(IProject project) {
		String path = project.getLocation().toString() + sourceDirectoryName;
		File groupDir = new File(path);
		File[] cppFiles = groupDir.listFiles(new FilenameFilter(){
			public boolean accept(File directory, String filename) {
				if(filename.endsWith(".cpp")){
					return true;
				}
				return false;
			}
		});
		
		try {
			for (int i = 0; i < cppFiles.length; i++) {
				File cppFile = cppFiles[i];
				FileReader cppReader = new FileReader(cppFile);
				BufferedReader cppBufReader = new BufferedReader(cppReader);
				String line = "";
				while ((line = cppBufReader.readLine()) != null){
					if (line.indexOf(kcasesArrayDeclaration) > -1){
						cppBufReader.close();
						return ModuleType.HARDCODED;
					}
					if (line.indexOf(unitTestDefineTag) > -1){
						cppBufReader.close();
						return ModuleType.STIFUNIT;
					}
					line = cppBufReader.readLine();
				}
				cppBufReader.close();
			}
		}
		catch (IOException ex) {
			LogExceptionHandler
			.showNotifyDialog(Messages
					.getString("TestModuleTypeRecognizer.cannotFindCppFile"));
		}
		return ModuleType.NORMAL;
	}
}
