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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.symbian.driver.DriverFactory
 * @model kind="package"
 * @generated
 */
public interface DriverPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "driver";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.symbian.com/TestDriver";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DriverPackage eINSTANCE = com.symbian.driver.impl.DriverPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.BuildImpl <em>Build</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.BuildImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getBuild()
	 * @generated
	 */
	int BUILD = 0;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD__COMPONENT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Test Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD__TEST_BUILD = 1;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD__URI = 2;

	/**
	 * The number of structural features of the '<em>Build</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.CmdPCImpl <em>Cmd PC</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.CmdPCImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getCmdPC()
	 * @generated
	 */
	int CMD_PC = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_PC__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_PC__PHASE = 1;

	/**
	 * The feature id for the '<em><b>Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_PC__SYNC = 2;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_PC__URI = 3;

	/**
	 * The number of structural features of the '<em>Cmd PC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_PC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.CmdSymbianImpl <em>Cmd Symbian</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.CmdSymbianImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getCmdSymbian()
	 * @generated
	 */
	int CMD_SYMBIAN = 2;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_SYMBIAN__ARGUMENT = 0;

	/**
	 * The feature id for the '<em><b>Output</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_SYMBIAN__OUTPUT = 1;

	/**
	 * The feature id for the '<em><b>Stat Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_SYMBIAN__STAT_COMMAND = 2;

	/**
	 * The feature id for the '<em><b>Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_SYMBIAN__SYNC = 3;

	/**
	 * The number of structural features of the '<em>Cmd Symbian</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMD_SYMBIAN_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.DocumentRootImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 3;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Driver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DRIVER = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.DriverImpl <em>Driver</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.DriverImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getDriver()
	 * @generated
	 */
	int DRIVER = 4;

	/**
	 * The feature id for the '<em><b>Driver Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__DRIVER_INFO = 0;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER__TASK = 1;

	/**
	 * The number of structural features of the '<em>Driver</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.DriverInfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.DriverInfoImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getDriverInfo()
	 * @generated
	 */
	int DRIVER_INFO = 5;

	/**
	 * The feature id for the '<em><b>Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER_INFO__INFO = 0;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVER_INFO_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.ExecuteOnPCImpl <em>Execute On PC</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.ExecuteOnPCImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getExecuteOnPC()
	 * @generated
	 */
	int EXECUTE_ON_PC = 6;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_PC__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Cmd</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_PC__CMD = 1;

	/**
	 * The feature id for the '<em><b>Build</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_PC__BUILD = 2;

	/**
	 * The number of structural features of the '<em>Execute On PC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_PC_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.ExecuteOnSymbianImpl <em>Execute On Symbian</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.ExecuteOnSymbianImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getExecuteOnSymbian()
	 * @generated
	 */
	int EXECUTE_ON_SYMBIAN = 7;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_SYMBIAN__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Cmd</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_SYMBIAN__CMD = 1;

	/**
	 * The feature id for the '<em><b>Test Execute Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Rtest</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_SYMBIAN__RTEST = 3;

	/**
	 * The number of structural features of the '<em>Execute On Symbian</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTE_ON_SYMBIAN_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.FlashROMImpl <em>Flash ROM</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.FlashROMImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getFlashROM()
	 * @generated
	 */
	int FLASH_ROM = 8;

	/**
	 * The feature id for the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLASH_ROM__PC_PATH = 0;

	/**
	 * The number of structural features of the '<em>Flash ROM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLASH_ROM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.InfoImpl <em>Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.InfoImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getInfo()
	 * @generated
	 */
	int INFO = 9;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO__KEY = 1;

	/**
	 * The number of structural features of the '<em>Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.ReferenceImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__URI = 0;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.RetrieveFromSymbianImpl <em>Retrieve From Symbian</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.RetrieveFromSymbianImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getRetrieveFromSymbian()
	 * @generated
	 */
	int RETRIEVE_FROM_SYMBIAN = 11;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_FROM_SYMBIAN__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Transfer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_FROM_SYMBIAN__TRANSFER = 1;

	/**
	 * The number of structural features of the '<em>Retrieve From Symbian</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRIEVE_FROM_SYMBIAN_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.RtestImpl <em>Rtest</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.RtestImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getRtest()
	 * @generated
	 */
	int RTEST = 12;

	/**
	 * The feature id for the '<em><b>Result File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTEST__RESULT_FILE = 0;

	/**
	 * The feature id for the '<em><b>Symbian Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTEST__SYMBIAN_PATH = 1;

	/**
	 * The number of structural features of the '<em>Rtest</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTEST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TaskImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 13;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Execute On PC</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXECUTE_ON_PC = 1;

	/**
	 * The feature id for the '<em><b>Transfer To Symbian</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TRANSFER_TO_SYMBIAN = 2;

	/**
	 * The feature id for the '<em><b>Execute On Symbian</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXECUTE_ON_SYMBIAN = 3;

	/**
	 * The feature id for the '<em><b>Retrieve From Symbian</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__RETRIEVE_FROM_SYMBIAN = 4;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REFERENCE = 5;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TASK = 6;

	/**
	 * The feature id for the '<em><b>Flashrom</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FLASHROM = 7;

	/**
	 * The feature id for the '<em><b>Start Trace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__START_TRACE = 8;

	/**
	 * The feature id for the '<em><b>Stop Trace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__STOP_TRACE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = 10;

	/**
	 * The feature id for the '<em><b>Pre Reboot Device</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRE_REBOOT_DEVICE = 11;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TIMEOUT = 12;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TestCaseImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 14;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TARGET = 0;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TestCasesListImpl <em>Test Cases List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TestCasesListImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTestCasesList()
	 * @generated
	 */
	int TEST_CASES_LIST = 15;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASES_LIST__TEST_CASE = 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASES_LIST__OPERATOR = 1;

	/**
	 * The number of structural features of the '<em>Test Cases List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASES_LIST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TestExecuteScriptImpl <em>Test Execute Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TestExecuteScriptImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTestExecuteScript()
	 * @generated
	 */
	int TEST_EXECUTE_SCRIPT = 16;

	/**
	 * The feature id for the '<em><b>Test Cases List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_EXECUTE_SCRIPT__TEST_CASES_LIST = 0;

	/**
	 * The feature id for the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_EXECUTE_SCRIPT__PC_PATH = 1;

	/**
	 * The feature id for the '<em><b>Symbian Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_EXECUTE_SCRIPT__SYMBIAN_PATH = 2;

	/**
	 * The number of structural features of the '<em>Test Execute Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_EXECUTE_SCRIPT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TransferImpl <em>Transfer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TransferImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTransfer()
	 * @generated
	 */
	int TRANSFER = 17;

	/**
	 * The feature id for the '<em><b>Move</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__MOVE = 0;

	/**
	 * The feature id for the '<em><b>PC Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__PC_PATH = 1;

	/**
	 * The feature id for the '<em><b>Symbian Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER__SYMBIAN_PATH = 2;

	/**
	 * The number of structural features of the '<em>Transfer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.TransferToSymbianImpl <em>Transfer To Symbian</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.TransferToSymbianImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTransferToSymbian()
	 * @generated
	 */
	int TRANSFER_TO_SYMBIAN = 18;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_TO_SYMBIAN__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Transfer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_TO_SYMBIAN__TRANSFER = 1;

	/**
	 * The number of structural features of the '<em>Transfer To Symbian</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFER_TO_SYMBIAN_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.StartTraceImpl <em>Start Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.StartTraceImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getStartTrace()
	 * @generated
	 */
	int START_TRACE = 19;

	/**
	 * The feature id for the '<em><b>Enable Primary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE__ENABLE_PRIMARY_FILTERS = 0;

	/**
	 * The feature id for the '<em><b>Enable Secondary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE__ENABLE_SECONDARY_FILTERS = 1;

	/**
	 * The feature id for the '<em><b>Disable Primary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE__DISABLE_PRIMARY_FILTERS = 2;

	/**
	 * The feature id for the '<em><b>Disable Secondary Filters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE__DISABLE_SECONDARY_FILTERS = 3;

	/**
	 * The feature id for the '<em><b>Config File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE__CONFIG_FILE_PATH = 4;

	/**
	 * The number of structural features of the '<em>Start Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TRACE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.symbian.driver.impl.StopTraceImpl <em>Stop Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.impl.StopTraceImpl
	 * @see com.symbian.driver.impl.DriverPackageImpl#getStopTrace()
	 * @generated
	 */
	int STOP_TRACE = 20;

	/**
	 * The number of structural features of the '<em>Stop Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_TRACE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.symbian.driver.OperatorType <em>Operator Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.OperatorType
	 * @see com.symbian.driver.impl.DriverPackageImpl#getOperatorType()
	 * @generated
	 */
	int OPERATOR_TYPE = 21;

	/**
	 * The meta object id for the '{@link com.symbian.driver.Phase <em>Phase</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.Phase
	 * @see com.symbian.driver.impl.DriverPackageImpl#getPhase()
	 * @generated
	 */
	int PHASE = 22;

	/**
	 * The meta object id for the '{@link com.symbian.driver.StatCommand <em>Stat Command</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.StatCommand
	 * @see com.symbian.driver.impl.DriverPackageImpl#getStatCommand()
	 * @generated
	 */
	int STAT_COMMAND = 23;

	/**
	 * The meta object id for the '<em>Component Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see com.symbian.driver.impl.DriverPackageImpl#getComponentNameType()
	 * @generated
	 */
	int COMPONENT_NAME_TYPE = 24;

	/**
	 * The meta object id for the '<em>Operator Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.OperatorType
	 * @see com.symbian.driver.impl.DriverPackageImpl#getOperatorTypeObject()
	 * @generated
	 */
	int OPERATOR_TYPE_OBJECT = 25;

	/**
	 * The meta object id for the '<em>Phase Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.Phase
	 * @see com.symbian.driver.impl.DriverPackageImpl#getPhaseObject()
	 * @generated
	 */
	int PHASE_OBJECT = 26;

	/**
	 * The meta object id for the '<em>Stat Command Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.driver.StatCommand
	 * @see com.symbian.driver.impl.DriverPackageImpl#getStatCommandObject()
	 * @generated
	 */
	int STAT_COMMAND_OBJECT = 27;

	/**
	 * The meta object id for the '<em>Symbian Path</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see com.symbian.driver.impl.DriverPackageImpl#getSymbianPath()
	 * @generated
	 */
	int SYMBIAN_PATH = 28;

	/**
	 * The meta object id for the '<em>Target Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see com.symbian.driver.impl.DriverPackageImpl#getTargetType()
	 * @generated
	 */
	int TARGET_TYPE = 29;


	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Build <em>Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Build</em>'.
	 * @see com.symbian.driver.Build
	 * @generated
	 */
	EClass getBuild();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.Build#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Component Name</em>'.
	 * @see com.symbian.driver.Build#getComponentName()
	 * @see #getBuild()
	 * @generated
	 */
	EAttribute getBuild_ComponentName();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Build#isTestBuild <em>Test Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Build</em>'.
	 * @see com.symbian.driver.Build#isTestBuild()
	 * @see #getBuild()
	 * @generated
	 */
	EAttribute getBuild_TestBuild();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Build#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see com.symbian.driver.Build#getURI()
	 * @see #getBuild()
	 * @generated
	 */
	EAttribute getBuild_URI();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.CmdPC <em>Cmd PC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cmd PC</em>'.
	 * @see com.symbian.driver.CmdPC
	 * @generated
	 */
	EClass getCmdPC();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdPC#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.symbian.driver.CmdPC#getValue()
	 * @see #getCmdPC()
	 * @generated
	 */
	EAttribute getCmdPC_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdPC#getPhase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase</em>'.
	 * @see com.symbian.driver.CmdPC#getPhase()
	 * @see #getCmdPC()
	 * @generated
	 */
	EAttribute getCmdPC_Phase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdPC#isSync <em>Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sync</em>'.
	 * @see com.symbian.driver.CmdPC#isSync()
	 * @see #getCmdPC()
	 * @generated
	 */
	EAttribute getCmdPC_Sync();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdPC#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see com.symbian.driver.CmdPC#getURI()
	 * @see #getCmdPC()
	 * @generated
	 */
	EAttribute getCmdPC_URI();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.CmdSymbian <em>Cmd Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cmd Symbian</em>'.
	 * @see com.symbian.driver.CmdSymbian
	 * @generated
	 */
	EClass getCmdSymbian();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.CmdSymbian#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Argument</em>'.
	 * @see com.symbian.driver.CmdSymbian#getArgument()
	 * @see #getCmdSymbian()
	 * @generated
	 */
	EAttribute getCmdSymbian_Argument();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdSymbian#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output</em>'.
	 * @see com.symbian.driver.CmdSymbian#getOutput()
	 * @see #getCmdSymbian()
	 * @generated
	 */
	EAttribute getCmdSymbian_Output();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdSymbian#getStatCommand <em>Stat Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stat Command</em>'.
	 * @see com.symbian.driver.CmdSymbian#getStatCommand()
	 * @see #getCmdSymbian()
	 * @generated
	 */
	EAttribute getCmdSymbian_StatCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.CmdSymbian#isSync <em>Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sync</em>'.
	 * @see com.symbian.driver.CmdSymbian#isSync()
	 * @see #getCmdSymbian()
	 * @generated
	 */
	EAttribute getCmdSymbian_Sync();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see com.symbian.driver.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.symbian.driver.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link com.symbian.driver.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see com.symbian.driver.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link com.symbian.driver.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see com.symbian.driver.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.DocumentRoot#getDriver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Driver</em>'.
	 * @see com.symbian.driver.DocumentRoot#getDriver()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Driver();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Driver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Driver</em>'.
	 * @see com.symbian.driver.Driver
	 * @generated
	 */
	EClass getDriver();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.Driver#getDriverInfo <em>Driver Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Driver Info</em>'.
	 * @see com.symbian.driver.Driver#getDriverInfo()
	 * @see #getDriver()
	 * @generated
	 */
	EReference getDriver_DriverInfo();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.Driver#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task</em>'.
	 * @see com.symbian.driver.Driver#getTask()
	 * @see #getDriver()
	 * @generated
	 */
	EReference getDriver_Task();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.DriverInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see com.symbian.driver.DriverInfo
	 * @generated
	 */
	EClass getDriverInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.DriverInfo#getInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Info</em>'.
	 * @see com.symbian.driver.DriverInfo#getInfo()
	 * @see #getDriverInfo()
	 * @generated
	 */
	EReference getDriverInfo_Info();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.ExecuteOnPC <em>Execute On PC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execute On PC</em>'.
	 * @see com.symbian.driver.ExecuteOnPC
	 * @generated
	 */
	EClass getExecuteOnPC();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.ExecuteOnPC#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.symbian.driver.ExecuteOnPC#getGroup()
	 * @see #getExecuteOnPC()
	 * @generated
	 */
	EAttribute getExecuteOnPC_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.ExecuteOnPC#getCmd <em>Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cmd</em>'.
	 * @see com.symbian.driver.ExecuteOnPC#getCmd()
	 * @see #getExecuteOnPC()
	 * @generated
	 */
	EReference getExecuteOnPC_Cmd();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.ExecuteOnPC#getBuild <em>Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Build</em>'.
	 * @see com.symbian.driver.ExecuteOnPC#getBuild()
	 * @see #getExecuteOnPC()
	 * @generated
	 */
	EReference getExecuteOnPC_Build();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.ExecuteOnSymbian <em>Execute On Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execute On Symbian</em>'.
	 * @see com.symbian.driver.ExecuteOnSymbian
	 * @generated
	 */
	EClass getExecuteOnSymbian();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.ExecuteOnSymbian#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.symbian.driver.ExecuteOnSymbian#getGroup()
	 * @see #getExecuteOnSymbian()
	 * @generated
	 */
	EAttribute getExecuteOnSymbian_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.ExecuteOnSymbian#getCmd <em>Cmd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cmd</em>'.
	 * @see com.symbian.driver.ExecuteOnSymbian#getCmd()
	 * @see #getExecuteOnSymbian()
	 * @generated
	 */
	EReference getExecuteOnSymbian_Cmd();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.ExecuteOnSymbian#getTestExecuteScript <em>Test Execute Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Execute Script</em>'.
	 * @see com.symbian.driver.ExecuteOnSymbian#getTestExecuteScript()
	 * @see #getExecuteOnSymbian()
	 * @generated
	 */
	EReference getExecuteOnSymbian_TestExecuteScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.ExecuteOnSymbian#getRtest <em>Rtest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rtest</em>'.
	 * @see com.symbian.driver.ExecuteOnSymbian#getRtest()
	 * @see #getExecuteOnSymbian()
	 * @generated
	 */
	EReference getExecuteOnSymbian_Rtest();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.FlashROM <em>Flash ROM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flash ROM</em>'.
	 * @see com.symbian.driver.FlashROM
	 * @generated
	 */
	EClass getFlashROM();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.FlashROM#getPCPath <em>PC Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PC Path</em>'.
	 * @see com.symbian.driver.FlashROM#getPCPath()
	 * @see #getFlashROM()
	 * @generated
	 */
	EAttribute getFlashROM_PCPath();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Info <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Info</em>'.
	 * @see com.symbian.driver.Info
	 * @generated
	 */
	EClass getInfo();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Info#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.symbian.driver.Info#getValue()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Info#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.symbian.driver.Info#getKey()
	 * @see #getInfo()
	 * @generated
	 */
	EAttribute getInfo_Key();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see com.symbian.driver.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the reference '{@link com.symbian.driver.Reference#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Uri</em>'.
	 * @see com.symbian.driver.Reference#getUri()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Uri();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.RetrieveFromSymbian <em>Retrieve From Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Retrieve From Symbian</em>'.
	 * @see com.symbian.driver.RetrieveFromSymbian
	 * @generated
	 */
	EClass getRetrieveFromSymbian();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.RetrieveFromSymbian#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.symbian.driver.RetrieveFromSymbian#getGroup()
	 * @see #getRetrieveFromSymbian()
	 * @generated
	 */
	EAttribute getRetrieveFromSymbian_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.RetrieveFromSymbian#getTransfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transfer</em>'.
	 * @see com.symbian.driver.RetrieveFromSymbian#getTransfer()
	 * @see #getRetrieveFromSymbian()
	 * @generated
	 */
	EReference getRetrieveFromSymbian_Transfer();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Rtest <em>Rtest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rtest</em>'.
	 * @see com.symbian.driver.Rtest
	 * @generated
	 */
	EClass getRtest();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Rtest#getResultFile <em>Result File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result File</em>'.
	 * @see com.symbian.driver.Rtest#getResultFile()
	 * @see #getRtest()
	 * @generated
	 */
	EAttribute getRtest_ResultFile();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Rtest#getSymbianPath <em>Symbian Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbian Path</em>'.
	 * @see com.symbian.driver.Rtest#getSymbianPath()
	 * @see #getRtest()
	 * @generated
	 */
	EAttribute getRtest_SymbianPath();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see com.symbian.driver.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.Task#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.symbian.driver.Task#getGroup()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getExecuteOnPC <em>Execute On PC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execute On PC</em>'.
	 * @see com.symbian.driver.Task#getExecuteOnPC()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_ExecuteOnPC();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getTransferToSymbian <em>Transfer To Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transfer To Symbian</em>'.
	 * @see com.symbian.driver.Task#getTransferToSymbian()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_TransferToSymbian();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getExecuteOnSymbian <em>Execute On Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execute On Symbian</em>'.
	 * @see com.symbian.driver.Task#getExecuteOnSymbian()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_ExecuteOnSymbian();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getRetrieveFromSymbian <em>Retrieve From Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Retrieve From Symbian</em>'.
	 * @see com.symbian.driver.Task#getRetrieveFromSymbian()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_RetrieveFromSymbian();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference</em>'.
	 * @see com.symbian.driver.Task#getReference()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Reference();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task</em>'.
	 * @see com.symbian.driver.Task#getTask()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Task();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.Task#getFlashrom <em>Flashrom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flashrom</em>'.
	 * @see com.symbian.driver.Task#getFlashrom()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Flashrom();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Task#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.driver.Task#getName()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Task#isPreRebootDevice <em>Pre Reboot Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Reboot Device</em>'.
	 * @see com.symbian.driver.Task#isPreRebootDevice()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PreRebootDevice();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Task#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.driver.Task#getTimeout()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Timeout();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.Task#getStartTrace <em>Start Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Trace</em>'.
	 * @see com.symbian.driver.Task#getStartTrace()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_StartTrace();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.Task#getStopTrace <em>Stop Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stop Trace</em>'.
	 * @see com.symbian.driver.Task#getStopTrace()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_StopTrace();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case</em>'.
	 * @see com.symbian.driver.TestCase
	 * @generated
	 */
	EClass getTestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.TestCase#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see com.symbian.driver.TestCase#getTarget()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_Target();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.TestCasesList <em>Test Cases List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Cases List</em>'.
	 * @see com.symbian.driver.TestCasesList
	 * @generated
	 */
	EClass getTestCasesList();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.TestCasesList#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Case</em>'.
	 * @see com.symbian.driver.TestCasesList#getTestCase()
	 * @see #getTestCasesList()
	 * @generated
	 */
	EReference getTestCasesList_TestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.TestCasesList#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see com.symbian.driver.TestCasesList#getOperator()
	 * @see #getTestCasesList()
	 * @generated
	 */
	EAttribute getTestCasesList_Operator();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.TestExecuteScript <em>Test Execute Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Execute Script</em>'.
	 * @see com.symbian.driver.TestExecuteScript
	 * @generated
	 */
	EClass getTestExecuteScript();

	/**
	 * Returns the meta object for the containment reference '{@link com.symbian.driver.TestExecuteScript#getTestCasesList <em>Test Cases List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Test Cases List</em>'.
	 * @see com.symbian.driver.TestExecuteScript#getTestCasesList()
	 * @see #getTestExecuteScript()
	 * @generated
	 */
	EReference getTestExecuteScript_TestCasesList();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.TestExecuteScript#getPCPath <em>PC Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PC Path</em>'.
	 * @see com.symbian.driver.TestExecuteScript#getPCPath()
	 * @see #getTestExecuteScript()
	 * @generated
	 */
	EAttribute getTestExecuteScript_PCPath();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.TestExecuteScript#getSymbianPath <em>Symbian Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbian Path</em>'.
	 * @see com.symbian.driver.TestExecuteScript#getSymbianPath()
	 * @see #getTestExecuteScript()
	 * @generated
	 */
	EAttribute getTestExecuteScript_SymbianPath();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.Transfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transfer</em>'.
	 * @see com.symbian.driver.Transfer
	 * @generated
	 */
	EClass getTransfer();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Transfer#isMove <em>Move</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Move</em>'.
	 * @see com.symbian.driver.Transfer#isMove()
	 * @see #getTransfer()
	 * @generated
	 */
	EAttribute getTransfer_Move();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Transfer#getPCPath <em>PC Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PC Path</em>'.
	 * @see com.symbian.driver.Transfer#getPCPath()
	 * @see #getTransfer()
	 * @generated
	 */
	EAttribute getTransfer_PCPath();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.Transfer#getSymbianPath <em>Symbian Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbian Path</em>'.
	 * @see com.symbian.driver.Transfer#getSymbianPath()
	 * @see #getTransfer()
	 * @generated
	 */
	EAttribute getTransfer_SymbianPath();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.TransferToSymbian <em>Transfer To Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transfer To Symbian</em>'.
	 * @see com.symbian.driver.TransferToSymbian
	 * @generated
	 */
	EClass getTransferToSymbian();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.driver.TransferToSymbian#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.symbian.driver.TransferToSymbian#getGroup()
	 * @see #getTransferToSymbian()
	 * @generated
	 */
	EAttribute getTransferToSymbian_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.driver.TransferToSymbian#getTransfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transfer</em>'.
	 * @see com.symbian.driver.TransferToSymbian#getTransfer()
	 * @see #getTransferToSymbian()
	 * @generated
	 */
	EReference getTransferToSymbian_Transfer();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.StartTrace <em>Start Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Trace</em>'.
	 * @see com.symbian.driver.StartTrace
	 * @generated
	 */
	EClass getStartTrace();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.StartTrace#getEnablePrimaryFilters <em>Enable Primary Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable Primary Filters</em>'.
	 * @see com.symbian.driver.StartTrace#getEnablePrimaryFilters()
	 * @see #getStartTrace()
	 * @generated
	 */
	EAttribute getStartTrace_EnablePrimaryFilters();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.StartTrace#getEnableSecondaryFilters <em>Enable Secondary Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable Secondary Filters</em>'.
	 * @see com.symbian.driver.StartTrace#getEnableSecondaryFilters()
	 * @see #getStartTrace()
	 * @generated
	 */
	EAttribute getStartTrace_EnableSecondaryFilters();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.StartTrace#getDisablePrimaryFilters <em>Disable Primary Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disable Primary Filters</em>'.
	 * @see com.symbian.driver.StartTrace#getDisablePrimaryFilters()
	 * @see #getStartTrace()
	 * @generated
	 */
	EAttribute getStartTrace_DisablePrimaryFilters();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.StartTrace#getDisableSecondaryFilters <em>Disable Secondary Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disable Secondary Filters</em>'.
	 * @see com.symbian.driver.StartTrace#getDisableSecondaryFilters()
	 * @see #getStartTrace()
	 * @generated
	 */
	EAttribute getStartTrace_DisableSecondaryFilters();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.driver.StartTrace#getConfigFilePath <em>Config File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config File Path</em>'.
	 * @see com.symbian.driver.StartTrace#getConfigFilePath()
	 * @see #getStartTrace()
	 * @generated
	 */
	EAttribute getStartTrace_ConfigFilePath();

	/**
	 * Returns the meta object for class '{@link com.symbian.driver.StopTrace <em>Stop Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop Trace</em>'.
	 * @see com.symbian.driver.StopTrace
	 * @generated
	 */
	EClass getStopTrace();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.OperatorType <em>Operator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Operator Type</em>'.
	 * @see com.symbian.driver.OperatorType
	 * @generated
	 */
	EEnum getOperatorType();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Phase</em>'.
	 * @see com.symbian.driver.Phase
	 * @generated
	 */
	EEnum getPhase();

	/**
	 * Returns the meta object for enum '{@link com.symbian.driver.StatCommand <em>Stat Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Stat Command</em>'.
	 * @see com.symbian.driver.StatCommand
	 * @generated
	 */
	EEnum getStatCommand();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Component Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Component Name Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='componentName_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='[^\\\\%20/%20:%20\\*%20\\?%20\\.]*'"
	 * @generated
	 */
	EDataType getComponentNameType();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.OperatorType <em>Operator Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Operator Type Object</em>'.
	 * @see com.symbian.driver.OperatorType
	 * @model instanceClass="com.symbian.driver.OperatorType"
	 *        extendedMetaData="name='operator_._type:Object' baseType='operator_._type'"
	 * @generated
	 */
	EDataType getOperatorTypeObject();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.Phase <em>Phase Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Phase Object</em>'.
	 * @see com.symbian.driver.Phase
	 * @model instanceClass="com.symbian.driver.Phase"
	 *        extendedMetaData="name='phase_._type:Object' baseType='phase_._type'"
	 * @generated
	 */
	EDataType getPhaseObject();

	/**
	 * Returns the meta object for data type '{@link com.symbian.driver.StatCommand <em>Stat Command Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Stat Command Object</em>'.
	 * @see com.symbian.driver.StatCommand
	 * @model instanceClass="com.symbian.driver.StatCommand"
	 *        extendedMetaData="name='statCommand:Object' baseType='statCommand'"
	 * @generated
	 */
	EDataType getStatCommandObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Symbian Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Symbian Path</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='SymbianPath' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='[a-zA-Z$]:\\\\([^\\\\%20/%20:%20\\*%20\\?]+\\\\)*[^\\\\%20/%20:%20\\?]+'"
	 * @generated
	 */
	EDataType getSymbianPath();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Target Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Target Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='target_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='(\\S+\\.tcs)|([^%20:\\.]+)(\\:[^%20:\\.]+)?'"
	 * @generated
	 */
	EDataType getTargetType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DriverFactory getDriverFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.BuildImpl <em>Build</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.BuildImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getBuild()
		 * @generated
		 */
		EClass BUILD = eINSTANCE.getBuild();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD__COMPONENT_NAME = eINSTANCE.getBuild_ComponentName();

		/**
		 * The meta object literal for the '<em><b>Test Build</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD__TEST_BUILD = eINSTANCE.getBuild_TestBuild();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD__URI = eINSTANCE.getBuild_URI();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.CmdPCImpl <em>Cmd PC</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.CmdPCImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getCmdPC()
		 * @generated
		 */
		EClass CMD_PC = eINSTANCE.getCmdPC();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_PC__VALUE = eINSTANCE.getCmdPC_Value();

		/**
		 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_PC__PHASE = eINSTANCE.getCmdPC_Phase();

		/**
		 * The meta object literal for the '<em><b>Sync</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_PC__SYNC = eINSTANCE.getCmdPC_Sync();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_PC__URI = eINSTANCE.getCmdPC_URI();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.CmdSymbianImpl <em>Cmd Symbian</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.CmdSymbianImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getCmdSymbian()
		 * @generated
		 */
		EClass CMD_SYMBIAN = eINSTANCE.getCmdSymbian();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_SYMBIAN__ARGUMENT = eINSTANCE.getCmdSymbian_Argument();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_SYMBIAN__OUTPUT = eINSTANCE.getCmdSymbian_Output();

		/**
		 * The meta object literal for the '<em><b>Stat Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_SYMBIAN__STAT_COMMAND = eINSTANCE.getCmdSymbian_StatCommand();

		/**
		 * The meta object literal for the '<em><b>Sync</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CMD_SYMBIAN__SYNC = eINSTANCE.getCmdSymbian_Sync();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.DocumentRootImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Driver</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DRIVER = eINSTANCE.getDocumentRoot_Driver();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.DriverImpl <em>Driver</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.DriverImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getDriver()
		 * @generated
		 */
		EClass DRIVER = eINSTANCE.getDriver();

		/**
		 * The meta object literal for the '<em><b>Driver Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRIVER__DRIVER_INFO = eINSTANCE.getDriver_DriverInfo();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRIVER__TASK = eINSTANCE.getDriver_Task();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.DriverInfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.DriverInfoImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getDriverInfo()
		 * @generated
		 */
		EClass DRIVER_INFO = eINSTANCE.getDriverInfo();

		/**
		 * The meta object literal for the '<em><b>Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DRIVER_INFO__INFO = eINSTANCE.getDriverInfo_Info();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.ExecuteOnPCImpl <em>Execute On PC</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.ExecuteOnPCImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getExecuteOnPC()
		 * @generated
		 */
		EClass EXECUTE_ON_PC = eINSTANCE.getExecuteOnPC();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTE_ON_PC__GROUP = eINSTANCE.getExecuteOnPC_Group();

		/**
		 * The meta object literal for the '<em><b>Cmd</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTE_ON_PC__CMD = eINSTANCE.getExecuteOnPC_Cmd();

		/**
		 * The meta object literal for the '<em><b>Build</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTE_ON_PC__BUILD = eINSTANCE.getExecuteOnPC_Build();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.ExecuteOnSymbianImpl <em>Execute On Symbian</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.ExecuteOnSymbianImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getExecuteOnSymbian()
		 * @generated
		 */
		EClass EXECUTE_ON_SYMBIAN = eINSTANCE.getExecuteOnSymbian();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTE_ON_SYMBIAN__GROUP = eINSTANCE.getExecuteOnSymbian_Group();

		/**
		 * The meta object literal for the '<em><b>Cmd</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTE_ON_SYMBIAN__CMD = eINSTANCE.getExecuteOnSymbian_Cmd();

		/**
		 * The meta object literal for the '<em><b>Test Execute Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTE_ON_SYMBIAN__TEST_EXECUTE_SCRIPT = eINSTANCE.getExecuteOnSymbian_TestExecuteScript();

		/**
		 * The meta object literal for the '<em><b>Rtest</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTE_ON_SYMBIAN__RTEST = eINSTANCE.getExecuteOnSymbian_Rtest();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.FlashROMImpl <em>Flash ROM</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.FlashROMImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getFlashROM()
		 * @generated
		 */
		EClass FLASH_ROM = eINSTANCE.getFlashROM();

		/**
		 * The meta object literal for the '<em><b>PC Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLASH_ROM__PC_PATH = eINSTANCE.getFlashROM_PCPath();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.InfoImpl <em>Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.InfoImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getInfo()
		 * @generated
		 */
		EClass INFO = eINSTANCE.getInfo();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__VALUE = eINSTANCE.getInfo_Value();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFO__KEY = eINSTANCE.getInfo_Key();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.ReferenceImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__URI = eINSTANCE.getReference_Uri();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.RetrieveFromSymbianImpl <em>Retrieve From Symbian</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.RetrieveFromSymbianImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getRetrieveFromSymbian()
		 * @generated
		 */
		EClass RETRIEVE_FROM_SYMBIAN = eINSTANCE.getRetrieveFromSymbian();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRIEVE_FROM_SYMBIAN__GROUP = eINSTANCE.getRetrieveFromSymbian_Group();

		/**
		 * The meta object literal for the '<em><b>Transfer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETRIEVE_FROM_SYMBIAN__TRANSFER = eINSTANCE.getRetrieveFromSymbian_Transfer();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.RtestImpl <em>Rtest</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.RtestImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getRtest()
		 * @generated
		 */
		EClass RTEST = eINSTANCE.getRtest();

		/**
		 * The meta object literal for the '<em><b>Result File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTEST__RESULT_FILE = eINSTANCE.getRtest_ResultFile();

		/**
		 * The meta object literal for the '<em><b>Symbian Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTEST__SYMBIAN_PATH = eINSTANCE.getRtest_SymbianPath();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TaskImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__GROUP = eINSTANCE.getTask_Group();

		/**
		 * The meta object literal for the '<em><b>Execute On PC</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__EXECUTE_ON_PC = eINSTANCE.getTask_ExecuteOnPC();

		/**
		 * The meta object literal for the '<em><b>Transfer To Symbian</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__TRANSFER_TO_SYMBIAN = eINSTANCE.getTask_TransferToSymbian();

		/**
		 * The meta object literal for the '<em><b>Execute On Symbian</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__EXECUTE_ON_SYMBIAN = eINSTANCE.getTask_ExecuteOnSymbian();

		/**
		 * The meta object literal for the '<em><b>Retrieve From Symbian</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__RETRIEVE_FROM_SYMBIAN = eINSTANCE.getTask_RetrieveFromSymbian();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__REFERENCE = eINSTANCE.getTask_Reference();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__TASK = eINSTANCE.getTask_Task();

		/**
		 * The meta object literal for the '<em><b>Flashrom</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__FLASHROM = eINSTANCE.getTask_Flashrom();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__NAME = eINSTANCE.getTask_Name();

		/**
		 * The meta object literal for the '<em><b>Pre Reboot Device</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__PRE_REBOOT_DEVICE = eINSTANCE.getTask_PreRebootDevice();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__TIMEOUT = eINSTANCE.getTask_Timeout();

		/**
		 * The meta object literal for the '<em><b>Start Trace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__START_TRACE = eINSTANCE.getTask_StartTrace();

		/**
		 * The meta object literal for the '<em><b>Stop Trace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__STOP_TRACE = eINSTANCE.getTask_StopTrace();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TestCaseImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__TARGET = eINSTANCE.getTestCase_Target();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TestCasesListImpl <em>Test Cases List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TestCasesListImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTestCasesList()
		 * @generated
		 */
		EClass TEST_CASES_LIST = eINSTANCE.getTestCasesList();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASES_LIST__TEST_CASE = eINSTANCE.getTestCasesList_TestCase();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASES_LIST__OPERATOR = eINSTANCE.getTestCasesList_Operator();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TestExecuteScriptImpl <em>Test Execute Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TestExecuteScriptImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTestExecuteScript()
		 * @generated
		 */
		EClass TEST_EXECUTE_SCRIPT = eINSTANCE.getTestExecuteScript();

		/**
		 * The meta object literal for the '<em><b>Test Cases List</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_EXECUTE_SCRIPT__TEST_CASES_LIST = eINSTANCE.getTestExecuteScript_TestCasesList();

		/**
		 * The meta object literal for the '<em><b>PC Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_EXECUTE_SCRIPT__PC_PATH = eINSTANCE.getTestExecuteScript_PCPath();

		/**
		 * The meta object literal for the '<em><b>Symbian Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_EXECUTE_SCRIPT__SYMBIAN_PATH = eINSTANCE.getTestExecuteScript_SymbianPath();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TransferImpl <em>Transfer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TransferImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTransfer()
		 * @generated
		 */
		EClass TRANSFER = eINSTANCE.getTransfer();

		/**
		 * The meta object literal for the '<em><b>Move</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFER__MOVE = eINSTANCE.getTransfer_Move();

		/**
		 * The meta object literal for the '<em><b>PC Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFER__PC_PATH = eINSTANCE.getTransfer_PCPath();

		/**
		 * The meta object literal for the '<em><b>Symbian Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFER__SYMBIAN_PATH = eINSTANCE.getTransfer_SymbianPath();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.TransferToSymbianImpl <em>Transfer To Symbian</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.TransferToSymbianImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTransferToSymbian()
		 * @generated
		 */
		EClass TRANSFER_TO_SYMBIAN = eINSTANCE.getTransferToSymbian();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFER_TO_SYMBIAN__GROUP = eINSTANCE.getTransferToSymbian_Group();

		/**
		 * The meta object literal for the '<em><b>Transfer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFER_TO_SYMBIAN__TRANSFER = eINSTANCE.getTransferToSymbian_Transfer();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.StartTraceImpl <em>Start Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.StartTraceImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getStartTrace()
		 * @generated
		 */
		EClass START_TRACE = eINSTANCE.getStartTrace();

		/**
		 * The meta object literal for the '<em><b>Enable Primary Filters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TRACE__ENABLE_PRIMARY_FILTERS = eINSTANCE.getStartTrace_EnablePrimaryFilters();

		/**
		 * The meta object literal for the '<em><b>Enable Secondary Filters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TRACE__ENABLE_SECONDARY_FILTERS = eINSTANCE.getStartTrace_EnableSecondaryFilters();

		/**
		 * The meta object literal for the '<em><b>Disable Primary Filters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TRACE__DISABLE_PRIMARY_FILTERS = eINSTANCE.getStartTrace_DisablePrimaryFilters();

		/**
		 * The meta object literal for the '<em><b>Disable Secondary Filters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TRACE__DISABLE_SECONDARY_FILTERS = eINSTANCE.getStartTrace_DisableSecondaryFilters();

		/**
		 * The meta object literal for the '<em><b>Config File Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TRACE__CONFIG_FILE_PATH = eINSTANCE.getStartTrace_ConfigFilePath();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.impl.StopTraceImpl <em>Stop Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.impl.StopTraceImpl
		 * @see com.symbian.driver.impl.DriverPackageImpl#getStopTrace()
		 * @generated
		 */
		EClass STOP_TRACE = eINSTANCE.getStopTrace();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.OperatorType <em>Operator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.OperatorType
		 * @see com.symbian.driver.impl.DriverPackageImpl#getOperatorType()
		 * @generated
		 */
		EEnum OPERATOR_TYPE = eINSTANCE.getOperatorType();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.Phase <em>Phase</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.Phase
		 * @see com.symbian.driver.impl.DriverPackageImpl#getPhase()
		 * @generated
		 */
		EEnum PHASE = eINSTANCE.getPhase();

		/**
		 * The meta object literal for the '{@link com.symbian.driver.StatCommand <em>Stat Command</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.StatCommand
		 * @see com.symbian.driver.impl.DriverPackageImpl#getStatCommand()
		 * @generated
		 */
		EEnum STAT_COMMAND = eINSTANCE.getStatCommand();

		/**
		 * The meta object literal for the '<em>Component Name Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see com.symbian.driver.impl.DriverPackageImpl#getComponentNameType()
		 * @generated
		 */
		EDataType COMPONENT_NAME_TYPE = eINSTANCE.getComponentNameType();

		/**
		 * The meta object literal for the '<em>Operator Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.OperatorType
		 * @see com.symbian.driver.impl.DriverPackageImpl#getOperatorTypeObject()
		 * @generated
		 */
		EDataType OPERATOR_TYPE_OBJECT = eINSTANCE.getOperatorTypeObject();

		/**
		 * The meta object literal for the '<em>Phase Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.Phase
		 * @see com.symbian.driver.impl.DriverPackageImpl#getPhaseObject()
		 * @generated
		 */
		EDataType PHASE_OBJECT = eINSTANCE.getPhaseObject();

		/**
		 * The meta object literal for the '<em>Stat Command Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.driver.StatCommand
		 * @see com.symbian.driver.impl.DriverPackageImpl#getStatCommandObject()
		 * @generated
		 */
		EDataType STAT_COMMAND_OBJECT = eINSTANCE.getStatCommandObject();

		/**
		 * The meta object literal for the '<em>Symbian Path</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see com.symbian.driver.impl.DriverPackageImpl#getSymbianPath()
		 * @generated
		 */
		EDataType SYMBIAN_PATH = eINSTANCE.getSymbianPath();

		/**
		 * The meta object literal for the '<em>Target Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see com.symbian.driver.impl.DriverPackageImpl#getTargetType()
		 * @generated
		 */
		EDataType TARGET_TYPE = eINSTANCE.getTargetType();

	}

} //DriverPackage
