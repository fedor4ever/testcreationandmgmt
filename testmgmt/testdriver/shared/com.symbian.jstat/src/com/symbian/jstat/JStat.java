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



package com.symbian.jstat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import com.symbian.utils.JarUtils;


/**
 * @author EngineeringTools
 */
public class JStat {
	
	/////////////////////////////////////
	// Types of JStat Commands
	/////////////////////////////////////
	
	/** Retrieves a file from the Symbian device to the host machine. */
	public static final int RETRIEVE_FILE = 0;

	/** Sends a file from the host machine to the Symbian device. */
	public static final int SEND_FILE = 1;

	/** Gets a screen capture from the Symbian device. */
	public static final int GET_SCREEN_CAPTURE = 2;

	/** Installs a SIS file on the Symbian device. */
	public static final int INSTALL_FILE = 3;

	/** Uninstalls a SIS file on the */
	public static final int UNINSTALL_FILE = 4;

	/** Lists the drivers on the Symbian device. */
	public static final int LIST_DRIVES = 5;

	/** Lists the files at a specific directory on the Symbian device. */
	public static final int LIST_FILES = 6;

	/** Deletes a file from the Symbian device. */
	public static final int DELETE = 7;

	/** Creates a folder on the Symbian device. */
	public static final int CREATE_FOLDER = 8;

	/** Removes a folder on the Symbian device. */
	public static final int REMOVE_FOLDER = 9;

	/** Runs a program/application on the Symbian device. */
	public static final int RUN = 10;

	/** Kills a process on the Symbian device. */
	public static final int KILL = 11;

	/** Polls a process on the Symbian device. */
	public static final int POLL = 12;
	
	/** Checks whether a file/dir exists. */
	public static final int CHECK_LOCATION = 13;
	
	/** Retrieve device infos */
	public static final int DEVICE_INFO = 14;
	
	/** Restart device */
	public static final int RESTART = 15;

	/** Polls a process on the Symbian device. */
	public static final String[] COMMANDS = { "Retrieve File", "Send File", "Get Screen Capture", "Install File",
			"Uninstall File", "List Drivers", "List Files", "Delete", "Create Folder", "Remove Folder", "Run", "Kill",
			"Poll", "Check Location", "Device infos", "restart device" };

	/////////////////////////////////////
	// Private JStat Variables
	/////////////////////////////////////
	
	/** JStat Internal Timeout. This timeout doesn't work though for I/O blocks. */
	private static long sTimeout = 600000;

	/** The output stream for JStat. */
	private PrintStream iPrintStream = null;

	/** The transport used by JStat. */
	private String iTransport = null;

	/** Internal Buffer for JStat. */
	final static int BUFFER = 2048;
	
	/** Stat Work Directory. */
	private static final String STAT_WORK = "c:/apps/stat/work";
	
	/** If STAT needs a delay before starting. */
	private boolean iNeedsDelay = false;

	/** A list of JSTAT instances depending on transport type. */
	private static Hashtable jstatInstances = new Hashtable();
	
	/** A simple semphore too allow multiple instances of JStat. */
	private Semaphore semaphore = new Semaphore(1);
	
	/** The size of the Packets for STAT. */
	private int iPacketSize = -1;
	
	/** The logger for the JSTAT class. */
	protected static final Logger LOGGER = Logger.getLogger(JStat.class.getName());
	
