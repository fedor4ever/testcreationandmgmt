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


package com.symbian.driver.perspectives;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;

public class TestPespective implements IPerspectiveFactory {
	
	/**
	 * Perspective ID <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String ID_PERSPECTIVE = "com.symbian.driver.provider.DriverEditorAdvisorPerspective";

	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 *      <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void createInitialLayout(IPageLayout layout) {
		
		layout.setEditorAreaVisible(true);
		layout.addPerspectiveShortcut(ID_PERSPECTIVE);
		
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		left.addView(IPageLayout.ID_RES_NAV);

		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.80f , layout.getEditorArea());
		
		bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        
		layout.getViewLayout(IPageLayout.ID_PROP_SHEET).setCloseable(false);
		layout.getViewLayout(IPageLayout.ID_PROBLEM_VIEW).setCloseable(false);
		layout.getViewLayout(IConsoleConstants.ID_CONSOLE_VIEW).setCloseable(false);

	}
	
}
