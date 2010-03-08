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

package com.symbian.driver.core.controller.tasks;

import static com.symbian.driver.core.environment.ILiterals.TEST_EXECUTE;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.Task;
import com.symbian.driver.TestCase;
import com.symbian.driver.TestCasesList;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.core.CrashException;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.controller.utils.SerialListener;
import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.CoreDumpProxy;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.utils.Epoc;

/**
 * @author EngineeringTools
 * 
 */
public class TEFTask implements IVisitor {

	/** The logger for the Visitor class. */
	private final static Logger LOGGER = Logger.getLogger(TEFTask.class.getName());

	private final TDConfig CONFIG = TDConfig.getInstance();

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();

	/** Local copy of the TestExecute Script EMF Object. */
	private final TestExecuteScript iTestExecuteScript;

	DeviceCommsProxy iDeviceProxy = null;
	
	/**
	 * @param aTestExecuteScript
	 */
	public TEFTask(TestExecuteScript aTestExecuteScript) {
		iTestExecuteScript = aTestExecuteScript;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IVisitor#execute(com.symbian.driver.Task,
	 *      com.symbian.driver.core.processors.PreProcessor)
	 */
	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {

		// step1 : prepare TEF command
		String lTargetScript = iTestExecuteScript.getSymbianPath();
		// get rid of $: from symbian path
		lTargetScript = lTargetScript.replace("$:", ILiterals.C);

		String lOperator = null;
		String lTestCasesString = "";
		TestCasesList lTestCasesList = iTestExecuteScript.getTestCasesList();
		// Get TestCases if there are any
		if (lTestCasesList != null) {
			lOperator = lTestCasesList.getOperator().getLiteral().equalsIgnoreCase("exclude") ? "-tcx" : "-tci";
			Iterator lIter = lTestCasesList.getTestCase().iterator();

			while (lIter.hasNext()) {
				TestCase lTestCase = (TestCase) lIter.next();
				String lTarget = lTestCase.getTarget();
				if (ModelUtils.isTestCasesFile(lTarget)) {
					// if it's file then calculate the destination.
					lTestCasesString = lTestCasesString + (new File(lTargetScript)).getParent().toString() + File.separator
							+ (new File(lTarget)).getName();
					lTestCasesString = lTestCasesString.replace("$:", ILiterals.C);
				} else {
					// it's either a range or a single test case
					lTestCasesString = lTestCasesString + lTarget;
				}
				if (lIter.hasNext()) {
					lTestCasesString = lTestCasesString + ",";
				}
			}
		}

		if (lOperator != null && lTestCasesString != "") {
			lTargetScript = lTargetScript + " " + lOperator + " " + lTestCasesString;
		}

		// Create TEF process

		try {
			iDeviceProxy = DeviceCommsProxy.getInstance();
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
			iExceptions.put(lException, ESeverity.ERROR);
			return iExceptions;
		}
		List<String> lArgs = new LinkedList<String>();
		lArgs.add(lTargetScript);

		String lUCCAddress = null;
		try {
			lUCCAddress = CONFIG.getPreference(TDConfig.UCC_IP_ADDRESS);
		} catch (ParseException lParseException) {
			iExceptions.put(lParseException, ESeverity.ERROR);
			LOGGER.log(Level.WARNING,
					"Could not get the UCC Address. Please ensure you have specified the correct address using the -f switch.",
					lParseException);
			return iExceptions;
		}

		// step2 : start a serial listener if necessary

		SerialListener listen = null;
		// if TEF is configured for logchannel = serial / both then have the
		// serial listener here
		String lChannel = CONFIG.getLogChannel();
		if (lChannel == null) {
			lChannel = "file"; // BC
		}
		if (lChannel != null && (lChannel.compareToIgnoreCase("serial") == 0 || lChannel.compareToIgnoreCase("both") == 0)) {
			// also consider giving it the results path to dump the real time
			// logs into...
			try {
				String lScriptName = new File(iTestExecuteScript.getSymbianPath()).getName().replaceAll("\\.script", ".log");
				File lPCResultPath = new File(CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT)
						+ ModelUtils.getBaseDirectory((Task) iTestExecuteScript.eContainer().eContainer(), CONFIG
								.getPreferenceInteger(TDConfig.RUN_NUMBER)) + File.separator);

				File lPCLogFile = new File(lPCResultPath.getAbsolutePath() + lScriptName);
				if (!lPCResultPath.isDirectory() && !lPCResultPath.mkdirs()) {
					LOGGER.severe("Could not create result folder: " + lPCResultPath);
					iExceptions.put(new Exception("Could not create result folder"), ESeverity.ERROR);
				} else {
					listen = new SerialListener(CONFIG.getPreference(TDConfig.TEFPORT), lPCLogFile);
				}
			} catch (ParseException parseEx) {
				LOGGER.log(Level.SEVERE, parseEx.getMessage(), parseEx);
				iExceptions.put(parseEx, ESeverity.ERROR);
			}
		}
		
