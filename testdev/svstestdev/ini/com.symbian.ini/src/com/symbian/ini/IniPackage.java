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


package com.symbian.ini;

import com.symbian.comment.CommentPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.symbian.ini.IniFactory
 * @model kind="package"
 * @generated
 */
public interface IniPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ini";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.symbian.com/ini";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ini";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IniPackage eINSTANCE = com.symbian.ini.impl.IniPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.symbian.ini.impl.IniModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.impl.IniModelImpl
	 * @see com.symbian.ini.impl.IniPackageImpl#getIniModel()
	 * @generated
	 */
	int INI_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Ini</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_MODEL__INI = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.symbian.ini.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.impl.SectionImpl
	 * @see com.symbian.ini.impl.IniPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 3;

	/**
	 * The meta object id for the '{@link com.symbian.ini.impl.DataImpl <em>Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.impl.DataImpl
	 * @see com.symbian.ini.impl.IniPackageImpl#getData()
	 * @generated
	 */
	int DATA = 4;

	/**
	 * The meta object id for the '{@link com.symbian.ini.impl.IniCommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.impl.IniCommentImpl
	 * @see com.symbian.ini.impl.IniPackageImpl#getIniComment()
	 * @generated
	 */
	int INI_COMMENT = 5;

	/**
	 * The meta object id for the '{@link com.symbian.ini.Ini <em>Ini</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.Ini
	 * @see com.symbian.ini.impl.IniPackageImpl#getIni()
	 * @generated
	 */
	int INI = 1;

	/**
	 * The number of structural features of the '<em>Ini</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_FEATURE_COUNT = 0;


	/**
	 * The meta object id for the '{@link com.symbian.ini.IniLeaf <em>Leaf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.ini.IniLeaf
	 * @see com.symbian.ini.impl.IniPackageImpl#getIniLeaf()
	 * @generated
	 */
	int INI_LEAF = 2;

	/**
	 * The number of structural features of the '<em>Leaf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_LEAF_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Attached Comment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTACHED_COMMENT = CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT;

	/**
	 * The feature id for the '<em><b>Attached Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTACHED_TAG = CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ini Leaf</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__INI_LEAF = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Attached Comment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__ATTACHED_COMMENT = CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT;

	/**
	 * The feature id for the '<em><b>Attached Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__ATTACHED_TAG = CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__KEY = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA__VALUE = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FEATURE_COUNT = CommentPackage.ATTACHED_COMMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_COMMENT__COMMENT = CommentPackage.COMMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_COMMENT__TAG = CommentPackage.COMMENT__TAG;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INI_COMMENT_FEATURE_COUNT = CommentPackage.COMMENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link com.symbian.ini.IniModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.symbian.ini.IniModel
	 * @generated
	 */
	EClass getIniModel();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.ini.IniModel#getIni <em>Ini</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ini</em>'.
	 * @see com.symbian.ini.IniModel#getIni()
	 * @see #getIniModel()
	 * @generated
	 */
	EReference getIniModel_Ini();

	/**
	 * Returns the meta object for class '{@link com.symbian.ini.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see com.symbian.ini.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.ini.Section#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.symbian.ini.Section#getName()
	 * @see #getSection()
	 * @generated
	 */
	EAttribute getSection_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.symbian.ini.Section#getIniLeaf <em>Ini Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ini Leaf</em>'.
	 * @see com.symbian.ini.Section#getIniLeaf()
	 * @see #getSection()
	 * @generated
	 */
	EReference getSection_IniLeaf();

	/**
	 * Returns the meta object for class '{@link com.symbian.ini.Data <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data</em>'.
	 * @see com.symbian.ini.Data
	 * @generated
	 */
	EClass getData();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.ini.Data#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.symbian.ini.Data#getKey()
	 * @see #getData()
	 * @generated
	 */
	EAttribute getData_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.ini.Data#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.symbian.ini.Data#getValue()
	 * @see #getData()
	 * @generated
	 */
	EAttribute getData_Value();

	/**
	 * Returns the meta object for class '{@link com.symbian.ini.IniComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see com.symbian.ini.IniComment
	 * @generated
	 */
	EClass getIniComment();

	/**
	 * Returns the meta object for class '{@link com.symbian.ini.Ini <em>Ini</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ini</em>'.
	 * @see com.symbian.ini.Ini
	 * @generated
	 */
	EClass getIni();

	/**
	 * Returns the meta object for class '{@link com.symbian.ini.IniLeaf <em>Leaf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf</em>'.
	 * @see com.symbian.ini.IniLeaf
	 * @generated
	 */
	EClass getIniLeaf();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IniFactory getIniFactory();

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
		 * The meta object literal for the '{@link com.symbian.ini.impl.IniModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.impl.IniModelImpl
		 * @see com.symbian.ini.impl.IniPackageImpl#getIniModel()
		 * @generated
		 */
		EClass INI_MODEL = eINSTANCE.getIniModel();

		/**
		 * The meta object literal for the '<em><b>Ini</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INI_MODEL__INI = eINSTANCE.getIniModel_Ini();

		/**
		 * The meta object literal for the '{@link com.symbian.ini.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.impl.SectionImpl
		 * @see com.symbian.ini.impl.IniPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION__NAME = eINSTANCE.getSection_Name();

		/**
		 * The meta object literal for the '<em><b>Ini Leaf</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECTION__INI_LEAF = eINSTANCE.getSection_IniLeaf();

		/**
		 * The meta object literal for the '{@link com.symbian.ini.impl.DataImpl <em>Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.impl.DataImpl
		 * @see com.symbian.ini.impl.IniPackageImpl#getData()
		 * @generated
		 */
		EClass DATA = eINSTANCE.getData();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA__KEY = eINSTANCE.getData_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA__VALUE = eINSTANCE.getData_Value();

		/**
		 * The meta object literal for the '{@link com.symbian.ini.impl.IniCommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.impl.IniCommentImpl
		 * @see com.symbian.ini.impl.IniPackageImpl#getIniComment()
		 * @generated
		 */
		EClass INI_COMMENT = eINSTANCE.getIniComment();

		/**
		 * The meta object literal for the '{@link com.symbian.ini.Ini <em>Ini</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.Ini
		 * @see com.symbian.ini.impl.IniPackageImpl#getIni()
		 * @generated
		 */
		EClass INI = eINSTANCE.getIni();

		/**
		 * The meta object literal for the '{@link com.symbian.ini.IniLeaf <em>Leaf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.ini.IniLeaf
		 * @see com.symbian.ini.impl.IniPackageImpl#getIniLeaf()
		 * @generated
		 */
		EClass INI_LEAF = eINSTANCE.getIniLeaf();

	}

} //IniPackage
