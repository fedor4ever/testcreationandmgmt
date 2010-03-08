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

import com.symbian.tef.script.RunUtils;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.UtilityCommand;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Run Utils</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.RunUtilsImpl#getUtilityCommand <em>Utility Command</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RunUtilsImpl#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RunUtilsImpl extends EObjectImpl implements RunUtils {
	/**
	 * The default value of the '{@link #getUtilityCommand() <em>Utility Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilityCommand()
	 * @generated
	 * @ordered
	 */
	protected static final UtilityCommand UTILITY_COMMAND_EDEFAULT = UtilityCommand.MAKE_READ_WRITE;

	/**
	 * The cached value of the '{@link #getUtilityCommand() <em>Utility Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUtilityCommand()
	 * @generated
	 * @ordered
	 */
	protected UtilityCommand utilityCommand = UTILITY_COMMAND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParam() <em>Param</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParam()
	 * @generated
	 * @ordered
	 */
	protected EList<String> param;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RunUtilsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.RUN_UTILS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UtilityCommand getUtilityCommand() {
		return utilityCommand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUtilityCommand(UtilityCommand newUtilityCommand) {
		UtilityCommand oldUtilityCommand = utilityCommand;
		utilityCommand = newUtilityCommand == null ? UTILITY_COMMAND_EDEFAULT : newUtilityCommand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.RUN_UTILS__UTILITY_COMMAND, oldUtilityCommand, utilityCommand));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getParam() {
		if (param == null) {
			param = new EDataTypeUniqueEList<String>(String.class, this, ScriptPackage.RUN_UTILS__PARAM);
		}
		return param;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.RUN_UTILS__UTILITY_COMMAND:
				return getUtilityCommand();
			case ScriptPackage.RUN_UTILS__PARAM:
				return getParam();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScriptPackage.RUN_UTILS__UTILITY_COMMAND:
				setUtilityCommand((UtilityCommand)newValue);
				return;
			case ScriptPackage.RUN_UTILS__PARAM:
				getParam().clear();
				getParam().addAll((Collection<? extends String>)newValue);
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
			case ScriptPackage.RUN_UTILS__UTILITY_COMMAND:
				setUtilityCommand(UTILITY_COMMAND_EDEFAULT);
				return;
			case ScriptPackage.RUN_UTILS__PARAM:
				getParam().clear();
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
			case ScriptPackage.RUN_UTILS__UTILITY_COMMAND:
				return utilityCommand != UTILITY_COMMAND_EDEFAULT;
			case ScriptPackage.RUN_UTILS__PARAM:
				return param != null && !param.isEmpty();
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
		result.append(" (utilityCommand: ");
		result.append(utilityCommand);
		result.append(", param: ");
		result.append(param);
		result.append(')');
		return result.toString();
	}

} //RunUtilsImpl