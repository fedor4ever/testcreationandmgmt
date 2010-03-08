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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;

// import com.symbian.jstat.JStat;
// import com.symbian.jstat.JStatException;

/**
 * Creates a fileset where each fileset relates directly to one SIS file. On
 * each fileset the following operations can occur:
 * <ul>
 * <li></li>
 * </ul>
 * 
 * @author EngineeringTools
 */
public class ExecuteTransferSet implements Set {

	/** Logger. */
	private static final Logger LOGGER = Logger.getLogger(ExecuteTransferSet.class.getName());

	/** The set of all Epoc Files. */
	private Set<ExecuteTransfer> iFileSet = new LinkedHashSet<ExecuteTransfer>();

	/** The set of all Epoc Files. */
	private Set<ConditionalTransfer> iOptFileSet = new LinkedHashSet<ConditionalTransfer>();

	/** A unique name/identifier for the ExecuteTransferSet. */
	private String iName;

	/** The UID for the SIS file. */
	private String iUid = null;

	/** Location for Package File for this Set. */
	private File iPkgFile = null;

	/** Location for Repository of this Set. */
	private File iRepository = null;

	// CONSTANTS
	/** Repository root variable literal. */
	private static final String REPOS_VARIABLE_LITERAL = "${repositoryRoot}";

	/** String Literal for ".pkg". */
	private static final String PKG = ".pkg";

	/** String Literal for "testDriverPackage". */
	private static final String TEST_DRIVER_PACKAGE = "testDriverPackage";

	// PlatSec On fields
	/** The SIS Name. Hardcoded to testDriver. */
	private static final String SIS_NAME = "testDriver";

	/** The SIS Major number. Hardcoded to 1. */
	private static final String SIS_MAJOR = "1";

	/** The SIS Minor number. Hardcoded to 0. */
	private static final String SIS_MINOR = "0";

	/** The SIS Build number. Hardcoded to 0. */
	private static final String SIS_BUILD = "0";

	/** Literal for end of line: ie \r\n or \n. */
	public static final String EOL = System.getProperty("line.separator");

	/**
	 * Constructor for ExecuteTransferSet. Creates a fileset where each fileset
	 * relates directly to one SIS file.
	 * 
	 * @param aName
	 *            The unique name/id of the fileset
	 * @param aRepository
	 * @param aTransferList
	 *            
	 * @param aUid
	 * @throws IOException
	 */
	public ExecuteTransferSet(final String aName, final File aRepository) throws IOException {
		LOGGER.entering(this.getName(), "Constructor", aName);
		// Check that the Repository exits
		if (!aRepository.isDirectory() && !aRepository.mkdirs()) {
			throw new IOException("Could not create repository directory.");
		}

		iName = aName;
		iRepository = aRepository;

		// Create a new Package file
		iPkgFile = new File(aRepository.getCanonicalPath() + File.separator + TEST_DRIVER_PACKAGE + PKG);

		if (!iPkgFile.isFile()) {
			if (!iPkgFile.createNewFile()) {
				throw new IOException("Could not create package file.");
			}
		}
	}

