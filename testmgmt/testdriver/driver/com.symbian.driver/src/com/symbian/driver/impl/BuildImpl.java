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
import com.symbian.driver.DriverFactory;
import com.symbian.driver.DriverPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Build</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.driver.impl.BuildImpl#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link com.symbian.driver.impl.BuildImpl#isTestBuild <em>Test Build</em>}</li>
 *   <li>{@link com.symbian.driver.impl.BuildImpl#getURI <em>URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuildImpl extends EObjectImpl implements Build {
	/**
	 * The cached value of the '{@link #getComponentName() <em>Component Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentName()
	 * @generated
	 * @ordered
	 */
	protected EList<String> componentName;

	/**
	 * The default value of the '{@link #isTestBuild() <em>Test Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBuild()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TEST_BUILD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTestBuild() <em>Test Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestBuild()
	 * @generated
	 * @ordered
	 */
	protected boolean testBuild = TEST_BUILD_EDEFAULT;

	/**
	 * This is true if the Test Build attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean testBuildESet;

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
	protected BuildImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DriverPackage.Literals.BUILD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComponentName() {
		if (componentName == null) {
			componentName = new EDataTypeEList<String>(String.class, this, DriverPackage.BUILD__COMPONENT_NAME);
		}
		return componentName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTestBuild() {
		return testBuild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestBuild(boolean newTestBuild) {
		boolean oldTestBuild = testBuild;
		testBuild = newTestBuild;
		boolean oldTestBuildESet = testBuildESet;
		testBuildESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.BUILD__TEST_BUILD, oldTestBuild, testBuild, !oldTestBuildESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTestBuild() {
		boolean oldTestBuild = testBuild;
		boolean oldTestBuildESet = testBuildESet;
		testBuild = TEST_BUILD_EDEFAULT;
		testBuildESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.BUILD__TEST_BUILD, oldTestBuild, TEST_BUILD_EDEFAULT, oldTestBuildESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTestBuild() {
		return testBuildESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DriverPackage.BUILD__URI, oldURI, uRI, !oldURIESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, DriverPackage.BUILD__URI, oldURI, URI_EDEFAULT, oldURIESet));
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
			case DriverPackage.BUILD__COMPONENT_NAME:
				return getComponentName();
			case DriverPackage.BUILD__TEST_BUILD:
				return isTestBuild() ? Boolean.TRUE : Boolean.FALSE;
			case DriverPackage.BUILD__URI:
				return getURI();
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
			case DriverPackage.BUILD__COMPONENT_NAME:
				getComponentName().clear();
				getComponentName().addAll((Collection<? extends String>)newValue);
				return;
			case DriverPackage.BUILD__TEST_BUILD:
				setTestBuild(((Boolean)newValue).booleanValue());
				return;
			case DriverPackage.BUILD__URI:
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
			case DriverPackage.BUILD__COMPONENT_NAME:
				getComponentName().clear();
				return;
			case DriverPackage.BUILD__TEST_BUILD:
				unsetTestBuild();
				return;
			case DriverPackage.BUILD__URI:
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
			case DriverPackage.BUILD__COMPONENT_NAME:
				return componentName != null && !componentName.isEmpty();
			case DriverPackage.BUILD__TEST_BUILD:
				return isSetTestBuild();
			case DriverPackage.BUILD__URI:
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
		result.append(" (componentName: ");
		result.append(componentName);
		result.append(", testBuild: ");
		if (testBuildESet) result.append(testBuild); else result.append("<unset>");
		result.append(", uRI: ");
		if (uRIESet) result.append(uRI); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * @see java.lang.Object#clone()
	 * @generated NOT
	 */
	public Object clone() {
		Build lBuild = DriverFactory.eINSTANCE.createBuild();
		lBuild.getComponentName().addAll(componentName);
		lBuild.setTestBuild(testBuild);
		lBuild.setURI(uRI);
		return lBuild;
	}

} //BuildImpl