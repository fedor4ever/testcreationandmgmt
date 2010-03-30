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

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.ICarbideBuildManager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.nokia.testfw.codegen.ui.Messages;

public class ChooseProjectPage extends WizardPage implements
		IPageChangingListener {

	private TableViewer iTableViewer;
	private IStructuredSelection iSelection;
	private ICarbideBuildManager iBuildManager;
	private IProject iTargetProject;

	public ChooseProjectPage() {
		super("ChooseProjectPage");
		setTitle(Messages.getString("ChooseProjectPage.Title"));
		setDescription(Messages.getString("ChooseProjectPage.Description"));
		iBuildManager = CarbideBuilderPlugin.getBuildManager();
	}

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		Composite container = new Composite(parent, 0);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginTop = 10;
		gridLayout.marginRight = 10;
		gridLayout.marginLeft = 10;
		gridLayout.marginBottom = 10;
		gridLayout.horizontalSpacing = 10;
		container.setLayout(gridLayout);
		Label selectAnExistingLabel = new Label(container, 0);
		GridData gridData = new GridData(4, 2, true, false);
		selectAnExistingLabel.setLayoutData(gridData);
		selectAnExistingLabel.setText(Messages
				.getString("ChooseProjectPage.Label"));
		iTableViewer = new TableViewer(container, 2816);
		iTableViewer.getTable().setLayoutData(new GridData(4, 4, true, true));
		iTableViewer.setContentProvider(new ArrayContentProvider());
		iTableViewer.setLabelProvider(new WorkbenchLabelProvider());
		iTableViewer.setInput(getEligibleProjects());
		iTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						handleSelection(selection);
					}
				});
		setControl(container);

		iSelection = (IStructuredSelection) ((BasicNewResourceWizard) getWizard())
				.getSelection();
		iTableViewer.setSelection(iSelection);
		handleSelection(iSelection);

		((WizardDialog) getContainer()).addPageChangingListener(this);
	}

	public IProject getTargetProject() {
		return iTargetProject;
	}

	private boolean isValid() {
		return !iSelection.isEmpty();
	}

	protected void handleSelection(IStructuredSelection selection) {
		iSelection = selection;
		if (iTableViewer.getTable().getItemCount() == 0) {
			setErrorMessage(Messages
					.getString("ChooseProjectPage.NoProjectsError"));
		} else {
			if (iSelection.isEmpty()) {
				setErrorMessage(Messages
						.getString("ChooseProjectPage.NoSelectionError"));
			} else {
				iTargetProject = (IProject) iSelection.getFirstElement();
				setErrorMessage(null);
			}
		}
		setPageComplete(isValid());
	}

	private IProject[] getEligibleProjects() {
		List<IProject> eligibleProjects = new ArrayList<IProject>();
		IProject projects[] = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		for (int i = 0; i < projects.length; i++) {
			IProject project = projects[i];
			if (projectIsEligible(project))
				eligibleProjects.add(project);
		}

		return (IProject[]) eligibleProjects
				.toArray(new IProject[eligibleProjects.size()]);
	}

	private boolean projectIsEligible(IProject project) {
		return iBuildManager.isCarbideProject(project);
	}

	public void handlePageChanging(PageChangingEvent event) {
		if (event.getCurrentPage() instanceof ChooseProjectPage) {
			if(iTargetProject!=null){
				((AbstractTemplateWizard) getWizard()).setSelection(iSelection);
				((AbstractTemplateWizard) getWizard())
						.setTargetProject(iTargetProject);
				((AbstractTemplateWizard) getWizard())
						.setProjectLocation(iTargetProject.getLocation()
								.toOSString());
				((AbstractTemplateWizard) getWizard()).getDataMap().put(
						AbstractTemplateWizard.PROJECT_NAME,
						iTargetProject.getName());
				((AbstractTemplateWizard) getWizard()).initPages();
			}
		}
	}
}
