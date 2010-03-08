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
 * STIF config defaults.
 * 
 * Defines STIF configuration file elemens default value.
 * 
 */
public class ConfigDefaults {
	/**
	 * <code>HardwareLogOutput</code> default value
	 */
	private static final OutputType DEFAULT_HARDWARE_LOG_OUTPUT = OutputType.FILE;
	/**
	 * <code>HardwareLogFormat</code> default value
	 */
	private static final OutputFileFormat DEFAULT_HARDWARE_LOG_FORMAT = OutputFileFormat.HTML;
	/**
	 * <code>EmulatorLogOutput</code> default value
	 */
	private static final OutputType DEFAULT_EMULATOR_LOG_OUTPUT = OutputType.FILE;
	/**
	 * <code>EmulatorLogFormat</code> default value
	 */
	private static final OutputFileFormat DEFAULT_EMULATOR_LOG_FORMAT = OutputFileFormat.TXT;
	/**
	 * <code>LogFileCreationMode</code> default value
	 */
	private static final FileCreationMode DEFAULT_LOG_FILE_CREATION_MODE = FileCreationMode.OVERWRITE;
	/**
	 * <code>HardwareLogBasePath</code> default value
	 */
	private static final String DEFAULT_HARDWARE_LOG_BASE_PATH = "C:\\LOGS\\TestFramework\\";
	/**
	 * <code>EmulatorLogBasePath</code> default value
	 */
	private static final String DEFAULT_EMULATOR_LOG_BASE_PATH = "C:\\LOGS\\TestFramework\\";
	/**
	 * <code>LogFileUnicode</code> default value
	 */
	private static final YesNo DEFAULT_LOG_FILE_UNICODE = YesNo.NO;
	/**
	 * <code>LogWithEventRanking</code> default value
	 */
	private static final YesNo ADD_TESTCASE_TITLE = YesNo.NO;
	/**
	 * <code>AddTestCaseTitle</code> default value
	 */
	private static final YesNo DEFAULT_LOG_WITH_EVENT_RANKING = YesNo.YES;
	/**
	 * <code>LogWithLineBreak</code> default value
	 */
	private static final YesNo DEFAULT_LOG_WITH_LINE_BREAK = YesNo.YES;
	/**
	 * <code>LogWithTimeStamp</code> default value
	 */
	private static final YesNo DEFAULT_LOG_WITH_TIME_STAMP = YesNo.YES;
	/**
	 * <code>ThreadIdToFile</code> default value
	 */
	private static final YesNo DEFAULT_THREAD_ID_TO_LOG_FILE = YesNo.YES;
	/**
	 * <code>CreateLogDirectories</code> default value
	 */
	private static final YesNo DEFAULT_CREATE_LOG_DIRECTORIES = YesNo.YES;
	/**
	 * <code>DisableMeasurment</code> default value
	 */
	private static final MeasurementModule DEFAULT_DISABLE_MEASUREMENT = MeasurementModule.STIF_MEASUREMENT_DISABLE_NONE;
	/**
	 * <code>TestReportFormat</code> default value
	 */
	private static final OutputFileFormat DEFAULT_TEST_REPORT_FORMAT = OutputFileFormat.TXT;
	/**
	 * <code>TestReportMode</code> default value
	 */
	private static final TestReportMode DEFAULT_TEST_RAPORT_MODE = TestReportMode.FULL_REPORT;
	/**
	 * <code>CreateTestReport</code> default value
	 */
	private static final YesNo DEFAULT_CREATE_TEST_RAPORT = YesNo.YES;
	/**
	 * <code>TestReportFilePath</code> default value
	 */
	private static final String DEFAULT_TEST_RAPORT_FILE_PATH = "C:\\LOGS\\TestFramework\\";
	/**
	 * <code>TestReportFileName</code> default value
	 */
	private static final String DEFAULT_TEST_RAPORT_FILE_NAME = "TestReport";
	/**
	 * <code>TestReportOutput</code> default value
	 */
	private static final OutputType DEFAULT_TEST_REPORT_OUTPUT = OutputType.FILE;
	/**
	 * <code>TestReportFileCreationMode</code> default value
	 */
	private static final FileCreationMode DEFAULT_TEST_RAPORT_FILE_CREATION_MODE = FileCreationMode.OVERWRITE;
	/**
	 * <code>DeviceResetDllName</code> default value
	 */
	private static final String DEFAULT_DEVICE_RESET_DLL_NAME = "StifResetForNokia.dll";
	/**
	 * <code>Timeout</code> default value
	 */
	private static final Integer DEFAULT_TIMEOUT = new Integer( 0 );
		
