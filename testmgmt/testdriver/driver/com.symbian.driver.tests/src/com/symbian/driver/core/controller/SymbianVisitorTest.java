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

import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.Phase;
import com.symbian.driver.RetrieveFromSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.StatCommand;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.driver.util.DriverSwitch;

/**
 * @author EngineeringTools
 */
public class SymbianVisitorTest extends MockObjectTestCase {
	
	private final SymbianVisitor iSymbianVisitor = new SymbianVisitor();
	private final Stack iAsyncStack = new Stack();
	private final DriverFactory iDriverFactory = DriverFactory.eINSTANCE;
	
	private Mock iTransferSet;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setUp();
		
		iTransferSet = new Mock(ExecuteTransferSet.class);
		iTransferSet.expects(atLeastOnce()).method("isEmpty").will(returnValue(false));
		iTransferSet.expects(atLeastOnce()).method("add").will(returnValue(true));
	}
	
	/**
	 * 
	 */
	public void testSwitchCmdPC() {
		DriverSwitch lDriverSwitch = iSymbianVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.BUILD_LITERAL, false)) {
			fail();
		}
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.RUN_LITERAL, true)) {
			fail();
		}
		
		if (!TestControllerUtils.createAndTestCmdPc(lDriverSwitch, Phase.BOTH_LITERAL, true)) {
			fail();
		}
	}
	
	public void testSwitchCmdSymbian() {
		DriverSwitch lDriverSwitch = iSymbianVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		CmdSymbian lCmdSymbian = iDriverFactory.createCmdSymbian();
		lCmdSymbian.setOutput("");
		lCmdSymbian.setStatCommand(StatCommand.CREATE_FOLDER_LITERAL);
		lCmdSymbian.setSync(false);
		
		Object lReturn = lDriverSwitch.doSwitch(lCmdSymbian);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	public void testSwitchTestExecuteScript() {
		DriverSwitch lDriverSwitch = iSymbianVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		TestExecuteScript lTestExecuteScript = iDriverFactory.createTestExecuteScript();
		lTestExecuteScript.setPCPath("");
		lTestExecuteScript.setSymbianPath("");
		
		Object lReturn = lDriverSwitch.doSwitch(lTestExecuteScript);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	public void testSwitchRTest() {
		DriverSwitch lDriverSwitch = iSymbianVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		Rtest lRtest = iDriverFactory.createRtest();
		lRtest.setResultFile("");
		lRtest.setSymbianPath("");
		
		Object lReturn = lDriverSwitch.doSwitch(lRtest);
		
		if (!(lReturn instanceof CmdPC)) {
			fail();
		}
	}
	
	public void testSwitchRetrieveFromSymbian() {
		DriverSwitch lDriverSwitch = iSymbianVisitor.emfSwitch((ExecuteTransferSet) iTransferSet.proxy(), iAsyncStack);
		
		RetrieveFromSymbian lRetrieveFromSymbian = iDriverFactory.createRetrieveFromSymbian();
		
		EList lTransferList = lRetrieveFromSymbian.getTransfer();
		
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
	 * Test method for 'com.symbian.driver.driver.visitor.DeviceVisitor.DeviceVisitor(EObject)'
	 */
	public void testDeviceVisitor() {
		Visitor lVisitor = new SymbianVisitor();
		lVisitor.start(CreateTask.CreateFullTree());
	}

}
