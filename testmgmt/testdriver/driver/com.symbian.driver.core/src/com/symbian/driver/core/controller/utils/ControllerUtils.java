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


package com.symbian.driver.core.controller.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.JarUtils;

/**
 * Utilties for the Visitor.
 * 
 * @author EngineeringTools
 */
public final class ControllerUtils {
	
	/** Logger for this class. */
	static final Logger LOGGER = Logger.getLogger(ControllerUtils.class.getName());
   
    private static File sKey = null;
    
    private static File sCert = null;


	/**
	 * Returns the key file.
	 * 
	 * @return The file location of the SIS file key.
	 * @throws IOException If the key has an I/O problem.
	 */
	public static final File getKey() throws IOException {
		if (sKey == null) {
			String lKey = null;
			try {
				lKey = TDConfig.getInstance().getPreference(TDConfig.KEY);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.WARNING, "Could not get the custom Key Location.", lParseException);
			}
			
			if (lKey != null && new File(lKey).isFile()) {
				sKey = new File(lKey);
				return sKey;
			}
			
			if ((sKey = JarUtils.extractResource(ControllerUtils.class, "/resource/testdriver.key")) == null ) {
				throw new IOException("Could not extract Key.");
			}
			try {
				TDConfig.getInstance().setPreference(TDConfig.KEY, sKey.getAbsolutePath());
			} catch (ParseException e) {
				//  Auto-generated catch block
			}
		}
		
		return sKey;
	}
	
	/**
	 * Returns the certificate file.
	 * 
	 * @return The file location of the SIS file certificate.
	 * @throws IOException If the certificate has an I/O problem.
	 */
	public static final File getCert() throws IOException {
		if (sCert == null) {
			String lCert = null;
			try {
				lCert = TDConfig.getInstance().getPreference(TDConfig.CERT);
			} catch (ParseException lParseException) {
				LOGGER.log(Level.WARNING, "Could not get the custom Certificate Location.", lParseException);
			}
			
			if (lCert != null && new File(lCert).isFile()) {
				sCert = new File(lCert);
				return sCert;
			}
			
			if ((sCert = JarUtils.extractResource(ControllerUtils.class, "/resource/testdriver.cert")) == null ) {
				throw new IOException("Could not extract Certificate.");
			}
			try {
				TDConfig.getInstance().setPreference(TDConfig.CERT, sCert.getAbsolutePath());
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Could not set cetificate location in config.", lParseException);
			}
		}
		
		return sCert;
	}

}