	static {
		LOGGER.entering(JStat.class.getName(), "Loading DLLs");
		
		//Load JStat.dll
		try {
			LOGGER.fine("Loading JStat.dll");
			File lJStatDll = JarUtils.extractResource(JStat.class, "/resource/JStat.dll");
			System.load(lJStatDll.getAbsolutePath());
		} catch (IOException lIOException) {
			try {
				File lJStatDll = new File("../../shared/com.symbian.jstat/resource/JStat.dll");
				System.load(lJStatDll.getAbsolutePath());
			} catch (NoClassDefFoundError lNoClassDefFoundError) {
				LOGGER.log(Level.SEVERE, "JStat.dll could not be extracted.", lNoClassDefFoundError);
				System.exit(-1);
			}
		} catch (Throwable lThrowable) {
			LOGGER.log(Level.SEVERE, "JStat could not load jstat.dll.", lThrowable);
			System.exit(-1);
		}
		
		//Load Stat.dll
		try {
			LOGGER.fine("Loading Stat.dll");
			File lStatDll = JarUtils.extractResource(JStat.class, "/resource/stat.dll");
			System.load(lStatDll.getAbsolutePath());
		} catch (IOException lIOException) {
			try {
				File lStatDll = new File("../../shared/com.symbian.jstat/resource/stat.dll");
				System.load(lStatDll.getAbsolutePath());
			} catch (NoClassDefFoundError lNoClassDefFoundError) {
				LOGGER.log(Level.SEVERE, "JStat.dll could not be extracted.", lNoClassDefFoundError);
				System.exit(-1);
			}
		} catch (Throwable lThrowable) {
			LOGGER.log(Level.SEVERE, "JStat could not load stat.dll.", lThrowable);
			System.exit(-1);
		}
		
		//Load SymbianUsb.dll
		try {
			LOGGER.fine("Loading SymbianUsb.dll");
			File lSymbianUsbDll = JarUtils.extractResource(JStat.class, "/resource/SymbianUsb.dll");
			System.load(lSymbianUsbDll.getAbsolutePath());
		} catch (IOException lIOException) {
			try {
				File lSymbianUsbDll = new File("../../shared/com.symbian.jstat/resource/SymbianUsb.dll");
				System.load(lSymbianUsbDll.getAbsolutePath());
			} catch (NoClassDefFoundError lNoClassDefFoundError) {
				LOGGER.log(Level.SEVERE, "SymbianUsb.dll could not be extracted. Please ensure that SymbianUsb.dll is on your path.", lIOException);
				System.exit(-1);
			}
		} catch (Throwable lThrowable) {
			LOGGER.log(Level.SEVERE, "JStat could not load SymbianUsb.dll.", lThrowable);
			System.exit(-1);
		}
		
		LOGGER.exiting(JStat.class.getName(), "DLLs succefully loaded");
	}
	
	///////////////////////////////////////////
	// JStat Singleton Creation
	///////////////////////////////////////////
	
	/**
	 * @return The singleton instance of JStat
	 */
	public static JStat getInstance() {
		LOGGER.entering(JStat.class.getName(), "Constructor without Transport");
		
		JStat lJStatInstance = null;
		
		if(!jstatInstances.isEmpty())
		{
			lJStatInstance = (JStat)jstatInstances.elements().nextElement();
		}
		
		return lJStatInstance;

	}
	
	
	/**
	 * @param aTransport
	 * @return The singleton instance of JStat
	 */
	public static JStat getInstance(final String aTransport) {
		LOGGER.entering(JStat.class.getName(), "Constructor with Transport");
		
		JStat lJStatInstance = null;
		
		if (jstatInstances.containsKey(aTransport)) 
		{
			lJStatInstance = (JStat) jstatInstances.get(aTransport);
		}
		else
		{
			lJStatInstance = new JStat(aTransport);
			jstatInstances.put(aTransport, lJStatInstance);
			
		}
		
		return lJStatInstance;

	}
	
	
	/**
	 * @param aTransport
	 * @param aTimeout 
	 * @return The singleton instance of JStat
	 */
	
	public static JStat getInstance(final String aTransport, long aTimeout) {
		LOGGER.entering(JStat.class.getName(), "Constructor with Transport and Timeout");
		
		JStat lJStatInstance = JStat.getInstance(aTransport);
		setTimeout(aTimeout);
		
		return lJStatInstance;
	}
	
