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

public class PathNodeConverter {

	static public PathNode pathToNode(PathNode root, String path) {
		PathNode parent = root;
		PathNode child = null;
		String[] segmentArray = path.split("/");
		for (String segment : segmentArray) {
			child = parent.getChild(segment);
			if (child == null) {
				child = new PathNode(segment);
				parent.addChild(child);
			}
			parent = child;
		}
		return child;
	}

	static public String nodeToPath(PathNode node) {
		String path = node.getName();
		while (node.getParent() != null) {
			path = node.getParent().getName() + "/" + path;
			node = node.getParent();
		}
		return path;
	}
}
