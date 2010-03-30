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
package com.nokia.testfw.codegen.ui.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.core.settings.model.ICStorageElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.DefaultMMPViewConfiguration;
import com.nokia.carbide.cdt.builder.DefaultViewConfiguration;
import com.nokia.carbide.cdt.builder.EMMPPathContext;
import com.nokia.carbide.cdt.builder.EpocEngineHelper;
import com.nokia.carbide.cdt.builder.MMPViewPathHelper;
import com.nokia.carbide.cdt.builder.project.ICarbideBuildConfiguration;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.epoc.engine.BldInfDataRunnableAdapter;
import com.nokia.carbide.cpp.epoc.engine.BldInfViewRunnableAdapter;
import com.nokia.carbide.cpp.epoc.engine.EpocEnginePlugin;
import com.nokia.carbide.cpp.epoc.engine.MMPDataRunnableAdapter;
import com.nokia.carbide.cpp.epoc.engine.model.IView;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IBldInfData;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IBldInfView;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IMMPReference;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IMakMakeReference;
import com.nokia.carbide.cpp.epoc.engine.model.mmp.IMMPData;
import com.nokia.carbide.cpp.epoc.engine.preprocessor.AcceptedNodesViewFilter;
import com.nokia.carbide.cpp.epoc.engine.preprocessor.AllNodesViewFilter;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;

public class ProjectInfoHelper {

	public final static String USERINCLUDE = "USERINCLUDE";
	public final static String SYSTEMINCLUDE = "SYSTEMINCLUDE";
	public final static String LIBRARY = "LIBRARY";
	public final static String TEMPLATE = "com.nokia.testfw.codegen.Template";
	public final static String NAME = "name";
	public final static String LOCATION = "location";

	public static Map<String, Set<String>> getBuildInfoMap(IProject project) {
		Set<String> lUserIncludeSet = new TreeSet<String>();
		Set<String> lSystemIncludeSet = new TreeSet<String>();
		Set<String> lLibrarySet = new TreeSet<String>();

		ICarbideProjectInfo carbideprojectinfo = CarbideBuilderPlugin
				.getBuildManager().getProjectInfo(project);

		if (carbideprojectinfo != null) {
			getProjectInclude(carbideprojectinfo, lUserIncludeSet,
					lSystemIncludeSet, lLibrarySet);
		}

		Map<String, Set<String>> lBuildInfoMap = new HashMap<String, Set<String>>();
		lBuildInfoMap.put(USERINCLUDE, lUserIncludeSet);
		lBuildInfoMap.put(SYSTEMINCLUDE, lSystemIncludeSet);
		lBuildInfoMap.put(LIBRARY, lLibrarySet);
		return lBuildInfoMap;

	}

	public static void getProjectInclude(final ICarbideProjectInfo info,
			final Set<String> userIncludeSet,
			final Set<String> systemIncluseSet, final Set<String> librarySet) {
		final ICarbideBuildConfiguration buildConfig = info
				.getDefaultConfiguration();
		final IPath epocRoot = EpocEngineHelper.getEpocRootForProject(info
				.getProject());
		EpocEnginePlugin.runWithBldInfData(info
				.getWorkspaceRelativeBldInfPath(),
				new DefaultViewConfiguration(info.getProject(), buildConfig,
						new AcceptedNodesViewFilter()),
				new BldInfDataRunnableAdapter() {

					public Object run(IBldInfData data) {
						List<IMakMakeReference> maks = data
								.getMakMakeReferences();
						for (int i = 0; i < maks.size(); i++) {
							IMakMakeReference mak = maks.get(i);
							if (!(mak instanceof IMMPReference)) {
								continue;
							}
							IMMPReference mmp = (IMMPReference) mak;
							IPath workspaceRelativeMMPPath = (new Path(info
									.getProject().getName())).append(mmp
									.getPath());
							EpocEnginePlugin.runWithMMPData(
									workspaceRelativeMMPPath,
									new DefaultMMPViewConfiguration(info
											.getProject(), buildConfig,
											new AcceptedNodesViewFilter()),
									new MMPDataRunnableAdapter() {

										public Object run(IMMPData mmpData) {
											MMPViewPathHelper helper = new MMPViewPathHelper(
													mmpData, epocRoot);
											for (Iterator<IPath> iterator = mmpData
													.getUserIncludes()
													.iterator(); iterator
													.hasNext();) {
												IPath path = iterator.next();
												IPath fullPath = helper
														.convertMMPToFilesystem(
																EMMPPathContext.USERINCLUDE,
																path);
												if (fullPath != null)
													userIncludeSet.add(fullPath
															.toOSString());
											}
											for (Iterator<IPath> iterator = mmpData
													.getSystemIncludes()
													.iterator(); iterator
													.hasNext();) {
												IPath includePath = iterator
														.next();
												systemIncluseSet
														.add(includePath
																.toString());
											}
											librarySet.addAll(mmpData
													.getLibraries());
											return null;
										}
									});
						}

						return null;
					}
				});
	}

