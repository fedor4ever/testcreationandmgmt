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

import java.util.List;

import org.eclipse.cdt.core.dom.ast.cpp.ICPPMethod;
import org.eclipse.swt.graphics.Image;

/**
 * A Wrapper class to wrap a Symbian C++ method which can be tested by TEFUnit, 
 * for TEFUnit wizard to provide a project->class->method selection UI to users. 
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
public class MethodWrapper implements Wrapper<ClassWrapper> {

	private final String iMethodName;
	
	private String iNormalisedMethodName;
	
	private final ClassWrapper iClassWrapper;
	
	private boolean iSelected;
	
	private final static Image METHOD_IMAGE = TefTemplatesCarbidePlugin.imageDescriptorFromPlugin(
			"com.symbian.tdep.templates.carbide",
			"icons/method.gif").createImage();

	/**
	 * @param aMethodName
	 * @param aClassWrapper
	 */
	public MethodWrapper(ICPPMethod aMethod, ClassWrapper aClassWrapper) {
		
		String iTempMethodName = aMethod.toString();
		int iTempPos = iTempMethodName.lastIndexOf(") ");
		if(iTempPos>0)
		{
			iMethodName = iTempMethodName.substring(0,iTempPos+1);
			
		}
		else
		{
			iMethodName = iTempMethodName;
		}
		iClassWrapper = aClassWrapper;
		// Normailse operator overloaded method names
		iNormalisedMethodName = iMethodName.replace(" ", "")
			.replace("(void)", "")
			.replace("()", "")
			.replace(",", "_")
			.replace("(", "_")
			.replace(")", "")
			.replace("+", "Plus")
			.replace("-", "Minus")
			.replace("/", "Devide")
			.replace("*", iMethodName.contains("opeartor") ? "Multiply" : "Pointer")
			.replace("&", "Reference")
			.replace("=", "Equals")
			.replace("!", "Not")
			.replace(">>", "ShiftRight")
			.replace("<<", "ShiftLeft")
			.replace("~", "Destructor");
	}
	
	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getName()
	 */
	public String getName() {
		return iMethodName;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getChildren()
	 */
	public List<Wrapper> getChildren() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getParent()
	 */
	public ClassWrapper getParent() {
		return iClassWrapper;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#isSelected()
	 */
	public boolean isSelected() {
		return iSelected;
	}

	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#setSelected(boolean)
	 */
	public void setSelected(boolean aSelected) {
		iSelected = aSelected;
	}


	/* (non-Javadoc)
	 * @see com.symbian.tef.templates.carbide.Wrapper#getImage()
	 */
	public Image getImage() {
		return METHOD_IMAGE;
	}
	
	/**
	 * @param lMethod
	 * @return
	 */
	public String getNormalisedName() {
		return iNormalisedMethodName;
	}

}
