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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.BaseReport#getExecptionReport <em>Execption Report</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#getDuration <em>Duration</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#getTask <em>Task</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#isTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#isCrash <em>Crash</em>}</li>
 *   <li>{@link com.symbian.driver.report.BaseReport#getCoredump <em>Coredump</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getBaseReport()
 * @model extendedMetaData="name='baseReport' kind='elementOnly'"
 * @generated
 */
public interface BaseReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Execption Report</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.report.ExceptionReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execption Report</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execption Report</em>' containment reference list.
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_ExecptionReport()
	 * @model type="com.symbian.driver.report.ExceptionReport" containment="true"
	 *        extendedMetaData="kind='element' name='execptionReport'"
	 * @generated
	 */
	EList getExecptionReport();

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(String)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Duration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='duration'"
	 * @generated
	 */
	String getDuration();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Name()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' attribute.
	 * @see #setTask(String)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Task()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='task'"
	 * @generated
	 */
	String getTask();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#getTask <em>Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' attribute.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(String value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #isSetTimeout()
	 * @see #unsetTimeout()
	 * @see #setTimeout(boolean)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Timeout()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='timeout'"
	 * @generated
	 */
	boolean isTimeout();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#isTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #isSetTimeout()
	 * @see #unsetTimeout()
	 * @see #isTimeout()
	 * @generated
	 */
	void setTimeout(boolean value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.BaseReport#isTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTimeout()
	 * @see #isTimeout()
	 * @see #setTimeout(boolean)
	 * @generated
	 */
	void unsetTimeout();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.BaseReport#isTimeout <em>Timeout</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Timeout</em>' attribute is set.
	 * @see #unsetTimeout()
	 * @see #isTimeout()
	 * @see #setTimeout(boolean)
	 * @generated
	 */
	boolean isSetTimeout();

	/**
	 * Returns the value of the '<em><b>Crash</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Crash</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Crash</em>' attribute.
	 * @see #setCrash(boolean)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Crash()
	 * @model default="false" unique="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isCrash();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#isCrash <em>Crash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Crash</em>' attribute.
	 * @see #isCrash()
	 * @generated
	 */
	void setCrash(boolean value);

	/**
	 * Returns the value of the '<em><b>Coredump</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coredump</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coredump</em>' attribute.
	 * @see #setCoredump(String)
	 * @see com.symbian.driver.report.ReportPackage#getBaseReport_Coredump()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 * @generated
	 */
	String getCoredump();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.BaseReport#getCoredump <em>Coredump</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coredump</em>' attribute.
	 * @see #getCoredump()
	 * @generated
	 */
	void setCoredump(String value);

} // BaseReport