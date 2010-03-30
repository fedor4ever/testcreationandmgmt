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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

import com.nokia.testfw.stf.scripteditor.utils.ParseProblem.ProblemType;


import java.io.IOException;

/**
 * Parser of script. Used to check syntax and find code sections in cfg files  
 *
 */
public class ScriptParser {
	
	/**
	 * Default constructor
	 *
	 */
	public ScriptParser() {
		problemsList = new ArrayList<ParseProblem>();
		markersList = new ArrayList<IMarker>();
		createdObjectsList = new ArrayList<String[]>();
		
	}

	/**
	 * Removes all problem markers
	 *
	 */
	private void clearProblemMarkers() {
		for (int a = 0; a < markersList.size(); a++) {
			try {
				markersList.get(a).delete();
				markersList.remove(a);
				
			} catch (CoreException ex) {
			}
		}
	}
	public void checkScripterScript(String scriptContent, IEditorInput input) {

		Parser parser = new Parser("/scripter.xml");
		checkScript(scriptContent, input, parser);
	}
//	public void checkCombinerScript(String scriptContent, IEditorInput input) {
//
//		Parser parser = new Parser("/combiner.xml");
//		checkScript(scriptContent, input, parser);
//	
//	}
	
	/**
	 * Processes the scriptContent in search of syntax errors (testscripter).
	 * The found errors are stored to problemsList.
	 * 
	 * @param scriptContent
	 * 			Content of script as a string
	 * @param input
	 * 			IEditorInput object needed to display found errors 
	 */
	public void checkScript(String scriptContent, IEditorInput input, Parser parser) {
		Parser.createdMacrosList.clear();
		Parser.createdObjectsList.clear();
		BufferedReader scriptReader = new BufferedReader(new StringReader(
				scriptContent));
		String line = null;
		lineNumber = -1;
		createdObjectsList.clear();
		ArrayList<String> commands = parser.provideCommands();
		String[] sectionTags = parser.provideSectionTags();
		problemsList = new ArrayList<ParseProblem>();
		boolean defineSectionFound = false;
		// Variables help to remove block comments.
		boolean inBlkComment = false;
		String token = null;
		String token1 = null;
		StringTokenizer t = null;
		int blkCommentBeginLineNo = -1;
		SectionContextStack sectionStack = new SectionContextStack();
		SectionContext curSection = null;
		subSectionContent = new ArrayList<String>();
		
		try {
			while (true) {
				line = scriptReader.readLine();
				lineNumber++;

				if (line == null) {
					break;
				}
				line = line.trim();
				
				// Remove block comment enclosed in "/*" and "*/".
				// "/*" must be at the begin of the line and "*/ must be at the
				// end of the line.
				inBlkComment = false;
				t = new StringTokenizer(line);
				while (t.hasMoreTokens())
				{
					token = t.nextToken();
					if (token.indexOf("/*") >= 0){
						if (blkCommentBeginLineNo == -1) {
							blkCommentBeginLineNo = lineNumber;
						} else {
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
									errorEmbeddedSlashStar));
						}
					}else if (token.indexOf("*/") >= 0) {
						inBlkComment = true; // needed to skip the line containing "*/"
						if (blkCommentBeginLineNo != -1) {
							blkCommentBeginLineNo = -1;
						} else {
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
									errorTailStarSlash));
						}
					}
				}
				
				if (line.indexOf("/*") > 0) {
					problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
							errorSlashStarMustBeFirstWord));
				}
				
				if (line.indexOf("*/") > 0 && !line.substring(line.length() - 2).equals("*/")) {
					problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
							errorStarSlashMustBeLastWord));
				}

				if (blkCommentBeginLineNo != -1 || inBlkComment || line.equals("") || line.startsWith("//") || line.startsWith("#")){
					if (sectionStack.lastSectionContext() != null) {
						sectionStack.lastSectionContext().incTitleAtLine();
					}
					continue;
				}
				
				// removes line comments
				String[] commentsRem = line.split("//");
				if(commentsRem.length > 0){
				line = commentsRem[0].trim();
				}
				
				if (line.startsWith("[")) {
					// Section tag found
					t = new StringTokenizer(line);
					while (t.hasMoreTokens()) // Tags can be located at one line.
					{
						if(t.countTokens()>1){
							token = t.nextToken();
							token1 = t.nextToken();
							if(token1!=null){
								if(token.equals("[Sub") && !subSectionContent.contains("[Sub")){
									subSectionContent.add(token);
								}
								if(token1.matches("([a-z]+)\\]")){
									subSectionContent.add(token1);
								}
							}
						}
						else
							token = t.nextToken();
						
						if(token.equals("[Sub]")){
							// Non-existing tag
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
									errorSubTagMissingTitle + token + "\""));
						}
						
						if(token.equals("[Sub")){
							token = "[Sub]";
						}

						int i = findSectionTagIndex(sectionTags, token);
						if (i < sectionTags.length) {
							if (i % 2 == 0) { // Section opened.
								if (sectionStack.size() > 0) {
									problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
											errorEmbeddedSection));
									curSection = sectionStack.lastSectionContext();
								}
								sectionStack.addContext(new SectionContext(i, lineNumber, sectionTags[i].equals("[Test]")));

								// Mandatory "title" keyword right after [Test]
								// tag.
								if (sectionStack.lastSectionContext().isTestSection) {
									sectionStack.lastSectionContext().setTitleAtLine(lineNumber + 1);
									sectionStack.lastSectionContext().setTitleExpected(true);
								}
							} else { // Section closed
								curSection = sectionStack.lastSectionContext();
								if (sectionTags[i].equals("[Endtest]") && curSection != null
										&& curSection.isTestSection && curSection.isTitleExpected()) {
									problemsList.add(new ParseProblem(ProblemType.ERROR, curSection.getTitleAtLine(),
											errorTitleMustBeTheFirstWord));
								}
								if (!sectionStack.removeContext(i)) { 
									// Cannot find nearest paired open section tag.
									problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
													errorMissingTag + sectionTags[i - 1] + doubleQuotation));
								}
							}
						} else {
							// Non-existing tag
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
									errorNonExistingTag + token + doubleQuotation));
						}
					}
				} else { // Commands.
					ArrayList<String> tokenizedLine = lineTokenize(line);
					// "title" must be the first word.
					ArrayList<String> tLine = lineTokenize(line);
					curSection = sectionStack.lastSectionContext();
					if (tLine.size() > 0 && tLine.get(0).equals("title")) {
						if (curSection != null){
							if (!curSection.isTitleExpected()) { // Multiple title lines detected.
								problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
										errorUnexpectedTitle));
							} else	if (curSection.getTitleAtLine()!= lineNumber) { // title line at wrong line.
								problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
										errorTitleMustBeTheFirstWord));
							} else {
								curSection.setTitleExpected(false);
								continue;
							}
						}
					}

					checkCreatedObjects(tokenizedLine);

					String command = null;
					if (tokenizedLine.size() > 0) {
						command = tokenizedLine.get(0);
					}
					tokenizedLine = resolveDefine(tokenizedLine);
					String lineCheck = checkCommand(commands, tokenizedLine, parser);
					int defIndex = findSectionTagIndex(sectionTags, "[Define]");
					int inclIndex = findSectionTagIndex(sectionTags, "[New_Include_Module]");
					defineSectionFound = sectionStack .isCurrentSection(defIndex)
							|| sectionStack.isCurrentSection(inclIndex);

					if (defineSectionFound) {
						// Inside a define section
						if (tokenizedLine.size() == 0) {
							continue;
						}

						// check if statement in define section have 2
						// elements (e.g. "EVENT TestScripterEvent")
						if (tokenizedLine.size() != 2) {
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
									errorSyntaxError));
						} else {
							// if statement contains keyword it can't be
							// used in define section.
							for (int i = 0; i < commands.size(); i++) {
								if (command.equals(commands.get(i))) {
									problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber,
											errorSyntaxError));
								}
							}
							makeDefineObjects(tokenizedLine);
						}
					}
					// if line do not contains valid command with valid
					// parameters error note is displayed
					if (sectionStack.size() > 0) {
						// Command inside section
						if (command != null && command.equals("INCLUDE")) {
							// INCLUDE not expected.
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber, errorNonSectionCmd + command + doubleQuotation));
						}
					} else {
						// Command outside section
						if (command != null && !command.equals("INCLUDE")) {
							// free text detected.
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber, errorFreeText));
						} else if (lineCheck != null) {
							// INCLUDE syntax error
							problemsList.add(new ParseProblem(ProblemType.ERROR, lineNumber, lineCheck));
						}
					}
				}
			}
			
			if (blkCommentBeginLineNo != -1){
				problemsList.add(new ParseProblem(ProblemType.ERROR, blkCommentBeginLineNo,
						errorMissingStarSlash));
			}
			// check if all sections are closed.
			SectionContext[] arr = new SectionContext[sectionStack.size()];
			sectionStack.toArray(arr);
			for (SectionContext tmp : arr) {
				problemsList.add(new ParseProblem(ProblemType.ERROR, tmp.lineNumber, errorMissingTag + sectionTags[tmp.sectionIndex + 1] + "\""));
				if (tmp.isTestSection && tmp.isTitleExpected()) {
					problemsList.add(new ParseProblem(ProblemType.ERROR, tmp.getTitleAtLine(),
					errorTitleMustBeTheFirstWord));
				}
			}
		} catch (IOException ex) {

		}
		addErrorMarkers(input);
	}
	
	/**
	 * checks if line starts with created object, if so no error would be displayed
	 * 
	 * @param tokenizedLine
	 * @return
	 */
	private ArrayList<String> checkCreatedObjects(ArrayList<String> tokenizedLine) {
		
		for(int i = 0 ; i < Parser.createdObjectsList.size() ; i++ ){
			if(tokenizedLine.size()>1 && tokenizedLine.get(0).equals(Parser.createdObjectsList.get(i))){
				if(tokenizedLine.size()>1){
				tokenizedLine.clear();
				break;
				}	
			}
		}
		return tokenizedLine;
	}

	/**
	 * replace defined statments with their value.
	 * 
	 * @param tokenizedLine
	 * @param parser 
	 * @return tokenizedLine
	 */
	private ArrayList<String> resolveDefine(ArrayList<String> tokenizedLine) {
		if (createdObjectsList.size()>0 && tokenizedLine != null){
		for (int i = 0; i < tokenizedLine.size(); i++) {
			for (int j = 0; j < createdObjectsList.size(); j++) {
				
			
			if(tokenizedLine.get(i).equals(createdObjectsList.get(j)[0])){
				tokenizedLine.remove(i);
				tokenizedLine.add(i, createdObjectsList.get(j)[1]);
				
				
				
			}
			}
			
		}
		}
		
		return tokenizedLine;
	}

	/**
	 * Add statment from define section to createdObjectsList, 
	 * list is used to replace defined words with correct aliases
	 * 
	 * @param tokenizedLine
	 */
	private void makeDefineObjects(ArrayList<String> tokenizedLine) {
		
		defineAlias = tokenizedLine.get(0);
		defineValue = tokenizedLine.get(1);
		String[] st = new String[2];
		st[0]=defineAlias;
		st[1]=defineValue;
		createdObjectsList.add(st);
		Parser.createdMacrosList.add(defineAlias);
		
	}

	/**
	 * Check if first token form tokenizedLine is a valid command and invokes
	 * methods checking parameters
	 * 
	 * @param commands -
	 *            list of commands
	 * @param tokenizedLine -
	 *            line form editor input
	 * @param parser -
	 *            parser object
	 * @return error note or null
	 */
	private String checkCommand(ArrayList<String> commands,
			ArrayList<String> tokenizedLine, Parser parser) {
		for (int i = 0; i < commands.size(); i++) {

			if (tokenizedLine.size() == 0) {
				return null;
			}
			if (tokenizedLine.size() > 0
					&& tokenizedLine.get(0).equals(commands.get(i))) {
				tokenizedLine.remove(0);

				String ret = parser.checkArguments(tokenizedLine, commands
						.get(i));
				if (ret != null) {
					return ret;

				} else {
					return null;
				}
			}
		}
		return errorSyntaxError;
	}

	/**
	 * Separate passed string in to tokens
	 * 
	 * @param string
	 * @return ArrayList list of tokens
	 */

	static ArrayList<String> lineTokenize(String string) {

		char[] pars = string.toCharArray();
		ArrayList<String> ret = new ArrayList<String>();
		String set;
		int beginpos = 0;
		int endpos = 1;
		boolean qclosed;

		for (int i = 0; i < pars.length; i++) {
			
			if (pars[i] == '"') {
				// set flag checking if guot is closed
				qclosed = false;
				int a = i;

				if (i > 0 && pars[i - 1] == '\\') {
					pars[i - 1] = ' ';

					// begin position = i;
					continue;
				}

				// when char before '"' is different than space.
				if (i > 0 && !Character.isWhitespace(pars[i - 1])
						&& (pars[i - 1] != '=')) {
					set = new String(pars, beginpos, endpos - beginpos);
					ret.add(set);

				}
				beginpos = i; //+ 1;

				// searching for next '"' char
				for (int j = i + 1; j < pars.length; j++) {

					if (pars[j] == '"') {
						qclosed = true;
						if (j > 0 && pars[j - 1] == '\\') {

							pars[j - 1] = ' ';
							continue;
						}
						// set end position to last element
						endpos = j+1; //j;

						set = new String(pars, beginpos, endpos - beginpos);
						ret.add(set);
						beginpos = (j + 1);
						i = j;
						break;
					}
				}
				if (!qclosed && pars[a] == '\"') {

					set = new String(pars, a, 1);
					beginpos = a++;

					ret.add(set);

				}
			}
			endpos = i;
			// checking for space
			if (Character.isWhitespace(pars[i])) {

				// check if previous char is a space,
				// if yes skip it
				if (i > 0 && Character.isWhitespace(pars[i - 1])
						&& i < pars.length - 1) {
					beginpos++;

				}
				// create new token from begin position to current position
				if (i > 0 && !Character.isWhitespace(pars[i - 1])
						&& (pars[i - 1] != '"')) {
					set = new String(pars, beginpos, endpos - beginpos);
					// begin position is a next char after current position
					beginpos = (i + 1);

					ret.add(set.trim());
				}
			}
			// checking for '='
			if (pars[i] == '=') {

				// when char before "=" is different than space.
				if (i > 0 && !Character.isWhitespace(pars[i - 1])
						&& (pars[i - 1] != '"')) {
					set = new String(pars, beginpos, endpos - beginpos);

					ret.add(set);
					// begin position is set on = char

				}// when char after "=" is different than space.
				beginpos = i;
				if (i < pars.length - 1 && !Character.isWhitespace(pars[i + 1])) {
					set = new String(pars, endpos, 1);
					ret.add(set);
					beginpos = (i + 1);

				}
			}


		}
		// last tag have to be tokenized sepretly becouse last har may not be
		// space.
		if (endpos == pars.length - 1 && pars[(pars.length - 1)] != '\"') {

			set = new String(pars, beginpos, pars.length - beginpos);
			if (set.length() != 0 && set.toCharArray()[0] != ' ') {
				ret.add(set);
			}
		}

		return ret;

	}

	/**
	 * Makes IEditorInput display the found errors
	 * 
	 * @param input
	 * 			IEditorInput object that IMarker (error markers) 
	 * 			of found errors will be added to
	 */
	private void addErrorMarkers(IEditorInput input) {
		try {
			
			clearProblemMarkers();
			if (resource == null) {
				resource = (IResource) input.getAdapter(IResource.class);
				if (resource == null) {
					resource = ResourcesPlugin.getWorkspace().getRoot();
				}
			}
			resource.deleteMarkers(null, true, 0);

			for (int i = 0; i < problemsList.size(); i++) {
				IMarker marker = resource.createMarker(IMarker.PROBLEM);
				marker.setAttribute(IMarker.LINE_NUMBER,
						problemsList.get(i).lineNumber + 1);
				marker.setAttribute(IMarker.MESSAGE,
						problemsList.get(i).description);

				if (problemsList.get(i).type == ParseProblem.ProblemType.ERROR) {
					marker
							.setAttribute(IMarker.PRIORITY,
									IMarker.PRIORITY_HIGH);
					marker.setAttribute(IMarker.SEVERITY,
							IMarker.SEVERITY_ERROR);
				} else if (problemsList.get(i).type == ParseProblem.ProblemType.WARNING) {
					marker
							.setAttribute(IMarker.PRIORITY,
									IMarker.PRIORITY_HIGH);
					marker.setAttribute(IMarker.SEVERITY,
							IMarker.SEVERITY_WARNING);
				}
				markersList.add(marker);
			}
		} catch (CoreException ex) {

		}
	}

	public static ScripterSection getCurrentScripterSection(String document,
			int offset) {
		boolean currentSectionFound = false;
		currentSearchOffset = 0;
		ScripterSection currentSection = ScripterSection.NoSection;
		ScripterSection previousSection = ScripterSection.NoSection;

		while (!currentSectionFound) {
			currentSection = findNextScripterSection(document);
			if (currentSearchOffset > offset || currentSection == null)
				currentSectionFound = true;
			else {
				previousSection = currentSection;
			}
		}
		return previousSection;
	}
	
	/**
	 * This method is used by testcombiner content assistant to provide
	 * info about keywords valid in current context
	 * 
	 * @param document
	 * 			Script content
	 * @param offset
	 * 			Current offset of cursor in file
	 * @return
	 * 			Object describing if cursor is currently in a section ([Define], [Test], etc.)
	 */
	
