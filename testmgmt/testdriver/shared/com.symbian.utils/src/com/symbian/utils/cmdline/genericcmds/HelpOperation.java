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


package com.symbian.utils.cmdline.genericcmds;

import java.util.List;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.CmdLine;

/**
 * Implements 'help' command: either general help or help on a particular
 * command.
 * 
 * @author EngineeringTools
 */
public class HelpOperation extends AnyGenericOperation {

	/**
	 * @see com.symbian.utils.cmdline.genericcmds.AnyGenericOperation#runSpecial(com.symbian.utils.cmdline.CmdLine)
	 * @param aCmd
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws ParseException
	 *             {@inheritDoc}
	 */
	protected synchronized Object runSpecial(final CmdLine aCmd) throws ParseException {
		List lExtraParams = aCmd.getCommandLine().getArgList();
		if (lExtraParams.isEmpty()) {
			aCmd.printAllCmds();
		} else if (lExtraParams.size() > 1) {
			throw new ParseException("Too many parameters");
		} else {
			String lSoughtCommand = (String) lExtraParams.get(0);
			CmdLine lCmdLine = aCmd.find(lSoughtCommand);
			if (lCmdLine != null) {
				lCmdLine.printHelp();
			} else {
				LOGGER.log(Level.SEVERE, "Unknown command '" + lSoughtCommand + "'");
				aCmd.printAllCmds();
			}
		}
		return aCmd;
	}
}
