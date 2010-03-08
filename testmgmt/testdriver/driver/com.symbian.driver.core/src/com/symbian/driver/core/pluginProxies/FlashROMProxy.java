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

import com.symbian.driver.core.extension.IRomFlashing;

public class FlashROMProxy extends PluginProxy<IRomFlashing> implements IRomFlashing {

	private static final String EXTENSION_POINT = "com.symbian.driver.core.RomFlashing";
	
	@Override
	protected String getExtensionPointName() {
		return EXTENSION_POINT;
	}

	public boolean FlashRom(String romLocation) {
		boolean ret = false;
		IRomFlashing rbt = getDelegate() ;
		while( (rbt == null)
			||	((ret = rbt.FlashRom(romLocation)) == false))
		{
			rbt = iterateDelegate() ; 
			if(rbt == null) 
			{
				return false;
			}
		}
		
		return ret;
	}

	
	private static FlashROMProxy myInstance = null;
	public static FlashROMProxy getInstance() throws Exception
	{
		if(myInstance == null)
			myInstance = new FlashROMProxy() ;
		return myInstance ; 
	}
	
	private FlashROMProxy() throws Exception{
		super(); 
	}
	
}
