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

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.eclipse.ui.console.MessageConsoleStream;

import com.symbian.driver.provider.DriverEditPlugin;

/**
 * @author EngineeringTools
 * 
 */
public class EclipseConsoleHandler extends ConsoleHandler {

	private static MessageConsoleStream INFO = null;

	private static MessageConsoleStream WARNING = null;

	private static MessageConsoleStream SEVERE = null;

	private static EclipseConsoleHandler iEclipseConsoleHandler = null;

	public static EclipseConsoleHandler getInstance(MessageConsoleStream aInfo,
			MessageConsoleStream aWarning, MessageConsoleStream aSevere) {
		if (iEclipseConsoleHandler == null) {
			iEclipseConsoleHandler = new EclipseConsoleHandler(aInfo, aWarning,
					aSevere);
		}
		return iEclipseConsoleHandler;
	}

	/**
	 * @param aInfo
	 * @param aWarning
	 * @param aSevere
	 */
	public EclipseConsoleHandler(MessageConsoleStream aInfo,
			MessageConsoleStream aWarning, MessageConsoleStream aSevere) {
		INFO = aInfo;
		WARNING = aWarning;
		SEVERE = aSevere;
	}

	/**
	 * @see java.util.logging.StreamHandler#flush()
	 */
	public synchronized void flush() {
		try {
			INFO.flush();
			WARNING.flush();
			SEVERE.flush();
		} catch (IOException lException) {
			DriverEditPlugin.getPlugin().log(lException);
		}
	}

	/**
	 * @see java.util.logging.ConsoleHandler#publish(java.util.logging.LogRecord)
	 */
	public void publish(final LogRecord aRecord) {
		DateFormat lDate = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.SHORT);

		if (aRecord.getLevel().equals(Level.INFO)) {
			INFO.println(lDate.format(new Date(aRecord.getMillis())) + " "
					+ aRecord.getMessage());
		} else if (aRecord.getLevel().equals(Level.WARNING)) {
			WARNING.println(lDate.format(new Date(aRecord.getMillis()))
					+ " "
					+ aRecord.getMessage()
					+ (aRecord.getThrown() != null ? "\n\t"
							+ aRecord.getThrown().getMessage() : ""));
		} else if (aRecord.getLevel().equals(Level.SEVERE)) {
			SEVERE.println(lDate.format(new Date(aRecord.getMillis()))
					+ " "
					+ aRecord.getMessage()
					+ (aRecord.getThrown() != null ? "\n\t"
							+ aRecord.getThrown().getMessage() : ""));
		}

		flush();
	}
}
