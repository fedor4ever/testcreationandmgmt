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

import org.apache.commons.cli.ParseException;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.presentation.DriverEditorPlugin;
import com.symbian.driver.utils.UIUtils;

/**
 * @author EngineeringTools
 * 
 * This page creates the following setting: TEFDEPS: To set TEF dependencies to
 * true/false RBUILD: to set RBuild to true/false CLEAN: to set clean before
 * build to true/false BLSDMAKE: to set buildmake when building to true/false
 * 
 */
public class DriverLaunchArgumentsTab extends AbstractLaunchConfigurationTab {

	private Button iTefDepsButton;

	private Button iRBuildButton;

	private Button iCleanButton;

	private Button iBldMakeButton;

	private Text iSourceField;

	private Text iResultField;

	private Text iReposField;

	private Text iEpocField;

	private String iName = "Arguments";

	/**
	 * createControl: create the widgets
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControl(Composite aParent) {
		Font lFont = aParent.getFont();

		Label lLabel = new Label(aParent, SWT.NONE);
		lLabel.setText(iName);
		lLabel.setFont(lFont);

		Composite lTabComposite = new Composite(aParent, SWT.NONE);
		setControl(lTabComposite);

		GridData lCompositeGridData = new GridData(GridData.FILL_BOTH);

		lTabComposite.setLayoutData(lCompositeGridData);

		GridLayout lCompositeGridLayout = new GridLayout(1, false);
		lCompositeGridLayout.marginTop = 10;
		lCompositeGridLayout.verticalSpacing = 10;
		lCompositeGridLayout.horizontalSpacing = 10;
		lCompositeGridLayout.marginWidth = 10;
		lTabComposite.setLayout(lCompositeGridLayout);

		lTabComposite.setFont(lFont);

		getPathsOptions(lTabComposite);

		getBuildOptions(lTabComposite);

		getTEFOptions(lTabComposite);

		lTabComposite.pack();

	}

	private void getPathsOptions(Composite aParent) {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final String lWorkspaceRootPath = root.getLocation().toFile().toString();

		final Group lPathsOptions = new Group(aParent, SWT.SHADOW_ETCHED_IN);
		{
			lPathsOptions.setText("Paths variables");
			lPathsOptions.setLayout(new GridLayout(3, false));
			lPathsOptions.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			final Label lEpocRootLabel = new Label(lPathsOptions, SWT.NONE);
			{
				lEpocRootLabel.setText("&Epoc Root");
			}

			// EPOCROOT text field
			iEpocField = new Text(lPathsOptions, SWT.BORDER);
			iEpocField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			iEpocField.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					// set config to dirty
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// add a directory browse button for EPOCROOT
			final Button lBrowseButton = new Button(lPathsOptions, SWT.NONE);
			{
				lBrowseButton.setLayoutData(new GridData());
				lBrowseButton.setText("Browse");
				lBrowseButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.NONE);
						String lInitial = iEpocField.getText();
						if (lInitial != null && lInitial.length() != 0) {
							dlg.setFilterPath(iEpocField.getText());
						} else {
							dlg.setFilterPath(lWorkspaceRootPath);
						}

						String selectedDirectory = dlg.open();
						if (selectedDirectory != null && selectedDirectory.length() > 0) {
							iEpocField.setText(selectedDirectory);
							setDirty(true);
							updateLaunchConfigurationDialog();
						}
					}
				});
			}

			final Label lSourceRootLabel = new Label(lPathsOptions, SWT.NONE);
			{
				lSourceRootLabel.setText("&Source Root");
			}

			// SOURCEROOT text field
			iSourceField = new Text(lPathsOptions, SWT.BORDER);
			iSourceField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			iSourceField.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					// set config to dirty
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// add a directory browse button for SOURCEROOT
			final Button lBrowseSourceButton = new Button(lPathsOptions, SWT.NONE);
			{
				lBrowseSourceButton.setLayoutData(new GridData());
				lBrowseSourceButton.setText("Browse");
				lBrowseSourceButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.NONE);
						String lInitial = iSourceField.getText();
						if (lInitial != null && lInitial.length() != 0) {
							dlg.setFilterPath(iSourceField.getText());
						} else {
							dlg.setFilterPath(lWorkspaceRootPath);
						}
						String selectedDirectory = dlg.open();
						if (selectedDirectory != null && selectedDirectory.length() > 0) {
							iSourceField.setText(selectedDirectory);
							setDirty(true);
							updateLaunchConfigurationDialog();
						}
					}
				});
			}

			final Label lReposRootLabel = new Label(lPathsOptions, SWT.NONE);
			{
				lReposRootLabel.setText("&Repository Root");
			}

			// REPOSITORYROOT text field
			iReposField = new Text(lPathsOptions, SWT.BORDER);
			iReposField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			iReposField.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					// set config to dirty
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// add a directory browse button for REPOSITORYROOT
			final Button lBrowseReposButton = new Button(lPathsOptions, SWT.NONE);
			{
				lBrowseReposButton.setLayoutData(new GridData());
				lBrowseReposButton.setText("Browse");
				lBrowseReposButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.NONE);
						String lInitial = iReposField.getText();
						if (lInitial != null && lInitial.length() != 0) {
							dlg.setFilterPath(iReposField.getText());
						} else {
							dlg.setFilterPath(lWorkspaceRootPath);
						}
						String selectedDirectory = dlg.open();
						if (selectedDirectory != null && selectedDirectory.length() > 0) {
							iReposField.setText(selectedDirectory);
							setDirty(true);
							updateLaunchConfigurationDialog();
						}
					}
				});
			}

			final Label lResultRootLabel = new Label(lPathsOptions, SWT.NONE);
			{
				lResultRootLabel.setText("Re&sult Root");
			}

			// RESULTROOT text field
			iResultField = new Text(lPathsOptions, SWT.BORDER);
			iResultField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			iResultField.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					// set config to dirty
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// add a directory browse button for RESULTROOT
			final Button lBrowseResultButton = new Button(lPathsOptions, SWT.NONE);
			{
				lBrowseResultButton.setLayoutData(new GridData());
				lBrowseResultButton.setText("Browse");
				lBrowseResultButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.NONE);
						String lInitial = iResultField.getText();
						if (lInitial != null && lInitial.length() != 0) {
							dlg.setFilterPath(iResultField.getText());
						} else {
							dlg.setFilterPath(lWorkspaceRootPath);
						}
						String selectedDirectory = dlg.open();
						if (selectedDirectory != null && selectedDirectory.length() > 0) {
							iResultField.setText(selectedDirectory);
							setDirty(true);
							updateLaunchConfigurationDialog();
						}
					}
				});
			}
			
			lPathsOptions.pack();
		}
	}

	/**
	 * getTEFOptions: create widgets for TEF options: TEF execute dependencies
	 * 
	 * @param lTabComposite
	 */
	private void getTEFOptions(Composite lTabComposite) {
		final Group lTefOptions = new Group(lTabComposite, SWT.NONE);
		{
			lTefOptions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			lTefOptions.setText("Test Execute Framework Options");
			lTefOptions.setLayout(new GridLayout());

			// TEF execute option ON/OFF
			iTefDepsButton = new Button(lTefOptions, SWT.CHECK);
			iTefDepsButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
			iTefDepsButton.setText("Transfer TEF Dependencies");
			iTefDepsButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});
		}
	}

	/**
	 * getBuildOptions : creates the build options: Rbuild/Clean/BldMake
	 * 
	 * @param lTabComposite
	 */
	private void getBuildOptions(Composite lTabComposite) {
		final Group lBuildGroup = new Group(lTabComposite, SWT.SHADOW_ETCHED_IN);
		{
			lBuildGroup.setText("Build Options");
			final GridLayout gridLayout = new GridLayout();
			lBuildGroup.setLayout(gridLayout);
			lBuildGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			// RBuild ON/OFF
			iRBuildButton = new Button(lBuildGroup, SWT.CHECK);
			iRBuildButton.setLayoutData(new GridData());
			iRBuildButton.setText("&Rebuild repository without rebuilding the code");
			iRBuildButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iCleanButton.setSelection(false);
					iBldMakeButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// run build clean build ON/OFF
			iCleanButton = new Button(lBuildGroup, SWT.CHECK);
			iCleanButton.setLayoutData(new GridData());
			iCleanButton.setText("Run &clean on builds");
			iCleanButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iRBuildButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			// run bldmake ON/OFF
			iBldMakeButton = new Button(lBuildGroup, SWT.CHECK);
			iBldMakeButton.setLayoutData(new GridData());
			iBldMakeButton.setText("Run &bldmake on builds");
			iBldMakeButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iRBuildButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			lBuildGroup.pack();
		}
	}

	public String getName() {
		return iName;
	}

	/**
	 * initializeFrom : initialise from an existing config
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration aConfiguration) {
		try {

			iTefDepsButton.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.TEF_DEPS, false));
			iRBuildButton.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.RBUILD, false));
			iCleanButton.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.CLEAN, false));
			iBldMakeButton.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.BLDMAKE, false));
			iReposField.setText(aConfiguration.getAttribute(DriverLaunchConstants.REPOSITORY_ROOT, ""));
			iResultField.setText(aConfiguration.getAttribute(DriverLaunchConstants.RESULT_ROOT, ""));
			iEpocField.setText(aConfiguration.getAttribute(DriverLaunchConstants.EPOC_ROOT, ""));
			iSourceField.setText(aConfiguration.getAttribute(DriverLaunchConstants.SOURCE_ROOT, ""));

		} catch (CoreException e) {
			try {
				setDefaults(aConfiguration.getWorkingCopy());
			} catch (CoreException lCoreException) {
				DriverEditorPlugin.getPlugin().getLog().log(
						new Status(IStatus.ERROR, DriverEditorPlugin.getPlugin().getBundle().getSymbolicName(),
								IStatus.ERROR, "Could not initialise the config", lCoreException));
			}
		}

	}

	/**
	 * performApply: fills the config
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy aConfiguration) {
		// set the values from the controls
		aConfiguration.setAttribute(DriverLaunchConstants.TEF_DEPS, iTefDepsButton.getSelection());
		aConfiguration.setAttribute(DriverLaunchConstants.RBUILD, iRBuildButton.getSelection());
		aConfiguration.setAttribute(DriverLaunchConstants.CLEAN, iCleanButton.getSelection());
		aConfiguration.setAttribute(DriverLaunchConstants.BLDMAKE, iBldMakeButton.getSelection());
		aConfiguration.setAttribute(DriverLaunchConstants.SOURCE_ROOT, iSourceField.getText());
		aConfiguration.setAttribute(DriverLaunchConstants.EPOC_ROOT, iEpocField.getText());
		aConfiguration.setAttribute(DriverLaunchConstants.REPOSITORY_ROOT, iReposField.getText());
		aConfiguration.setAttribute(DriverLaunchConstants.RESULT_ROOT, iResultField.getText());
	}

	/**
	 * setDefaults: set default values
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy aConfiguration) {

		// set defaults from TestDriver config when creating new config
		TDConfig lConfig = TDConfig.getInstance();

		try {
			aConfiguration.setAttribute(DriverLaunchConstants.TEF_DEPS, lConfig.isPreference(TDConfig.TEST_EXECUTE));
			aConfiguration.setAttribute(DriverLaunchConstants.RBUILD, Boolean.FALSE);
			aConfiguration.setAttribute(DriverLaunchConstants.BLDMAKE, lConfig.isPreference(TDConfig.BLDMAKE));
			aConfiguration.setAttribute(DriverLaunchConstants.CLEAN, lConfig.isPreference(TDConfig.CLEAN));
			aConfiguration.setAttribute(DriverLaunchConstants.SOURCE_ROOT, lConfig.getPreferenceFile(
					TDConfig.SOURCE_ROOT).getPath());
			aConfiguration.setAttribute(DriverLaunchConstants.EPOC_ROOT, lConfig.getPreferenceFile(TDConfig.EPOC_ROOT)
					.getPath());
			aConfiguration.setAttribute(DriverLaunchConstants.REPOSITORY_ROOT, lConfig.getPreferenceFile(
					TDConfig.REPOSITORY_ROOT).getPath());
			aConfiguration.setAttribute(DriverLaunchConstants.RESULT_ROOT, lConfig.getPreferenceFile(
					TDConfig.RESULT_ROOT).getPath());

		} catch (ParseException e) {
			// ignore it
		}
	}

	/**
	 * return tab icon
	 */
	public Image getImage() {
		// ImageDescriptor.createFromURL((URL)
		// TefEditorPlugin.getPlugin().getImage("full/ctool16/variable_view")).createImage();
		return new Image(UIUtils.getDisplay(), DriverLaunchArgumentsTab.class
				.getResourceAsStream("/icons/full/ctool16/variable_tab.gif"));
	}

}
