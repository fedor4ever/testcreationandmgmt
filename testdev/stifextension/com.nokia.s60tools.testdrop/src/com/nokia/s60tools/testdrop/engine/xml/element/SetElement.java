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
 * Provides Set element parts that is based on the plan element part
 */
public class SetElement extends BasePlanElement {

	private static final String SET_ELEMENT = "set"; 
	private SessionElement sessionElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param sessionElement
	 *            Session element is the Set element's parent
	 * @param targetElement
	 *            target element part for the element
	 * 
	 */
	public SetElement(String name, SessionElement sessionElement,
			TargetElement targetElement) {
		super(SET_ELEMENT, name, harness, targetElement);
		setId++;
		this.sessionElement = sessionElement;
		createElement();
	}

	/**
	 * Constructor with mandatory and optional parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param sessionElement
	 *            Session element is the Set element's parent
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 */
	public SetElement(String name, SessionElement sessionElement,
			TargetElement targetElement, String enabled, int passrate) {
		super(SET_ELEMENT, name, harness, targetElement, enabled, passrate);
		setId++;
		this.sessionElement = sessionElement;
		createElement();
	}

	/**
	 * Sets element's attribute from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(
				ID_ATTRIBUTE,
				testRunId + PUNCTUATION + planId + PUNCTUATION + sessionId
						+ PUNCTUATION + setId);
		sessionElement.getElement().appendChild(getElement());
	}
}
