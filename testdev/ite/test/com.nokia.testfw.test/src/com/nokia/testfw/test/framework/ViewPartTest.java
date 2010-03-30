/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of the License "Symbian Foundation License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/
package com.nokia.testfw.test.framework;

import junit.framework.TestCase;

import org.eclipse.ui.IViewPart;

import org.eclipse.ui.PlatformUI;

//import junit.framework.TestCase;
import org.junit.Before;
import org.junit.After;


/**
 * this class is used to test a view part.
 * the subclass need to implememnt getViewId() method to return the view id under test
 * @author xiaoma
 *
 */
public abstract class ViewPartTest extends TestCase{
	
	protected IViewPart viewPart;
	
	@Before
	public void setUp() throws Exception {
		viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(
	    		getViewId());
	    if (viewPart == null) {
	    	throw new RuntimeException("can't find view:" + getViewId());
	    }	    
	}
	
	@After
	public void tearDown() throws Exception {
		viewPart.dispose();
	}
	
	protected abstract String getViewId(); 
	
}
