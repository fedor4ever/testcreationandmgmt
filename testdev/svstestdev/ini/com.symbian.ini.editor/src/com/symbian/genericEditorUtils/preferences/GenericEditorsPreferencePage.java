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



package com.symbian.genericEditorUtils.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.symbian.ini.presentation.IniEditorPlugin;

public class GenericEditorsPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private IPreferenceStore iStore;

	public GenericEditorsPreferencePage() {
		super(SWT.NONE);
		iStore = IniEditorPlugin.getPlugin().getPreferenceStore();
		setPreferenceStore(iStore);
		setDescription("Settings for Package, Ini and Script Editors.");
	}

	@Override
	public void createFieldEditors() {

		Composite lParent = getFieldEditorParent();
		lParent.setLayout(new GridLayout(1, false));
		lParent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite lStringComposite = new Composite(lParent, SWT.NONE);
		lStringComposite.setLayout(new GridLayout(2, false));
		lStringComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		new StringFieldEditor(PreferenceConstants.TEST_DATA,
				"Test Data on Device",
				lStringComposite);
		
		new BooleanFieldEditor(PreferenceConstants.COMMENT_ABOVE,
				"Comments attached to above element",
				lParent);
		
		lParent.pack();
		
	}

	public void init(IWorkbench aWorkbench) {
		setPreferenceStore(IniEditorPlugin.getPlugin().getPreferenceStore());
	}


}
