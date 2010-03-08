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


package com.nokia.s60tools.stif.scripteditor.editors;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Detect begining and end of a section in script.
 * 
 */

public class SectionDetector implements IWordDetector {
	/**
	 * Checks [ char position
	 * 
	 * @return boolean
	 */

	public boolean isWordStart(char c) {
		if (c == '[') {
			return true;
		}
		return Character.isJavaIdentifierStart(c);
	}

	/**
	 * Checks ] char position
	 * 
	 * @return boolean
	 */
	public boolean isWordPart(char c) {
		if (c == ']')
			return true;
		return Character.isJavaIdentifierPart(c);
	}
}
