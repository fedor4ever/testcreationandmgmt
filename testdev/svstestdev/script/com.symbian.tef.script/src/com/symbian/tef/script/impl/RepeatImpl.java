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

import com.symbian.tef.script.Container;
import com.symbian.tef.script.Repeat;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SectionPesistance;

import com.symbian.tef.script.Tef;

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
 * An implementation of the model object '<em><b>Repeat</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.tef.script.impl.RepeatImpl#getSection <em>Section</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RepeatImpl#getIniPersistance <em>Ini Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RepeatImpl#getSectionPersistance <em>Section Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RepeatImpl#getTef <em>Tef</em>}</li>
 *   <li>{@link com.symbian.tef.script.impl.RepeatImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepeatImpl extends EObjectImpl implements Repeat {
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
	 * The cached value of the '{@link #getTef() <em>Tef</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTef()
	 * @generated
	 * @ordered
	 */
	protected EList<Tef> tef;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepeatImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScriptPackage.Literals.REPEAT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tef> getTef() {
		if (tef == null) {
			tef = new EObjectContainmentEList<Tef>(Tef.class, this, ScriptPackage.REPEAT__TEF);
		}
		return tef;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScriptPackage.REPEAT__SECTION, oldSection, section));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.REPEAT__SECTION, oldSection, section));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.REPEAT__INI_PERSISTANCE, oldIniPersistance, iniPersistance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.REPEAT__SECTION_PERSISTANCE, oldSectionPersistance, sectionPersistance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScriptPackage.REPEAT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScriptPackage.REPEAT__TEF:
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
			case ScriptPackage.REPEAT__SECTION:
				if (resolve) return getSection();
				return basicGetSection();
			case ScriptPackage.REPEAT__INI_PERSISTANCE:
				return getIniPersistance();
			case ScriptPackage.REPEAT__SECTION_PERSISTANCE:
				return getSectionPersistance();
			case ScriptPackage.REPEAT__TEF:
				return getTef();
			case ScriptPackage.REPEAT__NAME:
				return getName();
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
			case ScriptPackage.REPEAT__SECTION:
				setSection((Section)newValue);
				return;
			case ScriptPackage.REPEAT__INI_PERSISTANCE:
				setIniPersistance((String)newValue);
				return;
			case ScriptPackage.REPEAT__SECTION_PERSISTANCE:
				setSectionPersistance((String)newValue);
				return;
			case ScriptPackage.REPEAT__TEF:
				getTef().clear();
				getTef().addAll((Collection<? extends Tef>)newValue);
				return;
			case ScriptPackage.REPEAT__NAME:
				setName((String)newValue);
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
			case ScriptPackage.REPEAT__SECTION:
				setSection((Section)null);
				return;
			case ScriptPackage.REPEAT__INI_PERSISTANCE:
				setIniPersistance(INI_PERSISTANCE_EDEFAULT);
				return;
			case ScriptPackage.REPEAT__SECTION_PERSISTANCE:
				setSectionPersistance(SECTION_PERSISTANCE_EDEFAULT);
				return;
			case ScriptPackage.REPEAT__TEF:
				getTef().clear();
				return;
			case ScriptPackage.REPEAT__NAME:
				setName(NAME_EDEFAULT);
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
			case ScriptPackage.REPEAT__SECTION:
				return section != null;
			case ScriptPackage.REPEAT__INI_PERSISTANCE:
				return INI_PERSISTANCE_EDEFAULT == null ? iniPersistance != null : !INI_PERSISTANCE_EDEFAULT.equals(iniPersistance);
			case ScriptPackage.REPEAT__SECTION_PERSISTANCE:
				return SECTION_PERSISTANCE_EDEFAULT == null ? sectionPersistance != null : !SECTION_PERSISTANCE_EDEFAULT.equals(sectionPersistance);
			case ScriptPackage.REPEAT__TEF:
				return tef != null && !tef.isEmpty();
			case ScriptPackage.REPEAT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		if (baseClass == Tef.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Container.class) {
			switch (derivedFeatureID) {
				case ScriptPackage.REPEAT__TEF: return ScriptPackage.CONTAINER__TEF;
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
		if (baseClass == Tef.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Container.class) {
			switch (baseFeatureID) {
				case ScriptPackage.CONTAINER__TEF: return ScriptPackage.REPEAT__TEF;
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
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //RepeatImpl