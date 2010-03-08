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

import com.symbian.driver.remoting.client.ClientRemote;

/**
 * Class used to update client.
 * 
 * @author EngineeringTools
 */
public final class ClientUpdate {
	/** a reference to a ClientRemote */
	private ClientRemote clientRemote;

	/** update enabled or not */
	private boolean isEnabled;

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(ClientUpdate.class.getName());

	/**
	 * Standard Constructor
	 */
	public ClientUpdate() {
		isEnabled = false;
	}

	/**
	 * Enable upadtes.
	 * 
	 * @param aClientName
	 *            String : client name
	 * 
	 */
	public void enableUpdate(String aClientName) {
		clientRemote = ClientRegister.getClient(aClientName);
		if (clientRemote != null) {
			isEnabled = true;
		}
	}

	/**
	 * Checks if a client is updates enabled.
	 * 
	 * @return true/false
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Send a message to client.
	 * 
	 * @param aMessage
	 *            The message to update the client with.
	 * 
	 */
	public void update(String aMessage) {
		if (isEnabled) {
			try {
				clientRemote.update("Master: " + aMessage);
			} catch (IOException e) {
				LOGGER.log(Level.WARNING, "Master: Unable update client. Client may have disconnected.", e);
			}
		}
	}

	/**
	 * Disable updates to a client.
	 * 
	 */
	public void disableUpdate() {
		clientRemote = null;
		isEnabled = false;
	}
}