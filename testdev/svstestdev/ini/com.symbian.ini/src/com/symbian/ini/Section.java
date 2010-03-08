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

import com.symbian.comment.AttachedComment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.ini.Section#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.ini.Section#getIniLeaf <em>Ini Leaf</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.ini.IniPackage#getSection()
 * @model
 * @generated
 */
public interface Section extends AttachedComment, Ini {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.symbian.ini.IniPackage#getSection_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.symbian.ini.Section#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Ini Leaf</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.ini.IniLeaf}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ini Leaf</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ini Leaf</em>' containment reference list.
	 * @see com.symbian.ini.IniPackage#getSection_IniLeaf()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<IniLeaf> getIniLeaf();

} // Section