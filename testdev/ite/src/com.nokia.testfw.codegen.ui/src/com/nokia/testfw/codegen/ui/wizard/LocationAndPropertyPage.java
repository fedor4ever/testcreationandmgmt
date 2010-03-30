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

import java.util.Set;

import org.eclipse.cdt.core.CConventions;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.nokia.testfw.codegen.ui.preferences.PreferenceUtil;
import com.nokia.testfw.codegen.ui.Messages;

public class LocationAndPropertyPage extends PropertyPage {

	private Text iTestFolderLocationEdit;

	private String iTestFolderLocation;

	public LocationAndPropertyPage() {
		super(Messages.getString("LocationAndPropertyPage.Title"));
		setTitle(Messages.getString("LocationAndPropertyPage.Title"));
		setDescription(Messages
				.getString("LocationAndPropertyPage.Description"));
	}

	protected void customExtention(Composite parent) {
		// Location
		Label lTestFolderLocationLabel = new Label(parent, SWT.NONE);
		GridData gd_TestFolderLocationLabel = new GridData();
		lTestFolderLocationLabel.setLayoutData(gd_TestFolderLocationLabel);
		lTestFolderLocationLabel.setText("Test Folder:");

		iTestFolderLocationEdit = new Text(parent, SWT.BORDER);
		GridData gd_TestFolderLocationEdit = new GridData(4, 16777216, true,
				false);
		gd_TestFolderLocationEdit.horizontalSpan = 2;
		iTestFolderLocationEdit.setLayoutData(gd_TestFolderLocationEdit);
		iTestFolderLocationEdit.setToolTipText(Messages
				.getString("LocationAndPropertyPage.Location_Tooltip"));

		Set<String> folderSet = ((AbstractTemplateWizard) getWizard())
				.getTestFoldersSet();
		IProject targetProject = ((AbstractTemplateWizard) getWizard())
				.getTargetProject();
		if (targetProject != null && folderSet != null && folderSet.size() > 0) {
			String folder = folderSet.iterator().next();
			if (targetProject.getFolder(folder).exists()) {
				iTestFolderLocation = folder;
			}
		}
		if (iTestFolderLocation == null) {
			iTestFolderLocationEdit
					.setText(iTestFolderLocation = PreferenceUtil
							.getTestFolderName(null));
		} else {
			iTestFolderLocationEdit.setText(iTestFolderLocation);
			iTestFolderLocationEdit.setEnabled(false);
		}
		iTestFolderLocationEdit.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				org.eclipse.swt.graphics.Point selection = iTestFolderLocationEdit
						.getSelection();
				setPath(iTestFolderLocationEdit, new Path(
						iTestFolderLocationEdit.getText()));
				iTestFolderLocationEdit.setSelection(selection);
				// iTestFolderSet
				setPageComplete(validatePage());
			}
		});
	}

	private void setPath(Text text, IPath path) {
		path = path.makeRelative().setDevice(null);
		String pathStr = path.toPortableString();
		if (!text.getText().equals(pathStr))
			text.setText(pathStr);
	}

	protected boolean validatePage() {
		boolean isValid = true;
		setMessage(null);

		if (iTestFolderLocationEdit.getText().length() == 0) {
			isValid = false;
			setErrorMessage(Messages
					.getString("LocationAndPropertyPage.Empty_TestFolderLocation"));
		} else if (!(CConventions.validateFieldName(iTestFolderLocationEdit
				.getText()).isOK())) {
			isValid = false;
			setErrorMessage(Messages
					.getString("LocationAndPropertyPage.Invalid_TestFolderName"));
		}
		if (isValid) {
			iTestFolderLocation = iTestFolderLocationEdit.getText().trim();
			setErrorMessage(null);
			return super.validatePage();
		}
		return isValid;
	}

	public void collectData() {
		super.collectData();
		((AbstractTemplateWizard) getWizard())
				.setTestFolderLocation(iTestFolderLocation);
	}
}
