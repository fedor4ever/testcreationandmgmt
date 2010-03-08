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


package com.symbian.ini;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.ini.IniModel#getIni <em>Ini</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.ini.IniPackage#getIniModel()
 * @model
 * @generated
 */
public interface IniModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Ini</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.ini.Ini}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ini</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ini</em>' containment reference list.
	 * @see com.symbian.ini.IniPackage#getIniModel_Ini()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Ini> getIni();

} // IniModel