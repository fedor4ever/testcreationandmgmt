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
import org.eclipse.cdt.core.model.ICProject;

import com.nokia.testfw.codegen.model.INode;
import com.nokia.testfw.codegen.model.ProjectNodeImpl;

/**
 * @author k21wang
 * 
 */
public class UIProjectNode extends ProjectNodeImpl implements IUINode {

	private ICElement iCElement;

	/**
	 * @param arg0
	 */
	public UIProjectNode(ICProject project) {
		super(project.getElementName());
		iCElement = project;
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
		boolean lSelected = false;
		for (INode child : getChildren()) {
			if (child instanceof IUINode) {
				if (((IUINode) child).isSelected()) {
					lSelected = true;
					break;
				}
			} else {
				lSelected = true;
				break;
			}
		}
		return lSelected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nokia.testfw.codegen.ui.parser.model.IUINode#setSelected(boolean)
	 */
	public void setSelected(boolean selected) {
		for (INode child : getChildren()) {
			if (child instanceof IUINode) {
				((IUINode) child).setSelected(selected);
			}
		}
	}

	public boolean isVisible() {
		boolean isVisible = false;
		for (INode child : getChildren()) {
			if (child instanceof IUINode) {
				if (((IUINode) child).isVisible()) {
					isVisible = true;
					break;
				}
			} else {
				isVisible = true;
				break;
			}
		}
		return isVisible;
	}

	public void setVisible(boolean visible) {
		for (INode child : getChildren()) {
			if (child instanceof IUINode) {
				((IUINode) child).setVisible(visible);
			}
		}
	}
}
