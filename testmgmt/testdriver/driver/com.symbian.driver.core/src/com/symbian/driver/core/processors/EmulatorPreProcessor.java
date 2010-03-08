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

package com.symbian.driver.core.processors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.FileUpdateListener;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.utils.Epoc;
import com.symbian.utils.JarUtils;
import com.symbian.utils.Utils;
import com.symbian.utils.XSLTUtils;

/**
 * @author EngineeringTools
 * 
 */
public class EmulatorPreProcessor implements PreProcessor {

	/** Post Processor. */
	private EmulatorPostProcessor iEmulatorPostProcessor = null;

	// Emulator Variables
	/** The reference to the emulator. */
	private ExecuteOnHost iEpoc = null;

	// RDebug Ouput File
	// /** Location of where RDebug output gets streamed too. */
	// private File iRDebugOutput = null;

	// Folder and File locations
	/** The folder \epoc32\RELEASE\WINSCW\. */
	private File iWinscwReleaseDir = null;

	/** The folder \epoc32\WINSCW\c\. */
	private File iWinscwCDir = null;

	/** The file \epoc32\RELEASE\WINSCW\ethertap.pdd */
	private File iEthertapPddFile = null;

	private FileUpdateListener iDebugListener = null;

	private File iEpocWindOut = new File(System.getProperty("java.io.tmpdir"), "epocwind.out");

	/**
	 * Constructor for the Emulator Device.
	 * 
	 * Sets up the location of the stat.ini file.
	 * 
	 * @throws ParseException
	 */
	public EmulatorPreProcessor() {
		LOGGER.entering(EmulatorPreProcessor.class.getName(), "Constructor");

		// Intialise the JSTAT transport
		File lEpocRoot = new File(".");
		String lVariant = "udeb";

		try {
			lEpocRoot = TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT);
			lVariant = TDConfig.getInstance().getPreference(TDConfig.VARIANT);

		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get preferences.", lParseException);
		}

		iWinscwReleaseDir = new File(lEpocRoot, "/epoc32/RELEASE/WINSCW/" + lVariant);
		iWinscwCDir = new File(lEpocRoot, "/epoc32/WINSCW/c/");

		iEthertapPddFile = new File(iWinscwReleaseDir, "/ethertap.pdd");

