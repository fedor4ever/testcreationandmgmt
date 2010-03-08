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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.DriverPackage
 * @generated
 */
public interface DriverFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DriverFactory eINSTANCE = com.symbian.driver.impl.DriverFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Build</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Build</em>'.
	 * @generated
	 */
	Build createBuild();

	/**
	 * Returns a new object of class '<em>Cmd PC</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cmd PC</em>'.
	 * @generated
	 */
	CmdPC createCmdPC();

	/**
	 * Returns a new object of class '<em>Cmd Symbian</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cmd Symbian</em>'.
	 * @generated
	 */
	CmdSymbian createCmdSymbian();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Driver</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Driver</em>'.
	 * @generated
	 */
	Driver createDriver();

	/**
	 * Returns a new object of class '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Info</em>'.
	 * @generated
	 */
	DriverInfo createDriverInfo();

	/**
	 * Returns a new object of class '<em>Execute On PC</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execute On PC</em>'.
	 * @generated
	 */
	ExecuteOnPC createExecuteOnPC();

	/**
	 * Returns a new object of class '<em>Execute On Symbian</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execute On Symbian</em>'.
	 * @generated
	 */
	ExecuteOnSymbian createExecuteOnSymbian();

	/**
	 * Returns a new object of class '<em>Flash ROM</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flash ROM</em>'.
	 * @generated
	 */
	FlashROM createFlashROM();

	/**
	 * Returns a new object of class '<em>Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Info</em>'.
	 * @generated
	 */
	Info createInfo();

	/**
	 * Returns a new object of class '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference</em>'.
	 * @generated
	 */
	Reference createReference();

	/**
	 * Returns a new object of class '<em>Retrieve From Symbian</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Retrieve From Symbian</em>'.
	 * @generated
	 */
	RetrieveFromSymbian createRetrieveFromSymbian();

	/**
	 * Returns a new object of class '<em>Rtest</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rtest</em>'.
	 * @generated
	 */
	Rtest createRtest();

	/**
	 * Returns a new object of class '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task</em>'.
	 * @generated
	 */
	Task createTask();

	/**
	 * Returns a new object of class '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case</em>'.
	 * @generated
	 */
	TestCase createTestCase();

	/**
	 * Returns a new object of class '<em>Test Cases List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Cases List</em>'.
	 * @generated
	 */
	TestCasesList createTestCasesList();

	/**
	 * Returns a new object of class '<em>Test Execute Script</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Execute Script</em>'.
	 * @generated
	 */
	TestExecuteScript createTestExecuteScript();

	/**
	 * Returns a new object of class '<em>Transfer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transfer</em>'.
	 * @generated
	 */
	Transfer createTransfer();

	/**
	 * Returns a new object of class '<em>Transfer To Symbian</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transfer To Symbian</em>'.
	 * @generated
	 */
	TransferToSymbian createTransferToSymbian();

	/**
	 * Returns a new object of class '<em>Start Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Trace</em>'.
	 * @generated
	 */
	StartTrace createStartTrace();

	/**
	 * Returns a new object of class '<em>Stop Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stop Trace</em>'.
	 * @generated
	 */
	StopTrace createStopTrace();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DriverPackage getDriverPackage();

} //DriverFactory
