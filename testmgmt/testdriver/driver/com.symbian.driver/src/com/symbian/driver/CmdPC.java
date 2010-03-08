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
 * A representation of the model object '<em><b>Cmd PC</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				A Microsoft Windows DOS command. Currently only programs that are able to run under MS-DOS are supported. All StdOut and
 * 				StdError will be parsed for "ERROR:" and "WARNING:" strings, and be recorded in the log files.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.CmdPC#getValue <em>Value</em>}</li>
 *   <li>{@link com.symbian.driver.CmdPC#getPhase <em>Phase</em>}</li>
 *   <li>{@link com.symbian.driver.CmdPC#isSync <em>Sync</em>}</li>
 *   <li>{@link com.symbian.driver.CmdPC#getURI <em>URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getCmdPC()
 * @model extendedMetaData="name='cmdPC' kind='simple'"
 * @generated
 */
public interface CmdPC extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.symbian.driver.DriverPackage#getCmdPC_Value()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdPC#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Phase</b></em>' attribute.
	 * The default value is <code>"build"</code>.
	 * The literals are from the enumeration {@link com.symbian.driver.Phase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phase</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phase</em>' attribute.
	 * @see com.symbian.driver.Phase
	 * @see #isSetPhase()
	 * @see #unsetPhase()
	 * @see #setPhase(Phase)
	 * @see com.symbian.driver.DriverPackage#getCmdPC_Phase()
	 * @model default="build" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='phase'"
	 * @generated
	 */
	Phase getPhase();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdPC#getPhase <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase</em>' attribute.
	 * @see com.symbian.driver.Phase
	 * @see #isSetPhase()
	 * @see #unsetPhase()
	 * @see #getPhase()
	 * @generated
	 */
	void setPhase(Phase value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.CmdPC#getPhase <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPhase()
	 * @see #getPhase()
	 * @see #setPhase(Phase)
	 * @generated
	 */
	void unsetPhase();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.CmdPC#getPhase <em>Phase</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Phase</em>' attribute is set.
	 * @see #unsetPhase()
	 * @see #getPhase()
	 * @see #setPhase(Phase)
	 * @generated
	 */
	boolean isSetPhase();

	/**
	 * Returns the value of the '<em><b>Sync</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					If sync is "false", then the command will run asyncronously to the current thread, and only be terminated when complete,
	 * 					if the timeout occurs or at the end of the current task (whichever is first). If sync is "true" then the current thread
	 * 					will wait till the command has finished or the timeout occurs. This tag is optional and the default is "true".
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sync</em>' attribute.
	 * @see #isSetSync()
	 * @see #unsetSync()
	 * @see #setSync(boolean)
	 * @see com.symbian.driver.DriverPackage#getCmdPC_Sync()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='sync'"
	 * @generated
	 */
	boolean isSync();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdPC#isSync <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sync</em>' attribute.
	 * @see #isSetSync()
	 * @see #unsetSync()
	 * @see #isSync()
	 * @generated
	 */
	void setSync(boolean value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.CmdPC#isSync <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSync()
	 * @see #isSync()
	 * @see #setSync(boolean)
	 * @generated
	 */
	void unsetSync();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.CmdPC#isSync <em>Sync</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sync</em>' attribute is set.
	 * @see #unsetSync()
	 * @see #isSync()
	 * @see #setSync(boolean)
	 * @generated
	 */
	boolean isSetSync();

	/**
	 * Returns the value of the '<em><b>URI</b></em>' attribute.
	 * The default value is <code>"file://c:/"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					This must be a fully qualified URI. TestDriver currently only supports running with the "file:/"; protocol, but may
	 * 					eventually be able to run over other protocols. The URI does support variables but not wildcards.
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>URI</em>' attribute.
	 * @see #isSetURI()
	 * @see #unsetURI()
	 * @see #setURI(String)
	 * @see com.symbian.driver.DriverPackage#getCmdPC_URI()
	 * @model default="file://c:/" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='URI'"
	 * @generated
	 */
	String getURI();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdPC#getURI <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>URI</em>' attribute.
	 * @see #isSetURI()
	 * @see #unsetURI()
	 * @see #getURI()
	 * @generated
	 */
	void setURI(String value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.CmdPC#getURI <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetURI()
	 * @see #getURI()
	 * @see #setURI(String)
	 * @generated
	 */
	void unsetURI();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.CmdPC#getURI <em>URI</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>URI</em>' attribute is set.
	 * @see #unsetURI()
	 * @see #getURI()
	 * @see #setURI(String)
	 * @generated
	 */
	boolean isSetURI();

} // CmdPC