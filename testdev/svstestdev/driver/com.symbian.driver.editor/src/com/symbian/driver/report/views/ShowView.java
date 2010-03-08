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




package com.symbian.driver.report.views;

import java.util.logging.Logger;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import com.symbian.driver.presentation.DriverEditorPlugin;

/**
 * 
 * @author HocineA ShowView is a convenience class that helps show the view in
 *         the workbench from a non-UI thread. implements Runnable.
 * 
 */

public class ShowView implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(ShowView.class.getName());

	/** a Reference to the TestViewer */
	TestViewer lViewer;

	/**
	 * Contructor
	 * 
	 * @param aReport :
	 *            (Report) the Report model to plug into the view
	 */
	public ShowView() {
	}

	/**
	 * showTestRunnerViewPartInActivePage : Shows the the view if not displayed
	 * and set it's input to iReport
	 */
	public void showTestRunnerViewPartInActivePage() {
		IWorkbenchPart activePart = null;
		IWorkbenchPage page = null;

		page = getActivePage();
		activePart = page.getActivePart();
		try {
			lViewer = (TestViewer) page.findView(TestViewer.ID);
			if (lViewer == null) {
				lViewer = (TestViewer) page.showView(TestViewer.ID);
			}
		} catch (PartInitException lInitException) {
			DriverEditorPlugin.getPlugin().log(lInitException);
		}
		page.activate(activePart);
	}

	/**
	 * getActivePage : Get the active page in the workbench
	 * 
	 * @return IWorkbenchPage
	 */
	private static IWorkbenchPage getActivePage() {
		IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
		if (activeWorkbenchWindow == null) {
			return null;
		}
		return activeWorkbenchWindow.getActivePage();
	}

	/**
	 * getActiveWorkbenchWindow : gets the ative workbench window
	 * 
	 * @return IWorkbenchWindow
	 */
	private static IWorkbenchWindow getActiveWorkbenchWindow() {
		IWorkbench workBench = DriverEditorPlugin.getPlugin().getWorkbench();
		if (workBench == null) {
			return null;
		}

		return workBench.getActiveWorkbenchWindow();
	}

	public void run() {
		showTestRunnerViewPartInActivePage();
	}

}
