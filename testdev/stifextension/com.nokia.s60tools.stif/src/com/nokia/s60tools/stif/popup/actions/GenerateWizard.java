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


package com.nokia.s60tools.stif.popup.actions;

import java.io.File;

import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.nokia.s60tools.stif.Constants;
import com.nokia.s60tools.stif.util.FileAccessUtils;
import com.nokia.s60tools.stif.util.PluginUtils;
import com.nokia.s60tools.stif.wizards.SkeletonWizard;

/**
 * Test Case Wizard class
 *
 */
public class GenerateWizard implements IObjectActionDelegate {

	IWorkbenchPart part;
	ISelection selection;
	
	/*
	 * Constructor
	 */
	public GenerateWizard() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		SkeletonWizard wizard = new SkeletonWizard();

		if(selection instanceof IStructuredSelection)
		{
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object selItem = sel.getFirstElement();
			if (selItem instanceof IMethodDeclaration) {
	            IMethodDeclaration metDec = ((IMethodDeclaration)selItem);
	            IProject proj = metDec.getCProject().getProject();
	            IPath path = proj.getLocation();
	            
	            String mmpPath = path.toString() + File.separator + "group" + File.separator + proj.getName() + ".mmp";
	            File mmpFile = new File(mmpPath);
	            @SuppressWarnings("unused")
				String mmpFileContent = FileAccessUtils.getContents(mmpFile);
	            
	            if (mmpFileContent != null) {
		            int startIndex = mmpFileContent.indexOf("TYPE") + 5;
		    	    int endIndex = mmpFileContent.indexOf("*/");
		    	    boolean moduleIsValid = false;
		    	    if(startIndex == 7 && endIndex != -1)
		    	    {
		    	    	String type = mmpFileContent.substring(startIndex, endIndex);
		    	    	int moduleType = PluginUtils.setModuleType(type);
		    	    	if(moduleType == Constants.MODULE_TYPE_HARDCODED || moduleType == Constants.MODULE_TYPE_TESTCLASS
		    	    			|| moduleType == Constants.MODULE_TYPE_PYTHONTEST)
		    	    	{
		    	    		moduleIsValid = true;
		    	    	}
		    	    }
		    	    	
	    	    	if(moduleIsValid == true)
	    	    	{
	    	    		wizard.init(part.getSite().getWorkbenchWindow().getWorkbench(), sel	);
	    	    		//Instantiates the wizard container with the wizard and opens it
	    	    		WizardDialog dialog = new WizardDialog( part.getSite().getShell(), wizard);
	    	    		dialog.create();
	    	    		dialog.open();
	    	    	}
	    	    	else
	    	    	{
	    	    		MessageBox box = new MessageBox(new Shell(),SWT.ICON_INFORMATION);
		    			box.setText("Impossible to generate a test case");
		    			box.setMessage( "Test Case Wizard works only with hardcoded and testclass modules." );
		    			box.open();
	    	    	}
	            }
	            else {
	            	MessageBox box = new MessageBox(new Shell(),SWT.ICON_INFORMATION);
	    			box.setText("Cannot recognize test module type");
	    			box.setMessage( "It is impossible to to determine test module type" );
	    			box.open();
	            }
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
