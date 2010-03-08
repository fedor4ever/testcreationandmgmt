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


// $ANTLR : "CommentLexer.g" -> "CommentLexer.java"$

package com.symbian.comment.parser;


public interface CommentLexerTokenTypes {
	int EOF = 1;

	int NULL_TREE_LOOKAHEAD = 3;

	int WS = 4;

	int TAG = 5;

	int TAG_KEY = 6;

	int TAG_TRAILING_VALUE = 7;

	int EOL = 8;

	int STRING = 9;

	int ID = 10;
}
