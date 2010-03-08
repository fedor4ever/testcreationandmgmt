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

import java.util.ArrayList;

public interface Arguments {
	
	public ArrayList<String> validate(ArrayList<String> mandList);
	public String getArgumentBinding();
	public String getArgumentName();
	public String getArgumenType(); 
	public String getNextValue(); 
	
	
	
}
