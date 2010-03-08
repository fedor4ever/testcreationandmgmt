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

package com.symbian.driver.core.controller;

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

/**
 * @author EngineeringTools
 *
 */
public class CreateTask {
	
	private static final DriverFactory iDriverFactory = DriverFactory.eINSTANCE;
	
	private static final String URI = null;
	private static final String COMPONENT_1 = null;
	private static final String COMPONENT_2 = null;
	private static final String NOTEPAD = null;
	private static final String WINDOWS = null;
	private static final String OUTPUT = null;
	private static final String TEF_TEST_PC = null;
	private static final String TEF_TEST_SYMBIAN = null;
	private static final String TRANSFER_PC = null;
	private static final String TRANSFER_SYMBIAN = null;
	private static final String RTEST = null;
	private static final String RETRIEVE_PC = null;
	private static final String RETRIEVE_SYMBIAN = null;
	private static final String REFERENCE_URI = null;

	/**
	 * Structure of Nodes.
	 * 
	 * lRootTask
	 * 		- lTaskLevel1a (tests going up)
	 * 		- lTaskLevel1b (tests staying level)
	 * 			- lTaskLevel2a
	 * 				- lTaskLevel3a (tests going up several levels)
	 * 			- lTaskLevel2b (tests going down)
	 * 				- lTaskLevel3b
	 * 				- lTaskLevel3c
	 * 		- lTaskLevel1c (tests going down several levels)
	 * 			- lTaskLevel2c (tests going down several levels on last)
	 * 
	 * @param aRootTask
	 */
	public static void createTree(Task aRootTask) {
		// 1st Level
		Task l1a = createTask();
		aRootTask.getTask().add(l1a);
		Task l1b = createTask();
		aRootTask.getTask().add(l1b);
		Task l1c = createTask();
		aRootTask.getTask().add(l1c);
		// 2nd Level
		Task l2a = createTask();
		l1b.getTask().add(l2a);
		Task l2b = createTask();
		l1b.getTask().add(l2b);
		Task l2c = createTask();
		l1c.getTask().add(l2c);
		// 3rd Level
		Task l3a = createTask();
		l2a.getTask().add(l3a);
		Task l3b = createTask();
		l2b.getTask().add(l3b);
		Task l3c = createTask();
		l2b.getTask().add(l3c);
	}

