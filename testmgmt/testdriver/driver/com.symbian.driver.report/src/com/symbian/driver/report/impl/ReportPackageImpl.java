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

import com.symbian.driver.report.BaseReport;
import com.symbian.driver.report.DocumentRoot;
import com.symbian.driver.report.ExceptionReport;
import com.symbian.driver.report.GenericReport;
import com.symbian.driver.report.GenericResult;
import com.symbian.driver.report.Report;
import com.symbian.driver.report.ReportFactory;
import com.symbian.driver.report.ReportInfo;
import com.symbian.driver.report.ReportPackage;
import com.symbian.driver.report.TefReport;
import com.symbian.driver.report.TefTestCase;
import com.symbian.driver.report.TefTestCaseResult;
import com.symbian.driver.report.TefTestCaseSummary;
import com.symbian.driver.report.TefTestRunWsProgram;
import com.symbian.driver.report.TefTestRunWsProgramResult;
import com.symbian.driver.report.TefTestRunWsProgramSummary;
import com.symbian.driver.report.TefTestStep;
import com.symbian.driver.report.TefTestStepResult;
import com.symbian.driver.report.TefTestStepSummary;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReportPackageImpl extends EPackageImpl implements ReportPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reportInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestCaseSummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestRunWsProgramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestRunWsProgramSummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefTestStepSummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum genericResultEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tefTestCaseResultEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tefTestRunWsProgramResultEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tefTestStepResultEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType genericResultObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tefTestCaseResultObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tefTestRunWsProgramResultObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tefTestStepResultObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.symbian.driver.report.ReportPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ReportPackageImpl() {
		super(eNS_URI, ReportFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ReportPackage init() {
		if (isInited) return (ReportPackage)EPackage.Registry.INSTANCE.getEPackage(ReportPackage.eNS_URI);

		// Obtain or create and register package
		ReportPackageImpl theReportPackage = (ReportPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ReportPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ReportPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theReportPackage.createPackageContents();

		// Initialize created meta-data
		theReportPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theReportPackage.freeze();

		return theReportPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseReport() {
		return baseReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseReport_ExecptionReport() {
		return (EReference)baseReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Duration() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Name() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Task() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Timeout() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Crash() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseReport_Coredump() {
		return (EAttribute)baseReportEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Report() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExceptionReport() {
		return exceptionReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionReport_Message() {
		return (EAttribute)exceptionReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionReport_StackTrace() {
		return (EAttribute)exceptionReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericReport() {
		return genericReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericReport_Log() {
		return (EAttribute)genericReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericReport_Trace() {
		return (EAttribute)genericReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericReport_Result() {
		return (EAttribute)genericReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfo() {
		return infoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Key() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfo_Value() {
		return (EAttribute)infoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReport() {
		return reportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReport_ReportInfo() {
		return (EReference)reportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReport_AReport() {
		return (EReference)reportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReportInfo() {
		return reportInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReportInfo_Info() {
		return (EReference)reportInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefReport() {
		return tefReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefReport_TefTestCaseSummary() {
		return (EReference)tefReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefReport_TefTestStepSummary() {
		return (EReference)tefReportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefReport_TefTestRunWsProgramSummary() {
		return (EReference)tefReportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefReport_Log() {
		return (EAttribute)tefReportEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestCase() {
		return tefTestCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCase_Name() {
		return (EAttribute)tefTestCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCase_Result() {
		return (EAttribute)tefTestCaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestCaseSummary() {
		return tefTestCaseSummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefTestCaseSummary_TestCase() {
		return (EReference)tefTestCaseSummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCaseSummary_Count() {
		return (EAttribute)tefTestCaseSummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCaseSummary_Fail() {
		return (EAttribute)tefTestCaseSummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCaseSummary_Inconclusive() {
		return (EAttribute)tefTestCaseSummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCaseSummary_Pass() {
		return (EAttribute)tefTestCaseSummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestCaseSummary_SkippedSelectively() {
		return (EAttribute)tefTestCaseSummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestRunWsProgram() {
		return tefTestRunWsProgramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgram_Name() {
		return (EAttribute)tefTestRunWsProgramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgram_Result() {
		return (EAttribute)tefTestRunWsProgramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestRunWsProgramSummary() {
		return tefTestRunWsProgramSummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefTestRunWsProgramSummary_TestCase() {
		return (EReference)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Abort() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Count() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Fail() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Inconclusive() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Panic() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Pass() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Unexecuted() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestRunWsProgramSummary_Unknown() {
		return (EAttribute)tefTestRunWsProgramSummaryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestStep() {
		return tefTestStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStep_Name() {
		return (EAttribute)tefTestStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStep_Result() {
		return (EAttribute)tefTestStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefTestStepSummary() {
		return tefTestStepSummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefTestStepSummary_TestCase() {
		return (EReference)tefTestStepSummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Abort() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Count() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Fail() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Inconclusive() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Panic() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Pass() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Unexecuted() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTefTestStepSummary_Unknown() {
		return (EAttribute)tefTestStepSummaryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGenericResult() {
		return genericResultEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTefTestCaseResult() {
		return tefTestCaseResultEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTefTestRunWsProgramResult() {
		return tefTestRunWsProgramResultEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTefTestStepResult() {
		return tefTestStepResultEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGenericResultObject() {
		return genericResultObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTefTestCaseResultObject() {
		return tefTestCaseResultObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTefTestRunWsProgramResultObject() {
		return tefTestRunWsProgramResultObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTefTestStepResultObject() {
		return tefTestStepResultObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportFactory getReportFactory() {
		return (ReportFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		baseReportEClass = createEClass(BASE_REPORT);
		createEReference(baseReportEClass, BASE_REPORT__EXECPTION_REPORT);
		createEAttribute(baseReportEClass, BASE_REPORT__DURATION);
		createEAttribute(baseReportEClass, BASE_REPORT__NAME);
		createEAttribute(baseReportEClass, BASE_REPORT__TASK);
		createEAttribute(baseReportEClass, BASE_REPORT__TIMEOUT);
		createEAttribute(baseReportEClass, BASE_REPORT__CRASH);
		createEAttribute(baseReportEClass, BASE_REPORT__COREDUMP);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__REPORT);

		exceptionReportEClass = createEClass(EXCEPTION_REPORT);
		createEAttribute(exceptionReportEClass, EXCEPTION_REPORT__MESSAGE);
		createEAttribute(exceptionReportEClass, EXCEPTION_REPORT__STACK_TRACE);

		genericReportEClass = createEClass(GENERIC_REPORT);
		createEAttribute(genericReportEClass, GENERIC_REPORT__LOG);
		createEAttribute(genericReportEClass, GENERIC_REPORT__TRACE);
		createEAttribute(genericReportEClass, GENERIC_REPORT__RESULT);

		infoEClass = createEClass(INFO);
		createEAttribute(infoEClass, INFO__KEY);
		createEAttribute(infoEClass, INFO__VALUE);

		reportEClass = createEClass(REPORT);
		createEReference(reportEClass, REPORT__REPORT_INFO);
		createEReference(reportEClass, REPORT__AREPORT);

		reportInfoEClass = createEClass(REPORT_INFO);
		createEReference(reportInfoEClass, REPORT_INFO__INFO);

		tefReportEClass = createEClass(TEF_REPORT);
		createEReference(tefReportEClass, TEF_REPORT__TEF_TEST_CASE_SUMMARY);
		createEReference(tefReportEClass, TEF_REPORT__TEF_TEST_STEP_SUMMARY);
		createEReference(tefReportEClass, TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY);
		createEAttribute(tefReportEClass, TEF_REPORT__LOG);

		tefTestCaseEClass = createEClass(TEF_TEST_CASE);
		createEAttribute(tefTestCaseEClass, TEF_TEST_CASE__NAME);
		createEAttribute(tefTestCaseEClass, TEF_TEST_CASE__RESULT);

		tefTestCaseSummaryEClass = createEClass(TEF_TEST_CASE_SUMMARY);
		createEReference(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__TEST_CASE);
		createEAttribute(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__COUNT);
		createEAttribute(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__FAIL);
		createEAttribute(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__INCONCLUSIVE);
		createEAttribute(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__PASS);
		createEAttribute(tefTestCaseSummaryEClass, TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY);

		tefTestRunWsProgramEClass = createEClass(TEF_TEST_RUN_WS_PROGRAM);
		createEAttribute(tefTestRunWsProgramEClass, TEF_TEST_RUN_WS_PROGRAM__NAME);
		createEAttribute(tefTestRunWsProgramEClass, TEF_TEST_RUN_WS_PROGRAM__RESULT);

		tefTestRunWsProgramSummaryEClass = createEClass(TEF_TEST_RUN_WS_PROGRAM_SUMMARY);
		createEReference(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED);
		createEAttribute(tefTestRunWsProgramSummaryEClass, TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN);

		tefTestStepEClass = createEClass(TEF_TEST_STEP);
		createEAttribute(tefTestStepEClass, TEF_TEST_STEP__NAME);
		createEAttribute(tefTestStepEClass, TEF_TEST_STEP__RESULT);

		tefTestStepSummaryEClass = createEClass(TEF_TEST_STEP_SUMMARY);
		createEReference(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__TEST_CASE);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__ABORT);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__COUNT);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__FAIL);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__INCONCLUSIVE);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__PANIC);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__PASS);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__UNEXECUTED);
		createEAttribute(tefTestStepSummaryEClass, TEF_TEST_STEP_SUMMARY__UNKNOWN);

		// Create enums
		genericResultEEnum = createEEnum(GENERIC_RESULT);
		tefTestCaseResultEEnum = createEEnum(TEF_TEST_CASE_RESULT);
		tefTestRunWsProgramResultEEnum = createEEnum(TEF_TEST_RUN_WS_PROGRAM_RESULT);
		tefTestStepResultEEnum = createEEnum(TEF_TEST_STEP_RESULT);

		// Create data types
		genericResultObjectEDataType = createEDataType(GENERIC_RESULT_OBJECT);
		tefTestCaseResultObjectEDataType = createEDataType(TEF_TEST_CASE_RESULT_OBJECT);
		tefTestRunWsProgramResultObjectEDataType = createEDataType(TEF_TEST_RUN_WS_PROGRAM_RESULT_OBJECT);
		tefTestStepResultObjectEDataType = createEDataType(TEF_TEST_STEP_RESULT_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Add supertypes to classes
		genericReportEClass.getESuperTypes().add(this.getBaseReport());
		tefReportEClass.getESuperTypes().add(this.getBaseReport());

		// Initialize classes and features; add operations and parameters
		initEClass(baseReportEClass, BaseReport.class, "BaseReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBaseReport_ExecptionReport(), this.getExceptionReport(), null, "execptionReport", null, 0, -1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Duration(), theXMLTypePackage.getString(), "duration", null, 1, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Task(), theXMLTypePackage.getString(), "task", null, 1, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Timeout(), theXMLTypePackage.getBoolean(), "timeout", "false", 0, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Crash(), theXMLTypePackage.getBoolean(), "crash", "false", 0, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBaseReport_Coredump(), theXMLTypePackage.getAnyURI(), "coredump", null, 0, 1, BaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Report(), this.getReport(), null, "report", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(exceptionReportEClass, ExceptionReport.class, "ExceptionReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExceptionReport_Message(), theXMLTypePackage.getString(), "message", null, 1, 1, ExceptionReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionReport_StackTrace(), theXMLTypePackage.getString(), "stackTrace", null, 1, 1, ExceptionReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericReportEClass, GenericReport.class, "GenericReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGenericReport_Log(), theXMLTypePackage.getAnyURI(), "log", null, 1, 1, GenericReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericReport_Trace(), theXMLTypePackage.getAnyURI(), "trace", null, 0, 1, GenericReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericReport_Result(), this.getGenericResult(), "result", "error", 0, 1, GenericReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(infoEClass, Map.Entry.class, "Info", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInfo_Key(), theXMLTypePackage.getString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfo_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportEClass, Report.class, "Report", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReport_ReportInfo(), this.getReportInfo(), null, "reportInfo", null, 1, 1, Report.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReport_AReport(), this.getBaseReport(), null, "aReport", null, 0, -1, Report.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportInfoEClass, ReportInfo.class, "ReportInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReportInfo_Info(), this.getInfo(), null, "info", null, 1, -1, ReportInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefReportEClass, TefReport.class, "TefReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTefReport_TefTestCaseSummary(), this.getTefTestCaseSummary(), null, "tefTestCaseSummary", null, 0, 1, TefReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTefReport_TefTestStepSummary(), this.getTefTestStepSummary(), null, "tefTestStepSummary", null, 1, 1, TefReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTefReport_TefTestRunWsProgramSummary(), this.getTefTestRunWsProgramSummary(), null, "tefTestRunWsProgramSummary", null, 1, 1, TefReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefReport_Log(), theXMLTypePackage.getAnyURI(), "log", null, 1, 1, TefReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestCaseEClass, TefTestCase.class, "TefTestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTefTestCase_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, TefTestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCase_Result(), this.getTefTestCaseResult(), "result", "pass", 1, 1, TefTestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestCaseSummaryEClass, TefTestCaseSummary.class, "TefTestCaseSummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTefTestCaseSummary_TestCase(), this.getTefTestCase(), null, "testCase", null, 0, -1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCaseSummary_Count(), theXMLTypePackage.getInt(), "count", null, 1, 1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCaseSummary_Fail(), theXMLTypePackage.getInt(), "fail", null, 1, 1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCaseSummary_Inconclusive(), theXMLTypePackage.getInt(), "inconclusive", null, 1, 1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCaseSummary_Pass(), theXMLTypePackage.getInt(), "pass", null, 1, 1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestCaseSummary_SkippedSelectively(), theXMLTypePackage.getInt(), "skippedSelectively", null, 1, 1, TefTestCaseSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestRunWsProgramEClass, TefTestRunWsProgram.class, "TefTestRunWsProgram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTefTestRunWsProgram_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, TefTestRunWsProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgram_Result(), this.getTefTestRunWsProgramResult(), "result", "pass", 1, 1, TefTestRunWsProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestRunWsProgramSummaryEClass, TefTestRunWsProgramSummary.class, "TefTestRunWsProgramSummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTefTestRunWsProgramSummary_TestCase(), this.getTefTestRunWsProgram(), null, "testCase", null, 0, -1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Abort(), theXMLTypePackage.getInt(), "abort", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Count(), theXMLTypePackage.getInt(), "count", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Fail(), theXMLTypePackage.getInt(), "fail", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Inconclusive(), theXMLTypePackage.getInt(), "inconclusive", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Panic(), theXMLTypePackage.getInt(), "panic", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Pass(), theXMLTypePackage.getInt(), "pass", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Unexecuted(), theXMLTypePackage.getInt(), "unexecuted", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestRunWsProgramSummary_Unknown(), theXMLTypePackage.getInt(), "unknown", null, 1, 1, TefTestRunWsProgramSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestStepEClass, TefTestStep.class, "TefTestStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTefTestStep_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, TefTestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStep_Result(), this.getTefTestStepResult(), "result", "pass", 1, 1, TefTestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefTestStepSummaryEClass, TefTestStepSummary.class, "TefTestStepSummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTefTestStepSummary_TestCase(), this.getTefTestStep(), null, "testCase", null, 0, -1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Abort(), theXMLTypePackage.getInt(), "abort", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Count(), theXMLTypePackage.getInt(), "count", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Fail(), theXMLTypePackage.getInt(), "fail", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Inconclusive(), theXMLTypePackage.getInt(), "inconclusive", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Panic(), theXMLTypePackage.getInt(), "panic", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Pass(), theXMLTypePackage.getInt(), "pass", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Unexecuted(), theXMLTypePackage.getInt(), "unexecuted", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTefTestStepSummary_Unknown(), theXMLTypePackage.getInt(), "unknown", null, 1, 1, TefTestStepSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(genericResultEEnum, GenericResult.class, "GenericResult");
		addEEnumLiteral(genericResultEEnum, GenericResult.ERROR_LITERAL);
		addEEnumLiteral(genericResultEEnum, GenericResult.WARNING_LITERAL);
		addEEnumLiteral(genericResultEEnum, GenericResult.PASS_LITERAL);

		initEEnum(tefTestCaseResultEEnum, TefTestCaseResult.class, "TefTestCaseResult");
		addEEnumLiteral(tefTestCaseResultEEnum, TefTestCaseResult.PASS_LITERAL);
		addEEnumLiteral(tefTestCaseResultEEnum, TefTestCaseResult.FAIL_LITERAL);
		addEEnumLiteral(tefTestCaseResultEEnum, TefTestCaseResult.INCONCLUSIVE_LITERAL);
		addEEnumLiteral(tefTestCaseResultEEnum, TefTestCaseResult.SKIPPED_SELECTIVELY_LITERAL);

		initEEnum(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.class, "TefTestRunWsProgramResult");
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.PASS_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.FAIL_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.INCONCLUSIVE_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.ABORT_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.PANIC_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.UNKNOWN_LITERAL);
		addEEnumLiteral(tefTestRunWsProgramResultEEnum, TefTestRunWsProgramResult.UNEXECUTED_LITERAL);

		initEEnum(tefTestStepResultEEnum, TefTestStepResult.class, "TefTestStepResult");
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.PASS_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.FAIL_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.INCONCLUSIVE_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.ABORT_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.PANIC_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.UNKNOWN_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.UNEXECUTED_LITERAL);
		addEEnumLiteral(tefTestStepResultEEnum, TefTestStepResult.TEST_SUITE_ERROR_LITERAL);

		// Initialize data types
		initEDataType(genericResultObjectEDataType, GenericResult.class, "GenericResultObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tefTestCaseResultObjectEDataType, TefTestCaseResult.class, "TefTestCaseResultObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tefTestRunWsProgramResultObjectEDataType, TefTestRunWsProgramResult.class, "TefTestRunWsProgramResultObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tefTestStepResultObjectEDataType, TefTestStepResult.class, "TefTestStepResultObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (baseReportEClass, 
		   source, 
		   new String[] {
			 "name", "baseReport",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getBaseReport_ExecptionReport(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "execptionReport"
		   });		
		addAnnotation
		  (getBaseReport_Duration(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "duration"
		   });		
		addAnnotation
		  (getBaseReport_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getBaseReport_Task(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "task"
		   });		
		addAnnotation
		  (getBaseReport_Timeout(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "timeout"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Report(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "report",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (exceptionReportEClass, 
		   source, 
		   new String[] {
			 "name", "exceptionReport",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExceptionReport_Message(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "message"
		   });		
		addAnnotation
		  (getExceptionReport_StackTrace(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "stackTrace"
		   });		
		addAnnotation
		  (genericReportEClass, 
		   source, 
		   new String[] {
			 "name", "genericReport",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getGenericReport_Log(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "log"
		   });		
		addAnnotation
		  (getGenericReport_Result(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "result"
		   });		
		addAnnotation
		  (genericResultEEnum, 
		   source, 
		   new String[] {
			 "name", "result_._type"
		   });		
		addAnnotation
		  (genericResultObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "result_._type:Object",
			 "baseType", "result_._type"
		   });		
		addAnnotation
		  (infoEClass, 
		   source, 
		   new String[] {
			 "name", "info",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getInfo_Key(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "key"
		   });		
		addAnnotation
		  (getInfo_Value(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "value"
		   });		
		addAnnotation
		  (reportEClass, 
		   source, 
		   new String[] {
			 "name", "report",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getReport_ReportInfo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "reportInfo"
		   });		
		addAnnotation
		  (getReport_AReport(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "aReport"
		   });		
		addAnnotation
		  (reportInfoEClass, 
		   source, 
		   new String[] {
			 "name", "reportInfo",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getReportInfo_Info(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "info"
		   });		
		addAnnotation
		  (tefReportEClass, 
		   source, 
		   new String[] {
			 "name", "tefReport",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTefReport_TefTestCaseSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tefTestCaseSummary"
		   });		
		addAnnotation
		  (getTefReport_TefTestStepSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tefTestStepSummary"
		   });		
		addAnnotation
		  (getTefReport_TefTestRunWsProgramSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tefTestRunWsProgramSummary"
		   });		
		addAnnotation
		  (getTefReport_Log(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "log"
		   });		
		addAnnotation
		  (tefTestCaseEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestCase",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTefTestCase_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTefTestCase_Result(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "result"
		   });		
		addAnnotation
		  (tefTestCaseResultEEnum, 
		   source, 
		   new String[] {
			 "name", "result_._1_._type"
		   });		
		addAnnotation
		  (tefTestCaseResultObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "result_._1_._type:Object",
			 "baseType", "result_._1_._type"
		   });		
		addAnnotation
		  (tefTestCaseSummaryEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestCaseSummary",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_TestCase(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testCase"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_Count(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "count"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_Fail(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fail"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_Inconclusive(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "inconclusive"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_Pass(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "pass"
		   });		
		addAnnotation
		  (getTefTestCaseSummary_SkippedSelectively(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "skipped_selectively"
		   });		
		addAnnotation
		  (tefTestRunWsProgramEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestRunWsProgram",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTefTestRunWsProgram_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTefTestRunWsProgram_Result(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "result"
		   });		
		addAnnotation
		  (tefTestRunWsProgramResultEEnum, 
		   source, 
		   new String[] {
			 "name", "result_._2_._type"
		   });		
		addAnnotation
		  (tefTestRunWsProgramResultObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "result_._2_._type:Object",
			 "baseType", "result_._2_._type"
		   });		
		addAnnotation
		  (tefTestRunWsProgramSummaryEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestRunWsProgramSummary",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_TestCase(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testCase"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Abort(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "abort"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Count(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "count"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Fail(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fail"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Inconclusive(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "inconclusive"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Panic(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "panic"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Pass(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "pass"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Unexecuted(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unexecuted"
		   });		
		addAnnotation
		  (getTefTestRunWsProgramSummary_Unknown(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unknown"
		   });		
		addAnnotation
		  (tefTestStepEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestStep",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getTefTestStep_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTefTestStep_Result(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "result"
		   });		
		addAnnotation
		  (tefTestStepResultEEnum, 
		   source, 
		   new String[] {
			 "name", "result_._3_._type"
		   });		
		addAnnotation
		  (tefTestStepResultObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "result_._3_._type:Object",
			 "baseType", "result_._3_._type"
		   });		
		addAnnotation
		  (tefTestStepSummaryEClass, 
		   source, 
		   new String[] {
			 "name", "tefTestStepSummary",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTefTestStepSummary_TestCase(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "testCase"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Abort(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "abort"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Count(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "count"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Fail(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fail"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Inconclusive(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "inconclusive"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Panic(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "panic"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Pass(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "pass"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Unexecuted(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unexecuted"
		   });		
		addAnnotation
		  (getTefTestStepSummary_Unknown(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "unknown"
		   });
	}

} //ReportPackageImpl
