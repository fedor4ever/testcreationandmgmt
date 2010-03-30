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
package com.nokia.testfw.codegen.ui.wizard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.cdt.core.CConventions;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.ui.parser.ProjectInfoHelper;

public class ModuleAndPropertyPage extends PropertyPage {

	private Text iModuleNameText;
	private String iModuleName;
	private Set<String> iModuleSet = new HashSet<String>();
	private Button iModuleSeparateFolderBtn;
	private boolean iSeparateFolder = false;
	private IProject iProject;

	public ModuleAndPropertyPage() {
		super(Messages.getString("ModuleAndPropertyPage.Title"));
		setTitle(Messages.getString("ModuleAndPropertyPage.Title"));
		setDescription(Messages.getString("ModuleAndPropertyPage.Description"));
	}

	protected void customExtention(Composite parent) {
		// Module
		Label lTestFolderLocationLabel = new Label(parent, SWT.NONE);
		GridData gd_TestFolderLocationLabel = new GridData();
		lTestFolderLocationLabel.setLayoutData(gd_TestFolderLocationLabel);
		lTestFolderLocationLabel.setText("Test Class Name:");

		iModuleNameText = new Text(parent, SWT.BORDER);
		GridData gd_ModuleNameText = new GridData(4, 16777216, true, false);
		gd_ModuleNameText.horizontalSpan = 2;
		iModuleNameText.setLayoutData(gd_ModuleNameText);
		iModuleNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				setPageComplete(validatePage());
			}
		});

		iModuleSeparateFolderBtn = new Button(parent, SWT.CHECK);
		GridData gd_TModuleSeparateFolderBtn = new GridData();
		gd_TModuleSeparateFolderBtn.horizontalSpan = 3;
		iModuleSeparateFolderBtn.setLayoutData(gd_TModuleSeparateFolderBtn);
		iModuleSeparateFolderBtn
				.setText("Create individual folder for this Test Class");
		iModuleSeparateFolderBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				iSeparateFolder = iModuleSeparateFolderBtn.getSelection();
			}
		});
		iModuleSeparateFolderBtn.setSelection(iSeparateFolder);
	}

	@Override
	public void initPage(Map<String, Object> dataMap) {
		super.initPage(dataMap);
		iProject = ((AbstractTemplateWizard) getWizard()).getTargetProject();
		try {
			if (((AbstractTemplateWizard) getWizard()).isShowNewProjectPage()) {
				String lProjectName = (String) iDataMap
						.get(AbstractTemplateWizard.PROJECT_NAME);
				iModuleNameText.setText(lProjectName);
			} else {
				String modules = ProjectInfoHelper.getProjectStorage(iProject,
						STFNewTestWizard.STF_SCRIPT, STFNewTestWizard.MODULES);
				if (modules != null) {
					String[] module = modules.split(",");
					for (String m : module) {
						iModuleSet.add(m.toLowerCase());
					}
				}

				String separate_folder = ProjectInfoHelper.getProjectStorage(
						iProject, STFNewTestWizard.STF_SCRIPT,
						STFNewTestWizard.SEPARATE_FOLDER);
				iSeparateFolder = Boolean.parseBoolean(separate_folder);
				iModuleSeparateFolderBtn.setSelection(iSeparateFolder);
			}
		} catch (CoreException e) {
			CodegenUIPlugin.getDefault().getLog().log(e.getStatus());
		}
	}

	protected boolean validatePage() {
		boolean isValid = true;
		setMessage(null);
	
		if(!(CConventions.validateFieldName(iModuleNameText.getText()).isOK())){
			isValid = false;
			setErrorMessage(Messages
					.getString("ModuleAndPropertyPage.Invalid_ModuleName"));
		}
		if (iModuleSet.contains(iModuleNameText.getText().toLowerCase())) {
			isValid = false;
			setErrorMessage(Messages
					.getString("ModuleAndPropertyPage.Duplicate_ModuleName"));
		}

		if (isValid) {
			iModuleName = iModuleNameText.getText().trim();
			iSeparateFolder = iModuleSeparateFolderBtn.getSelection();
			setErrorMessage(null);
			return super.validatePage();
		}
		return isValid;
	}

	public String getModuleName() {
		return iModuleNameText.getText().trim();
	}

	public void setModuleName(String module) {
		iModuleName = module;
	}

	public void collectData() {
		super.collectData();
		iDataMap.put("class_name", iModuleName);
		((STFNewTestWizard) getWizard()).setModuleName(iModuleName);
		if (iSeparateFolder) {
			((AbstractTemplateWizard) getWizard())
					.setTestFolderLocation(iModuleName);
		} else {
			((AbstractTemplateWizard) getWizard()).setTestFolderLocation(null);
		}
	}
}
