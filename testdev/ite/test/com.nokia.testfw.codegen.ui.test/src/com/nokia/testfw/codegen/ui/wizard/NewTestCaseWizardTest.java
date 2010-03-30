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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.PlatformUI;

import com.nokia.testfw.test.framework.WizardTestCase;
import com.nokia.testfw.test.utils.ProjectUtils;
import com.nokia.testfw.test.utils.TestUtils;

public class NewTestCaseWizardTest extends WizardTestCase {
	IProject targetProject;

	protected void setUp() throws Exception {
		targetProject = ProjectUtils.getTargetProject("testProject");
		if (targetProject == null) {
			// targetProject.delete(true, true, new NullProgressMonitor());
			String projectPath = (new File("resource/HelloWorld"))
					.getCanonicalPath();
			String bldInfPath = "Bld.inf";
			targetProject = ProjectUtils.createTargetProject("testProject",
					projectPath, bldInfPath);
		}
		ProjectUtils.selectProject(targetProject);
		super.setUp();
	}

	SUTNewTestWizard wizard;

	protected IWizard getWizard() {
		wizard = new SUTNewTestWizard();
		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(
				targetProject));
		return wizard;
	}

	public void testCreateTestSuitePerform() {
		wizard.setShowChooseProjectPage(true);
		dialog.open();
		TestUtils.delay(3000);
		IWizardPage currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		dialog.getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				wizard.performFinish();
			}
		});
		dialog.close();
		TestUtils.delay(3000);

		IFolder testfolder = targetProject.getFolder("tsrc");
		assertTrue(testfolder.exists());
	}

	public void testCreateTestCasePerform() {
		wizard.setShowChooseProjectPage(false);
		dialog.open();
		TestUtils.delay(3000);
		IWizardPage currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		currentPage = dialog.getCurrentPage();
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		currentPage = dialog.getCurrentPage();
		if (currentPage instanceof LocationAndPropertyPage) {
			((LocationAndPropertyPage) currentPage).setShowPreviewChanges(true);
			dialog.updateButtons();
		}
		dialog.showPage(currentPage.getNextPage());
		TestUtils.delay(3000);
		dialog.getShell().getDisplay().syncExec(new Runnable() {
			public void run() {
				wizard.performFinish();
			}
		});
		dialog.close();
		TestUtils.delay(3000);

		IFolder testfolder = targetProject.getFolder("tsrc");
		assertTrue(testfolder.exists());
	}

}