	/**
	 * @return
	 */
	public static Task createTask() {
		Task lTask = iDriverFactory.createTask();
		
		// ////////////////////
		// Create ExecuteOnPC
		{
			ExecuteOnPC lExecuteOnPC = iDriverFactory.createExecuteOnPC();
			
			// test build
			Build lBuildTest = iDriverFactory.createBuild();
			lBuildTest.setTestBuild(true);
			lBuildTest.setURI(URI);
			lBuildTest.getComponentName().add(COMPONENT_1);
			lBuildTest.getComponentName().add(COMPONENT_2);
			lExecuteOnPC.getBuild().add(lBuildTest);
			// normal build
			Build lBuildNotTest = iDriverFactory.createBuild();
			lBuildNotTest.setTestBuild(true);
			lBuildNotTest.setURI(URI);
			lBuildNotTest.getComponentName().add(COMPONENT_1);
			lBuildNotTest.getComponentName().add(COMPONENT_1);
			lExecuteOnPC.getBuild().add(lBuildNotTest);
			// sync cmd
			CmdPC lCmdPCSync = iDriverFactory.createCmdPC();
			lCmdPCSync.setSync(true);
			lCmdPCSync.setURI(WINDOWS);
			lCmdPCSync.setValue(NOTEPAD);
			lExecuteOnPC.getCmd().add(lCmdPCSync);
			// async cmd
			CmdPC lCmdPCAsync = iDriverFactory.createCmdPC();
			lCmdPCAsync.setSync(true);
			lCmdPCAsync.setURI(WINDOWS);
			lCmdPCAsync.setValue(NOTEPAD);
			lExecuteOnPC.getCmd().add(lCmdPCAsync);
			
			lTask.getExecuteOnPC().add(lExecuteOnPC);
		}
		
		// ////////////////////
		// Create ExecuteOnSymbian
		{
			ExecuteOnSymbian lExecuteOnSymbian = iDriverFactory.createExecuteOnSymbian();
			
			// sync cmd
			CmdSymbian lCmdSymbianSync = iDriverFactory.createCmdSymbian();
			lCmdSymbianSync.setSync(true);
			lCmdSymbianSync.setOutput(OUTPUT);
			lCmdSymbianSync.setStatCommand(StatCommand.CREATE_FOLDER_LITERAL);
			lExecuteOnSymbian.getCmd().add(lCmdSymbianSync);
			// async cmd
			CmdSymbian lCmdSymbianAsync = iDriverFactory.createCmdSymbian();
			lCmdSymbianAsync.setSync(false);
			lCmdSymbianAsync.setOutput(OUTPUT);
			lCmdSymbianAsync.setStatCommand(StatCommand.CREATE_FOLDER_LITERAL);
			lExecuteOnSymbian.getCmd().add(lCmdSymbianAsync);
			// rtest
			Rtest lRtest = iDriverFactory.createRtest();
			lRtest.setResultFile(OUTPUT);
			lRtest.setSymbianPath(RTEST);
			lExecuteOnSymbian.getRtest().add(lRtest);
			// TEF Script
			TestExecuteScript lTestExecuteScript = iDriverFactory.createTestExecuteScript();
			lTestExecuteScript.setPCPath(TEF_TEST_PC);
			lTestExecuteScript.setSymbianPath(TEF_TEST_SYMBIAN);
			lExecuteOnSymbian.getTestExecuteScript().add(lTestExecuteScript);
			
			lTask.getExecuteOnSymbian().add(lExecuteOnSymbian);
		}
		
		// ////////////////////
		// Create TransferToSymbian
		{
			TransferToSymbian lTransferToSymbian = iDriverFactory.createTransferToSymbian();
			
			// copy
			Transfer lCopy = iDriverFactory.createTransfer();
			lCopy.setMove(false);
			lCopy.setPCPath(TRANSFER_PC);
			lCopy.setSymbianPath(TRANSFER_SYMBIAN);
			lTransferToSymbian.getTransfer().add(lCopy);
			// move
			Transfer lMove = iDriverFactory.createTransfer();
			lMove.setMove(true);
			lMove.setPCPath(TRANSFER_PC);
			lMove.setSymbianPath(TRANSFER_SYMBIAN);
			lTransferToSymbian.getTransfer().add(lMove);
			
			lTask.getTransferToSymbian().add(lTransferToSymbian);
		}
		
		// ////////////////////
		// Create RetrieveFromSymbian
		{
			RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
			
			// copy
			Transfer lCopy = iDriverFactory.createTransfer();
			lCopy.setMove(false);
			lCopy.setPCPath(RETRIEVE_PC);
			lCopy.setSymbianPath(RETRIEVE_SYMBIAN);
			lRetrieveFromSymbian.getTransfer().add(lCopy);
			// move
			Transfer lMove = iDriverFactory.createTransfer();
			lMove.setMove(true);
			lMove.setPCPath(RETRIEVE_PC);
			lMove.setSymbianPath(RETRIEVE_SYMBIAN);
			lRetrieveFromSymbian.getTransfer().add(lMove);

			
			lTask.getRetrieveFromSymbian().add(lRetrieveFromSymbian);
		}
		
		// ////////////////////
		// Create Reference
		{
			Reference lReference = iDriverFactory.createReference();
			
//			lReference.setURI(REFERENCE_URI);
			
			lTask.getReference().add(lReference);
		}
		
		return lTask;
	}

	public static Task CreateFullTree() {
		Task lRootTask = createTask();
		createTree(lRootTask);
		return lRootTask;
	}

}
