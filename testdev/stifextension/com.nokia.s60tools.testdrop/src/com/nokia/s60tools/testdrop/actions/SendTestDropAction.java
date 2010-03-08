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
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Action for pop-up shortcut. Mouse right key on project or cfg file
 * > Send TestDrop from project explorer
 * 
 */
public class SendTestDropAction implements IObjectActionDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		StartUp.setActivePartTargetDialog(action, workbenchPart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		StartUp.startTargetDialog(action, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection strSel = (StructuredSelection)selection;
			Object res = strSel.getFirstElement();
			if (res instanceof IProject) {
				IProject project = ((IResource)res).getProject();
				StartUp.setSelectedProject(project);
				LogExceptionHandler.log(project.getName());
			}
			else if (res instanceof IFile) {
				Object[] cfgObjects = strSel.toArray();
				IFile[] cfgFiles = new IFile[cfgObjects.length];
				for (int i = 0; i < cfgObjects.length; i++) {
					cfgFiles[i] = (IFile)cfgObjects[i];
				}
				StartUp.setSelectedCfgFiles(cfgFiles);
			}
		}
	}
}
