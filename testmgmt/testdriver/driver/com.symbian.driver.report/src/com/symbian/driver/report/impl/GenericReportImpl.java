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

import com.symbian.driver.report.GenericReport;
import com.symbian.driver.report.GenericResult;
import com.symbian.driver.report.ReportPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.GenericReportImpl#getLog <em>Log</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.GenericReportImpl#getTrace <em>Trace</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.GenericReportImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenericReportImpl extends BaseReportImpl implements GenericReport {
	/**
	 * The default value of the '{@link #getLog() <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLog()
	 * @generated
	 * @ordered
	 */
	protected static final String LOG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLog() <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLog()
	 * @generated
	 * @ordered
	 */
	protected String log = LOG_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrace() <em>Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrace()
	 * @generated
	 * @ordered
	 */
	protected static final String TRACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTrace() <em>Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrace()
	 * @generated
	 * @ordered
	 */
	protected String trace = TRACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected static final GenericResult RESULT_EDEFAULT = GenericResult.ERROR_LITERAL;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected GenericResult result = RESULT_EDEFAULT;

	/**
	 * This is true if the Result attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resultESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.GENERIC_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLog() {
		return log;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLog(String newLog) {
		String oldLog = log;
		log = newLog;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.GENERIC_REPORT__LOG, oldLog, log));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrace(String newTrace) {
		String oldTrace = trace;
		trace = newTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.GENERIC_REPORT__TRACE, oldTrace, trace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericResult getResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResult(GenericResult newResult) {
		GenericResult oldResult = result;
		result = newResult == null ? RESULT_EDEFAULT : newResult;
		boolean oldResultESet = resultESet;
		resultESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.GENERIC_REPORT__RESULT, oldResult, result, !oldResultESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResult() {
		GenericResult oldResult = result;
		boolean oldResultESet = resultESet;
		result = RESULT_EDEFAULT;
		resultESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.GENERIC_REPORT__RESULT, oldResult, RESULT_EDEFAULT, oldResultESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResult() {
		return resultESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReportPackage.GENERIC_REPORT__LOG:
				return getLog();
			case ReportPackage.GENERIC_REPORT__TRACE:
				return getTrace();
			case ReportPackage.GENERIC_REPORT__RESULT:
				return getResult();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReportPackage.GENERIC_REPORT__LOG:
				setLog((String)newValue);
				return;
			case ReportPackage.GENERIC_REPORT__TRACE:
				setTrace((String)newValue);
				return;
			case ReportPackage.GENERIC_REPORT__RESULT:
				setResult((GenericResult)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case ReportPackage.GENERIC_REPORT__LOG:
				setLog(LOG_EDEFAULT);
				return;
			case ReportPackage.GENERIC_REPORT__TRACE:
				setTrace(TRACE_EDEFAULT);
				return;
			case ReportPackage.GENERIC_REPORT__RESULT:
				unsetResult();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ReportPackage.GENERIC_REPORT__LOG:
				return LOG_EDEFAULT == null ? log != null : !LOG_EDEFAULT.equals(log);
			case ReportPackage.GENERIC_REPORT__TRACE:
				return TRACE_EDEFAULT == null ? trace != null : !TRACE_EDEFAULT.equals(trace);
			case ReportPackage.GENERIC_REPORT__RESULT:
				return isSetResult();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (log: ");
		result.append(log);
		result.append(", trace: ");
		result.append(trace);
		result.append(", result: ");
		if (resultESet) result.append(result); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //GenericReportImpl