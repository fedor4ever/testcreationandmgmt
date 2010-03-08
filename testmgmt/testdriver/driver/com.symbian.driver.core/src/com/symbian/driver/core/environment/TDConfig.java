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

package com.symbian.driver.core.environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;

import com.symbian.driver.core.extension.IConfig;
import com.symbian.utils.Epoc;
import com.symbian.utils.Utils;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.config.CheckGetConfig;
import com.symbian.utils.config.CheckSetConfig;
import com.symbian.utils.config.ConfigUtils;

/**
 * @author EngineeringTools
 * 
 */
public class TDConfig extends ConfigUtils {

	// Internal Variables
	/** Testdriver prefix. */
	private static final String CONFIG_ID = "/com/symbian/driver";

	/** Generic Logger. */
	private static final Logger LOGGER = Logger.getLogger(TDConfig.class.getName());

	/** Singleton reference to TestDriverConfig. */
	private static TDConfig sConfig;

	/** TEF Lite Dependencies. */
	private static final String TEFLITE_DEPENDENCIES_DEFAULT = "TestExecuteLite.exe;TestExecuteLogClient.dll;TestExecuteLogEngine.exe;TestExecuteUtils.dll;EventLogServer.exe";
	
	/** TEF Default Dependencies. */
	private static final String TEF_DEPENDENCIES_DEFAULT = "TestExecute.exe;TestExecuteLogClient.dll;TestExecuteLogEngine.exe;TestExecuteUtils.dll;EventLogServer.exe";

	/** TEF Default Dependencies for EKA2. */
	public static final String TEF_DEPENDENCIES_EKA2 = "TestExecute.ini;RFileLoggerClient.dll;RFileLoggerServer.exe;testexecutepipslogclient.dll;wrapperutilsplugin1.dll;wrapperutilsplugin5.dll";

	
	/** TEF Default Dependencies for EKA2. */
	public static final String TEF_DEPENDENCIES_OPT_EKA2 = "IniParser.dll\tif NOT exists(\"z:\\sys\\bin\\IniParser.dll\") AND NOT exists(\"c:\\sys\\bin\\IniParser.dll\")";

	/** TEF device location of HTML results. */
	private String iTefHtmlResultLocation;

	/** TEF device location of XML resultss. */
	private String iTefXmlResultLocation;

	/** TEF device logging channel chosen. */
	private String iTefLogChannel;

	// KEYS
	/** Run bldmake.bat before a build command. Must be of type BOOLEAN. */
	public static final int BLDMAKE = 0;

	/**
	 * The Symbian OS Build Number in the format {CBRNum}_Symbian_OS_v{X}.{X}.
	 * Must be of type STRING.
	 */
	public static final int BUILD_NUMBER = 1;

	/** Run clean on bldmake and abld. Must be of type BOOLEAN. */
	public static final int CLEAN = 2;

	/** Client name. Must be of type STRING. */
	public static final int CLIENT = 3;

	/** The entry point address for TestDriver to run. This must be of type URI. */
	public static final int ENTRY_POINT_ADDRESS = 4;

	/** The epoc root of TestDriver. This must be of type FILE. */
	public static final int EPOC_ROOT = 5;

	/** The Job ID for TestDriver Remote. This must be of type INTEGER. */
	public static final int JOB_ID = 6;

	/** The Jobs folder for the master side. This must be of type File. */
	public static final int JOBS_FOLDER = 7;

	/** The Kernel type: EKA1 or EKA2. This must be of type STRING */
	public static final int KERNEL = 8;

	/**
	 * Synchronisation mode for remote execution: sync/async. This must be of
	 * type STRING.
	 */
	public static final int MODE = 9;

	/** Default platform: THUMB, ARMv5, ARM4, . This must be of type STRING. */
	public static final int PLATFORM = 10;

	/** Platform Security (PlatSec). This must be of type BOOLEAN. */
	public static final int PLATSEC = 11;

	/** RDebug. This must be of type STRING */
	public static final int RDEBUG = 12;

	/** Repository Root of TestDriver. This must be of type FILE. */
	public static final int REPOSITORY_ROOT = 13;

	/** Result Root of TestDriver. This must be of type FILE. */
	public static final int RESULT_ROOT = 14;

	/** Rom File name. This must be of type FILE. */
	public static final int ROM = 15;

	/** The current run number. This must be of type INTEGER. */
	public static final int RUN_NUMBER = 16;

	/**
	 * Server name and service used for TestDriver Remote. This must be of type
	 * STRING.
	 */
	public static final int SERVER = 17;

	/** Server name used for TestDriver Remote. This must be of type STRING. */
	public static final int SERVER_NAME = 18;

	/** The remote service name. This must be of type STRING. */
	public static final int SERVICE = 19;

	/**
	 * If you install to the sis/bin folder irrespective of platsec. Used for
	 * STATLite. This must be of type BOOLEAN.
	 */
	public static final int SYS_BIN = 20;

	/** Key for Source Root of TestDriver. This must be of type FILE. */
	public static final int SOURCE_ROOT = 21;

	/** The location of the program TrgTest.exe. This must be of type INTEGER. */
	public static final int TARGET_TEST = 22;

	/** If TEF Dependencies need to be installed. This must be of type BOOLEAN. */
	public static final int TEST_EXECUTE = 23;

	/** TEF Dependencies. This must be of type STRING. */
	public static final int TEST_EXECUTE_DEPENDENCIES = 24;

	/** Test Pacakge name. This must be of type FILE. */
	public static final int TEST_PACKAGE = 25;

	/** Default Transport: serial1, usb, bt, etc. This must be of type STRING. */
	public static final int TRANSPORT = 26;

	/** UCC Address for syncronised testing. This must be of type STRING. */
	public static final int UCC_IP_ADDRESS = 27;

