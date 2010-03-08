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

/**
 * @author EngineeringTools
 *
 * constants-store interface, relevant to XML files
 */
public interface Tags {
	
    // Common Tags
    /** Tag for name (name). */
    String NAME = "name";
    
    /** Tag for time-out (timeout). */
    String TIMEOUT = "timeout";
    
    // Common Tags: Dependencies
    /** Tag for the list of dependencies (dependencies). */
    String DEPENDENCIES_LIST = "dependencies";

    /** Tag for the list of data dependencies (data). */
    String DATA_DEPENDENCIES_LIST = "data";

    /** Tag for list of build depndencies (buildable). */
    String BUILD_DEPENDENCIES_LIST = "buildable";

    /** Tag for list of build depndencies (build). */
    String BUILD_DEPENDENCIES_LIST_LEGACY = "build"; // this is because both Tags are valid !
    
    /** Tag for type of build (type). */
    String BUILD_TYPE = "type";
    
    /** Tag for type of build value test (test). */
    String BUILD_TYPE_TEST = "test";
    
    /** Tag for mmp file location (mmpFile). */
    String MMP_FILE_ITEM = "mmpFile";

    /** Tag for bld.inf folder location(bldInfPath). */
    String BLD_INF_ITEM = "bldInfPath";
    
    /** Tag for host path location (hostPath). */
    String HOST_PATH_ITEM = "hostPath";

    /** Tag for device path location (devicePath). */
    String DEVICE_PATH = "devicePath";
    
	/** Tag for time stamp on retrieve (timeStamp). */
	String TIME_STAMP = "timeStamp";
	
	// Common Tags: Resource
	/** Tag for resouce list (resourceItems). */
	String RESOURCE_ITEMS_LIST = "resourceItems";

	/** Tag for a resource (resource). */
	String RESOURCE = "resource";

	/** Tag for an AIF resource (aif). */
	String AIF = "aif";
	
	/** Tag for (). */
	String YES = "yes";

	/** Tag for (). */
	String NO = "no";
	
	// TEST SUITE tags
    /** Tag for test suites (testSuite). */
    String TEST_SUITE = "testSuite";
    
    /** Tag for (). */
    String TEST_ITEMS_LIST = "testItems";
    
    /** Tag for TEF servers list (testExecuteServers). */
    String TEST_EXECUTE_SERVERS_LIST = "testExecuteServers";
    
    /** Tag for (). */
    String SERVER = "server";
    
    /** Tag for (). */
    String SUITE = "suite";
    
    /** Tag for (). */
    String TEST = "test";
    
    /** Inside test_item.xml, to define testservers:
     * 	<testservers>
     *  	<server>server_name</server>
     *  <testservers>
     */
    String TEST_SERVERS="testServers";
    
    /** Tag for (). */
    String TEST_REF = "testRef";

    /** Tag for (). */
    String SUITE_REF = "suiteRef";

    // TEF test
    /** Tag for TEF tests (testExecuteTest). */
    String TEST_EXECUTE_TEST = "testExecuteTest";

    /** Tag for TEF servers (testExecuteServer). */
    String TEST_EXECUTE_SERVER = "testExecuteServer";
    
    /** Tag for (). */
    String TEST_SCRIPTS_LIST = "testScripts";

    /** Tag for (). */
    String SCRIPT = "script";
    
    /** Tag for (). */
    String TEF_SERVERS_DIR_NAME = "testexecuteservers";
    
	/** Tag for (). */
	String STEP_LOGS_LIST = "stepLogs";
	
	/** Tag for (). */
	String LOG = "log";
    
    // TEF Server
	/** Tag for (). */
	String LOGFILE = "logFile";

    /** Tag for (). */
    String INI_LIST = "iniItems";

    /** Tag for (). */
    String INI_ITEM = "iniFile";

    /** Tag for (). */
    String CONFIG_FILE = "configFile";

