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
 * A representation of the model object '<em><b>Outstanding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.Outstanding#getPollInterval <em>Poll Interval</em>}</li>
 *   <li>{@link com.symbian.tef.script.Outstanding#getObjectName <em>Object Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getOutstanding()
 * @model
 * @generated
 */
public interface Outstanding extends Leaf {
	/**
	 * Returns the value of the '<em><b>Poll Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Poll Interval</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Poll Interval</em>' attribute.
	 * @see #isSetPollInterval()
	 * @see #unsetPollInterval()
	 * @see #setPollInterval(Integer)
	 * @see com.symbian.tef.script.ScriptPackage#getOutstanding_PollInterval()
	 * @model unsettable="true"
	 * @generated
	 */
	Integer getPollInterval();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Outstanding#getPollInterval <em>Poll Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Poll Interval</em>' attribute.
	 * @see #isSetPollInterval()
	 * @see #unsetPollInterval()
	 * @see #getPollInterval()
	 * @generated
	 */
	void setPollInterval(Integer value);

	/**
	 * Unsets the value of the '{@link com.symbian.tef.script.Outstanding#getPollInterval <em>Poll Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPollInterval()
	 * @see #getPollInterval()
	 * @see #setPollInterval(Integer)
	 * @generated
	 */
	void unsetPollInterval();

	/**
	 * Returns whether the value of the '{@link com.symbian.tef.script.Outstanding#getPollInterval <em>Poll Interval</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Poll Interval</em>' attribute is set.
	 * @see #unsetPollInterval()
	 * @see #getPollInterval()
	 * @see #setPollInterval(Integer)
	 * @generated
	 */
	boolean isSetPollInterval();

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
	 * @see com.symbian.tef.script.ScriptPackage#getOutstanding_ObjectName()
	 * @model
	 * @generated
	 */
	String getObjectName();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.Outstanding#getObjectName <em>Object Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Name</em>' attribute.
	 * @see #getObjectName()
	 * @generated
	 */
	void setObjectName(String value);

} // Outstanding
