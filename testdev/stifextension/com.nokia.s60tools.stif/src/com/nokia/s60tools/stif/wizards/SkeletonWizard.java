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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import com.nokia.carbide.cdt.builder.project.ICarbideBuildConfiguration;
import com.nokia.s60tools.stif.Constants;
import com.nokia.s60tools.stif.util.*;

import org.eclipse.jface.resource.ImageDescriptor;



/**
 * @Class representing Test Case Skeleton Wizard
 */
public class SkeletonWizard extends Wizard {

	/** Handle to SetParametersPage	 */
	private SkeletonSetParametersPage setParametersPage;
	/** Handle to SkeletonSelectOutputPage	 */
	private SkeletonSelectOutputPage selectOutputPage;
	/** Handle to SkeletonPreviewPage	 */
	private SkeletonPreviewPage previewPage;
	/** Handle to selected elementh of workspace	 */
	private IStructuredSelection selection;
	/** Handle to CodeGenerator */
	private CodeGenerator generator;
	/** Handle to selected elementh of workspace */
	protected IWorkbench workbench;
	
	/**
	 * Constructor
	 */
	public SkeletonWizard() {
		super();
		ImageDescriptor imgDesc = com.nokia.s60tools.stif.Activator.getImageDescriptor("icons/stif_bannered.png");
		setDefaultPageImageDescriptor(imgDesc);
		setNeedsProgressMonitor(true);
		setWindowTitle("Test Case Wizard");
	}
	
	/** 
	 * Initialase bounds of the Wizard page
	 */
	private void initializeBounds() {
	    Shell shell = this.getShell();
	    shell.setSize(550, 500);
	    Monitor primary = shell.getMonitor();
	    Rectangle bounds = primary.getBounds ();
	    Rectangle rect = shell.getBounds ();
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    shell.setLocation (x, y);
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		initializeBounds();
		setParametersPage = new SkeletonSetParametersPage(selection);
		addPage(setParametersPage);
		selectOutputPage = new SkeletonSelectOutputPage(selection);
		addPage(selectOutputPage);
		previewPage = new SkeletonPreviewPage();
		addPage(previewPage);
	}
	
	/**
	 * Overridden to check if preview page is the next page and is it
	 * requested to be shown. If user has not selected it to be shown, 
	 * returns null as next page. 
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextpage;
		if (page instanceof SkeletonSelectOutputPage) {
			if (selectOutputPage.isPreviewChanges()) {
				nextpage = super.getNextPage(page);
			} else {
				nextpage = null; 
			}
		} else {
			nextpage = super.getNextPage(page);
		}
		return nextpage;
	}
	

	/**
	 * Generates new codes from user input
	 */
	public void generateCode() {
		getProjectSDKPaths();
		if(selectOutputPage.getModuleType() == Constants.MODULE_TYPE_HARDCODED)
		{
			generator = new CodeGeneratorHard( setParametersPage.getTestCaseMethods() , 
				setParametersPage.getTestCaseMethodParams(), selectOutputPage.getModuleType(),
				selectOutputPage.getOutputCppFilePath(), selectOutputPage.getOutputIncFilePath(),
				selectOutputPage.getCClassName(), getProjectSDKPaths());
		}
		if(selectOutputPage.getModuleType() == Constants.MODULE_TYPE_TESTCLASS)
		{
			generator = new CodeGeneratorTestClass( setParametersPage.getTestCaseMethods() , 
					setParametersPage.getTestCaseMethodParams(), selectOutputPage.getModuleType(),
					selectOutputPage.getOutputCppFilePath(), selectOutputPage.getOutputIncFilePath(),
					selectOutputPage.getCClassName(), getProjectSDKPaths(), selectOutputPage.getCfgFileName());
		}
		if(selectOutputPage.getModuleType() == Constants.MODULE_TYPE_PYTHONTEST)
		{
			generator = new CodeGeneratorPython( setParametersPage.getTestCaseMethods() , 
					setParametersPage.getTestCaseMethodParams(), selectOutputPage.getModuleType(),
					selectOutputPage.getOutputCppFilePath(), "",
					selectOutputPage.getCClassName(),getProjectSDKPaths(), selectOutputPage.getCfgFileName());
		}
		
		generator.run();
	}
	
	/** (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		// if generator does not exists (user didn't go to preview) create and run it
		if (generator == null) {
			generateCode();
		}
		// save content to files
		MessageBox messageBox = new MessageBox(getShell(), SWT.OK|SWT.CANCEL|SWT.ICON_QUESTION);
		messageBox.setText("Confirm save");
		messageBox.setMessage("Selected files will now be overwritten with the new source content. Continue?");
		if (messageBox.open() == SWT.OK) {
			return generator.saveContents();
		} else {
			return false;
		}
	}
	
	/**
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) 
	{
		this.workbench = workbench;
		this.selection = selection;
	}
	
	/**
	 * Returns the code generator
	 * @return codegenerator object
	 */
	public CodeGenerator getGenerator() {
		return generator;
	}
	
	/**
	 * Returns vector of strings that describe paths of all SDK-s registered for this project
	 * @return Vector of paths to registered SDK-s
	 */
	private Vector<String> getProjectSDKPaths()
	{
		IStructuredSelection sel = selection;
		Object selItem = sel.getFirstElement();
		Vector<String> SDKList = new Vector<String>();
		if (selItem instanceof IMethodDeclaration) {
            IMethodDeclaration metDec = ((IMethodDeclaration)selItem);
            IProject proj = metDec.getCProject().getProject();
            List<ICarbideBuildConfiguration> buildConf = 
            	com.nokia.carbide.cdt.builder.CarbideBuilderPlugin.getBuildManager().getProjectInfo(proj).getBuildConfigurations();
            Iterator<ICarbideBuildConfiguration> iter = buildConf.iterator();
            while(iter.hasNext())
            {
            	String epocRoot = iter.next().getSDK().getEPOCROOT();
            	if(!SDKList.contains(epocRoot))
            		SDKList.add(epocRoot);
            }
		}
		return SDKList;		
	}
}
