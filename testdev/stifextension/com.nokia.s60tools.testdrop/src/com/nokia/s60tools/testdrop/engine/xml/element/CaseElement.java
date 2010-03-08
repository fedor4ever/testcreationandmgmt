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
 * Provides case element parts that based on the plan area
 */
public class CaseElement extends BasePlanElement {

	private static final String CASE_ELEMENT = "case"; 
	private SetElement setElement;

	/**
	 * Constructors class with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param setElement
	 *            setElement is the case element's parent
	 * @param targetElement
	 *            target element part for the element
	 * 
	 */
	public CaseElement(String name, SetElement setElement,
			TargetElement targetElement) {
		super(CASE_ELEMENT, name, harness, targetElement);
		caseId++;
		this.setElement = setElement;
		createElement();
	}

	/**
	 * Constructors class with mandatory and optional parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param setElement
	 *            setElement is the case element's parent element
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 * 
	 */
	public CaseElement(String name, SetElement setElement,
			TargetElement targetElement, String enabled, int passrate) {
		super(CASE_ELEMENT, name, harness, targetElement, enabled, passrate);
		caseId++;
		this.setElement = setElement;
		createElement();
	}

	/**
	 * Sets element's attributes from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(
				ID_ATTRIBUTE,
				testRunId + PUNCTUATION + planId + PUNCTUATION + sessionId
						+ PUNCTUATION + setId + PUNCTUATION + caseId);
		setElement.getElement().appendChild(getElement());
	}
}