	/**
	 * @param aTransport
	 * @param aTimeout
	 * @param aNeedsDelay
	 * @return The singleton instance of JStat
	 */
	public static JStat getInstance(final String aTransport, long aTimeout, boolean aNeedsDelay) {
		LOGGER.entering(JStat.class.getName(), "Constructor with Transport, Timeout and NeedsDelay");
		
		JStat lJStatInstance = JStat.getInstance(aTransport);
		setTimeout(aTimeout);
		lJStatInstance.setNeedsDelay(aNeedsDelay);
		
		return lJStatInstance;
	}
	
	/**
	 * Set the packet size to be used for the communication with the board.
	 * 
	 * @param aSize
	 */
	public void setPacketSize(int aSize) {
		iPacketSize = aSize;
	}
	
	/**
	 * Private constructor for the JStat singleton.
	 * 
	 * @param aTransport
	 */
	private JStat(final String aTransport) {
		setTransport(aTransport);
	}
	
	///////////////////////////////////////////
	// STAT Native Code
	///////////////////////////////////////////

	/**
	 * C Native method to get the current version of STAT.
	 * 
	 * @return The result code.
	 */
	private native String getStatVersion();
	
	
	///////////////////////////////////////////
	// JStat Switch
	///////////////////////////////////////////
	
	/**
	 * Switch to run a command with no paramters.
	 * 
	 * @param aStatCommand
	 *            The stat command. Use the JStat constants to choice which
	 *            command to run.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If the command run is incorrect or does not have the correct
	 *             number of parameters.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult runSwitch(int aStatCommand) throws JStatException, TimeLimitExceededException {
		switch (aStatCommand) {
		case GET_SCREEN_CAPTURE:
			return getScreenCapture();
		case LIST_DRIVES:
			return listDrives();
		case DEVICE_INFO:
			return getInfo();
		case RESTART:
			return restartBoard();
		default:
			throw new JStatException("Incorrect command or paramters.");
		}
	}

	/**
	 * @param aStatCommand
	 *            The stat command. Use the JStat constants to choice which
	 *            command to run.
	 * @param aFirstParam
	 *            The parameter for this command.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If the command run is incorrect or does not have the correct
	 *             number of parameters.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult runSwitch(int aStatCommand, String aFirstParam) throws JStatException,
			TimeLimitExceededException {
		switch (aStatCommand) {
		case INSTALL_FILE:
			return installFile(aFirstParam);
		case UNINSTALL_FILE:
			return uninstallFile(aFirstParam);
		case LIST_FILES:
			return listFiles(aFirstParam);
		case DELETE:
			return delete(aFirstParam);
		case RETRIEVE_FILE:
			return retrieveFile(aFirstParam);
		case CREATE_FOLDER:
			return createFolder(aFirstParam);
		case REMOVE_FOLDER:
			return removeFolder(aFirstParam);
		case RUN:
			return run(aFirstParam);
		case KILL:
			return kill(aFirstParam);
		case POLL:
			return poll(aFirstParam);
		case CHECK_LOCATION:
			return checkLocation(aFirstParam);
		default:
			throw new JStatException("Incorrect command or paramters.");
		}
	}

	/**
	 * @param aStatCommand
	 *            The stat command. Use the JStat constants to choice which
	 *            command to run.
	 * @param aFirstParam
	 *            The first parameter for this command.
	 * @param aSecondParam
	 *            The second parameter for this command.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If the command run is incorrect or does not have the correct
	 *             number of parameters.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult runSwitch(int aStatCommand, String aFirstParam, String aSecondParam) throws JStatException,
			TimeLimitExceededException {
		switch (aStatCommand) {
		case SEND_FILE:
			return sendFile(new File(aFirstParam), aSecondParam);
		case RETRIEVE_FILE:
			return retrieveFile(aFirstParam, new File(aSecondParam));
		case RUN:
			return run(aFirstParam, aSecondParam);
		default:
			throw new JStatException("Incorrect command or paramters.");
		}
	}
	
	///////////////////////////////////////////
	// JStat Setters and Getters
	///////////////////////////////////////////

	/**
	 * Sets the output stream for JStat.
	 * 
	 * @param aPrintStream
	 *            The ouput stream of JStat.
	 */
	public void setPrintStream(PrintStream aPrintStream) {
		iPrintStream = aPrintStream;
	}

