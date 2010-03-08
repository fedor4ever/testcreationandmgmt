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


package com.symbian.utils.test;

import java.io.File;

import com.symbian.utils.Epoc;

import junit.framework.TestCase;

/** JUnit test for Epoc.
 * @author EngineeringTools
 */
public class TestEpoc extends TestCase {

    /** Tests the isTargetValid method. */
    public final void testIsTargetValid() {
        assertTrue(Epoc.isTargetValid("winscw"));
        assertTrue(Epoc.isTargetValid("winSCW"));
        assertTrue(Epoc.isTargetValid("armv5"));
        assertTrue(Epoc.isTargetValid("aRMv5"));

        assertFalse(Epoc.isTargetValid("wins"));
        assertFalse(Epoc.isTargetValid("arm3"));
    }

    /** Test the isVariantValid method. */
    public final void testIsVariantValid() {
        assertTrue(Epoc.isVariantValid("UDEB"));
        assertTrue(Epoc.isVariantValid("UREL"));
        assertTrue(Epoc.isVariantValid("udeb"));
        assertTrue(Epoc.isVariantValid("urel"));

        assertFalse(Epoc.isVariantValid("qwqw"));
    }

    /** Tests the isTargetHardware method. */
    public final void testIsTargetHardware() {
        try {
            assertFalse(Epoc.isTargetEmulator("armv5"));
        } catch (IllegalArgumentException lIllegalArgumentException) {
            fail();
        }

        try {
            assertTrue(Epoc.isTargetEmulator("winscw"));
        } catch (IllegalArgumentException lIllegalArgumentException) {
            fail();
        }

        try {
            Epoc.isTargetEmulator("");
            fail();
        } catch (IllegalArgumentException lIllegalArgumentException) {
        	assertTrue(true);
        }
    }

    /** Test checkEpocEnv method. */
    public final void testCheckEpocEnv() {
        try {
            assertTrue(Epoc.isEpocrootValid(new File("\\")));
        } catch (IllegalArgumentException lIllegalArgumentException) {
            fail();
        }
    }

    /** test isInstallRequired method.*/
    public final void testIsInstallRequired() {
        assertTrue(Epoc.hasExecutableExtension("abc.exe"));
        assertTrue(Epoc.hasExecutableExtension("abc.ExE"));
        assertTrue(Epoc.hasExecutableExtension("abc.dll"));
        assertTrue(Epoc.hasExecutableExtension("abc.dLL"));
        assertTrue(Epoc.hasExecutableExtension("abc.dll"));

        assertFalse(Epoc.hasExecutableExtension("abc.ini"));
        assertFalse(Epoc.hasExecutableExtension("abc.ffg"));
    }
}
