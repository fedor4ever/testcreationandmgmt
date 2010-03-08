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
 * Provides param element parts that based on the step area
 */
public class ParamElement extends BaseElement {

	private static final String PARAM_ELEMENT = "param"; 
	private String attribute;
	private String value;
	private Element paramsElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param attribute
	 *            name for element attribute
	 * @param value
	 *            value for above attribute
	 * @param paramsElement
	 *            Params element is the param element's parent
	 * 
	 */
	public ParamElement(String attribute, String value, Element paramsElement) {
		super(PARAM_ELEMENT);
		this.attribute = attribute;
		this.value = value;
		this.paramsElement = paramsElement;
		createElement();
	}

	/**
	 * Sets element's attribute from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(attribute, value);
		paramsElement.appendChild(getElement());
	}
}
