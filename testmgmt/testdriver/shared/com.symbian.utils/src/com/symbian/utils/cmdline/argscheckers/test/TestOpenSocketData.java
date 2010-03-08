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



package com.symbian.utils.cmdline.argscheckers.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.argscheckers.OpenSocketData;

/**
 * @author EngineeringTools
 */
public class TestOpenSocketData extends TestCase {

    OpenSocketData lFilterUnderTest = new OpenSocketData(7);

    public void testLocalHost() {
        try {
            lFilterUnderTest.check("localhost");
        } catch (ParseException aException) {
            fail();
        }
    }

    public void testAddressAndPort() {

        try {
            lFilterUnderTest.check(InetAddress.getLocalHost().getHostAddress());
        } catch (ParseException aException) {
            fail();
        } catch (UnknownHostException e) {
            fail();
        }
    }

    public void testLocalHostAndPort() {
        try {
            lFilterUnderTest.check("localhost:7");
        } catch (ParseException aException) {
            fail();
        }
    }

    public void testIncorectSyntaxes() {
        try {
            lFilterUnderTest.check("localhost::7");
            fail();
        } catch (ParseException aException) {

        }

        try {
            lFilterUnderTest.check("localhost:7:1");
            fail();
        } catch (ParseException aException) {

        }

        try {
            lFilterUnderTest.check("1234aa");
            fail();
        } catch (ParseException aException) {

        }

        try {
            lFilterUnderTest.check("7");
            fail();
        } catch (ParseException aException) {

        }

        try {
            lFilterUnderTest.check("__1234");
            fail();
        } catch (ParseException aException) {

        }
    }

}