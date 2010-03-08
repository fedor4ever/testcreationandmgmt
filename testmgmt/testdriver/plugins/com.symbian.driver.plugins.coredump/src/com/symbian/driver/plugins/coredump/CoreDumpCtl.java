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

package com.symbian.driver.plugins.coredump;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.extension.ICoreDump;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer;
import com.symbian.driver.core.pluginProxies.DeviceCommsProxy;

public class CoreDumpCtl implements ICoreDump {

	private static final Logger LOGGER = Logger.getLogger(CoreDumpCtl.class.getName());
	
	private static final String INI_PREFIX = "coredump";
	private static final String INI_SUFFIX = ".ini";
	
	private static final String COREDUMP_OUTPUT_PATH = "c:";
	private static final String COREDUMP_CONFIG_PATH = COREDUMP_OUTPUT_PATH + "\\coredumpserver.ini";
	private static final String ARGS_CONFIG = "-f " + COREDUMP_CONFIG_PATH;
	private static final String CMD_COREDUMP = "coredumpscript";
	
	private static final String COREDUMP_DATA = "coredump.etf";
	
	private static final String LINE_END = "\r\n";
	/**
	 * disable command timeout
	 */
	private final static int TIMEOUT = -1;
	
	ArrayList<String> apps = new ArrayList<String>();
	
	static String currentAppName = "";
	 
	/**
	 * indicate the core dump server has started successfully 
	 */
	boolean coreDumpStarted = false;
	
	/**
	 * hold the reference to remote core dump server process
	 */
	IDeviceComms.ISymbianProcess coreDumpProc = null;
	
	public CoreDumpCtl() {
		//add the crashapp by default for testing
        //apps.add("z:\\sys\\bin\\crashapp.exe");
	}
	
	/**
	 * retrieve the crash data from device to PC
	 * @param path, the PC path to save crash data
	 */
	public boolean getCrashData(String path) {
		if (!coreDumpStarted) {
			return false;
		}
		boolean gotCrashData = false;
		try {
			DeviceCommsProxy lDeviceProxy = DeviceCommsProxy.getInstance();
			ISymbianTransfer transfer = lDeviceProxy.createSymbianTransfer();
			//list all files under coredump output dir
			List<File> files = transfer.dir(new File(COREDUMP_OUTPUT_PATH));
			if (files != null) {
				for (File file : files) {
					if (file.getName().indexOf(COREDUMP_DATA) >= 0) {
						LOGGER.log(Level.INFO, "find application crash data file:" + file.getAbsolutePath());
						//retrieve the crash data to PC
						DeviceUtils.retrieveFile(file.getAbsolutePath(),
                                path + File.separator + file.getName(),
                                true,
                                null);
						gotCrashData = true;
						continue;
					}
				}
			} 
			LOGGER.log(Level.INFO, "can't find application crash data under " + COREDUMP_OUTPUT_PATH);
		} catch (Exception e) {
            LOGGER.log(Level.WARNING, "error when try to locate coredump data", e.getMessage());
		}
		return gotCrashData;
	}

	/**
	 * check if application crash has happened
	 */
	public boolean isAppCrash() {
		if (!coreDumpStarted) {
			return false;
		}
		try {
			DeviceCommsProxy lDeviceProxy = DeviceCommsProxy.getInstance();
			ISymbianTransfer transfer = lDeviceProxy.createSymbianTransfer();
			List<File> files = transfer.dir(new File(COREDUMP_OUTPUT_PATH));
			if (files != null) {
				for (File file : files) {
					if (file.getName().indexOf(COREDUMP_DATA) >= 0) {
						LOGGER.log(Level.INFO, "find application crash data file:" + file.getAbsolutePath());
						return true;
					}
				}
			} 
		} catch (Exception e) {
            LOGGER.log(Level.WARNING, "error when try to locate coredump data", e.getMessage());
		}
		return false;
	}

	/**
	 * check if system crash has happened
	 */
	public boolean isSystemCrash() {
		LOGGER.log(Level.WARNING, "system crash is not supported by coredumpserver");
		return false;
	}

	/**
	 * config coredump server to observer all running application
	 */
	public boolean monitorAllApps() {
		LOGGER.log(Level.WARNING, "monitor all is not suppored yet by coredumpserver");
		return false;
	}

	/**
	 * config coredump server to observer an application
	 * this should be called before call startServer()
	 * for example: "z:\sys\bin\crashapp.exe"
	 */
	public boolean monitorApp(String exeName) {
		apps.add(exeName);
		return true;
	}

