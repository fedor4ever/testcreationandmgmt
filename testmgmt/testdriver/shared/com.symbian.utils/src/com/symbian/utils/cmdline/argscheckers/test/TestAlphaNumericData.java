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

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.argscheckers.AlphaNumericData;

/**
 * @author EngineeringTools
 */
public class TestAlphaNumericData extends TestCase {

    public void test() {
        AlphaNumericData lFilterUnderTest = new AlphaNumericData();

        try {
            lFilterUnderTest.check("1234567890");
            lFilterUnderTest.check("qwerty");
            lFilterUnderTest.check("1234567890abcdef_");
            lFilterUnderTest.check("__1234");
        } catch (ParseException aException) {
            fail();
        }

        try {
            lFilterUnderTest.check("1234*aa");
            lFilterUnderTest.check("12.s34");
            lFilterUnderTest.check("12^s34");

        } catch (ParseException aException) {

        }

    }

}