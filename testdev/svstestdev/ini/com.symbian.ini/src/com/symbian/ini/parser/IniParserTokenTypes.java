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


// $ANTLR : "Ini.g" -> "IniLexer.java"$

package com.symbian.ini.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import antlr.TokenStreamSelector;

import com.symbian.comment.Comment;
import com.symbian.comment.parser.CommentUtils;
import com.symbian.ini.Data;
import com.symbian.ini.IniFactory;
import com.symbian.ini.IniModel;
import com.symbian.ini.IniPackage;
import com.symbian.ini.Section;
import com.symbian.ini.impl.IniFactoryImpl;
import com.symbian.ini.util.WrappedResourceDiagnostic;

public interface IniParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int WS = 4;
	int TAG = 5;
	int TAG_KEY = 6;
	int TAG_TRAILING_VALUE = 7;
	int EOL = 8;
	int STRING = 9;
	int ID = 10;
	int LEFT_SQ_BRAKET = 11;
	int RIGHT_SQ_BRAKET = 12;
	int VALUE = 13;
	int COMMENT = 14;
	int FORWARD_SLASH = 15;
	int STAR = 16;
}