	/**
	 * Sets the transport for JStat. This can be any of the following:
	 * <ul>
	 * <li>serial1</li>
	 * <li>serial2</li>
	 * <li>serial3</li>
	 * <li>usb1</li>
	 * <li>tcp, for example "tcp:192.168.0.3:3000"</li>
	 * <li>bt</li>
	 * </ul>
	 * 
	 * @param aTransport
	 *            The transport of JStat.
	 */
	public void setTransport(String aTransport) {
		
		iTransport = aTransport;
	}

	/**
	 * Sets the IP address for JStat. This must be a valid ip address. This is
	 * similar to the using {@link #setTransport(String)} with "tcp:\<IP\>".
	 * 
	 * @param aIp
	 *            The IP address for JStat.
	 */
	public void setTCP(String aIp) {
		
		iTransport = "tcp:" + aIp;
	}

	/**
	 * Sets the USB port for Jstat. This must be a valid port address. This is
	 * similar to the using {@link #setTransport(String)} with "usb\<PORT\>".
	 * 
	 * @param aPort
	 *            The USB port to use. Currently only 1 is valid.
	 */
	public void setUSB(String aPort) {
		iTransport = "usb" + aPort;
	}

	/**
	 * Sets the port for JStat. This must be a valid port address. This is
	 * similar to the using {@link #setTransport(String)} with "serial\<PORT\>".
	 * 
	 * @param aPort
	 *            The port to set for JStat.
	 */
	public void setSerial(String aPort) {
	
		iTransport = "serial" + aPort;
	}

	/**
	 * Sets the maximum packet size for STAT, overwriting the default values
	 * @param size
	 * 			Maximum packet size
	 */
	public void setMaxPacketSize(int size)
	{
		setPacketSize(size);
	}
	
	/**
	 * @return The internal timeout for JStat.
	 */
	public static long getTimeout()
	{
		return sTimeout;
	}

	/**
	 * @param aTimeout
	 */
	public static void setTimeout(long aTimeout)
	{
		sTimeout = aTimeout;
	}

	/**
	 * @param aNeedsDelay
	 */
	private void setNeedsDelay(boolean aNeedsDelay)
	{
		iNeedsDelay = aNeedsDelay;
	}
	
	///////////////////////////////////////////
	// STAT Commands
	///////////////////////////////////////////

