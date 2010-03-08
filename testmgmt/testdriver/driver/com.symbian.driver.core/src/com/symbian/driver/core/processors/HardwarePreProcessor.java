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

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.tasks.ExecuteTransfer;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.core.controller.utils.ControllerUtils;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.controller.utils.SerialListener;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.Epoc;

/**
 * @author EngineeringTools
 * 
 */
public class HardwarePreProcessor implements PreProcessor {

	// Generic Logger + Configuration
	/** The logger for the Visitor class. */
	protected static final Logger LOGGER = Logger.getLogger(EmulatorPreProcessor.class.getName());

	/** Generic settings/configuration. */
	protected final TDConfig CONFIG = TDConfig.getInstance();

	/** RDebug Log File. */
	private File iRDebug = new File("RDebug.log").getAbsoluteFile();

	/** RDebug Thread. */
	private SerialListener iRdebugListener = null;

	/** Post Processor for the Hardware. */
	private HardwarePostProcessor iHardwarePostProcessor = null;

	/** TEF dependencies transfer set. */
	private ExecuteTransferSet iTEFDependenciesTransferSet;

	/**
	 * @throws ParseException
	 */
	public HardwarePreProcessor() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 * @throws JStatException
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 * @throws InterruptedException
	 * 
	 * @see com.symbian.driver.core.processors.PreProcessor#start()
	 */
	public boolean start() throws ParseException, TimeLimitExceededException, IOException {
		boolean lResult = true;
		// Start Hardware
		String lHardwareSwitch = "automation\\hardwareswitch.exe";
		if (new File(lHardwareSwitch).isFile()) {
			Runtime.getRuntime().exec(lHardwareSwitch + " on");
		}
		if (!DeviceUtils.poll(null)) {
			lResult = false;
			stop();
		} else {

			// Start RDebug Capture
			if (!TDConfig.getInstance().getPreference(TDConfig.RDEBUG).equalsIgnoreCase("")) {
				iRdebugListener = new SerialListener(CONFIG.getPreference(TDConfig.RDEBUG), iRDebug);
			}

			iHardwarePostProcessor = new HardwarePostProcessor(iRdebugListener);
			
			// Install TEF Dependencies
			if (TDConfig.getInstance().isPreference(TDConfig.TEST_EXECUTE)) {
				installTEFDependencies();
				//after tef dep. installed, iTEFDependenciesTransferSet should set
				iHardwarePostProcessor.setTEFDEF(iTEFDependenciesTransferSet);
			}

			Runtime.getRuntime().addShutdownHook(iHardwarePostProcessor);
		}

		return lResult;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.symbian.driver.core.processors.PreProcessor#stop()
	 */
	public void stop() {
		if (iHardwarePostProcessor != null) {

			try {
				iHardwarePostProcessor.start();
			} catch (IllegalThreadStateException lException) {
				return;
			}

			try {
				iHardwarePostProcessor.join();
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "Shutdown was interupted. Please shutdown and restore manaully.",
						lInterruptedException);
			}

		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws JStatException
	 * @throws IOException
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 * 
	 * @see com.symbian.driver.core.processors.PreProcessor#restart()
	 */
	public boolean restart() {
		boolean lResult = true;
		stop();

		try {
			start();
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to a time limit issue.",
					lTimeLimitExceededException);
			lResult = false;
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to a configuration issue.", lParseException);
			lResult = false;
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Restarting the board has failed due to a IO issue.", lIOException);
			lResult = false;
		}

		return lResult;
	}

	/**
	 * @see com.symbian.driver.core.processors.PreProcessor#setRDebugOuput(java.io.File)
	 */
	public void setRDebugOuput(File aRDebugOutput) throws IOException {
		if (iRdebugListener == null) {
			throw new IOException("RDebug must be defined for RTests. Please use -l to configure the RDebug Port.");
		}
		iRdebugListener.setLogFile(aRDebugOutput);
	}

	/**
	 * @see com.symbian.driver.core.processors.PreProcessor#restoreRDebugOutput()
	 */
	public void restoreRDebugOutput() {
		iRdebugListener.setLogFile(iRDebug);
	}

	/**
	 * Installs the Test Execute (TEF) Dependencies.
	 * 
	 * @throws ParseException
	 *             A configuration error.
	 * @throws IOException
	 *             A file error.
	 * @throws JStatException
	 *             A STAT error.
	 * @throws TimeLimitExceededException
	 *             If the installation took too much time.
	 * @throws JStatException
	 */
	private void installTEFDependencies() throws ParseException, IOException, TimeLimitExceededException {
		// Delete any previous files form the repository
		TDConfig CONFIG = TDConfig.getInstance();
		File[] lRepositoryFolder = CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT).listFiles();
		for (int lIter = lRepositoryFolder.length - 1; lIter >= 0; lIter--) {
			if (lRepositoryFolder[lIter].isFile() && !lRepositoryFolder[lIter].delete()) {
				LOGGER.log(Level.WARNING, "Could not delete TEF Dependencies form repsository.");
			}
		}
		
		// Copy the TestExecuteDependencies to the board.
		iTEFDependenciesTransferSet = new ExecuteTransferSet("TEFDep", CONFIG
				.getPreferenceFile(TDConfig.REPOSITORY_ROOT));
		
		String lUidTef = CONFIG.getPreference(TDConfig.UIDLAST);

		File lTEFSisFile = new File((CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT) + File.separator
				+ "testDriver_TEF_" + lUidTef + com.symbian.driver.core.environment.ILiterals.SIS));

