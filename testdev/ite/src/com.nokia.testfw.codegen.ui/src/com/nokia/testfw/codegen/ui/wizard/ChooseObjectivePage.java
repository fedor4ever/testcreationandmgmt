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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.ui.CElementLabelProvider;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

import com.nokia.testfw.codegen.model.IMethodNode;
import com.nokia.testfw.codegen.model.INode;
import com.nokia.testfw.codegen.model.IProjectNode;
import com.nokia.testfw.codegen.model.NodeImpl;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.ui.parser.Parser;
import com.nokia.testfw.codegen.ui.parser.model.IUINode;
import com.nokia.testfw.codegen.ui.parser.model.UIClassNode;
import com.nokia.testfw.codegen.ui.parser.model.UIMethodNode;
import com.nokia.testfw.codegen.ui.parser.model.UIProjectNode;

/**
 * A customized wizard page for user to select classes and methods to test using
 * TEF Block project.
 * 
 * @author Development Tools
 */
class ChooseObjectivePage extends AbstractTemplateWizardPage {

	private static final String CONSTRUCTOR = "Constructor";

	private static final String DESTRUCTOR = "Destructor";

	private static final String OPERATOR = "Operator";

	private static final String CONST = "Const";

	private static final String INLINE = "Inline";

	private static final String STATIC = "Static";

	private static final String VIRTUAL = "Virtual";

	private static final String PUREVIRTUAL = "PureVirtual";

	private static final String[] iFilters = new String[] { CONSTRUCTOR,
			DESTRUCTOR, OPERATOR, CONST, INLINE, STATIC, VIRTUAL, PUREVIRTUAL };

	private Tree iTree;

	private final Map<String, Boolean> filtersMap = new HashMap<String, Boolean>();

	private ContainerCheckedTreeViewer iCheckboxTreeViewer;

	private IStructuredSelection iSelection;

	private UIProjectNode iUIProjectNode;

	private Label iCounterLabel;

	private int iMethodCounter = 0;

	private int iSelectedCounter = 0;

	/**
	 * 
	 */
	public ChooseObjectivePage() {
		super("ChooseObjectivePage");
		setTitle(Messages.getString("ChooseObjectivePage.Title"));
		setDescription(Messages.getString("ChooseObjectivePage.Description"));
		for (String filter : iFilters) {
			filtersMap.put(filter, Boolean.TRUE);
		}
	}

