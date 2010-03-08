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
import com.symbian.driver.Rtest;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rtest</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.RtestImpl#getResultFile <em>Result File</em>}</li>
 *   <li>{@link com.symbian.driver.impl.RtestImpl#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RtestImpl extends EObjectImpl implements Rtest {
	/**
	 * The default value of the '{@link #getResultFile() <em>Result File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultFile()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultFile() <em>Result File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultFile()
	 * @generated
	 * @ordered
	 */
	protected String resultFile = RESULT_FILE_EDEFAULT;

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
	protected RtestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.RTEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultFile() {
		return resultFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultFile(String newResultFile) {
		String oldResultFile = resultFile;
		resultFile = newResultFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.RTEST__RESULT_FILE, oldResultFile, resultFile));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.RTEST__SYMBIAN_PATH, oldSymbianPath, symbianPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.RTEST__RESULT_FILE:
				return getResultFile();
			case DriverPackage.RTEST__SYMBIAN_PATH:
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
			case DriverPackage.RTEST__RESULT_FILE:
				setResultFile((String)newValue);
				return;
			case DriverPackage.RTEST__SYMBIAN_PATH:
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
			case DriverPackage.RTEST__RESULT_FILE:
				setResultFile(RESULT_FILE_EDEFAULT);
				return;
			case DriverPackage.RTEST__SYMBIAN_PATH:
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
			case DriverPackage.RTEST__RESULT_FILE:
				return RESULT_FILE_EDEFAULT == null ? resultFile != null : !RESULT_FILE_EDEFAULT.equals(resultFile);
			case DriverPackage.RTEST__SYMBIAN_PATH:
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
		result.append(" (resultFile: ");
		result.append(resultFile);
		result.append(", symbianPath: ");
		result.append(symbianPath);
		result.append(')');
		return result.toString();
	}

} //RtestImpl