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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import com.symbian.driver.CmdSymbian;
import com.symbian.driver.StatCommand;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.extension.IDeviceComms.ISymbianProcess;
import com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;
import com.symbian.driver.core.processors.PreProcessor;

public class CmdSymbianTask implements IVisitor {

	/** The logger for the Visitor class. */
	private final static Logger LOGGER = Logger.getLogger(CmdSymbian.class.getName());

	/** EMF Object for Symbian Command. */
	private final CmdSymbian iCmdSymbian;

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();

	public CmdSymbianTask(CmdSymbian aCmdSymbian) {
		iCmdSymbian = aCmdSymbian;
	}

	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		/**
		 * format : 
		 * <cmdSymbian statCommand="aCommand" output="aOutput" sync="boolean"> 
		 * 		<argument></argument> 
		 * </cmdSymbian> 
		 * The list of argument could be empty.
		 * 
		 * supported stat commands are: 
		 * <xsd:enumeration value="createFolder"/>
		 * <xsd:enumeration value="removeFolder"/> 
		 * <xsd:enumeration value="listDrives"/> 
		 * <xsd:enumeration value="listFiles"/>
		 * <xsd:enumeration value="getScreenCapture"/> ??
		 * <xsd:enumeration value="delete"/> 
		 * <xsd:enumeration value="run"/> 
		 * <xsd:enumeration value="startLogging"/> ??
		 * <xsd:enumeration value="stopLogging"/> ??
		 */

		StatCommand lStatCommandLiteral = iCmdSymbian.getStatCommand();
		EList lArguments = iCmdSymbian.getArgument();
		DeviceCommsProxy lDeviceProxy = null;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, "Failed to get comms proxy.", lException.getMessage());
			iExceptions.put(lException, ESeverity.ERROR);
			return iExceptions;
		}
		ISymbianTransfer lSymbianTransfer = lDeviceProxy.createSymbianTransfer();
		ISymbianProcess lSymbianProcess = lDeviceProxy.createSymbianProcess();

		// Mapping from EMF stat commands to JStat stat commands
		if (lStatCommandLiteral == StatCommand.CREATE_FOLDER) {
			if (lArguments.isEmpty()) {
				iExceptions.put(new Exception("Stat Command : createFolder must provide a folder path."), ESeverity.ERROR);
			}
			if (!lSymbianTransfer.mkdir(new File((String) lArguments.get(0)))) {
				iExceptions.put(new Exception("Stat Command : Could not create folder " + (String) lArguments.get(0)), ESeverity.ERROR);				
			}
		} else if (lStatCommandLiteral == StatCommand.DELETE) {
			if (lArguments.isEmpty()) {
				iExceptions.put(new Exception("Stat Command : deleteFolder must provide a folder path."), ESeverity.ERROR);
			}
			if (!lSymbianTransfer.delete(new File((String) lArguments.get(0)), false)) {
				iExceptions.put(new Exception("Stat Command : Could not delete file " + (String) lArguments.get(0)), ESeverity.ERROR);
			}
		} else if (lStatCommandLiteral == StatCommand.GET_SCREEN_CAPTURE) {
			lSymbianProcess.captureScreen();
		} else if (lStatCommandLiteral == StatCommand.LIST_DRIVES) {
			List<String> lDrives = lSymbianTransfer.listDrives();
			LOGGER.info("List of drives: " + lDrives);
		} else if (lStatCommandLiteral == StatCommand.LIST_FILES) {
			if (lArguments.isEmpty()) {
				iExceptions.put(new Exception("Stat Command : listFiles must provide a folder path."),ESeverity.ERROR);
			}
			List<File> lFiles = lSymbianTransfer.dir((File) lArguments.get(0));
			System.out.println("List of files at " + lArguments.get(0) + " : " + lFiles);
		} else if (lStatCommandLiteral == StatCommand.REMOVE_FOLDER) {
			if (lArguments.isEmpty()) {
				iExceptions.put(new Exception("Stat Command : deleteFolder must provide a folder path."), ESeverity.ERROR);
			}
			if (!lSymbianTransfer.delete((File) lArguments.get(0), true)) {
				iExceptions.put(new Exception("Stat Command : Could not delete folder " + (String) lArguments.get(0)), ESeverity.ERROR);
			}
		} else if (lStatCommandLiteral == StatCommand.RUN) {
			int lTimeout;
			if (!aTask.isSetTimeout()) {
				lTimeout = 0;
			} else {
				lTimeout = aTask.getTimeout() <= 0 ? -1 : aTask.getTimeout() * 1000;
			}
			
			String lCommand = null;
			List<String> lListArgs = new ArrayList<String>(0);
			ListIterator lLIter = lArguments.listIterator();
			if (lLIter.hasNext()) {
				lCommand = (String) lLIter.next();
			}
			while (lLIter.hasNext()) {
				lListArgs.add((String) lLIter.next());
				
			}
			
			
			
			if (!lSymbianProcess.runCommand((String) lCommand, lListArgs, lTimeout,
					iCmdSymbian.isSync())) {
				iExceptions.put(new Exception("Stat Command : Failed to run " + (String) lArguments.get(0) + " " + lArguments.subList(1, 1)), ESeverity.ERROR);
			}
		} else {
			iExceptions.put(new IOException("Stat Command is incorrect: " + lStatCommandLiteral), ESeverity.ERROR);
		}

		// Stream Buffer to Ouput file
		if (iCmdSymbian.getOutput() != null) {
			File lOutputFile = new File(ModelUtils.subsituteVariables(iCmdSymbian.getOutput()));

			if (!lOutputFile.getParentFile().isDirectory() && !lOutputFile.getParentFile().mkdirs()) {
				iExceptions.put(new IOException("Could not create the STAT command output parent directory: "
						+ lOutputFile.toString()), ESeverity.ERROR);
			} else {

				BufferedWriter lWriter;
				try {
					lWriter = new BufferedWriter(new FileWriter(lOutputFile));
					lWriter.write(lSymbianProcess.getOutputStream().toString());
					lWriter.flush();
					lWriter.close();
				} catch (IOException lIOException) {
					iExceptions.put(lIOException, ESeverity.ERROR);
				}
			}
		}
		return iExceptions;
	}
}
