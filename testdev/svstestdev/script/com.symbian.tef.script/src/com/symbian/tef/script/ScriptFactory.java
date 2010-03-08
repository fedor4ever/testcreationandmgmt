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


package com.symbian.tef.script;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.tef.script.ScriptPackage
 * @generated
 */
public interface ScriptFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScriptFactory eINSTANCE = com.symbian.tef.script.impl.ScriptFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Tef Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Model</em>'.
	 * @generated
	 */
	TefModel createTefModel();

	/**
	 * Returns a new object of class '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case</em>'.
	 * @generated
	 */
	TestCase createTestCase();

	/**
	 * Returns a new object of class '<em>Prefix</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Prefix</em>'.
	 * @generated
	 */
	Prefix createPrefix();

	/**
	 * Returns a new object of class '<em>Repeat</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repeat</em>'.
	 * @generated
	 */
	Repeat createRepeat();

	/**
	 * Returns a new object of class '<em>Test Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Step</em>'.
	 * @generated
	 */
	TestStep createTestStep();

	/**
	 * Returns a new object of class '<em>Print</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Print</em>'.
	 * @generated
	 */
	Print createPrint();

	/**
	 * Returns a new object of class '<em>Load Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load Suite</em>'.
	 * @generated
	 */
	LoadSuite createLoadSuite();

	/**
	 * Returns a new object of class '<em>Load Server</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Load Server</em>'.
	 * @generated
	 */
	LoadServer createLoadServer();

	/**
	 * Returns a new object of class '<em>Run Utils</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Utils</em>'.
	 * @generated
	 */
	RunUtils createRunUtils();

	/**
	 * Returns a new object of class '<em>Run Program</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Program</em>'.
	 * @generated
	 */
	RunProgram createRunProgram();

	/**
	 * Returns a new object of class '<em>Run Script</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Script</em>'.
	 * @generated
	 */
	RunScript createRunScript();

	/**
	 * Returns a new object of class '<em>Pause</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pause</em>'.
	 * @generated
	 */
	Pause createPause();

	/**
	 * Returns a new object of class '<em>Delay</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delay</em>'.
	 * @generated
	 */
	Delay createDelay();

	/**
	 * Returns a new object of class '<em>Consecutive</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Consecutive</em>'.
	 * @generated
	 */
	Consecutive createConsecutive();

	/**
	 * Returns a new object of class '<em>Concurrent</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concurrent</em>'.
	 * @generated
	 */
	Concurrent createConcurrent();

	/**
	 * Returns a new object of class '<em>Shared Data</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shared Data</em>'.
	 * @generated
	 */
	SharedData createSharedData();

	/**
	 * Returns a new object of class '<em>Tef Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tef Comment</em>'.
	 * @generated
	 */
	TefComment createTefComment();

	/**
	 * Returns a new object of class '<em>Test Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Block</em>'.
	 * @generated
	 */
	TestBlock createTestBlock();

	/**
	 * Returns a new object of class '<em>Create Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Object</em>'.
	 * @generated
	 */
	CreateObject createCreateObject();

	/**
	 * Returns a new object of class '<em>Restore Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Restore Object</em>'.
	 * @generated
	 */
	RestoreObject createRestoreObject();

	/**
	 * Returns a new object of class '<em>Command</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Command</em>'.
	 * @generated
	 */
	Command createCommand();

	/**
	 * Returns a new object of class '<em>Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Store</em>'.
	 * @generated
	 */
	Store createStore();

	/**
	 * Returns a new object of class '<em>Outstanding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outstanding</em>'.
	 * @generated
	 */
	Outstanding createOutstanding();

	/**
	 * Returns a new object of class '<em>Async Delay</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Async Delay</em>'.
	 * @generated
	 */
	AsyncDelay createAsyncDelay();

	/**
	 * Returns a new object of class '<em>Shared Active Scheduler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shared Active Scheduler</em>'.
	 * @generated
	 */
	SharedActiveScheduler createSharedActiveScheduler();

	/**
	 * Returns a new object of class '<em>Store Active Scheduler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Store Active Scheduler</em>'.
	 * @generated
	 */
	StoreActiveScheduler createStoreActiveScheduler();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ScriptPackage getScriptPackage();

} //ScriptFactory
