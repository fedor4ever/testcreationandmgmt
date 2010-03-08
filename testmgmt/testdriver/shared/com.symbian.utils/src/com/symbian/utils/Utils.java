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



package com.symbian.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author EngineeringTools
 */
public final class Utils {

	private final static Logger LOGGER = Logger.getLogger(Utils.class.getName());

	/**
	 * Copies a file from iHostPath to the aCopyTo location. This method also
	 * changes the HostPath of the file to the new location.
	 * 
	 * @param aCopyFrom
	 *            The location from where to copy the file from
	 * @param aCopyTo
	 *            The location of where to copy the file too.
	 * @throws IOException
	 *             If the copy fails.
	 */
	public static final void copy(final File aCopyFrom, final File aCopyTo)
			throws IOException {
		if (!aCopyTo.getParentFile().isDirectory()
				&& !aCopyTo.getParentFile().mkdirs()) {
			throw new IOException("Could not create copy to directory: "
					+ aCopyTo.getAbsolutePath());
		}
		
		if (aCopyTo.isFile()) {
			LOGGER.warning("The file: " + aCopyTo + " will be overwritten by: " + aCopyFrom);
		}

		FileChannel lSrcChannel = new FileInputStream(aCopyFrom).getChannel();
		FileChannel lDstChannel = new FileOutputStream(aCopyTo).getChannel();

		LOGGER.fine("\tCopying from \"" + aCopyFrom.getCanonicalPath()
				+ "\" to \"" + aCopyTo.getCanonicalPath() + "\"");

		lDstChannel.transferFrom(lSrcChannel, 0, lSrcChannel.size());

		lSrcChannel.close();
		lDstChannel.close();
	}

	/**
	 * 
	 * Cleans a string, removing all ' ' and '\t'
	 * 
	 * @param aString
	 *            to be cleaned
	 * @return a cleaned string
	 */

	public static String cleanString(String aString) {
		StringBuffer lStringBuffer = new StringBuffer();
		for (int lStringIndex = 0; lStringIndex < aString.length(); lStringIndex++) {
			if (aString.charAt(lStringIndex) != ' '
					&& aString.charAt(lStringIndex) != '\t') {
				lStringBuffer.append(aString.charAt(lStringIndex));
			}
		}
		return lStringBuffer.toString();
	}

	/**
	 * 
	 * Cleans a string, removing only '\t'
	 * 
	 * @param aString
	 *            to be cleaned
	 * @return a cleaned string
	 */

	public static String cleanTabString(String aString) {
		StringBuffer lStringBuffer = new StringBuffer();
		for (int lStringIndex = 0; lStringIndex < aString.length(); lStringIndex++)
			if (aString.charAt(lStringIndex) != '\t')
				lStringBuffer.append(aString.charAt(lStringIndex));
		return lStringBuffer.toString();
	}

	/**
	 * Class returning a Vector containing IP addresses of attached boards. It
	 * uses system call to "ipconfig" and "route print"
	 * 
	 * @param aAdapterName
	 * 
	 * @return A vector containing the IP addresses using the localhost as a
	 *         gateway, corresponding to board IP addresses connected through
	 *         ppp0. Empty if no IP addresses found or no local IP address found
	 *         for ppp0.
	 * @throws IOException,
	 *             StringIndexOutOfBoundsException
	 * @throws StringIndexOutOfBoundsException
	 */
	public static Vector getBoardIP(String aAdapterName) throws IOException,
			StringIndexOutOfBoundsException {
		// Get local ip address of PPP adapter RAS Server (Dial In) Interface
		// (ppp0)
		String lPPP0Address = "";
		Vector lBoardAddress = new Vector();
		Runtime lRuntime = Runtime.getRuntime();

		BufferedReader lBufferedReader = new BufferedReader(
				new InputStreamReader(lRuntime.exec("ipconfig")
						.getInputStream()));

		boolean lPPP0Found = false;
		String lOutput;
		while ((lOutput = lBufferedReader.readLine()) != null) {
			if (lOutput.toLowerCase().indexOf(aAdapterName) != -1) {
				lPPP0Found = true;
			}
			if (lPPP0Found && lOutput.toLowerCase().indexOf("ip address") != -1) {
				lPPP0Address = lOutput.substring(lOutput.lastIndexOf(":") + 2);
				break;
			}
		}

		if (!lPPP0Address.equals("")) {
			lBufferedReader = new BufferedReader(new InputStreamReader(lRuntime
					.exec(
							"route print *.*.*.* " + lPPP0Address + " IF "
									+ lPPP0Address).getInputStream()));

			boolean lReady = false;

			while ((lOutput = lBufferedReader.readLine()) != null) {
				if (lOutput.toLowerCase().startsWith("default gateway")) {
					// end of the routing table
					break;
				}
				if (lReady) {
					String lCleanedString = cleanString(lOutput);
					if (lCleanedString.indexOf("255") > 0) {
						System.out.println("Pot BOARD: " + lCleanedString);
						lBoardAddress.addElement(lCleanedString.substring(0,
								lCleanedString.indexOf("255")));
					}
				}
				if (lOutput.toLowerCase().startsWith("network destination")) {
					lReady = true;
				}
			}
		}

		return lBoardAddress;
	}

	/**
	 * @param aEpocroot
	 * @return 
	 */
	public static String getBuildNumber(File aEpocroot) {
		String epocBuild = null;
		BufferedReader lBufferedReader = null;
		
		try {

			lBufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(aEpocroot.getCanonicalPath()
							+ File.separator + "epoc32" + File.separator
							+ "data" + File.separator + "buildinfo.txt")));
			lBufferedReader.readLine();
			lBufferedReader.readLine();
			String line = lBufferedReader.readLine();
			epocBuild = (Utils.cleanString(line)).substring((Utils
					.cleanString(line)).indexOf("Build") + 5);


		} catch (Exception lException) {
			LOGGER.fine("buildinfo.txt not found, " + lException.getMessage());
			/*
			 * It's if we can not work out the build number, we just return
			 * null.
			 */
		} finally {
			if (lBufferedReader != null) {
				try {
					lBufferedReader.close();
				} catch (IOException lIOException) {
					LOGGER.log(Level.WARNING, "Could not close buffer on /epoc32/data/buildinfo.txt", lIOException);
				}
			}
		}

		return epocBuild;
	}

	/**
	 * Returns a list of files from a directory ending with a specific extension.
	 * 
	 * @param aDirectory The directory to search through.
	 * @param aExtension The extention to search with.
	 * @return The array of files with a specific extension.
	 */
	public static File[] getFilesWithExtension(File aDirectory, final String aExtension) {
		return aDirectory.listFiles(new FileFilter() {
			public boolean accept(File lFile) {
				if (lFile.getName().endsWith(aExtension)) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * @param aDirectory The directory to delete all sub-directories or files.
	 * 
	 * @return <code>true</code> if everything was deleted, <code>false</code> otherwise.
	 */
	public static boolean recusiveDelete(final File aDirectory) {
		if (!aDirectory.isDirectory()) {
			return false;
		}
		
		LOGGER.info("Deleting Directory: " + aDirectory);
		
		boolean lSucceded = true;
		
		File[] aFiles = aDirectory.listFiles();
		for (int lIter = 0; lIter < aFiles.length; lIter++) {
			if (aFiles[lIter].isDirectory()) {
				lSucceded &= recusiveDelete(aFiles[lIter]);
			}
			
			lSucceded &= aFiles[lIter].delete();
		}
		
		return lSucceded;
	}
}
