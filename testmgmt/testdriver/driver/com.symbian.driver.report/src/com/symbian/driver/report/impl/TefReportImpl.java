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

import com.symbian.driver.report.ReportPackage;
import com.symbian.driver.report.TefReport;
import com.symbian.driver.report.TefTestCaseSummary;
import com.symbian.driver.report.TefTestRunWsProgramSummary;
import com.symbian.driver.report.TefTestStepSummary;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tef Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.TefReportImpl#getTefTestCaseSummary <em>Tef Test Case Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefReportImpl#getTefTestStepSummary <em>Tef Test Step Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefReportImpl#getTefTestRunWsProgramSummary <em>Tef Test Run Ws Program Summary</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefReportImpl#getLog <em>Log</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TefReportImpl extends BaseReportImpl implements TefReport {
	/**
	 * The cached value of the '{@link #getTefTestCaseSummary() <em>Tef Test Case Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTefTestCaseSummary()
	 * @generated
	 * @ordered
	 */
	protected TefTestCaseSummary tefTestCaseSummary;

	/**
	 * The cached value of the '{@link #getTefTestStepSummary() <em>Tef Test Step Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTefTestStepSummary()
	 * @generated
	 * @ordered
	 */
	protected TefTestStepSummary tefTestStepSummary;

	/**
	 * The cached value of the '{@link #getTefTestRunWsProgramSummary() <em>Tef Test Run Ws Program Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTefTestRunWsProgramSummary()
	 * @generated
	 * @ordered
	 */
	protected TefTestRunWsProgramSummary tefTestRunWsProgramSummary;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TefReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.TEF_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestCaseSummary getTefTestCaseSummary() {
		return tefTestCaseSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTefTestCaseSummary(TefTestCaseSummary newTefTestCaseSummary, NotificationChain msgs) {
		TefTestCaseSummary oldTefTestCaseSummary = tefTestCaseSummary;
		tefTestCaseSummary = newTefTestCaseSummary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY, oldTefTestCaseSummary, newTefTestCaseSummary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTefTestCaseSummary(TefTestCaseSummary newTefTestCaseSummary) {
		if (newTefTestCaseSummary != tefTestCaseSummary) {
			NotificationChain msgs = null;
			if (tefTestCaseSummary != null)
				msgs = ((InternalEObject)tefTestCaseSummary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY, null, msgs);
			if (newTefTestCaseSummary != null)
				msgs = ((InternalEObject)newTefTestCaseSummary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY, null, msgs);
			msgs = basicSetTefTestCaseSummary(newTefTestCaseSummary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY, newTefTestCaseSummary, newTefTestCaseSummary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestStepSummary getTefTestStepSummary() {
		return tefTestStepSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTefTestStepSummary(TefTestStepSummary newTefTestStepSummary, NotificationChain msgs) {
		TefTestStepSummary oldTefTestStepSummary = tefTestStepSummary;
		tefTestStepSummary = newTefTestStepSummary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY, oldTefTestStepSummary, newTefTestStepSummary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTefTestStepSummary(TefTestStepSummary newTefTestStepSummary) {
		if (newTefTestStepSummary != tefTestStepSummary) {
			NotificationChain msgs = null;
			if (tefTestStepSummary != null)
				msgs = ((InternalEObject)tefTestStepSummary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY, null, msgs);
			if (newTefTestStepSummary != null)
				msgs = ((InternalEObject)newTefTestStepSummary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY, null, msgs);
			msgs = basicSetTefTestStepSummary(newTefTestStepSummary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY, newTefTestStepSummary, newTefTestStepSummary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefTestRunWsProgramSummary getTefTestRunWsProgramSummary() {
		return tefTestRunWsProgramSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTefTestRunWsProgramSummary(TefTestRunWsProgramSummary newTefTestRunWsProgramSummary, NotificationChain msgs) {
		TefTestRunWsProgramSummary oldTefTestRunWsProgramSummary = tefTestRunWsProgramSummary;
		tefTestRunWsProgramSummary = newTefTestRunWsProgramSummary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY, oldTefTestRunWsProgramSummary, newTefTestRunWsProgramSummary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTefTestRunWsProgramSummary(TefTestRunWsProgramSummary newTefTestRunWsProgramSummary) {
		if (newTefTestRunWsProgramSummary != tefTestRunWsProgramSummary) {
			NotificationChain msgs = null;
			if (tefTestRunWsProgramSummary != null)
				msgs = ((InternalEObject)tefTestRunWsProgramSummary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY, null, msgs);
			if (newTefTestRunWsProgramSummary != null)
				msgs = ((InternalEObject)newTefTestRunWsProgramSummary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY, null, msgs);
			msgs = basicSetTefTestRunWsProgramSummary(newTefTestRunWsProgramSummary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY, newTefTestRunWsProgramSummary, newTefTestRunWsProgramSummary));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_REPORT__LOG, oldLog, log));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY:
				return basicSetTefTestCaseSummary(null, msgs);
			case ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY:
				return basicSetTefTestStepSummary(null, msgs);
			case ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY:
				return basicSetTefTestRunWsProgramSummary(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY:
				return getTefTestCaseSummary();
			case ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY:
				return getTefTestStepSummary();
			case ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY:
				return getTefTestRunWsProgramSummary();
			case ReportPackage.TEF_REPORT__LOG:
				return getLog();
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
			case ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY:
				setTefTestCaseSummary((TefTestCaseSummary)newValue);
				return;
			case ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY:
				setTefTestStepSummary((TefTestStepSummary)newValue);
				return;
			case ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY:
				setTefTestRunWsProgramSummary((TefTestRunWsProgramSummary)newValue);
				return;
			case ReportPackage.TEF_REPORT__LOG:
				setLog((String)newValue);
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
			case ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY:
				setTefTestCaseSummary((TefTestCaseSummary)null);
				return;
			case ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY:
				setTefTestStepSummary((TefTestStepSummary)null);
				return;
			case ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY:
				setTefTestRunWsProgramSummary((TefTestRunWsProgramSummary)null);
				return;
			case ReportPackage.TEF_REPORT__LOG:
				setLog(LOG_EDEFAULT);
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
			case ReportPackage.TEF_REPORT__TEF_TEST_CASE_SUMMARY:
				return tefTestCaseSummary != null;
			case ReportPackage.TEF_REPORT__TEF_TEST_STEP_SUMMARY:
				return tefTestStepSummary != null;
			case ReportPackage.TEF_REPORT__TEF_TEST_RUN_WS_PROGRAM_SUMMARY:
				return tefTestRunWsProgramSummary != null;
			case ReportPackage.TEF_REPORT__LOG:
				return LOG_EDEFAULT == null ? log != null : !LOG_EDEFAULT.equals(log);
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
		result.append(')');
		return result.toString();
	}

} //TefReportImpl