	/**
	 * Gets <code>TestReportMode</code> default value
	 * @return <code>TestReportMode</code> default value
	 */
	public static TestReportMode getTestReportModeDefaultValue() {
		return DEFAULT_TEST_RAPORT_MODE;
	}
	
	/**
	 * Gets <code>CreateTestReport</code> default value
	 * @return <code>CreateTestReport</code> default value
	 */
	public static YesNo getCreateTestReportDefaultValue() {
		return DEFAULT_CREATE_TEST_RAPORT;
	}
	
	/**
	 * Gets <code>TestReportFileName</code> default value
	 * @return <code>TestReportFileName</code> default value
	 */
	public static String getTestReportFileNameDefaultValue() {
		return DEFAULT_TEST_RAPORT_FILE_NAME;
	}

	/**
	 * Gets <code>TestReportFilePath</code> default value
	 * @return <code>TestReportFilePath</code> default value
	 */
	public static String getTestReportFilePathDefaultValue() {
		return DEFAULT_TEST_RAPORT_FILE_PATH;
	}
	
	/**
	 * Gets <code>TestReportFormat</code> default value
	 * @return <code>TestReportFormat</code> default value
	 */
	public static OutputFileFormat getTestReportFormatDefaultValue() {
		return DEFAULT_TEST_REPORT_FORMAT;
	}

	/**
	 * Gets <code>TestReportOutput</code> default value
	 * @return <code>TestReportOutput</code> default value
	 */
	public static OutputType getTestReportOutputDefaultValue() {
		return DEFAULT_TEST_REPORT_OUTPUT;
	}

	/**
	 * Gets <code>TestReportFileCreationMode</code> default value
	 * @return <code>TestReportFileCreationMode</code> default value
	 */
	public static FileCreationMode getTestReportFileCreationModeDefaultValue() {
		return DEFAULT_TEST_RAPORT_FILE_CREATION_MODE;
	}

	/**
	 * Gets <code>DeviceResetDllName</code> default value
	 * @return <code>DeviceResetDllName</code> default value
	 */
	public static String getDeviceResetDllNameDefaultValue() {
		return DEFAULT_DEVICE_RESET_DLL_NAME;
	}

	/**
	 * Gets <code>Timeout</code> default value
	 * @return <code>Timeout</code> default value
	 */
	public static Integer getTimeoutDefaultValue() {
		return DEFAULT_TIMEOUT;
	}

	/**
	 * Gets <code>DisableMeasurement</code> default value
	 * @return <code>DisableMeasurement</code> default value
	 */
	public static MeasurementModule getDisableMeasurementDefaultValue() {
		return DEFAULT_DISABLE_MEASUREMENT;
	}

	/**
	 * Gets <code>CreateLogDirectories</code> default value
	 * @return <code>CreateLogDirectories</code> default value
	 */
	public static YesNo getCreateLogDirectoriesDefaultValue() {
		return DEFAULT_CREATE_LOG_DIRECTORIES;
	}

	/**
	 * Gets <code>ThreadIdToLogFile</code> default value
	 * @return <code>ThreadIdToLogFile</code> default value
	 */
	public static YesNo getThreadIdToLogFileDefaultValue() {
		return DEFAULT_THREAD_ID_TO_LOG_FILE;
	}

