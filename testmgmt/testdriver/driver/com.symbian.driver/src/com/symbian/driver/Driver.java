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


package com.symbian.driver;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Driver</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				The root element of all Driver files. This must contain a task element and can contain one driverInfo element.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Driver#getDriverInfo <em>Driver Info</em>}</li>
 *   <li>{@link com.symbian.driver.Driver#getTask <em>Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getDriver()
 * @model extendedMetaData="name='driver' kind='elementOnly'"
 * @generated
 */
public interface Driver extends EObject {
	/**
	 * Returns the value of the '<em><b>Driver Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver Info</em>' containment reference.
	 * @see #setDriverInfo(DriverInfo)
	 * @see com.symbian.driver.DriverPackage#getDriver_DriverInfo()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='driverInfo'"
	 * @generated
	 */
	DriverInfo getDriverInfo();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Driver#getDriverInfo <em>Driver Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver Info</em>' containment reference.
	 * @see #getDriverInfo()
	 * @generated
	 */
	void setDriverInfo(DriverInfo value);

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference.
	 * @see #setTask(Task)
	 * @see com.symbian.driver.DriverPackage#getDriver_Task()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='task'"
	 * @generated
	 */
	Task getTask();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Driver#getTask <em>Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' containment reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(Task value);

} // Driver