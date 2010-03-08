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

package com.symbian.driver.core.pluginProxies;


import com.symbian.driver.core.extension.ISoftwareTrace;

/**
 * @author Development Tools
 *
 */
public class SoftwareTraceProxy extends PluginProxy<ISoftwareTrace> implements ISoftwareTrace{        
	
	private static final String EXTENSION_POINT = "com.symbian.driver.core.SoftwareTrace";

	private static SoftwareTraceProxy myInstance = null;
	
	protected String getExtensionPointName()
	{
		return EXTENSION_POINT;
	}

	
	public static SoftwareTraceProxy getInstance() throws Exception
	{
		if(myInstance == null)
			myInstance = new SoftwareTraceProxy() ;
		return myInstance ; 
	}
	
	private SoftwareTraceProxy() throws Exception{
		super(); 
	}

	public boolean configFilters(String primaryFiltersToEnable,
    		String secondaryFiltersToEnable,
    		String primaryFiltersToDisable,
    		String secondaryFiltersToDisable) {
		ISoftwareTrace trace = getDelegate() ;
		if (trace == null) {
			return false;
		}
		trace.configFilters(primaryFiltersToEnable,
				secondaryFiltersToEnable,
				primaryFiltersToDisable,
				secondaryFiltersToDisable);
		return false;
	}

	public boolean getTraceData(String filePath) {
		ISoftwareTrace trace = getDelegate() ;
		if (trace == null) {
			return false;
		}
		return trace.getTraceData(filePath);
	}

	public boolean startTrace() {
		ISoftwareTrace trace = getDelegate() ;
		if (trace == null) {
			return false;
		}
		return trace.startTrace();
	}

	public boolean stopTrace() {
		ISoftwareTrace trace = getDelegate() ;
		if (trace == null) {
			return false;
		}
		return trace.stopTrace();
	}


	public boolean setConfigFile(String filePath) {
		ISoftwareTrace trace = getDelegate() ;
		if (trace == null) {
			return false;
		}
		return trace.setConfigFile(filePath);
	}
}
