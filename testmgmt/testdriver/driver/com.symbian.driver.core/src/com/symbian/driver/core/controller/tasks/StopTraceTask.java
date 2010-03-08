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

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.StopTrace;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.SoftwareTraceProxy;
import com.symbian.driver.core.processors.PreProcessor;

public class StopTraceTask implements IVisitor {

	private static final String TRACE_FILE_PREFIX = "_swtrace.utf";
	protected static final Logger LOGGER = Logger.getLogger(StartTraceTask.class.getName());

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();
	
	
	public StopTraceTask(StopTrace stopTrace) {
		super();
	}


	public Map<? extends Exception, ESeverity> execute(Task task, PreProcessor symbianDevice) {
		LOGGER.log(Level.INFO, "Stop utrace");
		try {
			//stop trace
			boolean ret = SoftwareTraceProxy.getInstance().stopTrace();
			if (ret) {
			    //transfer the log file
			    retrieveTraceFile(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			iExceptions.put(e, ESeverity.ERROR);
		}
		return iExceptions;
	}
	
	private void retrieveTraceFile(Task task) throws Exception {
		
		File outputPath = new File((TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT)),
				ModelUtils.getBaseDirectory(TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER)));
		
		File pcResult = new File(outputPath, task.getName()+ TRACE_FILE_PREFIX);
		LOGGER.info("retrieve trace data to :" + pcResult);
		try {
		SoftwareTraceProxy.getInstance().getTraceData(pcResult.getAbsolutePath());
		} catch (Exception e) {
		}
	}

}
