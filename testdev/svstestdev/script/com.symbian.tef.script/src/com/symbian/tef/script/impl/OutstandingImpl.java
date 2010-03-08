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


package com.symbian.tef.script.impl;

import com.symbian.tef.script.Outstanding;
import com.symbian.tef.script.ScriptPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outstanding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.OutstandingImpl#getPollInterval <em>Poll Interval</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.OutstandingImpl#getObjectName <em>Object Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutstandingImpl extends EObjectImpl implements Outstanding {
	/**
	 * The default value of the '{@link #getPollInterval() <em>Poll Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollInterval()
	 * @generated
	 * @ordered
	 */
	protected static final Integer POLL_INTERVAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPollInterval() <em>Poll Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollInterval()
	 * @generated
	 * @ordered
	 */
	protected Integer pollInterval = POLL_INTERVAL_EDEFAULT;

	/**
	 * This is true if the Poll Interval attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean pollIntervalESet;

	/**
	 * The default value of the '{@link #getObjectName() <em>Object Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String OBJECT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getObjectName() <em>Object Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectName()
	 * @generated
	 * @ordered
	 */
	protected String objectName = OBJECT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutstandingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.OUTSTANDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPollInterval() {
		return pollInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPollInterval(Integer newPollInterval) {
		Integer oldPollInterval = pollInterval;
		pollInterval = newPollInterval;
		boolean oldPollIntervalESet = pollIntervalESet;
		pollIntervalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.OUTSTANDING__POLL_INTERVAL, oldPollInterval, pollInterval, !oldPollIntervalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPollInterval() {
		Integer oldPollInterval = pollInterval;
		boolean oldPollIntervalESet = pollIntervalESet;
		pollInterval = POLL_INTERVAL_EDEFAULT;
		pollIntervalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.OUTSTANDING__POLL_INTERVAL, oldPollInterval, POLL_INTERVAL_EDEFAULT, oldPollIntervalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPollInterval() {
		return pollIntervalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectName(String newObjectName) {
		String oldObjectName = objectName;
		objectName = newObjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.OUTSTANDING__OBJECT_NAME, oldObjectName, objectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.OUTSTANDING__POLL_INTERVAL:
				return getPollInterval();
			case ScriptPackage.OUTSTANDING__OBJECT_NAME:
				return getObjectName();
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
			case ScriptPackage.OUTSTANDING__POLL_INTERVAL:
				setPollInterval((Integer)newValue);
				return;
			case ScriptPackage.OUTSTANDING__OBJECT_NAME:
				setObjectName((String)newValue);
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
			case ScriptPackage.OUTSTANDING__POLL_INTERVAL:
				unsetPollInterval();
				return;
			case ScriptPackage.OUTSTANDING__OBJECT_NAME:
				setObjectName(OBJECT_NAME_EDEFAULT);
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
			case ScriptPackage.OUTSTANDING__POLL_INTERVAL:
				return isSetPollInterval();
			case ScriptPackage.OUTSTANDING__OBJECT_NAME:
				return OBJECT_NAME_EDEFAULT == null ? objectName != null : !OBJECT_NAME_EDEFAULT.equals(objectName);
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
		result.append(" (pollInterval: ");
		if (pollIntervalESet) result.append(pollInterval); else result.append("<unset>");
		result.append(", objectName: ");
		result.append(objectName);
		result.append(')');
		return result.toString();
	}

} //OutstandingImpl
