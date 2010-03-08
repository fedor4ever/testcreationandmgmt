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


package com.nokia.s60tools.stif.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import org.eclipse.jface.resource.ImageDescriptor;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;

import com.nokia.s60tools.stif.Constants;
import com.nokia.carbide.cpp.sdk.core.ISymbianBuildContext;
import com.nokia.carbide.cpp.sdk.ui.shared.BuildTargetsPage;
import com.nokia.carbide.cpp.project.core.ProjectCorePlugin;

import com.nokia.s60tools.stif.util.IOUtil;

/**
 * Wizard to create a STIF test module project 
 * based on selected template.
 */
public class NewTestModuleWizard extends Wizard implements INewWizard {
	private NewTestModulePage newPage;
	private BuildTargetsPage  buildTargetPage;
	private java.util.List<ISymbianBuildContext> choosenSDKs;
	public String SDKPath;

	/**
	 * Constructor for NewTestModuleWizard.
	 */
	public NewTestModuleWizard() {
		super();
		ImageDescriptor imgDesc = com.nokia.s60tools.stif.Activator.getImageDescriptor("icons/stif_bannered.png");
		setDefaultPageImageDescriptor(imgDesc);
		setNeedsProgressMonitor(true);
		setWindowTitle("Test Module Wizard");
	}
	
	/**
	 * Adding the newPage to the wizard.
	 */
	public void addPages() {
		buildTargetPage = new BuildTargetsPage();
		addPage(buildTargetPage);
		newPage = new NewTestModulePage();
		addPage(newPage);
	}
	
	/**
	 * Gets SDK path
	 * @return
	 * 		SDK path
	 */
	public String getSDKPath()
	{
		return SDKPath;
	}
	
	/**
	 * Overridden to check if preview page is the next page and is it
	 * requested to be shown. If user has not selected it to be shown, 
	 * returns null as next page. 
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextpage;
		if (page instanceof BuildTargetsPage) {
			SDKPath = buildTargetPage.getSelectedBuildConfigs().get(0).getSDK().getEPOCROOT();
			newPage.setModulePath(SDKPath);
		}
		nextpage = super.getNextPage(page);
		return nextpage;
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String modulePath = newPage.getModulePath();
		final String moduleName = newPage.getModuleName();
		final int moduleType = newPage.getModuleType();
		final String testModuleTemplatePath = newPage.getTestModuleTemplatesPath();
		choosenSDKs = buildTargetPage.getSelectedBuildConfigs();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(modulePath, moduleName, moduleType, testModuleTemplatePath, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
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
	 * The worker method. Calls batch with the arguments from wizard newPage.
	 */
	private void doFinish(
		String modulePath, String moduleName,
		int moduleType, String templatePath,
		IProgressMonitor monitor) throws CoreException {
		// create a sample file
		monitor.beginTask("Creating new STIF test module: " + moduleName, 3);
		
		try {
			Runtime r = Runtime.getRuntime();
			@SuppressWarnings("unused")
			Process p;
			String batchFile = "";
			if (moduleType == Constants.MODULE_TYPE_NORMAL) {
			    templatePath += File.separator + "TestModuleXXX" + File.separator;
			    batchFile = "createmodule.bat";
			    
			} else if (moduleType == Constants.MODULE_TYPE_HARDCODED) {
				templatePath += File.separator + "HardCodedTestModuleXXX" + File.separator;
				batchFile = "createhardcodedmodule.bat";
			} else if (moduleType == Constants.MODULE_TYPE_TESTCLASS) {
				templatePath += File.separator + "TemplateScriptXXX" + File.separator;
				batchFile = "CreateTestClass.bat";
			} else if (moduleType == Constants.MODULE_TYPE_KERNELTEST) {
				templatePath += File.separator + "TemplateKernelScriptXXX" + File.separator;
				batchFile = "CreateKernelTestClass.bat";
			} else if (moduleType == Constants.MODULE_TYPE_CAPSMODIFIER) {
				templatePath += File.separator + "CapsModifierXXX" + File.separator;
				batchFile = "CreateCapsModifier.bat";
			} else if (moduleType == Constants.MODULE_TYPE_PYTHONTEST) {
				templatePath += File.separator + "PythonWrapperXXX" + File.separator;
				batchFile = "createpythonwrapper.bat";
			} else if (moduleType == Constants.MODULE_TYPE_STIFUNIT) {
				templatePath += File.separator + "STIFUnitXXX" + File.separator;
				
				File bldInfFile = new File(modulePath + File.separator + "group" + File.separator + "bld.inf");
				// we need to check if the selected path is a path to an existing project or not. It is done by checking
				// if group\bld.inf file exists under the selected path
				if (!bldInfFile.exists()) {
					batchFile = "CreateSTIFUnitModule.bat";
				} 
				else {
					batchFile = "CreateSTIFUnitModuleVar2.bat";
				}
				
			}
			
			File checkOfBatExistance = new File(templatePath + "\\" + batchFile);
			if(!checkOfBatExistance.exists()){
				throw new IOException(batchFile + " directory does not exist under path to TestModuleTempates specified in STIF properties");
			}
			
			File file = new File(templatePath);
			String command = "perl -x " + batchFile + " \"" + moduleName + "\" \"" + modulePath + File.separator;
			IOUtil.ignoreProcessStreams(r.exec(command, new String[0], file)).waitFor();
			monitor.worked(1);
			
			if(batchFile == "CreateSTIFUnitModuleVar2.bat"){
				// we do not import project of STIFUnit var2 into workspace. This is because no separate project is really
				// meant to be created. Just new options are added to an existing module
				return;
			}
			
			monitor.setTaskName("Importing project to Carbide.C++. ");
			String projPath = modulePath + moduleName;
			//Caps modifier cause that modules are generated with suffix _exe
			if (moduleType == Constants.MODULE_TYPE_CAPSMODIFIER) {
				projPath += "_exe";
				moduleName += "_exe";
			}
			String projRelGroupPath = projPath + File.separator + "group" + File.separator;
			String projRelBldInfPath = "group" + File.separator + "bld.inf";

			String mmpFilePath = projRelGroupPath + moduleName + ".mmp";
			
			IProject createdProject = ProjectCorePlugin.createProject(moduleName, projPath);
			createdProject.setPersistentProperty(new QualifiedName("project_settings", "TestModuleTemplates path"), templatePath);
			
			ProjectCorePlugin.postProjectCreatedActions(createdProject,
					projRelBldInfPath, choosenSDKs,
					new java.util.ArrayList<String>(), mmpFilePath, null, monitor);
			monitor.worked(2);

		} catch (Exception e) {
			throwCoreException("Error running batch file: " + e.getMessage());
		}
	}

	/**
	 * Helper method for throwing Core Exception with proper message
	 * 
	 * @param message
	 * 			message to be passed to exception's constructor
	 * @throws CoreException
	 * 			exception is thrown each time method is called
	 */
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "com.nokia.s60tools.stif", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
}