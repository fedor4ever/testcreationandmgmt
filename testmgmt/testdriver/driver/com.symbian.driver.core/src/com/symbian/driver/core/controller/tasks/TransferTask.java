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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.symbian.driver.CmdSymbian;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.Task;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.driver.core.processors.PreProcessor;

public class TransferTask implements IVisitor {

	/** The logger for the Visitor class. */
	private static final Logger LOGGER = Logger.getLogger(CmdSymbian.class.getName());

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();

	/** EMF Object for Transfer. */
	private final Transfer iTransfer;

	/**
	 * @param aTransfer
	 */
	public TransferTask(Transfer aTransfer) {
		iTransfer = aTransfer;
	}

	public Map<? extends Exception, ESeverity> execute() {
		return execute(null, null);
	}

	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		if (iTransfer.eContainer() instanceof RetrieveFromSymbian) {

			// Support variables
			iTransfer.setPCPath(ModelUtils.subsituteVariables(iTransfer.getPCPath(), (Task) iTransfer.eContainer()
					.eContainer()));

			IDeviceComms.ISymbianTransfer lSymbianTransfer;
			try {
				lSymbianTransfer = DeviceCommsProxy.getInstance().createSymbianTransfer();

				// get rid of $: from symbian path
				String lSymbianPath = iTransfer.getSymbianPath();
				lSymbianPath = lSymbianPath.replace("$:", ILiterals.C);

				// Create retrieve directory
				File lPCDir = new File(ModelUtils.subsituteVariables(iTransfer.getPCPath()));
				if (!lPCDir.getParentFile().isDirectory() && !lPCDir.getParentFile().mkdirs()) {
					throw new IOException("Could not make PC retrieve directory.");
				}

				// WildCard Support
				if (lSymbianPath.indexOf('*') >= 0) {
					// Get List of files in a Directory
					File lParent = new File(lSymbianPath).getParentFile();
					List<File> lDirList = lSymbianTransfer.dir(lParent);
					// Create regular expression
					String lRegExp = new File(lSymbianPath).getName();
					lRegExp = lRegExp.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".\\*");
					Pattern lFileNamePattern = Pattern.compile(lRegExp);

					for (Iterator iter = lDirList.iterator(); iter.hasNext();) {
						String lFile = ((File) iter.next()).toString();
						if (lFileNamePattern.matcher(new File(lFile).getName()).matches()) {
							// Retrieve the files
							DeviceUtils.retrieveFile(lFile, lPCDir + File.separator + new File(lFile).getName(),
									iTransfer.isMove(), aTask);
						}
					}

					
				} else {
					// Standard Retrieve
					DeviceUtils.retrieveFile(lSymbianPath,
							ModelUtils.subsituteVariables(iTransfer.getPCPath()),
							iTransfer.isMove(), aTask);
				
				}
			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Failed to retrieve file(s)", lException);
				iExceptions.put(lException, ESeverity.ERROR);
			}
		}
		return iExceptions;
	}
}
