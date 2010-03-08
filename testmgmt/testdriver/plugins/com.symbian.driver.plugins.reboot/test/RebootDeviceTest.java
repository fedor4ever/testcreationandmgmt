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

import junit.framework.TestCase;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.symbian.driver.core.extension.IReboot;

public class RebootDeviceTest extends TestCase {

	
	private static final String EXTENSION_POINT = "com.symbian.driver.core.DeviceReboot";
	private String  CLASS_ATTRIBUTE = "class";
	IReboot plugin = null ; 
	
	
	protected void setUp() throws Exception {
		super.setUp();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
	    IExtensionPoint extensionPoint =
	            registry.getExtensionPoint(EXTENSION_POINT);
        IExtension[] extensions = extensionPoint.getExtensions();
        plugin = (IReboot )extensions[0].getConfigurationElements()[0].createExecutableExtension(CLASS_ATTRIBUTE) ;  
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSwitcthOff() {
		if(!plugin.SwitcthOff() )
			fail();
	}

	public void testSwitcthOn() {
		if(!plugin.SwitcthOn())
			fail(); 
	}

	public void testReboot() {
		if(!plugin.Reboot())
			fail();
	}

}
