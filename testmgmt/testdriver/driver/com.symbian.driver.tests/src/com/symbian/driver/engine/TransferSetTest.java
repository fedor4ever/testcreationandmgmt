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


package com.symbian.driver.engine;

import java.io.File;
import java.io.IOException;

import javax.naming.TimeLimitExceededException;

import junit.framework.TestCase;

import com.symbian.driver.core.controller.tasks.ExecuteTransfer;
import com.symbian.driver.core.controller.tasks.ExecuteTransferSet;
import com.symbian.jstat.JStat;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;

/** The JUnit Test case for TransferSetTest.
 * @author EngineeringTools
 */
public class TransferSetTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		
		JStat.getInstance(EngineTests.TRANSPORT, EngineTests.TIMEOUT);
		
		JStatResult lResult = JStat.getInstance().getInfo();
		if (lResult == null || lResult.getReturnedValue() != 13) {
			fail();
		}
		
		if (!EngineTests.TD_REPOSITORY.isDirectory() && !EngineTests.TD_REPOSITORY.mkdirs()) {
			fail();
		}
	}

	/** Test method for 'com.symbian.environment.epoc.EpocFileSet.EpocFileSet(String)'.*/
	public final void testTransferSet() {
		System.out.println("STARTING NAME HOST EPOC FILE SET");
		ExecuteTransferSet lExecuteTransferSet = null;
		try {
			lExecuteTransferSet = new ExecuteTransferSet(EngineTests.NAME, EngineTests.TD_REPOSITORY);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		assertEquals(EngineTests.NAME, lExecuteTransferSet.getName());
		
		assertTrue(lExecuteTransferSet.isEmpty());
	}

	/** Test method for 'com.symbian.environment.epoc.EpocFileSet.add(Execute)'. */
	public final void testAddRemoveFile() {
		System.out.println("STARTING NAME ADD REMOVE EPOC FILE");
		
		ExecuteTransferSet lEpocFileSet = null;
		
		try {
			lEpocFileSet = new ExecuteTransferSet(EngineTests.NAME, EngineTests.TD_REPOSITORY);
		} catch (IOException e1) {
			e1.printStackTrace();
			fail();
		}
		
		if (lEpocFileSet == null) {
			fail();
		}
		
		try {
			ExecuteTransfer lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
			if (!lEpocFileSet.add(lExecuteTransfer)) {
				fail();
			}
			
			if (!lEpocFileSet.contains(lExecuteTransfer)) {
				fail();
			}
			
			if (!lEpocFileSet.remove(lExecuteTransfer)) {
				fail();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			
			if (lEpocFileSet.add(new ExecuteTransfer(EngineTests.PC_FILE_WRONG, EngineTests.SYMBIAN_FILE_1))) {
				fail();
			}
			
			if (lEpocFileSet.add(new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_WRONG_SYNTAX))) {
				fail();
			}
			
		} catch (IOException e) {
			assertTrue(true);
		}

		if (lEpocFileSet.remove(EngineTests.PC_FILE_WRONG)) {
			fail();
		}

	}

	/** Test method for 'com.symbian.environment.epoc.EpocFileSet.build()'. */
	public final void testInstallPlatsecOn() {
		try {
			ExecuteTransferSet lExecuteTransferSet = new ExecuteTransferSet(EngineTests.NAME, EngineTests.TD_REPOSITORY);
			
			lExecuteTransferSet.add(new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1));
			lExecuteTransferSet.add(new ExecuteTransfer(EngineTests.PC_FILE_2, EngineTests.SYMBIAN_FILE_2));
			
			lExecuteTransferSet.createRepository(EngineTests.TD_UID);
			lExecuteTransferSet.createSis(EngineTests.TD_EPOCROOT, EngineTests.SIS_PATH, EngineTests.TD_CERT, EngineTests.TD_KEY);
			
			lExecuteTransferSet.installSis(EngineTests.SIS_PATH, EngineTests.TIMEOUT);
			
			if (JStat.getInstance().listFiles("c:\\").getReceivedData().indexOf(EngineTests.PC_FILE_1.getName()) < 0) {
				fail();
			}
			
			try {
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_2.getName());
			} catch (JStatException lJStatException) {
				fail();
			}
			
			lExecuteTransferSet.uninstall(0);
			
			try {
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
				fail();
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_2.getName());
				fail();
			} catch (JStatException lJStatException) {
				assertTrue(true);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for 'com.symbian.environment.epoc.EpocFileSet.build()'. */
	public final void testInstallPlatsecOff() {
		try {
			ExecuteTransferSet lExecuteTransferSet = new ExecuteTransferSet(EngineTests.NAME, EngineTests.TD_REPOSITORY);
			
			lExecuteTransferSet.add(new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1));
			lExecuteTransferSet.add(new ExecuteTransfer(EngineTests.PC_FILE_2, EngineTests.SYMBIAN_FILE_2));

			lExecuteTransferSet.createRepository(EngineTests.TD_UID);
			lExecuteTransferSet.createSis(EngineTests.TD_EPOCROOT, EngineTests.SIS_PATH, EngineTests.TD_CERT, EngineTests.TD_KEY);
			
			lExecuteTransferSet.installRepository();
			
			try {
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_2.getName());
			} catch (JStatException lJStatException) {
				fail();
			}
			
			lExecuteTransferSet.uninstall(0);
			
			try {
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
				fail();
				JStat.getInstance().checkLocation(EngineTests.PC_FILE_2.getName());
				fail();
			} catch (JStatException lJStatException) {
				assertTrue(true);
			}
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
		File[] lListFile = EngineTests.TD_REPOSITORY.listFiles();
		
		if (lListFile != null) {
			for (int lIter = 0; lIter < lListFile.length; lIter++) {
				if (!lListFile[lIter].delete()) {
					System.out.println("Could not delete: " + lListFile[lIter]);
				}
			}
		}
		
		if (EngineTests.TD_REPOSITORY.isDirectory() && !EngineTests.TD_REPOSITORY.delete()) {
			System.out.println("Could not delete: " + EngineTests.TD_REPOSITORY);
		}
	}

}
