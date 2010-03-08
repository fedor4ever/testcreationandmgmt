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

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.nokia.s60tools.stif.Constants;
import com.nokia.s60tools.stif.util.FileAccessUtils;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (cpp).
 */

public class NewTestModulePage extends WizardPage implements IPageChangedListener {
	
	/**
	 * Handle to control giving an input to Path to test module 
	 */
	private Text modulePathText;

	/**
	 * Handle to control representing the name of test module 
	 */
	private Text moduleName;
	
	/**
	 * Path to test module 
	 */
	private String modulePath;
	
	/**
	 * Handle to control giving an input to TestModuleTemplates
	 */
	private Text testModuleTemplatesPathText;
	
	/**
	 * Path to TestModuleTemplates
	 */
	private String testModuleTemplatesPath;
	
	/**
	 * Module type
	 * @see com.nokia.s60tools.stif.Constants
	 * 
	 */
	private int moduleType;
	
	/**
	 * Handle to group of controls
	 */
	private Group group1;
	
	/**
	 * Handle to a check box button defining if the project needs to be created
	 * in the workspace or not
	 */
	Button buttonCheckbox;
	/**
	 * Button used for browsing direcories
	 */
	Button buttonBrowse;
	/**
	 * Button used for browsing direcories to point the TestModuleTemplates directory
	 */
	Button buttonBrowseTemplates;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewTestModulePage() {
		super("wizardPage");
		System.out.println("start of NewTestModuleWizard constructor");
		setTitle("New test module");
		setDescription("This wizard creates a new STIF test module.");
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		
		//Radio group selection for template type
	    group1 = new Group(container, SWT.SHADOW_IN);
	    group1.setText("Select module type");
	    group1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    Button button = new Button(group1, SWT.RADIO);
	    button.setText("normal"); 
	    button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_NORMAL;
				dialogChanged();
			}
		});
	    
	    button = new Button(group1, SWT.RADIO);
	    button.setText("hardcoded");
	    button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_HARDCODED;
				dialogChanged();
			}
		});
	    Button buttonTest = new Button(group1, SWT.RADIO);
	    buttonTest.setText("testclass");
	    buttonTest.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_TESTCLASS;
				dialogChanged();
			}
		});
	    button = new Button(group1, SWT.RADIO);
	    button.setText("kerneltest");
	    button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_KERNELTEST;
				dialogChanged();
			}
		});
	    button = new Button(group1, SWT.RADIO);
	    button.setText("capsmodifier");
	    button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_CAPSMODIFIER;
				dialogChanged();
			}
		});
	    button = new Button(group1, SWT.RADIO);
	    button.setText("stifunit");
	    button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				moduleType = Constants.MODULE_TYPE_STIFUNIT;
				dialogChanged();
			}
		});
	    
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    gd.horizontalSpan = 2;
	    group1.setLayoutData(gd);
	    
	    Label label = new Label(container, SWT.CENTER); //spacer
	    
	    //Module name
	    label = new Label(container, SWT.NULL);
		label.setText("&Module name:");

		moduleName = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		moduleName.setLayoutData(gd);
		moduleName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				handleCheckbox();
				dialogChanged();
			}
		});
		
	    label = new Label(container, SWT.CENTER); //spacer
	    label = new Label(container, SWT.CENTER); //spacer
	    
	    //Module path
	    buttonCheckbox = new Button(container, SWT.CHECK);
	    buttonCheckbox.setText("Create test module to workspace");
	    gd = new GridData(GridData.FILL_HORIZONTAL);
	    gd.horizontalSpan = 2;
	    buttonCheckbox.setLayoutData(gd);
	    buttonCheckbox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleCheckbox();
				dialogChanged();
			}
		});
	    
		label = new Label(container, SWT.NULL);
		label.setText("Path:");

		modulePathText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		modulePathText.setLayoutData(gd);
		modulePathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		buttonBrowse = new Button(container, SWT.PUSH);
		buttonBrowse.setText("Browse...");
		buttonBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		
		label = new Label(container, SWT.NULL);
		label.setText("TestModuleTemplates folder:");
		
		testModuleTemplatesPathText = new Text(container, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		testModuleTemplatesPathText.setLayoutData(gd);
		
		buttonBrowseTemplates = new Button(container, SWT.PUSH);
		buttonBrowseTemplates.setText("Browse...");
		buttonBrowseTemplates.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowseTemplates();
				dialogChanged();
			}
		});
		
		initialize();
		dialogChanged();
		buttonTest.setSelection(true);
		moduleType = Constants.MODULE_TYPE_TESTCLASS;
		setControl(container);
		
		/** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForTestModuleWizard);
	}
	

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		moduleName.setText("NewTestModule");
		buttonCheckbox.setSelection(false);
		handleCheckbox();
	}
	
	
	/**
	 * Disables / Enables items based on checkbox selection
	 */
	private void handleCheckbox() {
		if (buttonCheckbox.getSelection()) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IPath path = root.getLocation();
			modulePath = path.toOSString() + File.separator;
			modulePathText.setEnabled(false);
			buttonBrowse.setEnabled(false);
		} else {
			modulePathText.setEnabled(true);
			buttonBrowse.setEnabled(true);
		}
	}
	
	/**
	 * Uses the standard directory selection dialog to choose the new value for
	 * the module path field.
	 */
	private void handleBrowse() {
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		String path = dialog.open();
		if (path != null) {
			modulePath = path + File.separator;
			modulePathText.setText( modulePath );
		}
	}
	
	/**
	 * Uses the standard directory selection dialog to choose the new value for
	 * the test module templates path field.
	 */
	private void handleBrowseTemplates() {
		DirectoryDialog dialog = new DirectoryDialog(getShell());
		String path = dialog.open();
		
		if (path != null) {
			testModuleTemplatesPath = path + File.separator;
			testModuleTemplatesPathText.setText( testModuleTemplatesPath );
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged() {
		String templatePath = testModuleTemplatesPath;
		if(templatePath == null){
			return;
		}
		boolean typeSelected = false;
		for (int i = 0; i < group1.getChildren().length; i++) {
			Button button = (Button)group1.getChildren()[i];
			if (button.getSelection()) {
				typeSelected = true;
				break;
			}
		}
		if (!typeSelected) {
			updateStatus("Module type must be specified");
			return;
		}
		if (getModuleName() == null || getModuleName().equals("")) {
			updateStatus("Module name must be specified");
			return;
		}
		else{
		String titlePatternString = "^[A-Za-z_]+\\d*$";
		Pattern titlePattern = Pattern.compile(titlePatternString, Pattern.MULTILINE);
		Matcher regExMatcher = titlePattern.matcher(getModuleName());
		if(!regExMatcher.find()){
			updateStatus("Using some characters in name of a module may cause compilation errors.");
			return;
		}
		}
		File file = new File(modulePath);
		if (!file.exists()) {
			updateStatus("Module save path must exist");
			return;
		}
		if (!file.canWrite()) {
			updateStatus("Module save path must be writable");
			return;
		}
		
		if (moduleType == Constants.MODULE_TYPE_STIFUNIT) {
			File bldInfFile = new File(modulePathText.getText() + File.separator
					+ "group" + File.separator + "bld.inf");
			if(bldInfFile.exists())	{  //STIFUnitv2
				updateStatus(null);
				setMessage("A directory with seleced name already exists. STIFUnit module will be added " +
						"to this project. Existing blf.inf file will be modified.",
						IMessageProvider.WARNING);
				return;
			}
			else {	//STIFUnit
				setMessage(null);
			}
		}
		
		File file2 = new File(modulePathText.getText() + File.separator + moduleName.getText());
		if (file2.exists()) {
			updateStatus("Folder by that module name already exists in save path");
			file2 = null;
			return;
		}
			
		if(moduleType == Constants.MODULE_TYPE_TESTCLASS) {
			String templPath = templatePath;
			templPath += File.separator + "TemplateScriptXXX" + File.separator + "src" + File.separator;
			
			if(!FileAccessUtils.checkTestScripterOrHardcodedTemplateExistance("TemplateScriptXXXBlocks.cpp", templPath))
			{
				updateStatus("Cannot create correct testclass module - lack of templates - choose proper folder");
				return;
			}
		}
		if(moduleType == Constants.MODULE_TYPE_HARDCODED) {
			String templPath = templatePath;
			templPath += File.separator + "HardCodedTestModuleXXX" + File.separator + "src" + File.separator;
			
			if(!FileAccessUtils.checkTestScripterOrHardcodedTemplateExistance("HardCodedTestModuleXXXCases.cpp", templPath))
			{
				updateStatus("Cannot create correct hardcoded module - lack of templates - choose proper folder");
				return;
			}
		}
		if(moduleType == Constants.MODULE_TYPE_NORMAL) {
			String templPath = templatePath;
			templPath += File.separator + "TestModuleXXX" + File.separator + "src" + File.separator + "TestModuleXXX.cpp";
			File cpp = new File(templPath);
			if(!cpp.exists())
			{
				updateStatus("Cannot create correct normal module - lack of templates - choose proper folder");
				return;
			}
		}
		if(moduleType == Constants.MODULE_TYPE_STIFUNIT) {
			String templPath = templatePath;
			templPath += File.separator + "STIFUnitXXX" + File.separator + "src" + File.separator + "STIFUnitXXXCases.cpp";
			File cpp = new File(templPath);
			if(!cpp.exists())
			{
				updateStatus("Cannot create correct STIFUnit module - lack of templates - choose proper folder");
				return;
			}
		}
		if(moduleType == Constants.MODULE_TYPE_KERNELTEST) {
			String templPath = templatePath;
			templPath += File.separator + "TemplateKernelScriptXXX" + File.separator + "src" + File.separator + "TemplateKernelScriptXXXBlocks.cpp";
			File cpp = new File(templPath);
			if(!cpp.exists())
			{
				updateStatus("Cannot create correct kerneltest module - lack of templates - choose proper folder");
				return;
			}
		}
		if(moduleType == Constants.MODULE_TYPE_CAPSMODIFIER) {
			String templPath = templatePath;
			templPath += File.separator + "CapsModifierXXX" + File.separator + "src" + File.separator + "CapsModifierXXX_exe.cpp";
			File cpp = new File(templPath);
			if(!cpp.exists())
			{
				updateStatus("Cannot create correct capsmodifier module - lack of templates - choose proper folder");
				return;
			}
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Gets module path
	 * @return
	 * 		module path
	 */
	public String getModulePath() {
		return modulePathText.getText();
	}

	/**
	 * Gets module name
	 * @return
	 * 		module name
	 */
	public String getModuleName() {
		return moduleName.getText();
	}

	/**
	 * Gets module type
	 * @return
	 * 		module type
	 */
	public int getModuleType() {
		return moduleType;
	}
	
	/**
	 * Gets test module templates path
	 * @return
	 * 		test module templates path
	 */
	public String getTestModuleTemplatesPath() {
		return testModuleTemplatesPath;
	}
	
	/**
	 * Sets module path
	 * @param newText
	 * 			module path
	 */
	public void setModulePath(String newText) {
		modulePath = newText;
		modulePathText.setText(modulePath);
		
		testModuleTemplatesPath = newText + "epoc32\\tools\\s60rndtools\\stif\\TestModuleTemplates";
		testModuleTemplatesPathText.setText(testModuleTemplatesPath);
	}
	
	/**
	 * it is needed to focus one of controls from the wizard page in order to enable
	 * context sensitive help to work properly
	 */
	public void pageChanged(PageChangedEvent ev) {
		Object page = ev.getSelectedPage();
		if(page instanceof NewTestModulePage){
			moduleName.setFocus();
		}
	}
}