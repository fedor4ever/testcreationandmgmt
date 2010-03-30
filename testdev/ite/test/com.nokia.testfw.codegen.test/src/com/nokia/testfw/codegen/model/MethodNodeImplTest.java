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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class MethodNodeImplTest extends TestCase {

	MethodNodeImpl iMethodNode;
	ClassNodeImpl iClassNode;
	ProjectNodeImpl iProjectNode;

	protected void setUp() throws Exception {
		iProjectNode = new ProjectNodeImpl("testProject");
		iClassNode = new ClassNodeImpl("testClass", iProjectNode);
		iProjectNode.addChild(iClassNode);
		iMethodNode = new MethodNodeImpl("method", iClassNode);
		iClassNode.addChild(iMethodNode);
		iMethodNode.addParameters("String", "name");
		iMethodNode.addParameters("int", "index");
	}

	public void testSetName() {
		iMethodNode.setName("myMethod");
		assertEquals(iMethodNode.getName(), "myMethod");
		assertEquals(iMethodNode.getFullName(), "myMethod (String, int)");
		assertEquals(iMethodNode.getNormalisedName(), "myMethod_String_int");
		iMethodNode.setName("method");
	}

	public void testIsValid() {
		assertTrue(iMethodNode.isValid());
	}

	public void testCompareTo() {
		MethodNodeImpl lMethodNode = new MethodNodeImpl("method", iClassNode);
		lMethodNode.addParameters("String", "name");
		lMethodNode.addParameters("int", "index");

		assertEquals(iMethodNode.compareTo(lMethodNode), 0);
	}

	public void testIsAsync() {
		assertFalse(iMethodNode.isAsync());
	}

	public void testSetAsync() {
		iMethodNode.setAsync(true);
		assertTrue(iMethodNode.isAsync());
		iMethodNode.setAsync(false);
		assertFalse(iMethodNode.isAsync());
	}

	public void testSetGetAddParameters() {
		assertNotNull(iMethodNode.getParameters());
		assertTrue(iMethodNode.getParameters().size() == 2);
		List<String[]> parameters = new ArrayList<String[]>();
		iMethodNode.setParameters(parameters);
		assertTrue(iMethodNode.getParameters().size() == 0);
		iMethodNode.addParameters("String", "name");
		assertTrue(iMethodNode.getParameters().size() == 1);
	}
}
