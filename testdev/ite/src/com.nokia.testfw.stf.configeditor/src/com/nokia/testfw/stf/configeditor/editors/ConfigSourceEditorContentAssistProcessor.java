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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import com.nokia.testfw.stf.configmanager.ConfigUtil;
import com.nokia.testfw.stf.configmanager.SectionElementType;

/**
 * Config source editor content assistant
 *
 */
public class ConfigSourceEditorContentAssistProcessor implements IContentAssistProcessor {
	/**
	 * List of STIF config keywords
	 */
	private List<String> keywords = null;
	
	/**
	 * Creates content assistant
	 */
	public ConfigSourceEditorContentAssistProcessor() {
		keywords = new ArrayList<String>();
		
        SectionElementType[] engineDefaultsElements = ConfigUtil.getAllowedEngineDefaultsSectionElements();
        for ( int i = 0; i < engineDefaultsElements.length; i++) {
        	keywords.add( ConfigUtil.getEngineDefaultsSectionElementTag(engineDefaultsElements[ i ]) );
        }

        SectionElementType[] loggerDefaultsElements = ConfigUtil.getAllowedLoggerDefaultsSectionElements();
        for ( int i = 0; i < loggerDefaultsElements.length; i++) {
        	keywords.add( ConfigUtil.getLoggerDefaultsSectionElementTag(loggerDefaultsElements[ i ]) );
        }

        SectionElementType[] moduleElements = ConfigUtil.getAllowedModuleSectionElements();
        for ( int i = 0; i < moduleElements.length; i++) {
        	keywords.add( ConfigUtil.getModuleSectionElementTag(moduleElements[ i ]) );
        }		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		String source = viewer.getDocument().get();
		int startOffset = offset;
		for ( int i = offset - 1; i >= 0; i-- ) {
			if ( Character.isWhitespace(source.charAt( i )) ) {
				break;
			}
			startOffset = i;
		}
		
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		String token = source.substring( startOffset, offset );
		for ( int i = 0; i < keywords.size(); i++ ) {
			String keyword = keywords.get( i );
			if ( keyword.startsWith( token ) ) {
				CompletionProposal proposal = new CompletionProposal(
						keyword,startOffset, offset - startOffset, keyword.length() );
				proposals.add( proposal );
			}
		}
		
		return proposals.toArray(new ICompletionProposal[0]);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
	 */
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
	 */
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
	 */
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
	 */
	public String getErrorMessage() {
		return null;
	}

}
