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



package com.symbian.tdep.templates.carbide;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import com.nokia.carbide.internal.api.templatewizard.ui.IExtraPagesProvider;
import com.nokia.carbide.internal.api.templatewizard.ui.IWizardDataPage;

/**
 * A extension point class to provide extra wizard pages to template wizard  
 * 
 * @author Development Tools
 */
public class TemplatesPagesProvider implements IExtraPagesProvider {

	private IStructuredSelection iStructuredSelection;

	/**
	 * @return extra page(s) of template wizard
	 */
	public List<IWizardDataPage> getPages(IWorkbenchWizard aWorkbenchWizard) {
		List<IWizardDataPage> lWizardDataPageList = new ArrayList<IWizardDataPage>();
		
		lWizardDataPageList.add(new GetTestableItemsPage(iStructuredSelection));
		
		return lWizardDataPageList;
	}

	/**
	 * Initialize this provider 
	 */
	public void init(IWorkbenchWizard aWorkbenchWizard, IWorkbench aWorkbench, IStructuredSelection aStructuredSelection) {
		iStructuredSelection = aStructuredSelection;
	}
}
