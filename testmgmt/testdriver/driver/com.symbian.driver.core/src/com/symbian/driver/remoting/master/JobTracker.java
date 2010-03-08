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

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JobTrack - track the lifecycle/states of a TestJob. Based on the static
 * singleton design pattern.
 * 
 * @author EngineeringTools
 */
public final class JobTracker extends Snapshotable {
	/** a HashMap to hold the job status */
	private static final HashMap<Integer, String> JOBTRACKER = new HashMap<Integer, String>();

	// Instantiation by first reference.
	private static final JobTracker INSTANCE = new JobTracker();

	private static final String SNAPSHOT_STORE_FILE = "jobTracker.snapshot";

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(JobTracker.class.getName());

	/**
	 * Standard Singlton Constructor.
	 */
	private JobTracker() {
		// Singlton constructor
	}

	/**
	 * Add the state of the job
	 * 
	 * @param aTestJobId
	 *            A Job ID
	 * @param aState
	 *            A Job state
	 */
	public static void add(int aTestJobId, String aState) {
		synchronized (JOBTRACKER) {
			JOBTRACKER.put(new Integer(aTestJobId), aState);
		}
	}

	/**
	 * Stop tracking a job
	 * 
	 * @param aTestJobId
	 *            int: a job ID
	 */
	public static void remove(int aTestJobId) {
		synchronized (JOBTRACKER) {
			JOBTRACKER.remove(new Integer(aTestJobId));
		}
	}
	
	/**
	 * Stop Tracking all jobs
	 * 
	 */
	public static void removeAll() {
		synchronized (JOBTRACKER) {
			JOBTRACKER.clear();
		}
	}

	/**
	 * Check to see if a job is being tracked.
	 * 
	 * @param aTestJobId
	 *            A job ID
	 * @return <code>truw</code> if the job is trackable, <code>false</code> otherwise.
	 */
	public static boolean isTrackable(int aTestJobId) {
		synchronized (JOBTRACKER) {
			return (JOBTRACKER.containsKey(new Integer(aTestJobId)));
		}
	}

	/**
	 * Check to see if there is/are job/s in the state specified.
	 * 
	 * @param aState
	 *            String : a state
	 * 
	 * @return boolean true/false
	 */
	public static boolean isState(String aState) {
		synchronized (JOBTRACKER) {
			return (JOBTRACKER.containsValue(aState));
		}
	}

	/**
	 * Find out the current status/state of a job.
	 * 
	 * @param aTestJobId
	 *            A job ID
	 * @return The status of the Job.
	 */
	public static String queryStatus(int aTestJobId) {
		synchronized (JOBTRACKER) {
			return JOBTRACKER.get(new Integer(aTestJobId));
		}
	}

	//  ie singleton?? for javaDoc??
	/**
	 * Technique for allowing outside code to explicitly request the object from
	 * the class. So that it can call the non static methods while respecting
	 * the design and encapsulation of the class.
	 * 
	 * @return The singleton instance of the JobTracker object.
	 */
	public static JobTracker getInstance() {
		return INSTANCE;
	}

	/**
	 * take snap shot
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#takeSnapshot()
	 */
	public void takeSnapshot() {
		try {
			// Snap a shot (photo) while object is posing.
			Snapshot lSnapshot = new JobTrackerSnapshot(new HashMap<Integer, String>(JOBTRACKER));
			// File the shot (photo) using the superclass's persistant filing
			// method.
			serialize(lSnapshot, SNAPSHOT_STORE_FILE);
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not take snapshot of JobTracker. " + lE.getMessage(), lE);
		}
	}

	/**
	 * restore job tracker from a snapshot
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#restoreSnapshot()
	 */
	public void restoreSnapshot() {
		try {
			// Get (most recently taken) shot of this object.
			JobTrackerSnapshot lSnapshot = (JobTrackerSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			// Restore this object to the previous shot.
			if (!((lSnapshot.jobTracker).isEmpty())) {
				JOBTRACKER.putAll(lSnapshot.jobTracker);
			}
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of JobTracker. " + lE.getMessage(), lE);
		} catch (ClassNotFoundException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of JobTracker. " + lE.getMessage(), lE);
		}
	}

	/**
	 * check if job tracker needs to be restored
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#needRestore()
	 */
	public boolean needRestore() {
		boolean lNeedRestore = false;
		try {
			Snapshot lSnapshot = deserialize(SNAPSHOT_STORE_FILE);
			if (!((((JobTrackerSnapshot) lSnapshot).jobTracker).isEmpty())) {
				lNeedRestore = true;
			}
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of JobTracker. " + lE.getMessage());
		} catch (ClassNotFoundException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of JobTracker. " + lE.getMessage());
		}
		return lNeedRestore;
	}
}