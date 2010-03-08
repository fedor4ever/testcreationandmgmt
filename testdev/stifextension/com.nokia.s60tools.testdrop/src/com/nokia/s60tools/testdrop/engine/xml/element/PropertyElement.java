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
 * Provides property element parts that based on the device
 */
public class PropertyElement extends BaseElement {

	private static final String PROPERTY_ELEMENT = "property"; 
	private final String NAME_ATTRIBUTE = "name"; 
	private final String VALUE_ATTRIBUTE = "value"; 
	private String name;
	private String value;
	private DeviceElement deviceElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param value
	 *            value for value attribute
	 * @param deviceElement
	 *            Device element is the property element's parent
	 * 
	 */
	public PropertyElement(String name, String value,
			DeviceElement deviceElement) {
		super(PROPERTY_ELEMENT);
		this.name = name;
		this.value = value;
		this.deviceElement = deviceElement;
		createElement();
	}

	/**
	 * Sets element's attributes from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(NAME_ATTRIBUTE, name);
		getElement().setAttribute(VALUE_ATTRIBUTE, value);
		deviceElement.getElement().appendChild(getElement());
	}
}
