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


// $ANTLR : "Ini.g" -> "IniParser.java"$

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

public class IniParser extends antlr.LLkParser       implements IniParserTokenTypes
 {

	/** Factory for EMF Ini Files **/
	protected static final IniFactoryImpl INI_FACTORY = (IniFactoryImpl) IniFactory.eINSTANCE;
	
	/** Collection of Recognition Exceptions **/
	private List<WrappedResourceDiagnostic> iExceptions = new ArrayList<WrappedResourceDiagnostic>();

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

	/** Returns all exceptions that occured during parsing.
	 * 
	 * @return The list of wrapped exceptions that occured during parsing
	 */
	public List<WrappedResourceDiagnostic> getExceptions() {
		return iExceptions;
	}

protected IniParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public IniParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected IniParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public IniParser(TokenStream lexer) {
  this(lexer,1);
}

public IniParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final IniModel  ini() throws RecognitionException, TokenStreamException {
		IniModel lIni = INI_FACTORY.createIniModel();
		
		
				Section lSection = null;
				Data lData = null;
				Comment lComment = null;
			
		
		try {      // for error handling
			{
			_loop384:
			do {
				boolean synPredMatched383 = false;
				if (((_tokenSet_0.member(LA(1))))) {
					int _m383 = mark();
					synPredMatched383 = true;
					inputState.guessing++;
					try {
						{
						match(Token.EOF_TYPE);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched383 = false;
					}
					rewind(_m383);
inputState.guessing--;
				}
				if ( synPredMatched383 ) {
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==LEFT_SQ_BRAKET)) {
					lSection=section();
					if ( inputState.guessing==0 ) {
						lIni.getIni().add(lSection);
					}
				}
				else if ((LA(1)==COMMENT)) {
					comment(lIni);
				}
				else if ((LA(1)==ID)) {
					lData=data();
					if ( inputState.guessing==0 ) {
						lIni.getIni().add(lData);
					}
				}
				else {
					break _loop384;
				}
				
			} while (true);
			}
			match(Token.EOF_TYPE);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		return lIni;
	}
	
	public final Section  section() throws RecognitionException, TokenStreamException {
		Section lSection = INI_FACTORY.createSection();
		
		Token  lName = null;
		
				Data lData = null;
			
		
		try {      // for error handling
			match(LEFT_SQ_BRAKET);
			lName = LT(1);
			match(ID);
			match(RIGHT_SQ_BRAKET);
			if ( inputState.guessing==0 ) {
				lSection.setName(lName.getText());
			}
			{
			_loop389:
			do {
				boolean synPredMatched388 = false;
				if (((_tokenSet_0.member(LA(1))))) {
					int _m388 = mark();
					synPredMatched388 = true;
					inputState.guessing++;
					try {
						{
						switch ( LA(1)) {
						case LEFT_SQ_BRAKET:
						{
							match(LEFT_SQ_BRAKET);
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
					}
					catch (RecognitionException pe) {
						synPredMatched388 = false;
					}
					rewind(_m388);
inputState.guessing--;
				}
				if ( synPredMatched388 ) {
					if ( inputState.guessing==0 ) {
						break;
					}
				}
				else if ((LA(1)==COMMENT)) {
					comment(lSection);
				}
				else if ((LA(1)==ID)) {
					lData=data();
					if ( inputState.guessing==0 ) {
						lSection.getIniLeaf().add(lData);
					}
				}
				else {
					break _loop389;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		return lSection;
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
						if (aEObject instanceof Section) {
							lFeature = IniPackage.Literals.SECTION__INI_LEAF;
						} else if (aEObject instanceof IniModel) {
							lFeature = IniPackage.Literals.INI_MODEL__INI;
						}
						
						CommentUtils.setComment(aEObject, INI_FACTORY.createIniComment(), lCommentToken, lFeature);
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
	}
	
	public final Data  data() throws RecognitionException, TokenStreamException {
		Data lDataMap = INI_FACTORY.createData();
		
		Token  lKey = null;
		Token  lValue = null;
		
		try {      // for error handling
			lKey = LT(1);
			match(ID);
			lValue = LT(1);
			match(VALUE);
			if ( inputState.guessing==0 ) {
				
						lDataMap.setKey(lKey.getText());
						lDataMap.setValue(lValue.getText());
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		return lDataMap;
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
		"LEFT_SQ_BRAKET",
		"RIGHT_SQ_BRAKET",
		"VALUE",
		"COMMENT",
		"FORWARD_SLASH",
		"STAR"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 19458L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
