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

package com.symbian.driver.core.extension;

public interface ICoreDump {
	
	/**
	 * start to core dump server
	 * @return operation success or not
	 */
	boolean startServer();
	
	/**
	 * stop the core dump server
	 * @return operation success or not
	 */
	boolean stopServer();

    boolean isAppCrash();
    
    boolean isSystemCrash();
    
	boolean getCrashData(String path);
	
	/**
	 * configure the core dump server to monitor application by name
	 * @param exeName, the executable name to monitor
	 * @return operation success or not
	 */
	boolean monitorApp(String exeName);
	
	/**
	 * configure the core dump server to monitor all applications
	 * @return operation success or not
	 */
	boolean monitorAllApps();
}
