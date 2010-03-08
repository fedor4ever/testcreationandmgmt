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

package com.symbian.driver.core.environment;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;

import com.symbian.utils.Utils;

/**
 * @author EngineeringTools
 *
 */
public class TDConfigTest extends TestCase {

	private TDConfig CONFIG;

	// Test Data
	private static final String TEF_DEPENDENCIES_DEFAULT = "TestExecute.exe;TestExecuteLogClient.dll;TestExecuteLogEngine.exe;TestExecuteUtils.dll;EventLogServer.exe;TestExecuteLogEngineExt.exe;TestExecuteLogClientExt.dll";
	private static final File VALID_DIR = new File("c:/");
	private static final File EPOC_ROOT = new File("P:/");
	private static final String VALID_STRING = "string";
	private static final String EKA1 = "EKA1";
	private static final String ARMV5 = "armv5";
	private static final File ROM = new File("./data/sys$rom.bin");
	private static final String SERIAL1 = "serial1";
	private static final String UDEB = "udeb";
	private static final String XML_TESTEXECUTE = null;
	private static final String HTML_TESTEXECUTE = "c:\\logs\\testexecute\\";
	private static final URI CORRECT_OLD_ROOT_ADDRESS = URI.createURI("#a.b");
	private static final URI CORRECT_NEW_ROOT_ADDRESS = URI.createURI(new File("plugin.xml").getAbsolutePath()+ "#a.b");
	private static final String NO_MANIFEST = "No manifest found.";

	protected void setUp() throws Exception {
		super.setUp();
		
		CONFIG = TDConfig.getInstance();
		
		if (CONFIG == null) {
			fail();
		}
	}
	
