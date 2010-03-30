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


package com.nokia.testfw.stf.scripteditor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.text.IDocument;

import com.nokia.testfw.stf.scripteditor.editors.scripter.ScripterWordsProvider;

/**
 * Class used to parse content of script in search of sections (test cases).
 * Used to update outline view and folding structure.
 * 
 */
public class SectionFinder {
	
	/**
	 * Search for sections in document
	 * 
	 * @param scripterDoc
	 * 			Document to be parsed in search of test cases
	 * @return
	 * 		Array containing found test cases
	 */
	public static Section[] getSections(IDocument scripterDoc){
		String content = scripterDoc.get();
		ArrayList<TestCase> testCases = new ArrayList<TestCase>();
		
		if(currentSections == null){
			currentSections = new ArrayList<Section>();
		}

		for(int i = 0; i < currentSections.size(); i++){
			currentSections.get(i).setIsDeleted(true);
		}
		
		int currentPos = 0;
		while(currentPos < content.length()){
			int startIndex = content.indexOf(ScripterWordsProvider.TEST_TAG, currentPos);
			int endIndex = content.indexOf(ScripterWordsProvider.END_TEST_TAG, currentPos);
			if(endIndex > 0){
				endIndex += ScripterWordsProvider.END_TEST_TAG.length();
				int positionOfNewLine = content.indexOf("\n", endIndex);
				if(positionOfNewLine > -1){
					endIndex = positionOfNewLine + 1;
				}
			}
			if(startIndex > -1 && endIndex > -1){
				if(startIndex < endIndex) {
					String titleValue = getTitle(content, startIndex, endIndex);
					if(titleValue != null &&  titleValue.length() > 0){
						int titleIndex = content.indexOf(titleValue, startIndex);
						TestCase testCase = new TestCase(titleValue);
						testCase.setStartOffset(titleIndex + titleValue.length());
						testCase.setEndOffset(endIndex);
						
						String testContent = content.substring(startIndex, endIndex);
						for(int i = 0; i < currentSections.size(); i++){
							if(currentSections.get(i).getContent().equals(testContent)){
								testCase.setIsNew(false);
								currentSections.get(i).setIsDeleted(false);
							}
						}
						if(testCase.getIsNew()){
							testCase.setContent(testContent);
							currentSections.add(testCase);
						}
						testCases.add(testCase);
					}
				}
			}
			else {
				break;
			}
			currentPos = endIndex;
		}
		
		int i = 0;
		while(i < currentSections.size()){
			if(currentSections.get(i).getIsDeleted()){
				currentSections.remove(i);
			}
			else{
				i++;
			}
		}
		
		return testCases.toArray(new TestCase[0]);
	}
	
	/**
	 * Returns title of test starting at 'start' and 'ending' at end offset
	 * 
	 * @param content
	 * 			Content of script
	 * @param start
	 * 			Start offset of test case
	 * @param end
	 * 			End offset
	 * @return
	 * 		Name of test case
	 */
	private static String getTitle(String content, int start, int end){
		String retVal = null;
		String sb = removeComments(content.substring(start, end));

		boolean titleFound = false;
		StringTokenizer t = new StringTokenizer(sb.toString());
		while (t.hasMoreTokens()) {
			String token = t.nextToken();
			if (token.equals(ScripterWordsProvider.TITLE_KEYWORD)) {
				titleFound = true;
				break;
			}
		}
		
		// Now return the case name after "title".
		if (titleFound) {
			int titleTagIndex = sb.indexOf(ScripterWordsProvider.TITLE_KEYWORD);
			if(titleTagIndex > -1){
				int titleIndex = titleTagIndex + ScripterWordsProvider.TITLE_KEYWORD.length();
				int indexOfNewLine = sb.indexOf("\n", titleTagIndex);
				retVal = sb.substring(titleIndex, indexOfNewLine).trim();
			}
		}
		return retVal;
	}
	
	/**
	 * Line comments started with "//" and "#" and c style block comments are removed.
	 * Set to public so that it can be reused.
	 * @param content
	 * @return comments stripped string.
	 */
	public static String removeComments(String content) {
		BufferedReader reader = new BufferedReader(new StringReader(content));
		StringBuffer sb = new StringBuffer(); // buffer contains no comments.
		boolean isLineComment = false;
		boolean inComment = false;
		char ch;
		
		/**
		 *  Remove all comments in case of "title" inside comment.
		 *  This won't check any errors.
		 */
		try {
			while ((ch = (char)reader.read()) != (char)-1) {
				char next;
				switch (ch) {
				case '/':
					if (!inComment){
						reader.mark(1);
						next = (char)reader.read();
						if (next == '/' || next == '*') {
							inComment = true;
							if (next == '/') {
								isLineComment = true;
							}
						} else {
							sb.append(ch);
							reader.reset();
						}
					}
					break;
				case '#':
					if (!inComment) {
						inComment = true;
						isLineComment = true;
					}
					break;
				case '\n':
					if (inComment) {
						if (isLineComment) {
							inComment = false;
							isLineComment = false;
						} 
					} else {
						sb.append(ch);
					}
					break;
				case '*':
					if (inComment) {
						reader.mark(1);
						next = (char) reader.read();
						if (next != '/') {
							reader.reset();
						} else {
							inComment = false;
						}
					} else {
						sb.append(ch);
					}
					break;
				default:
					if (!inComment) {
						sb.append(ch);
					}
					break;
				}
			}
		}catch (IOException e) {
			
		}
		return sb.toString();
	}
	private static ArrayList<Section> currentSections;
}
