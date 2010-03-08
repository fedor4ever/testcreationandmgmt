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

import com.symbian.utils.cmdline.CmdLine;

/**
 * Describes syntax for 'version' command.
 * @author EngineeringTools
 */
public class VersionCmdLine extends CmdLine {

    /**
     * The version commandline command.
     */
    public VersionCmdLine() {
        super("version", new VersionOperation(), "print TestDriver version.");
    }

}
