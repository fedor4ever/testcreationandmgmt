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



package com.symbian.driver.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.Phase;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.controller.event.TaskFinishedEvent;
import com.symbian.driver.core.controller.tasks.BuildTEFTask;
import com.symbian.driver.core.controller.tasks.BuildTask;
import com.symbian.driver.core.controller.tasks.BuildTransferTask;
import com.symbian.driver.core.controller.tasks.CmdPCTask;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.core.controller.tasks.IExecuteTransferSet;
import com.symbian.driver.core.controller.utils.ControllerUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.extension.IVisitor.ESeverity;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.driver.util.DriverSwitch;

/**
 * Walks through the EMF tree and processes all commands relating to the PC,
 * including the creation of the Repository.
 * 
 * Specifically this class visits the following tags:
 * <ul>
 * <li><code>Build</code></li>;
 * <li><code>CmdPc</code></li>;
 * <li><code>Reference</code></li>;
 * <li><code>testExecuteScript</code> and;</li>
 * <li><code>TransferToSymbian</code></li>
 * </ul>
 * 
 * @author EngineeringTools
 */
public class PCVisitor extends Visitor {

	/** If Testdriver should do an rbuild or build. */
	private static boolean iRBuild = false;

	/** The static UID stack. */
	protected static final ArrayList<String> UID_LIST = new ArrayList<String>();

	/**
	 * Constructor for HostVisitor.
	 * 
	 * Creates the stack of UID's.
	 */
	public PCVisitor() {
		// Prepare a UID set to be used when creating sis files.
		String lUidFirstLiteral = null;
		String lUidLastLiteral = null;
		TDConfig CONFIG = TDConfig.getInstance();
		try {
			lUidFirstLiteral = CONFIG.getPreference(TDConfig.UIDFIRST);
			lUidLastLiteral = CONFIG.getPreference(TDConfig.UIDLAST);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get the preference for the first and last UID.", lParseException);
		}

		int lUidFirstInt = 0x10210D02;
		int lUidLastInt = 0x10210D32;

		try {
			if (lUidFirstLiteral != null && !lUidFirstLiteral.equalsIgnoreCase("0") && !lUidFirstLiteral.equalsIgnoreCase("")) {
				lUidFirstInt = Integer.decode(lUidFirstLiteral).intValue();
			}
			if (lUidLastLiteral != null && !lUidLastLiteral.equalsIgnoreCase("0") && !lUidLastLiteral.equalsIgnoreCase("")) {
				lUidLastInt = Integer.decode(lUidLastLiteral).intValue();
			}
		} catch (NumberFormatException lNumberFormatException) {
			LOGGER.log(Level.WARNING, "Please specifiy correct UID number ranges.", lNumberFormatException);
		}

		// Configures the UID set
		if (UID_LIST.size() == 0) {
			for (int lUidIndex = lUidFirstInt; lUidIndex <= lUidLastInt; lUidIndex++) {
				UID_LIST.add("0x" + Integer.toHexString(lUidIndex));
			}

			if (UID_LIST.size() != 49) {
				LOGGER.log(Level.SEVERE, "Could not intilaise UID stack, stack is of size: " + UID_LIST.size());
			}
		}
	}

