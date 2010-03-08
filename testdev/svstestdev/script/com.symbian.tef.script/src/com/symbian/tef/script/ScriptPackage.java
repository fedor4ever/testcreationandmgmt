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

import com.symbian.comment.CommentPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.symbian.tef.script.ScriptFactory
 * @model kind="package"
 * @generated
 */
public interface ScriptPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "script";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.symbian.com/tef";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tef.script";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScriptPackage eINSTANCE = com.symbian.tef.script.impl.ScriptPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.TefModelImpl <em>Tef Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.TefModelImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTefModel()
	 * @generated
	 */
	int TEF_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_MODEL__TEF = 0;

	/**
	 * The number of structural features of the '<em>Tef Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.Tef <em>Tef</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.Tef
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTef()
	 * @generated
	 */
	int TEF = 1;

	/**
	 * The number of structural features of the '<em>Tef</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.Container <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.Container
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__TEF = TEF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = TEF_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.Leaf <em>Leaf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.Leaf
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLeaf()
	 * @generated
	 */
	int LEAF = 3;

	/**
	 * The number of structural features of the '<em>Leaf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_FEATURE_COUNT = TEF_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.TestCaseImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 4;

	/**
	 * The feature id for the '<em><b>Attached Comment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ATTACHED_COMMENT = CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT;

	/**
	 * The feature id for the '<em><b>Attached Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ATTACHED_TAG = CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TEF = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__NAME = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.PrefixImpl <em>Prefix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.PrefixImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPrefix()
	 * @generated
	 */
	int PREFIX = 5;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX__TEF = CONTAINER__TEF;

	/**
	 * The number of structural features of the '<em>Prefix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.RepeatImpl <em>Repeat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.RepeatImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRepeat()
	 * @generated
	 */
	int REPEAT = 6;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.TestStepImpl <em>Test Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.TestStepImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestStep()
	 * @generated
	 */
	int TEST_STEP = 7;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.PrintImpl <em>Print</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.PrintImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPrint()
	 * @generated
	 */
	int PRINT = 8;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.LoadSuiteImpl <em>Load Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.LoadSuiteImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLoadSuite()
	 * @generated
	 */
	int LOAD_SUITE = 9;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.LoadServerImpl <em>Load Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.LoadServerImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLoadServer()
	 * @generated
	 */
	int LOAD_SERVER = 10;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.RunUtilsImpl <em>Run Utils</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.RunUtilsImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunUtils()
	 * @generated
	 */
	int RUN_UTILS = 11;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.RunProgramImpl <em>Run Program</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.RunProgramImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunProgram()
	 * @generated
	 */
	int RUN_PROGRAM = 12;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.RunScriptImpl <em>Run Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.RunScriptImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunScript()
	 * @generated
	 */
	int RUN_SCRIPT = 13;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.PauseImpl <em>Pause</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.PauseImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPause()
	 * @generated
	 */
	int PAUSE = 14;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.DelayImpl <em>Delay</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.DelayImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getDelay()
	 * @generated
	 */
	int DELAY = 15;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.ConsecutiveImpl <em>Consecutive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.ConsecutiveImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getConsecutive()
	 * @generated
	 */
	int CONSECUTIVE = 16;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.ConcurrentImpl <em>Concurrent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.ConcurrentImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getConcurrent()
	 * @generated
	 */
	int CONCURRENT = 17;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.SharedDataImpl <em>Shared Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.SharedDataImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSharedData()
	 * @generated
	 */
	int SHARED_DATA = 18;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.SectionPesistance <em>Section Pesistance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.SectionPesistance
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSectionPesistance()
	 * @generated
	 */
	int SECTION_PESISTANCE = 19;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_PESISTANCE__SECTION = 0;

	/**
	 * The feature id for the '<em><b>Ini Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_PESISTANCE__INI_PERSISTANCE = 1;

	/**
	 * The feature id for the '<em><b>Section Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_PESISTANCE__SECTION_PERSISTANCE = 2;

	/**
	 * The number of structural features of the '<em>Section Pesistance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_PESISTANCE_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT__SECTION = SECTION_PESISTANCE__SECTION;

	/**
	 * The feature id for the '<em><b>Ini Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT__INI_PERSISTANCE = SECTION_PESISTANCE__INI_PERSISTANCE;

	/**
	 * The feature id for the '<em><b>Section Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT__SECTION_PERSISTANCE = SECTION_PESISTANCE__SECTION_PERSISTANCE;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT__TEF = SECTION_PESISTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT__NAME = SECTION_PESISTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Repeat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPEAT_FEATURE_COUNT = SECTION_PESISTANCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Attached Comment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__ATTACHED_COMMENT = CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT;

	/**
	 * The feature id for the '<em><b>Attached Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__ATTACHED_TAG = CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__SECTION = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ini Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__INI_PERSISTANCE = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Section Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__SECTION_PERSISTANCE = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__SERVER = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__TIMEOUT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__METHOD = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__ERROR = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Panic String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__PANIC_STRING = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Panic Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__PANIC_CODE = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__RESULT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Heap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__HEAP = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>OOM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP__OOM = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Test Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_STEP_FEATURE_COUNT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Print</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINT__PRINT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Print</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINT_FEATURE_COUNT = LEAF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SUITE__SERVER = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shared Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SUITE__SHARED_DATA = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Load Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SUITE_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SERVER__SERVER = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shared Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SERVER__SHARED_DATA = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Load Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOAD_SERVER_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Utility Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_UTILS__UTILITY_COMMAND = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Param</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_UTILS__PARAM = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Run Utils</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_UTILS_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_PROGRAM__NAME = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_PROGRAM__TIMEOUT = LEAF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>WS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_PROGRAM__WS = LEAF_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Run Program</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_PROGRAM_FEATURE_COUNT = LEAF_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_SCRIPT__SCRIPT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Script Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_SCRIPT__SCRIPT_PERSISTANCE = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Run Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_SCRIPT_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Pause</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAUSE_FEATURE_COUNT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELAY__TIMEOUT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Delay</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELAY_FEATURE_COUNT = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Consecutive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSECUTIVE_FEATURE_COUNT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Concurrent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCURRENT_FEATURE_COUNT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_DATA__SECTION = SECTION_PESISTANCE__SECTION;

	/**
	 * The feature id for the '<em><b>Ini Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_DATA__INI_PERSISTANCE = SECTION_PESISTANCE__INI_PERSISTANCE;

	/**
	 * The feature id for the '<em><b>Section Persistance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_DATA__SECTION_PERSISTANCE = SECTION_PESISTANCE__SECTION_PERSISTANCE;

	/**
	 * The number of structural features of the '<em>Shared Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_DATA_FEATURE_COUNT = SECTION_PESISTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.TefCommentImpl <em>Tef Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.TefCommentImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTefComment()
	 * @generated
	 */
	int TEF_COMMENT = 20;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_COMMENT__COMMENT = CommentPackage.COMMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_COMMENT__TAG = CommentPackage.COMMENT__TAG;

	/**
	 * The number of structural features of the '<em>Tef Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEF_COMMENT_FEATURE_COUNT = CommentPackage.COMMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.TestBlockImpl <em>Test Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.TestBlockImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestBlock()
	 * @generated
	 */
	int TEST_BLOCK = 21;

	/**
	 * The feature id for the '<em><b>Tef</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__TEF = CONTAINER__TEF;

	/**
	 * The feature id for the '<em><b>Heap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__HEAP = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__TIMEOUT = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__SERVER = CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ini File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__INI_FILE = CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Panic Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__PANIC_CODE = CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Panic String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK__PANIC_STRING = CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Test Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_BLOCK_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.CreateObjectImpl <em>Create Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.CreateObjectImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getCreateObject()
	 * @generated
	 */
	int CREATE_OBJECT = 22;

	/**
	 * The feature id for the '<em><b>Object Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OBJECT__OBJECT_TYPE = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Object Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OBJECT__OBJECT_NAME = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Create Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_OBJECT_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.RestoreObjectImpl <em>Restore Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.RestoreObjectImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRestoreObject()
	 * @generated
	 */
	int RESTORE_OBJECT = 23;

	/**
	 * The feature id for the '<em><b>Object Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESTORE_OBJECT__OBJECT_TYPE = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Object Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESTORE_OBJECT__OBJECT_NAME = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Restore Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESTORE_OBJECT_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.CommandImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 24;

	/**
	 * The feature id for the '<em><b>Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ERROR = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Async Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ASYNC_ERROR = LEAF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Object Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__OBJECT_NAME = LEAF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Function Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__FUNCTION_NAME = LEAF_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__SECTION = LEAF_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = LEAF_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.StoreImpl <em>Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.StoreImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getStore()
	 * @generated
	 */
	int STORE = 25;

	/**
	 * The feature id for the '<em><b>Section</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORE__SECTION = LEAF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORE_FEATURE_COUNT = LEAF_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.OutstandingImpl <em>Outstanding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.OutstandingImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getOutstanding()
	 * @generated
	 */
	int OUTSTANDING = 26;

	/**
	 * The feature id for the '<em><b>Poll Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTSTANDING__POLL_INTERVAL = LEAF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Object Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTSTANDING__OBJECT_NAME = LEAF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Outstanding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTSTANDING_FEATURE_COUNT = LEAF_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.AsyncDelayImpl <em>Async Delay</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.AsyncDelayImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getAsyncDelay()
	 * @generated
	 */
	int ASYNC_DELAY = 27;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNC_DELAY__TIMEOUT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Async Delay</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASYNC_DELAY_FEATURE_COUNT = LEAF_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.SharedActiveSchedulerImpl <em>Shared Active Scheduler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.SharedActiveSchedulerImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSharedActiveScheduler()
	 * @generated
	 */
	int SHARED_ACTIVE_SCHEDULER = 28;

	/**
	 * The number of structural features of the '<em>Shared Active Scheduler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_ACTIVE_SCHEDULER_FEATURE_COUNT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.impl.StoreActiveSchedulerImpl <em>Store Active Scheduler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.impl.StoreActiveSchedulerImpl
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getStoreActiveScheduler()
	 * @generated
	 */
	int STORE_ACTIVE_SCHEDULER = 29;

	/**
	 * The number of structural features of the '<em>Store Active Scheduler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORE_ACTIVE_SCHEDULER_FEATURE_COUNT = LEAF_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.symbian.tef.script.UtilityCommand <em>Utility Command</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.tef.script.UtilityCommand
	 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getUtilityCommand()
	 * @generated
	 */
	int UTILITY_COMMAND = 30;


	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.TefModel <em>Tef Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Model</em>'.
	 * @see com.symbian.tef.script.TefModel
	 * @generated
	 */
	EClass getTefModel();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.tef.script.TefModel#getTef <em>Tef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tef</em>'.
	 * @see com.symbian.tef.script.TefModel#getTef()
	 * @see #getTefModel()
	 * @generated
	 */
	EReference getTefModel_Tef();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Tef <em>Tef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef</em>'.
	 * @see com.symbian.tef.script.Tef
	 * @generated
	 */
	EClass getTef();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see com.symbian.tef.script.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.tef.script.Container#getTef <em>Tef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tef</em>'.
	 * @see com.symbian.tef.script.Container#getTef()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Tef();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Leaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf</em>'.
	 * @see com.symbian.tef.script.Leaf
	 * @generated
	 */
	EClass getLeaf();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case</em>'.
	 * @see com.symbian.tef.script.TestCase
	 * @generated
	 */
	EClass getTestCase();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.tef.script.TestCase#getName()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_Name();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Prefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefix</em>'.
	 * @see com.symbian.tef.script.Prefix
	 * @generated
	 */
	EClass getPrefix();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Repeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repeat</em>'.
	 * @see com.symbian.tef.script.Repeat
	 * @generated
	 */
	EClass getRepeat();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Repeat#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.tef.script.Repeat#getName()
	 * @see #getRepeat()
	 * @generated
	 */
	EAttribute getRepeat_Name();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.TestStep <em>Test Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Step</em>'.
	 * @see com.symbian.tef.script.TestStep
	 * @generated
	 */
	EClass getTestStep();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.symbian.tef.script.TestStep#getServer()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.tef.script.TestStep#getTimeout()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see com.symbian.tef.script.TestStep#getMethod()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Method();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getError <em>Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error</em>'.
	 * @see com.symbian.tef.script.TestStep#getError()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Error();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getPanicString <em>Panic String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic String</em>'.
	 * @see com.symbian.tef.script.TestStep#getPanicString()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_PanicString();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getPanicCode <em>Panic Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic Code</em>'.
	 * @see com.symbian.tef.script.TestStep#getPanicCode()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_PanicCode();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.symbian.tef.script.TestStep#getResult()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Result();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getHeap <em>Heap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Heap</em>'.
	 * @see com.symbian.tef.script.TestStep#getHeap()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_Heap();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestStep#getOOM <em>OOM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>OOM</em>'.
	 * @see com.symbian.tef.script.TestStep#getOOM()
	 * @see #getTestStep()
	 * @generated
	 */
	EAttribute getTestStep_OOM();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Print <em>Print</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Print</em>'.
	 * @see com.symbian.tef.script.Print
	 * @generated
	 */
	EClass getPrint();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Print#getPrint <em>Print</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Print</em>'.
	 * @see com.symbian.tef.script.Print#getPrint()
	 * @see #getPrint()
	 * @generated
	 */
	EAttribute getPrint_Print();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.LoadSuite <em>Load Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load Suite</em>'.
	 * @see com.symbian.tef.script.LoadSuite
	 * @generated
	 */
	EClass getLoadSuite();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.LoadSuite#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.symbian.tef.script.LoadSuite#getServer()
	 * @see #getLoadSuite()
	 * @generated
	 */
	EAttribute getLoadSuite_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.LoadSuite#isSharedData <em>Shared Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared Data</em>'.
	 * @see com.symbian.tef.script.LoadSuite#isSharedData()
	 * @see #getLoadSuite()
	 * @generated
	 */
	EAttribute getLoadSuite_SharedData();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.LoadServer <em>Load Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Load Server</em>'.
	 * @see com.symbian.tef.script.LoadServer
	 * @generated
	 */
	EClass getLoadServer();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.LoadServer#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.symbian.tef.script.LoadServer#getServer()
	 * @see #getLoadServer()
	 * @generated
	 */
	EAttribute getLoadServer_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.LoadServer#isSharedData <em>Shared Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared Data</em>'.
	 * @see com.symbian.tef.script.LoadServer#isSharedData()
	 * @see #getLoadServer()
	 * @generated
	 */
	EAttribute getLoadServer_SharedData();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.RunUtils <em>Run Utils</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Run Utils</em>'.
	 * @see com.symbian.tef.script.RunUtils
	 * @generated
	 */
	EClass getRunUtils();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunUtils#getUtilityCommand <em>Utility Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utility Command</em>'.
	 * @see com.symbian.tef.script.RunUtils#getUtilityCommand()
	 * @see #getRunUtils()
	 * @generated
	 */
	EAttribute getRunUtils_UtilityCommand();

	/**
	 * Returns the meta object for the attribute list '{@link com.symbian.tef.script.RunUtils#getParam <em>Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Param</em>'.
	 * @see com.symbian.tef.script.RunUtils#getParam()
	 * @see #getRunUtils()
	 * @generated
	 */
	EAttribute getRunUtils_Param();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.RunProgram <em>Run Program</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Run Program</em>'.
	 * @see com.symbian.tef.script.RunProgram
	 * @generated
	 */
	EClass getRunProgram();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunProgram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.tef.script.RunProgram#getName()
	 * @see #getRunProgram()
	 * @generated
	 */
	EAttribute getRunProgram_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunProgram#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.tef.script.RunProgram#getTimeout()
	 * @see #getRunProgram()
	 * @generated
	 */
	EAttribute getRunProgram_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunProgram#isWS <em>WS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>WS</em>'.
	 * @see com.symbian.tef.script.RunProgram#isWS()
	 * @see #getRunProgram()
	 * @generated
	 */
	EAttribute getRunProgram_WS();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.RunScript <em>Run Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Run Script</em>'.
	 * @see com.symbian.tef.script.RunScript
	 * @generated
	 */
	EClass getRunScript();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunScript#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script</em>'.
	 * @see com.symbian.tef.script.RunScript#getScript()
	 * @see #getRunScript()
	 * @generated
	 */
	EAttribute getRunScript_Script();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RunScript#getScriptPersistance <em>Script Persistance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script Persistance</em>'.
	 * @see com.symbian.tef.script.RunScript#getScriptPersistance()
	 * @see #getRunScript()
	 * @generated
	 */
	EAttribute getRunScript_ScriptPersistance();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Pause <em>Pause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pause</em>'.
	 * @see com.symbian.tef.script.Pause
	 * @generated
	 */
	EClass getPause();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Delay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delay</em>'.
	 * @see com.symbian.tef.script.Delay
	 * @generated
	 */
	EClass getDelay();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Delay#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.tef.script.Delay#getTimeout()
	 * @see #getDelay()
	 * @generated
	 */
	EAttribute getDelay_Timeout();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Consecutive <em>Consecutive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consecutive</em>'.
	 * @see com.symbian.tef.script.Consecutive
	 * @generated
	 */
	EClass getConsecutive();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Concurrent <em>Concurrent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concurrent</em>'.
	 * @see com.symbian.tef.script.Concurrent
	 * @generated
	 */
	EClass getConcurrent();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.SharedData <em>Shared Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shared Data</em>'.
	 * @see com.symbian.tef.script.SharedData
	 * @generated
	 */
	EClass getSharedData();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.SectionPesistance <em>Section Pesistance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section Pesistance</em>'.
	 * @see com.symbian.tef.script.SectionPesistance
	 * @generated
	 */
	EClass getSectionPesistance();

	/**
	 * Returns the meta object for the reference '{@link com.symbian.tef.script.SectionPesistance#getSection <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Section</em>'.
	 * @see com.symbian.tef.script.SectionPesistance#getSection()
	 * @see #getSectionPesistance()
	 * @generated
	 */
	EReference getSectionPesistance_Section();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.SectionPesistance#getIniPersistance <em>Ini Persistance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ini Persistance</em>'.
	 * @see com.symbian.tef.script.SectionPesistance#getIniPersistance()
	 * @see #getSectionPesistance()
	 * @generated
	 */
	EAttribute getSectionPesistance_IniPersistance();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.SectionPesistance#getSectionPersistance <em>Section Persistance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Persistance</em>'.
	 * @see com.symbian.tef.script.SectionPesistance#getSectionPersistance()
	 * @see #getSectionPesistance()
	 * @generated
	 */
	EAttribute getSectionPesistance_SectionPersistance();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.TefComment <em>Tef Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tef Comment</em>'.
	 * @see com.symbian.tef.script.TefComment
	 * @generated
	 */
	EClass getTefComment();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.TestBlock <em>Test Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Block</em>'.
	 * @see com.symbian.tef.script.TestBlock
	 * @generated
	 */
	EClass getTestBlock();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getHeap <em>Heap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Heap</em>'.
	 * @see com.symbian.tef.script.TestBlock#getHeap()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_Heap();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.tef.script.TestBlock#getTimeout()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.symbian.tef.script.TestBlock#getServer()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getIniFile <em>Ini File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ini File</em>'.
	 * @see com.symbian.tef.script.TestBlock#getIniFile()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_IniFile();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getPanicCode <em>Panic Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic Code</em>'.
	 * @see com.symbian.tef.script.TestBlock#getPanicCode()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_PanicCode();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.TestBlock#getPanicString <em>Panic String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Panic String</em>'.
	 * @see com.symbian.tef.script.TestBlock#getPanicString()
	 * @see #getTestBlock()
	 * @generated
	 */
	EAttribute getTestBlock_PanicString();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.CreateObject <em>Create Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Object</em>'.
	 * @see com.symbian.tef.script.CreateObject
	 * @generated
	 */
	EClass getCreateObject();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.CreateObject#getObjectType <em>Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Type</em>'.
	 * @see com.symbian.tef.script.CreateObject#getObjectType()
	 * @see #getCreateObject()
	 * @generated
	 */
	EAttribute getCreateObject_ObjectType();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.CreateObject#getObjectName <em>Object Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Name</em>'.
	 * @see com.symbian.tef.script.CreateObject#getObjectName()
	 * @see #getCreateObject()
	 * @generated
	 */
	EAttribute getCreateObject_ObjectName();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.RestoreObject <em>Restore Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Restore Object</em>'.
	 * @see com.symbian.tef.script.RestoreObject
	 * @generated
	 */
	EClass getRestoreObject();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RestoreObject#getObjectType <em>Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Type</em>'.
	 * @see com.symbian.tef.script.RestoreObject#getObjectType()
	 * @see #getRestoreObject()
	 * @generated
	 */
	EAttribute getRestoreObject_ObjectType();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.RestoreObject#getObjectName <em>Object Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Name</em>'.
	 * @see com.symbian.tef.script.RestoreObject#getObjectName()
	 * @see #getRestoreObject()
	 * @generated
	 */
	EAttribute getRestoreObject_ObjectName();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see com.symbian.tef.script.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Command#getError <em>Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error</em>'.
	 * @see com.symbian.tef.script.Command#getError()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_Error();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Command#getAsyncError <em>Async Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async Error</em>'.
	 * @see com.symbian.tef.script.Command#getAsyncError()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_AsyncError();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Command#getObjectName <em>Object Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Name</em>'.
	 * @see com.symbian.tef.script.Command#getObjectName()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_ObjectName();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Command#getFunctionName <em>Function Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Name</em>'.
	 * @see com.symbian.tef.script.Command#getFunctionName()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_FunctionName();

	/**
	 * Returns the meta object for the reference '{@link com.symbian.tef.script.Command#getSection <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Section</em>'.
	 * @see com.symbian.tef.script.Command#getSection()
	 * @see #getCommand()
	 * @generated
	 */
	EReference getCommand_Section();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Store <em>Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Store</em>'.
	 * @see com.symbian.tef.script.Store
	 * @generated
	 */
	EClass getStore();

	/**
	 * Returns the meta object for the reference '{@link com.symbian.tef.script.Store#getSection <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Section</em>'.
	 * @see com.symbian.tef.script.Store#getSection()
	 * @see #getStore()
	 * @generated
	 */
	EReference getStore_Section();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.Outstanding <em>Outstanding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outstanding</em>'.
	 * @see com.symbian.tef.script.Outstanding
	 * @generated
	 */
	EClass getOutstanding();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Outstanding#getPollInterval <em>Poll Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Poll Interval</em>'.
	 * @see com.symbian.tef.script.Outstanding#getPollInterval()
	 * @see #getOutstanding()
	 * @generated
	 */
	EAttribute getOutstanding_PollInterval();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.Outstanding#getObjectName <em>Object Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Name</em>'.
	 * @see com.symbian.tef.script.Outstanding#getObjectName()
	 * @see #getOutstanding()
	 * @generated
	 */
	EAttribute getOutstanding_ObjectName();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.AsyncDelay <em>Async Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Async Delay</em>'.
	 * @see com.symbian.tef.script.AsyncDelay
	 * @generated
	 */
	EClass getAsyncDelay();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.tef.script.AsyncDelay#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.symbian.tef.script.AsyncDelay#getTimeout()
	 * @see #getAsyncDelay()
	 * @generated
	 */
	EAttribute getAsyncDelay_Timeout();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.SharedActiveScheduler <em>Shared Active Scheduler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shared Active Scheduler</em>'.
	 * @see com.symbian.tef.script.SharedActiveScheduler
	 * @generated
	 */
	EClass getSharedActiveScheduler();

	/**
	 * Returns the meta object for class '{@link com.symbian.tef.script.StoreActiveScheduler <em>Store Active Scheduler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Store Active Scheduler</em>'.
	 * @see com.symbian.tef.script.StoreActiveScheduler
	 * @generated
	 */
	EClass getStoreActiveScheduler();

	/**
	 * Returns the meta object for enum '{@link com.symbian.tef.script.UtilityCommand <em>Utility Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Utility Command</em>'.
	 * @see com.symbian.tef.script.UtilityCommand
	 * @generated
	 */
	EEnum getUtilityCommand();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScriptFactory getScriptFactory();

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
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.TefModelImpl <em>Tef Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.TefModelImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTefModel()
		 * @generated
		 */
		EClass TEF_MODEL = eINSTANCE.getTefModel();

		/**
		 * The meta object literal for the '<em><b>Tef</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEF_MODEL__TEF = eINSTANCE.getTefModel_Tef();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.Tef <em>Tef</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.Tef
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTef()
		 * @generated
		 */
		EClass TEF = eINSTANCE.getTef();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.Container <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.Container
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Tef</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__TEF = eINSTANCE.getContainer_Tef();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.Leaf <em>Leaf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.Leaf
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLeaf()
		 * @generated
		 */
		EClass LEAF = eINSTANCE.getLeaf();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.TestCaseImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__NAME = eINSTANCE.getTestCase_Name();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.PrefixImpl <em>Prefix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.PrefixImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPrefix()
		 * @generated
		 */
		EClass PREFIX = eINSTANCE.getPrefix();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.RepeatImpl <em>Repeat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.RepeatImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRepeat()
		 * @generated
		 */
		EClass REPEAT = eINSTANCE.getRepeat();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPEAT__NAME = eINSTANCE.getRepeat_Name();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.TestStepImpl <em>Test Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.TestStepImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestStep()
		 * @generated
		 */
		EClass TEST_STEP = eINSTANCE.getTestStep();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__SERVER = eINSTANCE.getTestStep_Server();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__TIMEOUT = eINSTANCE.getTestStep_Timeout();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__METHOD = eINSTANCE.getTestStep_Method();

		/**
		 * The meta object literal for the '<em><b>Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__ERROR = eINSTANCE.getTestStep_Error();

		/**
		 * The meta object literal for the '<em><b>Panic String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__PANIC_STRING = eINSTANCE.getTestStep_PanicString();

		/**
		 * The meta object literal for the '<em><b>Panic Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__PANIC_CODE = eINSTANCE.getTestStep_PanicCode();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__RESULT = eINSTANCE.getTestStep_Result();

		/**
		 * The meta object literal for the '<em><b>Heap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__HEAP = eINSTANCE.getTestStep_Heap();

		/**
		 * The meta object literal for the '<em><b>OOM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_STEP__OOM = eINSTANCE.getTestStep_OOM();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.PrintImpl <em>Print</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.PrintImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPrint()
		 * @generated
		 */
		EClass PRINT = eINSTANCE.getPrint();

		/**
		 * The meta object literal for the '<em><b>Print</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRINT__PRINT = eINSTANCE.getPrint_Print();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.LoadSuiteImpl <em>Load Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.LoadSuiteImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLoadSuite()
		 * @generated
		 */
		EClass LOAD_SUITE = eINSTANCE.getLoadSuite();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SUITE__SERVER = eINSTANCE.getLoadSuite_Server();

		/**
		 * The meta object literal for the '<em><b>Shared Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SUITE__SHARED_DATA = eINSTANCE.getLoadSuite_SharedData();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.LoadServerImpl <em>Load Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.LoadServerImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getLoadServer()
		 * @generated
		 */
		EClass LOAD_SERVER = eINSTANCE.getLoadServer();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SERVER__SERVER = eINSTANCE.getLoadServer_Server();

		/**
		 * The meta object literal for the '<em><b>Shared Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOAD_SERVER__SHARED_DATA = eINSTANCE.getLoadServer_SharedData();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.RunUtilsImpl <em>Run Utils</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.RunUtilsImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunUtils()
		 * @generated
		 */
		EClass RUN_UTILS = eINSTANCE.getRunUtils();

		/**
		 * The meta object literal for the '<em><b>Utility Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_UTILS__UTILITY_COMMAND = eINSTANCE.getRunUtils_UtilityCommand();

		/**
		 * The meta object literal for the '<em><b>Param</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_UTILS__PARAM = eINSTANCE.getRunUtils_Param();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.RunProgramImpl <em>Run Program</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.RunProgramImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunProgram()
		 * @generated
		 */
		EClass RUN_PROGRAM = eINSTANCE.getRunProgram();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_PROGRAM__NAME = eINSTANCE.getRunProgram_Name();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_PROGRAM__TIMEOUT = eINSTANCE.getRunProgram_Timeout();

		/**
		 * The meta object literal for the '<em><b>WS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_PROGRAM__WS = eINSTANCE.getRunProgram_WS();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.RunScriptImpl <em>Run Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.RunScriptImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRunScript()
		 * @generated
		 */
		EClass RUN_SCRIPT = eINSTANCE.getRunScript();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_SCRIPT__SCRIPT = eINSTANCE.getRunScript_Script();

		/**
		 * The meta object literal for the '<em><b>Script Persistance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RUN_SCRIPT__SCRIPT_PERSISTANCE = eINSTANCE.getRunScript_ScriptPersistance();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.PauseImpl <em>Pause</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.PauseImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getPause()
		 * @generated
		 */
		EClass PAUSE = eINSTANCE.getPause();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.DelayImpl <em>Delay</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.DelayImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getDelay()
		 * @generated
		 */
		EClass DELAY = eINSTANCE.getDelay();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELAY__TIMEOUT = eINSTANCE.getDelay_Timeout();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.ConsecutiveImpl <em>Consecutive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.ConsecutiveImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getConsecutive()
		 * @generated
		 */
		EClass CONSECUTIVE = eINSTANCE.getConsecutive();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.ConcurrentImpl <em>Concurrent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.ConcurrentImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getConcurrent()
		 * @generated
		 */
		EClass CONCURRENT = eINSTANCE.getConcurrent();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.SharedDataImpl <em>Shared Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.SharedDataImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSharedData()
		 * @generated
		 */
		EClass SHARED_DATA = eINSTANCE.getSharedData();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.SectionPesistance <em>Section Pesistance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.SectionPesistance
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSectionPesistance()
		 * @generated
		 */
		EClass SECTION_PESISTANCE = eINSTANCE.getSectionPesistance();

		/**
		 * The meta object literal for the '<em><b>Section</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION_PESISTANCE__SECTION = eINSTANCE.getSectionPesistance_Section();

		/**
		 * The meta object literal for the '<em><b>Ini Persistance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION_PESISTANCE__INI_PERSISTANCE = eINSTANCE.getSectionPesistance_IniPersistance();

		/**
		 * The meta object literal for the '<em><b>Section Persistance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION_PESISTANCE__SECTION_PERSISTANCE = eINSTANCE.getSectionPesistance_SectionPersistance();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.TefCommentImpl <em>Tef Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.TefCommentImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTefComment()
		 * @generated
		 */
		EClass TEF_COMMENT = eINSTANCE.getTefComment();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.TestBlockImpl <em>Test Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.TestBlockImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getTestBlock()
		 * @generated
		 */
		EClass TEST_BLOCK = eINSTANCE.getTestBlock();

		/**
		 * The meta object literal for the '<em><b>Heap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__HEAP = eINSTANCE.getTestBlock_Heap();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__TIMEOUT = eINSTANCE.getTestBlock_Timeout();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__SERVER = eINSTANCE.getTestBlock_Server();

		/**
		 * The meta object literal for the '<em><b>Ini File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__INI_FILE = eINSTANCE.getTestBlock_IniFile();

		/**
		 * The meta object literal for the '<em><b>Panic Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__PANIC_CODE = eINSTANCE.getTestBlock_PanicCode();

		/**
		 * The meta object literal for the '<em><b>Panic String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_BLOCK__PANIC_STRING = eINSTANCE.getTestBlock_PanicString();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.CreateObjectImpl <em>Create Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.CreateObjectImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getCreateObject()
		 * @generated
		 */
		EClass CREATE_OBJECT = eINSTANCE.getCreateObject();

		/**
		 * The meta object literal for the '<em><b>Object Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_OBJECT__OBJECT_TYPE = eINSTANCE.getCreateObject_ObjectType();

		/**
		 * The meta object literal for the '<em><b>Object Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_OBJECT__OBJECT_NAME = eINSTANCE.getCreateObject_ObjectName();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.RestoreObjectImpl <em>Restore Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.RestoreObjectImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getRestoreObject()
		 * @generated
		 */
		EClass RESTORE_OBJECT = eINSTANCE.getRestoreObject();

		/**
		 * The meta object literal for the '<em><b>Object Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESTORE_OBJECT__OBJECT_TYPE = eINSTANCE.getRestoreObject_ObjectType();

		/**
		 * The meta object literal for the '<em><b>Object Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESTORE_OBJECT__OBJECT_NAME = eINSTANCE.getRestoreObject_ObjectName();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.CommandImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__ERROR = eINSTANCE.getCommand_Error();

		/**
		 * The meta object literal for the '<em><b>Async Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__ASYNC_ERROR = eINSTANCE.getCommand_AsyncError();

		/**
		 * The meta object literal for the '<em><b>Object Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__OBJECT_NAME = eINSTANCE.getCommand_ObjectName();

		/**
		 * The meta object literal for the '<em><b>Function Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__FUNCTION_NAME = eINSTANCE.getCommand_FunctionName();

		/**
		 * The meta object literal for the '<em><b>Section</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND__SECTION = eINSTANCE.getCommand_Section();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.StoreImpl <em>Store</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.StoreImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getStore()
		 * @generated
		 */
		EClass STORE = eINSTANCE.getStore();

		/**
		 * The meta object literal for the '<em><b>Section</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORE__SECTION = eINSTANCE.getStore_Section();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.OutstandingImpl <em>Outstanding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.OutstandingImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getOutstanding()
		 * @generated
		 */
		EClass OUTSTANDING = eINSTANCE.getOutstanding();

		/**
		 * The meta object literal for the '<em><b>Poll Interval</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTSTANDING__POLL_INTERVAL = eINSTANCE.getOutstanding_PollInterval();

		/**
		 * The meta object literal for the '<em><b>Object Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTSTANDING__OBJECT_NAME = eINSTANCE.getOutstanding_ObjectName();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.AsyncDelayImpl <em>Async Delay</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.AsyncDelayImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getAsyncDelay()
		 * @generated
		 */
		EClass ASYNC_DELAY = eINSTANCE.getAsyncDelay();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASYNC_DELAY__TIMEOUT = eINSTANCE.getAsyncDelay_Timeout();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.SharedActiveSchedulerImpl <em>Shared Active Scheduler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.SharedActiveSchedulerImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getSharedActiveScheduler()
		 * @generated
		 */
		EClass SHARED_ACTIVE_SCHEDULER = eINSTANCE.getSharedActiveScheduler();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.impl.StoreActiveSchedulerImpl <em>Store Active Scheduler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.impl.StoreActiveSchedulerImpl
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getStoreActiveScheduler()
		 * @generated
		 */
		EClass STORE_ACTIVE_SCHEDULER = eINSTANCE.getStoreActiveScheduler();

		/**
		 * The meta object literal for the '{@link com.symbian.tef.script.UtilityCommand <em>Utility Command</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.tef.script.UtilityCommand
		 * @see com.symbian.tef.script.impl.ScriptPackageImpl#getUtilityCommand()
		 * @generated
		 */
		EEnum UTILITY_COMMAND = eINSTANCE.getUtilityCommand();

	}

} //ScriptPackage
