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

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.symbian.driver.remoting.master.TestResultSet;

/**
 * Client remote interface.
 * 
 * @author EngineeringTools
 */

public interface ClientRemote extends Remote {

	/**
	 * get a message from master
	 * 
	 * @param aMessage
	 *            String to be sent from master to client.
	 * @throws RemoteException 
	 */
	void update(String aMessage) throws RemoteException;

	/**
	 * get results from master
	 * 
	 * @param aTestResultSet
	 *            TestResultSet to be sent from master to client.
	 * @throws RemoteException 
	 */
	void sendResult(TestResultSet aTestResultSet) throws RemoteException;
	
	/**
	 * read bytes from the remote file start from assigned position   
	 * @param fileName, the file name to read
	 * @param position, the start position
	 * @param length, the length to read
	 * @return the bytes, null if the file has reach end-of-stream
	 * @throws RemoteException
	 */
	byte[] readFile(String fileName, long position, int length) throws RemoteException;

	
}