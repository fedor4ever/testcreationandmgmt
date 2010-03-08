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


package com.symbian.tef.script;

import com.symbian.ini.Section;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section Pesistance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.tef.script.SectionPesistance#getSection <em>Section</em>}</li>
 *   <li>{@link com.symbian.tef.script.SectionPesistance#getIniPersistance <em>Ini Persistance</em>}</li>
 *   <li>{@link com.symbian.tef.script.SectionPesistance#getSectionPersistance <em>Section Persistance</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.tef.script.ScriptPackage#getSectionPesistance()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SectionPesistance extends EObject {
	/**
	 * Returns the value of the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Section</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Section</em>' reference.
	 * @see #setSection(Section)
	 * @see com.symbian.tef.script.ScriptPackage#getSectionPesistance_Section()
	 * @model required="true"
	 * @generated
	 */
	Section getSection();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.SectionPesistance#getSection <em>Section</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section</em>' reference.
	 * @see #getSection()
	 * @generated
	 */
	void setSection(Section value);

	/**
	 * Returns the value of the '<em><b>Ini Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ini Persistance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ini Persistance</em>' attribute.
	 * @see #setIniPersistance(String)
	 * @see com.symbian.tef.script.ScriptPackage#getSectionPesistance_IniPersistance()
	 * @model required="true"
	 * @generated
	 */
	String getIniPersistance();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.SectionPesistance#getIniPersistance <em>Ini Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ini Persistance</em>' attribute.
	 * @see #getIniPersistance()
	 * @generated
	 */
	void setIniPersistance(String value);

	/**
	 * Returns the value of the '<em><b>Section Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Section Persistance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Section Persistance</em>' attribute.
	 * @see #setSectionPersistance(String)
	 * @see com.symbian.tef.script.ScriptPackage#getSectionPesistance_SectionPersistance()
	 * @model required="true"
	 * @generated
	 */
	String getSectionPersistance();

	/**
	 * Sets the value of the '{@link com.symbian.tef.script.SectionPesistance#getSectionPersistance <em>Section Persistance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Persistance</em>' attribute.
	 * @see #getSectionPersistance()
	 * @generated
	 */
	void setSectionPersistance(String value);

} // SectionPesistance