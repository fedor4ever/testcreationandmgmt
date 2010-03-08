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



package com.symbian.driver.remoting.client;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.remoting.master.TDIWrapper;

/**
 * Class representing a test job.
 * 
 * @author EngineeringTools
 */
public final class TestJob implements Serializable {
	
	/** Serialised UID. */
	private static final long serialVersionUID = 4276966660051551656L;
	
	protected final static Logger LOGGER = Logger.getLogger(TestJob.class.getName());

	//  use enum in java 1.5 for job status.
	
	/** Literal for new. */
	public static final String NEW = "new";

	/** Literal for waiting. */
	public static final String WAITING = "waiting";

	/** Literal for executing. */
	public static final String EXECUTING = "executing";

	/** Literal for completed. */
	public static final String COMPLETED = "completed";

	/** Literal for terminated. */
	public static final String TERMINATED = "terminated";

	/** Literal for failed. */
	public static final String FAILED = "failed";

	/** Literal for Emulator. */
	public static final String EMULATOR_IMAGE = "Emulator";

	/** Literal for ROM. */
	public static final String ROM_IMAGE = "ROM";

	/** The name of the test package. */
	private final String testPackageName;

	/** Locations of the TD folders. */
	private String sourceFolder, targetFolder, resultsFolder;

	/** The ID which RMI registers with. */
	private String registrationId;

	/** the ID which the job registers with. */
	private int testJobId;

	/** The current status of the job. */
	private String currentState;

	/** The TestDriver wrapper. */
	private transient TDIWrapper testdriver = null;

	/**
	 * Standard Constructor
	 * 
	 * @param aTestPackageName
	 *            String : test package name
	 */
	public TestJob(String aTestPackageName) {
		testPackageName = aTestPackageName;
		currentState = NEW;
	}

	public String getTestPackageName() {
	    return testPackageName;	
	}
	
	/**
	 * Set registration ID
	 * 
	 * @param aRegistrationId
	 *            String : a registration ID
	 * 
	 */
	public void setRegistrationId(String aRegistrationId) {
		registrationId = aRegistrationId;
	}

	/**
	 * Get registration ID
	 * 
	 * @return String : registration ID
	 */
	public String getRegistrationId() {
		return registrationId;
	}

	/**
	 * set job ID
	 * 
	 * @param aTestJobId
	 *            int : a job ID
	 * 
	 */
	public void setId(int aTestJobId) {
		testJobId = aTestJobId;
	}

	/**
	 * get job ID
	 * 
	 * @return int: a job ID
	 */
	public int getId() {
		return testJobId;
	}

	/**
	 * set job state
	 * 
	 * @param aState
	 *            String : a state
	 * 
	 */
	public void setState(String aState) {
		currentState = aState;
	}

	/**
	 * get job state
	 * 
	 * @return String : the job state
	 */
	public String getState() {
		return currentState;
	}


	public void setSourceFolder(String folder) {
		sourceFolder = folder;
	}
	
	public String getSourceFolder() {
		return sourceFolder;
	}
	
	public void setTargetFolder(String folder) {
		targetFolder = folder;
	}
	
	public String getTagetFolder() {
		return targetFolder;
	}
	
	
	

	/**
	 * set results folder
	 * 
	 * @param aResultsFolder
	 *            File: results folder location
	 * 
	 */
	public void setResultsFolder(String folder) {
		resultsFolder = folder;
	}
	

	/**
	 * execute a test job
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void execute() throws IOException, ArrayIndexOutOfBoundsException, TimeLimitExceededException, ParseException {

		File lTestpackage = new File(targetFolder + File.separator + testPackageName);

		LOGGER.log(Level.INFO, "execute job on package: " + lTestpackage.getAbsolutePath());
		testdriver = new TDIWrapper(lTestpackage, new File(resultsFolder));
		testdriver.start();

	}

	/**
	 * terminate a test job
	 * 
	 */
	public void stop() {
		if (testdriver != null) {
			testdriver.stop();
		}
	}

}