/*
 * Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
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
package com.nokia.testfw.launch.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab;

import com.nokia.cdt.debug.cw.symbian.SettingsData;
import com.nokia.cdt.internal.debug.launch.ui.DebuggerTab;
import com.nokia.cdt.internal.debug.launch.ui.ExceptionsTab;
import com.nokia.cdt.internal.debug.launch.ui.ExecutablesTab;
import com.nokia.testfw.launch.TFWLaunchPlugin;

@SuppressWarnings("restriction")
public class SUTEmulationConfigTagGroup extends
		AbstractLaunchConfigurationTabGroup {

	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		if (mode.equals("debug")) {
			ILaunchConfigurationTab tabs[] = { new SUTEmulatorMainTab(),
					new EnvironmentTab(), new DebuggerTab(),
					new ExecutablesTab(true), new ExceptionsTab(),
					new SourceLookupTab(), new CommonTab() };
			setTabs(tabs);
		} else if (mode.equals("run")) {
			ILaunchConfigurationTab tabs[] = { new SUTEmulatorMainTab(),
					new EnvironmentTab(), new CommonTab() };
			setTabs(tabs);
		}

	}

	public void setDefaults(ILaunchConfigurationWorkingCopy config) {
		super.setDefaults(config);
		IProject project = TFWLaunchPlugin.getSelectedProject();
		SettingsData
				.setDefaults(
						config,
						SettingsData.LaunchConfig_Emulator,
						project);
	}

}
