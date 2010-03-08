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
 * A representation of the model object '<em><b>Tef Test Run Ws Program Summary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getTestCase <em>Test Case</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort <em>Abort</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getCount <em>Count</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getFail <em>Fail</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive <em>Inconclusive</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic <em>Panic</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPass <em>Pass</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted <em>Unexecuted</em>}</li>
 *   <li>{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown <em>Unknown</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary()
 * @model extendedMetaData="name='tefTestRunWsProgramSummary' kind='elementOnly'"
 * @generated
 */
public interface TefTestRunWsProgramSummary extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Case</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.report.TefTestRunWsProgram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case</em>' containment reference list.
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_TestCase()
	 * @model type="com.symbian.driver.report.TefTestRunWsProgram" containment="true"
	 *        extendedMetaData="kind='element' name='testCase'"
	 * @generated
	 */
	EList getTestCase();

	/**
	 * Returns the value of the '<em><b>Abort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abort</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abort</em>' attribute.
	 * @see #isSetAbort()
	 * @see #unsetAbort()
	 * @see #setAbort(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Abort()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='abort'"
	 * @generated
	 */
	int getAbort();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort <em>Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abort</em>' attribute.
	 * @see #isSetAbort()
	 * @see #unsetAbort()
	 * @see #getAbort()
	 * @generated
	 */
	void setAbort(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort <em>Abort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAbort()
	 * @see #getAbort()
	 * @see #setAbort(int)
	 * @generated
	 */
	void unsetAbort();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getAbort <em>Abort</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Abort</em>' attribute is set.
	 * @see #unsetAbort()
	 * @see #getAbort()
	 * @see #setAbort(int)
	 * @generated
	 */
	boolean isSetAbort();

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #isSetCount()
	 * @see #unsetCount()
	 * @see #setCount(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Count()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='count'"
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #isSetCount()
	 * @see #unsetCount()
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCount()
	 * @see #getCount()
	 * @see #setCount(int)
	 * @generated
	 */
	void unsetCount();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getCount <em>Count</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Count</em>' attribute is set.
	 * @see #unsetCount()
	 * @see #getCount()
	 * @see #setCount(int)
	 * @generated
	 */
	boolean isSetCount();

	/**
	 * Returns the value of the '<em><b>Fail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail</em>' attribute.
	 * @see #isSetFail()
	 * @see #unsetFail()
	 * @see #setFail(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Fail()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='fail'"
	 * @generated
	 */
	int getFail();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getFail <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail</em>' attribute.
	 * @see #isSetFail()
	 * @see #unsetFail()
	 * @see #getFail()
	 * @generated
	 */
	void setFail(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getFail <em>Fail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFail()
	 * @see #getFail()
	 * @see #setFail(int)
	 * @generated
	 */
	void unsetFail();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getFail <em>Fail</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fail</em>' attribute is set.
	 * @see #unsetFail()
	 * @see #getFail()
	 * @see #setFail(int)
	 * @generated
	 */
	boolean isSetFail();

	/**
	 * Returns the value of the '<em><b>Inconclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inconclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inconclusive</em>' attribute.
	 * @see #isSetInconclusive()
	 * @see #unsetInconclusive()
	 * @see #setInconclusive(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Inconclusive()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='inconclusive'"
	 * @generated
	 */
	int getInconclusive();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inconclusive</em>' attribute.
	 * @see #isSetInconclusive()
	 * @see #unsetInconclusive()
	 * @see #getInconclusive()
	 * @generated
	 */
	void setInconclusive(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive <em>Inconclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInconclusive()
	 * @see #getInconclusive()
	 * @see #setInconclusive(int)
	 * @generated
	 */
	void unsetInconclusive();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getInconclusive <em>Inconclusive</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Inconclusive</em>' attribute is set.
	 * @see #unsetInconclusive()
	 * @see #getInconclusive()
	 * @see #setInconclusive(int)
	 * @generated
	 */
	boolean isSetInconclusive();

	/**
	 * Returns the value of the '<em><b>Panic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panic</em>' attribute.
	 * @see #isSetPanic()
	 * @see #unsetPanic()
	 * @see #setPanic(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Panic()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='panic'"
	 * @generated
	 */
	int getPanic();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic <em>Panic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panic</em>' attribute.
	 * @see #isSetPanic()
	 * @see #unsetPanic()
	 * @see #getPanic()
	 * @generated
	 */
	void setPanic(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic <em>Panic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPanic()
	 * @see #getPanic()
	 * @see #setPanic(int)
	 * @generated
	 */
	void unsetPanic();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPanic <em>Panic</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Panic</em>' attribute is set.
	 * @see #unsetPanic()
	 * @see #getPanic()
	 * @see #setPanic(int)
	 * @generated
	 */
	boolean isSetPanic();

	/**
	 * Returns the value of the '<em><b>Pass</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pass</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pass</em>' attribute.
	 * @see #isSetPass()
	 * @see #unsetPass()
	 * @see #setPass(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Pass()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='pass'"
	 * @generated
	 */
	int getPass();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPass <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pass</em>' attribute.
	 * @see #isSetPass()
	 * @see #unsetPass()
	 * @see #getPass()
	 * @generated
	 */
	void setPass(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPass <em>Pass</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPass()
	 * @see #getPass()
	 * @see #setPass(int)
	 * @generated
	 */
	void unsetPass();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getPass <em>Pass</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Pass</em>' attribute is set.
	 * @see #unsetPass()
	 * @see #getPass()
	 * @see #setPass(int)
	 * @generated
	 */
	boolean isSetPass();

	/**
	 * Returns the value of the '<em><b>Unexecuted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unexecuted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unexecuted</em>' attribute.
	 * @see #isSetUnexecuted()
	 * @see #unsetUnexecuted()
	 * @see #setUnexecuted(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Unexecuted()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='unexecuted'"
	 * @generated
	 */
	int getUnexecuted();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted <em>Unexecuted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unexecuted</em>' attribute.
	 * @see #isSetUnexecuted()
	 * @see #unsetUnexecuted()
	 * @see #getUnexecuted()
	 * @generated
	 */
	void setUnexecuted(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted <em>Unexecuted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnexecuted()
	 * @see #getUnexecuted()
	 * @see #setUnexecuted(int)
	 * @generated
	 */
	void unsetUnexecuted();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnexecuted <em>Unexecuted</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unexecuted</em>' attribute is set.
	 * @see #unsetUnexecuted()
	 * @see #getUnexecuted()
	 * @see #setUnexecuted(int)
	 * @generated
	 */
	boolean isSetUnexecuted();

	/**
	 * Returns the value of the '<em><b>Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unknown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unknown</em>' attribute.
	 * @see #isSetUnknown()
	 * @see #unsetUnknown()
	 * @see #setUnknown(int)
	 * @see com.symbian.driver.report.ReportPackage#getTefTestRunWsProgramSummary_Unknown()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='attribute' name='unknown'"
	 * @generated
	 */
	int getUnknown();

	/**
	 * Sets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown <em>Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unknown</em>' attribute.
	 * @see #isSetUnknown()
	 * @see #unsetUnknown()
	 * @see #getUnknown()
	 * @generated
	 */
	void setUnknown(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown <em>Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnknown()
	 * @see #getUnknown()
	 * @see #setUnknown(int)
	 * @generated
	 */
	void unsetUnknown();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.report.TefTestRunWsProgramSummary#getUnknown <em>Unknown</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unknown</em>' attribute is set.
	 * @see #unsetUnknown()
	 * @see #getUnknown()
	 * @see #setUnknown(int)
	 * @generated
	 */
	boolean isSetUnknown();

} // TefTestRunWsProgramSummary