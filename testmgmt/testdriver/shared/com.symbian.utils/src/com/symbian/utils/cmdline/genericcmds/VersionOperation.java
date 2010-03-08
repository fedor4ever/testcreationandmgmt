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


package com.symbian.utils.cmdline.genericcmds;


import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.CmdLine;

/**
 * Implements 'version' command.
 * @author EngineeringTools
 */
public class VersionOperation extends AnyGenericOperation {

    /** {@inheritDoc}
     * Effectively adds nothing to the super-class.
     * @see com.symbian.utils.cmdline.genericcmds.AnyGenericOperation#runSpecial(com.symbian.utils.cmdline.CmdLine)
     * @param aCmd {@inheritDoc}
     * @return {@inheritDoc}
     * @throws ParseException {@inheritDoc}
     */
    protected Object runSpecial(final CmdLine aCmd) throws ParseException {
    	System.out.println(aCmd.getNameVersionCopyRight());        
        return aCmd;
    }
}
