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

public class MethodEditDialog extends Dialog {
	private boolean result = false;
	private String iTitle;
	private MethodItem iTestMethod;
	private ClassItem iTestClass;
	private Text iTextTestedCommandName;
	private Button iBtnOK;

	private static final int NORMAL = 0;

	private static final int INVALID = -1;

	private static final int DUPLICATE = -2;

	private static final String NEWMETHOD = "Method";

	public MethodEditDialog(Shell parent, String title, MethodItem testmethod) {
		super(parent, 0);
		iTitle = title;
		if (testmethod != null) {
			iTestMethod = testmethod;
			iTestClass = (ClassItem) iTestMethod.getParent();
		} else {
			throw new IllegalArgumentException(
					"Parameter testmethod can not be null.");
		}
	}

	public MethodEditDialog(Shell parent, String title, ClassItem parentClass) {
		super(parent, 0);
		iTitle = title;
		if (parentClass != null) {
			iTestClass = parentClass;
			int counter = 1;
			while (checkDuplicate(NEWMETHOD + Integer.toString(counter)) != NORMAL) {
				counter++;
			}
			iTestMethod = new MethodItem(NEWMETHOD + Integer.toString(counter),
					parentClass);
		} else {
			throw new IllegalArgumentException(
					"Parameter parentClass can not be null.");
		}
	}

	private void init(final Shell shell) {
		shell.setText(iTitle);
		shell.setLayout(new GridLayout(3, true));
		Label label = new Label(shell, SWT.None);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);
		label.setText(Messages.getString("MethodDialog.TestedCommandName"));

		iTextTestedCommandName = new Text(shell, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		iTextTestedCommandName.setLayoutData(data);
		iTextTestedCommandName.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.character == ' ') {
					event.doit = false;
				}
				if (event.keyCode == SWT.CR || event.keyCode == SWT.KEYPAD_CR) {
					iBtnOK.notifyListeners(SWT.Selection, null);
				}
			}
		});

		label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);
		label.setText(Messages.getString("MethodDialog.TestMethodName"));

		Text lTextTestMethodName = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		lTextTestMethodName.setLayoutData(data);
		iTextTestedCommandName.setData(lTextTestMethodName);
		iTextTestedCommandName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String text = ((Text) event.widget).getText();
				((Text) event.widget.getData()).setText(TemplateUtil.getMethodWrapperName(text));
			}
		});
		iTextTestedCommandName.setText(iTestMethod.getName());
		iTextTestedCommandName.selectAll();

		label = new Label(shell, SWT.None);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button lSynchronouBtn = new Button(shell, SWT.RADIO);
		lSynchronouBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lSynchronouBtn.setText(Messages.getString("MethodDialog.Synchronous"));
		lSynchronouBtn.setSelection(!iTestMethod.isAsync());

		final Button lAsynchronousBtn = new Button(shell, SWT.RADIO);
		lAsynchronousBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lAsynchronousBtn.setText(Messages
				.getString("MethodDialog.Asynchronous"));
		lAsynchronousBtn.setSelection(iTestMethod.isAsync());

		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		label.setLayoutData(data);

		label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);

		iBtnOK = new Button(shell, SWT.PUSH);
		iBtnOK.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iBtnOK.setText(Messages.getString("Dialog.OK"));
		iBtnOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String lTestedCommandName = iTextTestedCommandName.getText();
				int lCheckRlt = validation(lTestedCommandName);
				if (lCheckRlt == NORMAL) {
					result = true;
					iTestMethod.setName(lTestedCommandName);
					iTestMethod.setParameter(true);
					iTestMethod.setAsync(lAsynchronousBtn.getSelection());
					((Button) event.widget).getShell().dispose();
				} else {
					if (lCheckRlt == DUPLICATE) {
						MessageDialog.openError(shell, Messages
								.getString("MethodDialog.Error"), Messages
								.getString("MethodDialog.DuplicateMethodName",
										lTestedCommandName));
					} else {
						MessageDialog.openError(shell, Messages
								.getString("MethodDialog.Error"), Messages
								.getString("MethodDialog.InvalidMethodName",
										lTestedCommandName));
					}
				}
			}
		});

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

	public boolean open() {
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		init(shell);
		shell.pack();

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

	private int validation(String name) {
		if (name.matches("^[a-zA-Z~_]\\w*")) {
			return checkDuplicate(name);
		}
		return INVALID;
	}

	private int checkDuplicate(String name) {
		Set<MethodItem> lMethodSet = (Set<MethodItem>) iTestClass.getChildren();
		for (MethodItem item : lMethodSet) {
			if (item != iTestMethod) {
				if (item.getName().equals(name)) {
					return DUPLICATE;
				}
			}
		}
		return NORMAL;
	}

	public MethodItem getMethodItem() {
		return iTestMethod;
	}
}
