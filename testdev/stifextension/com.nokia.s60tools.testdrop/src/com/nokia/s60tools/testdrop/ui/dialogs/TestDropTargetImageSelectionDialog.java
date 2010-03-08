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


package com.nokia.s60tools.testdrop.ui.dialogs;

import java.io.File;
import java.util.List;
import java.util.Vector;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.nokia.s60tools.testdrop.resources.TestDropHelpContextIDs;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.ui.dialogs.model.DialogModel;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Target device image selection dialog
 * 
 * @see org.eclipse.jface.dialogs.Dialog
 */
public class TestDropTargetImageSelectionDialog extends Dialog {

	private final String[] FILTER_IMAGES_NAMES = { Messages
			.getString("TestDropTargetImageSelectionDialog.allFiles") }; 
	private final String[] FILTER_IMAGES_EXTS = { "*.*" }; 
	private final String dialogTitle;
	private final int widthHint;
	private final int heightHint;
	private final String SEND_BUTTON_TEXT = Messages
			.getString("TestDropTargetImageSelectionDialog.sendButton"); 
	private final String SAVE_BUTTON_TEXT = Messages
			.getString("TestDropTargetImageSelectionDialog.saveButton"); 
	private final String TARGET_MASTER_IMAGE_SELECTION_LABEL = Messages
			.getString("TestDropTargetImageSelectionDialog.listOfTheTargetDevices"); 
	private final String addButtonText = Messages
			.getString("TestDropTargetImageSelectionDialog.browseButton"); 
	private final String removeButtonText = Messages
			.getString("TestDropTargetImageSelectionDialog.removeButton"); 
	private DialogModel dialogModel;
	private ListViewer masterListViewer;
	private Button addButton;
	private Button removeButton;
	private List<File> masterImages;
	private boolean staredFromPrederence;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 *            Parent shell.
	 * @param dialogModel
	 *            contain data for constructs the dialog
	 * @param listBoxContent
	 *            Content to be shown in the dialog.
	 * @param isResizable
	 *            If true, dialog will be resizable.
	 * @param widthHint
	 *            Preferred width for the dialog.
	 * @param heightHint
	 *            Preferred height for the dialog.
	 */

	public TestDropTargetImageSelectionDialog(Shell parentShell,
			String dialogTitle, DialogModel dialogModel, boolean isResizable,
			int widthHint, int heightHint, boolean staredFromPrederence) {

		super(parentShell);
		if (isResizable) {
			setShellStyle(getShellStyle() | SWT.RESIZE);
		}
		this.dialogTitle = dialogTitle;
		this.dialogModel = dialogModel;
		this.widthHint = widthHint;
		this.heightHint = heightHint;
		masterImages = new Vector<File>();
		this.staredFromPrederence = staredFromPrederence;

	}

