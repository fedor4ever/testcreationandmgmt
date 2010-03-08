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

package com.symbian.driver.core.controller.utils;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.JarUtils;

/**
 * @author EngineeringTools
 *
 */
public class SerialListener implements Runnable, SerialPortEventListener
{
	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(ControllerUtils.class.getName());
	/** Input stream from the serial port */
    InputStream inputStream;
    /** Serial port instance*/
    SerialPort serialPort;
    /** log file to dump the serial data into*/
    String m_LogFilePath ;
    /** control boolean to regulate the life of the listener thread*/
    boolean m_Life ;
    /** Generic settings/configuration. */
	protected static TDConfig CONFIG = TDConfig.getInstance();
	private static String iRxtxDllPath = null;
	
	static {
		LOGGER.entering(SerialListener.class.getName(), "Loading RXTX DLLs");
		File lRxtxDll =null;
		//Load RXTX.dll
		try {
			lRxtxDll = JarUtils.extractResource(SerialListener.class, "/resource/rxtxSerial.dll");
		} catch (IOException lIOException) {
				lRxtxDll = new File("../resource/rxtxSerial.dll");
		} catch (Throwable lThrowable) {
			LOGGER.log(Level.SEVERE, "Could not load RXTD dlls.", lThrowable);
			iRxtxDllPath = null;
		} finally {
			if (lRxtxDll == null || !lRxtxDll.exists()) {
				iRxtxDllPath = null;
		} else {
			iRxtxDllPath = lRxtxDll.getParentFile().getAbsolutePath();
		}
		LOGGER.exiting(SerialListener.class.getName(), "DLLs succefully loaded");
	}
	
//	static {
//		iRxtxDllPath = SerialListener.class.getResource("/").getPath();
//		
//		try {
//			if (! new File(iRxtxDllPath + File.separator + "resource" + File.separator + "rxtxSerial.dll").exists()) {
//				if (! new File(iRxtxDllPath + File.separator + ".." + File.separator + "resource" + File.separator + "rxtxSerial.dll").exists()) {
//					iRxtxDllPath = null;
//				} else {
//					iRxtxDllPath = iRxtxDllPath + File.separator + "..";
//				}
//			}
//			appendPath(iRxtxDllPath);
//		} catch (Throwable e) {
//			LOGGER.log(Level.SEVERE, "Could not set path to rxtx dlls. " + e.getMessage(), e);
//			iRxtxDllPath = null;
//		} 
	}

    
  /** Object to start listener thread
 * @param comPort
 * @param aTestExecuteScript
 */
public SerialListener(String comPort, File aFile) {
	LOGGER.fine("Create the RDEBUG listener.");
	if (iRxtxDllPath == null) {
		LOGGER.severe("RXTX dll not found.");
		return;
	}
	
	try {
		appendPath(iRxtxDllPath);
	} catch (Throwable e) {
		LOGGER.log(Level.SEVERE, "Could not set path to rxtx dlls. " + e.getMessage(), e);
		return;
	} 
	
	comPort = comPort.toUpperCase();
	File lParentDir = aFile.getParentFile();
	if (!lParentDir.exists() && !lParentDir.mkdir()) {
		LOGGER.log(Level.SEVERE, "Could not create folder " + lParentDir.getAbsolutePath());
		return;
	}
	m_LogFilePath = aFile.getAbsolutePath() ; 
	CommPortIdentifier portIdentifier  = null ;
	try {
		LOGGER.info("Calling RXTX.");
		portIdentifier = CommPortIdentifier.getPortIdentifier(comPort);
		LOGGER.info("RXTX Libraries loaded.");
	} catch (NoSuchPortException noPortEx) {
		LOGGER.log(Level.SEVERE, noPortEx.getMessage(), noPortEx);
		return;
	} catch (Throwable lul) {
		LOGGER.log(Level.SEVERE, lul.getMessage(), lul);
		return;
	}
      if ( portIdentifier.isCurrentlyOwned() )
      {
    	  LOGGER.log(Level.SEVERE, "Requested Port is owned by another application.");
    	  return;
      }
      else
      {
          try {
			CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
			  if ( commPort instanceof SerialPort )
			  {
			      SerialPort serialPort = (SerialPort) commPort;
			      //hard coded for now, but to be made configurable
			      //as soon as a use case appears for it...
			      serialPort.setSerialPortParams(115200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			      inputStream = serialPort.getInputStream();
			      serialPort.addEventListener(this);
			      serialPort.notifyOnDataAvailable(true);
			      m_Life = true ;
			      (new Thread(this)).start();
			  }
			  else
			  {
				  LOGGER.log(Level.SEVERE, "Given port is not a serial comport." );
			  }
		} catch (PortInUseException portInUseEx) {
			LOGGER.log(Level.SEVERE, portInUseEx.getMessage(), portInUseEx);
		} catch (UnsupportedCommOperationException badOpEx) {
			LOGGER.log(Level.SEVERE, badOpEx.getMessage(), badOpEx);
		} catch (IOException IOEx) {
			LOGGER.log(Level.SEVERE, IOEx.getMessage(), IOEx);
		} catch (TooManyListenersException listenerOverflowEx) {
			LOGGER.log(Level.SEVERE, listenerOverflowEx.getMessage(), listenerOverflowEx);
		}
      }     
  }


public static void appendPath(String pathname) throws SecurityException, 
	NoSuchFieldException, IllegalArgumentException, IllegalAccessException
	{
	Class clsLoader = ClassLoader.class;
	Field field = clsLoader.getDeclaredField("sys_paths");
	String libPath = new 
	String(System.getProperty("java.library.path"));


	if (!field.isAccessible())
	{
		field.setAccessible(true);
	}

	//
	// Reset the sys_paths field in the class loader to null so that
	// whenever "System.loadLibrary" is called it will be reconstructed
	// with the changed value.
	//
	field.set(clsLoader, null);

	if (!libPath.endsWith(System.getProperty("path.separator")))
	{
		libPath = libPath.concat(System.getProperty("path.separator"));
}

System.setProperty("java.library.path", libPath.concat(pathname));
}


  /* (non-Javadoc)
 * @see java.lang.Runnable#run()
 */
public void run() {
    	while(m_Life)
    	{}
  } 
  
  /* (non-Javadoc)
 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
 */
synchronized public void serialEvent(SerialPortEvent event) {
	switch (event.getEventType()) {
    case SerialPortEvent.BI:
    case SerialPortEvent.OE:
    case SerialPortEvent.FE:
    case SerialPortEvent.PE:
    case SerialPortEvent.CD:
    case SerialPortEvent.CTS:
    case SerialPortEvent.DSR:
    case SerialPortEvent.RI:
    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
        break;
    case SerialPortEvent.DATA_AVAILABLE:
    		try {
    			BufferedWriter out = new BufferedWriter(new FileWriter(m_LogFilePath, true));
				int c = inputStream.read() ; 
				while(c != -1 
						&& inputStream.available() != 0) //as it looked like read doesnt break on -1
				{
					if(c!=0)
					{
						//System.out.print((char)c);
						out.write((char)c);
					}
					c = inputStream.read() ;
				}
    	        out.close();
			} catch (IOException IOEx) {
				LOGGER.log(Level.SEVERE, IOEx.getMessage(), IOEx);
			}
  } 
}

/**
 * @return
 */
public boolean isM_Life() {
	return m_Life;
}

/**
 * @param life
 */
public void setM_Life(boolean life) {
	m_Life = life;
}

public void setLogFile(File aFile) {
	m_LogFilePath = aFile.getAbsolutePath();
}
}
