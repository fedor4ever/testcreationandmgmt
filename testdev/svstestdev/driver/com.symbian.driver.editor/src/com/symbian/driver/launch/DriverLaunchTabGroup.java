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


package com.symbian.driver.launch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * DriverLaunchTabGroup: groups all the tabs to be displayed for Launching
 * TestDriver
 * 
 * @author EngineeringTools
 * 
 */
public class DriverLaunchTabGroup extends AbstractLaunchConfigurationTabGroup {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
	 *      java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog aDialog, String aMode) {
		
		ArrayList<ILaunchConfigurationTab> lExtTabs = new ArrayList<ILaunchConfigurationTab>();
		lExtTabs.add(new DriverLaunchMainTab());
		lExtTabs.add(new DriverLaunchArgumentsTab());
		lExtTabs.add(new DriverLaunchDeviceTab());
		
		//get rextension registry
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (null == registry) {
			//.log(Level.SEVERE, "registry returned null, Could not initialise Plugin");
		} else {
			IExtensionPoint lUIConfig = registry.getExtensionPoint("com.symbian.driver.core.TDLauncher");
			if (lUIConfig == null) {
				//error
			} else {
				IExtension[] lConfigExt = lUIConfig.getExtensions();
				for (int i = 0; i < lConfigExt.length; i++) {
					IConfigurationElement[] lconfelems = lConfigExt[i].getConfigurationElements();
					for (IConfigurationElement element : lconfelems) {
						String lUIConfigClass = element.getAttribute("class");
						if (lUIConfigClass != null) {
							//instantiate the class
							ILaunchConfigurationTab lExtTab= null;
							try {
								lExtTab = (ILaunchConfigurationTab) element.createExecutableExtension("class");
							} catch (CoreException e) {
								e.printStackTrace();
							}
							if (lExtTab != null ) {
								lExtTabs.add(lExtTab);
							}
						}
					}
				}				
			}
		}
		
		
		lExtTabs.add(new CommonTab());
		
		setTabs((ILaunchConfigurationTab[]) lExtTabs.toArray(new ILaunchConfigurationTab[0]));
		//setTabs(new ILaunchConfigurationTab[] { new DriverLaunchMainTab(),
		//		new DriverLaunchArgumentsTab(), new DriverLaunchDeviceTab(), new CommonTab() });
	}

}