		// step3 : prepare crash-monitoring if specified to do so
		boolean lRecovery = isRecoveryOn();
		if(lRecovery)
			monitorTestApps();
				
		// step4 : run the process be it UCC or normal

		boolean lRes = false;
		// Execute the Test
		if (lUCCAddress != null && !lUCCAddress.equalsIgnoreCase("")) {
			// Run Script with UCC
			lRes = runUCC(lArgs, aTask);
		} else {
			// Run Script without UCC
			lRes = runRegular(lArgs, aTask);
		}
		if (lRes) {
			if (lChannel != null && (lChannel.compareToIgnoreCase("file") == 0 || lChannel.compareToIgnoreCase("both") == 0)) {
				retrieveResultFile(aTask);
			} else if (lChannel != null && (lChannel.compareToIgnoreCase("serial") == 0 || lChannel.compareToIgnoreCase("both") == 0)) {
				if (listen != null) {
					listen.setM_Life(false); // kill the listener thread.
				}
			}
		}
		
		// step5 : check crash status, if crash happens, retrieve crash data
		try {
			if(lRecovery) {
				if(CoreDumpProxy.getInstance().isAppCrash())
					retrieveCrashData();

				// Stop PCDS if it's running
				if(CoreDumpProxy.getInstance().stopServer())
					LOGGER.info("PCDS stopped successfully!");
				else
					LOGGER.info("Failed to stop PCDS!");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Unable to create PCDS proxy. Recovery disabled!",e);
		}
		
		return iExceptions;
	}

