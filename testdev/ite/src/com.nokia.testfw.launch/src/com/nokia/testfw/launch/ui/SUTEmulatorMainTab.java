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
package com.nokia.testfw.launch.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.model.ICProject; //import org.eclipse.cdt.debug.core.CDebugUtils;
import org.eclipse.cdt.launch.AbstractCLaunchDelegate;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.EpocEngineHelper;
import com.nokia.carbide.cdt.builder.project.ICarbideBuildConfiguration;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.cdt.internal.debug.launch.ui.EmulationMainTab;
import com.nokia.cdt.internal.debug.launch.ui.Messages;
import com.nokia.testfw.launch.TFWLaunchPlugin;
import com.nokia.testfw.launch.LaunchConfigurationConstants;

public class SUTEmulatorMainTab extends EmulationMainTab {
	protected Combo testDllCombo;
	protected Text testCaseText;

	protected void createHostAppGroup(Composite parent, int colSpan) {
		Composite projComp = new Composite(parent, 0);
		GridLayout projLayout = new GridLayout();
		projLayout.numColumns = 2;
		projLayout.marginHeight = 0;
		projLayout.marginWidth = 0;
		projComp.setLayout(projLayout);
		GridData gd = new GridData(768);
		gd.horizontalSpan = colSpan;
		projComp.setLayoutData(gd);
		hostLabel = new Label(projComp, 0);
		// hostLabel.setText(Messages.getString("EmulationMainTab.2"));
		hostLabel.setText("Test dll:");
		gd = new GridData();
		gd.horizontalSpan = 2;
		hostLabel.setLayoutData(gd);
		hostLabel.setToolTipText(Messages.getString("EmulationMainTab.3"));

		testDllCombo = new Combo(projComp, SWT.DROP_DOWN);
		gd = new GridData(768);
		testDllCombo.setLayoutData(gd);
		testDllCombo.setToolTipText(Messages.getString("EmulationMainTab.3"));
		testDllCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent evt) {
				updateLaunchConfigurationDialog();
			}

		});

		hostBrowse = createPushButton(projComp, Messages
				.getString("EmulationMainTab.4"), null);
		hostBrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent evt) {
				FileDialog dialog = new FileDialog(getShell(), 0);
				dialog.setFilterExtensions(new String[] { "*.dll" });
				dialog.setText(Messages.getString("EmulationMainTab.5"));
				String result = dialog.open();
				if (result != null && (new File(result)).exists()) {
					hostText.setText(new Path(result).lastSegment());
					updateLaunchConfigurationDialog();
				}
			}
		});

		Label lTestCaseLabel = new Label(projComp, 0);
		lTestCaseLabel.setText("Test case:");
		gd = new GridData();
		gd.horizontalSpan = 2;
		lTestCaseLabel.setLayoutData(gd);
		lTestCaseLabel.setToolTipText(Messages.getString("EmulationMainTab.3"));

		testCaseText = new Text(projComp, 2052);
		gd = new GridData(768);
		gd.horizontalSpan = 2;
		testCaseText.setLayoutData(gd);
		testCaseText.setToolTipText(Messages.getString("EmulationMainTab.3"));
		testCaseText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				updateLaunchConfigurationDialog();
			}

		});

		hostText = new Text(projComp, SWT.Hide);
		// this host exe text is invisible, set by parent class
		hostText.setVisible(false);
	}

	public void initializeFrom(ILaunchConfiguration config) {
		super.initializeFrom(config);
		try {
			String pathStr = config
					.getAttribute(
							"com.freescale.cdt.debug.cw.core.settings.DebuggerCommonData.Host App Path",
							"");
			IPath path = new Path(pathStr);
			hostText.setText(path.removeLastSegments(1).append(
					"symbianunittest.exe").toOSString());

			ICProject targetProject = AbstractCLaunchDelegate
					.getCProject(config);
			// ICProject targetProject = CDebugUtils.getCProject(config);

			if (targetProject != null) {
				gatherDlls(targetProject.getProject());
			}

			String dlls = config.getAttribute(
					LaunchConfigurationConstants.DLLNAME, (String) null);
			if (dlls == null) {
				if (testDllCombo.getItemCount() > 0) {
					testDllCombo.select(testDllCombo.getItemCount() - 1);
				}
			} else {
				testDllCombo.setText(dlls);
			}
			String cases = config.getAttribute(
					LaunchConfigurationConstants.TESTCASENAME, (String) null);
			if (cases != null) {
				testCaseText.setText(cases);
			}
		} catch (CoreException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					SUTEmulatorMainTab.class.getName(),
					"Exception was thrown while show Emulator Launch.", e);
			TFWLaunchPlugin.getDefault().getLog().log(lStatus);
		}
	}

	private void gatherDlls(IProject project) {
		if (project != null) {
			ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
					.getProjectInfo(project);
			if (cpi != null) {
				ICarbideBuildConfiguration buildConfig = cpi
						.getDefaultConfiguration();
				List<IPath> exePaths = new ArrayList<IPath>(0);
				List<IPath> mmpPaths = new ArrayList<IPath>(0);
				EpocEngineHelper.getPathToAllExecutables(buildConfig,
						new ArrayList<IPath>(), exePaths,
						new ArrayList<IPath>(), mmpPaths);
				if (exePaths.size() > 0) {
					for (int i = 0; i < exePaths.size(); i++) {
						String fileExt = exePaths.get(i).getFileExtension();
						if (fileExt == null
								|| fileExt.compareToIgnoreCase("dll") != 0)
							continue;
						testDllCombo.add(exePaths.get(i).lastSegment());
					}
				}
			}
		}
	}

	public boolean isValid(ILaunchConfiguration config) {
		if (testDllCombo.getText().trim().length() > 0) {
			setErrorMessage(null);
			return super.isValid(config);
		} else {
			setErrorMessage("must specify the test dll.");
			return false;
		}
	}

	public void performApply(ILaunchConfigurationWorkingCopy config) {
		super.performApply(config);
		config
				.setAttribute(
						"com.freescale.cdt.debug.cw.core.settings.DebuggerCommonData.Host App Path",
						hostText.getText());
		StringBuilder sb = new StringBuilder("-noprompt -o=txt -t=");
		sb.append(testDllCombo.getText());
		if (testCaseText.getText().trim().length() > 0) {
			sb.append(" -c=").append(testCaseText.getText());
		}
		config.setAttribute("org.eclipse.cdt.launch.PROGRAM_ARGUMENTS", sb
				.toString());
		config.setAttribute(LaunchConfigurationConstants.DLLNAME, testDllCombo
				.getText());
		config.setAttribute(LaunchConfigurationConstants.TESTCASENAME,
				testCaseText.getText());
	}
}