		LOGGER.exiting(EmulatorPreProcessor.class.getName(), "Constructor");
	}

	/**
	 * Starts the Epoc Emulator using stat.
	 * 
	 * @throws ParseException
	 *             If there is a configuration error.
	 * @throws IOException
	 *             If there is a file error.
	 * @throws JStatException
	 *             If there is a STAT error.
	 * @throws InterruptedException
	 *             If there is a thread error.
	 * @throws TimeLimitExceededException
	 *             If the startup timedout.
	 */
	public boolean start() throws ParseException, IOException, TimeLimitExceededException {
		// Shutdown Hook
		boolean lReturn = true;
		iEmulatorPostProcessor = new EmulatorPostProcessor(iEpoc, iEthertapPddFile, iWinscwReleaseDir, iDebugListener);
		Runtime.getRuntime().addShutdownHook(iEmulatorPostProcessor);

		String lCommsDBInput = TDConfig.getInstance().getPreference(TDConfig.COMMDB).toLowerCase();
		if (lCommsDBInput.equalsIgnoreCase("on") || lCommsDBInput.equalsIgnoreCase("true")
				|| lCommsDBInput.startsWith("overwrite")) {
			// Run WinTAP for the emulator.
			configureEmulator();
		}

		try {
			if (!DeviceCommsProxy.getInstance().startCommsServer(true)) {
				LOGGER.log(Level.SEVERE, "Failed to start and connect to emulator.");
				lReturn = false;
			} else {
				iDebugListener = new FileUpdateListener();
				iDebugListener.setInputFile(iEpocWindOut);
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "Could not start emulator communication services.", lException);
			lReturn = false;
		}
		return lReturn;
	}

	/**
	 * Stops the emulator.
	 */
	public void stop() {
		if (iEmulatorPostProcessor != null) {

			try {
				iEmulatorPostProcessor.start();
			} catch (IllegalThreadStateException lException) {
				// Make sure never run to post processor more than once
				return;
			}

			try {
				iEmulatorPostProcessor.join();
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "Shutdown was interupted. Please shutdown and restore manaully.",
						lInterruptedException);
			}
		}

	}

	/**
	 * Restarts the emulator.
	 */
	public boolean restart() {
		Boolean lReturn = true;
		stop();

		try {
			lReturn = start();
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to a Configuration issue.", lParseException);
			lReturn = false;
		} catch (IOException lException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to an IO Exception.", lException);
			lReturn = false;
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to a TimeOut.", lTimeLimitExceededException);
			lReturn = false;
		}

		// poll device and return true or false
		return lReturn;
	}

	public void setRDebugOuput(File aRDebugOutput) {
		iDebugListener.setOutputFile(aRDebugOutput);
	}

	/**
	 * @see com.symbian.driver.core.processors.PreProcessor#restoreRDebugOutput()
	 */
	public void restoreRDebugOutput() {
		iDebugListener.setOutputFile(null);
	}

	/**
	 * @param aOverwriteCommDB
	 * @throws ParseException
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	private void configureEmulator() throws ParseException, IOException, TimeLimitExceededException {

		DeviceUtils.killAllEpocs();

		// Setup PDD & cfg2xml version
		String lResource = "/resource/wintap/ETHERTAP.PDD.";
		String lCfg2XmlVersion = "93";
		if (TDConfig.getInstance().getPreference(TDConfig.KERNEL).indexOf("1") >= 0) {
			lCfg2XmlVersion = "8.1a";
			lResource += "81.eka1";
		} else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("8.1") >= 0) {
			lCfg2XmlVersion = "8.1a";
			lResource += "81.eka2";
		} else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("9.1") >= 0) {
			lCfg2XmlVersion = "9.1";
			lResource += "91";
		} else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("9.2") >= 0) {
			lCfg2XmlVersion = "92";
			lResource += "92plus";
		} else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("9.3") >= 0) {
			lCfg2XmlVersion = "93";
			lResource += "92plus";
		} else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("9.4") >= 0) {
			lCfg2XmlVersion = "94";
			lResource += "92plus";	
		}else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("vtb91")>=0){
			lCfg2XmlVersion = "94";
			lResource += "92plus";
		}else if (TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).indexOf("vtb92")>=0){
			lCfg2XmlVersion = "95";
			lResource += "95plus";
		}else {
			lCfg2XmlVersion = "95";
			lResource += "95plus";
		}

		// Backup Old Wintap PDD + Install WinTap PDD
		backupFile(iEthertapPddFile);
		extractResourceFile(lResource, iEthertapPddFile);

		// Backup Old Wintap CED
		LOGGER.info("Backup old CommDB file.");
		new ExecuteOnHost(iWinscwReleaseDir, "ceddump.exe").doTask(true, 0, false);

		File lWinTapCfgOld = new File(iWinscwCDir, "/cedout.cfg");
		if (!lWinTapCfgOld.isFile()) {
			throw new IOException("Could not dump orginal CommDB to a file");
		}

		// Install CommDat
		String lInputCommDb = TDConfig.getInstance().getPreference(TDConfig.COMMDB).toLowerCase();
		if (false && (lInputCommDb.equalsIgnoreCase("true") || lInputCommDb.equalsIgnoreCase("on"))) {
			addWintap(lCfg2XmlVersion);
		} else if (lInputCommDb.equalsIgnoreCase("overwrite")) {
			overwriteWintap();
		} else if (lInputCommDb.startsWith("overwrite") && lInputCommDb.indexOf("=") >= 0) {
			overwriteWintapWith(lInputCommDb);
		}

	}

	/**
	 * @param lCfg2XmlVersion
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	private void addWintap(String lCfg2XmlVersion) throws IOException, TimeLimitExceededException {
		// Convert Old backed up CFG to XML
		new ExecuteOnHost(iWinscwCDir, "cfg2xml.bat -mode:file " + lCfg2XmlVersion + " cedout.cfg").doTask(true, 0,
				false);

		File lWinTapXmlOld = new File(iWinscwCDir, "/cedout.xml");
		if (!lWinTapXmlOld.isFile()) {
			throw new IOException("Could not convert the original CommDB .cfg file to a .xml file bye cfg2xml utility.");
		}

		// Add WinTap to XML via XSLT
		String lWinTapXmlLiteral = "commdbWinTap.xml";
		XSLTUtils.transformXml(lWinTapXmlOld, iWinscwCDir, lWinTapXmlLiteral, "/resource/wintap/commdbwintap.xsl",
				EmulatorPreProcessor.class);

		// Install WinTap CED
		LOGGER.info("Installing new WinTAP CommDB file.");
		new ExecuteOnHost(iWinscwReleaseDir, "ced.exe -i c:\\" + lWinTapXmlLiteral).doTask(true, 0, false);
	}

	/**
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	private void overwriteWintap() throws IOException, TimeLimitExceededException {
		// Copy Default WinTAP CED to WINSCW C drive
		String lTDWinTap = "TdWinTap.xml";

		extractResourceFile("/resource/wintap/" + lTDWinTap, new File(iWinscwCDir, lTDWinTap));

		// Install WinTap CED
		LOGGER.info("Installing new WinTAP CommDB file.");
		new ExecuteOnHost(iWinscwReleaseDir, "ced.exe -i c:\\" + lTDWinTap).doTask(true, 0, false);
	}

	/**
	 * @param lInputCommDb
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	private void overwriteWintapWith(String lInputCommDb) throws IOException, TimeLimitExceededException {
		String[] lInput = lInputCommDb.split("=");
		if (lInput.length == 2) {
			File lNewCommDB = new File(lInput[1]);

			if (lNewCommDB.isFile()) {
				// Add WinTap to XML via XSLT
				LOGGER.info("Adding wintap to: " + lNewCommDB);
				String lWinTapXmlLiteral = "commdbWinTap.xml";
				XSLTUtils.transformXml(lNewCommDB, iWinscwCDir, lWinTapXmlLiteral, "/resource/wintap/commdbwintap.xsl",
						EmulatorPreProcessor.class);

				// Install WinTap CED
				LOGGER.info("Installing new WinTAP CommDB file.");
				new ExecuteOnHost(iWinscwReleaseDir, "ced.exe -i c:\\" + lWinTapXmlLiteral).doTask(true, 0, false);
			} else {
				LOGGER.severe("Could not find the file: " + lNewCommDB);
			}
		}
	}

	/**
	 * @param lSisFile
	 * @return All the files backed up for the emulator.
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 * @deprecated This needs to use the emulator ROM tool
	 */
	public static HashMap<File, File> backupEmulator(File lSisFile) throws IOException, FileNotFoundException {
		HashMap<File, File> lEmulatorHashBackup = new HashMap<File, File>();

		try {
			if (Epoc.isTargetEmulator(TDConfig.getInstance().getPreference(TDConfig.PLATFORM))) {
				File lPackageFile = new File(lSisFile.getParent() + File.separator + "testDriverPackage.pkg");
				if (!lPackageFile.isFile()) {
					throw new IOException("Could not find the Package file.");
				}

				BufferedReader lReposIn = new BufferedReader(new FileReader(lPackageFile));

				String lFileLoc = null;
				while ((lFileLoc = lReposIn.readLine()) != null) {
					String[] lSplitFileLoc = lFileLoc.split("\"-\"");

					if (lSplitFileLoc.length == 2) {
						String lSymbianPathLiteral = lSplitFileLoc[1].substring(0, lSplitFileLoc[1].length() - 1);

						File lEmulatorOrginal = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT)
								+ File.separator + ModelUtils.getEpoc32RelPlatVar() + File.separator
								+ new File(lSymbianPathLiteral).getName());
						File lEmulatorBackup = backupFile(lEmulatorOrginal);

						if (lEmulatorBackup != null) {
							lEmulatorHashBackup.put(lEmulatorOrginal, lEmulatorBackup);
						}
						
						//look into z drive as well

						lEmulatorOrginal = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT)
								+ File.separator + ModelUtils.getEpoc32RelPlatVar() + File.separator + "Z"
								+ lSymbianPathLiteral.substring(2).replace('\\', File.separatorChar));
						lEmulatorBackup = backupFile(lEmulatorOrginal);
						
						if (lEmulatorBackup != null) {
							lEmulatorHashBackup.put(lEmulatorOrginal, lEmulatorBackup);
						}

					}
				}
			}
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not backup emulator file due to configuration issue", lParseException);
		}
		return lEmulatorHashBackup;
	}

	/**
	 * @param lEmulatorHashBackup
	 * 
	 * @deprecated This needs to use the emulator ROM tool
	 */
	public static void restoreEmulator(HashMap<File, File> lEmulatorHashBackup) {
		if (lEmulatorHashBackup != null) {
			for (File lEmulatorOrginal : lEmulatorHashBackup.keySet()) {
				File lEmulatorBackup = lEmulatorHashBackup.get(lEmulatorOrginal);

				if (lEmulatorBackup != null && !lEmulatorBackup.renameTo(lEmulatorOrginal)) {
					LOGGER.fine("Could not restore Emulator backup of: " + lEmulatorBackup.toString() + " to "
							+ lEmulatorOrginal.toString());
				}
			}
		}
	}

	/**
	 * @param lFile
	 * @return The backed up file.
	 * @throws IOException
	 */
	public static final File backupFile(File lFile) throws IOException {
		if (lFile.isFile()) {

			LOGGER.info("Backup file: " + lFile);
			File lFileBackup = new File(lFile.getCanonicalPath() + BACKUP);

			if (!lFile.renameTo(lFileBackup)) {

				LOGGER.warning("Overwriting previous backup file at: " + lFileBackup + "; with: " + lFile);
				lFileBackup.delete();

				if (!lFile.renameTo(lFileBackup)) {
					throw new IOException("Could not backup old file: " + lFile);
				}
			}

			return lFileBackup;
		}

		return null;
	}

	/**
	 * @param aResource
	 * @param aOldFile
	 * @return The newly installed file.
	 * @throws IOException
	 */
	public static final File extractResourceFile(String aResource, File aOldFile) throws IOException {
		File lNewFile = JarUtils.extractResource(EmulatorPreProcessor.class, aResource);
		LOGGER.info("Installing new file: " + lNewFile);
		Utils.copy(lNewFile, aOldFile);

		return lNewFile;
	}
}
