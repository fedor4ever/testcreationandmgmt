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

import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.argscheckers.AlphaNumericData;

/**
 * @author EngineeringTools
 *
 */
public class DummyCmdLineTest extends CmdLine {

    /**
     * 
     */
    public DummyCmdLineTest() {
        super("dummy", new DummyOperationTest(), "run a test or a suite");
        
        AlphaNumericData lAlphaNumericCheck = new AlphaNumericData();
        
        addSwitch("a", true, "mandatory_alphanumericparam", true, lAlphaNumericCheck);
        addSwitch("b", true, "mandatory_noparam", true, false);
        addSwitch("c", true, "mandatory_anyparam", true, true);
        
        addSwitch("d", true, "optional_alphanumericparam", false, lAlphaNumericCheck);
        addSwitch("e", true, "optional_noparam", false, false);
        addSwitch("f", true, "optional_anyparam", false, true);
    }


}
