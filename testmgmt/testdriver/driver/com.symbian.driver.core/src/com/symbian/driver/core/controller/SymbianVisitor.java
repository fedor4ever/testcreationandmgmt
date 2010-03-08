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
import java.io.FileFilter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.FlashROM;
import com.symbian.driver.Phase;
import com.symbian.driver.Rtest;
import com.symbian.driver.StartTrace;
import com.symbian.driver.StopTrace;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.controller.event.TaskFinishedEvent;
import com.symbian.driver.core.controller.tasks.CmdPCTask;
import com.symbian.driver.core.controller.tasks.CmdSymbianTask;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.core.controller.tasks.FlashROMTask;
import com.symbian.driver.core.controller.tasks.RTest;
import com.symbian.driver.core.controller.tasks.StartTraceTask;
import com.symbian.driver.core.controller.tasks.StopTraceTask;
import com.symbian.driver.core.controller.tasks.TEFTask;
import com.symbian.driver.core.controller.tasks.TransferTask;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.extension.IVisitor.ESeverity;
import com.symbian.driver.core.pluginProxies.CoreDumpProxy;
import com.symbian.driver.core.pluginProxies.RebootProxy;
import com.symbian.driver.core.processors.EmulatorPreProcessor;
import com.symbian.driver.core.processors.HardwarePreProcessor;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.driver.util.DriverSwitch;
import com.symbian.utils.Epoc;

/**
 * Walks through the EMF tree processing all device/Symbian/run commands.
 * 
 * Specifically this class visits the following tags:
 * <ul>
 * <li><code>CmdSymbian</code>;</li>
 * <li><code>Reference</code>;</li>
 * <li><code>RetrieveFromSymbian</code>;</li>
 * <li><code>RTest</code>;</li>
 * <li><code>TestExeucteScript</code> and;</li>
 * <li><code>TransferToSymbian</code>.</li>
 * </ul>
 * 
 * @author EngineeringTools
 */
public class SymbianVisitor extends Visitor {

	/** Symbian Device (Emulator/Hardware). */
	private static PreProcessor sSymbianDevice = null;

	private static boolean iRebooted = false;
	
	/**
	 * Starts the Run Visitor.
	 * 
	 * If <code>WINSCW</code> or <code>WINS</code> then starts the emulator
	 * using the WINTAP Address in the configuration. Otherwise it starts the
	 * Hardware board. If ncessary it will also start RDebug and install TEF
	 * dependencies.
	 * 
	 * @param aTask
	 * @throws Exception
	 */
	public boolean start(final Task aTask) {
		boolean lReturn =true;
		boolean lEmulator = false;
		try {
			TDConfig CONFIG = TDConfig.getInstance();
			lEmulator = Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM));
			if (lEmulator) {
				// Start Emulator
				LOGGER.fine("Creating Emulator Pre Processor");
				sSymbianDevice = new EmulatorPreProcessor();
			} else {
				// Start Hardware
				LOGGER.fine("Creating Hardware Pre Processor");
				sSymbianDevice = new HardwarePreProcessor();
			}

			// Start the Symbian Device (Emulator or Hardware
			LOGGER.fine("Starting Pre Processor");
			if (sSymbianDevice.start()) {

				// Increment the Run Number
				CONFIG.incrementRunNumber();
				LOGGER.info("Run number: " + CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER));

