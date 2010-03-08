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

import com.symbian.comment.AttachedComment;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.TestStep#getServer <em>Server</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getMethod <em>Method</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getError <em>Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getPanicString <em>Panic String</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getPanicCode <em>Panic Code</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getResult <em>Result</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getHeap <em>Heap</em>}</li>
 *   <li>{@link com.symbian.tef.script.TestStep#getOOM <em>OOM</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getTestStep()
 * @model
 * @generated
 */
public interface TestStep extends AttachedComment, SectionPesistance, Leaf {
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
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Server()
	 * @model required="true"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

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
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Timeout()
	 * @model default="100" required="true"
	 * @generated
	 */
	int getTimeout();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(int value);

	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(String)
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Method()
	 * @model required="true"
	 * @generated
	 */
	String getMethod();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(String value);

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
	 * @see #setError(String)
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Error()
	 * @model unsettable="true"
	 *        extendedMetaData="pattern='\\S+'"
	 * @generated
	 */
	String getError();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getError <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error</em>' attribute.
	 * @see #isSetError()
	 * @see #unsetError()
	 * @see #getError()
	 * @generated
	 */
	void setError(String value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getError <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetError()
	 * @see #getError()
	 * @see #setError(String)
	 * @generated
	 */
	void unsetError();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getError <em>Error</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Error</em>' attribute is set.
	 * @see #unsetError()
	 * @see #getError()
	 * @see #setError(String)
	 * @generated
	 */
	boolean isSetError();

	/**
	 * Returns the value of the '<em><b>Panic String</b></em>' attribute.
	 * The default value is <code>""</code>.
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
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_PanicString()
	 * @model default="" unsettable="true"
	 *        extendedMetaData="pattern='\\S+'"
	 * @generated
	 */
	String getPanicString();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getPanicString <em>Panic String</em>}' attribute.
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
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getPanicString <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPanicString()
	 * @see #getPanicString()
	 * @see #setPanicString(String)
	 * @generated
	 */
	void unsetPanicString();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getPanicString <em>Panic String</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Panic String</em>' attribute is set.
	 * @see #unsetPanicString()
	 * @see #getPanicString()
	 * @see #setPanicString(String)
	 * @generated
	 */
	boolean isSetPanicString();

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
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_PanicCode()
	 * @model unsettable="true"
	 * @generated
	 */
	int getPanicCode();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getPanicCode <em>Panic Code</em>}' attribute.
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
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getPanicCode <em>Panic Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPanicCode()
	 * @see #getPanicCode()
	 * @see #setPanicCode(int)
	 * @generated
	 */
	void unsetPanicCode();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getPanicCode <em>Panic Code</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' attribute.
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #setResult(String)
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Result()
	 * @model unsettable="true"
	 *        extendedMetaData="pattern='\\S+'"
	 * @generated
	 */
	String getResult();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' attribute.
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #getResult()
	 * @generated
	 */
	void setResult(String value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResult()
	 * @see #getResult()
	 * @see #setResult(String)
	 * @generated
	 */
	void unsetResult();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getResult <em>Result</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Result</em>' attribute is set.
	 * @see #unsetResult()
	 * @see #getResult()
	 * @see #setResult(String)
	 * @generated
	 */
	boolean isSetResult();

	/**
	 * Returns the value of the '<em><b>Heap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heap</em>' attribute.
	 * @see #isSetHeap()
	 * @see #unsetHeap()
	 * @see #setHeap(int)
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_Heap()
	 * @model unsettable="true"
	 * @generated
	 */
	int getHeap();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getHeap <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heap</em>' attribute.
	 * @see #isSetHeap()
	 * @see #unsetHeap()
	 * @see #getHeap()
	 * @generated
	 */
	void setHeap(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getHeap <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHeap()
	 * @see #getHeap()
	 * @see #setHeap(int)
	 * @generated
	 */
	void unsetHeap();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getHeap <em>Heap</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Heap</em>' attribute is set.
	 * @see #unsetHeap()
	 * @see #getHeap()
	 * @see #setHeap(int)
	 * @generated
	 */
	boolean isSetHeap();

	/**
	 * Returns the value of the '<em><b>OOM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OOM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OOM</em>' attribute.
	 * @see #isSetOOM()
	 * @see #unsetOOM()
	 * @see #setOOM(int)
	 * @see com.symbian.tef.script.ScriptPackage#getTestStep_OOM()
	 * @model unsettable="true"
	 * @generated
	 */
	int getOOM();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.TestStep#getOOM <em>OOM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OOM</em>' attribute.
	 * @see #isSetOOM()
	 * @see #unsetOOM()
	 * @see #getOOM()
	 * @generated
	 */
	void setOOM(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.TestStep#getOOM <em>OOM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOOM()
	 * @see #getOOM()
	 * @see #setOOM(int)
	 * @generated
	 */
	void unsetOOM();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.TestStep#getOOM <em>OOM</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>OOM</em>' attribute is set.
	 * @see #unsetOOM()
	 * @see #getOOM()
	 * @see #setOOM(int)
	 * @generated
	 */
	boolean isSetOOM();

} // TestStep