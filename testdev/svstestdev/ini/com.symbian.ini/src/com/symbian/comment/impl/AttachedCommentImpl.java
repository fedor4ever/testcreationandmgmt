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


package com.symbian.comment.impl;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEList;

import com.symbian.comment.AttachedComment;
import com.symbian.comment.Comment;
import com.symbian.comment.CommentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attached Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.symbian.comment.impl.AttachedCommentImpl#getAttachedComment <em>Attached Comment</em>}</li>
 *   <li>{@link com.symbian.comment.impl.AttachedCommentImpl#getAttachedTag <em>Attached Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttachedCommentImpl extends EObjectImpl implements AttachedComment {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttachedCommentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommentPackage.Literals.ATTACHED_COMMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList getAttachedComment() {
		EList lComments = new BasicEList();
		EList lSiblingObjects = eContainer().eContents();
		int lCurrentIndex = lSiblingObjects.indexOf(this);
		
		int lIter = isAttachedAbove() ? lCurrentIndex - 1 : lCurrentIndex + 1;
		if (lIter >= 0 || lIter < lSiblingObjects.size()) {
			
			EObject lAttachedEObject = (EObject) lSiblingObjects.get(lIter);
			lIter = next(lIter);
			while (lIter >= 0 && lIter < lSiblingObjects.size() && (lAttachedEObject instanceof Comment)) {
				lComments.add((Comment) lAttachedEObject);
				lAttachedEObject = (EObject) lSiblingObjects.get(lIter);
				lIter = next(lIter);
			}
		}
		
		return new EcoreEList.UnmodifiableEList<Comment>(
				this, CommentPackage.Literals.ATTACHED_COMMENT__ATTACHED_COMMENT, lComments.size(), lComments.toArray());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EMap getAttachedTag() {
		EMap lTags = new TagList(this.eClass(), CommentTagImpl.class, this.eInternalContainer(), this.eContainerFeatureID);
		
		EList lSiblingObjects = eContainer().eContents();
		int lCurrentIndex = lSiblingObjects.indexOf(this);
		
		int lIter = isAttachedAbove() ? lCurrentIndex - 1 : lCurrentIndex + 1;
		if (lIter >= 0 || lIter < lSiblingObjects.size()) {
			
			EObject lPreviousObject = (EObject) lSiblingObjects.get(lIter);
			
			lIter = next(lIter);
			while (lIter >= 0 && lIter < lSiblingObjects.size() && (lPreviousObject instanceof CommentTagImpl)) {
				
				lTags.putAll((EMap) lPreviousObject);
				lPreviousObject = (EObject) lSiblingObjects.get(lIter);
				
				lIter = next(lIter);
				
			}
			
		}
			
		return lTags;
	}
	
	private int next(int aIter) {
		if (isAttachedAbove()) {
			aIter--;
		} else {
			aIter++;
		}
		
		return aIter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAttachedAbove() {
		IScopeContext lScopeContext = new ConfigurationScope();
		IEclipsePreferences lEclipsePreferences = lScopeContext.getNode("com.symbian.ini.editor");
		
		boolean lIsAttachedAbove = true;
		if (lEclipsePreferences != null) {
			lIsAttachedAbove = lEclipsePreferences.getBoolean("commentAbove", true);
		}
		
		return lIsAttachedAbove;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT:
				return getAttachedComment();
			case CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG:
				if (coreType) return getAttachedTag();
				else return getAttachedTag().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommentPackage.ATTACHED_COMMENT__ATTACHED_COMMENT:
				return !getAttachedComment().isEmpty();
			case CommentPackage.ATTACHED_COMMENT__ATTACHED_TAG:
				return !getAttachedTag().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AttachedCommentImpl