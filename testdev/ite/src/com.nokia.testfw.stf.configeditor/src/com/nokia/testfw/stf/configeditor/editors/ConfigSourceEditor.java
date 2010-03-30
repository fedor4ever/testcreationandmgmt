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

import java.util.ResourceBundle;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * Config source editor
 * 
 */
public class ConfigSourceEditor extends TextEditor {
	/**
	 * Creates config source editor
	 */
	public ConfigSourceEditor() {
        super();
        setSourceViewerConfiguration( new ConfigSourceEditorConfiguration() );	
        
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#createActions()
	 */
	protected void createActions() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle( "STIFConfigEditor" );
		Action action = new ContentAssistAction(resourceBundle, "ContentAssistProposal.", this); 
		String id = ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		action.setActionDefinitionId(id);
		setAction("ContentAssistProposal", action); 
		markAsStateDependentAction("ContentAssistProposal", true);
	}
}
