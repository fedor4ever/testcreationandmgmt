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

package com.nokia.testfw.codegen;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

public class StringResourceLoaderTest extends TestCase {

	StringResourceLoader loader;

	protected void setUp() throws Exception {
		loader = new StringResourceLoader();
	}

	public void testGetLastModifiedResource() {
		assertEquals(loader.getLastModified(null), 0);
	}

	public void testGetResourceStreamAndGetString() {
		try {
			InputStream stream = loader.getResourceStream("ABC");
			assertEquals(StringResourceLoader.getString(stream), "ABC");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testIsSourceModifiedResource() {
		assertFalse(loader.isSourceModified(null));
	}

	public void testInitExtendedProperties() {
		loader.init(null);
	}

}
