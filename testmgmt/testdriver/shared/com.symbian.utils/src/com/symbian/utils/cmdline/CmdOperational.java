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


package com.symbian.utils.cmdline;

import org.apache.commons.cli.ParseException;

/**
 * Interface for a command (Strategy design pattern).
 * @author EngineeringTools
 */
public interface CmdOperational {

    /**
     * Executes a command.
     * @param aCmd command syntax
     * @return any type of object the implementation of this interface needed to return
     * @throws ParseException if the command couldn't be processed
     */
    Object run(final CmdLine aCmd) throws ParseException;

}
