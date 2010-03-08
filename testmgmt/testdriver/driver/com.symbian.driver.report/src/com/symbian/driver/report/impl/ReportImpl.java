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
import com.symbian.driver.report.Report;
import com.symbian.driver.report.ReportInfo;
import com.symbian.driver.report.ReportPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.ReportImpl#getReportInfo <em>Report Info</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.ReportImpl#getAReport <em>AReport</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReportImpl extends EObjectImpl implements Report {
	/**
	 * The cached value of the '{@link #getReportInfo() <em>Report Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReportInfo()
	 * @generated
	 * @ordered
	 */
	protected ReportInfo reportInfo;

	/**
	 * The cached value of the '{@link #getAReport() <em>AReport</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAReport()
	 * @generated
	 * @ordered
	 */
	protected EList aReport;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReportInfo(ReportInfo newReportInfo, NotificationChain msgs) {
		ReportInfo oldReportInfo = reportInfo;
		reportInfo = newReportInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReportPackage.REPORT__REPORT_INFO, oldReportInfo, newReportInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReportInfo(ReportInfo newReportInfo) {
		if (newReportInfo != reportInfo) {
			NotificationChain msgs = null;
			if (reportInfo != null)
				msgs = ((InternalEObject)reportInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReportPackage.REPORT__REPORT_INFO, null, msgs);
			if (newReportInfo != null)
				msgs = ((InternalEObject)newReportInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReportPackage.REPORT__REPORT_INFO, null, msgs);
			msgs = basicSetReportInfo(newReportInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.REPORT__REPORT_INFO, newReportInfo, newReportInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAReport() {
		if (aReport == null) {
			aReport = new EObjectContainmentEList(BaseReport.class, this, ReportPackage.REPORT__AREPORT);
		}
		return aReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.REPORT__REPORT_INFO:
				return basicSetReportInfo(null, msgs);
			case ReportPackage.REPORT__AREPORT:
				return ((InternalEList)getAReport()).basicRemove(otherEnd, msgs);
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
			case ReportPackage.REPORT__REPORT_INFO:
				return getReportInfo();
			case ReportPackage.REPORT__AREPORT:
				return getAReport();
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
			case ReportPackage.REPORT__REPORT_INFO:
				setReportInfo((ReportInfo)newValue);
				return;
			case ReportPackage.REPORT__AREPORT:
				getAReport().clear();
				getAReport().addAll((Collection)newValue);
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
			case ReportPackage.REPORT__REPORT_INFO:
				setReportInfo((ReportInfo)null);
				return;
			case ReportPackage.REPORT__AREPORT:
				getAReport().clear();
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
			case ReportPackage.REPORT__REPORT_INFO:
				return reportInfo != null;
			case ReportPackage.REPORT__AREPORT:
				return aReport != null && !aReport.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ReportImpl