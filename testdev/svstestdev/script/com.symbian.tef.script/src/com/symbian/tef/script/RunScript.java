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

import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Run Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.RunScript#getScript <em>Script</em>}</li>
 *   <li>{@link com.symbian.tef.script.RunScript#getScriptPersistance <em>Script Persistance</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getRunScript()
 * @model
 * @generated
 */
public interface RunScript extends Leaf {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(Resource)
	 * @see com.symbian.tef.script.ScriptPackage#getRunScript_Script()
	 * @model required="true" transient="true"
	 * @generated
	 */
	Resource getScript();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.RunScript#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(Resource value);

	/**
	 * Returns the value of the '<em><b>Script Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Persistance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Persistance</em>' attribute.
	 * @see #setScriptPersistance(String)
	 * @see com.symbian.tef.script.ScriptPackage#getRunScript_ScriptPersistance()
	 * @model required="true"
	 * @generated
	 */
	String getScriptPersistance();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.RunScript#getScriptPersistance <em>Script Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Persistance</em>' attribute.
	 * @see #getScriptPersistance()
	 * @generated
	 */
	void setScriptPersistance(String value);

} // RunScript