/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.stif.util;

import com.nokia.s60tools.stif.Constants;

public class PluginUtils {
	/**
	 * Sets the type of module
	 * @param type String type of module
	 */
	static public int setModuleType( String type )
	{
		if (type.equalsIgnoreCase("TESTCLASS") )
			return Constants.MODULE_TYPE_TESTCLASS;
		if ( type.equalsIgnoreCase("HARDCODED") )
			return Constants.MODULE_TYPE_HARDCODED;
		if (type.equalsIgnoreCase("KERNELTEST") )
			return Constants.MODULE_TYPE_KERNELTEST;
		if (type.equalsIgnoreCase("CAPSMODIFIER") )
			return Constants.MODULE_TYPE_CAPSMODIFIER;			
		if (type.equalsIgnoreCase("PYTHON") )
			return Constants.MODULE_TYPE_PYTHONTEST;
		if (type.equalsIgnoreCase("STIFUNIT") )
			return Constants.MODULE_TYPE_STIFUNIT;
		return 0;
	}

}
