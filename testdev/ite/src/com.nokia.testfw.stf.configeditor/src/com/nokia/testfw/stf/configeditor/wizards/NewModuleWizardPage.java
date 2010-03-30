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

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * New module wizard page
 * 
 */
public class NewModuleWizardPage extends WizardPage implements IPageChangedListener {
	private Text otherNameValue = null;
	private Button testScripterButton = null;
	//private Button testCombinerButton = null;
	private Button otherNameButton = null;

	ISelection selection = null;
	
	/**
	 * Creates wizard page
	 * @param Workbench selection
	 */
	public NewModuleWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("New module");
		setDescription("This wizard creates a new module entry in initialization file of STF.");		
		this.selection = selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite mainPanel = new Composite(parent, SWT.NULL);
		TableWrapLayout mainPanelLayout = new TableWrapLayout();
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.numColumns = 2;

		testScripterButton = new Button(mainPanel, SWT.RADIO);
		testScripterButton.setText("TestScripter");
		testScripterButton.setSelection( true );
		TableWrapData testScripterButtonLayoutData = new TableWrapData( TableWrapData.FILL_GRAB );
		testScripterButtonLayoutData.colspan = 2;
		testScripterButton.setLayoutData( testScripterButtonLayoutData );

//		testCombinerButton = new Button(mainPanel, SWT.RADIO);
//		testCombinerButton.setText("TestCombiner");
//		TableWrapData testCombinerButtonLayoutData = new TableWrapData( TableWrapData.FILL_GRAB );
//		testCombinerButtonLayoutData.colspan = 2;
//		testCombinerButton.setLayoutData( testCombinerButtonLayoutData );
		
		otherNameButton = new Button(mainPanel, SWT.RADIO);
		otherNameButton.setText("Other:");		
					
		otherNameValue = new Text(mainPanel, SWT.BORDER | SWT.SINGLE);
		otherNameValue.setEnabled( false );
		otherNameValue.setLayoutData( new TableWrapData(TableWrapData.FILL_GRAB) );

		SelectionListener selectEventHandler = new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				dialogChanged();
			}

			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
			}};
		
		testScripterButton.addSelectionListener(selectEventHandler);
//		testCombinerButton.addSelectionListener(selectEventHandler);
		otherNameButton.addSelectionListener(selectEventHandler);
		otherNameValue.addModifyListener( new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();			
			}});
		
		initialize();
		dialogChanged();
		setControl(mainPanel);
		
		/** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForConfigurationFileEditor);;
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
	}

	/**
	 * Ensures that proper module name is defined
	 */
	private void dialogChanged() {
		if ( testScripterButton.getSelection() == true ) {
			otherNameValue.setEnabled(false);
			updateStatus(null);
		} 
//		if ( testCombinerButton.getSelection() == true ) {
//			otherNameValue.setEnabled(false);
//			updateStatus(null);
//		} 
		if ( otherNameButton.getSelection() == true ) {
			otherNameValue.setEnabled(true);
			if ( otherNameValue.getText() == "" ) {
				updateStatus( "Module name must be specified." );
			} else {
				updateStatus(null);
			}
		}
		
	}

	/**
	 * Updates wizard execution status
	 * @param message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	/**
	 * Gets new module name
	 * @return module name
	 */
	public String getModuleName() {
		if ( testScripterButton.getSelection() == true ) {
			return "testscripter";
		} /*else if ( testCombinerButton.getSelection() == true ) {
			return "testcombiner";
		} */else if ( otherNameButton.getSelection() == true ) {
			return otherNameValue.getText();
		}
		return null;
	}

	public void pageChanged(PageChangedEvent ev) {
		if(ev.getSelectedPage() instanceof NewModuleWizardPage){
			otherNameButton.setFocus();
		}
	}
}