	public void testGetSetBuildNumber() {

		try {
			String lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			System.out.println("First Build Number: " + lBuildNumber);
			String lTest = "Test123";
			CONFIG.setPreference(TDConfig.BUILD_NUMBER, lTest);
			lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			System.out.println("Second Build Number: " + lBuildNumber);
			if (!lBuildNumber.equals(lTest)) {
				fail();
			}
			
			lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			System.out.println("Third Build Number: " + lBuildNumber);
			if (!lBuildNumber.equals(lTest)) {
				fail();
			}
			
			int lCount = 0;
			while (lCount < 10) {
				lCount++;
				final String lTest2 = Integer.toString(lCount);
				CONFIG.setPreference(TDConfig.BUILD_NUMBER, lTest2);
				
				Thread lThread = new Thread () {
					public void run() {
						try {
							String lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
							System.out.println("Forth Build Number: " + lBuildNumber);
							if (!lBuildNumber.equals(lTest2)) {
								fail();
							}
						} catch (ParseException e) {
							e.printStackTrace();
							fail();
						}
					}
				};
				
				lThread.start();
				lThread.join();
			}

		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.getResultHtmlFileLocation()'
	 */
	public void testGetResultHtmlFileLocation() {
		try {
			CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, EPOC_ROOT);
			
			String lResult = CONFIG.getResultHtmlFileLocation();
			assertEquals(lResult, HTML_TESTEXECUTE);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.getResultXmlFileLocation()'
	 */
	public void testGetResultXmlFileLocation() {
		try {
			CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, EPOC_ROOT);
			
			String lResult = CONFIG.getResultXmlFileLocation();
			assertEquals(lResult, XML_TESTEXECUTE);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.incrementRunNumber()'
	 */
	public void testIncrementRunNumber() {
		try {
			int lFirst = CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER);
			CONFIG.incrementRunNumber();
			assertEquals(CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER), lFirst + 1);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.printTestDriverConfig()'
	 */
	public void testPrintTestDriverConfig() {
		try {
			CONFIG.printConfig(true);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.setConfigFromCmdLine(CmdLine)'
	 */
	public void testSetConfigFromCmdLine() {
	
		fail();
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.exportConfig(File)'
	 */
	public void testExportConfig() {
		
		fail();
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.importConfig(File)'
	 */
	public void testImportConfig() {
		
		fail();
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.printConfig(boolean)'
	 */
	public void testPrintConfig() {
		try {
			System.out.println("-----------------------------------------");
			CONFIG.printConfig(true);
			System.out.println("-----------------------------------------");
			CONFIG.printConfig(false);
			System.out.println("-----------------------------------------");
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.getPreferenceURI(String)'
	 */
	public void testGetPreferenceURI() {
		
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing getPreferenceURI with key: " + lKey);
				URI lUri = CONFIG.getPreferenceURI(lKey);
				if (lKey == TDConfig.ENTRY_POINT_ADDRESS) {
					
					if (lUri == null) {
						fail();
					}
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}
	
	/*
	 * Test method for 'com.symbian.driver.core.environment.TDConfig.setPreferenceURI(String, URI)'
	 */
	public void testSetPreferenceURI() {

		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing setPreferenceURI with key: " + lKey);
				CONFIG.setPreferenceURI(lKey, CORRECT_OLD_ROOT_ADDRESS);
				if (lKey == TDConfig.ENTRY_POINT_ADDRESS) {
					
					URI lUri = CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS);
					if (lUri == null || !lUri.equals(CORRECT_OLD_ROOT_ADDRESS)) {
						fail();
					}
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
		
		try {
			CONFIG.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, CORRECT_NEW_ROOT_ADDRESS);
			URI lUri = CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS);
			if (lUri == null || !lUri.equals(CORRECT_NEW_ROOT_ADDRESS)) {
				fail();
			}
		} catch (ParseException e) {
			fail();
		}
		
	}


	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getPreference(String)'
	 */
	public void testGetPreference() {

		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing getPreference with key: " + lKey);
				String lValue = CONFIG.getPreference(lKey);
				if (lKey == TDConfig.BUILD_NUMBER) {
					if (lValue == null) {
						// 
					}
				} else if (lKey == TDConfig.CLIENT) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.KERNEL) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.MODE) {
						if (lValue == null) {
							fail();
						}
				} else if (lKey == TDConfig.PLATFORM) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.SERVER) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.TEST_EXECUTE_DEPENDENCIES) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.TRANSPORT) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.UCC_IP_ADDRESS) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.VARIANT) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.WINTAP) {
					if (lValue == null) {
						fail();
					}
				} else if (lKey == TDConfig.ENTRY_POINT_ADDRESS) {
					if (lValue == null) {
						fail();
					}
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
		

		
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.setPrefrence(String, String)'
	 */
	public void testSetPrefrence() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			
			try {
				System.out.println("Testing setPreference with key: " + lKey);
				CONFIG.setPreference(lKey, "TESTING SET PREFERENCE");
				if (lKey == TDConfig.BUILD_NUMBER) {
					 
				} else if (lKey == TDConfig.CLIENT) {
				 
				} else if (lKey == TDConfig.KERNEL) {
					 
				} else if (lKey == TDConfig.MODE) {
					
				} else if (lKey == TDConfig.PLATFORM) {
					
				} else if (lKey == TDConfig.SERVER) {
					
				} else if (lKey == TDConfig.TEST_EXECUTE_DEPENDENCIES) {
					 
				} else if (lKey == TDConfig.TRANSPORT) {
					 
				} else if (lKey == TDConfig.UCC_IP_ADDRESS) {
					 
				} else if (lKey == TDConfig.VARIANT) {
					
				} else if (lKey == TDConfig.WINTAP) {
					 
				} else if (lKey == TDConfig.ENTRY_POINT_ADDRESS) {
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.isPreference(String)'
	 */
	public void testIsPreference() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing isPreference with key: " + lKey);
				boolean lValue = CONFIG.isPreference(lKey);
				if (lKey == TDConfig.BLDMAKE) {
				 
				} else if (lKey == TDConfig.CLEAN) {
				
				} else if (lKey == TDConfig.PLATSEC) {
				
				} else if (lKey == TDConfig.SYS_BIN) {
				
				} else if (lKey == TDConfig.TEST_EXECUTE) {
				
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.setPrefrenceBoolean(String, boolean)'
	 */
	public void testSetPrefrenceBoolean() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing setPreferenceBoolean with key: " + lKey);
				CONFIG.setPreferenceBoolean(lKey, true);
				if (lKey == TDConfig.BLDMAKE) {
				
				} else if (lKey == TDConfig.CLEAN) {
					 
				} else if (lKey == TDConfig.PLATSEC) {
					
				} else if (lKey == TDConfig.SYS_BIN) {
					 
				} else if (lKey == TDConfig.TEST_EXECUTE) {
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getPreferenceInteger(String)'
	 */
	public void testGetPreferenceInteger() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing getPreferenceInteger with key: " + lKey);
				int lValue = CONFIG.getPreferenceInteger(lKey);
				if (lKey == TDConfig.JOB_ID) {
					 
				} else if (lKey == TDConfig.RUN_NUMBER) {
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.setPrefrenceInteger(String, int)'
	 */
	public void testSetPrefrenceInteger() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing setPreferenceInteger with key: " + lKey);
				CONFIG.setPreferenceInteger(lKey, 55);
				if (lKey == TDConfig.JOB_ID) {
					
				} else if (lKey == TDConfig.RUN_NUMBER) {
					 
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getPreferenceFile(String)'
	 */
	public void testGetPreferenceFile() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing getPreferenceFile with key: " + lKey);
				File lValue = CONFIG.getPreferenceFile(lKey);
				if (lKey == TDConfig.EPOC_ROOT) {
					 
				} else if (lKey == TDConfig.REPOSITORY_ROOT) {
					 
				} else if (lKey == TDConfig.RESULT_ROOT) {
					 
				} else if (lKey == TDConfig.SOURCE_ROOT) {
					 
				} else if (lKey == TDConfig.TARGET_TEST) {
					 
				} else if (lKey == TDConfig.TEST_PACKAGE) {
					 
				} else if (lKey == TDConfig.WORKING_PATH) {
					 
				} else if (lKey == TDConfig.XML_ROOT) {
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.setPrefrenceFile(String, File)'
	 */
	public void testSetPrefrenceFile() {
		for (int lKey = 0; lKey < TDConfig.PREFERENCE_KEYS.length; lKey++) {
			try {
				System.out.println("Testing setPreferenceFile with key: " + lKey);
				CONFIG.setPreferenceFile(lKey, new File("."));
				if (lKey == TDConfig.EPOC_ROOT) {
					
				} else if (lKey == TDConfig.REPOSITORY_ROOT) {
					
				} else if (lKey == TDConfig.RESULT_ROOT) {
					
				} else if (lKey == TDConfig.SOURCE_ROOT) {
					
				} else if (lKey == TDConfig.TARGET_TEST) {
					 
				} else if (lKey == TDConfig.TEST_PACKAGE) {
					 
				} else if (lKey == TDConfig.WORKING_PATH) {
					
				} else if (lKey == TDConfig.XML_ROOT) {
					
				} else {
					System.err.println(lKey + " did not produce an error.");
					fail();
				}
			} catch (ParseException e) {
				// Correct behaviour
				System.out.println(lKey + " failed correctly with message: "  + e.getMessage());
			}
		}
	}
	
	public void testSetGetPreferenceCorrect() {
		try {
			CONFIG.setPreference(TDConfig.BUILD_NUMBER, "A");
		} catch (ParseException e) {
			// Correct Behaviour: Cannot set build number
		}
		
		try {
			// EpocRoot
			CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT), VALID_DIR);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Bldmake
			CONFIG.setPreferenceBoolean(TDConfig.BLDMAKE, true);
			assertEquals(CONFIG.isPreference(TDConfig.BLDMAKE), true);
			CONFIG.setPreferenceBoolean(TDConfig.BLDMAKE, false);
			assertEquals(CONFIG.isPreference(TDConfig.BLDMAKE), false);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Build Number
			assertEquals(CONFIG.getPreference(TDConfig.BUILD_NUMBER), Utils.getBuildNumber(CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT)));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Clean
			CONFIG.setPreferenceBoolean(TDConfig.CLEAN, true);
			assertEquals(CONFIG.isPreference(TDConfig.CLEAN), true);
			CONFIG.setPreferenceBoolean(TDConfig.CLEAN, false);
			assertEquals(CONFIG.isPreference(TDConfig.CLEAN), false);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Client
			CONFIG.setPreference(TDConfig.CLIENT, VALID_STRING);
			assertEquals(CONFIG.getPreference(TDConfig.CLIENT), VALID_STRING);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Entry Point Address
			CONFIG.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, CORRECT_NEW_ROOT_ADDRESS);
			assertEquals(CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS), CORRECT_NEW_ROOT_ADDRESS);
			CONFIG.setPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS, CORRECT_OLD_ROOT_ADDRESS);
			assertEquals(CONFIG.getPreferenceURI(TDConfig.ENTRY_POINT_ADDRESS), CORRECT_OLD_ROOT_ADDRESS);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	
		// EpocRoot: First test above
		
		try {
			// JobId
			CONFIG.setPreferenceInteger(TDConfig.JOB_ID, 78);
			assertEquals(CONFIG.getPreferenceInteger(TDConfig.JOB_ID), 78);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Kernel
			CONFIG.setPreference(TDConfig.KERNEL, EKA1);
			assertEquals(CONFIG.getPreference(TDConfig.KERNEL), EKA1);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Mode
			CONFIG.setPreference(TDConfig.MODE, VALID_STRING);
			assertEquals(CONFIG.getPreference(TDConfig.MODE), VALID_STRING);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Platform
			CONFIG.setPreference(TDConfig.PLATFORM, ARMV5);
			assertEquals(CONFIG.getPreference(TDConfig.PLATFORM), ARMV5);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// PlatSec
			CONFIG.setPreferenceBoolean(TDConfig.PLATSEC, true);
			assertEquals(CONFIG.isPreference(TDConfig.PLATSEC), true);
			CONFIG.setPreferenceBoolean(TDConfig.PLATSEC, false);
			assertEquals(CONFIG.isPreference(TDConfig.PLATSEC), false);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Repository Root
			CONFIG.setPreferenceFile(TDConfig.REPOSITORY_ROOT, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.REPOSITORY_ROOT), VALID_DIR);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Result Root
			CONFIG.setPreferenceFile(TDConfig.RESULT_ROOT, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.RESULT_ROOT), VALID_DIR);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Run Number
			CONFIG.setPreferenceInteger(TDConfig.RUN_NUMBER, 83);
			assertEquals(CONFIG.getPreferenceInteger(TDConfig.RUN_NUMBER), 83);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Server
			CONFIG.setPreference(TDConfig.SERVER, VALID_STRING);
			assertEquals(CONFIG.getPreference(TDConfig.SERVER), VALID_STRING);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// sis/bin
			CONFIG.setPreferenceBoolean(TDConfig.SYS_BIN, true);
			assertEquals(CONFIG.isPreference(TDConfig.SYS_BIN), true);
			CONFIG.setPreferenceBoolean(TDConfig.SYS_BIN, false);
			assertEquals(CONFIG.isPreference(TDConfig.SYS_BIN), false);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Source Root
			CONFIG.setPreferenceFile(TDConfig.SOURCE_ROOT, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT), VALID_DIR);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// TargetTest
			CONFIG.setPreferenceFile(TDConfig.TARGET_TEST, ROM);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.TARGET_TEST), ROM);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// TestExecute
			CONFIG.setPreferenceBoolean(TDConfig.TEST_EXECUTE, true);
			assertEquals(CONFIG.isPreference(TDConfig.TEST_EXECUTE), true);
			CONFIG.setPreferenceBoolean(TDConfig.TEST_EXECUTE, false);
			assertEquals(CONFIG.isPreference(TDConfig.TEST_EXECUTE), false);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// TestExecute Dependencies
			CONFIG.setPreference(TDConfig.TEST_EXECUTE_DEPENDENCIES, ARMV5);
			assertEquals(CONFIG.getPreference(TDConfig.TEST_EXECUTE_DEPENDENCIES), ARMV5);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// TestPackage
			CONFIG.setPreferenceFile(TDConfig.TEST_PACKAGE, ROM);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.TEST_PACKAGE), ROM);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Transport
			CONFIG.setPreference(TDConfig.TRANSPORT, SERIAL1);
			assertEquals(CONFIG.getPreference(TDConfig.TRANSPORT), SERIAL1);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// UCC Address
			CONFIG.setPreference(TDConfig.UCC_IP_ADDRESS, VALID_STRING);
			assertEquals(CONFIG.getPreference(TDConfig.UCC_IP_ADDRESS), VALID_STRING);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Variant
			CONFIG.setPreference(TDConfig.VARIANT, UDEB);
			assertEquals(CONFIG.getPreference(TDConfig.VARIANT), UDEB);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Wintap
			CONFIG.setPreference(TDConfig.WINTAP, VALID_STRING);
			assertEquals(CONFIG.getPreference(TDConfig.WINTAP), VALID_STRING);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// Working Path
			CONFIG.setPreferenceFile(TDConfig.WORKING_PATH, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.WORKING_PATH), VALID_DIR);	
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			// XML Root
			CONFIG.setPreferenceFile(TDConfig.XML_ROOT, VALID_DIR);
			assertEquals(CONFIG.getPreferenceFile(TDConfig.XML_ROOT), VALID_DIR);	
			
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getName()'
	 */
	public void testGetName() {

	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getVersion()'
	 */
	public void testGetVersion() {
		assertEquals(CONFIG.getVersion(), NO_MANIFEST);
	}

	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.getCopyright()'
	 */
	public void testGetCopyright() {
		assertEquals(CONFIG.getCopyright(), NO_MANIFEST);
	}
	
	/*
	 * Test method for 'com.symbian.utils.ConfigUtils.clearConfig()'
	 */
	public void testClearConfig() {
		try {
			CONFIG.clearConfig();
			
			String lBuildNumber = CONFIG.getPreference(TDConfig.BUILD_NUMBER);
			if (lBuildNumber != null) {
				fail();
			}
		} catch (NullPointerException e) {
			// Correct behaviour
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

}
