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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Run Utils</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.RunUtils#getUtilityCommand <em>Utility Command</em>}</li>
 *   <li>{@link com.symbian.tef.script.RunUtils#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getRunUtils()
 * @model
 * @generated
 */
public interface RunUtils extends Leaf {
	/**
	 * Returns the value of the '<em><b>Utility Command</b></em>' attribute.
	 * The literals are from the enumeration {@link com.symbian.tef.script.UtilityCommand}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Utility Command</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utility Command</em>' attribute.
	 * @see com.symbian.tef.script.UtilityCommand
	 * @see #setUtilityCommand(UtilityCommand)
	 * @see com.symbian.tef.script.ScriptPackage#getRunUtils_UtilityCommand()
	 * @model id="true" required="true"
	 * @generated
	 */
	UtilityCommand getUtilityCommand();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.RunUtils#getUtilityCommand <em>Utility Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utility Command</em>' attribute.
	 * @see com.symbian.tef.script.UtilityCommand
	 * @see #getUtilityCommand()
	 * @generated
	 */
	void setUtilityCommand(UtilityCommand value);

	/**
	 * Returns the value of the '<em><b>Param</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Param</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Param</em>' attribute list.
	 * @see com.symbian.tef.script.ScriptPackage#getRunUtils_Param()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getParam();

} // RunUtils