//	public static CombinerSection getCurrentCombinerSection(String document,
//			int offset) {
//		boolean currentSectionFound = false;
//		currentSearchOffset = 0;
//		CombinerSection currentSection = CombinerSection.NoSection;
//		CombinerSection previousSection = CombinerSection.NoSection;
//
//		while (!currentSectionFound) {
//			currentSection = findNextCombinerSection(document);
//			if (currentSearchOffset > offset || currentSection == null)
//				currentSectionFound = true;
//			else {
//				previousSection = currentSection;
//			}
//		}
//		return previousSection;
//	}
	
	/**
	 * Utility method used by getCurrentScripterSection method
	 * 
	 * @param document
	 * 			Script content
	 * @return
	 * 			Object describing the section that follows current section (the next 
	 * 			section after the one in which cursor currently remains). 
	 */
	private static ScripterSection findNextScripterSection(String document) {
		int currentSearchOffsetAtStart = currentSearchOffset;
		ScripterSection section = ScripterSection.NoSection;
		int indexOfOpeningBracket = document.indexOf("[", currentSearchOffset);
		int indexOfClosingBracket = -1;
		if (indexOfOpeningBracket > -1) {
			indexOfClosingBracket = document
					.indexOf("]", indexOfOpeningBracket);
			if (indexOfClosingBracket > -1) {
				String sectionWord = document.substring(
						indexOfOpeningBracket + 1, indexOfClosingBracket);
				if (sectionWord.equals("Test")) {
					section = ScripterSection.TestSection;
				} else if (sectionWord.equals("Define")) {
					section = ScripterSection.DefineSection;
				} else if (sectionWord.equals("New_Include_Module")) {
					section = ScripterSection.NewIncludeModuleSection;
				} else if (sectionWord.equals("StifSettings")) {
					section = ScripterSection.StifSettingsSection;
				} else if (sectionWord.equals("Data")) {
					section = ScripterSection.DataSection;
				}  
				currentSearchOffset = indexOfClosingBracket + 1;
			}
		}
		if (currentSearchOffset != currentSearchOffsetAtStart)
			return section;
		else
			return null;
	}

	
	/**
	 * Utility method used by getCurrentCombinerSection method
	 * 
	 * @param document
	 * 			Script content
	 * @return
	 * 			Object describing the section that follows current section (the next 
	 * 			section after the one in which cursor currently remains). 
	 */
