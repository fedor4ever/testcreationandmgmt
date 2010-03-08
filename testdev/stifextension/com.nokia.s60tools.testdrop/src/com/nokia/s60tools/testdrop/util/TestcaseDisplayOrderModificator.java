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


package com.nokia.s60tools.testdrop.util;

import java.util.ArrayList;

/**
 * Helper class for modifing order of results (default/failed first/passed first)
 * to be displayed in the detailed results view 
 *
 */
public class TestcaseDisplayOrderModificator {
	
	private static final String TEST_RESULT_LABEL = "Test case results:";
	private static final String DIV_TAG_NAME = "<div";
	private static final String END_DIV_TAG_NAME = "</div>";
	private static final String FAILED_CLASS = "class=\"failed\"";
	private static final String PASSED_CLASS = "class=\"passed\"";
	
	private static int firstResultDivIndex = -1;
	private static int lastResultEndDivIndex = -1;
	
	/**
	 * Used to move failed/passed results to the beggining
	 * so that they will be displayed before other results
	 *  
	 * @param input
	 * 			String containing original results
	 * @param failedFirst
	 * 			if true then failed results will be moved to the beginning
	 * 			if false then passed results will be moved to the beginning
	 * @return
	 * 			Modified results
	 */
	public static String moveFailedOrPassedToBeginning(String input, boolean failedFirst) {
		StringBuilder modifiedInput = new StringBuilder();
		try {
			ArrayList<String> divs = extractDivs(input);
			if(divs != null) {
				divs = orderDivs(divs, failedFirst);
				modifiedInput.append(input.substring(0, firstResultDivIndex));
				for(int i = 0; i < divs.size(); i++) {
					modifiedInput.append(divs.get(i));
				}
				modifiedInput.append(input.substring(lastResultEndDivIndex + END_DIV_TAG_NAME.length(), 
						input.length()));
			} else {
				modifiedInput.append(input);
			}
		}
		catch(Exception ex) {
		}
		return modifiedInput.toString();
	}
	
	/**
	 * Returns list of strings containing div tags
	 * 
	 * @param input
	 * 			html input to extract div-s from
	 * @return
	 * 			list of found div-s
	 */
	private static ArrayList<String> extractDivs(String input) {
		int indexOfTestResultsLabel = input.indexOf(TEST_RESULT_LABEL);
		if(indexOfTestResultsLabel < 0) { //this is an abnormal situation-best to return unmodified input
			return null;
		}
		String resultsPartOfInput = input.substring(indexOfTestResultsLabel);
		
		int firstResultEndDivIndex = indexOfTestResultsLabel;
		
		String firstDiv = null;
		do {
			firstResultDivIndex = input.indexOf(DIV_TAG_NAME, firstResultEndDivIndex);
			firstResultEndDivIndex = input.indexOf(END_DIV_TAG_NAME, firstResultDivIndex);
			firstDiv = input.substring(firstResultDivIndex, firstResultEndDivIndex);
		} while(firstDiv.indexOf("&raquo") < 0); 
		
		ArrayList<String> resultList = new ArrayList<String>();
		int indexOfNextDivTag = -1;
		int indexOfNextEndDivTag = -1;
		do {
			indexOfNextDivTag = resultsPartOfInput.indexOf(DIV_TAG_NAME, 
					indexOfNextDivTag + 1);
			indexOfNextEndDivTag = resultsPartOfInput.indexOf(END_DIV_TAG_NAME,
					indexOfNextDivTag);
			if(indexOfNextDivTag > -1 && indexOfNextEndDivTag > -1)
			{
				String divString = resultsPartOfInput.substring(indexOfNextDivTag,
						indexOfNextEndDivTag + END_DIV_TAG_NAME.length());
				if (divString.indexOf("&raquo") < 0) {
					continue;
				}
				resultList.add(divString);
				lastResultEndDivIndex = indexOfNextEndDivTag;
			}
		} while(indexOfNextDivTag > -1);
		
		lastResultEndDivIndex += indexOfTestResultsLabel;
		return resultList;
	}
	
	/**
	 * Method for sorting the list of div-s
	 * 
	 * @param inputList
	 * 			list of unsorted div-s
	 * @param failedFirst
	 * 			if true then failed results will be moved to the beginning of list
	 * @return
	 * 			if false then passed results will be moved to the beginning of list
	 */
	private static ArrayList<String> orderDivs(ArrayList<String> inputList, boolean failedFirst) {
		ArrayList<String> orderedList = new ArrayList<String>();
		ArrayList<String> endElementsList = new ArrayList<String>();
		String classToSearchFor = null;
		if(failedFirst == true){
			classToSearchFor = FAILED_CLASS;
		} else {
			classToSearchFor = PASSED_CLASS;
		}
		for (int i = 0; i < inputList.size(); i++) {
			String inputItem = inputList.get(i);
			if(inputItem.indexOf(classToSearchFor) > -1){
				orderedList.add(inputItem);
			} else {
				endElementsList.add(inputItem);
			}
		}
		orderedList.addAll(endElementsList);
		return orderedList;
	}
}
