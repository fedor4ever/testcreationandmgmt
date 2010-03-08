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

import com.symbian.utils.cmdline.argscheckers.NumericData;

/**
 * @author EngineeringTools
 */
public class TestNumericData extends TestCase {

    NumericData lFilterUnderTest = new NumericData();

    public void testCorrectNumber() {

        try {
            lFilterUnderTest.check("1234567890");
        } catch (ParseException aException) {
            System.err.println(aException.getMessage());
            fail();
        }

    }

    public void testIncorrectNumbers() {

        try {
            lFilterUnderTest.check("1234aa");
            fail();
        } catch (ParseException aException) {

        }

        try {
            lFilterUnderTest.check("12s34");
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