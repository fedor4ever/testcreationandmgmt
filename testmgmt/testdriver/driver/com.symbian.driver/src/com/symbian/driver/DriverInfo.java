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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Contains extra user information which gets put in the report also. This is not directly used by TestDriver. It is an
 * 				annotation tool for Driver files.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.DriverInfo#getInfo <em>Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getDriverInfo()
 * @model extendedMetaData="name='driverInfo' kind='elementOnly'"
 * @generated
 */
public interface DriverInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Info</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Info}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Info</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getDriverInfo_Info()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='info'"
	 * @generated
	 */
	EList<Info> getInfo();

} // DriverInfo