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

import com.symbian.driver.core.environment.TDConfig;

import junit.framework.TestCase;

public class ImportUtilsTest extends TestCase {
	
	/** Generic settings/configuration. */
	public static TDConfig CONFIG = TDConfig.getInstance();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		CONFIG.setPreference(TDConfig.EPOC_ROOT, "P:/");
	}

	public void testGetNextFile() {
		assertEquals(
				"c:/aPath/bPath/testexecuteservers/aServer.xml",
				ImportUtils.getNextFile("c:/aPath/bPath.xml", "aServer", true));
		
		assertEquals("c:/aPath", ImportUtils.getNextFile("c:/aPath", "aServer", false));
	}

	public void testCreateValidPath() {
		fail("Not yet implemented");
	}

	public void testDtdResolver() {
		fail("Not yet implemented");
	}

	public void testFixXmlFile() {
		fail("Not yet implemented");
	}

}
