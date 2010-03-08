/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.stif.scripteditor.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.text.rules.IRule;


import com.nokia.s60tools.stif.scripteditor.editors.ScriptEditor.EditorMode;
import com.nokia.s60tools.stif.scripteditor.editors.combiner.CombinerWordsProvider;
import com.nokia.s60tools.stif.scripteditor.editors.scripter.ScripterWordsProvider;
import com.nokia.s60tools.stif.scripteditor.utils.KeywordDetector;

/**
 * Config source scaner. Defines syntax highlighting rules.
 *
 */
public class ScriptScanner extends RuleBasedScanner {

    /**
     * Creates config source scaner
     */
	public ScriptScanner(EditorMode mode){
		sectionWordRule = new WordRule(new SectionDetector());
		keywordRule = new WordRule(new KeywordDetector());
		
		Color COMMENT_COLOR = Display.getCurrent().getSystemColor( SWT.COLOR_DARK_GRAY );
		Token comment = new Token(new TextAttribute( COMMENT_COLOR ));
		hashCommentRule = new EndOfLineRule("#", comment);
		slashCommentRule = new EndOfLineRule("//", comment);
		
		String[] sectionWords = null;
		String[] keywords = null;
		if(mode == EditorMode.scripterMode){
			sectionWords = ScripterWordsProvider.provideSectionWords();
			keywords = ScripterWordsProvider.provideKeywords();
		}
		if(mode == EditorMode.combinerMode){
			sectionWords = CombinerWordsProvider.provideSectionWords();
			keywords = CombinerWordsProvider.provideKeywords();
		}
		for(int i = 0; i < sectionWords.length; i++){
			sectionWordRule.addWord(sectionWords[i], sectionWordToken);
		}
		for(int i = 0; i < keywords.length; i++){
			keywordRule.addWord(keywords[i], keywordToken);
		}
 		setRules(new IRule[] {sectionWordRule, keywordRule, hashCommentRule, slashCommentRule});
	}
	
	 /**
     * Changes set of keywords between TestScripter and TestCombiner
     */
	
	public void changeSetOfKeywords(EditorMode mode){
		sectionWordRule = new WordRule(new SectionDetector());
		keywordRule = new WordRule(new KeywordDetector());
		
		String[] sectionWords = null;
		String[] keywords = null;
		if(mode == EditorMode.scripterMode){
			sectionWords = ScripterWordsProvider.provideSectionWords();
			keywords = ScripterWordsProvider.provideKeywords();
		}
		if(mode == EditorMode.combinerMode){
			sectionWords = CombinerWordsProvider.provideSectionWords();
			keywords = CombinerWordsProvider.provideKeywords();
		}
		
		for(int i = 0; i < sectionWords.length; i++){
			sectionWordRule.addWord(sectionWords[i], sectionWordToken);
		}
		for(int i = 0; i < keywords.length; i++){
			keywordRule.addWord(keywords[i], keywordToken);
		}
		
		setRules(new IRule[] {sectionWordRule, keywordRule, hashCommentRule, slashCommentRule});
	}
	
	
	private Color KEYWORD_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	private Color SECTION_WORD_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
	
	private WordRule sectionWordRule;
	private WordRule keywordRule;
	private EndOfLineRule hashCommentRule;
	private EndOfLineRule slashCommentRule;
	
	private Token sectionWordToken = new Token(new TextAttribute(SECTION_WORD_COLOR, null, SWT.BOLD));
	private Token keywordToken = new Token(new TextAttribute(KEYWORD_COLOR, null, SWT.BOLD));
}
