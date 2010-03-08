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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.symbian.comment.CommentPackage;
import com.symbian.comment.impl.CommentPackageImpl;
import com.symbian.ini.IniPackage;
import com.symbian.ini.impl.IniPackageImpl;
import com.symbian.tef.script.AsyncDelay;
import com.symbian.tef.script.Command;
import com.symbian.tef.script.Concurrent;
import com.symbian.tef.script.Consecutive;
import com.symbian.tef.script.Container;
import com.symbian.tef.script.CreateObject;
import com.symbian.tef.script.Delay;
import com.symbian.tef.script.Leaf;
import com.symbian.tef.script.LoadServer;
import com.symbian.tef.script.LoadSuite;
import com.symbian.tef.script.Outstanding;
import com.symbian.tef.script.Pause;
import com.symbian.tef.script.Prefix;
import com.symbian.tef.script.Print;
import com.symbian.tef.script.Repeat;
import com.symbian.tef.script.RestoreObject;
import com.symbian.tef.script.RunProgram;
import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.RunUtils;
import com.symbian.tef.script.ScriptFactory;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SectionPesistance;
import com.symbian.tef.script.SharedActiveScheduler;
import com.symbian.tef.script.SharedData;
import com.symbian.tef.script.Store;
import com.symbian.tef.script.StoreActiveScheduler;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TefComment;
import com.symbian.tef.script.TefModel;
import com.symbian.tef.script.TestBlock;
import com.symbian.tef.script.TestCase;
import com.symbian.tef.script.TestStep;
import com.symbian.tef.script.UtilityCommand;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScriptPackageImpl extends EPackageImpl implements ScriptPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leafEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prefixEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repeatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass printEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loadSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loadServerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runUtilsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runProgramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runScriptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pauseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass consecutiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concurrentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sharedDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionPesistanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tefCommentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testBlockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass restoreObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outstandingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asyncDelayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sharedActiveSchedulerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storeActiveSchedulerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum utilityCommandEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.symbian.tef.script.ScriptPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ScriptPackageImpl() {
		super(eNS_URI, ScriptFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ScriptPackage init() {
		if (isInited) return (ScriptPackage)EPackage.Registry.INSTANCE.getEPackage(ScriptPackage.eNS_URI);

		// Obtain or create and register package
		ScriptPackageImpl theScriptPackage = (ScriptPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ScriptPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ScriptPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CommentPackageImpl theCommentPackage = (CommentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommentPackage.eNS_URI) instanceof CommentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommentPackage.eNS_URI) : CommentPackage.eINSTANCE);
		IniPackageImpl theIniPackage = (IniPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IniPackage.eNS_URI) instanceof IniPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IniPackage.eNS_URI) : IniPackage.eINSTANCE);

		// Create package meta-data objects
		theScriptPackage.createPackageContents();
		theCommentPackage.createPackageContents();
		theIniPackage.createPackageContents();

		// Initialize created meta-data
		theScriptPackage.initializePackageContents();
		theCommentPackage.initializePackageContents();
		theIniPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theScriptPackage.freeze();

		return theScriptPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefModel() {
		return tefModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTefModel_Tef() {
		return (EReference)tefModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTef() {
		return tefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Tef() {
		return (EReference)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeaf() {
		return leafEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCase() {
		return testCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_Name() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrefix() {
		return prefixEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepeat() {
		return repeatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepeat_Name() {
		return (EAttribute)repeatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestStep() {
		return testStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Server() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Timeout() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Method() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Error() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_PanicString() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_PanicCode() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Result() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_Heap() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestStep_OOM() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrint() {
		return printEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrint_Print() {
		return (EAttribute)printEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoadSuite() {
		return loadSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadSuite_Server() {
		return (EAttribute)loadSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadSuite_SharedData() {
		return (EAttribute)loadSuiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoadServer() {
		return loadServerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadServer_Server() {
		return (EAttribute)loadServerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLoadServer_SharedData() {
		return (EAttribute)loadServerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunUtils() {
		return runUtilsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunUtils_UtilityCommand() {
		return (EAttribute)runUtilsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunUtils_Param() {
		return (EAttribute)runUtilsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunProgram() {
		return runProgramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunProgram_Name() {
		return (EAttribute)runProgramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunProgram_Timeout() {
		return (EAttribute)runProgramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunProgram_WS() {
		return (EAttribute)runProgramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunScript() {
		return runScriptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunScript_Script() {
		return (EAttribute)runScriptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunScript_ScriptPersistance() {
		return (EAttribute)runScriptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPause() {
		return pauseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDelay() {
		return delayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelay_Timeout() {
		return (EAttribute)delayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConsecutive() {
		return consecutiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcurrent() {
		return concurrentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedData() {
		return sharedDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSectionPesistance() {
		return sectionPesistanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSectionPesistance_Section() {
		return (EReference)sectionPesistanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSectionPesistance_IniPersistance() {
		return (EAttribute)sectionPesistanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSectionPesistance_SectionPersistance() {
		return (EAttribute)sectionPesistanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTefComment() {
		return tefCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestBlock() {
		return testBlockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_Heap() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_Timeout() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_Server() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_IniFile() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_PanicCode() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestBlock_PanicString() {
		return (EAttribute)testBlockEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreateObject() {
		return createObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateObject_ObjectType() {
		return (EAttribute)createObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateObject_ObjectName() {
		return (EAttribute)createObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRestoreObject() {
		return restoreObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRestoreObject_ObjectType() {
		return (EAttribute)restoreObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRestoreObject_ObjectName() {
		return (EAttribute)restoreObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommand() {
		return commandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommand_Error() {
		return (EAttribute)commandEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommand_AsyncError() {
		return (EAttribute)commandEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommand_ObjectName() {
		return (EAttribute)commandEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommand_FunctionName() {
		return (EAttribute)commandEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommand_Section() {
		return (EReference)commandEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStore() {
		return storeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStore_Section() {
		return (EReference)storeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutstanding() {
		return outstandingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutstanding_PollInterval() {
		return (EAttribute)outstandingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutstanding_ObjectName() {
		return (EAttribute)outstandingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAsyncDelay() {
		return asyncDelayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAsyncDelay_Timeout() {
		return (EAttribute)asyncDelayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedActiveScheduler() {
		return sharedActiveSchedulerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStoreActiveScheduler() {
		return storeActiveSchedulerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUtilityCommand() {
		return utilityCommandEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptFactory getScriptFactory() {
		return (ScriptFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		tefModelEClass = createEClass(TEF_MODEL);
		createEReference(tefModelEClass, TEF_MODEL__TEF);

		tefEClass = createEClass(TEF);

		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__TEF);

		leafEClass = createEClass(LEAF);

		testCaseEClass = createEClass(TEST_CASE);
		createEAttribute(testCaseEClass, TEST_CASE__NAME);

		prefixEClass = createEClass(PREFIX);

		repeatEClass = createEClass(REPEAT);
		createEAttribute(repeatEClass, REPEAT__NAME);

		testStepEClass = createEClass(TEST_STEP);
		createEAttribute(testStepEClass, TEST_STEP__SERVER);
		createEAttribute(testStepEClass, TEST_STEP__TIMEOUT);
		createEAttribute(testStepEClass, TEST_STEP__METHOD);
		createEAttribute(testStepEClass, TEST_STEP__ERROR);
		createEAttribute(testStepEClass, TEST_STEP__PANIC_STRING);
		createEAttribute(testStepEClass, TEST_STEP__PANIC_CODE);
		createEAttribute(testStepEClass, TEST_STEP__RESULT);
		createEAttribute(testStepEClass, TEST_STEP__HEAP);
		createEAttribute(testStepEClass, TEST_STEP__OOM);

		printEClass = createEClass(PRINT);
		createEAttribute(printEClass, PRINT__PRINT);

		loadSuiteEClass = createEClass(LOAD_SUITE);
		createEAttribute(loadSuiteEClass, LOAD_SUITE__SERVER);
		createEAttribute(loadSuiteEClass, LOAD_SUITE__SHARED_DATA);

		loadServerEClass = createEClass(LOAD_SERVER);
		createEAttribute(loadServerEClass, LOAD_SERVER__SERVER);
		createEAttribute(loadServerEClass, LOAD_SERVER__SHARED_DATA);

		runUtilsEClass = createEClass(RUN_UTILS);
		createEAttribute(runUtilsEClass, RUN_UTILS__UTILITY_COMMAND);
		createEAttribute(runUtilsEClass, RUN_UTILS__PARAM);

		runProgramEClass = createEClass(RUN_PROGRAM);
		createEAttribute(runProgramEClass, RUN_PROGRAM__NAME);
		createEAttribute(runProgramEClass, RUN_PROGRAM__TIMEOUT);
		createEAttribute(runProgramEClass, RUN_PROGRAM__WS);

		runScriptEClass = createEClass(RUN_SCRIPT);
		createEAttribute(runScriptEClass, RUN_SCRIPT__SCRIPT);
		createEAttribute(runScriptEClass, RUN_SCRIPT__SCRIPT_PERSISTANCE);

		pauseEClass = createEClass(PAUSE);

		delayEClass = createEClass(DELAY);
		createEAttribute(delayEClass, DELAY__TIMEOUT);

		consecutiveEClass = createEClass(CONSECUTIVE);

		concurrentEClass = createEClass(CONCURRENT);

		sharedDataEClass = createEClass(SHARED_DATA);

		sectionPesistanceEClass = createEClass(SECTION_PESISTANCE);
		createEReference(sectionPesistanceEClass, SECTION_PESISTANCE__SECTION);
		createEAttribute(sectionPesistanceEClass, SECTION_PESISTANCE__INI_PERSISTANCE);
		createEAttribute(sectionPesistanceEClass, SECTION_PESISTANCE__SECTION_PERSISTANCE);

		tefCommentEClass = createEClass(TEF_COMMENT);

		testBlockEClass = createEClass(TEST_BLOCK);
		createEAttribute(testBlockEClass, TEST_BLOCK__HEAP);
		createEAttribute(testBlockEClass, TEST_BLOCK__TIMEOUT);
		createEAttribute(testBlockEClass, TEST_BLOCK__SERVER);
		createEAttribute(testBlockEClass, TEST_BLOCK__INI_FILE);
		createEAttribute(testBlockEClass, TEST_BLOCK__PANIC_CODE);
		createEAttribute(testBlockEClass, TEST_BLOCK__PANIC_STRING);

		createObjectEClass = createEClass(CREATE_OBJECT);
		createEAttribute(createObjectEClass, CREATE_OBJECT__OBJECT_TYPE);
		createEAttribute(createObjectEClass, CREATE_OBJECT__OBJECT_NAME);

		restoreObjectEClass = createEClass(RESTORE_OBJECT);
		createEAttribute(restoreObjectEClass, RESTORE_OBJECT__OBJECT_TYPE);
		createEAttribute(restoreObjectEClass, RESTORE_OBJECT__OBJECT_NAME);

		commandEClass = createEClass(COMMAND);
		createEAttribute(commandEClass, COMMAND__ERROR);
		createEAttribute(commandEClass, COMMAND__ASYNC_ERROR);
		createEAttribute(commandEClass, COMMAND__OBJECT_NAME);
		createEAttribute(commandEClass, COMMAND__FUNCTION_NAME);
		createEReference(commandEClass, COMMAND__SECTION);

		storeEClass = createEClass(STORE);
		createEReference(storeEClass, STORE__SECTION);

		outstandingEClass = createEClass(OUTSTANDING);
		createEAttribute(outstandingEClass, OUTSTANDING__POLL_INTERVAL);
		createEAttribute(outstandingEClass, OUTSTANDING__OBJECT_NAME);

		asyncDelayEClass = createEClass(ASYNC_DELAY);
		createEAttribute(asyncDelayEClass, ASYNC_DELAY__TIMEOUT);

		sharedActiveSchedulerEClass = createEClass(SHARED_ACTIVE_SCHEDULER);

		storeActiveSchedulerEClass = createEClass(STORE_ACTIVE_SCHEDULER);

		// Create enums
		utilityCommandEEnum = createEEnum(UTILITY_COMMAND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommentPackage theCommentPackage = (CommentPackage)EPackage.Registry.INSTANCE.getEPackage(CommentPackage.eNS_URI);
		IniPackage theIniPackage = (IniPackage)EPackage.Registry.INSTANCE.getEPackage(IniPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		containerEClass.getESuperTypes().add(this.getTef());
		leafEClass.getESuperTypes().add(this.getTef());
		testCaseEClass.getESuperTypes().add(theCommentPackage.getAttachedComment());
		testCaseEClass.getESuperTypes().add(this.getContainer());
		prefixEClass.getESuperTypes().add(this.getContainer());
		repeatEClass.getESuperTypes().add(this.getSectionPesistance());
		repeatEClass.getESuperTypes().add(this.getContainer());
		testStepEClass.getESuperTypes().add(theCommentPackage.getAttachedComment());
		testStepEClass.getESuperTypes().add(this.getSectionPesistance());
		testStepEClass.getESuperTypes().add(this.getLeaf());
		printEClass.getESuperTypes().add(this.getLeaf());
		loadSuiteEClass.getESuperTypes().add(this.getLeaf());
		loadServerEClass.getESuperTypes().add(this.getLeaf());
		runUtilsEClass.getESuperTypes().add(this.getLeaf());
		runProgramEClass.getESuperTypes().add(this.getLeaf());
		runScriptEClass.getESuperTypes().add(this.getLeaf());
		pauseEClass.getESuperTypes().add(this.getLeaf());
		delayEClass.getESuperTypes().add(this.getLeaf());
		consecutiveEClass.getESuperTypes().add(this.getLeaf());
		concurrentEClass.getESuperTypes().add(this.getLeaf());
		sharedDataEClass.getESuperTypes().add(this.getSectionPesistance());
		sharedDataEClass.getESuperTypes().add(this.getLeaf());
		tefCommentEClass.getESuperTypes().add(theCommentPackage.getComment());
		tefCommentEClass.getESuperTypes().add(this.getLeaf());
		testBlockEClass.getESuperTypes().add(this.getContainer());
		createObjectEClass.getESuperTypes().add(this.getLeaf());
		restoreObjectEClass.getESuperTypes().add(this.getLeaf());
		commandEClass.getESuperTypes().add(this.getLeaf());
		storeEClass.getESuperTypes().add(this.getLeaf());
		outstandingEClass.getESuperTypes().add(this.getLeaf());
		asyncDelayEClass.getESuperTypes().add(this.getLeaf());
		sharedActiveSchedulerEClass.getESuperTypes().add(this.getLeaf());
		storeActiveSchedulerEClass.getESuperTypes().add(this.getLeaf());

		// Initialize classes and features; add operations and parameters
		initEClass(tefModelEClass, TefModel.class, "TefModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTefModel_Tef(), this.getTef(), null, "tef", null, 1, -1, TefModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefEClass, Tef.class, "Tef", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, Container.class, "Container", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_Tef(), this.getTef(), null, "tef", null, 0, -1, Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(leafEClass, Leaf.class, "Leaf", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCase_Name(), ecorePackage.getEString(), "name", null, 1, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prefixEClass, Prefix.class, "Prefix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(repeatEClass, Repeat.class, "Repeat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepeat_Name(), ecorePackage.getEString(), "name", null, 1, 1, Repeat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testStepEClass, TestStep.class, "TestStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestStep_Server(), ecorePackage.getEString(), "server", null, 1, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_Timeout(), ecorePackage.getEInt(), "timeout", "100", 1, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_Method(), ecorePackage.getEString(), "method", null, 1, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_Error(), ecorePackage.getEString(), "Error", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_PanicString(), ecorePackage.getEString(), "PanicString", "", 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_PanicCode(), ecorePackage.getEInt(), "PanicCode", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_Result(), ecorePackage.getEString(), "Result", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_Heap(), ecorePackage.getEInt(), "Heap", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestStep_OOM(), ecorePackage.getEInt(), "OOM", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(printEClass, Print.class, "Print", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrint_Print(), ecorePackage.getEString(), "print", null, 1, 1, Print.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loadSuiteEClass, LoadSuite.class, "LoadSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLoadSuite_Server(), ecorePackage.getEString(), "server", null, 1, 1, LoadSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadSuite_SharedData(), ecorePackage.getEBoolean(), "sharedData", null, 1, 1, LoadSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loadServerEClass, LoadServer.class, "LoadServer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLoadServer_Server(), ecorePackage.getEString(), "server", null, 1, 1, LoadServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLoadServer_SharedData(), ecorePackage.getEBoolean(), "sharedData", null, 1, 1, LoadServer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runUtilsEClass, RunUtils.class, "RunUtils", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunUtils_UtilityCommand(), this.getUtilityCommand(), "utilityCommand", null, 1, 1, RunUtils.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRunUtils_Param(), ecorePackage.getEString(), "param", null, 1, -1, RunUtils.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runProgramEClass, RunProgram.class, "RunProgram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunProgram_Name(), ecorePackage.getEString(), "name", null, 1, 1, RunProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRunProgram_Timeout(), ecorePackage.getEInt(), "Timeout", null, 1, 1, RunProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRunProgram_WS(), ecorePackage.getEBoolean(), "WS", null, 1, 1, RunProgram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runScriptEClass, RunScript.class, "RunScript", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunScript_Script(), ecorePackage.getEResource(), "script", null, 1, 1, RunScript.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRunScript_ScriptPersistance(), ecorePackage.getEString(), "scriptPersistance", null, 1, 1, RunScript.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pauseEClass, Pause.class, "Pause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(delayEClass, Delay.class, "Delay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDelay_Timeout(), ecorePackage.getEInt(), "timeout", null, 1, 1, Delay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(consecutiveEClass, Consecutive.class, "Consecutive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(concurrentEClass, Concurrent.class, "Concurrent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sharedDataEClass, SharedData.class, "SharedData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sectionPesistanceEClass, SectionPesistance.class, "SectionPesistance", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSectionPesistance_Section(), theIniPackage.getSection(), null, "section", null, 1, 1, SectionPesistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSectionPesistance_IniPersistance(), ecorePackage.getEString(), "iniPersistance", null, 1, 1, SectionPesistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSectionPesistance_SectionPersistance(), ecorePackage.getEString(), "sectionPersistance", null, 1, 1, SectionPesistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tefCommentEClass, TefComment.class, "TefComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testBlockEClass, TestBlock.class, "TestBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestBlock_Heap(), ecorePackage.getEIntegerObject(), "Heap", "1048576", 0, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestBlock_Timeout(), ecorePackage.getEInt(), "timeout", "100", 1, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestBlock_Server(), ecorePackage.getEString(), "server", null, 1, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestBlock_IniFile(), ecorePackage.getEString(), "iniFile", null, 1, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestBlock_PanicCode(), ecorePackage.getEInt(), "PanicCode", null, 0, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestBlock_PanicString(), ecorePackage.getEString(), "PanicString", null, 0, 1, TestBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createObjectEClass, CreateObject.class, "CreateObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCreateObject_ObjectType(), ecorePackage.getEString(), "objectType", null, 1, 1, CreateObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCreateObject_ObjectName(), ecorePackage.getEString(), "objectName", null, 1, 1, CreateObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(restoreObjectEClass, RestoreObject.class, "RestoreObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRestoreObject_ObjectType(), ecorePackage.getEString(), "objectType", null, 1, 1, RestoreObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRestoreObject_ObjectName(), ecorePackage.getEString(), "objectName", null, 1, 1, RestoreObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commandEClass, Command.class, "Command", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCommand_Error(), ecorePackage.getEIntegerObject(), "Error", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommand_AsyncError(), ecorePackage.getEIntegerObject(), "AsyncError", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommand_ObjectName(), ecorePackage.getEString(), "objectName", null, 1, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCommand_FunctionName(), ecorePackage.getEString(), "functionName", null, 1, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommand_Section(), theIniPackage.getSection(), null, "section", null, 0, 1, Command.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(storeEClass, Store.class, "Store", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStore_Section(), theIniPackage.getSection(), null, "section", null, 1, 1, Store.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outstandingEClass, Outstanding.class, "Outstanding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutstanding_PollInterval(), ecorePackage.getEIntegerObject(), "pollInterval", null, 0, 1, Outstanding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutstanding_ObjectName(), ecorePackage.getEString(), "objectName", null, 0, 1, Outstanding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asyncDelayEClass, AsyncDelay.class, "AsyncDelay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAsyncDelay_Timeout(), ecorePackage.getEInt(), "timeout", null, 1, 1, AsyncDelay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sharedActiveSchedulerEClass, SharedActiveScheduler.class, "SharedActiveScheduler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(storeActiveSchedulerEClass, StoreActiveScheduler.class, "StoreActiveScheduler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(utilityCommandEEnum, UtilityCommand.class, "UtilityCommand");
		addEEnumLiteral(utilityCommandEEnum, UtilityCommand.MAKE_READ_WRITE);
		addEEnumLiteral(utilityCommandEEnum, UtilityCommand.DELETE_FILE);
		addEEnumLiteral(utilityCommandEEnum, UtilityCommand.DELETE_DIRECTORY);
		addEEnumLiteral(utilityCommandEEnum, UtilityCommand.COPY_FILE);
		addEEnumLiteral(utilityCommandEEnum, UtilityCommand.MK_DIR);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getTestStep_Error(), 
		   source, 
		   new String[] {
			 "pattern", "\\S+"
		   });		
		addAnnotation
		  (getTestStep_PanicString(), 
		   source, 
		   new String[] {
			 "pattern", "\\S+"
		   });		
		addAnnotation
		  (getTestStep_Result(), 
		   source, 
		   new String[] {
			 "pattern", "\\S+"
		   });
	}

} //ScriptPackageImpl
