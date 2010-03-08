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


package com.symbian.driver.utils;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.launch.DriverLaunchConstants;
import com.symbian.utils.Epoc;

public class TestDriverConfigurator {

	private static Logger LOGGER = Logger.getLogger(TestDriverConfigurator.class.getName());

	private static final String iEmptyString = "";

	/**
	 * config TestDriver : Converts the launch configuration and the preferences
	 * to TestDriver configuration.
	 * 
	 * @param aConfig
	 * @return void
	 * @throws
	 */
	public static void configTestDriver(ILaunchConfiguration aConfig) throws ParseException {
		if (aConfig != null) {
			try {
				convertLaunchConfig2TDConfig(aConfig);
			} catch (CoreException lCoreException) {
				LOGGER.log(Level.SEVERE, "Failed to set TestDriver configuration " + lCoreException.getMessage(),
						lCoreException);
			}
		}
		// convertPreference(aPreferences);
	}

	/**
	 * launchConfig2TDConfig : Fills TDConfig from the launch configuration
	 * 
	 * @param ILaunchConfiguration
	 *            aLaunchCongiguration
	 * @return void
	 * @throws ParseException :
	 *             when updating the TDConfig fails
	 * @throws CoreException :
	 *             when reading the launch configuration fails
	 * 
	 */
	private static void convertLaunchConfig2TDConfig(ILaunchConfiguration aConfig) throws ParseException, CoreException {

		TDConfig lConfig = TDConfig.getInstance();

		Map lAttributes = aConfig.getAttributes();

		// EPOC ROOT
		if (lAttributes.containsKey(DriverLaunchConstants.EPOC_ROOT)) {
			lConfig.setPreferenceFile(TDConfig.EPOC_ROOT, new File((String) lAttributes
					.get(DriverLaunchConstants.EPOC_ROOT)));
		}

		// Entry point address : DRIVER + SUITE
		String lDriver = null;
		File lDriverFile = null;
		URI lSuite = null;
		if (lAttributes.containsKey(DriverLaunchConstants.DRIVER)) {
			lDriver = (String) lAttributes.get(DriverLaunchConstants.DRIVER);
			lDriverFile = new File(lDriver);
		}

		String lSuiteString = null;
		if (lAttributes.containsKey(DriverLaunchConstants.ENTRY_POINT_ADDRESS)) {
			lSuiteString = (String) lAttributes.get(DriverLaunchConstants.ENTRY_POINT_ADDRESS);
		}

		if (lDriver != null) {
			// set the xml root
			lConfig.setPreferenceFile(TDConfig.XML_ROOT, new File(lDriver).getParentFile());
			lSuite = URI.createFileURI(lDriverFile.getAbsolutePath());
			if (lSuiteString != null) {
				lSuite = lSuite.appendFragment(lSuiteString);
			}
			lConfig.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, lSuite);
		}

		// Platform
		if (lAttributes.containsKey(DriverLaunchConstants.PLATFORM)) {
			lConfig.setPreference(TDConfig.PLATFORM, (String) lAttributes.get(DriverLaunchConstants.PLATFORM));
		}

		// Variant
		if (lAttributes.containsKey(DriverLaunchConstants.VARIANT)) {
			lConfig.setPreference(TDConfig.VARIANT, (String) lAttributes.get(DriverLaunchConstants.VARIANT));
		}

		// Tefdeps
		if (lAttributes.containsKey(DriverLaunchConstants.TEF_DEPS)) {
			lConfig.setPreferenceBoolean(TDConfig.TEST_EXECUTE, (Boolean) lAttributes
					.get(DriverLaunchConstants.TEF_DEPS));
		}

		// Clean
		if (lAttributes.containsKey(DriverLaunchConstants.CLEAN)) {
			lConfig.setPreferenceBoolean(TDConfig.CLEAN, (Boolean) lAttributes.get(DriverLaunchConstants.CLEAN));
		}

		// Bldmake
		if (lAttributes.containsKey(DriverLaunchConstants.BLDMAKE)) {
			lConfig.setPreferenceBoolean(TDConfig.BLDMAKE, (Boolean) lAttributes.get(DriverLaunchConstants.BLDMAKE));
		}

