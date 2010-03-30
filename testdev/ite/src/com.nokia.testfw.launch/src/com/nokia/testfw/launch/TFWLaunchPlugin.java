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
package com.nokia.testfw.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.testfw.launch.monitor.LogMonitor;

/**
 * The activator class controls the plug-in life cycle
 */
public class TFWLaunchPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.nokia.testfw.launch";

	// The shared instance
	private static TFWLaunchPlugin plugin;

	private static TFWLaunchListener iLaunchListener;

	private static LogMonitor iLogMonitor;

	/**
	 * The constructor
	 */
	public TFWLaunchPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		addLaunchListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (iLogMonitor != null) {
			iLogMonitor.stopme();
			iLogMonitor = null;
		}
		super.stop(context);
		removeLaunchListener();
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static TFWLaunchPlugin getDefault() {
		return plugin;
	}

	/**
	 * @return the iLogMonitor
	 */
	public static LogMonitor getLogMonitor() {
		if (iLogMonitor == null) {
			iLogMonitor = new LogMonitor();
			iLogMonitor.start();
		}
		return iLogMonitor;
	}

	public static IProject getSelectedProject() {
		return CarbideBuilderPlugin.getProjectInContext();
	}

	/**
	 * Returns the active workbench window
	 * 
	 * @return the active workbench window
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		if (plugin == null)
			return null;
		IWorkbench workBench = plugin.getWorkbench();
		if (workBench == null)
			return null;
		return workBench.getActiveWorkbenchWindow();
	}

	public static IWorkbenchPage getActivePage() {
		IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
		if (activeWorkbenchWindow == null)
			return null;
		return activeWorkbenchWindow.getActivePage();
	}

	public static void addLaunchListener() {
		ILaunchManager launchManager = DebugPlugin.getDefault()
				.getLaunchManager();
		if (iLaunchListener == null) {
			iLaunchListener = new TFWLaunchListener();
			launchManager.addLaunchListener(iLaunchListener);
		}
	}

	public static void removeLaunchListener() {
		if (iLaunchListener != null) {
			ILaunchManager launchManager = DebugPlugin.getDefault()
					.getLaunchManager();
			launchManager.removeLaunchListener(iLaunchListener);
			iLaunchListener = null;
		}
	}

	public static TFWLaunchListener getLaunchListener() {
		return iLaunchListener;
	}

	public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, "Error", e)); //$NON-NLS-1$
	}

	public static void log(CoreException e) {
		log(e.getStatus()); //$NON-NLS-1$
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(int status, String msg) {
		Status s = new Status(status, PLUGIN_ID, status, msg, null);
		log(s);
	}
}
