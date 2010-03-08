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


package com.symbian.utils.cmdline.argscheckers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;

/**
 * Checks that a parameter is alphanumeric.
 * @author EngineeringTools
 */
public class AlphaNumericData implements DataAcceptable {

    /** {@inheritDoc}
     * @see com.symbian.utils.cmdline.argscheckers.DataAcceptable#check(java.lang.String)
     * @param aString {@inheritDoc}
     * @throws ParseException {@inheritDoc}
     */
    public void check(final String aString) throws ParseException {
        Pattern lPattern = Pattern.compile("^\\w*$");
        Matcher lMatcher = lPattern.matcher(aString);
        
        if (!lMatcher.find()) {
        	throw new ParseException("non alpha-numeric parameter '" + aString + "'");
        }
    }
}
