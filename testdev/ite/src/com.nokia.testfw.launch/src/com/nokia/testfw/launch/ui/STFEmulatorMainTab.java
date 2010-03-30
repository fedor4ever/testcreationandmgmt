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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.nokia.carbide.cdt.builder.DefaultViewConfiguration;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.epoc.engine.BldInfViewRunnableAdapter;
import com.nokia.carbide.cpp.epoc.engine.EpocEnginePlugin;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IBldInfView;
import com.nokia.carbide.cpp.epoc.engine.model.bldinf.IExport;
import com.nokia.carbide.cpp.epoc.engine.preprocessor.AllNodesViewFilter;
import com.nokia.cdt.internal.debug.launch.ui.EmulationMainTab;
import com.nokia.cdt.internal.debug.launch.ui.Messages;
import com.nokia.testfw.launch.TFWLaunchPlugin;
import com.nokia.testfw.launch.LaunchConfigurationConstants;

public class STFEmulatorMainTab extends EmulationMainTab {
	protected Combo testScriptCombo;
	private ICProject targetProject;

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
		hostLabel.setText("Test script on device: ");
		gd = new GridData();
		gd.horizontalSpan = 2;
		hostLabel.setLayoutData(gd);
		hostLabel.setToolTipText(Messages.getString("EmulationMainTab.3"));

		testScriptCombo = new Combo(projComp, SWT.DROP_DOWN);
		gd = new GridData(768);
		testScriptCombo.setLayoutData(gd);
		testScriptCombo
				.setToolTipText(Messages.getString("EmulationMainTab.3"));
		testScriptCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent evt) {
				updateLaunchConfigurationDialog();
			}

		});

		hostBrowse = createPushButton(projComp, Messages
				.getString("EmulationMainTab.4"), null);
		hostBrowse.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent evt) {
				FileDialog dialog = new FileDialog(getShell(), 0);
				dialog.setFilterExtensions(new String[] { "*.cfg" });
				dialog.setText(Messages.getString("EmulationMainTab.5"));
				dialog.setFilterPath(CarbideBuilderPlugin.getBuildManager()
						.getProjectInfo(targetProject.getProject())
						.getDefaultConfiguration().getSDK().getEPOCROOT());
				String result = dialog.open();
				if (result != null && (new File(result)).exists()) {
					hostText.setText(new Path(result).lastSegment());
					updateLaunchConfigurationDialog();
				}
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
			hostText.setText(path.removeLastSegments(1).append("stf.exe")
					.toOSString());

			targetProject = AbstractCLaunchDelegate.getCProject(config);
			// ICProject targetProject = CDebugUtils.getCProject(config);

			if (targetProject != null) {
				Map<String, String> scriptMap = getScripts(targetProject
						.getProject());
				for (String script : scriptMap.keySet()) {
					testScriptCombo.add(script);
					testScriptCombo.setData(script, scriptMap.get(script));
				}
			}

			String script = config.getAttribute(
					LaunchConfigurationConstants.SCRIPT_DEVICE_PATH,
					(String) null);
			if (script == null) {
				if (testScriptCombo.getItemCount() > 0) {
					testScriptCombo.select(0);
				}
			} else {
				testScriptCombo.setText(script);
			}
		} catch (CoreException e) {
			IStatus lStatus = new Status(IStatus.ERROR,
					STFEmulatorMainTab.class.getName(),
					"Exception was thrown while show Emulator Launch.", e);
			TFWLaunchPlugin.getDefault().getLog().log(lStatus);
		}
	}

	private Map<String, String> getScripts(IProject project) {
		final TreeMap<String, String> lScriptMap = new TreeMap<String, String>();
		if (project != null) {
			ICarbideProjectInfo cpi = CarbideBuilderPlugin.getBuildManager()
					.getProjectInfo(project);
			final IPath projPath = project.getLocation();
			if (cpi != null) {
				EpocEnginePlugin.runWithBldInfView(cpi
						.getWorkspaceRelativeBldInfPath(),
						new DefaultViewConfiguration(project, null,
								new AllNodesViewFilter()),
						new BldInfViewRunnableAdapter() {
							public Object run(IBldInfView infView) {
								List<IExport> list = infView.getTestExports();
								for (IExport export : list) {
									if ("cfg"
											.equalsIgnoreCase(export
													.getTargetPath()
													.getFileExtension())) {
										String target = export.getTargetPath()
												.toString();
										int offset = target.toLowerCase()
												.indexOf("/winscw/");
										if (offset > -1) {
											String source = export
													.getSourcePath().toString();
											target = target.substring(offset
													+ "/winscw/".length());
											target = target.replaceFirst("/",
													":/").replace('/', '\\');
											lScriptMap.put(target, projPath
													.append(source).toString());
										}
									}
								}
								return null;
							}
						});
			}
		}
		return lScriptMap;
	}

	public boolean isValid(ILaunchConfiguration config) {
		if (testScriptCombo.getText().trim().length() > 0) {
			setErrorMessage(null);
			return super.isValid(config);
		} else {
			setErrorMessage("must specify the test script.");
			return false;
		}
	}

	public void performApply(ILaunchConfigurationWorkingCopy config) {
		super.performApply(config);
		config
				.setAttribute(
						"com.freescale.cdt.debug.cw.core.settings.DebuggerCommonData.Host App Path",
						hostText.getText());
		StringBuilder sb = new StringBuilder("-s ");
		sb.append(testScriptCombo.getText());
		sb
				.append(" -NOPROMPT -log CreateLogDirectories YES -log EmulatorFormat HTML -log EmulatorOutput FILE -log EmulatorBasePath C:\\LOGS\\TestFramework\\");

		config.setAttribute("org.eclipse.cdt.launch.PROGRAM_ARGUMENTS", sb
				.toString());
		config.setAttribute(LaunchConfigurationConstants.SCRIPT_DEVICE_PATH,
				testScriptCombo.getText());
		config.setAttribute(LaunchConfigurationConstants.SCRIPT_HOST_PATH,
				(String) testScriptCombo.getData(testScriptCombo.getText()));
	}
}
