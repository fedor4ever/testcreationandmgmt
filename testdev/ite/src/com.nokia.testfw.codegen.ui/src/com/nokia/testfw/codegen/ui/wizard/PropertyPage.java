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

import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.nokia.testfw.codegen.ui.preferences.PreferenceConstants;
import com.nokia.testfw.codegen.ui.preferences.PreferenceUtil;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.Messages;

public class PropertyPage extends AbstractTemplateWizardPage {

	private Text iUID3Edit;
	private Button iUID3Button;
	private Text iAutherNameEdit;
	private String iUID3;
	private String iAutherName;
	protected Button iPreviewButton;
	protected boolean isShowPreview = true;
	protected Map<String, Object> iDataMap;

	public PropertyPage() {
		super(Messages.getString("PropertyPage.Title"));
		setTitle(Messages.getString("PropertyPage.Title"));
		setDescription(Messages.getString("PropertyPage.Description"));
	}

	protected PropertyPage(String pageName) {
		super(pageName);
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginTop = 10;
		gridLayout.marginRight = 10;
		gridLayout.marginLeft = 10;
		gridLayout.marginBottom = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);

		// Custom extention
		customExtention(container);

		// UID3
		Label lUID3Label = new Label(container, SWT.NONE);
		GridData gd_UID3Label = new GridData();
		lUID3Label.setLayoutData(gd_UID3Label);
		lUID3Label.setText("UID3:");

		iUID3Edit = new Text(container, 2048);
		GridData gd_UID3Edit = new GridData(GridData.FILL_HORIZONTAL);
		gd_UID3Edit.horizontalSpan = 1;
		iUID3Edit.setLayoutData(gd_UID3Edit);
		iUID3Edit.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setPageComplete(validatePage());
			}
		});

		iUID3Button = new Button(container, SWT.NONE);
		iUID3Button.setText("Random");
		iUID3Button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent selectionevent) {
				setUID3Random();
			}
		});

		// AutherName
		Label lAutherNameLabel = new Label(container, SWT.NONE);
		GridData gd_AutherNameLabel = new GridData();
		lAutherNameLabel.setLayoutData(gd_AutherNameLabel);
		lAutherNameLabel.setText("Author:");

		iAutherNameEdit = new Text(container, 2048);
		GridData gd_AutherNameEdit = new GridData(GridData.FILL_HORIZONTAL);
		gd_AutherNameEdit.horizontalSpan = 2;
		iAutherNameEdit.setLayoutData(gd_AutherNameEdit);

		// Seperator
		Label lSeperator = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_Seperator = new GridData(GridData.FILL_HORIZONTAL);
		gd_Seperator.horizontalSpan = 3;
		lSeperator.setLayoutData(gd_Seperator);

		// PreviewButton
		iPreviewButton = new Button(container, SWT.CHECK);
		iPreviewButton.setLayoutData(new GridData());
		iPreviewButton.setText("Preview changes");
		iPreviewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent selectionevent) {
				getContainer().updateButtons();
			}
		});
		iPreviewButton.setVisible(isShowPreview);

		setControl(container);
		initDefaults();
	}

	protected void customExtention(Composite parent) {
	}

	protected void initDefaults() {
		IPreferenceStore preferenceStore = CodegenUIPlugin.getDefault()
				.getPreferenceStore();
		setUID3Random();
		iAutherNameEdit.setText(iAutherName = preferenceStore
				.getString(PreferenceConstants.AUTHER));
	}

	private void setUID3Random() {
		iUID3Edit.setText(iUID3 = PreferenceUtil.getRandomAppUID());
		Event event = new Event();
		event.item = iUID3Edit;
		iUID3Edit.notifyListeners(24, event);
	}

	protected boolean validatePage() {
		boolean isValid = true;
		setMessage(null);

		if (!PreferenceUtil.validateAppUIDText(iUID3Edit.getText())) {
			isValid = false;
			setErrorMessage(Messages.getString(
					"LocationAndPropertyPage.UIDHexError", iUID3Edit.getText()));
		}

		if (isValid) {
			iUID3 = iUID3Edit.getText();
			iAutherName = iAutherNameEdit.getText();
			setErrorMessage(null);
		}
		return isValid;
	}

	public boolean isShowPreviewChanges() {
		return iPreviewButton.getSelection();
	}

	public void setShowPreviewChanges(boolean show) {
		iPreviewButton.setSelection(show);
	}

	public boolean isShowPreviewButton() {
		return isShowPreview;
	}

	public void setShowPreviewButton(boolean isShowPreviewButton) {
		this.isShowPreview = isShowPreviewButton;
	}

	public void collectData() {
		iDataMap.put("UID3", iUID3);
		iDataMap.put("auther_name", iAutherName);
	}

	public String getiUID3() {
		return iUID3;
	}

	public String getiAutherName() {
		return iAutherName;
	}

	public void initPage(Map<String, Object> dataMap) {
		iDataMap = dataMap;
		setPageComplete(validatePage());
	}
}
