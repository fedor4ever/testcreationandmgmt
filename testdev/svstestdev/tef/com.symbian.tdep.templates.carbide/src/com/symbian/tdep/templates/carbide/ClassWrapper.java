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



package com.symbian.tdep.templates.carbide;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPMethod;
import org.eclipse.swt.graphics.Image;

/**
 * A Wrapper class to wrap a Symbian C++ class, for TEFUnit wizard to provide a 
 * project->class->method selection UI to users. 
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
public class ClassWrapper implements Wrapper<ProjectWrapper> {

	private final String iClassName;
	
	private final ProjectWrapper iProjectWrapper;
	
	private final List<MethodWrapper> iMethodWrapperList = new ArrayList<MethodWrapper>();
	
	private final static Image CLASS_IMAGE = TefTemplatesCarbidePlugin.imageDescriptorFromPlugin(
			"com.symbian.tdep.templates.carbide",
			"icons/class.gif").createImage();

	/**
	 * @param aClassName
	 * @param aMethods 
	 */
	public ClassWrapper(ICPPClassType aClass, List<ICPPMethod> aMethods, ProjectWrapper aProjectWrapper) {
		iClassName = aClass.getName();
		iProjectWrapper = aProjectWrapper;
		for (ICPPMethod lMethod : aMethods) {
			iMethodWrapperList.add(new MethodWrapper(lMethod, this));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getName()
	 */
	public String getName() {
		return iClassName;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getChildren()
	 */
	public List<MethodWrapper> getChildren() {
		return iMethodWrapperList;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getParent()
	 */
	public ProjectWrapper getParent() {
		return iProjectWrapper;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#isSelected()
	 */
	public boolean isSelected() {
		boolean lSelected = false;
		for (Wrapper lWrapper : iMethodWrapperList) {
			if (lWrapper.isSelected()) {
				lSelected = true;
				break;
			}
		}
		return lSelected;
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#setSelected(boolean)
	 */
	public void setSelected(boolean aSelected) {
		for (Wrapper lWrapper : iMethodWrapperList) {
			lWrapper.setSelected(aSelected);
		}
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getImage()
	 */
	public Image getImage() {
		return CLASS_IMAGE;
	}

}