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


package com.symbian.driver.plugins.ftptelnet.test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.driver.plugins.ftptelnet.Activator;
import com.symbian.driver.plugins.ftptelnet.FtpTransfer;
import com.symbian.driver.plugins.ftptelnet.TelnetProcess;

public class TelnetProcessTest extends TestCase {
	
	private static IDeviceComms.ISymbianProcess lTelnetSession = null; //TelnetProcess.getInstance("tcp:192.168.0.3:23");
	private static IDeviceComms.ISymbianTransfer lFTPSession = null;
	private static Activator lActivator = new Activator().getDefault();
	protected void setUp() throws Exception {
		lActivator.startCommsServer(true);
		lTelnetSession = lActivator.createSymbianProcess();
		lFTPSession = lActivator.createSymbianTransfer();
	}

	protected void tearDown() throws Exception {
		lActivator.stop(true);
		//super.tearDown();
	}

	public final void testGetInstance() {
		IDeviceComms.ISymbianProcess lProcess = lActivator.createSymbianProcess();
		assertEquals(lTelnetSession, lProcess);
	}

	public final void testGetInstanceString() {
		lTelnetSession = TelnetProcess.getInstance("tcp:192.168.0.3:23");
		
		fail("Not yet implemented");
	}

	public final void testConnectTelnet() {
		
		fail("Not yet implemented"); 
	}

	public final void testJoin() {
		fail("Not yet implemented"); 
	}

	public final void testStop() {
		fail("Not yet implemented"); 
	}

	public final void testInstall() {
		lTelnetSession = TelnetProcess.getInstance("tcp:192.168.0.3:23");
		FtpTransfer lFtp =  FtpTransfer.getInstance("tcp:192.168.0.3:21");
		File iLocalDataFolder = new File("data").getAbsoluteFile();
		File aHostFile =  new File(iLocalDataFolder, "TestDriver_multimedia_0x10210d02.sis");
		File aSymbianFile = new File("c:/TestDriver_multimedia_0x10210d02.sis");
		File aPkgFile = new File(iLocalDataFolder, "TestDriver.pkg");
		lFtp.send(aHostFile, aSymbianFile);
		
		lTelnetSession.install(aSymbianFile, aPkgFile);
	}

	public final void testUninstall() {
		lTelnetSession = TelnetProcess.getInstance("tcp:192.168.0.3:23");
		FtpTransfer lFtp =  FtpTransfer.getInstance("tcp:192.168.0.3:21");
		File iLocalDataFolder = new File("data").getAbsoluteFile();
		File aHostFile =  new File(iLocalDataFolder, "TestDriver_multimedia_0x10210d02.sis");
		File aSymbianFile = new File("c:/TestDriver_multimedia_0x10210d02.sis");
		File aPkgFile = new File(iLocalDataFolder, "TestDriver.pkg");
		lFtp.send(aHostFile, aSymbianFile);
		lTelnetSession.install(aSymbianFile, aPkgFile);
		lTelnetSession.uninstall("10210d02");
	}

	public final void testRunCommand() {
		lTelnetSession = TelnetProcess.getInstance("tcp:192.168.0.3:23");
		List<String> lArguments = new LinkedList<String>();
		lTelnetSession.runCommand("pwd", lArguments, 0, true);
		lArguments.add("z:");
		lTelnetSession.runCommand("cd", lArguments, 0, true);		
		lArguments.clear();
		lTelnetSession.runCommand("ps", lArguments, 0, true);
	}

	public final void testPollDevice() {
		lTelnetSession = TelnetProcess.getInstance("tcp:192.168.0.3:23");
	}

	public final void testGetErrorStream() {
		fail("Not yet implemented"); 
	}

	public final void testGetInputStream() {
		fail("Not yet implemented"); 
	}

	public final void testGetOutputStream() {
		fail("Not yet implemented"); 
	}

	public final void testCaptureScreen() {
		fail("Not yet implemented"); 
	}

	public final void testRebootDevice() {
		fail("Not yet implemented"); 
	}

}
