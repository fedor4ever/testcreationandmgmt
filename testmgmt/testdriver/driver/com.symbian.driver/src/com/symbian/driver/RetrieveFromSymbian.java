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

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Retrieve From Symbian</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This task transfers a file from the Symbian device (hardware or emulator) to the PC in the run phase of TestDriver. If no PC
 * 				path is specified, then the file will be retrieved to the TestDriver result folder.
 * 
 * 				The "PCPath" does not currently support variables and/or wildcards.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.RetrieveFromSymbian#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.RetrieveFromSymbian#getTransfer <em>Transfer</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getRetrieveFromSymbian()
 * @model extendedMetaData="name='retrieveFromSymbian' kind='elementOnly'"
 * @generated
 */
public interface RetrieveFromSymbian extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see com.symbian.driver.DriverPackage#getRetrieveFromSymbian_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Transfer</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Transfer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getRetrieveFromSymbian_Transfer()
	 * @model containment="true" required="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transfer' group='#group:0'"
	 * @generated
	 */
	EList<Transfer> getTransfer();

} // RetrieveFromSymbian