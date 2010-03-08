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

package com.symbian.driver.core.controller.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.StartTrace;
import com.symbian.driver.Task;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.SoftwareTraceProxy;
import com.symbian.driver.core.processors.PreProcessor;

public class StartTraceTask implements IVisitor {

	private final StartTrace iStartTrace ; 
	protected static final Logger LOGGER = Logger.getLogger(StartTraceTask.class.getName());

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();
	
	
	public StartTraceTask(StartTrace startTrace) {
		super();
		iStartTrace = startTrace;
	}


	public Map<? extends Exception, ESeverity> execute(Task task, PreProcessor symbianDevice) {
		LOGGER.log(Level.INFO, "start utrace");
		LOGGER.log(Level.INFO, "   enable primary filters:" + iStartTrace.getEnablePrimaryFilters());
		LOGGER.log(Level.INFO, "   enable secondary filters:" + iStartTrace.getEnableSecondaryFilters());
		LOGGER.log(Level.INFO, "   disable primary filters:" + iStartTrace.getDisablePrimaryFilters());
		LOGGER.log(Level.INFO, "   disable secondary filters:" + iStartTrace.getDisableSecondaryFilters());
		try {
			String configPath = iStartTrace.getConfigFilePath();
			if (configPath != null) {
				//first transfer user specified ulogger configuration file to device
				SoftwareTraceProxy.getInstance().setConfigFile(configPath);
				Thread.sleep(10*1000);
			}
			//setup filter
			SoftwareTraceProxy.getInstance().configFilters(
					iStartTrace.getEnablePrimaryFilters(),
					iStartTrace.getEnableSecondaryFilters(), 
					iStartTrace.getDisablePrimaryFilters(), 
					iStartTrace.getDisableSecondaryFilters());
			//start trace
			SoftwareTraceProxy.getInstance().startTrace();
		} catch (Exception e) {
			e.printStackTrace();
			iExceptions.put(e, ESeverity.ERROR);
		}
		return iExceptions;
	}

}
