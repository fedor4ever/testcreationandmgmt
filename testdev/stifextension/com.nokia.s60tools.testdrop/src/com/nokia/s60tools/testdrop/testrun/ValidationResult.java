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
package com.nokia.s60tools.testdrop.testrun;

/**
 * Class for describing the result of validation of a test module
 *
 */
public class ValidationResult {

	private String message;
	private ValidationProblem validationProblem;
	
	/**
	 * Constructor
	 * 
	 * @param validationProblem
	 * 				Passes the problem type
	 * @param message
	 * 				Passes the message
	 */
	public ValidationResult(ValidationProblem validationProblem, String message) {
		this.validationProblem = validationProblem;
		this.message = message;
	}
	
	/**
	 * message getter
	 * 
	 * @return
	 * 		message	
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * validation problem getter
	 * 
	 * @return
	 * 		validation problem
	 */
	public ValidationProblem getValidationProblem() {
		return validationProblem;
	}
	
	/**
	 * Enum for describing the type of found problem
	 *
	 */
	public enum ValidationProblem {
		NO_PROBLEM,
		NO_ATSINTERFACE_FILE,
		NO_TEST_MODULE_FILE,
		NO_INI_FILE,
	}
}
