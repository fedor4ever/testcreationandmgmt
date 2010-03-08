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

package com.symbian.jstat.test;

import javax.naming.TimeLimitExceededException;

import com.symbian.jstat.JStat;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;

import junit.framework.TestCase;

public class JStatTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.runSwitch(int)'
	 */
	public void testRunSwitchInt() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.runSwitch(int, String)'
	 */
	public void testRunSwitchIntString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.runSwitch(int, String, String)'
	 */
	public void testRunSwitchIntStringString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.setPrintStream(PrintStream)'
	 */
	public void testSetPrintStream() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.setTransport(String)'
	 */
	public void testSetTransport() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.setTCP(String)'
	 */
	public void testSetTCP() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.setSerial(String)'
	 */
	public void testSetSerial() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.setUSB(String)'
	 */
	public void testSetUSB() {

		try {
			
			JStat lJStat = JStat.getInstance();
			JStatResult lJStatResult = lJStat.getInfo();
			
			if (lJStatResult == null) {
				fail();
			} else if (lJStatResult.getReturnedValue() != 13) {
				System.out.println("Failed Error Code: " + lJStatResult.getReturnedValue());
				fail();
			}
			
			System.out.println(lJStatResult.toString());
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.sendFile(String, String)'
	 */
	public void testSendFile() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.retrieveFile(String)'
	 */
	public void testRetrieveFileString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.retrieveFile(String, String)'
	 */
	public void testRetrieveFileStringString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.getScreenCapture()'
	 */
	public void testGetScreenCapture() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.installFile(String)'
	 */
	public void testInstallFile() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.uninstallFile(String)'
	 */
	public void testUninstallFile() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.listDrives()'
	 */
	public void testListDrives() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.listFiles(String)'
	 */
	public void testListFiles() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.delete(String)'
	 */
	public void testDelete() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.createFolder(String)'
	 */
	public void testCreateFolder() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.removeFolder(String)'
	 */
	public void testRemoveFolder() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.run(String, String)'
	 */
	public void testRunStringString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.run(String)'
	 */
	public void testRunString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.kill(int)'
	 */
	public void testKillInt() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.kill(String)'
	 */
	public void testKillString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.poll(int)'
	 */
	public void testPollInt() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.poll(String)'
	 */
	public void testPollString() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.startLogging(String)'
	 */
	public void testStartLogging() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.stopLogging()'
	 */
	public void testStopLogging() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.isLogging()'
	 */
	public void testIsLogging() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.getDeviceLogFile()'
	 */
	public void testGetDeviceLogFile() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.rename(String, String)'
	 */
	public void testRename() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.checkLocation(String)'
	 */
	public void testCheckLocation() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.restartBoard()'
	 */
	public void testRestartBoard() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.getVersion()'
	 */
	public void testGetVersion() {
		fail();
	}

	/*
	 * Test method for 'com.symbian.jstat.JStat.getInfo()'
	 */
	public void testGetInfo() {
		fail();
	}

}
