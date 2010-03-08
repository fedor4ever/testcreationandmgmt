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


package com.symbian.driver.report.util;

import com.symbian.driver.report.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage
 * @generated
 */
public class ReportAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReportPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ReportPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReportSwitch modelSwitch =
		new ReportSwitch() {
			public Object caseBaseReport(BaseReport object) {
				return createBaseReportAdapter();
			}
			public Object caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			public Object caseExceptionReport(ExceptionReport object) {
				return createExceptionReportAdapter();
			}
			public Object caseGenericReport(GenericReport object) {
				return createGenericReportAdapter();
			}
			public Object caseInfo(Map.Entry object) {
				return createInfoAdapter();
			}
			public Object caseReport(Report object) {
				return createReportAdapter();
			}
			public Object caseReportInfo(ReportInfo object) {
				return createReportInfoAdapter();
			}
			public Object caseTefReport(TefReport object) {
				return createTefReportAdapter();
			}
			public Object caseTefTestCase(TefTestCase object) {
				return createTefTestCaseAdapter();
			}
			public Object caseTefTestCaseSummary(TefTestCaseSummary object) {
				return createTefTestCaseSummaryAdapter();
			}
			public Object caseTefTestRunWsProgram(TefTestRunWsProgram object) {
				return createTefTestRunWsProgramAdapter();
			}
			public Object caseTefTestRunWsProgramSummary(TefTestRunWsProgramSummary object) {
				return createTefTestRunWsProgramSummaryAdapter();
			}
			public Object caseTefTestStep(TefTestStep object) {
				return createTefTestStepAdapter();
			}
			public Object caseTefTestStepSummary(TefTestStepSummary object) {
				return createTefTestStepSummaryAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.BaseReport <em>Base Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.BaseReport
	 * @generated
	 */
	public Adapter createBaseReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.ExceptionReport <em>Exception Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.ExceptionReport
	 * @generated
	 */
	public Adapter createExceptionReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.GenericReport <em>Generic Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.GenericReport
	 * @generated
	 */
	public Adapter createGenericReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.Report
	 * @generated
	 */
	public Adapter createReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.ReportInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.ReportInfo
	 * @generated
	 */
	public Adapter createReportInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefReport <em>Tef Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefReport
	 * @generated
	 */
	public Adapter createTefReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestCase <em>Tef Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestCase
	 * @generated
	 */
	public Adapter createTefTestCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestCaseSummary <em>Tef Test Case Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestCaseSummary
	 * @generated
	 */
	public Adapter createTefTestCaseSummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestRunWsProgram <em>Tef Test Run Ws Program</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestRunWsProgram
	 * @generated
	 */
	public Adapter createTefTestRunWsProgramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestRunWsProgramSummary
	 * @generated
	 */
	public Adapter createTefTestRunWsProgramSummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestStep <em>Tef Test Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestStep
	 * @generated
	 */
	public Adapter createTefTestStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.report.TefTestStepSummary <em>Tef Test Step Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.report.TefTestStepSummary
	 * @generated
	 */
	public Adapter createTefTestStepSummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ReportAdapterFactory
