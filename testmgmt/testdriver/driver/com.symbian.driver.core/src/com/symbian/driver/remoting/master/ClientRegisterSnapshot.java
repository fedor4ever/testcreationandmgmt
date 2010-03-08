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

import com.symbian.driver.remoting.client.ClientRemote;

/**
 * Serialise a snapshot of the register.
 * 
 * @author EngineeringTools
 */
public final class ClientRegisterSnapshot implements Snapshot, Serializable {

	// private static final long serialVersionUID = 1L;

	/** Serialised UID. */
	private static final long serialVersionUID = -2210612801880238662L;
	
	/** Snapshot of Client Register*/
	HashMap<String, ClientRemote> clientRegister;

	/**
	 * Standard Constructor
	 * 
	 * @param aClientRegister
	 *            HashMap : a client register.
	 */
	public ClientRegisterSnapshot(HashMap<String, ClientRemote> aClientRegister) {
		clientRegister = aClientRegister;
	}
}