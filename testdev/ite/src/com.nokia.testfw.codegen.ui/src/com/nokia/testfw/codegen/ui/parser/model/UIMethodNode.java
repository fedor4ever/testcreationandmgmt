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
import org.eclipse.cdt.core.model.IMethodDeclaration;

import com.nokia.testfw.codegen.model.MethodNodeImpl;

/**
 * @author k21wang
 * 
 */
public class UIMethodNode extends MethodNodeImpl implements IUINode {

	private boolean iSelected = true;

	private boolean isVisible = true;

	private ICElement iCElement;

	/**
	 * @param methodDec
	 * @param parent
	 */
	public UIMethodNode(IMethodDeclaration methodDec, UIClassNode parent) {
		super(methodDec.getElementName(), parent);
		iCElement = methodDec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.testfw.codegen.ui.parser.model.IUINode#getData()
	 */
	public ICElement getICElement() {
		return iCElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.testfw.codegen.ui.parser.model.IUINode#isSelected()
	 */
	public boolean isSelected() {
		return iSelected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nokia.testfw.codegen.ui.parser.model.IUINode#setSelected(boolean)
	 */
	public void setSelected(boolean selected) {
		iSelected = selected;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}
}
