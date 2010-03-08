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


package com.symbian.tef.script.util;

import com.symbian.comment.AttachedComment;
import com.symbian.comment.Comment;

import com.symbian.tef.script.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.tef.script.ScriptPackage
 * @generated
 */
public class ScriptAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ScriptPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ScriptPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptSwitch<Adapter> modelSwitch =
		new ScriptSwitch<Adapter>() {
			@Override
			public Adapter caseTefModel(TefModel object) {
				return createTefModelAdapter();
			}
			@Override
			public Adapter caseTef(Tef object) {
				return createTefAdapter();
			}
			@Override
			public Adapter caseContainer(Container object) {
				return createContainerAdapter();
			}
			@Override
			public Adapter caseLeaf(Leaf object) {
				return createLeafAdapter();
			}
			@Override
			public Adapter caseTestCase(TestCase object) {
				return createTestCaseAdapter();
			}
			@Override
			public Adapter casePrefix(Prefix object) {
				return createPrefixAdapter();
			}
			@Override
			public Adapter caseRepeat(Repeat object) {
				return createRepeatAdapter();
			}
			@Override
			public Adapter caseTestStep(TestStep object) {
				return createTestStepAdapter();
			}
			@Override
			public Adapter casePrint(Print object) {
				return createPrintAdapter();
			}
			@Override
			public Adapter caseLoadSuite(LoadSuite object) {
				return createLoadSuiteAdapter();
			}
			@Override
			public Adapter caseLoadServer(LoadServer object) {
				return createLoadServerAdapter();
			}
			@Override
			public Adapter caseRunUtils(RunUtils object) {
				return createRunUtilsAdapter();
			}
			@Override
			public Adapter caseRunProgram(RunProgram object) {
				return createRunProgramAdapter();
			}
			@Override
			public Adapter caseRunScript(RunScript object) {
				return createRunScriptAdapter();
			}
			@Override
			public Adapter casePause(Pause object) {
				return createPauseAdapter();
			}
			@Override
			public Adapter caseDelay(Delay object) {
				return createDelayAdapter();
			}
			@Override
			public Adapter caseConsecutive(Consecutive object) {
				return createConsecutiveAdapter();
			}
			@Override
			public Adapter caseConcurrent(Concurrent object) {
				return createConcurrentAdapter();
			}
			@Override
			public Adapter caseSharedData(SharedData object) {
				return createSharedDataAdapter();
			}
			@Override
			public Adapter caseSectionPesistance(SectionPesistance object) {
				return createSectionPesistanceAdapter();
			}
			@Override
			public Adapter caseTefComment(TefComment object) {
				return createTefCommentAdapter();
			}
			@Override
			public Adapter caseTestBlock(TestBlock object) {
				return createTestBlockAdapter();
			}
			@Override
			public Adapter caseCreateObject(CreateObject object) {
				return createCreateObjectAdapter();
			}
			@Override
			public Adapter caseRestoreObject(RestoreObject object) {
				return createRestoreObjectAdapter();
			}
			@Override
			public Adapter caseCommand(Command object) {
				return createCommandAdapter();
			}
			@Override
			public Adapter caseStore(Store object) {
				return createStoreAdapter();
			}
			@Override
			public Adapter caseOutstanding(Outstanding object) {
				return createOutstandingAdapter();
			}
			@Override
			public Adapter caseAsyncDelay(AsyncDelay object) {
				return createAsyncDelayAdapter();
			}
			@Override
			public Adapter caseSharedActiveScheduler(SharedActiveScheduler object) {
				return createSharedActiveSchedulerAdapter();
			}
			@Override
			public Adapter caseStoreActiveScheduler(StoreActiveScheduler object) {
				return createStoreActiveSchedulerAdapter();
			}
			@Override
			public Adapter caseAttachedComment(AttachedComment object) {
				return createAttachedCommentAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.TefModel <em>Tef Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.TefModel
	 * @generated
	 */
	public Adapter createTefModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Tef <em>Tef</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Tef
	 * @generated
	 */
	public Adapter createTefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Container
	 * @generated
	 */
	public Adapter createContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Leaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Leaf
	 * @generated
	 */
	public Adapter createLeafAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.TestCase
	 * @generated
	 */
	public Adapter createTestCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Prefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Prefix
	 * @generated
	 */
	public Adapter createPrefixAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Repeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Repeat
	 * @generated
	 */
	public Adapter createRepeatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.TestStep <em>Test Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.TestStep
	 * @generated
	 */
	public Adapter createTestStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Print <em>Print</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Print
	 * @generated
	 */
	public Adapter createPrintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.LoadSuite <em>Load Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.LoadSuite
	 * @generated
	 */
	public Adapter createLoadSuiteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.LoadServer <em>Load Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.LoadServer
	 * @generated
	 */
	public Adapter createLoadServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.RunUtils <em>Run Utils</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.RunUtils
	 * @generated
	 */
	public Adapter createRunUtilsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.RunProgram <em>Run Program</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.RunProgram
	 * @generated
	 */
	public Adapter createRunProgramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.RunScript <em>Run Script</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.RunScript
	 * @generated
	 */
	public Adapter createRunScriptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Pause <em>Pause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Pause
	 * @generated
	 */
	public Adapter createPauseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Delay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Delay
	 * @generated
	 */
	public Adapter createDelayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Consecutive <em>Consecutive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Consecutive
	 * @generated
	 */
	public Adapter createConsecutiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Concurrent <em>Concurrent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Concurrent
	 * @generated
	 */
	public Adapter createConcurrentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.SharedData <em>Shared Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.SharedData
	 * @generated
	 */
	public Adapter createSharedDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.SectionPesistance <em>Section Pesistance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.SectionPesistance
	 * @generated
	 */
	public Adapter createSectionPesistanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.TefComment <em>Tef Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.TefComment
	 * @generated
	 */
	public Adapter createTefCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.TestBlock <em>Test Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.TestBlock
	 * @generated
	 */
	public Adapter createTestBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.CreateObject <em>Create Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.CreateObject
	 * @generated
	 */
	public Adapter createCreateObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.RestoreObject <em>Restore Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.RestoreObject
	 * @generated
	 */
	public Adapter createRestoreObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Command
	 * @generated
	 */
	public Adapter createCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Store <em>Store</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Store
	 * @generated
	 */
	public Adapter createStoreAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.Outstanding <em>Outstanding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.Outstanding
	 * @generated
	 */
	public Adapter createOutstandingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.AsyncDelay <em>Async Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.AsyncDelay
	 * @generated
	 */
	public Adapter createAsyncDelayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.SharedActiveScheduler <em>Shared Active Scheduler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.SharedActiveScheduler
	 * @generated
	 */
	public Adapter createSharedActiveSchedulerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.tef.script.StoreActiveScheduler <em>Store Active Scheduler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.tef.script.StoreActiveScheduler
	 * @generated
	 */
	public Adapter createStoreActiveSchedulerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.comment.AttachedComment <em>Attached Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.comment.AttachedComment
	 * @generated
	 */
	public Adapter createAttachedCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.comment.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.comment.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ScriptAdapterFactory
