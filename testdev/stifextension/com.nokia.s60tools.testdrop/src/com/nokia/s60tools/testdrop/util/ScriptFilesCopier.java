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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;

/**
 * Utility class for copying files from original location to a new location.
 * This is needed to build a proper structure of a test drop
 *
 */
public class ScriptFilesCopier {
	
	private static final String relativeIniRootPath = "\\epoc32\\winscw\\c\\TestFramework";
	private static final String confDirectoryName = "\\conf";
	
	/**
	 * Copies all .cfg files from "conf" directory of a project to c:\testframework 
	 * directory on a given device
	 *  
	 * @param pathToDevice
	 * 			path to a device
	 * @param projectPath
	 * 			path to the project which files are to be copied
	 */
	public static void copyScriptFiles(String pathToDevice, String projectPath) {
		File confDir = new File(projectPath + confDirectoryName);
		FilenameFilter cfgFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.endsWith(".cfg")) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] filesToCopy = confDir.listFiles(cfgFilter);
		
		for (int i = 0; i < filesToCopy.length; i++) {
			copyScriptFile(filesToCopy[i], pathToDevice + relativeIniRootPath);
		}
	}
	
	/**
	 * Used to copy a set of specific files to the device.
	 * Files can be of any type and be located in any place
	 * 
	 * @param pathToDevice
	 * 			path to a device where the files will be copied
	 * @param filesToCopy
	 * 			IFile objects pointing to files that are to be copied
	 */
	public static void copyTestCombinerScriptFiles(String pathToDevice, IFile[] filesToCopy) {
		for (int i = 0; i < filesToCopy.length; i++) {
			copyScriptFile(new File(filesToCopy[i].getLocation().toString()), pathToDevice + relativeIniRootPath);
		}
	}
	
	/**
	 * Helper method for copying a single file
	 * 
	 * @param fileToCopy
	 * 			File object pointing to the file that is to be copied
	 * @param newPath
	 * 			new path of the file
	 */
	private static void copyScriptFile(File fileToCopy, String newPath) {
        try {	
			InputStream in = new FileInputStream(fileToCopy);
	        OutputStream out = new FileOutputStream(new File(newPath + "\\" + fileToCopy.getName()));
   
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        in.close();
	        out.close();
        }
        catch (IOException ex){
        	LogExceptionHandler.log("copyScriptFile: unable to copy " + fileToCopy.getName() + " file to " +
        			newPath + " directory");
        }
	}
}
