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
 * A representation of the model object '<em><b>Rtest</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This test is depreciated.
 * 
 * 				Copies, runs and retrieves R-Test's. Please see RTest documentation for further details.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Rtest#getResultFile <em>Result File</em>}</li>
 *   <li>{@link com.symbian.driver.Rtest#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getRtest()
 * @model extendedMetaData="name='rtest' kind='empty'"
 * @generated
 */
public interface Rtest extends EObject {
	/**
	 * Returns the value of the '<em><b>Result File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result File</em>' attribute.
	 * @see #setResultFile(String)
	 * @see com.symbian.driver.DriverPackage#getRtest_ResultFile()
	 * @model unique="false" dataType="com.symbian.driver.SymbianPath"
	 *        extendedMetaData="kind='attribute' name='ResultFile'"
	 * @generated
	 */
	String getResultFile();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Rtest#getResultFile <em>Result File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result File</em>' attribute.
	 * @see #getResultFile()
	 * @generated
	 */
	void setResultFile(String value);

	/**
	 * Returns the value of the '<em><b>Symbian Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Symbian Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Symbian Path</em>' attribute.
	 * @see #setSymbianPath(String)
	 * @see com.symbian.driver.DriverPackage#getRtest_SymbianPath()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='SymbianPath'"
	 * @generated
	 */
	String getSymbianPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Rtest#getSymbianPath <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbian Path</em>' attribute.
	 * @see #getSymbianPath()
	 * @generated
	 */
	void setSymbianPath(String value);

} // Rtest