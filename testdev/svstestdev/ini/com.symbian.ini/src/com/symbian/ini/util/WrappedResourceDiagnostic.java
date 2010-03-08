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


package com.symbian.ini.util;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author EngineeringTools
 *
 */
public class WrappedResourceDiagnostic extends WrappedException implements Resource.Diagnostic {

	private int iColumn = 0;
	
	private int iLine = 0;
	
	private String iLocation = null;
	
	public WrappedResourceDiagnostic(Exception exception) {
		super(exception);
	}
	
	public WrappedResourceDiagnostic(String message, Exception exception) {
		super(message, exception);
	}

	public int getColumn() {
		return iColumn;
	}

	public int getLine() {
		return iLine;
	}

	public String getLocation() {
		return iLocation;
	}

	public WrappedResourceDiagnostic setColumn(int column) {
		iColumn = column;
		
		return this;
	}

	public WrappedResourceDiagnostic setLine(int line) {
		iLine = line;
		
		return this;
	}

	public WrappedResourceDiagnostic setLocation(String location) {
		iLocation = location;
		
		return this;
	}

}
