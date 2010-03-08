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

package com.symbian.driver.plugins.romflash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;
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
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IConfig;
import com.symbian.driver.core.extension.IReboot;
import com.symbian.driver.core.extension.IRomFlashing;
import com.symbian.utils.JarUtils;
import com.symbian.utils.config.CheckGetConfig;
import com.symbian.utils.config.CheckSetConfig;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin implements IConfig, IRomFlashing, IExecutableExtension {

	static String method = "usb"; // default

	static String portNumber = null;

	private String rebootExtension = null;

	private String CLASS_ATTRIBUTE = "class";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.symbian.driver.plugins.romflash";

	// The shared instance
	private static Activator plugin;

	protected static final Logger LOGGER = Logger.getLogger(Activator.class.getName());

	// configuration elements...

	public enum Attribs {
		COM_METHOD(RomFlashingrLaunchConstants.COM_METHOD), REBOOT_EXT(RomFlashingrLaunchConstants.REBOOT_EXT), PORT(
				RomFlashingrLaunchConstants.PORT);
		String lAttrib;

		Attribs(String aAttrib) {
			this.lAttrib = aAttrib;
		}

		String getName() {
			return lAttrib;
		}
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtension e = registry.getExtension("com.symbian.driver.plugins.romflashing");
		IConfigurationElement[] variablesElements = e.getConfigurationElements();

		// initialise TDConfig from plugin config
		TDConfig lMainConfig = TDConfig.getInstance();
		rebootExtension = initialiseTDConfigElement(variablesElements, lMainConfig,
				RomFlashingrLaunchConstants.REBOOT_EXT);
		portNumber = initialiseTDConfigElement(variablesElements, lMainConfig, RomFlashingrLaunchConstants.PORT);
		method = initialiseTDConfigElement(variablesElements, lMainConfig, RomFlashingrLaunchConstants.COM_METHOD);
	}

	/**
	 * Checks the TDConfig element, set it from plugin config, if not done yet.
	 * 
	 * @param config
	 *            plugin configuration element
	 * @param lMainConfig
	 *            TD config
	 * @param lVariable
	 *            the TD config element
	 */
	private String initialiseTDConfigElement(IConfigurationElement[] aElements, TDConfig lMainConfig, String lVariable) {
		String lReturn = null;
		if (!lMainConfig.hasConfig(PLUGIN_ID + ":" + lVariable)) {
			// try to get the one from the plugin
			String name = null;

			for (IConfigurationElement el : aElements) {
				IConfigurationElement[] ace = el.getChildren();
				for (IConfigurationElement element : ace) {
					element.getAttributeNames();
					name = element.getAttribute("name");
					if (name.equals(lVariable)) {
						lReturn = element.getAttribute("value");
						lMainConfig.addConfig(PLUGIN_ID + ":" + lVariable, lReturn, new CheckGetConfig(),
								new CheckSetConfig(), String.class);
						break;
					}
				}
			}
		} else {
			// TDConfig already set
			try {
				lReturn = lMainConfig.getPreference(PLUGIN_ID + ":" + lVariable);
			} catch (ParseException e) {
				LOGGER.log(Level.SEVERE, "Could not read config , " + e.getMessage());
			}
		}
		return lReturn;
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
		String lGivenAttr = lMatcher.group(3);
		boolean lFound = false;
		for (Attribs lAttrib : Attribs.values()) {
			if (lAttrib.getName().equalsIgnoreCase(lGivenAttr)) {
				lFound = true;
				break;
			}
		}
		if (!lFound) {
			LOGGER.log(Level.SEVERE, "The specified key is not recognised by this plugin.");
			return false;
		}

		// set the configuration

		TDConfig lConfig = TDConfig.getInstance();

		try {
			lConfig.setPreference(PLUGIN_ID + ":" + lMatcher.group(3), lMatcher.group(4));
		} catch (ParseException e) {
			LOGGER.severe("Could not update config with : " + lSetting + " : " + e.getMessage());
		}

		if (lMatcher.group(3).equalsIgnoreCase(RomFlashingrLaunchConstants.COM_METHOD)) {
			method = lMatcher.group(4);
		} else if (lMatcher.group(3).equalsIgnoreCase(RomFlashingrLaunchConstants.PORT)) {
			portNumber = lMatcher.group(4);
		} else if (lMatcher.group(3).equalsIgnoreCase(RomFlashingrLaunchConstants.REBOOT_EXT)) {
			rebootExtension = lMatcher.group(4);
		}

		return true;

	}

	private boolean switchOff() {
		try {
			return ((IReboot) Platform.getExtensionRegistry().getExtensionPoint(rebootExtension).getExtensions()[0]
					.getConfigurationElements()[0].createExecutableExtension(CLASS_ATTRIBUTE)).SwitcthOff();
		} catch (InvalidRegistryObjectException lInvalidRegistryObjectException) {
			LOGGER.log(Level.SEVERE, "Failed to switch OFF device " + lInvalidRegistryObjectException.getMessage());
			return false;
		} catch (CoreException lCoreException) {
			LOGGER.log(Level.SEVERE, "Failed to switch OFF device " + lCoreException.getMessage());
			return false;
		}
	}

	private boolean switchOn() {
		try {
			return ((IReboot) Platform.getExtensionRegistry().getExtensionPoint(rebootExtension).getExtensions()[0]
					.getConfigurationElements()[0].createExecutableExtension(CLASS_ATTRIBUTE)).SwitcthOn();
		} catch (InvalidRegistryObjectException lInvalidRegistryObjectException) {
			LOGGER.log(Level.SEVERE, "Failed to switch ON device " + lInvalidRegistryObjectException.getMessage());
			return false;
		} catch (CoreException lCoreException) {
			LOGGER.log(Level.SEVERE, "Failed to switch ON device " + lCoreException.getMessage());
			return false;
		}
	}

	public boolean FlashRom(String romLocation) {

		try {
			File rom = new File(romLocation);
			if (method.compareToIgnoreCase("serial") == 0) {
				if (!(switchOff() &&	switchOn())) {
					LOGGER.log(Level.SEVERE, "Could not reboot device, so No rom flashing.");
					return false;
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					LOGGER.log(Level.SEVERE, "Failed to wait for sync.exe to finish.");
				}
				File trgtTestFile = JarUtils.extractResource(Activator.class, "/resource/trgtest.exe");
				if (trgtTestFile.isFile()) {
					ProcessBuilder ld = new ProcessBuilder(trgtTestFile.getAbsolutePath(), portNumber, rom
							.getCanonicalPath());
					ld.directory(trgtTestFile.getParentFile());
					Process pp = ld.start();
					LOGGER.log(Level.INFO,"started trgtest process");
					BufferedReader br = new BufferedReader(new InputStreamReader(pp.getInputStream()));
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) 
					{  
						sb.append(line).append("\n");
						LOGGER.log(Level.INFO, line);
					}
					//String answer = sb.toString();

					try {
						LOGGER.log(Level.INFO,"going to wait now for trgtest to finish");
						pp.waitFor();
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, "Failed to wait for trgtest.exe to finish.");
					}
					LOGGER.log(Level.INFO ,"trgtest returned: "+ pp.exitValue());
					pp.destroy();

				} else {
					LOGGER.log(Level.SEVERE, "Could not find trgtest.exe file.");
				}
			} else // usb rom loading
			{
				// switch the device off...
				switchOff();
				List<File> lis1 = Arrays.asList(File.listRoots());
				// get reboot plugin, and reboot the device
				switchOn(); // or just switch on as things may be
				try {
					Thread.sleep(10000);
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, "Failed to wait for sync.exe to finish.");
					}
				File[] listRoots2 = File.listRoots();
				// find the drive that made the difference!!
				File diff = null;
				for (File root : listRoots2) {
					if (!lis1.contains(root)) // found new drive
					{
						diff = root;
						break;
					}
				}
				File romfl = new File(diff, rom.getName());
				romfl.delete();
				File aCopyFrom = new File(rom.getCanonicalPath());
				FileChannel lSrcChannel = new FileInputStream(aCopyFrom).getChannel();
				FileChannel lDstChannel = new FileOutputStream(romfl).getChannel();
				lDstChannel.transferFrom(lSrcChannel, 0, lSrcChannel.size());
				lSrcChannel.close();
				lDstChannel.close();

				File syncFile = JarUtils.extractResource(Activator.class, "/resource/sync.exe");
				if (syncFile.isFile()) {

					ProcessBuilder ld = new ProcessBuilder(syncFile.getAbsolutePath(), "-r", "-e", diff.toString());

					ld.directory(syncFile.getParentFile());
					ld.start();
					// wait 10 seconds for the rom to load ...
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						LOGGER.log(Level.SEVERE, "Failed to wait for sync.exe to finish.");
					}
				} else {
					LOGGER.log(Level.SEVERE, "Could not find sync.exe file.");
				}
			}
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Could not flash ROM " + lIOException.getMessage());
			return false;
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Failed to wait for sync.exe to finish.");
		}
		return true;
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
