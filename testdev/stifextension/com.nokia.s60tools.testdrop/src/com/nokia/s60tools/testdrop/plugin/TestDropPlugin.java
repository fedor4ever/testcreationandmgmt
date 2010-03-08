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


package com.nokia.s60tools.testdrop.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.nokia.s60tools.testdrop.common.ProductInfoRegistry;
import com.nokia.s60tools.testdrop.resources.ImageKeys;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;

/**
 * The main plug-in class to be used in the desktop.
 */
public class TestDropPlugin extends AbstractUIPlugin {

	/**
	 * Shared plugin instance.
	 */
	private static TestDropPlugin plugin;
	private static IPreferenceStore prefStore;

	/**
	 * Plugin installation location.
	 */
	private static String pluginInstallLocation;

	/**
	 * The constructor.
	 */
	public TestDropPlugin() {
		plugin = this;
	}

	/**
	 * Find plug-in's installation path
	 * 
	 * @return plug-in's installation path
	 * @throws IOException
	 */
	private String getPluginInstallPath() throws IOException {
		// URL to the plugin's root ("/")
		URL relativeURL = getBundle().getEntry("/"); 
		// Converting into local path
		URL localURL = FileLocator.toFileURL(relativeURL);
		// Getting install location in correct form
		File f = new File(localURL.getPath());
		String pluginInstallLocation = f.getAbsolutePath();

		return pluginInstallLocation;
	}

	/**
	 * Returns images path relative to given plug-in install path.
	 * 
	 * @return Path were image resources are located.
	 */
	public static String getImagesPath() {
		return pluginInstallLocation + File.separatorChar
				+ ProductInfoRegistry.getImagesDirectoryName();
	}

	/**
	 * This method is called upon plug-in activation
	 * 
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		LogExceptionHandler.log("TestDrop Plugin STARTUP..."); 

		pluginInstallLocation = getPluginInstallPath();

		LogExceptionHandler.log("pluginInstallLocation: " 
				+ pluginInstallLocation);

		// Call to getImagesPath requires that getPluginInstallPath() is
		// called beforehand.
		String imagesPath = getImagesPath();

		LogExceptionHandler.log("imagesPath: " + imagesPath); 

	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static TestDropPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"com.nokia.s60tools.testdrop.plugin", path); 
	}

	/**
	 * This must be called from UI thread. If called from non-ui thread this
	 * returns <code>null</code>.
	 * 
	 * @return Currently active workbench page.
	 */
	public static IWorkbenchPage getCurrentlyActivePage() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
	}

	/**
	 * This must be called from UI thread. If called from non-ui thread this
	 * returns <code>null</code>.
	 * 
	 * @return The shell of the currently active workbench window..
	 */
	public static Shell getCurrentlyActiveWbWindowShell() {
		IWorkbenchPage page = getCurrentlyActivePage();
		if (page != null) {
			return page.getWorkbenchWindow().getShell();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
	 */
	protected void initializeImageRegistry(ImageRegistry imgReg) {

		//
		// Storing images to plugin's image registry
		//
		Display disp = Display.getCurrent();
		Image img = null;

		img = new Image(disp, getImagesPath() + "\\testdrop_tsk.png"); 
		imgReg.put(ImageKeys.IMG_APP_ICON, img);
	}

	/**
	 * Returns image descriptor for the given key
	 * 
	 * @param key
	 *            Key to find descriptor for
	 * @return image descriptor for the given key
	 */
	public static ImageDescriptor getImageDescriptorForKey(String key) {
		ImageRegistry imgReg = getDefault().getImageRegistry();
		return imgReg.getDescriptor(key);
	}

	/**
	 * Returns image for the given key
	 * 
	 * @param key
	 *            Key to search image for
	 * @return image for the given key
	 */
	public static Image getImageForKey(String key) {
		ImageRegistry imgReg = getDefault().getImageRegistry();
		return imgReg.get(key);
	}

	/**
	 * Returns the PreferenceStore place
	 * 
	 * @return the PreferenceStore place
	 */
	public static IPreferenceStore getPrefStore() {
		if (prefStore == null) {
			prefStore = getDefault().getPreferenceStore();
		}

		return prefStore;
	}

	/**
	 * Returns default display instance
	 * 
	 * @return default display instance
	 */
	public static Display getDefaultDisplay() {
		return Display.getDefault();
	}

}
