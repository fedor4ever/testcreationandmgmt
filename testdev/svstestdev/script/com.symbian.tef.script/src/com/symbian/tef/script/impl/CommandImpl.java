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

import com.symbian.ini.Section;

import com.symbian.tef.script.Command;
import com.symbian.tef.script.ScriptPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.CommandImpl#getError <em>Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.CommandImpl#getAsyncError <em>Async Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.CommandImpl#getObjectName <em>Object Name</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.CommandImpl#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.CommandImpl#getSection <em>Section</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommandImpl extends EObjectImpl implements Command {
	/**
	 * The default value of the '{@link #getError() <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getError() <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected Integer error = ERROR_EDEFAULT;

	/**
	 * This is true if the Error attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean errorESet;

	/**
	 * The default value of the '{@link #getAsyncError() <em>Async Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsyncError()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ASYNC_ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAsyncError() <em>Async Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsyncError()
	 * @generated
	 * @ordered
	 */
	protected Integer asyncError = ASYNC_ERROR_EDEFAULT;

	/**
	 * This is true if the Async Error attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean asyncErrorESet;

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
	 * The default value of the '{@link #getFunctionName() <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionName()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunctionName() <em>Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionName()
	 * @generated
	 * @ordered
	 */
	protected String functionName = FUNCTION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSection() <em>Section</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSection()
	 * @generated
	 * @ordered
	 */
	protected Section section;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.COMMAND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getError() {
		return error;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setError(Integer newError) {
		Integer oldError = error;
		error = newError;
		boolean oldErrorESet = errorESet;
		errorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.COMMAND__ERROR, oldError, error, !oldErrorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetError() {
		Integer oldError = error;
		boolean oldErrorESet = errorESet;
		error = ERROR_EDEFAULT;
		errorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.COMMAND__ERROR, oldError, ERROR_EDEFAULT, oldErrorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetError() {
		return errorESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getAsyncError() {
		return asyncError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsyncError(Integer newAsyncError) {
		Integer oldAsyncError = asyncError;
		asyncError = newAsyncError;
		boolean oldAsyncErrorESet = asyncErrorESet;
		asyncErrorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.COMMAND__ASYNC_ERROR, oldAsyncError, asyncError, !oldAsyncErrorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAsyncError() {
		Integer oldAsyncError = asyncError;
		boolean oldAsyncErrorESet = asyncErrorESet;
		asyncError = ASYNC_ERROR_EDEFAULT;
		asyncErrorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.COMMAND__ASYNC_ERROR, oldAsyncError, ASYNC_ERROR_EDEFAULT, oldAsyncErrorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAsyncError() {
		return asyncErrorESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.COMMAND__OBJECT_NAME, oldObjectName, objectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionName(String newFunctionName) {
		String oldFunctionName = functionName;
		functionName = newFunctionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.COMMAND__FUNCTION_NAME, oldFunctionName, functionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section getSection() {
		if (section != null && section.eIsProxy()) {
			InternalEObject oldSection = (InternalEObject)section;
			section = (Section)eResolveProxy(oldSection);
			if (section != oldSection) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScriptPackage.COMMAND__SECTION, oldSection, section));
			}
		}
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section basicGetSection() {
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSection(Section newSection) {
		Section oldSection = section;
		section = newSection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.COMMAND__SECTION, oldSection, section));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.COMMAND__ERROR:
				return getError();
			case ScriptPackage.COMMAND__ASYNC_ERROR:
				return getAsyncError();
			case ScriptPackage.COMMAND__OBJECT_NAME:
				return getObjectName();
			case ScriptPackage.COMMAND__FUNCTION_NAME:
				return getFunctionName();
			case ScriptPackage.COMMAND__SECTION:
				if (resolve) return getSection();
				return basicGetSection();
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
			case ScriptPackage.COMMAND__ERROR:
				setError((Integer)newValue);
				return;
			case ScriptPackage.COMMAND__ASYNC_ERROR:
				setAsyncError((Integer)newValue);
				return;
			case ScriptPackage.COMMAND__OBJECT_NAME:
				setObjectName((String)newValue);
				return;
			case ScriptPackage.COMMAND__FUNCTION_NAME:
				setFunctionName((String)newValue);
				return;
			case ScriptPackage.COMMAND__SECTION:
				setSection((Section)newValue);
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
			case ScriptPackage.COMMAND__ERROR:
				unsetError();
				return;
			case ScriptPackage.COMMAND__ASYNC_ERROR:
				unsetAsyncError();
				return;
			case ScriptPackage.COMMAND__OBJECT_NAME:
				setObjectName(OBJECT_NAME_EDEFAULT);
				return;
			case ScriptPackage.COMMAND__FUNCTION_NAME:
				setFunctionName(FUNCTION_NAME_EDEFAULT);
				return;
			case ScriptPackage.COMMAND__SECTION:
				setSection((Section)null);
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
			case ScriptPackage.COMMAND__ERROR:
				return isSetError();
			case ScriptPackage.COMMAND__ASYNC_ERROR:
				return isSetAsyncError();
			case ScriptPackage.COMMAND__OBJECT_NAME:
				return OBJECT_NAME_EDEFAULT == null ? objectName != null : !OBJECT_NAME_EDEFAULT.equals(objectName);
			case ScriptPackage.COMMAND__FUNCTION_NAME:
				return FUNCTION_NAME_EDEFAULT == null ? functionName != null : !FUNCTION_NAME_EDEFAULT.equals(functionName);
			case ScriptPackage.COMMAND__SECTION:
				return section != null;
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
		result.append(" (Error: ");
		if (errorESet) result.append(error); else result.append("<unset>");
		result.append(", AsyncError: ");
		if (asyncErrorESet) result.append(asyncError); else result.append("<unset>");
		result.append(", objectName: ");
		result.append(objectName);
		result.append(", functionName: ");
		result.append(functionName);
		result.append(')');
		return result.toString();
	}

} //CommandImpl
