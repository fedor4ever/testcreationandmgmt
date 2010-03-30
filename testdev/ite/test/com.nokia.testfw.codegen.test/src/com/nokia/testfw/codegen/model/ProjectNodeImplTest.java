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

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

public class ProjectNodeImplTest extends TestCase {

	ProjectNodeImpl node;

	protected void setUp() throws Exception {
		node = new ProjectNodeImpl("node");
		ClassNodeImpl lClassNode = new ClassNodeImpl("testClass", node);
		node.addChild(lClassNode);
		MethodNodeImpl lMethodNode = new MethodNodeImpl("method", lClassNode);
		lClassNode.addChild(lMethodNode);
		lMethodNode.addParameters("String", "name");
		lMethodNode.addParameters("int", "index");
	}

	public void testGetName() {
		assertEquals(node.getName(), "node");
		assertEquals(node.getNameUpperCase(), "NODE");
		assertEquals(node.getNameLowerCase(), "node");
	}

	public void testSetName() {
		node.setName("project");
		assertEquals(node.getName(), "project");
		node.setName("node");
		assertEquals(node.getName(), "node");
	}

	public void testGetParent() {
		assertNull(node.getParent());
	}

	public void testSetParent() {
		ProjectNodeImpl parent = new ProjectNodeImpl("parent");
		node.setParent(parent);
		assertEquals(node.getParent(), parent);
		node.setParent(null);
		assertNull(node.getParent());
	}

	public void testGetChildren() {
		assertNotNull(node.getChildren());
		assertTrue(node.getChildren().size() == 1);
	}

	public void testSetChildren() {
		Set<INode> set = node.getChildren();
		node.setChildren(new TreeSet<INode>());
		assertNotNull(node.getChildren());
		assertTrue(node.getChildren().size() == 0);
		node.setChildren(set);
	}

	public void testAddRemoveChild() {
		ClassNodeImpl classnode = new ClassNodeImpl("class", node);
		node.addChild(classnode);
		assertNotNull(node.getChildren());
		assertTrue(node.getChildren().size() == 2);
		node.removeChild(classnode);
		assertNotNull(node.getChildren());
		assertTrue(node.getChildren().size() == 1);
	}

	public void testCompareTo() {
		assertEquals(node.compareTo(new ProjectNodeImpl("node")), 0);
	}

	public void testSetGetSystemIncludes() {
		node.setSystemIncludes(new HashSet<String>());
		assertNotNull(node.getSystemIncludes());
	}

	public void testSetGetUserIncludes() {
		node.setUserIncludes(new HashSet<String>());
		assertNotNull(node.getUserIncludes());
	}

	public void testSetGetLibrarys() {
		node.setLibrarys(new HashSet<String>());
		assertNotNull(node.getLibrarys());
	}
}
