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


import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class ScriptParser extends antlr.LLkParser       implements ScriptParserTokenTypes
 {

	/** Script EMF Factory **/
	protected static final ScriptFactoryImpl SCRIPT_FACTORY = (ScriptFactoryImpl) ScriptFactoryImpl.eINSTANCE;
	
	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();

	/** Resource **/
	private ScriptResourceImpl iResource;

	/** cur testblock **/
	private TestBlock curTestBlock;

	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);

		WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
			.setColumn(aRecognitionException.getColumn())
			.setLine(aRecognitionException.getLine())
			.setLocation(aRecognitionException.getFilename());

		iExceptions.add(lDiagnostic);
	}

	/** Returns all exceptions that occured during parsing.
	 * 
	 * @return The list of wrapped exceptions that occured during parsing
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}
	
	/** Sets the EMF resource for this Parser
	 * @param aResource
	 */
	public void setResource(ScriptResourceImpl aResource) {
		iResource = aResource;
	}


protected ScriptParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public ScriptParser(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected ScriptParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public ScriptParser(TokenStream lexer) {
  this(lexer,3);
}

public ScriptParser(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
}

	public final TefModel  model() throws RecognitionException, TokenStreamException {
		TefModel lTefModel = SCRIPT_FACTORY.createTefModel();
		
		
				Tef lTef;
			
		
		try {      // for error handling
			{
			_loop5:
			do {
				boolean synPredMatched4 = false;
				if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))) && (_tokenSet_2.member(LA(3))))) {
					int _m4 = mark();
					synPredMatched4 = true;
					inputState.guessing++;
					try {
						{
						match(Token.EOF_TYPE);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched4 = false;
					}
					rewind(_m4);
inputState.guessing--;
				}
				if ( synPredMatched4 ) {
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))) && (_tokenSet_2.member(LA(3)))) {
					lTef=tef(lTefModel);
					if ( inputState.guessing==0 ) {
						
								if (lTef != null) {
									lTefModel.getTef().add(lTef);
								}
							
					}
				}
				else {
					break _loop5;
				}
				
			} while (true);
			}
			match(Token.EOF_TYPE);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		return lTefModel;
	}
	
	public final Tef  tef(
		EObject aEObject
	) throws RecognitionException, TokenStreamException {
		Tef lTef = null;
		
		
				int lPos = 0;
			
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case START_TESTCASE:
			case START_TEST_BLOCK:
			case PREFIX:
			case START_REPEAT:
			{
				lTef=container(aEObject);
				break;
			}
			case RUN_TEST_STEP:
			case PRINT:
			case LOAD_SUITE:
			case LOAD_SERVER:
			case RUN_UTILS:
			case RUN_PROGRAM:
			case RUN_SCRIPT:
			case PAUSE:
			case DELAY:
			case CONSECUTIVE:
			case CONCURRENT:
			case SHARED_DATA:
			case CREATE_OBJECT:
			case RESTORE_OBJECT:
			case STORE:
			case OUTSTANDING:
			case ASYNC_DELAY:
			case COMMAND:
			case SHARED_ACTIVE_SCHEDULER:
			case STORE_ACTIVE_SCHEDULER:
			case COMMENT:
			{
				lTef=leaf(aEObject);
				break;
			}
			case EOF:
			case END_TESTCASE:
			case REMOVE_PREFIX:
			case END_REPEAT:
			{
				if ( inputState.guessing==0 ) {
					lPos = mark();
				}
				{
				switch ( LA(1)) {
				case END_TESTCASE:
				{
					match(END_TESTCASE);
					break;
				}
				case REMOVE_PREFIX:
				{
					match(REMOVE_PREFIX);
					break;
				}
				case END_REPEAT:
				{
					match(END_REPEAT);
					break;
				}
				case EOF:
				{
					match(Token.EOF_TYPE);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					
							if (aEObject.eContainer() instanceof Container) {
								rewind(lPos);
							}
						
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTef;
	}
	
	public final Tef  container(
		EObject aEObject
	) throws RecognitionException, TokenStreamException {
		Tef lTef = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case START_TESTCASE:
			{
				lTef=testCase();
				break;
			}
			case PREFIX:
			{
				lTef=prefix();
				break;
			}
			case START_REPEAT:
			{
				lTef=repeat();
				break;
			}
			case START_TEST_BLOCK:
			{
				lTef=testBlock();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTef;
	}
	
	public final Tef  leaf(
		EObject aEObject
	) throws RecognitionException, TokenStreamException {
		Tef lTef = null;
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case RUN_TEST_STEP:
			{
				lTef=testStep();
				break;
			}
			case PRINT:
			{
				lTef=print();
				break;
			}
			case LOAD_SUITE:
			{
				lTef=loadSuite();
				break;
			}
			case LOAD_SERVER:
			{
				lTef=loadServer();
				break;
			}
			case RUN_UTILS:
			{
				lTef=runUtils();
				break;
			}
			case RUN_PROGRAM:
			{
				lTef=runProgram();
				break;
			}
			case RUN_SCRIPT:
			{
				lTef=runScript();
				break;
			}
			case PAUSE:
			{
				lTef=pause();
				break;
			}
			case DELAY:
			{
				lTef=delay();
				break;
			}
			case CONSECUTIVE:
			{
				lTef=consecutive();
				break;
			}
			case CONCURRENT:
			{
				lTef=concurrent();
				break;
			}
			case SHARED_DATA:
			{
				lTef=sharedData();
				break;
			}
			case CREATE_OBJECT:
			{
				lTef=create_object();
				break;
			}
			case RESTORE_OBJECT:
			{
				lTef=restore_object();
				break;
			}
			case STORE:
			{
				lTef=store();
				break;
			}
			case OUTSTANDING:
			{
				lTef=outstanding();
				break;
			}
			case ASYNC_DELAY:
			{
				lTef=async_delay();
				break;
			}
			case SHARED_ACTIVE_SCHEDULER:
			{
				lTef=shared_active_scheduler();
				break;
			}
			case STORE_ACTIVE_SCHEDULER:
			{
				lTef=store_active_scheduler();
				break;
			}
			case COMMAND:
			{
				lTef=command();
				break;
			}
			case COMMENT:
			{
				comment(aEObject);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTef;
	}
	
	public final TestCase  testCase() throws RecognitionException, TokenStreamException {
		TestCase lTestCase = SCRIPT_FACTORY.createTestCase();
		
		Token  lTestCaseStartName = null;
		Token  lTestCaseEndName = null;
		
				Tef lTef;
				boolean hasEnd = false;
			
		
		try {      // for error handling
			match(START_TESTCASE);
			lTestCaseStartName = LT(1);
			match(ID);
			{
			_loop13:
			do {
				if ((LA(1)==EOF) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					match(Token.EOF_TYPE);
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==END_TESTCASE) && (LA(2)==ID) && (_tokenSet_4.member(LA(3)))) {
					match(END_TESTCASE);
					lTestCaseEndName = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						
									if (lTestCaseStartName.getText().equalsIgnoreCase(lTestCaseEndName.getText())) {
										hasEnd = true;
										break;
									}
								
					}
				}
				else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					lTef=tef(lTestCase);
					if ( inputState.guessing==0 ) {
						
									if (lTef != null) {
										lTestCase.getTef().add(lTef);
									}
								
					}
				}
				else {
					break _loop13;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
						lTestCase.setName(lTestCaseStartName.getText());
					
			}

			if(!hasEnd){
				throw new RecognitionException("No \"END_TESTCASE " + lTestCaseStartName.getText() + "\"", 
						lTestCaseStartName.getFilename(), 
						lTestCaseStartName.getLine(), 
						lTestCaseStartName.getColumn());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTestCase;
	}
	
	public final Prefix  prefix() throws RecognitionException, TokenStreamException {
		Prefix lPrefix = SCRIPT_FACTORY.createPrefix();
		
		
				Tef lTef;
			
		
		try {      // for error handling
			match(PREFIX);
			{
			_loop25:
			do {
				if ((LA(1)==EOF||LA(1)==REMOVE_PREFIX) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case EOF:
					{
						match(Token.EOF_TYPE);
						break;
					}
					case REMOVE_PREFIX:
					{
						match(REMOVE_PREFIX);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					lTef=tef(lPrefix);
					if ( inputState.guessing==0 ) {
						
									if (lTef != null) {
										lPrefix.getTef().add(lTef);
									}
								
					}
				}
				else {
					break _loop25;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lPrefix;
	}
	
	public final Repeat  repeat() throws RecognitionException, TokenStreamException {
		Repeat lRepeat = SCRIPT_FACTORY.createRepeat();
		
		Token  lIni = null;
		Token  lSection = null;
		Token  lName = null;
		
				Tef lTef;
			
		
		try {      // for error handling
			match(START_REPEAT);
			lIni = LT(1);
			match(ID);
			lSection = LT(1);
			match(ID);
			lName = LT(1);
			match(ID);
			{
			_loop29:
			do {
				if ((LA(1)==EOF||LA(1)==END_REPEAT) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					{
					switch ( LA(1)) {
					case EOF:
					{
						match(Token.EOF_TYPE);
						break;
					}
					case END_REPEAT:
					{
						match(END_REPEAT);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					lTef=tef(lRepeat);
					if ( inputState.guessing==0 ) {
						
									if (lTef != null) {
										lRepeat.getTef().add(lTef);
									}
								
					}
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
						IniUtils.getSection(lIni.getText(), lSection.getText(), lRepeat, iResource);
						lRepeat.setName(lName.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lRepeat;
	}
	
	public final TestBlock  testBlock() throws RecognitionException, TokenStreamException {
		TestBlock lTestBlock = SCRIPT_FACTORY.createTestBlock();
		
		Token  lHeap = null;
		Token  lTimeOut = null;
		Token  lServername = null;
		Token  lIniFile = null;
		Token  lPanicString = null;
		Token  lPanicCode = null;
		
				Tef lTef;
				curTestBlock = lTestBlock;
			
		
		try {      // for error handling
			match(START_TEST_BLOCK);
			{
			switch ( LA(1)) {
			case EXLAMATION:
			{
				match(EXLAMATION);
				match(LITERAL_Heap);
				match(EQ);
				lHeap = LT(1);
				match(ID);
				break;
			}
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			lTimeOut = LT(1);
			match(ID);
			lServername = LT(1);
			match(ID);
			lIniFile = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				lTestBlock.setIniFile(lIniFile.getText()); IniUtils.loadIni(lIniFile.getText(), iResource);
			}
			{
			_loop21:
			do {
				if ((LA(1)==EOF) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					match(Token.EOF_TYPE);
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==END_TEST_BLOCK) && (_tokenSet_7.member(LA(2))) && (_tokenSet_8.member(LA(3)))) {
					match(END_TEST_BLOCK);
					{
					_loop20:
					do {
						if ((LA(1)==EXLAMATION) && (LA(2)==LITERAL_PanicString)) {
							{
							match(EXLAMATION);
							match(LITERAL_PanicString);
							match(EQ);
							lPanicString = LT(1);
							match(ID);
							}
							if ( inputState.guessing==0 ) {
								
														if(lPanicString!=null)
														{
															lTestBlock.setPanicString(lPanicString.getText());
														}
								
													
							}
						}
						else if ((LA(1)==EXLAMATION) && (LA(2)==LITERAL_PanicCode)) {
							{
							match(EXLAMATION);
							match(LITERAL_PanicCode);
							match(EQ);
							lPanicCode = LT(1);
							match(ID);
							}
							if ( inputState.guessing==0 ) {
								
														if(lPanicCode!=null)
														{
															lTestBlock.setPanicCode(Integer.parseInt(lPanicCode.getText()));
														}
								
													
							}
						}
						else {
							break _loop20;
						}
						
					} while (true);
					}
					if ( inputState.guessing==0 ) {
						
									//	lIniFileStack.pop();
										break;
									
					}
				}
				else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3)))) {
					lTef=tef(lTestBlock);
					if ( inputState.guessing==0 ) {
						
									if (lTef != null) {
										lTestBlock.getTef().add(lTef);
									}
								
					}
				}
				else {
					break _loop21;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
						lTestBlock.setTimeout(Integer.parseInt(lTimeOut.getText()));
						if(lHeap!=null)
						{
							lTestBlock.setHeap(Integer.parseInt(lHeap.getText()));
						}
						lTestBlock.setServer(lServername.getText());
							
					//	lIniFileStack.push(lIniFile.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTestBlock;
	}
	
	public final TestStep  testStep() throws RecognitionException, TokenStreamException {
		TestStep lTestStep = SCRIPT_FACTORY.createTestStep();
		
		Token  lTimeoutString = null;
		Token  lServer = null;
		Token  lMethod = null;
		Token  lIniToken = null;
		Token  lSectionToken = null;
		
		try {      // for error handling
			match(RUN_TEST_STEP);
			{
			_loop32:
			do {
				if ((LA(1)==EXLAMATION)) {
					negativeTest(lTestStep);
				}
				else {
					break _loop32;
				}
				
			} while (true);
			}
			lTimeoutString = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						try {
							lTestStep.setTimeout(Integer.parseInt(lTimeoutString.getText()));
						} catch (NumberFormatException lNumberFormatException) {
							throw new RecognitionException("Timeout could not be parsed to integer",
								LT(1).getFilename(),
								LT(1).getLine(),
								LT(1).getColumn());
						}
					
			}
			lServer = LT(1);
			match(ID);
			lMethod = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case ID:
			{
				lIniToken = LT(1);
				match(ID);
				lSectionToken = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					
							IniUtils.getSection(lIniToken.getText(), lSectionToken.getText(), lTestStep, iResource);
						
				}
				break;
			}
			case EOF:
			case END_TESTCASE:
			case REMOVE_PREFIX:
			case END_REPEAT:
			case START_TESTCASE:
			case START_TEST_BLOCK:
			case END_TEST_BLOCK:
			case PREFIX:
			case START_REPEAT:
			case RUN_TEST_STEP:
			case PRINT:
			case LOAD_SUITE:
			case LOAD_SERVER:
			case RUN_UTILS:
			case RUN_PROGRAM:
			case RUN_SCRIPT:
			case PAUSE:
			case DELAY:
			case CONSECUTIVE:
			case CONCURRENT:
			case SHARED_DATA:
			case CREATE_OBJECT:
			case RESTORE_OBJECT:
			case STORE:
			case OUTSTANDING:
			case ASYNC_DELAY:
			case COMMAND:
			case SHARED_ACTIVE_SCHEDULER:
			case STORE_ACTIVE_SCHEDULER:
			case COMMENT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						lTestStep.setServer(lServer.getText());
						lTestStep.setMethod(lMethod.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lTestStep;
	}
	
	public final Print  print() throws RecognitionException, TokenStreamException {
		Print lPrint = SCRIPT_FACTORY.createPrint();
		
		Token  lPrintToken = null;
		
		try {      // for error handling
			lPrintToken = LT(1);
			match(PRINT);
			if ( inputState.guessing==0 ) {
				
						lPrint.setPrint(lPrintToken.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lPrint;
	}
	
	public final LoadSuite  loadSuite() throws RecognitionException, TokenStreamException {
		LoadSuite lLoadSuite = SCRIPT_FACTORY.createLoadSuite();
		
		Token  lServer = null;
		
		try {      // for error handling
			match(LOAD_SUITE);
			lServer = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case SHARED_DATA_DASH:
			{
				match(SHARED_DATA_DASH);
				if ( inputState.guessing==0 ) {
					lLoadSuite.setSharedData(true);
				}
				break;
			}
			case EOF:
			case END_TESTCASE:
			case REMOVE_PREFIX:
			case END_REPEAT:
			case START_TESTCASE:
			case START_TEST_BLOCK:
			case END_TEST_BLOCK:
			case PREFIX:
			case START_REPEAT:
			case RUN_TEST_STEP:
			case PRINT:
			case LOAD_SUITE:
			case LOAD_SERVER:
			case RUN_UTILS:
			case RUN_PROGRAM:
			case RUN_SCRIPT:
			case PAUSE:
			case DELAY:
			case CONSECUTIVE:
			case CONCURRENT:
			case SHARED_DATA:
			case CREATE_OBJECT:
			case RESTORE_OBJECT:
			case STORE:
			case OUTSTANDING:
			case ASYNC_DELAY:
			case COMMAND:
			case SHARED_ACTIVE_SCHEDULER:
			case STORE_ACTIVE_SCHEDULER:
			case COMMENT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						lLoadSuite.setServer(lServer.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lLoadSuite;
	}
	
	public final LoadServer  loadServer() throws RecognitionException, TokenStreamException {
		LoadServer lLoadServer = SCRIPT_FACTORY.createLoadServer();
		
		Token  lServer = null;
		
		try {      // for error handling
			match(LOAD_SERVER);
			lServer = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case SHARED_DATA_DASH:
			{
				match(SHARED_DATA_DASH);
				if ( inputState.guessing==0 ) {
					lLoadServer.setSharedData(true);
				}
				break;
			}
			case EOF:
			case END_TESTCASE:
			case REMOVE_PREFIX:
			case END_REPEAT:
			case START_TESTCASE:
			case START_TEST_BLOCK:
			case END_TEST_BLOCK:
			case PREFIX:
			case START_REPEAT:
			case RUN_TEST_STEP:
			case PRINT:
			case LOAD_SUITE:
			case LOAD_SERVER:
			case RUN_UTILS:
			case RUN_PROGRAM:
			case RUN_SCRIPT:
			case PAUSE:
			case DELAY:
			case CONSECUTIVE:
			case CONCURRENT:
			case SHARED_DATA:
			case CREATE_OBJECT:
			case RESTORE_OBJECT:
			case STORE:
			case OUTSTANDING:
			case ASYNC_DELAY:
			case COMMAND:
			case SHARED_ACTIVE_SCHEDULER:
			case STORE_ACTIVE_SCHEDULER:
			case COMMENT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						lLoadServer.setServer(lServer.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lLoadServer;
	}
	
	public final RunUtils  runUtils() throws RecognitionException, TokenStreamException {
		RunUtils lRunUtils = SCRIPT_FACTORY.createRunUtils();
		
		Token  lName = null;
		Token  lParam = null;
		
		try {      // for error handling
			match(RUN_UTILS);
			lName = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						UtilityCommand lCommand = UtilityCommand.getByName(lName.getText());
						if (lCommand != null) {
							lRunUtils.setUtilityCommand(lCommand);
						}
					
			}
			{
			int _cnt45=0;
			_loop45:
			do {
				boolean synPredMatched44 = false;
				if (((_tokenSet_9.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3))))) {
					int _m44 = mark();
					synPredMatched44 = true;
					inputState.guessing++;
					try {
						{
						switch ( LA(1)) {
						case 33:
						{
							match(33);
							break;
						}
						case 34:
						{
							match(34);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
					}
					catch (RecognitionException pe) {
						synPredMatched44 = false;
					}
					rewind(_m44);
inputState.guessing--;
				}
				if ( synPredMatched44 ) {
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==ID) && (_tokenSet_9.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					lParam = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						lRunUtils.getParam().add(lParam.getText());
					}
				}
				else {
					if ( _cnt45>=1 ) { break _loop45; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt45++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lRunUtils;
	}
	
	public final RunProgram  runProgram() throws RecognitionException, TokenStreamException {
		RunProgram lRunProgram = SCRIPT_FACTORY.createRunProgram();
		
		Token  lRunProgramToken = null;
		Token  lTimeout = null;
		Token  lName = null;
		
				StringBuffer lNameBuffer = new StringBuffer();
			
		
		try {      // for error handling
			lRunProgramToken = LT(1);
			match(RUN_PROGRAM);
			lTimeout = LT(1);
			match(ID);
			{
			int _cnt50=0;
			_loop50:
			do {
				boolean synPredMatched49 = false;
				if (((_tokenSet_9.member(LA(1))) && (_tokenSet_5.member(LA(2))) && (_tokenSet_6.member(LA(3))))) {
					int _m49 = mark();
					synPredMatched49 = true;
					inputState.guessing++;
					try {
						{
						match(EOL);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched49 = false;
					}
					rewind(_m49);
inputState.guessing--;
				}
				if ( synPredMatched49 ) {
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==ID) && (_tokenSet_9.member(LA(2))) && (_tokenSet_5.member(LA(3)))) {
					lName = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						lNameBuffer.append(lName.getText() + " ");
					}
				}
				else {
					if ( _cnt50>=1 ) { break _loop50; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt50++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				
						lRunProgram.setName(lNameBuffer.toString());
						if (lRunProgramToken.getText().contains("ws")) {
							lRunProgram.setWS(true);
						}
						try {
							lRunProgram.setTimeout(Integer.parseInt(lTimeout.getText()));
						} catch (NumberFormatException lNumberFormatException) {
							throw new RecognitionException("Timeout could not be parsed to integer",
								LT(1).getFilename(),
								LT(1).getLine(),
								LT(1).getColumn());
						}
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lRunProgram;
	}
	
	public final RunScript  runScript() throws RecognitionException, TokenStreamException {
		RunScript lRunScript = SCRIPT_FACTORY.createRunScript();
		
		Token  lScript = null;
		
		try {      // for error handling
			match(RUN_SCRIPT);
			lScript = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						IniUtils.getScript(lScript.getText(), lRunScript, iResource);
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lRunScript;
	}
	
	public final Pause  pause() throws RecognitionException, TokenStreamException {
		Pause lPause = SCRIPT_FACTORY.createPause();
		
		
		try {      // for error handling
			match(PAUSE);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lPause;
	}
	
	public final Delay  delay() throws RecognitionException, TokenStreamException {
		Delay lDelay = SCRIPT_FACTORY.createDelay();
		
		Token  lTimeoutString = null;
		
		try {      // for error handling
			match(DELAY);
			lTimeoutString = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						try {
							int lTimeout = Integer.parseInt(lTimeoutString.getText());
							lDelay.setTimeout(lTimeout);
						} catch (NumberFormatException lNumberFormatException) {
							// Do nothing
						}
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lDelay;
	}
	
	public final Consecutive  consecutive() throws RecognitionException, TokenStreamException {
		Consecutive lConsecutive = SCRIPT_FACTORY.createConsecutive();
		
		
		try {      // for error handling
			match(CONSECUTIVE);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lConsecutive;
	}
	
	public final Concurrent  concurrent() throws RecognitionException, TokenStreamException {
		Concurrent lConcurrent = SCRIPT_FACTORY.createConcurrent();
		
		
		try {      // for error handling
			match(CONCURRENT);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lConcurrent;
	}
	
	public final SharedData  sharedData() throws RecognitionException, TokenStreamException {
		SharedData lSharedData = SCRIPT_FACTORY.createSharedData();
		
		Token  lIni = null;
		Token  lSection = null;
		
		try {      // for error handling
			match(SHARED_DATA);
			lIni = LT(1);
			match(ID);
			lSection = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						IniUtils.getSection(lIni.getText(), lSection.getText(), lSharedData, iResource);
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lSharedData;
	}
	
	public final CreateObject  create_object() throws RecognitionException, TokenStreamException {
		CreateObject lCreateObject =SCRIPT_FACTORY.createCreateObject();
		
		Token  lObjectType = null;
		Token  lObjectName = null;
		
		try {      // for error handling
			match(CREATE_OBJECT);
			lObjectType = LT(1);
			match(ID);
			lObjectName = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						lCreateObject.setObjectName(lObjectName.getText());
						lCreateObject.setObjectType(lObjectType.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lCreateObject;
	}
	
	public final RestoreObject  restore_object() throws RecognitionException, TokenStreamException {
		RestoreObject lRestoreObject = SCRIPT_FACTORY.createRestoreObject();
		
		Token  lObjectType = null;
		Token  lObjectName = null;
		
		try {      // for error handling
			match(RESTORE_OBJECT);
			lObjectType = LT(1);
			match(ID);
			lObjectName = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						lRestoreObject.setObjectName(lObjectName.getText());
						lRestoreObject.setObjectType(lObjectType.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lRestoreObject;
	}
	
	public final Store  store() throws RecognitionException, TokenStreamException {
		Store lStore = SCRIPT_FACTORY.createStore();
		
		Token  lSection = null;
		
		try {      // for error handling
			match(STORE);
			lSection = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						Section section = IniUtils.getSection(curTestBlock.getIniFile(), lSection.getText(), iResource);
						if (section != null) 
						{
							lStore.setSection(section);
						}		
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lStore;
	}
	
	public final Outstanding  outstanding() throws RecognitionException, TokenStreamException {
		Outstanding lOutstanding =SCRIPT_FACTORY.createOutstanding();
		
		Token  lArgu = null;
		
				int arguCount=0;
			
		
		try {      // for error handling
			match(OUTSTANDING);
			{
			_loop62:
			do {
				if ((LA(1)==ID)) {
					lArgu = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						
											arguCount++;
											if(arguCount==1)
											{
													lOutstanding.setPollInterval(Integer.parseInt(lArgu.getText()));
							
											}
											else if(arguCount==2)
											{
													lOutstanding.setObjectName(lArgu.getText());
						
											}
											else
											{
												break;
											}
										
					}
				}
				else {
					break _loop62;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lOutstanding;
	}
	
	public final AsyncDelay  async_delay() throws RecognitionException, TokenStreamException {
		AsyncDelay lAsynDelay = SCRIPT_FACTORY.createAsyncDelay();
		
		Token  lTimeout = null;
		
		try {      // for error handling
			match(ASYNC_DELAY);
			lTimeout = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				
						lAsynDelay.setTimeout(Integer.parseInt(lTimeout.getText()));
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lAsynDelay;
	}
	
	public final SharedActiveScheduler  shared_active_scheduler() throws RecognitionException, TokenStreamException {
		SharedActiveScheduler lSharedActiveScheduler = SCRIPT_FACTORY.createSharedActiveScheduler();
		
		
		try {      // for error handling
			match(SHARED_ACTIVE_SCHEDULER);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lSharedActiveScheduler;
	}
	
	public final StoreActiveScheduler  store_active_scheduler() throws RecognitionException, TokenStreamException {
		StoreActiveScheduler lStoreActiveScheduler = SCRIPT_FACTORY.createStoreActiveScheduler();
		
		
		try {      // for error handling
			match(STORE_ACTIVE_SCHEDULER);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lStoreActiveScheduler;
	}
	
	public final Command  command() throws RecognitionException, TokenStreamException {
		Command lCommand = SCRIPT_FACTORY.createCommand();
		
		Token  lError = null;
		Token  lAsyncError = null;
		Token  lObjectName = null;
		Token  lFunctionName = null;
		Token  lSection = null;
		
		try {      // for error handling
			match(COMMAND);
			{
			_loop68:
			do {
				if ((LA(1)==EXLAMATION) && (LA(2)==LITERAL_Error)) {
					{
					match(EXLAMATION);
					match(LITERAL_Error);
					match(EQ);
					lError = LT(1);
					match(ID);
					}
				}
				else if ((LA(1)==EXLAMATION) && (LA(2)==LITERAL_AsyncError)) {
					{
					match(EXLAMATION);
					match(LITERAL_AsyncError);
					match(EQ);
					lAsyncError = LT(1);
					match(ID);
					}
				}
				else {
					break _loop68;
				}
				
			} while (true);
			}
			lObjectName = LT(1);
			match(ID);
			lFunctionName = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case ID:
			{
				lSection = LT(1);
				match(ID);
				break;
			}
			case EOF:
			case END_TESTCASE:
			case REMOVE_PREFIX:
			case END_REPEAT:
			case START_TESTCASE:
			case START_TEST_BLOCK:
			case END_TEST_BLOCK:
			case PREFIX:
			case START_REPEAT:
			case RUN_TEST_STEP:
			case PRINT:
			case LOAD_SUITE:
			case LOAD_SERVER:
			case RUN_UTILS:
			case RUN_PROGRAM:
			case RUN_SCRIPT:
			case PAUSE:
			case DELAY:
			case CONSECUTIVE:
			case CONCURRENT:
			case SHARED_DATA:
			case CREATE_OBJECT:
			case RESTORE_OBJECT:
			case STORE:
			case OUTSTANDING:
			case ASYNC_DELAY:
			case COMMAND:
			case SHARED_ACTIVE_SCHEDULER:
			case STORE_ACTIVE_SCHEDULER:
			case COMMENT:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						lCommand.setObjectName(lObjectName.getText());
						lCommand.setFunctionName(lFunctionName.getText());
						if(lError!=null)
						{
							lCommand.setError(Integer.parseInt(lError.getText()));
						}
						if(lAsyncError!=null)
						{	
							lCommand.setAsyncError(Integer.parseInt(lAsyncError.getText()));
						}
						if (lSection != null) 
						{
							Section section = IniUtils.getSection(curTestBlock.getIniFile(), lSection.getText(), iResource);
							if (section != null) 
							{
								lCommand.setSection(section);
							}
						}
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return lCommand;
	}
	
	public final void comment(
		EObject aEObject
	) throws RecognitionException, TokenStreamException {
		
		Token  lCommentToken = null;
		
		try {      // for error handling
			lCommentToken = LT(1);
			match(COMMENT);
			if ( inputState.guessing==0 ) {
				
						EStructuralFeature lFeature = null;
						if (aEObject instanceof TefModel) {
							lFeature = ScriptPackage.Literals.TEF_MODEL__TEF;
						} else {
							lFeature = ScriptPackage.Literals.CONTAINER__TEF;
						}
						
						CommentUtils.setComment(aEObject,
								SCRIPT_FACTORY.createTefComment(),
								lCommentToken,
								lFeature);
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
	}
	
	public final void negativeTest(
		TestStep aTestStep
	) throws RecognitionException, TokenStreamException {
		
		Token  lErrorValue = null;
		Token  lPanicStringValue = null;
		Token  lPanicCodeValue = null;
		Token  lResultValue = null;
		Token  lHeapValue = null;
		Token  lOomValue = null;
		
		try {      // for error handling
			match(EXLAMATION);
			{
			switch ( LA(1)) {
			case LITERAL_Error:
			{
				match(LITERAL_Error);
				match(EQ);
				lErrorValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					aTestStep.setError(lErrorValue.getText());
				}
				break;
			}
			case LITERAL_PanicString:
			{
				match(LITERAL_PanicString);
				match(EQ);
				lPanicStringValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					aTestStep.setPanicString(lPanicStringValue.getText());
				}
				break;
			}
			case LITERAL_PanicCode:
			{
				match(LITERAL_PanicCode);
				match(EQ);
				lPanicCodeValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					
							try {
								aTestStep.setPanicCode(Integer.parseInt(lPanicCodeValue.getText()));
							} catch (NumberFormatException aNumberFormatException) {
								throw new RecognitionException("Panic code could not be parsed to integer",
									LT(1).getFilename(),
									LT(1).getLine(),
									LT(1).getColumn());
							}
						
				}
				break;
			}
			case LITERAL_Result:
			{
				match(LITERAL_Result);
				match(EQ);
				lResultValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					aTestStep.setResult(lResultValue.getText());
				}
				break;
			}
			case LITERAL_Heap:
			{
				match(LITERAL_Heap);
				match(EQ);
				lHeapValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					
							try {
								aTestStep.setHeap(Integer.parseInt(lHeapValue.getText()));
							} catch (NumberFormatException aNumberFormatException) {
								throw new RecognitionException("Heap could not be parsed to integer",
									LT(1).getFilename(),
									LT(1).getLine(),
									LT(1).getColumn());
							}
						
				}
				break;
			}
			case LITERAL_OOM:
			{
				match(LITERAL_OOM);
				match(EQ);
				lOomValue = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					
							try {
								aTestStep.setOOM(Integer.parseInt(lOomValue.getText()));
							} catch (NumberFormatException lNumberFormatException) {
								throw new RecognitionException("OOM could not be parsed to integer",
									LT(1).getFilename(),
									LT(1).getLine(),
									LT(1).getColumn());
							}
						
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			} else {
			  throw ex;
			}
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"WS",
		"TAG",
		"TAG_KEY",
		"TAG_TRAILING_VALUE",
		"EOL",
		"STRING",
		"ID",
		"END_TESTCASE",
		"REMOVE_PREFIX",
		"END_REPEAT",
		"START_TESTCASE",
		"START_TEST_BLOCK",
		"EXLAMATION",
		"\"Heap\"",
		"EQ",
		"END_TEST_BLOCK",
		"\"PanicString\"",
		"\"PanicCode\"",
		"PREFIX",
		"START_REPEAT",
		"RUN_TEST_STEP",
		"\"Error\"",
		"\"Result\"",
		"\"OOM\"",
		"PRINT",
		"LOAD_SUITE",
		"SHARED_DATA_DASH",
		"LOAD_SERVER",
		"RUN_UTILS",
		"\"\\n\"",
		"\"\\r\"",
		"RUN_PROGRAM",
		"RUN_SCRIPT",
		"PAUSE",
		"DELAY",
		"CONSECUTIVE",
		"CONCURRENT",
		"SHARED_DATA",
		"CREATE_OBJECT",
		"RESTORE_OBJECT",
		"STORE",
		"OUTSTANDING",
		"ASYNC_DELAY",
		"COMMAND",
		"\"AsyncError\"",
		"SHARED_ACTIVE_SCHEDULER",
		"STORE_ACTIVE_SCHEDULER",
		"COMMENT",
		"STAR"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 4222097568102402L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 4222097568168962L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4503573856779266L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 4222097568626690L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4222097568693250L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 4503573857303554L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 4222097568692226L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 4222097571838978L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 4222097568627714L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 66560L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	
	}
