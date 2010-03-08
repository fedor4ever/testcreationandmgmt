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


import com.symbian.driver.core.extension.IReboot;

/**
 * @author Development Tools
 *
 */
public class RebootProxy extends PluginProxy<IReboot> implements IReboot{        
	
	private static final String EXTENSION_POINT = "com.symbian.driver.core.DeviceReboot";
	public boolean Reboot() {
		boolean ret = false;
		IReboot rbt = getDelegate() ;
		while( (rbt == null)
			||	((ret = rbt.Reboot()) == false))
		{
			rbt = iterateDelegate() ; 
			if(rbt == null) 
			{
				return false;
			}
		}
		
		return ret;
	}
	
	public boolean SwitcthOff() {
		boolean ret = false;
		IReboot rbt = getDelegate() ;
		while( (rbt == null)
			||	((ret = rbt.SwitcthOff()) == false))
		{
			rbt = iterateDelegate() ; 
			if(rbt == null) 
			{
				return false;
			}
		}
		
		return ret;
	}
	public boolean SwitcthOn() {
		boolean ret = false;
		IReboot rbt = getDelegate() ;
		while( (rbt == null)
			||	((ret = rbt.SwitcthOn()) == false))
		{
			rbt = iterateDelegate() ; 
			if(rbt == null) 
			{
				return false;
			}
		}
		
		return ret;
	}

	protected String getExtensionPointName()
	{
		return EXTENSION_POINT;
	}

	private static RebootProxy myInstance = null;
	public static RebootProxy getInstance() throws Exception
	{
		if(myInstance == null)
			myInstance = new RebootProxy() ;
		return myInstance ; 
	}
	
	private RebootProxy() throws Exception{
		super(); 
	}
}
