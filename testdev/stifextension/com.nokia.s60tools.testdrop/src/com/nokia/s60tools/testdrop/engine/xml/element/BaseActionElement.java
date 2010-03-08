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
 * XML base action element class for action type element
 */
public class BaseActionElement extends BaseElement {

	public static final String TYPE_ELEMENT = "type"; 
	public static final String PARAMS_ELEMENT = "params"; 
	private String type;
	private Element paramsElement;

	/**
	 * Constructors with mandatory parameter
	 * 
	 * @param elementName
	 *            element name
	 * @param type
	 *            value for type element
	 */
	BaseActionElement(String elementName, String type) {
		super(elementName);
		this.type = type;
	}

	/**
	 * Creates element content
	 */
	protected void createElement() {
		Element typeElement = dom.createElement(TYPE_ELEMENT);
		typeElement.setTextContent(type);
		getElement().appendChild(typeElement);
	}

	/**
	 * Adds param elements
	 * 
	 * @param paramElement
	 *            element
	 */
	public void addParam(Element paramElement) {
		if (paramsElement == null) {
			paramsElement = dom.createElement(PARAMS_ELEMENT);
			getElement().appendChild(paramsElement);
		}
		paramsElement.appendChild(paramElement);
	}
}
