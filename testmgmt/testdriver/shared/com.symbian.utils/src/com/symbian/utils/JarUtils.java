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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

/**
 * @author EngineeringTools
 * 
 */
public class JarUtils {

	private static final Logger LOGGER = Logger.getLogger(JarUtils.class.getName());
	
	private final static int BUFFER = 2048;

   	/**
   	 * Extracts a resource from a JAR only if it doesn't exist.
   	 * 
   	 * @param aClass The class to extract the resource from.
   	 * @param aResource The resource to extract.
   	 * @return The File reference of the resource.
   	 * @throws IOException If the resource could not be extracted.
   	 */
   	public static File extractResourceOnce(Class aClass, String aResource) throws IOException {
   		File lJarFolder = new File(aClass.getResource("/").getPath());
		File lOutputFile = new File(lJarFolder, ".." + aResource);
		if(!lOutputFile.exists())
			lOutputFile = extractFile(aClass, aResource, lOutputFile);
		return lOutputFile;
   	}

	/**
	 * Extracts a resource from a JAR.
	 * 
	 * @param aClass The class to extract the resource from.
	 * @param aResource The resource to extract.
	 * @return The File reference of the resource.
	 * @throws IOException If the resource could not be extracted.
	 */
	public static File extractResource(Class aClass, String aResource) throws IOException {
		
		File lJarFolder = new File(aClass.getResource("/").getPath());
		File lOutputFile = new File(lJarFolder, ".." + aResource);
		lOutputFile = extractFile(aClass, aResource, lOutputFile);
		
		return lOutputFile;
	}

	public static File extractFile(Class aClass, String aResource, File aOutputFile) throws IOException {

		if (aOutputFile.exists() && aOutputFile.isFile()) {
			try {
				//sleep for 3 secs
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//do nothing
			}
			
			//Johnson comment out the delete statement below. since it cause problem when 
			//run multiple instance. 
			if (aOutputFile.getName().endsWith(".dll")) {
				return aOutputFile;
			}
			aOutputFile.delete();
		}

		// we need to extract the resource.
		if (!aOutputFile.getParentFile().isDirectory()) {
			if (!aOutputFile.getParentFile().mkdirs()) {
				throw new IOException("Could not create parent directory of resource: " + aResource);
			}
		}

		URL lResourceURL = aClass.getResource(aResource);

		if (lResourceURL == null) {
			throw new IOException("Resource : " + aResource + "does not exist.");
		}

		LOGGER.finer("Extracting: " + aResource + ", from: " + lResourceURL.toExternalForm());

		BufferedInputStream lBufferedInputStream = new BufferedInputStream(aClass.getResourceAsStream(aResource));

		if (lBufferedInputStream == null) {
			throw new IOException("Could not extract: " + aResource);
		}

		int lCount;
		byte lData[] = new byte[BUFFER];

		FileOutputStream lOutputStream = new FileOutputStream(aOutputFile.getPath());

		BufferedOutputStream lFileOutputStream = null;

		try {
			// write the files to the disk
			lFileOutputStream = new BufferedOutputStream(lOutputStream, BUFFER);

			while ((lCount = lBufferedInputStream.read(lData, 0, BUFFER)) != -1) {
				lFileOutputStream.write(lData, 0, lCount);
			}

		} catch (Exception lException) {
			IOException exception = new IOException("Could not create the resource: " + aResource);
			exception.initCause(lException);
			throw exception;
		} finally {
			//close the streams and release the lock
			try {
				if (lFileOutputStream != null) {
					lFileOutputStream.flush();
					lFileOutputStream.close();
				}
				if (lBufferedInputStream != null) {
					lBufferedInputStream.close();
				}
			} catch (Exception lException) {
				// let it continue, the files might be fine
				LOGGER.log(Level.SEVERE, " Could not close file " + aOutputFile + " properly.", lException);
			}
		}

		if (!aOutputFile.isFile()) {
			throw new IOException("Could not create the resource: " + aResource);
		}
		return aOutputFile;
	}

	public static void extractFolder(Class aClass, String aFolder, File aAbsoluteFile) throws IOException {
		JarFile lJarFile = new JarFile(aClass.getResource("/").getFile());
		ZipEntry lEntry = lJarFile.getEntry(aFolder);
		if (lEntry.isDirectory()) {
			for (Enumeration lEntries = lJarFile.entries(); lEntries.hasMoreElements();) {
				ZipEntry lZipEntry = (ZipEntry) lEntries.nextElement();
		     }
		}

		
		URL lResourceURL = aClass.getResource(aFolder);
		
		if (lResourceURL == null) {
			throw new IOException("Could not extract: " + aFolder);
		}
		
		LOGGER.finer("Extracting: " + aFolder + ", from: " + lResourceURL.toExternalForm());
		
	}
}
