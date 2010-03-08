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
 * Provides Step element parts that is based on the plan element part
 */
public class StepElement extends BasePlanElement {

	private static final String STEP_ELEMENT = "step"; 
	private final String PARAMS_ELEMENT = "params"; 
	private final String COMMAND_ELEMENT = "command"; 
	private final String SIGNIFICANT_ATTRIBUTE = "significant"; 
	private String significant;
	private String command;
	private CaseElement caseElement;
	private Element paramsElement;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param command
	 *            value for command element context
	 * @param caseElement
	 *            Case element is the Step element's parent
	 * @param targetElement
	 *            target element part for the element
	 * 
	 */
	public StepElement(String name, String command, CaseElement caseElement,
			TargetElement targetElement) {
		super(STEP_ELEMENT, name, harness, targetElement);
		this.command = command;
		this.caseElement = caseElement;
		stepId++;
		createElement();
	}

	/**
	 * Constructor with mandatory and optional parameters.
	 * 
	 * @param name
	 *            value for name attribute
	 * @param command
	 *            value for command element context
	 * @param caseElement
	 *            Case element is the Step element's parent
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 */
	public StepElement(String name, String command, CaseElement caseElement,
			TargetElement targetElement, String enabled, int passrate,
			String significant) {
		super(STEP_ELEMENT, name, harness, targetElement, enabled, passrate);
		this.command = command;
		this.caseElement = caseElement;
		stepId++;
		this.significant = significant;
		createElement();
	}

	/**
	 * Sets element's attributes from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(
				ID_ATTRIBUTE,
				testRunId + PUNCTUATION + planId + PUNCTUATION + sessionId
						+ PUNCTUATION + setId + PUNCTUATION + caseId
						+ PUNCTUATION + stepId);
		Element commandElement = dom.createElement(COMMAND_ELEMENT);
		commandElement.setTextContent(command);
		getElement().appendChild(commandElement);
		caseElement.getElement().appendChild(getElement());
		paramsElement = dom.createElement(PARAMS_ELEMENT);
		getElement().appendChild(paramsElement);
		if (significant != null) {
			getElement().setAttribute(SIGNIFICANT_ATTRIBUTE, significant);
		}
	}

	/**
	 * Returns params element
	 * 
	 * @return params element
	 */
	public Element getParamsElement() {
		return paramsElement;
	}
}
