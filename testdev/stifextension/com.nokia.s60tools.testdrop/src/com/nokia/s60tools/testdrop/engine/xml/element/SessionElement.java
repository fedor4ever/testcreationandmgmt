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
 * Provides Session element parts that is based on the plan element part
 */
public class SessionElement extends BasePlanElement {

	private static final String SESSION_ELEMENT = "session"; 
	private TestPlanElement testPlanElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param testPlanElement
	 *            Test Plan element is the Session element's parent
	 * @param targetElement
	 *            target element part for the element
	 * 
	 */
	public SessionElement(String name, TestPlanElement testPlanElement,
			TargetElement targetElement) {
		super(SESSION_ELEMENT, name, harness, targetElement);
		sessionId++;
		this.testPlanElement = testPlanElement;
		createElement();
	}

	/**
	 * Constructor with mandatory and optional parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param testPlanElement
	 *            Test Plan element is the Session element's parent
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 */
	public SessionElement(String name, TestPlanElement testPlanElement,
			TargetElement targetElement, String enabled, int passrate) {
		super(SESSION_ELEMENT, name, harness, targetElement, enabled, passrate);
		sessionId++;
		this.testPlanElement = testPlanElement;
		createElement();
	}

	/**
	 * Sets element's attribute from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(ID_ATTRIBUTE,
				testRunId + PUNCTUATION + planId + PUNCTUATION + sessionId);
		testPlanElement.getElement().appendChild(getElement());
	}
}
