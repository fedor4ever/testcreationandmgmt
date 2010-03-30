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
package com.nokia.testfw.stf.scripteditor.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import com.nokia.testfw.stf.scripteditor.Activator;
import com.nokia.testfw.stf.scripteditor.editors.ScriptEditor;

/**
 * @author xiaoma
 *
 */
public class ScriptEditorPreferencePage extends FieldEditorPreferencePage 
    implements IWorkbenchPreferencePage {

	public BooleanFieldEditor enableAutoActivation;
	public IntegerFieldEditor activationDelay;
	
	public ScriptEditorPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("STF script editor preference");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {

		enableAutoActivation = new BooleanFieldEditor(
				PreferenceConstants.AUTO_ACTIVATION,
				"Enable auto activation", getFieldEditorParent());
		
		activationDelay = new IntegerFieldEditor(
				PreferenceConstants.AUTO_ACTIVATION_DELAY,
				"Auto activation delay:", getFieldEditorParent());
		
		addField(enableAutoActivation);
		addField(activationDelay);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench arg0) {
	}
	
	public boolean performOk()
    {
	   
       boolean ret = super.performOk();;
       try {
			// update the editor if needed
			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
						.getActivePage();
				IEditorPart editor = page.getActiveEditor();
				if (editor != null && editor instanceof ScriptEditor) {
					ScriptEditor se = (ScriptEditor) editor;
					ContentAssistant assistant = (ContentAssistant) se
							.getConfiguration().getContentAssistant();
					assistant.enableAutoActivation(enableAutoActivation
							.getBooleanValue());
					assistant.setAutoActivationDelay(activationDelay
							.getIntValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
       
       return ret;
    }
	
}
