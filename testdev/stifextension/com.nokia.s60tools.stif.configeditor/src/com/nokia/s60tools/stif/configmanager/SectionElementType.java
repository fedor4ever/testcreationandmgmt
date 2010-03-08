/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.stif.configmanager;

/**
 * Enum of section elements
 * 
 */

public enum SectionElementType { UNDEFINED,
	TEST_REPORT_MODE,
	CREATE_TEST_REPORT,	
	TEST_REPORT_FILE_PATH,
	TEST_REPORT_FILE_NAME,	
	TEST_REPORT_FORMAT,	
	TEST_REPORT_OUTPUT,	
	TEST_REPORT_CREATION_MODE,	
	DEVICE_RESET_DLL_NAME,	
	DISABLE_MEASUREMENT,	
	TIMEOUT,
	UI_TESTING_SUPPORT,
	SEPARATE_PROCESSES,

	CREATE_LOG_DIRECTORIES,
	EMULATOR_BASE_PATH,
	EMULATOR_LOG_FORMAT,
	EMULATOR_LOG_OUTPUT,
	HARDWARE_BASE_PATH,
	HARDWARE_LOG_FORMAT,
	HARDWARE_LOG_OUTPUT,
	LOG_FILE_CREATION_MODE,
	THREAD_ID_TO_LOG_FILE,
	WITH_TIME_STAMP,
	WITH_LINE_BREAK,
	WITH_EVENT_RANKING,
	LOG_FILE_UNICODE,
	ADD_TESTCASE_TITLE,
	
	MODULE_NAME,
	INI_FILE,
	TEST_CASE_FILE 
 }