	/**
	 * Visits all the tasks in the model starting from a specified task.
	 * 
	 * @param aTask
	 */
	public boolean start(Task aTask) {
		// visit tasks to do build/rbuild and visit tasks to create packages
		if (super.start(aTask, iBuildSwitch, null, true) && super.start(aTask, iPackageSwitch, null, false)) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the RBuild boolean for the build phase of TestDriver.
	 * 
	 * @param aRBuild
	 *            <code>true</code> if you don't want to run the build chain,
	 *            <code>false</code> otherwise.
	 * @return The value of what RBuild was set to.
	 */
	public boolean setRBuild(final boolean aRBuild) {
		iRBuild = aRBuild;

		return iRBuild;
	}

	/**
	 * Checks if RBuild is running for the build phase of TestDriver.
	 * 
	 * @return <code>true</code> if abld should not run, <code>false</code>
	 *         otherwise.
	 */
	public static boolean isRBuild() {
		return iRBuild;
	}

	/**
	 * A Driver switch to build and run all the PC/Host Build phase tasks.
	 */
	private final DriverSwitch iBuildSwitch = new DriverSwitch() {

		@Override
		public Object caseCmdPC(final CmdPC aCmdPC) {
			if (aCmdPC.getPhase() == Phase.BUILD || aCmdPC.getPhase() == Phase.BOTH) {
				return new CmdPCTask(aCmdPC);
			}

			return null;
		}

		@Override
		public Object caseBuild(final Build aBuild) {
			return new BuildTask(aBuild, iRBuild);
		}

		@Override
		public Object caseTestExecuteScript(TestExecuteScript aTestExecuteScript) {
			return new BuildTEFTask(aTestExecuteScript);
		}

		@Override
		public Object caseTransfer(final Transfer aTransfer) {
			return new BuildTransferTask(aTransfer);
		}

		@Override
		public Object caseTask(Task aTask) {
			HashMap<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();
			File lBaseDirectory = SymbianVisitor.getBaseDirectory(aTask);
			
			try {
				aTask.setTransferSet(new ExecuteTransferSet(aTask.getName(), lBaseDirectory));
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Could not get new repository. Placing in current directory instead.", lIOException);
				lExceptions.put(lIOException, ESeverity.ERROR);
				try {
					aTask.setTransferSet((IExecuteTransferSet) new ExecuteTransferSet(aTask.getName(), new File(".")));
				} catch (IOException lIOException2) {
					LOGGER.log(Level.SEVERE, "Could not get new repository in current directory.", lIOException2);
					lExceptions.put(lIOException2, ESeverity.ERROR);
				}
			}
			return null;
		}

	};

	/**
	 * A Driver switch to move and package all the files into the Repository.
	 */
	private final DriverSwitch iPackageSwitch = new DriverSwitch() {

		@Override
		public Object caseTask(Task aObject) {
			return new IVisitor() {

				public Map<Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();

					// Create the repository if necessary
					try {
						File lBaseDirectory = SymbianVisitor.getBaseDirectory(aTask);

						if (!((ExecuteTransferSet) aTask.getTransferSet()).isEmpty() && lBaseDirectory != null) {
							// Setup the UID's
							String lUid = (String) UID_LIST.get(aTask.getLevel());

							// Create the Repository + SIS file
							File lSisFile = new File(lBaseDirectory, "TestDriver_" + aTask.getName() + "_" + lUid
									+ com.symbian.driver.core.environment.ILiterals.SIS);

							LOGGER.fine("Creating Repository Directory: " + lBaseDirectory.getCanonicalPath());

							if (!lBaseDirectory.isDirectory() && !lBaseDirectory.mkdirs()) {
								IOException lException = new IOException("Could not create repository at: "
										+ lBaseDirectory.getCanonicalPath());
								lExceptions.put(lException, ESeverity.ERROR);
								// return lExceptions;
							} else {

								// Clean up repository folder
								File[] lOldFiles = lBaseDirectory.listFiles();
								for (int lIter = 0; lIter < lOldFiles.length; lIter++) {
									if (lOldFiles[lIter].isFile() && !lOldFiles[lIter].delete()) {
										LOGGER.warning("Could not delete old repository file: " + lOldFiles[lIter]);
									}
								}

								// Create the repository

								((ExecuteTransferSet) aTask.getTransferSet()).createRepository(lUid);
								if ( ! CONFIG.isPreference(TDConfig.SYS_BIN) ) {
									((ExecuteTransferSet) aTask.getTransferSet()).createSis(CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT),
											lSisFile, ControllerUtils.getCert(), ControllerUtils.getKey());
								}
							}
						}

					} catch (IOException lIOException) {
						LOGGER.log(Level.SEVERE, "I/O exception while creating repository.", lIOException);
						lExceptions.put(lIOException, ESeverity.ERROR);
					} catch (TimeLimitExceededException lTimeLimitExceededException) {
						LOGGER.log(Level.SEVERE, "Time limit exceed for creation of repository.", lTimeLimitExceededException);
						lExceptions.put(lTimeLimitExceededException, ESeverity.ERROR);
					} catch (ParseException lParseException) {
						LOGGER.log(Level.SEVERE, "Could not get configuration.", lParseException);
						lExceptions.put(lParseException, ESeverity.ERROR);
					} finally {
						fireTaskFinishedEvent(new TaskFinishedEvent(aTask, true, false, lExceptions));
					}
					return lExceptions;
				}
			};
		};
	};

}
