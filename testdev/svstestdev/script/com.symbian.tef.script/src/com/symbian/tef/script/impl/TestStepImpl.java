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

import com.symbian.comment.impl.AttachedCommentImpl;

import com.symbian.comment.AttachedComment;
import com.symbian.comment.CommentPackage;

import com.symbian.ini.Section;

import com.symbian.tef.script.Leaf;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SectionPesistance;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TestStep;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getSection <em>Section</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getIniPersistance <em>Ini Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getSectionPersistance <em>Section Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getServer <em>Server</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getError <em>Error</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getPanicString <em>Panic String</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getPanicCode <em>Panic Code</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getResult <em>Result</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getHeap <em>Heap</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.TestStepImpl#getOOM <em>OOM</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestStepImpl extends AttachedCommentImpl implements TestStep {
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
	 * The default value of the '{@link #getIniPersistance() <em>Ini Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIniPersistance()
	 * @generated
	 * @ordered
	 */
	protected static final String INI_PERSISTANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIniPersistance() <em>Ini Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIniPersistance()
	 * @generated
	 * @ordered
	 */
	protected String iniPersistance = INI_PERSISTANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSectionPersistance() <em>Section Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionPersistance()
	 * @generated
	 * @ordered
	 */
	protected static final String SECTION_PERSISTANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSectionPersistance() <em>Section Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionPersistance()
	 * @generated
	 * @ordered
	 */
	protected String sectionPersistance = SECTION_PERSISTANCE_EDEFAULT;

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
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected String method = METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getError() <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getError() <em>Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected String error = ERROR_EDEFAULT;

	/**
	 * This is true if the Error attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean errorESet;

	/**
	 * The default value of the '{@link #getPanicString() <em>Panic String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanicString()
	 * @generated
	 * @ordered
	 */
	protected static final String PANIC_STRING_EDEFAULT = "";

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
	 * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected String result = RESULT_EDEFAULT;

	/**
	 * This is true if the Result attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resultESet;

	/**
	 * The default value of the '{@link #getHeap() <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeap()
	 * @generated
	 * @ordered
	 */
	protected static final int HEAP_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHeap() <em>Heap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeap()
	 * @generated
	 * @ordered
	 */
	protected int heap = HEAP_EDEFAULT;

	/**
	 * This is true if the Heap attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean heapESet;

	/**
	 * The default value of the '{@link #getOOM() <em>OOM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOOM()
	 * @generated
	 * @ordered
	 */
	protected static final int OOM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOOM() <em>OOM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOOM()
	 * @generated
	 * @ordered
	 */
	protected int oom = OOM_EDEFAULT;

	/**
	 * This is true if the OOM attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean oomESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.TEST_STEP;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScriptPackage.TEST_STEP__SECTION, oldSection, section));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__SECTION, oldSection, section));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIniPersistance() {
		return iniPersistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIniPersistance(String newIniPersistance) {
		String oldIniPersistance = iniPersistance;
		iniPersistance = newIniPersistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__INI_PERSISTANCE, oldIniPersistance, iniPersistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSectionPersistance() {
		return sectionPersistance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSectionPersistance(String newSectionPersistance) {
		String oldSectionPersistance = sectionPersistance;
		sectionPersistance = newSectionPersistance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__SECTION_PERSISTANCE, oldSectionPersistance, sectionPersistance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__SERVER, oldServer, server));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethod(String newMethod) {
		String oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__METHOD, oldMethod, method));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getError() {
		return error;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setError(String newError) {
		String oldError = error;
		error = newError;
		boolean oldErrorESet = errorESet;
		errorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__ERROR, oldError, error, !oldErrorESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetError() {
		String oldError = error;
		boolean oldErrorESet = errorESet;
		error = ERROR_EDEFAULT;
		errorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__ERROR, oldError, ERROR_EDEFAULT, oldErrorESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__PANIC_STRING, oldPanicString, panicString, !oldPanicStringESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__PANIC_STRING, oldPanicString, PANIC_STRING_EDEFAULT, oldPanicStringESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__PANIC_CODE, oldPanicCode, panicCode, !oldPanicCodeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__PANIC_CODE, oldPanicCode, PANIC_CODE_EDEFAULT, oldPanicCodeESet));
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
	public String getResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResult(String newResult) {
		String oldResult = result;
		result = newResult;
		boolean oldResultESet = resultESet;
		resultESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__RESULT, oldResult, result, !oldResultESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResult() {
		String oldResult = result;
		boolean oldResultESet = resultESet;
		result = RESULT_EDEFAULT;
		resultESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__RESULT, oldResult, RESULT_EDEFAULT, oldResultESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResult() {
		return resultESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeap() {
		return heap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeap(int newHeap) {
		int oldHeap = heap;
		heap = newHeap;
		boolean oldHeapESet = heapESet;
		heapESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__HEAP, oldHeap, heap, !oldHeapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHeap() {
		int oldHeap = heap;
		boolean oldHeapESet = heapESet;
		heap = HEAP_EDEFAULT;
		heapESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__HEAP, oldHeap, HEAP_EDEFAULT, oldHeapESet));
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
	public int getOOM() {
		return oom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOOM(int newOOM) {
		int oldOOM = oom;
		oom = newOOM;
		boolean oldOOMESet = oomESet;
		oomESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.TEST_STEP__OOM, oldOOM, oom, !oldOOMESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOOM() {
		int oldOOM = oom;
		boolean oldOOMESet = oomESet;
		oom = OOM_EDEFAULT;
		oomESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScriptPackage.TEST_STEP__OOM, oldOOM, OOM_EDEFAULT, oldOOMESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOOM() {
		return oomESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.TEST_STEP__SECTION:
				if (resolve) return getSection();
				return basicGetSection();
			case ScriptPackage.TEST_STEP__INI_PERSISTANCE:
				return getIniPersistance();
			case ScriptPackage.TEST_STEP__SECTION_PERSISTANCE:
				return getSectionPersistance();
			case ScriptPackage.TEST_STEP__SERVER:
				return getServer();
			case ScriptPackage.TEST_STEP__TIMEOUT:
				return new Integer(getTimeout());
			case ScriptPackage.TEST_STEP__METHOD:
				return getMethod();
			case ScriptPackage.TEST_STEP__ERROR:
				return getError();
			case ScriptPackage.TEST_STEP__PANIC_STRING:
				return getPanicString();
			case ScriptPackage.TEST_STEP__PANIC_CODE:
				return new Integer(getPanicCode());
			case ScriptPackage.TEST_STEP__RESULT:
				return getResult();
			case ScriptPackage.TEST_STEP__HEAP:
				return new Integer(getHeap());
			case ScriptPackage.TEST_STEP__OOM:
				return new Integer(getOOM());
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
			case ScriptPackage.TEST_STEP__SECTION:
				setSection((Section)newValue);
				return;
			case ScriptPackage.TEST_STEP__INI_PERSISTANCE:
				setIniPersistance((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__SECTION_PERSISTANCE:
				setSectionPersistance((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__SERVER:
				setServer((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__TIMEOUT:
				setTimeout(((Integer)newValue).intValue());
				return;
			case ScriptPackage.TEST_STEP__METHOD:
				setMethod((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__ERROR:
				setError((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__PANIC_STRING:
				setPanicString((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__PANIC_CODE:
				setPanicCode(((Integer)newValue).intValue());
				return;
			case ScriptPackage.TEST_STEP__RESULT:
				setResult((String)newValue);
				return;
			case ScriptPackage.TEST_STEP__HEAP:
				setHeap(((Integer)newValue).intValue());
				return;
			case ScriptPackage.TEST_STEP__OOM:
				setOOM(((Integer)newValue).intValue());
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
			case ScriptPackage.TEST_STEP__SECTION:
				setSection((Section)null);
				return;
			case ScriptPackage.TEST_STEP__INI_PERSISTANCE:
				setIniPersistance(INI_PERSISTANCE_EDEFAULT);
				return;
			case ScriptPackage.TEST_STEP__SECTION_PERSISTANCE:
				setSectionPersistance(SECTION_PERSISTANCE_EDEFAULT);
				return;
			case ScriptPackage.TEST_STEP__SERVER:
				setServer(SERVER_EDEFAULT);
				return;
			case ScriptPackage.TEST_STEP__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case ScriptPackage.TEST_STEP__METHOD:
				setMethod(METHOD_EDEFAULT);
				return;
			case ScriptPackage.TEST_STEP__ERROR:
				unsetError();
				return;
			case ScriptPackage.TEST_STEP__PANIC_STRING:
				unsetPanicString();
				return;
			case ScriptPackage.TEST_STEP__PANIC_CODE:
				unsetPanicCode();
				return;
			case ScriptPackage.TEST_STEP__RESULT:
				unsetResult();
				return;
			case ScriptPackage.TEST_STEP__HEAP:
				unsetHeap();
				return;
			case ScriptPackage.TEST_STEP__OOM:
				unsetOOM();
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
			case ScriptPackage.TEST_STEP__SECTION:
				return section != null;
			case ScriptPackage.TEST_STEP__INI_PERSISTANCE:
				return INI_PERSISTANCE_EDEFAULT == null ? iniPersistance != null : !INI_PERSISTANCE_EDEFAULT.equals(iniPersistance);
			case ScriptPackage.TEST_STEP__SECTION_PERSISTANCE:
				return SECTION_PERSISTANCE_EDEFAULT == null ? sectionPersistance != null : !SECTION_PERSISTANCE_EDEFAULT.equals(sectionPersistance);
			case ScriptPackage.TEST_STEP__SERVER:
				return SERVER_EDEFAULT == null ? server != null : !SERVER_EDEFAULT.equals(server);
			case ScriptPackage.TEST_STEP__TIMEOUT:
				return timeout != TIMEOUT_EDEFAULT;
			case ScriptPackage.TEST_STEP__METHOD:
				return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
			case ScriptPackage.TEST_STEP__ERROR:
				return isSetError();
			case ScriptPackage.TEST_STEP__PANIC_STRING:
				return isSetPanicString();
			case ScriptPackage.TEST_STEP__PANIC_CODE:
				return isSetPanicCode();
			case ScriptPackage.TEST_STEP__RESULT:
				return isSetResult();
			case ScriptPackage.TEST_STEP__HEAP:
				return isSetHeap();
			case ScriptPackage.TEST_STEP__OOM:
				return isSetOOM();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == SectionPesistance.class) {
			switch (derivedFeatureID) {
				case ScriptPackage.TEST_STEP__SECTION: return ScriptPackage.SECTION_PESISTANCE__SECTION;
				case ScriptPackage.TEST_STEP__INI_PERSISTANCE: return ScriptPackage.SECTION_PESISTANCE__INI_PERSISTANCE;
				case ScriptPackage.TEST_STEP__SECTION_PERSISTANCE: return ScriptPackage.SECTION_PESISTANCE__SECTION_PERSISTANCE;
				default: return -1;
			}
		}
		if (baseClass == Tef.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Leaf.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == SectionPesistance.class) {
			switch (baseFeatureID) {
				case ScriptPackage.SECTION_PESISTANCE__SECTION: return ScriptPackage.TEST_STEP__SECTION;
				case ScriptPackage.SECTION_PESISTANCE__INI_PERSISTANCE: return ScriptPackage.TEST_STEP__INI_PERSISTANCE;
				case ScriptPackage.SECTION_PESISTANCE__SECTION_PERSISTANCE: return ScriptPackage.TEST_STEP__SECTION_PERSISTANCE;
				default: return -1;
			}
		}
		if (baseClass == Tef.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Leaf.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (iniPersistance: ");
		result.append(iniPersistance);
		result.append(", sectionPersistance: ");
		result.append(sectionPersistance);
		result.append(", server: ");
		result.append(server);
		result.append(", timeout: ");
		result.append(timeout);
		result.append(", method: ");
		result.append(method);
		result.append(", Error: ");
		if (errorESet) result.append(error); else result.append("<unset>");
		result.append(", PanicString: ");
		if (panicStringESet) result.append(panicString); else result.append("<unset>");
		result.append(", PanicCode: ");
		if (panicCodeESet) result.append(panicCode); else result.append("<unset>");
		result.append(", Result: ");
		if (resultESet) result.append(result); else result.append("<unset>");
		result.append(", Heap: ");
		if (heapESet) result.append(heap); else result.append("<unset>");
		result.append(", OOM: ");
		if (oomESet) result.append(oom); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TestStepImpl