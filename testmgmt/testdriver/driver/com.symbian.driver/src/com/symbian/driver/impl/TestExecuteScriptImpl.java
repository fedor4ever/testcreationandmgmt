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


package com.symbian.driver.impl;

import com.symbian.driver.DriverPackage;
import com.symbian.driver.TestCasesList;
import com.symbian.driver.TestExecuteScript;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Execute Script</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.TestExecuteScriptImpl#getTestCasesList <em>Test Cases List</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TestExecuteScriptImpl#getPCPath <em>PC Path</em>}</li>
 *   <li>{@link com.symbian.driver.impl.TestExecuteScriptImpl#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestExecuteScriptImpl extends EObjectImpl implements TestExecuteScript {
	/**
	 * The cached value of the '{@link #getTestCasesList() <em>Test Cases List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCasesList()
	 * @generated
	 * @ordered
	 */
	protected TestCasesList testCasesList;

	/**
	 * This is true if the Test Cases List containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean testCasesListESet;

	/**
	 * The default value of the '{@link #getPCPath() <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPCPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PC_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPCPath() <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPCPath()
	 * @generated
	 * @ordered
	 */
	protected String pCPath = PC_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSymbianPath() <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbianPath()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBIAN_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbianPath() <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbianPath()
	 * @generated
	 * @ordered
	 */
	protected String symbianPath = SYMBIAN_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestExecuteScriptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.TEST_EXECUTE_SCRIPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCasesList getTestCasesList() {
		return testCasesList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTestCasesList(TestCasesList newTestCasesList, NotificationChain msgs) {
		TestCasesList oldTestCasesList = testCasesList;
		testCasesList = newTestCasesList;
		boolean oldTestCasesListESet = testCasesListESet;
		testCasesListESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, oldTestCasesList, newTestCasesList, !oldTestCasesListESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestCasesList(TestCasesList newTestCasesList) {
		if (newTestCasesList != testCasesList) {
			NotificationChain msgs = null;
			if (testCasesList != null)
				msgs = ((InternalEObject)testCasesList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, null, msgs);
			if (newTestCasesList != null)
				msgs = ((InternalEObject)newTestCasesList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, null, msgs);
			msgs = basicSetTestCasesList(newTestCasesList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTestCasesListESet = testCasesListESet;
			testCasesListESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, newTestCasesList, newTestCasesList, !oldTestCasesListESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetTestCasesList(NotificationChain msgs) {
		TestCasesList oldTestCasesList = testCasesList;
		testCasesList = null;
		boolean oldTestCasesListESet = testCasesListESet;
		testCasesListESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, oldTestCasesList, null, oldTestCasesListESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTestCasesList() {
		if (testCasesList != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)testCasesList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, null, msgs);
			msgs = basicUnsetTestCasesList(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldTestCasesListESet = testCasesListESet;
			testCasesListESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST, null, null, oldTestCasesListESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTestCasesList() {
		return testCasesListESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPCPath() {
		return pCPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPCPath(String newPCPath) {
		String oldPCPath = pCPath;
		pCPath = newPCPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TEST_EXECUTE_SCRIPT__PC_PATH, oldPCPath, pCPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSymbianPath() {
		return symbianPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSymbianPath(String newSymbianPath) {
		String oldSymbianPath = symbianPath;
		symbianPath = newSymbianPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.TEST_EXECUTE_SCRIPT__SYMBIAN_PATH, oldSymbianPath, symbianPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST:
				return basicUnsetTestCasesList(msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST:
				return getTestCasesList();
			case DriverPackage.TEST_EXECUTE_SCRIPT__PC_PATH:
				return getPCPath();
			case DriverPackage.TEST_EXECUTE_SCRIPT__SYMBIAN_PATH:
				return getSymbianPath();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST:
				setTestCasesList((TestCasesList)newValue);
				return;
			case DriverPackage.TEST_EXECUTE_SCRIPT__PC_PATH:
				setPCPath((String)newValue);
				return;
			case DriverPackage.TEST_EXECUTE_SCRIPT__SYMBIAN_PATH:
				setSymbianPath((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST:
				unsetTestCasesList();
				return;
			case DriverPackage.TEST_EXECUTE_SCRIPT__PC_PATH:
				setPCPath(PC_PATH_EDEFAULT);
				return;
			case DriverPackage.TEST_EXECUTE_SCRIPT__SYMBIAN_PATH:
				setSymbianPath(SYMBIAN_PATH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DriverPackage.TEST_EXECUTE_SCRIPT__TEST_CASES_LIST:
				return isSetTestCasesList();
			case DriverPackage.TEST_EXECUTE_SCRIPT__PC_PATH:
				return PC_PATH_EDEFAULT == null ? pCPath != null : !PC_PATH_EDEFAULT.equals(pCPath);
			case DriverPackage.TEST_EXECUTE_SCRIPT__SYMBIAN_PATH:
				return SYMBIAN_PATH_EDEFAULT == null ? symbianPath != null : !SYMBIAN_PATH_EDEFAULT.equals(symbianPath);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (pCPath: ");
		result.append(pCPath);
		result.append(", symbianPath: ");
		result.append(symbianPath);
		result.append(')');
		return result.toString();
	}

} //TestExecuteScriptImpl