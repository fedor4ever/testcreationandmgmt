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

import org.w3c.dom.Element;

/**
 * Provides file element parts that based on the test run area
 */
public class FileElement extends BaseElement {

	private static final String FILE_ELEMENT = "file"; 
	private String textContent;
	private Element parent;

	/**
	 * Constructors class with mandatory parameters.
	 * 
	 * @param textContent
	 *            value for element context
	 * @param parent
	 *            parent element
	 * 
	 */
	public FileElement(String textContent, Element parent) {
		super(FILE_ELEMENT);
		this.textContent = textContent;
		this.parent = parent;
		createElement();
	}

	/**
	 * Sets element content from the class data
	 */
	protected void createElement() {
		getElement().setTextContent(textContent);
		parent.appendChild(getElement());
	}
}
