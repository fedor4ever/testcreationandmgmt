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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IDeviceComms;

public class FtpTransfer implements IDeviceComms.ISymbianTransfer {

	private String iIP = "127.0.0.1";

	private int iPort = 21;

	private FTPClient iFTP = null;

	private String iUserName = "username";

	private String iPassword = "pass";

	private String iPassive = "true";

	private static String iSystemDrive = null;

	private static String iDefaultSystemDrive = "C";

	private static String iSystemDriveLiteral = "System Drive: ";

	private boolean iFirstDeleteIteration = true;

	private static final int iBufferSize = 10 * 1460;

	/** Singleton to STAT. */
	private static final Logger LOGGER = Logger.getLogger(FtpTransfer.class.getName());

	private static Hashtable<String, FtpTransfer> iFtpInstances = new Hashtable<String, FtpTransfer>();

	/**
	 * getInstance : gets the first instance from the list of instances. If
	 * there are no instances, it will try to create an instance with transport
	 * from TDConfig.
	 * 
	 * @return FtpTransfer instance (connected to the server if possible however
	 *         not guaranteed)
	 */
	public static synchronized FtpTransfer getInstance() {
		FtpTransfer lInstance = null;
		if (iFtpInstances.isEmpty()) {
			// create a new one with transport from config
			try {
				String lTransport = TDConfig.getInstance().getPreference(TDConfig.TRANSPORT);

				lInstance = new FtpTransfer(lTransport);
				iFtpInstances.put(lTransport, lInstance);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not create an FTP session.", lParseException);
			}
		} else {
			// get the first instance.
			lInstance = iFtpInstances.elements().nextElement();
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
	public static synchronized FtpTransfer getInstance(String aTransport) {
		FtpTransfer lInstance = null;
		if (iFtpInstances.containsKey(aTransport)) {
			// get the appropriate instance
			lInstance = (FtpTransfer) iFtpInstances.get(aTransport);
		} else {
			// create a new one with the transport provided
			try {
				lInstance = new FtpTransfer(aTransport);
				iFtpInstances.put(aTransport, lInstance);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not create an FTP session.", lParseException);
			}
		}
		return lInstance;
	}

	/**
	 * removeAllInstances: disconnect all instances and deletes them.
	 * 
	 * @return boolean success/fail (if any fails to disconnect)
	 */
	public static boolean removeAllInstances() {
		boolean lResult = true;
		Set<String> lkeySet = iFtpInstances.keySet();
		for (Iterator iter = lkeySet.iterator(); iter.hasNext();) {

			FtpTransfer lInstance = (FtpTransfer) iFtpInstances.get(iter.next());
			try {
				lInstance.disconnectFTP();
				iFtpInstances.remove(iter);
			} catch (IOException lIOException) {
				lResult = false;
				LOGGER.log(Level.SEVERE, "Session with transport: " + iter + " failed to disconnect.", lIOException);
			}
		}
		return lResult;
	}

	private FtpTransfer(String aTransport) throws ParseException {
		// check transport is valid
		String lTransport = aTransport;
		if (!isTransportValid(lTransport)) {
			throw new ParseException("Transport " + lTransport + " incorrect.");
		}

		// connect FTP
		connectFTP();

	}

	/**
	 * isTransportValid Checks if the transport is correct
	 * 
	 * @param String
	 *            aTransport. Should be in the form tcp:ip@:port
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
						FtpTelnetLaunchConstants.PLUGIN_ID + ":" + FtpTelnetLaunchConstants.FTPPORT));
				iPassive = TDConfig.getInstance().getPreference(
						FtpTelnetLaunchConstants.PLUGIN_ID + ":" + FtpTelnetLaunchConstants.FTPPASSIVE);
			} catch (Exception lException) {
				LOGGER.log(Level.WARNING, "Configuration error.", lException);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * connectFTP : connects to ftp server
	 * 
	 * @return boolean success/fail
	 */
	public boolean connectFTP() {
		if (isFTPConnected()) {
			return true;
		}
		// Connect to FTP
		try {

			// 1. create an apache client
			iFTP = new FTPClient();
			iFTP.addProtocolCommandListener(new ProtocolCommandListener() {

				public void protocolCommandSent(ProtocolCommandEvent aProtocolCommandEvent) {
					LOGGER.fine("FTP Command Send: (" + aProtocolCommandEvent.getCommand() + ") "
							+ aProtocolCommandEvent.getMessage());
				}

				public void protocolReplyReceived(ProtocolCommandEvent aProtocolCommandEvent) {
					LOGGER.fine("FTP Command Recieved: (" + aProtocolCommandEvent.getMessage() + ") Returned Code "
							+ aProtocolCommandEvent.getReplyCode());
				}
			});
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
			iFTP.configure(conf);
			// 2. connect
			iFTP.connect(iIP, iPort);

			// 3. check connection done.
			int reply = iFTP.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				disconnectFTP();
				LOGGER.log(Level.SEVERE, "FTP error: " + reply);
				return false;
			}

			// 4. Login
			if (!iFTP.login(iUserName, iPassword)) {
				LOGGER.log(Level.SEVERE, "FTP failed to login, Username: " + iUserName + " Password: " + iPassword);
				disconnectFTP();
				return false;
			} else {
				if (iPassive.equalsIgnoreCase("true")) {
					iFTP.enterLocalPassiveMode();
				}
				iFTP.setFileType(FTP.BINARY_FILE_TYPE);
				iFTP.setBufferSize(iBufferSize);
			}

		} catch (SocketException lSocketException) {
			LOGGER.log(Level.SEVERE, "Socket exception ", lSocketException);
			return false;
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Socket exception ", lIOException);
			return false;
		}

		LOGGER.info("FTP connection established with transport : " + iIP + ":" + iPort);

		return true;

	}

	/**
	 * disconnects from ftp server
	 * 
	 * @throws IOException
	 */
	public void disconnectFTP() throws IOException {
		if (iFTP != null && iFTP.isConnected()) {
			iFTP.logout();
			iFTP.disconnect();
			LOGGER.info("FTP with transport " + iIP + ":" + iPort + " Disconnected.");
		}
	}

	/**
	 * isFTPConnected : checks if connection is established.
	 * 
	 * @return boolean true/false
	 */
	public boolean isFTPConnected() {
		if (iFTP != null && iFTP.isConnected()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#cd(java.io.File)
	 */
	public boolean cd(File aSymbianPath) {
		LOGGER.info("FTP cd : " + aSymbianPath);
		boolean lResult = false;
		String lDir = normalizePath(aSymbianPath);
		if (connectFTP()) {
			try {
				lResult = iFTP.changeWorkingDirectory(lDir);
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : cd " + aSymbianPath, lIOException);
			}
		}

		return lResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#delete(java.io.File,
	 *      boolean)
	 */
	public boolean delete(File aSymbianFile, boolean aRecurse) {
		LOGGER.info("Deleting file/folder " + aSymbianFile.toString());
		// aSymbianFile can be
		// 1. folder name (this is the only case where aRecurse is relevant)
		// 2. file name
		// 3. a pattern

		boolean lResult = false;
		String lDir = aSymbianFile.getPath().replaceAll("\\\\", "/");
		if (iFirstDeleteIteration) {
			if (lDir.contains("*")) {
				lDir = normalizePath(aSymbianFile.getParentFile());
			} else {
				lDir = normalizePath(aSymbianFile);
			}
		}
		if (connectFTP()) {
			try {
				if (lDir.contains("*")) {
					// ex. delete \test\*.txt
					// calls delete("\test", "*.txt")
					lResult = delete(lDir, new File(lDir).getName());
				} else {
					// try to delete it first
					lResult = iFTP.deleteFile(lDir);
					if (!lResult) {
						String lRes = iFTP.getReplyString();
						if (lRes.indexOf("No such file or directory") == -1) {
							// it could be a existing folder
							// first delete all files
							lResult = delete(lDir, "*");

							if (lResult && aRecurse) {
								// delete recusively

								FTPFile[] lFiles = iFTP.listFiles();
								File lFile = null;
								for (int i = 0; i < lFiles.length; i++) {
									lFile = new File(pwd(), lFiles[i].getName());
									iFirstDeleteIteration = false;
									lResult = delete(lFile, true);
									if (!lResult) {
										break;
									}
								}
								// folder deleted , delete the folder itself
								if (lResult) {
									lResult = iFTP.removeDirectory(lDir);
								} else {
									LOGGER.log(Level.SEVERE, "Could not delete folder " + lFile);
								}
							}
						}
					}
				}
			} catch (IOException lIOException) {
				lResult = false;
				LOGGER.log(Level.SEVERE, "FTP command failed : delete " + (aRecurse ? "folder " : "file ")
						+ aSymbianFile, lIOException);
			}
		}

		return lResult;
	}

	private boolean delete(String aDirectory, String name) {
		boolean lReturn = true;
		String lName = name.replaceAll("\\*", "\\.\\*");

		Pattern lPattern = Pattern.compile(lName, Pattern.CASE_INSENSITIVE);
		try {
			lReturn = iFTP.changeWorkingDirectory(aDirectory);
			if (lReturn) {
				FTPFile[] lFiles = iFTP.listFiles();
				for (int i = 0; i < lFiles.length; i++) {
					if (lFiles[i].isFile()) {
						if (lPattern.matcher(lFiles[i].getName()).matches()) {
							// delete the file
							LOGGER.info("Deleting file " + lFiles[i].getName());
							lReturn = iFTP.deleteFile(lFiles[i].getName());
							LOGGER.info("Delete file " + lFiles[i].getName());
						}
					}
				}
			} else {
				lReturn = true;
			}
		} catch (IOException lIOException) {
			lReturn = false;
			LOGGER.log(Level.SEVERE, "FTP command failed : delete " + name, lIOException);
		}
		return lReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#dir(java.io.File)
	 */
	public List<File> dir(File aSymbianPath) {
		LOGGER.info("Listing folder " + aSymbianPath.toString());
		String lDir = aSymbianPath.getPath().replaceAll("\\\\", "/");
		String lName = ".*";
		if (lDir.contains("*")) {
			lDir = normalizePath(aSymbianPath.getParentFile());
			lName = aSymbianPath.getName().replaceAll("\\*", "\\.\\*");
		} else {
			if (!lDir.equals("/")) {
				lDir = normalizePath(aSymbianPath);
			}
		}

		Pattern lPattern = Pattern.compile(lName, Pattern.CASE_INSENSITIVE);
		List<File> lReturn = new LinkedList<File>();
		if (connectFTP()) {
			try {
				if (iFTP.changeWorkingDirectory(lDir)) {
					FTPFile[] lFiles = iFTP.listFiles();
					for (int i = 0; i < lFiles.length; i++) {
						if (lPattern.matcher(lFiles[i].getName()).matches()) {
							// convert file back to NT
							lReturn.add(new File(backToNT(new File(lDir, lFiles[i].getName()).getPath())));
						}
					}
				}
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : dir " + aSymbianPath, lIOException);
			}
		}
		return lReturn;
	}

	private String backToNT(String aDirectory) {
		return aDirectory.replaceAll("\\\\", "/").replaceAll("^/(.)", "$1:");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#mkdir(java.io.File)
	 */
	public boolean mkdir(File aSymbianDir) {
		LOGGER.info("Creating folder " + aSymbianDir.toString());
		String lDir = normalizePath(aSymbianDir);
		boolean lResult = false;
		if (connectFTP()) {
			try {
				String[] lPaths = lDir.split("/");
				String lPath = "/";
				for (int i = 0; i < lPaths.length; i++) {
					if (!lPaths[i].equals("")) {
						lPath = lPath + lPaths[i] + "/";
						lResult = iFTP.changeWorkingDirectory(lPath);
						if (!lResult) {
							// can create
							lResult = iFTP.makeDirectory(lPaths[i]);
							// and accessible
							lResult = iFTP.changeWorkingDirectory(lPath);
						}
					}
				}
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : mkdir " + aSymbianDir, lIOException);
			}
		}
		return lResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#move(java.io.File,
	 *      java.io.File)
	 */
	public boolean move(File aSymbianSource, File aSymbianDestination) {
		LOGGER.info("Moving file from " + aSymbianSource + " to " + aSymbianDestination);
		String lDirSource = normalizePath(aSymbianSource);
		String lDirDestination = normalizePath(aSymbianDestination);
		boolean lResult = false;
		if (connectFTP()) {
			try {
				lResult = iFTP.rename(lDirSource, lDirDestination);
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE,
						"FTP command failed : rename " + aSymbianSource + " to " + aSymbianDestination, lIOException);
			}
		}
		return lResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#pwd()
	 */
	public File pwd() {
		LOGGER.info("FTP pwd");
		String lResult = null;
		File lPWD = null;
		if (connectFTP()) {
			try {
				lResult = iFTP.printWorkingDirectory();
				if (lResult != null) {
					lPWD = new File(backToNT(lResult));
				}
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : pwd ", lIOException);
			}
		}
		return lPWD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#retrieve(java.io.File,
	 *      java.io.File)
	 */
	public boolean retrieve(File aSymbianFile, File aHostFile) {
		LOGGER.info("Retrieve file " + aSymbianFile + " to " + aHostFile);
		String lDirSource = normalizePath(aSymbianFile.getParentFile());
		String lName = aSymbianFile.getName();
		boolean lResult = false;

		if (connectFTP()) {
			try {
				lResult = iFTP.changeWorkingDirectory(lDirSource);
				if (lResult) {
					if (aHostFile.isDirectory()) {
						aHostFile = new File(aHostFile, lName);
					}
					lResult = iFTP.retrieveFile(lName, new FileOutputStream(aHostFile));
				}
			} catch (FileNotFoundException lFileNotFoundException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : retrieve ", lFileNotFoundException);
				lResult = false;
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "FTP command failed : retrieve ", lIOException);
				lResult = false;
			}
		}

		return lResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#send(java.io.File,
	 *      java.io.File)
	 */
	public boolean send(File aHostFile, File aSymbianFile) {
		LOGGER.info("Sending file " + aHostFile + " to " + aSymbianFile);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String lName = aSymbianFile.getName();
		boolean lResult = false;

		if (connectFTP()) {
			try {
				// create folder if it does exist
				String lParent = normalizePath(aSymbianFile.getParentFile());
				lResult = iFTP.changeWorkingDirectory(lParent);
				if (!lResult) {
					// try to create it
					lResult = iFTP.makeDirectory(lParent);
					if (lResult) {
						lResult = iFTP.changeWorkingDirectory(lParent);
					}
				}
				if (lResult) {
					lResult = iFTP.storeFile(lName, new FileInputStream(aHostFile));
				}
			} catch (FileNotFoundException lFileNotFoundException) {
				lResult = false;
				LOGGER.log(Level.SEVERE, "FTP command failed : send ", lFileNotFoundException);
			} catch (IOException lIOException) {
				lResult = false;
				LOGGER.log(Level.SEVERE, "FTP command failed : send ", lIOException);
			}
		}
		return lResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#listDrives()
	 */
	public List<String> listDrives() {
		LOGGER.info("listing Drives");
		List<String> lDrives = new LinkedList<String>();
		List<File> lFiles = new LinkedList<File>();
		if (connectFTP()) {
			lFiles = dir(new File("/"));
			for (Iterator iter = lFiles.iterator(); iter.hasNext();) {
				File element = (File) iter.next();
				lDrives.add(element.toString());
			}
		}
		if (lDrives.isEmpty()) {
			LOGGER.log(Level.SEVERE, "Could not get list of drives from server.");
		}
		return lDrives;
	}

	public String getSystemDrive() {
		LOGGER.fine("FTP getSystemDrive");
		if (iSystemDrive == null) {
			// get system drive
			if (connectFTP()) {
				try {
					String lStatus = iFTP.getStatus();
					int lPos = lStatus.indexOf(iSystemDriveLiteral);
					if (lPos != -1) {
						iSystemDrive = lStatus.substring(lPos + iSystemDriveLiteral.length(), lPos
								+ iSystemDriveLiteral.length() + 1);
					} else {
						LOGGER.log(Level.SEVERE,
								"Could not get System Drive from server. Will use the first from the list of drives.");
						List<String> lDrives = listDrives();
						if (!lDrives.isEmpty()) {
							iSystemDrive = lDrives.get(0);
						} else {
							LOGGER.log(Level.SEVERE, "Could not get System Drive from server. Will use default: \\"
									+ iDefaultSystemDrive + "\\");
							iSystemDrive = iDefaultSystemDrive;
						}
					}
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "FTP command failed : STAT ", lIOException);
				}

			}
		}
		return iSystemDrive;
	}

	public String normalizePath(File aSymbianFile) {
		// a SymbianPath could be
		// \path\... use system drive /C/path/...
		// drive:\path\... covert it to /C/path/...
		// path covert it to pwd()/path...
		// just file name pwd()/name
		String lReturn = null;
		if (aSymbianFile == null) {
			lReturn = pwd().getPath().replaceAll("\\\\", "/");
		} else {
			File lFile = aSymbianFile;
			lReturn = lFile.getPath().replaceAll("\\\\", "/");
			Matcher lMatcher = Pattern.compile("^(.):(.*)").matcher(lReturn);
			if (lReturn.startsWith("/")) {
				// prepend system drive to the path.
				lReturn = "/" + getSystemDrive() + lReturn;
			} else if (lReturn.startsWith("$:")) {
				lReturn = lReturn.replace("$:", "/" + getSystemDrive());
			} else if (lMatcher.find()) {
				lReturn = "/" + lMatcher.group(1) + lMatcher.group(2);
			} else {
				lReturn = pwd() + "/" + lReturn;
			}
		}
		return lReturn;
	}
}
