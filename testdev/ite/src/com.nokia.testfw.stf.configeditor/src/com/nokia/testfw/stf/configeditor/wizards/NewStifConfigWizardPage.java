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


package com.nokia.testfw.stf.configeditor.wizards;

import java.io.File;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ISourceRoot;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

//import com.nokia.testfw.codegen.ui.Messages;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (ini).
 */
public class NewStifConfigWizardPage extends WizardPage implements IPageChangedListener {
	/**
	 * Container name
	 */
	private Text containerText;
	/**
	 * File name
	 */
	private Text fileText;
	/**
	 * Workbench selection
	 */
	private ISelection selection;
	/**
	 * File name prefix
	 */
	private static final String filenamepre = "TestFramework_";
	
	/**
	 * Creates wizard page
	 * @param selection Workbench selection
	 */
	public NewStifConfigWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("New STF config file");
		setDescription("This wizard creates a new STF config file.");
		this.selection = selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged(true);
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged(false);
			}
		});
		initialize();
		dialogChanged(false);
		setControl(container);
		
		/** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForConfigurationFileWizard);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		Object obj = null;
		String projectname = null;
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {

			if (ssel.size() > 1)
				return;
			obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;				
				else
					container = ((IResource) obj).getParent();				
				projectname = container.getProject().getName();
				containerText.setText(container.getFullPath().toString());
			}
			if(obj instanceof ISourceRoot){
				projectname = ((ISourceRoot) obj).getCProject().toString();
				containerText.setText(((ISourceRoot) obj).getPath().toString());
			}
			if(obj instanceof ICElement){
				projectname = ((ICElement) obj).getElementName();
				containerText.setText(((ICElement) obj).getPath().toString());
			}

		}
		if(obj == null || projectname == null){
			fileText.setText(filenamepre);	
			return;
		}		

		if(projectname.length()>0){
			fileText.setText(filenamepre+projectname);
		}
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */
	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged(boolean aConNameChangeFlag) {
 		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getNoExtFileName();
		String projectname = null;
		
		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified or wrong file container");
			return;
		}
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.indexOf(".");
		if (dotLoc != -1) {
			updateStatus("File name must be valid");
			return;
		}		

		projectname = container.getProject().getName();

		if(aConNameChangeFlag == true){
			if(fileName.compareTo(filenamepre + projectname) != 0){
				fileName = filenamepre + projectname;
				fileText.setText(filenamepre + projectname);
			}
		}
		
		File file = new File(container.getLocation().append(fileName).toString()+".ini");
		if(file.exists()){
			updateStatus("File already exist");
			return;
		}
		updateStatus(null);
	}

	/**
	 * Updates progress status
	 * @param message progress message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Gets container name
	 * @return container name
	 */
	public String getContainerName() {
		return containerText.getText();
	}

	/**
	 * Gets file name
	 * @return file name
	 */
	public String getFileName() {
		return fileText.getText()+".ini";
	}
	
	public String getNoExtFileName() {
		return fileText.getText();
	}
	
	public void pageChanged(PageChangedEvent ev) {
		if(ev.getSelectedPage() instanceof NewStifConfigWizardPage){
			containerText.setFocus();
		}
	}	
}