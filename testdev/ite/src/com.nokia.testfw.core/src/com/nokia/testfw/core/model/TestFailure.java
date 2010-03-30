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
package com.nokia.testfw.core.model;

/**
 * This class represents a test failure.
 * There are several reasons
 * <li>ERROR, test error by assertion<li>
 * <li>TIMEOUT, time out during test case execution<li>
 * <li>CRASH, Test case Panic or device crash<li>
 * <li>UNKNOW, all other failures<li>
 * <p>
 * 
 * @author xiaoma
 *
 */
public class TestFailure {
	/**
	 * the failure message
	 */
	protected String message;
	
	/**
	 * the source and script file which caused the error,
	 * if null, means no error file has been located
	 */
	protected String file;
	
	/**
	 * the error number in the file. if -1, means no error has been located.
	 */
	protected int lineNum;
	
	/**
	 * the failure type
	 */
	protected Type type;
	
	/**
	 * create test failure from error message
	 * the failure type will be set to ERROR
	 * @param message, the error message
	 */
	public TestFailure(String message) {
		this(Type.ERROR, message);
	}
	
	

	/**
	 * create test failure with assigned type and message
	 * @param type, the failure type,
	 * @param message, the error message
	 * @see Type
	 */
	public TestFailure(Type type, String message) {
		this(type, message, null, -1);
	}
	
	/**
	 * create test failure
	 * @param type, the failure type
	 * @param message, error message
	 * @param file, the error file
	 * @param lineNum, the error line number
	 * @see Type
	 */
	public TestFailure(Type type, String message, String file,
			int lineNum) {
		this.type = type;
		this.message = message;
		this.file = file;
		this.lineNum = lineNum;
	}
	
	/**
	 * parse the file name and error location from error message.
	 * @return true, parse successfully
	 */
	public boolean parseFileAndLine() {
		return false;
	}
	
	
	public enum Type {ERROR, TIMEOUT, CRASH, UNKNOWN}; 
	
}
