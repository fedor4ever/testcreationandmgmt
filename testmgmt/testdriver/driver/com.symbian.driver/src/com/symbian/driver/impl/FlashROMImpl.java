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
import com.symbian.driver.FlashROM;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flash ROM</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.FlashROMImpl#getPCPath <em>PC Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlashROMImpl extends EObjectImpl implements FlashROM {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlashROMImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.FLASH_ROM;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.FLASH_ROM__PC_PATH, oldPCPath, pCPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.FLASH_ROM__PC_PATH:
				return getPCPath();
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
			case DriverPackage.FLASH_ROM__PC_PATH:
				setPCPath((String)newValue);
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
			case DriverPackage.FLASH_ROM__PC_PATH:
				setPCPath(PC_PATH_EDEFAULT);
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
			case DriverPackage.FLASH_ROM__PC_PATH:
				return PC_PATH_EDEFAULT == null ? pCPath != null : !PC_PATH_EDEFAULT.equals(pCPath);
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
		result.append(')');
		return result.toString();
	}

} //FlashROMImpl
