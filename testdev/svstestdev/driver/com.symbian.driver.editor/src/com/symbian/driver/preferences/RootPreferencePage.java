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


package com.symbian.driver.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RootPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public RootPreferencePage() {
	}

	public RootPreferencePage(String aTitle) {
		super(aTitle);
	}

	public RootPreferencePage(String aTitle, ImageDescriptor aImage) {
		super(aTitle, aImage);
	}
	
	@Override
	public void createControl(Composite aParent) {
        Composite content = new Composite(aParent, SWT.NONE);
        setControl(content);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        content.setLayout(layout);
        //Apply the font on creation for backward compatibility
        applyDialogFont(content);

        // initialize the dialog units
        initializeDialogUnits(content);
	}

	@Override
	protected Control createContents(Composite aParent) {
		return null;
	}

	public void init(IWorkbench aWorkbench) {
	}

}
