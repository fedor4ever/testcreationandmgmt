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

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * @author EngineeringTools
 *
 */
public class ResultLocationCmdLine extends CmdLine {
	

    /**
     * Standard constructor.
     */
    public ResultLocationCmdLine() {
        super("resultpath", new CmdOperational() {

			public Object run(CmdLine aCmd) {
        		try {
					System.out.println(TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT) +
							ModelUtils.getBaseDirectory(TDConfig.getInstance().getPreferenceInteger(TDConfig.RUN_NUMBER)));
					return aCmd;
				} catch (ParseException lParseException) {
					LOGGER.log(Level.SEVERE, "Could not get the latest result location.", lParseException);
				}
				
				return null;
			}
        	
        }, "Returns the last result location.");
    }
}
