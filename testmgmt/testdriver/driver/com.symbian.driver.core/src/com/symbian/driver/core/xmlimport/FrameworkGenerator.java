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



package com.symbian.driver.core.xmlimport;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.ExecuteOnPC;
import com.symbian.driver.ExecuteOnSymbian;
import com.symbian.driver.Reference;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.StatCommand;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.utils.Utils;

/**
 * @author EngineeringTools
 */
public class FrameworkGenerator {
	
	///////////////////////////////////////////////////////////////////////
	// Variable and Constans
	///////////////////////////////////////////////////////////////////////

	/** The Import directory. */
	private static File sImportDir = null;
	
	/** The Import file. */
	private File iImportFile = null;

	private final DriverFactory iDriverFactory = DriverFactory.eINSTANCE;

	/** XML File marker. */
	public static final String XML_FILE_MARKER = "xml";

	/** The orignal XML file marker. */
	public static final String ORIGINAL_XML_FILE_MARKER = "original-xml";

	/** Warning message if the file is ignored. */
	public static final String ERR_FILE_IGNORED = " is ignored currently. " + "\n You must take action to correct then try to load again.";

	/** Literal for the resultroot variable. */
	private final static String RESULT_ROOT = "${resultroot}";
	
	/** The logger for the Visitor class. */
	protected final static Logger LOGGER = Logger.getLogger(FrameworkGenerator.class.getName());

	///////////////////////////////////////////////////////////////////////
	// Constructor and Generator
	///////////////////////////////////////////////////////////////////////
	
	/**
	 * @param aImportDir
	 *            The import directory of XML files.
	 * @throws IOException
	 *             If the XML root directory is invalid.
	 */
	public FrameworkGenerator(final File aImportDir) throws IOException {
		if (aImportDir.isDirectory()) {
			sImportDir = aImportDir;
			
		} else if (aImportDir.isFile()) {
			if (sImportDir == null) {
				sImportDir = aImportDir.getParentFile();
			}
			iImportFile = aImportDir;
		} else {
			throw new IOException("Could not open the XML directory/file: " + aImportDir);
		}
	}

	/**
	 * Build test framework from its root.
	 * 
	 * @return The root task for the EMF tree.
	 */
	public Task buildEmfModel() {
		// Create the root task
		Task lBaseTask = iDriverFactory.createTask();
		lBaseTask.setName(sImportDir.getName());
		lBaseTask.setTimeout(TimeOut.DEFAULT);

		// make a list of all the XML files within the directory
		File[] lRootFiles = Utils.getFilesWithExtension(sImportDir, ".xml");
		
		// Iterate through the XML files
		if (iImportFile != null) {
			
			handleXML(iImportFile.getAbsolutePath(), false, lBaseTask);
			
		} else {
			
			for (int lCounter = 0; lCounter < lRootFiles.length; lCounter++) {
				String lCurrentFile = sImportDir + File.separator + lRootFiles[lCounter].getName();
	
				Task lTask = handleXML(lCurrentFile, true, lBaseTask);
				lBaseTask.getTask().add(lTask);
			}
			
		}

		return lBaseTask;
	}
	
