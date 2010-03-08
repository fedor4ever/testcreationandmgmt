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


package com.nokia.s60tools.testdrop.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction; 
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Class used to perform actions after choosing 'Send TestDrop' item from the main 
 * menu's 'Carbide' item or using Alt+D keyboard shortcut
 */
public class BindingAction implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window;
	private boolean validTestDropItemSelected = false;
	
	/**
	 * The constructor.
	 */
	public BindingAction() {
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		if(validTestDropItemSelected){
			try {
				StartUp.setActivePartTargetDialogByBinding(action, window);
				StartUp.startTargetDialog(action, null);
			} catch (NullPointerException ex) {
				LogExceptionHandler.showErrorDialog(ex.getMessage());
			}
		}
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		validTestDropItemSelected = false;			//we assume first that selection is invalid
		if (selection instanceof StructuredSelection) {
			StructuredSelection strSel = (StructuredSelection)selection;
			Object res = strSel.getFirstElement();
			if (res instanceof IProject) {
				IProject project = (IProject)res;
				validTestDropItemSelected = project.isOpen();	//if project is open then this is a valid selection
			}
			else if (res instanceof IFile) {
				IFile file = (IFile)res;
				String extension = file.getFileExtension();
				if(extension.equals("cfg")){
					validTestDropItemSelected = true;	//if this is a cfg file then this is a valid selection
				}
			}
		}
	}

	/**
	 * Use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
