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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attached Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.symbian.comment.AttachedComment#getAttachedComment <em>Attached Comment</em>}</li>
 *   <li>{@link com.symbian.comment.AttachedComment#getAttachedTag <em>Attached Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.symbian.comment.CommentPackage#getAttachedComment()
 * @model
 * @generated
 */
public interface AttachedComment extends EObject {
	/**
	 * Returns the value of the '<em><b>Attached Comment</b></em>' reference list.
	 * The list contents are of type {@link com.symbian.comment.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attached Comment</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attached Comment</em>' reference list.
	 * @see com.symbian.comment.CommentPackage#getAttachedComment_AttachedComment()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Comment> getAttachedComment();

	/**
	 * Returns the value of the '<em><b>Attached Tag</b></em>' reference list.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attached Tag</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attached Tag</em>' reference list.
	 * @see com.symbian.comment.CommentPackage#getAttachedComment_AttachedTag()
	 * @model mapType="com.symbian.comment.CommentTag<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EMap<String, String> getAttachedTag();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	boolean isAttachedAbove();

} // AttachedComment