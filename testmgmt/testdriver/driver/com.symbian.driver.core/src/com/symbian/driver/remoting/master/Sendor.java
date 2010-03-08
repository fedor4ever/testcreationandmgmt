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

import org.apache.commons.cli.ParseException;

import com.symbian.driver.remoting.client.ClientRemote;

/**
 * Sendor. Sends the results of a job back to a registered client.
 * 
 * @author EngineeringTools
 */
public final class Sendor {

	private final String registrationId;

	private final int testJobId;

	private ClientUpdate client;

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(ClientRegister.class.getName());

	/**
	 * Standard Constructor
	 * 
	 * @param aTestJobId
	 *            int : a job id.
	 * @param aRegistrationId
	 *            String : a registration Id
	 * @param aClient
	 *            ClientUpdate : a ClientUpdate object.
	 */
	public Sendor(int aTestJobId, String aRegistrationId, ClientUpdate aClient) {
		testJobId = aTestJobId;
		registrationId = aRegistrationId;
		client = aClient;
	}

	/**
	 * 
	 */
	public void send() {
		try {
			LOGGER.fine("Attempting to send results.");
			client.update("Attempting to send results.");
			TestResultSet testResultSet = new TestResultSet(new Integer(testJobId));
			testResultSet.Embed();
			ClientRemote clientRemote = ClientRegister.getClient(registrationId);
			clientRemote.sendResult(testResultSet);
		} catch (IOException lE) {
			LOGGER.log(Level.SEVERE, "Unable to send the results to client.", lE);
			client.update("Unable to send the results back.");
		} catch (ParseException lE) {
			LOGGER.log(Level.SEVERE, "Unable to send the results to client.", lE);
			client.update("Unable to send the results back.");
		}
	}
}