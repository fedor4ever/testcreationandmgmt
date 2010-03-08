/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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

package com.symbian.jstat;

/**
 * @author EngineeringTools
 *
 */
public class JStatException extends Exception {

	private JStatResult iResult = null;
	
	/**
	 * @param aString
	 */
	public JStatException(String aString) {
		super(aString);
	}

	/**
	 * @param aResult
	 */
	public JStatException(JStatResult aResult) {
		super(aResult.toString());
		iResult = aResult;
	}
	
	/**
	 * @return The result of the JStat Command
	 */
	public JStatResult getResult() {
		return iResult;
	}

}
