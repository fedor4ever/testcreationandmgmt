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

package com.symbian.driver.remoting.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;

import com.symbian.driver.Task;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.controller.SymbianVisitor;
import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.pluginProxies.RebootProxy;
import com.symbian.utils.Epoc;
import com.symbian.utils.Zipper;

/**
 * Test Driver wrapper on the server side
 * 
 * @author EngineeringTools
 */
public class TDIWrapper {

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(TDIWrapper.class.getName());

	/**
	 * lock for creating temp dir
	 */
    private static final Object tmpDirLock = new Object();
    
    private static int counter = -1; /* Protected by tmpDirLock */

	
	/** Location of Testdriver Automation folder. */
	private static File iAutomationFolder = null;

	/** The Server Logging Handler. */
	private FileHandler iTraceHandler = null;

	/** The thread for the TestDriver Job. */
	private Thread lSymbianThread;

	/**
	 * Standard Constructor
	 * 
	 * @param aTestPackage
	 *            A test pacakage path.
	 * @param aResultsPath
	 *            A path where to collect the results.
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	public TDIWrapper(final File aTestPackage, final File aResultsPath) throws ArrayIndexOutOfBoundsException,
			ParseException, IOException, TimeLimitExceededException {

		// Setup Autmation Folder
		URL lSource = TDIWrapper.class.getProtectionDomain().getCodeSource().getLocation();
		File lInstallationFolder = new File(lSource.getPath()).getParentFile().getParentFile();
		
		iAutomationFolder = new File(lInstallationFolder, "automation");
		
		LOGGER.info("Automation folder has been set as: " + iAutomationFolder);
		
		// Initialise Package
		final Task lStartTask = initialize(aTestPackage, aResultsPath);

		//  for timeout set the root node of SymbianVisitor to xxyy
		//  do some kind of stopping!
		lSymbianThread = new Thread(new Runnable() {
			public void run() {
				Thread lCurrentThread = Thread.currentThread();
				if (lSymbianThread == lCurrentThread) {
					SymbianVisitor lSymbianVisitor = new SymbianVisitor();
					try {
						TDConfig.getInstance().printConfig(true);
					} catch (IOException lE) {
						LOGGER.log(Level.WARNING, "Could not print the config..." + lE.getMessage(), lE);
					}
					// execute the test
					SecurityManager lSec = null;
					try {
						lSec = System.getSecurityManager();
						//set security manager to disable system.exit() called by Test Driver
						System.setSecurityManager(new NoExitSecurityManager());
						lSymbianVisitor.start(lStartTask);
					} catch (SecurityException lSecurityException) {
						// ignore
					} finally {
						// reset security manager so that the server can exit in case of problems.
						try {
						System.setSecurityManager(lSec);
						} catch (SecurityException lSecurityException) {
							// ignore
						}
					}
					//  cleanup all data except testresults
					
				}
			}
		});
	}

	/**
	 * 
	 */
	public void start() {
		lSymbianThread.start();
		try {
			lSymbianThread.join();

		} catch (InterruptedException lInterruptedException) {
			LOGGER.fine("Master interrupted while waiting for a job to execute.");
		} finally {
			if (iTraceHandler != null) {
				Logger.getLogger("").removeHandler(iTraceHandler);
			}
		}
	}

	/**
	 * 
	 */
	public void stop() {
		Thread lKillThread = lSymbianThread;
		lSymbianThread = null;
		lKillThread.interrupt();
	}
	

