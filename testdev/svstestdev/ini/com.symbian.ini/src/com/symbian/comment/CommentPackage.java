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


package com.symbian.comment;

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
 * @see com.symbian.comment.CommentFactory
 * @model kind="package"
 * @generated
 */
public interface CommentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "comment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.symbian.com/comment";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "comment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommentPackage eINSTANCE = com.symbian.comment.impl.CommentPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.symbian.comment.Comment <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.comment.Comment
	 * @see com.symbian.comment.impl.CommentPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENT = 0;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__TAG = 1;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.comment.impl.CommentTagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.comment.impl.CommentTagImpl
	 * @see com.symbian.comment.impl.CommentPackageImpl#getCommentTag()
	 * @generated
	 */
	int COMMENT_TAG = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_TAG__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_TAG__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_TAG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.symbian.comment.impl.AttachedCommentImpl <em>Attached Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.symbian.comment.impl.AttachedCommentImpl
	 * @see com.symbian.comment.impl.CommentPackageImpl#getAttachedComment()
	 * @generated
	 */
	int ATTACHED_COMMENT = 2;

	/**
	 * The feature id for the '<em><b>Attached Comment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHED_COMMENT__ATTACHED_COMMENT = 0;

	/**
	 * The feature id for the '<em><b>Attached Tag</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHED_COMMENT__ATTACHED_TAG = 1;

	/**
	 * The number of structural features of the '<em>Attached Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHED_COMMENT_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link com.symbian.comment.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see com.symbian.comment.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link com.symbian.comment.Comment#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see com.symbian.comment.Comment#getComment()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Comment();

	/**
	 * Returns the meta object for the map '{@link com.symbian.comment.Comment#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Tag</em>'.
	 * @see com.symbian.comment.Comment#getTag()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_Tag();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getCommentTag();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getCommentTag()
	 * @generated
	 */
	EAttribute getCommentTag_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getCommentTag()
	 * @generated
	 */
	EAttribute getCommentTag_Value();

	/**
	 * Returns the meta object for class '{@link com.symbian.comment.AttachedComment <em>Attached Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attached Comment</em>'.
	 * @see com.symbian.comment.AttachedComment
	 * @generated
	 */
	EClass getAttachedComment();

	/**
	 * Returns the meta object for the reference list '{@link com.symbian.comment.AttachedComment#getAttachedComment <em>Attached Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attached Comment</em>'.
	 * @see com.symbian.comment.AttachedComment#getAttachedComment()
	 * @see #getAttachedComment()
	 * @generated
	 */
	EReference getAttachedComment_AttachedComment();

	/**
	 * Returns the meta object for the reference list '{@link com.symbian.comment.AttachedComment#getAttachedTag <em>Attached Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attached Tag</em>'.
	 * @see com.symbian.comment.AttachedComment#getAttachedTag()
	 * @see #getAttachedComment()
	 * @generated
	 */
	EReference getAttachedComment_AttachedTag();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommentFactory getCommentFactory();

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
		 * The meta object literal for the '{@link com.symbian.comment.Comment <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.comment.Comment
		 * @see com.symbian.comment.impl.CommentPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__COMMENT = eINSTANCE.getComment_Comment();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__TAG = eINSTANCE.getComment_Tag();

		/**
		 * The meta object literal for the '{@link com.symbian.comment.impl.CommentTagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.comment.impl.CommentTagImpl
		 * @see com.symbian.comment.impl.CommentPackageImpl#getCommentTag()
		 * @generated
		 */
		EClass COMMENT_TAG = eINSTANCE.getCommentTag();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT_TAG__KEY = eINSTANCE.getCommentTag_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT_TAG__VALUE = eINSTANCE.getCommentTag_Value();

		/**
		 * The meta object literal for the '{@link com.symbian.comment.impl.AttachedCommentImpl <em>Attached Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.symbian.comment.impl.AttachedCommentImpl
		 * @see com.symbian.comment.impl.CommentPackageImpl#getAttachedComment()
		 * @generated
		 */
		EClass ATTACHED_COMMENT = eINSTANCE.getAttachedComment();

		/**
		 * The meta object literal for the '<em><b>Attached Comment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTACHED_COMMENT__ATTACHED_COMMENT = eINSTANCE.getAttachedComment_AttachedComment();

		/**
		 * The meta object literal for the '<em><b>Attached Tag</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTACHED_COMMENT__ATTACHED_TAG = eINSTANCE.getAttachedComment_AttachedTag();

	}

} //CommentPackage
