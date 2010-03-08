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


package com.symbian.driver.plugins.comms.stat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;

/**
 * StatProcess implements all non files related commands - run a command - poll
 * device ...
 */
final class StatProcess implements IDeviceComms.ISymbianProcess {

	/** Logger. */
	private static final Logger LOGGER = Logger.getLogger(StatProcess.class.getName());

	/** process polling interval. */
	private static final long POLL_SLEEP = 1000;

	/** STAT max timeout. */
	private static int TIMEOUT_MAX = 36000000;

	/** Singleton to STAT. */
	private StatProxy iStatProxy = new StatProxy();

	/** stop indicator. */
	private static boolean STOP = false;

	/** Process id. */
	private String iPid = null;

	/** timer for processes. */
	private Timer iTimeoutTimer = null;

	private StringBuffer iOutputBuffer = new StringBuffer();

	/**
	 * getErrorStream : not supported
	 * 
	 * @throws UnsupportedOperationException
	 * 
	 */
	public InputStream getErrorStream() {
		throw new UnsupportedOperationException("STAT does not support streaming of ERROR/STDERR stream.");
	}

	/**
	 * getInputStream: not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public InputStream getInputStream() {

		throw new UnsupportedOperationException("STAT does not support streaming of INPUT/STDIN stream.");
	}

	/**
	 * getOutputStream : not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public OutputStream getOutputStream() {
		return new ByteArrayOutputStream();
		// throw new UnsupportedOperationException("STAT does not support
		// streaming of OUTPUT/STDOUT stream.");
	}

	/**
	 * @see com.symbian.driver.core.extension.ISymbianProcessBuilder.ISymbianProcess#join()
	 * 
	 * Uses STAT to poll the executable by doing the following:
	 * <nl>
	 * <li> Poll for PID and sleep
	 * <li> Check if process has completed normally
	 * <li> Stop if polling failed 3 times, if the stop() method is called, or
	 * if process timedout.
	 * </nl>
	 * 
	 */
	public boolean join() {
		LOGGER.entering("StatProcess", "join");
		int lRetry = 0;

		try {
			while (true) {
				LOGGER.info("Polling PID: " + iPid);
				JStatResult lPollResult = null;

				// 1. Poll + Sleep
				try {
					Thread.sleep(POLL_SLEEP);
				} catch (InterruptedException lInterruptedException) {
					// ignore interruption
					LOGGER.fine("Polling sleep interrupted.");
				}
		
				try {
					lPollResult = iStatProxy.getStat().poll(iPid);
				} catch (JStatException lJStatException) {
					int lRet = lJStatException.getResult().getReturnedValue();
					// if 155/131 try again.
					if ((lRet == 155 || lRet == 131) && lRetry < 3) {
						LOGGER.log(Level.FINE, "JStat returned with an internal 155/131 when polling. Trying again: ",
								lJStatException);
						lRetry++;
						LOGGER.log(Level.SEVERE, "Failed to poll pid :" + iPid + " after 3 tries.");
						continue;
					} else {
						// try to kill the process before returning.
						LOGGER.info("Could not poll process, Trying to kill it - PID=" + iPid);
						try {
							iStatProxy.getStat().kill(iPid);
						} catch (JStatException lJException) {
							LOGGER.log(Level.SEVERE, "Could not kill process: "  + iPid, lJException);
						}
						throw lJStatException;
					}
				}

				// reset lRetry.
				lRetry = 0;

				// 2. pid completed
				if (lPollResult != null
						&& (lPollResult.getReturnedValue() == 0 || lPollResult.getReceivedData().equals("0"))) {
					LOGGER.info("Process with PID: " + iPid + " finished.");
					break;
				}

				// 3. check if timeout reached.
				if (STOP) {
					LOGGER.info("Timeout reached, Trying to kill it PID=" + iPid);
					try {
						iStatProxy.getStat().kill(iPid);
					} catch (JStatException lJException) {
						LOGGER.log(Level.SEVERE, "Could not kill process: "  + iPid, lJException);
					}
					throw new TimeLimitExceededException("Command timed out.");
				}
			}
			return true;

		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE, "STAT exception", lJStatException);
		} finally {
			LOGGER.exiting("StatProcess", "join");
			stopTimer();
		}

