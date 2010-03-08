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

package com.symbian.driver.core.processors;

import java.util.logging.Level;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.core.controller.utils.SerialListener;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;

/**
 * @author EngineeringTools
 *
 */
public class HardwarePostProcessor extends Thread implements PostProcessor {
	
	private SerialListener iRDebugThread = null;
	private ExecuteTransferSet iTefDeps = null;

	/**
	 * @param aStreamRDebug
	 */
	public HardwarePostProcessor(SerialListener aStreamRDebug) {
		super();
		
		iRDebugThread  = aStreamRDebug;
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		LOGGER.info("Stopping and cleaning after TestDriver.");
		try {
			Runtime.getRuntime().removeShutdownHook(this);
		} catch (Exception lException) {
			LOGGER.fine("Didn't succefully deregister the shutdownhook.");
		}
		
		//uninstall tef before stop communication channel
        try {
        	if(iTefDeps != null) {
        		TDConfig CONFIG = TDConfig.getInstance();
        		boolean lPlatSec = true;
        		try {
        			//lPlatSec = CONFIG.isPreference(TDConfig.PLATSEC) && !CONFIG.isPreference(TDConfig.SYS_BIN);
        			lPlatSec = ! CONFIG.isPreference(TDConfig.SYS_BIN);
        		}
        		catch (ParseException e) {
        			LOGGER.log(Level.WARNING, "Could not get the configuration for PlatSec. Defaulting to ON");
        		}
        		if (lPlatSec)
        		{
        			iTefDeps.uninstall();
        			LOGGER.fine("TEF dependencies package was successfully removed!");
        		}
        	}
		} catch (TimeLimitExceededException e) {
			LOGGER.log(Level.WARNING, " Warning: TEF dependencies package " +
									   	"was not successfully removed!");
		}
		
		try {
			DeviceCommsProxy.getInstance().stop(false);
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE," Error " , lException);
		}

		// Move RDebug to the results folder
		if (iRDebugThread != null) {
			LOGGER.fine("Killing RDebug.");
			iRDebugThread.setM_Life(false);
		}
		
		// Stop all remaing JStat Threads
		ThreadGroup lRootThread = Thread.currentThread().getThreadGroup().getParent();
		while (lRootThread.getParent() != null) {
			lRootThread = lRootThread.getParent();
		}
		
		// Get all threads
        Thread[] lThreads = new Thread[50];
        int lNumThreads = lRootThread.enumerate(lThreads, true);
        
        LOGGER.fine("The root thread is: " + lRootThread.getName() + "; and has " + lNumThreads + " children threads.");
        
        // Find any JStat threads remaining
        for (int lIter = 0;  lIter < lNumThreads; lIter++) {
        	LOGGER.fine("Looking at Thread: " + lThreads[lIter].getName());
            if (lThreads[lIter].getName().indexOf("JStat") >= 0 ) {
            		//|| lThreads[lIter].getName().indexOf("Timer") >= 0) {
    			LOGGER.log(Level.SEVERE, "Could not stop all JStat Threads therefore killing TestDriver. Please check your Hardware or Emulator for failures.");
            }
        }
        
        
        
        
        LOGGER.exiting(HardwarePostProcessor.class.getName(), "run");
	}

	public void setTEFDEF(ExecuteTransferSet fdependenciesTransferSet) {
		iTefDeps = fdependenciesTransferSet;
	}
	
}
