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

import java.util.ArrayList;

import org.apache.commons.cli.ParseException;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Task;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.presentation.DriverEditorPlugin;
import com.symbian.driver.util.DriverResourceImpl;
import com.symbian.driver.utils.FileUtils;
import com.symbian.driver.utils.UIUtils;

/**
 * @author EngineeringTools This class creates the main tab where the following
 *         are set - the root of the device (EPOCROOT) - the path to the driver
 *         file - the suite name to execute - the command to run build/run/both -
 *         the target : platform + variant
 * 
 */
public class DriverLaunchMainTab extends AbstractLaunchConfigurationTab {

	// private Text iEpocRoot;

	private Text iSuite;

	private String iName = "Main";

	private Combo iDriver;

	private Button iBuildButton;

	private Button iRunButton;

	private Button iBuildRunButton;

	private Button iArmv5Button;

	private Button iArmv4Button;

	private Button iWinscwButton;

	private Button iWinsButton;

	private Button iThumbButton;

	private Button iUrelButton;

	private Button iUdebButton;

	/**
	 * createControl : ceates the widgets and populates them with defaults when
	 * necessary
	 * 
	 * @return void
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
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
		lCompositeGridLayout.marginWidth = 10;
		lCompositeGridLayout.horizontalSpacing = 10;
		lCompositeGridLayout.verticalSpacing = 10;
		lCompositeGridLayout.marginHeight = 0;
		lTabComposite.setLayout(lCompositeGridLayout);

		lTabComposite.setFont(lFont);

		// Driver Options
		getDriverOptions(lTabComposite);

		// Build Options
		getBuildOptions(lTabComposite);

	}

	/**
	 * @param lTabComposite
	 */
	private void getDriverOptions(Composite lTabComposite) {
		final Group lDriverGroup = new Group(lTabComposite, SWT.SHADOW_ETCHED_IN);
		{
			lDriverGroup.setText("Test Target");
			lDriverGroup.setLayout(new GridLayout(3, false));
			lDriverGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// Driver File
			final Label lDriverLabel = new Label(lDriverGroup, SWT.NONE);
			{
				lDriverLabel.setText("&Driver Location:");
			}

			iDriver = new Combo(lDriverGroup, SWT.NONE);
			iDriver.setTextLimit(1000);
			// get all driver files in the workspace
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			ArrayList<String> lList = new ArrayList<String>();
			lList = FileUtils.scanDirectory(root, DriverLaunchConstants.DRIVER);

			// set the list in the combo box so that the user can chose one
			if (lList.size() > 0) {
				String[] lStringArray = new String[lList.size()];
				lList.toArray(lStringArray);
				iDriver.setItems(lStringArray);
			}

			iDriver.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalSpan = 2;
			iDriver.setLayoutData(gridData);

			// Suite to run A.B.C...
			final Label lSuiteLabel = new Label(lDriverGroup, SWT.NONE);
			{
				lSuiteLabel.setLayoutData(new GridData());
				lSuiteLabel.setText("&Suite:");
			}

			iSuite = new Text(lDriverGroup, SWT.BORDER);
			final GridData gridDataSuite = new GridData(SWT.FILL, SWT.CENTER, true, false);
			gridDataSuite.horizontalSpan = 2;
			iSuite.setLayoutData(gridDataSuite);
			iSuite.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			lDriverGroup.pack();
		}

		// Run Type
		final Label lRunTypeLabel = new Label(lDriverGroup, SWT.NONE);
		{
			lRunTypeLabel.setLayoutData(new GridData());
			lRunTypeLabel.setText("&Command: ");
		}

		final Composite lRunTypeComposite = new Composite(lDriverGroup, SWT.NONE);
		{
			lRunTypeComposite.setLayoutData(new GridData());
			lRunTypeComposite.setLayout(new RowLayout(SWT.VERTICAL));
			iBuildButton = new Button(lRunTypeComposite, SWT.RADIO);
			iBuildButton.setText("&Build");
			iRunButton = new Button(lRunTypeComposite, SWT.RADIO);
			iRunButton.setText("&Run");
			iBuildRunButton = new Button(lRunTypeComposite, SWT.RADIO);
			iBuildRunButton.setText("Build &and Run");
			iBuildButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iBuildButton.setSelection(true);
					iRunButton.setSelection(false);
					iBuildRunButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});
			iRunButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iRunButton.setSelection(true);
					iBuildButton.setSelection(false);
					iBuildRunButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});
			iBuildRunButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					iBuildRunButton.setSelection(true);
					iRunButton.setSelection(false);
					iBuildButton.setSelection(false);
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});
		}

	}

	/**
	 * 
	 * @param lTabComposite
	 */
	private void getBuildOptions(Composite lTabComposite) {

		final Group lBuildOptions = new Group(lTabComposite, SWT.SHADOW_ETCHED_IN);
		{
			lBuildOptions.setText("Platform Target");
			lBuildOptions.setLayout(new GridLayout(2, false));
			lBuildOptions.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			// Platform
			final Label lPlatformLabel = new Label(lBuildOptions, SWT.NONE);
			{
				lPlatformLabel.setText("Platform: ");
			}

			final Composite lPlatformComposite = new Composite(lBuildOptions, SWT.NONE);
			{
				lPlatformComposite.setLayout(new RowLayout());
				iArmv5Button = new Button(lPlatformComposite, SWT.RADIO);
				iArmv5Button.setText("ARMv&5");
				iArmv4Button = new Button(lPlatformComposite, SWT.RADIO);
				iArmv4Button.setText("ARM&4");
				iWinscwButton = new Button(lPlatformComposite, SWT.RADIO);
				iWinscwButton.setText("&WINSCW");
				iWinsButton = new Button(lPlatformComposite, SWT.RADIO);
				iWinsButton.setText("W&INS");
				iThumbButton = new Button(lPlatformComposite, SWT.RADIO);
				iThumbButton.setText("&THUMB");
				iArmv5Button.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iArmv5Button.setSelection(true);
						iArmv4Button.setSelection(false);
						iWinscwButton.setSelection(false);
						iWinsButton.setSelection(false);
						iThumbButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
				iArmv4Button.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iArmv4Button.setSelection(true);
						iArmv5Button.setSelection(false);
						iWinscwButton.setSelection(false);
						iWinsButton.setSelection(false);
						iThumbButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
				iWinscwButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iWinscwButton.setSelection(true);
						iArmv5Button.setSelection(false);
						iArmv4Button.setSelection(false);
						iWinsButton.setSelection(false);
						iThumbButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
				iWinsButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iWinsButton.setSelection(true);
						iArmv5Button.setSelection(false);
						iArmv4Button.setSelection(false);
						iWinscwButton.setSelection(false);
						iThumbButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
				iThumbButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iThumbButton.setSelection(true);
						iArmv4Button.setSelection(false);
						iWinscwButton.setSelection(false);
						iWinsButton.setSelection(false);
						iArmv4Button.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
			}

			// Variant
			final Label lVariantLabel = new Label(lBuildOptions, SWT.NONE);
			{
				lVariantLabel.setText("Variant: ");
			}

			final Composite lVariantComposite = new Composite(lBuildOptions, SWT.NONE);
			{
				lVariantComposite.setLayout(new RowLayout());
				iUrelButton = new Button(lVariantComposite, SWT.RADIO);
				iUrelButton.setText("U&REL");
				iUdebButton = new Button(lVariantComposite, SWT.RADIO);
				iUdebButton.setText("U&DEB");
				iUrelButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iUrelButton.setSelection(true);
						iUdebButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
				iUdebButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iUdebButton.setSelection(true);
						iUrelButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
			}

			lBuildOptions.pack();
		}
	}

	public String getName() {
		return iName;
	}

	/*
	 * (non-Javadoc) initializeForm : set initial values
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration aConfiguration) {

		// /////////////////
		// Driver Options
		// /////////////////

		try {

			// Driver
			iDriver.setText(aConfiguration.getAttribute(DriverLaunchConstants.DRIVER, ""));

			// Suite
			iSuite.setText(aConfiguration.getAttribute(DriverLaunchConstants.ENTRY_POINT_ADDRESS, ""));

			// Run Type

			int lRunType = aConfiguration.getAttribute(DriverLaunchConstants.RUN_TYPE, DriverLaunchConstants.BUILD);

			if (lRunType == DriverLaunchConstants.BUILD_RUN) {
				iBuildButton.setSelection(false);
				iRunButton.setSelection(false);
				iBuildRunButton.setSelection(true);
			} else if (lRunType == DriverLaunchConstants.RUN) {
				iBuildButton.setSelection(false);
				iRunButton.setSelection(true);
				iBuildRunButton.setSelection(false);
			} else {
				iBuildButton.setSelection(true);
				iRunButton.setSelection(false);
				iBuildRunButton.setSelection(false);
			}

			// /////////////////
			// Build Options
			// /////////////////

			// Variant
			String lVariant = aConfiguration.getAttribute(DriverLaunchConstants.VARIANT, DriverLaunchConstants.UREL);

			if (lVariant.equalsIgnoreCase(DriverLaunchConstants.UREL)) {
				iUrelButton.setSelection(true);
				iUdebButton.setSelection(false);
			} else {
				iUrelButton.setSelection(false);
				iUdebButton.setSelection(true);
			}

			// /////////////////
			// Platform Options
			// /////////////////

			String lPlatform = aConfiguration.getAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.ARMv5);

			if (lPlatform.equalsIgnoreCase(DriverLaunchConstants.ARMv5)) {
				iArmv5Button.setSelection(true);
				iArmv4Button.setSelection(false);
				iWinscwButton.setSelection(false);
				iWinsButton.setSelection(false);
				iThumbButton.setSelection(false);
			} else if (lPlatform.equalsIgnoreCase(DriverLaunchConstants.ARM4)) {
				iArmv5Button.setSelection(false);
				iArmv4Button.setSelection(true);
				iWinscwButton.setSelection(false);
				iWinsButton.setSelection(false);
				iThumbButton.setSelection(false);
			} else if (lPlatform.equalsIgnoreCase(DriverLaunchConstants.WINSCW)) {
				iArmv5Button.setSelection(false);
				iArmv4Button.setSelection(false);
				iWinscwButton.setSelection(true);
				iWinsButton.setSelection(false);
				iThumbButton.setSelection(false);
			} else if (lPlatform.equalsIgnoreCase(DriverLaunchConstants.WINS)) {
				iArmv5Button.setSelection(false);
				iArmv4Button.setSelection(false);
				iWinscwButton.setSelection(false);
				iWinsButton.setSelection(true);
				iThumbButton.setSelection(false);
			} else if (lPlatform.equalsIgnoreCase(DriverLaunchConstants.THUMB)) {
				iArmv5Button.setSelection(false);
				iArmv4Button.setSelection(false);
				iWinscwButton.setSelection(false);
				iWinsButton.setSelection(false);
				iThumbButton.setSelection(true);
			}

		} catch (CoreException aCoreException) {
			try {
				setDefaults(aConfiguration.getWorkingCopy());
			} catch (CoreException lCoreException) {
				DriverEditorPlugin.getPlugin().getLog().log(
						new Status(IStatus.ERROR, DriverEditorPlugin.getPlugin().getBundle().getSymbolicName(),
								IStatus.ERROR, "Could not initialise the config", lCoreException));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy aConfiguration) {

		// /////////////////
		// Driver Options
		// /////////////////

		// Driver
		aConfiguration.setAttribute(DriverLaunchConstants.DRIVER, iDriver.getText());

		// suite
		aConfiguration.setAttribute(DriverLaunchConstants.ENTRY_POINT_ADDRESS, iSuite.getText());

		// Run Type
		if (iBuildButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.RUN_TYPE, DriverLaunchConstants.BUILD);
		} else if (iRunButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.RUN_TYPE, DriverLaunchConstants.RUN);
		} else if (iBuildRunButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.RUN_TYPE, DriverLaunchConstants.BUILD_RUN);
		}

		// /////////////////
		// Build Options
		// /////////////////

		// Platform
		if (iArmv5Button.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.ARMv5);
		} else if (iArmv4Button.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.ARM4);
		} else if (iWinscwButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.WINSCW);
		} else if (iWinsButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.WINS);
		} else if (iThumbButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, DriverLaunchConstants.THUMB);
		}

		// Variant
		if (iUrelButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.VARIANT, DriverLaunchConstants.UREL);
		} else if (iUdebButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.VARIANT, DriverLaunchConstants.UDEB);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy aConfiguration) {

		// set defaults from TestDriver config when creating new config
		TDConfig lConfig = TDConfig.getInstance();

		try {
			// Platform
			aConfiguration.setAttribute(DriverLaunchConstants.PLATFORM, lConfig.getPreference(TDConfig.PLATFORM));
			// Variant
			aConfiguration.setAttribute(DriverLaunchConstants.VARIANT, lConfig.getPreference(TDConfig.VARIANT));

		} catch (ParseException lParseException) {
			// never mind if the initialization fails.
		}


		// set Driver file from current workspace. first driver file found
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		ArrayList<String> lList = FileUtils.scanDirectory(root, DriverLaunchConstants.DRIVER);
		if (lList.size() != 0) {
			String lDriver = lList.get(0);
			aConfiguration.setAttribute(DriverLaunchConstants.DRIVER, lDriver);

			// suite
			URI lURI = URI.createFileURI(lDriver);
			ResourceSet iResourceSet = ResourceLoader.getResourceSet();
			DriverResourceImpl lResource = (DriverResourceImpl) iResourceSet.getResource(lURI.trimFragment(), true);

			Task lTask = null;

			DocumentRoot lDocRoot = (DocumentRoot) lResource.getContents().get(0);
			if (lDocRoot != null) {
				lTask = lDocRoot.getDriver().getTask();
			}

			if (lTask != null) {
				aConfiguration.setAttribute(DriverLaunchConstants.ENTRY_POINT_ADDRESS, lTask.getName());
			}
		}

		// Run Type
		aConfiguration.setAttribute(DriverLaunchConstants.RUN_TYPE, DriverLaunchConstants.BUILD_RUN);
	}

	/**
	 * return tab icon
	 */
	public Image getImage() {
		return new Image(UIUtils.getDisplay(), DriverLaunchMainTab.class
				.getResourceAsStream("/icons/full/ctool16/main_tab.gif"));
	}

}
