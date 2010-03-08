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



package com.symbian.driver.remoting.packaging.installer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.Epoc;
import com.symbian.utils.Zipper;

/**
 * Package installer class.
 * 
 * @author EngineeringTools
 */
public class PackageInstaller implements Installer {
	
	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(PackageInstaller.class.getName());

	/**
	 * 
	 * @see com.symbian.driver.remoting.packaging.installer.Installer#Install(java.lang.String)
	 */
	public void Install(File aTestPackage) {
		try {
			
			TDConfig CONFIG = TDConfig.getInstance();
			File reposFile = CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT);
			File xmlRootFile = CONFIG.getPreferenceFile(TDConfig.XML_ROOT);
			File outLocation = CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT);

			File out = aTestPackage;
			Zipper.Unzip(out, outLocation);

			Properties manifest = new Properties();
			manifest.load(new FileInputStream((outLocation.getAbsolutePath() + File.separator + "Manifest.mf").replaceAll("\\\\+", "\\\\")));

			String platform = manifest.getProperty("platform");
			String romFile = manifest.getProperty("romFile");

			LOGGER.log(Level.INFO, "Package information: \n\t" + manifest.toString());

			// unzip it

			File lDep = new File((outLocation.getAbsolutePath() + File.separator + "Dependencies.zip").replaceAll("\\\\+", "\\\\"));
			File repository = new File((outLocation.getAbsolutePath() + File.separator + "Repository.zip").replaceAll("\\\\+", "\\\\"));
			File lXml = new File((outLocation.getAbsolutePath() + File.separator + "Xml.zip").replaceAll("\\\\+", "\\\\"));
			File lStat = new File((outLocation.getAbsolutePath() + File.separator + "Stat.zip").replaceAll("\\\\+", "\\\\"));

			try {
				if (lDep.exists()) {
					LOGGER.log(Level.INFO, "Unzipping " + lDep.toString());
					Zipper.Unzip(lDep, outLocation);
					lDep.delete();
				}
				if (repository.exists()) {
					LOGGER.log(Level.INFO, "Unzipping " + repository.toString());
					Zipper.Unzip(repository, reposFile);
					repository.delete();
				}
				if (lXml.exists()) {
					LOGGER.log(Level.INFO, "Unzipping " + lXml.toString());
					Zipper.Unzip(lXml, xmlRootFile);
					lXml.delete();
				}
				if (lStat.exists()) {
					LOGGER.log(Level.INFO, "Unzipping " + lStat.toString());
					Zipper.Unzip(lStat, outLocation);
					lStat.delete();
				}

				if (romFile != null) {
					if (Epoc.isTargetEmulator(platform)) {
						File imageFile = new File(outLocation.getAbsolutePath(), romFile);
						if (imageFile.exists()) {
							LOGGER.log(Level.INFO, "Unzipping emulator image " + imageFile.toString());
							Zipper.Unzip(imageFile, outLocation);
							imageFile.delete();
						}
					} else {
						// write("Flashing "+romFile+" through port
						// "+trgtestPort);
						// restoreRom(romFile,platform,trgtestPort);
					}
				}
			} catch (IOException lE) {
				LOGGER.log(Level.SEVERE, "Failed to unzip file.", lE);
			}

		} catch (IOException lE) {
			LOGGER.log(Level.SEVERE, "package installation failed ", lE);
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "Could not get preference: " + e.getMessage(), e);
		}

	}

}