	/**
	 * Copies a file from the PC to the Symbian device.
	 * 
	 * @param aOriginFile
	 *            The location of the PC side file to copy from.
	 * @param aDestPathFile
	 *            The location of the Device side file to copy to.
	 * @return The result of the JStat send file command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult sendFile(String aOriginFile, String aDestPathFile) throws JStatException,
			TimeLimitExceededException {
		
		JStatResult lResult = doCommand("<B><T" + aOriginFile + "," + aDestPathFile + "><E>");
		
		return lResult;
	}
	
	/**
	 * Copies a file from the PC to the Symbian device.
	 * 
	 * @param aOriginFile
	 *            The PC side file to copy from.
	 * @param aDestPathFile
	 *            The location of the Device side file to copy to.
	 * @return The result of the JStat send file command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult sendFile(File aOriginFile, String aDestPathFile) throws JStatException,
			TimeLimitExceededException {
		
		if (!aOriginFile.isFile()) {
			throw new JStatException("PC file doesn't exist: " + aOriginFile.toString());
		}

		File lStatFile = new File(STAT_WORK, aOriginFile.getName());

		try {
			JStatUtils.copy(aOriginFile, lStatFile);
		} catch (IOException lIOException) {
			throw new JStatException("IO Exception: " + lIOException.getMessage());
		}
		

		JStatResult lResult = doCommand("<B><T" + lStatFile.getName() + "," + aDestPathFile + "><E>");

		lStatFile.delete();

		return lResult;
	}
	

	/**
	 * Retrieves a file from the device side to the Stat Work directory.
	 * 
	 * @param aOriginPathFile
	 *            The location of the Device side file to retrieve.
	 * @return The result of the JStat retrieve file command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult retrieveFile(String aOriginPathFile) throws JStatException, TimeLimitExceededException {
		return doCommand("<B><R" + aOriginPathFile + ",><E>");
	}

	/**
	 * Retrieves a file from the device side to the PC.
	 * 
	 * @param aOriginPathFile
	 *            The location of the Device side file to retrieve.
	 * @param aDestFolder
	 *            The folder of where to place the recieved file.
	 * @return The result of the JStat retrieve file command.
	 * @throws JStatException
	 *             If the stat command fails.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult retrieveFile(String aOriginPathFile, String aDestFolder) throws JStatException,
			TimeLimitExceededException {
		return doCommand("<B><R" + aOriginPathFile + "," + aDestFolder + "><E>");
	}

	/**
	 * Retrieves a file from the device side to the PC.
	 * 
	 * @param aOriginPathFile
	 *            The location of the Device side file to retrieve.
	 * @param aDestPathFile
	 *            The location of where to place the recieved file.
	 * @return The result of the JStat retrieve file command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult retrieveFile(String aOriginPathFile, File aDestPathFile) throws JStatException,
			TimeLimitExceededException {
		JStatResult lResult = doCommand("<B><R" + aOriginPathFile + ",result><E>");

		File lStatFile = new File(STAT_WORK, "result" + File.separator + new File(aOriginPathFile).getName());

		if (!lStatFile.isFile()) {
			throw new JStatException("Could not retrieve file: " + aOriginPathFile);
		}

		if (!aDestPathFile.getParentFile().isDirectory() && !aDestPathFile.getParentFile().mkdirs()) {
			throw new JStatException("Could not create directory: " + aDestPathFile.getParent());
		}
		
		if (aDestPathFile.isFile()) {
			
			File lBackupFile = new File(aDestPathFile.getAbsolutePath() + "." + Math.round(Math.random() * 100000)  + ".backup");
			if (!aDestPathFile.renameTo(lBackupFile)) {
				LOGGER.log(Level.WARNING, "The destination file already exists: "
						+ aDestPathFile + ". It will be backedup to: " + lBackupFile);
			}
			
		}
		
		if (!lStatFile.renameTo(aDestPathFile)) {
			throw new JStatException("Could not copy retrieved file from STAT work directory.");
		}

		lStatFile.delete();

		return lResult;
	}

	/**
	 * Gets a screen capture of the Symbian device.
	 * 
	 * @return The result of the JStat get screen capture command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult getScreenCapture() throws JStatException, TimeLimitExceededException {

		return doCommand("<B><S><E>");

	}

	/**
	 * Install a SIS file on the Symbian device.
	 * 
	 * @param aFile
	 *            The SIS file to install.
	 * @return The result of the JStat install command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult installFile(String aFile) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><+" + aFile + "><E>");
	}

	/**
	 * Uninstall a SIS file on the Symbian device.
	 * 
	 * @param aUid
	 *            The UID of the SIS file to uninstall.
	 * @return The result of the JStat uninstall command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult uninstallFile(String aUid) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><-" + aUid + "><E>");

	}

	/**
	 * Lists all availbe drives on the Symbian device.
	 * 
	 * @return The result of the JStat list drivers command. The format of the
	 *         results is as follows:
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult listDrives() throws JStatException, TimeLimitExceededException {

		return doCommand("<B><W><E>");

	}

	/**
	 * Lists all files in a directory on the Symbian devices.
	 * 
	 * @param aLocation
	 *            The folder to list the contents.
	 * @return The result of the JStat list files command. The format of the
	 *         results is as follows:
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult listFiles(String aLocation) throws JStatException, TimeLimitExceededException {

		if (!aLocation.endsWith("\\")) {
			aLocation += "\\";
		}
		if (aLocation.endsWith("\\\\")) {
			aLocation = aLocation.substring(0, aLocation.length() - 2);
		}

		return doCommand("<B><V" + aLocation + "><E>");
	}

	/**
	 * Deletes a file from the Symbian device.
	 * 
	 * @param aLocation
	 *            The file to delete.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult delete(String aLocation) throws JStatException, TimeLimitExceededException {
		return doCommand("<B><U" + aLocation + "><E>");
	}

	/**
	 * Creates a directory on the Symbian device.
	 * 
	 * @param aLocation
	 *            The directory to create.
	 * @return The result of the JStat create folder command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult createFolder(String aLocation) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><Y" + aLocation + "\\><E>");

	}

	/**
	 * Removes a folder from the Symbian device.
	 * 
	 * @param aLocation
	 *            The folder to delete.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult removeFolder(String aLocation) throws JStatException, TimeLimitExceededException {
		return doCommand("<B><Z" + aLocation + "\\><E>");

	}

	/**
	 * Runs a Symbian executable on the Symbian device.
	 * 
	 * @param aExecutable
	 *            The executable to run.
	 * @param aArgs
	 *            The arguments to that executable.
	 * @return The result of the JStat run command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult run(String aExecutable, String aArgs) throws JStatException, TimeLimitExceededException {
		return doCommand("<B><J" + aExecutable + "," + aArgs + "><E>");
	}

	/**
	 * Runs a Symbian executable on the Symbain device.
	 * 
	 * @param aExecutable
	 *            The executable to run.
	 * @return The result of the JStat run command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult run(final String aExecutable) throws JStatException, TimeLimitExceededException {
		
		int lArgSpace = aExecutable.indexOf(' ');
		
		if (lArgSpace != -1) {
			return doCommand("<B><J" + aExecutable.substring(0, lArgSpace) + "," + aExecutable.substring(lArgSpace + 1, aExecutable.length()) + "><E>");
		}
		
		return doCommand("<B><J" + aExecutable + "><E>");
	}

	/**
	 * Kills a process on the Symbian device.
	 * 
	 * @param aPid
	 *            The Process IDentfication number (PID) of the process to kill.
	 * @return The result of the JStat kill command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult kill(int aPid) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><~" + aPid + "><E>");

	}

	/**
	 * Kills a process on the Symbian device.
	 * 
	 * @param aPid
	 *            The Process IDentfication number (PID) of the process to kill.
	 * @return The result of the JStat kill command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult kill(String aPid) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><~" + aPid + "><E>");

	}

	/**
	 * Polls a process on the Symbian device.
	 * 
	 * @param aPid
	 *            The Process IDentfication number (PID) of the process to poll.
	 * @return The result of the JStat poll command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult poll(int aPid) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><3" + aPid + "><E>");

	}

	/**
	 * Polls a process on the Symbian device.
	 * 
	 * @param aPid
	 *            The Process IDentfication number (PID) of the process to poll.
	 * @return The result of the JStat poll command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult poll(String aPid) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><3" + aPid + "><E>");

	}

	/**
	 * Renames a file on the Symbian device.
	 * 
	 * @param aArg0
	 *            The current name of the file.
	 * @param aArg1
	 *            The new name of the file.
	 * @return The result of the JStat rename command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult rename(String aArg0, String aArg1) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><2" + aArg0 + "," + aArg1 + "><E>");

	}

	/**
	 * Checks a location for the existance of a file.
	 * 
	 * @param aLocation
	 *            The location to check
	 * @return The result of the JStat check location command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult checkLocation(String aLocation) throws JStatException, TimeLimitExceededException {

		return doCommand("<B><1" + aLocation + "><E>");

	}

	/*
	 * public static JStatResult setReadOnly(String aFile, boolean aReadOnly) {
	 * 
	 * return doCommand("<B><3" + aLocation + "><E>"); }
	 */

