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

package com.symbian.driver.core.controller.tasks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EList;

import com.symbian.driver.Build;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.PCVisitor;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.ILiterals;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.processors.PreProcessor;

/**
 * This builds Symbian code using the Symbian toolchain. Uses the prototype
 * pattern to create a valid build.
 * 
 * @author EngrineeringTools
 */
public class BuildTask implements IVisitor {

	/** The logger for the Visitor class. */
	protected final static Logger LOGGER = Logger.getLogger(BuildTask.class.getName());

	/** Generic settings/configuration. */
	private final TDConfig iConfig = TDConfig.getInstance();

	/** Generic settings/configuration. */
	private final StringBuffer iOuputBuffer = new StringBuffer();

	/** Exceptions Map. */
	private final Map<Exception, ESeverity> iExceptions = new HashMap<Exception, ESeverity>();

	private final Build iBuild;

	private final boolean iIsRBuild; 
	
	private boolean isTestBuild = false;

	/**
	 * @param aBuild
	 * @param aIsRBuild
	 */
	public BuildTask(Build aBuild, boolean aIsRBuild) {
		iBuild = aBuild;
		iIsRBuild = aIsRBuild;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.symbian.driver.core.extension.IVisitor#execute(com.symbian.driver.Task,
	 *      com.symbian.driver.core.processors.PreProcessor)
	 */
	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice) {
		try {
			EList lComponenetNameList = iBuild.getComponentName();
			File lGroupBuild = ModelUtils.checkPCPath(iBuild.getURI(), false)[0];
			// get the version of SBS
			int sbsVersion = getSBSVersion();
			String testbuild = iConfig.getPreference("testbuild");
			// check the value of testbuild option 
			if (testbuild.equals("auto")){
				// use the value of the testbuild tag in .driver file
				isTestBuild=iBuild.isTestBuild();
			}else if (testbuild.equals("true")){
				// test build , ignoring the value of the testbuild tag
				isTestBuild=true;
			}else if (testbuild.equals("false")){
				// build, ignoring the value of the testbuild tag
				isTestBuild=false;
			}else {
				isTestBuild=iBuild.isTestBuild();
			}
			
			if (!iIsRBuild) { 
				doBeforeBuild(lGroupBuild, isTestBuild, sbsVersion);
			}
			Pattern lExt = Pattern
			.compile("^.*\\.((exe)|(app)|(dll)|(ani)|(ctl)|(fep)|(mdl)|(csy)|(ldd)|(pdd)|(prt)|(ecomiic)|(plugin))$");
			// .exe, .app, .dll, .ani, .ctl, .fep, .mdl, .csy, .ldd, .pdd, .prt,
			// .ECOMIIC, .PLUGIN
			Vector<File> lTransferFileVector = null;
			// Run bldmake.bat
				
			if (lComponenetNameList.isEmpty()) {
				lTransferFileVector = doBuild(lGroupBuild, isTestBuild, null);
			} else {
				lTransferFileVector = new Vector<File>();
				for (Iterator lComponenetIterator = lComponenetNameList.iterator(); lComponenetIterator.hasNext();) {
					String lComponent = (String) lComponenetIterator.next();
					Vector<File> lTempTranfer = doBuild(lGroupBuild, isTestBuild, lComponent);
						lTransferFileVector.addAll(lTempTranfer);
				}
			}
			// Pickup ExecuteTransfer Set from abld -what
			for (File lTransferFile : lTransferFileVector) {
				String lName = lTransferFile.getName().toLowerCase();
				if (lExt.matcher(lName).matches()) {

					String lTransferFileLiteral = lTransferFile.getCanonicalPath();

					if (!lTransferFile.isFile()) {
						throw new IOException("The target file: " + lTransferFileLiteral + " could not be found.");
					}

					String lDestination = com.symbian.driver.core.environment.ILiterals.SYS_BIN
							+ lTransferFile.getName();
					if (lTransferFileLiteral.indexOf("\\z\\") >= 0) {
						lDestination = ILiterals.C + "\\"
								+ lTransferFileLiteral.substring(lTransferFileLiteral.indexOf("\\z\\") + 3);
					}

					// Check for eclipsing
					Task lTask = ModelUtils.isFileEclipsing(lDestination, (Task) iBuild.eContainer().eContainer());

					if (lTask != null) {
						String lErrorString = "The file " + lTransferFile
								+ " will cause eclipsing as it has already been built in parent node "
								+ lTask.getName();
						LOGGER.log(Level.WARNING, lErrorString);
						iExceptions.put(new IOException(lErrorString), ESeverity.WARNING);
						continue;
					}

					// Calculate Destination
					String sisRoot = null;
					try{
						sisRoot = TDConfig.getInstance().getPreference("sisroot");
					}catch(Exception e){
						e.printStackTrace();
					}
					String lSymbianPath = null;
					if (!sisRoot.equalsIgnoreCase("c:")){
						lSymbianPath = sisRoot+"\\sys\\bin\\"+lTransferFile.getName();
					}else {
						lSymbianPath = com.symbian.driver.core.environment.ILiterals.SYS_BIN
						+ lTransferFile.getName();
					}
					
					if (lTransferFileLiteral.contains("\\z\\")) {
						lSymbianPath = com.symbian.driver.core.environment.ILiterals.C + "\\"
								+ lTransferFileLiteral.substring(lTransferFileLiteral.indexOf("z\\") + 2);
					} else if (lTransferFileLiteral.contains("\\data\\z\\")) {
						lSymbianPath = com.symbian.driver.core.environment.ILiterals.C + "\\"
								+ lTransferFileLiteral.substring(lTransferFileLiteral.indexOf("z\\") + 2);
					} else if (lTransferFileLiteral.contains("\\data\\c\\")) {
						lSymbianPath = com.symbian.driver.core.environment.ILiterals.C + "\\"
								+ lTransferFileLiteral.substring(lTransferFileLiteral.indexOf("c\\") + 2);
					}

					// Add file to Task
					boolean lAddToTaskSet = ((ExecuteTransferSet) aTask.getTransferSet()).add(new ExecuteTransfer(
							lTransferFile, lSymbianPath));

					if (!lAddToTaskSet) {
						LOGGER.warning("Could not add file to SIS package: " + lTransferFile.getAbsolutePath());
					}
				}
			}
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "IO Exception during build: " + iBuild.getURI(), lIOException);
			iExceptions.put(lIOException, ESeverity.ERROR);
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER
					.log(Level.WARNING, "Timeout exceeded, during build: " + iBuild.getURI(),
							lTimeLimitExceededException);
			iExceptions.put(lTimeLimitExceededException, ESeverity.ERROR);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not get configuration settings for build: " + iBuild.getURI(),
					lParseException);
			iExceptions.put(lParseException, ESeverity.ERROR);
		}

		return iExceptions;
	}

	/**
	 * Runs operations nessary before a build. This includes the following
	 * tasks:
	 * 
	 * <ul>
	 * <li>Checks the group directory for a bld.inf.</li>
	 * <li>Runs bldmake if configured</li>
	 * <li>Runs bldmake clean and abld clean if configured</li>
	 * </ul>
	 * 
	 * @param aGroupDir
	 *            The group directory containing the bld.inf file.
	 * @param aBuildType
	 *            If the build is a test or regular build.
	 * @param sbsVersion
	 * 			  The version of sbs version: v1 or v2
	 * @throws IOException
	 *             If the group directory path doesn't contain a bld.inf file.
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 */
	private void doBeforeBuild(File aGroupDir, final boolean aBuildType, int sbsVersion) throws IOException, ParseException,
			TimeLimitExceededException {
		// Check for Bld.inf
		if (!new File(aGroupDir + File.separator + com.symbian.driver.core.environment.ILiterals.BLD_INF).exists()) {
			throw new IOException("The group directory \"" + aGroupDir.getAbsolutePath()
					+ "\" does not contain a bld.inf file.");
		}
		if (sbsVersion ==1){
			// Do Bldmake.bat
			if (iConfig.isPreference(TDConfig.BLDMAKE)
					|| !new File(aGroupDir + File.separator + com.symbian.driver.core.environment.ILiterals.ABLD_BAT)
							.isFile()) {
				// Clean Bldmake.bat
				if (!PCVisitor.isRBuild() && iConfig.isPreference(TDConfig.CLEAN)) {
					ExecuteOnHost lClean = new ExecuteOnHost(aGroupDir,
							com.symbian.driver.core.environment.ILiterals.BLDMAKE_BAT
									+ com.symbian.driver.core.environment.ILiterals.CLEAN);
					lClean.doTask(true, TimeOut.NO_TIMEOUT, true);
					iOuputBuffer.append(lClean.getOutput());
				}
				// Bldmake.bat bldfiles
				ExecuteOnHost lBldMake = new ExecuteOnHost(aGroupDir,
						com.symbian.driver.core.environment.ILiterals.BLDMAKE_BAT
								+ com.symbian.driver.core.environment.ILiterals.BLDFILES);
				lBldMake.doTask(true, TimeOut.NO_TIMEOUT, true);
				iOuputBuffer.append(lBldMake.getOutput());
				// Really Clean Abld.bat
				if (!PCVisitor.isRBuild()
						&& new File(aGroupDir.getCanonicalPath() + File.separator
								+ com.symbian.driver.core.environment.ILiterals.ABLD_BAT).isFile()
						&& iConfig.isPreference(TDConfig.CLEAN)) {

					ExecuteOnHost lAbldReallyClean = new ExecuteOnHost(aGroupDir,
							com.symbian.driver.core.environment.ILiterals.ABLD_BAT
									+ (aBuildType ? com.symbian.driver.core.environment.ILiterals.TEST : "")
									+ com.symbian.driver.core.environment.ILiterals.REALLYCLEAN + " "
									+ iConfig.getPreference(TDConfig.PLATFORM) + " " + iConfig.getPreference(TDConfig.VARIANT));
					lAbldReallyClean.doTask(true, TimeOut.NO_TIMEOUT, true);
					iOuputBuffer.append(lAbldReallyClean.getOutput());
				}
			}
		}else{
			// SBS v2 Really Clean
			LOGGER.log(Level.INFO, "SBS v2 really Clean.");
			if(!PCVisitor.isRBuild()&& new File(aGroupDir.getAbsoluteFile() + File.separator
					+ com.symbian.driver.core.environment.ILiterals.SBS_BAT).isFile()
					&& iConfig.isPreference(TDConfig.CLEAN)){
				String lTest =(aBuildType ? com.symbian.driver.core.environment.ILiterals.TEST : "");
				//sbs -c winscw_udeb.test reallyclean
				ExecuteOnHost lSbsReallyClean = new ExecuteOnHost(aGroupDir,
						com.symbian.driver.core.environment.ILiterals.SBS_BAT
						+ "-c " + iConfig.getPreference(TDConfig.PLATFORM)+"_"
						+ iConfig.getPreference(TDConfig.VARIANT)+"."+lTest +" "
						+com.symbian.driver.core.environment.ILiterals.REALLYCLEAN);
				lSbsReallyClean.doTask(true, TimeOut.NO_TIMEOUT, true);
				iOuputBuffer.append(lSbsReallyClean.getOutput());
			}
		}		
	}

	/**
	 * Builds the Symbian code.
	 * 
	 * @param aGroupDir
	 *            The group directory containing the bld.inf file.
	 * @param aBuildType
	 *            If <code>true</code> then will run "abld test build", else
	 *            if <code>false</code> then it will run "abld build".
	 * @param aComponent
	 *            The mmp target.
	 * @return The vector of files to tranfer.
	 * @throws IOException
	 *             If reading the output of the build commands failed.
	 * @throws InterruptedException
	 *             If the build commands thread was interupted
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 */
	private Vector<File> doBuild(final File aGroupDir, final boolean aBuildType, final String aComponent)
			throws IOException, ParseException, TimeLimitExceededException {
		int sbsVersion = this.getSBSVersion();
		String lTest =(aBuildType ? com.symbian.driver.core.environment.ILiterals.TEST : "");
		String lBuildCommand="";
		if (sbsVersion == 1){
			lBuildCommand = com.symbian.driver.core.environment.ILiterals.ABLD_BAT + lTest
				+ com.symbian.driver.core.environment.ILiterals.BUILD + iConfig.getPreference(TDConfig.PLATFORM) + " "
				+ iConfig.getPreference(TDConfig.VARIANT) + (aComponent != null ? " " + aComponent : "");
			// excute the build command. 
			ExecuteOnHost lAbld = new ExecuteOnHost(aGroupDir, lBuildCommand);
			if (!PCVisitor.isRBuild()) {
				lAbld.doTask(true, TimeOut.NO_TIMEOUT, true);
				iOuputBuffer.append(lAbld.getOutput());
			} else if (!new File(aGroupDir, com.symbian.driver.core.environment.ILiterals.ABLD_BAT).isFile()) {
				doBeforeBuild(aGroupDir, aBuildType,sbsVersion);
				ExecuteOnHost lExport = new ExecuteOnHost(aGroupDir, com.symbian.driver.core.environment.ILiterals.ABLD_BAT
						+ lTest + com.symbian.driver.core.environment.ILiterals.EXPORT);
				ExecuteOnHost lMakeFile = new ExecuteOnHost(aGroupDir,
						com.symbian.driver.core.environment.ILiterals.ABLD_BAT + lTest
								+ com.symbian.driver.core.environment.ILiterals.MAKEFILE);
				lExport.doTask(true, TimeOut.NO_TIMEOUT, true);
				lMakeFile.doTask(true, TimeOut.NO_TIMEOUT, true);
				iOuputBuffer.append(lExport.getOutput() + lMakeFile.getOutput());
			}
			Vector<File> lTransferFileVector = findTargets(aGroupDir, lBuildCommand);
			if (PCVisitor.isRBuild() && lTransferFileVector.size() == 0) {
				LOGGER.fine("Could not find any targets. Attempting to rebuild.");
				lAbld.doTask(true, TimeOut.NO_TIMEOUT, true);
				iOuputBuffer.append(lAbld.getOutput());
				lTransferFileVector = findTargets(aGroupDir, lBuildCommand);
				
			}
			return lTransferFileVector;
		}else{
			// sbs v2
			lBuildCommand = com.symbian.driver.core.environment.ILiterals.SBS_BAT
				+ "-c " + iConfig.getPreference(TDConfig.PLATFORM)+"_"
				+ iConfig.getPreference(TDConfig.VARIANT)+"."+lTest + (aComponent != null ? " " +"-p "+ aComponent+".mmp" : "");
			// execute the build command. 
			ExecuteOnHost lSbs = new ExecuteOnHost(aGroupDir,lBuildCommand);
			lSbs.doTask(true, TimeOut.NO_TIMEOUT, true);
			iOuputBuffer.append(lSbs.getOutput());
			Vector<File> lTransferFileVector = findTargets(aGroupDir, lBuildCommand);
			return lTransferFileVector;
		}
	}

	/**
	 * Finds the targets that were built.
	 * 
	 * This uses the "abld -what" command to find the appropriate targets.
	 * 
	 * @param aGroupDir
	 *            The group directory where the bld.inf file is located.
	 * @param lBuildCommand
	 *            The build command used when creating the targets.
	 * @return The vector of targets created by a build process.
	 * @throws IOException
	 *             If the output of "abld -what" cannot be read.
	 * @throws InterruptedException
	 *             If the process of "abld -what" is interrupted.
	 * @throws ParseException
	 * @throws TimeLimitExceededException
	 */
	private Vector<File> findTargets(final File aGroupDir, String lBuildCommand) throws IOException, ParseException,
			TimeLimitExceededException {
		LOGGER.log(Level.INFO,"Finding targets to be transferred.");
		int sbsVersion = getSBSVersion();
		if (sbsVersion ==1){
			lBuildCommand = lBuildCommand + com.symbian.driver.core.environment.ILiterals.WHAT;
		}else{
			lBuildCommand = lBuildCommand +com.symbian.driver.core.environment.ILiterals.SBS_WHAT;
		}
		ExecuteOnHost lWhat = new ExecuteOnHost(aGroupDir, lBuildCommand);
		LOGGER.fine("Trying to find targets in group: " + aGroupDir + ", using command: " + lBuildCommand);

		lWhat.doTask(true, TimeOut.FIVE_MIN, true);
		
		Vector<File> lTransferFileVector = new Vector<File>();

		String lWhatResult = lWhat.getOutput();
		/*
		 * This output is compliant with the symbian build system The file paths
		 * are relative to the EPOCROOT which is relative to source drive e.g.
		 * \aEpocRoot\epoc32\release\amrv5\\urel\a.exe Build system
		 * EPOCROOT=\aEpocRoot\ TD EPOCROOT=d:\aEpocRoot
		 * 
		 * The files should then be computed as
		 * d:\aEpocRoot\epoc32\release\amrv5\\urel\a.exe
		 * 
		 */
		
		if(this.getSBSVersion()!=1){
			lWhatResult = lWhatResult.substring(2);
		}
		
		if (lWhatResult != null && !lWhatResult.equalsIgnoreCase("")) {

			String lFindPath = ModelUtils.getEpoc32RelPlatVar().toLowerCase();
			LOGGER.fine("Trying to find files under: " + lFindPath);

			for (String lLine : lWhatResult.toLowerCase().split("\\n|\\r")) {
				LOGGER.finer("Checking line: " + lLine);
				if ((lLine.indexOf(lFindPath) != -1 && !lLine.endsWith(".map"))) {
					File lTransferFile = null;
					if(getSBSVersion()==1){
						lTransferFile = new File(iConfig.getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath()
								.substring(0, 2), lLine.toLowerCase().trim());
					}else{ 
						lTransferFile = new File(iConfig.getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath()
								.substring(0, 2), lLine.toLowerCase().trim().substring(2));
					}
					if (lTransferFile.isFile()) {
						lTransferFileVector.add(lTransferFile);
						LOGGER.fine("Target found: " + lTransferFile.getAbsolutePath());
					} else {
						iExceptions.put(new IOException("The file: " + lTransferFile.getAbsolutePath()
								+ ", does not exist. Ensure that your build is correct."), ESeverity.WARNING);
						LOGGER.warning("The file: " + lTransferFile
								+ ", does not exist. Ensure that your build is correct.");
					}
				}
			}
		} else {
			LOGGER.severe("No ouput detected with abld build -what.");
		}

		if (lTransferFileVector == null || lTransferFileVector.size() == 0) {
			LOGGER.warning("Did not find any exports in: " + aGroupDir + ", using command: " + lBuildCommand);
		}

		return lTransferFileVector;
	}

	/**
	 * @return
	 */
	public Vector<String> parseWarnings() {
		Vector<String> lBuildWarnings = new Vector<String>();
		if (iOuputBuffer != null) {
			for (String lLine : iOuputBuffer.toString().toLowerCase().split("\\n|\\r")) {
				if (lLine.indexOf("warning:") != -1) {
					lBuildWarnings.add(lLine);
				}
			}
		}

		return lBuildWarnings;
	}
	
	private int getSBSVersion(){
		try{
			String sbsVersionStr = this.iConfig.getPreference("sbs").toLowerCase();
			String version = sbsVersionStr.substring(1);
			int sbsVersion = Integer.parseInt(version);
			return sbsVersion;
		}catch(ParseException e){
			e.printStackTrace();
			return -1;
		}
	}
}