	/** Default value for Variant: udeb or urel. This must be of type STRING. */
	public static final int VARIANT = 28;

	/** Wintap Address for Emulator Run. This must be of type STRING. */
	public static final int WINTAP = 29;

	/** Working Path of TestDriver. This must be of type FILE. */
	public static final int WORKING_PATH = 30;

	/** XML Root of TestDriver. This must be of type FILE. */
	public static final int XML_ROOT = 31;

	/**
	 * If CommDB needs to be installed with the WinTap Emulator. This must be of
	 * type BOOLEAN.
	 */
	public static final int COMMDB = 32;

	/** The first UID to use for TestDriver. This must be of type STRING. */
	public static final int UIDFIRST = 33;

	/** The last UID to use for TestDriver. This must be of type STRING. */
	public static final int UIDLAST = 34;

	/** The location of the Key. This must be of type STRING. */
	public static final int KEY = 35;

	/** The location of the Certificate. This must be of type STRING. */
	public static final int CERT = 36;

	/** The timeout for STAT. This must be of type INT. */
	public static final int TOTAL_TIMEOUT = 37;

	/** COM port to be used to listen for TEF logs . This must be of type string. */
	public static final int TEFPORT = 38;

	/** Communicatio plagin ID */
	public static final int COMM_PLUGIN = 39;
	
	private File CERT_PASSWORD = null;
	
	public static final int SIGN_ALGORITHM = 40;

	public static final String ENV_EPOCROOT = "EPOCROOT";
	public static final int TEF_LITE = 41;
    public static final int RECOVERY = 42;   
    /** test building */
    public static final int TEST_BUILD =43; 
    /** device driver */
    public static final int SIS_ROOT=44;
    public static final int SBS = 45;

	public enum CnfKeys {

		BLDMAKE("bldmake"), BUILD_NUMBER("buildNumber"), CLEAN("clean"), CLIENT("client");

		CnfKeys(String aValue) {
		}
	}

	// /** Key array. */
	// public static final int[] PREFERENCE_KEYS = { BLDMAKE, BUILD_NUMBER,
	// CLEAN, CLIENT, ENTRY_POINT_ADDRESS, EPOC_ROOT, JOB_ID, JOBS_FOLDER,
	// KERNEL, MODE,
	// PLATFORM, PLATSEC, RDEBUG, REPOSITORY_ROOT, RESULT_ROOT, ROM, RUN_NUMBER,
	// SERVER, SERVER_NAME, SERVICE, SYS_BIN, SOURCE_ROOT, TARGET_TEST,
	// TEST_EXECUTE, TEST_EXECUTE_DEPENDENCIES, TEST_PACKAGE, TRANSPORT,
	// UCC_IP_ADDRESS, VARIANT, WINTAP, WORKING_PATH, XML_ROOT, COMMDB,
	// UIDFIRST,
	// UIDLAST, KEY, CERT, STAT_TIMEOUT ,TEFPORT };

	private static final String[] PREFERENCE_LITERALS = { "bldmake", "buildNumber", "clean", "client",
			"entryPointAddress", "epocRoot", "jobID", "jobsfolder", "kernel", "mode", "platform", "platsec", "rdebug",
			"repositoryRoot", "resultRoot", "rom", "runNumber", "server", "server_name", "service", "sysbin",
			"sourceRoot", "trgtest", "testExecute", "testExecuteDependencies", "testPackage", "transport",
			"uccAddress", "variant", "wintap", "workingPath", "xmlRoot", "commDB", "uidFirst", "uidLast", "key",
			"cert", "testtimelimit", "tefport", "commplugin", "sigalgorithm", "teflite", "recovery","testbuild","sisroot","sbs"};

	/**
	 * 
	 * @return An instance of sConfig.
	 */
	public static TDConfig getInstance() {
		if (sConfig == null) {
			try {
				sConfig = new TDConfig();
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Failed to create a configuration", lIOException);
			}
		}

		return sConfig;
	}

	public static TDConfig newInstance() {
		try {
			sConfig = new TDConfig();
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Failed to create a configuration", lIOException);
		}

		return sConfig;
	}

	
	/**
	 * @throws IOException
	 */
	private TDConfig() throws IOException {
		super(CONFIG_ID, PREFERENCE_LITERALS);
        setupConfig();
	}

	
	private void setupConfig() throws IOException {

		// If a generic setter/getter is adequte
		CheckGetConfig lGenericGetCheck = new CheckGetConfig();
		CheckSetConfig lGenericSetCheck = new CheckSetConfig();

		// Configuration sorted Alphabetically

		// ////////////
		// Bldmake.bat
		addConfig(BLDMAKE, Boolean.toString(true), lGenericGetCheck, lGenericSetCheck, Boolean.class);

		// ////////////
		// Clean.bat
		addConfig(CLEAN, Boolean.toString(true), lGenericGetCheck, lGenericSetCheck, Boolean.class);

		// ////////////
		// Client
		addConfig(CLIENT, "localhost", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Entry Point Root Address
		addConfig(ENTRY_POINT_ADDRESS, "", new CheckGetConfig() {

			public Object get(String aKey, Object aValue) {
				if (aValue == null || ((String) aValue).equalsIgnoreCase("")) {
					throw new NullPointerException(
							"The suite address is null. Please use -s to specify a correct suite address");
				}

				URI lRootAddress = URI.createURI((String) aValue);
				//  check validity of URI
				return lRootAddress.toString();
			}

		}, lGenericSetCheck, String.class);

		// ////////////
		// Epoc Root
		addConfig(EPOC_ROOT, "c:", new CheckGetConfig() {

			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException("EpocRoot is null.");
				}

				File lEpocRoot = (File) aValue;
				if (!Epoc.isEpocrootValid(lEpocRoot)) {
					throw new ParseException("Epocroot is invalid: " + aValue);
				}
				return lEpocRoot;
			}

		}, lGenericSetCheck, File.class);

