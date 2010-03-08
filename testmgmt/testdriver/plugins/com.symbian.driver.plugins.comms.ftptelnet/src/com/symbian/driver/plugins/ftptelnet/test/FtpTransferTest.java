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
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.plugins.ftptelnet.FtpTransfer;

public class FtpTransferTest extends TestCase {

	File iLocalDataFolder = new File("data").getAbsoluteFile();

	File iRemoteFolder = new File("C:\\myremotefolder");

	File iDeepRemoteFolder = new File(iRemoteFolder, "level2\\level3\\");

	File iEmptyFile = new File(iLocalDataFolder, "empty_file.txt");

	File iSmallTextFile = new File(iLocalDataFolder, "smalltext.txt");

	File iBigTextFile = new File(iLocalDataFolder, "bigtext.txt");

	File ismallBinFile = new File(iLocalDataFolder, "smallbin.sis");

	File iBigBinFile = new File(iLocalDataFolder, "bigbin.lib");

	protected void setUp() throws Exception {
		if (!iLocalDataFolder.isDirectory()) {
			fail("Local data folder missing." + iLocalDataFolder);
		}
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		//super.setUp();
	}

	protected void tearDown() throws Exception {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		lSession.delete(iRemoteFolder, true);
		//super.tearDown();
	}

	public final void testGetInstance() {
		// try to get an FTP client session.
		try {
			String lTestTransport = "tcp:192.168.0.3:21";
			// save current transport.
			String lTransportSave = TDConfig.getInstance().getPreference(TDConfig.TRANSPORT);
			// set a transport
			TDConfig.getInstance().setPreference(TDConfig.TRANSPORT, lTestTransport);
			FtpTransfer lFTPSession = FtpTransfer.getInstance();
			assertTrue(lFTPSession.mkdir(iDeepRemoteFolder));
			assertTrue(lFTPSession.isFTPConnected());
			TDConfig.getInstance().setPreference(TDConfig.TRANSPORT, lTransportSave);
		} catch (ParseException lParseException) {
			fail("Config problem: " + lParseException.getMessage());
		}
	}

