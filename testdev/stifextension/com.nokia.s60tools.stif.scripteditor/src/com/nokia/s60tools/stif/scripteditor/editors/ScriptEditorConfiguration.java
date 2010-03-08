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


package com.nokia.s60tools.stif.scripteditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.nokia.s60tools.stif.scripteditor.editors.ScriptEditor.EditorMode;
import com.nokia.s60tools.stif.scripteditor.editors.combiner.CombinerAssistProcessor;
import com.nokia.s60tools.stif.scripteditor.editors.scripter.ScripterAssistProcessor;

/**
 * Config source editor configuration
 * 
 */

public class ScriptEditorConfiguration extends SourceViewerConfiguration {

	/**
	 * Creates Script Editor configuration
	 *  @param EditorMode 
	 */
	public ScriptEditorConfiguration(EditorMode mode) {
		assistant = new ContentAssistant();
		reconciler = new PresentationReconciler();
		scriptScanner = new ScriptScanner(mode);
		repairer = new DefaultDamagerRepairer(scriptScanner);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration#getAnnotationHover
	 */
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return new DefaultAnnotationHover();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer arg0) {
		reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setDamager(repairer, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return assistant;
	}

	/**
	 * Changes Script Editor mode
	 * @param EditorMode 
	 */
	public void changeConfigurationMode(EditorMode mode) {
		if (mode == EditorMode.scripterMode) {
			assistant.setContentAssistProcessor(new ScripterAssistProcessor(),
					IDocument.DEFAULT_CONTENT_TYPE);
			scriptScanner.changeSetOfKeywords(EditorMode.scripterMode);
		}
		if (mode == EditorMode.combinerMode) {
			assistant.setContentAssistProcessor(new CombinerAssistProcessor(),
					IDocument.DEFAULT_CONTENT_TYPE);
			scriptScanner.changeSetOfKeywords(EditorMode.combinerMode);
		}
	}

	private ContentAssistant assistant;

	private PresentationReconciler reconciler;

	private DefaultDamagerRepairer repairer;

	private ScriptScanner scriptScanner;
}
