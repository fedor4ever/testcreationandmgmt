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

package com.nokia.s60tools.testdrop.engine.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.testrun.TestRun;
import com.nokia.s60tools.testdrop.util.IniFileModifier;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.ScriptFilesCopier;


/**
 * Job class for background process of running test module (test module on emulator)
 * 
 */
public class RunTestModuleJob extends Job {

	/**
	 * Enumeration to describe type of a test module
	 *
	 */
	public enum ModuleType {
		HARDCODED,
		NORMAL,
		TESTCLASS,
		STIFUNIT,
		TESTCOMBINER,
	}
	
	private TestRun testRun;
	
	private final String testReportXmlRelativePath = "\\epoc32\\winscw\\c\\Logs\\Testframework\\TestReport.xml";
	
	private final String commandHardcoded = " -testmodule [moduleName] -engineini c:\\testframework\\tmp.ini";
	private final String commandTestclass = " -testmodule testscripter [scriptFilesPaths] -engineini c:\\testframework\\tmp.ini";
	private final String commandNormal = " -testmodule [moduleName] [scriptFilesPaths] -engineini c:\\testframework\\tmp.ini";
	private final String commandTestCombiner = " -testmodule testcombiner [scriptFilesPaths] -engineini c:\\testframework\\tmp.ini";
	private final String moduleNamePlaceholder = "[moduleName]";
	private final String scriptFilesPathsPlaceholder = "[scriptFilesPaths]";
	
	private static final String JOB_TITLE = Messages
		.getString("RunTestModuleJob.runsTestModule");
	
	private static boolean isTestRunInProgress = false;
	
	/**
	 * Constructors
	 * 
	 * @param pathToDevice
	 *            path to atsinterface.exe file
	 * @param moduleName
	 *            name of the test module
	 * @param scriptFilePath
	 *            path to .cfg file for test scripter or test combiner
	 * @param iniFilePath
	 *            path to .ini file. Proper ini file is needed for STIF to produce test results
	 *            in xml form
	 */
	public RunTestModuleJob(TestRun testRun) {
		super(JOB_TITLE);
		this.testRun = testRun;
	}
	
