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
package com.nokia.testfw.codegen.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = CodegenUIPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(PreferenceConstants.TEST_FOLDER_NAME, "tsrc");
		store.setDefault(PreferenceConstants.UID3_MAX_VALUE, "0x0EFFFFFF");
		store.setDefault(PreferenceConstants.UID3_MIN_VALUE, "0x0E000000");
		store.setDefault(PreferenceConstants.AUTHER, System
				.getProperty("user.name"));
	}

}