	/**
	 * Creates a repository with all the files in transfer.
	 * 
	 * @param aUid
	 *            The UID of current repository
	 * 
	 * @throws IOException
	 *             If the locations of any of the files are invalid.
	 */
	public void createRepository(final String aUid) throws IOException {
		LOGGER.entering(this.getName(), "createRepository");
		if (iFileSet.isEmpty()) {
			return;
		}

		iUid = aUid;

		// Create a new PackageFile
		PrintWriter lPrintWriter = new PrintWriter(new FileWriter(iPkgFile));
		lPrintWriter.println("#{\"" + SIS_NAME + "\"},(" + iUid + ")," + SIS_MAJOR + "," + SIS_MINOR + "," + SIS_BUILD);
		lPrintWriter.println();
		lPrintWriter.println("%{\"Symbian\"}");
		lPrintWriter.println(":\"Symbian\"");
		lPrintWriter.println();

		StringBuffer lStringBuffer = new StringBuffer();
		for (Iterator lFileIterator = iFileSet.iterator(); lFileIterator.hasNext();) {
			Object lObject = lFileIterator.next();

			if (lObject instanceof ExecuteTransfer) {
				ExecuteTransfer lExecuteTransfer = (ExecuteTransfer) lObject;

				try {

					// Copy the file to the repository
					lExecuteTransfer.fileToRepository(iRepository);

					// Write the file to the PKG file
					lPrintWriter.write(lExecuteTransfer.fileToSIS());

					// Log the operation to the StringBuffer for the LOGGER
					lStringBuffer.append("\tAdding " + lExecuteTransfer.getPCPath() + " to SIS at "
							+ lExecuteTransfer.getSymbianPath() + ExecuteTransfer.EOL);

				} catch (IOException IOException) {
					LOGGER.log(Level.SEVERE, IOException.getMessage(), IOException);
				}
			}
		}

		LOGGER.info(lStringBuffer.toString());

		lPrintWriter.flush();
		lPrintWriter.close();
	}

	/**
	 * Create a SIS file with a PKG file.
	 * 
	 * @param aEpocRoot
	 *            The EPOCROOT directory to use when creating the SIS files
	 * @param aSisPcPath
	 *            The location of where to start the SIS files to
	 * @param aCert
	 *            The Certificate location to sign the SIS file with.
	 * @param aKey
	 *            The Key location to encrypt the SIS file with.
	 * @param aSisPackage
	 *            The package file to be used to create the SIS file.
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 * @throws FileNotFoundException
	 */
	public void createSis(final File aEpocRoot, final File aSisPcPath, final File aCert, final File aKey)
			throws IOException, TimeLimitExceededException, FileNotFoundException {
		LOGGER.fine("Creating SIS File: " + aSisPcPath);

		File lCreateSis = new File(aEpocRoot, "epoc32/tools/createsis.bat");
		File lMakeSis = new File(aEpocRoot, "epoc32/tools/makesis.exe");
		File lSignSis = new File(aEpocRoot, "epoc32/tools/signsis.exe");

		// Create the SIS file
		if (false && lCreateSis.exists()) {
			//  never enter this if statement... defect with createsis on
			// the security team.

			// Use the CreateSIS batch script
			new ExecuteOnHost(aEpocRoot, lCreateSis.getCanonicalPath() + " create -key " + aKey.getAbsolutePath()
					+ " -cert " + aCert.getAbsolutePath() + iPkgFile.getCanonicalPath()).doTask(true, 0, false);

			//  change -tmp.sis to just .sis when signing works
			// (new File((aSisPackage.getPath()).replaceFirst("\\.pkg",
			// "-tmp\\.sis"))).renameTo(aHostPath);

		} else if (lMakeSis.exists()) {

			// Run MakeSIS
			ExecuteOnHost lPacker = new ExecuteOnHost(aEpocRoot, lMakeSis.getCanonicalPath() + " \"" + iPkgFile.getCanonicalPath() + "\" \""
					+ aSisPcPath.getCanonicalPath() + "\"");
			
			if (lPacker.doTask(true, 0, true)) {
				String lOutput = lPacker.getOutput();
				if (lOutput.contains("error")) {
					throw new IOException("Creating sis file for " + iPkgFile.getCanonicalPath() + "  failed.");
				}
			} else {
				throw new IOException("Creating sis file for " + iPkgFile.getCanonicalPath() + "  failed.");
			}

			if (lSignSis.exists()) {
				
				File lPassPhrase = TDConfig.getInstance().getCertPass();
				String lPassString = lPassPhrase == null ? "" : " \"" + lPassPhrase.getAbsolutePath() + "\" ";
				
				
				String lAlgo = " -cr ";
				try {
					lAlgo = TDConfig.getInstance().getPreference(TDConfig.SIGN_ALGORITHM).equalsIgnoreCase("RSA") ? " -cr " : " -cd " ;
				} catch (ParseException lParseException) {
					LOGGER.log(Level.SEVERE, "Could not get signing algorith type from config. defaulting to RSA");
				}

				// Run SignSIS
				ExecuteOnHost lExecuteOnHost = new ExecuteOnHost(aEpocRoot, lSignSis.getCanonicalPath() + lAlgo + " -s \"" + aSisPcPath.getCanonicalPath()
						+ "\" \"" + aSisPcPath.getCanonicalPath() + "\" \"" + aCert.getCanonicalPath() + "\" \""
						+ aKey.getCanonicalPath() + "\"" + lPassString );
				if (lExecuteOnHost.doTask(true, 0, true)) {
					String lOutput = lExecuteOnHost.getOutput();
					if (lOutput.contains("error")) {
						throw new IOException("Signing sis file " + aSisPcPath.getCanonicalPath() + " failed.");
					}
				} else {
					throw new IOException("Signing sis file " + aSisPcPath.getCanonicalPath() + " failed.");
				}

			} else {
				throw new IOException("signsis.exe does not exist in the /epoc32/tools/ folder");
			}

		} else {
			throw new IOException("makesis.exe does not exist in the /epoc32/tools/ folder");
		}

		// Rewrite the package file with variables for PlatSec OFF remoting

		// Get the repository path in a string and get rid of traling \
		String lReposPath = iRepository.getCanonicalPath();
		if (lReposPath.endsWith("\\")) {
			lReposPath = lReposPath.substring(0, lReposPath.length() - 1);
		}

		// Replace all repository paths with the repository variable
		BufferedReader lSisPackageReader = new BufferedReader(new FileReader(iPkgFile));
		String lReadLine = null;
		StringBuffer lChangedPackage = new StringBuffer();
		while ((lReadLine = lSisPackageReader.readLine()) != null) {
			lChangedPackage.append(lReadLine.toLowerCase().replaceAll("\\Q" + lReposPath.toLowerCase() + "\\E",
					"\\" + REPOS_VARIABLE_LITERAL)
					+ "\n");
		}
		lSisPackageReader.close();

		// Write the new result to a buffer
		PrintWriter lPrintWriter = new PrintWriter(new FileOutputStream(iPkgFile));
		lPrintWriter.write(lChangedPackage.toString());
		lPrintWriter.flush();
		lPrintWriter.close();

		// Confirm that the Sis package was created
		if (!aSisPcPath.isFile()) {
			throw new IOException("Could not create SIS file: " + aSisPcPath);
		}
	}

