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

package com.nokia.testfw.codegen.ui.wizard;

import java.util.Set;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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

import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.model.INode;
import com.nokia.testfw.codegen.model.MethodNodeImpl;
import com.nokia.testfw.codegen.model.ClassNodeImpl;

public class MethodEditDialog extends Dialog {
	private static final Image ERROR_IMG = JFaceResources
			.getImage("dialog_message_error_image");

	private boolean result = false;
	private String iTitle;
	private MethodNodeImpl iTestMethod;
	private ClassNodeImpl iTestClass;
	private Text iTestMethodNameText;
	private Button iBtnOK;
	private CLabel iMessageLabel;
	private String iMethodName;

	private static final String NEWMETHOD = "TestMethod";

	public MethodEditDialog(Shell parent, String title,
			MethodNodeImpl testmethod) {
		super(parent, 0);
		iTitle = title;
		if (testmethod != null) {
			iTestMethod = testmethod;
			iTestClass = (ClassNodeImpl) iTestMethod.getParent();
		} else {
			throw new IllegalArgumentException(
					"Parameter testmethod can not be null.");
		}
	}

	public MethodEditDialog(Shell parent, String title,
			ClassNodeImpl parentClass) {
		super(parent, 0);
		iTitle = title;
		if (parentClass != null) {
			iTestClass = parentClass;
			int counter = 1;
			while (isDuplicate(NEWMETHOD + Integer.toString(counter))) {
				counter++;
			}
			iTestMethod = new MethodNodeImpl(NEWMETHOD
					+ Integer.toString(counter), parentClass);
		} else {
			throw new IllegalArgumentException(
					"Parameter parentClass can not be null.");
		}
	}

	private void init(final Shell shell) {
		shell.setText(iTitle);
		shell.setLayout(new GridLayout(4, true));
		Label label = new Label(shell, SWT.None);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		label.setLayoutData(data);
		label.setText(Messages.getString("MethodDialog.MethodName"));

		iTestMethodNameText = new Text(shell, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		iTestMethodNameText.setLayoutData(data);
		iTestMethodNameText.setText(iTestMethod.getName());
		iTestMethodNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				canComplete(validation());
			}
		});
		iTestMethodNameText.selectAll();

		// Message Label
		iMessageLabel = new CLabel(shell, SWT.NONE);
		GridData messageLabelData = new GridData(GridData.FILL_HORIZONTAL);
		messageLabelData.horizontalSpan = 4;
		iMessageLabel.setLayoutData(messageLabelData);

		label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		iBtnOK = new Button(shell, SWT.PUSH);
		iBtnOK.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iBtnOK.setText(Messages.getString("Dialog.OK"));
		iBtnOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				result = true;
				iTestMethod.setName(iMethodName);
				((Button) event.widget).getShell().dispose();
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

		canComplete(validation());
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

	private boolean isDuplicate(String name) {
		Set<INode> lMethodSet = (Set<INode>) iTestClass.getChildren();
		for (INode item : lMethodSet) {
			if (item != iTestMethod) {
				if (item.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validation() {
		String text = iTestMethodNameText.getText();
		if (!text.matches("^[a-zA-Z~_]\\w*")) {
			showMessage(Messages.getString("MethodDialog.InvalidMethodName",
					text));
			return false;
		}
		if (isDuplicate(text)) {
			showMessage(Messages.getString("MethodDialog.DuplicateMethodName",
					text));
			return false;
		}
		iMethodName = text;
		showMessage(null);
		return true;
	}

	private void canComplete(boolean validate) {
		if (validate) {
			iBtnOK.setEnabled(true);
		} else {
			iBtnOK.setEnabled(false);
		}
	}

	private void showMessage(String message) {
		if (message == null) {
			iMessageLabel.setImage(null);
			iMessageLabel.setText(null);
		} else {
			iMessageLabel.setImage(ERROR_IMG);
			iMessageLabel.setText(message);
		}
	}

	public MethodNodeImpl getMethodItem() {
		return iTestMethod;
	}
}
