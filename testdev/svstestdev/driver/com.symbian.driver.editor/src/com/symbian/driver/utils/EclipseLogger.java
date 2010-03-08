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


package com.symbian.driver.utils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.symbian.driver.presentation.DriverEditorPlugin;

public class EclipseLogger {
	// The first group of methods that follow are for convenience, 
	// appending information, error messages, and exceptions to the log for the Favorites plugin. 

	public static void logInfo(String message) {
	   log(IStatus.INFO, IStatus.OK, message, null);
	}

	public static void logError (Throwable exception) {
	   logError("Unexpected Exception", exception);
	}

	public static void logError (String message, Throwable exception) {
	   log(IStatus.ERROR, IStatus.OK, message, exception);
	}
	// Each of the preceding methods ultimately calls the following methods which create a status object 
	// (Status objects) and then append that status object to the log. 

	public static void log(int severity, int code, String message, Throwable exception) {
		log(createStatus(severity, code, message, exception));
	}

	public static IStatus createStatus (int severity, int code, String message, Throwable exception) {
		return new Status(severity, DriverEditorPlugin.ID, code, message, exception);
	}

	public static void log(IStatus status) {
	   DriverEditorPlugin.getPlugin().getLog().log(status);
	}
}
