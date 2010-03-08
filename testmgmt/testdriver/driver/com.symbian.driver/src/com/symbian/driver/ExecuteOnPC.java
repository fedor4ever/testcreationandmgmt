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
 * A representation of the model object '<em><b>Execute On PC</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This task allows you to execute a command on the PC. Use the generic "cmdPC" tag for executing command line tasks. Use
 * 				"build" to run a build for TestDriver.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.ExecuteOnPC#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.ExecuteOnPC#getCmd <em>Cmd</em>}</li>
 *   <li>{@link com.symbian.driver.ExecuteOnPC#getBuild <em>Build</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getExecuteOnPC()
 * @model extendedMetaData="name='executeOnPC' kind='elementOnly'"
 * @generated
 */
public interface ExecuteOnPC extends EObject {
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
	 * @see com.symbian.driver.DriverPackage#getExecuteOnPC_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Cmd</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.CmdPC}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmd</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmd</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getExecuteOnPC_Cmd()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cmd' group='#group:0'"
	 * @generated
	 */
	EList<CmdPC> getCmd();

	/**
	 * Returns the value of the '<em><b>Build</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Build}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Build</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Build</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getExecuteOnPC_Build()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='build' group='#group:0'"
	 * @generated
	 */
	EList<Build> getBuild();

	Build findDuplicateBuildByURI(String aUri);

} // ExecuteOnPC