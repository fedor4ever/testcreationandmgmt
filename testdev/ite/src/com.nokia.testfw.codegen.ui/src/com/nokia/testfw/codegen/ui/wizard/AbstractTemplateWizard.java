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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ISourceEntry;
import org.eclipse.cdt.core.settings.model.CSourceEntry;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.core.settings.model.ICSourceEntry;
import org.eclipse.cdt.core.settings.model.WriteAccessException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.project.ICarbideBuildConfiguration;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.internal.project.ui.ProjectUIPlugin;
import com.nokia.carbide.cpp.project.core.ProjectCorePlugin;
import com.nokia.carbide.cpp.project.ui.sharedui.NewProjectPage;
import com.nokia.carbide.cpp.sdk.core.ISymbianBuildContext;
import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.carbide.cpp.sdk.ui.shared.BuildTargetsPage;
import com.nokia.testfw.codegen.ChangeFileContent;
import com.nokia.testfw.codegen.CodegenEngine;
import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.ui.ResourceChangeListener;
import com.nokia.testfw.codegen.ui.parser.Parser;
import com.nokia.testfw.codegen.ui.parser.ProjectInfoHelper;

public abstract class AbstractTemplateWizard extends BasicNewResourceWizard
		implements IPreviewResult, INewWizard {

	public static final String PROJECT_OBJECT = "project_object";
	public static final String PROJECT_NAME = "project_name";
	public static final String LOCATION = "location";

	protected boolean iShowNewProjectPage = true;
	protected NewProjectPage iNewProjectPage;
	protected BuildTargetsPage iBuildTargetsPage;
	protected List<ISymbianBuildContext> iBuildConfigs;
	protected ISymbianSDK iDefaultSDK;

	protected IProject iTargetProject;
	protected String iProjectName;
	protected String iProjectLocation;
	protected boolean iSelfCreateProject = false;
	protected String iTestFolderLocation = null;

	protected Map<String, Object> iDataMap = new HashMap<String, Object>();
	protected Map<String, ChangeFileContent> iResultMap;
	protected final String iTemplateName;
	protected boolean iTestMMP = true;

	public AbstractTemplateWizard(String template) {
		iTemplateName = template;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		if (iShowNewProjectPage == false) {
			Object sel = selection.getFirstElement();
			if (sel == null)
				return;
			if (sel instanceof ICElement) {
				iTargetProject = ((ICElement) sel).getCProject().getProject();
			} else if (sel instanceof IResource) {
				iTargetProject = ((IResource) sel).getProject();
			}
			iProjectName = iTargetProject.getName();
			iProjectLocation = iTargetProject.getLocation().toOSString();
			iDataMap.put(PROJECT_NAME, iProjectName);
			iDefaultSDK = getDefaultSDK(iTargetProject);
		}
	}

	protected ISymbianSDK getDefaultSDK(IProject project) {
		if (project != null
				&& CarbideBuilderPlugin.getBuildManager().isCarbideProject(
						project) && project.isAccessible()) {
			ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
					.getProjectInfo(project);
			if (cpi != null) {
				ICarbideBuildConfiguration config = cpi
						.getDefaultConfiguration();
				if (config != null) {
					return config.getSDK();
				}
			}
		}
		return null;
	}

	public void addPages() {
		if (iShowNewProjectPage) {
			iNewProjectPage = new NewProjectPage("Symbian Test Framework",
					"Create a new Symbian Test Framework project") {
				@Override
				public void createControl(Composite parent) {
					super.createControl(parent);
					((WizardDialog) getContainer())
							.addPageChangingListener(new IPageChangingListener() {
								public void handlePageChanging(
										PageChangingEvent event) {
									if (event.getCurrentPage() instanceof NewProjectPage) {
										iProjectName = iNewProjectPage
												.getProjectName();
										iProjectLocation = iNewProjectPage
												.getLocationPath().append(
														iProjectName)
												.toOSString();
										iDataMap
												.put(PROJECT_NAME, iProjectName);
									}
								}
							});
				}
			};
			addPage(iNewProjectPage);

			iBuildTargetsPage = new BuildTargetsPage() {
				@Override
				public void createControl(Composite parent) {
					super.createControl(parent);
					((WizardDialog) getContainer())
							.addPageChangingListener(new IPageChangingListener() {
								public void handlePageChanging(
										PageChangingEvent event) {
									if (event.getCurrentPage() instanceof BuildTargetsPage) {
										iBuildConfigs = getSelectedBuildConfigs();
										iDefaultSDK = iBuildConfigs.get(0)
												.getSDK();
										initPages();
									}
								}
							});
				}

				protected boolean validatePage() {
					boolean valid = super.validatePage();
					if (valid)
						checkPathWithSDKs(iNewProjectPage.getLocationPath());
					return valid;
				}
			};
			addPage(iBuildTargetsPage);
		}
	}

	public ISymbianSDK getDefaultSDK() {
		if (iDefaultSDK == null) {
			iDefaultSDK = getDefaultSDK(iTargetProject);
		}
		return iDefaultSDK;
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		if (initPagesConditon()) {
			initPages();
		}
	}

	protected abstract boolean initPagesConditon();

	public List<ISymbianBuildContext> getBuildConfigs() {
		return iBuildConfigs;
	}

	@Override
	public boolean performFinish() {
		int oldAddFilesOption = ProjectUIPlugin.getAddFilesToProjectOption();
		int oldChangedFilesOption = ProjectUIPlugin
				.getChangedFilesInProjectOption();
		ProjectUIPlugin.setAddFilesToProjectOption(2);
		ProjectUIPlugin.setChangedFilesInProjectOption(2);
		ResourceChangeListener.setEnable(false);

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("Starting...", IProgressMonitor.UNKNOWN);
				try {
					if (iTargetProject == null) {
						iTargetProject = createTargetProject(monitor);
					}
					if (iResultMap == null) {
						iResultMap = generateFiles(monitor);
					}
					if (iResultMap != null && iTargetProject != null) {
						writeResultMapToFiles(iTargetProject, monitor);
					} else {
						MessageDialog.openError(getShell(), "Error",
								"Exception was thrown while generating files");
					}
				} catch (Throwable e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			new ProgressMonitorDialog(getShell()).run(true, false, op);
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					AbstractTemplateWizard.class.getName(),
					"Exception was thrown while generating files.", e
							.getTargetException());
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
			MessageDialog.openError(getShell(),
					"Exception was thrown while generating files", e
							.getTargetException().getMessage());
		} finally {
			ProjectUIPlugin.setAddFilesToProjectOption(oldAddFilesOption);
			ProjectUIPlugin
					.setChangedFilesInProjectOption(oldChangedFilesOption);
			ResourceChangeListener.setEnable(true);
		}
		return true;
	}

	@Override
	public boolean performCancel() {
		if (iSelfCreateProject && iTargetProject != null) {
			IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException {
					try {
						iTargetProject.delete(true, null);
					} catch (Throwable e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			};

			try {
				new ProgressMonitorDialog(getShell()).run(true, false, op);
			} catch (InterruptedException e) {
			} catch (InvocationTargetException e) {
				IStatus lStatus = new Status(IStatus.ERROR,
						AbstractTemplateWizard.class.getName(),
						"Exception was thrown while delete temporary project.",
						e.getTargetException());
				CodegenUIPlugin.getDefault().getLog().log(lStatus);
			}

		}
		return super.performCancel();
	}

	public void setSelection(IStructuredSelection currentSelection) {
		selection = currentSelection;
	}

	public IProject createTargetProject(IProgressMonitor monitor)
			throws CoreException {
		if (monitor != null) {
			monitor.subTask("Creating new project");
		}
		if (iTargetProject == null) {
			iTargetProject = ProjectCorePlugin.createProject(iProjectName,
					iProjectLocation);
			iSelfCreateProject = true;
		}
		return iTargetProject;
	}

	protected void setProjectInfo(IProject project, IFile bldInf,
			IProgressMonitor monitor) throws CoreException {
		monitor.subTask("Setting project information");
		if (iShowNewProjectPage) {
			// Convert IProject to ICProject;
			IFolder groupFolder = iTargetProject.getFolder("group");
			if (groupFolder.exists() == false) {
				groupFolder.create(true, true, monitor);
			}
			IFile mainBldInf = groupFolder.getFile("bld.inf");
			if (mainBldInf.exists() == false) {
				String content = "";
				if (bldInf != null) {
					content = abstractBldInf(bldInf);
				}
				mainBldInf.create(new ByteArrayInputStream(content.getBytes()),
						true, monitor);
			}
			ProjectCorePlugin
					.postProjectCreatedActions(iTargetProject, mainBldInf
							.getProjectRelativePath().toString(),
							iBuildConfigs, new ArrayList<String>(), null, null,
							monitor);
		}

		ProjectInfoHelper.setProjectTemplate(project, iTemplateName);
		if (iTestFolderLocation != null) {
			Set<String> testFoldersSet = ProjectInfoHelper.getTestFolders(
					project, iTemplateName);
			if (!testFoldersSet.contains(iTestFolderLocation)) {
				try {
					ProjectInfoHelper.addTestFolders(project, iTemplateName,
							iTestFolderLocation);
				} catch (CoreException e) {
					CodegenUIPlugin.getDefault().getLog().log(e.getStatus());
				}
			}
		}
	}

	private String abstractBldInf(IFile bldInf) throws CoreException {
		boolean inBlock = false;
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(bldInf
				.getContents()));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if (inBlock == false) {
					sb.append(line).append("\n");
				}
				if (line.indexOf("] Begin - Do not remove") > 0) {
					inBlock = true;
				} else if (line.indexOf("] End - Do not remove") > 0) {
					sb.append(line).append("\n");
					inBlock = false;
				}
			}
		} catch (IOException e) {
			IStatus lStatus = new Status(IStatus.ERROR, Parser.class.getName(),
					IStatus.ERROR, "Exception while reading file", e);
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
		}
		return sb.toString();
	}

	private void writeResultMapToFiles(final IProject project,
			IProgressMonitor monitor) throws CoreException, IOException {
		monitor.subTask("Writing new test codes to file");
		Set<IPath> lSourcePathSet = new HashSet<IPath>();
		IFile bldinf = null;
		ICProject lCProject = CoreModel.getDefault().create(iTargetProject);
		Set<IFile> mmpSet = new HashSet<IFile>();
		for (String path : iResultMap.keySet()) {
			IPath newFilePath = new Path(path);
			IFile newFile = project.getFile(newFilePath);
			ChangeFileContent lChangeContent = iResultMap.get(path);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					lChangeContent.getNewContent().getBytes());
			if (newFile.exists()) {
				newFile.setContents(inputStream, true, true, monitor);
			} else {
				createParentFolder(newFile, monitor);
				newFile.create(inputStream, true, monitor);
			}
			String lFileExtension = newFilePath.getFileExtension();
			if ("h".equalsIgnoreCase(lFileExtension)
					|| "cpp".equalsIgnoreCase(lFileExtension)
					|| "hrh".equalsIgnoreCase(lFileExtension)) {
				if (!lCProject.isOnSourceRoot(newFile)) {
					lSourcePathSet.add(newFilePath.uptoSegment(1));
				}
			}
			if (newFilePath.lastSegment().equalsIgnoreCase("bld.inf")) {
				bldinf = newFile;
			} else if (newFilePath.lastSegment().endsWith(".mmp")) {
				mmpSet.add(newFile);
			}
			monitor.worked(1);
		}
		setProjectInfo(project, bldinf, monitor);
		if (bldinf != null) {
			ProjectInfoHelper.addSubBldInfToProject(project, bldinf
					.getProjectRelativePath());
		} else {
			for (IFile mmpFile : mmpSet) {
				ProjectInfoHelper.addMMPFileToProject(project, mmpFile
						.getProjectRelativePath(), iTestMMP);
			}
		}

		if (lSourcePathSet.size() > 0) {
			CoreModel lCoreModel = CoreModel.getDefault();
			if (lCoreModel.isNewStyleProject(iTargetProject)) {
				ArrayList<ICSourceEntry> lSourcePathList = new ArrayList<ICSourceEntry>();
				for (IPath lSourcePath : lSourcePathSet) {
					lSourcePathList.add(new CSourceEntry(lSourcePath, null, 0));
				}
				ICSourceEntry[] lCSourceEntry = lSourcePathList
						.toArray(new ICSourceEntry[0]);
				ICProjectDescription des = CCorePlugin.getDefault()
						.getProjectDescription(iTargetProject, true);
				addEntryToAllCfgs(des, lCSourceEntry, false);
				CCorePlugin.getDefault().setProjectDescription(iTargetProject,
						des, false, monitor);
			} else {
				ArrayList<ISourceEntry> lSourcePathList = new ArrayList<ISourceEntry>();
				for (IPath lSourcePath : lSourcePathSet) {
					lSourcePathList.add(CoreModel.newSourceEntry(lSourcePath));
				}
				ISourceEntry[] lSourceEntry = lSourcePathList
						.toArray(new ISourceEntry[0]);
				CoreModel.getDefault().create(iTargetProject)
						.setRawPathEntries(lSourceEntry, monitor);
			}
		}
		// project.refreshLocal(IResource.DEPTH_ONE, monitor);
	}

	private void addEntryToAllCfgs(ICProjectDescription des,
			ICSourceEntry[] entry, boolean removeProj)
			throws WriteAccessException, CoreException {
		ICConfigurationDescription cfgs[] = des.getConfigurations();
		ICConfigurationDescription aicconfigurationdescription[];
		int j = (aicconfigurationdescription = cfgs).length;
		for (int i = 0; i < j; i++) {
			ICConfigurationDescription cfg = aicconfigurationdescription[i];
			ICSourceEntry entries[] = cfg.getSourceEntries();
			entries = addEntry(entries, entry, removeProj);
			cfg.setSourceEntries(entries);
		}

	}

	private ICSourceEntry[] addEntry(ICSourceEntry entries[],
			ICSourceEntry[] entry, boolean removeProj) {
		Set<ICSourceEntry> set = new HashSet<ICSourceEntry>();
		ICSourceEntry aicsourceentry[];
		int j = (aicsourceentry = entries).length;
		for (int i = 0; i < j; i++) {
			ICSourceEntry se = aicsourceentry[i];
			if (!removeProj || (new Path(se.getValue())).segmentCount() != 1)
				set.add(se);
		}
		for (ICSourceEntry en : entry) {
			set.add(en);
		}
		return (ICSourceEntry[]) set.toArray(new ICSourceEntry[set.size()]);
	}

	private Map<String, ChangeFileContent> generateFiles(
			IProgressMonitor monitor) throws Exception {
		Map<String, ChangeFileContent> lResultMap = new HashMap<String, ChangeFileContent>();
		collectPagesData();

		monitor.subTask("Creating new test codes");

		String output = iTargetProject.getLocation().toOSString();
		if (iTestFolderLocation != null && iTestFolderLocation.length() > 0) {
			output = iTargetProject.getLocation().append(iTestFolderLocation)
					.toOSString();
		}

		CodegenEngine engine = new CodegenEngine(output, iDataMap);
		engine.init();
		engine.setWriteToFile(false);
		Map<String, ChangeFileContent> result = engine.generate(iTemplateName);

		if (iTestFolderLocation != null && iTestFolderLocation.length() > 0) {
			Map<String, ChangeFileContent> new_result = new HashMap<String, ChangeFileContent>();
			IPath testFolder = new Path(iTestFolderLocation);
			for (String path : result.keySet()) {
				new_result.put(testFolder.append(path).toString(), result
						.get(path));
			}
			result = new_result;
		}

		lResultMap.putAll(result);
		monitor.worked(1);
		return lResultMap;
	}

	/**
	 * @return the iTargetProject
	 */
	public IProject getTargetProject() {
		return iTargetProject;
	}

	/**
	 * @param targetProject
	 *            the iTargetProject to set
	 */
	public void setTargetProject(IProject project) {
		iTargetProject = project;
	}

	/**
	 * @return the iShowChooseProjectPage
	 */
	public boolean isShowNewProjectPage() {
		return iShowNewProjectPage;
	}

	/**
	 * @param iShowChooseProjectPage
	 *            the iShowChooseProjectPage to set
	 */
	public void setShowNewProjectPage(boolean showNewProjectPage) {
		this.iShowNewProjectPage = showNewProjectPage;
	}

	protected void initPages() {
		IWizardPage[] pages = getPages();
		for (IWizardPage page : pages) {
			if (page instanceof AbstractTemplateWizardPage) {
				((AbstractTemplateWizardPage) page).initPage(iDataMap);
			}
		}
	}

	protected void collectPagesData() {
		IWizardPage[] pages = getPages();
		for (IWizardPage page : pages) {
			if (page instanceof AbstractTemplateWizardPage) {
				((AbstractTemplateWizardPage) page).collectData();
			}
		}
	}

	public Map<String, Object> getDataMap() {
		return iDataMap;
	}

	private void createParentFolder(IResource resource, IProgressMonitor monitor)
			throws CoreException {
		if (!resource.getParent().exists()) {
			createParentFolder(resource.getParent(), monitor);
			((IFolder) resource.getParent()).create(true, true, monitor);
		}
	}

	public String getTemplateName() {
		return iTemplateName;
	}

	public String getProjectName() {
		return iProjectName;
	}

	public String getProjectLocation() {
		return iProjectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		iProjectLocation = projectLocation;
	}

	public String getTestFolderLocation() {
		return iTestFolderLocation;
	}

	public void setTestFolderLocation(String location) {
		iTestFolderLocation = location;
	}

	/**
	 * @return the testFoldersSet
	 */
	public Set<String> getTestFoldersSet() {
		try {
			if (iTargetProject != null
					&& CarbideBuilderPlugin.getBuildManager().isCarbideProject(
							iTargetProject) && iTargetProject.isAccessible()) {
				return ProjectInfoHelper.getTestFolders(iTargetProject,
						iTemplateName);
			}
		} catch (CoreException e) {
			IStatus lStatus = new Status(
					IStatus.ERROR,
					SUTNewTestWizard.class.getName(),
					"Exception was thrown while accessing project persistent property.",
					e);
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
			MessageDialog.openError(getShell(),
					"Exception was thrown while generating files", e
							.getMessage());
		}
		return null;
	}

	public Map<String, ChangeFileContent> getPreviewResult() {
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("Creating new test codes",
						IProgressMonitor.UNKNOWN);
				try {
					iResultMap = generateFiles(monitor);
				} catch (Throwable e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			new ProgressMonitorDialog(getShell()).run(true, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					AbstractTemplateWizard.class.getName(),
					"Exception was thrown while generating files.", e
							.getTargetException());
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
			MessageDialog.openError(getShell(),
					"Exception was thrown while generating files", e
							.getTargetException().getMessage());
			return null;
		}

		return iResultMap;
	}

	public void setPreviewResult(Map<String, ChangeFileContent> result) {
		iResultMap = result;
	}
}
