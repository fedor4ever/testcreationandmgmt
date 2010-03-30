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


package com.nokia.testfw.stf.scripteditor.utils;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Class used by ScriptScanner to help to decide if word is a keyword
 */
public class KeywordDetector implements IWordDetector {
	
	/**
	 * Implementation of IWordDetector.isWordStart
	 */
	public boolean isWordStart(char c) {
		return Character.isJavaIdentifierStart(c);
	}
	
	/**
	 * Implementation of IWordDetector.isWordPart
	 */
	public boolean isWordPart(char c) {
		return Character.isJavaIdentifierPart(c);
	}
}
