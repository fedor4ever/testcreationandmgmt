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


package com.symbian.driver.core.cmdline;

import java.util.logging.Level;

import com.symbian.driver.Task;
import com.symbian.driver.core.FrameworkBasedOperation;
import com.symbian.driver.core.controller.SymbianVisitor;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.argscheckers.AlphaNumericData;

/**
 * Class that describes the syntax of the 'run' command.
 * @author EngineeringTools
 *
 */
public class RunCmdLine extends CmdLine {

    /**
     * Standard constructor.
     */
    public RunCmdLine() {
        super("run", new FrameworkBasedOperation() {
        	
            protected synchronized Object runSpecial(final CmdLine aCmd, final Task aTarget) {
        		try {
        			
        			SymbianVisitor lSymbianVisitor = new SymbianVisitor();
        			if (lSymbianVisitor.start(aTarget)) {
        				return aCmd;
        			}
        			
        		} catch (Exception lException) {
        			LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
        		}
        		return null;
            }
        	
        }, "Run a driver file.");

        AlphaNumericData lAlphaNumericCheck = new AlphaNumericData();
        addSwitch("p", true, "The build target platform: armv5, arm4, thumb, winscw, wins. (Preference platform)", false, lAlphaNumericCheck);
        addSwitch("b", true, "The build target variant/build: udeb, urel. (Preference variant)", false, lAlphaNumericCheck);
        addSwitch("s", true, "Address of root task to run. (Preference entryPointAddress)", false, true);
        addSwitch("platsec", false, "Platform Security (PlatSec): ON, OFF. (Preference platsec)  DEPRECATED", false, true);
        addSwitch("t", true, "Transport protocol used to link with hardware board. (Preference transport)", false, true);
        addSwitch("l", true, "Location of where RDebug logs should be saved. (Preference rdebug)", false, true);
        addSwitch("f", true, "IP port for usage with UCC. (Preference uccAddress)", false, true);
        addSwitch("sysbin", false, "Allows copying to SYS/BIN with STATLite. (Preference sysbin) DEPRECATED: use --statlite", false, true);
        addSwitch("statlite", false, "Using STAT lite. (Preference statlite)", false, true);
        addSwitch("teflite", false, "Using TEF lite. (Preference teflite)", false, true);
        addSwitch("buildNumber", false, "OS build number. (Preference buildnumber)", false, true);
        addSwitch("c", true, "Location of where to save the results & logs. (Variable: ${resultroot}, Preference resultRoot)", false, true);
        addSwitch("k", true, "Kernel EKA1/EKA2. (Preference kernel)", false, true);
        addSwitch("ip", false, "IP address for tcp. DEPRECATED: use -t tcp:XX.XX.XX", false, true);
        addSwitch("commdb", false, "Intiatialise CommDB with WinTAP for the emulator. Use ON/OVERWRITE/OFF. (Preference commdb)", false, true);
		addSwitch("certpass", false, "Passphrase for signing sis files. please double quote if it has spaces", false, true);
		addSwitch("sigalgorithm", false, "Signature Algorithm RSA/DSA (default to RSA).", false, true);
		addSwitch("recovery", false, "Trigger to support crash recovery. Use ON/OFF. (Preference recovery)", false, true);
    }
}
