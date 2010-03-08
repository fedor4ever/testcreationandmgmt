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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.report.ReportPackage
 * @generated
 */
public class ReportSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReportPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportSwitch() {
		if (modelPackage == null) {
			modelPackage = ReportPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ReportPackage.BASE_REPORT: {
				BaseReport baseReport = (BaseReport)theEObject;
				Object result = caseBaseReport(baseReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				Object result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.EXCEPTION_REPORT: {
				ExceptionReport exceptionReport = (ExceptionReport)theEObject;
				Object result = caseExceptionReport(exceptionReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.GENERIC_REPORT: {
				GenericReport genericReport = (GenericReport)theEObject;
				Object result = caseGenericReport(genericReport);
				if (result == null) result = caseBaseReport(genericReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.INFO: {
				Map.Entry info = (Map.Entry)theEObject;
				Object result = caseInfo(info);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.REPORT: {
				Report report = (Report)theEObject;
				Object result = caseReport(report);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.REPORT_INFO: {
				ReportInfo reportInfo = (ReportInfo)theEObject;
				Object result = caseReportInfo(reportInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_REPORT: {
				TefReport tefReport = (TefReport)theEObject;
				Object result = caseTefReport(tefReport);
				if (result == null) result = caseBaseReport(tefReport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_CASE: {
				TefTestCase tefTestCase = (TefTestCase)theEObject;
				Object result = caseTefTestCase(tefTestCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_CASE_SUMMARY: {
				TefTestCaseSummary tefTestCaseSummary = (TefTestCaseSummary)theEObject;
				Object result = caseTefTestCaseSummary(tefTestCaseSummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM: {
				TefTestRunWsProgram tefTestRunWsProgram = (TefTestRunWsProgram)theEObject;
				Object result = caseTefTestRunWsProgram(tefTestRunWsProgram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY: {
				TefTestRunWsProgramSummary tefTestRunWsProgramSummary = (TefTestRunWsProgramSummary)theEObject;
				Object result = caseTefTestRunWsProgramSummary(tefTestRunWsProgramSummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_STEP: {
				TefTestStep tefTestStep = (TefTestStep)theEObject;
				Object result = caseTefTestStep(tefTestStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReportPackage.TEF_TEST_STEP_SUMMARY: {
				TefTestStepSummary tefTestStepSummary = (TefTestStepSummary)theEObject;
				Object result = caseTefTestStepSummary(tefTestStepSummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseReport(BaseReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exception Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exception Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExceptionReport(ExceptionReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseGenericReport(GenericReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInfo(Map.Entry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseReport(Report object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseReportInfo(ReportInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Report</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Report</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefReport(TefReport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestCase(TefTestCase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Case Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Case Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestCaseSummary(TefTestCaseSummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Run Ws Program</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Run Ws Program</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestRunWsProgram(TefTestRunWsProgram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Run Ws Program Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Run Ws Program Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestRunWsProgramSummary(TefTestRunWsProgramSummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestStep(TefTestStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Test Step Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Test Step Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTefTestStepSummary(TefTestStepSummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //ReportSwitch
