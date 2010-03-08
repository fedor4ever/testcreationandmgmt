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

import java.io.IOException;

/**
 * @author EngineeringTools
 *
 */
public class Test extends HarnessTest {
    

    public static void main(String[] args) {
        HarnessTest lHarness = new HarnessTest();
        System.out.println("qweqwe");
        lHarness.release();
        
        try {
            System.out.print(lHarness.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
