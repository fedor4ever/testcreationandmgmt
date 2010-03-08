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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.GenericReport#getLog <em>Log</em>}</li>
 *   <li>{@link com.symbian.driver.report.GenericReport#getTrace <em>Trace</em>}</li>
 *   <li>{@link com.symbian.driver.report.GenericReport#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getGenericReport()
 * @model extendedMetaData="name='genericReport' kind='elementOnly'"
 * @generated
 */
public interface GenericReport extends BaseReport {
	/**
	 * Returns the value of the '<em><b>Log</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log</em>' attribute.
	 * @see #setLog(String)
	 * @see com.symbian.driver.report.ReportPackage#getGenericReport_Log()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='log'"
	 * @generated
	 */
	String getLog();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.GenericReport#getLog <em>Log</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log</em>' attribute.
	 * @see #getLog()
	 * @generated
	 */
	void setLog(String value);

	/**
	 * Returns the value of the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace</em>' attribute.
	 * @see #setTrace(String)
	 * @see com.symbian.driver.report.ReportPackage#getGenericReport_Trace()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 * @generated
	 */
	String getTrace();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.GenericReport#getTrace <em>Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace</em>' attribute.
	 * @see #getTrace()
	 * @generated
	 */
	void setTrace(String value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' attribute.
	 * The default value is <code>"error"</code>.
	 * The literals are from the enumeration {@link com.symbian.driver.report.GenericResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' attribute.
	 * @see com.symbian.driver.report.GenericResult
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #setResult(GenericResult)
	 * @see com.symbian.driver.report.ReportPackage#getGenericReport_Result()
	 * @model default="error" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='result'"
	 * @generated
	 */
	GenericResult getResult();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.GenericReport#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' attribute.
	 * @see com.symbian.driver.report.GenericResult
	 * @see #isSetResult()
	 * @see #unsetResult()
	 * @see #getResult()
	 * @generated
	 */
	void setResult(GenericResult value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.GenericReport#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResult()
	 * @see #getResult()
	 * @see #setResult(GenericResult)
	 * @generated
	 */
	void unsetResult();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.GenericReport#getResult <em>Result</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Result</em>' attribute is set.
	 * @see #unsetResult()
	 * @see #getResult()
	 * @see #setResult(GenericResult)
	 * @generated
	 */
	boolean isSetResult();

} // GenericReport