				// Start Test Driver
				if (!super.start(aTask, iSymbianSwitch, sSymbianDevice, true)) {
					lReturn = false;
				}
			} else {
				lReturn = false;
				LOGGER.log(Level.SEVERE, "Could not start symbian device.");
				//sSymbianDevice.stop();
			}

		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, lIOException.getMessage(), lIOException);
			lReturn = false;
		} catch (EmptyStackException lEmptyStackException) {
			// For the Install and UID Stack Execpetions
			LOGGER.log(Level.FINE, "Stack Exception for UID/Install stack.", lEmptyStackException);
			lReturn = false;
		} catch (StringIndexOutOfBoundsException lStringIndexOutOfBoundsException) {
			LOGGER.log(Level.SEVERE, "Wintap for emulator tests has an error.", lStringIndexOutOfBoundsException);
			lReturn = false;
		} catch (Exception Exception) {
			lReturn = false;
			LOGGER.log(Level.SEVERE, "JStat failed while starting the run", Exception);
			// something, maybe stat or something on the device couldve hanged
			// if a reboot plugin is available, see if reboot can help
			if (!lEmulator) {
				try {
					if (RebootProxy.getInstance().Reboot()) {
						iRebooted = true;
					} else {
						LOGGER.log(Level.SEVERE, "could not reboot. See previous errors.");
					}
				} catch (Exception lException) {
					LOGGER.log(Level.SEVERE, "could not reboot thru any plugins.", lException);
				}
			}
		} catch (Throwable lThrowable) {
			LOGGER.log(Level.SEVERE, lThrowable.getMessage(), lThrowable);
			lReturn = false;
		} finally {
			// Stop Test Driver
			stop();
		}
		return lReturn;
	}

	/**
	 * Stops the Symbian Device (Hardware or Emulator).
	 * 
	 * This will also uninstall any remaing tasks in the Install Stack.
	 */
	public boolean stop() {
		boolean lReturn = true;
		if (!iRebooted) {

			super.stop();

			// Uninstall any remaining tasks
			try {

				while (!TASK_SET.isEmpty()) {
					((ExecuteTransferSet) TASK_SET.pop().getTransferSet()).uninstall();
				}

			} catch (TimeLimitExceededException lTimeLimitExceededException) {
				LOGGER.log(Level.SEVERE, "Could not uninstall any remaining Tasks due to timeout.",
						lTimeLimitExceededException);
				lReturn = false;
			}
			
			// Stop Symbian
			sSymbianDevice.stop();

		}
		return lReturn;
		// }
	}

	/**
	 * 
	 */
	private final DriverSwitch iSymbianSwitch = new DriverSwitch() {

		@Override
		public Object caseFlashROM(FlashROM object) {
			try {
				boolean lEmulator = Epoc.isTargetEmulator(CONFIG.getPreference(TDConfig.PLATFORM));
				if (!lEmulator) {
					return new FlashROMTask(object);
				}
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Configuration error", lParseException);
			}
			return null;
		}

		@Override
		public Object caseCmdPC(final CmdPC aCmdPC) {
			if (aCmdPC.getPhase() == Phase.RUN || aCmdPC.getPhase() == Phase.BOTH) {
				return new CmdPCTask(aCmdPC);
			}

			return null;
		}

		@Override
		public Object caseCmdSymbian(final CmdSymbian aCmdSymbian) {
			return new CmdSymbianTask(aCmdSymbian);
		}

		@Override
		public Object caseTestExecuteScript(final TestExecuteScript aTestExecuteScript) {
			return new TEFTask(aTestExecuteScript);
		}

		@Override
		public Object caseRtest(Rtest aRtest) {
			return new RTest(aRtest);
		}

		@Override
		public Object caseTransfer(final Transfer aTransfer) {
			return new TransferTask(aTransfer);
		}
		
        public Object caseStartTrace(final StartTrace aStartTrace) {
            return new StartTraceTask(aStartTrace);
        }
        
        public Object caseStopTrace(final StopTrace aStopTrace) {
			return new StopTraceTask(aStopTrace);
		}
		
		@Override
		public Object caseTask(Task aTask) {
			return new IVisitor() {

				public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
					Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();
					// The root directory to use for repositories
					File lBaseDirectory = getBaseDirectory(aTask);
					if (lBaseDirectory == null) {
						return lExceptions;
					}

					// A transfer set used to create and transfer repositories
					try {
						aTask.setTransferSet(new ExecuteTransferSet(aTask.getName(), lBaseDirectory));
					} catch (Exception lIOException) {
						LOGGER.log(Level.WARNING, "Could not create new transfer.", lIOException);
						lExceptions.put(lIOException, ESeverity.ERROR);
					}

					Map<Exception, ESeverity> uninstall = uninstall(aTask);
					// Uninstall Previous Execute
					lExceptions.putAll(uninstall);

					Map<Exception, ESeverity> install = install(aTask);
					// Install this task
					lExceptions.putAll(install);

					fireTaskFinishedEvent(new TaskFinishedEvent(aTask, false, false, lExceptions));

					return lExceptions;
				}

			};
		}
	};

	/**
	 * Uninstalls the previous tasks if necessary.
	 * 
	 * @param aTask
	 *            The current tasks
	 * @return <code>true</code> if no failures occurd during the
	 *         installation, <code>false</code> otherwise
	 */
	private Map<Exception, ESeverity> uninstall(final Task aTask) {
		LOGGER.entering(this.getClass().getName(), "uninstall");

		Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();

		if (TASK_SET.isEmpty()) {
			TASK_SET.push(aTask);
			iPreviousLevel = aTask.getLevel();
			return lExceptions;

		}
		try {
			ExecuteTransferSet lExecuteTransferSet = null;
			if (!TASK_SET.isEmpty() && iPreviousLevel == aTask.getLevel()) {

				LOGGER.finer("Staying level in the task hierachy.");

				Task lTask = TASK_SET.pop();
				lExecuteTransferSet = (ExecuteTransferSet) lTask.getTransferSet();
				if (!lExecuteTransferSet.uninstall()) {
					lExceptions.put(new Exception("Un-installing " + lExecuteTransferSet.getUid() + " failed."), ESeverity.ERROR);
				}

			} else if (iPreviousLevel > aTask.getLevel()) {
				LOGGER.finer("Going down one level in the task hierachy.");

				Task lTask = TASK_SET.pop();
				iPreviousLevel = aTask.getLevel();
				while (aTask.eContainer() != lTask.eContainer()) {
					lExecuteTransferSet = (ExecuteTransferSet) lTask.getTransferSet();
					if (!lExecuteTransferSet.uninstall()) {
						lExceptions.put(new Exception("Un-installing " + lExecuteTransferSet.getUid() + " failed."), ESeverity.ERROR);
					}
					lTask = TASK_SET.pop();
				}
				lExecuteTransferSet = (ExecuteTransferSet) lTask.getTransferSet();
				if (!lExecuteTransferSet.uninstall()) {
					lExceptions.put(new Exception("Un-installing " + lExecuteTransferSet.getUid() + " failed."), ESeverity.ERROR);
				}
			}

		} catch (TimeLimitExceededException lTimeLimitExceededException) {

			LOGGER.log(Level.SEVERE, "Uninstall/deleting of repository failed due to timeout",
					lTimeLimitExceededException);
			lExceptions.put(lTimeLimitExceededException, ESeverity.ERROR);

		} finally {
			iPreviousLevel = aTask.getLevel();
			TASK_SET.push(aTask);

		}

		return lExceptions;
	}

	private File iSisFile;

	/**
	 * Installs the current task if necessary.
	 * 
	 * @param aTask
	 *            The current task to install.
	 * @param aExecuteTransferSet
	 *            The transfer set relating to the current tasks.
	 * @param aBase
	 *            The base repository directory to install from.
	 * @return <code>true</code> if no failures occurd during the
	 *         installation, <code>false</code> otherwise
	 * @throws ParseException
	 *             If there was a configuration exception.
	 */
	private Map<Exception, ESeverity> install(final Task aTask) {
		LOGGER.entering(this.getClass().getName(), "install");

		HashMap<File, File> lEmulatorHashBackup = null;
		Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();
		TDConfig CONFIG = TDConfig.getInstance();
		String ltaskName = ((ExecuteTransferSet) aTask.getTransferSet()).getName();
		boolean lPlatSec = true;
		try {
			//lPlatSec = CONFIG.isPreference(TDConfig.PLATSEC) && !CONFIG.isPreference(TDConfig.SYS_BIN);
			lPlatSec = ! CONFIG.isPreference(TDConfig.SYS_BIN);
		} catch (ParseException e) {
			LOGGER.log(Level.WARNING, "Could not get the configuration for PlatSec. Defaulting to ON");
		}

		try {

			// Install the SIS file or Repository
			if (lPlatSec) {
				// PlatSec ON

				File lBaseDirectory = getBaseDirectory(aTask);
				if (lBaseDirectory == null) {
					return lExceptions;
				}

				File[] lFile = lBaseDirectory.listFiles(new FileFilter() {
					public boolean accept(File lTestFile) {
						if (lTestFile.getName().toLowerCase().startsWith("testdriver_")
								&& lTestFile.getName().toLowerCase().endsWith(
										com.symbian.driver.core.environment.ILiterals.SIS)) {
							return true;
						}
						return false;
					}
				});

				if (lFile == null) {
					LOGGER.fine("The repository is empty at: " + lBaseDirectory);
				} else if (lFile.length == 1) {

					File lSisFile = lFile[0];

					LOGGER.fine("Setting SIS File to: " + lSisFile.getCanonicalPath());

					// ////////////////////////////////////////
					//  EMULATOR  to delete: Moves the emulator files
					// Replace with buildrom -> emulatorbuild
					lEmulatorHashBackup = EmulatorPreProcessor.backupEmulator(lSisFile);

					((ExecuteTransferSet) aTask.getTransferSet()).installSis(lSisFile);

				} else if (lFile.length > 1) {
					throw new IOException("There are too many SIS files at: " + ltaskName
							+ ". Please delete your repository and rebuild.");
					
				}

			} else {
				// PlatSec OFF
				LOGGER.fine("Installing Repository Directory with PlatSec Off");

				((ExecuteTransferSet) aTask.getTransferSet()).installRepository();
			}

		} catch (IOException lIOException) {

			LOGGER.log(Level.SEVERE, "Installation/copying of repository " + ltaskName + " failed due IO Error",
					lIOException);
			lExceptions.put(lIOException, ESeverity.ERROR);

		} catch (TimeLimitExceededException lTimeLimitExceededException) {

			LOGGER.log(Level.SEVERE, "Installation/copying of repository " + ltaskName + " failed due to time out.",
					lTimeLimitExceededException);
			lExceptions.put(lTimeLimitExceededException, ESeverity.ERROR);

		} finally {
			EmulatorPreProcessor.restoreEmulator(lEmulatorHashBackup);
		}

		return lExceptions;
	}

	static File getBaseDirectory(Task aTask) {
		File lBaseDirectory = new File(".");
		try {
			lBaseDirectory = new File(TDConfig.getInstance().getPreferenceFile(TDConfig.REPOSITORY_ROOT), ModelUtils
					.getBaseDirectory(aTask, Integer.MIN_VALUE));
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get repository directory.", lParseException);
		}

		return lBaseDirectory;
	}

}
