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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.Task;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.processors.PreProcessor;

/**
 * @author Development Tools
 *
 */
public class BuildTransferTask implements IVisitor {

	/** The logger for the Visitor class. */
	protected final static Logger LOGGER = Logger.getLogger(BuildTransferTask.class.getName());
	
	/** Local copy of EMF Object Transfer. */
	private final Transfer iTransfer;

	public BuildTransferTask(Transfer aTransfer) {
		iTransfer = aTransfer;
	}

	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		Map<Exception, ESeverity> lExceptions = new HashMap<Exception, ESeverity>();
		
		if (iTransfer.eContainer() instanceof TransferToSymbian) {

			try {
				File[] lFileSet = ModelUtils.checkPCPath(iTransfer.getPCPath(), true);

				boolean lTransfer = true;

				if (lFileSet.length == 1) {
					transferFile(iTransfer, lFileSet[0], iTransfer.getSymbianPath(), aTask);
				} else {
					for (int lIterator = 0; lIterator < lFileSet.length; lIterator++) {

						String lSymbianLiteral = iTransfer.getSymbianPath();
						if (lSymbianLiteral.indexOf("*") >= 0) {
							lSymbianLiteral = lSymbianLiteral.substring(0, lSymbianLiteral.lastIndexOf("\\\\") + 1);
						} else if (!lSymbianLiteral.endsWith("\\")) {
							lSymbianLiteral = lSymbianLiteral.concat("\\");
						}

						lSymbianLiteral = lSymbianLiteral.concat(lFileSet[lIterator].getName());

						lTransfer &= transferFile(iTransfer, lFileSet[lIterator], lSymbianLiteral, aTask);
					}
				}

				if (!lTransfer) {
					lExceptions.put(new IOException("ExecuteTransfer failed of: " + iTransfer.getSymbianPath()), ESeverity.WARNING);
				}

			} catch (IOException lIOException) {
				LOGGER.log(Level.WARNING, lIOException.getMessage(), lIOException);
				lExceptions.put(lIOException, ESeverity.WARNING);
			}
		}
		
		return lExceptions;
	}
	
	/**
	 * Prepare a file for tranfering to the Symbian device.
	 * 
	 * @param aTransfer
	 * @param aPCPath
	 * @param aSymbianPath
	 * @throws IOException
	 */
	private boolean transferFile(final Transfer aTransfer, File aPCPath, String aSymbianPath, Task aTask) throws IOException {
		Task lTask = ModelUtils.isFileEclipsing(aSymbianPath, (Task) aTransfer.eContainer().eContainer());
		
		if (lTask != null) {
			LOGGER.log(Level.WARNING, "The file " + aPCPath.getCanonicalPath()
					+ " will cause eclipsing as it has already been transferred in parent node " + lTask.getName());
			return false;
		}
		
		if (!((ExecuteTransferSet)aTask.getTransferSet()).add(new ExecuteTransfer(aPCPath, aSymbianPath))) {
			return false;
		}

		if (aTransfer.isMove()) {
			aPCPath.deleteOnExit();
		}

		return true;
	}

}
