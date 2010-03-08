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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportFactory
 * @model kind="package"
 * @generated
 */
public interface ReportPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "report";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.symbian.com/DriverReport";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "report";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReportPackage eINSTANCE = com.symbian.driver.report.impl.ReportPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.BaseReportImpl <em>Base Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.BaseReportImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getBaseReport()
	 * @generated
	 */
	int BASE_REPORT = 0;

	/**
	 * The feature id for the '<em><b>Execption Report</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__EXECPTION_REPORT = 0;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__DURATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__NAME = 2;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__TASK = 3;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__TIMEOUT = 4;

	/**
	 * The feature id for the '<em><b>Crash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__CRASH = 5;

	/**
	 * The feature id for the '<em><b>Coredump</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT__COREDUMP = 6;

	/**
	 * The number of structural features of the '<em>Base Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_REPORT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.DocumentRootImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Report</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REPORT = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.ExceptionReportImpl <em>Exception Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.ExceptionReportImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getExceptionReport()
	 * @generated
	 */
	int EXCEPTION_REPORT = 2;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_REPORT__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Stack Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_REPORT__STACK_TRACE = 1;

	/**
	 * The number of structural features of the '<em>Exception Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_REPORT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.GenericReportImpl <em>Generic Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.GenericReportImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericReport()
	 * @generated
	 */
	int GENERIC_REPORT = 3;

	/**
	 * The feature id for the '<em><b>Execption Report</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__EXECPTION_REPORT = BASE_REPORT__EXECPTION_REPORT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__DURATION = BASE_REPORT__DURATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__NAME = BASE_REPORT__NAME;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__TASK = BASE_REPORT__TASK;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__TIMEOUT = BASE_REPORT__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Crash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__CRASH = BASE_REPORT__CRASH;

	/**
	 * The feature id for the '<em><b>Coredump</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__COREDUMP = BASE_REPORT__COREDUMP;

	/**
	 * The feature id for the '<em><b>Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__LOG = BASE_REPORT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__TRACE = BASE_REPORT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT__RESULT = BASE_REPORT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Generic Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REPORT_FEATURE_COUNT = BASE_REPORT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.InfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.InfoImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getInfo()
	 * @generated
	 */
	int INFO = 4;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.ReportImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 5;

	/**
	 * The feature id for the '<em><b>Report Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__REPORT_INFO = 0;

	/**
	 * The feature id for the '<em><b>AReport</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__AREPORT = 1;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.ReportInfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.ReportInfoImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getReportInfo()
	 * @generated
	 */
	int REPORT_INFO = 6;

	/**
	 * The feature id for the '<em><b>Info</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_INFO__INFO = 0;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_INFO_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefReportImpl <em>Tef Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefReportImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefReport()
	 * @generated
	 */
	int TEF_REPORT = 7;

	/**
	 * The feature id for the '<em><b>Execption Report</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__EXECPTION_REPORT = BASE_REPORT__EXECPTION_REPORT;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__DURATION = BASE_REPORT__DURATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__NAME = BASE_REPORT__NAME;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__TASK = BASE_REPORT__TASK;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__TIMEOUT = BASE_REPORT__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Crash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__CRASH = BASE_REPORT__CRASH;

	/**
	 * The feature id for the '<em><b>Coredump</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__COREDUMP = BASE_REPORT__COREDUMP;

	/**
	 * The feature id for the '<em><b>Tef Test Case Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__TEF_TEST_CASE_SUMMARY = BASE_REPORT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tef Test Step Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__TEF_TEST_STEP_SUMMARY = BASE_REPORT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tef Test Run Ws Program Summary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY = BASE_REPORT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT__LOG = BASE_REPORT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Tef Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_REPORT_FEATURE_COUNT = BASE_REPORT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestCaseImpl <em>Tef Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestCaseImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCase()
	 * @generated
	 */
	int TEF_TEST_CASE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE__RESULT = 1;

	/**
	 * The number of structural features of the '<em>Tef Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl <em>Tef Test Case Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestCaseSummaryImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseSummary()
	 * @generated
	 */
	int TEF_TEST_CASE_SUMMARY = 9;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__TEST_CASE = 0;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__COUNT = 1;

	/**
	 * The feature id for the '<em><b>Fail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__FAIL = 2;

	/**
	 * The feature id for the '<em><b>Inconclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__INCONCLUSIVE = 3;

	/**
	 * The feature id for the '<em><b>Pass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__PASS = 4;

	/**
	 * The feature id for the '<em><b>Skipped Selectively</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY = 5;

	/**
	 * The number of structural features of the '<em>Tef Test Case Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_CASE_SUMMARY_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestRunWsProgramImpl <em>Tef Test Run Ws Program</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestRunWsProgramImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgram()
	 * @generated
	 */
	int TEF_TEST_RUN_WS_PROGRAM = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM__RESULT = 1;

	/**
	 * The number of structural features of the '<em>Tef Test Run Ws Program</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl <em>Tef Test Run Ws Program Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramSummary()
	 * @generated
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY = 11;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE = 0;

	/**
	 * The feature id for the '<em><b>Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT = 1;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT = 2;

	/**
	 * The feature id for the '<em><b>Fail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL = 3;

	/**
	 * The feature id for the '<em><b>Inconclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE = 4;

	/**
	 * The feature id for the '<em><b>Panic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC = 5;

	/**
	 * The feature id for the '<em><b>Pass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS = 6;

	/**
	 * The feature id for the '<em><b>Unexecuted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED = 7;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN = 8;

	/**
	 * The number of structural features of the '<em>Tef Test Run Ws Program Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_RUN_WS_PROGRAM_SUMMARY_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestStepImpl <em>Tef Test Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestStepImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStep()
	 * @generated
	 */
	int TEF_TEST_STEP = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP__RESULT = 1;

	/**
	 * The number of structural features of the '<em>Tef Test Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.impl.TefTestStepSummaryImpl <em>Tef Test Step Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.impl.TefTestStepSummaryImpl
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepSummary()
	 * @generated
	 */
	int TEF_TEST_STEP_SUMMARY = 13;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__TEST_CASE = 0;

	/**
	 * The feature id for the '<em><b>Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__ABORT = 1;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__COUNT = 2;

	/**
	 * The feature id for the '<em><b>Fail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__FAIL = 3;

	/**
	 * The feature id for the '<em><b>Inconclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__INCONCLUSIVE = 4;

	/**
	 * The feature id for the '<em><b>Panic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__PANIC = 5;

	/**
	 * The feature id for the '<em><b>Pass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__PASS = 6;

	/**
	 * The feature id for the '<em><b>Unexecuted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__UNEXECUTED = 7;

	/**
	 * The feature id for the '<em><b>Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY__UNKNOWN = 8;

	/**
	 * The number of structural features of the '<em>Tef Test Step Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_TEST_STEP_SUMMARY_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.GenericResult <em>Generic Result</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.GenericResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericResult()
	 * @generated
	 */
	int GENERIC_RESULT = 14;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.TefTestCaseResult <em>Tef Test Case Result</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestCaseResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseResult()
	 * @generated
	 */
	int TEF_TEST_CASE_RESULT = 15;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.TefTestRunWsProgramResult <em>Tef Test Run Ws Program Result</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestRunWsProgramResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramResult()
	 * @generated
	 */
	int TEF_TEST_RUN_WS_PROGRAM_RESULT = 16;

	/**
	 * The meta object id for the '{@link com.symbian.driver.report.TefTestStepResult <em>Tef Test Step Result</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepResult()
	 * @generated
	 */
	int TEF_TEST_STEP_RESULT = 17;

	/**
	 * The meta object id for the '<em>Generic Result Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.GenericResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericResultObject()
	 * @generated
	 */
	int GENERIC_RESULT_OBJECT = 18;

	/**
	 * The meta object id for the '<em>Tef Test Case Result Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestCaseResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseResultObject()
	 * @generated
	 */
	int TEF_TEST_CASE_RESULT_OBJECT = 19;

	/**
	 * The meta object id for the '<em>Tef Test Run Ws Program Result Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestRunWsProgramResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramResultObject()
	 * @generated
	 */
	int TEF_TEST_RUN_WS_PROGRAM_RESULT_OBJECT = 20;

	/**
	 * The meta object id for the '<em>Tef Test Step Result Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepResultObject()
	 * @generated
	 */
	int TEF_TEST_STEP_RESULT_OBJECT = 21;


	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.BaseReport <em>Base Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Report</em>'.
	 * @see com.symbian.driver.report.BaseReport
	 * @generated
	 */
	EClass getBaseReport();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.report.BaseReport#getExecptionReport <em>Execption Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execption Report</em>'.
	 * @see com.symbian.driver.report.BaseReport#getExecptionReport()
	 * @see #getBaseReport()
	 * @generated
	 */
	EReference getBaseReport_ExecptionReport();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see com.symbian.driver.report.BaseReport#getDuration()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Duration();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.driver.report.BaseReport#getName()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task</em>'.
	 * @see com.symbian.driver.report.BaseReport#getTask()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Task();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#isTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.driver.report.BaseReport#isTimeout()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#isCrash <em>Crash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Crash</em>'.
	 * @see com.symbian.driver.report.BaseReport#isCrash()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Crash();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.BaseReport#getCoredump <em>Coredump</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coredump</em>'.
	 * @see com.symbian.driver.report.BaseReport#getCoredump()
	 * @see #getBaseReport()
	 * @generated
	 */
	EAttribute getBaseReport_Coredump();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see com.symbian.driver.report.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.report.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.symbian.driver.report.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link com.symbian.driver.report.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see com.symbian.driver.report.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link com.symbian.driver.report.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see com.symbian.driver.report.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.report.DocumentRoot#getReport <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Report</em>'.
	 * @see com.symbian.driver.report.DocumentRoot#getReport()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Report();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.ExceptionReport <em>Exception Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception Report</em>'.
	 * @see com.symbian.driver.report.ExceptionReport
	 * @generated
	 */
	EClass getExceptionReport();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.ExceptionReport#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see com.symbian.driver.report.ExceptionReport#getMessage()
	 * @see #getExceptionReport()
	 * @generated
	 */
	EAttribute getExceptionReport_Message();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.ExceptionReport#getStackTrace <em>Stack Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stack Trace</em>'.
	 * @see com.symbian.driver.report.ExceptionReport#getStackTrace()
	 * @see #getExceptionReport()
	 * @generated
	 */
	EAttribute getExceptionReport_StackTrace();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.GenericReport <em>Generic Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Report</em>'.
	 * @see com.symbian.driver.report.GenericReport
	 * @generated
	 */
	EClass getGenericReport();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.GenericReport#getLog <em>Log</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log</em>'.
	 * @see com.symbian.driver.report.GenericReport#getLog()
	 * @see #getGenericReport()
	 * @generated
	 */
	EAttribute getGenericReport_Log();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.GenericReport#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace</em>'.
	 * @see com.symbian.driver.report.GenericReport#getTrace()
	 * @see #getGenericReport()
	 * @generated
	 */
	EAttribute getGenericReport_Trace();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.GenericReport#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.symbian.driver.report.GenericReport#getResult()
	 * @see #getGenericReport()
	 * @generated
	 */
	EAttribute getGenericReport_Result();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see java.util.Map.Entry
	 * @model keyUnique="false" keyDataType="org.eclipse.emf.ecore.xml.type.String"
	 *        keyExtendedMetaData="kind='attribute' name='key'"
	 *        valueUnique="false" valueDataType="org.eclipse.emf.ecore.xml.type.String"
	 *        valueExtendedMetaData="kind='attribute' name='value'"
	 *        extendedMetaData="name='info' kind='empty'"
	 * @generated
	 */
	EClass getInfo();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Value();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see com.symbian.driver.report.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.report.Report#getReportInfo <em>Report Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Report Info</em>'.
	 * @see com.symbian.driver.report.Report#getReportInfo()
	 * @see #getReport()
	 * @generated
	 */
	EReference getReport_ReportInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.report.Report#getAReport <em>AReport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>AReport</em>'.
	 * @see com.symbian.driver.report.Report#getAReport()
	 * @see #getReport()
	 * @generated
	 */
	EReference getReport_AReport();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.ReportInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see com.symbian.driver.report.ReportInfo
	 * @generated
	 */
	EClass getReportInfo();

	/**
	 * Returns the meta object for the map '{@link com.symbian.driver.report.ReportInfo#getInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Info</em>'.
	 * @see com.symbian.driver.report.ReportInfo#getInfo()
	 * @see #getReportInfo()
	 * @generated
	 */
	EReference getReportInfo_Info();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefReport <em>Tef Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Report</em>'.
	 * @see com.symbian.driver.report.TefReport
	 * @generated
	 */
	EClass getTefReport();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.report.TefReport#getTefTestCaseSummary <em>Tef Test Case Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tef Test Case Summary</em>'.
	 * @see com.symbian.driver.report.TefReport#getTefTestCaseSummary()
	 * @see #getTefReport()
	 * @generated
	 */
	EReference getTefReport_TefTestCaseSummary();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.report.TefReport#getTefTestStepSummary <em>Tef Test Step Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tef Test Step Summary</em>'.
	 * @see com.symbian.driver.report.TefReport#getTefTestStepSummary()
	 * @see #getTefReport()
	 * @generated
	 */
	EReference getTefReport_TefTestStepSummary();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.report.TefReport#getTefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tef Test Run Ws Program Summary</em>'.
	 * @see com.symbian.driver.report.TefReport#getTefTestRunWsProgramSummary()
	 * @see #getTefReport()
	 * @generated
	 */
	EReference getTefReport_TefTestRunWsProgramSummary();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefReport#getLog <em>Log</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log</em>'.
	 * @see com.symbian.driver.report.TefReport#getLog()
	 * @see #getTefReport()
	 * @generated
	 */
	EAttribute getTefReport_Log();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestCase <em>Tef Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Case</em>'.
	 * @see com.symbian.driver.report.TefTestCase
	 * @generated
	 */
	EClass getTefTestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.driver.report.TefTestCase#getName()
	 * @see #getTefTestCase()
	 * @generated
	 */
	EAttribute getTefTestCase_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCase#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.symbian.driver.report.TefTestCase#getResult()
	 * @see #getTefTestCase()
	 * @generated
	 */
	EAttribute getTefTestCase_Result();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestCaseSummary <em>Tef Test Case Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Case Summary</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary
	 * @generated
	 */
	EClass getTefTestCaseSummary();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.report.TefTestCaseSummary#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Case</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getTestCase()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EReference getTefTestCaseSummary_TestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCaseSummary#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getCount()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EAttribute getTefTestCaseSummary_Count();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCaseSummary#getFail <em>Fail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getFail()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EAttribute getTefTestCaseSummary_Fail();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCaseSummary#getInconclusive <em>Inconclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inconclusive</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getInconclusive()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EAttribute getTefTestCaseSummary_Inconclusive();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCaseSummary#getPass <em>Pass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pass</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getPass()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EAttribute getTefTestCaseSummary_Pass();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively <em>Skipped Selectively</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Skipped Selectively</em>'.
	 * @see com.symbian.driver.report.TefTestCaseSummary#getSkippedSelectively()
	 * @see #getTefTestCaseSummary()
	 * @generated
	 */
	EAttribute getTefTestCaseSummary_SkippedSelectively();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestRunWsProgram <em>Tef Test Run Ws Program</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Run Ws Program</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgram
	 * @generated
	 */
	EClass getTefTestRunWsProgram();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgram#getName()
	 * @see #getTefTestRunWsProgram()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgram_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgram#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgram#getResult()
	 * @see #getTefTestRunWsProgram()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgram_Result();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Run Ws Program Summary</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary
	 * @generated
	 */
	EClass getTefTestRunWsProgramSummary();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Case</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getTestCase()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EReference getTefTestRunWsProgramSummary_TestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort <em>Abort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abort</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Abort();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getCount()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Count();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getFail <em>Fail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getFail()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Fail();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive <em>Inconclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inconclusive</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Inconclusive();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic <em>Panic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Panic();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPass <em>Pass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pass</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getPass()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Pass();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted <em>Unexecuted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unexecuted</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Unexecuted();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unknown</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown()
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 */
	EAttribute getTefTestRunWsProgramSummary_Unknown();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestStep <em>Tef Test Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Step</em>'.
	 * @see com.symbian.driver.report.TefTestStep
	 * @generated
	 */
	EClass getTefTestStep();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStep#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.driver.report.TefTestStep#getName()
	 * @see #getTefTestStep()
	 * @generated
	 */
	EAttribute getTefTestStep_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStep#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.symbian.driver.report.TefTestStep#getResult()
	 * @see #getTefTestStep()
	 * @generated
	 */
	EAttribute getTefTestStep_Result();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.report.TefTestStepSummary <em>Tef Test Step Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Test Step Summary</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary
	 * @generated
	 */
	EClass getTefTestStepSummary();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.report.TefTestStepSummary#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Case</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getTestCase()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EReference getTefTestStepSummary_TestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getAbort <em>Abort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abort</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getAbort()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Abort();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getCount()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Count();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getFail <em>Fail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getFail()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Fail();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getInconclusive <em>Inconclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inconclusive</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getInconclusive()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Inconclusive();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getPanic <em>Panic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getPanic()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Panic();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getPass <em>Pass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pass</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getPass()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Pass();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getUnexecuted <em>Unexecuted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unexecuted</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getUnexecuted()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Unexecuted();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.report.TefTestStepSummary#getUnknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unknown</em>'.
	 * @see com.symbian.driver.report.TefTestStepSummary#getUnknown()
	 * @see #getTefTestStepSummary()
	 * @generated
	 */
	EAttribute getTefTestStepSummary_Unknown();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.report.GenericResult <em>Generic Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generic Result</em>'.
	 * @see com.symbian.driver.report.GenericResult
	 * @generated
	 */
	EEnum getGenericResult();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.report.TefTestCaseResult <em>Tef Test Case Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tef Test Case Result</em>'.
	 * @see com.symbian.driver.report.TefTestCaseResult
	 * @generated
	 */
	EEnum getTefTestCaseResult();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.report.TefTestRunWsProgramResult <em>Tef Test Run Ws Program Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tef Test Run Ws Program Result</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramResult
	 * @generated
	 */
	EEnum getTefTestRunWsProgramResult();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.report.TefTestStepResult <em>Tef Test Step Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tef Test Step Result</em>'.
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @generated
	 */
	EEnum getTefTestStepResult();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.report.GenericResult <em>Generic Result Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Generic Result Object</em>'.
	 * @see com.symbian.driver.report.GenericResult
	 * @model instanceClass="com.symbian.driver.report.GenericResult"
	 *        extendedMetaData="name='result_._type:Object' baseType='result_._type'"
	 * @generated
	 */
	EDataType getGenericResultObject();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.report.TefTestCaseResult <em>Tef Test Case Result Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tef Test Case Result Object</em>'.
	 * @see com.symbian.driver.report.TefTestCaseResult
	 * @model instanceClass="com.symbian.driver.report.TefTestCaseResult"
	 *        extendedMetaData="name='result_._1_._type:Object' baseType='result_._1_._type'"
	 * @generated
	 */
	EDataType getTefTestCaseResultObject();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.report.TefTestRunWsProgramResult <em>Tef Test Run Ws Program Result Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tef Test Run Ws Program Result Object</em>'.
	 * @see com.symbian.driver.report.TefTestRunWsProgramResult
	 * @model instanceClass="com.symbian.driver.report.TefTestRunWsProgramResult"
	 *        extendedMetaData="name='result_._2_._type:Object' baseType='result_._2_._type'"
	 * @generated
	 */
	EDataType getTefTestRunWsProgramResultObject();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.report.TefTestStepResult <em>Tef Test Step Result Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tef Test Step Result Object</em>'.
	 * @see com.symbian.driver.report.TefTestStepResult
	 * @model instanceClass="com.symbian.driver.report.TefTestStepResult"
	 *        extendedMetaData="name='result_._3_._type:Object' baseType='result_._3_._type'"
	 * @generated
	 */
	EDataType getTefTestStepResultObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReportFactory getReportFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.BaseReportImpl <em>Base Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.BaseReportImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getBaseReport()
		 * @generated
		 */
		EClass BASE_REPORT = eINSTANCE.getBaseReport();

		/**
		 * The meta object literal for the '<em><b>Execption Report</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_REPORT__EXECPTION_REPORT = eINSTANCE.getBaseReport_ExecptionReport();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__DURATION = eINSTANCE.getBaseReport_Duration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__NAME = eINSTANCE.getBaseReport_Name();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__TASK = eINSTANCE.getBaseReport_Task();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__TIMEOUT = eINSTANCE.getBaseReport_Timeout();

		/**
		 * The meta object literal for the '<em><b>Crash</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__CRASH = eINSTANCE.getBaseReport_Crash();

		/**
		 * The meta object literal for the '<em><b>Coredump</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASE_REPORT__COREDUMP = eINSTANCE.getBaseReport_Coredump();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.DocumentRootImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Report</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REPORT = eINSTANCE.getDocumentRoot_Report();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.ExceptionReportImpl <em>Exception Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.ExceptionReportImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getExceptionReport()
		 * @generated
		 */
		EClass EXCEPTION_REPORT = eINSTANCE.getExceptionReport();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_REPORT__MESSAGE = eINSTANCE.getExceptionReport_Message();

		/**
		 * The meta object literal for the '<em><b>Stack Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_REPORT__STACK_TRACE = eINSTANCE.getExceptionReport_StackTrace();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.GenericReportImpl <em>Generic Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.GenericReportImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericReport()
		 * @generated
		 */
		EClass GENERIC_REPORT = eINSTANCE.getGenericReport();

		/**
		 * The meta object literal for the '<em><b>Log</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_REPORT__LOG = eINSTANCE.getGenericReport_Log();

		/**
		 * The meta object literal for the '<em><b>Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_REPORT__TRACE = eINSTANCE.getGenericReport_Trace();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_REPORT__RESULT = eINSTANCE.getGenericReport_Result();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.InfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.InfoImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getInfo()
		 * @generated
		 */
		EClass INFO = eINSTANCE.getInfo();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__KEY = eINSTANCE.getInfo_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__VALUE = eINSTANCE.getInfo_Value();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.ReportImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '<em><b>Report Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT__REPORT_INFO = eINSTANCE.getReport_ReportInfo();

		/**
		 * The meta object literal for the '<em><b>AReport</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT__AREPORT = eINSTANCE.getReport_AReport();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.ReportInfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.ReportInfoImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getReportInfo()
		 * @generated
		 */
		EClass REPORT_INFO = eINSTANCE.getReportInfo();

		/**
		 * The meta object literal for the '<em><b>Info</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT_INFO__INFO = eINSTANCE.getReportInfo_Info();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefReportImpl <em>Tef Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefReportImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefReport()
		 * @generated
		 */
		EClass TEF_REPORT = eINSTANCE.getTefReport();

		/**
		 * The meta object literal for the '<em><b>Tef Test Case Summary</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_REPORT__TEF_TEST_CASE_SUMMARY = eINSTANCE.getTefReport_TefTestCaseSummary();

		/**
		 * The meta object literal for the '<em><b>Tef Test Step Summary</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_REPORT__TEF_TEST_STEP_SUMMARY = eINSTANCE.getTefReport_TefTestStepSummary();

		/**
		 * The meta object literal for the '<em><b>Tef Test Run Ws Program Summary</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY = eINSTANCE.getTefReport_TefTestRunWsProgramSummary();

		/**
		 * The meta object literal for the '<em><b>Log</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_REPORT__LOG = eINSTANCE.getTefReport_Log();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestCaseImpl <em>Tef Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestCaseImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCase()
		 * @generated
		 */
		EClass TEF_TEST_CASE = eINSTANCE.getTefTestCase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE__NAME = eINSTANCE.getTefTestCase_Name();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE__RESULT = eINSTANCE.getTefTestCase_Result();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl <em>Tef Test Case Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestCaseSummaryImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseSummary()
		 * @generated
		 */
		EClass TEF_TEST_CASE_SUMMARY = eINSTANCE.getTefTestCaseSummary();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_TEST_CASE_SUMMARY__TEST_CASE = eINSTANCE.getTefTestCaseSummary_TestCase();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE_SUMMARY__COUNT = eINSTANCE.getTefTestCaseSummary_Count();

		/**
		 * The meta object literal for the '<em><b>Fail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE_SUMMARY__FAIL = eINSTANCE.getTefTestCaseSummary_Fail();

		/**
		 * The meta object literal for the '<em><b>Inconclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE_SUMMARY__INCONCLUSIVE = eINSTANCE.getTefTestCaseSummary_Inconclusive();

		/**
		 * The meta object literal for the '<em><b>Pass</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE_SUMMARY__PASS = eINSTANCE.getTefTestCaseSummary_Pass();

		/**
		 * The meta object literal for the '<em><b>Skipped Selectively</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY = eINSTANCE.getTefTestCaseSummary_SkippedSelectively();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestRunWsProgramImpl <em>Tef Test Run Ws Program</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestRunWsProgramImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgram()
		 * @generated
		 */
		EClass TEF_TEST_RUN_WS_PROGRAM = eINSTANCE.getTefTestRunWsProgram();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM__NAME = eINSTANCE.getTefTestRunWsProgram_Name();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM__RESULT = eINSTANCE.getTefTestRunWsProgram_Result();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl <em>Tef Test Run Ws Program Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramSummary()
		 * @generated
		 */
		EClass TEF_TEST_RUN_WS_PROGRAM_SUMMARY = eINSTANCE.getTefTestRunWsProgramSummary();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE = eINSTANCE.getTefTestRunWsProgramSummary_TestCase();

		/**
		 * The meta object literal for the '<em><b>Abort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT = eINSTANCE.getTefTestRunWsProgramSummary_Abort();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT = eINSTANCE.getTefTestRunWsProgramSummary_Count();

		/**
		 * The meta object literal for the '<em><b>Fail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL = eINSTANCE.getTefTestRunWsProgramSummary_Fail();

		/**
		 * The meta object literal for the '<em><b>Inconclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE = eINSTANCE.getTefTestRunWsProgramSummary_Inconclusive();

		/**
		 * The meta object literal for the '<em><b>Panic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC = eINSTANCE.getTefTestRunWsProgramSummary_Panic();

		/**
		 * The meta object literal for the '<em><b>Pass</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS = eINSTANCE.getTefTestRunWsProgramSummary_Pass();

		/**
		 * The meta object literal for the '<em><b>Unexecuted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED = eINSTANCE.getTefTestRunWsProgramSummary_Unexecuted();

		/**
		 * The meta object literal for the '<em><b>Unknown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN = eINSTANCE.getTefTestRunWsProgramSummary_Unknown();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestStepImpl <em>Tef Test Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestStepImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStep()
		 * @generated
		 */
		EClass TEF_TEST_STEP = eINSTANCE.getTefTestStep();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP__NAME = eINSTANCE.getTefTestStep_Name();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP__RESULT = eINSTANCE.getTefTestStep_Result();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.impl.TefTestStepSummaryImpl <em>Tef Test Step Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.impl.TefTestStepSummaryImpl
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepSummary()
		 * @generated
		 */
		EClass TEF_TEST_STEP_SUMMARY = eINSTANCE.getTefTestStepSummary();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_TEST_STEP_SUMMARY__TEST_CASE = eINSTANCE.getTefTestStepSummary_TestCase();

		/**
		 * The meta object literal for the '<em><b>Abort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__ABORT = eINSTANCE.getTefTestStepSummary_Abort();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__COUNT = eINSTANCE.getTefTestStepSummary_Count();

		/**
		 * The meta object literal for the '<em><b>Fail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__FAIL = eINSTANCE.getTefTestStepSummary_Fail();

		/**
		 * The meta object literal for the '<em><b>Inconclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__INCONCLUSIVE = eINSTANCE.getTefTestStepSummary_Inconclusive();

		/**
		 * The meta object literal for the '<em><b>Panic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__PANIC = eINSTANCE.getTefTestStepSummary_Panic();

		/**
		 * The meta object literal for the '<em><b>Pass</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__PASS = eINSTANCE.getTefTestStepSummary_Pass();

		/**
		 * The meta object literal for the '<em><b>Unexecuted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__UNEXECUTED = eINSTANCE.getTefTestStepSummary_Unexecuted();

		/**
		 * The meta object literal for the '<em><b>Unknown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEF_TEST_STEP_SUMMARY__UNKNOWN = eINSTANCE.getTefTestStepSummary_Unknown();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.GenericResult <em>Generic Result</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.GenericResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericResult()
		 * @generated
		 */
		EEnum GENERIC_RESULT = eINSTANCE.getGenericResult();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.TefTestCaseResult <em>Tef Test Case Result</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestCaseResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseResult()
		 * @generated
		 */
		EEnum TEF_TEST_CASE_RESULT = eINSTANCE.getTefTestCaseResult();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.TefTestRunWsProgramResult <em>Tef Test Run Ws Program Result</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestRunWsProgramResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramResult()
		 * @generated
		 */
		EEnum TEF_TEST_RUN_WS_PROGRAM_RESULT = eINSTANCE.getTefTestRunWsProgramResult();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.report.TefTestStepResult <em>Tef Test Step Result</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestStepResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepResult()
		 * @generated
		 */
		EEnum TEF_TEST_STEP_RESULT = eINSTANCE.getTefTestStepResult();

		/**
		 * The meta object literal for the '<em>Generic Result Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.GenericResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getGenericResultObject()
		 * @generated
		 */
		EDataType GENERIC_RESULT_OBJECT = eINSTANCE.getGenericResultObject();

		/**
		 * The meta object literal for the '<em>Tef Test Case Result Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestCaseResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestCaseResultObject()
		 * @generated
		 */
		EDataType TEF_TEST_CASE_RESULT_OBJECT = eINSTANCE.getTefTestCaseResultObject();

		/**
		 * The meta object literal for the '<em>Tef Test Run Ws Program Result Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestRunWsProgramResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestRunWsProgramResultObject()
		 * @generated
		 */
		EDataType TEF_TEST_RUN_WS_PROGRAM_RESULT_OBJECT = eINSTANCE.getTefTestRunWsProgramResultObject();

		/**
		 * The meta object literal for the '<em>Tef Test Step Result Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.report.TefTestStepResult
		 * @see com.symbian.driver.report.impl.ReportPackageImpl#getTefTestStepResultObject()
		 * @generated
		 */
		EDataType TEF_TEST_STEP_RESULT_OBJECT = eINSTANCE.getTefTestStepResultObject();

	}

} //ReportPackage