		// source root
		if (lAttributes.containsKey(DriverLaunchConstants.SOURCE_ROOT)) {
			lConfig.setPreferenceFile(TDConfig.SOURCE_ROOT, new File((String) lAttributes
					.get(DriverLaunchConstants.SOURCE_ROOT)));
		}

		// repository root
		if (lAttributes.containsKey(DriverLaunchConstants.REPOSITORY_ROOT)) {
			lConfig.setPreferenceFile(TDConfig.REPOSITORY_ROOT, new File((String) lAttributes
					.get(DriverLaunchConstants.REPOSITORY_ROOT)));
		}

		// result root
		if (lAttributes.containsKey(DriverLaunchConstants.RESULT_ROOT)) {
			lConfig.setPreferenceFile(TDConfig.RESULT_ROOT, new File((String) lAttributes
					.get(DriverLaunchConstants.RESULT_ROOT)));
		}

		// sysbin
		if (lAttributes.containsKey(DriverLaunchConstants.SYS_BIN)) {
			lConfig.setPreferenceBoolean(TDConfig.SYS_BIN, (Boolean) lAttributes.get(DriverLaunchConstants.SYS_BIN));
		}

		// rdebug
		if (lAttributes.containsKey(DriverLaunchConstants.RDEBUG)) {
			lConfig.setPreference(TDConfig.RDEBUG, (String) lAttributes.get(DriverLaunchConstants.RDEBUG));
		}

		// transport
		if (!Epoc.isTargetEmulator(lConfig.getPreference(TDConfig.PLATFORM))) {

			// set hardware transport
			if (lAttributes.containsKey(DriverLaunchConstants.TRANSPORT)) {

				String lTransport = ((String) lAttributes.get(DriverLaunchConstants.TRANSPORT)).toLowerCase();

				if (lTransport.equals(iEmptyString)) {
					// default serial
					lTransport = "serial";
				}

				String lIPaddress = iEmptyString;
				if (lAttributes.containsKey(DriverLaunchConstants.IP_ADDRESS)) {
					lIPaddress = (String) lAttributes.get(DriverLaunchConstants.IP_ADDRESS);
				}

				String lPort = iEmptyString;
				if (lAttributes.containsKey(DriverLaunchConstants.PORT)) {
					lPort = (String) lAttributes.get(DriverLaunchConstants.PORT);
				}

				if (lTransport.equals("serial") || lTransport.equals("usb") || lTransport.equals("bt")) {
					if (lPort.equals(iEmptyString)) {
						lPort = "1";
					}
					lTransport = lTransport + lPort;
				} else if (lTransport.equals("tcp")) {
					if (!lIPaddress.equals(iEmptyString)) {
						lTransport = "tcp:" + lIPaddress;
						if (!lPort.equals(iEmptyString)) {
							lTransport = lTransport + ":" + lPort;
						}
					}
				}
				lConfig.setPreference(TDConfig.TRANSPORT, lTransport);
			}

		}

		// Commsdb options .. emulator
		if (lAttributes.containsKey(DriverLaunchConstants.COMMDB)) {
			String lCommDB = ((String) lAttributes.get(DriverLaunchConstants.COMMDB)).toLowerCase();
			if (lCommDB.equalsIgnoreCase("overwrite")) {
				String lOverwrite = (String) lAttributes.get(DriverLaunchConstants.COMMDB_OVERWRITE);
				lConfig.setPreference(TDConfig.COMMDB, lCommDB
						+ (!lOverwrite.equals(iEmptyString) ? "=" + lOverwrite : iEmptyString));
			} else {
				lConfig.setPreference(TDConfig.COMMDB, lCommDB);
			}
		}

		// set remaining values (these are defaults as we do not support
		// EKA1)
		lConfig.setPreference(TDConfig.KERNEL, "EKA2");
		lConfig.setPreference(TDConfig.BUILD_NUMBER, "tdep_build");
		lConfig.setPreferenceBoolean(TDConfig.PLATSEC, true);
		lConfig.setPreference(TDConfig.UCC_IP_ADDRESS, "");
	}
}
