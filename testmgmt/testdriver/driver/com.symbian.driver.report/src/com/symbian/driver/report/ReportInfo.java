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


package com.symbian.driver.report;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.ReportInfo#getInfo <em>Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getReportInfo()
 * @model extendedMetaData="name='reportInfo' kind='elementOnly'"
 * @generated
 */
public interface ReportInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Info</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Info</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Info</em>' map.
	 * @see com.symbian.driver.report.ReportPackage#getReportInfo_Info()
	 * @model mapType="com.symbian.driver.report.Info" keyType="java.lang.String" valueType="java.lang.String"
	 *        extendedMetaData="kind='element' name='info'"
	 * @generated
	 */
	EMap getInfo();

} // ReportInfo