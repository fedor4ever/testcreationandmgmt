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


package com.symbian.utils.cmdline;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.symbian.utils.cmdline.genericcmds.HelpCmdLine;
import com.symbian.utils.cmdline.genericcmds.VersionCmdLine;

/**
 * The class that modelizes a group of commands, typically corresponds to a
 * tool.
 * 
 * @author EngineeringTools
 */
public final class CmdLineSet {

	protected static Logger LOGGER = Logger.getLogger(CmdLineSet.class.getName());

	/** The list of commands. */
	private Set iCmds = new LinkedHashSet();

	/** The parser for the commands. */
	private CommandLineParser iParser = new PosixParser();

	private String iName;

	private String iVersion;

	private String iCopyright;

	/**
	 * Standard constructor for users interested in using the ToolEnvironment
	 * class.
	 */
	/**
	 * 
	 */
	public CmdLineSet() {
		/*
		 * add default version and help command
		 * 
		 */
		add(new HelpCmdLine());
		add(new VersionCmdLine());
	}

	/**
	 * Print all the commands available (with a short description) for a
	 * particular command set.
	 */
	public void printCmds() {
		System.out.println("Common usage:\n" + iName + " [COMMAND]\n[COMMAND] options");

		Iterator lIterator = iCmds.iterator();
		while (lIterator.hasNext()) {
			CmdLine lCmd = (CmdLine) lIterator.next();
			lCmd.printDescription();
		}
		System.out.println(iName + " help [COMMAND] for command specific help");
	}

	/**
	 * Process a command.
	 * 
	 * @param aArgs
	 *            array of parameters provided by the user
	 * @return result of the processsed command
	 * @throws ParseException
	 *             if command line is incorrect
	 */
	public Object processCommand(final String[] aArgs) {

		String[] arguments = aArgs;
		CmdLine lCmd = null;
		if (aArgs == null || aArgs.length < 1 || find(arguments[0]) == null) {
			LOGGER.warning("Command line incorrect.");
			lCmd = find("help");
			lCmd.printAllCmds();
			return null;
		}
		lCmd = find(arguments[0]);

		String[] lParams = new String[arguments.length - 1];
		for (int lCounter = 1; lCounter < arguments.length; lCounter++) {
			lParams[lCounter - 1] = arguments[lCounter];
		}

		try {
			CommandLine lCmdLine = iParser.parse(lCmd.getOptions(), lParams);
			if (lCmdLine == null) {
				LOGGER.severe("Cannot parse command line arguments.");
				lCmd.printHelp();
				return null;
			}
			lCmd.checkConstraints(lCmdLine);
			return lCmd.start(lCmdLine);
		} catch (ParseException lParseException) {
			LOGGER.severe("Problem with switch(es): \"" + lParseException.getMessage() + "\"");
			lCmd.printHelp();
			return null;
		}
	}

	/**
	 * Find a command in a command set given a command name.
	 * 
	 * @param aCommand
	 *            command name to find
	 * @return A commandline.
	 */
	public CmdLine find(final String aCommand) {
		Iterator lIterator = iCmds.iterator();
		while (lIterator.hasNext()) {
			CmdLine lCmd = (CmdLine) lIterator.next();
			if (lCmd.isCommand(aCommand)) {
				return lCmd;
			}
		}
		return null;
	}

	/**
	 * Add a command to a command set.
	 * 
	 * @param aCmd
	 *            command to add
	 */
	public void add(final CmdLine aCmd) {
		iCmds.add(aCmd);
		aCmd.setCmdSet(this);
	}

	/**
	 * Remove a command from a command set.
	 * 
	 * @param aCmd
	 *            command to remove
	 */
	public void remove(final CmdLine aCmd) {
		iCmds.remove(aCmd);
		aCmd.setCmdSet(null);
	}

	/**
	 * Print the version of the tool associated to the command set.
	 * 
	 * @return 
	 */
	public String printNameVersionCopyRight() {
		return iName + " v" + iVersion + " (" + iCopyright + ")";
	}

	/**
	 * @param lName
	 * @param lVersion
	 * @param lCopyright
	 */
	public void setNameVersionCopyRight(String lName, String lVersion, String lCopyright) {
		if (lName == null) {
			iName = "null";
		} else {
			iName = lName;
		}

		if (lVersion == null) {
			iVersion = "null";
		} else {
			iVersion = lVersion;
		}

		if (lCopyright == null) {
			iCopyright = "null";
		} else {
			iCopyright = lCopyright;
		}

	}
}
