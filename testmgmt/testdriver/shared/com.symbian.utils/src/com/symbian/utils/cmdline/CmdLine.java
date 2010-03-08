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

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.symbian.utils.cmdline.argscheckers.AnyData;
import com.symbian.utils.cmdline.argscheckers.DataAcceptable;

/**
 * Modelizes the syntax of a command.
 * 
 * @author EngineeringTools
 */
public abstract class CmdLine {

	/** The command to run. */
	private String iCommand = null;

	/** The command operation. */
	private CmdOperational iCmdOperation = null;

	/** A discription of the command. */
	private String iDescription = null;

	/** A command set. */
	private CmdLineSet iCmdSet = null;

	/** The options to the command. */
	private Options iOptions = new Options();

	/** The formater for the Help. */
	private HelpFormatter iFormatter = new HelpFormatter();

	/** The command line. */
	private CommandLine iCommandLine = null;

	/** The list of paramters to check. */
	private ArrayList iParametersToCheck = new ArrayList();

	/** The list of paramter checks. */
	private ArrayList iParametersChecks = new ArrayList();

	/** The data checker. */
	private AnyData iAnyDataChecker = new AnyData();

	/** The first column size to print. */
	private static final int FIRST_COL_SIZE = 15;

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(CmdLine.class.getName());

	/**
	 * Retrieves the <code>org.apache.commons.cli.CommandLine</code> object
	 * that parses the command line. Use this to customize the command line.
	 * 
	 * @return Command Line
	 * @throws ParseException
	 *             If the commandline is invalid.
	 */
	public CommandLine getCommandLine() throws ParseException {
		if (iCommandLine == null) {
			throw new ParseException("NULL pointer exception at constructor of Commandline.");
		}
		return iCommandLine;
	}

	/**
	 * Standard constructor.
	 * 
	 * @param aCommand
	 *            name of the command
	 * @param aCmdOperation
	 *            object that implements command
	 * @param aDescription
	 *            brief description of the command (used by help command)
	 */
	public CmdLine(final String aCommand, final CmdOperational aCmdOperation, final String aDescription) {
		iCommand = aCommand;
		iCmdOperation = aCmdOperation;
		iDescription = aDescription;
		
		//add a config parameter to all commands to allow user to run testdriver with config file, 
		//instead of everytime use windows registry.
		if (needConfigParam()) {
		    addSwitch("conf", false, "configuration file location.", false, true);
		}
	}
	
	/**
	 * check if the command need config paramters
	 * @return
	 */
	private boolean needConfigParam() {
		String clsName = this.getClass().getName(); 
	    if (clsName.indexOf("ConfigCmdLine") > 0) {
	    	return false;
	    }
	    if (clsName.indexOf("HelpCmdLine") > 0) {
	    	return false;
	    }
	    if (clsName.indexOf("VersionCmdLine") > 0) {
	    	return false;
	    }
	    return true;
	}

	/**
	 * Associates to a command set.
	 * 
	 * @param aCmdSet
	 *            command set
	 */
	protected void setCmdSet(final CmdLineSet aCmdSet) {
		iCmdSet = aCmdSet;
	}

	/**
	 * Determines if a particular command name matches the command.
	 * 
	 * @param aCommand
	 *            command name
	 * @return <code>true</code> if the string is a command,
	 *         <code>false</code> otherwise.
	 */
	public final boolean isCommand(final String aCommand) {
		return (iCommand.equalsIgnoreCase(aCommand));
	}

	/**
	 * Adds a switch to a command.
	 * 
	 * @param aSwitchName
	 *            name of the switch
	 * @param aIsOneLetter
	 *            <code>true</code> if the switch is composed of only one letter, <code>false</code> if long format switch.
	 * @param aDescription
	 *            description of the switch (for help command)
	 * @param aIsMandatory
	 *            make switch mandatory
	 * @param aHasArgs
	 *            make switch arguments possible 
	 *            'possible'
	 */
	public synchronized void addSwitch(final String aSwitchName, final boolean aIsOneLetter, final String aDescription,
			final boolean aIsMandatory, final boolean aHasArgs) {
		addSwitch(aSwitchName, aIsOneLetter, aDescription, aIsMandatory, aHasArgs ? iAnyDataChecker : null);
	}

