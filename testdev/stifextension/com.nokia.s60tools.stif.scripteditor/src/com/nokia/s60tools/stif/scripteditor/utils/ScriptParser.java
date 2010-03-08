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

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import com.nokia.s60tools.stif.scripteditor.utils.ParseProblem.ProblemType;


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
	public void checkCombinerScript(String scriptContent, IEditorInput input) {

		Parser parser = new Parser("/combiner.xml");
		checkScript(scriptContent, input, parser);
	
	}
	
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
		int sectionOpened = -1;
		int sectionClosed = -1;
		int ncs = 0;
		int checksum = 0;
		boolean defineSectionFound = false;

		try {
			while (true) {
				line = scriptReader.readLine();
				lineNumber++;

				if (line == null) {
					break;
				}
				if(line.startsWith("//") || line.startsWith("#")){
					continue;
				}
				// removes comments
				String[] commentsRem = line.split("//");
				if(commentsRem.length > 0){
				line = commentsRem[0];
				}
				if (!line.startsWith("[")) {
					ArrayList<String> tokenizedLine = lineTokenize(line);
					
					checkCreatedObjects(tokenizedLine);
					
					String command = null;
					if (tokenizedLine.size() > 0) {
						command = tokenizedLine.get(0);
						
					}
					tokenizedLine = resolveDefine(tokenizedLine);
					
					String lineCheck = checkCommand(commands, tokenizedLine,
							parser);

					if (defineSectionFound) {
						if (tokenizedLine.size() == 0) {
							continue;
						}
						
						// check if statment in define section have 2 elements
						// (ex. "EVENT TestScripterEvent")
						if (tokenizedLine.size() != 2) {
							problemsList.add(new ParseProblem(
									ProblemType.ERROR, lineNumber,
									errorSyntaxError));
						} else {
							// if statment contains keyword it can't be used in
							// define section
							for (int i = 0; i < commands.size(); i++) {
								if (command.equals(commands.get(i))) {
									problemsList.add(new ParseProblem(
											ProblemType.ERROR, lineNumber,
											errorSyntaxError));
									
								}
							}
							makeDefineObjects(tokenizedLine);

						}

					}
					
					// if line do not contains valid command with valid
					// parameters error note is displayed
					else if (lineCheck != null) {
						
						problemsList.add(new ParseProblem(ProblemType.ERROR,
								lineNumber, lineCheck));

					} 
				}else {
						// checks if all sections tags are correctly parryed
						for (int i = 0; i < sectionTags.length; i = i + 2) {

							if (line.equals(sectionTags[i])) {
								ncs = lineNumber;
								sectionOpened = i;
								sectionClosed = -1;
								checksum++;
								
								//checks if test command is first command after [Test] tag 
								//and makes sure that title is used only once in test
								if (sectionTags[i].equals("[Test]")) {
									String tc = scriptReader.readLine();
									lineNumber++;
		
									ArrayList<String> tLine = lineTokenize(tc);

									ArrayList<String> c = new ArrayList<String>();
									c.add("title");
									String lineCheck = checkCommand(c, tLine,
											parser);
									if (lineCheck != null) {
										
										problemsList.add(new ParseProblem(ProblemType.ERROR,
												lineNumber, errorTitleMustBeTheFirstWord));
									}
								}
								if (sectionTags[i].equals("[Define]") || sectionTags[i].equals("[New_Include_Module]")) {
									defineSectionFound = true;
								}

							}
							if (i < sectionTags.length - 1
									&& line.equals(sectionTags[i + 1])) {

								sectionClosed = i;
								checksum++;
								if (sectionTags[i + 1].equals("[Enddefine]") || sectionTags[i + 1].equals("[End_Include_Module]")) {
									defineSectionFound = false;
								}
						
								if (sectionTags[i + 1].equals("[Endtest]")) {
//									createdObjectsList.clear();
//									objectsList.clear();
								}
							}

						}

					}

			
			}
			// check if all sections are ended
			if ((sectionOpened - sectionClosed) != 0) {
				problemsList.add(new ParseProblem(ProblemType.ERROR, ncs,
						errorNotAllSectionsClosed));
			}
//			// checks if number of sections tags is odd
//			if (checksum % 2 == 0) {
//				problemsList.add(new ParseProblem(ProblemType.ERROR,
//						lineNumber-1, errorNotAllSectionsClosed));
//			}
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
	
	public static CombinerSection getCurrentCombinerSection(String document,
			int offset) {
		boolean currentSectionFound = false;
		currentSearchOffset = 0;
		CombinerSection currentSection = CombinerSection.NoSection;
		CombinerSection previousSection = CombinerSection.NoSection;

		while (!currentSectionFound) {
			currentSection = findNextCombinerSection(document);
			if (currentSearchOffset > offset || currentSection == null)
				currentSectionFound = true;
			else {
				previousSection = currentSection;
			}
		}
		return previousSection;
	}
	
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
	private static CombinerSection findNextCombinerSection(String document) {
		int currentSearchOffsetAtStart = currentSearchOffset;
		CombinerSection section = CombinerSection.NoSection;
		int indexOfOpeningBracket = document.indexOf("[", currentSearchOffset);
		int indexOfClosingBracket = -1;
		if (indexOfOpeningBracket > -1) {
			indexOfClosingBracket = document
					.indexOf("]", indexOfOpeningBracket);
			if (indexOfClosingBracket > -1) {
				String sectionWord = document.substring(
						indexOfOpeningBracket + 1, indexOfClosingBracket);
				if (sectionWord.equals("Test")) {
					section = CombinerSection.TestSection;
				} else if (sectionWord.equals("Define")) {
					section = CombinerSection.DefineSection;
				} else if (sectionWord.equals("New_Include_Module")) {
					section = CombinerSection.NewIncludeModuleSection;
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
	 * Enum That decribes section of testcombiner script  
	 */
	public enum ScripterSection {
		NoSection, TestSection, DefineSection, NewIncludeModuleSection, StifSettingsSection
	}

	/**
	 * Combiner commands
	 */
	public enum ScripterCommand {
		TitleCommand, TimeoutCommand, PriorityCommand, CreateCommand, DeleteCommand, RequestCommand, ReleaseCommand, WaitCommand, PrintCommand, CreateKernelCommand, AllowNextResultCommand, AllowErrorCodesCommand, WaitTestClassCommand, PauseCommand, LoopCommand, EndloopCommand, OomIgnoreFailureCommand, OomHeapFailNextCommand, OomHeapSetFailCommand, OomHeapToNormalCommand, TestInterferenceCommand, TestMeasurementCommand, SetCommand, UnsetCommand, VarCommand, CallsubCommand
	}

	public enum CombinerSection {
		NoSection, TestSection, DefineSection, NewIncludeModuleSection,
	}

	public enum CombinerCommand {
		CancelIfErrorCommand, PauseCombinerCommand, RunCommand, CancelCommand, PauseCommand, ResumeCommand, CompleteCommand, TestMeasurementCommand, LoopCommand, EndloopCommand
	}

	private List<IMarker> markersList;

	private List<ParseProblem> problemsList;

	private int lineNumber = -1;

	ArrayList<String> objectNames = null;

	ArrayList<String> definedNames = null;

	ArrayList<String> testIds = null;

	private static int currentSearchOffset = 0;

	private String errorTestKeywordIsNotTheLastWord = "[Test] keyword can be the only word in the line";

	private String errorTitleMustBeTheFirstWord = "test case content must start with the \"title\" keyword and title following";

	private String errorNotAllSectionsClosed = "Not all sections closed";

	private String errorSyntaxError = "syntax error";



	private IResource resource;
	
	
	private String defineAlias;
	
	private String defineValue;
	


	
	
	private ArrayList<String[]> createdObjectsList;
}
