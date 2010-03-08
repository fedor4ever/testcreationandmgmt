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

import org.eclipse.emf.ecore.resource.Resource;

import com.symbian.driver.core.ExtendedDriverValidator;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * @author EngineeringTools
 *
 */
public class ValidateCmdLine extends CmdLine {

    /**
     * Standard constructor.
     */
    public ValidateCmdLine() {
        super("validate", new CmdOperational() {
        	
        	public synchronized Object run(CmdLine aCmd) {
        		try {
        			TDConfig.getInstance().setConfigFromCmdLine(aCmd);
        			ResourceLoader.load();
        			ExtendedDriverValidator.validator((Resource) ResourceLoader.getResourceSet().getResources().get(0));
        			return aCmd;
        		} catch (Exception lException) {
        			LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
        		}
        		
        		return null;
            }
        	
        }, "Validate a driver file.");

        addSwitch("s", true, "Address of root task to run. (Preference entryPointAddress)", false, true);
    }
}
