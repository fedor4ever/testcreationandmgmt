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
 * A representation of the model object '<em><b>Load Suite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.LoadSuite#getServer <em>Server</em>}</li>
 *   <li>{@link com.symbian.tef.script.LoadSuite#isSharedData <em>Shared Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getLoadSuite()
 * @model
 * @generated
 */
public interface LoadSuite extends Leaf {
	/**
	 * Returns the value of the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' attribute.
	 * @see #setServer(String)
	 * @see com.symbian.tef.script.ScriptPackage#getLoadSuite_Server()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.LoadSuite#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Returns the value of the '<em><b>Shared Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Data</em>' attribute.
	 * @see #setSharedData(boolean)
	 * @see com.symbian.tef.script.ScriptPackage#getLoadSuite_SharedData()
	 * @model required="true"
	 * @generated
	 */
	boolean isSharedData();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.LoadSuite#isSharedData <em>Shared Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Data</em>' attribute.
	 * @see #isSharedData()
	 * @generated
	 */
	void setSharedData(boolean value);

} // LoadSuite