package com.nokia.testfw.test.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.ICarbideBuildManager;
import com.nokia.carbide.cpp.internal.api.sdk.SBSv2Utils;
import com.nokia.carbide.cpp.project.core.ProjectCorePlugin;
import com.nokia.carbide.cpp.sdk.core.ISDKManager;
import com.nokia.carbide.cpp.sdk.core.ISymbianBuildContext;
import com.nokia.carbide.cpp.sdk.core.ISymbianSDK;
import com.nokia.carbide.cpp.sdk.core.SDKCorePlugin;

public class ProjectUtils {

	public static IProject getTargetProject(String projectName) {
		ICarbideBuildManager iBuildManager = CarbideBuilderPlugin
				.getBuildManager();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		if (project.exists() && iBuildManager.isCarbideProject(project)) {
			return project;
		}
		return null;
	}

	public static void selectProject(IProject project) {
		IViewPart part = null;
		// = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		// .getActivePage().findView(ProjectExplorer.VIEW_ID);
		try {
			part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ProjectExplorer.VIEW_ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		if (part != null) {
			((CommonNavigator) part).selectReveal(new StructuredSelection(
					project));
		}
	}

	public static void removeTargetProject(String projectName) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectName);
		if (project.exists()) {
			try {
				project.delete(true, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	public static IProject createTargetProject(String projectName,
			String projectPath, String projectRelativeBldInfPath)
			throws CoreException {

		List<ISymbianBuildContext> choosenSDKs = new ArrayList<ISymbianBuildContext>();
		boolean sbsv2 = false;

		Object value = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell().getData("useSBSv2Builder");
		if (value != null && (value instanceof Boolean)) {
			sbsv2 = ((Boolean) value).booleanValue();
		}

		ISDKManager sdkMgr = SDKCorePlugin.getSDKManager();
		List<ISymbianSDK> sdkList = sdkMgr.getSDKList();
		if (sdkList == null)
			return null;
		List<ISymbianSDK> sdkListCopy = new ArrayList<ISymbianSDK>();
		for (Iterator<ISymbianSDK> iterator = sdkList.iterator(); iterator
				.hasNext();) {
			ISymbianSDK currSDK = (ISymbianSDK) iterator.next();
			if (currSDK.isEnabled())
				sdkListCopy.add(currSDK);
		}

		if (sbsv2)
			sdkListCopy = SBSv2Utils.getSupportedSDKs(sdkListCopy);

		for (Iterator<ISymbianSDK> iter = sdkListCopy.iterator(); iter
				.hasNext();) {
			ISymbianSDK sdk = (ISymbianSDK) iter.next();
			List<ISymbianBuildContext> configurations = sbsv2 ? SBSv2Utils
					.getFilteredSBSv2BuildContexts(sdk) : sdk
					.getFilteredBuildConfigurations();
			for (Iterator<ISymbianBuildContext> iterator = configurations
					.iterator(); iterator.hasNext();) {
				ISymbianBuildContext config = (ISymbianBuildContext) iterator
						.next();
				choosenSDKs.add(config);
			}
		}

		IPath destPath = ResourcesPlugin.getWorkspace().getRoot().getLocation()
				.append(projectName);
		IFileStore destStore = EFS.getLocalFileSystem().getStore(destPath);
		destStore.delete(EFS.NONE, null);
		IFileStore store = EFS.getLocalFileSystem().fromLocalFile(
				new File(projectPath));
		store.copy(destStore, EFS.OVERWRITE, null);

		IProject createdProject;
		createdProject = ProjectCorePlugin.createProject(projectName, destPath
				.toOSString());

		ProjectCorePlugin.postProjectCreatedActions(createdProject,
				projectRelativeBldInfPath, choosenSDKs,
				new ArrayList<String>(), null, null, new NullProgressMonitor());

		return createdProject;
	}
}