		String[] lTEFDep = CONFIG.getTEFDependencies();
		for (int lTEFDepIter = 0; lTEFDepIter < lTEFDep.length; lTEFDepIter++) {

			// Add the File to the Repository
			boolean lAddToTransfer = false;
			if (lTEFDep[lTEFDepIter].endsWith(".ini")) {
				File lTestExecIniFile = null;
				String LEpocRoot = TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath();
				if (Epoc.isTargetEmulator(TDConfig.getInstance().getPreference(TDConfig.PLATFORM))) {
					// pick the ini file from
					// ${epocroot}epoc32\winscw\c\system\data\testexecute.ini
					lTestExecIniFile = new File(
							(LEpocRoot + File.separator + com.symbian.driver.core.environment.ILiterals.EPOC32
									+ File.separator + Epoc.WINSCW + File.separator + "c" + File.separator
									+ com.symbian.driver.core.environment.ILiterals.SYSTEM + File.separator
									+ com.symbian.driver.core.environment.ILiterals.DATA + File.separator + lTEFDep[lTEFDepIter])
									.replaceAll("\\\\+", "\\\\"));

				} else {
					// pick the ini file from
					// ${EPOCROOT}epoc32\data\Z\system\data\testexecute.ini
					lTestExecIniFile = new File(
							(LEpocRoot + File.separator + com.symbian.driver.core.environment.ILiterals.EPOC32
									+ File.separator + com.symbian.driver.core.environment.ILiterals.DATA
									+ File.separator + "z" + File.separator
									+ com.symbian.driver.core.environment.ILiterals.SYSTEM + File.separator
									+ com.symbian.driver.core.environment.ILiterals.DATA + File.separator + lTEFDep[lTEFDepIter])
									.replaceAll("\\\\+", "\\\\"));
				}
				lAddToTransfer = iTEFDependenciesTransferSet.add(new ExecuteTransfer(lTestExecIniFile,
						com.symbian.driver.core.environment.ILiterals.SYS_DATA + lTEFDep[lTEFDepIter]));
			} else {
				File lFile = new File((CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath() + File.separator
						+ ModelUtils.getEpoc32RelPlatVar() + lTEFDep[lTEFDepIter]).replaceAll("\\\\+", "\\\\"));
				if (lFile.exists()) {
					lAddToTransfer = iTEFDependenciesTransferSet.add(new ExecuteTransfer(lFile,
							com.symbian.driver.core.environment.ILiterals.SYS_BIN + 
							(lTEFDep[lTEFDepIter].equalsIgnoreCase("testexecutelite.exe") ? "testexecute.exe" : lTEFDep[lTEFDepIter])));
				} else {
					LOGGER.warning("File does not exist : " + lFile);
				}
			}
			if (!lAddToTransfer) {
				LOGGER.severe("Could not add " + lTEFDep[lTEFDepIter] + " to repository/sis package.");
			}

		}
		
		/*add optional TEF dependencies using PKG conditional syntax, e.g.:
		 * if exists("z:\sys\bin\IniParser.dll") AND NOT exists("c:\sys\bin\IniParser.dll")
		 *  "Z:\EPOC32\RELEASE\ARMV5\UREL\iniparser.dll"-"!:\sys\bin\iniparser.dll"
		 * endif
		 * CONFIG.getTEFOptionalDependencies() returns map which contains file entry and condition in
		 * format of: {[File entry],[Condition blocks]}
		 */
		Map<String,String> lTEFOptDeps = CONFIG.getTEFOptionalDependencies();
		Set<Entry<String,String>> lEntrySet = lTEFOptDeps.entrySet();
		boolean lAddToTransfer;
		String lFileStr, lCondition;
		for (Entry<String,String> lEntry : lEntrySet) {
			lAddToTransfer = false;
			lFileStr = lEntry.getKey();
			lCondition = lEntry.getValue();
			
			File lFile = new File((CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath() + File.separator
					+ ModelUtils.getEpoc32RelPlatVar() + lFileStr).replaceAll("\\\\+", "\\\\"));
			if (lFile.exists()) {
				lAddToTransfer = iTEFDependenciesTransferSet.add(
						new ExecuteTransferSet.ConditionalTransfer(
								lFile, com.symbian.driver.core.environment.ILiterals.SYS_BIN + lFileStr, lCondition));
			} else {
				LOGGER.warning("File does not exist : " + lFile);
			}
			if (!lAddToTransfer) {
				LOGGER.severe("Could not add " + lFileStr + " to repository/sis package.");
			}
		}		

		// Create repository + pkg
		iTEFDependenciesTransferSet.createRepository(lUidTef);

		//if (CONFIG.isPreference(TDConfig.PLATSEC) && !CONFIG.isPreference(TDConfig.SYS_BIN)) {
		if ( ! CONFIG.isPreference(TDConfig.SYS_BIN)) {

			// Create SIS file
			iTEFDependenciesTransferSet.createSis(CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT), lTEFSisFile,
					ControllerUtils.getCert(), ControllerUtils.getKey());

			// Install SIS File
			iTEFDependenciesTransferSet.installSis(lTEFSisFile);

		} else {
			iTEFDependenciesTransferSet.installRepository();
		}
	}
}
