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

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.symbian.driver.remoting.client.ClientRemote;
import com.symbian.driver.remoting.client.TestJob;

/**
 * RMI Master remote base class. It conatins all the can do to be published to
 * clients.
 * 
 * @author EngineeringTools
 */
/**
 * 
 * 
 * @author EngineeringTools
 */
public interface MasterRemote extends Remote {

	/**
	 * Returns the status of the master and the number of jobs in the queue.
	 * 
	 * @return String.
	 * @throws RemoteException 
	 */
	String queryMasterStatus() throws RemoteException;

	/**
	 * Register client with master.
	 * 
	 * @param aClientName
	 *            A client name
	 * @param aClientRemote
	 *            ClientRemote object
	 * @throws RemoteException
	 */
	void register(String aClientName, ClientRemote aClientRemote) throws RemoteException;

	/**
	 * Check if a client is registered.
	 * 
	 * @param aClientName
	 *            String : a client name.
	 * @return true/false
	 * @throws RemoteException
	 */
	boolean isRegistered(String aClientName) throws RemoteException;

	/**
	 * SubmitJob to the queue.
	 * 
	 * @param aTestJob
	 *            TestJob object
	 * @return The Job ID number.
	 * @throws RemoteException If RMI throws an exception.
	 */
	int submitJob(TestJob aTestJob) throws RemoteException;

	/**
	 * Query Job Status.
	 * 
	 * @param aTestJobId
	 *            int a test job id
	 * @return The status of the Job.
	 * @throws RemoteException If RMI throws an exception
	 */
	String queryJobStatus(int aTestJobId) throws RemoteException;

	/**
	 * Terminates a job.
	 * 
	 * @param aTestJobId
	 *            int : a job id
	 * @return String : informative message.
	 * @throws RemoteException
	 */
	String terminateJob(int aTestJobId) throws RemoteException;

	/**
	 * get test results as a zip file.
	 * 
	 * @param aTestJobId
	 *            int : a job id
	 * @return TestResultSet object which contains the test results.
	 * @throws RemoteException 
	 */
	TestResultSet getResults(int aTestJobId) throws RemoteException;
	
	/**
	 * Cleanup a job results.
	 * 
	 * @param aTestJobId
	 *            int : a job id
	 * @return String : informative message.
	 * @throws RemoteException
	 */
	String cleanupJob(int aTestJobId) throws RemoteException;

}