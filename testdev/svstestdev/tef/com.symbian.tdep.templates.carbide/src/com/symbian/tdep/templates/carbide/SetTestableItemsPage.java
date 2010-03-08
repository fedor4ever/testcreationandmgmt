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

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.nokia.carbide.internal.api.templatewizard.ui.IWizardDataPage;
import com.nokia.carbide.cpp.project.ui.sharedui.NewProjectPage;

/**
 * A customized wizard page for user to set test classes and methods using TEF
 * Block project.
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
final class SetTestableItemsPage extends WizardPage implements IWizardDataPage {

	/**
	 * @author Development Tools
	 * 
	 */
	private final class ClassMethodLabelProvider extends LabelProvider {
		public Image getImage(Object aObject) {
			if (aObject instanceof ITestItem) {
				return ((ITestItem) aObject).getImage();
			}
			return null;
		}

		public String getText(Object aObject) {
			if (aObject instanceof ITestItem) {
				return ((ITestItem) aObject).getTestName();
			}
			return null;
		}
	}

	/**
	 * @author Development Tools
	 * 
	 */
	private final class ClassMethodContentProvider implements
			ITreeContentProvider {

		public Object[] getChildren(Object aObject) {
			if (aObject instanceof ITestItem) {
				if (((ITestItem) aObject).getChildren() != null) {
					return ((ITestItem) aObject).getChildren().toArray();
				}
			}
			return null;
		}

		public Object getParent(Object aObject) {
			if (aObject instanceof ITestItem) {
				return ((ITestItem) aObject).getParent();
			}
			return null;
		}

		public boolean hasChildren(Object aObject) {
			if (getChildren(aObject) != null) {
				return getChildren(aObject).length > 0;
			}
			return false;
		}

		public Object[] getElements(Object aObject) {
			return ((Vector) aObject).toArray();
		}

		public void dispose() {
			// do nothing
		}

		public void inputChanged(Viewer aViewer, Object aOldInput,
				Object aNewInput) {
			// do nothing
		}
	}

	private Tree iTree;
	private TreeViewer iTreeViewer;

	private final ProjectItem iProject;
	private Button iBtnAddClass;
	private Button iBtnEditClass;
	private Button iBtnDeleteClass;
	private Button iBtnAddMethod;
	private Button iBtnEditMethod;
	private Button iBtnDeleteMethod;
	private ITestItem iSelectedItem;
	private NewProjectPage iNewProjectPage;

	// Constants
	private static final String NAME = "Define Test Classes and Methods";
	public static final String PROJECT = "project";

	/**
	 * 
	 */
	public SetTestableItemsPage() {
		super(NAME);
		setTitle(NAME);
		setDescription("Define the wrapper classes and test methods.");

		iProject = new ProjectItem(PROJECT);
	}

	/**
	 * Implement method of IWizardDataPage to provide data to project creation
	 * process.
	 */
	public Map<String, Object> getPageValues() {
		Map<String, Object> lPageValues = new HashMap<String, Object>();
		lPageValues.put(PROJECT, iProject);
		return lPageValues;
	}

	/**
	 * Implement method of IDialogPage to create UI of this wizard page
	 */
	public void createControl(Composite aComposite) {
		initializeDialogUnits(aComposite);

		IWizardPage lpage = this;
		while ((lpage = lpage.getPreviousPage()) != null) {
			if (lpage instanceof NewProjectPage) {
				iNewProjectPage = (NewProjectPage) lpage;
				break;
			}
		}

		final Composite lComposite = new Composite(aComposite, SWT.NONE);
		{
			lComposite.setLayout(new GridLayout(1, false));
			lComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

			setControl(lComposite);

			iTreeViewer = new TreeViewer(lComposite, SWT.BORDER);

			// Content Provider
			iTreeViewer.setContentProvider(new ClassMethodContentProvider());

			// Label Provider
			iTreeViewer.setLabelProvider(new ClassMethodLabelProvider());

			// Selection Changed listener
			iTreeViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							checkButtonState();
						}
					});

			iTreeViewer.setComparator(new ViewerComparator());

			// Double Click Listener
			iTreeViewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					IStructuredSelection selection = (IStructuredSelection) event
							.getSelection();
					iSelectedItem = (ITestItem) selection.getFirstElement();
					if (iSelectedItem instanceof ClassItem) {
						iBtnEditClass.notifyListeners(SWT.Selection, null);
					}
					if (iSelectedItem instanceof MethodItem) {
						iBtnEditMethod.notifyListeners(SWT.Selection, null);
					}
				}
			});
			iTree = iTreeViewer.getTree();
			iTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		}

		final Composite lBtnComposite = new Composite(lComposite, SWT.NONE);
		{
			lBtnComposite.setLayout(new GridLayout(3, true));
			lBtnComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// Add Class Button
			iBtnAddClass = new Button(lBtnComposite, SWT.PUSH);
			iBtnAddClass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnAddClass.setText(Messages.getString("ClassDialog.AddClass"));
			iBtnAddClass.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					try {
						ClassEditDialog dialog = new ClassEditDialog(
								lBtnComposite.getShell(), Messages
										.getString("ClassDialog.AddClassTitle"),
								iProject);

						if (dialog.open()) {
							iProject.addChild(dialog.getClassItem());
							iTreeViewer.add(iProject, dialog.getClassItem());
							iTreeViewer.expandToLevel(dialog.getClassItem(), 0);
						}
						setPageComplete(isPageComplete());
					} catch (Exception e) {
						e.printStackTrace();
						IStatus lStatus = new Status(IStatus.WARNING,
								SetTestableItemsPage.class.getName(),
								"Exception was thrown while adding class.", e);
						TefTemplatesCarbidePlugin.getDefault().getLog().log(
								lStatus);
					}
				}
			});

			// Edit Class Button
			iBtnEditClass = new Button(lBtnComposite, SWT.PUSH);
			iBtnEditClass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnEditClass.setText(Messages.getString("ClassDialog.EditClass"));
			iBtnEditClass.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ClassItem item;
					if (iSelectedItem instanceof ClassItem) {
						item = (ClassItem) iSelectedItem;
					} else {
						item = (ClassItem) iSelectedItem.getParent();
					}
					ClassEditDialog dialog = new ClassEditDialog(lBtnComposite
							.getShell(), Messages
							.getString("ClassDialog.EditClassTitle"), item);
					if (dialog.open()) {
						iTreeViewer.update(item, null);
					}
					setPageComplete(isPageComplete());
				}
			});

			// Delete Class Button
			iBtnDeleteClass = new Button(lBtnComposite, SWT.PUSH);
			iBtnDeleteClass
					.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnDeleteClass.setText(Messages
					.getString("ClassDialog.DeleteClass"));
			iBtnDeleteClass.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ClassItem item;
					if (iSelectedItem instanceof ClassItem) {
						item = (ClassItem) iSelectedItem;
					} else {
						item = (ClassItem) iSelectedItem.getParent();
					}
					boolean rlt = MessageDialog.openQuestion(lBtnComposite
							.getShell(), Messages
							.getString("ClassDialog.ConfirmDelete"), Messages
							.getString("ClassDialog.WhetherDeleteClass", item
									.getTestName()));
					if (rlt) {
						((ProjectItem) item.getParent()).removeChild(item);
						iTreeViewer.remove(item);
					}
					setPageComplete(isPageComplete());
				}
			});

			// Add Method Button
			iBtnAddMethod = new Button(lBtnComposite, SWT.PUSH);
			iBtnAddMethod.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnAddMethod.setText(Messages.getString("MethodDialog.AddMethod"));
			iBtnAddMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					try {
						ClassItem item;
						if (iSelectedItem instanceof ClassItem) {
							item = (ClassItem) iSelectedItem;
						} else {
							item = (ClassItem) iSelectedItem.getParent();
						}
						MethodEditDialog dialog = new MethodEditDialog(
								lBtnComposite.getShell(), Messages
										.getString("MethodDialog.AddMethodTitle"),
								item);
						if (dialog.open()) {
							item.addChild(dialog.getMethodItem());
							iTreeViewer.add(item, dialog.getMethodItem());
							iTreeViewer
									.expandToLevel(dialog.getMethodItem(), 0);
						}
						setPageComplete(isPageComplete());
					} catch (Exception e) {
						IStatus lStatus = new Status(IStatus.WARNING,
								SetTestableItemsPage.class.getName(),
								"Exception was thrown while adding method.", e);
						TefTemplatesCarbidePlugin.getDefault().getLog().log(
								lStatus);
					}
				}
			});

			// Edit Method Button
			iBtnEditMethod = new Button(lBtnComposite, SWT.PUSH);
			iBtnEditMethod
					.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnEditMethod.setText(Messages
					.getString("MethodDialog.EditMethod"));
			iBtnEditMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MethodItem item = (MethodItem) iSelectedItem;
					MethodEditDialog dialog = new MethodEditDialog(
							lBtnComposite.getShell(), Messages
									.getString("MethodDialog.EditMethodTitle"), item);
					if (dialog.open()) {
						iTreeViewer.update(item, null);
					}
					setPageComplete(isPageComplete());
				}
			});

			// Delete Method Button
			iBtnDeleteMethod = new Button(lBtnComposite, SWT.PUSH);
			iBtnDeleteMethod.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL));
			iBtnDeleteMethod.setText(Messages
					.getString("MethodDialog.DeleteMethod"));
			iBtnDeleteMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MethodItem item = (MethodItem) iSelectedItem;
					boolean rlt = MessageDialog.openQuestion(lBtnComposite
							.getShell(), Messages
							.getString("MethodDialog.ConfirmDelete"), Messages
							.getString("MethodDialog.WhetherDeleteMethod", item
									.getTestName()));
					if (rlt) {
						((ClassItem) item.getParent()).removeChild(item);
						iTreeViewer.remove(item);
					}
					setPageComplete(isPageComplete());
				}
			});
		}

		((WizardDialog) getContainer())
				.addPageChangingListener(new IPageChangingListener() {
					public void handlePageChanging(PageChangingEvent event) {
						IWizardPage lTarPage = (IWizardPage) event
								.getTargetPage();
						if (lTarPage instanceof SetTestableItemsPage) {
							iProject.setName(iNewProjectPage.getProjectName());
							iTreeViewer.refresh();
						}
					}
				});

		// Set Input
		Vector vector = new Vector();
		vector.add(iProject);
		iTreeViewer.setInput(vector);
		iTreeViewer.setSelection(new TreeSelection(new TreePath(
				new Object[] { iProject })));
	}

	private void checkButtonState() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();
		iSelectedItem = (ITestItem) selection.getFirstElement();
		if (iSelectedItem instanceof ClassItem) {
			iBtnAddClass.setEnabled(true);
			iBtnEditClass.setEnabled(true);
			iBtnDeleteClass.setEnabled(true);
			iBtnAddMethod.setEnabled(true);
			iBtnEditMethod.setEnabled(false);
			iBtnDeleteMethod.setEnabled(false);
		} else if (iSelectedItem instanceof MethodItem) {
			iBtnAddClass.setEnabled(true);
			iBtnEditClass.setEnabled(true);
			iBtnDeleteClass.setEnabled(true);
			iBtnAddMethod.setEnabled(true);
			iBtnEditMethod.setEnabled(true);
			iBtnDeleteMethod.setEnabled(true);
		} else {// ProjectItem or Nothing selected
			iBtnAddClass.setEnabled(true);
			iBtnEditClass.setEnabled(false);
			iBtnDeleteClass.setEnabled(false);
			iBtnAddMethod.setEnabled(false);
			iBtnEditMethod.setEnabled(false);
			iBtnDeleteMethod.setEnabled(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return true;
	}
}
