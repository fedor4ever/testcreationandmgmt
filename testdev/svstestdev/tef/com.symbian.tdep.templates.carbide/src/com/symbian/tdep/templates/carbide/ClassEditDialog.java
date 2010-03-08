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


package com.symbian.tdep.templates.carbide;

import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.symbian.tdep.templates.carbide.util.TemplateUtil;

// Edit Class Dialog
public class ClassEditDialog extends Dialog {
	// Dialog title
	private String iTitle;
	// Edited class
	private ClassItem iTestClass;
	// Parent project
	private ProjectItem iTestProject;
	// Execution result
	boolean result = false;
	// Widget of Wrapped Class
	private Text iTextWrappedClass;
	// OK Button
	private Button iBtnOK;

	// Normal class name
	private static final int NORMAL = 0;
	// Invalid class name
	private static final int INVALID = -1;
	// Duplicate class name
	private static final int DUPLICATE = -2;

	// Default new class name prefix
	private static final String NEWCLASS = "CTestData";

	// Constructor
	public ClassEditDialog(Shell parent, String title, ClassItem testclass) {
		super(parent, 0);
		iTitle = title;
		if (testclass != null) {
			iTestClass = testclass;
			iTestProject = (ProjectItem) iTestClass.getParent();
		} else {
			throw new IllegalArgumentException(
					"Parameter testclass can not be null.");
		}
	}

	// Constructor
	public ClassEditDialog(Shell parent, String title, ProjectItem parentProject) {
		super(parent, 0);
		iTitle = title;
		if (parentProject != null) {
			iTestProject = parentProject;
			int counter = 1;
			while (checkDuplicate(NEWCLASS + Integer.toString(counter)) != NORMAL) {
				counter++;
			}
			iTestClass = new ClassItem(NEWCLASS + Integer.toString(counter),
					parentProject);
		} else {
			throw new IllegalArgumentException(
					"Parameter parentProject can not be null.");
		}
	}

	// Initial dialog
	private void init(final Shell shell) {
		// Dialog title and layout
		shell.setText(iTitle);
		shell.setLayout(new GridLayout(3, true));

		// Widgets of Wrapped Class
		Label label = new Label(shell, SWT.None);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);
		label.setText(Messages.getString("ClassDialog.WrappedObjectName"));

		iTextWrappedClass = new Text(shell, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		iTextWrappedClass.setLayoutData(data);
		iTextWrappedClass.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.character == ' ') {
					event.doit = false;
				}
				if (event.keyCode == SWT.CR || event.keyCode == SWT.KEYPAD_CR) {
					iBtnOK.notifyListeners(SWT.Selection, null);
				}
			}
		});

		// Widgets of Wrapper Class
		label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);
		label.setText(Messages.getString("ClassDialog.WrapperClassName"));

		Text textWrapperClass = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		textWrapperClass.setLayoutData(data);
		iTextWrappedClass.setData(textWrapperClass);
		iTextWrappedClass.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String text = ((Text) event.widget).getText();
				((Text) event.widget.getData()).setText(TemplateUtil.getClassWrapperName(text));
			}
		});
		iTextWrappedClass.setText(iTestClass.getName());
		iTextWrappedClass.selectAll();

		// Separator
		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		label.setLayoutData(data);

		// Place holder
		label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);

		// OK Button
		iBtnOK = new Button(shell, SWT.PUSH);
		iBtnOK.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iBtnOK.setText(Messages.getString("Dialog.OK"));
		iBtnOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String lWrappedObjectName = iTextWrappedClass.getText();
				int lCheckRlt = validation(lWrappedObjectName);
				if (lCheckRlt == NORMAL) {
					result = true;
					iTestClass.setName(lWrappedObjectName);
					((Button) event.widget).getShell().dispose();
				} else {
					if (lCheckRlt == DUPLICATE) {
						MessageDialog.openError(shell, Messages
								.getString("ClassDialog.Error"), Messages
								.getString("ClassDialog.DuplicateClassName",
										lWrappedObjectName));
					} else {
						MessageDialog.openError(shell, Messages
								.getString("ClassDialog.Error"), Messages
								.getString("ClassDialog.InvalidClassName",
										lWrappedObjectName));
					}
				}
			}
		});

		// Cancle Button
		Button lBtnCancel = new Button(shell, SWT.PUSH);
		lBtnCancel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lBtnCancel.setText(Messages.getString("Dialog.Cancel"));
		lBtnCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				result = false;
				((Button) event.widget).getShell().dispose();
			}
		});
	}

	// Open dialog
	public boolean open() {
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		init(shell);
		shell.pack();

		// Reside dialog location to parent shell center.
		Rectangle lParentBounds = parent.getBounds();
		Point lDialogSize = shell.getSize();
		shell.setLocation(lParentBounds.x
				+ (lParentBounds.width - lDialogSize.x) / 2, lParentBounds.y
				+ (lParentBounds.height - lDialogSize.y) / 2);

		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	// Validate class name
	private int validation(String name) {
		if (name.matches("^[_a-zA-Z]\\w*")) {
			return checkDuplicate(name);
		}
		return INVALID;
	}

	// Check duplication
	private int checkDuplicate(String name) {
		Set<ClassItem> lClassSet = iTestProject.getChildren();
		for (ClassItem item : lClassSet) {
			if (item != iTestClass) {
				if (item.getName().equals(name)) {
					return DUPLICATE;
				}
			}
		}
		return NORMAL;
	}

	// Get editing class
	public ClassItem getClassItem() {
		return iTestClass;
	}
}
