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
import com.symbian.driver.report.ExceptionReport;
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
 * An implementation of the model object '<em><b>Base Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#getExecptionReport <em>Execption Report</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#getTask <em>Task</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#isTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#isCrash <em>Crash</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.BaseReportImpl#getCoredump <em>Coredump</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaseReportImpl extends EObjectImpl implements BaseReport {
	/**
	 * The cached value of the '{@link #getExecptionReport() <em>Execption Report</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecptionReport()
	 * @generated
	 * @ordered
	 */
	protected EList execptionReport;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final String DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected String duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTask() <em>Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTask()
	 * @generated
	 * @ordered
	 */
	protected static final String TASK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTask() <em>Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTask()
	 * @generated
	 * @ordered
	 */
	protected String task = TASK_EDEFAULT;

	/**
	 * The default value of the '{@link #isTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TIMEOUT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeout()
	 * @generated
	 * @ordered
	 */
	protected boolean timeout = TIMEOUT_EDEFAULT;

	/**
	 * This is true if the Timeout attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean timeoutESet;

	/**
	 * The default value of the '{@link #isCrash() <em>Crash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCrash()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CRASH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCrash() <em>Crash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCrash()
	 * @generated
	 * @ordered
	 */
	protected boolean crash = CRASH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoredump() <em>Coredump</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoredump()
	 * @generated
	 * @ordered
	 */
	protected static final String COREDUMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCoredump() <em>Coredump</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoredump()
	 * @generated
	 * @ordered
	 */
	protected String coredump = COREDUMP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaseReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.BASE_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExecptionReport() {
		if (execptionReport == null) {
			execptionReport = new EObjectContainmentEList(ExceptionReport.class, this, ReportPackage.BASE_REPORT__EXECPTION_REPORT);
		}
		return execptionReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(String newDuration) {
		String oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTask() {
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTask(String newTask) {
		String oldTask = task;
		task = newTask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__TASK, oldTask, task));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(boolean newTimeout) {
		boolean oldTimeout = timeout;
		timeout = newTimeout;
		boolean oldTimeoutESet = timeoutESet;
		timeoutESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__TIMEOUT, oldTimeout, timeout, !oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTimeout() {
		boolean oldTimeout = timeout;
		boolean oldTimeoutESet = timeoutESet;
		timeout = TIMEOUT_EDEFAULT;
		timeoutESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.BASE_REPORT__TIMEOUT, oldTimeout, TIMEOUT_EDEFAULT, oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTimeout() {
		return timeoutESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCrash() {
		return crash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCrash(boolean newCrash) {
		boolean oldCrash = crash;
		crash = newCrash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__CRASH, oldCrash, crash));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCoredump() {
		return coredump;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoredump(String newCoredump) {
		String oldCoredump = coredump;
		coredump = newCoredump;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.BASE_REPORT__COREDUMP, oldCoredump, coredump));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
				return ((InternalEList)getExecptionReport()).basicRemove(otherEnd, msgs);
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
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
				return getExecptionReport();
			case ReportPackage.BASE_REPORT__DURATION:
				return getDuration();
			case ReportPackage.BASE_REPORT__NAME:
				return getName();
			case ReportPackage.BASE_REPORT__TASK:
				return getTask();
			case ReportPackage.BASE_REPORT__TIMEOUT:
				return isTimeout() ? Boolean.TRUE : Boolean.FALSE;
			case ReportPackage.BASE_REPORT__CRASH:
				return isCrash() ? Boolean.TRUE : Boolean.FALSE;
			case ReportPackage.BASE_REPORT__COREDUMP:
				return getCoredump();
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
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
				getExecptionReport().clear();
				getExecptionReport().addAll((Collection)newValue);
				return;
			case ReportPackage.BASE_REPORT__DURATION:
				setDuration((String)newValue);
				return;
			case ReportPackage.BASE_REPORT__NAME:
				setName((String)newValue);
				return;
			case ReportPackage.BASE_REPORT__TASK:
				setTask((String)newValue);
				return;
			case ReportPackage.BASE_REPORT__TIMEOUT:
				setTimeout(((Boolean)newValue).booleanValue());
				return;
			case ReportPackage.BASE_REPORT__CRASH:
				setCrash(((Boolean)newValue).booleanValue());
				return;
			case ReportPackage.BASE_REPORT__COREDUMP:
				setCoredump((String)newValue);
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
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
				getExecptionReport().clear();
				return;
			case ReportPackage.BASE_REPORT__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case ReportPackage.BASE_REPORT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ReportPackage.BASE_REPORT__TASK:
				setTask(TASK_EDEFAULT);
				return;
			case ReportPackage.BASE_REPORT__TIMEOUT:
				unsetTimeout();
				return;
			case ReportPackage.BASE_REPORT__CRASH:
				setCrash(CRASH_EDEFAULT);
				return;
			case ReportPackage.BASE_REPORT__COREDUMP:
				setCoredump(COREDUMP_EDEFAULT);
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
			case ReportPackage.BASE_REPORT__EXECPTION_REPORT:
				return execptionReport != null && !execptionReport.isEmpty();
			case ReportPackage.BASE_REPORT__DURATION:
				return DURATION_EDEFAULT == null ? duration != null : !DURATION_EDEFAULT.equals(duration);
			case ReportPackage.BASE_REPORT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ReportPackage.BASE_REPORT__TASK:
				return TASK_EDEFAULT == null ? task != null : !TASK_EDEFAULT.equals(task);
			case ReportPackage.BASE_REPORT__TIMEOUT:
				return isSetTimeout();
			case ReportPackage.BASE_REPORT__CRASH:
				return crash != CRASH_EDEFAULT;
			case ReportPackage.BASE_REPORT__COREDUMP:
				return COREDUMP_EDEFAULT == null ? coredump != null : !COREDUMP_EDEFAULT.equals(coredump);
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
		result.append(" (duration: ");
		result.append(duration);
		result.append(", name: ");
		result.append(name);
		result.append(", task: ");
		result.append(task);
		result.append(", timeout: ");
		if (timeoutESet) result.append(timeout); else result.append("<unset>");
		result.append(", crash: ");
		result.append(crash);
		result.append(", coredump: ");
		result.append(coredump);
		result.append(')');
		return result.toString();
	}

} //BaseReportImpl