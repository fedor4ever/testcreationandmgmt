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

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class ClassNodeImpl extends NodeImpl implements IClassNode {

	// Class declaration location
	private String iDeclLocation;

	// Class header file name
	private String iHeaderFileName;

	// Class implementation locations
	private Set<String> iImplLocationSet = new TreeSet<String>();

	// Class include header files
	private Set<String> iIncludeHeaderSet = new TreeSet<String>();

	public ClassNodeImpl(String name, ProjectNodeImpl parent) {
		super(name, parent);
	}

	// Set declaration location
	public void setDeclLocation(String location) {
		iDeclLocation = location;
		if (location != null) {
			iHeaderFileName = location.substring(location
					.lastIndexOf(File.separator) + 1);
		} else {
			iHeaderFileName = null;
		}
	}

	// Get declaration location
	public String getDeclLocation() {
		return iDeclLocation;
	}

	public String getHeaderFileName() {
		return iHeaderFileName;
	}

	// Get implementation location
	public String[] getImplLocation() {
		return iImplLocationSet.toArray(new String[0]);
	}

	// Set implementation location
	public void setImplLocation(Set<String> locationSet) {
		iImplLocationSet = locationSet;
	}

	// Add implementation location
	public void addImplLocation(String location) {
		iImplLocationSet.add(location);
	}

	// Get implementation location
	public String[] getIncludeHeaders() {
		return iIncludeHeaderSet.toArray(new String[0]);
	}

	// Set implementation location
	public void setIncludeHeaders(Set<String> headerSet) {
		iIncludeHeaderSet = headerSet;
	}

	// Add implementation location
	public void addIIncludeHeader(String header) {
		iIncludeHeaderSet.add(header);
	}

	public Object clone() {
		ClassNodeImpl clone = new ClassNodeImpl(getName(),
				(ProjectNodeImpl) getParent());
		clone.iDeclLocation = iDeclLocation;
		clone.iHeaderFileName = iHeaderFileName;
		clone.iImplLocationSet = new TreeSet<String>(iImplLocationSet);
		clone.iIncludeHeaderSet = new TreeSet<String>(iIncludeHeaderSet);
		return clone;
	}
}
