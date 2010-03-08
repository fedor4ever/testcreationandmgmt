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
 * A representation of the model object '<em><b>Test Execute Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				A TEF script file which will be transfered, run and the corresponding results retrieved to the TestDriver result folder. TEF
 * 				Scripts do not accept wildcards but do accept variables in the PCPath. During the build phase of TestDriver all the scripts
 * 				are collected into the repository and packaged into a SIS file. In the run phase they are subsequently installed or
 * 				transfered, run and then un-installed or deleted depending on if PlatSec is on or off respectively. Please see the
 * 				documentation for the TEF tests for further details.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.TestExecuteScript#getTestCasesList <em>Test Cases List</em>}</li>
 *   <li>{@link com.symbian.driver.TestExecuteScript#getPCPath <em>PC Path</em>}</li>
 *   <li>{@link com.symbian.driver.TestExecuteScript#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getTestExecuteScript()
 * @model extendedMetaData="name='testExecuteScript' kind='elementOnly'"
 * @generated
 */
public interface TestExecuteScript extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Cases List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Cases List</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Cases List</em>' containment reference.
	 * @see #isSetTestCasesList()
	 * @see #unsetTestCasesList()
	 * @see #setTestCasesList(TestCasesList)
	 * @see com.symbian.driver.DriverPackage#getTestExecuteScript_TestCasesList()
	 * @model containment="true" unsettable="true"
	 *        extendedMetaData="kind='element' name='testCasesList'"
	 * @generated
	 */
	TestCasesList getTestCasesList();

	/**
	 * Sets the value of the '{@link com.symbian.driver.TestExecuteScript#getTestCasesList <em>Test Cases List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Cases List</em>' containment reference.
	 * @see #isSetTestCasesList()
	 * @see #unsetTestCasesList()
	 * @see #getTestCasesList()
	 * @generated
	 */
	void setTestCasesList(TestCasesList value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.TestExecuteScript#getTestCasesList <em>Test Cases List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTestCasesList()
	 * @see #getTestCasesList()
	 * @see #setTestCasesList(TestCasesList)
	 * @generated
	 */
	void unsetTestCasesList();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.TestExecuteScript#getTestCasesList <em>Test Cases List</em>}' containment reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Test Cases List</em>' containment reference is set.
	 * @see #unsetTestCasesList()
	 * @see #getTestCasesList()
	 * @see #setTestCasesList(TestCasesList)
	 * @generated
	 */
	boolean isSetTestCasesList();

	/**
	 * Returns the value of the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PC Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PC Path</em>' attribute.
	 * @see #setPCPath(String)
	 * @see com.symbian.driver.DriverPackage#getTestExecuteScript_PCPath()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='PCPath'"
	 * @generated
	 */
	String getPCPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.TestExecuteScript#getPCPath <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PC Path</em>' attribute.
	 * @see #getPCPath()
	 * @generated
	 */
	void setPCPath(String value);

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
	 * @see com.symbian.driver.DriverPackage#getTestExecuteScript_SymbianPath()
	 * @model unique="false" dataType="com.symbian.driver.SymbianPath" required="true"
	 *        extendedMetaData="kind='attribute' name='SymbianPath'"
	 * @generated
	 */
	String getSymbianPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.TestExecuteScript#getSymbianPath <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbian Path</em>' attribute.
	 * @see #getSymbianPath()
	 * @generated
	 */
	void setSymbianPath(String value);

} // TestExecuteScript