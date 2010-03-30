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
package com.nokia.testfw.codegen.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.nokia.testfw.codegen.ui.parser.ProjectInfoHelper;

public class ResourceChangeListener implements IResourceChangeListener {

	private static boolean enable = true;

	/**
	 * @return the enable
	 */
	public static boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public static void setEnable(boolean enable) {
		ResourceChangeListener.enable = enable;
	}

	public void resourceChanged(IResourceChangeEvent event) {

		if (enable == false)
			return;

		if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
			return;
		}
		IResourceDelta projects[] = event.getDelta().getAffectedChildren();
		if (projects.length <= 0) {
			return;
		}

		for (IResourceDelta projectDelta : projects) {
			if ((projectDelta.getFlags() & IResourceDelta.OPEN) != 0
					|| projectDelta.getKind() == IResourceDelta.REMOVED
					|| (projectDelta.getFlags() & IResourceDelta.MARKERS) != 0
					|| (projectDelta.getFlags() & IResourceDelta.DESCRIPTION) != 0)
				continue;
			IResource resource = projectDelta.getResource();
			if (resource == null || !(resource instanceof IProject))
				continue;
			IProject project = (IProject) resource;
			try {
				if (!project.isAccessible()
						|| !project
								.hasNature("com.nokia.carbide.cdt.builder.carbideCPPBuilderNature"))
					continue;
			} catch (CoreException e) {
				CodegenUIPlugin.getDefault().getLog().log(e.getStatus());
			}
			visitChildren(projectDelta);
		}
	}

	private void visitChildren(IResourceDelta delta) {
		IResourceDelta addedChildren[] = delta
				.getAffectedChildren(IResourceDelta.ADDED);
		if (addedChildren.length > 0) {
			if (addedChildren.length == 1
					&& (addedChildren[0].getFlags() & IResourceDelta.MOVED_FROM) != 0) {
				IResourceDelta removedDeltaChildren[] = getDeltaChildren(delta,
						true);
				if (removedDeltaChildren.length == 1
						&& (removedDeltaChildren[0].getFlags() & IResourceDelta.MOVED_TO) != 0) {
					IResource oldResource = removedDeltaChildren[0]
							.getResource();
					IResource newResource = addedChildren[0].getResource();
					if (oldResource instanceof IFolder
							&& newResource instanceof IFolder) {
						testFolderRenamed((IFolder) oldResource,
								(IFolder) newResource);
					}
					return;
				}
			}
		}

		IResourceDelta removedChildren[] = delta
				.getAffectedChildren(IResourceDelta.REMOVED);
		if (removedChildren.length > 0) {
			List<IFolder> removedIFolders = new ArrayList<IFolder>(0);
			for (IResourceDelta child : removedChildren) {
				IResource resource = child.getResource();
				if (resource != null && (resource instanceof IFolder))
					removedIFolders.add((IFolder) resource);
			}

			if (removedIFolders.size() > 0)
				testFolderDeleted(removedIFolders.toArray(new IFolder[0]));
		}

		IResourceDelta changedChildren[] = delta
				.getAffectedChildren(IResourceDelta.CHANGED);
		if (changedChildren.length > 0)
			if (changedChildren.length == 1) {
				IResourceDelta child = changedChildren[0];
				if (child.getAffectedChildren().length > 0)
					visitChildren(child);
			} else {
				boolean rename = false;
				if (changedChildren.length == 2) {
					IResourceDelta oldFiles[] = changedChildren[0]
							.getAffectedChildren(IResourceDelta.REMOVED);
					IResourceDelta newFiles[] = changedChildren[1]
							.getAffectedChildren(IResourceDelta.ADDED);
					if (oldFiles.length == 0 && newFiles.length == 0) {
						oldFiles = changedChildren[1]
								.getAffectedChildren(IResourceDelta.REMOVED);
						newFiles = changedChildren[0]
								.getAffectedChildren(IResourceDelta.ADDED);
					}
					for (IResourceDelta oldDelta : oldFiles) {
						String oldName = oldDelta.getFullPath().lastSegment();
						for (IResourceDelta newDelta : newFiles) {
							if (!newDelta.getFullPath().lastSegment().equals(
									oldName))
								continue;
							IResource oldResource = oldDelta.getResource();
							IResource newResource = newDelta.getResource();
							if (oldResource instanceof IFolder
									&& newResource instanceof IFolder) {
								testFolderRenamed((IFolder) oldResource,
										(IFolder) newResource);
							}
							rename = true;
							break;
						}

					}
				}
				if (!rename) {
					List<IFolder> removedFolders = new ArrayList<IFolder>(0);
					for (IResourceDelta child : changedChildren) {
						IResourceDelta removedDeltaChildren[] = getDeltaChildren(
								child, true);
						for (IResourceDelta removedChild : removedDeltaChildren) {
							IResource resource = removedChild.getResource();
							if (resource != null
									&& (resource instanceof IFolder))
								removedFolders.add((IFolder) resource);
						}
					}

					if (removedFolders.size() > 0)
						testFolderDeleted(removedFolders
								.toArray(new IFolder[0]));
				}
			}
	}

	private IResourceDelta[] getDeltaChildren(IResourceDelta delta,
			boolean removed) {
		List<IResourceDelta> deltaChildren = new ArrayList<IResourceDelta>();
		IResourceDelta airesourcedelta[] = delta
				.getAffectedChildren(removed ? IResourceDelta.REMOVED
						: IResourceDelta.ADDED);
		for (IResourceDelta child : airesourcedelta) {
			deltaChildren.add(child);
		}

		airesourcedelta = delta.getAffectedChildren(IResourceDelta.CHANGED);
		for (IResourceDelta child : airesourcedelta) {
			IResourceDelta[] childresourcedelta = getDeltaChildren(child,
					removed);
			for (IResourceDelta child2 : childresourcedelta) {
				deltaChildren.add(child2);
			}
		}

		return (IResourceDelta[]) deltaChildren
				.toArray(new IResourceDelta[deltaChildren.size()]);
	}

	private void testFolderRenamed(final IFolder oldFolder,
			final IFolder newFolder) {
		final IProject oldProject = oldFolder.getProject();
		WorkspaceJob job = new WorkspaceJob(
				"Update test folder informaton (rename)") {

			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				try {
					String template = ProjectInfoHelper
							.getProjectTemplate(oldProject);
					Set<String> testFolders = ProjectInfoHelper.getTestFolders(
							oldProject, template);
					if (testFolders.contains(oldFolder.getProjectRelativePath()
							.toString())) {
						ProjectInfoHelper.renameTestFolders(oldProject,
								template, oldFolder.getProjectRelativePath()
										.toString(), newFolder
										.getProjectRelativePath().toString());
					}
				} catch (CoreException e) {
					CodegenUIPlugin.getDefault().getLog().log(e.getStatus());
				}
				return Status.OK_STATUS;
			}

		};

		job.setRule(oldProject.getWorkspace().getRoot());
		job.setUser(true);
		job.schedule();
		return;
	}

	private void testFolderDeleted(final IFolder[] folders) {
		if (folders.length > 0) {
			final IProject project = folders[0].getProject();

			WorkspaceJob job = new WorkspaceJob(
					"Update test folder informaton (delete)") {

				public IStatus runInWorkspace(IProgressMonitor monitor)
						throws CoreException {
					try {
						String template = ProjectInfoHelper
								.getProjectTemplate(project);
						Set<String> testFolders = ProjectInfoHelper
								.getTestFolders(project, template);
						for (IFolder folder : folders) {
							String folderPathStr = folder
									.getProjectRelativePath().toString();
							if (testFolders.contains(folderPathStr)) {
								ProjectInfoHelper.removeTestFolders(project,
										template, folderPathStr);
							}
						}
					} catch (CoreException e) {
						CodegenUIPlugin.getDefault().getLog()
								.log(e.getStatus());
						return e.getStatus();
					}
					return Status.OK_STATUS;
				}

			};

			job.setRule(project.getWorkspace().getRoot());
			job.setUser(true);
			job.schedule();
			return;
		}
	}

}