		return false;
	}

	/**
	 * stopTimer
	 */
	private void stopTimer() {
		if (iTimeoutTimer != null) {
			iTimeoutTimer.cancel();
		}

		iTimeoutTimer = null;
		iPid = null;
	}

	/**
	 * Starts a timer to terminate a job when the TimeOut has occured if Timeout
	 * is greater than 0
	 * 
	 * @param aTimeout
	 *            The length of time before the job should be canceled.
	 * @return The Timer object which controls the timeout.
	 */
	private static Timer startTimer(final int aTimeout) {
		try {
			TIMEOUT_MAX = TDConfig.getInstance().getPreferenceInteger(TDConfig.TOTAL_TIMEOUT);
		} catch (ParseException lParseException) {
			// ignore , we have a default value.
		}
		Timer lTimeoutTimer = new Timer(false);
		lTimeoutTimer.schedule(new TimerTask() {
			public void run() {
				LOGGER.log(Level.SEVERE, "Time out : " + aTimeout + " ms reached.");
				STOP = true;
			}
		}, (aTimeout == 0) ? TIMEOUT_MAX : aTimeout);

		return lTimeoutTimer;
	}

	/**
	 * Stop a process
	 * 
	 * @param none
	 * @return : boolean success/fail
	 */
	public boolean stop() {
		STOP = true;
		return true;
	}

	/**
	 * runCommand:
	 * 
	 * @param :
	 *            String aCommand : the command to run
	 * @param :
	 *            List<String> aArguments : a list of arguments
	 * @param :
	 *            int aTimeout : a command timeout
	 * @param :
	 *            boolean aWait : wait for end of command true/false.
	 * 
	 * @return boolean success/fail
	 */
	public boolean runCommand(String aCommand, List<String> aArguments, int aTimeout, boolean aWait) {
		LOGGER.entering("StatProcess2", "runCommand");
		JStatResult lRunResult = null;
		STOP = false;

		// Start the STAT command
		try {
			if (aArguments == null || aArguments.size() == 0) {
				lRunResult = iStatProxy.getStat().run(aCommand);
			} else {
				String lArgs = null;
				Iterator iter = aArguments.iterator();
				if (iter.hasNext()) {
					lArgs = (String) iter.next();
				}
				while (iter.hasNext()) {
					lArgs = lArgs + " " + (String) iter.next();
				}
				LOGGER.info("Executing command " + aCommand + (aArguments != null ? " " + aArguments.toString() : ""));
				lRunResult = iStatProxy.getStat().run(aCommand, lArgs);
			}

			if (lRunResult != null && lRunResult.getReturnedValue() == 13) {

				iPid = lRunResult.getReceivedData();

				LOGGER.info("PID of process \"" + aCommand + (aArguments != null ? " " + aArguments.toString() : "")
						+ "\": " + iPid);

				if (iPid != null && aTimeout >= 0) {
					// launch a timer in the background
					iTimeoutTimer = startTimer(aTimeout);
				}
				if (aWait && !join()) {
						LOGGER.info("A Problem happened when waiting for pid : " + iPid + ". See previous errors.");
						return false;
				}
			} else {
				LOGGER.info("Executing \"" + aCommand + (aArguments != null ? " " + aArguments.toString() : "")
						+ "\" return with errors: " + lRunResult.toString());
				return false;
			}

		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "STAT time limit exeeded", lTimeLimitExceededException);
			return false;
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE, "STAT exception", lJStatException);
			return false;
		}

		LOGGER.exiting("StatProcess", "runCommand");
		return true;
	}

	/**
	 * Install a sis package on the device.
	 * 
	 * @param :
	 *            File aSymbianFile
	 * @return : boolean success/fail
	 */
	public boolean install(File aSymbianFile, File aPkg) {
		LOGGER.info("Installing package " + aSymbianFile.toString());
		JStatResult lResult = null;
		try {
			lResult = iStatProxy.getStat().installFile(aSymbianFile.toString());
			if (lResult.getReturnedValue() == 13) {
				return true;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Stat time limit exceeded.", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE, "Stat Exception: ", lJStatException);
			if (lJStatException.getResult().getErrorMessage().indexOf("10257") >= 0
					|| lJStatException.getResult().getErrorMessage().indexOf("10147") >= 0) {
				// If failed then check each file one by one.
				isPackageInstalled(aPkg);
			}
		}
		LOGGER.log(Level.SEVERE, "Failed to install sis package : " + aSymbianFile);
		return false;
	}

	/**
	 * uninstall a sis pacakge
	 * 
	 * @param :
	 *            String aUid
	 * @return boolean success/fail
	 */
	public boolean uninstall(String aUid) {
		LOGGER.info("uninstalling UId " + aUid);
		JStatResult lResult = null;

		try {
			lResult = iStatProxy.getStat().uninstallFile(aUid);
			if (lResult.getReturnedValue() == 13) {
				return true;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "STAT time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJException) {
			LOGGER.log(Level.SEVERE, "Stat Exception: ", lJException);
		}
		LOGGER.log(Level.SEVERE, "Failed to uninstall UID " + aUid );
		return false;
	}

	// 
	public boolean captureScreen() {
		try {
			iOutputBuffer.append(iStatProxy.getStat().getScreenCapture());
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "STAT time limit exeeded", lTimeLimitExceededException);
			return false;
		} catch (JStatException lJException) {
			LOGGER.log(Level.SEVERE, "Stat Exception: ", lJException);
			return false;
		}
		return true;
	}

	/**
	 * Confirms if a Package file has been installed by iterationg through the
	 * package for each file.
	 * 
	 * @return <code>true</code> if the Package File has been installed,
	 *         <code>false</code> otherwise.
	 */
	public boolean isPackageInstalled(File aPkg) {
		StringBuffer lErrorMessage = new StringBuffer();
		lErrorMessage.append("The status of all the files on the systems is:\n");

		if (aPkg == null) {
			return false;
		}

		boolean lIsPackageInstalled = true;
		try {

			BufferedReader lSisPackageReader = new BufferedReader(new FileReader(aPkg));

			String lReadLine = null;
			while ((lReadLine = lSisPackageReader.readLine()) != null) {
				String[] lSplitFileLoc = lReadLine.split("\" *- *\"");

				if (lSplitFileLoc.length == 2) {
					String lSymbianFile = lSplitFileLoc[1].replaceAll("\"", "");

					// Check that the file is installed.
					try {
						iStatProxy.getStat().checkLocation(lSymbianFile.replace("$:", ILiterals.C));
						// If no error occurs than the file is installed
						lErrorMessage.append("\t- " + lSymbianFile.toString()
								+ ":\t would overwrite an already installed file, causing an Eclipsing Problem.\n");

					} catch (JStatException lJStatException) {
						// If error occurs than the file is not installed.
						lIsPackageInstalled = false;
					}
				}
			}

			lErrorMessage
					.append("All other files would not overwrite. If the problem persists please ensure that there is no Binary or UID Eclipsing Issues.");

			lSisPackageReader.close();

			LOGGER.severe(lErrorMessage.toString());

		} catch (FileNotFoundException lFileNotFoundException) {
			LOGGER.log(Level.SEVERE, "Package file not found.", lFileNotFoundException);
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Could not get check if the file has already been installed.",
					lTimeLimitExceededException);
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Package file had an IO error.", lIOException);
		}
		return lIsPackageInstalled;
	}

}