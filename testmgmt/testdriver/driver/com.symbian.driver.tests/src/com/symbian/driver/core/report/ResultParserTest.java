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

package com.symbian.driver.core.report;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import com.symbian.utils.XSLTUtils;

/**
 * @author TimothyL
 *
 */
public class ResultParserTest extends TestCase {

	private static final String NAME = "name";

	/**
	 * Test method for 'com.symbian.resultsparser.ResultParser.ParseResults()'
	 */
	public void testParseTefResults() {
//		try {
//			TefReport lErrors = (TefReport) ResultParser.parseResults(new File("test/TEFTest.data"), new File(".").getAbsolutePath().length(), "task", NAME, true, 12);
//			assertNotNull(lErrors);
//			
//			assertEquals(lErrors.getPass(),1);
//			assertEquals(lErrors.getFail(),2);
//			assertEquals(lErrors.getTimeout(),3);
//			assertEquals(lErrors.getPanic(),4);
//			assertEquals(lErrors.getUnexecuted(),5);
//			assertEquals(lErrors.getUnknown(),6);
//			assertEquals(lErrors.getAbort(),7);
//			assertEquals(lErrors.getInconclusive(),8);
//			assertEquals(lErrors.getName(), NAME);
//			
//		} catch (FileNotFoundException lFileNotFoundException) {
//			fail();
//		}
	}
	
	/**
	 * 
	 */
	public void testParseRTestResultsPass() {

//		try {
//			RtestReport lErrors = (RtestReport) ResultParser.parseResults(new File("test/TEFTest.data"), new File(".").getAbsolutePath().length(), "task", NAME, true, 12);
//			assertNotNull(lErrors);
//			
//			assertTrue(lErrors.isPass());
//			assertEquals(lErrors.getName(),NAME);
//			
//		} catch (FileNotFoundException lFileNotFoundException) {
//			fail();
//		}
	}
	
	public void testXSLTtransform() {
		try {
			XSLTUtils.transformXml(
					new File("N:\\results\\M04032_Symbian_OS_v9.2\\armv5\\urel\\330\\run330_M04032_Symbian_OS_v9.2.xml"),
					new File("."),
					"test.html",
					"/resource/xslt/oldTDReport.xsl", Result.class);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