	/**
	 * Implement method of IDialogPage to create UI of this wizard page
	 */
	public void createControl(Composite aComposite) {
		initializeDialogUnits(aComposite);

		Composite lComposite = new Composite(aComposite, SWT.NONE);
		{
			lComposite.setLayout(new GridLayout(2, false));
			lComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

			setControl(lComposite);

			iCheckboxTreeViewer = new ContainerCheckedTreeViewer(lComposite,
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
					return (Object[]) aObject;
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
						return super.getImage(((IUINode) aObject)
								.getICElement());
					}
					return null;
				}

				public String getText(Object aObject) {
					if (aObject instanceof IUINode) {
						return super
								.getText(((IUINode) aObject).getICElement());
					}
					return null;
				}
			});

			iCheckboxTreeViewer.addFilter(createTreeViewerFilter());

			iTree = iCheckboxTreeViewer.getTree();
			iTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			// Add Checked Listener
			iCheckboxTreeViewer
					.addCheckStateListener(new ICheckStateListener() {
						public void checkStateChanged(
								CheckStateChangedEvent aChangedEvent) {
							IUINode lNodeItem = (IUINode) aChangedEvent
									.getElement();
							lNodeItem.setSelected(aChangedEvent.getChecked());
							refresh(false);
						}
					});

			Group filterGroup = new Group(lComposite, SWT.SHADOW_IN);
			filterGroup.setLayoutData(new GridData(
					GridData.VERTICAL_ALIGN_BEGINNING));
			filterGroup.setLayout(new GridLayout());
			filterGroup.setText("Method Filters:");

			SelectionAdapter adapter = createSelectionAdapter();
			for (String filter : iFilters) {
				Boolean filtervalue = filtersMap.get(filter);
				Button button = new Button(filterGroup, SWT.CHECK);
				button.setLayoutData(new GridData());
				button.setText(filter);
				button.setSelection(filtervalue);
				button.setData(filter);
				button.addSelectionListener(adapter);
			}

			iCounterLabel = new Label(lComposite, SWT.LEFT);
			GridData lCounterGridData = new GridData(GridData.FILL_HORIZONTAL);
			lCounterGridData.horizontalSpan = 2;
			iCounterLabel.setLayoutData(lCounterGridData);
		}
		setPageComplete(isValid());
	}

	private ViewerFilter createTreeViewerFilter() {
		ViewerFilter filter = new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentNode, Object node) {
				IUINode lUINode = (IUINode) node;
				ICElement lICElement = lUINode.getICElement();
				if (lICElement instanceof IStructure) {
					boolean result = false;
					UIClassNode classNode = (UIClassNode) lUINode;
					for (INode child : classNode.getChildren()) {
						boolean visibility = getMethodVisibility((IMethodDeclaration) ((IUINode) child)
								.getICElement());
						((IUINode) child).setVisible(visibility);
						result = result || visibility;
					}
					return result;
				}
				if (lICElement instanceof IMethodDeclaration) {
					boolean visibility = getMethodVisibility((IMethodDeclaration) lICElement);
					lUINode.setVisible(visibility);
					if (visibility == true) {
						iMethodCounter++;
						if (lUINode.isSelected()) {
							iSelectedCounter++;
						}
					}
					return visibility;
				}
				return true;
			}

			private boolean getMethodVisibility(IMethodDeclaration lMethod) {
				boolean visibility = true;
				try {
					if (!filtersMap.get(CONSTRUCTOR)) {
						if (lMethod.isConstructor()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(DESTRUCTOR)) {
						if (lMethod.isDestructor()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(INLINE)) {
						if (lMethod.isInline()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(OPERATOR)) {
						if (lMethod.isOperator()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(PUREVIRTUAL)) {
						if (lMethod.isPureVirtual()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(STATIC)) {
						if (lMethod.isStatic()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(VIRTUAL)) {
						if (lMethod.isVirtual()) {
							visibility = false;
						}
					}
					if (!filtersMap.get(CONST)) {
						if (lMethod.isConst()) {
							visibility = false;
						}
					}
				} catch (CModelException e) {
					IStatus lStatus = new Status(IStatus.ERROR,
							ChooseObjectivePage.class.getName(),
							"Exception was thrown while showing methods.", e);
					CodegenUIPlugin.getDefault().getLog().log(lStatus);
				}
				return visibility;
			}

		};
		return filter;
	}

	private SelectionAdapter createSelectionAdapter() {
		SelectionAdapter adapter = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Button button = (Button) event.widget;
				String filter = (String) button.getData();
				filtersMap.put(filter, button.getSelection());
				refresh(true);
				for (INode classnode : iUIProjectNode.getChildren()) {
					for (INode methodnode : classnode.getChildren()) {
						if (((UIMethodNode) methodnode).isVisible()) {
							iCheckboxTreeViewer.setChecked(methodnode,
									((UIMethodNode) methodnode).isSelected());
						}
					}
				}
				if (iUIProjectNode.isVisible() == false) {
					iCheckboxTreeViewer.setChecked(iUIProjectNode, false);
				}
			}
		};
		return adapter;
	}

	@SuppressWarnings("unchecked")
	private void update(IStructuredSelection currentSelection) {
		if (currentSelection == iSelection) {
			return;
		}
		if (currentSelection == null) {
			iSelection = null;
			iUIProjectNode = null;
			iCheckboxTreeViewer.setInput(null);
			iCheckboxTreeViewer.refresh();
			iCounterLabel.setText("");
			return;
		} else {
			iSelection = currentSelection;
		}
		Object element = iSelection.getFirstElement();
		try {
			if (element instanceof IProject) {
				iUIProjectNode = Parser.parseProject((IProject) element,
						((AbstractTemplateWizard) getWizard())
								.getTemplateName(), Parser.PUBLIC);
			}
			if (element instanceof IMethodDeclaration) {
				Object[] elements = iSelection.toList().toArray(
						new IMethodDeclaration[0]);
				iUIProjectNode = Parser
						.parseMethod((IMethodDeclaration[]) elements);
			}
			if (element instanceof IStructure) {
				Object[] elements = iSelection.toList().toArray(
						new IStructure[0]);
				iUIProjectNode = Parser.parseClass((IStructure[]) elements,
						Parser.PUBLIC);
			}
			if (element instanceof ITranslationUnit) {
				Object[] elements = iSelection.toList().toArray(
						new ITranslationUnit[0]);
				iUIProjectNode = Parser.parseTranslationUnit(
						(ITranslationUnit[]) elements, Parser.PUBLIC);
			}
		} catch (CModelException e) {
			IStatus lStatus = new Status(
					IStatus.ERROR,
					ChooseObjectivePage.class.getName(),
					"Exception was thrown while updating Classes to Test Page.",
					e);
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
		}
		if (iUIProjectNode != null) {
			iMethodCounter = 0;
			iSelectedCounter = 0;
			iCheckboxTreeViewer.setInput(new Object[] { iUIProjectNode });
			iCheckboxTreeViewer.setSubtreeChecked(iUIProjectNode, true);
			iCheckboxTreeViewer.expandAll();
			iCounterLabel.setText("[" + iSelectedCounter + "/" + iMethodCounter
					+ "]" + " methods selected.");
			setPageComplete(isValid());
		}
	}

	private void refresh(boolean expand) {
		iMethodCounter = 0;
		iSelectedCounter = 0;
		iCheckboxTreeViewer.refresh();
		if (expand) {
			iCheckboxTreeViewer.expandAll();
		}
		iCounterLabel.setText("[" + iSelectedCounter + "/" + iMethodCounter
				+ "]" + " methods selected.");
		setPageComplete(isValid());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	private boolean isValid() {

		boolean isValid = false;

		if (iUIProjectNode == null || iUIProjectNode.isVisible() == false)
			return false;

		for (INode classnode : iUIProjectNode.getChildren()) {
			for (INode methodnode : classnode.getChildren()) {
				UIMethodNode uimethodnode = (UIMethodNode) methodnode;
				if (uimethodnode.isVisible() && uimethodnode.isSelected()) {
					isValid = true;
					break;
				}
			}
		}
		return isValid;
	}

	private INode cloneSelclted(INode node) {
		INode clone = (INode) ((NodeImpl) node).clone();
		for (INode child : node.getChildren()) {
			if (((IUINode) child).isSelected() && ((IUINode) child).isVisible()) {
				if ((child instanceof IMethodNode)) {
					((NodeImpl) clone).addChild((INode) ((NodeImpl) child)
							.clone());
				} else {
					((NodeImpl) clone).addChild(cloneSelclted(child));
				}
			}
		}
		return clone;
	}

	public void collectData() {
		IProjectNode selProjectNode = (IProjectNode) cloneSelclted(iUIProjectNode);
		iDataMap.put(AbstractTemplateWizard.PROJECT_OBJECT, selProjectNode);
	}

	public void initPage(Map<String, Object> dataMap) {
		super.initPage(dataMap);
		update(((AbstractTemplateWizard) getWizard()).getSelection());
	}
}