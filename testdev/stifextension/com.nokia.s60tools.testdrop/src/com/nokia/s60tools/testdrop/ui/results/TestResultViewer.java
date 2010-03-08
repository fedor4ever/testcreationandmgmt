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


package com.nokia.s60tools.testdrop.ui.results;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.TestcaseDisplayOrderModificator;

/**
 * 
 * Shows test results on editor area.
 * 
 */
public class TestResultViewer extends MultiPageEditorPart implements IReusableEditor {

	private static final String EDITOR_ID = "com.nokia.s60tools.testdrop.results.TestResultViewer"; 

	private static Browser browser = null;
	private static Composite browserComposite = null;
	private Button executionOrderButton;
	private Button failedFirstOrderButton;
	private Button passedFirstOrderButton;
	private ResultInput input;
	private String testResult;
	
	/**
	 * Constructor
	 */
	public TestResultViewer() {
		super();
	}

	/**
	 * Creates content
	 */
	protected void createPages() {
		if (browserComposite == null) {
			browserComposite = new Composite(getContainer(), SWT.BORDER);

			GridLayout layout = new GridLayout();
			layout.numColumns = 1;
			layout.marginTop = 0;
			layout.marginBottom = 0;
			layout.marginLeft = 0;
			layout.marginRight = 0;
			browserComposite.setLayout(layout);
		}

		if (browser == null) {
			Group orderButtonsGroup = new Group(browserComposite, SWT.NONE);
			
			GridLayout buttonsLayout = new GridLayout();
			buttonsLayout.marginTop = 0;
			buttonsLayout.marginBottom = 0;
			buttonsLayout.numColumns = 4;
			orderButtonsGroup.setLayout(buttonsLayout);
			
			GridData orderButtonsGroupGridData = new GridData( GridData.FILL_HORIZONTAL );
			orderButtonsGroup.setLayoutData(orderButtonsGroupGridData);
			
			GridData labelGridData = new GridData(GridData.BEGINNING);
			
			Label displayOrderLabel = new Label(orderButtonsGroup, SWT.NONE);
			displayOrderLabel.setLayoutData(labelGridData);
			displayOrderLabel.setText("Results display order:");
			
			GridData buttonsGroupGridData = new GridData( GridData.FILL_HORIZONTAL );
			
			executionOrderButton = new Button(orderButtonsGroup, SWT.RADIO);
			executionOrderButton.setLayoutData(buttonsGroupGridData);
			executionOrderButton.setText("execution order");
			executionOrderButton.setSelection(true);
			executionOrderButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					browser.setText(testResult);
				}
			});
			
			failedFirstOrderButton = new Button(orderButtonsGroup, SWT.RADIO);
			failedFirstOrderButton.setLayoutData(buttonsGroupGridData);
			failedFirstOrderButton.setText("failed first");
			failedFirstOrderButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					browser.setText(TestcaseDisplayOrderModificator.moveFailedOrPassedToBeginning
							(testResult, true));
				}
			});
			
			passedFirstOrderButton = new Button(orderButtonsGroup, SWT.RADIO);
			passedFirstOrderButton.setLayoutData(buttonsGroupGridData);
			passedFirstOrderButton.setText("passed first");
			passedFirstOrderButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					browser.setText(TestcaseDisplayOrderModificator.moveFailedOrPassedToBeginning
							(testResult, false));
				}
			});
			
			browser = new Browser(browserComposite, SWT.NONE);
			
			GridData browserGridData = new GridData(GridData.FILL_HORIZONTAL |
					GridData.FILL_VERTICAL);
			browser.setLayoutData(browserGridData);
			browser.setText(testResult);
			
			addPage(browserComposite);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorPart#doSave()
	 */
	public void doSave(IProgressMonitor monitor) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorPart#doSaveAs()
	 */
	public void doSaveAs() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorPart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Returns editor id
	 * 
	 * @return editor id
	 */
	public static String getEditorId() {
		return EDITOR_ID;
	}
	
	@Override
	public void setInput(IEditorInput editorInput) {
		input = (ResultInput)editorInput;
		BufferedReader in;
		testResult = null;
		try {
			in = new BufferedReader(new InputStreamReader(input.getStorage()
					.getContents()));
			StringBuffer buffer = new StringBuffer();
			String line = null;

			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			testResult = buffer.toString();

		} catch (CoreException ex) {
			LogExceptionHandler.log(ex.getMessage());
		} catch (IOException ex) {
			LogExceptionHandler.log(ex.getMessage());
		}
		if (browser != null && !browser.isDisposed()) {
			if(executionOrderButton != null && executionOrderButton.getSelection() == true) {
				browser.setText(testResult);
			} else if (failedFirstOrderButton != null && failedFirstOrderButton.getSelection() == true) {
				browser.setText(TestcaseDisplayOrderModificator.moveFailedOrPassedToBeginning
						(testResult, true));
			} else if (passedFirstOrderButton != null && passedFirstOrderButton.getSelection() == true) {
				browser.setText(TestcaseDisplayOrderModificator.moveFailedOrPassedToBeginning
						(testResult, false));
			}
		}
		
		super.setInput(editorInput);
	}
	
	@Override
	public void dispose() {
		browser.dispose();
		browser = null;
		browserComposite.dispose();
		browserComposite = null;
		super.dispose();
	}
}
