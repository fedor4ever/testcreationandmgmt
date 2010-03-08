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
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This task allows you to reference another task, in either this file, or an any other driver XML file. This acts like an
 * 				import statement. Please note that the URI for this reference must be a fully qualified URI. The URI accepts the following
 * 				variables: "${epocroot}", "${sourceroot}", "${xmlroot}", "${platform}" and lastly "${build}".
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Reference#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getReference()
 * @model extendedMetaData="name='reference' kind='empty'"
 * @generated
 */
public interface Reference extends EObject {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uri</em>' reference.
	 * @see #setUri(Task)
	 * @see com.symbian.driver.DriverPackage#getReference_Uri()
	 * @model extendedMetaData="kind='attribute' name='uri'"
	 * @generated
	 */
	Task getUri();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Reference#getUri <em>Uri</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' reference.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(Task value);

} // Reference