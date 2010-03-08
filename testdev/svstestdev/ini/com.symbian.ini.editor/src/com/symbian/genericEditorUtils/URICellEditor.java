/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies).
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


package com.symbian.genericEditorUtils;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

public class URICellEditor extends TextCellEditor {

	private URIStyle iStyle = URIStyle.URI;

	private Composite iComposite = null;

	private Text iText = null;

	private Button iBrowseButton = null;

	private ILabelProvider iLabelProvider = null;

	private ITreeContentProvider iContentProvider = null;

	private Object iInput = null;

	private String iTitle = "Selection Dialog";

	private String iMessage = "Please select an item.";

	private UriValidator iValidator;

	private Object iSelection;

	/** Creates a URI editor, using the EMF Resource Dialog.
	 * 
	 * @param parent
	 * @param aUValidator
	 */
	public URICellEditor(Composite parent, UriValidator aUValidator) {
		iStyle = URIStyle.URI;
		iValidator = aUValidator;

		create(parent);
	}

	/** Creates a URI editor, using the SWT directory selection dialog box.
	 * 
	 * @param parent
	 * @param aTitle
	 * @param aMessage
	 * @param aUriValidator
	 */
	public URICellEditor(Composite parent, String aTitle, String aMessage, UriValidator aUriValidator) {
		iStyle = URIStyle.DIR;
		iTitle = aTitle;
		iMessage = aMessage;
		iValidator = aUriValidator;

		create(parent);
	}

	/** Creates a URI editor, using a tree selection dialog box.
	 * 
	 * @param parent
	 * @param aContentProvider
	 * @param aLabelProvider
	 * @param aInput
	 * @param aTitle
	 * @param aMessage
	 * @param aUriValidator
	 */
	public URICellEditor(Composite parent, ITreeContentProvider aContentProvider, ILabelProvider aLabelProvider,
			Object aInput, String aTitle, String aMessage, UriValidator aUriValidator) {
		iStyle = URIStyle.EMF;
		iTitle = aTitle;
		iMessage = aMessage;
		iLabelProvider = aLabelProvider;
		iContentProvider = aContentProvider;
		iInput = aInput;
		iValidator = aUriValidator;

		create(parent);

	}
	
	public void deactivate() {
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#getStyle()
	 */
	@Override
	public int getStyle() {
		int lTextStyle = SWT.READ_ONLY;
		if (iStyle == URIStyle.DIR || iStyle == URIStyle.URI) {
			lTextStyle = SWT.BORDER;
		}
		
		return lTextStyle;
	}

	@Override
	protected Control createControl(Composite parent) {
		iComposite = new Composite(parent, SWT.NULL);
		iComposite.setLayout(new GridLayout(2, false));
		iComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		iText = (Text) super.createControl(iComposite);
		
		iText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL));
		iText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		iBrowseButton = new Button(iComposite, SWT.DOWN);
		iBrowseButton.setText("Browse");

		iBrowseButton.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (e.character == '\u001b') { // Escape
					fireCancelEditor();
				}
			}

		});

		iBrowseButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent aEvent) {

				Object lResult = null;
				if (iStyle == URIStyle.EMF
						&& iLabelProvider != null
						&& iContentProvider != null
						&& iInput != null) {
					lResult = createTreeSelectionDialog();
				} else if (iStyle == URIStyle.DIR) {
					lResult = createDirDialog();
				} else {
					lResult = createResourceDialog();
				}

				if (lResult != null) {
					boolean newValidState = isCorrect(lResult);
					if (newValidState) {
						markDirty();
						doSetValue(lResult);
						valueChanged(true, isCorrect(lResult));
					} else {
						// try to insert the current value into the error message.
						setErrorMessage(MessageFormat.format(getErrorMessage(),
								new Object[] { lResult.toString() }));
					}
					fireApplyEditorValue();
				}
			}
		});

		return iComposite;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object aValue) {
		iSelection = aValue;
		if (aValue != null && !aValue.toString().equals("")) {
			if (aValue instanceof Resource) {
				Resource lResource = (Resource) aValue;
				aValue = new File(lResource.getURI().path()).getName();
			}
			super.doSetValue(aValue.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		if (iSelection != null) {
			return iSelection;
		}
		return super.doGetValue();
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private Object createResourceDialog() {
		ResourceDialog lDialog = new ResourceDialog(iComposite.getShell(),
				iTitle, SWT.OPEN | SWT.SINGLE);
		if (lDialog != null) {
			lDialog.setBlockOnOpen(true);
			int lReturnCode = lDialog.open();
			
			if (lReturnCode == ResourceDialog.OK) {
				List<URI> lUriList = lDialog.getURIs();
	
				if (lUriList != null && lUriList.size() == 1) {
					if (iValidator != null) {
						Object lValidate = iValidator.validate(lUriList.get(0));
						
						if (lValidate == null) {
							MessageBox lWarningBox = new MessageBox(iComposite.getShell(), SWT.ICON_ERROR);
							lWarningBox.setText(iValidator.getErrorTitle());
							lWarningBox.setMessage(iValidator.getErrorMessage());
							lWarningBox.open();
							
							return createResourceDialog();
						}
						
						return lValidate;
					}
					
					return lUriList.get(0).path();
				}
			}
		}

		return null;
	}

	/**
	 * 
	 */
	private Object createDirDialog() {
		DirectoryDialog lDialog = new DirectoryDialog(iComposite.getShell());
		if (lDialog != null) {
			lDialog.setMessage(iMessage);
			lDialog.setText(iTitle);
			String lDir = lDialog.open();

			if (iValidator != null && lDir != null && !lDir.equalsIgnoreCase("")) {
				Object lValidate = iValidator.validate(lDir);
				
				if (lValidate == null) {
					MessageBox lWarningBox = new MessageBox(iComposite.getShell(), SWT.ICON_ERROR);
					lWarningBox.setText(iValidator.getErrorTitle());
					lWarningBox.setMessage(iValidator.getErrorMessage());
					lWarningBox.open();
					
					return createDirDialog();
				}
				
				return lValidate;
			}
			return lDir;
		}

		return null;
	}

	/**
	 * 
	 */
	private Object createTreeSelectionDialog() {
		ElementTreeSelectionDialog lDialog = new ElementTreeSelectionDialog(
				iComposite.getShell(), iLabelProvider, iContentProvider);
		if (lDialog != null) {
			lDialog.setInput(iInput);
			lDialog.setBlockOnOpen(true);
			lDialog.setTitle(iTitle);
			lDialog.setMessage(iMessage);
			int lReturnCode = lDialog.open();
			
			if (lReturnCode == ElementTreeSelectionDialog.OK) {
				Object lFirstResult = lDialog.getFirstResult();
				
				if (iValidator != null && lFirstResult != null) {
					Object lValidate = iValidator.validate(lFirstResult);
					
					if (lValidate == null) {
						MessageBox lWarningBox = new MessageBox(iComposite.getShell(), SWT.ICON_ERROR);
						lWarningBox.setText(iValidator.getErrorTitle());
						lWarningBox.setMessage(iValidator.getErrorMessage());
						lWarningBox.open();
						
						return createTreeSelectionDialog();
					}
					
					return lValidate;
				}
	
				return lFirstResult;
			}
		}

		return null;
	}
	
	/**
	 * @author Development Tools
	 *
	 */
	private enum URIStyle {
		URI, EMF, DIR;
	}

}
