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
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.CmdPC;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.processors.PreProcessor;

/**
 * @author EngineeringTools
 *
 */
public class CmdPCTask implements IVisitor {
	
	/** The logger for the Visitor class. */
	private final static Logger LOGGER = Logger.getLogger(CmdPCTask.class.getName());
	
	/** EMF Command PC Object. */
	private final CmdPC iCmdPc;
	
	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();
	
	public CmdPCTask(CmdPC aCmdPC) {
		iCmdPc = aCmdPC;
	}

	/**
	 * @param aVisitor 
	 * @param aAsyncTaskSet
	 * @param aCmdPC
	 * @return The EMF CommandPC Object
	 */
	public Map<? extends Exception, ESeverity> execute(final Task aTask, PreProcessor aSymbianDevice) {

		try {
			File lWorkingDir;
			if (iCmdPc.getURI() != null) {
				String lDir;
				iCmdPc.setURI(ModelUtils.subsituteVariables(iCmdPc.getURI()));
				
				
				if (iCmdPc.getURI().indexOf('\\') >= 0) {
					// Regular Support
					lDir = iCmdPc.getURI();
				} else {
					// URI Support
					lDir = URI.create(iCmdPc.getURI()).getPath();
				}
				
				lWorkingDir = ModelUtils.checkPCPath(lDir, false)[0];
			} else {
				lWorkingDir = new File(".");
			}
			
			ExecuteOnHost lExecuteOnHost = new ExecuteOnHost(lWorkingDir, iCmdPc.getValue());

			if (iCmdPc.isSync()) {
				lExecuteOnHost.doTask(true, aTask.getTimeout() * 1000, true);
			} else {
				lExecuteOnHost.doTask(false, aTask.getTimeout() * 1000, true);
				aTask.getExecuteSet().push(lExecuteOnHost);
			}
			
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, lIOException.getMessage(), lIOException);
			iExceptions.put(lIOException, ESeverity.ERROR);
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
			iExceptions.put(lException, ESeverity.ERROR);
		}

		return iExceptions;
	}
}
