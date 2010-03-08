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

package com.symbian.driver.core;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.DriverFactory;
import com.symbian.driver.Reference;
import com.symbian.driver.Task;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.utils.Utils;

/**
 * @author EngineeringTools
 * 
 */
public class ResourceLoaderTest extends TestCase {

	private static final File EPOC_ROOT = new File("h:/");
	private static final File XML_ROOT_OLD = new File("data/xml/plattest");
	private static final String XML_ROOT_NEW = "file:/${sourceroot}/xml/JUnitTset.driver";
	private static final File REPOSITORY = new File("data/repos");
	private static final File RESULT = new File("data/result");
	private static final File SOURCE_ROOT = new File("");

	private final TDConfig CONFIG = TDConfig.getInstance();

	protected void setUp() throws Exception {
		super.setUp();

		CONFIG.setPreferenceFile(TDConfig.EPOC_ROOT, EPOC_ROOT);
		CONFIG.setPreferenceFile(TDConfig.REPOSITORY_ROOT, REPOSITORY);
		CONFIG.setPreferenceFile(TDConfig.RESULT_ROOT, RESULT);
		CONFIG.setPreferenceFile(TDConfig.SOURCE_ROOT, SOURCE_ROOT);
		CONFIG.setPreferenceFile(TDConfig.XML_ROOT, XML_ROOT_OLD);
	}

	/**
	 * Test method for 'com.symbian.driver.controller.ResourceLoader.load()'
	 */
	public void testLoad() {
		Task lTask = null;
		try {
			lTask = ResourceLoader.load();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		if (lTask == null && lTask.getTask().size() != 0) {
			fail();
		}
	}

	/**
	 * Test method for
	 * 'com.symbian.driver.controller.ResourceLoader.loadReference(Reference,
	 * Visitor)'
	 */
	public void testLoadReference() {
		Reference lReference = DriverFactory.eINSTANCE.createReference();
//		lReference.setURI(XML_ROOT_NEW);
//
//		try {
//			ResourceLoader.loadReference(lReference);
//			ResourceLoader.loadReference(lReference);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
	}

	/**
	 * Test method for
	 * 'com.symbian.driver.controller.ResourceLoader.checkPCPath(String,
	 * boolean)'
	 */
	public void testCheckPCPath() {
		File[] lFile = null;

		try {
			// Get standard file
			lFile = ModelUtils.checkPCPath("VisitorTest.java", false);

			if (lFile.length != 1 || !lFile[0].isFile()) {
				fail();
			}

			// Get sourceroot file
			lFile = CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT).listFiles();

			lFile = ModelUtils.checkPCPath(lFile[0].getName(), false);

			if (lFile.length != 1 || !lFile[0].isFile()) {
				fail();
			}

			// Get epocroot file
			lFile = CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).listFiles();

			lFile = ModelUtils.checkPCPath(lFile[0].getName(), false);

			if (lFile.length != 1 || !lFile[0].isFile()) {
				fail();
			}

			// Get Standard wildcard file
			lFile = ModelUtils.checkPCPath("*.java", true);

			if (lFile.equals(Utils.getFilesWithExtension(new File("."), ".java"))) {
				fail();
			}

			// Use build variable 
			fail();
			// Use variant variable 

			// Use epocroot variable 

			// Use sourceroot variable 

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
}
