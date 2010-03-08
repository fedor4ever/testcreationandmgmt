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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.master.MasterRemote;
import com.symbian.driver.remoting.master.TestResultSet;
import com.symbian.utils.Zipper;

/**
 * RMI TestClient class. This class implement all the operations that a client
 * agent should implement including the callbacks that will be sent to the
 * server.
 * 
 * @author EngineeringTools
 */
public final class TestClient {
	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(TestClient.class.getName());

	/** Test result file name */
	private static final String RESULTS_ZIP = "TestResult.zip";

	/** Server //host/serive address */
	private String serverAddress = null;

	/** Job folder path */
	private String jobFolder = null;

	private int testJobId = 0;

	private TestJob testJob = null;

	private MasterRemote master = null;

	private SubmissionMonitor submissionMonitor = null;

	private static final int monitorTimeSeconds = 30;

	private static final int noOfAttempts = 10;

	/**
	 * 
	 */
	public TestClient() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
	}

	/**
	 * Callback method. Used by remote Master to update the client.
	 * 
	 * @param aMessage
	 *            a String message.
	 */

	public void update(String aMessage) {
		LOGGER.info(aMessage);
		//  this is not right
		/*
		 * Pattern p = Pattern.compile("^Master: Unregistered .* from
		 * Master\\.$"); if (p.matcher(message).matches()) { if
		 * (submissionMonitor != null) { submissionMonitor.stop(); }
		 * System.exit(0); }
		 */
	}

	/**
	 * Callback method. Used by remote Master to send the results back to the
	 * client.
	 * 
	 * @param aTestResultSet
	 *            a TestResultSet object
	 */
	public void cacheResults(TestResultSet aTestResultSet) {
		try {
			LOGGER.info("Client: Received results. Attempting to cache the results.");
			aTestResultSet.ExtractToFile(jobFolder + File.separator + RESULTS_ZIP);
			LOGGER.info("Client: Results cached to " + jobFolder + ".");
		} catch (IOException lIOE) {
			LOGGER.log(Level.SEVERE, "Client: Problem with caching the results. " + lIOE.getMessage() + lIOE);
		}
	}

	/**
	 * Set server address
	 * 
	 * @param aServerAddress
	 *            a Server address //host/service
	 */
	public void setServerAddress(String aServerAddress) {
		serverAddress = aServerAddress;
	}

	/**
	 * query master status
	 */
	public void queryMasterStatus() {
		try {
			String query = master.queryMasterStatus();
			LOGGER.info(query);
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to query Master.", lRE);
		}
	}

	/**
	 * Update a test package to be sent to a remote master.
	 * 
	 * @param aWorkingFolder
	 *            String a working directory
	 * @param aTestPackage
	 *            String a test package name
	 * @param aImage
	 *            String a image name
	 * @param aPlatsec
	 *            true/false ON/OFF
	 * 
	 * @return the name of the updated test package
	 */
	public String updatePackage(String aWorkingFolder, String aTestPackage, String aImage, boolean aPlatsec, boolean aSysbin, boolean aTestexec, String aTransport, String aRdebug, boolean aTefLite) {
		String lTPUpdatedName = "updated_" + aTestPackage;
		try {
			Zipper lZip = new Zipper();

			File lWorkingFolder = new File(aWorkingFolder);
			File lTestPackageFile = new File(lWorkingFolder, aTestPackage);
			LOGGER.fine("Unzipping " + lTestPackageFile.toString() + "  To: " + lWorkingFolder.toString());

			// enumerate the contained files to be removed after being
			// repackaged
			Enumeration<? extends ZipEntry> lEnumer = (new ZipFile(lTestPackageFile)).entries();
			Vector<String> lUnzippedFileNames = new Vector<String>();

			while (lEnumer.hasMoreElements()) {
				String entryName = ((ZipEntry) lEnumer.nextElement()).getName();
				lUnzippedFileNames.addElement(entryName);
			}

			LOGGER.fine("Unzipping " + lTestPackageFile.toString() + "  To: " + lWorkingFolder.toString());

			// unzip testpackage
			Zipper.Unzip(lTestPackageFile, lWorkingFolder);

			// update manifest
			BufferedWriter lBw = new BufferedWriter(new FileWriter(lWorkingFolder.getCanonicalPath() + File.separator
					+ "Manifest.mf", true));
			PrintWriter lPw = new PrintWriter(lBw);

			if (aImage != null) {
				lPw.println("romFile=" + aImage);
			}

			lPw.println("platsec=" + aPlatsec);

			if (aTransport != null) {
				lPw.println("transport=" + aTransport);
			}
			
			lPw.println("sysbin=" + aSysbin);
			
			lPw.println("statlite=" + aSysbin);
			
			lPw.println("teflite=" + aTefLite);
			
			lPw.println("testexec=" + aTestexec);
			
			if (aRdebug != null ) {
			lPw.println("rdebug=" + aRdebug);
			}
			

			lPw.flush();
			lPw.close();
			lBw.close();

			// add image (rom by the moment)
			if (aImage != null) {
				lZip.addFile(new File(lWorkingFolder.getCanonicalPath() + File.separator + aImage));
			}
			// zip testpackage

			for (int i = 0; i < lUnzippedFileNames.size(); i++) {
				LOGGER.fine("Adding file : " + lWorkingFolder.toString() + " + "
						+ lUnzippedFileNames.elementAt(i));
				lZip.addFile(new File(lWorkingFolder.toString() + File.separator
						+ lUnzippedFileNames.elementAt(i)));
			}

			LOGGER.fine("Zipping the updated package");
			lZip.zip(new File(lWorkingFolder, lTPUpdatedName), lWorkingFolder.getCanonicalPath());

			// delete all files previously unzipped in the working folder

			for (int i = 0; i < lUnzippedFileNames.size(); i++) {
				new File(lWorkingFolder + lUnzippedFileNames.elementAt(i)).delete();
			}

		} catch (IOException lException) {
			LOGGER.log(Level.SEVERE, "Failed to update package. " + lException.getMessage(), lException);
			return null;
		}

		return lTPUpdatedName;
	}

	/**
	 * Create a testJob object.
	 * 
	 * @param aJobFolder
	 *            String : a jobFolder.
	 * @param aTestpackageName
	 *            String: a testpackage name
	 * 
	 */
	public void createJob(String aJobFolder, String aTestpackageName) {
		jobFolder = aJobFolder;
		LOGGER.fine("Client: Creating a test job.");
		testJob = new TestJob(aTestpackageName);
		LOGGER.fine("Client: Test job created.");
	}

	/**
	 * Checks wether a connection is possible with the master and sets master
	 * variable.
	 * 
	 * @return boolean : true = connected, false = a coneaction error happened.
	 * 
	 */
	public boolean connectToMaster() {

		LOGGER.info("Client: Connecting to Master.");
		String lLookupName = null;
		try {
			lLookupName = (serverAddress != null) ? serverAddress : TDConfig.getInstance().getPreference(TDConfig.SERVER);
			master = (MasterRemote) Naming.lookup(lLookupName);
		} catch (NotBoundException lNBE) {
			LOGGER.log(Level.SEVERE, "Client: The remote service " + lLookupName + " is not available. ", lNBE);
			return false;
		} catch (AccessException lAE) {
			LOGGER.log(Level.SEVERE, "Client: Could not connect to Master. Server address supplied: " + lLookupName,
					lAE);
			return false;
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to connect to Master. Master may not be running. ", lRE);
			return false;
		} catch (MalformedURLException lMUE) {
			LOGGER.log(Level.SEVERE, "Client: The RMI Registry lookup name " + lLookupName
					+ " is not appropriately formatted. ", lMUE);
			return false;
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Client: The RMI Registry lookup name " + lLookupName
					+ " is not appropriately formatted. ", lParseException);
			return false;
		}
		LOGGER.info("Client: Connected to Master.");
		return true;
	}

	/**
	 * Register client with master detected by connectToMaster. A testJob need
	 * to be available (created by createJob()).
	 * 
	 * @param aClientName
	 *            String: client name
	 * @return <code>true</code> if the registration suceeded, <code>false</code> otherwise.
	 */
	public boolean registerWithMaster(String aClientName) {

		LOGGER.info("Client: Registering " + aClientName + " with Master.");

		if (testJob == null) {
			LOGGER.log(Level.SEVERE, "Client: Client trying to register with no TestJob in hand.");
			return false;
		}
		testJob.setRegistrationId(aClientName);

		try {
			// send a ref of TestClient wrapped into Client Remote Impl to the
			// remote object.
			ClientRemote client = new ClientRemoteImpl(this);
			if (master == null) {
				LOGGER.log(Level.SEVERE, "Client: Client must be connected before trying to register.");
				return false;
			}

			// try to register, get a remote exception if failed
			master.register(testJob.getRegistrationId(), client);
			LOGGER.info("Client: Registered with Master.");
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to register with the Master. "
					+ "Try again or submit using the async mode.", lRE);
			return false;
		}

		return true;
	}

	/**
	 * monitor a submitted job
	 * 
	 * @throws ParseException
	 */
	public void monitorSubmission() throws ParseException {
		submissionMonitor = new SubmissionMonitor(testJobId, monitorTimeSeconds, noOfAttempts);
		submissionMonitor.start();
	}

	/**
	 * Submit a job to monitor.
	 * 
	 * @return boolean : true == job successfully submitted, false == otherwise.
	 * 
	 */
	public boolean submitJobToMaster() {

		LOGGER.info("Client: Submiting job to Master.");
		if (testJob == null) {
			LOGGER.log(Level.SEVERE, "Client: Client trying to register with no TestJob in hand.");
			return false;
		}
		if (master == null) {
			LOGGER.log(Level.SEVERE, "Client: Client must be connected first.");
			return false;
		}
		if (jobFolder == null) {
			LOGGER.log(Level.SEVERE, "Client: job folder not initialised.");
			return false;
		}

		File sourceFolder = new File(jobFolder);
		if (!(sourceFolder.isDirectory())) {
			LOGGER.log(Level.SEVERE, "Client: The following path does not exist: " + jobFolder
					+ " . Please ensure that the path exists with the relevant input files needed for the job.");
			return false;
		}

		try {
			testJob.setSourceFolder(jobFolder);
			testJobId = master.submitJob(testJob);
			// Job sent off - job promised to send a postcard (TestResultSet).
			//master will call back to get the package file in multiple calls

		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Job was not accepted. ", lRE);
			return false;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Client: Unable to submit the job. ", e);
			return false;
		}

		LOGGER.info("Client: Job submitted to Master.");
		LOGGER.info("Client: Job ID: " + testJobId);
		return true;
	}

	/**
	 * Query job status.
	 * 
	 * @param aTestJobId
	 *            a test job ID
	 * @return boolean : true == operation successful, false==otherwise
	 */
	public boolean queryJobStatus(int aTestJobId) {
		try {
			String query = master.queryJobStatus(aTestJobId);
			LOGGER.info(query);
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to get job status from Master: " + lRE);
			return false;
		}
		return true;
	}

	/**
	 * Get test job results.
	 * 
	 * @param aJobFolder
	 *            Job folder where to recieve the results.
	 * @param aTestJobId
	 *            A test job ID
	 * @return boolean : true == operation successful, false==otherwise
	 */
	public boolean getTestJobResults(String aJobFolder, int aTestJobId) {
		jobFolder = aJobFolder;
		try {
			LOGGER.info("Client: Getting the results for job " + aTestJobId + ".");
			TestResultSet lTestResultSet = master.getResults(aTestJobId);
			if (lTestResultSet != null) {
				LOGGER.info("Client: Received results. Attempting to cache the results.");
				lTestResultSet.ExtractToFile(jobFolder + File.separator + RESULTS_ZIP);
				LOGGER.info("Client: Results cached to " + jobFolder + ".");
			} else {
				LOGGER.info("Client: There are no results for job " + aTestJobId + ".");
				LOGGER.info("Client: Checking status of job " + aTestJobId + " with Master.");
				queryJobStatus(aTestJobId);
			}
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to get results from Master.", lRE);
			return false;
		} catch (IOException lIOE) {
			LOGGER.log(Level.SEVERE, "Client: Problem with caching the results.", lIOE);
			return false;
		}
		return true;
	}

	/**
	 * Terminate a test job
	 * 
	 * @param aTestJobId
	 *            int: a test job ID
	 * @return boolean : true == operation successful, false==otherwise
	 */
	public boolean terminateJob(int aTestJobId) {
		try {
			String message = master.terminateJob(aTestJobId);
			LOGGER.info(message);
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to get Master to terminate job.", lRE);
			return false;
		}
		return true;
	}
	
	/**
	 * Cleanup a test job results
	 * 
	 * @param aTestJobId
	 *            int: a test job ID
	 * @return boolean : true == operation successful, false==otherwise
	 */
	public boolean cleanupJob(int aTestJobId) {
		try {
			String message = master.cleanupJob(aTestJobId);
			LOGGER.info(message);
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Client: Unable to get Master to terminate job.", lRE);
			return false;
		}
		return true;
	}
	
}
