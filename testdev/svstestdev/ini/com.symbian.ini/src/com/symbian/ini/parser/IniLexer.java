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

public class IniLexer extends antlr.CharScanner implements IniParserTokenTypes, TokenStream
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
		
		if (!aRecognitionException.getMessage().contains("0xFFFF")) {
			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(aRecognitionException)
				.setColumn(aRecognitionException.getColumn())
				.setLine(aRecognitionException.getLine())
				.setLocation(aRecognitionException.getFilename());
	
			iExceptions.add(lDiagnostic);
		}
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
public IniLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public IniLexer(Reader in) {
	this(new CharBuffer(in));
}
public IniLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public IniLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
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
				case '[':
				{
					mLEFT_SQ_BRAKET(true);
					theRetToken=_returnToken;
					break;
				}
				case ']':
				{
					mRIGHT_SQ_BRAKET(true);
					theRetToken=_returnToken;
					break;
				}
				case '=':
				{
					mVALUE(true);
					theRetToken=_returnToken;
					break;
				}
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mWS(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='/') && (LA(2)=='*'||LA(2)=='/')) {
						mCOMMENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (true)) {
						mFORWARD_SLASH(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (true)) {
						mSTAR(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_0.member(LA(1))) && (true)) {
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
				_ttype = testLiteralsTable(_ttype);
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

	public final void mLEFT_SQ_BRAKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LEFT_SQ_BRAKET;
		int _saveIndex;
		
		try {      // for error handling
			match('[');
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
	
	public final void mRIGHT_SQ_BRAKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RIGHT_SQ_BRAKET;
		int _saveIndex;
		
		try {      // for error handling
			match(']');
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
	
	public final void mFORWARD_SLASH(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = FORWARD_SLASH;
		int _saveIndex;
		
		try {      // for error handling
			match('/');
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
	
	public final void mVALUE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = VALUE;
		int _saveIndex;
		Token lValue=null;
		Token lOtherValue=null;
		
				StringBuffer lValueBuffer = new StringBuffer();
				int line, column = 0;
			
		
		try {      // for error handling
			_saveIndex=text.length();
			match('=');
			text.setLength(_saveIndex);
			mSTRING(true);
			lValue=_returnToken;
			lValueBuffer.append(lValue.getText());
			{
			_loop398:
			do {
				if (((LA(1) >= '\u0000' && LA(1) <= '\ufffe'))) {
					
							boolean lIsEqual = false;
							for (int lLookAhead = 1; lLookAhead < 50; lLookAhead++) {
								try {
									if (LA(lLookAhead) == '=') {
										lIsEqual = true;
									}
								} catch (Throwable lThrowable) {
									break;
								}
							}
					
							if (LA(1) == '[' || LA(1) == '/' || lIsEqual) {
								break;
							}
						
					mSTRING(true);
					lOtherValue=_returnToken;
					
							lValueBuffer.append("\n" + lOtherValue.getText());
						
				}
				else {
					break _loop398;
				}
				
			} while (true);
			}
			
					text.setLength(_begin); text.append(lValueBuffer.toString());
				
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
			_loop411:
			do {
				if ((LA(1)==' ') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe'))) {
					_saveIndex=text.length();
					match(' ');
					text.setLength(_saveIndex);
				}
				else if ((LA(1)=='\t') && ((LA(2) >= '\u0000' && LA(2) <= '\ufffe'))) {
					_saveIndex=text.length();
					match('\t');
					text.setLength(_saveIndex);
				}
				else {
					break _loop411;
				}
				
			} while (true);
			}
			{
			_loop414:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					{
					match(_tokenSet_2);
					}
				}
				else {
					break _loop414;
				}
				
			} while (true);
			}
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
				if ((LA(1)=='\n') && (true)) {
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
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
	
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		try {      // for error handling
			{
			int _cnt404=0;
			_loop404:
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
					if ((LA(1)=='\n') && (true)) {
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
					if ( _cnt404>=1 ) { break _loop404; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				_cnt404++;
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
	
	public final void mID(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ID;
		int _saveIndex;
		
		try {      // for error handling
			{
			int _cnt408=0;
			_loop408:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					{
					match(_tokenSet_0);
					}
				}
				else {
					if ( _cnt408>=1 ) { break _loop408; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt408++;
			} while (true);
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
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[2048];
		data[0]=-2305843013508670977L;
		data[1]=-671088641L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
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
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[2048];
		for (int i = 0; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
