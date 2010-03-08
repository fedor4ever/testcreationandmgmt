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


package com.symbian.driver.core.controller.tasks;

import java.io.IOException;

import javax.naming.TimeLimitExceededException;

/**
 * A marker interface for the Execute and Transfer files.
 * 
 * @author EngineeringTools
 */
public interface Execute {

	/** The length of JStat Sleep. */
	int POLL_SLEEP =0;

	/** STAT Ok return code. */
	int STATOK = 13;
	
	/** Max Timeout: 5 hours. */
	int TIMEOUT_MAX = 18000000;

	/**
	 * Executes the operation for this task.
	 * 
	 * @param aDoCleanUp
	 *            <code>true</code> if it should runs the cleanup immediatly
	 *            after executing the task, <code>false</code> if it should
	 *            run the cleanup after leaving the target.
	 * @param aTimeout 
	 * @param captureOutput 
	 * @return 
	 * @throws IOException
	 * @throws JStatException
	 *             Stat Exception
	 * @throws InterruptedException
	 * @throws TimeLimitExceededException 
	 */
	boolean doTask(final boolean aDoCleanUp, final int aTimeout, boolean captureOutput) throws IOException, InterruptedException, TimeLimitExceededException;

	/**
	 * Does cleanup / post processing on the current task.
	 * 
	 * Note that the cleanup time polls POLL_SLEEP interval. Any call to doCleanup will wait at most that amount of time.
	 * @param aWaitForTimeout 
	 * 
	 * @return <code>true</code> if the cleanup succeded, <code>false</code>
	 *         otherwise.
	 * @throws JStatException 
	 * @throws TimeLimitExceededException 
	 */
	boolean doCleanUp(final boolean aWaitForTimeout) throws TimeLimitExceededException;

	/**
	 * Polls to check if the process is running.
	 * 
	 * @return <code>true</code> if the process is running, <code>false</code> otherwise.
	 */
	boolean isRunning();
	
	/**
	 * Returns the output Buffer
	 * 
	 * @return The StringBuffer to output.
	 */
	String getOutput();
}