	/**
	 * Does a software restart of the board. Note that this command cannot do a
	 * hardware restart of the board.
	 * 
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult restartBoard() throws JStatException, TimeLimitExceededException {

		return doCommand("<B><|><E>");

	}

	/**
	 * Returns the current version of Stat.
	 * 
	 * @return The version of Stat running.
	 */
	public String getVersion() {

		return getStatVersion();

	}

	/**
	 * Gets the Symbian device information
	 * 
	 * @return The result of the JStat info command. The result is as follows:
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	public JStatResult getInfo() throws JStatException, TimeLimitExceededException {

		return doCommand("<B><D><E>");

	}
	
	///////////////////////////////////////////
	// Process JStat Commands
	///////////////////////////////////////////
	
	/**
	 * Send STAT a raw command.
	 * 
	 * @param aCommand
	 * @return JStatResult object.
	 * @throws TimeLimitExceededException
	 * @throws JStatException
	 */
	public JStatResult sendRawCommand(final String aCommand) throws TimeLimitExceededException, JStatException {
		return doCommand(aCommand);
	}

	/**
	 * @param aCommand
	 * @param isCompulsory
	 * @return The JStat Result Object
	 * @throws TimeLimitExceededException
	 * @throws JStatException
	 */
	public JStatResult sendRawCommand(final String aCommand, boolean isCompulsory) throws TimeLimitExceededException, JStatException {
		return doCommand(aCommand,isCompulsory);
	}
	
