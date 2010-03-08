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

import java.io.IOException;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				A "task" is a set of operations grouped together logically. These operations fall in two categories, the operations that run
 * 				during the build phase of TestDriver and the operations that run during the run phase of TestDriver. All operations in a
 * 				task are executed in the order that they appear in the XML file.
 * 
 * 				The build phase includes: (1) executing all the operations in "executeOnPC"; and (2) creating the repositories/SIS files for
 * 				the "transferToSymbian" operation.
 * 
 * 				The run phase includes: (1) executing all the operations in "executeOnSymbian", (2) transferring the created repositories/SIS
 * 				files in build phase to the Symbian device (hardware or emulator) for the "transferToSymbian" operation and any other
 * 				operations and lastly (3) retrieving all the files from the Symbian device in the "retrieveFromSymbain" operation.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.driver.Task#getGroup <em>Group</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getExecuteOnPC <em>Execute On PC</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getTransferToSymbian <em>Transfer To Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getExecuteOnSymbian <em>Execute On Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getRetrieveFromSymbian <em>Retrieve From Symbian</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getReference <em>Reference</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getTask <em>Task</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getFlashrom <em>Flashrom</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getStartTrace <em>Start Trace</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getStopTrace <em>Stop Trace</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getName <em>Name</em>}</li>
 *   <li>{@link com.symbian.driver.Task#isPreRebootDevice <em>Pre Reboot Device</em>}</li>
 *   <li>{@link com.symbian.driver.Task#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.driver.DriverPackage#getTask()
 * @model extendedMetaData="name='task' kind='elementOnly'"
 * @generated
 */
public interface Task extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see com.symbian.driver.DriverPackage#getTask_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Execute On PC</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.ExecuteOnPC}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execute On PC</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execute On PC</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_ExecuteOnPC()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='executeOnPC' group='#group:0'"
	 * @generated
	 */
	EList<ExecuteOnPC> getExecuteOnPC();

	/**
	 * Returns the value of the '<em><b>Transfer To Symbian</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.TransferToSymbian}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer To Symbian</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer To Symbian</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_TransferToSymbian()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transferToSymbian' group='#group:0'"
	 * @generated
	 */
	EList<TransferToSymbian> getTransferToSymbian();

	/**
	 * Returns the value of the '<em><b>Execute On Symbian</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.ExecuteOnSymbian}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execute On Symbian</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execute On Symbian</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_ExecuteOnSymbian()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='executeOnSymbian' group='#group:0'"
	 * @generated
	 */
	EList<ExecuteOnSymbian> getExecuteOnSymbian();

	/**
	 * Returns the value of the '<em><b>Retrieve From Symbian</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.RetrieveFromSymbian}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Retrieve From Symbian</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retrieve From Symbian</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_RetrieveFromSymbian()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='retrieveFromSymbian' group='#group:0'"
	 * @generated
	 */
	EList<RetrieveFromSymbian> getRetrieveFromSymbian();

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Reference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_Reference()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='reference' group='#group:0'"
	 * @generated
	 */
	EList<Reference> getReference();

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.Task}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_Task()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task' group='#group:0'"
	 * @generated
	 */
	EList<Task> getTask();

	/**
	 * Returns the value of the '<em><b>Flashrom</b></em>' containment reference list.
	 * The list contents are of type {@link com.symbian.driver.FlashROM}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flashrom</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flashrom</em>' containment reference list.
	 * @see com.symbian.driver.DriverPackage#getTask_Flashrom()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flashrom' group='#group:0'"
	 * @generated
	 */
	EList<FlashROM> getFlashrom();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A unique name identifying the task. There cannot be any other task with the same name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.symbian.driver.DriverPackage#getTask_Name()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Task#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Pre Reboot Device</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Reboot Device</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Reboot Device</em>' attribute.
	 * @see #isSetPreRebootDevice()
	 * @see #unsetPreRebootDevice()
	 * @see #setPreRebootDevice(boolean)
	 * @see com.symbian.driver.DriverPackage#getTask_PreRebootDevice()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='preRebootDevice'"
	 * @generated
	 */
	boolean isPreRebootDevice();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Task#isPreRebootDevice <em>Pre Reboot Device</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Reboot Device</em>' attribute.
	 * @see #isSetPreRebootDevice()
	 * @see #unsetPreRebootDevice()
	 * @see #isPreRebootDevice()
	 * @generated
	 */
	void setPreRebootDevice(boolean value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.Task#isPreRebootDevice <em>Pre Reboot Device</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPreRebootDevice()
	 * @see #isPreRebootDevice()
	 * @see #setPreRebootDevice(boolean)
	 * @generated
	 */
	void unsetPreRebootDevice();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.Task#isPreRebootDevice <em>Pre Reboot Device</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Pre Reboot Device</em>' attribute is set.
	 * @see #unsetPreRebootDevice()
	 * @see #isPreRebootDevice()
	 * @see #setPreRebootDevice(boolean)
	 * @generated
	 */
	boolean isSetPreRebootDevice();

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 					Expressed in the number of seconds. Any number less than or equal to 0 means no timeout. Please note that when executing asynchronous tasks this number should be greater than 0.
	 * 				
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #isSetTimeout()
	 * @see #unsetTimeout()
	 * @see #setTimeout(int)
	 * @see com.symbian.driver.DriverPackage#getTask_Timeout()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="kind='attribute' name='timeout'"
	 * @generated
	 */
	int getTimeout();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Task#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #isSetTimeout()
	 * @see #unsetTimeout()
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(int value);

	/**
	 * Unsets the value of the '{@link com.symbian.driver.Task#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTimeout()
	 * @see #getTimeout()
	 * @see #setTimeout(int)
	 * @generated
	 */
	void unsetTimeout();

	/**
	 * Returns whether the value of the '{@link com.symbian.driver.Task#getTimeout <em>Timeout</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Timeout</em>' attribute is set.
	 * @see #unsetTimeout()
	 * @see #getTimeout()
	 * @see #setTimeout(int)
	 * @generated
	 */
	boolean isSetTimeout();

	/**
	 * Returns the value of the '<em><b>Start Trace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Trace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Trace</em>' containment reference.
	 * @see #setStartTrace(StartTrace)
	 * @see com.symbian.driver.DriverPackage#getTask_StartTrace()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='startTrace' group='#group:0'"
	 * @generated
	 */
	StartTrace getStartTrace();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Task#getStartTrace <em>Start Trace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Trace</em>' containment reference.
	 * @see #getStartTrace()
	 * @generated
	 */
	void setStartTrace(StartTrace value);

	/**
	 * Returns the value of the '<em><b>Stop Trace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stop Trace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stop Trace</em>' containment reference.
	 * @see #setStopTrace(StopTrace)
	 * @see com.symbian.driver.DriverPackage#getTask_StopTrace()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='stopTrace' group='#group:0'"
	 * @generated
	 */
	StopTrace getStopTrace();

	/**
	 * Sets the value of the '{@link com.symbian.driver.Task#getStopTrace <em>Stop Trace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Trace</em>' containment reference.
	 * @see #getStopTrace()
	 * @generated
	 */
	void setStopTrace(StopTrace value);

	/**
	 * Returns the level of the current task starting at 0.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the level of the current task.
	 * @generated NOT
	 */
	int getLevel();
	
	/**
	 * Returns the address of the current task in the EMF tree.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the level of the current task.
	 * @generated NOT
	 */
	String getAddress();
	
	void setTransferSet(Object aTransferSet) throws IOException;

	Object getTransferSet();

	Stack<Object> getExecuteSet();

} // Task