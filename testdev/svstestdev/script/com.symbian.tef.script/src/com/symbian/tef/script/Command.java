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

import com.symbian.ini.Section;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.Command#getError <em>Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.Command#getAsyncError <em>Async Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.Command#getObjectName <em>Object Name</em>}</li>
 *   <li>{@link com.symbian.tef.script.Command#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link com.symbian.tef.script.Command#getSection <em>Section</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getCommand()
 * @model
 * @generated
 */
public interface Command extends Leaf {
	/**
	 * Returns the value of the '<em><b>Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error</em>' attribute.
	 * @see #isSetError()
	 * @see #unsetError()
	 * @see #setError(Integer)
	 * @see com.symbian.tef.script.ScriptPackage#getCommand_Error()
	 * @model unsettable="true"
	 * @generated
	 */
	Integer getError();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Command#getError <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error</em>' attribute.
	 * @see #isSetError()
	 * @see #unsetError()
	 * @see #getError()
	 * @generated
	 */
	void setError(Integer value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.Command#getError <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetError()
	 * @see #getError()
	 * @see #setError(Integer)
	 * @generated
	 */
	void unsetError();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.Command#getError <em>Error</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Error</em>' attribute is set.
	 * @see #unsetError()
	 * @see #getError()
	 * @see #setError(Integer)
	 * @generated
	 */
	boolean isSetError();

	/**
	 * Returns the value of the '<em><b>Async Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Async Error</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Async Error</em>' attribute.
	 * @see #isSetAsyncError()
	 * @see #unsetAsyncError()
	 * @see #setAsyncError(Integer)
	 * @see com.symbian.tef.script.ScriptPackage#getCommand_AsyncError()
	 * @model unsettable="true"
	 * @generated
	 */
	Integer getAsyncError();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Command#getAsyncError <em>Async Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async Error</em>' attribute.
	 * @see #isSetAsyncError()
	 * @see #unsetAsyncError()
	 * @see #getAsyncError()
	 * @generated
	 */
	void setAsyncError(Integer value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.Command#getAsyncError <em>Async Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsyncError()
	 * @see #getAsyncError()
	 * @see #setAsyncError(Integer)
	 * @generated
	 */
	void unsetAsyncError();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.Command#getAsyncError <em>Async Error</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Async Error</em>' attribute is set.
	 * @see #unsetAsyncError()
	 * @see #getAsyncError()
	 * @see #setAsyncError(Integer)
	 * @generated
	 */
	boolean isSetAsyncError();

	/**
	 * Returns the value of the '<em><b>Object Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Name</em>' attribute.
	 * @see #setObjectName(String)
	 * @see com.symbian.tef.script.ScriptPackage#getCommand_ObjectName()
	 * @model required="true"
	 * @generated
	 */
	String getObjectName();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Command#getObjectName <em>Object Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Name</em>' attribute.
	 * @see #getObjectName()
	 * @generated
	 */
	void setObjectName(String value);

	/**
	 * Returns the value of the '<em><b>Function Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Name</em>' attribute.
	 * @see #setFunctionName(String)
	 * @see com.symbian.tef.script.ScriptPackage#getCommand_FunctionName()
	 * @model required="true"
	 * @generated
	 */
	String getFunctionName();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Command#getFunctionName <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Name</em>' attribute.
	 * @see #getFunctionName()
	 * @generated
	 */
	void setFunctionName(String value);

	/**
	 * Returns the value of the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Section</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Section</em>' reference.
	 * @see #setSection(Section)
	 * @see com.symbian.tef.script.ScriptPackage#getCommand_Section()
	 * @model
	 * @generated
	 */
	Section getSection();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Command#getSection <em>Section</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section</em>' reference.
	 * @see #getSection()
	 * @generated
	 */
	void setSection(Section value);

} // Command
