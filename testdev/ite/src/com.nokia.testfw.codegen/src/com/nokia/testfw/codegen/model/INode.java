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
package com.nokia.testfw.codegen.model;

import java.util.Set;

public interface INode extends Cloneable {

	/**
	 * 
	 * @return name of object
	 */
	public String getName();

	/**
	 * 
	 * @return upper case name of object
	 */
	public String getNameUpperCase();

	/**
	 * 
	 * @return lower case name of object
	 */
	public String getNameLowerCase();

	/**
	 * 
	 * @return Child test object of this test object
	 */
	public Set<? extends INode> getChildren();

	/**
	 * 
	 * @return Parent test object of this test object
	 */
	public INode getParent();
}