//	private static CombinerSection findNextCombinerSection(String document) {
//		int currentSearchOffsetAtStart = currentSearchOffset;
//		CombinerSection section = CombinerSection.NoSection;
//		int indexOfOpeningBracket = document.indexOf("[", currentSearchOffset);
//		int indexOfClosingBracket = -1;
//		if (indexOfOpeningBracket > -1) {
//			indexOfClosingBracket = document
//					.indexOf("]", indexOfOpeningBracket);
//			if (indexOfClosingBracket > -1) {
//				String sectionWord = document.substring(
//						indexOfOpeningBracket + 1, indexOfClosingBracket);
//				if (sectionWord.equals("Test")) {
//					section = CombinerSection.TestSection;
//				} else if (sectionWord.equals("Define")) {
//					section = CombinerSection.DefineSection;
//				} else if (sectionWord.equals("New_Include_Module")) {
//					section = CombinerSection.NewIncludeModuleSection;
//				}
//				currentSearchOffset = indexOfClosingBracket + 1;
//			}
//		}
//		if (currentSearchOffset != currentSearchOffsetAtStart)
//			return section;
//		else
//			return null;
//	}
	
	/**
	 * Enum That describes section of testcombiner script  
	 */
	public enum ScripterSection {
		NoSection, TestSection, DefineSection, NewIncludeModuleSection, StifSettingsSection, DataSection
	}

	/**
	 * Helper to check script and locate errors
	 */
	private int findSectionTagIndex(String[] tags, String tag) {
		int index = 0;
		while (index < tags.length && !tags[index].equals(tag))++index;
		return index;
	}
	
	private class SectionContext{
		public SectionContext(int index, int lineNo, boolean testSection) {
			sectionIndex = index;
			lineNumber = lineNo;
			isTestSection = testSection;
		}
		public void incTitleAtLine() {
			if (isTestSection) {
				++titleAtLine;
			}
		}
		public void setTitleAtLine(int lineNo) {
			if (isTestSection) {
				titleAtLine = lineNo;
				titleExpected = true;
			}
		}
		
		public int getTitleAtLine() {
			return titleAtLine;
		}
		
		public void setTitleExpected(boolean title) {
			if (isTestSection) {
				titleExpected = title;
			}
		}
		
		public boolean isTitleExpected() {
			return titleExpected;
		}
		public int sectionIndex;
		public int lineNumber;
		public boolean isTestSection;
		
		// Members for [Test] section only.
		private int titleAtLine;
		private boolean titleExpected;
	}
	
	private class SectionContextStack{
		public SectionContextStack() {
			contextStack = new Stack<SectionContext>();
		}
		
		public void addContext(SectionContext context) {
			contextStack.push(context);
		}
		
		public boolean removeContext(int sectionIndex) {
			if (!contextStack.empty() && contextStack.lastElement().sectionIndex == --sectionIndex) {
				contextStack.pop();
				return true;
			}
			return false;
		}
		
		public boolean isCurrentSection(int i) {
			return !contextStack.empty() && contextStack.lastElement().sectionIndex == i;
		}
		
		public SectionContext[] toArray(SectionContext[] arr) {
			return contextStack.toArray(arr);
		}
		
		public SectionContext lastSectionContext() {
			if (!contextStack.empty()) {
				return contextStack.lastElement();
			}
			else {
				return null;
			}
		}
		
		public int size() {
			return contextStack.size();
		}
		
		private Stack<SectionContext> contextStack;
	}

