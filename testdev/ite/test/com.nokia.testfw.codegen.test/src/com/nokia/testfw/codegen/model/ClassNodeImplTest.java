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

import junit.framework.TestCase;

public class ClassNodeImplTest extends TestCase {

	ClassNodeImpl iClassNodeImpl;
	ProjectNodeImpl iProjectNode;

	protected void setUp() throws Exception {
		iProjectNode = new ProjectNodeImpl("testProject");
		iClassNodeImpl = new ClassNodeImpl("class", iProjectNode);
		iProjectNode.addChild(iClassNodeImpl);
	}

	public void testSetGetDeclLocation() {
		iClassNodeImpl.setDeclLocation("location");
		assertEquals(iClassNodeImpl.getDeclLocation(), "location");
		iClassNodeImpl.getHeaderFileName();
		iClassNodeImpl.setDeclLocation(null);
		assertNull(iClassNodeImpl.getDeclLocation());
	}

	public void testAddGetSetImplLocation() {
		iClassNodeImpl.addImplLocation("location");
		assertEquals(iClassNodeImpl.getImplLocation().length, 1);
		iClassNodeImpl.setImplLocation(new HashSet<String>());
		assertEquals(iClassNodeImpl.getImplLocation().length, 0);
	}
}
