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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Build</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Runs a symbian build chain command, packages the targets in a SIS file (if PlatSec is on), transfers them to the device,
 * 				installs them, runs them and then deletes the files/SIS package.
 * 
 * 				This operation is done in two phases, the build phase and the run phase. During the build phase, "abld.bat" is called in the
 * 				group directory. This directory must contain a "bld.inf" file. Optionally a "bldmake.bat" and "clean" operations will also
 * 				be run, if specified in the TestDriver configuration. The targets are then calculated using "abld.bat -what" and copied to
 * 				the repository, and packaged into a SIS file, irrespective of PlatSec being on or off.
 * 
 * 				In the run phase the repositories or SIS packages are transfered to the device depending on whether PlatSec is on or off
 * 				respectively. If PlatSec is on then SIS package is installed at the beginning of the task and un-installed at the end of the
 * 				task. If PlatSec is off then the repository files are transfered at the beginning of the task and deleted at the end.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Build#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link com.symbian.driver.Build#isTestBuild <em>Test Build</em>}</li>
 *   <li>{@link com.symbian.driver.Build#getURI <em>URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getBuild()
 * @model extendedMetaData="name='build' kind='elementOnly'"
 * @generated
 */
public interface Build extends EObject {
	/**
	 * Returns the value of the '<em><b>Component Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 						The list of components specified by the MMP files. It is not necessary to include the MMP file extension when
	 * 						listing the components of a group directory.
	 * 					
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component Name</em>' attribute list.
	 * @see com.symbian.driver.DriverPackage#getBuild_ComponentName()
	 * @model unique="false" dataType="com.symbian.driver.ComponentNameType"
	 *        extendedMetaData="kind='element' name='componentName'"
	 * @generated
	 */
	EList<String> getComponentName();

	/**
	 * Returns the value of the '<em><b>Test Build</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					Adds the option "test" to all Symbian Build Chain commands. For example "abld.bat" is run as "abld.bat test a component".
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Test Build</em>' attribute.
	 * @see #isSetTestBuild()
	 * @see #unsetTestBuild()
	 * @see #setTestBuild(boolean)
	 * @see com.symbian.driver.DriverPackage#getBuild_TestBuild()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='testBuild'"
	 * @generated
	 */
	boolean isTestBuild();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Build#isTestBuild <em>Test Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Build</em>' attribute.
	 * @see #isSetTestBuild()
	 * @see #unsetTestBuild()
	 * @see #isTestBuild()
	 * @generated
	 */
	void setTestBuild(boolean value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.Build#isTestBuild <em>Test Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTestBuild()
	 * @see #isTestBuild()
	 * @see #setTestBuild(boolean)
	 * @generated
	 */
	void unsetTestBuild();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.Build#isTestBuild <em>Test Build</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Test Build</em>' attribute is set.
	 * @see #unsetTestBuild()
	 * @see #isTestBuild()
	 * @see #setTestBuild(boolean)
	 * @generated
	 */
	boolean isSetTestBuild();

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
	 * @see com.symbian.driver.DriverPackage#getBuild_URI()
	 * @model default="file://c:/" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='URI'"
	 * @generated
	 */
	String getURI();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Build#getURI <em>URI</em>}' attribute.
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
	 * Unsets the value of the '{@link com.symbian.driver.Build#getURI <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetURI()
	 * @see #getURI()
	 * @see #setURI(String)
	 * @generated
	 */
	void unsetURI();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.Build#getURI <em>URI</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>URI</em>' attribute is set.
	 * @see #unsetURI()
	 * @see #getURI()
	 * @see #setURI(String)
	 * @generated
	 */
	boolean isSetURI();

	/**
	 * @return The clone of the Build Object
	 * @generated NOT
	 */
	Object clone();

} // Build