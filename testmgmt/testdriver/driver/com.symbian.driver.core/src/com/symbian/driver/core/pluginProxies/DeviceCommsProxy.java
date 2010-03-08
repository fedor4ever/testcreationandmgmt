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


package com.symbian.driver.core.pluginProxies;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IDeviceComms;

public class DeviceCommsProxy implements IDeviceComms {

	private static final String EXTENSION_POINT = "com.symbian.driver.core.DeviceComms";
	Logger LOGGER = Logger.getLogger(DeviceCommsProxy.class.getName());

	private static IDeviceComms iActivator = null;

	protected String getExtensionPointName() {
		return EXTENSION_POINT;
	}

	private static DeviceCommsProxy myInstance = null;

	public static DeviceCommsProxy getInstance() throws Exception {
		if (myInstance == null) {
			myInstance = new DeviceCommsProxy();
		}
		return myInstance;
	}

	private DeviceCommsProxy() throws Exception {
		String lExName = TDConfig.getInstance().getPreference(TDConfig.COMM_PLUGIN);
		IExtensionPoint lExP = Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT);
		IExtension[] lEx = lExP.getExtensions();
		if (lEx.length == 0) {
			LOGGER.log(Level.SEVERE, "TestDriver could not find any communication plugin.");
			return;
		}
		for (IExtension extension : lEx) {
			if (extension.getUniqueIdentifier().equalsIgnoreCase(lExName)) {
				iActivator = (IDeviceComms) extension.getConfigurationElements()[0].createExecutableExtension("class");
			}
		}
		if (iActivator == null) {
			LOGGER.log(Level.SEVERE, "TestDriver could not find the requested communiaction plugin : " + lExName);
		}
	}

	public ISymbianProcess createSymbianProcess() {
		return iActivator.createSymbianProcess();
	}

	public ISymbianTransfer createSymbianTransfer() {
		return iActivator.createSymbianTransfer();
	}

	public boolean isDeviceAlive() {
		return iActivator.isDeviceAlive();
	}

	public boolean rebootDevice() {
		return iActivator.rebootDevice();
	}

	public boolean startCommsServer(boolean isEmulator) {
		return iActivator.startCommsServer(isEmulator);
	}

	public void stop(boolean isEmulator) {
		iActivator.stop(isEmulator);
	}
}
