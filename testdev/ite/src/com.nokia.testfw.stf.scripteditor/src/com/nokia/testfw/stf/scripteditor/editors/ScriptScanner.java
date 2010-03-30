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


package com.nokia.testfw.stf.scripteditor.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.text.rules.IRule;


import com.nokia.testfw.stf.scripteditor.editors.scripter.ScripterWordsProvider;
import com.nokia.testfw.stf.scripteditor.utils.KeywordDetector;

/**
 * Config source scaner. Defines syntax highlighting rules.
 *
 */
public class ScriptScanner extends RuleBasedScanner {

    /**
     * Creates config source scaner
     */
	public ScriptScanner(/*EditorMode mode*/){
		
		sectionWordRule = new WordRule(new SectionDetector());
		keywordRule = new WordRule(new KeywordDetector(), Token.WHITESPACE);
		
		wsRule = new WhitespaceRule(new IWhitespaceDetector() {
            public boolean isWhitespace(char c) {
               return Character.isWhitespace(c);
            }
         });
		
		Color COMMENT_COLOR = Display.getCurrent().getSystemColor( SWT.COLOR_DARK_GRAY );
		Token comment = new Token(new TextAttribute( COMMENT_COLOR ));
		hashCommentRule = new EndOfLineRule("#", comment);
		slashCommentRule = new EndOfLineRule("//", comment);
		blkCommentRule = new MultiLineRule("/*", "*/", comment);

		String[] sectionWords = null;
		String[] keywords = null;

		sectionWords = ScripterWordsProvider.provideSectionWords();
		keywords = ScripterWordsProvider.provideKeywords();
		for (int i = 0; i < sectionWords.length; i++) {
			sectionWordRule.addWord(sectionWords[i], sectionWordToken);
		}
		for(int i = 0; i < keywords.length; i++){
			keywordRule.addWord(keywords[i], keywordToken);
		}
 		setRules(new IRule[] {sectionWordRule, keywordRule, hashCommentRule, slashCommentRule, wsRule, blkCommentRule});
	}
	
	 /**
     * Changes set of keywords between TestScripter and TestCombiner
     */
	
	public void changeSetOfKeywords(ArrayList<String> subSectionContent/*EditorMode mode*/){
		sectionWordRule = new WordRule(new SectionDetector());
		keywordRule = new WordRule(new KeywordDetector(), Token.WHITESPACE);
		
		String[] sectionWords = null;
		String[] keywords = null;

		sectionWords = ScripterWordsProvider.provideSectionWords();
		keywords = ScripterWordsProvider.provideKeywords();
		
		for(int i = 0; i < sectionWords.length; i++){
			sectionWordRule.addWord(sectionWords[i], sectionWordToken);
		}

		if(subSectionContent!=null && subSectionContent.size()>0){
			if(subSectionContent.get(0).equals("[Sub")){
				sectionWordRule.addWord(subSectionContent.get(0), sectionWordToken);
			}
			if(subSectionContent.size()>1){
				for(int i=1;i<subSectionContent.size();i++){
					if(subSectionContent.get(i).matches("[a-z]+\\]")){
						sectionWordRule.addWord(subSectionContent.get(i), sectionWordToken);
					}
				}
			}
		}
		
		for(int i = 0; i < keywords.length; i++){
			keywordRule.addWord(keywords[i], keywordToken);
		}
		
		setRules(new IRule[] {sectionWordRule, keywordRule, hashCommentRule, slashCommentRule, wsRule, blkCommentRule});
	}
	
	private Color KEYWORD_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	private Color SECTION_WORD_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
	
	private WordRule sectionWordRule;
	private WordRule keywordRule;
	private WhitespaceRule wsRule;
	private EndOfLineRule hashCommentRule;
	private EndOfLineRule slashCommentRule;
	private MultiLineRule blkCommentRule;
	
	private Token sectionWordToken = new Token(new TextAttribute(SECTION_WORD_COLOR, null, SWT.BOLD));
	private Token keywordToken = new Token(new TextAttribute(KEYWORD_COLOR, null, SWT.BOLD));
}
