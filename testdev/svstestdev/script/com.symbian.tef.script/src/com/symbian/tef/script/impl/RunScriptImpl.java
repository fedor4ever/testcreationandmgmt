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

import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.ScriptPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Run Script</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.RunScriptImpl#getScript <em>Script</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RunScriptImpl#getScriptPersistance <em>Script Persistance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RunScriptImpl extends EObjectImpl implements RunScript {
	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final Resource SCRIPT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected Resource script = SCRIPT_EDEFAULT;

	/**
	 * The default value of the '{@link #getScriptPersistance() <em>Script Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptPersistance()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_PERSISTANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScriptPersistance() <em>Script Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScriptPersistance()
	 * @generated
	 * @ordered
	 */
	protected String scriptPersistance = SCRIPT_PERSISTANCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RunScriptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.RUN_SCRIPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(Resource newScript) {
		Resource oldScript = script;
		script = newScript;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.RUN_SCRIPT__SCRIPT, oldScript, script));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScriptPersistance() {
		return scriptPersistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScriptPersistance(String newScriptPersistance) {
		String oldScriptPersistance = scriptPersistance;
		scriptPersistance = newScriptPersistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.RUN_SCRIPT__SCRIPT_PERSISTANCE, oldScriptPersistance, scriptPersistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.RUN_SCRIPT__SCRIPT:
				return getScript();
			case ScriptPackage.RUN_SCRIPT__SCRIPT_PERSISTANCE:
				return getScriptPersistance();
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
			case ScriptPackage.RUN_SCRIPT__SCRIPT:
				setScript((Resource)newValue);
				return;
			case ScriptPackage.RUN_SCRIPT__SCRIPT_PERSISTANCE:
				setScriptPersistance((String)newValue);
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
			case ScriptPackage.RUN_SCRIPT__SCRIPT:
				setScript(SCRIPT_EDEFAULT);
				return;
			case ScriptPackage.RUN_SCRIPT__SCRIPT_PERSISTANCE:
				setScriptPersistance(SCRIPT_PERSISTANCE_EDEFAULT);
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
			case ScriptPackage.RUN_SCRIPT__SCRIPT:
				return SCRIPT_EDEFAULT == null ? script != null : !SCRIPT_EDEFAULT.equals(script);
			case ScriptPackage.RUN_SCRIPT__SCRIPT_PERSISTANCE:
				return SCRIPT_PERSISTANCE_EDEFAULT == null ? scriptPersistance != null : !SCRIPT_PERSISTANCE_EDEFAULT.equals(scriptPersistance);
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
		result.append(" (script: ");
		result.append(script);
		result.append(", scriptPersistance: ");
		result.append(scriptPersistance);
		result.append(')');
		return result.toString();
	}

} //RunScriptImpl