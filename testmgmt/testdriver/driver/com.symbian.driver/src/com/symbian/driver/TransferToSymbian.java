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
 * A representation of the model object '<em><b>Transfer To Symbian</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This task transfers a file from the PC to the Symbian device (hardware or emulator).
 * 
 * 				In the build phase of TestDriver, all the files specified in the "transferToSymbian" operation will be collected in a
 * 				repository and a SIS package will be created irrespective of Platform Security (PlatSec) being on or off.
 * 
 * 				In the run phase of TestDriver the action depends on weather PlatSec is on or off. If PlatSec is on then the SIS file will
 * 				be transfered and installed at the start of the task and un-installed and deleted at the end of the task. If PlatSec is off
 * 				then the files in the repository will be transfered at the start of the task and deleted at the end of the task.
 * 
 * 				The "PCPath" of "TransferToSymbian" accepts the following variables: "${epocroot}", "${sourceroot}", "${xmlroot}",
 * 				"${platform}" and lastly "${build}". These variables point to the locations and settings specified by the command
 * 				"TestDriver config" on the command line. If these variables are used thus creating an absolute path then the wildcard "*" is
 * 				permissible to copy over all files in a directory, for example "${epocroot}\a\b\${platform}\${build}\d\e\*" will copy all
 * 				files in the directory "e" to the Symbian device.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.TransferToSymbian#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.TransferToSymbian#getTransfer <em>Transfer</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getTransferToSymbian()
 * @model extendedMetaData="name='transferToSymbian' kind='elementOnly'"
 * @generated
 */
public interface TransferToSymbian extends EObject {
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
	 * @see com.symbian.driver.DriverPackage#getTransferToSymbian_Group()
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
	 * @see com.symbian.driver.DriverPackage#getTransferToSymbian_Transfer()
	 * @model containment="true" required="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transfer' group='#group:0'"
	 * @generated
	 */
	EList<Transfer> getTransfer();

} // TransferToSymbian