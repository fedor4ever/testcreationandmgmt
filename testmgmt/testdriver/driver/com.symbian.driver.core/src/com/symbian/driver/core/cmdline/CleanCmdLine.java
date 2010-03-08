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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.master.JobCounter;
import com.symbian.utils.Utils;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * @author EngineeringTools
 *
 */
public class CleanCmdLine extends CmdLine {
	
    /**
     * Standard constructor.
     */
    public CleanCmdLine() {
        super("clean", new CmdOperational() {
        	
        	public Object run(CmdLine aCmd) throws ParseException {
        		CommandLine lCommandLine = aCmd.getCommandLine();
        		
        		if (lCommandLine.hasOption("reallyclean")) {
        			cleanXMLRepository(false);
        			cleanSourceRepository();
        			cleanResultRepository();
        			cleanJobsRepository();
        			cleanStatWorkRepository();
        			cleanConfig();
        		} else if (lCommandLine.hasOption("x")) {
        			cleanXMLRepository(lCommandLine.getOptionValue("x").equalsIgnoreCase("true")
        					|| lCommandLine.getOptionValue("x").equalsIgnoreCase("on"));
        		} else if (lCommandLine.hasOption("repos")) {
        			cleanSourceRepository();
        		} else if (lCommandLine.hasOption("c")) {
        			cleanResultRepository();
        		} else if (lCommandLine.hasOption("clear")) {
        			cleanConfig();
        		} else if (lCommandLine.hasOption("jobs")) {
        			cleanJobsRepository();
        		} else if (lCommandLine.hasOption("runnumber")) {
        			cleanRunNumber();
        		} else if (lCommandLine.hasOption("stat")) {
        			cleanStatWorkRepository();
        		} else {
        			//fix defect 125633 by default, we will clean source repository and result repository
        			cleanSourceRepository();
        			cleanResultRepository();
        		}
				
				return aCmd;
        	}
        	
        }, "Cleans TestDriver artifacts.");
        
        addSwitch("reallyclean", false, "Cleans all the .driver files in the XML root, cleans the source, result, jobs and stat repositories and clears TestDriver Configuration.", false, false);
		addSwitch("x", true, "Clean the location of where TestDriver v1 saved its XML files." +
				" If TRUE will delete everything, if FALSE will only delete the .driver files", false, true);
		addSwitch("repos", false, "Clean the the repository for TestDriver (variable: ${repositoryroot}).", false, false);
		addSwitch("c", true, "Clean the results & logs of TestDriver (variable: ${resultroot}).", false, false);
		addSwitch("clear", false, "Clear the configuration backstore", false, false);
		addSwitch("jobs", false, "SERVER: Clean the remote jobs folder.", false, false);
		addSwitch("runnumber", false, "Reset the run number.", false, false);
		addSwitch("stat", false, "Clean the stat work directory.", false, false);
		}

	/**
	 * @param lCommandLine
	 * @throws ParseException
	 */
	private static void cleanXMLRepository(final boolean aClearEverything) throws ParseException {
		if (aClearEverything) {
			Utils.recusiveDelete(TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT));
		} else {
			File[] lDriverFiles = Utils.getFilesWithExtension(TDConfig.getInstance().getPreferenceFile(TDConfig.XML_ROOT), ".driver");
			for (int lIter = 0; lIter < lDriverFiles.length; lIter++) {
				lDriverFiles[lIter].delete();
			}
		}
	}

	/**
	 * 
	 */
	private static void cleanStatWorkRepository() {
		Utils.recusiveDelete(new File("c:/apps/stat/work"));
	}

	/**
	 * @throws ParseException
	 */
	private static void cleanRunNumber() throws ParseException {
		TDConfig.getInstance().setPreferenceInteger(TDConfig.RUN_NUMBER, 0);
	}

	/**
	 * @throws ParseException
	 */
	private static void cleanJobsRepository() throws ParseException {
		Utils.recusiveDelete(TDConfig.getInstance().getPreferenceFile(TDConfig.JOBS_FOLDER));
		//clear job counter.
		try {
			JobCounter jobCounter = new JobCounter();
			if (jobCounter.needRestore()) {
				LOGGER.info("Reset Job counter to 0.");
				jobCounter.reset();
				jobCounter.takeSnapshot();
			}
		} catch (Exception e) {
			LOGGER.warning("error to reset job count." + e.toString());
		}
	}

	/**
	 * @throws ParseException
	 */
	private static void cleanResultRepository() throws ParseException {
		Utils.recusiveDelete(TDConfig.getInstance().getPreferenceFile(TDConfig.RESULT_ROOT));
		cleanLogs();
	}
	
	/**
	 * Fix DEF130053. add code to clean testdriver logs at current dir 
	 */
	private static void cleanLogs() {
	    Pattern p = Pattern.compile("TestDriver\\d_\\d.log");

		File currentDir = new File(".");
		for (File file : currentDir.listFiles()) {
			if(p.matcher(file.getName()).matches()) {
				//it is testdriver log file, delete it
			    file.deleteOnExit();	
			}
		}
	}

	/**
	 * @throws ParseException
	 */
	private static void cleanSourceRepository() throws ParseException {
		Utils.recusiveDelete(TDConfig.getInstance().getPreferenceFile(TDConfig.REPOSITORY_ROOT));
	}

	/**
	 * 
	 */
	private static void cleanConfig() {
		try {
			TDConfig.getInstance().clearConfig();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Could not clean configuration.");
		}
	}
    
}
