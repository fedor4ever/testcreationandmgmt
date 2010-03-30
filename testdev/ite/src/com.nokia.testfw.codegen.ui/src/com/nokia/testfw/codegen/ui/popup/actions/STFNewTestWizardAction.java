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

package com.nokia.testfw.codegen.ui.popup.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.nokia.testfw.codegen.ui.wizard.STFNewTestWizard;

/**
 * Test Case Wizard class
 * 
 */
public class STFNewTestWizardAction implements IObjectActionDelegate {

	IWorkbenchPart part;
	ISelection selection;

	/*
	 * Constructor
	 */
	public STFNewTestWizardAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.
	 * action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {

		if (selection instanceof IStructuredSelection) {
			STFNewTestWizard wizard = new STFNewTestWizard();
			String title;

			IStructuredSelection sel = (IStructuredSelection) selection;
			if (sel.getFirstElement() instanceof IResource) {
				wizard.setShowNewProjectPage(false);
				title = "New Symbian Test Framework Test Class Wizard";
			} else {
				wizard.setShowNewProjectPage(false);
				wizard.setShowNewModulePage(false);
				title = "New Symbian Test Framework Test Case Wizard";
			}
			wizard
					.init(part.getSite().getWorkbenchWindow().getWorkbench(),
							sel);
			// Instantiates the wizard container with the wizard and
			// opens it
			WizardDialog dialog = new WizardDialog(part.getSite().getShell(),
					wizard);
			dialog.setTitle(title);
			dialog.create();
			dialog.open();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
