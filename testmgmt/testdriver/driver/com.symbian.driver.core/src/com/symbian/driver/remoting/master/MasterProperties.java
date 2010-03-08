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



package com.symbian.driver.remoting.master;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class representing master properties.
 * 
 * @author EngineeringTools
 */
public class MasterProperties extends Properties {
	
	// Static Instance for master.properties
	
	/** Serialised UID. */
	private static final long serialVersionUID = -4959403797558446225L;
	
	/** Snapshot of Master.properties. */
	static private MasterProperties sInstance;

	/**
	 * get instance of this object
	 * 
	 * @return this instance
	 * @throws IOException 
	 */
	static public MasterProperties getInstance() throws IOException {
		if (sInstance == null)
			sInstance = new MasterProperties();

		return sInstance;
	}

	// Private constructor for singleton
	private MasterProperties() throws IOException {
		FileInputStream propertyFile = new FileInputStream("master.properties");

		this.load(propertyFile);
	}

}