	/**
	 * Adds a switch to a command.
	 * 
	 * @param aSwitchName
	 *            name of the switch
	 * @param aIsSingle
	 *            If the switch is single.
	 * @param aDescription
	 *            description of the switch (for help command)
	 * @param aIsMandatory
	 *            make switch mandatory
	 * @param aDataCheck
	 *            facility to check the validity of the parameter.
	 */
	public synchronized void addSwitch(final String aSwitchName, final boolean aIsSingle, final String aDescription,
			final boolean aIsMandatory, final DataAcceptable aDataCheck) {

		Option lOption = null;

		OptionBuilder.withArgName(aSwitchName);
		OptionBuilder.withDescription(aDescription);
		OptionBuilder.isRequired(aIsMandatory);
		OptionBuilder.hasArg(aDataCheck != null);

		if (!aIsSingle) {
			OptionBuilder.withLongOpt(aSwitchName);
			lOption = OptionBuilder.create();
		} else {
			lOption = OptionBuilder.create(aSwitchName);
		}

		if (aDataCheck != null) {
			iParametersToCheck.add(aSwitchName);
			iParametersChecks.add(aDataCheck);
		}
		iOptions.addOption(lOption);
	}

	/**
	 * Parses a command to check its validity.
	 * 
	 * @param aCommandLine
	 *            <code>org.apache.commons.cli.CommandLine</code> object
	 * @throws ParseException
	 *             if the command provided is not syntaxically correct
	 */
	public final void checkConstraints(final CommandLine aCommandLine) throws ParseException {
		iCommandLine = aCommandLine;

		if (iParametersChecks.size() != iParametersToCheck.size()) {
			throw new ParseException("internal error, badly handled Parameters checks");
		}

		for (int lCounter = 0; lCounter < iParametersChecks.size(); lCounter++) {
			DataAcceptable lDataChecker = (DataAcceptable) iParametersChecks.get(lCounter);
			String lArgToCheck = (String) iParametersToCheck.get(lCounter);
			if (lDataChecker == null || lArgToCheck == null) {
				throw new ParseException("internal error, badly handled Parameter '" + lArgToCheck + "' (1)");
			}
			String lDataToCheck = aCommandLine.getOptionValue(lArgToCheck);
			if (aCommandLine.hasOption(lArgToCheck)) {
				if (lDataToCheck == null) {
					throw new ParseException("internal error, badly handled Parameter '" + lArgToCheck + "' (2)");
				}
				lDataChecker.check(lDataToCheck);
			}
		}

		checkAdditionalConstraints(aCommandLine);
	}

	/**
	 * Checks additional constraints. method only useful if overridden by
	 * specialized classes<br>
	 * this is the method to be overriden if one wants to specify specific
	 * forbidden switch combinations
	 * 
	 * @param aCommandLine
	 *            The commandline to check addtional constraints
	 * @throws ParseException
	 */
	public void checkAdditionalConstraints(final CommandLine aCommandLine) throws ParseException {
		//  document or delete
	}

	/**
	 * Displays the help.
	 */
	public void printHelp() {

		iFormatter.printHelp(iCommand, iDescription, iOptions, "", true);
	}

	/**
	 * Prints description of the particular command.
	 */
	public void printDescription() {
		String lTmp = iCommand.toUpperCase();
		if (iCommand.length() < FIRST_COL_SIZE) {
			for (int lCounter = iCommand.length(); lCounter < FIRST_COL_SIZE; lCounter++) {
				lTmp += " ";
			}
		}

		System.out.println("\t" + lTmp + " - " + iDescription);
	}

	/**
	 * Displays command purpose.
	 * 
	 * @return 
	 */
	public String getPurpose() {
		return iDescription;
	}

	/**
	 * @return The options for the command.
	 */
	public final Options getOptions() {
		return iOptions;
	}

	/**
	 * Starts the command.
	 * 
	 * @param aCmdLine
	 *            The commandline to start.
	 * @return object object returned by command implementation object.
	 * @throws ParseException
	 *             If the command is null.
	 */
	public final Object start(final CommandLine aCmdLine) throws ParseException {
		if (iCmdOperation == null) {
			throw new ParseException("command \"" + iCommand + "\" is set to do nothing");
		}
		return iCmdOperation.run(this);
	}

	/**
	 * Returns command name.
	 * 
	 * @return command name string
	 */
	public String getCommand() {
		return iCommand;
	}

	/**
	 * Displays tool version.
	 * 
	 * @return 
	 * @throws ParseException
	 *             if command is not linked to any command set
	 */
	public String getNameVersionCopyRight() throws ParseException {
		if (iCmdSet == null) {
			throw new ParseException("command not linked to any command set!");
		}

		return iCmdSet.printNameVersionCopyRight();
	}

	/**
	 * Displays the name of all the commands.
	 */
	public void printAllCmds() {
		iCmdSet.printCmds();
	}

	/**
	 * Finds a particular command in the associated command set, given a command
	 * name.
	 * 
	 * @param aSoughtCommand
	 *            The command to find.
	 * @return sought command
	 */
	public CmdLine find(final String aSoughtCommand) {
		return iCmdSet.find(aSoughtCommand);
	}
}
