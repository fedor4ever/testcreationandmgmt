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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.symbian.ini.presentation.IniEditorPlugin;

public class GenericEditorsPreferenceInitializer extends
		AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore lStore = IniEditorPlugin.getPlugin().getPreferenceStore();
		
		lStore.setDefault(PreferenceConstants.COMMENT_ABOVE, true);
		lStore.setDefault(PreferenceConstants.TEST_DATA, "c:\\testData\\");
	}

}
