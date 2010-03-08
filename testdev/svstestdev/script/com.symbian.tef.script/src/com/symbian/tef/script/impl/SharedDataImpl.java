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

import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SectionPesistance;
import com.symbian.tef.script.SharedData;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shared Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.SharedDataImpl#getSection <em>Section</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.SharedDataImpl#getIniPersistance <em>Ini Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.SharedDataImpl#getSectionPersistance <em>Section Persistance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SharedDataImpl extends EObjectImpl implements SharedData {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SharedDataImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.SHARED_DATA;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScriptPackage.SHARED_DATA__SECTION, oldSection, section));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.SHARED_DATA__SECTION, oldSection, section));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.SHARED_DATA__INI_PERSISTANCE, oldIniPersistance, iniPersistance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.SHARED_DATA__SECTION_PERSISTANCE, oldSectionPersistance, sectionPersistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScriptPackage.SHARED_DATA__SECTION:
				if (resolve) return getSection();
				return basicGetSection();
			case ScriptPackage.SHARED_DATA__INI_PERSISTANCE:
				return getIniPersistance();
			case ScriptPackage.SHARED_DATA__SECTION_PERSISTANCE:
				return getSectionPersistance();
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
			case ScriptPackage.SHARED_DATA__SECTION:
				setSection((Section)newValue);
				return;
			case ScriptPackage.SHARED_DATA__INI_PERSISTANCE:
				setIniPersistance((String)newValue);
				return;
			case ScriptPackage.SHARED_DATA__SECTION_PERSISTANCE:
				setSectionPersistance((String)newValue);
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
			case ScriptPackage.SHARED_DATA__SECTION:
				setSection((Section)null);
				return;
			case ScriptPackage.SHARED_DATA__INI_PERSISTANCE:
				setIniPersistance(INI_PERSISTANCE_EDEFAULT);
				return;
			case ScriptPackage.SHARED_DATA__SECTION_PERSISTANCE:
				setSectionPersistance(SECTION_PERSISTANCE_EDEFAULT);
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
			case ScriptPackage.SHARED_DATA__SECTION:
				return section != null;
			case ScriptPackage.SHARED_DATA__INI_PERSISTANCE:
				return INI_PERSISTANCE_EDEFAULT == null ? iniPersistance != null : !INI_PERSISTANCE_EDEFAULT.equals(iniPersistance);
			case ScriptPackage.SHARED_DATA__SECTION_PERSISTANCE:
				return SECTION_PERSISTANCE_EDEFAULT == null ? sectionPersistance != null : !SECTION_PERSISTANCE_EDEFAULT.equals(sectionPersistance);
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
		result.append(" (iniPersistance: ");
		result.append(iniPersistance);
		result.append(", sectionPersistance: ");
		result.append(sectionPersistance);
		result.append(')');
		return result.toString();
	}

} //SharedDataImpl