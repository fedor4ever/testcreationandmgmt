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


package com.symbian.utils.cmdline.test.testharness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * simple test harness which could be used to automatically test the command line tools using JUnit
 * @author EngineeringTools
 *
 */
public class HarnessTest {

    static String lFileName = "tmp.txt";

    static File iTempFile = new File(lFileName);

    public HarnessTest() {
        divertStdOut();
    }

    private void divertStdOut() {
        try {
            System.setOut(new PrintStream(new FileOutputStream(iTempFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readLine() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(lFileName));
        String str;
        String lWhole = new String();
        while ((str = in.readLine()) != null) {
            lWhole += str;
        }
        return lWhole;
    }
    
    public void release(){
        System.setOut(System.out);
    }

}
