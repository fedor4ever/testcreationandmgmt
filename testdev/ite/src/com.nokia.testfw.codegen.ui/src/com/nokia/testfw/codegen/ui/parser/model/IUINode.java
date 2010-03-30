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
package com.nokia.testfw.codegen.ui.parser.model;

import org.eclipse.cdt.core.model.ICElement;

public interface IUINode {

	/**
	 * 
	 * @return if this object is selected by user
	 */
	public boolean isSelected();

	/**
	 * Set the flag of whether object is selected by user
	 */
	public void setSelected(boolean selected);

	/**
	 * 
	 * @return if this object is selected by user
	 */
	public boolean isVisible();

	/**
	 * Set the flag of whether object is selected by user
	 */
	public void setVisible(boolean visible);

	/**
	 * Get ICElement
	 * 
	 * @return storage
	 */
	public ICElement getICElement();
}
