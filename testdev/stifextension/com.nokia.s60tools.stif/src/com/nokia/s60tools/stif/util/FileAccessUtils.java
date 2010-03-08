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


package com.nokia.s60tools.stif.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Utility class with static methods giving access to some Symbian and STIF
 * specific files
 */
public class FileAccessUtils {

	/**
	 * Fetch the entire contents of a text file, and return it in a String.
	 * Does not throw Exceptions to the caller. Returns null if file was not found.
	 *
	 * @param aFile is a file which already exists and can be read.
	 */
	static public String getContents(File aFile) {
		String retVal = null;
		try {
			retVal = getContents(new BufferedReader(new FileReader(aFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	/**
	 * Returns contents of a BufferedReader in string
	 *  
	 * @param input buffered reader that contains the content
	 * @return content in string
	 */
	static public String getContents(BufferedReader input) {
		StringBuffer contents = new StringBuffer();
	    try {
	      //use buffering, reading one line at a time
	      String line = null;
	      /*
	      * readLine is a bit quirky :
	      * it returns the content of a line MINUS the newline.
	      * it returns null only for the END of the stream.
	      * it returns an empty String if two newlines appear in a row.
	      */
	      while ((line = input.readLine())!= null) {
	        contents.append(line);
	        contents.append(System.getProperty("line.separator"));
	      }
	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        if (input!= null) {
	          //flush and close both "input" and its underlying FileReader
	          input.close();
	        }
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	    return contents.toString();
	}

	/**
	 * Change the contents of text file in its entirety, overwriting any
	 * existing text.
	 *
	 * Throws all exceptions to the caller.
	 *
	 * @param aFile is an existing file which can be written to.
	 * @throws IllegalArgumentException if param does not comply.
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException if problem encountered during write.
	 */
	static public void setContents(File aFile, String aContents)
	                                 throws FileNotFoundException, IOException {
		if (aFile == null) {
	      throw new IllegalArgumentException("File should not be null.");
	    }
	    if (!aFile.exists()) {
	      throw new FileNotFoundException ("File does not exist: " + aFile);
	    }
	    if (!aFile.isFile()) {
	      throw new IllegalArgumentException("Should not be a directory: " + aFile);
	    }
	    if (!aFile.canWrite()) {
	      throw new IllegalArgumentException("File cannot be written: " + aFile);
	    }

	    Writer output = null;
	    try {
	      //use buffering
	      //FileWriter always assumes default encoding is OK!
	      output = new BufferedWriter( new FileWriter(aFile) );
	      output.write( aContents );
	    }
	    finally {
	      //flush and close both "output" and its underlying FileWriter
	      if (output != null) output.close();
	    }
	  }
	
	/**
	 * Returns path of Stiftestframework bld.inf file
	 *  
	 * @return content as IPath or null on error case
	 */
	static public IPath getStifBldInfPath(Vector<String> SDKPaths)
	{
		//Go to group dir to retrieve STIF blf.inf file
		IPath pathToStif = new Path(SDKPaths.get(0));
		String stringPathToStif = pathToStif.toOSString() + "stif";
		//Retrieve a path to Stif bld.inf file
		String stifBldFilePath = stringPathToStif.concat(File.separator + "group" + File.separator + "bld.inf");
		IPath path = new Path(stifBldFilePath);
		if (path.isValidPath(stifBldFilePath) )
			return path;
		else 
			return null;
	}
	
	/**
	 * Function that creates array of config files paths
	 * @param cfgFileName Name of config file
	 * @return array containing paths to config files
	 */
	static public ArrayList<IPath> getConfigFilePathts(String cfgFileName, 
			Vector<String> SDKList)
	{
		ArrayList<IPath> pathsList = new ArrayList<IPath>();
		Iterator it = FileAccessUtils.getTestFrameworkIniPathts(SDKList).iterator();
		while(it.hasNext())
		{
			IPath testFIniPath = (IPath) it.next();
			IPath cfgFilePath = testFIniPath.removeLastSegments(1);
			cfgFilePath = cfgFilePath.append(cfgFileName);
			pathsList.add( cfgFilePath);
		}
		
		return pathsList;
	}
	
	/** Funtion that creates array of paths of TestFramework.ini files
	 * 
	 * @return array of paths to TestFramework.ini file
	 */
	static public ArrayList<IPath> getTestFrameworkIniPathts(Vector<String> SDKPaths)
	{
		ArrayList<IPath> pathsList = new ArrayList<IPath>();
		Iterator<String> it = SDKPaths.iterator();
		while (it.hasNext()) {
			pathsList.add(new Path(it.next() + "epoc32\\winscw\\c\\testframework\\testframework.ini"));
		}
		return pathsList;
	}
	
	/** Function returning path to mmp file
	 * @return path to mmp file
	 */
	static public IPath getMmpFileLoc()
	{
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();  
	    IProject[] projects = root.getProjects();
	    IPath fullPath = projects[0].getLocation();
	    return fullPath;
	}
	
	/**
	 * Returns project type
	 */
	public static String getProjectType(String name, String strFullPath) 
	{
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();  
	    IProject[] projects = root.getProjects();
	    name = projects[0].getName();
	    String mmpName = name.concat(".mmp");
	    IPath fullPath = projects[0].getLocation();
	    strFullPath = fullPath.toOSString();
	    String strCppFullPath = strFullPath.concat(File.separator+
	    		"group"+File.separator+mmpName);
	    File mmpFile = new File(strCppFullPath);
	    String mmpFileContent = FileAccessUtils.getContents( mmpFile );
	    int startIndex = mmpFileContent.indexOf("TYPE") + 5;
	    int endIndex = mmpFileContent.indexOf("*/");
	    String type = mmpFileContent.substring(startIndex, endIndex);
		return type;
	}
	
	/**
	 * Checks if proper templates exist
	 * 
	 * @param name
	 * 			template file name
	 * @param strFullPath
	 * 			template file path
	 * @return
	 * 			true if proper template exists
	 */
	public static boolean checkTestScripterOrHardcodedTemplateExistance(String name, String strFullPath){
		File cpp = new File(strFullPath + "\\" + name);
		if(!cpp.exists())
			return false;
		String[] tags = { "[INCLUDE FILES]","[test cases entries]","[End of File]" };
		String cppContent = FileAccessUtils.getContents(cpp);
		for(int i = 0; i < tags.length; i++)
		{
			if(cppContent.indexOf(tags[i]) == -1)
				return false;
		}
		return true;
	}
}