	private boolean isRecoveryOn() {
		try{
			String recovery = CONFIG.getPreference(TDConfig.RECOVERY);
			boolean isEmulator = Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM));
			return !isEmulator && "on".equalsIgnoreCase(recovery);
		}catch(Exception e) {
			LOGGER.warning("Unable to create PCDS proxy. Crash recovery disabled!");
			return false;
		}
	}

	/**
	 * @param aVisitor
	 * @param aTestExecuteScript
	 * @param lExecuteOnDevice
	 * @param aTask
	 * @return The last execption raised when running UCC.
	 * @throws JStatException
	 */
	private boolean runUCC(List<String> aArgs, Task aTask) {
		boolean lReturn = true;
		Socket lUccSocket = null;
		DataOutputStream lSocketOut = null;
		DataInputStream lSocketIn = null;
		int lRunNumber = 0;
		int lUccPort = -1;
		String lUccAddress = null;
		IDeviceComms.ISymbianProcess lProcess = null;

		try {
			String[] lUccSplit = TDConfig.getInstance().getPreference(TDConfig.UCC_IP_ADDRESS).split(":");
			lRunNumber = TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER);

			lUccAddress = lUccSplit[0];
			lUccPort = Integer.parseInt(lUccSplit[1]);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not get configuration for UCC.", lParseException);
			iExceptions.put(lParseException, ESeverity.ERROR);
			lReturn = false;
		} catch (NumberFormatException lNumberFormatException) {
			LOGGER.log(Level.SEVERE, "Could not parse the port number for UCC.", lNumberFormatException);
			iExceptions.put(lNumberFormatException, ESeverity.ERROR);
			lReturn = false;
		}

		if (lUccAddress == null || lUccAddress.equals("") || lUccPort < 0) {
			iExceptions.put(new UnknownHostException("Please specify a valid UCC address for example 192.168.0.1:3000"), ESeverity.ERROR);
			return false;
		}

		// Run the test
		try {
			LOGGER.info("Running UCC with:\n\tAddress: " + lUccAddress + "\n\tUCC Port:" + lUccPort);

			lUccSocket = new Socket(lUccAddress, lUccPort);
			lSocketOut = new DataOutputStream(lUccSocket.getOutputStream());
			lSocketIn = new DataInputStream(lUccSocket.getInputStream());

			LOGGER.fine("Starting UCC while still polling");
			lProcess = iDeviceProxy.createSymbianProcess();
			if (lProcess != null) {
				// run and don't wait
				if (!lProcess.runCommand(TEST_EXECUTE, aArgs, aTask.getTimeout() * 1000, false)) {
					iExceptions.put(new Exception("Failed to run TEF for UCC."), ESeverity.ERROR);
					lReturn = false;
				}

				// Tell UCC that the test has started.
				LOGGER.fine("Writing to UCC socket: " + lRunNumber);
				lSocketOut.writeInt(lRunNumber);
				lSocketOut.flush();

				int lUCCReply = lSocketIn.readInt();
				LOGGER.fine("UCC Reply: " + lUCCReply);
			}

		} catch (UnknownHostException lUnknownHostException) {
			LOGGER.log(Level.SEVERE, "Could not find UCC host", lUnknownHostException);
			iExceptions.put(lUnknownHostException, ESeverity.ERROR);
			return false;
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "IO Exception during UCC testing: "
					+ lIOException.getMessage()
					+ (lUccSocket != null ? "\nUcc Socket Connected: " + lUccSocket.isConnected() + "\nUcc Socket InputShutdown: "
							+ lUccSocket.isInputShutdown() + "\nUcc Socket OutputShutdown:" + lUccSocket.isOutputShutdown()
							+ "\nUcc Socket Bound: " + lUccSocket.isBound() : "\nUcc Socket is NULL"), lIOException);
			iExceptions.put(lIOException, ESeverity.ERROR);
			return false;
		} finally {

			// Close UCC
			if (lSocketOut != null) {
				try {
					LOGGER.log(Level.FINE, "Closing Socket Out.");
					lUccSocket.shutdownInput();
					lUccSocket.shutdownOutput();
					lSocketOut.close();
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "Could not close UCC Out socket.", lIOException);
					iExceptions.put(lIOException, ESeverity.ERROR);
				}
			}
			if (lSocketIn != null) {
				try {
					LOGGER.log(Level.FINE, "Closing Socket In.");
					lSocketIn.close();
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "Could not close UCC In socket.", lIOException);
					iExceptions.put(lIOException, ESeverity.ERROR);
				}
			}
			if (lUccSocket != null) {
				try {
					LOGGER.log(Level.FINE, "Closing Socket UCC.");
					lUccSocket.close();
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "Could not close UCC socket.", lIOException);
					iExceptions.put(lIOException, ESeverity.ERROR);
				}
			}

			if (!lUccSocket.isClosed()) {
				LOGGER.warning("Could not close the UCC sockets properly.");
			}

			lSocketOut = null;
			lSocketIn = null;
			lUccSocket = null;

			// Poll TEF Test
			if (!lProcess.join()) {
				iExceptions.put(new Exception("Coud not join UCC-TEF Process"), ESeverity.ERROR);
				lReturn = false;
			}

		}
		return lReturn;
	}

	/**
	 * @param aVisitor
	 * @param aTestExecuteScript
	 * @param aExecuteOnDevice
	 * @param aTask
	 * @return The last exception when running regular TEF Tests.
	 * @throws JStatException
	 */
	private boolean runRegular(List<String> aArgs, Task aTask) {
		boolean lReturn = true;
		String lBuildNumber = null;
		try {
			lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get build number");
		}
		
		int lTimeout;
		if (!aTask.isSetTimeout()) {
			lTimeout = 0;
		} else {
			lTimeout = aTask.getTimeout() <= 0 ? -1 : aTask.getTimeout() * 1000;
		}
		
		//determine the tef executable name to use.
		String TEST_EXECUTE_LITE = "TestExecuteLite.exe";
		TDConfig CONFIG = TDConfig.getInstance();
		boolean lEmulator = false;
		boolean bIsTefLite = false;
		String tef_exe_name;
		try{
			lEmulator = Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM));
			String tefLiteConfig = CONFIG.getPreference(TDConfig.TEF_LITE);
			if (tefLiteConfig.equalsIgnoreCase("auto"))
				bIsTefLite = CONFIG.isPreference(TDConfig.SYS_BIN);
			else
				bIsTefLite = tefLiteConfig.equalsIgnoreCase("true") || tefLiteConfig.equalsIgnoreCase("on"); 
			
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get configuration");
		}
		
		if (lEmulator)  //on emulator, two version of tef coexist: testexecute.exe and testexecutelite.exe
			tef_exe_name = bIsTefLite ?  TEST_EXECUTE_LITE : TEST_EXECUTE;
		else
			tef_exe_name = TEST_EXECUTE; //on real device, always use "testexecute.exe"

		if (lBuildNumber != null && Epoc.is9x(lBuildNumber)) {
			// run TEF test and wait
			if (!iDeviceProxy.createSymbianProcess().runCommand(tef_exe_name, aArgs, lTimeout, true)) {
				iExceptions.put(new Exception("Failed to run TEF"), ESeverity.ERROR);
				lReturn = false;
			}
		} else {
			// run TEF Test and don't wait
			if (!iDeviceProxy.createSymbianProcess().runCommand(tef_exe_name, aArgs, lTimeout, false)) {
				iExceptions.put(new Exception("Failed to run TEF"), ESeverity.ERROR);
				lReturn = false;
			}
		}
		return lReturn;
	}

	private void monitorTestApps() {
		LOGGER.fine("Prepare to monitor test application crash!");
		String lScript = iTestExecuteScript.getSymbianPath();
		LOGGER.fine("Parse script file: " + lScript);
		//load test server name from script file
		Vector<String> lTestServers = loadServerNames(lScript);
		if(lTestServers == null) {
			LOGGER.warning("Unable to get server names to monitor. Disable!");
			return;
		}
		
		boolean lSuccess = false;
		for(String lServerName : lTestServers) {
			try{
				lSuccess = CoreDumpProxy.getInstance().monitorApp(lServerName);
				if(lSuccess)
					LOGGER.info("Monitor crash on applications: " + lServerName);
				else
					LOGGER.warning("Failed to monitor crash!");
				}catch(Exception e) {}
		}
				
		// Start the PCDS - Process Core Dump Server, if configured
		try{
			if(CoreDumpProxy.getInstance().startServer())
				LOGGER.info("PCDS started successfully!");
			else
				LOGGER.warning("Failed to start PCDS server!");
		}catch(Exception e) {
			LOGGER.warning("Unable to create PCDS proxy. Crash recovery disabled!");
		}
	}

	private Vector<String> loadServerNames(String aScript) {
		Vector<String> lResults = null;

		String lUserHome = System.getProperty("user.home");
		File lTempDir = new File(lUserHome, "temp");
		if(!lTempDir.exists())
			lTempDir.mkdirs();
		File lTempFile = null;
		try{
			lTempFile = File.createTempFile("testdriver_", ".script", lTempDir);
			LOGGER.fine("retrieve script file from " + aScript + " to " + lTempFile.getAbsolutePath());
			if(!DeviceUtils.retrieveFile(aScript, lTempFile.getAbsolutePath(), false, null)) {
				LOGGER.warning("Unable to retrieve TEF script file: " + aScript);
				return null;
			}
		}catch(IOException e) {
			LOGGER.warning("Unable to create temporary storage! Server name loading skipped!");
			return null;
		}
		
		//parse script file
		try {
			BufferedReader lScriptInput = new BufferedReader(new FileReader(lTempFile));
			String lScriptLine = null;
			//prepare regular expression
			Pattern lSuitePattern = Pattern.compile("LOAD_SUITE\\s*(.*)");
			Pattern lScriptPattern = Pattern.compile("RUN_SCRIPT\\s*(.*)");
			Matcher lMatcher = null;
			
			while((lScriptLine = lScriptInput.readLine())!= null) {
				lMatcher = lSuitePattern.matcher(lScriptLine);
				if(lMatcher.matches()) {
					//LOAD_SUITE found, get server name
					if(lResults == null)
						lResults = new Vector<String>();
					String lServerName = lMatcher.group(1);
					int lIndex = lServerName.indexOf(" ");
					if(lIndex > -1)
						lServerName = lServerName.substring(0, lIndex);
					lResults.add("c:\\sys\\bin\\" + lServerName+".exe");
				}else {
					lMatcher = lScriptPattern.matcher(lScriptLine);
					if(lMatcher.matches()) {
						//RUN_SCRIPT found, retrieve it and recursively call loading
						String lScriptFile = lMatcher.group(1);
						Vector<String> lNames = loadServerNames(lScriptFile);
						if(lResults == null)
							lResults = new Vector<String>();
						lResults.addAll(lNames);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Can't open script file for reading!");
		}
		
		return lResults;
	}

	private void retrieveCrashData() {
		String lRDebugBase = null;
		try {
			lRDebugBase = CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT)
							+ ModelUtils.getBaseDirectory((Task) iTestExecuteScript.eContainer().eContainer(),
															CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER));
			lRDebugBase = lRDebugBase.replaceAll("\\\\+", "\\\\");
		} catch (ParseException lParseException) {
			lRDebugBase = new File(".").getAbsolutePath();
			LOGGER.log(Level.WARNING, "Store crash data under current directory: " + lRDebugBase);
		}
		
		SimpleDateFormat lFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String lTimeStamp = lFormat.format(new Date());
		String lCrashDataPath = lRDebugBase + File.separator 
								+ "crashdata" + File.separator + lTimeStamp;
		
		CrashException lException = new CrashException();
		try{
			boolean lSuccess = CoreDumpProxy.getInstance().getCrashData(lCrashDataPath);
			if(lSuccess) {
				LOGGER.info("Crash data stored at: " + lCrashDataPath);
				lException.setCoreDumpUrl(new File(lCrashDataPath).toURI().toURL());
			}
			else 
				LOGGER.warning("Failed to get crash data from device!");
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Unable to create PCDS proxy. Recovery disabled!",e);
		}
		iExceptions.put(lException, ESeverity.INFO);
	}

	/**
	 * @param aTask
	 * @param aVisitor
	 * @param aTestExecuteScript
	 * @return The last exception when retrieve result files.
	 * @throws JStatException
	 * @throws IOException
	 */
	private boolean retrieveResultFile(Task aTask) {
		boolean lReturn = true;
		LOGGER.fine("Getting results for test " + iTestExecuteScript.getPCPath() + ", " + iTestExecuteScript.getSymbianPath());

		// Get Test Name + Path
		String lTestName = new File(iTestExecuteScript.getSymbianPath()).getName();
		File lPCResultPath = null;
		try {
			lPCResultPath = new File((TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT)
					+ ModelUtils.getBaseDirectory((Task) iTestExecuteScript.eContainer().eContainer(), CONFIG
							.getPreferenceInteger(TDConfig.RUN_NUMBER)) + File.separator).replaceAll("\\\\+", "\\\\"));

			// Retrieve Results
			if (!lPCResultPath.isDirectory() && !lPCResultPath.mkdirs()) {
				LOGGER.severe("Could not create result folder: " + lPCResultPath);
			}

		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Cound not get the configuration while retrieving the result file", lParseException);
			iExceptions.put(lParseException, ESeverity.ERROR);

			lPCResultPath = new File(".");
		}

		// Retrieve the results
		try {

			String lResultPath = CONFIG.getResultHtmlFileLocation();
			if (lResultPath != null) {
				lResultPath = lResultPath.replace("${SYSDRIVE}", ILiterals.C);
			}
			if (!retrieveFile(lTestName, lPCResultPath.getCanonicalPath(), lResultPath, ".htm", aTask)) {
				iExceptions.put(new Exception("Failed to retrieve htm Test result file."), ESeverity.ERROR);
				lReturn = false;
			}
			if (!retrieveFile(ILiterals.TEST_RESULT, lPCResultPath.getCanonicalPath(), lResultPath, ".htm", aTask)) {
				iExceptions.put(new Exception("Failed to retrieve " + ILiterals.TEST_RESULT + ".htm " + " result file."), ESeverity.ERROR);
				lReturn = false;
			}
			lResultPath = CONFIG.getResultXmlFileLocation();
			if (lResultPath != null) {
				lResultPath = lResultPath.replace("${SYSDRIVE}", ILiterals.C);
			}
			// try to get the xml file.
			retrieveFile(lTestName, lPCResultPath.getCanonicalPath(), lResultPath, ".xml", aTask);

		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "I/O Exception while trying to retrieve a file.", lIOException);
			iExceptions.put(lIOException, ESeverity.ERROR);
			lReturn = false;
		}
		return lReturn;
	}

	/**
	 * @param lResultFile
	 * @param lPCResultPath
	 * @param aTask
	 * @throws JStatException
	 * @throws TimeLimitExceededException
	 */
	private static boolean retrieveFile(String lTestName, final String lPCResultPath, final String aSymbianResultPath,
			final String aFileExtension, Task aTask) {
		if (aSymbianResultPath != null) {
			lTestName = lTestName.replaceAll("\\.script", aFileExtension);
			lTestName = lTestName.replaceAll("\\.htm", aFileExtension);
			lTestName = lTestName.replaceAll("\\.xml", aFileExtension);
			return DeviceUtils.retrieveFile(aSymbianResultPath + lTestName, lPCResultPath + File.separator + lTestName, false, aTask);
		}
		return false;
	}
}
