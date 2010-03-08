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
 * A representation of the model object '<em><b>Execute On Symbian</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This task allows you to execute a command on the Symbian device (hardware or emulator). Use the generic "CmdSymbian" to run
 * 				any STAT command on the symbian board. Use "testExecuteScript" or "RTest" to run a test on the device.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.ExecuteOnSymbian#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.ExecuteOnSymbian#getCmd <em>Cmd</em>}</li>
 *   <li>{@link com.symbian.driver.ExecuteOnSymbian#getTestExecuteScript <em>Test Execute Script</em>}</li>
 *   <li>{@link com.symbian.driver.ExecuteOnSymbian#getRtest <em>Rtest</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getExecuteOnSymbian()
 * @model extendedMetaData="name='executeOnSymbian' kind='elementOnly'"
 * @generated
 */
public interface ExecuteOnSymbian extends EObject {
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
	 * @see com.symbian.driver.DriverPackage#getExecuteOnSymbian_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Cmd</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.CmdSymbian}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmd</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmd</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getExecuteOnSymbian_Cmd()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cmd' group='#group:0'"
	 * @generated
	 */
	EList<CmdSymbian> getCmd();

	/**
	 * Returns the value of the '<em><b>Test Execute Script</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.TestExecuteScript}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Execute Script</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Execute Script</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getExecuteOnSymbian_TestExecuteScript()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='testExecuteScript' group='#group:0'"
	 * @generated
	 */
	EList<TestExecuteScript> getTestExecuteScript();

	/**
	 * Returns the value of the '<em><b>Rtest</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Rtest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rtest</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rtest</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getExecuteOnSymbian_Rtest()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rtest' group='#group:0'"
	 * @generated
	 */
	EList<Rtest> getRtest();

} // ExecuteOnSymbian