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


package com.symbian.driver.report;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tef Test Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.TefTestStep#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestStep#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getTefTestStep()
 * @model extendedMetaData="name='tefTestStep' kind='empty'"
 * @generated
 */
public interface TefTestStep extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestStep_Name()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestStep#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' attribute.
	 * The default value is <code>"pass"</code>.
	 * The literals are from the enumeration {@link com.symbian.driver.report.TefTestStepResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' attribute.
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #setResult(TefTestStepResult)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestStep_Result()
	 * @model default="pass" unique="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='result'"
	 * @generated
	 */
	TefTestStepResult getResult();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestStep#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' attribute.
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #getResult()
	 * @generated
	 */
	void setResult(TefTestStepResult value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestStep#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResult()
	 * @see #getResult()
	 * @see #setResult(TefTestStepResult)
	 * @generated
	 */
	void unsetResult();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestStep#getResult <em>Result</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Result</em>' attribute is set.
	 * @see #unsetResult()
	 * @see #getResult()
	 * @see #setResult(TefTestStepResult)
	 * @generated
	 */
	boolean isSetResult();

} // TefTestStep