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

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * STIF configuration utility class
 * 
 */
public class ConfigUtil {
	private static final String TEST_REPORT_MODE_TAG = "TestReportMode=";
	private static final String CREATE_TEST_REPORT_TAG = "CreateTestReport=";	
	private static final String TEST_REPORT_FILE_PATH_TAG = "TestReportFilePath=";
	private static final String TEST_REPORT_FILE_NAME_TAG = "TestReportFileName=";	
	private static final String TEST_REPORT_FORMAT_TAG = "TestReportFormat=";	
	private static final String TEST_REPORT_OUTPUT_TAG = "TestReportOutput=";	
	private static final String TEST_REPORT_CREATION_METHOD_TAG = "TestReportFileCreationMode=";	
	private static final String DEVICE_RESET_DLL_NAME_TAG = "DeviceResetDllName=";	
	private static final String DISABLE_MEASUREMENT_TAG = "DisableMeasurement=";	
	private static final String TIMEOUT_TAG = "Timeout=";
	private static final String UI_TESTING_SUPPORT_TAG = "UITestingSupport=";
	private static final String SEPARATE_PROCESSES_TAG = "SeparateProcesses=";

	private static final String CREATE_LOG_DIRECTORIES_TAG = "CreateLogDirectories=";
	private static final String EMULATOR_BASE_PATH_TAG = "EmulatorBasePath=";
	private static final String EMULATOR_FORMAT_TAG = "EmulatorFormat=";
	private static final String EMULATOR_OUTPUT_TAG = "EmulatorOutput=";
	private static final String HARDWARE_BASE_PATH_TAG = "HardwareBasePath=";
	private static final String HARDWARE_FORMAT_TAG = "HardwareFormat=";
	private static final String HARDWARE_OUTPUT_TAG = "HardwareOutput=";
	private static final String FILE_CREATION_MODE_TAG = "FileCreationMode=";
	private static final String THREAD_ID_TO_LOG_FILE_TAG = "ThreadIdToLogFile=";
	private static final String WITH_TIME_STAMP_TAG = "WithTimeStamp=";
	private static final String WITH_LINE_BREAK_TAG = "WithLineBreak=";
	private static final String WITH_EVENT_RANKING_TAG = "WithEventRanking=";
	private static final String FILE_UNICODE_TAG = "FileUnicode=";	
	
	private static final String MODULE_NAME_TAG = "ModuleName=";
	private static final String MODULE_INI_FILE_TAG = "IniFile=";
	private static final String MODULE_TEST_CASE_FILE_TAG = "TestCaseFile=";
	
	private static final String ADD_TESTCASE_TITLE_TAG = "AddTestCaseTitle=";

	
	private static Hashtable<TestReportMode,String> testReportModeNames = null;
	
	private static Hashtable<MeasurementModule,String> measurementModuleNames = null;

	private static Hashtable<SectionElementType,String>  engineDefaultsElementTags = null;
	private static Hashtable<SectionElementType,String>  loggerDefaultsElementTags = null;
	private static Hashtable<SectionElementType,String>  moduleElementTags = null;	
	
	static {
		testReportModeNames = new Hashtable<TestReportMode, String>();
		testReportModeNames.put( TestReportMode.EMPTY, "Empty" );
		testReportModeNames.put( TestReportMode.SUMMARY, "Summary" );
		testReportModeNames.put( TestReportMode.ENVIRONMENT, "Environment" );
		testReportModeNames.put( TestReportMode.TESTCASE, "TestCases" );
		testReportModeNames.put( TestReportMode.FULL_REPORT, "FullReport" );
		
		measurementModuleNames = new Hashtable<MeasurementModule, String>();
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_DISABLE_NONE, "stifmeasurementdisablenone" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_DISABLE_ALL, "stifmeasurementdisableall" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_PLUGIN_01, "stifmeasurementplugin01" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_PLUGIN_02, "stifmeasurementplugin02" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_PLUGIN_03, "stifmeasurementplugin03" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_PLUGIN_04, "stifmeasurementplugin04" );
		measurementModuleNames.put(MeasurementModule.STIF_MEASUREMENT_PLUGIN_05, "stifmeasurementplugin05" );
		measurementModuleNames.put(MeasurementModule.STIF_BAPPEA_PROFILER, "stifbappeaprofiler");
	  	
