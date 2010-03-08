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
import java.util.ArrayList;

import com.symbian.driver.remoting.client.TestJob;

/**
 * QueueExecutor snap shot serialiser.
 * 
 * @author EngineeringTools
 */
public final class QueuedExecutorSnapshot implements Snapshot, Serializable {

	/** Serialised UID. */
	private static final long serialVersionUID = -224230623369005177L;

	/** Snapsot of Queued Executor. */
	ArrayList<TestJob> testJobQueue;

	/** Currently running Test Job. */
	TestJob runningTestJob;

	/**
	 * Standard Constructor
	 * 
	 * @param aTestJobQueue
	 *            ArrayList : a queue.
	 * @param aRunningTestJob
	 *            TestJob : a test job.
	 */
	public QueuedExecutorSnapshot(ArrayList<TestJob> aTestJobQueue, TestJob aRunningTestJob) {
		testJobQueue = aTestJobQueue;
		runningTestJob = aRunningTestJob;
	}
}