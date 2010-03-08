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



package com.symbian.driver.remoting.master;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.Utils;
import com.symbian.utils.Zipper;

/**
 * Serialize test results to be sent to client
 * 
 * @author EngineeringTools
 */
public class TestResultSet implements Serializable {

	/** Serialised UID. */
	private static final long serialVersionUID = -5397248689861926693L;

	/** Test result file name */
	private transient static final String RESULTS_ZIP = "TestResult.zip";

	/** master properties */
	static transient Properties masterProperties;

	static transient String MasterFolder;

	/** Absolute path to zip file, will use above jobRoot */
	private transient File zipFileName;

	/** Max 2 megabyte result */
	static final transient int MAX_SIZE = 2000000;

	// file
	/** size of the zip file - number of bytes */
	private Integer numberOfBytes;

	/** Buffer to store zip data bytes */
	private byte contents[];
	

	/**
	 * Standard Constructor. creates a zip
	 * 
	 * @param aJobID
	 *            int : job id
	 * @throws IOException
	 * @throws ParseException 
	 */
	public TestResultSet(Integer aJobID) throws IOException, ParseException {
		MasterFolder = TDConfig.getInstance().getPreferenceFile(TDConfig.JOBS_FOLDER).getAbsolutePath();
		CreateZip(aJobID);
	}

	/**
	 * Purpose : This function will create the physical zip file which will
	 * contain the test logs for a job run Input : JOB unique identifier
	 * 
	 * @param aJobID
	 *            A job id.
	 * @throws IOException
	 */
	private void CreateZip(Integer aJobID) throws IOException {

		// Absolute path to the files to zip
		String lJobUIDPath = new String(MasterFolder + File.separator + aJobID.toString());
		String lJobLogRoot = new String(lJobUIDPath + File.separator + "output");

		File lTestLogPath = new File(lJobLogRoot);

		// Check the directory exists and it is a directory
		if (!lTestLogPath.exists() || !lTestLogPath.isDirectory()) {
			throw new IOException("Logs folder for Job " + aJobID.toString() + " does not exist!");
		}

		// Absolute path of the zip file
		zipFileName = new File(lJobUIDPath + File.separator + RESULTS_ZIP);
		Zipper lZipResults = new Zipper();
		// avoid picking the logger .lck file.
		Pattern lResultsPat[] = { Pattern.compile(".*(?<!\\.lck)", Pattern.CASE_INSENSITIVE) };
		lZipResults.recurseFiles(lTestLogPath, lResultsPat);
		lZipResults.zip(zipFileName, lTestLogPath.getAbsolutePath());
	}

	
	/**
	 * Purpose : This function will delete the job results files
	 * 
	 * @param aJobID
	 *            A job id.
	 * @throws IOException
	 */
	public void delete(Integer aJobID) throws IOException {

		// Absolute path to the files to zip
		String lJobUIDPath = new String(MasterFolder + File.separator + aJobID.toString());
		
		File lTestLogPath = new File(lJobUIDPath);

		// Check the directory exists and it is a directory
		if (!lTestLogPath.exists() || !lTestLogPath.isDirectory()) {
			throw new IOException("Folder for Job " + aJobID.toString() + " does not exist!");
		}		
		if (!Utils.recusiveDelete(lTestLogPath)){
			throw new IOException("Could not delete job folder for job " + aJobID.toString() + ".");
		}
	}
	
	
	/**
	 * Purpose : To open the zip file and read the contents into a byte array.
	 * This will embed the bytes into this object
	 * 
	 * @throws IOException
	 */
	public void Embed() throws IOException {

		// Create stream to file
		FileInputStream lFis = new FileInputStream(zipFileName);
		DataInputStream lDis = new DataInputStream(lFis);

		// Create the buffer
		contents = new byte[MAX_SIZE];

		// Read contents into byte array
		numberOfBytes = new Integer(lDis.read(contents));

		// Close the stream
		lFis.close();
		lDis.close();
	}

	/**
	 * Purpose : To write the byte contents of zip file to file Input : Absolute
	 * path of file
	 * 
	 * @param aFileName
	 *            String : afile path.
	 * @throws IOException
	 */
	public void ExtractToFile(String aFileName) throws IOException {

		// Create stream to file
		FileOutputStream lFos = new FileOutputStream(aFileName);
		DataOutputStream lDos = new DataOutputStream(lFos);

		// Read contents into byte array
		lDos.write(contents, 0, numberOfBytes.intValue());

		// Close the stream
		lFos.close();
	}
}
