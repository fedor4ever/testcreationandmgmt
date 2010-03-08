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


package com.nokia.s60tools.stif.configeditor.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.nokia.s60tools.stif.configeditor.Activator;
import com.nokia.s60tools.stif.configmanager.ConfigManager;

/**
 * New module wizard
 * 
 */
public class NewModuleWizard extends Wizard implements INewWizard {
	/**
	 * Wizard page
	 */
	private NewModuleWizardPage page = null;
	/**
	 * Workbench selection
	 */
	private ISelection selection = null;
	/**
	 * STIF config manager
	 */
	private ConfigManager configManager = null;
	
	/**
	 * Creates wizard
	 */
	public NewModuleWizard( ConfigManager configManager ) {
		super();
		ImageDescriptor imgDesc = Activator.getImageDescriptor("icons/stif_bannered.png");
		setDefaultPageImageDescriptor( imgDesc );		
		setNeedsProgressMonitor(true);
		this.configManager = configManager;
	}
	
	/**
	 * Adds page to the wizard.
	 */
	public void addPages() {
		page = new NewModuleWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String moduleName = page.getModuleName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				doFinish( moduleName, monitor);
				monitor.done();
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Executes add module operation
	 * @param moduleName module names
	 * @param monitor progress monitor
	 */
	private void doFinish( String moduleName, IProgressMonitor monitor) {
		// create a sample file
		monitor.beginTask("Adding module:" + moduleName, 1);
		configManager.addModule(moduleName);
		monitor.worked(1);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
	
	/**
	 * Gets module name
	 * @return module name
	 */
	public String getModuleName() {
		if ( page!= null ) {
			return page.getModuleName();
		}
		return null;
	}
}