	/**
	 * @param aCommand
	 * @return The result of the STAT command.
	 * @throws JStatException
	 * @throws TimeLimitExceededException
	 */
	private JStatResult doCommand(final String aCommand) throws JStatException, TimeLimitExceededException {
		return doCommand(aCommand, true);
	}
	
	/**
	 * Runs a command on the device.
	 * 
	 * @param aCommand
	 *            The command to run on the device.
	 * @return The result of the JStat command.
	 * @throws JStatException
	 *             If JStat is busy.
	 * @throws TimeLimitExceededException
	 */
	private JStatResult doCommand(final String aCommand, boolean aIsCompulsary) throws JStatException, TimeLimitExceededException {
	
		if(iNeedsDelay) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
		try {

			if(semaphore.availablePermits()==0 && !aIsCompulsary)
			{
				System.out.println("Discarted thread !");
				return null;
			}
			semaphore.acquire();
			if (iPrintStream == null) {
				LOGGER.fine("[STAT command] " + aCommand);
			} else {
				iPrintStream.println("[STAT command] " + aCommand);
			}

			JStatRunCommand lJStatCommandThread = new JStatRunCommand(iTransport, aCommand, iPacketSize);

			lJStatCommandThread.start();
			lJStatCommandThread.join();
			//lJStatCommandThread.kill();
			
			JStatException lJStatException = lJStatCommandThread.getJStatException();
			if (lJStatException != null) {
				throw lJStatException;
			}

			JStatResult lJStatResult = lJStatCommandThread.getResult();
			if (lJStatResult == null) {
				throw new TimeLimitExceededException("JStat Command \"" + aCommand + "\" exceeded the internal timeout "
						+ sTimeout);
			} else if (lJStatResult.getReturnedValue() != 13) {
				throw new JStatException(lJStatResult);
			}
			
			return lJStatResult;

		} catch (InterruptedException lInterruptedException) {
			throw new JStatException("JStat Thread was interupted: " + lInterruptedException.getMessage());
		} finally {
			semaphore.release();
		}
	}
}
