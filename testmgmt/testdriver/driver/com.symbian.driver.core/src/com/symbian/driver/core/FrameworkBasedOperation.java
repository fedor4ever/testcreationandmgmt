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


package com.symbian.driver.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.Task;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class specialized by all the command operation classes.
 * 
 * @author EngineeringTools
 * 
 */
public abstract class FrameworkBasedOperation implements CmdOperational {

	/** Logger. */
	protected final static Logger LOGGER = Logger.getLogger(FrameworkBasedOperation.class.getName());
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see com.symbian.utils.cmdline.CmdOperational#run(com.symbian.utils.cmdline.CmdLine)
	 * @param aCmd
	 *            {@inheritDoc}
	 * @throws ParseException
	 *             {@inheritDoc}
	 * @throws  
	 */
	public synchronized Object run(final CmdLine aCmd) throws ParseException {

		TDConfig.getInstance().setConfigFromCmdLine(aCmd);
		LOGGER.info(aCmd.getNameVersionCopyRight() + "\n"
				+ "Command: " + aCmd.getCommand() + "\n"
				+ "Purpose: " + aCmd.getPurpose());
		
		Task lTask = ResourceLoader.load();

		if (lTask != null) {
			
			try {
				TDConfig.getInstance().printConfig(true);
				return runSpecial(aCmd, lTask);
				
			} catch (IOException lException) {
				LOGGER.log(Level.SEVERE, lException.getMessage(), lException);
				return null;
			}
			
		} else {
			throw new RuntimeException("Could not run the command specified.");
		}
	}

	/**
	 * Implements specific behavior in the subclass (Template Method design
	 * pattern).
	 * 
	 * @param aCmd
	 *            The command to run using runSpecial
	 * @param aTarget
	 * @return 
	 * @throws ParseException
	 *             If the commandline is incorrect.
	 */
	protected abstract Object runSpecial(final CmdLine aCmd, final Task aTarget) throws ParseException;

}
