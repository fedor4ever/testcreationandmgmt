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

import com.symbian.driver.CmdSymbian;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.ExecuteOnSymbian;

import com.symbian.driver.Rtest;
import com.symbian.driver.TestExecuteScript;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execute On Symbian</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnSymbianImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnSymbianImpl#getCmd <em>Cmd</em>}</li>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnSymbianImpl#getTestExecuteScript <em>Test Execute Script</em>}</li>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnSymbianImpl#getRtest <em>Rtest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecuteOnSymbianImpl extends EObjectImpl implements ExecuteOnSymbian {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecuteOnSymbianImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.EXECUTE_ON_SYMBIAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, DriverPackage.EXECUTE_ON_SYMBIAN__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CmdSymbian> getCmd() {
		return getGroup().list(DriverPackage.Literals.EXECUTE_ON_SYMBIAN__CMD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestExecuteScript> getTestExecuteScript() {
		return getGroup().list(DriverPackage.Literals.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rtest> getRtest() {
		return getGroup().list(DriverPackage.Literals.EXECUTE_ON_SYMBIAN__RTEST);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DriverPackage.EXECUTE_ON_SYMBIAN__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case DriverPackage.EXECUTE_ON_SYMBIAN__CMD:
				return ((InternalEList<?>)getCmd()).basicRemove(otherEnd, msgs);
			case DriverPackage.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT:
				return ((InternalEList<?>)getTestExecuteScript()).basicRemove(otherEnd, msgs);
			case DriverPackage.EXECUTE_ON_SYMBIAN__RTEST:
				return ((InternalEList<?>)getRtest()).basicRemove(otherEnd, msgs);
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
			case DriverPackage.EXECUTE_ON_SYMBIAN__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case DriverPackage.EXECUTE_ON_SYMBIAN__CMD:
				return getCmd();
			case DriverPackage.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT:
				return getTestExecuteScript();
			case DriverPackage.EXECUTE_ON_SYMBIAN__RTEST:
				return getRtest();
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
			case DriverPackage.EXECUTE_ON_SYMBIAN__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__CMD:
				getCmd().clear();
				getCmd().addAll((Collection<? extends CmdSymbian>)newValue);
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT:
				getTestExecuteScript().clear();
				getTestExecuteScript().addAll((Collection<? extends TestExecuteScript>)newValue);
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__RTEST:
				getRtest().clear();
				getRtest().addAll((Collection<? extends Rtest>)newValue);
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
			case DriverPackage.EXECUTE_ON_SYMBIAN__GROUP:
				getGroup().clear();
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__CMD:
				getCmd().clear();
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT:
				getTestExecuteScript().clear();
				return;
			case DriverPackage.EXECUTE_ON_SYMBIAN__RTEST:
				getRtest().clear();
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
			case DriverPackage.EXECUTE_ON_SYMBIAN__GROUP:
				return group != null && !group.isEmpty();
			case DriverPackage.EXECUTE_ON_SYMBIAN__CMD:
				return !getCmd().isEmpty();
			case DriverPackage.EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT:
				return !getTestExecuteScript().isEmpty();
			case DriverPackage.EXECUTE_ON_SYMBIAN__RTEST:
				return !getRtest().isEmpty();
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
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //ExecuteOnSymbianImpl