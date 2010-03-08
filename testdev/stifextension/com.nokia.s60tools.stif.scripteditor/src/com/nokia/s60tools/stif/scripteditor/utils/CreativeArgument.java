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

package com.nokia.s60tools.stif.scripteditor.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreativeArgument implements Arguments{

	public String getArgumentBinding() {
		
		return "none";
	}

	public CreativeArgument(String value, String name, String binding, String nvalue) {
		this.argumentType = value;
		this.argumentName = name;
		this.argumentBinding = binding;
		this.argumentNextvalue = nvalue;
		
		
	}
	
	public ArrayList<String> validate(ArrayList<String> tokenizedLine) {
		boolean lineCorrect = false;
		if (this.argumentBinding.equals("none")) {

		if (this.argumentType.equals("literal")) {

				String patternString = "^[0-9a-zA-Z\\\\/:=\\s]*$";

				lineCorrect = validateArgment(patternString, tokenizedLine.get(0));

			}	
		}
		if (lineCorrect) {
			createObject(tokenizedLine.get(0));
			tokenizedLine.remove(0);

		}
		return tokenizedLine;
		}
	
	public String getArgumentName() {
		return argumentName;
	}
	
	public String getNextValue() {
		return argumentNextvalue;
	}
	
		private void createObject(String string) {
			Parser.createdObjectsList.add(string);
		}

		private boolean validateArgment(String patternString,		
			String token) {
			Pattern titlePattern = Pattern
					.compile(patternString, Pattern.MULTILINE);
			Matcher regExMatcher = titlePattern.matcher(token);
			if (regExMatcher.find()) {
				return true;

			}
			return false;
		}
		//define argument type (ex. digit, text or name)
		private String argumentType;

		//uniqe argument name, it is value of XML argument tag
		private String argumentName;

		//define argument binding type (ex. = or space)
		private String argumentBinding;

		//define type of value that argument can take (ex. digit or literal)
		private String argumentNextvalue;
		
		//list of objects created using create command
		private static ArrayList<String> createdObjectsList;

		public String getArgumenType() {
			// TODO Auto-generated method stub
			return argumentType;
		}
}
