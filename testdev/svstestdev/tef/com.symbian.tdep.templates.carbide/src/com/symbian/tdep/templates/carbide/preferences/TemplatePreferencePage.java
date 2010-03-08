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

import org.eclipse.ui.IWorkbenchPreferencePage;

import com.symbian.tdep.templates.carbide.TefTemplatesCarbidePlugin;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>,
 * we can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class TemplatePreferencePage extends
		org.eclipse.ui.texteditor.templates.CTemplatePreferencePage implements
		IWorkbenchPreferencePage {

	public TemplatePreferencePage() {
		setPreferenceStore(TefTemplatesCarbidePlugin.getDefault()
				.getPreferenceStore());
		setTemplateStore(TefTemplatesCarbidePlugin.getDefault()
				.getTemplateStore());
		setContextTypeRegistry(TefTemplatesCarbidePlugin.getDefault()
				.getContextTypeRegistry());
	}

	protected boolean isShowFormatterSetting() {
		return false;
	}

	public boolean performOk() {

		boolean ok = super.performOk();

		TefTemplatesCarbidePlugin.getDefault().savePluginPreferences();

		return ok;
	}
}