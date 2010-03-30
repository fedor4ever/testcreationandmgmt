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
package com.nokia.testfw.resultview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class ResultViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.nokia.testfw.resultview";
	
	private static final String IMAGE_PATH = "icons/full/";

	// The shared instance
	private static ResultViewPlugin plugin;
	
	private static Hashtable<String, Image> images;
	
	/**
	 * The constructor
	 */
	public ResultViewPlugin() {
		images = new Hashtable<String, Image>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		//dispose image
		for (Image image : images.values()) {
			if (!image.isDisposed()) {
				image.dispose();
			}
		}
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ResultViewPlugin getDefault() {
		return plugin;
	}
	
	/**
     * retrieve an image based on its name (from the plug-in standard image folder)
     * @param imageName
     * @return <code>ImageDescriptor</code> of the image if found or the standard missing image descvritor
     */
    public static ImageDescriptor getImageDescriptor(String imageName) {
        return getImageDescriptor(ResultViewPlugin.getDefault().getBundle(), imageName);
    }
    
    public static Image getImage(String imageName) {
    	Image image = images.get(imageName);
		if (image == null) {
			ImageDescriptor desc = getImageDescriptor(imageName);
			image = desc.createImage();
			images.put(imageName, image);
		}
		return image;
    }

    /**
     * retrieve an image based on its name (from the plug-in standard image folder)
     * @param imageName
     * @return <code>ImageDescriptor</code> of the image if found or the standard missing image descriptor
     */
    public static final ImageDescriptor getImageDescriptor(Bundle bundle,
        String imageName) {
        URL url = getImageUrl(bundle, imageName);

        return (url != null) ? ImageDescriptor.createFromURL(url)
                             : ImageDescriptor.getMissingImageDescriptor();
    }
 
    
    /**
     * retrieve the URL of an image file based on its name (from the plug-in standard image folder)
     * @param imageName
     * @return <code>URL</code> of the image if found or null if not found
     */
//    private static URL getImageUrl(String imageName) {
//        return getImageUrl(ResultViewPlugin.getDefault().getBundle(), imageName);
//    }

    public static final URL getImageUrl(Bundle bundle, String imageName) {
        if (bundle == null) {
            bundle = ResultViewPlugin.getDefault().getBundle();
        }

        try {
            URL baseURL = bundle.getEntry("/");
            return new URL(baseURL, IMAGE_PATH + imageName);
        } catch (MalformedURLException e) {
            //            LOG.warn("image URL invalid (image probably missing)", e);
            return null;
        }
    }
    
    public static void log(Throwable e) {
		log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, "Error", e)); //$NON-NLS-1$
	}

	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	public static void log(int status, String msg) {
		Status s = new Status(status, PLUGIN_ID, status, msg, null);
		log(s);
	}


}
