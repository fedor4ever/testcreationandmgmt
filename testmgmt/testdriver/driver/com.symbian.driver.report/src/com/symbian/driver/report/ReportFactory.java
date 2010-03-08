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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage
 * @generated
 */
public interface ReportFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReportFactory eINSTANCE = com.symbian.driver.report.impl.ReportFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Base Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Base Report</em>'.
	 * @generated
	 */
	BaseReport createBaseReport();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Exception Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exception Report</em>'.
	 * @generated
	 */
	ExceptionReport createExceptionReport();

	/**
	 * Returns a new object of class '<em>Generic Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Report</em>'.
	 * @generated
	 */
	GenericReport createGenericReport();

	/**
	 * Returns a new object of class '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Report</em>'.
	 * @generated
	 */
	Report createReport();

	/**
	 * Returns a new object of class '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Info</em>'.
	 * @generated
	 */
	ReportInfo createReportInfo();

	/**
	 * Returns a new object of class '<em>Tef Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Report</em>'.
	 * @generated
	 */
	TefReport createTefReport();

	/**
	 * Returns a new object of class '<em>Tef Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Case</em>'.
	 * @generated
	 */
	TefTestCase createTefTestCase();

	/**
	 * Returns a new object of class '<em>Tef Test Case Summary</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Case Summary</em>'.
	 * @generated
	 */
	TefTestCaseSummary createTefTestCaseSummary();

	/**
	 * Returns a new object of class '<em>Tef Test Run Ws Program</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Run Ws Program</em>'.
	 * @generated
	 */
	TefTestRunWsProgram createTefTestRunWsProgram();

	/**
	 * Returns a new object of class '<em>Tef Test Run Ws Program Summary</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Run Ws Program Summary</em>'.
	 * @generated
	 */
	TefTestRunWsProgramSummary createTefTestRunWsProgramSummary();

	/**
	 * Returns a new object of class '<em>Tef Test Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Step</em>'.
	 * @generated
	 */
	TefTestStep createTefTestStep();

	/**
	 * Returns a new object of class '<em>Tef Test Step Summary</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Test Step Summary</em>'.
	 * @generated
	 */
	TefTestStepSummary createTefTestStepSummary();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReportPackage getReportPackage();

} //ReportFactory
