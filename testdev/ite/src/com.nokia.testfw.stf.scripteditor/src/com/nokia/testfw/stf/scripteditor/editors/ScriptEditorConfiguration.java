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


package com.nokia.testfw.stf.scripteditor.editors;

import java.util.ArrayList;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultTextHover;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.nokia.testfw.stf.scripteditor.Activator;
import com.nokia.testfw.stf.scripteditor.editors.scripter.ScripterAssistProcessor;
import com.nokia.testfw.stf.scripteditor.preference.PreferenceConstants;
import com.nokia.testfw.stf.scripteditor.utils.Command;

/**
 * Config source editor configuration
 * 
 */

public class ScriptEditorConfiguration extends SourceViewerConfiguration {

	/**
	 * Creates Script Editor configuration
	 */
	public ScriptEditorConfiguration() {
		assistant = new ContentAssistant();
		reconciler = new PresentationReconciler();
		scriptScanner = new ScriptScanner();
		repairer = new DefaultDamagerRepairer(scriptScanner);
		scripterAssistProcessor = new ScripterAssistProcessor();
		
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		assistant.enableAutoActivation(store.getBoolean(PreferenceConstants.AUTO_ACTIVATION));
		assistant.setAutoActivationDelay(store.getInt(PreferenceConstants.AUTO_ACTIVATION_DELAY));
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
	
	public IContentAssistant getContentAssistant() {
		return assistant;
	} 

	/**
	 * Changes Script Editor mode
	 */
	public void changeConfigurationMode(ArrayList<String> subSectionContent) {
		assistant.setContentAssistProcessor(scripterAssistProcessor,
				IDocument.DEFAULT_CONTENT_TYPE);
		scriptScanner.changeSetOfKeywords(subSectionContent);
	}
	
	public ITextHover getTextHover(ISourceViewer sourceViewer,
            String contentType)
	{
	    return new DefaultTextHover(sourceViewer) {
	    	
	    	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion)
	        {
	    		try {
					String keyword = textViewer.getDocument().get(
							hoverRegion.getOffset(), hoverRegion.getLength());
					ArrayList<Command> cmds = scripterAssistProcessor.getCommand();

					for (Command cmd : cmds) {
						if (keyword.equals(cmd.getCommandName())) {
							return cmd.getCommandInfo(); 
						}
					}
					return null;
				}
	    		catch (Exception e) {
	    			return null;
	    		}
	            
	        }
	    	
	    	
	    	
	    };
	}
	
	public String[] getDefaultPrefixes(ISourceViewer sourceViewer, String contentType) {
        return super.getDefaultPrefixes(sourceViewer, contentType);
    }

	private ContentAssistant assistant;

	private PresentationReconciler reconciler;

	private DefaultDamagerRepairer repairer;

	private ScriptScanner scriptScanner;
	
	private ScripterAssistProcessor scripterAssistProcessor;
}
