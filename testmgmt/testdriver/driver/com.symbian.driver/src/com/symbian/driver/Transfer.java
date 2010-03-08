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
 * A representation of the model object '<em><b>Transfer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Transfers a file from the PC to the Symbian device or visa-versa. If used during "transferToSymbian" then wildcards and
 * 				variables are allowed. If used from "retrieveFromSymbian" then wildcards and variables are not permissible.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Transfer#isMove <em>Move</em>}</li>
 *   <li>{@link com.symbian.driver.Transfer#getPCPath <em>PC Path</em>}</li>
 *   <li>{@link com.symbian.driver.Transfer#getSymbianPath <em>Symbian Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getTransfer()
 * @model extendedMetaData="name='transfer' kind='empty'"
 * @generated
 */
public interface Transfer extends EObject {
	/**
	 * Returns the value of the '<em><b>Move</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					If "true" then a move will be executed, if "false" then a copy will executed on the file to be transfered.
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Move</em>' attribute.
	 * @see #isSetMove()
	 * @see #unsetMove()
	 * @see #setMove(boolean)
	 * @see com.symbian.driver.DriverPackage#getTransfer_Move()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='move'"
	 * @generated
	 */
	boolean isMove();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Transfer#isMove <em>Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Move</em>' attribute.
	 * @see #isSetMove()
	 * @see #unsetMove()
	 * @see #isMove()
	 * @generated
	 */
	void setMove(boolean value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.Transfer#isMove <em>Move</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMove()
	 * @see #isMove()
	 * @see #setMove(boolean)
	 * @generated
	 */
	void unsetMove();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.Transfer#isMove <em>Move</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Move</em>' attribute is set.
	 * @see #unsetMove()
	 * @see #isMove()
	 * @see #setMove(boolean)
	 * @generated
	 */
	boolean isSetMove();

	/**
	 * Returns the value of the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					A valid windows path. Depending if placed in "tranferToSymbian" or "retrieveFromSymbian" wildcards and variables will or
	 * 					will not be allowed respectively.
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>PC Path</em>' attribute.
	 * @see #setPCPath(String)
	 * @see com.symbian.driver.DriverPackage#getTransfer_PCPath()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='PCPath'"
	 * @generated
	 */
	String getPCPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Transfer#getPCPath <em>PC Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PC Path</em>' attribute.
	 * @see #getPCPath()
	 * @generated
	 */
	void setPCPath(String value);

	/**
	 * Returns the value of the '<em><b>Symbian Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A valid Symbian path. This must be an absolute path and must be valid.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Symbian Path</em>' attribute.
	 * @see #setSymbianPath(String)
	 * @see com.symbian.driver.DriverPackage#getTransfer_SymbianPath()
	 * @model unique="false" dataType="com.symbian.driver.SymbianPath" required="true"
	 *        extendedMetaData="kind='attribute' name='SymbianPath'"
	 * @generated
	 */
	String getSymbianPath();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Transfer#getSymbianPath <em>Symbian Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbian Path</em>' attribute.
	 * @see #getSymbianPath()
	 * @generated
	 */
	void setSymbianPath(String value);

} // Transfer