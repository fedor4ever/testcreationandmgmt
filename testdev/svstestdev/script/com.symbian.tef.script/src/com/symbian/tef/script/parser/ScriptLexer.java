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


// $ANTLR 2.7.7 (20060930): "Script.g" -> "ScriptLexer.java"$
 	
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


import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class ScriptLexer extends antlr.CharScanner implements ScriptParserTokenTypes, TokenStream
 {

	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();
	
	/** Stream Selector **/
	private TokenStreamSelector sSelector;
	
	/** Set the selector for this lexor so that the lexer can switch if needed.
	 * 
	 * @param selector The selector to choose the lexor
	 */
	public void setTokenStreamSelector(TokenStreamSelector selector) {
		sSelector = selector;
	}
	
	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);
		
		WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
			.setColumn(aRecognitionException.getColumn())
			.setLine(aRecognitionException.getLine())
			.setLocation(aRecognitionException.getFilename());

		iExceptions.add(lDiagnostic);
	}
	
	/** Recover from a lexical error.
	 * 
	 * @param aRecognitionException The ANTLR Lexer exception.
	 * @param aBitSet The bitset related to this error.
	 * @throws CharStreamException If it failed to make the EOF token.
	 * @throws TokenStreamException If it failed to make the EOF token.
	 */
	private void recover(RecognitionException aRecognitionException, BitSet aBitSet) throws CharStreamException, TokenStreamException {
		if (LA(1) == '\uFFFF') {
			uponEOF();
			_returnToken = makeToken(Token.EOF_TYPE);
		}
	}
	
	/** Returns all exceptions that occured during lexing the inputstream.
	 * 
	 * @return A list of WrappedException from the lexor.
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}
public ScriptLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public ScriptLexer(Reader in) {
	this(new CharBuffer(in));
}
public ScriptLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public ScriptLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = false;
	setCaseSensitive(false);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("\n", this), new Integer(33));
	literals.put(new ANTLRHashString("PanicCode", this), new Integer(21));
	literals.put(new ANTLRHashString("Heap", this), new Integer(17));
	literals.put(new ANTLRHashString("OOM", this), new Integer(27));
	literals.put(new ANTLRHashString("\r", this), new Integer(34));
	literals.put(new ANTLRHashString("AsyncError", this), new Integer(48));
	literals.put(new ANTLRHashString("Result", this), new Integer(26));
	literals.put(new ANTLRHashString("PanicString", this), new Integer(20));
	literals.put(new ANTLRHashString("Error", this), new Integer(25));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '=':
				{
					mEQ(true);
					theRetToken=_returnToken;
					break;
				}
				case '!':
				{
					mEXLAMATION(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					//edit manually for lex ambiguity
					if ((LA(1)=='s') && (LA(2)=='h') && (LA(3)=='a') && (LA(4)=='r') && (LA(5)=='e') && (LA(6)=='d') && (LA(7)=='_') && (LA(8)=='a') ) {
						mSHARED_ACTIVE_SCHEDULER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='s') && (LA(2)=='h') && (LA(3)=='a') && (LA(4)=='r') && (LA(5)=='e') && (LA(6)=='d') && (LA(7)=='_')) {
						mSHARED_DATA(true);
						theRetToken=_returnToken;
					}					
					else if ((LA(1)=='s') && (LA(2)=='t') && (LA(3)=='a') && (LA(4)=='r') && (LA(5)=='t') && (LA(6)=='_') && (LA(7)=='t') && (LA(8)=='e') && (LA(9)=='s') && (LA(10)=='t') && (LA(11)=='_')) {
						mSTART_TEST_BLOCK(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='e') && (LA(2)=='n') && (LA(3)=='d') && (LA(4)=='_') && (LA(5)=='t') && (LA(6)=='e') && (LA(7)=='s') && (LA(8)=='t') && (LA(9)=='_') ) {
						mEND_TEST_BLOCK(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='s') && (LA(3)=='h') && (LA(4)=='a') && (LA(5)=='r') && (LA(6)=='e') && (LA(7)=='d')) {
						mSHARED_DATA_DASH(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='c') && (LA(2)=='o') && (LA(3)=='n') && (LA(4)=='c') && (LA(5)=='u') && (LA(6)=='r') && (LA(7)=='r')) {
						mCONCURRENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='c') && (LA(2)=='o') && (LA(3)=='n') && (LA(4)=='s') && (LA(5)=='e') && (LA(6)=='c') && (LA(7)=='u')) {
						mCONSECUTIVE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='s') && (LA(6)=='c') && (LA(7)=='r')) {
						mRUN_SCRIPT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='p'||LA(5)=='w') && (LA(6)=='r'||LA(6)=='s') && (LA(7)=='_'||LA(7)=='o')) {
						mRUN_PROGRAM(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='u') && (LA(6)=='t') && (LA(7)=='i')) {
						mRUN_UTILS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='l') && (LA(2)=='o') && (LA(3)=='a') && (LA(4)=='d') && (LA(5)=='_') && (LA(6)=='s') && (LA(7)=='e')) {
						mLOAD_SERVER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='l') && (LA(2)=='o') && (LA(3)=='a') && (LA(4)=='d') && (LA(5)=='_') && (LA(6)=='s') && (LA(7)=='u')) {
						mLOAD_SUITE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='e') && (LA(2)=='n') && (LA(3)=='d') && (LA(4)=='_') && (LA(5)=='r') && (LA(6)=='e') && (LA(7)=='p')) {
						mEND_REPEAT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='t') && (LA(6)=='e') && (LA(7)=='s')) {
						mRUN_TEST_STEP(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='s') && (LA(2)=='t') && (LA(3)=='a') && (LA(4)=='r') && (LA(5)=='t') && (LA(6)=='_') && (LA(7)=='r')) {
						mSTART_REPEAT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='e') && (LA(3)=='m') && (LA(4)=='o') && (LA(5)=='v') && (LA(6)=='e') && (LA(7)=='_')) {
						mREMOVE_PREFIX(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='s') && (LA(2)=='t') && (LA(3)=='a') && (LA(4)=='r') && (LA(5)=='t') && (LA(6)=='_') && (LA(7)=='t')) {
						mSTART_TESTCASE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='e') && (LA(2)=='n') && (LA(3)=='d') && (LA(4)=='_') && (LA(5)=='t') && (LA(6)=='e') && (LA(7)=='s')) {
						mEND_TESTCASE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='c') && (LA(2)=='r') && (LA(3)=='e') && (LA(4)=='a') && (LA(5)=='t') && (LA(6)=='e') && (LA(7)=='_')) {
						mCREATE_OBJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='r') && (LA(2)=='e') && (LA(3)=='s') && (LA(4)=='t') && (LA(5)=='o') && (LA(6)=='r') && (LA(7)=='e')) {
						mRESTORE_OBJECT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='c') && (LA(2)=='o') && (LA(3)=='m') && (LA(4)=='m') && (LA(5)=='a') && (LA(6)=='n') && (LA(7)=='d')) {
						mCOMMAND(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='o') && (LA(2)=='u') && (LA(3)=='t') && (LA(4)=='s') && (LA(5)=='t') && (LA(6)=='a') && (LA(7)=='n')) {
						mOUTSTANDING(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='a') && (LA(2)=='s') && (LA(3)=='y') && (LA(4)=='n') && (LA(5)=='c') && (LA(6)=='_') && (LA(7)=='d')) {
						mASYNC_DELAY(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='s') && (LA(2)=='t') && (LA(3)=='o') && (LA(4)=='r') && (LA(5)=='e') && (LA(6)=='_') && (LA(7)=='a')) {
						mSTORE_ACTIVE_SCHEDULER(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='p') && (LA(2)=='r') && (LA(3)=='e') && (LA(4)=='f') && (LA(5)=='i') && (LA(6)=='x') && (true)) {
						mPREFIX(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='p') && (LA(2)=='r') && (LA(3)=='i') && (LA(4)=='n') && (LA(5)=='t') && (LA(6)=='\t'||LA(6)=='\n'||LA(6)=='\r'||LA(6)==' ')) {
						mPRINT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='d') && (LA(2)=='e') && (LA(3)=='l') && (LA(4)=='a') && (LA(5)=='y') && (true) && (true)) {
						mDELAY(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='p') && (LA(2)=='a') && (LA(3)=='u') && (LA(4)=='s') && (LA(5)=='e') && (true) && (true)) {
						mPAUSE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='s') && (LA(2)=='t') && (LA(3)=='o') && (LA(4)=='r') && (LA(5)=='e') && (true) && (true)) {
						mSTORE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='*'||LA(2)=='/') && (true) && (true) && (true) && (true) && (true)) {
						mCOMMENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='\t'||LA(1)=='\n'||LA(1)=='\r'||LA(1)==' ') && (true) && (true) && (true) && (true) && (true) && (true)) {
						mWS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='\n'||LA(1)=='\r') && (true) && (true) && (true) && (true) && (true) && (true)) {
						mEOL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (true) && (true) && (true) && (true) && (true) && (true)) {
						mSTAR(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true) && (true) && (true) && (true)) {
						mID(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				reportError(e);
				consume();
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		try {      // for error handling
			{
			int _cnt76=0;
			_loop76:
			do {
				switch ( LA(1)) {
				case ' ':
				{
					match(' ');
					break;
				}
				case '\t':
				{
					match('\t');
					break;
				}
				case '\n':
				{
					match('\n');
					newline();
					break;
				}
				case '\r':
				{
					match('\r');
					{
					if ((LA(1)=='\n') && (true) && (true) && (true) && (true) && (true) && (true)) {
						match('\n');
					}
					else {
					}
					
					}
					newline();
					break;
				}
				default:
				{
					if ( _cnt76>=1 ) { break _loop76; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				_cnt76++;
			} while (true);
			}
			_ttype = Token.SKIP;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEOL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EOL;
		int _saveIndex;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case '\n':
			{
				_saveIndex=text.length();
				match('\n');
				text.setLength(_saveIndex);
				break;
			}
			case '\r':
			{
				_saveIndex=text.length();
				match('\r');
				text.setLength(_saveIndex);
				{
				if ((LA(1)=='\n')) {
					_saveIndex=text.length();
					match('\n');
					text.setLength(_saveIndex);
				}
				else {
				}
				
				}
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			newline();
			
					_ttype = Token.SKIP;
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EQ;
		int _saveIndex;
		
		try {      // for error handling
			match('=');
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEXLAMATION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EXLAMATION;
		int _saveIndex;
		
		try {      // for error handling
			match('!');
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STAR;
		int _saveIndex;
		
		try {      // for error handling
			match('*');
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSHARED_DATA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SHARED_DATA;
		int _saveIndex;
		
		try {      // for error handling
			match("shared_data");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSHARED_DATA_DASH(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SHARED_DATA_DASH;
		int _saveIndex;
		
		try {      // for error handling
			match("-shareddata");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCONCURRENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CONCURRENT;
		int _saveIndex;
		
		try {      // for error handling
			match("concurrent");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCONSECUTIVE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CONSECUTIVE;
		int _saveIndex;
		
		try {      // for error handling
			match("consecutive");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDELAY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DELAY;
		int _saveIndex;
		
		try {      // for error handling
			match("delay");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPAUSE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PAUSE;
		int _saveIndex;
		
		try {      // for error handling
			match("pause");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRUN_SCRIPT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RUN_SCRIPT;
		int _saveIndex;
		
		try {      // for error handling
			match("run_script");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRUN_PROGRAM(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RUN_PROGRAM;
		int _saveIndex;
		
		try {      // for error handling
			if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='p')) {
				match("run_program");
			}
			else if ((LA(1)=='r') && (LA(2)=='u') && (LA(3)=='n') && (LA(4)=='_') && (LA(5)=='w')) {
				match("run_ws_program");
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRUN_UTILS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RUN_UTILS;
		int _saveIndex;
		
		try {      // for error handling
			match("run_utils");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLOAD_SERVER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LOAD_SERVER;
		int _saveIndex;
		
		try {      // for error handling
			match("load_server");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLOAD_SUITE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LOAD_SUITE;
		int _saveIndex;
		
		try {      // for error handling
			match("load_suite");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEND_REPEAT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = END_REPEAT;
		int _saveIndex;
		
		try {      // for error handling
			match("end_repeat");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPREFIX(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PREFIX;
		int _saveIndex;
		
		try {      // for error handling
			match("prefix");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRUN_TEST_STEP(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RUN_TEST_STEP;
		int _saveIndex;
		
		try {      // for error handling
			match("run_test_step");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTART_REPEAT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = START_REPEAT;
		int _saveIndex;
		
		try {      // for error handling
			match("start_repeat");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mREMOVE_PREFIX(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = REMOVE_PREFIX;
		int _saveIndex;
		
		try {      // for error handling
			match("remove_prefix");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTART_TESTCASE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = START_TESTCASE;
		int _saveIndex;
		
		try {      // for error handling
			match("start_testcase");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEND_TESTCASE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = END_TESTCASE;
		int _saveIndex;
		
		try {      // for error handling
			match("end_testcase");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCREATE_OBJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = CREATE_OBJECT;
		int _saveIndex;
		
		try {      // for error handling
			match("create_object");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRESTORE_OBJECT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RESTORE_OBJECT;
		int _saveIndex;
		
		try {      // for error handling
			match("restore_object");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMAND;
		int _saveIndex;
		
		try {      // for error handling
			match("command");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTORE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STORE;
		int _saveIndex;
		
		try {      // for error handling
			match("store");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mOUTSTANDING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = OUTSTANDING;
		int _saveIndex;
		
		try {      // for error handling
			match("outstanding");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASYNC_DELAY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASYNC_DELAY;
		int _saveIndex;
		
		try {      // for error handling
			match("async_delay");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTART_TEST_BLOCK(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = START_TEST_BLOCK;
		int _saveIndex;
		
		try {      // for error handling
			match("start_test_block");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEND_TEST_BLOCK(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = END_TEST_BLOCK;
		int _saveIndex;
		
		try {      // for error handling
			match("end_test_block");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSHARED_ACTIVE_SCHEDULER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SHARED_ACTIVE_SCHEDULER;
		int _saveIndex;
		
		try {      // for error handling
			match("shared_active_scheduler");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTORE_ACTIVE_SCHEDULER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STORE_ACTIVE_SCHEDULER;
		int _saveIndex;
		
		try {      // for error handling
			match("store_active_scheduler");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPRINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PRINT;
		int _saveIndex;
		Token lPrint=null;
		
		try {      // for error handling
			_saveIndex=text.length();
			match("print");
			text.setLength(_saveIndex);
			{
			switch ( LA(1)) {
			case '\t':  case ' ':
			{
				{
				int _cnt114=0;
				_loop114:
				do {
					if ((LA(1)==' ') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe')) && (true) && (true) && (true) && (true) && (true)) {
						_saveIndex=text.length();
						match(' ');
						text.setLength(_saveIndex);
					}
					else if ((LA(1)=='\t') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe')) && (true) && (true) && (true) && (true) && (true)) {
						_saveIndex=text.length();
						match('\t');
						text.setLength(_saveIndex);
					}
					else {
						if ( _cnt114>=1 ) { break _loop114; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt114++;
				} while (true);
				}
				mSTRING(true);
				lPrint=_returnToken;
				text.setLength(_begin); text.append(lPrint.getText());
				break;
			}
			case '\r':
			{
				_saveIndex=text.length();
				match('\r');
				text.setLength(_saveIndex);
				break;
			}
			case '\n':
			{
				_saveIndex=text.length();
				match('\n');
				text.setLength(_saveIndex);
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	private final void mSTRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STRING;
		int _saveIndex;
		
		try {      // for error handling
			{
			_loop122:
			do {
				if ((LA(1)==' ') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe')) && (true) && (true) && (true) && (true) && (true)) {
					_saveIndex=text.length();
					match(' ');
					text.setLength(_saveIndex);
				}
				else if ((LA(1)=='\t') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe')) && (true) && (true) && (true) && (true) && (true)) {
					_saveIndex=text.length();
					match('\t');
					text.setLength(_saveIndex);
				}
				else {
					break _loop122;
				}
				
			} while (true);
			}
			{
			_loop125:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					{
					match(_tokenSet_2);
					}
				}
				else {
					break _loop125;
				}
				
			} while (true);
			}
			_saveIndex=text.length();
			mEOL(false);
			text.setLength(_saveIndex);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMENT;
		int _saveIndex;
		
		try {      // for error handling
			{
			if ((LA(1)=='/') && (LA(2)=='/')) {
				_saveIndex=text.length();
				match("//");
				text.setLength(_saveIndex);
			}
			else if ((LA(1)=='/') && (LA(2)=='*')) {
				match("/*");
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			}
			
					sSelector.push("CommentLexer");
					_token = sSelector.nextToken();
					_token.setType(_ttype);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mID(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ID;
		int _saveIndex;
		
		try {      // for error handling
			{
			int _cnt119=0;
			_loop119:
			do {
				switch ( LA(1)) {
				case 'a':  case 'b':  case 'c':  case 'd':
				case 'e':  case 'f':  case 'g':  case 'h':
				case 'i':  case 'j':  case 'k':  case 'l':
				case 'm':  case 'n':  case 'o':  case 'p':
				case 'q':  case 'r':  case 's':  case 't':
				case 'u':  case 'v':  case 'w':  case 'x':
				case 'y':  case 'z':
				{
					matchRange('a','z');
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
				case '.':
				{
					match('.');
					break;
				}
				case ',':
				{
					match(',');
					break;
				}
				case '-':
				{
					match('-');
					break;
				}
				case '_':
				{
					match('_');
					break;
				}
				case '[':
				{
					match('[');
					break;
				}
				case ']':
				{
					match(']');
					break;
				}
				case ':':
				{
					match(':');
					break;
				}
				case '\\':
				{
					match('\\');
					break;
				}
				case '/':
				{
					match('/');
					break;
				}
				case '*':
				{
					match('*');
					break;
				}
				case '~':
				{
					match('~');
					break;
				}
				default:
				{
					if ( _cnt119>=1 ) { break _loop119; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				_cnt119++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		data[0]=576447558163890176L;
		data[1]=5188146765227884544L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[1025];
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		data[0]=-9217L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	
	}