		//try to figure out epoc root from system env if the current setting is wrong
		try {
			File epoc = getPreferenceFile(EPOC_ROOT);
			if (!Epoc.isEpocrootValid(epoc)) {
				//try to figure out epoc root from system env
				LOGGER.info("the epocroot in configuration is wrong");
			}
			
		} catch (ParseException e) {
			String epocRoot = System.getenv(ENV_EPOCROOT);
			if (epocRoot != null && !epocRoot.equals("")) {
				if (epocRoot.equals("\\")) {
					String workDir = System.getProperty("user.dir");
					if (workDir.indexOf(":") > 0) {
						epocRoot = workDir.substring(0, workDir.indexOf(":")+1) + epocRoot;
					}
				}
				File epoc = new File(epocRoot);
				if (Epoc.isEpocrootValid(epoc)) {
					try {
					    setPreferenceFile(EPOC_ROOT, epoc);
					} catch (Exception pe) {
					}
					LOGGER.info("use system env EPOC_ROOT :" + epocRoot);
				}
			}
		}
		
		// ////////////
		// Build Number
		addConfig(BUILD_NUMBER, "0", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					String lBuildNumber = Utils.getBuildNumber(getPreferenceFile(EPOC_ROOT));

					if (lBuildNumber == null) {
						throw new ParseException("Could not get the build number from the EpocRoot: "
								+ getPreferenceFile(EPOC_ROOT));
					}

					return lBuildNumber;
				}

