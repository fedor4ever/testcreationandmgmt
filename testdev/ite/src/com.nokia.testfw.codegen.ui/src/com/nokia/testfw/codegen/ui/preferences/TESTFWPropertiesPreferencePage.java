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

package com.nokia.testfw.codegen.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.preferences.PreferenceConstants;
import com.nokia.testfw.codegen.ui.preferences.PreferenceUtil;
import com.nokia.testfw.codegen.ui.Messages;

public class TESTFWPropertiesPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public StringFieldEditor iTestFolderName;
	public HexFieldEditor iUID3MaxValue;
	public HexFieldEditor iUID3MinValue;
	public StringFieldEditor iAutherName;

	public TESTFWPropertiesPreferencePage() {
		super(GRID);
		setPreferenceStore(CodegenUIPlugin.getDefault().getPreferenceStore());
		setDescription(Messages
				.getString("TESTFWPropertiesPreferencePage.Description"));
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {

		iTestFolderName = new StringFieldEditor(
				PreferenceConstants.TEST_FOLDER_NAME,
				Messages
						.getString("TESTFWPropertiesPreferencePage.TestFolder.Name"),
				getFieldEditorParent()) {
			protected boolean doCheckState() {
				String testFolderName = getTextControl().getText();
				if (!testFolderName.matches("[\\w]*")) {
					setErrorMessage(Messages
							.getString("TESTFWPropertiesPreferencePage.TestFolder.Error"));
					return false;
				}
				return true;
			}
		};

		iUID3MaxValue = new HexFieldEditor(
				PreferenceConstants.UID3_MAX_VALUE,
				Messages
						.getString("TESTFWPropertiesPreferencePage.UID3.MaxValue"),
				getFieldEditorParent()) {
			protected boolean doCheckState() {
				if (super.doCheckState()) {
					if (iUID3MaxValue.getIntValue() < iUID3MinValue
							.getIntValue()) {
						setErrorMessage(Messages
								.getString("TESTFWPropertiesPreferencePage.UID3.Error1"));
						return false;
					} else {
						return true;
					}
				} else {
					Messages.getString(
							"TESTFWPropertiesPreferencePage.UIDHexError",
							iUID3MaxValue.getStringValue());
					return false;
				}
			}
		};

		iUID3MinValue = new HexFieldEditor(
				PreferenceConstants.UID3_MIN_VALUE,
				Messages
						.getString("TESTFWPropertiesPreferencePage.UID3.MinValue"),
				getFieldEditorParent()) {
			protected boolean doCheckState() {
				if (super.doCheckState()) {
					if (iUID3MinValue.getIntValue() > iUID3MaxValue
							.getIntValue()) {
						setErrorMessage(Messages
								.getString("TESTFWPropertiesPreferencePage.UID3.Error2"));
						return false;
					} else {
						return true;
					}
				} else {
					Messages.getString(
							"TESTFWPropertiesPreferencePage.UIDHexError",
							iUID3MinValue.getStringValue());
					return false;
				}
			}
		};

		iAutherName = new StringFieldEditor(PreferenceConstants.AUTHER,
				Messages.getString("TESTFWPropertiesPreferencePage.Author"),
				getFieldEditorParent());

		iTestFolderName.setEmptyStringAllowed(false);
		iUID3MaxValue.setEmptyStringAllowed(false);
		iUID3MinValue.setEmptyStringAllowed(false);
		iAutherName.setEmptyStringAllowed(true);

		addField(iTestFolderName);
		addField(iUID3MaxValue);
		addField(iUID3MinValue);
		addField(iAutherName);
	}

	public boolean performOk() {
		//CodegenUIPlugin.getDefault().savePluginPreferences();
		return super.performOk();
	}

	public class HexFieldEditor extends StringFieldEditor {

		private int minValidValue = 0;
		private int maxValidValue = Integer.MAX_VALUE;
		private static final int DEFAULT_TEXT_LIMIT = 10;

		protected HexFieldEditor() {
		}

		public HexFieldEditor(String name, String labelText, Composite parent) {
			this(name, labelText, parent, DEFAULT_TEXT_LIMIT);
		}

		public HexFieldEditor(String name, String labelText, Composite parent,
				int textLimit) {
			init(name, labelText);
			setTextLimit(textLimit);
			setEmptyStringAllowed(false);
			setErrorMessage(Messages
					.getString("TESTFWPropertiesPreferencePage.UIDHexError3"));
			createControl(parent);
			final Text text = getTextControl();
			text.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent event) {
					String hexString = text.getText();
					int number = -1;
					try {
						number = Integer.decode(hexString).intValue();
						hexString = PreferenceUtil
								.createCanonicalHexString(number);
						text.setText(hexString);
					} catch (NumberFormatException _ex) {
					}
				}
			});
		}

		public void setValidRange(int min, int max) {
			minValidValue = min;
			maxValidValue = max;
			setErrorMessage(Messages.getString(
					"TESTFWPropertiesPreferencePage.UIDHexError2",
					new Object[] { Integer.toHexString(min),
							Integer.toHexString(max) }));
		}

		protected boolean checkState() {
			String hexString;
			Text text = getTextControl();
			if (text == null)
				return false;
			hexString = text.getText();
			int number = -1;
			try {
				number = Integer.decode(hexString).intValue();
			} catch (NumberFormatException _ex) {
				showErrorMessage();
				return false;
			}
			if (number < minValidValue || number > maxValidValue) {
				showErrorMessage();
				return false;
			}
			clearErrorMessage();
			return true;
		}

		protected void doLoad() {
			Text text = getTextControl();
			if (text != null) {
				String strHex = getPreferenceStore().getString(
						getPreferenceName());
				text.setText(strHex);
				oldValue = strHex;
			}
		}

		protected void doLoadDefault() {
			Text text = getTextControl();
			if (text != null) {
				String strHex = getPreferenceStore().getString(
						getPreferenceName());
				text.setText(strHex);
			}
			valueChanged();
		}

		protected void doStore() {
			Text text = getTextControl();
			if (text != null) {
				getPreferenceStore().setValue(getPreferenceName(),
						text.getText());
			}
		}

		public int getIntValue() throws NumberFormatException {
			return Integer.decode(getStringValue()).intValue();
		}
	}
}
