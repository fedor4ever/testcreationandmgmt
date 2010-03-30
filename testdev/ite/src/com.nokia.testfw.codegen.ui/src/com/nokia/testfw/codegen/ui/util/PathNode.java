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

package com.nokia.testfw.codegen.ui.util;

import java.util.HashMap;
import java.util.Map;

public class PathNode {
	private String name;
	private PathNode parent;
	private Map<String, PathNode> childrenMap = new HashMap<String, PathNode>();
	private Object data;

	public PathNode(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PathNode getParent() {
		return parent;
	}

	public void setParent(PathNode parent) {
		this.parent = parent;
	}

	public void addChild(PathNode node) {
		node.setParent(this);
		childrenMap.put(node.name, node);
	}

	public PathNode getChild(String name) {
		return childrenMap.get(name);
	}

	public PathNode[] getChildren() {
		return childrenMap.values().toArray(new PathNode[0]);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