				return super.set(aKey, aValue);
			}

		}, String.class);
		

		// ////////////
		// Job ID
		addConfig(JOB_ID, "0", lGenericGetCheck, lGenericSetCheck, Integer.class);

		// ////////////
		// Server Jobs folder
		addConfig(JOBS_FOLDER, "c:" + File.separator + "Jobs", new CheckGetConfig() {
			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lJobsFolder = (File) aValue;
				if (!lJobsFolder.isDirectory() && !lJobsFolder.mkdirs()) {
					throw new ParseException("Remote Jobs Folder is invalid: " + aValue);
				}
				return lJobsFolder;
			}
		}, lGenericSetCheck, File.class);

		// ////////////
		// Kernel
		addConfig(KERNEL, "EKA2", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				if (!Epoc.isKernelValid((String) aValue)) {
					throw new ParseException("Kernel is invalid: " + aValue);
				}
				return aValue;
			}

		}, String.class);

		// ////////////
		// Mode
		addConfig(MODE, "sync", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				if (!Epoc.isModeValid((String) aValue)) {
					throw new ParseException("Synchronisation mode is invalid: " + aValue);
				}
				return aValue;
			}

		}, String.class);

		// ////////////
		// Platform
		addConfig(PLATFORM, "armv5", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				if (!Epoc.isTargetValid((String) aValue)) {
					throw new ParseException("Platform is invalid: " + aValue);
				}
				return aValue;
			}

		}, String.class);

		// ////////////
		// Platform Security
		addConfig(PLATSEC, Boolean.toString(true), lGenericGetCheck, lGenericSetCheck, Boolean.class);

		// ////////////
		// RDEBUG
		addConfig(RDEBUG, "log", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Repository
		addConfig(REPOSITORY_ROOT, new File("./repository").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lRepositoryRoot = (File) aValue;
				if (!lRepositoryRoot.isDirectory() && !lRepositoryRoot.mkdirs()) {
					throw new ParseException("Repository Root is invalid: " + aValue);
				}
				return lRepositoryRoot;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// Result Root
		addConfig(RESULT_ROOT, new File("./results").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lResultRoot = (File) aValue;
				if (!lResultRoot.isDirectory() && !lResultRoot.mkdirs()) {
					throw new ParseException("Result Root is invalid: " + aValue);
				}
				return lResultRoot;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// ROM
		addConfig(ROM, "", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Run Number
		addConfig(RUN_NUMBER, "0", lGenericGetCheck, lGenericSetCheck, Integer.class);

		// ////////////
		// Server + service name (client)
		addConfig(SERVER, "//localhost/RemoteTestDriver", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Server name (master)
		addConfig(SERVER_NAME, "localhost", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// remote Service name (master)
		addConfig(SERVICE, "RemoteTestDriver", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Sys Bin
		addConfig(SYS_BIN, Boolean.toString(false), lGenericGetCheck, lGenericSetCheck, Boolean.class);

		// ////////////
		// Source Root
		addConfig(SOURCE_ROOT, new File(".").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lSourceRoot = (File) aValue;
				/*
				 * if (!lSourceRoot.isDirectory()) { throw new
				 * ParseException("Source Root is invalid: " + aValue); }
				 */
				return lSourceRoot;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// Target Test Port
		addConfig(TARGET_TEST, "0", lGenericGetCheck, lGenericSetCheck, Integer.class);

		// ////////////
		// TEF
		addConfig(TEST_EXECUTE, Boolean.toString(true), lGenericGetCheck, lGenericSetCheck, Boolean.class);

		// ////////////
		// TEF Dependencies
		// addConfig(TEST_EXECUTE_DEPENDENCIES, TEF_DEPENDENCIES_DEFAULT,
		// lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Test Package
		addConfig(TEST_PACKAGE, new File("./test.pkg").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lTestPackage = (File) aValue;
				return lTestPackage;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// Transport
		addConfig(TRANSPORT, "serial1", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				if (!Epoc.isTransportValid((String) aValue)) {
					throw new ParseException("Transport is invalid: " + aValue);
				}

				String[] lAddress = ((String) aValue).split(":");
				if (lAddress.length == 2 && lAddress[0].startsWith("tcp")) {
					return ((String) aValue).concat(":3000");
				}

				return aValue;
			}

		}, String.class);

		// ////////////
		// UCC IP address
		addConfig(UCC_IP_ADDRESS, "192.168.0.3", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Variant
		addConfig(VARIANT, "udeb", lGenericGetCheck, new CheckSetConfig() {

			public Object set(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				if (!Epoc.isVariantValid((String) aValue)) {
					throw new ParseException("Variant is invalid: " + aValue);
				}
				return aValue;
			}

		}, String.class);

		// ////////////
		// Wintap
		addConfig(WINTAP, "192.168.0.3", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Working Path
		addConfig(WORKING_PATH, new File(".").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lWorkingPath = (File) aValue;
				if (!lWorkingPath.isDirectory()) {
					throw new ParseException("Working path is invalid: " + aValue);
				}
				return lWorkingPath;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// XML Root
		addConfig(XML_ROOT, new File(".").getAbsolutePath(), new CheckGetConfig() {

			public Object get(String aKey, Object aValue) throws ParseException {
				if (aValue == null) {
					throw new NullPointerException(aKey + " is null.");
				}

				File lXmlRoot = (File) aValue;
				if (!lXmlRoot.isDirectory()) {
					throw new ParseException("XML Root is invalid: " + aValue);
				}
				return lXmlRoot;
			}

		}, lGenericSetCheck, File.class);

		// ////////////
		// CommDB with WinTAP for Emulator
		addConfig(COMMDB, "off", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// First UID
		addConfig(UIDFIRST, "0x10210D02", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Last UID
		addConfig(UIDLAST, "0x10210D32", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Key Location
		addConfig(KEY, "", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// Certificate Location
		addConfig(CERT, "", lGenericGetCheck, lGenericSetCheck, String.class);

		// ////////////
		// STAT Timeout
		addConfig(TOTAL_TIMEOUT, Integer.toString(TimeOut.TOTAL_TIMEOUT), lGenericGetCheck, lGenericSetCheck, Integer.class);

		// //////////
		// TEF PORT
		addConfig(TEFPORT, "", lGenericGetCheck, lGenericSetCheck, String.class);

		// //////////
		// Communication Plugin
		addConfig(COMM_PLUGIN, "com.symbian.driver.plugins.comms.stat", lGenericGetCheck, lGenericSetCheck,
				String.class);
		
		addConfig(SIGN_ALGORITHM, "RSA", lGenericGetCheck, lGenericSetCheck, String.class);

		addConfig(TEF_LITE, "auto", lGenericGetCheck, lGenericSetCheck, String.class);
		
		//Crash recovery
		addConfig(RECOVERY, "off", lGenericGetCheck, lGenericSetCheck, String.class);
		
		// Force test build 
		addConfig(TEST_BUILD,"auto",lGenericGetCheck, lGenericSetCheck, String.class);
		
		// add sis path
		addConfig(SIS_ROOT,"c:",lGenericGetCheck,lGenericSetCheck, String.class);
		
		// sbs 
		addConfig(SBS, "v1", lGenericGetCheck, lGenericSetCheck, String.class);
		
		super.completeConfigFromStore();

		try {
			setPreference(BUILD_NUMBER, null);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "First get build number failed", lParseException);
		}
	}
	
	/**
	 * If the plugins have not been initiated, then we must initiate them to
	 * have their settings added to the config.
	 * 
	 */
	public void completeConfigFromPlugins() {
		// get extensions registry
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (null == registry) {
			LOGGER.log(Level.SEVERE, "registry returned null, Could not initialise config");
		} else {
			// registry non empty
			// check extension point for config is registered
			IExtensionPoint lConfigEP = registry.getExtensionPoint("com.symbian.driver.core.TDConfig");
			if (lConfigEP == null) {
				LOGGER.fine("There are no TD Config extensions.");
			} else {
				LOGGER.fine("TDConfig EP found");
				// TDConfig EP registered
				// get all extensions for it
				IExtension[] lConfigExt = lConfigEP.getExtensions();
				for (IExtension lExt : lConfigExt) {
					//LOGGER.info("Extension = " + lExt.getUniqueIdentifier() + " --- " + lExt.getLabel() + " ---- " + lExt.getExtensionPointUniqueIdentifier());
					IConfigurationElement[] lConfigurationElements = lExt.getConfigurationElements();
					for (IConfigurationElement element : lConfigurationElements) {
						String lClass = element.getAttribute("class");
						if (lClass != null) {
							try {
								element.createExecutableExtension("class");
							} catch (CoreException e) {
								LOGGER.log(Level.SEVERE, "Could set the value to config." + e.getMessage());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @return The result file location
	 */
	public String getResultHtmlFileLocation() {
		if (iTefHtmlResultLocation == null) {
			parseIniFile();
		}

		return iTefHtmlResultLocation;
	}

	/**
	 * @return The result logging channel
	 */
	public String getLogChannel() {
		if (iTefLogChannel == null) {
			parseIniFile();
		}
		return iTefLogChannel;
	}

	/**
	 * @return The result file location
	 */
	public String getResultXmlFileLocation() {
		if (iTefXmlResultLocation == null) {
			parseIniFile();
		}

		return iTefXmlResultLocation;
	}

	/**
	 * Increments the run number by 1.
	 * 
	 * @throws ParseException
	 */
	public void incrementRunNumber() throws ParseException {
		this.setPreferenceInteger(RUN_NUMBER, this.getPreferenceInteger(RUN_NUMBER) + 1);
	}

	/**
	 * @throws
	 * @throws IOException
	 * @throws ParseException
	 * @throws IOException
	 */
	private void parseIniFile() {
		// Reset XML and HTML results roots.
		// Get the TEF ini result file location
		boolean lMatchNotFound = true;

		try {
			File lTEFIni = null;
			String LEpocRoot = getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath();
			if (Epoc.isTargetEmulator(TDConfig.getInstance().getPreference(TDConfig.PLATFORM))) {
				// pick the ini file from
				// ${epocroot}epoc32\winscw\c\system\data\testexecute.ini
				lTEFIni = new File((LEpocRoot + File.separator + ILiterals.EPOC32 + File.separator + Epoc.WINSCW
						+ File.separator + "c" + File.separator + ILiterals.SYSTEM + File.separator + ILiterals.DATA
						+ File.separator + "testexecute.ini").replaceAll("\\\\+", "\\\\"));

			} else {
				// pick the ini file from
				// ${EPOCROOT}epoc32\data\Z\system\data\testexecute.ini
				lTEFIni = new File((LEpocRoot + File.separator + ILiterals.EPOC32 + File.separator + ILiterals.DATA
						+ File.separator + "z" + File.separator + ILiterals.SYSTEM + File.separator + ILiterals.DATA
						+ File.separator + "testexecute.ini").replaceAll("\\\\+", "\\\\"));
			}

			if (lTEFIni.isFile() && lTEFIni.canRead()) {
				BufferedReader lBufferedReader = new BufferedReader(new FileReader(lTEFIni));
				Pattern lPattern = Pattern.compile("\\s*(\\S*)\\s*=\\s*(\\S*)\\s*");
				String lOutput = null;

				boolean lHtml = false;
				boolean lXml = false;

				while ((lOutput = lBufferedReader.readLine()) != null) {
					Matcher lMatcher = lPattern.matcher(lOutput);

					if (lMatcher.find()) {
						if (lMatcher.group(1).equalsIgnoreCase("html")) {
							iTefHtmlResultLocation = lMatcher.group(2);
						} else if (lMatcher.group(1).equalsIgnoreCase("xml")) {
							iTefXmlResultLocation = lMatcher.group(2);
						} else if (lMatcher.group(1).equalsIgnoreCase("logmode")) {
							if (lMatcher.group(2).equalsIgnoreCase("html")) {
								lHtml = true;
							} else if (lMatcher.group(2).equalsIgnoreCase("xml")) {
								lXml = true;
							} else if (lMatcher.group(2).equalsIgnoreCase("both")) {
								lHtml = true;
								lXml = true;
							}
						} else if (lMatcher.group(1).equalsIgnoreCase("logchannel")) {
							iTefLogChannel = lMatcher.group(2);
						}

						lMatchNotFound = false;
					}
				}

				if (!lHtml) {
					iTefHtmlResultLocation = null;
				}
				if (!lXml) {
					iTefXmlResultLocation = null;
				}

				lBufferedReader.close();
			}

		} catch (IOException lException) {
			LOGGER.log(Level.WARNING, "IO problems encountered while trying to parse TestExecute.ini", lException);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Configuration problems encountered while trying to parse TestExecute.ini",
					lParseException);
		} finally {
			if (lMatchNotFound) {
				iTefHtmlResultLocation = ILiterals.C + "\\Logs\\TestExecute\\";
				iTefXmlResultLocation = null;
			}
		}
	}

	/**
	 * @param aCmd
	 */
	public void setConfigFromCmdLine(CmdLine aCmd) {
		try {
			// config
			if (aCmd.getCommandLine().hasOption("conf")) {
				
				String configFile = aCmd.getCommandLine().getOptionValue("conf");
				//load preference here
				File cfgFile = new File(configFile);
				if (cfgFile.exists()) {
					this.importConfig(cfgFile);
					//setup cofig again according to config file
					setupConfig();
					LOGGER.log(Level.INFO, "loaded configuration file:" + configFile);
				} else {
					LOGGER.log(Level.SEVERE, "Configuration file doesn't exists:" + configFile);
				}
			}
			
			// This NEEDS to be first because of Build Number relies on it.
			// Epoc Root			
			if (aCmd.getCommandLine().hasOption("e")) {
				this.setPreferenceFile(EPOC_ROOT, new File(aCmd.getCommandLine().getOptionValue("e"))
						.getCanonicalFile());
			}

			// Variant
			if (aCmd.getCommandLine().hasOption("b")) {
				this.setPreference(VARIANT, aCmd.getCommandLine().getOptionValue("b"));
			}

			// Platform
			if (aCmd.getCommandLine().hasOption("p")) {
				this.setPreference(PLATFORM, aCmd.getCommandLine().getOptionValue("p"));
			}

			// PlatSec
			if (aCmd.getCommandLine().hasOption("platsec")) {
				String lPlatsec = aCmd.getCommandLine().getOptionValue("platsec");
				this.setPreferenceBoolean(PLATSEC, (lPlatsec.equalsIgnoreCase("true") || lPlatsec
						.equalsIgnoreCase("on")) ? true : false);
			}

			// EntryPointAddress
			if (aCmd.getCommandLine().hasOption("s")) {
				String lRootAddress = aCmd.getCommandLine().getOptionValue("s");
				URI lRootURI = null;
				if (lRootAddress.indexOf(".driver") < 0) {
					lRootURI = URI.createURI("#" + lRootAddress);
				} else {
					lRootURI = URI.createURI(lRootAddress);
				}

				this.setPreferenceURI(ENTRY_POINT_ADDRESS, lRootURI);
			}

			// UCC IP Address
			if (aCmd.getCommandLine().hasOption("f")) {
				this.setPreference(UCC_IP_ADDRESS, aCmd.getCommandLine().getOptionValue("f"));
			} else {
				this.setPreference(UCC_IP_ADDRESS, "");
			}

			// Transport
			if (aCmd.getCommandLine().hasOption("t")) {
				// IP
				if (aCmd.getCommandLine().hasOption("ip")) {
					this.setPreference(TRANSPORT, "tcp:" + aCmd.getCommandLine().getOptionValue("ip"));
				} else {
					this.setPreference(TRANSPORT, aCmd.getCommandLine().getOptionValue("t"));
				}
			}

			// Test Package
			if (aCmd.getCommandLine().hasOption("tp")) {
				this.setPreferenceFile(TEST_PACKAGE, new File(aCmd.getCommandLine().getOptionValue("tp"))
						.getCanonicalFile());
			}

			// SYS/BIN
			if (aCmd.getCommandLine().hasOption("sysbin")) {
				String lSysBin = aCmd.getCommandLine().getOptionValue("sysbin");
				this.setPreferenceBoolean(SYS_BIN,
						(lSysBin.equalsIgnoreCase("true") || lSysBin.equalsIgnoreCase("on")) ? true : false);
			}

			// statLite.
			// "statLite" will depreciate "sysbin", so put this after sysbin.
			if (aCmd.getCommandLine().hasOption("statlite")) {
				String lStatLite = aCmd.getCommandLine().getOptionValue("statlite");
				this.setPreferenceBoolean(SYS_BIN,
						(lStatLite.equalsIgnoreCase("true") || lStatLite.equalsIgnoreCase("on")) ? true : false);
			}
			
			if (aCmd.getCommandLine().hasOption("teflite")) {
				String ltefLite = aCmd.getCommandLine().getOptionValue("teflite");
				if (!ltefLite.equalsIgnoreCase("true") && 
					!ltefLite.equalsIgnoreCase("false") &&
					!ltefLite.equalsIgnoreCase("on") &&
					!ltefLite.equalsIgnoreCase("off") &&
					!ltefLite.equalsIgnoreCase("auto")) {
					LOGGER.log(Level.SEVERE, "tetLite can only be true/false/auto. Defaulting to auto");
				} else {
					this.setPreference(TEF_LITE, ltefLite.toUpperCase());
				}
			}
			
			// Server
			if (aCmd.getCommandLine().hasOption("srv")) {
				this.setPreference(SERVER, aCmd.getCommandLine().getOptionValue("srv"));
			}

			// Job ID
			if (aCmd.getCommandLine().hasOption("j")) {
				this.setPreferenceInteger(JOB_ID, Integer.parseInt(aCmd.getCommandLine().getOptionValue("j")));
			}

			// Working Path
			if (aCmd.getCommandLine().hasOption("i")) {
				this.setPreferenceFile(WORKING_PATH, new File(aCmd.getCommandLine().getOptionValue("i"))
						.getCanonicalFile());
			}

			// Client
			if (aCmd.getCommandLine().hasOption("cl")) {
				this.setPreference(CLIENT, aCmd.getCommandLine().getOptionValue("cl"));
			}

			// ROM
			if (aCmd.getCommandLine().hasOption("r")) {
				this.setPreference(ROM, aCmd.getCommandLine().getOptionValue("r"));
			}

			// Mode
			if (aCmd.getCommandLine().hasOption("m")) {
				this.setPreference(MODE, aCmd.getCommandLine().getOptionValue("m"));
			}

			// Build Number
			if (aCmd.getCommandLine().hasOption("buildNumber")) {
				this.setPreference(BUILD_NUMBER, aCmd.getCommandLine().getOptionValue("buildNumber"));
			}

			// RDebug
			if (aCmd.getCommandLine().hasOption("l")) {
				this.setPreference(RDEBUG, aCmd.getCommandLine().getOptionValue("l"));
			} else {
				this.setPreference(RDEBUG, "");
			}

			// Result Root
			if (aCmd.getCommandLine().hasOption("c")) {
				this.setPreferenceFile(RESULT_ROOT, new File(aCmd.getCommandLine().getOptionValue("c"))
						.getCanonicalFile());
			}

			// XML Root
			if (aCmd.getCommandLine().hasOption("x")) {
				this
						.setPreferenceFile(XML_ROOT, new File(aCmd.getCommandLine().getOptionValue("x"))
								.getCanonicalFile());
			}

			// Repository Root
			if (aCmd.getCommandLine().hasOption("repos")) {
				this.setPreferenceFile(REPOSITORY_ROOT, new File(aCmd.getCommandLine().getOptionValue("repos"))
						.getCanonicalFile());
			}

			// Source Root
			if (aCmd.getCommandLine().hasOption("source")) {
				this.setPreferenceFile(SOURCE_ROOT, new File(aCmd.getCommandLine().getOptionValue("source"))
						.getCanonicalFile());
			}

			// BldMake
			if (aCmd.getCommandLine().hasOption("bldmake")) {
				String lBldmake = aCmd.getCommandLine().getOptionValue("bldmake");
				this.setPreferenceBoolean(BLDMAKE, (lBldmake.equalsIgnoreCase("true") || lBldmake
						.equalsIgnoreCase("on")) ? true : false);
			}

			// Clean
			if (aCmd.getCommandLine().hasOption("bldclean")) {
				String lClean = aCmd.getCommandLine().getOptionValue("bldclean");
				this.setPreferenceBoolean(CLEAN,
						(lClean.equalsIgnoreCase("true") || lClean.equalsIgnoreCase("on")) ? true : false);
			}

			// TestExecute
			if (aCmd.getCommandLine().hasOption("testexec")) {
				String lTestexecute = aCmd.getCommandLine().getOptionValue("testexec");
				this.setPreferenceBoolean(TEST_EXECUTE, (lTestexecute.equalsIgnoreCase("true") || lTestexecute
						.equalsIgnoreCase("on")) ? true : false);
			}

			// remote host
			if (aCmd.getCommandLine().hasOption("server")) {
				this.setPreference(SERVER_NAME, aCmd.getCommandLine().getOptionValue("server"));
			}

			// remote service
			if (aCmd.getCommandLine().hasOption("service")) {
				this.setPreference(SERVICE, aCmd.getCommandLine().getOptionValue("service"));
			}

			// remote jobs folder
			if (aCmd.getCommandLine().hasOption("jobs")) {
				this.setPreferenceFile(JOBS_FOLDER, new File(aCmd.getCommandLine().getOptionValue("jobs"))
						.getCanonicalFile());
			}

			// remote board flusing port
			if (aCmd.getCommandLine().hasOption("port")) {
				this.setPreferenceInteger(TARGET_TEST, Integer.parseInt(aCmd.getCommandLine().getOptionValue("port")));
			}

			// Import
			if (aCmd.getCommandLine().hasOption("import")) {
				this.importConfig(new File(aCmd.getCommandLine().getOptionValue("import")));
				setupConfig();
			}

			// Export
			if (aCmd.getCommandLine().hasOption("export")) {
				this.exportConfig(new File(aCmd.getCommandLine().getOptionValue("export")));
			}

			// Clear
			if (aCmd.getCommandLine().hasOption("clear")) {
				this.clearConfig();
			}

			// CommDB for wintap
			if (aCmd.getCommandLine().hasOption("commdb")) {
				this.setPreference(COMMDB, aCmd.getCommandLine().getOptionValue("commdb"));
			}

			// Crash recovery support
			if (aCmd.getCommandLine().hasOption("recovery")) {
				this.setPreference(RECOVERY, aCmd.getCommandLine().getOptionValue("recovery"));
			}

			// UID's
			if (aCmd.getCommandLine().hasOption("uid")) {
				String[] lUids = aCmd.getCommandLine().getOptionValue("uid").split(":");
				if (lUids.length == 2 && lUids[0].length() == 10 && lUids[0].startsWith("0x")
						&& lUids[1].length() == 10 && lUids[1].startsWith("0x")) {
					this.setPreference(UIDFIRST, lUids[0]);
					this.setPreference(UIDLAST, lUids[1]);
				} else {
					LOGGER.log(Level.WARNING, "Could not set the UID with: "
							+ aCmd.getCommandLine().getOptionValue("uid"));
				}
			}

			// Key Location
			if (aCmd.getCommandLine().hasOption("key")) {
				this.setPreference(KEY, aCmd.getCommandLine().getOptionValue("key"));
			}

			// Certificate Location
			if (aCmd.getCommandLine().hasOption("cert")) {
				this.setPreference(CERT, aCmd.getCommandLine().getOptionValue("cert"));
			}

			// STAT Timeout
			if (aCmd.getCommandLine().hasOption("testtimelimit")) {
				this.setPreferenceInteger(TOTAL_TIMEOUT, Integer.parseInt(aCmd.getCommandLine().getOptionValue(
						"testtimelimit")));
			}
			// testbuild 
			if (aCmd.getCommandLine().hasOption("testbuild")) {
				this.setPreference(TEST_BUILD, aCmd.getCommandLine().getOptionValue("testbuild"));
			}
			// sbs version 
			if (aCmd.getCommandLine().hasOption("sbs")) {
				this.setPreference(SBS, aCmd.getCommandLine().getOptionValue("sbs"));
			}
			
			// Tef channel
			if (aCmd.getCommandLine().hasOption("tefport")) {
				this.setPreference(TEFPORT, aCmd.getCommandLine().getOptionValue("tefport"));
			}
			
			if (aCmd.getCommandLine().hasOption("sigalgorithm")) {
				String lAlgo = aCmd.getCommandLine().getOptionValue("sigalgorithm");
				if (!lAlgo.equalsIgnoreCase("RSA") && !lAlgo.equalsIgnoreCase("DSA")) {
					LOGGER.log(Level.SEVERE, "Signsing algorithm can only be RSA/DSA. Defaulting to RSA");
				} else {
					this.setPreference(SIGN_ALGORITHM, lAlgo.toUpperCase());
				}
			}

			if (aCmd.getCommandLine().hasOption("commplugin")) {
				String lPluginName = aCmd.getCommandLine().getOptionValue("commplugin");
				IExtensionRegistry registry = Platform.getExtensionRegistry();
				if (null == registry) {
					LOGGER.log(Level.SEVERE, "registry returned null, Could not initialise Plugin");
				} else {
					// check extension point for DeviceComms is registered
					IExtension lCommsPlugin = registry.getExtension("com.symbian.driver.core.DeviceComms", lPluginName);
					if (lCommsPlugin == null) {
						LOGGER.log(Level.SEVERE,
										"Extension " + lPluginName + " for TD Comms is not registered, please check your TD installation.");
					} else {
						this.setPreference(COMM_PLUGIN, lPluginName);
					}
				}				
			}

			if (aCmd.getCommandLine().hasOption("plugin")) {
				// check the symtax is correct
				String lRegExp = "^((.+):(.+))=(.+)$";
				final Pattern lPluginConfigPattern = Pattern.compile(lRegExp);
				String lOptionValue = aCmd.getCommandLine().getOptionValue("plugin");
				Matcher lMatcher = lPluginConfigPattern.matcher(lOptionValue);
				IConfig lPlugin = null;
				if (!lMatcher.find()) {
					LOGGER.log(Level.SEVERE,
							"Plugin configuration setting should be in the form PluginID:variable=value.");
				} else {
					// syntax correct
					// get extensions registry
					IExtensionRegistry registry = Platform.getExtensionRegistry();
					if (null == registry) {
						LOGGER.log(Level.SEVERE, "registry returned null, Could not initialise Plugin");
					} else {
						// registry non empty
						String lPluginName = lMatcher.group(2); // lOptionValue.split(":")[0];
						// check extension point for config is registered
						IExtensionPoint lConfigEP = registry.getExtensionPoint("com.symbian.driver.core.TDConfig");
						if (lConfigEP == null) {
							LOGGER
									.log(Level.SEVERE,
											"Extension point for TD Config is not registered, please check your TD installation.");
						} else {
							LOGGER.fine("TDConfig EP found");
							// TDConfig EP registered
							// get all extensions for it
							IExtension[] lConfigExt = lConfigEP.getExtensions();
							for (IExtension lExt : lConfigExt) {
								LOGGER.fine("Extension = " + lExt.getUniqueIdentifier() + " --- " + lExt.getLabel());
								if (lExt.getContributor().getName().equalsIgnoreCase(lPluginName)) {
									IConfigurationElement[] lConfigurationElements = lExt.getConfigurationElements();
									for (IConfigurationElement element : lConfigurationElements) {
										String lClass = element.getAttribute("class");
										if (lClass == null) {
											LOGGER.log(Level.SEVERE, "The plugin " + lPluginName
													+ " has not declared a class to use.");
										} else {
											try {
												lPlugin = (IConfig) element.createExecutableExtension("class");

												if (!lPlugin.setConfig(lOptionValue)) {
													LOGGER.log(Level.SEVERE, "Could set the value to config.");
												}
											} catch (CoreException e) {
												LOGGER.log(Level.SEVERE, "Could set the value to config."
														+ e.getMessage());
											}
										}
									}
								}
							}
							if (lPlugin == null) {
								LOGGER.log(Level.SEVERE, "Extension " + lPluginName + "is not registered.");
							}
						}
					}
				}
			}
			
			// password
			if (aCmd.getCommandLine().hasOption("certpass")) {
				String lPass = aCmd.getCommandLine().getOptionValue("certpass");
				File lPassFile = new File(lPass);
				if (lPassFile.isFile() && lPassFile.canRead()) {
					CERT_PASSWORD = lPassFile;
				} else {
					LOGGER.log(Level.SEVERE, "Passphrase file is invalid. Please check the file exist with read access.");
				}			
			}
			
			// sis_path
			if (aCmd.getCommandLine().hasOption("sisroot")) {
				this.setPreference(SIS_ROOT, aCmd.getCommandLine().getOptionValue("sisroot"));
			}
			
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, lParseException.getMessage(), lParseException);
		} catch (IllegalArgumentException lArgumentException) {
			LOGGER.log(Level.SEVERE, lArgumentException.getMessage(), lArgumentException);
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, lIOException.getMessage(), lIOException);
		}
	}

	/**
	 * @param aKey
	 * @param aUri
	 * @throws ParseException
	 */
	public void setPreferenceURI(final int aKey, URI aUri) throws ParseException {
		if (aKey != ENTRY_POINT_ADDRESS) {
			throw new ParseException("This preference " + aKey
					+ " cannot use the setPreferenceURI() method. It must be of type " + URI.class);
		}

		setPreference(aKey, aUri.toString());
	}

	/**
	 * @param aKey
	 * @return The preference as a URI.
	 * @throws ParseException
	 */
	public URI getPreferenceURI(final int aKey) throws ParseException {
		if (aKey != ENTRY_POINT_ADDRESS) {
			throw new ParseException("This preference " + aKey
					+ " cannot use the getPreferenceURI() method. It must be of type " + URI.class);
		}

		return URI.createURI(getPreference(aKey));
	}

	/**
	 * @return The Test Execute (TEF) dependencies.
	 * @throws ParseException
	 */
	public String[] getTEFDependencies() throws ParseException {
		TDConfig CONFIG = TDConfig.getInstance();
		boolean bIsTefLite;
		
		String tefLiteConfig = CONFIG.getPreference(TDConfig.TEF_LITE);
		if (tefLiteConfig.equalsIgnoreCase("auto"))
			bIsTefLite = CONFIG.isPreference(TDConfig.SYS_BIN);
		else
			bIsTefLite = tefLiteConfig.equalsIgnoreCase("true") || tefLiteConfig.equalsIgnoreCase("on"); 
		
		String[] lTefDepsList = bIsTefLite? TEFLITE_DEPENDENCIES_DEFAULT.split(";"): TEF_DEPENDENCIES_DEFAULT.split(";");
		String[] lTefDepsExtraList = TEF_DEPENDENCIES_EKA2.split(";");
		String lBuildNumber = getPreference(BUILD_NUMBER);
		Set<String> lDepsSet = new HashSet<String>(Arrays.asList(lTefDepsList));
		Set<String> lDepsExtraSet = new HashSet<String>(Arrays.asList(lTefDepsExtraList));

		if (!Epoc.isEka1(lBuildNumber) && !lBuildNumber.endsWith("8.1b")) {
			lDepsSet.addAll(lDepsExtraSet);
			return (String[]) lDepsSet.toArray(new String[lTefDepsExtraList.length + lTefDepsList.length]);
		}

		return (String[]) lDepsSet.toArray(new String[lTefDepsList.length]);
	}

	/**
	 * @return The Test Execute (TEF) dependencies.
	 * @throws ParseException
	 */
	public Map<String, String> getTEFOptionalDependencies()
			throws ParseException {

		Map<String, String> lOptionalDeps = new HashMap<String, String>();
		try {
			String[] lTefOptDepsList = TEF_DEPENDENCIES_OPT_EKA2.split(";");

			// optional dependencies is separated by ";" and each has
			// a [Filename]\t[Condition] format
			String lFileStr = null;
			String lConditionStr = null;
			for (String lTefOptDepStr : lTefOptDepsList) {
				lFileStr = lTefOptDepStr.split("\t")[0];
				lConditionStr = lTefOptDepStr.split("\t")[1];
				lOptionalDeps.put(lFileStr, lConditionStr);
			}
		} catch (Exception e) {
			throw new ParseException(
					"Incorrect TEF optional dependencies describing format!");
		}
		return lOptionalDeps;
	}

	public File getCertPass() {
		return CERT_PASSWORD;
	}
}
