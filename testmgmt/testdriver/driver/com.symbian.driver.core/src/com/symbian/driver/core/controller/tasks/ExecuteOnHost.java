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

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import com.symbian.driver.core.controller.tasks.Execute;
import com.symbian.nativeprocesshandler.ProcessHandler;
import com.symbian.utils.StreamGlobber;

/**
 * Executes a native command.
 * 
 * @author EngineeringTools
 */
public class ExecuteOnHost implements Execute {

	/** Logger. */
	private static Logger LOGGER = Logger.getLogger(Execute.class.getName());

	/** The command to execute. */
	private String iCmd = null;

	/** The command name. */
	private String iCmdName = null;

	/** The working directory to execute the command form. */
	private String iWorkingDirectory = null;

	/** The process of the executable. */
	private Process iChildProcess = null;

	/** The cmd's running before this process starts. */
	private int[] iPidBefore = null;

	/** Timer for the timeout. */
	private Timer iTimeoutTimer = null;

	private final StringBuffer iOutputBuffer = new StringBuffer();

	/** Stops the current thread. */
	private static boolean STOP = false;

	private StreamGlobber iStreamGobblerError = null;

	private StreamGlobber iStreamGobblerOutput = null;

	/**
	 * Constructor fo the execute method.
	 * 
	 * @param aWorkingDirectory
	 *            Sets the working directory for the program to execute.
	 * @param aCmd
	 *            The command to run.
	 * @param aOutputBuffer
	 *            The ouptut buffer to print out the STDERR and STDOUT.
	 * @param aPrintStream
	 * @throws IOException
	 */
	public ExecuteOnHost(final File aWorkingDirectory, final String aCmd) throws IOException {
		LOGGER.entering("ExecuteOnHost", "Constructor", new Object[] { aWorkingDirectory, aCmd });

		STOP = false;
		iCmd = aCmd;
		iCmdName = aCmd.split(" ")[0];

		if (!aWorkingDirectory.isDirectory()) {
			throw new IOException("Working directory is invalid: " + aWorkingDirectory.toString());
		}

		iWorkingDirectory = aWorkingDirectory.getAbsolutePath();
	}

	/**
	 * Executes the set of commands on the host side.
	 * 
	 * @param aDoCleanUp
	 *            {@inheritDoc}
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 * @see com.symbian.driver.core.controller.tasks.Execute#doTask(boolean,
	 *      int, boolean) boolean)
	 */
	public final boolean doTask(final boolean aDoCleanUp, final int aTimeout, boolean captureOutput) throws IOException,
			TimeLimitExceededException {
		boolean lReturn = true;
		LOGGER.entering("ExecuteOnHost", "doTask", Boolean.toString(aDoCleanUp));

		iPidBefore = ProcessHandler.getPidList(iCmdName);

		for (int lPIDIndex = 0; lPIDIndex < iPidBefore.length; lPIDIndex++) {
			LOGGER.fine("Found previous process " + iCmdName + " PID: " + iPidBefore[lPIDIndex]);
		}

		String lInfoMessage = "Working Directory: \"" + iWorkingDirectory + "\"\tExecuting: \"" + iCmd + "\"";
		try {
			iChildProcess = Runtime.getRuntime().exec("cmd.exe /C \"cd /d " + iWorkingDirectory + " && " + iCmd);
		} catch (IOException lIOException) {
			LOGGER.log(Level.WARNING, "ExecuteOnHost failed", lIOException);
			throw new IOException("ExecuteOnHost failed: " + lIOException.getMessage());
		}

		if (captureOutput) {
			iStreamGobblerOutput = new StreamGlobber(iOutputBuffer, iChildProcess.getInputStream(), false, lInfoMessage);
			iStreamGobblerError = new StreamGlobber(iOutputBuffer, iChildProcess.getErrorStream(), true, lInfoMessage);
			iStreamGobblerOutput.start();
			iStreamGobblerError.start();
		}

		STOP = false;
		iTimeoutTimer = startTimer(aTimeout);

		if (aDoCleanUp) {
			try {
				if (iStreamGobblerOutput != null) {
					iStreamGobblerOutput.join((aTimeout <= 0 || aTimeout >= TIMEOUT_MAX) ? TIMEOUT_MAX : aTimeout);
				}
				if (iStreamGobblerError != null) {
					iStreamGobblerError.join((aTimeout <= 0 || aTimeout >= TIMEOUT_MAX) ? TIMEOUT_MAX : aTimeout);
				}
			} catch (InterruptedException lInterruptedException) {
			}

			doCleanUp(true);
		} else {
			new Thread(new Runnable() {
				public void run() {
					try {
						doCleanUp(true);
					} catch (TimeLimitExceededException lTimeLimitExceededException) {
						LOGGER.log(Level.SEVERE, "ExecuteOnHost timed out.", lTimeLimitExceededException);
					}
				}
			}, "Asyncronous Cleanup Thread").start();
		}
		return lReturn;
	}

