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

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.ui.CElementLabelProvider;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.testfw.codegen.model.ClassNodeImpl;
import com.nokia.testfw.codegen.model.IMethodNode;
import com.nokia.testfw.codegen.model.INode;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.ui.parser.Parser;
import com.nokia.testfw.codegen.ui.parser.model.IUINode;
import com.nokia.testfw.codegen.ui.parser.model.UIProjectNode;

public class GenTestMethodDialog extends Dialog {
	private boolean result = false;
	private Button iBtnOK;
	private Text iHeaderText;
	private String iHeaderPath;
	private String iLib;
	private Text iLibText;
	private IProject iProject;
	private ContainerCheckedTreeViewer iCheckboxTreeViewer;
	private Composite iComposite;
	private UIProjectNode iHeaderNode;
	private ISymbianSDK iSDK;
	private CLabel iMessageLabel;
	private static final Image ERROR_IMG = JFaceResources
			.getImage("dialog_message_error_image");

	public GenTestMethodDialog(Shell parent, IProject project, ISymbianSDK sdk) {
		super(parent, 0);
		iProject = project;
		iSDK = sdk;
	}

	private void createControl(final Shell shell) {

		shell.setText("Generate Test Case From Header File");
		shell.setLayout(new GridLayout(4, true));

		// Header
		Label lHeaderLabel = new Label(shell, SWT.NONE);
		lHeaderLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lHeaderLabel.setText("Header File:");

		iHeaderText = new Text(shell, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		iHeaderText.setLayoutData(data);
		iHeaderText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String filename = iHeaderText.getText();
				File headerFile = new File(filename);
				if (headerFile.exists()) {
					showAST(new Path(filename));
				} else {
					iCheckboxTreeViewer.setInput(null);
				}
				checkComplete();
			}
		});

		Button lHeaderButton = new Button(shell, SWT.PUSH);
		lHeaderButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lHeaderButton.setText("&Browse...");
		lHeaderButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.h" });
				dialog.setFilterPath(iSDK.getIncludePath().toOSString());
				String filename = dialog.open();
				if (filename != null) {
					iHeaderText.setText(filename);
				}
			}
		});

		// Lib
		Label lLibLabel = new Label(shell, SWT.None);
		lLibLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lLibLabel.setText("Lib File:");

		iLibText = new Text(shell, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		iLibText.setLayoutData(data);
		iLibText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				checkComplete();
			}
		});

		Button lLibButton = new Button(shell, SWT.PUSH);
		lLibButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lLibButton.setText("&Browse...");
		lLibButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.lib" });
				dialog.setFilterPath(iSDK.getReleaseRoot().toOSString());
				String filename = dialog.open();
				if (filename != null) {
					iLibText.setText(new Path(filename).lastSegment());
				}
			}
		});

		iComposite = new Composite(shell, SWT.None);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 4;
		iComposite.setLayoutData(data);
		iComposite.setLayout(new FillLayout());

		iCheckboxTreeViewer = new ContainerCheckedTreeViewer(iComposite,
				SWT.BORDER);

		// Content Provider
		iCheckboxTreeViewer.setContentProvider(new ITreeContentProvider() {
			public Object[] getChildren(Object aObject) {
				return ((INode) aObject).getChildren().toArray();
			}

			public Object getParent(Object aObject) {
				return ((INode) aObject).getParent();
			}

			public boolean hasChildren(Object aObject) {
				return !(aObject instanceof IMethodNode);
			}

			public Object[] getElements(Object aObject) {
				if (aObject instanceof UIProjectNode) {
					return ((UIProjectNode) aObject).getChildren().toArray(
							new IUINode[0]);
				}
				return null;
			}

			public void dispose() {
				// do nothing
			}

			public void inputChanged(Viewer aViewer, Object aOldInput,
					Object aNewInput) {
				// do nothing
			}
		});

		// Label Provider
		iCheckboxTreeViewer.setLabelProvider(new CElementLabelProvider() {
			public Image getImage(Object aObject) {
				if (aObject instanceof IUINode) {
					return super.getImage(((IUINode) aObject).getICElement());
				}
				return null;
			}

			public String getText(Object aObject) {
				if (aObject instanceof IUINode) {
					return super.getText(((IUINode) aObject).getICElement());
				}
				return null;
			}
		});

		// Add Checked Listener
		iCheckboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent aChangedEvent) {
				IUINode lNodeItem = (IUINode) aChangedEvent.getElement();
				lNodeItem.setSelected(aChangedEvent.getChecked());
			}
		});

		// Message Label
		iMessageLabel = new CLabel(shell, SWT.NONE);
		GridData messageLabelData = new GridData(GridData.FILL_HORIZONTAL);
		messageLabelData.horizontalSpan = 4;
		iMessageLabel.setLayoutData(messageLabelData);

		// Space holder
		Label label = new Label(shell, SWT.None);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		// OK
		iBtnOK = new Button(shell, SWT.PUSH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		iBtnOK.setLayoutData(data);
		iBtnOK.setText(Messages.getString("Dialog.OK"));
		iBtnOK.setEnabled(false);
		iBtnOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				result = true;
				shell.dispose();
			}
		});

		// Cancel
		Button lBtnCancel = new Button(shell, SWT.PUSH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		lBtnCancel.setLayoutData(data);
		lBtnCancel.setText(Messages.getString("Dialog.Cancel"));
		lBtnCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				result = false;
				shell.dispose();
			}
		});
	}

	private void showAST(final IPath header) {
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("Starting syntax analysis...",
						IProgressMonitor.UNKNOWN);
				try {
					ICProject cproject = CoreModel.getDefault()
							.create(iProject);

					ITranslationUnit tu = CCorePlugin.getDefault()
							.getCoreModel().createTranslationUnitFrom(cproject,
									header);
					iHeaderNode = Parser
							.parseTranslationUnit(tu, Parser.PUBLIC);
				} catch (Throwable e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			new ProgressMonitorDialog(getParent()).run(true, false, op);
			iCheckboxTreeViewer.setInput(iHeaderNode);
			iCheckboxTreeViewer.setCheckedElements(iHeaderNode.getChildren()
					.toArray(new ClassNodeImpl[0]));
			iCheckboxTreeViewer.expandAll();
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					AbstractTemplateWizard.class.getName(),
					"Exception was thrown while analysis header file.", e
							.getTargetException());
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
			MessageDialog.openError(getParent(),
					"Exception was thrown while analysis header file.", e
							.getTargetException().getMessage());
		}
	}

	public boolean open() {
		Shell parent = getParent();
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		createControl(shell);
		shell.pack();
		shell.setSize(400, 400);

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

	private void showMessage(String message) {
		if (message == null) {
			iMessageLabel.setImage(null);
			iMessageLabel.setText(null);
		} else {
			iMessageLabel.setImage(ERROR_IMG);
			iMessageLabel.setText(message);
		}
	}

	private boolean validate() {
		if (!new File(iHeaderText.getText()).exists()) {
			showMessage("Specified header file \"" + iHeaderText.getText()
					+ "\" doesn't exist.");
			return false;
		}
		iHeaderPath = iHeaderText.getText();

		if (iLibText.getText() == "") {
			showMessage("Must specify lib file.");
			return false;
		}
		iLib = iLibText.getText();
		showMessage(null);
		return true;
	}

	// Check whether complete
	private void checkComplete() {
		if (validate()) {
			iBtnOK.setEnabled(true);
		} else {
			iBtnOK.setEnabled(false);
		}
	}

	public String getHeaderFilePath() {
		return iHeaderPath;
	}

	public UIProjectNode getHeaderNode() {
		return iHeaderNode;
	}

	public String getLib() {
		return iLib;
	}
}
