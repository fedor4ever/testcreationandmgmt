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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nokia.s60tools.testdrop.resources.Messages;

/**
 * A class for manipulating epoc.ini and TestFramework.ini files.
 * epoc.ini - adding/removing "textshell" value
 * TestFramework.ini - changing testresult format to xml
 *
 */
public class IniFileModifier {
	
	private static String pathToIniRoot = null;
	
	private static final String newModuleTag = "[New_Module]";
	private static final String endModuleTag = "[End_Module]";
	private static final String testReportFormatAttribute = "TestReportFormat";
	private static final String epocIniName = "\\epoc.ini";
	private static final String backedUpEpocIniName = "\\_epoc.ini";
	private static final String textshellValue = "\r\ntextshell";
	
	/**
	 * Changes TestFramework.ini file so the test report is produced as an xml file
	 * A temporary .ini file is created and this new is used when running epoc.ini
	 * 
	 * @param pathToIniRoot
	 * 			path to the directory that contains TestFramework.ini file
	 * @return
	 * 			File object that points to a new temporary ini file that makes
	 * 			atsinterface.exe produce test result as xml files
	 */
	public static File getIniWithXMLAsReport(String pathToIniRoot) {
		File ini = new File(pathToIniRoot + "\\testframework.ini");
		File tmpIni = new File(pathToIniRoot + "\\tmp.ini");
		
		if (!ini.exists()) {
			return null;
		}
		
		try {
			BufferedReader iniReader = new BufferedReader(new FileReader(ini));
			BufferedWriter iniWriter = new BufferedWriter(new FileWriter(tmpIni));
			
			String content = "";
			boolean insideModuleSection = false;
			while((content = iniReader.readLine()) != null) {
				if(content.indexOf(newModuleTag) > -1){
					insideModuleSection = true;
					continue;
				}
				else if(content.indexOf(endModuleTag) > -1) {
					insideModuleSection = false;
					continue;
				}
				else if(insideModuleSection == true){
					continue;
				}
				if(content.indexOf(testReportFormatAttribute) > -1) {
					content = content.replace("TXT", "XML");
					content = content.replace("HTML", "XML");
				}
				iniWriter.write(content);
				iniWriter.newLine();
			}
			iniWriter.close();
		}
		catch (IOException ex) {
			LogExceptionHandler.log(Messages.getString("IniFileModifier.IOExceptionMessage") + " "
					+ ex.getMessage());
		}
		return tmpIni;
	}
	
	/**
	 * Adds "textshell" value to epoc.ini file. In fact a temporary file is produced.
	 * The original epoc.ini file is kept only it's name is changed.
	 * 
	 * @param pathToIniRoot
	 * 			path to directory that contains epoc.ini file
	 * @return
	 * 			true if the modification succeeded
	 */
	public static boolean modifyEpocIniWithTextShell(String pathToIniRoot) {
		File epocIni = new File(pathToIniRoot + epocIniName);
		if (!epocIni.exists()) {
			return false;
		}
		epocIni.renameTo(new File(pathToIniRoot + "\\_epoc.ini"));
		epocIni = new File(pathToIniRoot + backedUpEpocIniName);
		
		try {	
			File copyOfEpocIni = new File(pathToIniRoot + epocIniName);
			InputStream input = new FileInputStream(epocIni);
			OutputStream output = new FileOutputStream(copyOfEpocIni);
		
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = input.read(buf)) > 0) {
				output.write(buf, 0, length);
			}
			input.close();
			
			String textshell = textshellValue;
			output.write(textshell.getBytes(), 0, textshell.length());
			output.close();
		}
		catch (IOException ex){
			LogExceptionHandler.log(Messages.getString("IniFileModifier.modifyEpocIniWithTextShell") + " " +
					ex.getMessage());
			return false;
		}
		IniFileModifier.pathToIniRoot = pathToIniRoot;
		return true;
	}
	
	/**
	 * Removes the temporary epoc.ini file and restores the original file
	 *
	 */
	public static void restoreOriginalEpocIni() {
		File modifiedEpocIni = new File(pathToIniRoot + epocIniName);
		if (modifiedEpocIni.exists()) {
			modifiedEpocIni.delete();
		}
		File originalEpocIni = new File(pathToIniRoot + backedUpEpocIniName);
		if (originalEpocIni.exists()) {
			originalEpocIni.renameTo(new File(pathToIniRoot + epocIniName));
		}
	}
}