	/**
	 * Installs a SIS file to the Symbian device. Use if Platform Security
	 * (PlatSec) is on.
	 * 
	 * @param aSisFile
	 *            The sis file to set the package to.
	 * @return 
	 * @throws IOException
	 *             If the SIS file location on the PC is invalid.
	 * @throws JStatException
	 *             If the transfering/installing of the SIS file causes an
	 *             exception.
	 * @throws TimeLimitExceededException
	 *             If the aTimeOut is exceeded.
	 * @throws JStatException
	 */
	public void installSis(final File aSisFile) throws IOException, TimeLimitExceededException {
		LOGGER.fine("Setting SIS file to: " + aSisFile.getAbsolutePath());
		String lSisName = aSisFile.getName().toLowerCase();
		iUid = lSisName.substring(lSisName.indexOf("0x"), lSisName.indexOf(".sis"));

		String lSymbianSisFile = getSymbianSisFile();

		if (aSisFile.isFile()) {

			// Sending SIS file
			LOGGER.fine("Sending file: " + aSisFile + " to " + lSymbianSisFile);
			DeviceCommsProxy lDeviceProxy = null;
			try {
				lDeviceProxy = DeviceCommsProxy.getInstance();
			} catch (Exception lException) {
				throw new IOException("Could not load comms proxy." + lException.getMessage());
			}
			if (lDeviceProxy.createSymbianTransfer().send(aSisFile.getCanonicalFile(), new File(lSymbianSisFile))) {

				// Install SIS file
				LOGGER.fine("Installing sis file: " + lSymbianSisFile);
				if (!lDeviceProxy.createSymbianProcess().install(new File(lSymbianSisFile), iPkgFile)) {
					throw new IOException("Failed to install SIS file: " + aSisFile);
				}
			} else {
				throw new IOException("Failed to transfer SIS file: " + aSisFile);
			}
		} else {
			throw new IOException("Incorrect SIS file: " + aSisFile);
		}
	}

