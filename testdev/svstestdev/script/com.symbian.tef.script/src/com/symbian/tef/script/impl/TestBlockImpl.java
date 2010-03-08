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

import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TestBlock;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getTef <em>Tef</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getHeap <em>Heap</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getServer <em>Server</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getIniFile <em>Ini File</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getPanicCode <em>Panic Code</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestBlockImpl#getPanicString <em>Panic String</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestBlockImpl extends EObjectImpl implements TestBlock {
	/**
	 * The cached value of the '{@link #getTef() <em>Tef</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTef()
	 * @generated
	 * @ordered
	 */
	protected EList<Tef> tef;

	/**
	 * The default value of the '{@link #getHeap() <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeap()
	 * @generated
	 * @ordered
	 */
	protected static final Integer HEAP_EDEFAULT = new Integer(1048576);

	/**
	 * The cached value of the '{@link #getHeap() <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeap()
	 * @generated
	 * @ordered
	 */
	protected Integer heap = HEAP_EDEFAULT;

	/**
	 * This is true if the Heap attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean heapESet;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 100;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getServer() <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServer() <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected String server = SERVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getIniFile() <em>Ini File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIniFile()
	 * @generated
	 * @ordered
	 */
	protected static final String INI_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIniFile() <em>Ini File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIniFile()
	 * @generated
	 * @ordered
	 */
	protected String iniFile = INI_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPanicCode() <em>Panic Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanicCode()
	 * @generated
	 * @ordered
	 */
	protected static final int PANIC_CODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPanicCode() <em>Panic Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanicCode()
	 * @generated
	 * @ordered
	 */
	protected int panicCode = PANIC_CODE_EDEFAULT;

	/**
	 * This is true if the Panic Code attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean panicCodeESet;

	/**
	 * The default value of the '{@link #getPanicString() <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanicString()
	 * @generated
	 * @ordered
	 */
	protected static final String PANIC_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPanicString() <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanicString()
	 * @generated
	 * @ordered
	 */
	protected String panicString = PANIC_STRING_EDEFAULT;

	/**
	 * This is true if the Panic String attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean panicStringESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestBlockImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.TEST_BLOCK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tef> getTef() {
		if (tef == null) {
			tef = new EObjectContainmentEList<Tef>(Tef.class, this, ScriptPackage.TEST_BLOCK__TEF);
		}
		return tef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getHeap() {
		return heap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeap(Integer newHeap) {
		Integer oldHeap = heap;
		heap = newHeap;
		boolean oldHeapESet = heapESet;
		heapESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__HEAP, oldHeap, heap, !oldHeapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHeap() {
		Integer oldHeap = heap;
		boolean oldHeapESet = heapESet;
		heap = HEAP_EDEFAULT;
		heapESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_BLOCK__HEAP, oldHeap, HEAP_EDEFAULT, oldHeapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHeap() {
		return heapESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(int newTimeout) {
		int oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServer() {
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServer(String newServer) {
		String oldServer = server;
		server = newServer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__SERVER, oldServer, server));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIniFile() {
		return iniFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIniFile(String newIniFile) {
		String oldIniFile = iniFile;
		iniFile = newIniFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__INI_FILE, oldIniFile, iniFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPanicCode() {
		return panicCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanicCode(int newPanicCode) {
		int oldPanicCode = panicCode;
		panicCode = newPanicCode;
		boolean oldPanicCodeESet = panicCodeESet;
		panicCodeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__PANIC_CODE, oldPanicCode, panicCode, !oldPanicCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPanicCode() {
		int oldPanicCode = panicCode;
		boolean oldPanicCodeESet = panicCodeESet;
		panicCode = PANIC_CODE_EDEFAULT;
		panicCodeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_BLOCK__PANIC_CODE, oldPanicCode, PANIC_CODE_EDEFAULT, oldPanicCodeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPanicCode() {
		return panicCodeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPanicString() {
		return panicString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanicString(String newPanicString) {
		String oldPanicString = panicString;
		panicString = newPanicString;
		boolean oldPanicStringESet = panicStringESet;
		panicStringESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_BLOCK__PANIC_STRING, oldPanicString, panicString, !oldPanicStringESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPanicString() {
		String oldPanicString = panicString;
		boolean oldPanicStringESet = panicStringESet;
		panicString = PANIC_STRING_EDEFAULT;
		panicStringESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_BLOCK__PANIC_STRING, oldPanicString, PANIC_STRING_EDEFAULT, oldPanicStringESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPanicString() {
		return panicStringESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScriptPackage.TEST_BLOCK__TEF:
				return ((InternalEList<?>)getTef()).basicRemove(otherEnd, msgs);
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
			case ScriptPackage.TEST_BLOCK__TEF:
				return getTef();
			case ScriptPackage.TEST_BLOCK__HEAP:
				return getHeap();
			case ScriptPackage.TEST_BLOCK__TIMEOUT:
				return new Integer(getTimeout());
			case ScriptPackage.TEST_BLOCK__SERVER:
				return getServer();
			case ScriptPackage.TEST_BLOCK__INI_FILE:
				return getIniFile();
			case ScriptPackage.TEST_BLOCK__PANIC_CODE:
				return new Integer(getPanicCode());
			case ScriptPackage.TEST_BLOCK__PANIC_STRING:
				return getPanicString();
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
			case ScriptPackage.TEST_BLOCK__TEF:
				getTef().clear();
				getTef().addAll((Collection<? extends Tef>)newValue);
				return;
			case ScriptPackage.TEST_BLOCK__HEAP:
				setHeap((Integer)newValue);
				return;
			case ScriptPackage.TEST_BLOCK__TIMEOUT:
				setTimeout(((Integer)newValue).intValue());
				return;
			case ScriptPackage.TEST_BLOCK__SERVER:
				setServer((String)newValue);
				return;
			case ScriptPackage.TEST_BLOCK__INI_FILE:
				setIniFile((String)newValue);
				return;
			case ScriptPackage.TEST_BLOCK__PANIC_CODE:
				setPanicCode(((Integer)newValue).intValue());
				return;
			case ScriptPackage.TEST_BLOCK__PANIC_STRING:
				setPanicString((String)newValue);
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
			case ScriptPackage.TEST_BLOCK__TEF:
				getTef().clear();
				return;
			case ScriptPackage.TEST_BLOCK__HEAP:
				unsetHeap();
				return;
			case ScriptPackage.TEST_BLOCK__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case ScriptPackage.TEST_BLOCK__SERVER:
				setServer(SERVER_EDEFAULT);
				return;
			case ScriptPackage.TEST_BLOCK__INI_FILE:
				setIniFile(INI_FILE_EDEFAULT);
				return;
			case ScriptPackage.TEST_BLOCK__PANIC_CODE:
				unsetPanicCode();
				return;
			case ScriptPackage.TEST_BLOCK__PANIC_STRING:
				unsetPanicString();
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
			case ScriptPackage.TEST_BLOCK__TEF:
				return tef != null && !tef.isEmpty();
			case ScriptPackage.TEST_BLOCK__HEAP:
				return isSetHeap();
			case ScriptPackage.TEST_BLOCK__TIMEOUT:
				return timeout != TIMEOUT_EDEFAULT;
			case ScriptPackage.TEST_BLOCK__SERVER:
				return SERVER_EDEFAULT == null ? server != null : !SERVER_EDEFAULT.equals(server);
			case ScriptPackage.TEST_BLOCK__INI_FILE:
				return INI_FILE_EDEFAULT == null ? iniFile != null : !INI_FILE_EDEFAULT.equals(iniFile);
			case ScriptPackage.TEST_BLOCK__PANIC_CODE:
				return isSetPanicCode();
			case ScriptPackage.TEST_BLOCK__PANIC_STRING:
				return isSetPanicString();
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
		result.append(" (Heap: ");
		if (heapESet) result.append(heap); else result.append("<unset>");
		result.append(", timeout: ");
		result.append(timeout);
		result.append(", server: ");
		result.append(server);
		result.append(", iniFile: ");
		result.append(iniFile);
		result.append(", PanicCode: ");
		if (panicCodeESet) result.append(panicCode); else result.append("<unset>");
		result.append(", PanicString: ");
		if (panicStringESet) result.append(panicString); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TestBlockImpl
