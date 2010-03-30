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

package com.nokia.testfw.stf.scripteditor.editors.scripter;

import java.util.ArrayList;

import com.nokia.testfw.stf.scripteditor.utils.Parser;
import com.nokia.testfw.stf.scripteditor.utils.SectionTag;

/**
 * Provides set of keywords and session tags for code assistant and syntax
 * checking
 * 
 */
public class ScripterWordsProvider {
	
	/**
	 * Provides set of sesion tags
	 * @return list of keywords
	 */
	public static String[] provideSectionWords(){
		Parser parser = new Parser("/scripter.xml");
		String[] sectionkeywrds = parser.provideSectionTags();
		return sectionkeywrds;
		

	}
	
	
	/**
	 * Provides set of opening sesion tags
	 * @return list of sections tags
	 */
	public static String[] provideSectionOpeningKeywords(){
		Parser parser = new Parser("/scripter.xml");
		ArrayList<SectionTag> arrayOfSections = parser.gerSection();
		String[] sectionkeywrds = new String[arrayOfSections.size()];
		for(int i = 0 ; i < sectionkeywrds.length ; i++ ){
			
			sectionkeywrds[i] = ("[" +arrayOfSections.get(i).getName() +"]");
			
		}
		return sectionkeywrds;
	}
	
	/**
	 * Provides set of keywords
	 * @return list keywords
	 */
	public static String[] provideKeywords(){
		
		
		Parser parser = new Parser("/scripter.xml");
		ArrayList<SectionTag> arrayOfSections = parser.gerSection();
		String[] sectionkeywrds = new String[arrayOfSections.size()];
		ArrayList<String> keywordsList = new ArrayList<String>();
		for(int i = 0 ; i < sectionkeywrds.length ; i++ ){
			
			SectionTag section =arrayOfSections.get(i);
			for(int g = 0 ; g < section.getCommandList().size() ; g++){
				
				String command = section.getCommandList().get(g).getCommandName();
				keywordsList.add(command);
		
			}

		}
		String[] keywordsArray = new String[keywordsList.size()];
		for (int h = 0 ; h < keywordsList.size() ; h++){
			keywordsArray[h] = keywordsList.get(h);			
		}
		
		
		return keywordsArray;
	}
	
	public static final String TEST_TAG = "[Test]";
	public static final String END_TEST_TAG = "[Endtest]";
	public static final String DEFINE_TAG = "[Define]";
	public static final String END_DEFINE_TAG = "[Enddefine]";
	public static final String NEW_INCLUDE_MODULE_TAG = "[New_Include_Module]";
	public static final String END_INCLUDE_MODULE_TAG = "[End_Include_Module]";
	public static final String STIF_SETTINGS_TAG = "[StifSettings]";
	public static final String END_STIF_SETTINGS_TAG = "[EndStifSettings]";
	public static final String END_DATA_TAG = "[Enddata]";
	
	public static final String TITLE_KEYWORD = "title";
	
}
