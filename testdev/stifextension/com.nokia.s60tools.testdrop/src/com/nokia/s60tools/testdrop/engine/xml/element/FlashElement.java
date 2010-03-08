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
 * Provides flash element parts that based on the test plan area
 */
public class FlashElement extends BaseElement {

	private static final String FLASH_ELEMENT = "flash"; 
	private final String TARGET_ALIAS_ATTRIBUTE = "target-alias"; 
	private final String IMAGES_ATTRIBUTE = "images"; 
	private Element parent;
	private String targetAlias;
	private String images;

	/**
	 * Constructor with mandatory parameters.
	 * 
	 * @param targetAlias
	 *            value for the target alias attribute
	 * @param images
	 *            value for the images attribute
	 * @param parent
	 *            parent element
	 * 
	 */
	public FlashElement(String targetAlias, String images, Element parent) {
		super(FLASH_ELEMENT);
		this.targetAlias = targetAlias;
		this.images = images;
		this.parent = parent;
		createElement();
	}

	/**
	 * Sets element's attributes from the class data
	 */
	protected void createElement() {
		getElement().setAttribute(TARGET_ALIAS_ATTRIBUTE, targetAlias);
		getElement().setAttribute(IMAGES_ATTRIBUTE, images);
		parent.appendChild(getElement());
	}
}
