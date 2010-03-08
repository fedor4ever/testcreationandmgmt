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

import java.util.Properties;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author EngineeringTools
 *
 */
public class EmailHandler extends Handler {
	
	private static Level iLevel = Level.WARNING;
	
	private static int iNumberErrors = 0;
	
	private static int iCounter = 0;
	
	private static LogRecord[] iFailedRecords;
	
	private static final LogManager LOG_MANAGER = LogManager.getLogManager();
	
	/**
	 * 
	 */
	public EmailHandler() {
		super();


		
		String lNumberErrors = LOG_MANAGER.getProperty("com.symbian.utils.log.EmailHandler.numbererrors");
		String lLevel = LOG_MANAGER.getProperty("com.symbian.utils.log.EmailHandler.sendlevel");
		
		
		if (lNumberErrors != null) {
			iNumberErrors = Integer.parseInt(lNumberErrors);
		}
		if (lLevel != null) {
			iLevel  = Level.parse(lLevel);
		}
		
		iFailedRecords = new LogRecord[iNumberErrors];
	}

	public void publish(LogRecord aRecord) {
		
		if (iNumberErrors != 0) {
			if (aRecord.getLevel().intValue() >= iLevel.intValue()) {
				if (iCounter < iNumberErrors - 1) {
					iFailedRecords[iCounter] = aRecord;
					iCounter++;
				} else {
					iFailedRecords[iCounter] = aRecord;
					iCounter = 0;
	
					StringBuffer lErrorMessage = new StringBuffer();
					lErrorMessage.append("Testdriver failed " + iCounter +" times, with the following error messages: \n\n");
					for (int lIter = 0; lIter < iNumberErrors; lIter++) {
						lErrorMessage.append(new SimpleFormatter().format(iFailedRecords[lIter]) + "\n\n");
					}
						
					String lFromAddress = LOG_MANAGER.getProperty("com.symbian.utils.log.EmailHandler.fromaddress");
					String[] lToAddress = LOG_MANAGER.getProperty("com.symbian.utils.log.EmailHandler.toaddress").split(",");
					String lServer = LOG_MANAGER.getProperty("com.symbian.utils.log.EmailHandler.server");

					if ((lFromAddress != null || lToAddress != null || lServer != null)
							&& aRecord.getLevel().intValue() >= iLevel.intValue()) {
						new MailInfo(lFromAddress, lToAddress, lServer, "TestDriver " + aRecord.getLevel().toString() + " Messages", lErrorMessage.toString()).sendMail();
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

/**
 * @author EngineeringTools
 *
 */
class MailInfo {
	private String iFromAddress;

	private String[] iToAddress;

	private String iServerAddress;

	private String iSubject;

	private String iMessage;

	private static final ErrorManager ERROR_MANAGER = new ErrorManager();

	/**
	 * @param aFromAddress
	 * @param aToAddress
	 * @param aSeverAdress
	 * @param aSubject
	 * @param aMessage
	 */
	public MailInfo(final String aFromAddress, final String[] aToAddress, final String aSeverAdress, final String aSubject, final String aMessage) {
		this.iFromAddress = aFromAddress;
		this.iToAddress = aToAddress;
		this.iServerAddress = aSeverAdress;
		this.iSubject = aSubject;
		this.iMessage = aMessage;
	}

	/**
	 * 
	 */
	public void sendMail() {
		try {
			Properties lProperties = new Properties();
			lProperties.put("mail.smtp.host", iServerAddress);
			Session session = Session.getDefaultInstance(lProperties, null);
			session.setDebug(false);
			// Create a iMessage
			Message mimeMsg = new MimeMessage(session);
			// Set the from and to address
			Address addressFrom = new InternetAddress(iFromAddress);
			mimeMsg.setFrom(addressFrom);
			Address[] to = new InternetAddress[iToAddress.length];
			for (int i = 0; i < iToAddress.length; i++)
				to[i] = new InternetAddress(iToAddress[i]);
			mimeMsg.setRecipients(Message.RecipientType.TO, to);
			mimeMsg.setSubject(iSubject);
			mimeMsg.setText(iMessage);
			Transport.send(mimeMsg);
		} catch (Exception lException) {
			ERROR_MANAGER.error("Could not send TestDriver email", lException, ErrorManager.GENERIC_FAILURE);
		} catch (Throwable lThrowable) {
			ERROR_MANAGER.error("Could not send TestDriver email", new Exception(), ErrorManager.GENERIC_FAILURE);
		}
	}
}
