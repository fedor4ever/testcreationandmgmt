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

package com.symbian.nativeprocesshandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.symbian.utils.JarUtils;

/**
 * JNI code to get Process information and kill natively.
 * 
 * @author EngineeringTools
 */
public class ProcessHandler {

	
	
	/** The logger for the ProcessHandler class. */
	protected static final Logger LOGGER = Logger.getLogger(ProcessHandler.class.getName());
	
	// Load the JNI code
	static {
		try {
			//Extract the library
			File lProcessHandlerDll = JarUtils.extractResource(ProcessHandler.class, "/resource/NativeProcessHandler.dll");			
			//load the library
			System.load(lProcessHandlerDll.getAbsolutePath());
		} catch (IOException lIOException) {
			try {
				File lJStatDll = new File("../../shared/com.symbian.nativeprocesshandler/resource/NativeProcessHandler.dll");
				System.load(lJStatDll.getAbsolutePath());
			} catch (NoClassDefFoundError lNoClassDefFoundError) {
				LOGGER.log(Level.SEVERE, "JStat.dll could not be extracted.", lNoClassDefFoundError);
				System.exit(-1);
			}
		} catch (UnsatisfiedLinkError lUnsatisfiedLinkError) {
			LOGGER.log(Level.SEVERE, "Could not load the NativeProcessHandler.dll.", lUnsatisfiedLinkError);
			System.exit(-1);
		} 
	}

	/**
	 * Gets the list of the PID's corresponding to the input string.
	 * 
	 * @param executable The name of the executable you want the PID of.
	 * @return List of PID's corresponding to the executable.
	 */
	public static native int[] getPidList(String executable);

	/**
	 * Use C native code to kill a process.
	 * 
	 * @param pid The Process IDentfication (PID) number of the process to kill.
	 */
	public static native void kill(int pid);

}
