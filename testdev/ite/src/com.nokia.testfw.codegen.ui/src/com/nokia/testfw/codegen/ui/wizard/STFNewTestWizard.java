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

import java.util.Set;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

import com.nokia.testfw.codegen.model.ClassNodeImpl;
import com.nokia.testfw.codegen.model.ProjectNodeImpl;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.parser.Parser;
import com.nokia.testfw.codegen.ui.parser.ProjectInfoHelper;
import com.nokia.testfw.codegen.ui.parser.model.UIProjectNode;

public class STFNewTestWizard extends AbstractTemplateWizard {

	public static final String STF_SCRIPT = "STF_Script";
	public static final String MODULES = "modules";
	public static final String SEPARATE_FOLDER = "separate_folder";

	private boolean iShowNewModulePage = true;
	private GenTestMethodPage iGenTestMethodPage;
	private ModuleAndPropertyPage iModuleAndPropertyPage;
	private TestCasePreviewPage iTestCasePreviewPage;

	private ProjectNodeImpl iProjectNode;
	private ClassNodeImpl iClassNode;
	private String iModuleName;

	public STFNewTestWizard() {
		super(STF_SCRIPT);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		if (iShowNewModulePage == false) {
			Object selObj = currentSelection.getFirstElement();
			if (selObj instanceof ITranslationUnit) {
				iModuleName = ((ITranslationUnit) selObj).getElementName();
				iModuleName = iModuleName.substring(0, iModuleName
						.lastIndexOf('.'));
				iDataMap.put("class_name", iModuleName);
				iProjectNode = createProjectNode(selObj);
				Set<String> lTestFolders = getTestFoldersSet();
				if (lTestFolders != null && lTestFolders.size() > 0) {
					String folder = ((ITranslationUnit) selObj).getResource()
							.getProjectRelativePath().segment(0);
					if (lTestFolders.contains(folder)) {
						iTestFolderLocation = folder;
					}
				}
			}
		}
		if (iProjectNode == null || iClassNode == null) {
			iProjectNode = createProjectNode(iTargetProject);
		}
		iDataMap.put(PROJECT_OBJECT, iProjectNode);
	}

	public void addPages() {
		super.addPages();

		if (iShowNewModulePage) {
			iModuleAndPropertyPage = new ModuleAndPropertyPage();
			iModuleAndPropertyPage.setShowPreviewButton(false);
			addPage(iModuleAndPropertyPage);
		}

		iGenTestMethodPage = new GenTestMethodPage();
		addPage(iGenTestMethodPage);

		if (!iShowNewProjectPage && !iShowNewModulePage) {
			iTestCasePreviewPage = new TestCasePreviewPage();
			addPage(iTestCasePreviewPage);
		}
	}

	@Override
	protected boolean initPagesConditon() {
		return !iShowNewProjectPage;
	}

	public boolean isShowNewModulePage() {
		return iShowNewModulePage;
	}

	public void setShowNewModulePage(boolean showNewModulePage) {
		iShowNewModulePage = showNewModulePage;
	}

	private ProjectNodeImpl createProjectNode(Object sel) {
		if (sel instanceof ITranslationUnit) {
			try {
				iProjectNode = Parser.parseTranslationUnit(
						(ITranslationUnit) sel, Parser.PUBLIC
								| Parser.PROTECTED | Parser.PRIVATE);
				if (iProjectNode.getChildren().size() > 0) {
					iClassNode = (ClassNodeImpl) iProjectNode.getChildren()
							.iterator().next();
				}
				((UIProjectNode) iProjectNode).setVisible(false);
			} catch (CModelException e) {
				CodegenUIPlugin.getDefault().getLog().log(e.getStatus());
			}
		} else if (sel instanceof IProject) {
			iProjectNode = new ProjectNodeImpl(((IProject) sel).getName());
			iClassNode = new ClassNodeImpl(((IProject) sel).getName(),
					iProjectNode);
			iProjectNode.addChild(iClassNode);
		} else {
			iProjectNode = new ProjectNodeImpl("");
			iClassNode = new ClassNodeImpl("TestClass", iProjectNode);
			iProjectNode.addChild(iClassNode);
		}

		return iProjectNode;
	}

	public String getModuleName() {
		return iModuleName;
	}

	public void setModuleName(String moduleName) {
		iModuleName = moduleName;
	}

	@Override
	protected void collectPagesData() {
		super.collectPagesData();
		iProjectNode.setName(iProjectName);
		iClassNode.setName(iModuleName);
	}

	@Override
	protected void setProjectInfo(IProject project, IFile bldInf,
			IProgressMonitor monitor) throws CoreException {
		super.setProjectInfo(project, bldInf, monitor);
		if (iShowNewProjectPage) {
			// Add STF Nature
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = "com.nokia.testfw.codegen.ui.STFProjectNature";
			description.setNatureIds(newNatures);
			project.setDescription(description, monitor);
		}

		if (iShowNewModulePage) {
			ProjectInfoHelper.setProjectStorage(project, STF_SCRIPT,
					SEPARATE_FOLDER, Boolean
							.toString(iTestFolderLocation != null));

			String modules = ProjectInfoHelper.getProjectStorage(project,
					STF_SCRIPT, MODULES);
			boolean module_exist = false;
			if (modules != null && modules.trim().length() > 0) {
				modules = modules.trim();
				String[] module = modules.split(",");
				for (String m : module) {
					if (m.equalsIgnoreCase(iModuleName)) {
						module_exist = true;
						break;
					}
				}
			}
			if (!module_exist) {
				if (modules == null) {
					modules = iModuleName;
				} else {
					modules += "," + iModuleName;
				}
				ProjectInfoHelper.setProjectStorage(project, STF_SCRIPT,
						MODULES, modules);
			}
		}
	}
}
