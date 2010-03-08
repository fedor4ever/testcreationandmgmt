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

package com.symbian.utils.log;

import java.io.IOException;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

/**
 * @author EngineeringTools
 * 
 */
public class NetSendHandler extends Handler {

	private static Level iLevel = Level.WARNING;

	private static int iNumberErrors = 0;

	private static int lCounter = 0;

	private static LogRecord[] iFailedRecords;

	private static final LogManager LOG_MANAGER = LogManager.getLogManager();

	private static final ErrorManager ERROR_MANAGER = new ErrorManager();

	/**
	 * 
	 */
	public NetSendHandler() {
		super();

		// try {
		// LOG_MANAGER.readConfiguration();
		// } catch (SecurityException lSecurityException) {
		// ERROR_MANAGER.error("Security Exception when reading loggging
		// configuration", lSecurityException, ErrorManager.GENERIC_FAILURE);
		// } catch (IOException lException) {
		// ERROR_MANAGER.error("IO Exception when reading loggging
		// configuration", lException, ErrorManager.GENERIC_FAILURE);
		// }

		String lNumberErrors = LOG_MANAGER.getProperty("com.symbian.utils.log.NetSendHandler.numbererrors");
		String lLevel = LOG_MANAGER.getProperty("com.symbian.utils.log.NetSendHandler.sendlevel");

		if (lNumberErrors != null) {
			iNumberErrors = Integer.parseInt(lNumberErrors);
		}
		if (lLevel != null) {
			iLevel = Level.parse(lLevel);
		}

		iFailedRecords = new LogRecord[iNumberErrors];
	}

	public void publish(LogRecord aRecord) {
		
		if (iNumberErrors != 0) {
			if (aRecord.getLevel().intValue() >= iLevel.intValue()) {
				if (lCounter < iNumberErrors - 1) {
					iFailedRecords[lCounter] = aRecord;
					lCounter++;
				} else {
					iFailedRecords[lCounter] = aRecord;
					lCounter = 0;
					
					int lNumberFinest= 0, lNumberFiner = 0, lNumberFine = 0,
						lNumberInfo = 0, lNumberWarning = 0, lNumberSevere = 0,
						lNumberConfig = 0;
					
					for (int lIter = 0; lIter < iNumberErrors; lIter++) {
						switch (iFailedRecords[lIter].getLevel().intValue()) {
							case 300: {
								lNumberFinest++;
								break;
							}
							case 400: {
								lNumberFiner++;
								break;
							}
							case 500: {
								lNumberFine++;
								break;
							}
							case 700: {
								lNumberConfig++;
								break;
							}
							case 800: {
								lNumberInfo++;
								break;
							}
							case 900: {
								lNumberWarning++;
								break;
							}
							case 1000: {
								lNumberSevere++;
								break;
							}
							default: {
								break;
							}
						}
					}
					
					StringBuffer lErrorMessage = new StringBuffer();
					lErrorMessage.append(" TestDriver contained the following number of messages: ");
					if (Level.FINEST.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberFinest + " FINEST; ");
					}
					if (Level.FINER.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberFiner + " FINER; ");
					}
					if (Level.FINE.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberFine + " FINE; ");
					}
					if (Level.CONFIG.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberConfig + " CONFIG; ");
					}
					if (Level.INFO.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberInfo + " INFO; ");
					}
					if (Level.WARNING.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberWarning + " WARNING; ");
					}
					if (Level.SEVERE.intValue() >= iLevel.intValue()) {
						lErrorMessage.append(lNumberSevere + " SEVERE; ");
					}
					
					try {
						String lExec = "net send " + LOG_MANAGER.getProperty("com.symbian.utils.log.NetSendHandler.ip") + lErrorMessage.toString();
						
						Runtime.getRuntime().exec(lExec);
						
					} catch (IOException lException) {
						ERROR_MANAGER.error("Could not net send TestDriver errors.", lException, ErrorManager.WRITE_FAILURE);
					}
				}
			}
		}
	}

	public void close() throws SecurityException {
		// Do nothing
	}

	public void flush() {
		// Do nothing
	}
}
