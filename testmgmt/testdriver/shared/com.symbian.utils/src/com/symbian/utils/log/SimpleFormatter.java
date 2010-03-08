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

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author EngineeringTools
 *
 */
public class SimpleFormatter extends Formatter {

	public String format(LogRecord aRecord) {
        StringBuffer buf = new StringBuffer();
        
        String aLevelName = null;
        if (aRecord.getLevel() == Level.INFO) {
        	aLevelName = "REMARK";
        } else if (aRecord.getLevel() == Level.SEVERE) {
        	aLevelName = "ERROR";
        } else {
        	aLevelName = aRecord.getLevel().getName();
        }

        buf.append(aLevelName);
        buf.append(' ');
        
        DateFormat lDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        
        buf.append(lDate.format(new Date(aRecord.getMillis())));
        buf.append(' ');
        buf.append(formatMessage(aRecord));
        
        if (aRecord.getThrown() != null
        		&& aRecord.getThrown().getMessage() != null
        		&& aRecord.getLevel().intValue() >= Level.SEVERE.intValue()) {
        	buf.append("\r\n\t" + aRecord.getThrown().getMessage());
        }
        
        buf.append("\r\n\r\n");
        return buf.toString();
	}

}