	public static void addMMPFileToProject(final IProject project,
			final IPath projectRelativeMmpPath, final boolean isTestMMP) {
		final ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
				.getProjectInfo(project);
		if (cpi == null || cpi.getDefaultConfiguration() == null) {
			return;
		} else {
			EpocEnginePlugin.runWithBldInfView(cpi
					.getWorkspaceRelativeBldInfPath(),
					new DefaultViewConfiguration(project, null,
							new AllNodesViewFilter()),
					new BldInfViewRunnableAdapter() {
						public Object run(IBldInfView infView) {
							List<IMakMakeReference> refs = isTestMMP ? infView
									.getTestMakMakeReferences() : infView
									.getMakMakeReferences();
							// check if the test project mmp already exists in
							// the bld.inf file
							for (IMakMakeReference ref : refs) {
								if (ref.getPath()
										.equals(projectRelativeMmpPath)) {
									return null;
								}
							}

							IMMPReference newRef = infView.createMMPReference();
							newRef.setPath(projectRelativeMmpPath);
							refs.add(newRef);
							IFile file = project.getFile(cpi
									.getProjectRelativeBldInfPath());
							commitStanza(infView, file);
							return null;
						}
					});
			return;
		}
	}

	public static void addSubBldInfToProject(IProject project,
			IPath projectRelativeBldInfPath) throws CoreException, IOException {
		final ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
				.getProjectInfo(project);
		if (cpi == null || cpi.getDefaultConfiguration() == null) {
			return;
		} else {
			IPath mainBldInfPath = cpi.getProjectRelativeBldInfPath();
			if (!mainBldInfPath.equals(projectRelativeBldInfPath)) {
				// check if the test bld.inf already exists in the main bld.inf
				IPath base = mainBldInfPath.uptoSegment(1);
				if (base.equals(mainBldInfPath)) {
					base = Path.EMPTY;
				}
				IPath relativePath = makeRelativeTo(base,
						projectRelativeBldInfPath);
				String include = "#include \"" + relativePath.toString() + "\"";

				IFile mainBldInfFile = project.getFile(mainBldInfPath);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(mainBldInfFile.getContents()));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.replaceAll("\\s", " ").trim();
					if (line.matches("^" + include)) {
						return;
					}
				}

				include = "\n" + include;
				mainBldInfFile.appendContents(new ByteArrayInputStream(include
						.getBytes()), true, true, null);
			}
		}
	}

	public static IPath makeRelativeTo(IPath base, IPath target) {
		if (target.getDevice() != base.getDevice()
				&& (target.getDevice() == null || !target.getDevice()
						.equalsIgnoreCase(base.getDevice())))
			return target;
		int commonLength = target.matchingFirstSegments(base);
		int differenceLength = base.segmentCount() - commonLength;
		int newSegmentLength = (differenceLength + target.segmentCount())
				- commonLength;
		if (newSegmentLength == 0) {
			return Path.EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < differenceLength; i++) {
				sb.append("..").append(File.separator);
			}
			String[] segments = target.segments();
			for (int i = commonLength; i < target.segmentCount(); i++) {
				sb.append(segments[i]).append(File.separator);
			}
			return (new Path(sb.toString())).removeTrailingSeparator();
		}
	}

	@SuppressWarnings("unchecked")
	private static void commitStanza(IView view, IFile file) {
		if (confirmEdit(file))
			do
				try {
					view.commit();
					break;
				} catch (IllegalStateException _ex) {
					if (!view.merge())
						view.revert();
				}
			while (true);
		else
			view.revert();
	}

	private static boolean confirmEdit(IFile file) {
		IWorkbench workbench = null;
		try {
			workbench = PlatformUI.getWorkbench();
		} catch (IllegalStateException _ex) {
			return true;
		}
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		if (window == null) {
			IWorkbenchWindow windows[] = workbench.getWorkbenchWindows();
			window = windows[0];
		}
		org.eclipse.swt.widgets.Shell shell = window.getShell();
		IStatus status = ResourcesPlugin.getWorkspace().validateEdit(
				new IFile[] { file }, shell);
		return status == null || status.isOK();
	}

	public static String getProjectTemplate(IProject project)
			throws CoreException {
		return getProjectStorage(project, TEMPLATE, NAME);
	}

	public static void setProjectTemplate(IProject project, String template)
			throws CoreException {
		setProjectStorage(project, TEMPLATE, NAME, template);
	}

	public static String getProjectStorage(IProject project, String block,
			String key) throws CoreException {
		String id = CodegenUIPlugin.getDefault().getBundle().getSymbolicName();
		ICProjectDescription lCPD = CCorePlugin.getDefault()
				.getProjectDescription(project, true);
		ICStorageElement element = lCPD.getStorage(id, true);
		ICStorageElement[] blockArray = element.getChildrenByName(block);
		if (blockArray != null) {
			for (ICStorageElement blockElement : blockArray) {
				String info = blockElement.getAttribute(key);
				if (info != null && info.trim().length() > 0) {
					return info;
				}
			}
		}
		return null;
	}

	public static void setProjectStorage(IProject project, String block,
			String key, String value) throws CoreException {
		String id = CodegenUIPlugin.getDefault().getBundle().getSymbolicName();
		ICProjectDescription lCPD = CCorePlugin.getDefault()
				.getProjectDescription(project, true);
		ICStorageElement element = lCPD.getStorage(id, true);
		ICStorageElement[] blockArray = element.getChildrenByName(block);
		ICStorageElement targetBlock;
		if (blockArray != null && blockArray.length > 0) {
			targetBlock = blockArray[0];
		} else {
			targetBlock = element.createChild(block);
		}
		targetBlock.setAttribute(key, value);
		lCPD.importStorage(id, element);
		CCorePlugin.getDefault().getProjectDescriptionManager()
				.setProjectDescription(project, lCPD, true,
						new NullProgressMonitor());
	}

	public static Set<String> getTestFolders(IProject project, String template)
			throws CoreException {
		Set<String> lTestFolderSet = new LinkedHashSet<String>();
		if (project != null && template != null) {
			String folders = getProjectStorage(project, template, LOCATION);
			if (folders != null) {
				String[] folderArray = folders.trim().split(",");
				for (String f : folderArray) {
					lTestFolderSet.add(f.trim());
				}
			}
		}
		return lTestFolderSet;
	}

	public static void addTestFolders(IProject project, String template,
			String testfolder) throws CoreException {
		Set<String> folderSet = getTestFolders(project, template);
		if (folderSet.contains(testfolder.trim())) {
			return;
		}
		folderSet.add(testfolder);
		StringBuilder sb = new StringBuilder();
		for (String folder : folderSet) {
			if (sb.length() == 0) {
				sb.append(folder);
			} else {
				sb.append(",").append(folder);
			}
		}
		setProjectStorage(project, template, LOCATION, sb.toString());
	}

	public static void removeTestFolders(IProject project, String template,
			String testfolder) throws CoreException {
		Set<String> folderSet = getTestFolders(project, template);
		if (!folderSet.contains(testfolder.trim())) {
			return;
		}
		folderSet.remove(testfolder);
		StringBuilder sb = new StringBuilder();
		for (String folder : folderSet) {
			if (sb.length() == 0) {
				sb.append(folder);
			} else {
				sb.append(",").append(folder);
			}
		}
		setProjectStorage(project, template, LOCATION, sb.toString());
	}

	public static void renameTestFolders(IProject project, String template,
			String oldfolder, String newfolder) throws CoreException {
		Set<String> folderSet = getTestFolders(project, template);
		if (!folderSet.contains(oldfolder)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (String folder : folderSet) {
			if (folder.equals(oldfolder)) {
				folder = newfolder;
			}
			if (sb.length() == 0) {
				sb.append(folder);
			} else {
				sb.append(",").append(folder);
			}
		}
		setProjectStorage(project, template, LOCATION, sb.toString());
	}
}
