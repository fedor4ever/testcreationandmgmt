/*
 * Copyright (c) 2010 Nokia Corporation and/or its subsidiary(-ies).
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

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Display;

import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.ui.parser.model.IUINode;
import com.nokia.testfw.codegen.ui.parser.model.UIMethodNode;
import com.nokia.testfw.codegen.model.INode;
import com.nokia.testfw.codegen.model.MethodNodeImpl;
import com.nokia.testfw.codegen.model.NodeImpl;
import com.nokia.testfw.codegen.model.ProjectNodeImpl;
import com.nokia.testfw.codegen.model.ClassNodeImpl;

final class GenTestMethodPage extends AbstractTemplateWizardPage {

	private final class ClassMethodLabelProvider extends LabelProvider {
		public Image getImage(Object aObject) {

			Image image = null;

			if (aObject instanceof ProjectNodeImpl) {
				image = new Image(Display.getCurrent(), getClass()
						.getResourceAsStream("/icons/project.gif"));
			}
			if (aObject instanceof ClassNodeImpl) {
				image = new Image(Display.getCurrent(), getClass()
						.getResourceAsStream("/icons/class.gif"));
			}

			if (aObject instanceof MethodNodeImpl) {
				image = new Image(Display.getCurrent(), getClass()
						.getResourceAsStream("/icons/method.gif"));
			}

			return image;
		}

		public String getText(Object aObject) {
			if (aObject instanceof NodeImpl) {
				return ((NodeImpl) aObject).getName();
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
			if (aObject instanceof NodeImpl) {
				if (((NodeImpl) aObject).getChildren() != null) {
					return ((NodeImpl) aObject).getChildren().toArray();
				}
			}
			return null;
		}

		public Object getParent(Object aObject) {
			if (aObject instanceof NodeImpl) {
				return ((NodeImpl) aObject).getParent();
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
			if (aObject instanceof ProjectNodeImpl) {
				Set<INode> classSet = ((ProjectNodeImpl) aObject).getChildren();
				return classSet.iterator().next().getChildren().toArray(
						new INode[0]);
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
	}

	private final class ClassMethodViewerFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentNode, Object node) {
			if (node instanceof IUINode) {
				IUINode lUINode = (IUINode) node;
				return lUINode.isVisible();
			}
			return true;
		}
	}

	private Tree iTree;
	private TreeViewer iTreeViewer;

	private ProjectNodeImpl iProjectNode;
	private ClassNodeImpl iClassNode;
	private Button iBtnAddMethod;
	private Button iBtnEditMethod;
	private Button iBtnDeleteMethod;
	private NodeImpl iSelectedItem;
	private Button iHeaderButton;
	private IProject iTargetProject;

	// Constants
	private static final String NAME = "Test Class Properties";

	/**
	 * 
	 */
	public GenTestMethodPage() {
		super(NAME);
		setTitle(NAME);
		setDescription("Define the test methods.");
	}

	/**
	 * Implement method of IDialogPage to create UI of this wizard page
	 */
	public void createControl(Composite aComposite) {
		initializeDialogUnits(aComposite);

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

			// Filter
			iTreeViewer.addFilter(new ClassMethodViewerFilter());

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
					iSelectedItem = (NodeImpl) selection.getFirstElement();
					if (iSelectedItem instanceof MethodNodeImpl) {
						iBtnEditMethod.notifyListeners(SWT.Selection, null);
					}
				}
			});
			iTree = iTreeViewer.getTree();
			iTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		}

		final Composite lBtnComposite = new Composite(lComposite, SWT.NONE);
		{
			lBtnComposite.setLayout(new GridLayout(4, true));
			lBtnComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// Add Method Button
			iBtnAddMethod = new Button(lBtnComposite, SWT.PUSH);
			iBtnAddMethod.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnAddMethod.setText(Messages
					.getString("GenTestMethodPage.AddMethod"));
			iBtnAddMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					try {

						MethodEditDialog dialog = new MethodEditDialog(
								lBtnComposite.getShell(),
								Messages
										.getString("MethodDialog.AddMethodTitle"),
								iClassNode);
						if (dialog.open()) {
							addMethod(dialog.getMethodItem().getName());
							iTreeViewer.refresh();
						}
						setPageComplete(isPageComplete());
					} catch (Exception e) {
						IStatus lStatus = new Status(IStatus.WARNING,
								GenTestMethodPage.class.getName(),
								"Exception was thrown while adding method.", e);
						CodegenUIPlugin.getDefault().getLog().log(lStatus);
					}
				}
			});

			// Edit Method Button
			iBtnEditMethod = new Button(lBtnComposite, SWT.PUSH);
			iBtnEditMethod
					.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnEditMethod.setText(Messages
					.getString("GenTestMethodPage.EditMethod"));
			iBtnEditMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MethodNodeImpl item = (MethodNodeImpl) iSelectedItem;
					MethodEditDialog dialog = new MethodEditDialog(
							lBtnComposite.getShell(), Messages
									.getString("MethodDialog.EditMethodTitle"),
							item);
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
					.getString("GenTestMethodPage.DeleteMethod"));
			iBtnDeleteMethod.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MethodNodeImpl item = (MethodNodeImpl) iSelectedItem;
					boolean rlt = MessageDialog.openQuestion(lBtnComposite
							.getShell(), Messages
							.getString("GenTestMethodPage.ConfirmDelete"),
							Messages.getString(
									"GenTestMethodPage.WhetherDeleteMethod",
									item.getName()));
					if (rlt) {
						((ClassNodeImpl) item.getParent()).removeChild(item);
						iTreeViewer.remove(item);
					}
					setPageComplete(isPageComplete());
				}
			});

			// HeaderButton
			iHeaderButton = new Button(lBtnComposite, SWT.PUSH);
			iHeaderButton.setText(Messages
					.getString("GenTestMethodPage.BindMethod"));
			iHeaderButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iHeaderButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					iHeaderButton.setEnabled(false);

					IRunnableWithProgress op = new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException {
							iTargetProject = ((AbstractTemplateWizard) getWizard())
									.getTargetProject();
							if (iTargetProject == null) {
								try {
									iTargetProject = ((AbstractTemplateWizard) getWizard())
											.createTargetProject(monitor);
								} catch (CoreException e) {
									CodegenUIPlugin.getDefault().getLog().log(
											e.getStatus());
									MessageDialog
											.openError(
													getShell(),
													"Exception was thrown while generating files",
													e.getMessage());
								}
							}

						}
					};
					try {
						new ProgressMonitorDialog(getShell()).run(true, false,
								op);
					} catch (InterruptedException e) {
					} catch (InvocationTargetException e) {
						IStatus lStatus = new Status(IStatus.ERROR,
								AbstractTemplateWizard.class.getName(),
								"Exception was thrown while creating project.",
								e.getTargetException());
						CodegenUIPlugin.getDefault().getLog().log(lStatus);
						MessageDialog.openError(getShell(),
								"Exception was thrown while creating project.",
								e.getTargetException().getMessage());
					}

					ISymbianSDK sdk = ((AbstractTemplateWizard) getWizard())
							.getDefaultSDK();
					GenTestMethodDialog dialog = new GenTestMethodDialog(
							iHeaderButton.getShell(), iTargetProject, sdk);
					try {
						if (dialog.open()) {
							IPath includePath = new Path(dialog
									.getHeaderFilePath());
							iClassNode.addIIncludeHeader(includePath
									.lastSegment());
							includePath = includePath.removeLastSegments(1);
							int match = includePath
									.matchingFirstSegments(new Path(sdk
											.getEPOCROOT()));
							if (match > 0) {
								includePath = includePath
										.removeFirstSegments(match);
							}
							includePath = includePath.setDevice(null);
							iProjectNode.getSystemIncludes().add(
									includePath.toString());
							iProjectNode.getLibrarys().add(dialog.getLib());
							for (INode classNode : dialog.getHeaderNode()
									.getChildren()) {
								for (INode methodNode : classNode.getChildren()) {
									if (((UIMethodNode) methodNode)
											.isSelected()) {
										addMethod(classNode.getName() + "_"
												+ methodNode.getName());
									}
								}
							}
							iTreeViewer.refresh();
							setPageComplete(isPageComplete());
						}
					} catch (Exception e) {
						IStatus lStatus = new Status(IStatus.ERROR,
								AbstractTemplateWizard.class.getName(),
								"Exception was thrown while creating project.",
								e);
						CodegenUIPlugin.getDefault().getLog().log(lStatus);
					} finally {
						iHeaderButton.setEnabled(true);
					}
				}
			});
		}
	}

	public void update() {
		iTreeViewer.setInput(iProjectNode);
		checkButtonState();
		setPageComplete(isPageComplete());
	}

	private void addMethod(String name) {
		if (isDuplicateMethod(name)) {
			int counter = 1;
			while (isDuplicateMethod(name + Integer.toString(counter))) {
				counter++;
			}
			name = name + Integer.toString(counter);
		}
		MethodNodeImpl node = new MethodNodeImpl(name, iClassNode);
		iClassNode.addChild(node);
	}

	private boolean isDuplicateMethod(String name) {
		Set<INode> lMethodSet = (Set<INode>) iClassNode.getChildren();
		for (INode item : lMethodSet) {
			if (item.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	private void checkButtonState() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();
		iSelectedItem = (NodeImpl) selection.getFirstElement();
		iBtnAddMethod.setEnabled(true);
		iHeaderButton.setEnabled(true);
		if (iSelectedItem instanceof MethodNodeImpl) {
			iBtnEditMethod.setEnabled(true);
			iBtnDeleteMethod.setEnabled(true);
		} else {// Nothing selected
			iBtnEditMethod.setEnabled(false);
			iBtnDeleteMethod.setEnabled(false);
		}
	}

	public void collectData() {
		if (iClassNode instanceof IUINode) {
			Iterator<INode> itr = iClassNode.getChildren().iterator();
			while (itr.hasNext()) {
				INode node = itr.next();
				if (node instanceof IUINode) {
					if (((IUINode) node).isVisible() == false) {
						itr.remove();
					}
				}
			}
		}
		iDataMap.put(AbstractTemplateWizard.PROJECT_OBJECT, iProjectNode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (iClassNode != null) {
			return iClassNode.getChildren().size() > 0;
		}
		return false;
	}

	public void initPage(Map<String, Object> dataMap) {
		super.initPage(dataMap);
		iProjectNode = (ProjectNodeImpl) iDataMap
				.get(AbstractTemplateWizard.PROJECT_OBJECT);
		if (iProjectNode == null || iProjectNode.getChildren().size() == 0) {
			throw new IllegalArgumentException("Must have test class object");
		}
		iClassNode = (ClassNodeImpl) iProjectNode.getChildren().iterator()
				.next();
		update();
	}
}
