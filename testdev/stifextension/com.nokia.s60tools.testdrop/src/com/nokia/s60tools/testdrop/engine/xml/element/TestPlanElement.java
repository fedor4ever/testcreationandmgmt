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
 * Provides Test plan element
 */
public class TestPlanElement extends BasePlanElement {

	private static final String PLAN_ELEMENT = "plan"; 
	private TestRunElement testRunElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param harness
	 *            value for harness attribute, that is default value for all its
	 *            children element
	 * @param targetElement
	 *            target element part for the element
	 */
	public TestPlanElement(String name, String harness,
			TargetElement targetElement, TestRunElement testRunElement) {
		super(PLAN_ELEMENT, name, harness, targetElement);
		this.testRunElement = testRunElement;
		planId++;
		createElement();
	}

	/**
	 * Constructor with mandatory and optional parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param harness
	 *            value for harness attribute, that is default value for all its
	 *            children element
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 */
	public TestPlanElement(String name, String harness,
			TargetElement targetElement, TestRunElement testRunElement,
			String enabled, int passrate) {
		super(PLAN_ELEMENT, name, harness, targetElement, enabled, passrate);
		this.testRunElement = testRunElement;
		planId++;
		createElement();
	}

	/**
	 * Sets element's attribute from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(ID_ATTRIBUTE,
				testRunId + PUNCTUATION + planId);
		testRunElement.getElement().appendChild(getElement());

	}
}
