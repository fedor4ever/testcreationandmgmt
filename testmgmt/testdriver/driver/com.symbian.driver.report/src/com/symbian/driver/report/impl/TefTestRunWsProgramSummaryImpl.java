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
import com.symbian.driver.report.TefTestRunWsProgram;
import com.symbian.driver.report.TefTestRunWsProgramSummary;

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
 * An implementation of the model object '<em><b>Tef Test Run Ws Program Summary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getTestCase <em>Test Case</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getAbort <em>Abort</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getCount <em>Count</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getFail <em>Fail</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getInconclusive <em>Inconclusive</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getPanic <em>Panic</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getPass <em>Pass</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getUnexecuted <em>Unexecuted</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestRunWsProgramSummaryImpl#getUnknown <em>Unknown</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TefTestRunWsProgramSummaryImpl extends EObjectImpl implements TefTestRunWsProgramSummary {
	/**
	 * The cached value of the '{@link #getTestCase() <em>Test Case</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCase()
	 * @generated
	 * @ordered
	 */
	protected EList testCase;

	/**
	 * The default value of the '{@link #getAbort() <em>Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbort()
	 * @generated
	 * @ordered
	 */
	protected static final int ABORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAbort() <em>Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbort()
	 * @generated
	 * @ordered
	 */
	protected int abort = ABORT_EDEFAULT;

	/**
	 * This is true if the Abort attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean abortESet;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final int COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected int count = COUNT_EDEFAULT;

	/**
	 * This is true if the Count attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean countESet;

	/**
	 * The default value of the '{@link #getFail() <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFail()
	 * @generated
	 * @ordered
	 */
	protected static final int FAIL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFail() <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFail()
	 * @generated
	 * @ordered
	 */
	protected int fail = FAIL_EDEFAULT;

	/**
	 * This is true if the Fail attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean failESet;

	/**
	 * The default value of the '{@link #getInconclusive() <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInconclusive()
	 * @generated
	 * @ordered
	 */
	protected static final int INCONCLUSIVE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getInconclusive() <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInconclusive()
	 * @generated
	 * @ordered
	 */
	protected int inconclusive = INCONCLUSIVE_EDEFAULT;

	/**
	 * This is true if the Inconclusive attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean inconclusiveESet;

	/**
	 * The default value of the '{@link #getPanic() <em>Panic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanic()
	 * @generated
	 * @ordered
	 */
	protected static final int PANIC_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPanic() <em>Panic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanic()
	 * @generated
	 * @ordered
	 */
	protected int panic = PANIC_EDEFAULT;

	/**
	 * This is true if the Panic attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean panicESet;

	/**
	 * The default value of the '{@link #getPass() <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPass()
	 * @generated
	 * @ordered
	 */
	protected static final int PASS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPass() <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPass()
	 * @generated
	 * @ordered
	 */
	protected int pass = PASS_EDEFAULT;

	/**
	 * This is true if the Pass attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean passESet;

	/**
	 * The default value of the '{@link #getUnexecuted() <em>Unexecuted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnexecuted()
	 * @generated
	 * @ordered
	 */
	protected static final int UNEXECUTED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUnexecuted() <em>Unexecuted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnexecuted()
	 * @generated
	 * @ordered
	 */
	protected int unexecuted = UNEXECUTED_EDEFAULT;

	/**
	 * This is true if the Unexecuted attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean unexecutedESet;

	/**
	 * The default value of the '{@link #getUnknown() <em>Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnknown()
	 * @generated
	 * @ordered
	 */
	protected static final int UNKNOWN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUnknown() <em>Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnknown()
	 * @generated
	 * @ordered
	 */
	protected int unknown = UNKNOWN_EDEFAULT;

	/**
	 * This is true if the Unknown attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean unknownESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TefTestRunWsProgramSummaryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.TEF_TEST_RUN_WS_PROGRAM_SUMMARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTestCase() {
		if (testCase == null) {
			testCase = new EObjectContainmentEList(TefTestRunWsProgram.class, this, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE);
		}
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAbort() {
		return abort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbort(int newAbort) {
		int oldAbort = abort;
		abort = newAbort;
		boolean oldAbortESet = abortESet;
		abortESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT, oldAbort, abort, !oldAbortESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAbort() {
		int oldAbort = abort;
		boolean oldAbortESet = abortESet;
		abort = ABORT_EDEFAULT;
		abortESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT, oldAbort, ABORT_EDEFAULT, oldAbortESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAbort() {
		return abortESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(int newCount) {
		int oldCount = count;
		count = newCount;
		boolean oldCountESet = countESet;
		countESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT, oldCount, count, !oldCountESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCount() {
		int oldCount = count;
		boolean oldCountESet = countESet;
		count = COUNT_EDEFAULT;
		countESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT, oldCount, COUNT_EDEFAULT, oldCountESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCount() {
		return countESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFail() {
		return fail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFail(int newFail) {
		int oldFail = fail;
		fail = newFail;
		boolean oldFailESet = failESet;
		failESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL, oldFail, fail, !oldFailESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFail() {
		int oldFail = fail;
		boolean oldFailESet = failESet;
		fail = FAIL_EDEFAULT;
		failESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL, oldFail, FAIL_EDEFAULT, oldFailESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFail() {
		return failESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInconclusive() {
		return inconclusive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInconclusive(int newInconclusive) {
		int oldInconclusive = inconclusive;
		inconclusive = newInconclusive;
		boolean oldInconclusiveESet = inconclusiveESet;
		inconclusiveESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE, oldInconclusive, inconclusive, !oldInconclusiveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInconclusive() {
		int oldInconclusive = inconclusive;
		boolean oldInconclusiveESet = inconclusiveESet;
		inconclusive = INCONCLUSIVE_EDEFAULT;
		inconclusiveESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE, oldInconclusive, INCONCLUSIVE_EDEFAULT, oldInconclusiveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInconclusive() {
		return inconclusiveESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPanic() {
		return panic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanic(int newPanic) {
		int oldPanic = panic;
		panic = newPanic;
		boolean oldPanicESet = panicESet;
		panicESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC, oldPanic, panic, !oldPanicESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPanic() {
		int oldPanic = panic;
		boolean oldPanicESet = panicESet;
		panic = PANIC_EDEFAULT;
		panicESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC, oldPanic, PANIC_EDEFAULT, oldPanicESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPanic() {
		return panicESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPass() {
		return pass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPass(int newPass) {
		int oldPass = pass;
		pass = newPass;
		boolean oldPassESet = passESet;
		passESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS, oldPass, pass, !oldPassESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPass() {
		int oldPass = pass;
		boolean oldPassESet = passESet;
		pass = PASS_EDEFAULT;
		passESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS, oldPass, PASS_EDEFAULT, oldPassESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPass() {
		return passESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUnexecuted() {
		return unexecuted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnexecuted(int newUnexecuted) {
		int oldUnexecuted = unexecuted;
		unexecuted = newUnexecuted;
		boolean oldUnexecutedESet = unexecutedESet;
		unexecutedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED, oldUnexecuted, unexecuted, !oldUnexecutedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnexecuted() {
		int oldUnexecuted = unexecuted;
		boolean oldUnexecutedESet = unexecutedESet;
		unexecuted = UNEXECUTED_EDEFAULT;
		unexecutedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED, oldUnexecuted, UNEXECUTED_EDEFAULT, oldUnexecutedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnexecuted() {
		return unexecutedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getUnknown() {
		return unknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnknown(int newUnknown) {
		int oldUnknown = unknown;
		unknown = newUnknown;
		boolean oldUnknownESet = unknownESet;
		unknownESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN, oldUnknown, unknown, !oldUnknownESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnknown() {
		int oldUnknown = unknown;
		boolean oldUnknownESet = unknownESet;
		unknown = UNKNOWN_EDEFAULT;
		unknownESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN, oldUnknown, UNKNOWN_EDEFAULT, oldUnknownESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnknown() {
		return unknownESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				return ((InternalEList)getTestCase()).basicRemove(otherEnd, msgs);
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
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				return getTestCase();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT:
				return new Integer(getAbort());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT:
				return new Integer(getCount());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL:
				return new Integer(getFail());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE:
				return new Integer(getInconclusive());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC:
				return new Integer(getPanic());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS:
				return new Integer(getPass());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED:
				return new Integer(getUnexecuted());
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN:
				return new Integer(getUnknown());
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
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				getTestCase().clear();
				getTestCase().addAll((Collection)newValue);
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT:
				setAbort(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT:
				setCount(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL:
				setFail(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE:
				setInconclusive(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC:
				setPanic(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS:
				setPass(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED:
				setUnexecuted(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN:
				setUnknown(((Integer)newValue).intValue());
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
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				getTestCase().clear();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT:
				unsetAbort();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT:
				unsetCount();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL:
				unsetFail();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE:
				unsetInconclusive();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC:
				unsetPanic();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS:
				unsetPass();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED:
				unsetUnexecuted();
				return;
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN:
				unsetUnknown();
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
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__TEST_CASE:
				return testCase != null && !testCase.isEmpty();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__ABORT:
				return isSetAbort();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__COUNT:
				return isSetCount();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__FAIL:
				return isSetFail();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__INCONCLUSIVE:
				return isSetInconclusive();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PANIC:
				return isSetPanic();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__PASS:
				return isSetPass();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNEXECUTED:
				return isSetUnexecuted();
			case ReportPackage.TEF_TEST_RUN_WS_PROGRAM_SUMMARY__UNKNOWN:
				return isSetUnknown();
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
		result.append(" (abort: ");
		if (abortESet) result.append(abort); else result.append("<unset>");
		result.append(", count: ");
		if (countESet) result.append(count); else result.append("<unset>");
		result.append(", fail: ");
		if (failESet) result.append(fail); else result.append("<unset>");
		result.append(", inconclusive: ");
		if (inconclusiveESet) result.append(inconclusive); else result.append("<unset>");
		result.append(", panic: ");
		if (panicESet) result.append(panic); else result.append("<unset>");
		result.append(", pass: ");
		if (passESet) result.append(pass); else result.append("<unset>");
		result.append(", unexecuted: ");
		if (unexecutedESet) result.append(unexecuted); else result.append("<unset>");
		result.append(", unknown: ");
		if (unknownESet) result.append(unknown); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TefTestRunWsProgramSummaryImpl