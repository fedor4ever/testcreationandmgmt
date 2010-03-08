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

import java.io.File;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.Phase;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.TransferToSymbian;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.util.DriverSwitch;

/**
 * @author EngineeringTools
 * 
 */
public class PCVisitorTest extends MockObjectTestCase {
	
	private final PCVisitor iPCVisitor = new PCVisitor();
	private final Stack iAsyncStack = new Stack();
	private final DriverFactory iDriverFactory = DriverFactory.eINSTANCE;
	
	private Mock iTransferSet;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		iTransferSet = new Mock(ExecuteTransferSet.class);
		iTransferSet.expects(atLeastOnce()).method("isEmpty").will(returnValue(false));
		iTransferSet.expects(atLeastOnce()).method("add").will(returnValue(true));
	}
	
	/**
	 * 
	 */
	public void testSwitchCmdPC() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.BUILD_LITERAL, true)) {
			fail();
		}
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.RUN_LITERAL, false)) {
			fail();
		}
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.BOTH_LITERAL, true)) {
			fail();
		}
	}
	
	/**
	 * 
	 */
	public void testSwitchBuildwithComponents() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		Build lBuild = iDriverFactory.createBuild();
		
		lBuild.setTestBuild(true);
		lBuild.setURI("");
		
		EList lComponentNameList = lBuild.getComponentName();
		lComponentNameList.add("");
		lComponentNameList.add("");
		lComponentNameList.add("");
		
		Object lReturn = lDriverSwitch.doSwitch(lBuild);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	/**
	 * 
	 */
	public void testSwitchBuildwithoutComponents() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		Build lBuild = iDriverFactory.createBuild();
		
		lBuild.setTestBuild(true);
		lBuild.setURI("");
		
		Object lReturn = lDriverSwitch.doSwitch(lBuild);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	/**
	 * 
	 */
	public void testSwitchBuildnotTest() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		Build lBuild = iDriverFactory.createBuild();
		
		lBuild.setTestBuild(false);
		lBuild.setURI("");
		
		Object lReturn = lDriverSwitch.doSwitch(lBuild);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	/**
	 * 
	 */
	public void testSwitchTestExecuteScript() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		TestExecuteScript lTestExecuteScript = iDriverFactory.createTestExecuteScript();
		
		lTestExecuteScript.setPCPath("");
		lTestExecuteScript.setSymbianPath("");
		
		Object lReturn = lDriverSwitch.doSwitch(lTestExecuteScript);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	/**
	 * 
	 */
	public void testSwitchTransfer() {
		DriverSwitch lDriverSwitch = iPCVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		TransferToSymbian lTransferToSymbian = iDriverFactory.createTransferToSymbian();
		
		EList lTransferList = lTransferToSymbian.getTransfer();
		
		Transfer[] lTranfers = new Transfer[6];
		
		lTranfers[0] = iDriverFactory.createTransfer();
		lTranfers[0].setMove(false);
		lTranfers[0].setPCPath("");
		lTranfers[0].setSymbianPath("");
		
		lTranfers[1] = iDriverFactory.createTransfer();
		lTranfers[1].setMove(false);
		lTranfers[1].setPCPath("");
		lTranfers[1].setSymbianPath("");
		
		lTranfers[2] = iDriverFactory.createTransfer();
		lTranfers[2].setMove(false);
		lTranfers[2].setPCPath("");
		lTranfers[2].setSymbianPath("");
		
		lTranfers[3] = iDriverFactory.createTransfer();
		lTranfers[3].setMove(false);
		lTranfers[3].setPCPath("");
		lTranfers[3].setSymbianPath("");

		lTranfers[4] = iDriverFactory.createTransfer();
		lTranfers[4].setMove(true);
		lTranfers[4].setPCPath("");
		lTranfers[4].setSymbianPath("");
		
		lTranfers[5] = iDriverFactory.createTransfer();
		lTranfers[5].setMove(true);
		lTranfers[5].setPCPath("");
		lTranfers[5].setSymbianPath("");
		
		lTransferList.add(lTranfers);
		
		for (int i = 0; i < lTranfers.length; i++) {
			Object lReturn = lDriverSwitch.doSwitch(lTranfers[i]);
			
			if (!(lReturn instanceof CmdPC)) {
				fail();
			}
		}
	}
	
	/**
	 * 
	 */
	public void testHandleTaskGoingUp() {
		
		Mock lTask = new Mock(Task.class);
		lTask.expects(once()).method("getLevel").will(returnValue(1));
		
		try {
			
			iPCVisitor.handleTask(0, (Task) lTask, (ExecuteTransferSet) iTransferSet.proxy(), new File(""));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * 
	 */
	public void testHandleTaskGoingLevel() {
		
		Mock lTask = new Mock(Task.class);
		lTask.expects(once()).method("getLevel").will(returnValue(5));
		
		try {
			
			iPCVisitor.handleTask(5, (Task) lTask, (ExecuteTransferSet) iTransferSet.proxy(), new File(""));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * 
	 */
	public void testHandleTaskGoingDown() {
		
		Mock lTask = new Mock(Task.class);
		lTask.expects(once()).method("getLevel").will(returnValue(5));
		
		try {
			
			iPCVisitor.handleTask(10, (Task) lTask, (ExecuteTransferSet) iTransferSet.proxy(), new File(""));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * 
	 */
	public void testRBuild() {
		if (PCVisitor.isRBuild()) {
			fail();
		}
		
		if (!iPCVisitor.setRBuild(true)) {
			fail();
		}
		
		if (!PCVisitor.isRBuild()) {
			fail();
		}
		
		testSwitchBuildwithComponents();
		
		if (iPCVisitor.setRBuild(false)) {
			fail();
		}
		
		if (PCVisitor.isRBuild()) {
			fail();
		}
		
		testSwitchBuildwithComponents();
	}
	
	/**
	 * Test method for
	 * 'com.symbian.driver.driver.visitor.HostVisitor.HostVisitor(EObject)'
	 */
	public void testPCVisitor() {		
		Task lRootTask = CreateTask.CreateFullTree();
		
		// RBuild before
		PCVisitor lRBuildBeforeVisitor = new PCVisitor();
		lRBuildBeforeVisitor.setRBuild(true);
		lRBuildBeforeVisitor.start(lRootTask);
		
		// Normal
		PCVisitor lBuildVisitor = new PCVisitor();
		lBuildVisitor.setRBuild(false);
		lBuildVisitor.start(lRootTask);
		
		// RBuild After
		PCVisitor lRBuildAfterVisitor = new PCVisitor();
		lRBuildAfterVisitor.setRBuild(true);
		lRBuildAfterVisitor.start(lRootTask);
		
		fail();
	}
}
