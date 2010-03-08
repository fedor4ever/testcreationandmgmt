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


package com.nokia.s60tools.stif.scripteditor.editors.combiner;

import java.util.ArrayList;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import com.nokia.s60tools.stif.scripteditor.utils.Arguments;
import com.nokia.s60tools.stif.scripteditor.utils.Command;
import com.nokia.s60tools.stif.scripteditor.utils.Parser;
import com.nokia.s60tools.stif.scripteditor.utils.ScriptParser;
import com.nokia.s60tools.stif.scripteditor.utils.SectionTag;
import com.nokia.s60tools.stif.scripteditor.utils.ScriptParser.CombinerSection;

/**
 * Implements code addistant fo script combiner editor mode.
 * 
 */
public class CombinerAssistProcessor implements IContentAssistProcessor {

	/**
	 * Creates assistant content
	 */
	public CombinerAssistProcessor() {
		String[] keywordsArray = CombinerWordsProvider.provideKeywords();
		String[] sectionWordsArray = CombinerWordsProvider
				.provideSectionOpeningKeywords();
		 Parser parser = new Parser("/combiner.xml");
		 SectionTag s = parser.gerSection().get(0);
		 this.command = s.getCommandList();
		
		for (int i = 0; i < keywordsArray.length; i++) {
			keywords.add(keywordsArray[i]);
		}
		for (int i = 0; i < sectionWordsArray.length; i++) {
			sectionWords.add(sectionWordsArray[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#computeCompletionProposals()
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		// TODO createdObjects = Parser.createdObjectsList();
		createdMacros = Parser.provideMacrosObjects();
		String document = viewer.getDocument().get();
		ArrayList<ICompletionProposal> props = new ArrayList<ICompletionProposal>();
		CombinerSection currentSection = ScriptParser
				.getCurrentCombinerSection(document, offset);

		int startOffset = offset;
		for (int i = offset - 1; i >= 0; i--) {
			if (Character.isWhitespace(document.charAt(i))) {
				break;
			}
			startOffset = i;
		}
		String wordBeginning = document.substring(startOffset, offset);
		if (currentSection != CombinerSection.NoSection) {
			if (currentSection == CombinerSection.NewIncludeModuleSection) {
				if (CombinerWordsProvider.END_INCLUDE_MODULE_TAG
						.startsWith(wordBeginning)) {
					props.add(new CompletionProposal(
							CombinerWordsProvider.END_INCLUDE_MODULE_TAG, startOffset,
							offset - startOffset,
							CombinerWordsProvider.END_INCLUDE_MODULE_TAG.length()));
				}
			} else if (currentSection == CombinerSection.DefineSection) {
				if (CombinerWordsProvider.END_DEFINE_TAG
						.startsWith(wordBeginning)) {
					props.add(new CompletionProposal(
							CombinerWordsProvider.END_DEFINE_TAG, startOffset,
							offset - startOffset,
							CombinerWordsProvider.END_DEFINE_TAG.length()));
				}
			} else if (currentSection == CombinerSection.TestSection) {
				if (CombinerWordsProvider.END_TEST_TAG
						.startsWith(wordBeginning)) {
					props.add(new CompletionProposal(
							CombinerWordsProvider.END_TEST_TAG,
							startOffset, offset - startOffset,
							CombinerWordsProvider.END_TEST_TAG
									.length()));
				}
			
			for ( int i = 0; i < createdMacros.size(); i++ ){
				String keyword = createdMacros.get(i);
				
				if(keyword.startsWith(wordBeginning)){
					
					
					CompletionProposal proposal = 
						new CompletionProposal((keyword),startOffset, offset - startOffset, keyword.length(),null,null,null," \n Object created in Define section");
					props.add(proposal);
				}
			}
			
			for (int i = 0; i < command.size(); i++) {
				String keyword = command.get(i).getCommandName();
				ArrayList<Arguments> optional = command.get(i).getOptional();
				ArrayList<Arguments> argument = command.get(i).getMandatory();
				String tempKeyword= command.get(i).getCommandName();
				if (keyword.startsWith(wordBeginning)) {
					for (int j = 0; j < argument.size() ; j++) {
						
						if(argument.get(j).getArgumenType() != null){
						if (argument.get(j).getArgumenType().equals("literal")||argument.get(j).getArgumenType().equals("name")){
							keyword = (keyword +" " + argument.get(j).getArgumentName());
							tempKeyword = tempKeyword.concat(" "+argument.get(j).getArgumentName());
						}
						if (argument.get(j).getArgumenType().equals("digit")){
							keyword = (keyword + " 0") ;
							tempKeyword = tempKeyword.concat(" "+argument.get(j).getArgumentName());
						}
						if (argument.get(j).getArgumenType().equals("value")){
							keyword = (keyword + " "+argument.get(j).getArgumentName()) ;
							tempKeyword = tempKeyword.concat(" value");
						}
						}

					
					}
					CompletionProposal proposal = new CompletionProposal(
							keyword, startOffset, offset - startOffset, keyword.length(),null,tempKeyword,null,command.get(i).getCommandInfo());
					props.add(proposal);
					if(optional!=null){
					for (int j = 0; j < optional.size() ; j++) {
						if(optional.get(j).getArgumenType().equals("words")){
							continue;									
						}
						if(optional.get(j).getArgumenType() != null){
							if (optional.get(j).getArgumenType().equals("literal") || optional.get(j).getArgumenType().equals("name") ){
								if(optional.get(j).getArgumentBinding().equals("=")){
									
									tempKeyword = tempKeyword.concat(" "+optional.get(j).getArgumentName());
									if(optional.get(j).getNextValue().equals("digit")){
						
										keyword = (keyword +" " + optional.get(j).getArgumentName()+" = 0");
										
									}
									else{
										keyword = (keyword +" " + optional.get(j).getArgumentName()+" = value");
										
									}
								}
								else{
									keyword = (keyword +" " + optional.get(j).getArgumentName());
									tempKeyword = tempKeyword.concat(" "+optional.get(j).getArgumentName());
								}
							}
							if (optional.get(j).getArgumenType().equals("digit")){
								keyword = (keyword + " 0") ;
								tempKeyword = tempKeyword.concat(" "+optional.get(j).getArgumentName());
							}
							if (optional.get(j).getArgumenType().equals("value")){
								keyword = (keyword + " "+optional.get(j).getArgumentName()) ;
								tempKeyword = tempKeyword.concat(" value");
							}
							if (optional.get(j).getArgumenType().equals("quoted")){
								keyword = (keyword + " \""+optional.get(j).getArgumentName()+" = value\"");
								tempKeyword = tempKeyword.concat(" "+optional.get(j).getArgumentName());
							}
						}
						CompletionProposal prop = new CompletionProposal(
								keyword, startOffset, offset - startOffset, keyword.length(),null,tempKeyword,null,command.get(i).getCommandInfo());
						props.add(prop);
					}
				}
				}
			}
			}
		} else {
			for (int i = 0; i < sectionWords.size(); i++) {
				String sectionWord = sectionWords.get(i);
				if (sectionWord.startsWith(wordBeginning)) {
					CompletionProposal proposal = new CompletionProposal(
							sectionWord, startOffset, offset - startOffset,
							sectionWord.length());
					props.add(proposal);
				}
			}
		}
		return props.toArray(new ICompletionProposal[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#computeContextInformation()
	 */
	public IContextInformation[] computeContextInformation(ITextViewer arg0,
			int arg1) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#getContextInformationAutoActivationCharacters()
	 */
	public char[] getContextInformationAutoActivationCharacters() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#getErrorMessage()
	 */
	public String getErrorMessage() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.contentassist#getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator() {

		return null;
	}

	private ArrayList<String>  sectionWords = new ArrayList<String>();

	private ArrayList<String>  keywords = new ArrayList<String>();
	
	private ArrayList<Command> command =new ArrayList<Command>();
	
	private ArrayList<String> createdMacros = new ArrayList<String>();
	private ArrayList<String> createdObjects = new ArrayList<String>();
}
