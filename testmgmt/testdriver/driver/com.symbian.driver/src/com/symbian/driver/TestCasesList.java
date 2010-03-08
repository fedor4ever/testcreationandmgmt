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
 * A representation of the model object '<em><b>Test Cases List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.TestCasesList#getTestCase <em>Test Case</em>}</li>
 *   <li>{@link com.symbian.driver.TestCasesList#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getTestCasesList()
 * @model extendedMetaData="name='testCasesList' kind='elementOnly'"
 * @generated
 */
public interface TestCasesList extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Case</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.TestCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTestCasesList_TestCase()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='testCase'"
	 * @generated
	 */
	EList<TestCase> getTestCase();

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The default value is <code>"include"</code>.
	 * The literals are from the enumeration {@link com.symbian.driver.OperatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see com.symbian.driver.OperatorType
	 * @see #isSetOperator()
	 * @see #unsetOperator()
	 * @see #setOperator(OperatorType)
	 * @see com.symbian.driver.DriverPackage#getTestCasesList_Operator()
	 * @model default="include" unique="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='operator'"
	 * @generated
	 */
	OperatorType getOperator();

	/**
	 * Sets the value of the '{@link com.symbian.driver.TestCasesList#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see com.symbian.driver.OperatorType
	 * @see #isSetOperator()
	 * @see #unsetOperator()
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(OperatorType value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.TestCasesList#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOperator()
	 * @see #getOperator()
	 * @see #setOperator(OperatorType)
	 * @generated
	 */
	void unsetOperator();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.TestCasesList#getOperator <em>Operator</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Operator</em>' attribute is set.
	 * @see #unsetOperator()
	 * @see #getOperator()
	 * @see #setOperator(OperatorType)
	 * @generated
	 */
	boolean isSetOperator();

} // TestCasesList