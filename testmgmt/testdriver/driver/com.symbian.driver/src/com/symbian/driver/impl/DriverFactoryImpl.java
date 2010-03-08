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


package com.symbian.driver.impl;

import com.symbian.driver.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DriverFactoryImpl extends EFactoryImpl implements DriverFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DriverFactory init() {
		try {
			DriverFactory theDriverFactory = (DriverFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.symbian.com/TestDriver"); 
			if (theDriverFactory != null) {
				return theDriverFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DriverFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DriverPackage.BUILD: return createBuild();
			case DriverPackage.CMD_PC: return createCmdPC();
			case DriverPackage.CMD_SYMBIAN: return createCmdSymbian();
			case DriverPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case DriverPackage.DRIVER: return createDriver();
			case DriverPackage.DRIVER_INFO: return createDriverInfo();
			case DriverPackage.EXECUTE_ON_PC: return createExecuteOnPC();
			case DriverPackage.EXECUTE_ON_SYMBIAN: return createExecuteOnSymbian();
			case DriverPackage.FLASH_ROM: return createFlashROM();
			case DriverPackage.INFO: return createInfo();
			case DriverPackage.REFERENCE: return createReference();
			case DriverPackage.RETRIEVE_FROM_SYMBIAN: return createRetrieveFromSymbian();
			case DriverPackage.RTEST: return createRtest();
			case DriverPackage.TASK: return createTask();
			case DriverPackage.TEST_CASE: return createTestCase();
			case DriverPackage.TEST_CASES_LIST: return createTestCasesList();
			case DriverPackage.TEST_EXECUTE_SCRIPT: return createTestExecuteScript();
			case DriverPackage.TRANSFER: return createTransfer();
			case DriverPackage.TRANSFER_TO_SYMBIAN: return createTransferToSymbian();
			case DriverPackage.START_TRACE: return createStartTrace();
			case DriverPackage.STOP_TRACE: return createStopTrace();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DriverPackage.OPERATOR_TYPE:
				return createOperatorTypeFromString(eDataType, initialValue);
			case DriverPackage.PHASE:
				return createPhaseFromString(eDataType, initialValue);
			case DriverPackage.STAT_COMMAND:
				return createStatCommandFromString(eDataType, initialValue);
			case DriverPackage.COMPONENT_NAME_TYPE:
				return createComponentNameTypeFromString(eDataType, initialValue);
			case DriverPackage.OPERATOR_TYPE_OBJECT:
				return createOperatorTypeObjectFromString(eDataType, initialValue);
			case DriverPackage.PHASE_OBJECT:
				return createPhaseObjectFromString(eDataType, initialValue);
			case DriverPackage.STAT_COMMAND_OBJECT:
				return createStatCommandObjectFromString(eDataType, initialValue);
			case DriverPackage.SYMBIAN_PATH:
				return createSymbianPathFromString(eDataType, initialValue);
			case DriverPackage.TARGET_TYPE:
				return createTargetTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DriverPackage.OPERATOR_TYPE:
				return convertOperatorTypeToString(eDataType, instanceValue);
			case DriverPackage.PHASE:
				return convertPhaseToString(eDataType, instanceValue);
			case DriverPackage.STAT_COMMAND:
				return convertStatCommandToString(eDataType, instanceValue);
			case DriverPackage.COMPONENT_NAME_TYPE:
				return convertComponentNameTypeToString(eDataType, instanceValue);
			case DriverPackage.OPERATOR_TYPE_OBJECT:
				return convertOperatorTypeObjectToString(eDataType, instanceValue);
			case DriverPackage.PHASE_OBJECT:
				return convertPhaseObjectToString(eDataType, instanceValue);
			case DriverPackage.STAT_COMMAND_OBJECT:
				return convertStatCommandObjectToString(eDataType, instanceValue);
			case DriverPackage.SYMBIAN_PATH:
				return convertSymbianPathToString(eDataType, instanceValue);
			case DriverPackage.TARGET_TYPE:
				return convertTargetTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Build createBuild() {
		BuildImpl build = new BuildImpl();
		return build;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CmdPC createCmdPC() {
		CmdPCImpl cmdPC = new CmdPCImpl();
		return cmdPC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CmdSymbian createCmdSymbian() {
		CmdSymbianImpl cmdSymbian = new CmdSymbianImpl();
		return cmdSymbian;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Driver createDriver() {
		DriverImpl driver = new DriverImpl();
		return driver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverInfo createDriverInfo() {
		DriverInfoImpl driverInfo = new DriverInfoImpl();
		return driverInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecuteOnPC createExecuteOnPC() {
		ExecuteOnPCImpl executeOnPC = new ExecuteOnPCImpl();
		return executeOnPC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecuteOnSymbian createExecuteOnSymbian() {
		ExecuteOnSymbianImpl executeOnSymbian = new ExecuteOnSymbianImpl();
		return executeOnSymbian;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlashROM createFlashROM() {
		FlashROMImpl flashROM = new FlashROMImpl();
		return flashROM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Info createInfo() {
		InfoImpl info = new InfoImpl();
		return info;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RetrieveFromSymbian createRetrieveFromSymbian() {
		RetrieveFromSymbianImpl retrieveFromSymbian = new RetrieveFromSymbianImpl();
		return retrieveFromSymbian;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rtest createRtest() {
		RtestImpl rtest = new RtestImpl();
		return rtest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCase createTestCase() {
		TestCaseImpl testCase = new TestCaseImpl();
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCasesList createTestCasesList() {
		TestCasesListImpl testCasesList = new TestCasesListImpl();
		return testCasesList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestExecuteScript createTestExecuteScript() {
		TestExecuteScriptImpl testExecuteScript = new TestExecuteScriptImpl();
		return testExecuteScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transfer createTransfer() {
		TransferImpl transfer = new TransferImpl();
		return transfer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransferToSymbian createTransferToSymbian() {
		TransferToSymbianImpl transferToSymbian = new TransferToSymbianImpl();
		return transferToSymbian;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartTrace createStartTrace() {
		StartTraceImpl startTrace = new StartTraceImpl();
		return startTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StopTrace createStopTrace() {
		StopTraceImpl stopTrace = new StopTraceImpl();
		return stopTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorType createOperatorTypeFromString(EDataType eDataType, String initialValue) {
		OperatorType result = OperatorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperatorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase createPhaseFromString(EDataType eDataType, String initialValue) {
		Phase result = Phase.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhaseToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatCommand createStatCommandFromString(EDataType eDataType, String initialValue) {
		StatCommand result = StatCommand.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStatCommandToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createComponentNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComponentNameTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorType createOperatorTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createOperatorTypeFromString(DriverPackage.Literals.OPERATOR_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperatorTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOperatorTypeToString(DriverPackage.Literals.OPERATOR_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phase createPhaseObjectFromString(EDataType eDataType, String initialValue) {
		return createPhaseFromString(DriverPackage.Literals.PHASE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhaseObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPhaseToString(DriverPackage.Literals.PHASE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatCommand createStatCommandObjectFromString(EDataType eDataType, String initialValue) {
		return createStatCommandFromString(DriverPackage.Literals.STAT_COMMAND, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStatCommandObjectToString(EDataType eDataType, Object instanceValue) {
		return convertStatCommandToString(DriverPackage.Literals.STAT_COMMAND, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSymbianPathFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSymbianPathToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createTargetTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTargetTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverPackage getDriverPackage() {
		return (DriverPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DriverPackage getPackage() {
		return DriverPackage.eINSTANCE;
	}

} //DriverFactoryImpl