	/**
	 * initialization: checks all required folders exist. Load the manifest and
	 * unzip the files required to run the tests.
	 * 
	 * @param aTestPackage
	 *            String : a test package path name.
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 */
	private Task initialize(File aTestPackage, File lResultsPath) throws IOException, ParseException,
			TimeLimitExceededException {
		TDConfig CONFIG = TDConfig.getInstance();

		File lRepository = CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT);
		File lEpocRoot = CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT);

		// create results folder
		if (!lResultsPath.isDirectory() && !lResultsPath.mkdirs()) {
			throw new IOException("Could not create results directory." + lResultsPath.toString());
		}
		// set the results root to the output folder
		CONFIG.setPreferenceFile(TDConfig.RESULT_ROOT, lResultsPath);

		// set the logger to log to a file in the resultsPath
		iTraceHandler = new FileHandler(lResultsPath.getAbsolutePath() + File.separator + "trace.txt");
		Logger.getLogger("").addHandler(iTraceHandler);
		// create Input folder where to unzip the testpackage
		File lInputPath = new File(lResultsPath.getParent(), "Input");
		if (!lInputPath.isDirectory() && !lInputPath.mkdirs()) {
			throw new IOException("Could not create Input directory.");
		}
		Properties manifest = new Properties();
		// unzip it
		File lDepZip = new File(lInputPath.getAbsolutePath() + File.separator + "dependencies.zip");
		File lStatZip = new File(lInputPath.getAbsolutePath() + File.separator + "Stat.zip");
		File lRepositoryZip = new File(lInputPath.getAbsolutePath() + File.separator + "repository.zip");
		File lXmlZip = new File(lInputPath.getAbsolutePath() + File.separator + "xml.zip");
		


		LOGGER.fine("Unzipping package :" + aTestPackage.getName() + " To : " + lInputPath.getCanonicalPath());
		Zipper.Unzip(aTestPackage, lInputPath);

		LOGGER.fine("Reading manifest : " + lInputPath.getAbsolutePath() + File.separator + "Manifest.mf");

		manifest.load(new FileInputStream(lInputPath.getAbsolutePath() + File.separator + "Manifest.mf"));

		CONFIG.setPreference(TDConfig.PLATFORM, manifest.getProperty("platform"));
		CONFIG.setPreference(TDConfig.VARIANT, manifest.getProperty("build"));
		CONFIG.setPreferenceBoolean(TDConfig.PLATSEC, manifest.getProperty("platsec").equalsIgnoreCase("true") || manifest.getProperty("platsec").equalsIgnoreCase("on"));
		CONFIG.setPreferenceBoolean(TDConfig.TEST_EXECUTE, manifest.getProperty("testexec").equalsIgnoreCase("true") || manifest.getProperty("testexec").equalsIgnoreCase("on"));
		CONFIG.setPreferenceBoolean(TDConfig.SYS_BIN, manifest.getProperty("sysbin").equalsIgnoreCase("true") || manifest.getProperty("sysbin").equalsIgnoreCase("on"));
		CONFIG.setPreference(TDConfig.BUILD_NUMBER, manifest.getProperty("buildNumber"));
		CONFIG.setPreference(TDConfig.KERNEL, manifest.getProperty("kernel"));		
		String lTransport = manifest.getProperty("transport");
		if (lTransport != null) {
			LOGGER.fine("Setting Transport to : " + lTransport); 
			CONFIG.setPreference(TDConfig.TRANSPORT, lTransport);
		}
		String lRdebug = manifest.getProperty("rdebug");
		if (lRdebug != null){
			CONFIG.setPreference(TDConfig.RDEBUG, lResultsPath.getCanonicalPath() + manifest.getProperty("rdebug")); 
		}
		
		if (lRepositoryZip.isFile()) {
			LOGGER.fine("Unzipping " + lRepositoryZip.getAbsolutePath() + " To : " + lRepository);
			Zipper.Unzip(lRepositoryZip, lRepository);
			lRepositoryZip.delete();
		}

		File lRomImage = new File(lInputPath.getAbsolutePath(), manifest.getProperty("romFile") != null ? manifest
				.getProperty("romFile") : "NULL");
		LOGGER.fine("unzipping rom : " + lRomImage.toString() + "..." + manifest.getProperty("romFile") );
		if (lRomImage.isFile()) {
			if (Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM))) {
				LOGGER.info("Unzipping emulator image " + lRomImage.getAbsolutePath() + " To : " + lEpocRoot);

				Zipper.Unzip(lRomImage, lEpocRoot);
				lRomImage.delete();
			} else {
				LOGGER.info("Flashing " + lRomImage + " through port "
						+ CONFIG.getPreferenceInteger(TDConfig.TARGET_TEST));

				restoreRom(lRomImage, CONFIG
						.getPreferenceInteger(TDConfig.TARGET_TEST));
				
				try {
					// This is just a temporary solution as JStat connect hangs  
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					//do nothing
				}
			}
		}
		
		try {
			if (lDepZip.isFile()) {
				LOGGER.fine("Unzipping " + lDepZip.getAbsolutePath() + " To : " + lEpocRoot);
				Zipper.Unzip(lDepZip, lEpocRoot);
				lDepZip.delete();
			}
			
			if (lStatZip.isFile()) {
				LOGGER.fine("Unzipping " + lStatZip.getAbsolutePath() + " To : " + lEpocRoot);
				Zipper.Unzip(lStatZip, lEpocRoot);
				lStatZip.delete();
			}
		} catch (IOException lIO) {
			LOGGER.log(Level.SEVERE, "Failed to install dependencies " + lIO.getMessage());
		}

		File lTempFile = createTempDir(lResultsPath.getCanonicalPath(), "testDriverRemote", "driver");
		
		URI lXmlUri = URI.createFileURI(lTempFile.getCanonicalPath() + File.separator
				+ manifest.getProperty("xmldriver"));
		lXmlUri = lXmlUri.appendFragment(manifest.getProperty("suite"));

		LOGGER.fine("Setting test suite to : " + lXmlUri.toString());
		CONFIG.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, lXmlUri);
		if (lXmlZip.isFile()) {
			LOGGER.fine("Unzipping " + lXmlZip.getAbsolutePath() + " To : " + lTempFile);
			String lPlatform = CONFIG.getPreference(TDConfig.PLATFORM);
			String lVariant = CONFIG.getPreference(TDConfig.VARIANT);
			File lSourceRoot = CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT);
			File lXmlRoot = CONFIG.getPreferenceFile(TDConfig.XML_ROOT);
			Zipper.Unzip(lXmlZip, lTempFile, lEpocRoot, lXmlRoot, lSourceRoot, lPlatform, lVariant);
			lXmlZip.delete();
		}
		return ResourceLoader.load(lXmlUri);
	}

	
	
	/**
	 * create a temp directory
	 * @param prefix, the prefix of the directory to create
	 * @param suffix, the suffix of the directory to create
	 * @param parentfolder, the parent
	 * @return the temp folder,
	 */
	private File createTempDir(String directory, String prefix, String suffix) {
		synchronized (tmpDirLock) {
			File tmpFile = null;
			do {
				if (counter == -1) {
					counter = new Random().nextInt() & 0xffff;
				}
				counter++;
				tmpFile = new File(directory, prefix + Integer.toString(counter)
						+ suffix);
			} while (tmpFile.exists());
			tmpFile.mkdirs();
			tmpFile.deleteOnExit();
			return tmpFile;
		}

	}
	
	/**
	 * restart a board.
	 * 
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	private void restartBoard() throws IOException, TimeLimitExceededException {
		//  add polling to see if board has restarted + Move to
		// HardwareUtils
		try {
			boolean isSuccess = RebootProxy.getInstance().Reboot();
			if(!isSuccess)
				LOGGER.warning("Unable to reboot device!");
		} catch (Exception e) {
			LOGGER.warning("Exception thrown out! Unable to get RebootProxy.");
		}
	}

	/**
	 * flash rom
	 * 
	 * @param aRomFile
	 *            A fome file path
	 * @param aPort
	 *            A port number
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	//  use restoreRom from core package
	private void restoreRom(File aRomFile, int aPort) throws IOException, TimeLimitExceededException {
		restartBoard();

		new ExecuteOnHost(iAutomationFolder, "trgtest.exe " + aPort + " " + aRomFile.getCanonicalPath()).doTask(true,
				TimeOut.NO_TIMEOUT, false);
		//  add Polling + Move to HardwareUtils
	}
}
