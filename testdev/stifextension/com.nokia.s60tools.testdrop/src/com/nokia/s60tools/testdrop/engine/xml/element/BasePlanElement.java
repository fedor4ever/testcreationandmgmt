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
 * XML base element class for plan area
 */
public abstract class BasePlanElement extends BaseElement {

	protected final String PUNCTUATION = "."; 
	protected final String ID_ATTRIBUTE = "id"; 
	protected final String NAME_ATTRIBUTE = "name"; 
	protected final String HARNESS_ATTRIBUTE = "harness"; 
	protected final String ENABLED_ATTRIBUTE = "enabled"; 
	protected final String PASSRATE_ATTRIBUTE = "passrate"; 
	protected static int planId;
	protected static int sessionId;
	protected static int setId;
	protected static int caseId;
	protected static int stepId;
	protected static String harness;
	protected TargetElement targetElement;
	private String name;
	private String enabled;
	private int passrate;

	/**
	 * Constructs an XML element with mandatory parameters.
	 * 
	 * @param elementName
	 *            Element name
	 * @param name
	 *            value for name attribute
	 * @param harness
	 *            value for harness attribute
	 * @param targetElement
	 *            target element part for the element
	 */
	protected BasePlanElement(String elementName, String name, String harness,
			TargetElement targetElement) {
		super(elementName);
		this.name = name;
		BasePlanElement.harness = harness;
		this.targetElement = targetElement;
		createBaseArgumet();
		addTargetElement();
	}

	/**
	 * Constructs an XML element with mandatory and optional parameters.
	 * 
	 * @param elementName
	 *            element name value
	 * @param name
	 *            value for name attribute
	 * @param harness
	 *            value for harness attribute
	 * @param targetElement
	 *            target element part for the element
	 * @param enabled
	 *            value for enabled attribute
	 * @param passrate
	 *            value for passrate attribute
	 * 
	 */
	protected BasePlanElement(String elementName, String name, String harness,
			TargetElement targetElement, String enabled, int passrate) {
		this(elementName, name, harness, targetElement);
		this.enabled = enabled;
		this.passrate = passrate;
		createOptionalArgumet();
	}

	/**
	 * creates base attributes of the element
	 */
	private void createBaseArgumet() {
		getElement().setAttribute(NAME_ATTRIBUTE, name);
		getElement().setAttribute(HARNESS_ATTRIBUTE, harness);

	}

	/**
	 * creates optional attribute of the element
	 */
	private void createOptionalArgumet() {
		getElement().setAttribute(ENABLED_ATTRIBUTE, enabled);
		getElement().setAttribute(PASSRATE_ATTRIBUTE, String.valueOf(passrate));

	}

	/**
	 * adds target element part for the element
	 */
	private void addTargetElement() {
		if (targetElement != null) {
			getElement().appendChild(targetElement.getElement());
		}
	}

	/**
	 * Abstract method that have to implement in inherited class
	 */
	protected abstract void createElement();

	/**
	 * Dispose method that should be called before to remove that instance
	 */
	public void dispose() {
		planId = 0;
		sessionId = 0;
		setId = 0;
		caseId = 0;
		stepId = 0;
	}
}
