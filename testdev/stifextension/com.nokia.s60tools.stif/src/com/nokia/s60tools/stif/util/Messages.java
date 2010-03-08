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

import org.eclipse.osgi.util.NLS;

/**
 * Class to get localized strings from resource file
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.nokia.s60tools.stif.util.Messages";
	
	public static String path_to_templates;
	public static String preference_description;
	
	static {
	NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
}
