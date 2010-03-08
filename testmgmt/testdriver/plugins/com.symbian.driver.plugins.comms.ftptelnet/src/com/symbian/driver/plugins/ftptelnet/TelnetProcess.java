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


package com.symbian.driver.plugins.ftptelnet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IDeviceComms;

/**
 * StatProcess implements all non files related commands - run a command - poll
 * device ...
 */
public class TelnetProcess implements IDeviceComms.ISymbianProcess, TelnetNotificationHandler {

	private static final Logger LOGGER = Logger.getLogger(TelnetProcess.class.getName());

	private static int TIMEOUT_MAX = 36000000;

	private String iIP = null;

	private int iPort = 23;

	private TelnetClient iClient = null;

	private InputStream iInputStream = null;

	private PrintStream iOutputStream = null;

	private static final String iPrompt = "localhost# ";

	private static final String iEnter = "\r\n";

	private static final String iSpace = " ";

	private static final int iCommandTimeOut = 10000;

	private static final int iInstallTimeOut = 300000;

	private static int iProcessTimeout = 0;

	private Timer iTelnetTimer = null;

	private static boolean iStopReading = false;

	private static String iTransport = null;

	private static Hashtable<String, TelnetProcess> iTelnetInstances = new Hashtable<String, TelnetProcess>();

	/**
	 * getInstance : gets the first instance from the list of instances. If
	 * there are no instances, it will try to create an instance with transport
	 * from TDConfig.
	 * 
	 * @return FtpTransfer instance (connected to the server if possible however
	 *         not guaranteed)
	 */
	public static synchronized TelnetProcess getInstance() {
		TelnetProcess lInstance = null;
		if (iTelnetInstances.isEmpty()) {
			// create a new one with transport from config
			try {
				String lTransport = TDConfig.getInstance().getPreference(TDConfig.TRANSPORT);
				lInstance = new TelnetProcess(lTransport);
				iTelnetInstances.put(lTransport, lInstance);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not create an TELNET session.", lParseException);
			}
		} else {
			// get the first instance.
			lInstance = iTelnetInstances.elements().nextElement();
		}
		return lInstance;
	}

	/**
	 * getInstance : get FtpTransfer instance for the provided transport if
	 * created or create a new one.
	 * 
	 * @param String
	 *            aTransport
	 * @return a FtpTransfer instance (connected to the server if possible
	 *         however not guaranteed)
	 */
	public static synchronized TelnetProcess getInstance(String aTransport) {
		TelnetProcess lInstance = null;
		if (iTelnetInstances.containsKey(aTransport)) {
			// get the appropriate instance
			lInstance = (TelnetProcess) iTelnetInstances.get(aTransport);
		} else {
			// create a new one with the transport provided
			try {
				lInstance = new TelnetProcess(aTransport);
				iTelnetInstances.put(aTransport, lInstance);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not create an TELNET session.", lParseException);
			}
		}
		return lInstance;
	}

	private TelnetProcess(String aTransport) throws ParseException {
		try {
		TIMEOUT_MAX = TDConfig.getInstance().getPreferenceInteger(TDConfig.TOTAL_TIMEOUT);
		} catch (ParseException lParseException) {
			//ignore we have a default value.
		}
		// check transport is valid
		iTransport = aTransport;
		if (!isTransportValid(iTransport)) {
			throw new ParseException("Transport " + iTransport + " incorrect.");
		}
		// try to connect to Telnet Server
		connectTelnet();

	}

	public boolean connectTelnet() {
		if (isTelnetConnected()) {
			return true;
		}
		// Connect to FTP
		iClient = new TelnetClient();

		try {
			iClient.connect(iIP, iPort);
			LOGGER.info("Telnet connection established with transport : " + iIP + ":" + iPort);
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "Telnet Client: Failed to connect " + lException.getMessage());
			return false;
		}
		// connected
		// Get input and output stream references
		iInputStream = iClient.getInputStream();
		iOutputStream = new PrintStream(iClient.getOutputStream());

