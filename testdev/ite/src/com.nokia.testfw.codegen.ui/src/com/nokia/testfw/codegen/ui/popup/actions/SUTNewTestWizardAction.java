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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.nokia.testfw.codegen.ui.wizard.SUTNewTestWizard;

/**
 * Test Case Wizard class
 * 
 */
public class SUTNewTestWizardAction implements IObjectActionDelegate {

	IWorkbenchPart part;
	ISelection selection;

	/*
	 * Constructor
	 */
	public SUTNewTestWizardAction() {
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
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object[] elements = sel.toArray();
			Set<ICProject> projectSet = new HashSet<ICProject>();
			for (Object element : elements) {
				projectSet.add(((ICElement) element).getCProject());
				if (element instanceof ITranslationUnit) {
					if (!((ITranslationUnit) element).isHeaderUnit()) {
						MessageBox box = new MessageBox(new Shell(),
								SWT.ICON_ERROR);
						box.setText("Impossible to generate test cases");
						box
								.setMessage("SymbianUnitTest Case Wizard works only with header files.");
						box.open();
						return;
					}
				}
				if (element instanceof IMethodDeclaration
						&& !(((IMethodDeclaration) element).getParent() instanceof IStructure)) {
					MessageBox box = new MessageBox(new Shell(), SWT.ICON_ERROR);
					box.setText("Impossible to generate test cases");
					box
							.setMessage("SymbianUnitTest Case Wizard works only with header files.");
					box.open();
					return;
				}
			}
			if (projectSet.size() > 1) {
				MessageBox box = new MessageBox(new Shell(), SWT.ICON_ERROR);
				box.setText("Impossible to generate test cases");
				box
						.setMessage("SymbianUnitTest Case Wizard works only with single project.");
				box.open();
				return;
			}

			SUTNewTestWizard wizard = new SUTNewTestWizard();
			wizard.setShowChooseProjectPage(false);
			wizard
					.init(part.getSite().getWorkbenchWindow().getWorkbench(),
							sel);
			// Instantiates the wizard container with the wizard and
			// opens it
			WizardDialog dialog = new WizardDialog(part.getSite().getShell(),
					wizard);
			dialog.setTitle("New SymbianUnitTest Case(s) Wizard");
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
