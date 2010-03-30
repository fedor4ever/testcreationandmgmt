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

package com.nokia.testfw.codegen.ui.wizard;

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;

public abstract class AbstractTemplateWizardPage extends WizardPage implements
		IDataCollector {

	protected Map<String, Object> iDataMap;

	public AbstractTemplateWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public AbstractTemplateWizardPage(String pageName) {
		super(pageName);
	}

	public void initPage(Map<String, Object> dataMap) {
		iDataMap = dataMap;
	}
}
