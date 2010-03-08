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

/**
 * Currently ScriptEditor supports only test case sections. But there are also
 * [Define] sections and other. So if in future we decide that we want also those
 * sections to be listed in outline view then Section class will be super class
 * for them. This class currently does not extend Section class functionality.
 * But probably it will in future.
 * 
 */
public class TestCase extends Section {
	
	/**
	 * Constructor
	 *  
	 * @param title
	 * 		Title of test case
	 */
	public TestCase(String title){
		super(title);
	}
}
