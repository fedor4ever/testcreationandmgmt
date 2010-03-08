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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tef Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.TefReport#getTefTestCaseSummary <em>Tef Test Case Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefReport#getTefTestStepSummary <em>Tef Test Step Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefReport#getTefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefReport#getLog <em>Log</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getTefReport()
 * @model extendedMetaData="name='tefReport' kind='elementOnly'"
 * @generated
 */
public interface TefReport extends BaseReport {
	/**
	 * Returns the value of the '<em><b>Tef Test Case Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tef Test Case Summary</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tef Test Case Summary</em>' containment reference.
	 * @see #setTefTestCaseSummary(TefTestCaseSummary)
	 * @see com.symbian.driver.report.ReportPackage#getTefReport_TefTestCaseSummary()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='tefTestCaseSummary'"
	 * @generated
	 */
	TefTestCaseSummary getTefTestCaseSummary();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefReport#getTefTestCaseSummary <em>Tef Test Case Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tef Test Case Summary</em>' containment reference.
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	void setTefTestCaseSummary(TefTestCaseSummary value);

	/**
	 * Returns the value of the '<em><b>Tef Test Step Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tef Test Step Summary</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tef Test Step Summary</em>' containment reference.
	 * @see #setTefTestStepSummary(TefTestStepSummary)
	 * @see com.symbian.driver.report.ReportPackage#getTefReport_TefTestStepSummary()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='tefTestStepSummary'"
	 * @generated
	 */
	TefTestStepSummary getTefTestStepSummary();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefReport#getTefTestStepSummary <em>Tef Test Step Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tef Test Step Summary</em>' containment reference.
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	void setTefTestStepSummary(TefTestStepSummary value);

	/**
	 * Returns the value of the '<em><b>Tef Test Run Ws Program Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tef Test Run Ws Program Summary</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tef Test Run Ws Program Summary</em>' containment reference.
	 * @see #setTefTestRunWsProgramSummary(TefTestRunWsProgramSummary)
	 * @see com.symbian.driver.report.ReportPackage#getTefReport_TefTestRunWsProgramSummary()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='tefTestRunWsProgramSummary'"
	 * @generated
	 */
	TefTestRunWsProgramSummary getTefTestRunWsProgramSummary();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefReport#getTefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tef Test Run Ws Program Summary</em>' containment reference.
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	void setTefTestRunWsProgramSummary(TefTestRunWsProgramSummary value);

	/**
	 * Returns the value of the '<em><b>Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log</em>' attribute.
	 * @see #setLog(String)
	 * @see com.symbian.driver.report.ReportPackage#getTefReport_Log()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='log'"
	 * @generated
	 */
	String getLog();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefReport#getLog <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log</em>' attribute.
	 * @see #getLog()
	 * @generated
	 */
	void setLog(String value);

} // TefReport