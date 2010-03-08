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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
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
 * A customized wizard page for user to select classes and methods to test 
 * using TEFUnit project. 
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
final class GetTestableItemsPage extends WizardPage implements IWizardDataPage {

	/**
	 * @author Development Tools
	 *
	 */
	private final class ClassMethodLabelProvider extends LabelProvider {
		public Image getImage(Object aObject) {
			if (aObject instanceof Wrapper) {
				return ((Wrapper) aObject).getImage();
			}
			
			return null;
		}

		public String getText(Object aObject) {
			if (aObject instanceof Wrapper) {
				return ((Wrapper) aObject).getName();
			}
			
			return null;
		}
	}


	/**
	 * @author Development Tools
	 *
	 */
	private final class ClassMethodContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object aObject) {
			if (aObject instanceof Wrapper) {
				return ((Wrapper) aObject).getChildren().toArray();
			}
			
			return null;
		}

		public Object getParent(Object aObject) {
			if (aObject instanceof Wrapper) {
				return ((Wrapper) aObject).getParent();
			}
			return null;
		}

		public boolean hasChildren(Object aObject) {
			return !(aObject instanceof MethodWrapper);
		}

		public Object[] getElements(Object aObject) {
			if (aObject instanceof IWorkspaceRoot) {
				for (IProject lProject : ((IWorkspaceRoot) aObject).getProjects()) {
					sProjects.add(new ProjectWrapper(lProject));
				}
			} else if (aObject instanceof IResource) {
				ProjectWrapper lProjectWrapper = new ProjectWrapper(((IResource) aObject).getProject());
				sProjects.add(lProjectWrapper);
			}
			
			return sProjects.toArray();
		}

		public void dispose() {
			// do nothing
		}

		public void inputChanged(Viewer aViewer, Object aOldInput, Object aNewInput) {
			// do nothing
		}
	}


	private Tree iTree;
	private IStructuredSelection iStructuredSelection;

	private static final Set<ProjectWrapper> sProjects = new LinkedHashSet<ProjectWrapper>();
	
	// Constants
	private static final String NAME = "Classes to Test";
	public static final String PROJECTS = "projects";
	
	/**
	 * @param aStructuredSelection 
	 */
	public GetTestableItemsPage(IStructuredSelection aStructuredSelection) {
		super(NAME);
		setTitle(NAME);
		setDescription("Select the classes and methods you want to test.");
		
		iStructuredSelection = aStructuredSelection;
	}

	/**
	 * Implement method of IWizardDataPage to provide data to project creation process.
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
		
		final Composite lComposite = new Composite(aComposite, SWT.NONE);
		{
			lComposite.setLayout(new GridLayout(1, false));
			lComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			
			setControl(lComposite);
	
			final ContainerCheckedTreeViewer checkboxTreeViewer = new ContainerCheckedTreeViewer(lComposite, SWT.BORDER);
			
			// Content Provider
			checkboxTreeViewer.setContentProvider(new ClassMethodContentProvider());
			
			// Label Provider		
			checkboxTreeViewer.setLabelProvider(new ClassMethodLabelProvider());
			
			// Set Input
			IResource lRoot = ResourcesPlugin.getWorkspace().getRoot();
			if (iStructuredSelection != null
					&& iStructuredSelection.getFirstElement() instanceof IResource) {
				lRoot = (IResource) iStructuredSelection.getFirstElement();
			}
			
			checkboxTreeViewer.setInput(lRoot);
			
			iTree = checkboxTreeViewer.getTree();
			iTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	
			// Add Checked Listener
			checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
	
				public void checkStateChanged(CheckStateChangedEvent aChangedEvent) {
					Wrapper lWrapper = (Wrapper) aChangedEvent.getElement();
					lWrapper.setSelected(aChangedEvent.getChecked());
					setPageComplete(isPageComplete());
				}
				
			});
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		boolean lIsSomethingSelected = false;
		for (ProjectWrapper lProjectWrapper : sProjects) {
			if (lProjectWrapper.isSelected()) {
				lIsSomethingSelected = true;
				break;
			}
		}
		return lIsSomethingSelected;
	}
	
}