/*
* Copyright (c) 2005 Nokia Corporation and/or its subsidiary(-ies).
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
* Description:  Simple log formatter used with Java's logger.
*
*/


package com.nokia.hti.common;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Simple log formatter used with Java's logger.
 * 
 */
public class SimpleLogFormatter extends Formatter
{

//==============================================================================
//Public constants

//==============================================================================
//Public methods

    public String format( LogRecord record )
    {
        return record.getMessage() == null
                ? "\n"
                : record.getMessage() + "\n";
    }

//==============================================================================
//Protected methods

//==============================================================================
//Private methods

//==============================================================================
//Protected attributes

//==============================================================================
//Private attributes

//==============================================================================
//Static initialization block

//==============================================================================
//Inner classes
}

