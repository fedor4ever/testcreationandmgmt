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


// $ANTLR 2.7.7 (20060930): "Script.g" -> "ScriptParser.java"$
 	
package com.symbian.tef.script.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import antlr.TokenStreamSelector;

import com.symbian.comment.parser.CommentUtils;
import com.symbian.ini.util.WrappedResourceDiagnostic;
import com.symbian.tef.script.Concurrent;
import com.symbian.tef.script.Container;
import com.symbian.tef.script.Consecutive;
import com.symbian.tef.script.Delay;
import com.symbian.tef.script.LoadServer;
import com.symbian.tef.script.LoadSuite;
import com.symbian.tef.script.Pause;
import com.symbian.tef.script.Prefix;
import com.symbian.tef.script.Print;
import com.symbian.tef.script.Repeat;
import com.symbian.tef.script.RunProgram;
import com.symbian.tef.script.RunScript;
import com.symbian.tef.script.RunUtils;
import com.symbian.tef.script.ScriptPackage;
import com.symbian.tef.script.SharedData;
import com.symbian.tef.script.Tef;
import com.symbian.tef.script.TefModel;
import com.symbian.tef.script.TestCase;
import com.symbian.tef.script.TestStep;
import com.symbian.tef.script.TestBlock;
import com.symbian.tef.script.CreateObject;
import com.symbian.tef.script.RestoreObject;
import com.symbian.tef.script.Command;
import com.symbian.tef.script.Store;
import com.symbian.tef.script.Outstanding;
import com.symbian.tef.script.AsyncDelay;
import com.symbian.tef.script.SharedActiveScheduler;
import com.symbian.tef.script.StoreActiveScheduler;
import com.symbian.tef.script.UtilityCommand;
import com.symbian.tef.script.impl.ScriptFactoryImpl;
import com.symbian.tef.script.util.IniUtils;
import com.symbian.tef.script.util.ScriptResourceImpl;
import com.symbian.ini.*;


public interface ScriptParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int WS = 4;
	int TAG = 5;
	int TAG_KEY = 6;
	int TAG_TRAILING_VALUE = 7;
	int EOL = 8;
	int STRING = 9;
	int ID = 10;
	int END_TESTCASE = 11;
	int REMOVE_PREFIX = 12;
	int END_REPEAT = 13;
	int START_TESTCASE = 14;
	int START_TEST_BLOCK = 15;
	int EXLAMATION = 16;
	int LITERAL_Heap = 17;
	int EQ = 18;
	int END_TEST_BLOCK = 19;
	int LITERAL_PanicString = 20;
	int LITERAL_PanicCode = 21;
	int PREFIX = 22;
	int START_REPEAT = 23;
	int RUN_TEST_STEP = 24;
	int LITERAL_Error = 25;
	int LITERAL_Result = 26;
	int LITERAL_OOM = 27;
	int PRINT = 28;
	int LOAD_SUITE = 29;
	int SHARED_DATA_DASH = 30;
	int LOAD_SERVER = 31;
	int RUN_UTILS = 32;
	// "\n" = 33
	// "\r" = 34
	int RUN_PROGRAM = 35;
	int RUN_SCRIPT = 36;
	int PAUSE = 37;
	int DELAY = 38;
	int CONSECUTIVE = 39;
	int CONCURRENT = 40;
	int SHARED_DATA = 41;
	int CREATE_OBJECT = 42;
	int RESTORE_OBJECT = 43;
	int STORE = 44;
	int OUTSTANDING = 45;
	int ASYNC_DELAY = 46;
	int COMMAND = 47;
	int LITERAL_AsyncError = 48;
	int SHARED_ACTIVE_SCHEDULER = 49;
	int STORE_ACTIVE_SCHEDULER = 50;
	int COMMENT = 51;
	int STAR = 52;
}
