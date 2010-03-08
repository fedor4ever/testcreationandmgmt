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

package com.symbian.jstat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EngineeringTools
 *
 */
public class JStatRunCommand extends Thread {
	
	private JStatException iJStatException = null;
	private String iTransport = null;
	private JStatResult iResult = null;
	private String iCommand = null;
	private int iPacketSize = -1;
	
	private volatile Thread iJStatRunCommand;
	
	/** The logger for the JSTAT class. */
	protected static final Logger LOGGER = Logger.getLogger(JStat.class.getName());
	
	/**
	 * @param aTransport
	 * @param aCommand
	 */
	public JStatRunCommand(String aTransport, String aCommand) {
		this.iTransport = aTransport;
		this.iCommand = aCommand;
		this.iJStatRunCommand = this;
		
		setName("JStat " + aCommand);
	}
	
	/**
	 * Constructor with the packet size.
	 * 
	 * @param aTransport
	 * @param aCommand
	 * @param aPacketSize
	 */
	public JStatRunCommand(String aTransport, String aCommand, int aPacketSize) {
		this.iTransport = aTransport;
		this.iCommand = aCommand;
		this.iJStatRunCommand = this;
		this.iPacketSize = aPacketSize; 
		
		setName("JStat " + aCommand);
	}
	
	
	/**
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		Thread lThisThread = Thread.currentThread();
		if (iJStatRunCommand == lThisThread) {
			
			int lHandle = -1;
			
			try {
				String lTransport = iTransport != null ? iTransport : "serial1";
				LOGGER.finer("Creating handle to JStat with transport: " + lTransport);
				lHandle = connect(lTransport);
	
				if (lHandle == -1) {
					throw new JStatException("Could not connect too native JStat with transport: " + lTransport);
				}
				
				if(iPacketSize!=-1)
				{
					LOGGER.finer("Setting packet size to: " + iPacketSize);
					setPacketSize(lHandle, iPacketSize);
				}
	
				LOGGER.finer("Sending Command to Jstat with handle: " + lHandle + ", and command: " + iCommand);
				int lReturn = doCommand(lHandle, iCommand);
	
				if (lReturn == -1) {
					throw new JStatException("Could not execute native JStat.");
				}
				
				LOGGER.finer("Retrieve data with handle: " + lHandle);
				String lRecievedData = getReceivedData(lHandle);
				LOGGER.finer("Retrieve error with handle: " + lHandle);
				String lRecievedError = getError(lHandle);
				
				if (lRecievedData == null || lRecievedError == null){
					String lErrorMessage = (lRecievedData == null ? " JStat returned no data." : "JStat returned data: <" + lRecievedData + ">");
					lErrorMessage += (lRecievedError == null ? "\nJStat returned no error code." : "\nJStat returned error code: <" + lRecievedError + ">");
					
					throw new JStatException(lErrorMessage);
				}
	
				iResult = new JStatResult(lReturn, lRecievedData, lRecievedError);
				
			} catch (JStatException lJStatException) {
				iJStatException = lJStatException;
				Logger.getLogger(JStatRunCommand.class.getName()).log(Level.SEVERE, "JStat Native Code Failed", lJStatException);
			} finally {
				if (lHandle != -1) {
					disconnect(lHandle);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void kill() {
		Thread lKillThread = iJStatRunCommand;
		iJStatRunCommand = null;
		lKillThread.interrupt();
	}

	/**
	 * @return The exception from the STAT Command
	 */
	public JStatException getJStatException() {
		return iJStatException;
	}
	
	/**
	 * @return The Result from the STAT Command
	 */
	public JStatResult getResult() {
		return iResult;
	}
	
	/**
	 * C Native method to connect to a Symbian device.
	 * 
	 * @param parameter
	 *            The connection parameters
	 * @return The result code.
	 */
	private native int connect(String parameter);

	/**
	 * C Native method to disconnect from a Symbian device.
	 * 
	 * @param sHandle
	 *            The sHandle to the connection.
	 * @return The result code.
	 */
	private native int disconnect(int handle);

	/**
	 * C Native method to do a command on the Symbian device.
	 * 
	 * @param sHandle
	 * @param command
	 * @return The result code.
	 */
	private native int doCommand(int handle, String command);

	/**
	 * C Native method to get the error code from JStat
	 * 
	 * @param sHandle
	 *            The sHandle to the connection.
	 * @return The result code.
	 */
	private native String getError(int handle);

	/**
	 * C Native method to get the returned data from the Symbian device.
	 * 
	 * @param sHandle
	 *            The sHandle to the connection.
	 * @return The result code.
	 */
	private native String getReceivedData(int handle);
	
	/**
	 * C Native method to overwrite the default packet size
	 */
	private native void setPacketSize(int handle, int size);

}