    // Commandline test
    /** Tag for commandline tests (commandLineTest). */
    String COMMAND_LINE_TEST = "commandLineTest";
    
    /** Tag for (). */
    String COMMAND_LINE = "commandLine";
    
	/** Tag for result file from a commandline (resultFile). */
	String RESULT_FILE = "resultFile";

    // R Test
    /** Tag for R tests (RTest). */
    String R_TEST = "RTest";
    
    /** Tag for (). */
    String BEFORE_TEST_RUN = "beforeTestRun";

    /** Tag for (). */
    String AFTER_TEST_RUN = "afterTestRun";

	/** Tag for (). */
	String COPY_FROM = "copyFrom";

	/** Tag for (). */
	String SRC = "src";

	/** Tag for (). */
	String DST = "dst";
	
	/** Tag for (). */
	String RETRIEVE_DEPENDENCIES_LIST = "retrieve";

	/** Tag for (). */
	String DELETE_DEPENDENCIES_LIST = "delete";

	/** Tag for (). */
	String LOG_MEMORY = "logMemory";

    // RTest Rom Test
    /** Tag for R tests on the rom (RTestRom). */
    String R_TEST_ROM = "RTestRom";

    // DocTypes
    /** Tag for (). */
    String DOCTYPE_SYMBIAN = "-//SYMBIAN LTD//";

    /** Tag for (). */
    String DOCTYPE_PUBLIC = " PUBLIC \"" + DOCTYPE_SYMBIAN;

    /** Tag for (). */
    String DOCTYPE_DTD = ".dtd";

    /** Tag for (). */
    String DOCTYPE_DOCTYPE = "<!DOCTYPE ";

    /** Tag for (). */
    String DOCTYPE_MIDDLE = "\" \"";

    /** Tag for (). */
    String DOCTYPE_END = DOCTYPE_DTD + "\" []>";

    /** Tag for (). */
    String DOCTYPE_TESTSUITE = DOCTYPE_DOCTYPE + TEST_SUITE + DOCTYPE_PUBLIC + TEST_SUITE.toUpperCase() + DOCTYPE_MIDDLE + TEST_SUITE + DOCTYPE_END;

    /** Tag for (). */
    String DOCTYPE_RTEST = DOCTYPE_DOCTYPE + R_TEST + DOCTYPE_PUBLIC + R_TEST.toUpperCase() + DOCTYPE_MIDDLE + R_TEST + DOCTYPE_END;

    /** Tag for (). */
    String DOCTYPE_RTESTROM = DOCTYPE_DOCTYPE + R_TEST_ROM + DOCTYPE_PUBLIC + R_TEST_ROM.toUpperCase() + DOCTYPE_MIDDLE + R_TEST_ROM + DOCTYPE_END;

    /** Tag for (). */
    String DOCTYPE_TESTEXECUTETEST = DOCTYPE_DOCTYPE + TEST_EXECUTE_TEST + DOCTYPE_PUBLIC + TEST_EXECUTE_TEST.toUpperCase() + DOCTYPE_MIDDLE + TEST_EXECUTE_TEST + DOCTYPE_END;

    /** Tag for (). */
    String DOCTYPE_TESTEXECUTESERVER = DOCTYPE_DOCTYPE + TEST_EXECUTE_SERVER + DOCTYPE_PUBLIC + TEST_EXECUTE_SERVER.toUpperCase() + DOCTYPE_MIDDLE + TEST_EXECUTE_SERVER + DOCTYPE_END;

    /** Tag for (). */
    String DOCTYPE_COMMANDLINETEST = DOCTYPE_DOCTYPE + COMMAND_LINE_TEST + DOCTYPE_PUBLIC + COMMAND_LINE_TEST.toUpperCase() + DOCTYPE_MIDDLE + COMMAND_LINE_TEST + DOCTYPE_END;

	// Other
	/** Tag for (). */
	String ALTERNATIVE_XML_FILE = "~";
}

