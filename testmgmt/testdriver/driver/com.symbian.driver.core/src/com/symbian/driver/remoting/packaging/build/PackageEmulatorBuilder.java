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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.Zipper;

/**
 * emulator package builder class.
 * 
 * @author EngineeringTools
 */
public class PackageEmulatorBuilder implements Builder {
	
	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(PackageEmulatorBuilder.class.getName());

	/**
	 * Build the emulator image.
	 * 
	 * @param aTestPackage
	 *            String : a test pacakage path name.
	 * @see com.symbian.driver.remoting.packaging.build.Builder#Build(java.lang.String)
	 */
	public void Build(File aTestPackage) {
		Zipper zip = new Zipper();
		
		try {
			TDConfig CONFIG = TDConfig.getInstance();
			zip.recurseFiles(new File((CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT) + File.separator + "epoc32" + File.separator + "release" + File.separator + CONFIG.getPreference(TDConfig.PLATFORM) + File.separator
					+ CONFIG.getPreference(TDConfig.VARIANT) + File.separator).replaceAll("\\\\+", "\\\\")));
			zip.recurseFiles(new File((CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT) + File.separator + "epoc32" + File.separator + "data" + File.separator).replaceAll("\\\\+", "\\\\")));
			zip.recurseFiles(new File((CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT) + File.separator + "epoc32" + File.separator + CONFIG.getPreference(TDConfig.PLATFORM) + File.separator).replaceAll("\\\\+", "\\\\")));
		
			File f = aTestPackage;
			
			zip.zip(f, CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getCanonicalPath());
			
			if (f.exists()) {
				LOGGER.info("Generated image package: " + f.getAbsolutePath());
			} else {
				LOGGER.info("Error generating image package: " + f.getName());
			}
			
		} catch (IOException lIO) {
			LOGGER.log(Level.SEVERE, "Failed to generate image package. ", lIO);
		}
		catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "Failed to generate image package.", e);
		}
	}
}
