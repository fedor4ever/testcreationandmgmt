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


package com.symbian.driver.util;

import com.symbian.driver.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.DriverPackage
 * @generated
 */
public class DriverAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DriverPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DriverPackage.eINSTANCE;
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
	protected DriverSwitch<Adapter> modelSwitch =
		new DriverSwitch<Adapter>() {
			@Override
			public Adapter caseBuild(Build object) {
				return createBuildAdapter();
			}
			@Override
			public Adapter caseCmdPC(CmdPC object) {
				return createCmdPCAdapter();
			}
			@Override
			public Adapter caseCmdSymbian(CmdSymbian object) {
				return createCmdSymbianAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseDriver(Driver object) {
				return createDriverAdapter();
			}
			@Override
			public Adapter caseDriverInfo(DriverInfo object) {
				return createDriverInfoAdapter();
			}
			@Override
			public Adapter caseExecuteOnPC(ExecuteOnPC object) {
				return createExecuteOnPCAdapter();
			}
			@Override
			public Adapter caseExecuteOnSymbian(ExecuteOnSymbian object) {
				return createExecuteOnSymbianAdapter();
			}
			@Override
			public Adapter caseFlashROM(FlashROM object) {
				return createFlashROMAdapter();
			}
			@Override
			public Adapter caseInfo(Info object) {
				return createInfoAdapter();
			}
			@Override
			public Adapter caseReference(Reference object) {
				return createReferenceAdapter();
			}
			@Override
			public Adapter caseRetrieveFromSymbian(RetrieveFromSymbian object) {
				return createRetrieveFromSymbianAdapter();
			}
			@Override
			public Adapter caseRtest(Rtest object) {
				return createRtestAdapter();
			}
			@Override
			public Adapter caseTask(Task object) {
				return createTaskAdapter();
			}
			@Override
			public Adapter caseTestCase(TestCase object) {
				return createTestCaseAdapter();
			}
			@Override
			public Adapter caseTestCasesList(TestCasesList object) {
				return createTestCasesListAdapter();
			}
			@Override
			public Adapter caseTestExecuteScript(TestExecuteScript object) {
				return createTestExecuteScriptAdapter();
			}
			@Override
			public Adapter caseTransfer(Transfer object) {
				return createTransferAdapter();
			}
			@Override
			public Adapter caseTransferToSymbian(TransferToSymbian object) {
				return createTransferToSymbianAdapter();
			}
			@Override
			public Adapter caseStartTrace(StartTrace object) {
				return createStartTraceAdapter();
			}
			@Override
			public Adapter caseStopTrace(StopTrace object) {
				return createStopTraceAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Build <em>Build</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Build
	 * @generated
	 */
	public Adapter createBuildAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.CmdPC <em>Cmd PC</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.CmdPC
	 * @generated
	 */
	public Adapter createCmdPCAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.CmdSymbian <em>Cmd Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.CmdSymbian
	 * @generated
	 */
	public Adapter createCmdSymbianAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Driver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Driver
	 * @generated
	 */
	public Adapter createDriverAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.DriverInfo <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.DriverInfo
	 * @generated
	 */
	public Adapter createDriverInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.ExecuteOnPC <em>Execute On PC</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.ExecuteOnPC
	 * @generated
	 */
	public Adapter createExecuteOnPCAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.ExecuteOnSymbian <em>Execute On Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.ExecuteOnSymbian
	 * @generated
	 */
	public Adapter createExecuteOnSymbianAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.FlashROM <em>Flash ROM</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.FlashROM
	 * @generated
	 */
	public Adapter createFlashROMAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Info <em>Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Info
	 * @generated
	 */
	public Adapter createInfoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.RetrieveFromSymbian <em>Retrieve From Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.RetrieveFromSymbian
	 * @generated
	 */
	public Adapter createRetrieveFromSymbianAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Rtest <em>Rtest</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Rtest
	 * @generated
	 */
	public Adapter createRtestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.TestCase
	 * @generated
	 */
	public Adapter createTestCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.TestCasesList <em>Test Cases List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.TestCasesList
	 * @generated
	 */
	public Adapter createTestCasesListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.TestExecuteScript <em>Test Execute Script</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.TestExecuteScript
	 * @generated
	 */
	public Adapter createTestExecuteScriptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.Transfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.Transfer
	 * @generated
	 */
	public Adapter createTransferAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.TransferToSymbian <em>Transfer To Symbian</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.TransferToSymbian
	 * @generated
	 */
	public Adapter createTransferToSymbianAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.StartTrace <em>Start Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.StartTrace
	 * @generated
	 */
	public Adapter createStartTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.symbian.driver.StopTrace <em>Stop Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.symbian.driver.StopTrace
	 * @generated
	 */
	public Adapter createStopTraceAdapter() {
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

} //DriverAdapterFactory
