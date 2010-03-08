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

package com.symbian.driver.core.processors;

import java.util.logging.Logger;

import com.symbian.driver.core.environment.TDConfig;

/**
 * @author EngineeringTools
 *
 */
public interface Processor {

	/** Backup Literal. */
	String BACKUP = ".backup";
	
	/** The WinTap Default Port, used for the Emulator. */
	//String WINTAP_DEFAULT_PORT = ":3000";
	
	/** The logger for the Visitor class. */
	Logger LOGGER = Logger.getLogger(EmulatorPreProcessor.class.getName());
	
}
