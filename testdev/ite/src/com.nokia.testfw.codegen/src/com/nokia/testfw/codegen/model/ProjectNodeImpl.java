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
import java.util.TreeSet;

public class ProjectNodeImpl extends NodeImpl implements IProjectNode {

	protected Set<String> iLibSet = new TreeSet<String>();

	protected Set<String> iSysIncSet = new TreeSet<String>();

	protected Set<String> iUserIncSet = new TreeSet<String>();

	// Constructor
	public ProjectNodeImpl(String name) {
		super(name, null);
	}

	public Set<String> getLibrarys() {
		return iLibSet;
	}

	public void setLibrarys(Set<String> librarySet) {
		iLibSet = librarySet;
	}

	public Set<String> getSystemIncludes() {
		return iSysIncSet;
	}

	public void setSystemIncludes(Set<String> includeSet) {
		iSysIncSet = includeSet;
	}

	public Set<String> getUserIncludes() {
		return iUserIncSet;
	}

	public void setUserIncludes(Set<String> includeSet) {
		iUserIncSet = includeSet;
	}

	public Object clone() {
		ProjectNodeImpl clone = new ProjectNodeImpl(getName());
		clone.iLibSet = new TreeSet<String>(iLibSet);
		clone.iSysIncSet = new TreeSet<String>(iSysIncSet);
		clone.iUserIncSet = new TreeSet<String>(iUserIncSet);
		return clone;
	}
}
