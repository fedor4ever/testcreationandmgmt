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

import java.util.List;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.symbian.tef.script.ScriptPackage
 * @generated
 */
public class ScriptSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ScriptPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptSwitch() {
		if (modelPackage == null) {
			modelPackage = ScriptPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ScriptPackage.TEF_MODEL: {
				TefModel tefModel = (TefModel)theEObject;
				T result = caseTefModel(tefModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.TEF: {
				Tef tef = (Tef)theEObject;
				T result = caseTef(tef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T result = caseContainer(container);
				if (result == null) result = caseTef(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.LEAF: {
				Leaf leaf = (Leaf)theEObject;
				T result = caseLeaf(leaf);
				if (result == null) result = caseTef(leaf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.TEST_CASE: {
				TestCase testCase = (TestCase)theEObject;
				T result = caseTestCase(testCase);
				if (result == null) result = caseAttachedComment(testCase);
				if (result == null) result = caseContainer(testCase);
				if (result == null) result = caseTef(testCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.PREFIX: {
				Prefix prefix = (Prefix)theEObject;
				T result = casePrefix(prefix);
				if (result == null) result = caseContainer(prefix);
				if (result == null) result = caseTef(prefix);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.REPEAT: {
				Repeat repeat = (Repeat)theEObject;
				T result = caseRepeat(repeat);
				if (result == null) result = caseSectionPesistance(repeat);
				if (result == null) result = caseContainer(repeat);
				if (result == null) result = caseTef(repeat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.TEST_STEP: {
				TestStep testStep = (TestStep)theEObject;
				T result = caseTestStep(testStep);
				if (result == null) result = caseAttachedComment(testStep);
				if (result == null) result = caseSectionPesistance(testStep);
				if (result == null) result = caseLeaf(testStep);
				if (result == null) result = caseTef(testStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.PRINT: {
				Print print = (Print)theEObject;
				T result = casePrint(print);
				if (result == null) result = caseLeaf(print);
				if (result == null) result = caseTef(print);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.LOAD_SUITE: {
				LoadSuite loadSuite = (LoadSuite)theEObject;
				T result = caseLoadSuite(loadSuite);
				if (result == null) result = caseLeaf(loadSuite);
				if (result == null) result = caseTef(loadSuite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.LOAD_SERVER: {
				LoadServer loadServer = (LoadServer)theEObject;
				T result = caseLoadServer(loadServer);
				if (result == null) result = caseLeaf(loadServer);
				if (result == null) result = caseTef(loadServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.RUN_UTILS: {
				RunUtils runUtils = (RunUtils)theEObject;
				T result = caseRunUtils(runUtils);
				if (result == null) result = caseLeaf(runUtils);
				if (result == null) result = caseTef(runUtils);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.RUN_PROGRAM: {
				RunProgram runProgram = (RunProgram)theEObject;
				T result = caseRunProgram(runProgram);
				if (result == null) result = caseLeaf(runProgram);
				if (result == null) result = caseTef(runProgram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.RUN_SCRIPT: {
				RunScript runScript = (RunScript)theEObject;
				T result = caseRunScript(runScript);
				if (result == null) result = caseLeaf(runScript);
				if (result == null) result = caseTef(runScript);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.PAUSE: {
				Pause pause = (Pause)theEObject;
				T result = casePause(pause);
				if (result == null) result = caseLeaf(pause);
				if (result == null) result = caseTef(pause);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.DELAY: {
				Delay delay = (Delay)theEObject;
				T result = caseDelay(delay);
				if (result == null) result = caseLeaf(delay);
				if (result == null) result = caseTef(delay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.CONSECUTIVE: {
				Consecutive consecutive = (Consecutive)theEObject;
				T result = caseConsecutive(consecutive);
				if (result == null) result = caseLeaf(consecutive);
				if (result == null) result = caseTef(consecutive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.CONCURRENT: {
				Concurrent concurrent = (Concurrent)theEObject;
				T result = caseConcurrent(concurrent);
				if (result == null) result = caseLeaf(concurrent);
				if (result == null) result = caseTef(concurrent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.SHARED_DATA: {
				SharedData sharedData = (SharedData)theEObject;
				T result = caseSharedData(sharedData);
				if (result == null) result = caseSectionPesistance(sharedData);
				if (result == null) result = caseLeaf(sharedData);
				if (result == null) result = caseTef(sharedData);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.SECTION_PESISTANCE: {
				SectionPesistance sectionPesistance = (SectionPesistance)theEObject;
				T result = caseSectionPesistance(sectionPesistance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.TEF_COMMENT: {
				TefComment tefComment = (TefComment)theEObject;
				T result = caseTefComment(tefComment);
				if (result == null) result = caseComment(tefComment);
				if (result == null) result = caseLeaf(tefComment);
				if (result == null) result = caseTef(tefComment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.TEST_BLOCK: {
				TestBlock testBlock = (TestBlock)theEObject;
				T result = caseTestBlock(testBlock);
				if (result == null) result = caseContainer(testBlock);
				if (result == null) result = caseTef(testBlock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.CREATE_OBJECT: {
				CreateObject createObject = (CreateObject)theEObject;
				T result = caseCreateObject(createObject);
				if (result == null) result = caseLeaf(createObject);
				if (result == null) result = caseTef(createObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.RESTORE_OBJECT: {
				RestoreObject restoreObject = (RestoreObject)theEObject;
				T result = caseRestoreObject(restoreObject);
				if (result == null) result = caseLeaf(restoreObject);
				if (result == null) result = caseTef(restoreObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.COMMAND: {
				Command command = (Command)theEObject;
				T result = caseCommand(command);
				if (result == null) result = caseLeaf(command);
				if (result == null) result = caseTef(command);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.STORE: {
				Store store = (Store)theEObject;
				T result = caseStore(store);
				if (result == null) result = caseLeaf(store);
				if (result == null) result = caseTef(store);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.OUTSTANDING: {
				Outstanding outstanding = (Outstanding)theEObject;
				T result = caseOutstanding(outstanding);
				if (result == null) result = caseLeaf(outstanding);
				if (result == null) result = caseTef(outstanding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.ASYNC_DELAY: {
				AsyncDelay asyncDelay = (AsyncDelay)theEObject;
				T result = caseAsyncDelay(asyncDelay);
				if (result == null) result = caseLeaf(asyncDelay);
				if (result == null) result = caseTef(asyncDelay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.SHARED_ACTIVE_SCHEDULER: {
				SharedActiveScheduler sharedActiveScheduler = (SharedActiveScheduler)theEObject;
				T result = caseSharedActiveScheduler(sharedActiveScheduler);
				if (result == null) result = caseLeaf(sharedActiveScheduler);
				if (result == null) result = caseTef(sharedActiveScheduler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScriptPackage.STORE_ACTIVE_SCHEDULER: {
				StoreActiveScheduler storeActiveScheduler = (StoreActiveScheduler)theEObject;
				T result = caseStoreActiveScheduler(storeActiveScheduler);
				if (result == null) result = caseLeaf(storeActiveScheduler);
				if (result == null) result = caseTef(storeActiveScheduler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTefModel(TefModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTef(Tef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer(Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Leaf</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Leaf</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLeaf(Leaf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestCase(TestCase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prefix</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prefix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrefix(Prefix object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repeat</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repeat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepeat(Repeat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestStep(TestStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Print</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Print</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrint(Print object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Load Suite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load Suite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoadSuite(LoadSuite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Load Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Load Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLoadServer(LoadServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run Utils</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Utils</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunUtils(RunUtils object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run Program</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Program</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunProgram(RunProgram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run Script</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Script</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunScript(RunScript object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pause</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pause</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePause(Pause object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delay</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delay</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDelay(Delay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Consecutive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Consecutive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConsecutive(Consecutive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concurrent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concurrent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcurrent(Concurrent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shared Data</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shared Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSharedData(SharedData object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Section Pesistance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Section Pesistance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSectionPesistance(SectionPesistance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tef Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tef Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTefComment(TefComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestBlock(TestBlock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Create Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Create Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCreateObject(CreateObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Restore Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Restore Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRestoreObject(RestoreObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommand(Command object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Store</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Store</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStore(Store object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outstanding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outstanding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutstanding(Outstanding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Async Delay</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Async Delay</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsyncDelay(AsyncDelay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shared Active Scheduler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shared Active Scheduler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSharedActiveScheduler(SharedActiveScheduler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Store Active Scheduler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Store Active Scheduler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStoreActiveScheduler(StoreActiveScheduler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attached Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attached Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttachedComment(AttachedComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ScriptSwitch
