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



package com.symbian.tdep.templates.carbide.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import com.symbian.tdep.templates.carbide.TefTemplatesCarbidePlugin;

public class PatternPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private StringFieldEditor classNamePattern, methodNamePattern;

	public PatternPreferencePage() {
		super(GRID);
		setPreferenceStore(TefTemplatesCarbidePlugin.getDefault()
				.getPreferenceStore());
		setDescription("Name Templates");
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {

		classNamePattern = new StringFieldEditor(
				PreferenceConstants.CLASS_WRAPPER_PATTERN,
				"Wrapper Class Name Template", getFieldEditorParent()) {
			protected boolean doCheckState() {
				String className = getTextControl().getText();
				if (!className.contains("${className}")) {
			        setErrorMessage("Wrapper Class Name Template should include \"${className}\".");
					return false;
				}
				if (!className.replace("${className}", "A").matches(
						"^[A-Za-z]+[\\w]*$")) {
			        setErrorMessage("Illegal character contained.");
					return false;
				}
				return true;
			}
		};

		methodNamePattern = new StringFieldEditor(
				PreferenceConstants.METHOD_WRAPPER_PATTERN,
				"Wrapper Method Name Template", getFieldEditorParent()) {
			protected boolean doCheckState() {
				String methodName = getTextControl().getText();
				if (!methodName.contains("${methodName}")) {
			        setErrorMessage("Wrapper Method Name Template should include \"${methodName}\".");
					return false;
				}
				if (!methodName.replace("${methodName}", "M").matches(
						"^[A-Za-z]+[\\w]*$")) {
			        setErrorMessage("Illegal character contained.");
					return false;
				}
				return true;
			}
		};

		classNamePattern.setEmptyStringAllowed(false);
		methodNamePattern.setEmptyStringAllowed(false);

		addField(classNamePattern);
		addField(methodNamePattern);
	}

	public boolean performOk() {
		TefTemplatesCarbidePlugin.getDefault().savePluginPreferences();
        return super.performOk();
	}

}
