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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.client.ClientRemote;
import com.symbian.driver.remoting.client.TestJob;
import com.symbian.utils.Utils;

/**
 * RMI Remote Master class implementation.
 * 
 * @author EngineeringTools
 */
public final class MasterRemoteImpl extends UnicastRemoteObject implements MasterRemote {

	/** Serialised UID. */
	private static final long serialVersionUID = -3537075333900856492L;

	/**	the chunk size is 16k to retrieve remote file */
	private static final int TRANSFER_LENGTH = 1024 * 16;
	/** queued executor */
	private final QueuedExecutor executorQueue;

	/** a Job Counter */
	private final JobCounter jobCounter;

	/** a Job Folder */
	private final String jobsFolder;

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(MasterRemoteImpl.class.getName());
	
	
	/**
	 * Standard Constructor. It starts the execution queue.
	 * 
	 * @param aJobsFolder
	 *            String : a folder wehere to store the jobs.
	 * @param aJobCounter
	 *            JobCounter : a job counter object.
	 * @param aExecutorQueue
	 *            QueuedExecutor : an execution queue.
	 * @throws RemoteException
	 */
	public MasterRemoteImpl(String aJobsFolder, JobCounter aJobCounter, QueuedExecutor aExecutorQueue) throws RemoteException {
		super();
		jobsFolder = aJobsFolder;
		jobCounter = aJobCounter;
		executorQueue = aExecutorQueue;
		executorQueue.start();
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#queryMasterStatus()
	 * @return String : a message conating the status of master 'busy' or 'free'
	 *         and the number of jobs in the queue.
	 */
	public String queryMasterStatus() {
		int noOfQueuedJobs = executorQueue.count();
		boolean jobRunning = JobTracker.isState(TestJob.EXECUTING);

		return ("Master: TestDriver is currently " + ((jobRunning) ? "busy" : "free") + " and there are "
				+ ((noOfQueuedJobs < 1) ? "no" : String.valueOf(noOfQueuedJobs)) + " jobs waiting in the queue.");
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#register(java.lang.String,
	 *      com.symbian.driver.remoting.client.ClientRemote)
	 */
	public void register(String aClientName, ClientRemote aClientRemote) {
		ClientRegister.register(aClientName, aClientRemote);
		ClientRegister.getInstance().takeSnapshot();
	}

	public boolean isRegistered(String aClientName) {
		return ClientRegister.isRegistered(aClientName);
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#submitJob(com.symbian.driver.remoting.client.TestJob)
	 */
	public int submitJob(TestJob aTestJob) throws RemoteException {
		// Assign a job id.
		jobCounter.increment();
		jobCounter.takeSnapshot(); // Need to take a snapshot at this point.
		int lTestJobId = jobCounter.get();
		aTestJob.setId(lTestJobId);

		// Add state of this job to the tracker.
		JobTracker.add(aTestJob.getId(), aTestJob.getState());

		// Create the folder for caching the files based on the testJobId.
		File lTargetFolder = new File(jobsFolder + File.separator + lTestJobId + File.separator + "Input");

		try {
			lTargetFolder.mkdirs();
		} catch (SecurityException lSE) {
			LOGGER.log(Level.SEVERE, "Master: Unable to create input folder "
					+ lTargetFolder + " for job " + lTestJobId + ".", lSE);
			throw new RemoteException(
					"Master having problems creating the job input folder.");
		}

		// Cache the test package and image into the appropriate location.
		aTestJob.setTargetFolder(lTargetFolder.getAbsolutePath());
		boolean ret = getRemoteFile(aTestJob);
		if (!ret) {
			throw new RemoteException(
					"Master having problems caching the files sent with the job.");
		}

		// Create the folder for the results based on the testJobId.
		File lResultsFolder = new File(jobsFolder + File.separator + lTestJobId
				+ File.separator + "Output");
		try {
			lResultsFolder.mkdirs();
		} catch (SecurityException lSE) {
			LOGGER.log(Level.SEVERE, "Master: Unable to create output folder for job " + lTestJobId + ".", lSE);
			throw new RemoteException("Master having problems creating the job results folder.");
		}

		// Set the results folder. Needed by testwrapper.
		aTestJob.setResultsFolder(lResultsFolder.getAbsolutePath());

		// Add job to the executor queue.
		executorQueue.queueJob(aTestJob);

		return lTestJobId; // if successfully submitted return job id else if
		// failed exception would have been thrown.
	}

	/**
	 * get package file from client remotely in multiple calls
	 * @return
	 */
	private boolean getRemoteFile(TestJob job) {
		//lookup client remote inteface
		String clientName = job.getRegistrationId();
		ClientRemote clientRemote = ClientRegister.getClient(clientName);
		
		String remoteFileName = job.getSourceFolder()
		    + File.separator
		    + job.getTestPackageName() ;
		String targetFile = job.getTagetFolder()
		    + File.separator
	        + job.getTestPackageName() ;
		
		//read file from remote in multiple calls to avoid OutOfMem if pass large file in memory at once. 
		BufferedOutputStream out = null;
		try {
			File testpackage = new File(targetFile);
			out = new BufferedOutputStream(new FileOutputStream(testpackage));
			byte[] data = null;
			long pos = 0;
			while ((data = clientRemote.readFile(remoteFileName, pos,
					TRANSFER_LENGTH)) != null) {
				
				out.write(data, 0, data.length);
				out.flush();
				pos += data.length;
			}
			LOGGER.log(Level.INFO, "received remote file:" + remoteFileName);
			LOGGER.log(Level.INFO, "  saved to :" + targetFile + " file size:" + testpackage.length());
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Master: Unable to retrive remote package file " 
					+ job.getId() + ".", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ioe) {
				}
				out = null;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#queryJobStatus(int)
	 */
	public String queryJobStatus(int aTestJobId) {
		boolean lTrackable = false;
		String lStatus = null;

		if ((JobTracker.isTrackable(aTestJobId))) {
			lStatus = JobTracker.queryStatus(aTestJobId);
			if (lStatus != null) {
				lTrackable = true;
			}
		}

		return (lTrackable) ? "Master: The current state of job " + aTestJobId + " is " + lStatus + "." : "Master: Job " + aTestJobId + " does not exist.";

	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#terminateJob(int)
	 */
	public String terminateJob(int aTestJobId) {
		boolean lTerminated = false;
		String lStatus = null;
		String lNotTerminateMessage = " ";

		if ((JobTracker.isTrackable(aTestJobId))) {
			lStatus = JobTracker.queryStatus(aTestJobId);
			if (lStatus.equals(TestJob.WAITING)) {
				//de-queue
				TestJob testJob = executorQueue.removeJob(aTestJobId);
				if (testJob != null) {
					if (ClientRegister.isRegistered(testJob.getRegistrationId())) {
						ClientRegister.deregister(testJob.getRegistrationId());
					}
					lTerminated = true;
				}
			} /*else if(lStatus.equals(TestJob.EXECUTING)){
				// stop it.
				TestJob testJob = executorQueue.killJob(aTestJobId);
				if (testJob != null) {
					if (ClientRegister.isRegistered(testJob.getRegistrationId())) {
						ClientRegister.deregister(testJob.getRegistrationId());
					}
					lTerminated = true;
				}
			}*/ else {				
				lNotTerminateMessage = "Current state of job " + aTestJobId + " is " + lStatus + ", so it cannot be terminated.";
			}			
		} else {
			lNotTerminateMessage = "Job " + aTestJobId + " does not exist.";
		}

		return (lTerminated) ? "Master: Job " + aTestJobId + " has been removed sucessfully." : "Master: " + lNotTerminateMessage;
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.MasterRemote#getResults(int)
	 */
	public TestResultSet getResults(int aTestJobId) throws RemoteException {
		String lStatus = null;
		TestResultSet lTestResultSet = null;

		if ((JobTracker.isTrackable(aTestJobId))) {
			lStatus = JobTracker.queryStatus(aTestJobId);
			if ((lStatus.equals(TestJob.COMPLETED)) || (lStatus.equals(TestJob.FAILED))) {
				try {
					lTestResultSet = new TestResultSet(new Integer(aTestJobId));
					lTestResultSet.Embed();
				} catch (IOException lE) {
					LOGGER.log(Level.SEVERE, "Master: Failed to send results for job " + aTestJobId + " on a client request.", lE);
					throw new RemoteException(lE.getMessage());
				} catch (ParseException lE) {
					LOGGER.log(Level.SEVERE, "Master: Error reading Preferences.", lE);
					throw new RemoteException(lE.getMessage());
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
		return lTestResultSet;
	}
	


	/**
	 * @see com.symbian.driver.remoting.master.MasterRemote#cleanupJob(int)
	 */
	public String cleanupJob(int aTestJobId) throws RemoteException {
		String lStatus = null;
		String lMessage = "";

				try {
					File lResultsFolder = new File(jobsFolder); // jobs folders
										
					if (aTestJobId > 0){
						//cleanup one jobs
						if ((JobTracker.isTrackable(aTestJobId))) {
							lStatus = JobTracker.queryStatus(aTestJobId);
							if ((lStatus.equals(TestJob.COMPLETED)) || (lStatus.equals(TestJob.FAILED))) {
								lResultsFolder = new File(jobsFolder + File.separator + aTestJobId);
							} else {
								lMessage = "Job " + aTestJobId + " status is " + lStatus + ". Therefore no cleanup.";
							}
						} else {
							lMessage = lMessage + "Job " + aTestJobId + " is not valid. ";
						}					
					} else if (!JobTracker.isState(TestJob.EXECUTING)){
						//delete repository
						TDConfig CONFIG = TDConfig.getInstance();
						File lRepos = CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT);
						if (!Utils.recusiveDelete(lRepos)) {
							lMessage = lMessage + "Failed to clean repository " + lRepos + ". ";
						}
						
						//empty the working drive
						File lEpoc = CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT);
						if (!Utils.recusiveDelete(lEpoc)) {
							lMessage = lMessage + "Failed to clean epocroot " + lEpoc + ". ";
						}
						
						//reset runnumber
						CONFIG.setPreferenceInteger(TDConfig.RUN_NUMBER, 0);
						
/*						//reset JOB ID 
						// should we reset this????
						jobCounter.reset();
						jobCounter.takeSnapshot();
						*/
						
						//clean stat logs
						if (!Utils.recusiveDelete(new File("c:/apps/stat/work"))) {
							lMessage = lMessage + "Failed to clean stat logs. ";
						}
					} else {
						lMessage = lMessage + "Request refused as there is a job running.";
					}
					
					if (!Utils.recusiveDelete(lResultsFolder)){
						//It's OK just continue
						lMessage = lMessage + "Failed to clean jobs folder " + lResultsFolder + ". ";
					}
				} catch (ParseException lE) {
					lMessage = lMessage + "Master: Error reading Preferences." + lE.getMessage();
				} finally {
					// clean jobtracker
					if (aTestJobId > 0){
					JobTracker.remove(aTestJobId);
					} else {
						JobTracker.removeAll();
					}
					JobTracker.getInstance().takeSnapshot();
				}
				if(lMessage.equals("")){
					if (aTestJobId >0 ) {
						return "Job " + aTestJobId + " cleanedup successfully.";
					} else {
						return "Master files cleaned up successfully.";
					}
				} else {
					return lMessage;
				}	
	}
}