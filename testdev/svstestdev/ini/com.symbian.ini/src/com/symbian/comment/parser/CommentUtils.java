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


package com.symbian.comment.parser;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import antlr.Token;

import com.symbian.comment.Comment;

/**
 * @author TimothyL
 * 
 */
public class CommentUtils {

	/**
	 * @param aEObject
	 * @param aComment
	 * @param aToken
	 * @param aFeature
	 */
	public static void setComment(EObject aEObject, Comment aComment,
			Token aToken, EStructuralFeature aFeature) {
		if (aToken instanceof CommentTagToken) {
			CommentTagToken lCommentTagToken = (CommentTagToken) aToken;

			Map.Entry lTag = lCommentTagToken.getTag();
			if (lTag != null) {
				aComment.setTag(lTag);
			}

			String lTrailingValue = lCommentTagToken.getTrailingValues();
			if (lTrailingValue != null) {
				addTrailingValues(lTrailingValue, aEObject, aComment);

				return;
			}
		} else if (aToken.getText() != null
				&& !aToken.getText().equalsIgnoreCase("")) {
			aComment.setComment(aToken.getText());
		}

		if (aFeature != null && aEObject != null) {
			((EList) aEObject.eGet(aFeature)).add(aComment);
		}
	}

	/**
	 * @param TrailingValue
	 * @param aEObject
	 * @param aComment
	 */
	private static void addTrailingValues(String TrailingValue,
			EObject aEObject, Comment aComment) {
		if (aEObject != null && aEObject.eContents() != null) {
			EList lSiblingObjects = aEObject.eContents();
			EObject lPreviousObject = (EObject) lSiblingObjects
					.get(lSiblingObjects.size() - 1);

			if (lPreviousObject instanceof Comment) {
				Map.Entry lTag = ((Comment) lPreviousObject).getTag();

				if (lTag != null) {
					String lNewValue = (lTag.getValue() != null ? lTag
							.getValue()
							+ "\r\n" : "")
							+ TrailingValue;
					lTag.setValue(lNewValue);

					return;
				}
			}
		}

		aComment.setComment(TrailingValue);
	}

}
