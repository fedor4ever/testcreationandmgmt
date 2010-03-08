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

package com.symbian.utils.config;

import org.apache.commons.cli.ParseException;

/**
 * @author EngineeringTools
 *
 */
public class CheckSetConfig {
	
	/**
	 * @param aKey
	 * @param aValue
	 * @return
	 * @throws ParseException 
	 */
	public Object set(final String aKey, final Object aValue) throws ParseException {
		if (aValue == null) {
			throw new NullPointerException("Generic setter: " + aKey + " is null.");
		}
		
		return aValue;
	}
}
