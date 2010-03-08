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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.ParseException;
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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.presentation.DriverEditorPlugin;
import com.symbian.driver.utils.UIUtils;
import com.symbian.utils.Epoc;

public class DriverLaunchDeviceTab extends AbstractLaunchConfigurationTab {

	final static String iName = "Device";

	Button iCommdbOnButton;

	Button iCommdbOffButton;

	Button iCommdbOverwriteButton;

	Text iCommDbFile;

	Button iSysBin;

	Button iSerialButton;

	Button iBTButton;

	Button iUSBButton;

	Button iTCPButton;

	Text iIPButton;

	Text iPortButton;

	Text iRDebugButton;

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

		getHardwareOptions(lTabComposite);

		getEmulatorOptions(lTabComposite);

	}

	/**
	 * get emulator settings
	 * 
	 * @param tabComposite
	 */
	private void getEmulatorOptions(Composite aParent) {
		GridData brLayout = new GridData();
		brLayout.grabExcessHorizontalSpace = true;
		brLayout.grabExcessVerticalSpace = true;
		brLayout.horizontalAlignment = GridData.FILL;
		brLayout.verticalAlignment = GridData.FILL;

		final Group lEmulatorOptions = new Group(aParent, SWT.SHADOW_ETCHED_IN);
		{
			lEmulatorOptions.setText("Emulator Options");
			lEmulatorOptions.setLayout(new GridLayout(2, false));
			lEmulatorOptions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			Label lCommdbLabel = new Label(lEmulatorOptions, SWT.NONE);
			lCommdbLabel.setText("&Commdb Options : ");

			Composite lCommdbOption = new Composite(lEmulatorOptions, SWT.NONE);
			{
				lCommdbOption.setLayout(new RowLayout());
				iCommdbOnButton = new Button(lCommdbOption, SWT.RADIO);
				iCommdbOnButton.setText("&On");
				iCommdbOffButton = new Button(lCommdbOption, SWT.RADIO);
				iCommdbOffButton.setText("O&ff");
				iCommdbOverwriteButton = new Button(lCommdbOption, SWT.RADIO);
				iCommdbOverwriteButton.setText("Over&write");

				iCommdbOnButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iCommdbOnButton.setSelection(true);
						iCommdbOffButton.setSelection(false);
						iCommdbOverwriteButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				iCommdbOffButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iCommdbOnButton.setSelection(false);
						iCommdbOffButton.setSelection(true);
						iCommdbOverwriteButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				iCommdbOverwriteButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iCommdbOnButton.setSelection(false);
						iCommdbOffButton.setSelection(false);
						iCommdbOverwriteButton.setSelection(true);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});
			}

			final Label lCommDbFile = new Label(lEmulatorOptions, SWT.NONE);
			{
				lCommDbFile.setText("CommsDat &XML File:");
			}

			iCommDbFile = new Text(lEmulatorOptions, SWT.BORDER);
			iCommDbFile.setLayoutData(brLayout);
			iCommDbFile.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			Label lCommsDbOverwiteNote = new Label(lEmulatorOptions, SWT.LEFT);
			{
				lCommsDbOverwiteNote
						.setText("Note: If a file is specified WinTAP will be added to the file. Otherwise a default CommsDat file will be used.");
				GridData lCommNote = new GridData(GridData.FILL_BOTH);
				lCommNote.horizontalSpan = 2;
				lCommsDbOverwiteNote.setLayoutData(lCommNote);
			}

			lEmulatorOptions.pack();

		}

	}

	/**
	 * get hardware setting
	 * 
	 * @param tabComposite
	 */
	private void getHardwareOptions(Composite aParent) {

		GridData brLayout = new GridData();
		brLayout.grabExcessHorizontalSpace = false;
		brLayout.grabExcessVerticalSpace = true;
		brLayout.verticalAlignment = GridData.FILL;
		brLayout.widthHint = 80;

		final Group lHardwareOptions = new Group(aParent, SWT.SHADOW_ETCHED_IN);
		{
			lHardwareOptions.setText("Hardware Options");
			lHardwareOptions.setLayout(new GridLayout(2, false));
			lHardwareOptions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

			final Label lSysbin = new Label(lHardwareOptions, SWT.NONE);
			{
				lSysbin.setText("Install to s&ys/bin for STATLite : ");
			}

			iSysBin = new Button(lHardwareOptions, SWT.CHECK);

			iSysBin.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent arg0) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			final Label lTansport = new Label(lHardwareOptions, SWT.NONE);
			{
				lTansport.setText("&Transport : ");
			}

			Composite lTransportOptions = new Composite(lHardwareOptions, SWT.NONE);
			{
				lTransportOptions.setLayout(new RowLayout());
				iSerialButton = new Button(lTransportOptions, SWT.RADIO);
				iSerialButton.setText("&Serial");
				iBTButton = new Button(lTransportOptions, SWT.RADIO);
				iBTButton.setText("&BlueTooth");
				iUSBButton = new Button(lTransportOptions, SWT.RADIO);
				iUSBButton.setText("&USB");
				iTCPButton = new Button(lTransportOptions, SWT.RADIO);
				iTCPButton.setText("&NT/RAS");

				iSerialButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iSerialButton.setSelection(true);
						iBTButton.setSelection(false);
						iUSBButton.setSelection(false);
						iTCPButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				iBTButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iSerialButton.setSelection(false);
						iBTButton.setSelection(true);
						iUSBButton.setSelection(false);
						iTCPButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				iUSBButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iSerialButton.setSelection(false);
						iBTButton.setSelection(false);
						iUSBButton.setSelection(true);
						iTCPButton.setSelection(false);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				iTCPButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent arg0) {
						iSerialButton.setSelection(false);
						iBTButton.setSelection(false);
						iUSBButton.setSelection(false);
						iTCPButton.setSelection(true);
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

			}

			final Label lIpLabel = new Label(lHardwareOptions, SWT.NONE);
			{
				lIpLabel.setText("&IP Address:");
			}

			iIPButton = new Text(lHardwareOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
			iIPButton.setLayoutData(brLayout);
			iIPButton.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			final Label lPortLabel = new Label(lHardwareOptions, SWT.NONE);
			{
				lPortLabel.setText("&Port Number:");
			}

			iPortButton = new Text(lHardwareOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
			iPortButton.setLayoutData(brLayout);

			iPortButton.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			final Label lRdebugLabel = new Label(lHardwareOptions, SWT.NONE);
			{
				lRdebugLabel.setText("&RDebug Port:");
			}

			iRDebugButton = new Text(lHardwareOptions, SWT.BORDER | SWT.SINGLE);
			iRDebugButton.setLayoutData(brLayout);

			iRDebugButton.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent evt) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			});

			final Label lRDebugNote = new Label(lHardwareOptions, SWT.LEFT);
			{
				lRDebugNote.setText("Note: If nothing is specified for RDebug then RDebug will not run.");
				GridData lRDebugNoteGridData = new GridData(GridData.FILL_BOTH);
				lRDebugNoteGridData.horizontalSpan = 2;
				lRDebugNote.setLayoutData(lRDebugNoteGridData);
			}

			lHardwareOptions.pack();
		}
	}

	public String getName() {
		return iName;
	}

	public void initializeFrom(ILaunchConfiguration aConfiguration) {

		try {
			iSysBin.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.SYS_BIN, false));
			iRDebugButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.RDEBUG, ""));

			String lTransport = aConfiguration.getAttribute(DriverLaunchConstants.TRANSPORT,
					DriverLaunchConstants.SERIAL);

			if (lTransport.equalsIgnoreCase(DriverLaunchConstants.SERIAL)) {
				iSerialButton.setSelection(true);
				iBTButton.setSelection(false);
				iUSBButton.setSelection(false);
				iTCPButton.setSelection(false);
			} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.BT)) {
				iSerialButton.setSelection(false);
				iBTButton.setSelection(true);
				iUSBButton.setSelection(false);
				iTCPButton.setSelection(false);
			} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.USB)) {
				iSerialButton.setSelection(false);
				iBTButton.setSelection(false);
				iUSBButton.setSelection(true);
				iTCPButton.setSelection(false);
			} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.TCP)) {
				iSerialButton.setSelection(false);
				iBTButton.setSelection(false);
				iUSBButton.setSelection(false);
				iTCPButton.setSelection(true);
			}

			iIPButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.IP_ADDRESS, ""));
			iPortButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.PORT, ""));

			String lCommdbOption = aConfiguration.getAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.ON);

			if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.ON)) {
				iCommdbOnButton.setSelection(true);
				iCommdbOffButton.setSelection(false);
				iCommdbOverwriteButton.setSelection(false);
			} else if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.OFF)) {
				iCommdbOnButton.setSelection(false);
				iCommdbOffButton.setSelection(true);
				iCommdbOverwriteButton.setSelection(false);
			} else if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.OVERWRITE)) {
				iCommdbOnButton.setSelection(false);
				iCommdbOffButton.setSelection(false);
				iCommdbOverwriteButton.setSelection(true);
			}

			iCommDbFile.setText(aConfiguration.getAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, ""));

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

	public void performApply(ILaunchConfigurationWorkingCopy aConfiguration) {

		aConfiguration.setAttribute(DriverLaunchConstants.SYS_BIN, iSysBin.getSelection());

		if (iCommdbOverwriteButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.OVERWRITE);
		} else if (iCommdbOnButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.ON);
		} else {
			aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.OFF);
		}

		aConfiguration.setAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, iCommDbFile.getText());

		if (iSerialButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.SERIAL);
		} else if (iBTButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.BT);
		} else if (iUSBButton.getSelection()) {
			aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.USB);
		} else {
			aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.TCP);
		}

		aConfiguration.setAttribute(DriverLaunchConstants.IP_ADDRESS, iIPButton.getText());

		aConfiguration.setAttribute(DriverLaunchConstants.PORT, iPortButton.getText());

		aConfiguration.setAttribute(DriverLaunchConstants.RDEBUG, iRDebugButton.getText());

	}

	public void setDefaults(ILaunchConfigurationWorkingCopy aConfiguration) {

		TDConfig lConfig = TDConfig.newInstance();

		try {

			aConfiguration.setAttribute(DriverLaunchConstants.SYS_BIN, lConfig.isPreference(TDConfig.SYS_BIN));
			aConfiguration.setAttribute(DriverLaunchConstants.RDEBUG, lConfig.getPreference(TDConfig.RDEBUG));

			String lTransport = lConfig.getPreference(TDConfig.TRANSPORT);
			String lPlaform = lConfig.getPreference(TDConfig.PLATFORM);

			String lCommdb = lConfig.getPreference(TDConfig.COMMDB);

			String[] lParts = lCommdb.split("=");
			aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, lParts[0]);

			if (lParts.length == 2) {
				aConfiguration.setAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, lParts[1]);
			}
			if (!Epoc.isTargetEmulator(lPlaform)) {
				Pattern lPattern = Pattern.compile("(serial|bt|usb)(\\d*)");
				Matcher lMatcher = lPattern.matcher(lTransport);
				if (lMatcher.find()) {
					aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, lMatcher.group(1));
					aConfiguration.setAttribute(DriverLaunchConstants.PORT, lMatcher.group(2));
				} else {
					aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.TCP);
					lParts = lTransport.split(":");
					if (lParts.length > 1) {
						aConfiguration.setAttribute(DriverLaunchConstants.IP_ADDRESS, lParts[1]);
					}
					if (lParts.length > 2) {
						aConfiguration.setAttribute(DriverLaunchConstants.PORT, lParts[2]);
					}
				}
			}
		} catch (ParseException e) {
			// ignore it
		}

	}

	/**
	 * return tab icon
	 */
	public Image getImage() {
		return new Image(UIUtils.getDisplay(), DriverLaunchArgumentsTab.class
				.getResourceAsStream("/icons/full/ctool16/link_obj.gif"));
	}

}
