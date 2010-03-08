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


package com.symbian.driver.plugins.ftptelnet;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.extension.IConfig;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.utils.Epoc;
import com.symbian.utils.config.CheckGetConfig;
import com.symbian.utils.config.CheckSetConfig;

public class Activator extends Plugin implements BundleActivator, IDeviceComms, IConfig, IExecutableExtension {

	/**
	 * The shared instance
	 */
	private static Activator plugin;


	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(Activator.class.getName());

	ISymbianTransfer iTransfer = null;

	ISymbianProcess iProcess = null;

	private String iPAddress = null;

	private static String iFTPPort = "21";
	private static String iFTPPassive = "true";
	private static String iTelnetPort = "23";
	
	private String iTransport = null;

	public enum Attribs {
		FTPPORT_ATTRIBUTE(FtpTelnetLaunchConstants.FTPPORT),
		FTPPASSIVE_ATTRIBUTE(FtpTelnetLaunchConstants.FTPPASSIVE),		
		TELNETPORT_ATTRIBUTE(FtpTelnetLaunchConstants.TELNETPORT);
		String lAttrib;

		Attribs(String aAttrib) {
			this.lAttrib = aAttrib;
		}

		String getName() {
			return lAttrib;
		}
	}
	
	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
		LOGGER.log(Level.FINE, "Activator::Activator");
	}
	
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		LOGGER.fine("static Activator::getDefault");
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

	public ISymbianProcess createSymbianProcess() {
		return TelnetProcess.getInstance();
	}

	public ISymbianTransfer createSymbianTransfer() {
		return FtpTransfer.getInstance();
	}

	public boolean initComm(String aTransport) {

		return false;
	}

	public boolean isDeviceAlive() {
		return TelnetProcess.getInstance().pollDevice();
	}

	public boolean rebootDevice() {

		boolean lReturn = false;
		lReturn = TelnetProcess.getInstance().rebootDevice();
		if (lReturn) {
			// the emulator should be restarted separately
			try {
				if (Epoc.isTargetEmulator(TDConfig.getInstance().getPreference(TDConfig.PLATFORM))) {
					lReturn = startCommsServer(true);
				}
			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Configuration error ", lException);
				return false;
			}
			FtpTransfer.removeAllInstances();
			TelnetProcess.removeAllInstances();
			// it's up to the caller to re-instablish the connection
		}
		return lReturn;
	}

	public boolean startCommsServer(boolean isEmulator) {
		boolean lReturn = true;
		
		if (isEmulator) {
			try {
				iTransport = "tcp:192.168.0.3:3000";
				String WINTAP_DEFAULT_PORT = ":3000";
				iTransport = "tcp:" + TDConfig.getInstance().getPreference(TDConfig.WINTAP) + WINTAP_DEFAULT_PORT;
				TDConfig.getInstance().setPreference(TDConfig.TRANSPORT, iTransport);

				File lEpocRoot = TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT);
				String lVariant = TDConfig.getInstance().getPreference(TDConfig.VARIANT);
				File iWinscwReleaseDir = new File(lEpocRoot, "/epoc32/RELEASE/WINSCW/" + lVariant);
				DeviceUtils.killAllEpocs();

				// Start services wrapper and Poll Device
				ExecuteOnHost iEpoc = new ExecuteOnHost(iWinscwReleaseDir, "epoc.exe -DStartUpMode=2 --");
				iEpoc.doTask(false, TimeOut.NO_TIMEOUT, false);
				LOGGER.info("Started Symbian Device and polling with telnet.");

				if (!DeviceUtils.poll(iEpoc, 10)) {
					lReturn = false;
					LOGGER.log(Level.WARNING, "Failed to poll the device after 10 retries.");
				}
			} catch (Exception lException) {
				lReturn = false;
				LOGGER.log(Level.SEVERE, "Failed to start the emulator.", lException);
			}
		}
		return lReturn;
	}

	public void stop(boolean isEmulator) {
		FtpTransfer.removeAllInstances();
		TelnetProcess.removeAllInstances();
	}

	/**
	 * Set a plugin configuration element.
	 * 
	 * @param lSetting
	 */

	public boolean setConfig(String lSetting) {
		// the plugin recieves the setting as pluginID::key=value it's up to the
		// plugin give sense to this setting

		String lRegExp = "^((.+):(.+))=(.+)$";
		final Pattern lFileNamePattern = Pattern.compile(lRegExp);
		Matcher lMatcher = lFileNamePattern.matcher(lSetting);
		if (!lMatcher.find()) {
			LOGGER.log(Level.SEVERE, "Plugin configuration setting should be in the form PluginID:variable=value.");
			return false;
		}

		// set the configuration

		TDConfig lConfig = TDConfig.getInstance();

		try {
			lConfig.setPreference(FtpTelnetLaunchConstants.PLUGIN_ID + ":" + lMatcher.group(3), lMatcher.group(4));
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not update config with : " + lSetting, lParseException);
			return false;
		}
		if (lMatcher.group(3).equalsIgnoreCase(FtpTelnetLaunchConstants.FTPPASSIVE)) {
			iFTPPassive = lMatcher.group(4);
		} else if (lMatcher.group(3).equalsIgnoreCase(FtpTelnetLaunchConstants.FTPPORT)) {
			iFTPPort = lMatcher.group(4);
		} else if (lMatcher.group(3).equalsIgnoreCase(FtpTelnetLaunchConstants.TELNETPORT)) {
			iTelnetPort = lMatcher.group(4);
		} else {
			LOGGER.log(Level.SEVERE, "variable name incorrect in "  + lSetting);
			return false;
		}

		return true;

	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {		
		//get registry
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		//get extension
		IExtension e = registry.getExtension("com.symbian.driver.plugins.comms.ftptelnet");
		//get variables from plugin.xml
		IConfigurationElement[] variablesElements = e.getConfigurationElements();
		
		
		//initialise TDConfig from plugin config
		TDConfig lMainConfig = TDConfig.getInstance();
		iFTPPassive = initialiseTDConfigElement(variablesElements, lMainConfig, FtpTelnetLaunchConstants.FTPPASSIVE);
		iFTPPort = initialiseTDConfigElement(variablesElements, lMainConfig, FtpTelnetLaunchConstants.FTPPORT);
		iTelnetPort= initialiseTDConfigElement(variablesElements, lMainConfig, FtpTelnetLaunchConstants.TELNETPORT);
		
	}
	
	/**
	 * Checks the TDConfig element, set it from plugin config, if not done yet.
	 * @param config plugin configuration element
	 * @param lMainConfig TD config
	 * @param lVariable the TD config element
	 */
	private String initialiseTDConfigElement(IConfigurationElement[] aElements, TDConfig lMainConfig, String lVariable) {
		String lReturn = null;
		if (!lMainConfig.hasConfig(FtpTelnetLaunchConstants.PLUGIN_ID + ":" + lVariable)) {
			//try to get the one from the plugin
			String name = null;
			
			for (IConfigurationElement el : aElements) {
				IConfigurationElement[] ace = el.getChildren();
				for (IConfigurationElement element : ace) {
					//element.getAttributeNames();
					name = element.getAttribute("name");					
					if (name != null && name.equals(lVariable)) {
						lReturn = element.getAttribute("value");
						lMainConfig.addConfig(FtpTelnetLaunchConstants.PLUGIN_ID + ":" + lVariable, lReturn, new CheckGetConfig(), new CheckSetConfig(), String.class);
						break;
					}
				}			
			}
		} else {
			//TDConfig already set
			try {
				lReturn = lMainConfig.getPreference(FtpTelnetLaunchConstants.PLUGIN_ID + ":" + lVariable);
			} catch (ParseException e) {
				LOGGER.log(Level.SEVERE, "Could not read config , " + e.getMessage());
			}
		}
		return lReturn;
	}

}
