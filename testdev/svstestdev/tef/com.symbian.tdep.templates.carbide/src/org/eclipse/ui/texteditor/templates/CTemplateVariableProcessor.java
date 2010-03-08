/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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



package org.eclipse.ui.texteditor.templates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class CTemplateVariableProcessor implements IContentAssistProcessor {

	CTemplateVariableProcessor() {
	}

	public void setContextType(TemplateContextType contextType) {
		fContextType = contextType;
	}

	public TemplateContextType getContextType() {
		return fContextType;
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int documentOffset) {
		if (fContextType == null)
			return null;
		List proposals = new ArrayList();
		String text = viewer.getDocument().get();
		int start = getStart(text, documentOffset);
		int end = documentOffset;
		String string = text.substring(start, end);
		String prefix = string.length() < 2 ? null : string.substring(2);
		int offset = start;
		int length = end - start;
		for (Iterator iterator = fContextType.resolvers(); iterator.hasNext();) {
			TemplateVariableResolver variable = (TemplateVariableResolver) iterator
					.next();
			if (prefix == null || variable.getType().startsWith(prefix))
				proposals.add(new TemplateVariableProposal(variable, offset,
						length, viewer));
		}

		Collections.sort(proposals, fgTemplateVariableProposalComparator);
		return (ICompletionProposal[]) proposals
				.toArray(new ICompletionProposal[proposals.size()]);
	}

	private int getStart(String string, int end) {
		int start = end;
		if (start >= 1 && string.charAt(start - 1) == '$')
			return start - 1;
		for (; start != 0
				&& Character.isUnicodeIdentifierPart(string.charAt(start - 1)); start--)
			;
		if (start >= 2 && string.charAt(start - 1) == '{'
				&& string.charAt(start - 2) == '$')
			return start - 2;
		else
			return end;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int documentOffset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return (new char[] { '$' });
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	private Comparator fgTemplateVariableProposalComparator = new Comparator() {

		public int compare(Object arg0, Object arg1) {
			TemplateVariableProposal proposal0 = (TemplateVariableProposal) arg0;
			TemplateVariableProposal proposal1 = (TemplateVariableProposal) arg1;
			return proposal0.getDisplayString().compareTo(
					proposal1.getDisplayString());
		}

		public boolean equals(Object arg0) {
			return false;
		}

		public int hashCode() {
			return super.hashCode();
		}

	};
	private TemplateContextType fContextType;

}