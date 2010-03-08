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

package com.symbian.driver.plugins.utrace;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.extension.ISoftwareTrace;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;

public class UTraceCtl implements ISoftwareTrace {

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(UTraceCtl.class.getName());
	
	private final static String CMD_LOGGER = "ulogger";
	private final static String ARGS_START = "-r";
	private final static String ARGS_STOP = "-q";
	private final static String ARGS_ENABLE_PRIMARY = "-ef";
	private final static String ARGS_ENABLE_SECONDARY = "-es";
	private final static String ARGS_DISABLE_PRIMARY = "-df";
	private final static String ARGS_DISABLE_SECONDARY = "-ds";
	
	private final static String ARGS_ENABLE_OUTPUT = "-eo uloggerfileplugin";
	private final static String TRACE_FILE_DEVICE = "c:\\logs\\swtrace.utf";
	private final static String ARGS_OUTPUT_PATH = "-ec uloggerfileplugin output_path " + TRACE_FILE_DEVICE;
	
	private final static String ULOGGER_CONFIG_PATH = "C:/private/10273881/uloggerconfig.ini";
	
	/**
	 * disable command timeout
	 */
	private final static int TIMEOUT = -1;
	
	/**
	 * enable or disable filters
	 */
	public boolean configFilters(String primaryFiltersToEnable,
    		String secondaryFiltersToEnable,
    		String primaryFiltersToDisable,
    		String secondaryFiltersToDisable) {

        
        DeviceCommsProxy lDeviceProxy;
        List<String> args;
        try {
        	lDeviceProxy = DeviceCommsProxy.getInstance();
        	//enable primary filters
        	if (primaryFiltersToEnable != null && !primaryFiltersToEnable.equals("")) {
        		String arg = ARGS_ENABLE_PRIMARY + " " + primaryFiltersToEnable;
        		args = Arrays.asList(arg);
    			if (!lDeviceProxy.createSymbianProcess().runCommand(
    					CMD_LOGGER, args, TIMEOUT, true)) {
    				return false;
    			}
    			
            }
        	//enable secondary filters
        	if (secondaryFiltersToEnable != null && !secondaryFiltersToEnable.equals("")) {
        		String arg = ARGS_ENABLE_SECONDARY + " " + secondaryFiltersToEnable;
        		args = Arrays.asList(arg);
    			if (!lDeviceProxy.createSymbianProcess().runCommand(
    					CMD_LOGGER, args, TIMEOUT, true)) {
    				return false;
    			}
    			
            }
			
        	//disable primary filters
        	if (primaryFiltersToDisable != null && !primaryFiltersToDisable.equals("")) {
        		String arg = ARGS_DISABLE_PRIMARY + " " + primaryFiltersToDisable;
        		args = Arrays.asList(arg);
    			if (!lDeviceProxy.createSymbianProcess().runCommand(
    					CMD_LOGGER, args, TIMEOUT, true)) {
    				return false;
    			}
            }
        	
        	//disable secondary filters
        	if (secondaryFiltersToDisable != null && !secondaryFiltersToDisable.equals("")) {
        		String arg = ARGS_DISABLE_SECONDARY + " " + secondaryFiltersToDisable;
        		args = Arrays.asList(arg);
    			if (!lDeviceProxy.createSymbianProcess().runCommand(
    					CMD_LOGGER, args, TIMEOUT, true)) {
    				return false;
    			}
            }
        	
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "can't start ulogger server", e);
		}
		return false;
	}

	/**
	 * retrieve trace data from c:\swtrace.elf on device to PC
	 */
	public boolean getTraceData(String filePath) {
		LOGGER.log(Level.INFO, "retrive trace data to PC :" + filePath);
		//the statement below will delete the trace data on device after retrieve
		DeviceUtils.retrieveFile(TRACE_FILE_DEVICE,
				filePath,
				true,
				null);
		if (new File(filePath).exists()) {			
			return true;
		}
		return false;
	}

	/**
	 * start the ulogger server and config the fileoutputplugin
	 */
	public boolean startTrace() {
		DeviceCommsProxy lDeviceProxy;
		try {
			
			lDeviceProxy = DeviceCommsProxy.getInstance();
			//active file output plugin
			List<String> args = Arrays.asList(ARGS_ENABLE_OUTPUT);
			if (!lDeviceProxy.createSymbianProcess().runCommand(
					CMD_LOGGER, args, TIMEOUT, true)) {
				return false;
			}
			LOGGER.log(Level.INFO, "active the uloggerfileplugin");
			
			//set the output path
			args = Arrays.asList(ARGS_OUTPUT_PATH);
			if (!lDeviceProxy.createSymbianProcess().runCommand(
					CMD_LOGGER, args, TIMEOUT, true)) {
				return false;
			}
			LOGGER.log(Level.INFO, "set the output path to c:\\logs\\swtrace.utf");
			
			//start ulogger server
			args = Arrays.asList(ARGS_START);
			if (!lDeviceProxy.createSymbianProcess().runCommand(
					CMD_LOGGER, args, TIMEOUT, false)) {
			    return false;	
			}
			LOGGER.log(Level.INFO, "start the ulogger server");
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "can't start ulogger server", e);
		}
		return false;
	}

	public boolean stopTrace() {
		DeviceCommsProxy lDeviceProxy;
		try {
			//stop the ulogger server
			lDeviceProxy = DeviceCommsProxy.getInstance();
			List<String> args = Arrays.asList(ARGS_STOP);
			if (lDeviceProxy.createSymbianProcess().runCommand(
					CMD_LOGGER, args, TIMEOUT, true)) {
				LOGGER.log(Level.INFO, "stop the ulogger server");
				return true;	
			}
			
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "can't stop ulogger server", e);
		}
		return false;
	}

	public boolean setConfigFile(String filePath) {
		LOGGER.log(Level.INFO, "try to transfer the ulogger config file :" + filePath);
		LOGGER.log(Level.INFO, "   to device:" + ULOGGER_CONFIG_PATH);
		if (!new File(filePath).exists()) {
			LOGGER.log(Level.WARNING, "the ulogger config file doesn't exists:" + filePath);
            return false;
		}
		
		return DeviceUtils.sendFile(filePath, ULOGGER_CONFIG_PATH);
	}

}
