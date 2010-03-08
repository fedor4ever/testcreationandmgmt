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

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.InputBuffer;
import antlr.LexerSharedInputState;
import antlr.NoViableAltForCharException;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamSelector;
import antlr.collections.impl.BitSet;

import com.symbian.ini.util.WrappedResourceDiagnostic;

public class CommentLexer extends antlr.CharScanner implements
		CommentLexerTokenTypes, TokenStream {

	/** Collection of Recognition Exceptions * */
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();

	/** Stream Selector * */
	private TokenStreamSelector sSelector;

	/**
	 * Set the selector for this lexor so that the lexer can switch if needed.
	 * 
	 * @param selector
	 *            The selector to choose the lexor
	 */
	public void setTokenStreamSelector(TokenStreamSelector selector) {
		sSelector = selector;
	}

	@Override
	public void reportError(RecognitionException aRecognitionException) {
		super.reportError(aRecognitionException);

		if (!aRecognitionException.getMessage().contains("0xFFFF")) {
			WrappedResourceDiagnostic lDiagnostic = new WrappedResourceDiagnostic(
					aRecognitionException).setColumn(
					aRecognitionException.getColumn()).setLine(
					aRecognitionException.getLine()).setLocation(
					aRecognitionException.getFilename());

			iExceptions.add(lDiagnostic);
		}
	}

	/**
	 * Recover from a lexical error.
	 * 
	 * @param aRecognitionException
	 *            The ANTLR Lexer exception.
	 * @param aBitSet
	 *            The bitset related to this error.
	 * @throws CharStreamException
	 *             If it failed to make the EOF token.
	 * @throws TokenStreamException
	 *             If it failed to make the EOF token.
	 */
	private void recover(RecognitionException aRecognitionException,
			BitSet aBitSet) throws CharStreamException, TokenStreamException {
		if (LA(1) == '\uFFFF') {
			uponEOF();
			_returnToken = makeToken(Token.EOF_TYPE);
		}
	}

	/**
	 * Returns all exceptions that occured during lexing the inputstream.
	 * 
	 * @return A list of WrappedException from the lexor.
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}

	public CommentLexer(InputStream in) {
		this(new ByteBuffer(in));
	}

	public CommentLexer(Reader in) {
		this(new CharBuffer(in));
	}

	public CommentLexer(InputBuffer ib) {
		this(new LexerSharedInputState(ib));
	}

	public CommentLexer(LexerSharedInputState state) {
		super(state);
		caseSensitiveLiterals = true;
		setCaseSensitive(true);
		literals = new Hashtable();
	}

	public Token nextToken() throws TokenStreamException {
		Token theRetToken = null;
		tryAgain: for (;;) {
			Token _token = null;
			int _ttype = Token.INVALID_TYPE;
			resetText();
			try { // for char stream error handling
				try { // for lexical error handling
					if ((LA(1) == '\t' || LA(1) == ' ')) {
						mWS(true);
						theRetToken = _returnToken;
					} else if ((LA(1) == '!')) {
						mTAG(true);
						theRetToken = _returnToken;
					} else if (((LA(1) >= '\u0000' && LA(1) <= '\ufffe'))) {
						mSTRING(true);
						theRetToken = _returnToken;
					} else if ((LA(1) == '\n' || LA(1) == '\r')) {
						mEOL(true);
						theRetToken = _returnToken;
					} else {
						if (LA(1) == EOF_CHAR) {
							uponEOF();
							_returnToken = makeToken(Token.EOF_TYPE);
						} else {
							throw new NoViableAltForCharException((char) LA(1),
									getFilename(), getLine(), getColumn());
						}
					}

					if (_returnToken == null)
						continue tryAgain; // found SKIP token
					_ttype = _returnToken.getType();
					_ttype = testLiteralsTable(_ttype);
					_returnToken.setType(_ttype);
					return _returnToken;
				} catch (RecognitionException e) {
					reportError(e);
					consume();
				}
			} catch (CharStreamException cse) {
				if (cse instanceof CharStreamIOException) {
					throw new TokenStreamIOException(
							((CharStreamIOException) cse).io);
				} else {
					throw new TokenStreamException(cse.getMessage());
				}
			}
		}
	}

	public final void mWS(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = WS;
		int _saveIndex;

		try { // for error handling
			{
				int _cnt91 = 0;
				_loop91: do {
					switch (LA(1)) {
					case ' ': {
						match(' ');
						_ttype = Token.SKIP;
						break;
					}
					case '\t': {
						match('\t');
						_ttype = Token.SKIP;
						break;
					}
					default: {
						if (_cnt91 >= 1) {
							break _loop91;
						} else {
							throw new NoViableAltForCharException((char) LA(1),
									getFilename(), getLine(), getColumn());
						}
					}
					}
					_cnt91++;
				} while (true);
			}
		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mTAG(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = TAG;
		int _saveIndex;
		Token lTagKey = null;
		Token lTagValue = null;

		try { // for error handling
			_saveIndex = text.length();
			match('!');
			text.setLength(_saveIndex);
			{
				_loop94: do {
					if ((LA(1) == ' ')) {
						_saveIndex = text.length();
						match(' ');
						text.setLength(_saveIndex);
					} else if ((LA(1) == '\t')) {
						_saveIndex = text.length();
						match('\t');
						text.setLength(_saveIndex);
					} else {
						break _loop94;
					}

				} while (true);
			}
			{
				if ((LA(1) == '@')) {
					mTAG_KEY(true);
					lTagKey = _returnToken;
					_token = lTagKey;
				} else if (((LA(1) >= '\u0000' && LA(1) <= '\ufffe'))) {
					mTAG_TRAILING_VALUE(true);
					lTagValue = _returnToken;
					_token = lTagValue;
				} else {
					throw new NoViableAltForCharException((char) LA(1),
							getFilename(), getLine(), getColumn());
				}

			}
		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	private final void mTAG_KEY(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = TAG_KEY;
		int _saveIndex;
		Token lTagKey = null;
		Token lTagValue = null;

		CommentTagToken lCommentTokenTag = new CommentTagToken();

		try { // for error handling
			_saveIndex = text.length();
			match('@');
			text.setLength(_saveIndex);
			mID(true);
			lTagKey = _returnToken;
			{
				if (((LA(1) >= '\u0000' && LA(1) <= '\ufffe'))) {
					mSTRING(true);
					lTagValue = _returnToken;
				} else {
				}

			}

			lCommentTokenTag.setTag(lTagKey.getText());
			lCommentTokenTag.setValue(lTagValue.getText());
			_token = lCommentTokenTag;

		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	private final void mTAG_TRAILING_VALUE(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = TAG_TRAILING_VALUE;
		int _saveIndex;
		Token lTagValue = null;

		CommentTagToken lCommentTokenTag = new CommentTagToken();

		try { // for error handling
			mSTRING(true);
			lTagValue = _returnToken;

			lCommentTokenTag.setTrailingValue(lTagValue.getText());
			_token = lCommentTokenTag;

		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	private final void mID(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = ID;
		int _saveIndex;

		try { // for error handling
			{
				_loop110: do {
					switch (LA(1)) {
					case ' ': {
						_saveIndex = text.length();
						match(' ');
						text.setLength(_saveIndex);
						break;
					}
					case '\t': {
						_saveIndex = text.length();
						match('\t');
						text.setLength(_saveIndex);
						break;
					}
					default: {
						break _loop110;
					}
					}
				} while (true);
			}
			{
				int _cnt113 = 0;
				_loop113: do {
					if ((_tokenSet_1.member(LA(1)))) {
						{
							match(_tokenSet_1);
						}
					} else {
						if (_cnt113 >= 1) {
							break _loop113;
						} else {
							throw new NoViableAltForCharException((char) LA(1),
									getFilename(), getLine(), getColumn());
						}
					}

					_cnt113++;
				} while (true);
			}
		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_2);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mSTRING(boolean _createToken)
			throws RecognitionException, CharStreamException,
			TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = STRING;
		int _saveIndex;

		try { // for error handling
			{
				_loop104: do {
					if ((LA(1) == ' ')) {
						_saveIndex = text.length();
						match(' ');
						text.setLength(_saveIndex);
					} else if ((LA(1) == '\t')) {
						_saveIndex = text.length();
						match('\t');
						text.setLength(_saveIndex);
					} else {
						break _loop104;
					}

				} while (true);
			}
			{
				_loop107: do {
					if ((_tokenSet_3.member(LA(1)))) {
						{
							match(_tokenSet_3);
						}
					} else {
						break _loop107;
					}

				} while (true);
			}
			_saveIndex = text.length();
			mEOL(false);
			text.setLength(_saveIndex);
		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	public final void mEOL(boolean _createToken) throws RecognitionException,
			CharStreamException, TokenStreamException {
		int _ttype;
		Token _token = null;
		int _begin = text.length();
		_ttype = EOL;
		int _saveIndex;

		try { // for error handling
			{
				switch (LA(1)) {
				case '\n': {
					_saveIndex = text.length();
					match('\n');
					text.setLength(_saveIndex);
					break;
				}
				case '\r': {
					_saveIndex = text.length();
					match('\r');
					text.setLength(_saveIndex);
					{
						if ((LA(1) == '\n')) {
							_saveIndex = text.length();
							match('\n');
							text.setLength(_saveIndex);
						} else {
						}

					}
					break;
				}
				default: {
					throw new NoViableAltForCharException((char) LA(1),
							getFilename(), getLine(), getColumn());
				}
				}
			}

			newline();
			sSelector.pop();

		} catch (RecognitionException ex) {
			reportError(ex);
			recover(ex, _tokenSet_0);
		}
		if (_createToken && _token == null && _ttype != Token.SKIP) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()
					- _begin));
		}
		_returnToken = _token;
	}

	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		return data;
	}

	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());

	private static final long[] mk_tokenSet_1() {
		long[] data = new long[2048];
		data[0] = -4294977025L;
		for (int i = 1; i <= 1022; i++) {
			data[i] = -1L;
		}
		data[1023] = 9223372036854775807L;
		return data;
	}

	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());

	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		for (int i = 0; i <= 1022; i++) {
			data[i] = -1L;
		}
		data[1023] = 9223372036854775807L;
		return data;
	}

	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());

	private static final long[] mk_tokenSet_3() {
		long[] data = new long[2048];
		data[0] = -9217L;
		for (int i = 1; i <= 1022; i++) {
			data[i] = -1L;
		}
		data[1023] = 9223372036854775807L;
		return data;
	}

	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());

}
