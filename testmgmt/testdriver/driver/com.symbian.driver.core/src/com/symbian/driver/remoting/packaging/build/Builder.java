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



package com.symbian.driver.remoting.packaging.build;

import java.io.File;

/**
 * Pacakge Builder base class.
 * 
 * @author EngineeringTools
 */
public interface Builder {
	
	/** Enumeration for Package. */
	public static final int PACKAGE = 0;

	/** Enumeration for Emulator. */
	public static final int EMULATOR_IMAGE = 1;

	/**
	 * Build a test package.
	 * 
	 * @param aTestPackage
	 *            String : Test package name.
	 * 
	 */
	public void Build(File aTestPackage);
}
