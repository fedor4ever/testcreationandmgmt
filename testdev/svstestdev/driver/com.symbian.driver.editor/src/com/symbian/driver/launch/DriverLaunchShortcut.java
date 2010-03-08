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


package com.symbian.driver.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import com.symbian.driver.presentation.DriverEditorPlugin;
import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Task;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.util.DriverResourceImpl;
import com.symbian.driver.utils.EclipseLogger;
import com.symbian.driver.utils.FileUtils;
import com.symbian.driver.utils.UIUtils;

public class DriverLaunchShortcut implements ILaunchShortcut {

	/**
	 * Launch shortcut from the workbench selection
	 */
	public void launch(ISelection selection, String mode) {

		// check we are comming from a structured selection
		if (selection instanceof IStructuredSelection) {
			Object firstSelection = ((IStructuredSelection) selection).getFirstElement();

			ArrayList<String> lList = new ArrayList<String>();
			IFile lSelection = null;
			if (firstSelection instanceof IFile) {
				// The selection is a file
				lSelection = (IFile) firstSelection;

				if (lSelection.getFileExtension().equalsIgnoreCase(DriverLaunchConstants.DRIVER)) {
					// the selected file is a driver file
					lList.add(0, lSelection.getLocation().toFile().getAbsolutePath());
				} else {
					// the selection is not a .driver file
					IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR, IStatus.ERROR,
							"The resource selected is not a driver file.", null);

					ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher", "TestDriver failed to launch...",
							lStatus);
					return;
				}
			} else if (firstSelection instanceof IProject) {
				// The slection is a project
				// search for the project driver files
				IProject lProject = (IProject) firstSelection;
				lList = FileUtils.scanDirectory(lProject, DriverLaunchConstants.DRIVER);
			} else {
				// search for all driver files in the workspace
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				lList = FileUtils.scanDirectory(root, DriverLaunchConstants.DRIVER);
			}

