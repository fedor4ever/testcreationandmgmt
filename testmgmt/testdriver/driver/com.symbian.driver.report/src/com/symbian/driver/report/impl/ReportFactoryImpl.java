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


package com.symbian.driver.report.impl;

import com.symbian.driver.report.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReportFactoryImpl extends EFactoryImpl implements ReportFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReportFactory init() {
		try {
			ReportFactory theReportFactory = (ReportFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.symbian.com/DriverReport"); 
			if (theReportFactory != null) {
				return theReportFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReportFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ReportPackage.BASE_REPORT: return createBaseReport();
			case ReportPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ReportPackage.EXCEPTION_REPORT: return createExceptionReport();
			case ReportPackage.GENERIC_REPORT: return createGenericReport();
			case ReportPackage.INFO: return (EObject)createInfo();
			case ReportPackage.REPORT: return createReport();
			case ReportPackage.REPORT_INFO: return createReportInfo();
			case ReportPackage.TEF_REPORT: return createTefReport();
			case ReportPackage.TEF_TEST_CASE: return createTefTestCase();
			case ReportPackage.TEF_TEST_CASE_SUMMARY: return createTefTestCaseSummary();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM: return createTefTestRunWsProgram();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY: return createTefTestRunWsProgramSummary();
			case ReportPackage.TEF_TEST_STEP: return createTefTestStep();
			case ReportPackage.TEF_TEST_STEP_SUMMARY: return createTefTestStepSummary();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ReportPackage.GENERIC_RESULT:
				return createGenericResultFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_CASE_RESULT:
				return createTefTestCaseResultFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_RESULT:
				return createTefTestRunWsProgramResultFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_STEP_RESULT:
				return createTefTestStepResultFromString(eDataType, initialValue);
			case ReportPackage.GENERIC_RESULT_OBJECT:
				return createGenericResultObjectFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_CASE_RESULT_OBJECT:
				return createTefTestCaseResultObjectFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_RESULT_OBJECT:
				return createTefTestRunWsProgramResultObjectFromString(eDataType, initialValue);
			case ReportPackage.TEF_TEST_STEP_RESULT_OBJECT:
				return createTefTestStepResultObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ReportPackage.GENERIC_RESULT:
				return convertGenericResultToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_CASE_RESULT:
				return convertTefTestCaseResultToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_RESULT:
				return convertTefTestRunWsProgramResultToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_STEP_RESULT:
				return convertTefTestStepResultToString(eDataType, instanceValue);
			case ReportPackage.GENERIC_RESULT_OBJECT:
				return convertGenericResultObjectToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_CASE_RESULT_OBJECT:
				return convertTefTestCaseResultObjectToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_RESULT_OBJECT:
				return convertTefTestRunWsProgramResultObjectToString(eDataType, instanceValue);
			case ReportPackage.TEF_TEST_STEP_RESULT_OBJECT:
				return convertTefTestStepResultObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseReport createBaseReport() {
		BaseReportImpl baseReport = new BaseReportImpl();
		return baseReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionReport createExceptionReport() {
		ExceptionReportImpl exceptionReport = new ExceptionReportImpl();
		return exceptionReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericReport createGenericReport() {
		GenericReportImpl genericReport = new GenericReportImpl();
		return genericReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry createInfo() {
		InfoImpl info = new InfoImpl();
		return info;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Report createReport() {
		ReportImpl report = new ReportImpl();
		return report;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportInfo createReportInfo() {
		ReportInfoImpl reportInfo = new ReportInfoImpl();
		return reportInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefReport createTefReport() {
		TefReportImpl tefReport = new TefReportImpl();
		return tefReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestCase createTefTestCase() {
		TefTestCaseImpl tefTestCase = new TefTestCaseImpl();
		return tefTestCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestCaseSummary createTefTestCaseSummary() {
		TefTestCaseSummaryImpl tefTestCaseSummary = new TefTestCaseSummaryImpl();
		return tefTestCaseSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgram createTefTestRunWsProgram() {
		TefTestRunWsProgramImpl tefTestRunWsProgram = new TefTestRunWsProgramImpl();
		return tefTestRunWsProgram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgramSummary createTefTestRunWsProgramSummary() {
		TefTestRunWsProgramSummaryImpl tefTestRunWsProgramSummary = new TefTestRunWsProgramSummaryImpl();
		return tefTestRunWsProgramSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestStep createTefTestStep() {
		TefTestStepImpl tefTestStep = new TefTestStepImpl();
		return tefTestStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestStepSummary createTefTestStepSummary() {
		TefTestStepSummaryImpl tefTestStepSummary = new TefTestStepSummaryImpl();
		return tefTestStepSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericResult createGenericResultFromString(EDataType eDataType, String initialValue) {
		GenericResult result = GenericResult.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenericResultToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestCaseResult createTefTestCaseResultFromString(EDataType eDataType, String initialValue) {
		TefTestCaseResult result = TefTestCaseResult.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestCaseResultToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgramResult createTefTestRunWsProgramResultFromString(EDataType eDataType, String initialValue) {
		TefTestRunWsProgramResult result = TefTestRunWsProgramResult.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestRunWsProgramResultToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestStepResult createTefTestStepResultFromString(EDataType eDataType, String initialValue) {
		TefTestStepResult result = TefTestStepResult.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestStepResultToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericResult createGenericResultObjectFromString(EDataType eDataType, String initialValue) {
		return createGenericResultFromString(ReportPackage.Literals.GENERIC_RESULT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenericResultObjectToString(EDataType eDataType, Object instanceValue) {
		return convertGenericResultToString(ReportPackage.Literals.GENERIC_RESULT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestCaseResult createTefTestCaseResultObjectFromString(EDataType eDataType, String initialValue) {
		return createTefTestCaseResultFromString(ReportPackage.Literals.TEF_TEST_CASE_RESULT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestCaseResultObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTefTestCaseResultToString(ReportPackage.Literals.TEF_TEST_CASE_RESULT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgramResult createTefTestRunWsProgramResultObjectFromString(EDataType eDataType, String initialValue) {
		return createTefTestRunWsProgramResultFromString(ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_RESULT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestRunWsProgramResultObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTefTestRunWsProgramResultToString(ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_RESULT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestStepResult createTefTestStepResultObjectFromString(EDataType eDataType, String initialValue) {
		return createTefTestStepResultFromString(ReportPackage.Literals.TEF_TEST_STEP_RESULT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTefTestStepResultObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTefTestStepResultToString(ReportPackage.Literals.TEF_TEST_STEP_RESULT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportPackage getReportPackage() {
		return (ReportPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static ReportPackage getPackage() {
		return ReportPackage.eINSTANCE;
	}

} //ReportFactoryImpl
