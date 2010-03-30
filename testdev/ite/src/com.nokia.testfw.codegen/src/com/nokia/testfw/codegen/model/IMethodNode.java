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

import java.util.List;

public interface IMethodNode extends INode {

	// Get visibility: public=0, protected=1, private=2
	public int getVisibility();
	
	// Get full name
	public String getFullName();

	// Get normalised Name
	public String getNormalisedName();

	// Get parameters
	public List<String[]> getParameters();

	// Asynchronous flag
	public boolean isAsync();

	// Constructor flag
	public boolean isConstructor();

	// Destructor flag
	public boolean isDestructor();

	// Operator flag
	public boolean isOperator();

	// PureVirtual flag
	public boolean isPureVirtual();

	// Static flag
	public boolean isStatic();

	// Asynchronous flag
	public boolean isInline();

	// Virtual flag
	public boolean isVirtual();
	
	// Const flag
	public boolean isConst();
}