			// now we might have a list of driver files..
			int lDriverCount = lList.size();
			File lDriverCandidate;
			if (lDriverCount < 1) {
				IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR, IStatus.ERROR, "Launch failed", null);

				ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher",
						"There are no driver files to launch...", lStatus);
				return;
			}
			if (lDriverCount > 1) {
				// the user must use one file.
				try {
					lDriverCandidate = chooseDriverFile(lList);
				} catch (LaunchCancelledByUserException e) {
					return;
				}
			} else {
				lDriverCandidate = new File(lList.get(0));
			}

			// At this point we have a driver file

			// get a configuration for this driver file

			try {
				ILaunchConfiguration config = findLaunchConfiguration(lDriverCandidate, mode);
				if (config != null) {
					config.launch(mode, null);
				}
			} catch (CoreException lException) {
				IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR, IStatus.ERROR, "Launch failed", lException);

				ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher", "TestDriver failed to launch...",
						lStatus);
				return;
			} catch (LaunchCancelledByUserException e) {
				return;
			}
		}
	}
	
	/**
	 * chooseDriverFile: gives the user a chance to chose a driver file or cancel the launch
	 * @param fileList
	 * @return File : The choosen driver file
	 * @throws LaunchCancelledByUserException
	 */

	private File chooseDriverFile(List fileList) throws LaunchCancelledByUserException {
		IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(UIUtils.getShell(), labelProvider);
		dialog.setElements(fileList.toArray());
		dialog.setTitle("Select TestDriver Configuration");
		dialog.setMessage("Select TestDriver run configuration");
		dialog.setMultipleSelection(false);
		int result = dialog.open();
		labelProvider.dispose();
		if (result == Window.OK) {
			return new File((String)dialog.getFirstResult());
		}
		throw new LaunchCancelledByUserException();
	}

	/**
	 * chooseConfiguration: gives the user a chance to choose a configuration or cancel the luanch
	 * @param configList
	 * @return ILuanchConfiguration : the choosen configuration.
	 * @throws LaunchCancelledByUserException
	 */
	private ILaunchConfiguration chooseConfiguration(List configList) throws LaunchCancelledByUserException {
		IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(UIUtils.getShell(), labelProvider);
		dialog.setElements(configList.toArray());
		dialog.setTitle("Select TestDriver Configuration");
		dialog.setMessage("Select TestDriver run configuration");
		dialog.setMultipleSelection(false);
		int result = dialog.open();
		labelProvider.dispose();
		if (result == Window.OK) {
			return (ILaunchConfiguration) dialog.getFirstResult();
		}
		throw new LaunchCancelledByUserException();
	}

	/**
	 * findLaunchConfiguration : File a configuration for a given driver file
	 * 
	 * @param :
	 *            File aFile : a driver file
	 * @param :
	 *            String aMode : a run mode
	 * @return : a ILaunchConfiguration if one exists for the driver file or
	 *         null
	 * @throws :
	 *             CoreException : if it can not browse the existing
	 *             configurations
	 * 
	 */
	private ILaunchConfiguration findLaunchConfiguration(File aFile, String mode) throws CoreException,
			LaunchCancelledByUserException {
		ILaunchConfigurationType configType = getDriverConfigType();
		List<ILaunchConfiguration> configs = new ArrayList<ILaunchConfiguration>();
		if (configType.supportsMode(mode)) {
			// get all existing configurations
			ILaunchConfiguration[] allConfigs = getLaunchManager().getLaunchConfigurations(configType);
			for (int i = 0; i < allConfigs.length; i++) {
				ILaunchConfiguration config = allConfigs[i];
				if (config.getAttribute(DriverLaunchConstants.DRIVER, "").equals(aFile.getAbsolutePath())) {
					configs.add(config);
				}
			}
		}

		switch (configs.size()) {
		case 0:
			// create a new config
			return createConfiguration(aFile);
		case 1:
			// we have one configuration
			return (ILaunchConfiguration) configs.get(0);
		default:
			// the user must choose a configuration
			return chooseConfiguration(configs);
		}
	}

	/**
	 * launch from an editor selection
	 */
	public void launch(IEditorPart editor, String mode) {
		IEditorInput input = editor.getEditorInput();
		ISelection selection = new StructuredSelection(input.getAdapter(IFile.class));
		launch(selection, mode);
	}

	private ILaunchConfiguration createConfiguration(File aFile) {
		ILaunchConfiguration config = null;
		try {
			ILaunchConfigurationType configType = getDriverConfigType();
			ILaunchConfigurationWorkingCopy wc = configType.newInstance(null, getLaunchManager()
					.generateUniqueLaunchConfigurationNameFrom(aFile.getName()));
			// set the driver file
			wc.setAttribute(DriverLaunchConstants.DRIVER, aFile.getAbsolutePath());
			// set the suite name to the root of the driver
			URI lURI = URI.createFileURI(aFile.getAbsolutePath());
			ResourceSet iResourceSet = ResourceLoader.getResourceSet();
			DriverResourceImpl lResource = (DriverResourceImpl) iResourceSet.getResource(lURI.trimFragment(), true);
			
			Task lTask = null;
			
			DocumentRoot lDocRoot = (DocumentRoot) lResource.getContents().get(0);
			if (lDocRoot != null) {
				lTask = lDocRoot.getDriver().getTask();
			}
						
			if (lTask == null) {
				IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR, IStatus.ERROR, "Launch failed", null);

				ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher", "Driver file " + lURI + " seems to empty...",
						lStatus);
				return null;
			}
			wc.setAttribute(DriverLaunchConstants.ENTRY_POINT_ADDRESS, lTask.getName());
			config = wc.doSave();
		} catch (CoreException ce) {
			IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR, IStatus.ERROR, "Launch failed", ce);

			ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher", "Error : see details...",
					lStatus);
			return null;
		}
		return config;
	}

	private ILaunchConfigurationType getDriverConfigType() {

		return getLaunchManager().getLaunchConfigurationType(DriverLaunchConstants.LAUNCH_CONFIG_TYPE);

	}

	private ILaunchManager getLaunchManager() {
		return DebugPlugin.getDefault().getLaunchManager();
	}

	public class LaunchCancelledByUserException extends Exception {
		private static final long serialVersionUID = 1L;
	}

}
