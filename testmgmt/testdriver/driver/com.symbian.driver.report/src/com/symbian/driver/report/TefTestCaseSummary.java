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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tef Test Case Summary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getTestCase <em>Test Case</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getCount <em>Count</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getFail <em>Fail</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getInconclusive <em>Inconclusive</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getPass <em>Pass</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively <em>Skipped Selectively</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary()
 * @model extendedMetaData="name='tefTestCaseSummary' kind='elementOnly'"
 * @generated
 */
public interface TefTestCaseSummary extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Case</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.report.TefTestCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case</em>' containment reference list.
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_TestCase()
	 * @model type="com.symbian.driver.report.TefTestCase" containment="true"
	 *        extendedMetaData="kind='element' name='testCase'"
	 * @generated
	 */
	EList getTestCase();

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #isSetCount()
	 * @see #unsetCount()
	 * @see #setCount(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_Count()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='count'"
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #isSetCount()
	 * @see #unsetCount()
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCount()
	 * @see #getCount()
	 * @see #setCount(int)
	 * @generated
	 */
	void unsetCount();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getCount <em>Count</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Count</em>' attribute is set.
	 * @see #unsetCount()
	 * @see #getCount()
	 * @see #setCount(int)
	 * @generated
	 */
	boolean isSetCount();

	/**
	 * Returns the value of the '<em><b>Fail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail</em>' attribute.
	 * @see #isSetFail()
	 * @see #unsetFail()
	 * @see #setFail(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_Fail()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='fail'"
	 * @generated
	 */
	int getFail();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getFail <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail</em>' attribute.
	 * @see #isSetFail()
	 * @see #unsetFail()
	 * @see #getFail()
	 * @generated
	 */
	void setFail(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getFail <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFail()
	 * @see #getFail()
	 * @see #setFail(int)
	 * @generated
	 */
	void unsetFail();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getFail <em>Fail</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fail</em>' attribute is set.
	 * @see #unsetFail()
	 * @see #getFail()
	 * @see #setFail(int)
	 * @generated
	 */
	boolean isSetFail();

	/**
	 * Returns the value of the '<em><b>Inconclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inconclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inconclusive</em>' attribute.
	 * @see #isSetInconclusive()
	 * @see #unsetInconclusive()
	 * @see #setInconclusive(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_Inconclusive()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='inconclusive'"
	 * @generated
	 */
	int getInconclusive();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getInconclusive <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inconclusive</em>' attribute.
	 * @see #isSetInconclusive()
	 * @see #unsetInconclusive()
	 * @see #getInconclusive()
	 * @generated
	 */
	void setInconclusive(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getInconclusive <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInconclusive()
	 * @see #getInconclusive()
	 * @see #setInconclusive(int)
	 * @generated
	 */
	void unsetInconclusive();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getInconclusive <em>Inconclusive</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Inconclusive</em>' attribute is set.
	 * @see #unsetInconclusive()
	 * @see #getInconclusive()
	 * @see #setInconclusive(int)
	 * @generated
	 */
	boolean isSetInconclusive();

	/**
	 * Returns the value of the '<em><b>Pass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pass</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pass</em>' attribute.
	 * @see #isSetPass()
	 * @see #unsetPass()
	 * @see #setPass(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_Pass()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='pass'"
	 * @generated
	 */
	int getPass();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getPass <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pass</em>' attribute.
	 * @see #isSetPass()
	 * @see #unsetPass()
	 * @see #getPass()
	 * @generated
	 */
	void setPass(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getPass <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPass()
	 * @see #getPass()
	 * @see #setPass(int)
	 * @generated
	 */
	void unsetPass();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getPass <em>Pass</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Pass</em>' attribute is set.
	 * @see #unsetPass()
	 * @see #getPass()
	 * @see #setPass(int)
	 * @generated
	 */
	boolean isSetPass();

	/**
	 * Returns the value of the '<em><b>Skipped Selectively</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skipped Selectively</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skipped Selectively</em>' attribute.
	 * @see #isSetSkippedSelectively()
	 * @see #unsetSkippedSelectively()
	 * @see #setSkippedSelectively(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestCaseSummary_SkippedSelectively()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='skipped_selectively'"
	 * @generated
	 */
	int getSkippedSelectively();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively <em>Skipped Selectively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skipped Selectively</em>' attribute.
	 * @see #isSetSkippedSelectively()
	 * @see #unsetSkippedSelectively()
	 * @see #getSkippedSelectively()
	 * @generated
	 */
	void setSkippedSelectively(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively <em>Skipped Selectively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSkippedSelectively()
	 * @see #getSkippedSelectively()
	 * @see #setSkippedSelectively(int)
	 * @generated
	 */
	void unsetSkippedSelectively();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively <em>Skipped Selectively</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Skipped Selectively</em>' attribute is set.
	 * @see #unsetSkippedSelectively()
	 * @see #getSkippedSelectively()
	 * @see #setSkippedSelectively(int)
	 * @generated
	 */
	boolean isSetSkippedSelectively();

} // TefTestCaseSummary