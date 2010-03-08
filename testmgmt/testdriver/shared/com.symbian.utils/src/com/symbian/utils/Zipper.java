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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 
 * 
 * @author EngineeringTools
 */
public class Zipper {
	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(Zipper.class
			.getName());

	final static int BUFFER = 2048;

	HashMap iFiles = new HashMap();

	ZipOutputStream out;

	/**
	 * @param aFile
	 * @param aZipEntryName 
	 */
	public void addFile(File aFile, String aZipEntryName) {
		if (aFile.exists() && aFile.canRead()) {
			String lAbsolutePath = aFile.getAbsolutePath();
			if (!iFiles.containsKey(lAbsolutePath)) {
				iFiles.put(aFile.getAbsolutePath(), aZipEntryName);
			}
		} else {
			LOGGER.log(Level.SEVERE,
					"Trying to add a non existing / not accesible file: "
							+ aFile.toString() + " To zip.");
		}
	}

	/**
	 * @param aFile
	 */
	public void addFile(File aFile) {
		addFile(aFile, aFile.toString());
	}

	/**
	 * @param aListOfFiles
	 */
	public void addFiles(File[] aListOfFiles) {
		for (int i = 0; i < aListOfFiles.length; i++) {
			addFile(aListOfFiles[i]);
		}
	}

	/**
	 * @param aFile
	 * @return aFile The removed File object or null.
	 */
	public File removeFile(File aFile) {
		return (File) iFiles.remove(aFile);
	}

	/**
	 * 
	 */
	public void clear() {
		iFiles.clear();
	}

	/**
	 * @param aZipFile
	 */
	public void zip(File aZipFile) {

		LOGGER.fine("Generating " + aZipFile.getName() + "...");

		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(aZipFile);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			out.setMethod(ZipOutputStream.DEFLATED);
			out.setLevel(Deflater.DEFAULT_COMPRESSION); // use default level

			byte data[] = new byte[BUFFER];
			for (Iterator lIterator = iFiles.keySet().iterator(); lIterator
					.hasNext();) {
				String lKey = (String) lIterator.next();
				FileInputStream fi = new FileInputStream(lKey);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry((String) iFiles.get(lKey));
				LOGGER.fine("adding " + entry.getName() + "...");
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
			out.close();
		} catch (Exception lE) {
			LOGGER.log(Level.SEVERE, "Zippinf file " + aZipFile.toString()
					+ " Failed", lE);
		}

	}

	/**
	 * @param aZipFile
	 * @param relativePath
	 */
	public void zip(File aZipFile, String relativePath) {
		if (relativePath.endsWith("\\")) {
			relativePath = relativePath.substring(0, relativePath.length() - 1);
		}
		LOGGER.fine("Generating " + aZipFile.getName() + " from path : "
				+ relativePath + " ...");

		if (iFiles.size() == 0) {
			LOGGER.log(Level.SEVERE, "There are no files available to build "
					+ aZipFile.getName());
			return;
		}

		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(aZipFile);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			out.setMethod(ZipOutputStream.DEFLATED);
			out.setLevel(Deflater.DEFAULT_COMPRESSION); // use default level

			byte data[] = new byte[BUFFER];
			for (Iterator lIterator = iFiles.keySet().iterator(); lIterator
					.hasNext();) {
				String lKey = (String) lIterator.next();
				String lValue = (String) iFiles.get(lKey);
				FileInputStream fi = new FileInputStream(lKey);
				origin = new BufferedInputStream(fi, BUFFER);

				// int offset = 0;
				ZipEntry entry = null;

				if (relativePath != "") {

					String lEntryName = lValue.toLowerCase().replaceFirst(
							"\\Q" + relativePath.toLowerCase() + "\\E", "");

					entry = new ZipEntry(lEntryName);

				} else {
					// just \
					entry = new ZipEntry(lValue);
				}
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
				origin.close();
			}
			out.close();

		} catch (Exception e) {
			LOGGER.severe("An error occured while zipping "
					+ aZipFile.getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		LOGGER.fine("Generated " + aZipFile.getName() + "...");
	}

	/**
	 * @param aFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void recurseFiles(File aFile) throws IOException,
			FileNotFoundException {
		// File is a directory
		if (aFile.isDirectory()) {
			// Create an array with all of the files and subdirectories
			// of the current directory.
			String[] lFileNames = aFile.list();

			// more files in directory
			if (lFileNames != null) {
				// Recursively add each array entry to make sure that we get
				// subdirectories as well as normal files in the directory.
				for (int i = 0; i < lFileNames.length; i++) {
					recurseFiles(new File(aFile, lFileNames[i]));
				}
			}
		}

		/*---------------------
		 Base case
		 ---------------------*/
		// Otherwise, a file so add it as an entry to the Zip file.
		else {
			addFile(aFile);
		}
	}

