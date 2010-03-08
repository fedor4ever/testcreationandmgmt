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


package com.nokia.s60tools.testdrop.engine.xml.value;

/**
 * Contains hardware property values
 * 
 */
public class HardwarePropertyValue {

	private String name;
	private String value;

	/**
	 * Constructs class with mandatory parameter
	 * 
	 * @param name
	 *            for hardware name's value
	 * @param value
	 *            for hardware value's value
	 */
	public HardwarePropertyValue(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns name
	 * 
	 * @return name value
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a new value name
	 * 
	 * @param name
	 *            a new name value
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns value
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets a new value
	 * 
	 * @param value
	 *            a new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
