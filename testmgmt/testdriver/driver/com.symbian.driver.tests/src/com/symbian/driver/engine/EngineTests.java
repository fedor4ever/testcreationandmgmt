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

import junit.framework.Test;
import junit.framework.TestSuite;

public class EngineTests {
	
	// TestDriver Settings
	public static final File TD_EPOCROOT = new File("h:\\");
	public static final File TD_REPOSITORY = new File("repos");
	public static final File TD_CERT = new File("..\\com.symbian.driver.engine\\resource\\testdriver.cert");
	public static final File TD_KEY = new File("..\\com.symbian.driver.engine\\resource\\testdriver.key");
	public static final String TD_UID = "0x10210D01";
	
	// JStat Settings
	public static final String TRANSPORT = "serial2";
	
	// Test Generic Constants
	public static final String NAME = "test";
	public static final int TIMEOUT = 100000;
	
	// Test Constants: PC Variablies
	public static final File PC_FILE_1 = new File("data/dependencies/data/test1.txt");
	public static final File PC_FILE_2 = new File("data/dependencies/data/test2.txt");
	public static final File PC_FILE_WRONG = new File("data\\dependencies\\data\\NonExistantFile.txt");
	public static final File PC_FILE_DELETE = new File("to.delete");
	public static final File PC_DIR_WRONG = new File("data\\dependencies\\NonExistantDir");
	public static final File SIS_PATH = new File("repos/testDriver.sis");

	
	// Test Constants: Symbian Variables
	public static final String SYMBIAN_C = "c:\\";
	public static final String SYMBIAN_FILE_1 = "c:\\test1.txt";
	public static final String SYMBIAN_FILE_2 = "c:\\test2.txt";
	public static final String SYMBIAN_FILE_WRONG = "c:\\nonExistantfile.file";
	public static final String SYMBIAN_FILE_WRONG_SYNTAX = "c::\\\\fileIncorrect.d.d";
	public static final String SYMBIAN_DIR = "c:\\test";
	public static final String SYMBIAN_NEW_DIR = "c:\\newdir\\test";
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.symbian.driver.engine");
		//$JUnit-BEGIN$
		suite.addTestSuite(ExecuteOnHostTest.class);
		suite.addTestSuite(TransferSetTest.class);
		suite.addTestSuite(TransferTest.class);
		suite.addTestSuite(ExecuteOnDeviceTest.class);
		suite.addTestSuite(Utils.class);
		//$JUnit-END$
		return suite;
	}

}
