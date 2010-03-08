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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author Development tools
 * Abstract proxy to process test driver plugins
 *
 */
public abstract class PluginProxy<T> {

	private T delegate = null;
	private static final String CLASS_ATTRIBUTE = "class";
	private IExtension[] extensions = null ;
	private int extIterator =0 ;
	private int confIterator = 0;
	protected final static Logger LOGGER = Logger.getLogger(PluginProxy.class.getName());
	
	public PluginProxy() throws Exception
	{
		init() ; 
	}
	
	
	protected abstract String getExtensionPointName() ;
	/**
	 * called if the currently held delegate fails
	 * to do the job.
	 * @return the next plugin object found installed   
	 * @throws Exception
	 */
	protected T iterateDelegate()  
	{
		delegate = null ; 
		if( (confIterator+1) <  extensions[extIterator].getConfigurationElements().length)
		{
			confIterator++ ;
			return getDelegate() ; 
		}
		while((extIterator+1) < extensions.length)
		{
			if(extensions[(extIterator+1)].getConfigurationElements().length > 0) //has some valid configuration...
			{
				extIterator++ ;
				confIterator =0  ;
				return getDelegate() ; 
			}
		}
		//exhausted our list of extension points and their 
		//corresponding configurations that can be used for 
		//no point re-iterating, its an endless thing, so just return  
		return delegate;
	}
	
	/**
	 * @return the actual plugin object that does the job 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected T getDelegate() {
	    if (null != delegate) {
	        return delegate;
	    }
	    try {
	        delegate  =
	        	(T)extensions[extIterator].getConfigurationElements()[confIterator].createExecutableExtension(CLASS_ATTRIBUTE) ; 
	    }
	 catch (CoreException ex) {
		// process and rethrow ...
		 LOGGER.log(Level.WARNING, ex.getMessage(), ex);	
		 delegate = null ;
	    }
	    return delegate;
	}
	
	/**
	 * Takes care of getting the extension points using the 
	 * plugin registry
	 * @throws Exception
	 */
	private void init() throws Exception
	{
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if(null != registry)
		{
	        IExtensionPoint extensionPoint =
	            registry.getExtensionPoint(getExtensionPointName());
	        extensions = extensionPoint.getExtensions();
	        if(extensions != null)
	        {
	        	//the following should work...
	        	extIterator = 0 ;
	        	confIterator = 0 ; 
	        }
		}
		else
		{
			LOGGER.log(Level.SEVERE, "registry returned null, Could not initialise Plugin Proxy");
			throw new Exception() ;  
		}
	}
}
