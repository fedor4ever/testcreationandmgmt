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


package com.symbian.tef.script;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tef Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.TefModel#getTef <em>Tef</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getTefModel()
 * @model
 * @generated
 */
public interface TefModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Tef</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.tef.script.Tef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tef</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tef</em>' containment reference list.
	 * @see com.symbian.tef.script.ScriptPackage#getTefModel_Tef()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Tef> getTef();

} // TefModel