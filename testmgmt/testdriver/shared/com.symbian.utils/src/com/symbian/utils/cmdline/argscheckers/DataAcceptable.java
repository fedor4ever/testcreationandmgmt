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

import org.apache.commons.cli.ParseException;

/**
 * @author EngineeringTools
 *
 */
public interface DataAcceptable {

    /**
     * Checks that the parameter is valid.
     * @param aString The string to check if it is valid.
     * @throws ParseException if the parameter is not valid
     */
    void check(final String aString) throws ParseException;

}
