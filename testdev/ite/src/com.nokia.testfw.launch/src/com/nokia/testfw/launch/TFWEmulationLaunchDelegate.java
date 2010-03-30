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
/**
 * 
 */
package com.nokia.testfw.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

import com.freescale.cdt.debug.cw.core.cdi.ISessionListener;
import com.freescale.cdt.debug.cw.core.cdi.Session;
import com.nokia.cdt.internal.debug.launch.EmulationLaunchDelegate;

public class TFWEmulationLaunchDelegate extends EmulationLaunchDelegate {

	public void launch(ILaunchConfiguration config, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		TFWLaunchPlugin.addLaunchListener();
		super.launch(config, mode, launch, monitor);
	}

	public void openDebugTraceConsole(final Session session) {
		super.openDebugTraceConsole(session);
		session.addListener(new ISessionListener() {
			public void sessionEnded() {
				TFWLaunchListener listener = TFWLaunchPlugin
						.getLaunchListener();
				if (listener != null) {
					listener.launchRemoved(session.getLaunch());
				}
			}
		});
	}
}