	/**
	 * Gets <code>WithTimeStamp</code> default value
	 * @return <code>WithTimeStamp</code> default value
	 */
	public static YesNo getWithTimeStampDefaultValue() {
		return DEFAULT_LOG_WITH_TIME_STAMP;
	}

	/**
	 * Gets <code>WithLineBreak</code> default value
	 * @return <code>WithLineBreak</code> default value
	 */
	public static YesNo getWithLineBreakDefaultValue() {
		return DEFAULT_LOG_WITH_LINE_BREAK;
	}

	/**
	 * Gets <code>WithEventRanking</code> default value
	 * @return <code>WithEventRanking</code> default value
	 */
	public static YesNo getWithEventRankingDefaultValue() {
		return DEFAULT_LOG_WITH_EVENT_RANKING;
	}

	/**
	 * Gets <code>FileUnicode</code> default value
	 * @return <code>FileUnicode</code> default value
	 */
	public static YesNo getFileUnicodeDefaultValue() {
		return DEFAULT_LOG_FILE_UNICODE;
	}
	
	/**
	 * Gets <code>AddTestCaseTitle</code> default value
	 * @return <code>AddTestCaseTitle</code> default value
	 */
	public static YesNo getAddTestCaseTitleDefaultValue() {
		return ADD_TESTCASE_TITLE;
	}

	/**
	 * Gets <code>EmulatorBasePath</code> default value
	 * @return <code>EmulatorBasePath</code> default value
	 */
	public static String getEmulatorBasePathDefaultValue() {
		return DEFAULT_EMULATOR_LOG_BASE_PATH;
	}

	/**
	 * Gets <code>HardwareBasePath</code> default value
	 * @return <code>HardwareBasePath</code> default value
	 */
	public static String getHardwareBasePathDefaultValue() {
		return DEFAULT_HARDWARE_LOG_BASE_PATH;
	}

	/**
	 * Gets <code>LogFileCreationMode</code> default value
	 * @return <code>LogFileCreationMode</code> default value
	 */
	public static FileCreationMode getLogFileCreationModeDefaultValue() {
		return DEFAULT_LOG_FILE_CREATION_MODE;
	}

	/**
	 * Gets <code>EmulatorLogFormat</code> default value
	 * @return <code>EmulatorLogFormat</code> default value
	 */
	public static OutputFileFormat getEmulatorLogFormatDefaultValue() {
		return DEFAULT_EMULATOR_LOG_FORMAT;
	}

	/**
	 * Gets <code>EmulatorLogOutput</code> default value
	 * @return <code>EmulatorLogOutput</code> default value
	 */
	public static OutputType getEmulatorLogOutputDefaultValue() {
		return DEFAULT_EMULATOR_LOG_OUTPUT;
	}

	/**
	 * Gets <code>HardwareLogFormat</code> default value
	 * @return <code>HardwareLogFormat</code> default value
	 */
	public static OutputFileFormat getHardwareLogFormatDefaultValue() {
		return DEFAULT_HARDWARE_LOG_FORMAT;
	}

	/**
	 * Gets <code>HardwareLogOutput</code> default value
	 * @return <code>HardwareLogOutput</code> default value
	 */
	public static OutputType getHardwareLogOutputDefaultValue() {
		return DEFAULT_HARDWARE_LOG_OUTPUT;
	}
	
	/**
	 * Gets <code>UITestingSupport</code> default value
	 * @return <code>UITestingSupport</code> default value
	 */
	public static YesNo getUITestingSupportDefaultValue(){
		return YesNo.YES;
	}
	
	/**
	 * Gets <code>SeparateProcesses</code> default value
	 * @return <code>SeparateProcesses</code> default value
	 */
	public static YesNo getSeparateProcessesDefaultValue(){
		return YesNo.YES;
	}
}