		// Advance to a prompt
		try {
			readUntilPrompt(iCommandTimeOut, false);
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "Failed to get prompt from Telnet Server");
			//disconnectTelnet();
			return false;
		}

		return true;
	}

	public void disconnectTelnet() {
		if (isTelnetConnected()) {
			try {
				try {
					write("exit");
				} catch (Exception e) {
					e.printStackTrace();
				}
				// disconnect
				iClient.disconnect();
				//if (iTransport != null) {
					iTelnetInstances.remove(iTransport);
				//}
				iClient = null;
				iTransport = null;
				LOGGER.info("Telnet with transport " + iIP + ":" + iPort + " Disconnected.");
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Failed to disconnect Telnet: " + lIOException.getMessage());
			}
		}
	}

	private boolean isTelnetConnected() {
		try {
			if (iClient != null && iClient.isConnected() && iClient.sendAYT(1000)) {
				return true;
			}
		} catch (Exception e) {
		} 
		return false;
	}

	/**
	 * checks if the transport is correct
	 * 
	 * @param aTransport
	 * @return true/false
	 */
	private boolean isTransportValid(String aTransport) {
		// ip transport should be in the form tcp:d+.d+.d+.d+:port
		Pattern lPattern = Pattern.compile("tcp:(\\d+\\.\\d+\\.\\d+\\.\\d+)(:\\d+)?");
		Matcher lMatcher = lPattern.matcher(aTransport);
		if (lMatcher.matches()) {
			iIP = lMatcher.group(1);
			try {
				iPort = Integer.parseInt(TDConfig.getInstance().getPreference(
						FtpTelnetLaunchConstants.PLUGIN_ID + ":" + FtpTelnetLaunchConstants.TELNETPORT));
			} catch (Exception lException) {
				LOGGER.log(Level.WARNING, "Configuration error.", lException);
			}
			return true;
		} else {
			return false;
		}
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
		LOGGER.entering("ExecuteOnDevice", "join");
		boolean lReturn = true;
		// read untill we get a prompt
		try {
			readUntilPrompt(iProcessTimeout, false);
		} catch (Exception lException) {
			lReturn = false;
		}
		iProcessTimeout = 0;
		LOGGER.exiting("ExecuteOnHost", "join");
		return lReturn;
	}

	private static Timer startTelnetTimer(final int aTimeout) {
		Timer lTimeoutTimer = new Timer("Telnet client Timer", false);
		iStopReading = false;
		lTimeoutTimer.schedule(new TimerTask() {
			public void run() {
				iStopReading = true;
			}
		}, aTimeout);
		return lTimeoutTimer;
	}

	public boolean install(File aSymbianFile, File aPkg) {
		boolean lReturn = true;
		
		LOGGER.info("Installing " + aSymbianFile.toString());

		String lCommand = "test_install ";
		String lReturnedString = null;

		// check server

		if (pollDevice()) {

			try {
				String lPath = aSymbianFile.getPath();
				
				lPath = lPath.replace("$:", FtpTransfer.getInstance().getSystemDrive() + ":");					
				
				
				lCommand = lCommand + lPath.replaceAll("/", "\\\\");
				// cd to z:
				write("cd z:");
				lReturnedString = readUntilPrompt(iCommandTimeOut, false);

				Thread.sleep(500);

				// call install
				write(lCommand);
				lReturnedString = readUntilPrompt(iInstallTimeOut, false);
				String[] lParts = lReturnedString.split(iEnter);

				// get status
				int lErr = 1;
				//  when zsh $status is fixed, replace the following if-else
				// by getStatus()

				if (lParts.length > 1) {
					String errLine = lParts[lParts.length - 2];
					Pattern lPattern = Pattern.compile("^Return Code: (-?\\d+)$");
					Matcher lMatcher = lPattern.matcher(errLine);
					if (lMatcher.matches()) {
						lErr = Integer.parseInt(lMatcher.group(1));
						if (lErr != 0) {
							lReturn = false;
							LOGGER.log(Level.SEVERE, "command " + lCommand + " failed with error : " + lErr);
							if (lErr == -10257) {
								isPackageInstalled(aPkg);
							}
						}
					}
				} else {
					LOGGER.info("TD did not recieve install error code from device.");
				}

			} catch (Exception lException) {
				lReturn = false;
			}
		}
		return lReturn;
	}

	public boolean uninstall(String aUid) {
		LOGGER.info("Un-Installing " + aUid);
		boolean lReturn = true;

		String lCommand = "test_uninstall " + aUid;

		if (pollDevice()) {
			try {
				
				// cd to z:
				write("cd z:");
				readUntilPrompt(iCommandTimeOut, false);

				// execute the install command
				write(lCommand);
				String lReturnedString = readUntilPrompt(iInstallTimeOut, false);

				// get command status
				// int lErr = getStatus();
				String[] lParts = lReturnedString.split(iEnter);

				// get status
				int lErr = 1;
				//  when zsh $status is fixed, replace the following if-else
				// by getStatus()

				if (lParts.length > 1) {
					String errLine = lParts[lParts.length - 2];
					Pattern lPattern = Pattern.compile("^Return Code: (-?\\d+)$");
					Matcher lMatcher = lPattern.matcher(errLine);
					if (lMatcher.matches()) {
						lErr = Integer.parseInt(lMatcher.group(1));
					}
				} else {
					LOGGER.info("TD did not recieve install error code from device.");
				}

				if (lErr != 0) {
					lReturn = false;
					LOGGER.log(Level.SEVERE, "command " + lCommand + " failed.");
				}
			} catch (Exception lException) {
				lReturn = false;
			}
		}
		return lReturn;
	}

	/**
	 * runCommand : sends a command to telnet server - send the command - read
	 * until the prompt - try the find the pid
	 * 
	 */
	public boolean runCommand(String aCommand, List<String> aArguments, int aTimeout, boolean aWait) {

		boolean lReturn = true;
		boolean log = false;
		StringBuffer lCommand = new StringBuffer();
		
		if (aTimeout == 0) {
			aTimeout = TIMEOUT_MAX;
		}
		iProcessTimeout = aTimeout;

		// check the device is fine
		if (pollDevice()) {
			// set the command
			lCommand.append(aCommand + iSpace);

			for (Iterator iter = aArguments.iterator(); iter.hasNext();) {
				String lArg = iter.next() + iSpace;
				
				lArg = lArg.replaceAll("\\$:", FtpTransfer.getInstance().getSystemDrive() + ":");
				lCommand.append(lArg);
			}

			if (aCommand.equalsIgnoreCase("testexecute.exe") && aWait) {
				// add the pipe
				lCommand.append("-pipe $PIPE ");
				log = true;
			}
			
			
			if (!aWait) {
				//open a second connection run the command and return.
				LOGGER.info("Executing : " + lCommand.toString() + ". And don't wait for termination.");
				TelnetProcess lProcess = null;
				try {
					lProcess = new TelnetProcess(iTransport);
					lProcess.runInBackground(lCommand.toString());
				} catch (Exception lException) {
					LOGGER.log(Level.SEVERE, "telnet error : ", lException);
					lReturn = false;
				} finally {
					lProcess.disconnectTelnet();
				}
				return lReturn;
			}

			// run
			try {
				// cd to z:
				write("cd z:");
				readUntilPrompt(iCommandTimeOut, false);

				LOGGER.info("Executing : " + lCommand.toString() + ". And wait for termination.");
				write(lCommand.toString());

				// wait for the prompt
				String lBuffer = readUntilPrompt(aTimeout, log);

			} catch (Exception lException) {
				lReturn = false;
				// if the command timed out then kill the process
				if (iStopReading) {
					TelnetProcess lProcess = null;
					try {
						LOGGER.info("Command " + lCommand + " timed out. Will try to kill the associated process.");
						lProcess = new TelnetProcess(iTransport);
						// get process pid
						String lPid = lProcess.getProcessPID(aCommand);
						if (lPid != null) {
							LOGGER.info("Killing process : " + lPid);
							// kill the process
							lProcess.killProcess(lPid);
						}
					} catch (Exception lException2) {
						LOGGER.log(Level.SEVERE, "telnet error : ", lException2);
					} finally {
						lProcess.disconnectTelnet();
					}
				}
				// continue reading until prompt anyway.
				try {
					readUntilPrompt(0, false);
				} catch (Exception lException2) {
					LOGGER.log(Level.SEVERE, "telnet error : ", lException2);
				}
			}
		}

		return lReturn;
	}
	
	private void runInBackground(String aCommand) throws Exception {
		LOGGER.info("Running command in background :" + aCommand);
		write("cd z:");
		readUntilPrompt(iCommandTimeOut, false);
		write(aCommand);
	}

	private String getProcessPID(String aCommand) throws Exception {
		LOGGER.info("Getting Process ID for :" + aCommand);
		String lPid = null;
		// cd to z:
		write("cd z:");
		String lReturnedString = readUntilPrompt(iCommandTimeOut, false);

		write("ps -o comm,pid | grep " + aCommand);
		lReturnedString = readUntilPrompt(iCommandTimeOut, false);
		String[] lParts = lReturnedString.split(iEnter);
		if (lParts.length > 1) {
			String lLine = lParts[0];
			Pattern lPattern = Pattern.compile("^(\\d+).*$");
			Matcher lMatcher = lPattern.matcher(lLine);
			if (lMatcher.matches()) {
				lPid = lMatcher.group(1);
			}
		}
		LOGGER.info("PID = " + lPid);
		return lPid;
	}
	

	public boolean pollDevice() {
		LOGGER.fine("Polling device");
		boolean lResult = true;
		// check server
		try {
			if (connectTelnet()) {
				lResult = true;
			} else {
				lResult = false;
			}
		} catch (Exception lException) {
			lResult = false;
			LOGGER.log(Level.INFO, "Telnet server is not responding. Try to reconnect.");
			disconnectTelnet();
			connectTelnet();
		}
		return lResult;
	}

	private boolean write(String value) throws Exception {
		try {
			LOGGER.fine("Telnet Client sending : " + value);
			for (int i = 0; i < value.length(); i++) {
				iOutputStream.print(value.charAt(i));
				iOutputStream.flush();
				Thread.sleep(100);
			}
			iOutputStream.print(iEnter);
			iOutputStream.flush();
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "An error happned while writing to Telnet server", lException);
			throw lException;
		}
		return true;
	}

	private String readUntilPrompt(int aTimeOut, boolean log) throws Exception {
		// read until we find the prompt or timeout
		// set a timer
		iStopReading = false;
		if (aTimeOut > 0) {
			iTelnetTimer = startTelnetTimer(aTimeOut);
		}
		StringBuffer sb = null;
		try {
			char lastChar = ' ';
			// wait for the first data / timeout
			while (iInputStream.available() == 0 && !iStopReading) {
			}
			if (!iStopReading) {
				// this means we have some data
				sb = new StringBuffer();
				StringBuffer line = new StringBuffer();

				while (!iStopReading) {
					char ch = (char) iInputStream.read();
					line.append(ch);
					if (ch == '\n' && log) {
						// show the progress to the user
						LOGGER.fine(line.toString());
						line.setLength(0);
					}
					sb.append(ch);
					if (ch == lastChar) {
						if (sb.toString().endsWith(iPrompt)) {
							return sb.toString();
						}
					}
				}
			} else {
				throw new Exception("Telnet Client could not get prompt (and timedout)");
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "An error happened while reading from Telnet server", lException);
			throw lException;
		} finally {
			LOGGER.fine("Telnet Server replied with : " + sb);
			if (iTelnetTimer != null) {
				iTelnetTimer.cancel();
				iTelnetTimer = null;
			}
		}
		LOGGER.log(Level.SEVERE, "TelNet Client Expecting : " + iPrompt + " - Got :" + sb);
		throw new Exception("TelNet Client Expecting : " + iPrompt + " - Got :" + sb);
	}

	public InputStream getErrorStream() {
		
		return null;
	}

	public InputStream getInputStream() {
		
		return iInputStream;
	}

	public OutputStream getOutputStream() {
		
		return iOutputStream;
	}

	public void receivedNegotiation(int aNegotiationCode, int aOptionCode) {
		String command = null;
		if (aNegotiationCode == TelnetNotificationHandler.RECEIVED_DO) {
			command = "DO";
		} else if (aNegotiationCode == TelnetNotificationHandler.RECEIVED_DONT) {
			command = "DONT";
		} else if (aNegotiationCode == TelnetNotificationHandler.RECEIVED_WILL) {
			command = "WILL";
		} else if (aNegotiationCode == TelnetNotificationHandler.RECEIVED_WONT) {
			command = "WONT";
		}
		LOGGER.info("Received " + command + " for option code " + aOptionCode);
	}

	public boolean captureScreen() {
		
		return false;
	}

	public boolean rebootDevice() {

		LOGGER.info("Rebooting device");
		boolean lReturn = true;

		String lCommand = "test_restart";

		// check server

		if (pollDevice()) {
			try {
				write("cd z:");
				readUntilPrompt(iCommandTimeOut, false);
				write(lCommand);
			} catch (Exception lException) {
				lReturn = false;
				LOGGER.log(Level.SEVERE, "Telnet Connection problem", lException);
			}
		}
		return lReturn;
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

		boolean lIsPackageInstalled = false;

		try {
			BufferedReader lSisPackageReader = new BufferedReader(new FileReader(aPkg));

			String lReadLine = null;
			while ((lReadLine = lSisPackageReader.readLine()) != null) {
				String[] lSplitFileLoc = lReadLine.split("\" *- *\"");

				if (lSplitFileLoc.length == 2) {
					String lSymbianFile = lSplitFileLoc[1].replaceAll("\"", "");

					// Check that the file is installed.

					if (!FtpTransfer.getInstance().dir(new File(lSymbianFile)).isEmpty()) {
						lErrorMessage.append("\t- " + lSymbianFile.toString()
								+ ":\t would overwrite an already installed file, causing an Eclipsing Problem.\n");

						lIsPackageInstalled = true;
					}
				}
			}

			lErrorMessage
					.append("All other files would not overwrite. If the problem persists please ensure that there is no Binary or UID Eclipsing Issues.");

			lSisPackageReader.close();

			LOGGER.info(lErrorMessage.toString());

		} catch (FileNotFoundException lFileNotFoundException) {
			LOGGER.log(Level.SEVERE, "Package file not found.", lFileNotFoundException);
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Package file had an IO error.", lIOException);
		}

		return lIsPackageInstalled;
	}

	/**
	 * removeAllInstances: disconnect all instances and deletes them.
	 * 
	 * @return boolean success/fail (if any fails to disconnect)
	 */
	public static boolean removeAllInstances() {
		boolean lResult = true;
		Set<String> lkeySet = iTelnetInstances.keySet();
		for (Iterator iter = lkeySet.iterator(); iter.hasNext();) {

			TelnetProcess lInstance = (TelnetProcess) iTelnetInstances.get(iter.next());
			lInstance.disconnectTelnet();
			iTelnetInstances.remove(iter);

		}
		return lResult;
	}

	public boolean stop() {
		
		return false;
	}

	private void killProcess(String pid) throws Exception {
		LOGGER.info("Killing Process ID :" + pid);
		write("cd z:");
		String lReturnedString = readUntilPrompt(iCommandTimeOut, false);
		write("test_kill " + pid);
		lReturnedString = readUntilPrompt(iCommandTimeOut, false);
	}

	private int getStatus() throws Exception {
		int lErr = 0;
		write("echo $status");
		String lStatus = readUntilPrompt(iCommandTimeOut, false);

		lErr = Integer.parseInt(lStatus.split(iEnter)[0]);
		return lErr;
	}

}