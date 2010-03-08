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


package com.symbian.driver;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ...
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.StartTrace#getEnablePrimaryFilters <em>Enable Primary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.StartTrace#getEnableSecondaryFilters <em>Enable Secondary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.StartTrace#getDisablePrimaryFilters <em>Disable Primary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.StartTrace#getDisableSecondaryFilters <em>Disable Secondary Filters</em>}</li>
 *   <li>{@link com.symbian.driver.StartTrace#getConfigFilePath <em>Config File Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getStartTrace()
 * @model extendedMetaData="name='startTrace' kind='empty'"
 * @generated
 */
public interface StartTrace extends EObject {
	/**
	 * Returns the value of the '<em><b>Enable Primary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Primary Filters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Primary Filters</em>' attribute.
	 * @see #setEnablePrimaryFilters(String)
	 * @see com.symbian.driver.DriverPackage#getStartTrace_EnablePrimaryFilters()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='enablePrimaryFilters'"
	 * @generated
	 */
	String getEnablePrimaryFilters();

	/**
	 * Sets the value of the '{@link com.symbian.driver.StartTrace#getEnablePrimaryFilters <em>Enable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Primary Filters</em>' attribute.
	 * @see #getEnablePrimaryFilters()
	 * @generated
	 */
	void setEnablePrimaryFilters(String value);

	/**
	 * Returns the value of the '<em><b>Enable Secondary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Secondary Filters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Secondary Filters</em>' attribute.
	 * @see #setEnableSecondaryFilters(String)
	 * @see com.symbian.driver.DriverPackage#getStartTrace_EnableSecondaryFilters()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='enableSecondaryFilters'"
	 * @generated
	 */
	String getEnableSecondaryFilters();

	/**
	 * Sets the value of the '{@link com.symbian.driver.StartTrace#getEnableSecondaryFilters <em>Enable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Secondary Filters</em>' attribute.
	 * @see #getEnableSecondaryFilters()
	 * @generated
	 */
	void setEnableSecondaryFilters(String value);

	/**
	 * Returns the value of the '<em><b>Disable Primary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disable Primary Filters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disable Primary Filters</em>' attribute.
	 * @see #setDisablePrimaryFilters(String)
	 * @see com.symbian.driver.DriverPackage#getStartTrace_DisablePrimaryFilters()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='disablePrimaryFilters'"
	 * @generated
	 */
	String getDisablePrimaryFilters();

	/**
	 * Sets the value of the '{@link com.symbian.driver.StartTrace#getDisablePrimaryFilters <em>Disable Primary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disable Primary Filters</em>' attribute.
	 * @see #getDisablePrimaryFilters()
	 * @generated
	 */
	void setDisablePrimaryFilters(String value);

	/**
	 * Returns the value of the '<em><b>Disable Secondary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disable Secondary Filters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disable Secondary Filters</em>' attribute.
	 * @see #setDisableSecondaryFilters(String)
	 * @see com.symbian.driver.DriverPackage#getStartTrace_DisableSecondaryFilters()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='disableSecondaryFilters'"
	 * @generated
	 */
	String getDisableSecondaryFilters();

	/**
	 * Sets the value of the '{@link com.symbian.driver.StartTrace#getDisableSecondaryFilters <em>Disable Secondary Filters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disable Secondary Filters</em>' attribute.
	 * @see #getDisableSecondaryFilters()
	 * @generated
	 */
	void setDisableSecondaryFilters(String value);

	/**
	 * Returns the value of the '<em><b>Config File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config File Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config File Path</em>' attribute.
	 * @see #setConfigFilePath(String)
	 * @see com.symbian.driver.DriverPackage#getStartTrace_ConfigFilePath()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='configFilePath'"
	 * @generated
	 */
	String getConfigFilePath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.StartTrace#getConfigFilePath <em>Config File Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config File Path</em>' attribute.
	 * @see #getConfigFilePath()
	 * @generated
	 */
	void setConfigFilePath(String value);

} // StartTrace
