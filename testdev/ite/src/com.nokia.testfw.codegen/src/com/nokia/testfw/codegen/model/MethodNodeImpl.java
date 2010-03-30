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

import java.util.ArrayList;
import java.util.List;

public class MethodNodeImpl extends NodeImpl implements IMethodNode {

	public final static int TYPE = 0;

	public final static int NAME = 1;

	private int iVisibility = 0;

	private boolean iAsync = false;

	private boolean iConstructor = false;

	private boolean iDestructor = false;

	private boolean iInline = false;

	private boolean iOperator = false;

	private boolean iPureVirtual = false;

	private boolean iStatic = false;

	private boolean iVirtual = false;

	private boolean iConst = false;

	private String iNormalisedMethodName;

	private String iNormalisedMethodFullName;

	private List<String[]> iParameters = new ArrayList<String[]>();

	private String iFullName;

	// Constructor
	public MethodNodeImpl(String name, ClassNodeImpl parent) {
		super(name, parent);
		iFullName = fullName(name);
		iNormalisedMethodName = normalisedName(name);
		iNormalisedMethodFullName = normalisedName(iFullName);
	}

	// Get full name
	public String getFullName() {
		return iFullName;
	}

	// Set name
	public void setName(String name) {
		super.setName(name);
		iFullName = fullName(name);
		iNormalisedMethodName = normalisedName(name);
		iNormalisedMethodFullName = normalisedName(iFullName);
	}

	// Get whether valid
	public boolean isValid() {
		return true;
	}

	// Set whether asynchronous
	public boolean isAsync() {
		return iAsync;
	}

	// Get whether asynchronous
	public void setAsync(boolean async) {
		iAsync = async;
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
		String normalised = name.replace(" ", "").replace("(void)", "")
				.replace("()", "").replace(",", "_").replace("(", "_").replace(
						")", "").replace("+", "Plus").replace("-", "Minus")
				.replace("/", "Devide").replace("*",
						name.contains("opeartor") ? "Multiply" : "Pointer")
				.replace("&", "Reference").replace("=", "Equals").replace("!",
						"Not").replace(">>", "ShiftRight").replace("<<",
						"ShiftLeft").replace("~", "DTOR_");
		if (getName().equals(getParent().getName())) {
			normalised = "CTOR_" + normalised;
		}

		return normalised;
	}

	// Get Normalised Name
	public String getNormalisedName() {
		return iNormalisedMethodName;
	}

	// Get Normalised Full Name
	public String getNormalisedFullName() {
		return iNormalisedMethodFullName;
	}

	public List<String[]> getParameters() {
		return iParameters;
	}

	public void setParameters(List<String[]> parameters) {
		iParameters = parameters;
		iFullName = fullName(getName());
		iNormalisedMethodFullName = normalisedName(iFullName);
	}

	public void addParameters(String type, String name) {
		iParameters.add(new String[] { type, name });
		iFullName = fullName(getName());
		iNormalisedMethodFullName = normalisedName(iFullName);
	}

	// For TreeSet
	public int compareTo(INode o) {
		return iNormalisedMethodFullName
				.compareToIgnoreCase(((MethodNodeImpl) o).iNormalisedMethodFullName);
	}

	public boolean isConstructor() {
		return iConstructor;
	}

	public boolean isDestructor() {
		return iDestructor;
	}

	public boolean isInline() {
		return iInline;
	}

	public boolean isOperator() {
		return iOperator;
	}

	public boolean isPureVirtual() {
		return iPureVirtual;
	}

	public boolean isStatic() {
		return iStatic;
	}

	public boolean isVirtual() {
		return iVirtual;
	}

	public void setConstructor(boolean constructor) {
		iConstructor = constructor;
	}

	public void setDestructor(boolean destructor) {
		iDestructor = destructor;
	}

	public void setInline(boolean inline) {
		iInline = inline;
	}

	public void setOperator(boolean operator) {
		iOperator = operator;
	}

	public void setPureVirtual(boolean pureVirtual) {
		iPureVirtual = pureVirtual;
	}

	public void setStatic(boolean static1) {
		iStatic = static1;
	}

	public void setVirtual(boolean virtual) {
		iVirtual = virtual;
	}

	public int getVisibility() {
		return iVisibility;
	}

	public void setVisibility(int visibility) {
		iVisibility = visibility;
	}

	public boolean isConst() {
		return iConst;
	}

	public void setConst(boolean const1) {
		iConst = const1;
	}

	public Object clone() {
		MethodNodeImpl clone = new MethodNodeImpl(getName(),
				(ClassNodeImpl) getParent());
		clone.iAsync = iAsync;
		clone.iConst = iConst;
		clone.iConstructor = iConstructor;
		clone.iDestructor = iDestructor;
		clone.iFullName = iFullName;
		clone.iInline = iInline;
		clone.iNormalisedMethodName = iNormalisedMethodName;
		clone.iNormalisedMethodFullName = iNormalisedMethodFullName;
		clone.iOperator = iOperator;
		clone.iPureVirtual = iPureVirtual;
		clone.iStatic = iStatic;
		clone.iVirtual = iVirtual;
		clone.iVisibility = iVisibility;
		clone.iParameters = new ArrayList<String[]>(iParameters);
		return clone;
	}
}
