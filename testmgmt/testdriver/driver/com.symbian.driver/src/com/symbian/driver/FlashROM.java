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
 * A representation of the model object '<em><b>Flash ROM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Flashes the Symbian OS device with a new ROM specified by the PC Path.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.FlashROM#getPCPath <em>PC Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getFlashROM()
 * @model extendedMetaData="name='flashROM' kind='empty'"
 * @generated
 */
public interface FlashROM extends EObject {
	/**
	 * Returns the value of the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PC Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PC Path</em>' attribute.
	 * @see #setPCPath(String)
	 * @see com.symbian.driver.DriverPackage#getFlashROM_PCPath()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='PCPath'"
	 * @generated
	 */
	String getPCPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.FlashROM#getPCPath <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PC Path</em>' attribute.
	 * @see #getPCPath()
	 * @generated
	 */
	void setPCPath(String value);

} // FlashROM