		engineDefaultsElementTags = new Hashtable<SectionElementType, String>();		
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_MODE, TEST_REPORT_MODE_TAG );
		engineDefaultsElementTags.put(SectionElementType.CREATE_TEST_REPORT, CREATE_TEST_REPORT_TAG );	
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_FILE_PATH, TEST_REPORT_FILE_PATH_TAG );
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_FILE_NAME, TEST_REPORT_FILE_NAME_TAG );	
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_FORMAT, TEST_REPORT_FORMAT_TAG );
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_OUTPUT, TEST_REPORT_OUTPUT_TAG ); 
		engineDefaultsElementTags.put(SectionElementType.TEST_REPORT_CREATION_MODE, TEST_REPORT_CREATION_METHOD_TAG );  
		engineDefaultsElementTags.put(SectionElementType.DEVICE_RESET_DLL_NAME, DEVICE_RESET_DLL_NAME_TAG );
		engineDefaultsElementTags.put(SectionElementType.DISABLE_MEASUREMENT, DISABLE_MEASUREMENT_TAG );
		engineDefaultsElementTags.put(SectionElementType.TIMEOUT, TIMEOUT_TAG );
		engineDefaultsElementTags.put(SectionElementType.UI_TESTING_SUPPORT, UI_TESTING_SUPPORT_TAG);
		engineDefaultsElementTags.put(SectionElementType.SEPARATE_PROCESSES, SEPARATE_PROCESSES_TAG);

		loggerDefaultsElementTags = new Hashtable<SectionElementType, String>();
		loggerDefaultsElementTags.put(SectionElementType.CREATE_LOG_DIRECTORIES, CREATE_LOG_DIRECTORIES_TAG );
		loggerDefaultsElementTags.put(SectionElementType.EMULATOR_BASE_PATH, EMULATOR_BASE_PATH_TAG );
		loggerDefaultsElementTags.put(SectionElementType.EMULATOR_LOG_FORMAT, EMULATOR_FORMAT_TAG );
		loggerDefaultsElementTags.put(SectionElementType.EMULATOR_LOG_OUTPUT, EMULATOR_OUTPUT_TAG ); 
		loggerDefaultsElementTags.put(SectionElementType.HARDWARE_BASE_PATH, HARDWARE_BASE_PATH_TAG ); 
		loggerDefaultsElementTags.put(SectionElementType.HARDWARE_LOG_FORMAT, HARDWARE_FORMAT_TAG );
		loggerDefaultsElementTags.put(SectionElementType.HARDWARE_LOG_OUTPUT, HARDWARE_OUTPUT_TAG );
		loggerDefaultsElementTags.put(SectionElementType.LOG_FILE_CREATION_MODE, FILE_CREATION_MODE_TAG );
		loggerDefaultsElementTags.put(SectionElementType.THREAD_ID_TO_LOG_FILE, THREAD_ID_TO_LOG_FILE_TAG );
		loggerDefaultsElementTags.put(SectionElementType.WITH_TIME_STAMP, WITH_TIME_STAMP_TAG );
		loggerDefaultsElementTags.put(SectionElementType.WITH_LINE_BREAK, WITH_LINE_BREAK_TAG ); 
		loggerDefaultsElementTags.put(SectionElementType.WITH_EVENT_RANKING, WITH_EVENT_RANKING_TAG );
		loggerDefaultsElementTags.put(SectionElementType.LOG_FILE_UNICODE, FILE_UNICODE_TAG );
		loggerDefaultsElementTags.put(SectionElementType.ADD_TESTCASE_TITLE, ADD_TESTCASE_TITLE_TAG);
		
		moduleElementTags = new Hashtable<SectionElementType, String>();
		moduleElementTags.put(SectionElementType.MODULE_NAME, MODULE_NAME_TAG );
		moduleElementTags.put(SectionElementType.INI_FILE, MODULE_INI_FILE_TAG );
		moduleElementTags.put(SectionElementType.TEST_CASE_FILE, MODULE_TEST_CASE_FILE_TAG ); 
	}		
		
	/**
	 * Converts string to YesNo value
	 * @param name text value
	 * @return translated YesNo type value
	 */
	public static YesNo getYesNoValueByName( String name ) {
		if ( name == null ) {
			return YesNo.NOT_DEFINED;
		}else if ( name.equals("YES") ) {
			return YesNo.YES;
		} else if ( name.equals("NO") ) {
			return YesNo.NO;
		}
		return YesNo.UNKNOWN; 		
	}

	/**
	 * Converts YesNo type value to text
	 * @param value YesNo type value
	 * @return YesNo type value text representation
	 */
	public static String getYesNoValueName( YesNo value ) {
		if ( value == YesNo.YES ) {
			return "YES";
		} else if ( value == YesNo.NO ) {
			return "NO";
		}
		return null; 		
	}

	/**
	 * Gets list of elements which are allowed to appear in engine defaults section
	 * @return list of allowed elements
	 */
	public static SectionElementType[] getAllowedEngineDefaultsSectionElements() {
		return (SectionElementType[])engineDefaultsElementTags.keySet().toArray(new SectionElementType[0]);
	}
	
	/**
	 * Gets list of elements which are allowed to appear in logger defaults section 
	 * @return list of allowed elements
	 */
	public static SectionElementType[] getAllowedLoggerDefaultsSectionElements() {
		return (SectionElementType[])loggerDefaultsElementTags.keySet().toArray(new SectionElementType[0]);
	}
	
	/**
	 * Gets list of elements which are allowed to appear in module section 
	 * @return list of allowed elements
	 */
	public static SectionElementType[] getAllowedModuleSectionElements() {
		return (SectionElementType[])moduleElementTags.keySet().toArray(new SectionElementType[0]);
	}	
	
	/**
	 * Gets engine defaults section element tag
	 * @param element element type
	 * @return element tag
	 */
	public static String getEngineDefaultsSectionElementTag( SectionElementType element ) {
		return engineDefaultsElementTags.get(element);
	}

	/**
	 * Gets logger defaults section element tag
	 * @param element element type
	 * @return element tag
	 */
	public static String getLoggerDefaultsSectionElementTag( SectionElementType element ) {
		return loggerDefaultsElementTags.get(element);
	}
	
	/**
	 * Gets module section element tag
	 * @param element element type
	 * @return element tag
	 */
	public static String getModuleSectionElementTag( SectionElementType element ) {
		return moduleElementTags.get(element);
	}	
	
	
	/**
	 * Gets allowed <code>TestReportMode</code> values text representations 
	 * @return allowed <code>TestReportMode</code> values text representations
	 */
	public static String[] getTestReportModeNames() {
		Collection<String> names = testReportModeNames.values();		
		return (String[])names.toArray(new String[names.size()]);
	}
	
	/**
	 * Gets converted to text <code>TestReportMode</code> value
	 * @param mode <code>TestReportMode</code> value
	 * @return converted value
	 */
	public static String getTestReportModeName( TestReportMode mode ) {
		return testReportModeNames.get(mode);
	}
	
	/**
	 * Converts text to <code>TestReportMode</code> value
	 * @param name text value
	 * @return <code>TestReportMode</code> type value
	 */
	public static TestReportMode getTestReportModeByName( String name ) {
		Enumeration<TestReportMode> keys = testReportModeNames.keys();
		while ( keys.hasMoreElements() ) {
			TestReportMode key = keys.nextElement();
			String value = testReportModeNames.get( key );
			if ( value.equals(name) ) {
				return key;
			}
		}
		return TestReportMode.UNKNOWN;
	}

	/**
	 * Converts text to <code>OutputFileFormat</code> value
	 * @param name text value
	 * @return <code>OutputFileFormat</code> type value
	 */
	public static OutputFileFormat getOutputFileFormatByName( String name ) {
		if ( name == null ) {
			return OutputFileFormat.NOT_DEFINED;
		} else if ( name.equals("TXT") ) {
			return OutputFileFormat.TXT;
		} else if ( name.equals("HTML") ) {
			return OutputFileFormat.HTML;			
		}
		return OutputFileFormat.UNKNOWN; 
	}
	
	/**
	 * Gets converted to text <code>OutputFileFormat</code> value
	 * @param value <code>OutputFileFormat</code> value
	 * @return converted value
	 */
	public static String getOutputFileFormatName(OutputFileFormat value) {
		if ( value == OutputFileFormat.TXT ) {
			return "TXT";
		} else if ( value == OutputFileFormat.HTML ) {
			return "HTML";
		}			
		return null;
	}	
	
	/**
	 * Converts text to <code>OutputType</code> value
	 * @param name text value
	 * @return <code>OutputType</code> type value
	 */
	public static OutputType getOutputTypeByName(String name) {
		if ( name == null ) {
			return OutputType.NOT_DEFINED;
		} else if ( name.equals("FILE") ) {
			return OutputType.FILE;
		} else if ( name.equals("RDEBUG") ) {
			return OutputType.RDEBUG;			
		}
		return OutputType.UNKNOWN; 
	}

	/**
	 * Gets converted to text <code>OutputType</code> value
	 * @param value <code>OutputType</code> value
	 * @return converted value
	 */
	public static String getOutputTypeName(OutputType value) {
		if ( value == OutputType.FILE ) {
			return "FILE";
		} else if ( value == OutputType.RDEBUG ) {
			return "RDEBUG";
		}			
		return null;
	}
		
	/**
	 * Converts text to <code>FileCreationMode</code> value
	 * @param name text value
	 * @return <code>FileCreationMode</code> type value
	 */
	public static FileCreationMode getFileCreationModeByName(String name) {
		if ( name == null ) {
			return FileCreationMode.NOT_DEFINED;
		} else if ( name.equals("OVERWRITE") ) {
			return FileCreationMode.OVERWRITE;
		} else if ( name.equals("APPEND") ) {
			return FileCreationMode.APPEND;			
		}
		return FileCreationMode.UNKNOWN; 
	}
	
	/**
	 * Gets converted to text <code>FileCreationMode</code> value
	 * @param value <code>FileCreationMode</code> value
	 * @return converted value
	 */
	public static String getFileCreationModeName(FileCreationMode value) {
		if ( value == FileCreationMode.APPEND ) {
			return "APPEND";
		} else if ( value == FileCreationMode.OVERWRITE ) {
			return "OVERWRITE";
		}
		return null;
	}	
	
	/**
	 * Gets allowed <code>MeasurementModule</code> values text representations
	 * @return allowed <code>MeasurementModule</code> values text representations
	 */
	public static String[] getMeasurementModuleNames() {
		Collection<String> names = measurementModuleNames.values();		
		return (String[])names.toArray(new String[names.size()]);
	}

	/**
	 * Converts text to <code>MeasurementModule</code> value
	 * @param name text value
	 * @return <code>MeasurementModule</code> type value
	 */
	public static MeasurementModule getMeasurementModuleByName(String name) {
		if ( name == null ) {
			return MeasurementModule.NOT_DEFINED;
		}
		
		Enumeration<MeasurementModule> keys = measurementModuleNames.keys();
		while ( keys.hasMoreElements() ) {
			MeasurementModule key = keys.nextElement();
			String value = measurementModuleNames.get( key );
			if ( value.equals(name) ) {
				return key;
			}
		}
		return MeasurementModule.UNKNOWN;
	}

	/**
	 * Gets converted to text <code>MeasurementModule</code> value
	 * @param module <code>MeasurementModule</code> value
	 * @return converted value
	 */
	public static String getMeasurementModuleName(MeasurementModule module) {
		return measurementModuleNames.get(module);
	}
	
	/**
	 * Checks if element has proper value
	 * @param elementType elemen type
	 * @param value element value
	 * @return <b><code>true</code></b> if element has proper value, 
	 *         <b><code>false</code></b> if value doesn't have proper value 
	 */
	public static boolean hasProperValue( SectionElementType elementType, String value ) {
		
		switch( elementType ) {
		// Engine defaults section
		case TEST_REPORT_MODE:
			return isProperTestReportModeValue( value );
		case CREATE_TEST_REPORT:
			return isProperYesNoValue( value );
		case TEST_REPORT_FILE_PATH:
			return true;
		case TEST_REPORT_FILE_NAME: 
			return true;
		case TEST_REPORT_FORMAT:
			return isProperOutputFileFormatValue( value );
		case TEST_REPORT_OUTPUT: 
			return isProperOutputTypeValue( value );
		case TEST_REPORT_CREATION_MODE:
			return isProperFileCreationModeValue( value );
		case DEVICE_RESET_DLL_NAME:
			return true;
		case DISABLE_MEASUREMENT: 
			return isProperMeasurementModuleValue( value );
		case TIMEOUT:
			int timeout = 0;
			try {
				timeout = Integer.parseInt( value );
			} catch ( NumberFormatException ex ) {
				return false;
			}
			if ( timeout < 0 ) {
				return false;
			}
			return true;
		case UI_TESTING_SUPPORT:
			return isProperYesNoValue( value );
		case SEPARATE_PROCESSES:
			return isProperYesNoValue( value );
		// Loger defaults section
		case CREATE_LOG_DIRECTORIES:
			return isProperYesNoValue( value );
		case EMULATOR_BASE_PATH:
			return true;
		case EMULATOR_LOG_FORMAT: 
			return isProperOutputFileFormatValue( value );
		case EMULATOR_LOG_OUTPUT: 
			return isProperOutputTypeValue( value );
		case HARDWARE_BASE_PATH:
			return true;
		case HARDWARE_LOG_FORMAT:
			return isProperOutputFileFormatValue( value );
		case HARDWARE_LOG_OUTPUT:
			return isProperOutputTypeValue( value );
		case LOG_FILE_CREATION_MODE: 
			return isProperFileCreationModeValue( value );
		case THREAD_ID_TO_LOG_FILE:
			return isProperYesNoValue( value );
		case WITH_TIME_STAMP:
			return isProperYesNoValue( value );
		case WITH_LINE_BREAK:
			return isProperYesNoValue( value );
		case WITH_EVENT_RANKING:
			return isProperYesNoValue( value );
		case LOG_FILE_UNICODE:
			return isProperYesNoValue( value );
		// Module section	
		case MODULE_NAME:
			if ( ( value.indexOf(" ") != -1 ) || ( value.indexOf("\t") != -1 ) ) {
				return false;
			}
			return true;
		case INI_FILE:			
			return true;
		case TEST_CASE_FILE:
			return true;
		case ADD_TESTCASE_TITLE:
			return isProperYesNoValue( value );
		}		
		
		return false;
	}
	
	/**
	 * Checks if text is proper <code>YesNo</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperYesNoValue( String value ) {
		YesNo yesNo = getYesNoValueByName( value );
		if ( yesNo == YesNo.UNKNOWN ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if text is proper <code>OutputFileFormat</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperOutputFileFormatValue( String value ) {
		OutputFileFormat format = getOutputFileFormatByName( value );
		if ( format == OutputFileFormat.UNKNOWN ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if text is proper <code>FileCreationMode</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperFileCreationModeValue( String value  ) {
		FileCreationMode mode = getFileCreationModeByName( value );
		if ( mode == FileCreationMode.UNKNOWN ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if text is proper <code>MeasurementModule</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperMeasurementModuleValue( String value ) {
		MeasurementModule module = getMeasurementModuleByName( value );
		if ( module == MeasurementModule.UNKNOWN ) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if text is proper <code>OutputType</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperOutputTypeValue( String value ) {
		OutputType type = getOutputTypeByName( value );
		if ( type == OutputType.UNKNOWN ) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if text is proper <code>ProperTestReportMode</code> type value
	 * @param value
	 * @return <b><code>true</code></b> if text is proper value, 
	 *         <b><code>false</code></b> if text is not proper value 
	 */
	private static boolean isProperTestReportModeValue( String value ) {
		TestReportMode mode = getTestReportModeByName( value );
		if ( mode == TestReportMode.UNKNOWN ) {
			return false;
		}
		return true;
	}
	
}
