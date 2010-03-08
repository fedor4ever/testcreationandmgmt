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


import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

public abstract class AnyGenericOperation  implements CmdOperational {

	protected static Logger LOGGER = Logger.getLogger(AnyGenericOperation.class.getName());
	
    public synchronized Object run(final CmdLine aCmd) throws ParseException {
        return runSpecial(aCmd);	// Template Method design pattern
    }

    protected abstract Object runSpecial(CmdLine aCmd) throws ParseException;

}
