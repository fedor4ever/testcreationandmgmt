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


package com.symbian.driver.plugins.comms.stat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.jstat.JStat;

/**
 * @author Development Tools
 *
 */
public class StatProxy {
	
	/** Logger. */
	private static final Logger LOGGER = Logger.getLogger(StatProxy.class.getName());
	
	/** Singleton to STAT. */
	private String iTransport;

	public String getTransport() {
		return iTransport;
	}
	
	public JStat getStat() {
		try {
			iTransport = TDConfig.getInstance().getPreference(TDConfig.TRANSPORT);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Failed to get config" , lParseException);
			iTransport = "serial1";
		}
		return JStat.getInstance(iTransport);
	}

}