	/**
	 * @param aFile
	 * @param filters
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void recurseFiles(File aFile, Pattern[] filters) throws IOException,
			FileNotFoundException {
		// File is a directory
		if (aFile.isDirectory()) {
			// Create an array with all of the files and subdirectories
			// of the current directory.
			String[] lFileNames = aFile.list();

			// more files in directory
			if (lFileNames != null) {
				// Recursively add each array entry to make sure that we get
				// subdirectories as well as normal files in the directory.
				for (int i = 0; i < lFileNames.length; i++) {
					recurseFiles(new File(aFile, lFileNames[i]), filters);
				}
			}
		} else {
			/*---------------------
			 Base case
			 ---------------------*/
			// Otherwise, a file so add it as an entry to the Zip file.
			for (int i = 0; i < filters.length; i++) {
				if (filters[i].matcher(aFile.getAbsolutePath()).matches()) {
					addFile(aFile);
					break;
				}
			}
		}

	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer stb = new StringBuffer();
		Iterator it = iFiles.keySet().iterator();
		while (it.hasNext()) {
			stb.append((String) it.next() + "\n");
		}

		return stb.toString();
	}

	/**
	 * Function will zip a source directory into a zip archive. Any existing zip
	 * will be deleted
	 * 
	 * @param aZipFile
	 * @param aSourcePath
	 * @throws IOException
	 */
	public static void zip(File aZipFile, File aSourcePath) throws IOException {

		// Check if the zip exists
		if (aZipFile.exists() && aZipFile.isFile()) {
			aZipFile.delete();
		}
		Zipper zipper = new Zipper();
		zipper.recurseFiles(aSourcePath);
		zipper.zip(aZipFile);

		/*
		 * // ------------------------------------------------------------ //
		 * Create the zip file //
		 * ------------------------------------------------------------ zos =
		 * new ZipOutputStream(new FileOutputStream(aZipFile)); // Call
		 * recursion. RecurseFiles(aSourcePath); // We are done adding entries
		 * to the zip archive, // so close the Zip output stream. zos.close();
		 */

	}
	
	/**
	 * Purpose : Unzip a zip archive to a directory
	 * 
	 * @param aArchiveZip
	 * @param aDestFile
	 * @throws IOException
	 */
	public static void Unzip(File aArchiveZip, File aDestFile) throws IOException {
		Unzip(aArchiveZip, aDestFile, null, null, null, null, null);
	}

	/**
	 * Purpose : Unzip a zip archive to a directory
	 * 
	 * @param aArchive
	 *            The archive to zip to.
	 * @param aDestinationPath
	 *            The destination of where to place the zip
	 * @param aEpocRoot
	 *            The Symbian Epoc Root directory.
	 * @param aXmlRoot
	 *            The XML root directory
	 * @param aSourceRoot
	 *            The source root directory.
	 * @param aPlatform
	 *            The platform ofthe build
	 * @param aVariant
	 *            The variant to build against
	 * @throws IOException
	 */
	public static void Unzip(File aArchive, File aDestinationPath,
			File aEpocRoot, File aXmlRoot, File aSourceRoot, String aPlatform,
			String aVariant) throws IOException {
		
		try {
			// Open Zip file for reading
			ZipFile zipFile = new ZipFile(aArchive, ZipFile.OPEN_READ);

			// Create an enumeration of the entries in the zip file
			Enumeration zipFileEntries = zipFile.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

				String currentEntry = entry.getName();

				if (currentEntry.startsWith("\\$\\{\\w+\\}")) {
					currentEntry = currentEntry.replaceAll("\\$\\{platform\\}",
							aPlatform);
					currentEntry = currentEntry.replaceAll("\\$\\{build\\}",
							aVariant);
					currentEntry = currentEntry.replaceAll("\\$\\{epocroot\\}",
							aEpocRoot.toString().replace('\\', '/'));
					currentEntry = currentEntry.replaceAll(
							"\\$\\{sourceroot\\}", aSourceRoot.toString()
									.replace('\\', '/'));
					currentEntry = currentEntry.replaceAll("\\$\\{xmlroot\\}",
							aXmlRoot.toString().replace('\\', '/'));
				} else {
					currentEntry = aDestinationPath + File.separator
							+ currentEntry;
				}

				File destFile = new File(currentEntry);

				// grab file's parent directory structure
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				// extract file if not a directory
				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(zipFile
							.getInputStream(entry));

					int currentByte;

					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,
							BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}

					dest.flush();
					dest.close();
					is.close();
				}
			}

			zipFile.close();
		} catch (Exception lException) {
			throw (new IOException("Failed to unzip archive : "
					+ aArchive.getName() + " To :" + aDestinationPath.getName()
					+ "Reason : " + lException.getMessage()));
		}
	}

}
