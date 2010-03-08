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

class Argument implements Arguments {

	public Argument(String value, String name, String binding, String nvalue) {
		this.argumentType = value;
		this.argumentName = name;
		this.argumentBinding = binding;
		this.argumentNextvalue = nvalue;

	}

	public String getNextValue() {
		return argumentNextvalue;
	}
	
	/**
	 * @return argument type (ex. normal, selective)
	 */
	public String getArgumenType() {
		return argumentType;
	}

	/**
	 * @return expected argument value
	 */
	public String getArgumentName() {
		return argumentName;
	}

	/**
	 * @return expected binding value ("=" or space)
	 */
	public String getArgumentBinding() {
		return argumentBinding;
	}

	/**
	 * @return expected value of complex argument
	 */
	public String getargumentNextvalue() {
		return argumentNextvalue;
	}

	/**
	 * 
	 */
	public ArrayList<String> validate(ArrayList<String> tokenizedLine) {
		boolean lineCorrect = false;
		boolean firstArgument = false;
		boolean secondArgument = false;
		boolean thirdArgument = false;
		if (tokenizedLine.size() > 0) {

			if (this.argumentBinding.equals("none")) {

				if (this.argumentType.equals("digit")) {

					String patternString = "^[0-9+-]*$";

					lineCorrect = validateArgment(patternString, tokenizedLine
							.get(0));

				} else if (this.argumentType.equals("literal")) {

					String patternString = "^[0-9a-zA-Z\\\\/:=\\s._()-,]*$";

					lineCorrect = validateArgment(patternString, tokenizedLine
							.get(0));

				} else if (this.argumentType.equals("name")) {

					if (this.argumentName
							.equalsIgnoreCase(tokenizedLine.get(0))) {
						lineCorrect = true;
					}
					
					
				}else if (this.argumentType.equals("words")) {
					tokenizedLine.clear();
					
				}else if (this.argumentType.equals("object")) {
					for (int i = 0 ; i < Parser.createdObjectsList.size() ; i++){
					if(Parser.createdObjectsList.get(i).equalsIgnoreCase(tokenizedLine.get(0))){
						lineCorrect = true;
					}
					}
				}else if (this.argumentType.equals("objectdelete")) {
					for (int i = 0 ; i < Parser.createdObjectsList.size() ; i++){
						if(Parser.createdObjectsList.get(i).equalsIgnoreCase(tokenizedLine.get(0))){
							lineCorrect = true;
							Parser.createdObjectsList.remove(i);
						}
						}
				}else if (this.argumentType.equals("quoted")) {
					if(tokenizedLine.get(0).startsWith("\"") && tokenizedLine.get(0).endsWith("\"")){
						lineCorrect = true;
					}
				}

				if (lineCorrect && tokenizedLine.size()>0) {
					tokenizedLine.remove(0);

				}

			} else if (this.argumentBinding.equals("=")) {
				if (tokenizedLine.size() > 2) {

					if (this.argumentType.equals("digit")) {

						String patternString = "^[0-9+-]*$";

						firstArgument = validateArgment(patternString,
								tokenizedLine.get(0));

					} else if (this.argumentType.equals("literal")) {

						String patternString = "^[0-9a-zA-Z\\\\/:._]*$";

						firstArgument = validateArgment(patternString,
								tokenizedLine.get(0));

					} else if (this.argumentType.equals("name")) {

						if (this.argumentName.equalsIgnoreCase(tokenizedLine
								.get(0))) {
							firstArgument = true;
						}
					}
					if (tokenizedLine.get(1).equals("=")) {
						secondArgument = true;

					}
					if (this.argumentNextvalue.equals("digit")) {

						String patternString = "^[0-9+-]*$";

						thirdArgument = validateArgment(patternString,
								tokenizedLine.get(2));

					} else if (this.argumentNextvalue.equals("literal")) {

						String patternString = "^[0-9a-zA-Z\\\\/:._]*$";

						thirdArgument = validateArgment(patternString,
								tokenizedLine.get(2));

					} else if (this.argumentNextvalue.equals("name")) {

						if (this.argumentName.equalsIgnoreCase(tokenizedLine
								.get(2))) {
							thirdArgument = true;
						}
					}
					else if (this.argumentNextvalue.equals("words")) {
						tokenizedLine.clear();
					
					}
					if (tokenizedLine.size()>0 && firstArgument && secondArgument && thirdArgument) {

						tokenizedLine.remove(0);
						tokenizedLine.remove(0);
						tokenizedLine.remove(0);
					}

				} 
			}else if (this.argumentBinding.equals("space")) {
					if (tokenizedLine.size() > 1) {
							if (this.argumentType.equals("digit")) {

								String patternString = "^[0-9+-]*$";

								firstArgument = validateArgment(patternString,
										tokenizedLine.get(0));

							} else if (this.argumentType.equals("literal")) {

								String patternString = "^[0-9a-zA-Z\\\\/:._]*$";

								firstArgument = validateArgment(patternString,
										tokenizedLine.get(0));

							} else if (this.argumentType.equals("name")) {

								if (this.argumentName.equalsIgnoreCase(tokenizedLine
										.get(0))) {
									firstArgument = true;
								}
							}

							if (this.argumentNextvalue.equals("digit")) {

								String patternString = "^[0-9+-]*$";

								thirdArgument = validateArgment(patternString,
										tokenizedLine.get(1));

							} else if (this.argumentNextvalue.equals("literal")) {

								String patternString = "^[0-9a-zA-Z\\\\/:._]*$";

								thirdArgument = validateArgment(patternString,
										tokenizedLine.get(1));

							} else if (this.argumentNextvalue.equals("name")) {

								if (this.argumentName.equalsIgnoreCase(tokenizedLine
										.get(1))) {
									thirdArgument = true;
								}
							
							}if (tokenizedLine.size()>0 && firstArgument &&  thirdArgument) {

						tokenizedLine.remove(0);
						tokenizedLine.remove(0);
							}
					}
				}
			}
		

		return tokenizedLine;

	}

	/**
	 * Validate single argument
	 * 
	 * @param patternString -
	 *            regular expresion mathing expected argument.
	 * @param token -
	 *            single token from tokenized line
	 * @return true if argument is valid
	 */
	private boolean validateArgment(String patternString, String token) {
		Pattern titlePattern = Pattern
				.compile(patternString, Pattern.MULTILINE);
		Matcher regExMatcher = titlePattern.matcher(token);
		if (regExMatcher.find()) {
			return true;

		}
		return false;
	}

	// define argument type (ex. digit, text or name)
	private String argumentType;

	// uniqe argument name, it is value of XML argument tag
	private String argumentName;

	// define argument binding type (ex. = or space)
	private String argumentBinding;

	// define type of value that argument can take (ex. digit or literal)
	private String argumentNextvalue;

}