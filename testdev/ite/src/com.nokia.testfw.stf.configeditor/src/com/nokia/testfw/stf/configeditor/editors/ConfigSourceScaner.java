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


package com.nokia.testfw.stf.configeditor.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.nokia.testfw.stf.configmanager.ConfigUtil;
import com.nokia.testfw.stf.configmanager.SectionElementType;

/**
 * Config source scaner. Defines syntax highlighting rules used by {@link ConfigSourceEditor}.
 *
 */
public class ConfigSourceScaner extends RuleBasedScanner {
	private Color KEYWORD_COLOR = null;
	private Color COMMENT_COLOR = null;
	private Color SECTION_COLOR = null;
	
    /**
     * Creates config source scaner
     */
    public ConfigSourceScaner() {
        WordRule rule = new WordRule(new IWordDetector() {
        	public boolean isWordStart(char c) { 
        		return Character.isJavaIdentifierStart(c); 
        	}
        	public boolean isWordPart(char c) {  
        		if ( c == '=' ) {
        			return true;
        		}
        		return Character.isJavaIdentifierPart(c); 
        	}
        });
        
        SECTION_COLOR = Display.getCurrent().getSystemColor( SWT.COLOR_BLUE );
        KEYWORD_COLOR = Display.getCurrent().getSystemColor( SWT.COLOR_BLACK );
        COMMENT_COLOR = Display.getCurrent().getSystemColor( SWT.COLOR_DARK_GRAY );        
        
        Token section = new Token(new TextAttribute( SECTION_COLOR, null, SWT.BOLD));
        Token keyword = new Token(new TextAttribute( KEYWORD_COLOR, null, SWT.BOLD));
        Token comment = new Token(new TextAttribute( COMMENT_COLOR ));

        //add tokens for each reserved word
        SectionElementType[] engineDefaultsElements = ConfigUtil.getAllowedEngineDefaultsSectionElements();
        for ( int i = 0; i < engineDefaultsElements.length; i++) {
        	rule.addWord( ConfigUtil.getEngineDefaultsSectionElementTag(engineDefaultsElements[ i ]), 
            		keyword );
        }

        SectionElementType[] loggerDefaultsElements = ConfigUtil.getAllowedLoggerDefaultsSectionElements();
        for ( int i = 0; i < loggerDefaultsElements.length; i++) {
        	rule.addWord( ConfigUtil.getLoggerDefaultsSectionElementTag(loggerDefaultsElements[ i ]), 
        			keyword );
        }

        SectionElementType[] moduleElements = ConfigUtil.getAllowedModuleSectionElements();
        for ( int i = 0; i < moduleElements.length; i++) {
        	rule.addWord( ConfigUtil.getModuleSectionElementTag(moduleElements[ i ]), keyword );
        }
        
        setRules(new IRule[] {
           rule,
           new SingleLineRule("[", "]", section ),
           new EndOfLineRule("#", comment ),           
           new EndOfLineRule("\\\\", comment ),           
           new WhitespaceRule(new IWhitespaceDetector() {
              public boolean isWhitespace(char c) {
                 return Character.isWhitespace(c);
              }
           }),
        });
     }
}
