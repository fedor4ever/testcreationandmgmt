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

package com.symbian.driver.plugins.reboot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
import org.osgi.framework.BundleContext;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IConfig;
import com.symbian.driver.core.extension.IReboot;
import com.symbian.utils.JarUtils;
import com.symbian.utils.config.CheckGetConfig;
import com.symbian.utils.config.CheckSetConfig;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin implements IConfig, IReboot, IExecutableExtension {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "com.symbian.driver.plugins.reboot";

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	public enum Attribs {
		USERNAME_ATTRIBUTE(RebootDriverLaunchConstants.USER_NAME),
		PASSWD_ATTRIBUTE(RebootDriverLaunchConstants.PASSWORD),
		HOSTADDR_ATTRIBUTE(RebootDriverLaunchConstants.HOST_ADDR),
		OUTLETNO_ATTRIBUTE(RebootDriverLaunchConstants.OUTLET_NUMBER),
		METHOD_ATTRIBUTE(RebootDriverLaunchConstants.REBOOT_METHOD);
		String lAttrib;

		Attribs(String aAttrib) {
			this.lAttrib = aAttrib;
		}

		String getName() {
			return lAttrib;
		}
	}

	/**
	 * Logger for the plugin
	 */
	protected final static Logger LOGGER = Logger.getLogger(Activator.class.getName());

	/**
	 * Members corresponding to configuration for telnet option
	 */
	private String userName = null;

	private String password = null;

	private String hostAddr = null;

	private String outletNo = null;

	/**
	 * Members corresponding to method of rebooting device
	 */
	private String method;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.extensions.IReboot#SwitcthOff()
	 */
	public synchronized boolean SwitcthOff() {
		String switchState = "2";// 2- Immediate Off
		try {
			switchOp(switchState);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.extensions.IReboot#SwitcthOn()
	 */
	public synchronized boolean SwitcthOn() {
		String switchState = "1"; // 1- Immediate On
		try {
			switchOp(switchState);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.extensions.IReboot#Reboot()
	 */
	public synchronized boolean Reboot() {
		return (SwitcthOff() && SwitcthOn());
	}
	
	
	/**
	 * @param switchState
	 * @throws IOException
	 */
	private synchronized void switchOp(String switchState) throws IOException {
		LOGGER.log(Level.INFO, "Activator::switchOp");
		
		// Alternative being "TelnetSwitch"
		if (method.compareToIgnoreCase("PortTalk") == 0)// ATX power
		{
			String state = switchState == "2" ? "OFF" : "ON"; 
			File hardwareSwitchFile = JarUtils.extractResource(Activator.class, "/resource/HardwareSwitch.exe");
			if (hardwareSwitchFile.isFile()) {
				Process p = Runtime.getRuntime().exec("cmd.exe /c " + hardwareSwitchFile.getAbsolutePath() + " " + state);
				BufferedReader iBufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String lOutput = null;
				StringBuffer iOutputBuffer = new StringBuffer();
				while ((lOutput = iBufferedReader.readLine()) != null) {
					lOutput += System.getProperty("line.separator");
					iOutputBuffer.append(lOutput);
					// Parse for errors and redirect to appropriate buffer.
				}
				LOGGER.info(iOutputBuffer.toString());
			} else {
				LOGGER.warning(hardwareSwitchFile.getAbsolutePath() + " was not found");
			}
		} else {
			
			String responseLine = "";
			String[] states ={ 
					"User Name :","Password  :", "Control Console", 
					"Device Manager", "Outlet Management", "Outlet Control/Configuration",
					"Outlet "+outletNo, "Control Outlet","Immediate Off","Press <ENTER> to continue..."};

			String[] answers = { userName, password, "1", "2","1", 
					outletNo, "1", switchState, "YES", "\n" };		

			Socket sock = new Socket(hostAddr, 23);
			PrintWriter dataToTelnetServer = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader dataFromTelnetServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			// start the state machine...
			int state = 0;
			long lastStateChng = System.currentTimeMillis();
			while (state < states.length) {
				while (dataFromTelnetServer.ready()) {
					char nextChar = (char) dataFromTelnetServer.read();
					responseLine += nextChar;
				}
				LOGGER.log(Level.FINE, responseLine);
				if (responseLine.contains(states[state])) // sort of on
				{
					
					LOGGER.log(Level.FINE, "answers[" + state + "]:" + answers[state]);
					dataToTelnetServer.println(answers[state]);
					state++;
					lastStateChng = System.currentTimeMillis();
					continue;
				}
				if ((System.currentTimeMillis() - lastStateChng) > 10000) {
					// too much time since last change of state...
					// we have lost the connection...
					LOGGER.log(Level.SEVERE, "Lost telnet connection");
					break;
				}
			}
			responseLine="";
			dataToTelnetServer.flush();
			dataToTelnetServer.close();
			dataFromTelnetServer.close();
			sock.close();
		}
	}

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
		LOGGER.log(Level.FINE, "Activator::Activator");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		LOGGER.log(Level.FINE, "Activator::start");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		LOGGER.log(Level.FINE, "Activator::stop");
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
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtension e = registry.getExtension("com.symbian.driver.plugins.reboot.devicereboot");
		IConfigurationElement[] variablesElements = e.getConfigurationElements();
		
		//initialise TDConfig from plugin config
		TDConfig lMainConfig = TDConfig.getInstance();

		userName = initialiseTDConfigElement(variablesElements, lMainConfig, RebootDriverLaunchConstants.USER_NAME);
		password = initialiseTDConfigElement(variablesElements, lMainConfig, RebootDriverLaunchConstants.PASSWORD);
		hostAddr = initialiseTDConfigElement(variablesElements, lMainConfig, RebootDriverLaunchConstants.HOST_ADDR);
		outletNo = initialiseTDConfigElement(variablesElements, lMainConfig, RebootDriverLaunchConstants.OUTLET_NUMBER);
		method = initialiseTDConfigElement(variablesElements, lMainConfig, RebootDriverLaunchConstants.REBOOT_METHOD);

	}

	/**
	 * Checks the TDConfig element, set it from plugin config, if not done yet.
	 * @param config plugin configuration element
	 * @param lMainConfig TD config
	 * @param lVariable the TD config element
	 */
	private String initialiseTDConfigElement(IConfigurationElement[] aElements, TDConfig lMainConfig, String lVariable) {
		String lReturn = null;
		if (!lMainConfig.hasConfig(PLUGIN_ID + ":" + lVariable)) {
			//try to get the one from the plugin
			String name = null;
			
			for (IConfigurationElement el : aElements) {
				IConfigurationElement[] ace = el.getChildren();
				for (IConfigurationElement element : ace) {
					element.getAttributeNames();
					name = element.getAttribute("name");					
					if (name.equals(lVariable)) {
						lReturn = element.getAttribute("value");
						lMainConfig.addConfig(PLUGIN_ID + ":" + lVariable, lReturn, new CheckGetConfig(), new CheckSetConfig(), String.class);
						break;
					}
				}			
			}
		} else {
			//TDConfig already set
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
			LOGGER.severe("Could not update config with : " + lSetting  + " : " + e.getMessage());
		}			
			if (lMatcher.group(3).equalsIgnoreCase(RebootDriverLaunchConstants.USER_NAME)) {
				userName = lMatcher.group(4);
			} else if (lMatcher.group(3).equalsIgnoreCase(RebootDriverLaunchConstants.PASSWORD)) {
				password = lMatcher.group(4);
			} else if (lMatcher.group(3).equalsIgnoreCase(RebootDriverLaunchConstants.HOST_ADDR)) {
				hostAddr = lMatcher.group(4);
			} else if (lMatcher.group(3).equalsIgnoreCase(RebootDriverLaunchConstants.OUTLET_NUMBER)) {
				outletNo = lMatcher.group(4);
			} else if (lMatcher.group(3).equalsIgnoreCase(RebootDriverLaunchConstants.REBOOT_METHOD)) {
				method = lMatcher.group(4);
			}			
			
		return true;

	}

}
