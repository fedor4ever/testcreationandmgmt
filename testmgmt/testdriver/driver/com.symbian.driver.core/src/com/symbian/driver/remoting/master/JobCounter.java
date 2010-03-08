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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing jobs counter.
 * 
 * @author EngineeringTools
 */
public final class JobCounter extends Snapshotable {
	/** snapshot file */
	private static final String SNAPSHOT_STORE_FILE = "jobCounter.snapshot";

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(JobCounter.class.getName());

	private static int counter;

	/**
	 * Standard Constructor
	 */
	public JobCounter() {
		counter = 0000;
	}
	
	/**
	 * Standard Constructor
	 */
	public void reset() {
		counter = 0000;
	}
	
	/**
	 * Increment counter.
	 */
	public void increment() {
		++counter;
	}

	/**
	 * Decrement counter.
	 */
	public void decrement() {
		--counter;
	}

	/**
	 * Get counter
	 * 
	 * @return int counter.
	 */
	public int get() {
		return counter;
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#takeSnapshot()
	 */
	public void takeSnapshot() {
		try {
			// Snap a shot (photo) while object is posing.
			Snapshot lSnapshot = new JobCounterSnapshot(counter);
			// File the shot (photo) using the superclass's persistant filing
			// method.
			serialize(lSnapshot, SNAPSHOT_STORE_FILE);
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Cound not take a snapshot of Job Counter.", lE);
		}

	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#restoreSnapshot()
	 */
	public void restoreSnapshot() {
		try {
			// Get (most recently taken) shot of this object.
			JobCounterSnapshot lSnapshot = (JobCounterSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			// Restore this object to the previous shot.
			counter = lSnapshot.counter;
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Job Counter.", lE);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Job Counter.", e);
		}
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#needRestore()
	 */
	public boolean needRestore() {
		boolean lNeedRestore = false;
		try {
			JobCounterSnapshot lSnapshot = (JobCounterSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			if (lSnapshot.counter > 0) {
				lNeedRestore = true;
			}
		} catch (IOException e) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Job Counter", e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Job Counter", e);
		}
		return lNeedRestore;
	}
}