	/**
	 * Creates dialog area
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {

		Composite dialogAreaComposite = (Composite) super
				.createDialogArea(parent);

		createMasterImageDialogArea(dialogAreaComposite);

		Composite fillComposite = new Composite(dialogAreaComposite, SWT.NONE);
		GridLayout glFill = new GridLayout(1, false);
		GridData gdFill = new GridData(GridData.FILL_BOTH);
		fillComposite.setLayout(glFill);
		fillComposite.setLayoutData(gdFill);

		return dialogAreaComposite;
	}

	/**
	 * Creates master device image selection dialog area
	 * 
	 * @param dialogAreaComposite
	 *            the dialogAreaComposite contains the dialog area
	 */
	private void createMasterImageDialogArea(Composite dialogAreaComposite) {
		final int cols = 2;
		GridLayout gdl = new GridLayout(cols, false);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		dialogAreaComposite.setLayout(gdl);
		dialogAreaComposite.setLayoutData(gd);

		GridData gdLabel = new GridData(GridData.FILL_HORIZONTAL);
		gdLabel.horizontalSpan = 2;

		Label masterDeviceSelectionLabel = new Label(dialogAreaComposite,
				SWT.LEFT);
		masterDeviceSelectionLabel.setLayoutData(gdLabel);
		masterDeviceSelectionLabel.setText(String.format(
				TARGET_MASTER_IMAGE_SELECTION_LABEL, dialogModel
						.getSelectedMasterDevice().getAlias()));

		GridData gd2 = new GridData(GridData.FILL_BOTH);

		masterListViewer = new ListViewer(dialogAreaComposite);
		masterListViewer.getControl().setLayoutData(gd2);
		masterListViewer.setContentProvider(new IStructuredContentProvider() {
			@SuppressWarnings("unchecked")
			public Object[] getElements(Object inputElement) {
				List<IPath> paths = (List<IPath>) inputElement;
				return paths.toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		});

		if (dialogModel.getSelectedMasterDevice().getImages() != null
				&& !dialogModel.getSelectedMasterDevice().getImages().isEmpty()) {
			masterImages = dialogModel.getSelectedMasterDevice().getImages();
		}

		masterListViewer.setInput(masterImages);
		masterListViewer.setLabelProvider(new LabelProvider() {

			public Image getImage(Object element) {
				return null;
			}

			public String getText(Object file) {
				return ((File) file).getName();
			}
		});

		Composite buttonComposite = new Composite(dialogAreaComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);

		GridData gridData = new GridData(GridData.FILL_VERTICAL);
		buttonComposite.setLayout(gridLayout);
		buttonComposite.setLayoutData(gridData);
		GridData buttonGridData = new GridData(GridData.FILL_HORIZONTAL);
		addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText(addButtonText);
		addButton.setLayoutData(buttonGridData);
		removeButton = new Button(buttonComposite, SWT.PUSH);
		removeButton.setText(removeButtonText);
		removeButton.setLayoutData(buttonGridData);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
				dlg.setFilterNames(FILTER_IMAGES_NAMES);
				dlg.setFilterExtensions(FILTER_IMAGES_EXTS);
				String imageFile = dlg.open();
				if (imageFile != null) {
					masterImages.add(new File(imageFile));
				}
				masterListViewer.update(masterImages, null);
				masterListViewer.refresh(false);
			}
		});

		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) masterListViewer
						.getSelection();
				File image = (File) selection.getFirstElement();
				if (image == null) {
					if (masterImages.size() != 0) {
						LogExceptionHandler
								.showNotifyDialog(Messages
										.getString("TestDropTargetImageSelectionDialog.selectDeviceImageFirstException")); 
					} else {
						LogExceptionHandler
								.showNotifyDialog(Messages
										.getString("TestDropTargetImageSelectionDialog.deviceImageListIsEmptyException")); 
					}
					return;
				}
				masterImages.remove(image);
				masterListViewer.update(masterImages, null);
				masterListViewer.refresh(false);
			}
		});

		// set context-sensitive help
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						removeButton,
						TestDropHelpContextIDs.TESTDROP_IMAGE_SELECTION_DIALOG_REMOVE_BUTTON);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						addButton,
						TestDropHelpContextIDs.TESTDROP_IMAGE_SELECTION_DIALOG_BROWSE_BUTTON);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				masterListViewer.getControl(),
				TestDropHelpContextIDs.TESTDROP_IMAGE_SELECTION_DIALOG_LIST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (dialogTitle != null)
			shell.setText(dialogTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed(org.eclipse.swt.widgets.Composite)
	 */
	protected void okPressed() {
		if (staredFromPrederence) {
			dialogModel.getSelectedMasterDevice().setImages(masterImages);
		} else {
			StartUp.sendTestDrop(masterImages, true);
		}

		this.close();
		super.okPressed();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed(org.eclipse.swt.widgets.Composite)
	 */
	protected void cancelPressed() {
		if (!staredFromPrederence) {
			this.close();
			StartUp.startTargetDialog(null, dialogModel);
		}
		super.cancelPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite) /
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = null;
		if (staredFromPrederence) {
			button = createButton(parent, IDialogConstants.OK_ID,
					SAVE_BUTTON_TEXT, true);
		} else {
			button = createButton(parent, IDialogConstants.OK_ID,
					SEND_BUTTON_TEXT, true);
		}

		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// set context-sensitive help
		if (button != null) {
			if (staredFromPrederence) {
				PlatformUI
						.getWorkbench()
						.getHelpSystem()
						.setHelp(
								button,
								TestDropHelpContextIDs.TESTDROP_PREFERENCES_DEFAULT_TARGET_DEVICE_IMAGE_SELECTION_DIALOG_SAVE_BUTTON);
				PlatformUI
						.getWorkbench()
						.getHelpSystem()
						.setHelp(
								cancelButton,
								TestDropHelpContextIDs.TESTDROP_PREFERENCES_DEFAULT_TARGET_DEVICE_IMAGE_SELECTION_DIALOG_CANCEL_BUTTON);

			} else {
				PlatformUI
						.getWorkbench()
						.getHelpSystem()
						.setHelp(
								button,
								TestDropHelpContextIDs.TESTDROP_IMAGE_SELECTION_DIALOG_SEND_BUTTON);
				PlatformUI
						.getWorkbench()
						.getHelpSystem()
						.setHelp(
								cancelButton,
								TestDropHelpContextIDs.TESTDROP_IMAGE_SELECTION_DIALOG_CANCEL_BUTTON);

			}
		}
	}
}
