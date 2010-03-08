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
 * A representation of the model object '<em><b>Cmd Symbian</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Symbian STAT compatible commands. If the attribute "output" is specified, then the results will be printed at this location,
 * 				otherwise to STDOUT and/or to the log files.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.CmdSymbian#getArgument <em>Argument</em>}</li>
 *   <li>{@link com.symbian.driver.CmdSymbian#getOutput <em>Output</em>}</li>
 *   <li>{@link com.symbian.driver.CmdSymbian#getStatCommand <em>Stat Command</em>}</li>
 *   <li>{@link com.symbian.driver.CmdSymbian#isSync <em>Sync</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getCmdSymbian()
 * @model extendedMetaData="name='cmdSymbian' kind='elementOnly'"
 * @generated
 */
public interface CmdSymbian extends EObject {
	/**
	 * Returns the value of the '<em><b>Argument</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 						The list of arguments depending on the "statCommand" used. Please ensure that you use the correct arguments. Details
	 * 						can be found in the STAT documentation.
	 * 					
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Argument</em>' attribute list.
	 * @see com.symbian.driver.DriverPackage#getCmdSymbian_Argument()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="2"
	 *        extendedMetaData="kind='element' name='argument'"
	 * @generated
	 */
	EList<String> getArgument();

	/**
	 * Returns the value of the '<em><b>Output</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The location of the file to print out all the output.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Output</em>' attribute.
	 * @see #setOutput(String)
	 * @see com.symbian.driver.DriverPackage#getCmdSymbian_Output()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='output'"
	 * @generated
	 */
	String getOutput();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdSymbian#getOutput <em>Output</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output</em>' attribute.
	 * @see #getOutput()
	 * @generated
	 */
	void setOutput(String value);

	/**
	 * Returns the value of the '<em><b>Stat Command</b></em>' attribute.
	 * The default value is <code>"createFolder"</code>.
	 * The literals are from the enumeration {@link com.symbian.driver.StatCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					The STAT command to run. For more details on these commands please see the STAT documentation.
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stat Command</em>' attribute.
	 * @see com.symbian.driver.StatCommand
	 * @see #isSetStatCommand()
	 * @see #unsetStatCommand()
	 * @see #setStatCommand(StatCommand)
	 * @see com.symbian.driver.DriverPackage#getCmdSymbian_StatCommand()
	 * @model default="createFolder" unique="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='statCommand'"
	 * @generated
	 */
	StatCommand getStatCommand();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdSymbian#getStatCommand <em>Stat Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stat Command</em>' attribute.
	 * @see com.symbian.driver.StatCommand
	 * @see #isSetStatCommand()
	 * @see #unsetStatCommand()
	 * @see #getStatCommand()
	 * @generated
	 */
	void setStatCommand(StatCommand value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.CmdSymbian#getStatCommand <em>Stat Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStatCommand()
	 * @see #getStatCommand()
	 * @see #setStatCommand(StatCommand)
	 * @generated
	 */
	void unsetStatCommand();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.CmdSymbian#getStatCommand <em>Stat Command</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Stat Command</em>' attribute is set.
	 * @see #unsetStatCommand()
	 * @see #getStatCommand()
	 * @see #setStatCommand(StatCommand)
	 * @generated
	 */
	boolean isSetStatCommand();

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
	 * @see com.symbian.driver.DriverPackage#getCmdSymbian_Sync()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='sync'"
	 * @generated
	 */
	boolean isSync();

	/**
	 * Sets the value of the '{@link com.symbian.driver.CmdSymbian#isSync <em>Sync</em>}' attribute.
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
	 * Unsets the value of the '{@link com.symbian.driver.CmdSymbian#isSync <em>Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSync()
	 * @see #isSync()
	 * @see #setSync(boolean)
	 * @generated
	 */
	void unsetSync();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.CmdSymbian#isSync <em>Sync</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Sync</em>' attribute is set.
	 * @see #unsetSync()
	 * @see #isSync()
	 * @see #setSync(boolean)
	 * @generated
	 */
	boolean isSetSync();

} // CmdSymbian