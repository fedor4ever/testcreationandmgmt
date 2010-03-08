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

/**
 * Class representing Job Counter snapshot.
 * 
 * @author EngineeringTools
 */
public final class JobCounterSnapshot implements Snapshot, Serializable {

	/** Serialised UID. */
	private static final long serialVersionUID = 5178067793141262671L;
	
	/** a counter */
	int counter;

	/**
	 * Standard Constructor.
	 * 
	 * @param aCounter
	 *            int: a counter.
	 */
	public JobCounterSnapshot(int aCounter) {
		counter = aCounter;
	}
}