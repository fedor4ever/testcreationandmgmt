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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

import com.nokia.carbide.internal.api.templatewizard.ui.IWizardDataPage;

/**
 * A customized wizard page for user to select classes and methods to test using
 * TEF Block project.
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
final class GetTestableItemsUnitPage extends WizardPage implements
		IWizardDataPage {

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
				if (aObject instanceof MethodItem) {
					return ((MethodItem) aObject).getFullName();
				} else {
					return ((ITestItem) aObject).getName();
				}
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
				return ((ITestItem) aObject).getChildren().toArray();
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
			return !(aObject instanceof MethodItem);
		}

		public Object[] getElements(Object aObject) {
			sProjects = (Set<ProjectItem>) aObject;
			return sProjects.toArray();
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
	private ContainerCheckedTreeViewer iCheckboxTreeViewer;
	private GenerateUnitFromPage iGenerateUnitFromPage;

	private static Set<ProjectItem> sProjects = new LinkedHashSet<ProjectItem>();

	// Constants
	private static final String NAME = "Classes to Test";
	public static final String PROJECTS = "projects";

	/**
	 * 
	 */
	public GetTestableItemsUnitPage() {
		super(NAME);
		setTitle(NAME);
		setDescription("Select the classes and methods you want to test.");
	}

	/**
	 * Implement method of IWizardDataPage to provide data to project creation
	 * process.
	 */
	public Map<String, Object> getPageValues() {
		Map<String, Object> lPageValues = new HashMap<String, Object>();
		lPageValues.put(PROJECTS, sProjects);
		return lPageValues;
	}

	/**
	 * Implement method of IDialogPage to create UI of this wizard page
	 */
	public void createControl(Composite aComposite) {
		initializeDialogUnits(aComposite);
		sProjects.clear();

		IWizardPage lpage = this;
		while ((lpage = lpage.getPreviousPage()) != null) {
			if (lpage instanceof GenerateUnitFromPage) {
				iGenerateUnitFromPage = (GenerateUnitFromPage) lpage;
				break;
			}
		}

		final Composite lComposite = new Composite(aComposite, SWT.NONE);
		{
			lComposite.setLayout(new GridLayout(1, false));
			lComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

			setControl(lComposite);

			iCheckboxTreeViewer = new ContainerCheckedTreeViewer(lComposite,
					SWT.BORDER);

			// Content Provider
			iCheckboxTreeViewer
					.setContentProvider(new ClassMethodContentProvider());

			// Label Provider
			iCheckboxTreeViewer
					.setLabelProvider(new ClassMethodLabelProvider());

			iTree = iCheckboxTreeViewer.getTree();
			iTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			// Add Checked Listener
			iCheckboxTreeViewer
					.addCheckStateListener(new ICheckStateListener() {
						public void checkStateChanged(
								CheckStateChangedEvent aChangedEvent) {
							ITestItem lITestItem = (ITestItem) aChangedEvent
									.getElement();
							lITestItem.setSelected(aChangedEvent.getChecked());
							setPageComplete(isPageComplete());
						}
					});
		}

		((WizardDialog) getContainer())
				.addPageChangingListener(new IPageChangingListener() {
					public void handlePageChanging(PageChangingEvent event) {
						IWizardPage lCurPage = (IWizardPage) event
								.getCurrentPage();
						IWizardPage lTarPage = (IWizardPage) event
								.getTargetPage();
						if (lTarPage instanceof GetTestableItemsUnitPage) {
							if (lCurPage instanceof GenerateUnitFromPage) {
								if (iGenerateUnitFromPage.isChanged() == false) {
									return;
								}
								Set<ProjectItem> iProjects = (Set<ProjectItem>) iGenerateUnitFromPage
										.getPageValues()
										.get(GenerateUnitFromPage.TESTPROJECTS);
								iCheckboxTreeViewer.setInput(iProjects);
								iCheckboxTreeViewer.setAllChecked(true);
								iCheckboxTreeViewer.expandAll();
							}
						}
					}
				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		boolean lIsSomethingSelected = false;
		for (ProjectItem lProjectItem : sProjects) {
			if (lProjectItem.isSelected()) {
				lIsSomethingSelected = true;
				break;
			}
		}
		return lIsSomethingSelected;
	}

}