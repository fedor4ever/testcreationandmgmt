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

package com.symbian.driver.engine.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.naming.TimeLimitExceededException;

import com.symbian.driver.core.controller.tasks.IExecute;
import com.symbian.driver.core.controller.tasks.IExecuteTransfer;
import com.symbian.driver.engine.impl.ExecuteTransfer;
import com.symbian.jstat.JStat;
import com.symbian.jstat.JStatException;

import junit.framework.TestCase;

public class TransferTest extends TestCase {
	
	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.Transfer(File, String)'
	 */
	public void testTransferConstructorFileString() {
		try {
			IExecuteTransfer lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
			if (lExecuteTransfer == null) {
				fail();
			}
			
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		
		try {
			new ExecuteTransfer(EngineTests.PC_FILE_WRONG, EngineTests.SYMBIAN_FILE_1);
			fail();
		} catch (IOException e) {
			assertTrue(true);
		}
		
		try {
			new ExecuteTransfer(EngineTests.PC_DIR_WRONG, EngineTests.SYMBIAN_FILE_1);
			fail();
		} catch (IOException e) {
			assertTrue(true);
		}
	}

	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.Transfer(File, String, PrintStream)'
	 */
	public void testTransferConstructorFileStringPrintStream() {
		try {
			IExecuteTransfer lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
			
			if (lExecuteTransfer == null) {
				fail();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			new ExecuteTransfer(EngineTests.PC_FILE_WRONG, EngineTests.SYMBIAN_FILE_1);
			fail();
		} catch (IOException e) {
			assertTrue(true);
		}
		
		try {
			new ExecuteTransfer(EngineTests.PC_DIR_WRONG, EngineTests.SYMBIAN_FILE_1);
			fail();
		} catch (IOException e) {
			assertTrue(true);
		}
	}

	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.doTask(boolean, int)'
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.doCleanUp()'
	 */
	public void testDoTaskDoCleanup() {
		ExecuteTransfer lExecuteTransfer = null;
		try {
			PipedOutputStream lPipedOutputStream = new PipedOutputStream();
			PipedInputStream lPipedInputStream = new PipedInputStream(lPipedOutputStream);
			
			lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
			
			BufferedReader lBufferedReader = new BufferedReader(new InputStreamReader(lPipedInputStream));
			
			lExecuteTransfer.doTask(true, EngineTests.TIMEOUT, false);

			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(IExecute.STATOK)})) {
				fail();
			}
			
			lPipedInputStream.close();
			lPipedOutputStream.close();
			lBufferedReader.close();

			JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
			
			if (!lExecuteTransfer.doCleanUp(true)) {
				fail();
			}

			JStat.getInstance().checkLocation(EngineTests.PC_FILE_1.getName());
			if (Utils.readBuffer(lBufferedReader, new String[] {EngineTests.PC_FILE_1.getName()})) {
				fail();
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
		
		try {
			if (lExecuteTransfer.doCleanUp(true)) {
				fail();
			}
		} catch (JStatException e1) {
			assertTrue(true);
		} catch (TimeLimitExceededException e) {
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.getPCPath()'
	 */
	public void testGetPCPath() {
		IExecuteTransfer lExecuteTransfer = null;
		try {
			lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		if (!lExecuteTransfer.getPCPath().isFile()) {
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.getSymbianPath()'
	 */
	public void testGetSymbianPath() {
		IExecuteTransfer lExecuteTransfer = null;
		try {
			lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		if (!lExecuteTransfer.getSymbianPath().equals(EngineTests.SYMBIAN_FILE_1)) {
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.engine.ExecuteTransfer.toString()'
	 */
	public void testToString() {
		IExecuteTransfer lExecuteTransfer = null;
		try {
			lExecuteTransfer = new ExecuteTransfer(EngineTests.PC_FILE_1, EngineTests.SYMBIAN_FILE_1);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		if (!lExecuteTransfer.toString().equals("ExecuteTransfer:\n\t- PC Path: " + EngineTests.PC_FILE_1 + "\n\t- Symbian Path: " + EngineTests.SYMBIAN_FILE_1)) {
			fail();
		}
	}
}