	/**
	 * Transfers the repository files to the Symbian device. Use if Platform
	 * Security (PlatSec) is off.
	 * @return 
	 * 
	 * @throws IOException
	 *             If the repository files are invaild.
	 * @throws TimeLimitExceededException
	 */
	public boolean installRepository() throws IOException, TimeLimitExceededException {
		LOGGER.entering(this.getName(), "installRepository");
		boolean lReturn = true;

		File lPackageFile = new File(iRepository.getCanonicalPath() + File.separator + TEST_DRIVER_PACKAGE + PKG);

		if (lPackageFile.isFile()) {
			BufferedReader lReposIn = new BufferedReader(new FileReader(lPackageFile));

			String lFileLoc = null;
			while ((lFileLoc = lReposIn.readLine()) != null) {
				String[] lSplitFileLoc = lFileLoc.split("\" *- *\"");

				if (lSplitFileLoc.length == 2) {
					String lReplace = iRepository.getCanonicalPath().replace('\\', '/');

					File lPCPath = new File(lSplitFileLoc[0].substring(1).replaceFirst(
							"\\Q" + REPOS_VARIABLE_LITERAL + "\\E", lReplace));
					if (!lPCPath.isFile()) {
						lReposIn.close();
						throw new IOException("The file in the repository is not valid: " + lPCPath.toString());
					}

					this.add(new ExecuteTransfer(lPCPath, lSplitFileLoc[1].replaceAll("\"", "")));
				}
			}

			lReposIn.close();

			for (Iterator lIterator = this.iterator(); lIterator.hasNext();) {
				ExecuteTransfer lExecuteTransfer = ((ExecuteTransfer) lIterator.next());
				if (!lExecuteTransfer.doTask(true, 0, false)) {
					lReturn = false;
				}
			}
		} else {
			LOGGER.fine("The repository is empty at: " + iRepository);
		}
		return lReturn;
	}

	/**
	 * Uninstalls the SIS file and deletes any transfered files from the Symbian
	 * device.
	 * @return 
	 * 
	 * @throws TimeLimitExceededException
	 */
	public boolean uninstall() throws TimeLimitExceededException {
		// Uninstall SIS File
		boolean lReturn = true;
		if (iUid != null) {
			LOGGER.fine("JSTAT Uninstalling file: " + iUid);

			// try {
			DeviceCommsProxy lDeviceProxy = null;
			try {
				lDeviceProxy = DeviceCommsProxy.getInstance();
			} catch (Exception lException) {
				return false;
			}
			lReturn = lDeviceProxy.createSymbianProcess().uninstall(iUid.replaceFirst("0x", ""));
			lReturn = lDeviceProxy.createSymbianTransfer().delete(new File(getSymbianSisFile()), false);
		}
		return lReturn;
	}

	/**
	 * Gets the name of the SIS file on the Symbian device.
	 * 
	 * @return The sis file location in the format of
	 *         "c:\{SISFILENAME}{UID}.sis".
	 */
	public String getSymbianSisFile() {
		String sisFile = null;
		String sisRoot= null;
		try{
			sisRoot = TDConfig.getInstance().getPreference("sisroot");
		}catch(ParseException lParseException){
			LOGGER.log(Level.SEVERE, "The sis root is invalid.");
		}
		if (sisRoot.equalsIgnoreCase("c:")){
			sisFile = ILiterals.C + "\\" + iName + iUid + ".sis";
		}else {
			sisFile = sisRoot+"\\"+iName+iUid+".sis";
		}
		return sisFile;
	}
	
