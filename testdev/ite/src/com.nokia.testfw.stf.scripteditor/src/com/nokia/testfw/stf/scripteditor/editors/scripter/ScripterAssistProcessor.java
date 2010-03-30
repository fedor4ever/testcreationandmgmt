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

import org.eclipse.jface.contentassist.IContentAssistSubjectControl;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import com.nokia.testfw.stf.scripteditor.utils.Arguments;
import com.nokia.testfw.stf.scripteditor.utils.Command;
import com.nokia.testfw.stf.scripteditor.utils.Parser;
import com.nokia.testfw.stf.scripteditor.utils.ScriptParser;
import com.nokia.testfw.stf.scripteditor.utils.SectionTag;
import com.nokia.testfw.stf.scripteditor.utils.ScriptParser.ScripterSection;


import java.text.MessageFormat;
import java.util.ArrayList;


public class ScripterAssistProcessor  implements IContentAssistProcessor
 {
	
	public ScripterAssistProcessor() {
		String[] keywordsArray = ScripterWordsProvider.provideKeywords();
		String[] sectionWordsArray = ScripterWordsProvider
				.provideSectionOpeningKeywords();
		parser = new Parser("/scripter.xml");
		SectionTag testSec = parser.getSectionByName("Test");
		this.testCommand = testSec.getCommandList();
		SectionTag dataSec = parser.getSectionByName("Data");
		this.dataCommand = dataSec.getCommandList();

		for (int i = 0; i < keywordsArray.length; i++) {
			keywords.add(keywordsArray[i]);
		}
		for (int i = 0; i < sectionWordsArray.length; i++) {
			sectionWords.add(sectionWordsArray[i]);
		}
	}
	
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		
		createdMacros = Parser.provideMacrosObjects();
		createdObjects = Parser.provideObjectsList();
		
		String document = viewer.getDocument().get();
		ArrayList<ICompletionProposal> props = new ArrayList<ICompletionProposal>();
		ScripterSection currentSection = ScriptParser.getCurrentScripterSection(document, offset);
		
		int startOffset = offset;
		for(int i = offset - 1; i >= 0; i--){
			if(Character.isWhitespace(document.charAt(i))){
				break;
			}
			startOffset = i;
		}
		String wordBeginning = document.substring( startOffset, offset );
		if(currentSection != ScripterSection.NoSection){
			if(currentSection == ScripterSection.StifSettingsSection){
				if(ScripterWordsProvider.END_TEST_TAG.startsWith(wordBeginning)){
					props.add(new CompletionProposal(ScripterWordsProvider.END_STIF_SETTINGS_TAG, startOffset, offset - startOffset, ScripterWordsProvider.END_TEST_TAG.length()));
				}
			}
			else if(currentSection == ScripterSection.DefineSection){
				if(ScripterWordsProvider.END_DEFINE_TAG.startsWith(wordBeginning)){
					props.add(new CompletionProposal(ScripterWordsProvider.END_DEFINE_TAG, startOffset, offset - startOffset, ScripterWordsProvider.END_DEFINE_TAG.length()));
				}
			}
			else if(currentSection == ScripterSection.NewIncludeModuleSection){
				if(ScripterWordsProvider.END_INCLUDE_MODULE_TAG.startsWith(wordBeginning)){
					props.add(new CompletionProposal(ScripterWordsProvider.END_INCLUDE_MODULE_TAG, startOffset, offset - startOffset, ScripterWordsProvider.END_INCLUDE_MODULE_TAG.length()));
				}
			}
			else if(currentSection == ScripterSection.DataSection){
				if(ScripterWordsProvider.END_DATA_TAG.startsWith(wordBeginning)){
					props.add(new CompletionProposal(ScripterWordsProvider.END_DATA_TAG, startOffset, offset - startOffset, ScripterWordsProvider.END_DATA_TAG.length()));
				}
				createProposalForCmds(props, wordBeginning, startOffset, offset, dataCommand);
			}
			else if(currentSection == ScripterSection.TestSection){
				getProposalsForTestSection(props, wordBeginning, startOffset, offset);
			}
			
		}
		else {
			for (String sectionWord : sectionWords) {
				if (sectionWord.startsWith(wordBeginning)) {
					if (sectionWord.startsWith("[")) {
						sectionWord = sectionWord.substring(1);
                        sectionWord = sectionWord.substring(0, sectionWord.length()-1);						
					}
					
					SectionTag secTag = parser.getSectionByName(sectionWord);
					String propStr = secTag.getProposal();
					propStr = propStr.replace('\\', '\n');
					CompletionProposal proposal = 
						new CompletionProposal(propStr, startOffset, offset - startOffset, propStr.length());
					props.add(proposal);
				}
			}
			
		}
		return props.toArray(new ICompletionProposal[0]);
	}
	
	
    private void getProposalsForTestSection(ArrayList<ICompletionProposal> props, String wordBeginning,
			int startOffset, int offset) {

		if (ScripterWordsProvider.END_STIF_SETTINGS_TAG
				.startsWith(wordBeginning)) {
			props.add(new CompletionProposal(
					ScripterWordsProvider.END_TEST_TAG, startOffset, offset
							- startOffset,
					ScripterWordsProvider.END_STIF_SETTINGS_TAG.length()));
		}

		for (int i = 0; i < createdMacros.size(); i++) {
			String keyword = createdMacros.get(i);
			if (keyword.startsWith(wordBeginning)) {
				CompletionProposal proposal = new CompletionProposal((keyword),
						startOffset, offset - startOffset, keyword.length(),
						null, null, null, " \n Macro defined in Define section");
				props.add(proposal);
			}
		}

		for (int i = 0; i < createdObjects.size(); i++) {
			String keyword = createdObjects.get(i);
			if (keyword.startsWith(wordBeginning)) {
				CompletionProposal proposal = new CompletionProposal((keyword),
						startOffset, offset - startOffset, keyword.length(),
						null, null, null,
						" \n Object created using \"create\" or \"cratekernel\" command.");
				props.add(proposal);
			}
		}
		createProposalForCmds(props, wordBeginning, startOffset, offset, testCommand);
	}
    
    private void createProposalForCmds(ArrayList<ICompletionProposal> props, String wordBeginning,
			int startOffset, int offset, ArrayList<Command> command) {
    	for (int i = 0; i < command.size(); i++) {
			String keyword = command.get(i).getCommandName();
			ArrayList<Arguments> optional = command.get(i).getOptional();
			ArrayList<Arguments> argument = command.get(i).getMandatory();
			String tempKeyword = command.get(i).getCommandName();
			if (keyword.startsWith(wordBeginning)) {
				for (int j = 0; j < argument.size(); j++) {

					if (argument.get(j).getArgumenType() != null) {
						if (argument.get(j).getArgumenType().equals("literal")) {
							keyword = (keyword + " " + argument.get(j)
									.getArgumentName());
							tempKeyword = tempKeyword.concat(" "
									+ argument.get(j).getArgumentName());
						}
						if (argument.get(j).getArgumenType().equals("digit")) {
							keyword = (keyword + " 0");
							tempKeyword = tempKeyword.concat(" "
									+ argument.get(j).getArgumentName());
						}
						if (argument.get(j).getArgumenType().equals("value")) {
							keyword = (keyword + " " + argument.get(j)
									.getArgumentName());
							tempKeyword = tempKeyword.concat(" value");
						}
						if (argument.get(j).getArgumenType().equals("quoted")) {
							keyword = (keyword + " \""
									+ argument.get(j).getArgumentName() + "\"");
							tempKeyword = tempKeyword.concat(" "
									+ argument.get(j).getArgumentName());
						}
					}

				}
				CompletionProposal proposal = new CompletionProposal(keyword,
						startOffset, offset - startOffset, keyword.length(),
						null, tempKeyword, null, "\n"
								+ command.get(i).getCommandInfo());
				props.add(proposal);
				if (optional != null) {
					for (int j = 0; j < optional.size(); j++) {

						if (optional.get(j).getArgumenType() != null) {
							if (optional.get(j).getArgumenType()
									.equals("words")) {
								continue;
							}

							if (optional.get(j).getArgumenType().equals(
									"literal")
									|| optional.get(j).getArgumenType().equals(
											"name")) {
								if (optional.get(j).getArgumentBinding()
										.equals("=")) {

									tempKeyword = tempKeyword
											.concat(" "
													+ optional.get(j)
															.getArgumentName());
									if (optional.get(j).getNextValue().equals(
											"digit")) {

										keyword = (keyword
												+ " "
												+ optional.get(j)
														.getArgumentName() + " = 0");

									} else {
										keyword = (keyword
												+ " "
												+ optional.get(j)
														.getArgumentName() + " = value");

									}
								} else {
									keyword = (keyword + " " + optional.get(j)
											.getArgumentName());
									tempKeyword = tempKeyword
											.concat(" "
													+ optional.get(j)
															.getArgumentName());
								}
							}
							if (optional.get(j).getArgumenType()
									.equals("digit")) {
								keyword = (keyword + " 0");
								tempKeyword = tempKeyword.concat(" "
										+ optional.get(j).getArgumentName());
							}
							if (optional.get(j).getArgumenType()
									.equals("value")) {
								keyword = (keyword + " " + optional.get(j)
										.getArgumentName());
								tempKeyword = tempKeyword.concat(" value");
							}
							if (optional.get(j).getArgumenType().equals(
									"quoted")) {
								keyword = (keyword + " \""
										+ optional.get(j).getArgumentName() + " = value\"");
								tempKeyword = tempKeyword.concat(" "
										+ optional.get(j).getArgumentName());
							}
						}
						CompletionProposal prop = new CompletionProposal(
								keyword, startOffset, offset - startOffset,
								keyword.length(), null, tempKeyword, null,
								command.get(i).getCommandInfo());
						props.add(prop);
					}
				}
			}
		}
    	
    }
    
	
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int documentOffset) {
		IContextInformation[] result= new IContextInformation[5];
		for (int i= 0; i < result.length; i++)
			result[i]= new ContextInformation(
				MessageFormat.format("CompletionProcessor.ContextInfo.display.pattern", new Object[] { new Integer(i), new Integer(documentOffset) }),
				MessageFormat.format("CompletionProcessor.ContextInfo.value.pattern", new Object[] { new Integer(i), new Integer(documentOffset - 5), new Integer(documentOffset + 5)}));
		return result;
	}
	
	public char[] getCompletionProposalAutoActivationCharacters() {
		
		return new char[] { '.' , 't', 'p' , 'g', 'e'};

	}
	
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return new char[] { 't' };
	}
	
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	private ArrayList<String> createdMacros = new ArrayList<String>();
	private ArrayList<String> createdObjects = new ArrayList<String>();
	private ArrayList<String> sectionWords = new ArrayList<String>();
	private ArrayList<String> keywords = new ArrayList<String>();
	private ArrayList<Command> testCommand =new ArrayList<Command>();
	private ArrayList<Command> dataCommand =new ArrayList<Command>();
	private Parser parser;
	
	public ICompletionProposal[] computeCompletionProposals(IContentAssistSubjectControl arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public IContextInformation[] computeContextInformation(IContentAssistSubjectControl arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Command> getCommand() {
		return testCommand;
	}
	

}