//	public enum CombinerSection {
//		NoSection, TestSection, DefineSection, NewIncludeModuleSection,
//	}


	private List<IMarker> markersList;

	private List<ParseProblem> problemsList;

	private int lineNumber = -1;

	ArrayList<String> objectNames = null;

	ArrayList<String> definedNames = null;

	ArrayList<String> testIds = null;

	private static int currentSearchOffset = 0;

	private String errorTitleMustBeTheFirstWord = "test case content must start with the \"title\" keyword and title following";

	private String errorSyntaxError = "syntax error";

	private String errorEmbeddedSlashStar = "Embedded \"/*\" not allowed";
	
	private String errorTailStarSlash = "Tail \"*/\"";
	
	private String errorMissingStarSlash = "Missing \"*/\"";
	
	private String errorMissingTag = "Missing tag: \"";
	
	private String errorNonExistingTag = "Non existing tag: \"";
	
	private String errorSubTagMissingTitle = "Sub Tag missing ttitle: \"";
	
	private String errorUnexpectedTitle = "Unexpected \"title\" keyword";
	
	private String errorFreeText = "Free text not allowed";
	
	private String errorEmbeddedSection = "Embedded section not allowed";
	
	private String errorSlashStarMustBeFirstWord = "/* must be at the begin of the line";
	
	private String errorStarSlashMustBeLastWord = "*/ must be at the end of the line";
	
	private String errorNonSectionCmd = "Non-section command: \"";
	
	private String doubleQuotation = "\"";

	private IResource resource;	
	
	private String defineAlias;
	
	private String defineValue;	
	
	private ArrayList<String[]> createdObjectsList;
	
	public ArrayList<String> subSectionContent;
}