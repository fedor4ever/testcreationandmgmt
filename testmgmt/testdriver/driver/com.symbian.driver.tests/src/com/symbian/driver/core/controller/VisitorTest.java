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

package com.symbian.driver.core.controller;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.DriverFactory;
import com.symbian.driver.Task;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.controller.PCVisitor;
import com.symbian.driver.core.controller.PrintVisitor;
import com.symbian.driver.core.controller.SymbianVisitor;
import com.symbian.driver.core.environment.TDConfig;

/**
 * @author EngineeringTools
 */
public class VisitorTest extends TestCase {
	
	private DriverFactory iDriverFactory = DriverFactory.eINSTANCE;
	
	private final String iEntryPointAddress = "A";
	
	private Task iTask = null;
	
	private final TDConfig CONFIG = TDConfig.getInstance();

	public void setUp() {
		try {
			CONFIG.setPreferenceFile(TDConfig.ENTRY_POINT_ADDRESS, new File(""));
			
			iTask = ResourceLoader.load();
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
		
	}

	/**
	 * Test method for 'com.symbian.test.driver.run.BuildVisitorTest.Visitor()'
	 */
	public void testVisitor() {
		try {
			CONFIG.setPreferenceFile(TDConfig.ENTRY_POINT_ADDRESS, new File(""));
			
			System.out.println("PRINT visitor:");
			Visitor lVisitor = new PrintVisitor();
			lVisitor.start(iTask);
			
			System.out.println("HOST visitor (build):");
			lVisitor = new PCVisitor();
			((PCVisitor) lVisitor).setRBuild(false);
			lVisitor.start(iTask);
			
			System.out.println("HOST visitor (rbuild):");
			lVisitor = new PCVisitor();
			((PCVisitor) lVisitor).setRBuild(true);
			lVisitor.start(iTask);
			
			System.out.println("DEVICE visitor:");
			lVisitor = new SymbianVisitor();
			lVisitor.start(iTask);
			
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for 'com.symbian.test.driver.run.BuildVisitorTest.Visitor()'
	 */
	public void testAddressVisitor() {
		try {
			CONFIG.setPreferenceFile(TDConfig.ENTRY_POINT_ADDRESS, new File(""));
			
			System.out.println("PRINT visitor:");
			Visitor lVisitor = new PrintVisitor();
			lVisitor.start(iTask);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for 'com.symbian.test.driver.run.BuildVisitorTest.Visitor()'
	 */
	public void testNegativeAddressVisitor() {
		try {
			CONFIG.setPreferenceFile(TDConfig.ENTRY_POINT_ADDRESS, new File(""));
		} catch (ParseException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PRINT visitor:");
		try {
			Visitor lVisitor = new PrintVisitor();
			lVisitor.start(iTask);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	/**
	 * 
	 */
	public void testDoSubTask() {
		fail();
	}
}
