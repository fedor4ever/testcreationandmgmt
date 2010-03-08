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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nokia.s60tools.testdrop.util.LogExceptionHandler;

/**
 * XML base element class
 */
public abstract class BaseElement {

	protected final static int testRunId = 1;
	protected static Document dom;
	protected String elementName;
	private Element currentElement;

	/**
	 * Constructs an XML element that based on the given parameter.
	 * 
	 * @param elementName
	 *            element name
	 */
	protected BaseElement(String elementName) {
		this.elementName = elementName;
		currentElement = dom.createElement(elementName);
	}

	/**
	 * Initialize method for document
	 */
	public static void initializeDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.newDocument();

		} catch (ParserConfigurationException pce) {
			LogExceptionHandler.log("initializeDom() " + pce.getMessage()); 
		}
	}

	/**
	 * Abstract method that have to implement in inherited class
	 */
	protected abstract void createElement();

	/**
	 * Returns document instance
	 */
	public Document getDom() {
		return dom;
	}

	/**
	 * Returns element from current object
	 */
	public Element getElement() {
		return currentElement;
	}

	/**
	 * Dispose method that should be called before to remove that instance
	 */
	public void dispose() {
		dom = null;
	}
}
