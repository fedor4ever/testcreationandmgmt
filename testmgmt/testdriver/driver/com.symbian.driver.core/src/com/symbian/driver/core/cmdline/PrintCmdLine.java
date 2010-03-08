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
import com.symbian.driver.core.controller.PrintVisitor;
import com.symbian.utils.cmdline.CmdLine;

/**
 * Class that describes the syntax of the 'print' command.
 * 
 * @author EngineeringTools
 */
public class PrintCmdLine extends CmdLine {

	/**
	 * Standard constructor.
	 */
	public PrintCmdLine() {
		super("print", new FrameworkBasedOperation() {
			
			protected synchronized Object runSpecial(final CmdLine aCmd, final Task aTarget) {
				try {
					
					PrintVisitor lPrintVisitor = new PrintVisitor();
					lPrintVisitor.start(aTarget);
					return aCmd;
					
				} catch (Exception lException) {
					LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
				}
				return null;
			}
			
		}, "Display testsuite contents to screen.");
		
		addSwitch("s", true, "Address of root task to run. (Preference entryPointAddress)", false, true);
	}
}
