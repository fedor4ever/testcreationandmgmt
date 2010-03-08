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

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EMap;

/**
 * @author EngineeringTools
 *
 */
public interface ResultParser {
	
	/** Matches literal "whitespace = whitespace any number". */
	String EQUALS = "\\s*=\\s*(\\d*)";
	
	/** Matches literal "whitespace : whitespace any number". */
	String DOUBLEDOT = "\\s*:\\s*(\\d*)";
	
	/** Matches literal "attemptedTestStep". */
	String ATTEMPTED_TEST_STEP = "attemptedTestStep";
	
	/** Matches literal "passedTestStep". */
	String PASSED_TEST_STEP = "passedTestStep";
	
	/** Matches literal "failedTestStep". */
	String FAILED_TEST_STEP = "failedTestStep";
	
	/** Matches literal "unkownTestStep". */
	String UNKOWN_TEST_STEP = "unkownTestStep";
	
	/** Generic Logger. */
	Logger LOGGER = Logger.getLogger(ResultParser.class.getName());
	
	/**
	 * Parses the result file.
	 * 
	 * 
	 * @param aResultRootLength 
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void parseResults(final int aResultRootLength) throws IOException, ParseException;

	/**
	 * Creates an empty EMF report.
	 */
	public void createEmptyReport();

	/**
	 * Generates any approriate summary info statments
	 * 
	 * @param aInfo The map to the Report Info block.
	 */
	public void createSummary(EMap aInfo);
}