	/**
	 * start the core dump server to monitor test application
	 * <li>generate the coredumpserver config file</li>
	 * <li>transfer config file to device</li>
	 * <li>call 'coredumpscript -f coredumpserver.ini' to start coredumpserver</li>
	 */
	public boolean startServer() {
		LOGGER.log(Level.INFO, "try to start coredump server");
		if (coreDumpStarted) {
			//stop the coredump server first if alreay started 
			stopServer();
		}
		
		//create output path
//		createOutputDir();
		//create coredumpserver.ini
		File iniFile = createCoreDumpConfig();
		//transfer to device
		if (iniFile.exists()) {
		    boolean sent = sendConfigFile(iniFile.getAbsolutePath());
		    if (!sent) {
		    	LOGGER.log(Level.WARNING, "error when transfer coredumpserver.ini");
		    	return false;
		    }
		} else {
			return false;
		}
		
		//start codedumpserver
		coreDumpStarted =  startCoreDumpServer(iniFile.getAbsolutePath());
		if (coreDumpStarted) {
			LOGGER.log(Level.INFO, "coredump server started");
		}
		return coreDumpStarted;
	}

	/**
	 * stop the core dump server
	 */
	public boolean stopServer() {
		if (!coreDumpStarted) {
			return false;
		}
		LOGGER.log(Level.INFO, "try to stop coredump server");
		coreDumpProc.stop();
        coreDumpProc = null;
        coreDumpStarted = false;
        apps.clear();
        //delete the coredumpserver.ini from device
        DeviceCommsProxy lDeviceProxy = null;
		try {
			lDeviceProxy = DeviceCommsProxy.getInstance();
			if (lDeviceProxy.createSymbianTransfer().delete(
					new File(COREDUMP_CONFIG_PATH), false)) {
				LOGGER.log(Level.FINE, "delete the coredump config file on device");
				return true;
			}
		} catch (Exception lException) {
			LOGGER.log(Level.SEVERE, lException.getMessage());
		}
		
		return true;
	}
	
	/**
	 * create temp coredumpserver.ini to monitor test application
	 * @return the created temp file
	 */
	private File createCoreDumpConfig() {
		//create the file template from resource
		try {
			File iniFile = File.createTempFile(INI_PREFIX, INI_SUFFIX);
			iniFile.deleteOnExit();
			InputStream is = getClass().getResourceAsStream("/resource/coredumpserver.ini");
			InputStreamReader in = new InputStreamReader(is, "UTF-16LE");
            OutputStreamWriter out = new OutputStreamWriter(
            		new FileOutputStream(iniFile), "UTF-16LE");
            char buffer[] = new char[4096];
            int read = 0;
            while (read != -1) {
            	read = in.read(buffer, 0, buffer.length);
            	if (read != -1) {
					out.write(buffer, 0, read);
				}
            }
            
            is.close();
			out.write("[ObservedConfiguration]");
			out.write(LINE_END);
			int index = 0;
			for (String app : apps) {
				out.write("KeyType" + index + "=" + app);
				out.write(LINE_END);
				out.write("KeyValue"+ index + "=" + app);
				out.write(LINE_END);
				index++;
			}
			out.close();
			try {
			    Thread.sleep(5*1000);
			} catch (Exception se) {
			}
			return iniFile;

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "error when create coredumpserver.ini", e);
		}
		return null;
	}
	
	/**
	 * send the coredumpserver.ini from PC to device
	 * @param pcPath
	 * @return
	 */
	private boolean sendConfigFile(String pcPath) {
		LOGGER.log(Level.INFO, "try to transfer the coredumpserver.ini file :" + pcPath);
		LOGGER.log(Level.INFO, "   to device:" + COREDUMP_CONFIG_PATH);
		if (!new File(pcPath).exists()) {
			LOGGER.log(Level.WARNING, "the coredumpserver config file doesn't exists:" + pcPath);
            return false;
		}
		return DeviceUtils.sendFile(pcPath, COREDUMP_CONFIG_PATH);
	}
	
	
	
	/**
	 * start the coredump server with created config file
	 * @param configPath, the generated coredumpserver.ini
	 * @return 
	 */
	private boolean startCoreDumpServer(String configPath) {
		try {
		    DeviceCommsProxy lDeviceProxy = DeviceCommsProxy.getInstance();
		    List<String> args = Arrays.asList(ARGS_CONFIG);
		    
		    //start server
		    coreDumpProc = lDeviceProxy.createSymbianProcess();
		    		    boolean result = coreDumpProc.runCommand(
				CMD_COREDUMP, args, TIMEOUT, false);
		    if (!result) {
		    	LOGGER.log(Level.WARNING, "can't start coredump server");
                coreDumpProc.stop();
                coreDumpProc = null;
		    }
		    return result;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "error when try to start coredumpserver:", e);
			return false;
		}
	}

//	private void createOutputDir() {
//		try {
//		    DeviceCommsProxy lDeviceProxy = DeviceCommsProxy.getInstance();
//		    boolean ret = lDeviceProxy.createSymbianTransfer().mkdir(
//		    		new File(COREDUMP_OUTPUT_PATH));
//		    if (ret) {
//		    	LOGGER.log(Level.INFO, "created coredump data dir:" + COREDUMP_OUTPUT_PATH);
//		    }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
