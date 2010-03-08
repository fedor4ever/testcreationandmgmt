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

import com.symbian.utils.cmdline.argscheckers.EpocVariantData;

/**
 * @author EngineeringTools
 */
public class TestEpocVariantData extends TestCase {

    public void test() {
        EpocVariantData lFilterUnderTest = new EpocVariantData();

        try {
            lFilterUnderTest.check("urel");
            lFilterUnderTest.check("UDEB");
        } catch (ParseException aException) {
            fail();
        }

        try {
            lFilterUnderTest.check("gsfjksdj");
            lFilterUnderTest.check("arm5");

        } catch (ParseException aException) {

        }

    }

}