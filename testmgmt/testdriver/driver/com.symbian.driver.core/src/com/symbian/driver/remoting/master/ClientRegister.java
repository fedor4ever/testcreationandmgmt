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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.remoting.client.ClientRemote;

/**
 * Class to register clients with master.
 * 
 * @author EngineeringTools
 */
public final class ClientRegister extends Snapshotable {
	/** Hash map containing the registered clients */
	private static final HashMap<String, ClientRemote> REGISTER = new HashMap<String, ClientRemote>();

	/** Instantiation by first reference. */
	private static final ClientRegister INSTANCE = new ClientRegister();

	/** snaphost file */
	private static final String SNAPSHOT_STORE_FILE = "clientRegister.snapshot";

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(ClientRegister.class.getName());

	/** Private constructor (singleton) */
	private ClientRegister() {
		// Singleton
	}

	/**
	 * Add a client to hashmap.
	 * 
	 * @param aClientName
	 *            The client name
	 * @param aClientRemote
	 *            Client object refrence
	 * 
	 */
	public static void register(String aClientName, ClientRemote aClientRemote) {
		synchronized (REGISTER) {
			REGISTER.put(aClientName, aClientRemote);
		}
	}

	/**
	 * Deregister a client. remove it from hash map.
	 * 
	 * @param aClientName
	 *            Client name
	 * 
	 */
	public static void deregister(String aClientName) {
		synchronized (REGISTER) {
			REGISTER.remove(aClientName);
		}
	}

	/**
	 * Check if a client is registered.
	 * 
	 * @param aClientName
	 *            A client name
	 * @return <code>true</code> if registered, <code>false</code> otherwise.
	 */
	public static boolean isRegistered(String aClientName) {
		synchronized (REGISTER) {
			return (REGISTER.containsKey(aClientName));
		}
	}

	/**
	 * Get a the reference of a remote client.
	 * 
	 * @param aClientName
	 *            A client name
	 * 
	 * @return A reference to the remote client / null
	 */
	public static ClientRemote getClient(String aClientName) {
		synchronized (REGISTER) {
			return REGISTER.get(aClientName);
		}
	}

	/**
	 * Return the instance of this object.
	 * 
	 * @return The singlton instance of the ClientRegister.
	 * 
	 */
	public static ClientRegister getInstance() {
		return INSTANCE;
	}

	/**
	 * Take a snapshot of the register.
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#takeSnapshot()
	 */
	public void takeSnapshot() {
		try {
			// Snap a shot (photo) while object is posing.
			Snapshot lSnapshot = new ClientRegisterSnapshot(new HashMap<String, ClientRemote>(REGISTER));
			// File the shot (photo) using the superclass's persistant filing
			// method.
			serialize(lSnapshot, SNAPSHOT_STORE_FILE);
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not take snapshot of the client register.", lE);
		}
	}

	/**
	 * Rebuild the register from a snapshot.
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#restoreSnapshot()
	 */
	public void restoreSnapshot() {
		try {
			// Get (most recently taken) shot of this object.
			ClientRegisterSnapshot lSnapshot = (ClientRegisterSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			// Restore this object to the previous shot.
			if (!((lSnapshot.clientRegister).isEmpty())) {
				REGISTER.putAll(lSnapshot.clientRegister);
			}
		} catch (IOException e) {
			LOGGER.log(Level.FINE, "Could not restore client register snapshot.", e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.FINE, "Could not restore client register snapshot.", e);
		}
	}

	/**
	 * Check if the register needs to be restored.
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#needRestore()
	 * @return boolean : true/false.
	 */
	public boolean needRestore() {
		boolean lNeedRestore = false;
		try {
			Snapshot snapshot = deserialize(SNAPSHOT_STORE_FILE);
			if (!((((ClientRegisterSnapshot) snapshot).clientRegister).isEmpty())) {
				lNeedRestore = true;
			}
		} catch (IOException e) {
			LOGGER.log(Level.FINE, "Could not restore client register snapshot.", e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.FINE, "Could not restore client register snapshot.", e);
		}
		return lNeedRestore;
	}

}