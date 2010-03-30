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

abstract public class NodeImpl implements INode, Comparable<INode> {

	private String iName;

	// Parent item of this item
	private INode iParent;

	// Children items of this item
	private Set<INode> iChildSet = new TreeSet<INode>();

	// Constructor
	protected NodeImpl(String name, NodeImpl parent) {
		iName = name;
		iParent = parent;
	}

	// Get name
	public String getName() {
		return iName;
	}

	// Get name
	public String getNameUpperCase() {
		return iName.toUpperCase();
	}

	// Get name
	public String getNameLowerCase() {
		return iName.toLowerCase();
	}

	// Set name
	public void setName(String name) {
		iName = name;
	}

	// Get parent
	public INode getParent() {
		return iParent;
	}

	// Set parent
	public void setParent(INode parent) {
		iParent = parent;
	}

	// Get children
	public Set<INode> getChildren() {
		return iChildSet;
	}

	// Set children
	public void setChildren(Set<INode> childSet) {
		iChildSet = childSet;
		for (INode child : iChildSet) {
			((NodeImpl) child).setParent(this);
		}
	}

	// Add child
	public boolean addChild(INode aNode) {
		((NodeImpl) aNode).setParent(this);
		return iChildSet.add(aNode);
	}

	// Remove child
	public boolean removeChild(INode aNode) {
		((NodeImpl) aNode).setParent(null);
		return iChildSet.remove(aNode);
	}

	// For TreeSet
	public int compareTo(INode o) {
		return iName.compareToIgnoreCase(o.getName());
	}

	abstract public Object clone();
}