	/**
	 * Checks if any RunTestModuleJob is already running.
	 * In such case a new RunTestModuleJob cannot be run 
	 * 
	 * @return
	 * 		true if some RunTestModuleJob is running
	 * 		false if no RunTestModuleJob is running
	 */
	public static boolean getIsTestRunInProgress() {
		return isTestRunInProgress;
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		isTestRunInProgress = true;
		
		String pathToReportXml = testRun.getDevicePath() + testReportXmlRelativePath;
		File reportToDelete = new File(pathToReportXml);
		if(reportToDelete.exists()){
			reportToDelete.delete();
		}		
		
		Runtime runtime = Runtime.getRuntime();
		
		IniFileModifier.modifyEpocIniWithTextShell(testRun.getEpocIniRootPath());
		File tmpTestFrameworkIni = IniFileModifier.getIniWithXMLAsReport(testRun.getIniRootPath());
		
		String command = "";
		String scriptFilesPathsConcatenated = "";
		switch(testRun.getModuleType()) {	// different command line parameter values for atsinterface.exe depending on test module type 
		case HARDCODED :
		case STIFUNIT :
			command = testRun.getATSInterfaceFilePath() 
				+ commandHardcoded.replace(moduleNamePlaceholder, testRun.getModuleName());
			break;
		case TESTCLASS :
			ScriptFilesCopier.copyScriptFiles(testRun.getDevicePath(), testRun.getProjectPath());
			for (int i = 0; i < testRun.getScriptFilesPaths().length; i++) {
				scriptFilesPathsConcatenated += "-config " + testRun.getScriptFilesPaths()[i] + " ";
			}
			command = testRun.getATSInterfaceFilePath() 
				+ commandTestclass.replace(scriptFilesPathsPlaceholder, scriptFilesPathsConcatenated);
			break;
		case NORMAL :
			for (int i = 0; i < testRun.getScriptFilesPaths().length; i++) {
				scriptFilesPathsConcatenated += "-config " + testRun.getScriptFilesPaths()[i] + " ";
			}
			command = testRun.getATSInterfaceFilePath() + 
				commandNormal.replace(moduleNamePlaceholder, testRun.getModuleName())
				.replace(scriptFilesPathsPlaceholder, scriptFilesPathsConcatenated);
			break;
		case TESTCOMBINER :
			IFile[] selectedScriptFiles = testRun.getTestCombinerScriptFilesAsIFiles();
			ScriptFilesCopier.copyTestCombinerScriptFiles(testRun.getDevicePath(), selectedScriptFiles);
			for (int i = 0; i < testRun.getTestCombinerScriptFiles().length; i++) {
				scriptFilesPathsConcatenated += "-config " + testRun.getTestCombinerScriptFiles()[i] + " ";
			}
			command = testRun.getATSInterfaceFilePath() + 
				commandTestCombiner.replace(scriptFilesPathsPlaceholder, scriptFilesPathsConcatenated);
			
			break;
		}
		
		try {
			runtime.exec(command).waitFor();
			isTestRunInProgress = false;
		}
		catch (InterruptedException ex) {
			LogExceptionHandler.log(Messages.getString("RunTestModuleJob.InterruptedExceptionMessage") + " "
					+ ex.getMessage());
		}
		catch (IOException ex) {
			LogExceptionHandler.log(Messages.getString("RunTestModuleJob.IOExceptionMessage") + " "
					+ ex.getMessage());
		}

		File tmpIni = new File(testRun.getEpocIniRootPath());
		if(tmpIni.exists()) {
			tmpIni.delete();
		}
		
		IniFileModifier.restoreOriginalEpocIni();
		tmpTestFrameworkIni.delete();
		
		try {
			String reportContent = getTestReportAsString(pathToReportXml);
			return new Status(IStatus.OK, this.getName(), reportContent);
		}
		catch (IOException ex) {
			return new Status(IStatus.WARNING, this.getName(), 
					Messages.getString("RunTestModuleJob.testReportXmlNotFound"));
		}
	}
	
	/**
	 * Returns a test report in a form of String object
	 * 
	 * @param pathToReportXml
	 * 			current path to test report in xml format
	 * @return
	 * 			test report as String
	 * @throws IOException
	 * 			if a problem with files occur
	 */
	private String getTestReportAsString(String pathToReportXml) throws IOException {
		File reportXml = new File(pathToReportXml);
		BufferedReader reportReader = new BufferedReader(new FileReader(reportXml));
		
		Date reportCreationDate = new Date(reportXml.lastModified());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(reportCreationDate);
		String reportContent = appendDateTimePart(calendar.get(Calendar.YEAR)) + "-";
		reportContent += appendDateTimePart(calendar.get(Calendar.MONTH) + 1) + "-";
		reportContent += appendDateTimePart(calendar.get(Calendar.DAY_OF_MONTH)) + " ";
		reportContent += appendDateTimePart(calendar.get(Calendar.HOUR_OF_DAY)) + ":";
		reportContent += appendDateTimePart(calendar.get(Calendar.MINUTE)) + ":";
		reportContent += appendDateTimePart(calendar.get(Calendar.SECOND)) + ";";
		
		String line = null;
		while ((line = reportReader.readLine()) != null) {
			reportContent += line;
		}
		reportReader.close();
		
		return reportContent;
	}
	
	/**
	 * Method adds a beginning "0" to a date/time bit if this is a simple digit
	 * 
	 * @param toAppend
	 * 			a value to be checked for a need to be expaded with "0"
	 * @return
	 * 			a ready to append value
	 */
	private String appendDateTimePart(int toAppend) {
		String result = "";
		if (toAppend < 10) {
			result += "0";
		}
		return result + Integer.toString(toAppend);
	}
}
