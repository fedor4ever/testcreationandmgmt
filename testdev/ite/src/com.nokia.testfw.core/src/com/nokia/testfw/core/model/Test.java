/*
 * Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
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
package com.nokia.testfw.core.model;

/**
 * @author xiaoma
 *
 */
public abstract class Test {

	/**
	 * An unambiguous reference to the test
	 */
	protected String name;
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return name;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.name = identifier;
	}

	/**
	 * @return the title
	 */
//	public String getTitle() {
//		return title;
//	}

	/**
	 * @param title the title to set
	 */
//	public void setTitle(String title) {
//		this.title = title;
//	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * the name by which the test is formally known 
	 */
//	protected String title;
	/**
	 * A representation in words of the nature and characteristics of the test 
	 */
	protected String description;
	/**
	 * an identifier that allows one to distinguish between different revisions of test
	 */
	protected String version;

	/**
	 * 
	 */
	public Test() {
		super();
	}

}