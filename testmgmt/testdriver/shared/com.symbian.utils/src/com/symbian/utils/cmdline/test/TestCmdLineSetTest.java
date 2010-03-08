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


package com.symbian.utils.cmdline.test;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdLineSet;

/**
 * @author EngineeringTools
 *  
 */
public class TestCmdLineSetTest extends TestCase {

    private CmdLineSet iNonToolEnvironmentBaseCmdSet;

    protected void setUp() throws Exception {
        super.setUp();
        iNonToolEnvironmentBaseCmdSet = new CmdLineSet();
        iNonToolEnvironmentBaseCmdSet.add(new DummyCmdLineTest());
    }

    /**
     * 
     */
    public void testAllOKay() {
        try {
            CmdLine lCmd = (CmdLine) iNonToolEnvironmentBaseCmdSet.processCommand(new String[] { "dummy", "-a", "abcd", "-b", "-c", "efgh" });

            assertTrue(lCmd.getCommandLine().hasOption("a"));
            assertEquals("abcd", lCmd.getCommandLine().getOptionValue("a"));

            assertTrue(lCmd.getCommandLine().hasOption("b"));
            assertEquals(null, lCmd.getCommandLine().getOptionValue("b"));

            assertTrue(lCmd.getCommandLine().hasOption("c"));
            assertEquals("efgh", lCmd.getCommandLine().getOptionValue("c"));

        } catch (ParseException aException) {
            System.err.println(aException.getMessage());
            fail();
        }
    }

    /**
     * negative test.
     */
    public void testNoSwitches() {
            iNonToolEnvironmentBaseCmdSet.processCommand(new String[] { "dummy" });
            fail();
    }

    /**
     * negative test.
     */
    public void testBadSwitchFormat() {
            iNonToolEnvironmentBaseCmdSet.processCommand(new String[] { "dummy", "-a", "---$£$", "-b", "-c", "efgh" });
            fail();
    }

    /**
     * 
     */
    public void testThreeBareWords() {
        try {
            CmdLine lCmd = (CmdLine) iNonToolEnvironmentBaseCmdSet.processCommand(new String[] { "dummy", "-a", "abcd", "-b", "-c", "efgh", "bare-z", "bare-y",
                    "bare-x" });
            List lList = lCmd.getCommandLine().getArgList();
            assertEquals(3, lList.size());
            assertEquals("bare-z", lList.get(0));
            assertEquals("bare-y", lList.get(1));
            assertEquals("bare-x", lList.get(2));
        } catch (ParseException aException) {
            System.err.println(aException.getMessage());
            fail();
        }
    }

    /**
     * 
     */
    public void testOptionalSwitches() {
        try {
            CmdLine lCmd = (CmdLine) iNonToolEnvironmentBaseCmdSet.processCommand(new String[] { "dummy", "-a", "abcd", "-b", "-c", "efgh", "-d", "ijkl", "-e",
                    "-f", "mnop" });

            assertTrue(lCmd.getCommandLine().hasOption("d"));
            assertEquals("ijkl", lCmd.getCommandLine().getOptionValue("d"));

            assertTrue(lCmd.getCommandLine().hasOption("e"));
            assertEquals(null, lCmd.getCommandLine().getOptionValue("e"));

            assertTrue(lCmd.getCommandLine().hasOption("f"));
            assertEquals("mnop", lCmd.getCommandLine().getOptionValue("f"));
        } catch (ParseException aException) {
            System.err.println(aException.getMessage());
            fail();
        }
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
