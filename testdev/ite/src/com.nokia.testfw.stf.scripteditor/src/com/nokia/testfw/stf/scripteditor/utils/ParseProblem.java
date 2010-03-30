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


package com.nokia.testfw.stf.scripteditor.utils;


/**
 * Objects of this class are used to describe a found syntax problem
 *
 */
public class ParseProblem {
	
	public enum ProblemType { 
		ERROR, 
		WARNING };
	
	/**
	 * Constructor 
	 * 
	 * @param type
	 * 			ERROR/WARNING
	 * @param lineNumber
	 * 			Number of line that this problem refers to
	 * @param description
	 * 			Description of problem/solving the problem
	 */
	public ParseProblem(ProblemType type, int lineNumber, String description){
		this.type = type;
		this.lineNumber = lineNumber;
		this.description = description;
	}
		
	/**
	 * Problem type
	 */
	public ProblemType type;
	/**
	 * Line number in which problem occurs
	 */
	public int lineNumber;
	/**
	 * Problem description
	 */
	public String description;
}