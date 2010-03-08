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


package com.nokia.s60tools.stif.scripteditor.utils;

import org.eclipse.jface.viewers.LabelProvider;

import com.nokia.s60tools.stif.scripteditor.utils.TestCase;

/**
 * Needed to display nodes in outline view
 */
public class OutlineViewLabelProvider extends LabelProvider {
	
	@Override
	public String getText(Object testOb) {
		TestCase test = (TestCase)testOb;
		return test.getName();
	}
}
