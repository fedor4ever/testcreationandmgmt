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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.remoting.master.TestResultSet;

/**
 * RMI client remote implementation class. Implements the ClientRemote
 * interface.
 * 
 * @author EngineeringTools
 */
public final class ClientRemoteImpl extends UnicastRemoteObject implements ClientRemote {

	/** Serialized UID. */
	private static final long serialVersionUID = 8521021263473944488L;
	
	protected final static Logger LOGGER = Logger.getLogger(ClientRemoteImpl.class.getName());
	
	/** Test Job Client. */
	private TestClient client;
	private Hashtable<String, FileChannel>  fileChannelTab;
	private Hashtable<String, FileInputStream>  fileStreamTab;

	/**
	 * Standard Constructor.
	 * 
	 * @param aClient
	 *            a refrence to a TestClient object.
	 * @throws RemoteException 
	 */
	public ClientRemoteImpl(TestClient aClient) throws RemoteException {
		client = aClient;
		fileChannelTab = new Hashtable<String, FileChannel>();
		fileStreamTab = new Hashtable<String, FileInputStream>();
	}

	/**
	 * implement get a message from master
	 * 
	 * @see com.symbian.driver.remoting.client.ClientRemote#update(java.lang.String)
	 * @param aMessage
	 *            a String message to be sent to client.
	 */
	public void update(String aMessage) {
		client.update(aMessage);
	}

	/**
	 * implement get results from master
	 * 
	 * @see com.symbian.driver.remoting.client.ClientRemote#sendResult(com.symbian.driver.remoting.master.TestResultSet)
	 * @param aTestResultSet
	 *            a TestResultSet object to be sent to client.
	 */
	public void sendResult(TestResultSet aTestResultSet) {
		client.cacheResults(aTestResultSet);
	}

	/**
	 * read file chunk by check at.
	 * since it is a remote method, the file channel handler is cached for reuse during multiple calls.
	 * user will continue call this method until the returned byte[] is null. which means
	 * end of the file stream
	 * @param fileName, the file to read from
	 * @param position, the position to start to read
	 * @param maxLen, the max length of data to read in on chunk 
	 */
	public byte[] readFile(String fileName, long position, int maxLen) {
		try {
			FileChannel fc = fileChannelTab.get(fileName);
			if (fc == null) {
				//open file and put the file channel handler into hash table
				fc = openFile(fileName);
			}
			//read from assigned position.
			ByteBuffer byteBuffer = ByteBuffer.allocate(maxLen);
			int len = fc.read(byteBuffer, position);
			if (len <= 0) {
				//close file at the end of stream
				closeFile(fileName);
				return null;
			}
					
			byteBuffer.flip();
			byte[] data = new byte[len];
			byteBuffer.get(data, 0, len);
	        return data;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "error during read packge file:" + fileName, e);
			return null;
		}
	}
	
	/**
	 * open the file channel, and put in the hash table for reuse in multiple read remote call 
	 * @param fileName
	 * @return the opened file channel
	 * @throws FileNotFoundException
	 */
	private FileChannel openFile(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        FileChannel fc = fis.getChannel();
        fileChannelTab.put(fileName, fc);
        fileStreamTab.put(fileName, fis);
        return fc;
	}
	
	/**
	 * close the file channel, and remove from table
	 * @param fileName
	 * @throws IOException
	 */
	private void closeFile(String fileName) throws IOException {
		FileChannel fc= fileChannelTab.get(fileName);
		fc.close();
		fc = null;
        FileInputStream fis = fileStreamTab.get(fileName);
        fis.close();
        fis = null;
	}
	
	/**
	 * close the streams and channel if still open during finalization of 
	 * the remote object
	 */
	protected void finalize() throws Throwable {
		
		Iterator<FileChannel> itorFC = fileChannelTab.values().iterator();
		while (itorFC.hasNext()) {
			FileChannel fc = itorFC.next();
			fc.close();
			fc = null;
			itorFC.remove();
		}
		
		Iterator<FileInputStream> itor = fileStreamTab.values().iterator();
		while (itor.hasNext()) {
			FileInputStream is = itor.next();
			is.close();
			is = null;
			itor.remove();
		}
	}
}