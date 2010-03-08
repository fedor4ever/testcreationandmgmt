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
import com.symbian.driver.StartTrace;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.StartTraceImpl#getEnablePrimaryFilters <em>Enable Primary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.impl.StartTraceImpl#getEnableSecondaryFilters <em>Enable Secondary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.impl.StartTraceImpl#getDisablePrimaryFilters <em>Disable Primary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.impl.StartTraceImpl#getDisableSecondaryFilters <em>Disable Secondary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.impl.StartTraceImpl#getConfigFilePath <em>Config File Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartTraceImpl extends EObjectImpl implements StartTrace {
	/**
	 * The default value of the '{@link #getEnablePrimaryFilters() <em>Enable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnablePrimaryFilters()
	 * @generated
	 * @ordered
	 */
	protected static final String ENABLE_PRIMARY_FILTERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnablePrimaryFilters() <em>Enable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnablePrimaryFilters()
	 * @generated
	 * @ordered
	 */
	protected String enablePrimaryFilters = ENABLE_PRIMARY_FILTERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnableSecondaryFilters() <em>Enable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableSecondaryFilters()
	 * @generated
	 * @ordered
	 */
	protected static final String ENABLE_SECONDARY_FILTERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnableSecondaryFilters() <em>Enable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableSecondaryFilters()
	 * @generated
	 * @ordered
	 */
	protected String enableSecondaryFilters = ENABLE_SECONDARY_FILTERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisablePrimaryFilters() <em>Disable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisablePrimaryFilters()
	 * @generated
	 * @ordered
	 */
	protected static final String DISABLE_PRIMARY_FILTERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisablePrimaryFilters() <em>Disable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisablePrimaryFilters()
	 * @generated
	 * @ordered
	 */
	protected String disablePrimaryFilters = DISABLE_PRIMARY_FILTERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisableSecondaryFilters() <em>Disable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisableSecondaryFilters()
	 * @generated
	 * @ordered
	 */
	protected static final String DISABLE_SECONDARY_FILTERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisableSecondaryFilters() <em>Disable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisableSecondaryFilters()
	 * @generated
	 * @ordered
	 */
	protected String disableSecondaryFilters = DISABLE_SECONDARY_FILTERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfigFilePath() <em>Config File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigFilePath()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIG_FILE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfigFilePath() <em>Config File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigFilePath()
	 * @generated
	 * @ordered
	 */
	protected String configFilePath = CONFIG_FILE_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StartTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.START_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEnablePrimaryFilters() {
		return enablePrimaryFilters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnablePrimaryFilters(String newEnablePrimaryFilters) {
		String oldEnablePrimaryFilters = enablePrimaryFilters;
		enablePrimaryFilters = newEnablePrimaryFilters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.START_TRACE__ENABLE_PRIMARY_FILTERS, oldEnablePrimaryFilters, enablePrimaryFilters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEnableSecondaryFilters() {
		return enableSecondaryFilters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableSecondaryFilters(String newEnableSecondaryFilters) {
		String oldEnableSecondaryFilters = enableSecondaryFilters;
		enableSecondaryFilters = newEnableSecondaryFilters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.START_TRACE__ENABLE_SECONDARY_FILTERS, oldEnableSecondaryFilters, enableSecondaryFilters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisablePrimaryFilters() {
		return disablePrimaryFilters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisablePrimaryFilters(String newDisablePrimaryFilters) {
		String oldDisablePrimaryFilters = disablePrimaryFilters;
		disablePrimaryFilters = newDisablePrimaryFilters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.START_TRACE__DISABLE_PRIMARY_FILTERS, oldDisablePrimaryFilters, disablePrimaryFilters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisableSecondaryFilters() {
		return disableSecondaryFilters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisableSecondaryFilters(String newDisableSecondaryFilters) {
		String oldDisableSecondaryFilters = disableSecondaryFilters;
		disableSecondaryFilters = newDisableSecondaryFilters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.START_TRACE__DISABLE_SECONDARY_FILTERS, oldDisableSecondaryFilters, disableSecondaryFilters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConfigFilePath() {
		return configFilePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigFilePath(String newConfigFilePath) {
		String oldConfigFilePath = configFilePath;
		configFilePath = newConfigFilePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.START_TRACE__CONFIG_FILE_PATH, oldConfigFilePath, configFilePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.START_TRACE__ENABLE_PRIMARY_FILTERS:
				return getEnablePrimaryFilters();
			case DriverPackage.START_TRACE__ENABLE_SECONDARY_FILTERS:
				return getEnableSecondaryFilters();
			case DriverPackage.START_TRACE__DISABLE_PRIMARY_FILTERS:
				return getDisablePrimaryFilters();
			case DriverPackage.START_TRACE__DISABLE_SECONDARY_FILTERS:
				return getDisableSecondaryFilters();
			case DriverPackage.START_TRACE__CONFIG_FILE_PATH:
				return getConfigFilePath();
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
			case DriverPackage.START_TRACE__ENABLE_PRIMARY_FILTERS:
				setEnablePrimaryFilters((String)newValue);
				return;
			case DriverPackage.START_TRACE__ENABLE_SECONDARY_FILTERS:
				setEnableSecondaryFilters((String)newValue);
				return;
			case DriverPackage.START_TRACE__DISABLE_PRIMARY_FILTERS:
				setDisablePrimaryFilters((String)newValue);
				return;
			case DriverPackage.START_TRACE__DISABLE_SECONDARY_FILTERS:
				setDisableSecondaryFilters((String)newValue);
				return;
			case DriverPackage.START_TRACE__CONFIG_FILE_PATH:
				setConfigFilePath((String)newValue);
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
			case DriverPackage.START_TRACE__ENABLE_PRIMARY_FILTERS:
				setEnablePrimaryFilters(ENABLE_PRIMARY_FILTERS_EDEFAULT);
				return;
			case DriverPackage.START_TRACE__ENABLE_SECONDARY_FILTERS:
				setEnableSecondaryFilters(ENABLE_SECONDARY_FILTERS_EDEFAULT);
				return;
			case DriverPackage.START_TRACE__DISABLE_PRIMARY_FILTERS:
				setDisablePrimaryFilters(DISABLE_PRIMARY_FILTERS_EDEFAULT);
				return;
			case DriverPackage.START_TRACE__DISABLE_SECONDARY_FILTERS:
				setDisableSecondaryFilters(DISABLE_SECONDARY_FILTERS_EDEFAULT);
				return;
			case DriverPackage.START_TRACE__CONFIG_FILE_PATH:
				setConfigFilePath(CONFIG_FILE_PATH_EDEFAULT);
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
			case DriverPackage.START_TRACE__ENABLE_PRIMARY_FILTERS:
				return ENABLE_PRIMARY_FILTERS_EDEFAULT == null ? enablePrimaryFilters != null : !ENABLE_PRIMARY_FILTERS_EDEFAULT.equals(enablePrimaryFilters);
			case DriverPackage.START_TRACE__ENABLE_SECONDARY_FILTERS:
				return ENABLE_SECONDARY_FILTERS_EDEFAULT == null ? enableSecondaryFilters != null : !ENABLE_SECONDARY_FILTERS_EDEFAULT.equals(enableSecondaryFilters);
			case DriverPackage.START_TRACE__DISABLE_PRIMARY_FILTERS:
				return DISABLE_PRIMARY_FILTERS_EDEFAULT == null ? disablePrimaryFilters != null : !DISABLE_PRIMARY_FILTERS_EDEFAULT.equals(disablePrimaryFilters);
			case DriverPackage.START_TRACE__DISABLE_SECONDARY_FILTERS:
				return DISABLE_SECONDARY_FILTERS_EDEFAULT == null ? disableSecondaryFilters != null : !DISABLE_SECONDARY_FILTERS_EDEFAULT.equals(disableSecondaryFilters);
			case DriverPackage.START_TRACE__CONFIG_FILE_PATH:
				return CONFIG_FILE_PATH_EDEFAULT == null ? configFilePath != null : !CONFIG_FILE_PATH_EDEFAULT.equals(configFilePath);
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
		result.append(" (enablePrimaryFilters: ");
		result.append(enablePrimaryFilters);
		result.append(", enableSecondaryFilters: ");
		result.append(enableSecondaryFilters);
		result.append(", disablePrimaryFilters: ");
		result.append(disablePrimaryFilters);
		result.append(", disableSecondaryFilters: ");
		result.append(disableSecondaryFilters);
		result.append(", configFilePath: ");
		result.append(configFilePath);
		result.append(')');
		return result.toString();
	}

} //StartTraceImpl
