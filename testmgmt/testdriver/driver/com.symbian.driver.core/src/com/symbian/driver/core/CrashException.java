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

package com.symbian.driver.core;

import java.net.URL;

/**
 * This exception is thrown when application crash or system crash 
 * happen on device during testing. 
 * 
 */

public class CrashException extends RuntimeException {
    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4242447256430568240L;
	
	/**
	 * the url address of coredump file if exists
	 */
	private URL coreDumpUrl;
	
	/**
     * Constructs a new instance of CrashException.
     * All fields default to null.
     */
    public CrashException() {
	    super();
    }
    
    public URL getCoreDumpUrl() {
    	return coreDumpUrl;
    }
    
    public void setCoreDumpUrl(URL url) {
    	coreDumpUrl = url;
    }
}