	public final void testGetInstanceString() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		assertTrue(lSession.isFTPConnected());
	}

	public final void testCd() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.isFTPConnected());
		assertFalse(lSession.cd(new File("someting that does exist.")));
		assertTrue(lSession.cd(new File("/")));
		assertTrue(lSession.cd(new File("\\")));
		assertTrue(lSession.cd(new File("c:\\")));
		assertTrue(lSession.cd(new File("c:")));
		assertTrue(lSession.cd(new File("$:")));
		// create remote folder
		assertTrue(lSession.mkdir(iRemoteFolder));
		// create a file in remote folder
		assertTrue(lSession.send(iEmptyFile, new File(iRemoteFolder, iEmptyFile.getName())));
		assertTrue(lSession.cd(iRemoteFolder));
		assertFalse(lSession.cd(new File(iRemoteFolder, iEmptyFile.getName())));
	}

	public final void testDelete() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, "second" + iSmallTextFile.getName())));
		// delete something which does exist
		assertFalse(lSession.delete(new File("xxxxxxxx"), true));
		assertFalse(lSession.delete(new File("xxxxxxxx"), false));
		// delete a file with recursive (recursive should not be relevant to files)
		assertTrue(lSession.delete(new File(iDeepRemoteFolder, iSmallTextFile.getName()), true));
		assertTrue(lSession.delete(new File(iDeepRemoteFolder, "second" + iSmallTextFile.getName()), false));
		// try to delete it again
		assertFalse(lSession.delete(new File(iDeepRemoteFolder, iSmallTextFile.getName()), true));
		// try to delete it again
		assertFalse(lSession.delete(new File(iDeepRemoteFolder, iSmallTextFile.getName()), false));
		//send the files again
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, "1" + iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, "21" + iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, "211" + iSmallTextFile.getName())));
		
		// delete non empty folder
		assertTrue(lSession.delete(iRemoteFolder, false));
		
		// delete with pattern
		assertTrue(lSession.delete(new File(iDeepRemoteFolder, "2*"), false));
		assertTrue(lSession.delete(new File(iDeepRemoteFolder, "*"), false));
				
		// delete empty folder
		assertTrue(lSession.delete(iDeepRemoteFolder, true));
	}

	public final void testDir() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		List<File> lFiles = lSession.dir(new File("something wrong"));
		if (!lFiles.isEmpty()) {
			fail("dir returned a list of files from a non existing dir.");
		}
		
		assertTrue(lSession.send(iSmallTextFile, new File(iDeepRemoteFolder, iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iRemoteFolder, iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iRemoteFolder, "second" + iSmallTextFile.getName())));
		assertTrue(lSession.send(ismallBinFile, new File(iRemoteFolder, ismallBinFile.getName())));

		lFiles = lSession.dir(new File("/"));
		System.out.println(lFiles.toString());

		lFiles = lSession.dir(iRemoteFolder);
		System.out.println(lFiles.toString());

		lFiles = lSession.dir(new File(iRemoteFolder, "level2"));
		System.out.println(lFiles.toString());

		lFiles = lSession.dir(iDeepRemoteFolder);
		System.out.println(lFiles.toString());

		lFiles = lSession.dir(new File(iRemoteFolder,"*.txt"));
		System.out.println(lFiles.toString());
		
		
	}

	public final void testMkdir() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		//mostly tested in above tests
		//already exist
		assertTrue(lSession.mkdir(iRemoteFolder));
		assertTrue(lSession.mkdir(iRemoteFolder));
	}

	public final void testMove() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		assertTrue(lSession.send(iSmallTextFile, new File(iRemoteFolder, iSmallTextFile.getName())));
		assertTrue(lSession.send(iSmallTextFile, new File(iRemoteFolder, "second" + iSmallTextFile.getName())));
		assertTrue(lSession.send(ismallBinFile, new File(iRemoteFolder, ismallBinFile.getName())));
		assertTrue(lSession.send(iBigBinFile, new File(iRemoteFolder, iBigBinFile.getName())));
		assertTrue(lSession.move(new File(iRemoteFolder, ismallBinFile.getName()),new File(iDeepRemoteFolder, ismallBinFile.getName())));
		assertFalse(lSession.move(new File(iRemoteFolder, ismallBinFile.getName()), new File("vvvvv")));
		assertFalse(lSession.move(new File("rubbish1"), new File("rubissh2")));
		
	}

	public final void testPwd() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		lSession.cd(iRemoteFolder);
		File lFile = lSession.pwd();
		assertNotNull(lFile);
		System.out.println("PWD returned:" + lFile.toString());
		assertEquals(iRemoteFolder, lFile);
	}



	public final void testSend() {

		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		assertFalse(lSession.send(new File("vvvv"), iDeepRemoteFolder));
		assertFalse(lSession.send(new File("hjvh"), new File(iDeepRemoteFolder, "a\\b\\d")));
		assertTrue(lSession.send(iBigBinFile, new File(iDeepRemoteFolder, iBigBinFile.getName())));
		assertTrue(lSession.send(iBigTextFile, new File(iDeepRemoteFolder, iBigTextFile.getName())));
	}
	
	public final void testRetrieve() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");
		assertTrue(lSession.mkdir(iDeepRemoteFolder));
		assertTrue(lSession.send(iBigBinFile, new File(iDeepRemoteFolder, iBigBinFile.getName())));
		assertTrue(lSession.send(iBigTextFile, new File(iDeepRemoteFolder, iBigTextFile.getName())));
		assertTrue(lSession.send(iEmptyFile, new File(iDeepRemoteFolder, iEmptyFile.getName())));
		assertFalse(lSession.retrieve(new File("somerubbish"), iLocalDataFolder));
		File lRetBigBinFile = new File(iLocalDataFolder, "retrieved" + iBigBinFile.getName());
		lRetBigBinFile.deleteOnExit();
		assertTrue(lSession.retrieve(new File(iDeepRemoteFolder, iBigBinFile.getName()), lRetBigBinFile));
		File lRetEmptyFile = new File(iLocalDataFolder, "retrieved" + iEmptyFile.getName());
		lRetEmptyFile.deleteOnExit();
		assertTrue(lSession.retrieve(new File(iDeepRemoteFolder, iEmptyFile.getName()), lRetEmptyFile));
		File lRetBigTextFile = new File(iLocalDataFolder, "retrieved" + iBigTextFile.getName());
		lRetBigTextFile.deleteOnExit();
		assertTrue(lSession.retrieve(new File(iDeepRemoteFolder, iBigTextFile.getName()), lRetBigTextFile));		
	}

	public final void testListDrives() {
		FtpTransfer lSession = FtpTransfer.getInstance("tcp:192.168.0.3:21");

		List<String> lDrives = lSession.listDrives();
		assertNotNull(lDrives);
		assertTrue(!lDrives.isEmpty());
		System.out.println("List of drives :" + lDrives.toString());
	}

}
