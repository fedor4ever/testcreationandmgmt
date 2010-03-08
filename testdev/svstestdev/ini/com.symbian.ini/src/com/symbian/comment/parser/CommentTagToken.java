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
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import com.symbian.comment.CommentFactory;
import com.symbian.comment.impl.CommentFactoryImpl;
import com.symbian.comment.impl.CommentTagImpl;

import antlr.CommonToken;

public class CommentTagToken extends CommonToken {
	
	private String iKey;
	private String iValue;
	private String iTrailingValue;

	public void setTag(String aKey) {
		iKey = aKey;
	}
	
	public void setValue(String aValue) {
		iValue = aValue;
	}
	
	public void setTrailingValue(String aTrailingValue) {
		iTrailingValue = aTrailingValue;
	}

	public Map.Entry getTag() {
		if (iKey != null && iValue != null && !iKey.equalsIgnoreCase("")) {
			CommentTagImpl lCommentTag = (CommentTagImpl) ((CommentFactoryImpl) CommentFactory.eINSTANCE).createCommentTag();
			lCommentTag.setKey(iKey);
			lCommentTag.setValue(iValue);
			
			return lCommentTag;
		}
		
		return null;
	}

	public String getTrailingValues() {
		return iTrailingValue;
	}

}
