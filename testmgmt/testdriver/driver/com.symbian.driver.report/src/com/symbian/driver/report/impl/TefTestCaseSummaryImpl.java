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
import com.symbian.driver.report.TefTestCase;
import com.symbian.driver.report.TefTestCaseSummary;

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
 * An implementation of the model object '<em><b>Tef Test Case Summary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getTestCase <em>Test Case</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getCount <em>Count</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getFail <em>Fail</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getInconclusive <em>Inconclusive</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getPass <em>Pass</em>}</li>
 *   <li>{@link com.symbian.driver.report.impl.TefTestCaseSummaryImpl#getSkippedSelectively <em>Skipped Selectively</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TefTestCaseSummaryImpl extends EObjectImpl implements TefTestCaseSummary {
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
	 * The default value of the '{@link #getSkippedSelectively() <em>Skipped Selectively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkippedSelectively()
	 * @generated
	 * @ordered
	 */
	protected static final int SKIPPED_SELECTIVELY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSkippedSelectively() <em>Skipped Selectively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkippedSelectively()
	 * @generated
	 * @ordered
	 */
	protected int skippedSelectively = SKIPPED_SELECTIVELY_EDEFAULT;

	/**
	 * This is true if the Skipped Selectively attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean skippedSelectivelyESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TefTestCaseSummaryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ReportPackage.Literals.TEF_TEST_CASE_SUMMARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTestCase() {
		if (testCase == null) {
			testCase = new EObjectContainmentEList(TefTestCase.class, this, ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE);
		}
		return testCase;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT, oldCount, count, !oldCountESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT, oldCount, COUNT_EDEFAULT, oldCountESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL, oldFail, fail, !oldFailESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL, oldFail, FAIL_EDEFAULT, oldFailESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE, oldInconclusive, inconclusive, !oldInconclusiveESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE, oldInconclusive, INCONCLUSIVE_EDEFAULT, oldInconclusiveESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_CASE_SUMMARY__PASS, oldPass, pass, !oldPassESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_CASE_SUMMARY__PASS, oldPass, PASS_EDEFAULT, oldPassESet));
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
	public int getSkippedSelectively() {
		return skippedSelectively;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkippedSelectively(int newSkippedSelectively) {
		int oldSkippedSelectively = skippedSelectively;
		skippedSelectively = newSkippedSelectively;
		boolean oldSkippedSelectivelyESet = skippedSelectivelyESet;
		skippedSelectivelyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY, oldSkippedSelectively, skippedSelectively, !oldSkippedSelectivelyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSkippedSelectively() {
		int oldSkippedSelectively = skippedSelectively;
		boolean oldSkippedSelectivelyESet = skippedSelectivelyESet;
		skippedSelectively = SKIPPED_SELECTIVELY_EDEFAULT;
		skippedSelectivelyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY, oldSkippedSelectively, SKIPPED_SELECTIVELY_EDEFAULT, oldSkippedSelectivelyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSkippedSelectively() {
		return skippedSelectivelyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE:
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
			case ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE:
				return getTestCase();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT:
				return new Integer(getCount());
			case ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL:
				return new Integer(getFail());
			case ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE:
				return new Integer(getInconclusive());
			case ReportPackage.TEF_TEST_CASE_SUMMARY__PASS:
				return new Integer(getPass());
			case ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY:
				return new Integer(getSkippedSelectively());
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
			case ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE:
				getTestCase().clear();
				getTestCase().addAll((Collection)newValue);
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT:
				setCount(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL:
				setFail(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE:
				setInconclusive(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__PASS:
				setPass(((Integer)newValue).intValue());
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY:
				setSkippedSelectively(((Integer)newValue).intValue());
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
			case ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE:
				getTestCase().clear();
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT:
				unsetCount();
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL:
				unsetFail();
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE:
				unsetInconclusive();
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__PASS:
				unsetPass();
				return;
			case ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY:
				unsetSkippedSelectively();
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
			case ReportPackage.TEF_TEST_CASE_SUMMARY__TEST_CASE:
				return testCase != null && !testCase.isEmpty();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__COUNT:
				return isSetCount();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__FAIL:
				return isSetFail();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__INCONCLUSIVE:
				return isSetInconclusive();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__PASS:
				return isSetPass();
			case ReportPackage.TEF_TEST_CASE_SUMMARY__SKIPPED_SELECTIVELY:
				return isSetSkippedSelectively();
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
		result.append(" (count: ");
		if (countESet) result.append(count); else result.append("<unset>");
		result.append(", fail: ");
		if (failESet) result.append(fail); else result.append("<unset>");
		result.append(", inconclusive: ");
		if (inconclusiveESet) result.append(inconclusive); else result.append("<unset>");
		result.append(", pass: ");
		if (passESet) result.append(pass); else result.append("<unset>");
		result.append(", skippedSelectively: ");
		if (skippedSelectivelyESet) result.append(skippedSelectively); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TefTestCaseSummaryImpl