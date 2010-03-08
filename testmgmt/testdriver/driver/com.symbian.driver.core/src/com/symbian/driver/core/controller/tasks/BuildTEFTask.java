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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import com.symbian.driver.Task;
import com.symbian.driver.TestCase;
import com.symbian.driver.TestCasesList;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.processors.PreProcessor;

public class BuildTEFTask implements IVisitor {
	
	/** The logger for the Visitor class. */
	protected final static Logger LOGGER = Logger.getLogger(BuildTEFTask.class.getName());

	/** Local copy of EMF Object for Test Execute Script.*/
	private final TestExecuteScript iTestExecuteScript;

	/**
	 * @param aTestExecuteScript
	 */
	public BuildTEFTask(TestExecuteScript aTestExecuteScript) {
		iTestExecuteScript = aTestExecuteScript;
	}

	/* (non-Javadoc)
	 * @see com.symbian.driver.core.extension.IVisitor#execute(com.symbian.driver.Task, com.symbian.driver.core.processors.PreProcessor)
	 */
	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();
		
		try {

			File lScript = ModelUtils.checkPCPath(iTestExecuteScript.getPCPath(), false)[0];

			if (lScript.isFile()) {
				Task eTask = ModelUtils.isFileEclipsing(iTestExecuteScript.getSymbianPath(), aTask);
				if ( eTask == null) {
				if (!((ExecuteTransferSet)aTask.getTransferSet()).add(new ExecuteTransfer(lScript, iTestExecuteScript.getSymbianPath()))) {
					String lErrorMsg = "Could not add script to SIS package / repository: "
							+ iTestExecuteScript.getPCPath();
					LOGGER.severe(lErrorMsg);
					lExceptions.put(new IOException(lErrorMsg), ESeverity.ERROR);
				} else {
					// script added, now check if we have a TestCases file
					TestCasesList lTestCasesList = iTestExecuteScript.getTestCasesList();
					if (lTestCasesList != null) {
						EList lTestCases = lTestCasesList.getTestCase();
						Iterator lIter = lTestCases.iterator();
						String lDestination = new File(iTestExecuteScript.getSymbianPath()).getParent().toString();
						while (lIter.hasNext()) {
							TestCase lTestCaseFile = (TestCase) lIter.next();
							String lTarget = lTestCaseFile.getTarget();
							File lTargetSource = null;
							if (ModelUtils.isTestCasesFile(lTarget)
									&& (lTargetSource = ModelUtils.checkTCFile(lTarget, lScript)) != null) {
								// we have a cfg file to add to the sis
								// package
								if (!((ExecuteTransferSet)aTask.getTransferSet()).add(new ExecuteTransfer(lTargetSource, lDestination + File.separator + lTargetSource.getName()))) {
									String lErrorMsg = "Could not add script to SIS package / repository: "
											+ lTargetSource.toString();
									LOGGER.severe(lErrorMsg);
									lExceptions.put(new IOException(lErrorMsg), ESeverity.ERROR);
								}
							}
						}
					}
				}
				} else {
					LOGGER.log(Level.WARNING, "The file " + lScript.getCanonicalPath()
							+ " will cause eclipsing as it has already been transferred in parent node " + eTask.getName());
				}
			}


		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, lIOException.getMessage(), lIOException);
			lExceptions.put(lIOException, ESeverity.ERROR);
		}

		return lExceptions;
	}

}
