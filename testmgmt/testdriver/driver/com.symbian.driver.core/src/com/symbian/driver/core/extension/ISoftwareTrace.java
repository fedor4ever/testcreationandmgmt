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

/**
 * @author Development Tools
 * Defining utrace plugin interface  
 *
 */

public interface ISoftwareTrace {
	/**
	 * start the ULogger server
	 * @return
	 */
    public boolean startTrace();
    
    /**
     * stop the ULogger server
     * @return
     */
    public boolean stopTrace();
    
    /**
     * Enable or disable filters
     * @return
     */
    public boolean configFilters(String primaryFiltersToEnable,
    		String secondaryFiltersToEnable,
    		String primaryFiltersToDisable,
    		String secondaryFiltersToDisable);
    
    /**
     * retrieve the trace file to 
     * @param filePath
     * @return
     */
    public boolean getTraceData(String filePath);
    
    /**
     * setup user specified ULogger configuration file
     * @param filePath, the path to config file
     * @return
     */
    public boolean setConfigFile(String filePath);
}
