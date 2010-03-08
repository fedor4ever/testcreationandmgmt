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

import java.io.Serializable;
import java.util.HashMap;

/**
 * Serialise snapshot of JobTracker.
 * 
 * @author EngineeringTools
 */
public final class JobTrackerSnapshot implements Snapshot, Serializable {

	/** Serialised UID. */
	private static final long serialVersionUID = -3109596331823343083L;
	
	/** Snapshot of jobTracker. */
	HashMap<Integer, String> jobTracker;

	/**
	 * Standard Constructor
	 * 
	 * @param aJobTracker
	 *            HashMap : a job tracker.
	 */
	public JobTrackerSnapshot(HashMap<Integer, String> aJobTracker) {
		jobTracker = aJobTracker;
	}
}