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


package com.symbian.tdep.templates.carbide;

import java.util.Set;

import org.eclipse.swt.graphics.Image;

public interface ITestItem {

	/**
	 * 
	 * @return name of object
	 */
	String getName();

	/**
	 * @param name
	 */
	void setName(String name);

	/**
	 * 
	 * @return name of test object
	 */
	String getTestName();

	/**
	 * 
	 * @return Child test object of this test object
	 */
	Set<? extends ITestItem> getChildren();

	/**
	 * 
	 * @return Parent test object of this test object
	 */
	ITestItem getParent();

	/**
	 * 
	 * @return if this object is selected by user
	 */
	boolean isValid();

	/**
	 * 
	 * @return if this object is selected by user
	 */
	boolean isSelected();

	/**
	 * to Set selection status of this object
	 * 
	 * @param aSelected
	 */
	void setSelected(boolean aSelected);

	/**
	 * @return icon image of wrapped object
	 */
	Image getImage();

}