	/**
	 * @param aXmlFilePath
	 *            of the XML file associated to the suite
	 * @param aIsRoot
	 *            if this suite is the root of the tree
	 * @param aBaseTask
	 * @return TstComponent
	 * @throws JDOMException
	 *             If the parsing of the file fails.
	 * @throws IOException
	 */
	private Task handleXML(String aXmlFilePath, final boolean aIsRoot, final Task aBaseTask) {
		// Build the JDOM model of the XML file
		Document lDoc = null;
		SAXBuilder lBuilder = new SAXBuilder(true);

		// Reolve the DTD's
		ImportUtils.dtdResolver(lBuilder);

		try {
			lDoc = lBuilder.build(aXmlFilePath);
		} catch (JDOMException lJDOMException) {
			
			try {
				// Fix the XML files according to the DTD's
				aXmlFilePath = ImportUtils.fixXmlFile(aXmlFilePath);
				lDoc = lBuilder.build(aXmlFilePath);
			} catch (JDOMException lException) {
				LOGGER.log(Level.SEVERE, aXmlFilePath + " is not well-formed:" + lException.getMessage() + " Please correct manually.", lException);
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, aXmlFilePath + " caused an I/O error while importing.", lIOException);
			}

		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, aXmlFilePath + " caused an I/O error while importing.", lIOException);
		}

		// Insert into the EMF model
		if (lDoc != null) {
			
			//////////////////////////////////////////////
			// Header Information
			Element lRootElement = lDoc.getRootElement();
			String lRootElementName = lRootElement.getName();

			Task lTask = iDriverFactory.createTask();

			// name
			if (lRootElement.getChild(Tags.NAME) != null) {
				lTask.setName(lRootElement.getChildTextTrim(Tags.NAME));
			}

			// timeout
			if (lRootElement.getChild(Tags.TIMEOUT) != null) {
				lTask.setTimeout(Integer.parseInt(lRootElement.getChildTextTrim(Tags.TIMEOUT)));
			}

			if (aIsRoot && !lRootElementName.equals(Tags.TEST_SUITE)) {
				LOGGER.warning("The XML root must contain only suite.");
			} else {

				//////////////////////////////////////////////
				// Iterate through all the old XML's
				if (lRootElementName.equals(Tags.TEST_SUITE)) {
					
					// Handle the TEF Suite XML
					handleXMLSuite(aXmlFilePath, lRootElement, lTask);
					
				} else if (lRootElementName.equalsIgnoreCase(Tags.TEST_EXECUTE_SERVER)) {
					
					// Handle the TEF Serve XML
					handleXMLTefServer(lRootElement, lTask);
					
				} else if (lRootElementName.equalsIgnoreCase(Tags.TEST_EXECUTE_TEST)) {
					
					// Handle the TEF Test XML
					handleXMLTefTest(aXmlFilePath, lRootElement, lTask);
					
				} else if (lRootElementName.equalsIgnoreCase(Tags.COMMAND_LINE_TEST)) {
					
					// Handle the Command Line Test XML
					handleXMLCommandLineTest(lRootElement, lTask);
					
				} else if (lRootElementName.equalsIgnoreCase(Tags.R_TEST)) {
					
					// Handle the RTest XML
					handleXMLRTest(lRootElement, lTask);
					
				} else if (lRootElementName.equalsIgnoreCase(Tags.R_TEST_ROM)) {
					
					// Handle the RTest ROM XML
					handleXMLRTestRom(lRootElement, lTask);
					
				} else {
					
					LOGGER.log(Level.SEVERE, "XML doesn't conform to the DTD's.");
					
				}
	
				return lTask;
			}
		}
		
		LOGGER.warning("\n " + aXmlFilePath + ERR_FILE_IGNORED);
		
		return null;
	}
	
	///////////////////////////////////////////////////////////////////////
	// XML Handlers
	///////////////////////////////////////////////////////////////////////

	/**
	 * @param aXmlFilePath
	 * @param aBaseTask
	 * @param aRootElement
	 * @param aTask
	 * @throws JDOMException
	 */
	private void handleXMLSuite(String aXmlFilePath, Element aRootElement, Task aTask) {
		LOGGER.fine("Test Suite: " + aRootElement.getChildTextTrim(Tags.NAME));

		if (aRootElement.getChild(Tags.DEPENDENCIES_LIST) != null) {
			// dependencies: data
			createDepData(aTask, aRootElement.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DATA_DEPENDENCIES_LIST));

			// dependencies: build
			ExecuteOnPC lExecuteOnPC = iDriverFactory.createExecuteOnPC();
			createDepBuild(lExecuteOnPC, aTask, aRootElement, true);
		}

		// server & test list
		if (aRootElement.getChild(Tags.TEST_ITEMS_LIST) != null) {
			Element lTestItems = aRootElement.getChild(Tags.TEST_ITEMS_LIST);
			
			// Suite, Tests and Reference lists
			for (Iterator lIterator = lTestItems.getChildren().iterator(); lIterator.hasNext();) {
				Element lChildItem = (Element) lIterator.next();
				
				if (lChildItem.getName().equalsIgnoreCase(Tags.SUITE)) {
					handleXMLSuiteTest(aTask, lChildItem, aXmlFilePath);
				} else if (lChildItem.getName().equalsIgnoreCase(Tags.TEST)) {
					handleXMLSuiteTest(aTask, lChildItem, aXmlFilePath);
				} else if (lChildItem.getName().equalsIgnoreCase(Tags.SUITE_REF)) {
					createReference(aTask, lChildItem);
				} else if (lChildItem.getName().equalsIgnoreCase(Tags.TEST_REF)) {
					createReference(aTask, lChildItem);
				}
			}
		}
	}

	/**
	 * @param aXmlFilePath
	 * @param aElement
	 * @param aTask
	 * @throws JDOMException
	 * @throws IOException
	 */
	private void handleXMLTefTestServers(String aXmlFilePath, Element aElement, Task aTask) {
		if (aElement.getChild(Tags.TEST_SERVERS) != null) {
			String lDir = new File(aXmlFilePath).getParent();
			// Iterate through all the servers
			for (Iterator lIterator = aElement.getChild(Tags.TEST_SERVERS).getChildren(Tags.SERVER).iterator();
					lIterator.hasNext();) {
				String lServerName = ((Element) lIterator.next()).getTextTrim();
				
				try {
					// Get the next XML Server file
					Task lServerTask = handleXML(ImportUtils.getNextFile(lDir, lServerName, true), false, aTask);
				
					if (lServerTask != null) {
						if (lServerTask.getExecuteOnPC() != null) {
							if (aTask.getExecuteOnPC().isEmpty()) {
								
								// 1 Server to be added
								aTask.getExecuteOnPC().addAll(lServerTask.getExecuteOnPC());
								
							} else {
								
								// 2 -> n Server to be added
								if (lServerTask.getExecuteOnPC().size() != 1) {
									throw new IOException("Could not parse the ExecuteOnPC correctly.");
								}
								
								// Iterate through all the builds and place in one <ExecuteOnHost>
								final ExecuteOnPC lNewExecuteOnPC = (ExecuteOnPC) aTask.getExecuteOnPC().get(0);
								final EList lNewBuildList = lNewExecuteOnPC.getBuild();
								final ExecuteOnPC lNextServerExecuteOnPC = (ExecuteOnPC) lServerTask.getExecuteOnPC().get(0);
								final EList lNextServerBuildList = lNextServerExecuteOnPC.getBuild();
								
								// Add all CmdPc's
								lNewExecuteOnPC.getCmd().addAll(lNextServerExecuteOnPC.getCmd());
				
								// Add all Builds
								for (Iterator lServerBuildIter = lNextServerBuildList.iterator(); lServerBuildIter.hasNext();) {
									
									Build lServerBuild = (Build) lServerBuildIter.next();
									final Build lDuplicateBuild = lNewExecuteOnPC.findDuplicateBuildByURI(lServerBuild.getURI());
										
									if (lDuplicateBuild == null) {
										lNewBuildList.add(lServerBuild.clone());
									} else {
										// Add componenet by component
										for (Iterator lServerComponentIter = lServerBuild.getComponentName().iterator(); lServerComponentIter.hasNext();) {
											String lServerComponent = (String) lServerComponentIter.next();
											
											if (!lDuplicateBuild.getComponentName().contains(lServerComponent)) {
												lDuplicateBuild.getComponentName().add(lServerComponent.toString());
											}
										}
									}
								}
							}
						}
						
						// Add all the child nodes to the root node
						if (lServerTask.getExecuteOnSymbian() != null) {
							aTask.getExecuteOnSymbian().addAll(lServerTask.getExecuteOnSymbian());
						}
						if (lServerTask.getTransferToSymbian() != null) {
							aTask.getTransferToSymbian().addAll(lServerTask.getTransferToSymbian());
						}
						if (lServerTask.getRetrieveFromSymbian() != null) {
							aTask.getRetrieveFromSymbian().addAll(lServerTask.getRetrieveFromSymbian());
						}
						if (lServerTask.getReference() != null) {
							aTask.getReference().addAll(lServerTask.getReference());
						}
					}
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "Could not find the Server: " + lServerName + ", from Suite XML file: " + aXmlFilePath, lIOException);
				}
			}
		}
	}

	/**
	 * @param aTask
	 * @param aTestItem
	 * @param aXmlFilePath
	 * @param aBaseTask
	 */
	private void handleXMLSuiteTest(Task aTask, Element aTestItem, String aXmlFilePath) {
		String lSuiteTestName = aTestItem.getTextTrim();
		
		Task lAddedTask = handleXML(ImportUtils.getNextFile(aXmlFilePath, lSuiteTestName, false),
				false, aTask);
		
		if (lAddedTask != null) {
			aTask.getTask().add(lAddedTask);
		}
	}

	/**
	 * @param aXmlFilePath xml file path of 
	 * @param lRoot
	 * @param lTask
	 */
	private void handleXMLTefTest(String aXmlFilePath, final Element lRoot, final Task lTask) {
		LOGGER.fine("Test Execute Test: " + lRoot.getChildTextTrim(Tags.NAME));
	
		// testServers: parse <testserver>.xml file under "testexecuteservers" directory and add
		// its contents into lTask
		handleXMLTefTestServers(aXmlFilePath, lRoot, lTask);

		// dependency: build
		ExecuteOnPC lExecuteOnPC = iDriverFactory.createExecuteOnPC();
		createDepBuild(lExecuteOnPC, lTask, lRoot, true);
	
		// dependency: data
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			List lChildren = lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DATA_DEPENDENCIES_LIST);
			if (lChildren != null) {
				createDepData(lTask, lChildren);
			}
			RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
			for (Iterator lRetrieveIter = lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.RETRIEVE_DEPENDENCIES_LIST).iterator(); lRetrieveIter.hasNext();) {
				Element lRetrieveElement = (Element) lRetrieveIter.next();
				createRetrieveFromSymbian(lRetrieveFromSymbian, lRetrieveElement.getChildTextTrim(Tags.DEVICE_PATH), lRetrieveElement
						.getChildTextTrim(Tags.HOST_PATH_ITEM));
			}
			
			if (lRetrieveFromSymbian.getTransfer().size() != 0) {
				lTask.getRetrieveFromSymbian().add(lRetrieveFromSymbian);
			}
		}
	
		// testScripts
		if (lRoot.getChildren(Tags.TEST_SCRIPTS_LIST) != null) {
			ExecuteOnSymbian lExecuteOnSymbian = iDriverFactory.createExecuteOnSymbian();
			for (Iterator lTestScriptIter = lRoot.getChildren(Tags.TEST_SCRIPTS_LIST).iterator(); lTestScriptIter.hasNext();) {
				Element lScriptElement = (Element) lTestScriptIter.next();
	
				if (lScriptElement.getChild(Tags.SCRIPT) != null) {
					TestExecuteScript lTestExecuteScript = iDriverFactory.createTestExecuteScript();
					lTestExecuteScript.setSymbianPath(lScriptElement.getChild(Tags.SCRIPT).getChildTextTrim(Tags.DEVICE_PATH));
					lTestExecuteScript.setPCPath(ImportUtils.createValidPath(lScriptElement.getChild(Tags.SCRIPT).getChildTextTrim(Tags.HOST_PATH_ITEM)));
		
					lExecuteOnSymbian.getTestExecuteScript().add(lTestExecuteScript);
				}
			}
			lTask.getExecuteOnSymbian().add(lExecuteOnSymbian);
		}
	
		// stepLogs
		if (lRoot.getChildren(Tags.STEP_LOGS_LIST) != null) {
			RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
			for (Iterator lStepLogsIter = lRoot.getChildren(Tags.STEP_LOGS_LIST).iterator(); lStepLogsIter.hasNext();) {
				createRetrieveFromSymbian(lRetrieveFromSymbian, ((Element) lStepLogsIter.next()).getChildTextTrim(Tags.LOG), RESULT_ROOT);
			}
			
			if (lRetrieveFromSymbian.getTransfer().size() != 0) {
				lTask.getRetrieveFromSymbian().add(lRetrieveFromSymbian);
			}
		}	
	}

	/**
	 * @param lRoot
	 * @param lTask
	 */
	private void handleXMLTefServer(final Element lRoot, final Task lTask) {
		LOGGER.fine("Test Execute Server: " + lRoot.getChildTextTrim(Tags.NAME));
	
		ExecuteOnPC lServerExecute = iDriverFactory.createExecuteOnPC();
	
		// dependencies: build
		createDepBuild(lServerExecute, lTask, lRoot, true);
	
		// iniFile
		if (lRoot.getChild(Tags.INI_LIST) != null) {
			createDepData(lTask, lRoot.getChild(Tags.INI_LIST).getChildren(Tags.INI_ITEM));
		}
	
		// configFile
		if (lRoot.getChild(Tags.CONFIG_FILE) != null) {
			TransferToSymbian lTransferToSymbian = iDriverFactory.createTransferToSymbian();
			lTask.getTransferToSymbian().add(lTransferToSymbian);
			createTransferToSymbian(lTransferToSymbian, 
					lRoot.getChild(Tags.CONFIG_FILE).getChildTextTrim(Tags.DEVICE_PATH), 
					lRoot.getChild(Tags.CONFIG_FILE).getChildTextTrim(Tags.HOST_PATH_ITEM));
		}
	
		// resourceItems
		if (lRoot.getChild(Tags.RESOURCE_ITEMS_LIST) != null) {
			createDepData(lTask, lRoot.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.RESOURCE));
			createDepData(lTask, lRoot.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.AIF));
		}
	
		// dependencies: data
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			createDepData(lTask, lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DATA_DEPENDENCIES_LIST));
		}
	
		// mmpFile, bldInfFile
		lServerExecute.getBuild().add(
				createBuild(true, true, lRoot.getChildTextTrim(Tags.BLD_INF_ITEM), lRoot.getChildTextTrim(Tags.MMP_FILE_ITEM), lRoot
						.getChildTextTrim(Tags.HOST_PATH_ITEM), lRoot.getChildTextTrim(Tags.DEVICE_PATH)));
		lTask.getExecuteOnPC().add(lServerExecute);
	}

	/**
	 * @param lRoot
	 * @param lTask
	 */
	private void handleXMLCommandLineTest(final Element lRoot, final Task lTask) {
		LOGGER.fine("Commandline test: " + lRoot.getChildTextTrim(Tags.NAME));
	
		// Before Test run
		ExecuteOnPC lExecuteOnPC = iDriverFactory.createExecuteOnPC();
		if (lRoot.getChild(Tags.BEFORE_TEST_RUN) != null) {
			createExecuteOnPc(lExecuteOnPC, lTask, lRoot.getChildTextTrim(Tags.BEFORE_TEST_RUN));
		}
		// Copy From
		if (lRoot.getChild(Tags.COPY_FROM) != null) {
			createExecuteOnPc(lExecuteOnPC, lTask, "cp " + lRoot.getChild(Tags.COPY_FROM).getAttributeValue(Tags.SRC) + " "
					+ lRoot.getChild(Tags.COPY_FROM).getAttributeValue(Tags.DST));
		}
	
		// dependencies: build
		createDepBuild(lExecuteOnPC, lTask, lRoot, false);
	
		// dependencies: data
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			TransferToSymbian lTransferToSymbian = iDriverFactory.createTransferToSymbian();
			lTask.getTransferToSymbian().add(lTransferToSymbian);
			
			for (Iterator lTransferIter = lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DATA_DEPENDENCIES_LIST).iterator(); lTransferIter.hasNext();) {
				Element lTransferElement = (Element) lTransferIter.next();
				createTransferToSymbian(lTransferToSymbian, lTransferElement.getChildTextTrim(Tags.DEVICE_PATH), lTransferElement.getChildTextTrim(Tags.HOST_PATH_ITEM));
			}
		}
		
		// commandLine
		createExecuteOnSymbian(lTask, lRoot.getChildTextTrim(Tags.COMMAND_LINE), StatCommand.RUN);
	
		// Log file
		RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
		if (lRoot.getChildTextTrim(Tags.LOGFILE) != null ) {
			createRetrieveFromSymbian(lRetrieveFromSymbian, lRoot.getChildTextTrim(Tags.LOGFILE), RESULT_ROOT);
		}
		
		// dependencies: delete
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			for (Iterator lDeleteIter = lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DELETE_DEPENDENCIES_LIST).iterator(); lDeleteIter.hasNext();) {
				createExecuteOnSymbian(lTask, ((Element) lDeleteIter.next()).getChildTextTrim(Tags.DEVICE_PATH), StatCommand.DELETE);
			}
		}
	
		// dependencies: retrieve
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			for (Iterator lRetrieveIter = lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.RETRIEVE_DEPENDENCIES_LIST).iterator(); lRetrieveIter.hasNext();) {
				Element lRetrieveElement = (Element) lRetrieveIter.next();
				createRetrieveFromSymbian(lRetrieveFromSymbian, lRetrieveElement.getChildTextTrim(Tags.DEVICE_PATH), lRetrieveElement
						.getChildTextTrim(Tags.HOST_PATH_ITEM));
			}
		}
	
		// After Test Run
		if (lRoot.getChild(Tags.AFTER_TEST_RUN) != null) {
			ExecuteOnPC lExecuteOnPCAfter = iDriverFactory.createExecuteOnPC();
			createExecuteOnPc(lExecuteOnPCAfter, lTask, lRoot.getChildTextTrim(Tags.AFTER_TEST_RUN));
		}
		
		if (lRetrieveFromSymbian.getTransfer() != null
				&& lRetrieveFromSymbian.getTransfer().size() != 0) {
			lTask.getRetrieveFromSymbian().add(lRetrieveFromSymbian);
		}
	}

	/**
	 * @param lRoot
	 * @param lTask
	 */
	private void handleXMLRTest(final Element lRoot, final Task lTask) {
		LOGGER.fine("RTest: " + lRoot.getChildTextTrim(Tags.NAME));
	
		// resourceItems
		if (lRoot.getChild(Tags.RESOURCE_ITEMS_LIST) != null) {			
			createDepData(lTask, lRoot.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.RESOURCE));
			createDepData(lTask, lRoot.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.AIF));
		}
	
		// dependencies: data
		if (lRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			createDepData(lTask, lRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.DATA_DEPENDENCIES_LIST));
		}
	
		// bldinf, mmpfile, devicePath
		ExecuteOnPC lExecuteOnPC = iDriverFactory.createExecuteOnPC();
		lExecuteOnPC.getBuild().add(
				createBuild(true, false, lRoot.getChildTextTrim(Tags.BLD_INF_ITEM), "", lRoot.getChildTextTrim(Tags.HOST_PATH_ITEM), lRoot
						.getChildTextTrim(Tags.DEVICE_PATH)));
		lTask.getExecuteOnPC().add(lExecuteOnPC);
	
		// dependencies: buildable
		createDepBuild(lExecuteOnPC, lTask, lRoot, true);
	
		createRTest(lRoot, lTask);
	
		// resultFile
		// createRetrieveFromSymbian(lTask,
		// lRoot.getChildTextTrim(Tags.RESULT_FILE),
		// CONFIG.getResultroot().getCanonicalPath());
	
		// devicePath
		// createExecuteOnSymbian(lTask,
		// lRoot.getChildTextTrim(Tags.DEVICE_PATH), StatCommand.RUN_LITERAL);
	
		// logMemory
		createExecuteOnSymbian(lTask, lRoot.getChildTextTrim(Tags.LOG_MEMORY), StatCommand.START_LOGGING);
	
		RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
		createRetrieveFromSymbian(lRetrieveFromSymbian, lRoot.getChildTextTrim(Tags.LOG_MEMORY), RESULT_ROOT);
		if (lRetrieveFromSymbian.getTransfer().size() != 0) {
			lTask.getRetrieveFromSymbian().add(lRetrieveFromSymbian);
		}
	}

	/**
	 * @param lRoot
	 * @param lTask
	 */
	private void handleXMLRTestRom(final Element lRoot, final Task lTask) {
		LOGGER.fine("RTest ROM: " + lRoot.getChildTextTrim(Tags.NAME));

		createRTest(lRoot, lTask);

		// devicePath
		// createExecuteOnSymbian(lTask,
		// lRoot.getChildTextTrim(Tags.DEVICE_PATH), StatCommand.RUN_LITERAL);

		// resultfile
		// createRetrieveFromSymbian(lTask,
		// lRoot.getChildTextTrim(Tags.RESULT_FILE),
		// CONFIG.getResultroot().getCanonicalPath());
		// Correct the host path
	}
	
	/**
	 * Adds a Execute on PC command to the current Execute.
	 * 
	 * @param aExecuteOnPC
	 * 
	 * @param aTask
	 *            The current Execute.
	 * @param aCmd
	 *            The command to run in the task.
	 */
	private void createExecuteOnPc(final ExecuteOnPC aExecuteOnPC, final Task aTask, String aCmd) {
		CmdPC lCommand = iDriverFactory.createCmdPC();
	
		lCommand.setValue(aCmd);
	
		aExecuteOnPC.getCmd().add(lCommand);
		aTask.getExecuteOnPC().add(aExecuteOnPC);
	}

	/**
	 * Adds an Execute On Symbian task to the current Execute.
	 * 
	 * @param aTask
	 *            The current Execute.
	 * @param aArgument
	 *            The arguments for the command.
	 * @param aStatCommand
	 *            The command to run.
	 */
	private void createExecuteOnSymbian(final Task aTask, final String aArgument, final StatCommand aStatCommand) {
		ExecuteOnSymbian lExecuteSymbian = iDriverFactory.createExecuteOnSymbian();
		CmdSymbian lCommandSymbian = iDriverFactory.createCmdSymbian();
	
		lCommandSymbian.setStatCommand(aStatCommand);
		lCommandSymbian.setSync(true);
	
		if(aArgument != null) {
			lCommandSymbian.getArgument().add(aArgument);
		}
		lExecuteSymbian.getCmd().add(lCommandSymbian);
		aTask.getExecuteOnSymbian().add(lExecuteSymbian);
	}

	/**
	 * Adds a Transfer To Symbian task to the current Execute.
	 * 
	 * @param aTransferToSymbian
	 *            The EMF object to add the Transfer Object to.
	 * @param aDevicePath
	 *            The device path for the transfer to symbian task.
	 * @param aHostPath
	 *            The host path for the transfer to symbian task.
	 */
	private void createTransferToSymbian(final TransferToSymbian aTransferToSymbian, final String aDevicePath, final String aHostPath) {
		Task lEclipseTask = ModelUtils.isFileEclipsing(
				aDevicePath.substring(3, aDevicePath.length()).toLowerCase(), 
				(Task) aTransferToSymbian.eContainer());
		
		if (lEclipseTask == null) {
			Transfer lTransfer = iDriverFactory.createTransfer();
		
			lTransfer.setSymbianPath(aDevicePath);
			lTransfer.setPCPath(ImportUtils.createValidPath(aHostPath));
			lTransfer.setMove(false);
			
			aTransferToSymbian.getTransfer().add(lTransfer);
		} else {
			LOGGER.severe("In the task node \"" + ((Task) aTransferToSymbian.eContainer()).getName()
					+ "\", the file on the PC \"" + aHostPath
					+ "\", would overwrite a file on the Device \"" + aDevicePath
					+ "\", because the parent task \"" + lEclipseTask.getName() + "\" already contains the file. The file will not be added to the driver file.");
		}
	}

	/**
	 * Adds a Retrieve from Symbian task to the current Execute.
	 * 
	 * @param aRetrieveFromSymbian
	 *            The EMF object to add the Transfer Object to.
	 * @param aRetrievePath
	 *            The path of the file to retrieve from.
	 * @param aHostPath
	 *            The path of where to copy the file to.
	 */
	private void createRetrieveFromSymbian(final RetrieveFromSymbian aRetrieveFromSymbian, final String aRetrievePath, final String aHostPath) {
		Transfer lTransfer = iDriverFactory.createTransfer();
	
		lTransfer.setSymbianPath(aRetrievePath);
		lTransfer.setPCPath(aHostPath);
		lTransfer.setMove(true);
	
		aRetrieveFromSymbian.getTransfer().add(lTransfer);
	}
	
	/**
	 * @param lRoot
	 * @param lTask
	 */
	private void createRTest(final Element lRoot, final Task lTask) {
		Rtest lRtest = iDriverFactory.createRtest();

		lRtest.setResultFile(lRoot.getChildTextTrim(Tags.RESULT_FILE));
		lRtest.setSymbianPath(lRoot.getChildTextTrim(Tags.DEVICE_PATH));

		ExecuteOnSymbian lExecuteOnSymbian = iDriverFactory.createExecuteOnSymbian();

		lExecuteOnSymbian.getRtest().add(lRtest);
		lTask.getExecuteOnSymbian().add(lExecuteOnSymbian);
	}

	/**
	 * @param aTask
	 * @param aTaskItems
	 */
	private void createReference(final Task aTask, final Element aTaskItems) {
		try {
			Reference lReference = iDriverFactory.createReference();
			String lRefName = sImportDir.getCanonicalPath() + "/"
				+ aTaskItems.getTextTrim().toLowerCase().replaceAll("\\.", "/");

			// Create Reference XML
			File lRefFile = new File(lRefName + ".xml");
			Task lReferenceTask = ResourceLoader.loadOldXml(lRefFile, null);
			
			// Set Reference URI
			lReference.setUri(lReferenceTask);
		
			aTask.getReference().add(lReference);
			
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Could not get path for XML Root while trying to parse the <" + aTaskItems.getName() + "> Reference", lIOException);
		} catch (ParseException lParseException) {
			LOGGER.log(Level.SEVERE, "Could not get parse the Suite Reference", lParseException);
		}
	}

	/**
	 * Adds a resource to a Execute.
	 * 
	 * @param aTask
	 *            The Execute to add the resource item to.
	 * @param aList
	 *            The list of resources to add to the Execute
	 */
	private void createDepData(final Task aTask, final List aList) {
		if (aList.size() > 0) {
			TransferToSymbian lTransferToSymbian = iDriverFactory.createTransferToSymbian();
			aTask.getTransferToSymbian().add(lTransferToSymbian);

			for (Iterator lIter = aList.iterator(); lIter.hasNext();) {
				Element lElement = (Element) lIter.next();
				createTransferToSymbian(lTransferToSymbian, lElement.getChildTextTrim(Tags.DEVICE_PATH), lElement.getChildTextTrim(Tags.HOST_PATH_ITEM));
			}
		}
	}

	/**
	 * Adds all the build dependencies to the current Execute. Works with both build and buildable tags.
	 * 
	 * @param aExecuteOnPC
	 * 
	 * @param aTask
	 *            The current Execute.
	 * @param aRoot
	 *            The root of the build dependencies
	 * @param aResource
	 *            <code>true</code> if the build Execute contains resources.
	 * @param aLegacyBuildable
	 *            <code>true</code> if the code uses the tag "buildable"
	 *            instead of "build".
	 */
	private void createDepBuild(final ExecuteOnPC aExecuteOnPC, final Task aTask, final Element aRoot, final boolean aResource) {
		if (aRoot.getChild(Tags.DEPENDENCIES_LIST) != null) {
			
			if (aRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.BUILD_DEPENDENCIES_LIST_LEGACY) != null) {
				createDepBuildGeneric(aExecuteOnPC, aTask, aRoot, aResource, Tags.BUILD_DEPENDENCIES_LIST_LEGACY);
			}
			
			if (aRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(Tags.BUILD_DEPENDENCIES_LIST) != null) {
				createDepBuildGeneric(aExecuteOnPC, aTask, aRoot, aResource, Tags.BUILD_DEPENDENCIES_LIST);
			}
			
		}
		
		if (!aExecuteOnPC.getBuild().isEmpty()) {
			aTask.getExecuteOnPC().add(aExecuteOnPC);
		}
	}

	/**
	 * Builds the dependencies for both any type of generic tag.
	 * 
	 * @param aExecuteOnPC
	 * @param aTask
	 * @param aRoot
	 * @param aResource
	 * @param lBuildTag
	 */
	private void createDepBuildGeneric(final ExecuteOnPC aExecuteOnPC, final Task aTask, final Element aRoot, final boolean aResource, String lBuildTag) {
		
		for (Iterator lDepIterator = aRoot.getChild(Tags.DEPENDENCIES_LIST).getChildren(lBuildTag).iterator(); lDepIterator.hasNext();) {
			Element lTmpElement = (Element) lDepIterator.next();

			Build lBuild = createBuild(true,
					(lTmpElement.getAttribute(Tags.BUILD_TYPE) == null ? false : lTmpElement.getAttributeValue(Tags.BUILD_TYPE).equals(Tags.BUILD_TYPE_TEST)),
					lTmpElement.getChildTextTrim(Tags.BLD_INF_ITEM),
					lTmpElement.getChildTextTrim(Tags.MMP_FILE_ITEM),
					lTmpElement.getChildTextTrim(Tags.HOST_PATH_ITEM),
					lTmpElement.getChildTextTrim(Tags.DEVICE_PATH));

			aExecuteOnPC.getBuild().add(lBuild);

			if (aResource && lTmpElement.getChild(Tags.RESOURCE_ITEMS_LIST) != null) {
				createDepData(aTask, lTmpElement.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.RESOURCE));
				createDepData(aTask, lTmpElement.getChild(Tags.RESOURCE_ITEMS_LIST).getChildren(Tags.AIF));
			}
		}
		
	}

	/**
	 * Creates a build command.
	 * 
	 * @param aSync
	 *            If the build command should be run syncronous.
	 * @param aTestBuild
	 *            <code>true</code> if this is a test build.
	 * @param aURI
	 *            The location of the group directory.
	 * @param aComponenetName
	 *            The MMP file.
	 * @param aHostPath
	 * @param aDevicePath
	 *            The location of where to put the file on the device.
	 * @return A build object for the EMF tree.
	 */
	private Build createBuild(final boolean aSync, final boolean aTestBuild, final String aURI, final String aComponenetName, final String aHostPath,
			final String aDevicePath) {
		Build lBuild = iDriverFactory.createBuild();
	
		lBuild.setURI(ImportUtils.createValidPath(aURI.toLowerCase().replaceAll("bld.inf", "")));
		lBuild.setTestBuild(aTestBuild);
	
		if (!aComponenetName.equals("")) {
			lBuild.getComponentName().add(aComponenetName.toLowerCase().replaceAll(".mmp", ""));
		}
		
		return lBuild;
	}
}