	public String getUid() {
		return iUid;
	}

	/**
	 * Get the name of the ExecuteTransferSet.
	 * 
	 * @return The Name of the ExecuteTransferSet
	 */
	public final String getName() {
		return iName;
	}

	/**
	 * Adds an Execute to the ExecuteTransferSet.
	 * 
	 * @param aFile
	 *            The Execute that needs to be added to the set.
	 * @return <code>true</code> if the add method succeded.
	 *         <code>false</code> if it failed.
	 * @see java.util.List#add(java.lang.Object)
	 */
	public final boolean add(final Object aFile) {
		LOGGER.entering(this.getName(), "add", aFile);

		if (aFile instanceof ExecuteTransfer) {
			ExecuteTransfer lExecuteTransfer = (ExecuteTransfer) aFile;

			for (Iterator lIter = iFileSet.iterator(); lIter.hasNext();) {
				ExecuteTransfer lFile = (ExecuteTransfer) lIter.next();

				if (lExecuteTransfer.getSymbianPath().equalsIgnoreCase(lFile.getSymbianPath())) {
					LOGGER.warning("Ignoring duplicate file, which would cause eclipsing errors: "
							+ lExecuteTransfer.toString());
					return false;
				}
			}

			return iFileSet.add(lExecuteTransfer);
		}
		return false;
	}

	/**
	 * Removes an Execute from the ExecuteTransferSet.
	 * 
	 * @param aFile
	 *            The File that needs to be removed from the set.
	 * @return <code>true</code> if the remove method succeded.
	 *         <code>false</code> if the remvoe method failed.
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public final boolean remove(final Object aFile) {
		LOGGER.entering(this.getName(), "remove", aFile);

		return iFileSet.remove(aFile);
	}

	/**
	 * Returns the state of the ExecuteTransferSet.
	 * 
	 * @return <code>true</code> if ExecuteTransferSet is empty,
	 *         <code>false</code> otherwise.
	 * @see java.util.Collection#isEmpty()
	 */
	public final boolean isEmpty() {
		LOGGER.entering(this.getName(), "isEmpty");

		return iFileSet.isEmpty();
	}

	/**
	 * Returns the size of the ExecuteTransferSet.
	 * 
	 * @return The size of the ExecuteTransferSet.
	 * @see java.util.Collection#size()
	 */
	public final int size() {
		LOGGER.entering(this.getName(), "size");

		return iFileSet.size();
	}

	/**
	 * @see java.util.Collection#clear()
	 */
	public final void clear() {
		LOGGER.entering(this.getName(), "clear");

		iFileSet.clear();
	}

	/**
	 * Returns an array of EpocFiles for the ExecuteTransferSet.
	 * 
	 * @return The Array of the EpocFiles in ExecuteTransferSet.
	 * @see java.util.Collection#toArray()
	 */
	public final Object[] toArray() {
		LOGGER.entering(this.getName(), "toArray");

		return iFileSet.toArray();
	}

