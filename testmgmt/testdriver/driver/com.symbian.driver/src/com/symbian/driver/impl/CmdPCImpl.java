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

import com.symbian.driver.CmdPC;
import com.symbian.driver.DriverPackage;

import com.symbian.driver.Phase;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cmd PC</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.CmdPCImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdPCImpl#getPhase <em>Phase</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdPCImpl#isSync <em>Sync</em>}</li>
 *   <li>{@link com.symbian.driver.impl.CmdPCImpl#getURI <em>URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CmdPCImpl extends EObjectImpl implements CmdPC {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected static final Phase PHASE_EDEFAULT = Phase.BUILD;

	/**
	 * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected Phase phase = PHASE_EDEFAULT;

	/**
	 * This is true if the Phase attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean phaseESet;

	/**
	 * The default value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNC_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSync() <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSync()
	 * @generated
	 * @ordered
	 */
	protected boolean sync = SYNC_EDEFAULT;

	/**
	 * This is true if the Sync attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean syncESet;

	/**
	 * The default value of the '{@link #getURI() <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getURI()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = "file://c:/";

	/**
	 * The cached value of the '{@link #getURI() <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getURI()
	 * @generated
	 * @ordered
	 */
	protected String uRI = URI_EDEFAULT;

	/**
	 * This is true if the URI attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean uRIESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CmdPCImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.CMD_PC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_PC__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase getPhase() {
		return phase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhase(Phase newPhase) {
		Phase oldPhase = phase;
		phase = newPhase == null ? PHASE_EDEFAULT : newPhase;
		boolean oldPhaseESet = phaseESet;
		phaseESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_PC__PHASE, oldPhase, phase, !oldPhaseESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPhase() {
		Phase oldPhase = phase;
		boolean oldPhaseESet = phaseESet;
		phase = PHASE_EDEFAULT;
		phaseESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.CMD_PC__PHASE, oldPhase, PHASE_EDEFAULT, oldPhaseESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPhase() {
		return phaseESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSync() {
		return sync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSync(boolean newSync) {
		boolean oldSync = sync;
		sync = newSync;
		boolean oldSyncESet = syncESet;
		syncESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_PC__SYNC, oldSync, sync, !oldSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSync() {
		boolean oldSync = sync;
		boolean oldSyncESet = syncESet;
		sync = SYNC_EDEFAULT;
		syncESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.CMD_PC__SYNC, oldSync, SYNC_EDEFAULT, oldSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSync() {
		return syncESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getURI() {
		return uRI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setURI(String newURI) {
		String oldURI = uRI;
		uRI = newURI;
		boolean oldURIESet = uRIESet;
		uRIESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.CMD_PC__URI, oldURI, uRI, !oldURIESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetURI() {
		String oldURI = uRI;
		boolean oldURIESet = uRIESet;
		uRI = URI_EDEFAULT;
		uRIESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.CMD_PC__URI, oldURI, URI_EDEFAULT, oldURIESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetURI() {
		return uRIESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DriverPackage.CMD_PC__VALUE:
				return getValue();
			case DriverPackage.CMD_PC__PHASE:
				return getPhase();
			case DriverPackage.CMD_PC__SYNC:
				return isSync() ? Boolean.TRUE : Boolean.FALSE;
			case DriverPackage.CMD_PC__URI:
				return getURI();
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
			case DriverPackage.CMD_PC__VALUE:
				setValue((String)newValue);
				return;
			case DriverPackage.CMD_PC__PHASE:
				setPhase((Phase)newValue);
				return;
			case DriverPackage.CMD_PC__SYNC:
				setSync(((Boolean)newValue).booleanValue());
				return;
			case DriverPackage.CMD_PC__URI:
				setURI((String)newValue);
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
			case DriverPackage.CMD_PC__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case DriverPackage.CMD_PC__PHASE:
				unsetPhase();
				return;
			case DriverPackage.CMD_PC__SYNC:
				unsetSync();
				return;
			case DriverPackage.CMD_PC__URI:
				unsetURI();
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
			case DriverPackage.CMD_PC__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case DriverPackage.CMD_PC__PHASE:
				return isSetPhase();
			case DriverPackage.CMD_PC__SYNC:
				return isSetSync();
			case DriverPackage.CMD_PC__URI:
				return isSetURI();
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
		result.append(" (value: ");
		result.append(value);
		result.append(", phase: ");
		if (phaseESet) result.append(phase); else result.append("<unset>");
		result.append(", sync: ");
		if (syncESet) result.append(sync); else result.append("<unset>");
		result.append(", uRI: ");
		if (uRIESet) result.append(uRI); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CmdPCImpl