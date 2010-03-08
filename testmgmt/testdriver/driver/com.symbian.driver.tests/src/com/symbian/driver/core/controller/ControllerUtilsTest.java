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
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.controller.utils.ControllerUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;

public class ControllerUtilsTest extends TestCase {
	
	/** Generic settings/configuration. */
    public static TDConfig CONFIG = TDConfig.getInstance();

	/*
	 * Test method for 'com.symbian.driver.core.controller.ControllerUtils.getCurrentAddress(Execute)'
	 */
	public void testGetCurrentAddress() {

	}

	/*
	 * Test method for 'com.symbian.driver.core.controller.ControllerUtils.getBaseDirectory(Execute, int)'
	 */
	public void testGetBaseDirectory() {

	}

	/*
	 * Test method for 'com.symbian.driver.core.controller.ControllerUtils.getEpoc32RelPlatVar()'
	 */
	public void testGetEpoc32RelPlatVar() {

	}

	/**
	 * Test method for 'com.symbian.driver.core.controller.ControllerUtils.checkPCPath(String, boolean)'
	 */
	public void testCheckPCPath() {
		try {
			File lDir = new File("src/com/symbian/driver/core/controller/C*.java");
			File[] lFile = ModelUtils.checkPCPath(lDir.toString(), true);
			
			System.out.println("The List of files is: ");
			for (int i = 0; i < lFile.length; i++) {
				System.out.println("\tFile: " + lFile[i].getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Test method for
	 * 'com.symbian.driver.controller.ResourceLoader.subsituteVariables(String)'
	 */
	public void testSubsituteVariables() {
		try {
			//checkSubsitute("", "", false);
			checkSubsitute("${sourceroot}", CONFIG.getPreferenceFile(TDConfig.SOURCE_ROOT).getAbsolutePath(), true);
			checkSubsitute("${xmlroot}", CONFIG.getPreferenceFile(TDConfig.XML_ROOT).getAbsolutePath(), true);
			checkSubsitute("${epocroot}", CONFIG.getPreferenceFile(TDConfig.EPOC_ROOT).getAbsolutePath(), true);
			checkSubsitute("${platform}", CONFIG.getPreference(TDConfig.PLATFORM), true);
			checkSubsitute("${build}", CONFIG.getPreference(TDConfig.VARIANT), true);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * @param aVariable 
	 * @param aSubsituteVariable 
	 * @param aCorrect 
	 * @throws ParseException 
	 */
	private void checkSubsitute(final String aVariable, final String aSubsituteVariable, boolean aCorrect) throws ParseException {
		String lOrigPathHead = "df:,l + n$$k }}$ {{a ${}os";
		String lOrigPathTail = "asd${ssourceroot}fko{xmlroot}as;ndfl;";
		String lBefore = lOrigPathHead + aVariable + lOrigPathTail;
		String lAfter = lOrigPathHead + aSubsituteVariable.replace('\\', '/') + lOrigPathTail;
		
		System.out.println("Before: " + lBefore);
		String lPath = ModelUtils.subsituteVariables(lBefore);
		System.out.println("After: " + lPath);
		System.out.println("Correct: " + lAfter + "\n");
		
		if (aCorrect) {
			if (lPath == null || lPath.equals(lBefore) || !lPath.equals(lAfter)) {
				fail();
			}
		} else {
			if (lPath == null || !lPath.equals(lBefore)  || lPath.equals(lAfter)) {
				fail();
			}
		}
	}

}
