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

package com.symbian.driver;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

import junit.framework.TestCase;

import com.symbian.driver.core.environment.TDConfig;

/**
 * @author EngineeringTools
 */
public class TestDriverTest extends TestCase {
	
	private static final File XML_ROOT = new File("data/xml/plattest");
	private static final File SOURCE_ROOT = new File("data");
	private static final File RESULT_ROOT = new File("data/result");
	private static final File REPOSITORY_ROOT = new File("data/repos");
	private static final File EPOC_ROOT = new File("h:/");
	private static final File DRIVER_FILE = new File("data/xml/JUnitTest.driver");
	
	private boolean NEGATIVE_TEST = false;
	
	private final TDConfig CONFIG = TDConfig.getInstance();

	protected void setUp() throws Exception {
		super.setUp();
		
		NEGATIVE_TEST = false;
		
		CONFIG.setPreferenceFile(TDConfig.ENTRY_POINT_ADDRESS, DRIVER_FILE);
		CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, EPOC_ROOT);
		CONFIG.setPreferenceFile(TDConfig.REPOSITORY_ROOT, REPOSITORY_ROOT);
		CONFIG.setPreferenceFile(TDConfig.RESULT_ROOT, RESULT_ROOT);
		CONFIG.setPreferenceFile(TDConfig.SOURCE_ROOT, SOURCE_ROOT);
		CONFIG.setPreferenceFile(TDConfig.XML_ROOT, XML_ROOT);
		
		LogManager.getLogManager().reset();
		LogManager.getLogManager().getLogger("").addHandler(new ConsoleHandler() {

			public void close() throws SecurityException {
				super.close();
			}

			public void flush() {
				super.flush();
			}

			public void publish(LogRecord aRecord) {
				if (aRecord.getLevel().intValue() >= Level.WARNING.intValue() && !NEGATIVE_TEST) {
					fail();
				}
				super.publish(aRecord);
			}
			
		});
	}
	
	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMain() {
		NEGATIVE_TEST = true;
		TestDriver.main(new String[] {});
		TestDriver.main(null);
	}
	
	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMainBuild() {
		TestDriver.main(new String[] {"build", "-b", "urel", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"build", "-p", "armv5", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"build", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"build"});
	}

	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMainRun() {
		TestDriver.main(new String[] {"run", "-p", "armv5", "-b", "urel", "-s", "plattest.pt_coretests", "-t", "serial3"});
		TestDriver.main(new String[] {"run", "-b", "urel", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"run", "-p", "armv5", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"run", "-p", "armv5", "-b", "urel"});
		TestDriver.main(new String[] {"run"});
		TestDriver.main(new String[] {"run", "-p", "winscw", "-b", "urel", "-s", "plattest.pt_coretests", "-t", "serial3"});
		TestDriver.main(new String[] {"run", "-b", "urel", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"run", "-p", "winscw", "-s", "plattest.pt_coretests"});
		TestDriver.main(new String[] {"run", "-p", "winscw", "-b", "urel"});
	}
	
	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMainPrint() {
		TestDriver.main(new String[] {"print", "-s", "plattest.pt_coretests"});
	}
	
	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMainConfig() {
		TestDriver.main(new String[] {"config", "-p", "armv5", "-b", "urel", "-s", "plattest.pt_coretests"});
	}
	
	/**
	 * Test method for 'com.symbian.driver.controller.TestDriver.main(String[])'
	 */
	public void testMainInstall() {
		TestDriver.main(new String[] {"install"});
	}
}
