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
 * XML element class for param element that is used by action element 
 * 
 */
public class ActionParamElement extends BaseElement {

	private static final String PARAM_ELEMENT = "param"; 

	private final String NAME = "name"; 
	private final String VALUE = "value"; 

	private String name;
	private String value;

	/**
	 * Constructors with mandatory parameter
	 * 
	 * @param name
	 *            for name attribute
	 * @param value
	 *            for value attribute
	 */
	public ActionParamElement(String name, String value) {
		super(PARAM_ELEMENT);
		this.name = name;
		this.value = value;
		createElement();
	}

	/**
	 * Creates element content
	 */
	protected void createElement() {
		getElement().setAttribute(NAME, name);
		getElement().setAttribute(VALUE, value);
	}
}
