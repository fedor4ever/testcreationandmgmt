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
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.Report#getReportInfo <em>Report Info</em>}</li>
 *   <li>{@link com.symbian.driver.report.Report#getAReport <em>AReport</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getReport()
 * @model extendedMetaData="name='report' kind='elementOnly'"
 * @generated
 */
public interface Report extends EObject {
	/**
	 * Returns the value of the '<em><b>Report Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Report Info</em>' containment reference.
	 * @see #setReportInfo(ReportInfo)
	 * @see com.symbian.driver.report.ReportPackage#getReport_ReportInfo()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='reportInfo'"
	 * @generated
	 */
	ReportInfo getReportInfo();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.Report#getReportInfo <em>Report Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Report Info</em>' containment reference.
	 * @see #getReportInfo()
	 * @generated
	 */
	void setReportInfo(ReportInfo value);

	/**
	 * Returns the value of the '<em><b>AReport</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.report.BaseReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AReport</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AReport</em>' containment reference list.
	 * @see com.symbian.driver.report.ReportPackage#getReport_AReport()
	 * @model type="com.symbian.driver.report.BaseReport" containment="true"
	 *        extendedMetaData="kind='element' name='aReport'"
	 * @generated
	 */
	EList getAReport();

} // Report