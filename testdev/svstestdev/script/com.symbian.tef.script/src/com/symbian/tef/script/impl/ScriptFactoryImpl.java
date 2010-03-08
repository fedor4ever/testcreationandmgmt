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


package com.symbian.tef.script.impl;

import com.symbian.tef.script.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScriptFactoryImpl extends EFactoryImpl implements ScriptFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScriptFactory init() {
		try {
			ScriptFactory theScriptFactory = (ScriptFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.symbian.com/tef"); 
			if (theScriptFactory != null) {
				return theScriptFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScriptFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptFactoryImpl() {
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
			case ScriptPackage.TEF_MODEL: return createTefModel();
			case ScriptPackage.TEST_CASE: return createTestCase();
			case ScriptPackage.PREFIX: return createPrefix();
			case ScriptPackage.REPEAT: return createRepeat();
			case ScriptPackage.TEST_STEP: return createTestStep();
			case ScriptPackage.PRINT: return createPrint();
			case ScriptPackage.LOAD_SUITE: return createLoadSuite();
			case ScriptPackage.LOAD_SERVER: return createLoadServer();
			case ScriptPackage.RUN_UTILS: return createRunUtils();
			case ScriptPackage.RUN_PROGRAM: return createRunProgram();
			case ScriptPackage.RUN_SCRIPT: return createRunScript();
			case ScriptPackage.PAUSE: return createPause();
			case ScriptPackage.DELAY: return createDelay();
			case ScriptPackage.CONSECUTIVE: return createConsecutive();
			case ScriptPackage.CONCURRENT: return createConcurrent();
			case ScriptPackage.SHARED_DATA: return createSharedData();
			case ScriptPackage.TEF_COMMENT: return createTefComment();
			case ScriptPackage.TEST_BLOCK: return createTestBlock();
			case ScriptPackage.CREATE_OBJECT: return createCreateObject();
			case ScriptPackage.RESTORE_OBJECT: return createRestoreObject();
			case ScriptPackage.COMMAND: return createCommand();
			case ScriptPackage.STORE: return createStore();
			case ScriptPackage.OUTSTANDING: return createOutstanding();
			case ScriptPackage.ASYNC_DELAY: return createAsyncDelay();
			case ScriptPackage.SHARED_ACTIVE_SCHEDULER: return createSharedActiveScheduler();
			case ScriptPackage.STORE_ACTIVE_SCHEDULER: return createStoreActiveScheduler();
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
			case ScriptPackage.UTILITY_COMMAND:
				return createUtilityCommandFromString(eDataType, initialValue);
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
			case ScriptPackage.UTILITY_COMMAND:
				return convertUtilityCommandToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefModel createTefModel() {
		TefModelImpl tefModel = new TefModelImpl();
		return tefModel;
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
	public Prefix createPrefix() {
		PrefixImpl prefix = new PrefixImpl();
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Repeat createRepeat() {
		RepeatImpl repeat = new RepeatImpl();
		return repeat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestStep createTestStep() {
		TestStepImpl testStep = new TestStepImpl();
		return testStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Print createPrint() {
		PrintImpl print = new PrintImpl();
		return print;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadSuite createLoadSuite() {
		LoadSuiteImpl loadSuite = new LoadSuiteImpl();
		return loadSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadServer createLoadServer() {
		LoadServerImpl loadServer = new LoadServerImpl();
		return loadServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunUtils createRunUtils() {
		RunUtilsImpl runUtils = new RunUtilsImpl();
		return runUtils;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunProgram createRunProgram() {
		RunProgramImpl runProgram = new RunProgramImpl();
		return runProgram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunScript createRunScript() {
		RunScriptImpl runScript = new RunScriptImpl();
		return runScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pause createPause() {
		PauseImpl pause = new PauseImpl();
		return pause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Delay createDelay() {
		DelayImpl delay = new DelayImpl();
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Consecutive createConsecutive() {
		ConsecutiveImpl consecutive = new ConsecutiveImpl();
		return consecutive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concurrent createConcurrent() {
		ConcurrentImpl concurrent = new ConcurrentImpl();
		return concurrent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SharedData createSharedData() {
		SharedDataImpl sharedData = new SharedDataImpl();
		return sharedData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TefComment createTefComment() {
		TefCommentImpl tefComment = new TefCommentImpl();
		return tefComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestBlock createTestBlock() {
		TestBlockImpl testBlock = new TestBlockImpl();
		return testBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateObject createCreateObject() {
		CreateObjectImpl createObject = new CreateObjectImpl();
		return createObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RestoreObject createRestoreObject() {
		RestoreObjectImpl restoreObject = new RestoreObjectImpl();
		return restoreObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Command createCommand() {
		CommandImpl command = new CommandImpl();
		return command;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Store createStore() {
		StoreImpl store = new StoreImpl();
		return store;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Outstanding createOutstanding() {
		OutstandingImpl outstanding = new OutstandingImpl();
		return outstanding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsyncDelay createAsyncDelay() {
		AsyncDelayImpl asyncDelay = new AsyncDelayImpl();
		return asyncDelay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SharedActiveScheduler createSharedActiveScheduler() {
		SharedActiveSchedulerImpl sharedActiveScheduler = new SharedActiveSchedulerImpl();
		return sharedActiveScheduler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoreActiveScheduler createStoreActiveScheduler() {
		StoreActiveSchedulerImpl storeActiveScheduler = new StoreActiveSchedulerImpl();
		return storeActiveScheduler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UtilityCommand createUtilityCommandFromString(EDataType eDataType, String initialValue) {
		UtilityCommand result = UtilityCommand.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUtilityCommandToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptPackage getScriptPackage() {
		return (ScriptPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ScriptPackage getPackage() {
		return ScriptPackage.eINSTANCE;
	}

} //ScriptFactoryImpl
