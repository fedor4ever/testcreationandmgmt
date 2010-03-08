/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.testdrop.util;

import java.util.concurrent.CancellationException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;

import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.ui.dialogs.TestDropMessageBox;

/**
 * Log and exception handler class
 * 
 */
public class LogExceptionHandler {
	
	private final static String preLog = "TestDrop log: "; 
	private final static boolean logEnabled = true;
	public final static String CANCELLED = Messages
			.getString("LogExceptionHandler.TestDropSendinCancelled"); 

	/**
	 * Logging method, prints out message that is gets from parameter
	 * 
	 * @param log
	 *            log message
	 */
	public static void log(String log) {
		if (logEnabled) {
			System.out.println(preLog + log);
		}
	}

	/**
	 * Shows error dialog with error message that is gets from the parameter
	 * 
	 * @param msg
	 *            error message
	 */
	public static void showErrorDialog(String msg) {
		TestDropMessageBox errorMessageBox = new TestDropMessageBox(msg, SWT.OK
				| SWT.ICON_ERROR);
		errorMessageBox.open();
	}

	/**
	 * Shows notify dialog with message
	 * 
	 * @param msg
	 *            message
	 */
	public static void showNotifyDialog(String msg) {
		TestDropMessageBox notifyMessageBox = new TestDropMessageBox(msg, SWT.OK
				| SWT.ICON_INFORMATION);
		notifyMessageBox.open();
	}

	/**
	 * Shows Question dialog, contain yes and no buttons
	 * 
	 * @param question
	 * @return user's answer
	 */
	public static int showQuestionDialog(String question) {
		TestDropMessageBox questionMessageBox = new TestDropMessageBox(question,
				SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		return questionMessageBox.open();
	}

	/**
	 * Checks if user cancelled sending operation
	 * 
	 * @param progressMonitor
	 *            IProgressMonitor instance
	 * @throws CancellationException
	 *             Cancellation Exception
	 */
	public static void cancelIfNeed(IProgressMonitor progressMonitor)
			throws CancellationException {
		if (progressMonitor == null) {
			throw new CancellationException(CANCELLED);
		} else {
			if (progressMonitor.isCanceled()) {
				throw new CancellationException(CANCELLED);
			}
		}

	}

	/**
	 * Checks if got cancellation exception
	 * 
	 * @param ex
	 *            Exception
	 * @return true if user is cancelled a job otherwise false
	 */
	public static boolean isCancelled(Exception ex) {
		if (ex.getMessage() != null && ex.getMessage().equals(CANCELLED)) {
			return true;
		} else {
			return false;
		}
	}
}
