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
 * A representation of the model object '<em><b>Test Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.TestBlock#getHeap <em>Heap</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestBlock#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestBlock#getServer <em>Server</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestBlock#getIniFile <em>Ini File</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestBlock#getPanicCode <em>Panic Code</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestBlock#getPanicString <em>Panic String</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getTestBlock()
 * @model
 * @generated
 */
public interface TestBlock extends Container {
	/**
	 * Returns the value of the '<em><b>Heap</b></em>' attribute.
	 * The default value is <code>"1048576"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heap</em>' attribute.
	 * @see #isSetHeap()
	 * @see #unsetHeap()
	 * @see #setHeap(Integer)
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_Heap()
	 * @model default="1048576" unsettable="true"
	 * @generated
	 */
	Integer getHeap();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getHeap <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heap</em>' attribute.
	 * @see #isSetHeap()
	 * @see #unsetHeap()
	 * @see #getHeap()
	 * @generated
	 */
	void setHeap(Integer value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestBlock#getHeap <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHeap()
	 * @see #getHeap()
	 * @see #setHeap(Integer)
	 * @generated
	 */
	void unsetHeap();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestBlock#getHeap <em>Heap</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Heap</em>' attribute is set.
	 * @see #unsetHeap()
	 * @see #getHeap()
	 * @see #setHeap(Integer)
	 * @generated
	 */
	boolean isSetHeap();

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * The default value is <code>"100"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(int)
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_Timeout()
	 * @model default="100" required="true"
	 * @generated
	 */
	int getTimeout();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(int value);

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
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_Server()
	 * @model required="true"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Returns the value of the '<em><b>Ini File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ini File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ini File</em>' attribute.
	 * @see #setIniFile(String)
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_IniFile()
	 * @model required="true"
	 * @generated
	 */
	String getIniFile();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getIniFile <em>Ini File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ini File</em>' attribute.
	 * @see #getIniFile()
	 * @generated
	 */
	void setIniFile(String value);

	/**
	 * Returns the value of the '<em><b>Panic Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panic Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panic Code</em>' attribute.
	 * @see #isSetPanicCode()
	 * @see #unsetPanicCode()
	 * @see #setPanicCode(int)
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_PanicCode()
	 * @model unsettable="true"
	 * @generated
	 */
	int getPanicCode();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getPanicCode <em>Panic Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panic Code</em>' attribute.
	 * @see #isSetPanicCode()
	 * @see #unsetPanicCode()
	 * @see #getPanicCode()
	 * @generated
	 */
	void setPanicCode(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestBlock#getPanicCode <em>Panic Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPanicCode()
	 * @see #getPanicCode()
	 * @see #setPanicCode(int)
	 * @generated
	 */
	void unsetPanicCode();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestBlock#getPanicCode <em>Panic Code</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Panic Code</em>' attribute is set.
	 * @see #unsetPanicCode()
	 * @see #getPanicCode()
	 * @see #setPanicCode(int)
	 * @generated
	 */
	boolean isSetPanicCode();

	/**
	 * Returns the value of the '<em><b>Panic String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panic String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panic String</em>' attribute.
	 * @see #isSetPanicString()
	 * @see #unsetPanicString()
	 * @see #setPanicString(String)
	 * @see com.symbian.tef.script.ScriptPackage#getTestBlock_PanicString()
	 * @model unsettable="true"
	 * @generated
	 */
	String getPanicString();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestBlock#getPanicString <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panic String</em>' attribute.
	 * @see #isSetPanicString()
	 * @see #unsetPanicString()
	 * @see #getPanicString()
	 * @generated
	 */
	void setPanicString(String value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestBlock#getPanicString <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPanicString()
	 * @see #getPanicString()
	 * @see #setPanicString(String)
	 * @generated
	 */
	void unsetPanicString();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestBlock#getPanicString <em>Panic String</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Panic String</em>' attribute is set.
	 * @see #unsetPanicString()
	 * @see #getPanicString()
	 * @see #setPanicString(String)
	 * @generated
	 */
	boolean isSetPanicString();

} // TestBlock
