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

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.DriverPackage;
import com.symbian.driver.ExecuteOnPC;

import java.util.Collection;
import java.util.Iterator;

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
 * An implementation of the model object '<em><b>Execute On PC</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnPCImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnPCImpl#getCmd <em>Cmd</em>}</li>
 *   <li>{@link com.symbian.driver.impl.ExecuteOnPCImpl#getBuild <em>Build</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecuteOnPCImpl extends EObjectImpl implements ExecuteOnPC {
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
	protected ExecuteOnPCImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.EXECUTE_ON_PC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, DriverPackage.EXECUTE_ON_PC__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CmdPC> getCmd() {
		return getGroup().list(DriverPackage.Literals.EXECUTE_ON_PC__CMD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Build> getBuild() {
		return getGroup().list(DriverPackage.Literals.EXECUTE_ON_PC__BUILD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DriverPackage.EXECUTE_ON_PC__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case DriverPackage.EXECUTE_ON_PC__CMD:
				return ((InternalEList<?>)getCmd()).basicRemove(otherEnd, msgs);
			case DriverPackage.EXECUTE_ON_PC__BUILD:
				return ((InternalEList<?>)getBuild()).basicRemove(otherEnd, msgs);
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
			case DriverPackage.EXECUTE_ON_PC__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case DriverPackage.EXECUTE_ON_PC__CMD:
				return getCmd();
			case DriverPackage.EXECUTE_ON_PC__BUILD:
				return getBuild();
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
			case DriverPackage.EXECUTE_ON_PC__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case DriverPackage.EXECUTE_ON_PC__CMD:
				getCmd().clear();
				getCmd().addAll((Collection<? extends CmdPC>)newValue);
				return;
			case DriverPackage.EXECUTE_ON_PC__BUILD:
				getBuild().clear();
				getBuild().addAll((Collection<? extends Build>)newValue);
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
			case DriverPackage.EXECUTE_ON_PC__GROUP:
				getGroup().clear();
				return;
			case DriverPackage.EXECUTE_ON_PC__CMD:
				getCmd().clear();
				return;
			case DriverPackage.EXECUTE_ON_PC__BUILD:
				getBuild().clear();
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
			case DriverPackage.EXECUTE_ON_PC__GROUP:
				return group != null && !group.isEmpty();
			case DriverPackage.EXECUTE_ON_PC__CMD:
				return !getCmd().isEmpty();
			case DriverPackage.EXECUTE_ON_PC__BUILD:
				return !getBuild().isEmpty();
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Build findDuplicateBuildByURI(String aUri) {
		for (Iterator lBuildIter = getBuild().iterator(); lBuildIter.hasNext();) {
			Build lBuild = (Build) lBuildIter.next();

			if (lBuild.getURI().compareToIgnoreCase(aUri) == 0) {
				return lBuild;
			}
		}

		return null;
	}

} //ExecuteOnPCImpl