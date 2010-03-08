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

import java.net.Socket;

import org.apache.commons.cli.ParseException;

/**
 * Checks that a parameter is valid socket name (machine name or decimal IP address with port).
 * @author EngineeringTools
 */
public class OpenSocketData implements DataAcceptable {

    /** The default port. */
    private static int DEFAULT_PORT = 7;

    /**
     * @param aPort Opens a port at the paramters.
     */
    public OpenSocketData(final int aPort) {
        DEFAULT_PORT = aPort;
    }

    /** {@inheritDoc}
     * @see com.symbian.utils.cmdline.argscheckers.DataAcceptable#check(java.lang.String)
     * @param aString {@inheritDoc}
     * @throws ParseException {@inheritDoc}
     */
    public void check(final String aString) throws ParseException {
        String[] lSplitSocket = aString.split(":");
        if (lSplitSocket.length < 1 || lSplitSocket.length > 2) {
            throw new ParseException("illegal socket name");
        }

        int lPort = (lSplitSocket.length == 1) ?  DEFAULT_PORT : Integer.parseInt(lSplitSocket[1]);
        if (lPort == 0) {
            throw new ParseException("illegal port number");
        }

        String lServer = lSplitSocket[0];

        Socket lSocket = null;

        // connect to server
        try {
            lSocket = new Socket(lServer, lPort);
            lSocket.close();
            lSocket = null;
        } catch (Exception lException) {
            throw new ParseException("cannot connect to socket '" + aString + "'");
        }
    }
}
