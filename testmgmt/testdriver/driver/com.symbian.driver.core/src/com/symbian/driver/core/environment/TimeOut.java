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

package com.symbian.driver.core.environment;

/**
 * @author EngineeringTools
 *
 */
public class TimeOut {

	/** Constant for No Timeout. */
	public static final int NO_TIMEOUT = 0;
	
	/** Constant for the Emulator Startup Time set at 500. */
	public static final int POLL_STARTUP_TIME = 500;

	/** Constant for the Emulator Attempts to poll set at 20. */
	public static final int POLL_ATTEMPTS = 20;

	/** Constant for the Default Timeout set at 0. */
	public static final int DEFAULT = 100000;

	/** Constant for the time to wait for a restart set at 5000. */
	public static final int RESTART_WAIT = 5000;

	/** Constant for 5 minutes. */
	public static final int FIVE_MIN = 300000;
	
	public static final int TOTAL_TIMEOUT = 36000000;

	/** Constant for 0.5 seconds. */
	public static final long RDEBUG_SLEEP = 500;
}
