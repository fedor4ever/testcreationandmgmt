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
import java.util.Set;

import org.eclipse.cdt.core.dom.ast.ASTTypeUtil;
import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.IParameter;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPMethod;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;

import com.symbian.tdep.templates.carbide.util.TemplateUtil;

public class MethodItem implements ITestItem, Comparable<ITestItem> {

	private final static Image METHOD_IMAGE = TefTemplatesCarbidePlugin
			.imageDescriptorFromPlugin("com.symbian.tdep.templates.carbide",
					"icons/method.gif").createImage();

	public final static int TYPE = 0;

	public final static int NAME = 1;

	private String iName;

	private ITestItem iParent;

	private boolean iHasParameter = false;

	private boolean iAsync = false;

	private boolean iSelected = true;

	private boolean iHasImpl = false;

	private String iNormalisedMethodName;

	private final List<String[]> iParameters = new ArrayList<String[]>();

	private String iFullName;

	// Constructor
	public MethodItem(ICPPMethod aCPPMethod, ITestItem parent) {
		iParent = parent;
		iName = aCPPMethod.getName();
		try {
			IParameter[] lParameters = aCPPMethod.getParameters();

			if (lParameters.length > 0) {
				iHasParameter = true;
				for (int i = 0; i < lParameters.length; i++) {
					String type = ASTTypeUtil.getType(lParameters[i].getType())
							.replace(" &", "&");
					type = type.replace(" *", "*");
					type = type.replace("enum ", "");
					if ("TRequestStatus&".equals(type)) {
						iAsync = true;
					}
					iParameters.add(new String[] { type,
							lParameters[i].getName() });
				}
			}
		} catch (DOMException e) {
			IStatus lStatus = new Status(IStatus.ERROR, MethodItem.class
					.getName(), IStatus.ERROR,
					"DOM exception while inspecting binding: "
							+ parent.getName() + "." + iName, e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}

		iFullName = fullName(iName);
		iNormalisedMethodName = normalisedName(iFullName);
	}

	public MethodItem(IMethodDeclaration aMethod, ITestItem parent) {
		iParent = parent;
		iName = aMethod.getElementName();
		try {
			String[] lParameters = aMethod.getParameterTypes();

			if (lParameters.length > 0) {
				iHasParameter = true;
	            String source = aMethod.getSource();
	            String allparams = source.substring(source.indexOf('(') + 1, source.lastIndexOf(')'));
	            String paramArray[] = allparams.split(",");

				for (int i = 0; i < lParameters.length; i++) {
	                String param = paramArray[i];
	                if(param.indexOf('=') != -1)
	                    param = paramArray[i].substring(0, paramArray[i].indexOf('=')).trim();
	                else
	                    param = param.trim();
	                param = param.substring(param.lastIndexOf(' ')).trim();					
					String type = lParameters[i];
	                if(type.indexOf('=') != -1)
	                	type = type.substring(0, type.indexOf('=')).trim();
					type = type.replace(" &", "&");
					type = type.replace(" *", "*");
					type = type.replace("enum ", "");
					if(param.startsWith("&")||param.startsWith("*")){
		                param = param.replaceAll("[&*]", "");					
					}
					if ("TRequestStatus&".equals(type)) {
						iAsync = true;
					}
					iParameters.add(new String[] { type, param });
				}
			}
		} catch (CModelException e) {
			IStatus lStatus = new Status(IStatus.ERROR, MethodItem.class
					.getName(), IStatus.ERROR,
					"CModelException while inspecting binding: "
							+ parent.getName() + "." + iName, e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
		}

		iFullName = fullName(iName);
		iNormalisedMethodName = normalisedName(iFullName);
	}

	// Constructor
	public MethodItem(String name, ITestItem parent) {
		iParent = parent;
		iName = name;
		iFullName = fullName(iName);
		iNormalisedMethodName = normalisedName(iFullName);
	}

	// Set name
	public String getName() {
		return iName;
	}

	// Get full name
	public String getFullName() {
		return iFullName;
	}

	// Set name
	public void setName(String name) {
		iName = name;
		iFullName = fullName(iName);
		iNormalisedMethodName = normalisedName(iFullName);
	}

	// Get parent
	public ITestItem getParent() {
		return iParent;
	}

	// Set parent
	public void setParent(ITestItem parent) {
		iParent = parent;
	}

	// Get children
	public Set<ITestItem> getChildren() {
		return null;
	}

	// Get image
	public Image getImage() {
		return METHOD_IMAGE;
	}

	// Get whether valid
	public boolean isValid() {
		return true;
	}

	// Get whether has parameter
	public boolean hasParameter() {
		return iHasParameter;
	}

	// Set whether has parameter
	public void setParameter(boolean hasParameter) {
		iHasParameter = hasParameter;
	}

	// Set whether asynchronous
	public boolean isAsync() {
		return iAsync;
	}

	// Get whether asynchronous
	public void setAsync(boolean async) {
		iAsync = async;
	}

	// Set whether selected
	public boolean isSelected() {
		return iSelected;
	}

	// Get whether selected
	public void setSelected(boolean aSelected) {
		iSelected = aSelected;
	}

	private String fullName(String name) {
		String lFullName = name + " (";
		if (iParameters.size() > 0) {
			lFullName += iParameters.get(0)[TYPE];
			for (int i = 1; i < iParameters.size(); i++) {
				lFullName += ", " + iParameters.get(i)[TYPE];
			}
		}
		lFullName += ")";
		return lFullName;
	}

	private String normalisedName(String name) {
		return name.replace(" ", "").replace("(void)", "").replace("()", "")
				.replace(",", "_").replace("(", "_").replace(")", "").replace(
						"+", "Plus").replace("-", "Minus").replace("/",
						"Devide").replace("*",
						name.contains("opeartor") ? "Multiply" : "Pointer")
				.replace("&", "Reference").replace("=", "Equals").replace("!",
						"Not").replace(">>", "ShiftRight").replace("<<",
						"ShiftLeft").replace("~", "Destructor").replace("<", "Lt").replace(">", "Gt");
	}

	// Get Normalised Name
	public String getNormalisedName() {
		return iNormalisedMethodName;
	}

	// Get name of test method
	public String getTestName() {
		String testName = TemplateUtil.getMethodWrapperName(iNormalisedMethodName);
		if (iHasParameter && iAsync) {
			testName += "(const TDesC&, const TInt)";
		} else {
			if (iHasParameter) {
				testName += "(const TDesC&)";
			} else if (iAsync) {
				testName += "(const TInt)";
			} else {
				testName += "()";
			}
		}

		return testName;
	}

	public List<String[]> getParameters() {
		return iParameters;
	}

	// Get whether has implementation
	public boolean isHasImpl() {
		return iHasImpl;
	}

	// Set whether has implementation
	public void setHasImpl(boolean hasImpl) {
		iHasImpl = hasImpl;
	}

	// For TreeSet
	public int compareTo(ITestItem o) {
		return iNormalisedMethodName
				.compareToIgnoreCase(((MethodItem) o).iNormalisedMethodName);
	}
}