	/**
	 * Starts a timer to terminate a job when the TimeOut has occured if Timeout
	 * is greater than 0
	 * 
	 * @param aTimeout
	 *            The length of time before the job should be canceled.
	 * @return The Timer object which controls the timeout.
	 */
	public static Timer startTimer(final int aTimeout) {
		Timer lTimeoutTimer = new Timer(false);
		lTimeoutTimer.schedule(new TimerTask() {
			public void run() {
				STOP = true;
				this.cancel();
			}
		}, (aTimeout <= 0 || aTimeout >= TIMEOUT_MAX) ? TIMEOUT_MAX : aTimeout);
		return lTimeoutTimer;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws TimeLimitExceededException
	 * 
	 * @see com.symbian.driver.core.controller.tasks.Execute#doCleanUp(boolean)
	 */
	public boolean doCleanUp(final boolean aWaitForTimeout) throws TimeLimitExceededException {
		LOGGER.entering("ExecuteOnHost", "doCleanUp");

		if (iChildProcess != null) {

			while (iChildProcess != null) {
				try {
					Thread.sleep(POLL_SLEEP);
					iChildProcess.exitValue();
					shutdownProcess();
					break;
				} catch (NullPointerException lNullPointerException) {
					shutdownProcess();
					break;
				} catch (IllegalThreadStateException lIllegalThreadStateException) {
					if (STOP || !aWaitForTimeout) {
						iChildProcess.destroy();
						shutdownProcess();
						throw new TimeLimitExceededException("ExecuteOnHost timed out.");
					}
				} catch (InterruptedException lInterruptedException) {
					LOGGER.log(Level.SEVERE, "ExecuteOnHost failed to poll thread.", lInterruptedException);
				}
			}

			int[] lPidAfter = ProcessHandler.getPidList(iCmdName);

			for (int lPIDIndex = 0; lPIDIndex < lPidAfter.length; lPIDIndex++) {
				LOGGER.info("Found current process " + iCmdName + " PID: " + lPidAfter[lPIDIndex]);
			}

			for (int lPidIndexAfter = 0; lPidIndexAfter < lPidAfter.length; lPidIndexAfter++) {
				if (iPidBefore.length > 0) {
					for (int lPidIndexBefore = 0; lPidIndexBefore < iPidBefore.length; lPidIndexBefore++) {
						if (lPidAfter[lPidIndexAfter] == iPidBefore[lPidIndexBefore]) {
							LOGGER.info("Killing process PID: " + lPidAfter[lPidIndexAfter]);
							ProcessHandler.kill(lPidAfter[lPidIndexAfter]);
						}
					}
				} else {
					LOGGER.info("Killing process PID: " + lPidAfter[lPidIndexAfter]);
					ProcessHandler.kill(lPidAfter[lPidIndexAfter]);
				}
			}

			iChildProcess = null;

			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	private void shutdownProcess() {
		if (iTimeoutTimer != null) {
			iTimeoutTimer.cancel();
			iTimeoutTimer = null;
		}

		if (iChildProcess != null) {
			iChildProcess.destroy();
			iChildProcess = null;
		}

		// Give time for the StreamGlobbers to shutdown.
		try {
			Thread.sleep(POLL_SLEEP);
		} catch (InterruptedException lInterruptedException) {
			LOGGER.log(Level.WARNING, "Could not sleep before killing the stream globbers.", lInterruptedException);
		}

		if (iStreamGobblerOutput != null) {
			Thread lStreamGobblerInputKill = iStreamGobblerOutput;
			iStreamGobblerOutput = null;
			lStreamGobblerInputKill.interrupt();
		}

		if (iStreamGobblerError != null) {
			Thread lStreamGobblerOutputKill = iStreamGobblerError;
			iStreamGobblerError = null;
			lStreamGobblerOutputKill.interrupt();
		}
	}

	public String toString() {
		return "ExecuteOnHost:\n\t- Working Directory: " + iWorkingDirectory + "\n\t- Command: " + iCmd;
	}

	public boolean isRunning() {
		try {
			if (iChildProcess != null) {
				iChildProcess.exitValue();
				return false;
			}
		} catch (IllegalThreadStateException lIllegalThreadStateException) {
			return true;
		}
		return false;
	}

	public String getOutput() {

		if (iStreamGobblerOutput != null) {
			try {
				LOGGER.fine("StreamGlobber Outuput wasn't finished");
				iStreamGobblerOutput.join();
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "The Standard Out Stream was interuputed", lInterruptedException);
			}
		}

		if (iStreamGobblerError != null) {
			try {
				LOGGER.fine("StreamGlobber Error wasn't finished");
				iStreamGobblerError.join();
			} catch (InterruptedException lInterruptedException) {
				LOGGER.log(Level.WARNING, "The Standard Error Stream was interuputed", lInterruptedException);
			}
		}

		if (iOutputBuffer.length() == 0) {
			LOGGER.fine("The log is empty.");
		}

		return iOutputBuffer.toString();
	}
}