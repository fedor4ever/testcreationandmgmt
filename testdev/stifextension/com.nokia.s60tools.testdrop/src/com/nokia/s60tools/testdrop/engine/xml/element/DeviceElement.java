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
 * Provides device element parts that based on the plan area
 */
public class DeviceElement extends BaseElement {

	private static final String DEVICE_ELEMENT = "device"; 
	private final String RANK_ATTRIBUTE = "rank"; 
	private final String ALIAS_ATTRIBUTE = "alias"; 
	private String rank;
	private String alias;
	private TargetElement targetElement;

	/**
	 * Constructors class with mandatory parameters.
	 * 
	 * @param rank
	 *            value for rank attribute
	 * @param alias
	 *            value for alias attribute
	 * @param targetElement
	 *            Target element is the device element's parent
	 */
	public DeviceElement(String rank, String alias, TargetElement targetElement) {
		super(DEVICE_ELEMENT);
		this.rank = rank;
		this.alias = alias;
		this.targetElement = targetElement;
		createElement();
	}

	/**
	 * Sets element's attributes from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(RANK_ATTRIBUTE, rank);
		getElement().setAttribute(ALIAS_ATTRIBUTE, alias);
		targetElement.getElement().appendChild(getElement());
	}
}
