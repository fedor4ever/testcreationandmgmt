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


import com.symbian.driver.core.extension.ICoreDump;

/**
 * @author Development Tools
 *
 */
public class CoreDumpProxy extends PluginProxy<ICoreDump> implements ICoreDump{        
	
	private static final String EXTENSION_POINT = "com.symbian.driver.core.CoreDump";
	
	public boolean startServer() {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.startServer();
		}
		return ret;
	}
	
	public boolean stopServer() {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.stopServer();
		}
		return ret;
	}
	
	public boolean isAppCrash() {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.isAppCrash();
		}
		return ret;
	}
	
	public boolean isSystemCrash() {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.isSystemCrash();
		}
		return ret;
	}
	
	/**
	 * @deprecated, use getCrashData(String path) instead
	 */
	public boolean getCrashData(String exeName, String path) {
        return getCrashData(path);
	}
	
	public boolean getCrashData(String path) {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.getCrashData(path);
		}
		return ret;
	}
	
	public boolean monitorApp(String exeName) {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.monitorApp(exeName);
		}
		return ret;
	}
	
	public boolean monitorAllApps() {
		boolean ret = false;
		ICoreDump cdp = getDelegate();
		if (cdp != null) {
		    ret = cdp.monitorAllApps();
		}
		return ret;
	}
	
	
	protected String getExtensionPointName()
	{
		return EXTENSION_POINT;
	}

	private static CoreDumpProxy myInstance = null;
	public static CoreDumpProxy getInstance() throws Exception
	{
		if(myInstance == null)
			myInstance = new CoreDumpProxy() ;
		return myInstance ; 
	}
	
	private CoreDumpProxy() throws Exception{
		super(); 
	}


}
