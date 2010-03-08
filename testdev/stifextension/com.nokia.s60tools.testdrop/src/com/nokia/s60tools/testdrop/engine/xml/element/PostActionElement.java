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


package com.nokia.s60tools.testdrop.engine.xml.element;

/**
 * Provides creation of the post action element content
 * 
 */
public class PostActionElement extends BaseActionElement {

	public static final String POST_ACTION_ELEMENT = "postAction"; 

	/**
	 * Constructor with mandatory parameter
	 * 
	 * @param type
	 *            value for type element
	 */
	public PostActionElement(String type) {
		super(POST_ACTION_ELEMENT, type);
		this.createElement();
	}
}