	/**
	 * Checks if the Execute is in the set ExecuteTransferSet.
	 * 
	 * @param aFile
	 *            The Execute to check if it is in the ExecuteTransferSet.
	 * @return <code>true</code> if ExecuteTransferSet contains the Execute,
	 *         <code>false</code> otherwise.
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public final boolean contains(final Object aFile) {
		LOGGER.entering(this.getName(), "conatins", aFile);

		return iFileSet.contains(aFile);
	}

	/**
	 * Add a collection of ExecuteTransferSet's.
	 * 
	 * @param aFileSet
	 *            Adds another ExecuteTransferSet into this ExecuteTransferSet.
	 * @return <code>true</code> if the <code>addAll</code> operation
	 *         succeded, <code>false</code> otherwise.
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public final boolean addAll(final Collection aFileSet) {
		LOGGER.entering(this.getName(), "addAll", aFileSet);

		return iFileSet.addAll(aFileSet);
	}

	/**
	 * Checks if another ExecuteTransferSet is a subset of this
	 * ExecuteTransferSet.
	 * 
	 * @param aFileSet
	 *            The File Set to check if it is a subset of this
	 *            ExecuteTransferSet.
	 * @return <code>true</code> if the this aEpocFileSet if it is a subset of
	 *         this ExecuteTransferSet, <code>false</code> otherwise.
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public final boolean containsAll(final Collection aFileSet) {
		LOGGER.entering(this.getName(), "containsAll", aFileSet);

		return iFileSet.containsAll(aFileSet);
	}

	/**
	 * Removes an entire ExecuteTransferSet.
	 * 
	 * @param aFileSet
	 *            The ExecuteTransferSet to remove from this ExecuteTransferSet
	 * @return <code>true</code> if the <code>removeAll</code> operation
	 *         succeded, <code>false</code> otherwise.
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public final boolean removeAll(final Collection aFileSet) {
		LOGGER.entering(this.getName(), "removeAll", aFileSet);

		return iFileSet.removeAll(aFileSet);
	}

	/**
	 * Checks if the ExecuteTransferSet collection is retained in this
	 * ExecuteTransferSet.
	 * 
	 * @param aFileSet
	 *            The ExecuteTransferSet to check if it retains.
	 * @return <code>true</code> if the <code>retainAll</code> operation
	 *         succeded, <code>false</code> otherwise.
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public final boolean retainAll(final Collection aFileSet) {
		LOGGER.entering(this.getName(), "retainAll", aFileSet);

		return iFileSet.retainAll(aFileSet);
	}

	/**
	 * Returns the iterator for the ExecuteTransferSet.
	 * 
	 * @return The iterator for the ExecuteTransferSet.
	 * @see java.util.Collection#iterator()
	 */
	public final Iterator iterator() {
		LOGGER.entering(this.getName(), "iterator");

		return iFileSet.iterator();
	}

	/**
	 * Converts the Execute array into an ...
	 * 
	 * @param aFile
	 *            The array of Execute's to.
	 * @return The array of the Execute's.
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	public final Object[] toArray(final Object[] aFile) {
		LOGGER.entering(this.getName(), "toArray", aFile);

		return iFileSet.toArray(aFile);
	}
	
	public static class ConditionalTransfer extends ExecuteTransfer{
		/** Logger. */
		private static Logger LOGGER = Logger.getLogger(ConditionalTransfer.class.getName());

		/** The condition blocks */
		private String iCondition = null;
		
		/**
		 * Constructor for a ConditionalTransfer File. Only handles the host and device
		 * path and the location of the stat working directory.
		 * 
		 * @param aExecuteTransfer
		 *            The ExecuteTransfer object.
		 * @param aCondition
		 *            The condition blocks 
		 * @throws IOException
		 *             If The paramters are incorrect.
		 */
		public ConditionalTransfer(final File aHostPath, final String aDevicePath, final String aCondition) throws IOException {
			super(aHostPath, aDevicePath);
			LOGGER.entering("ConditionalTransfer", "Constructor", new Object[] { aHostPath, aDevicePath, aCondition });
			iCondition = aCondition;
		}
		
		/**
		 * @return The string to add to the package file containing the host and
		 *         device path correctly formatted.
		 */
		public String fileToSIS() {
			StringBuffer lReturnString = new StringBuffer();
			lReturnString.append(iCondition + EOL);
			lReturnString.append(super.fileToSIS());
			lReturnString.append("endif");
			return lReturnString.toString();
		}
		
	}
}
