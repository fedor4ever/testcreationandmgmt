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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Print</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.Print#getPrint <em>Print</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getPrint()
 * @model
 * @generated
 */
public interface Print extends Leaf {
	/**
	 * Returns the value of the '<em><b>Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Print</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Print</em>' attribute.
	 * @see #setPrint(String)
	 * @see com.symbian.tef.script.ScriptPackage#getPrint_Print()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getPrint();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Print#getPrint <em>Print</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Print</em>' attribute.
	 * @see #getPrint()
	 * @generated
	 */
	void setPrint(String value);

} // Print