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

public interface IClassNode extends INode{

	// Get declare location
	public String getDeclLocation();

	// Get header file name
	public String getHeaderFileName();

	// Get implementation location
	public String[] getImplLocation();

	// Get include header files
	public String[] getIncludeHeaders();
}
