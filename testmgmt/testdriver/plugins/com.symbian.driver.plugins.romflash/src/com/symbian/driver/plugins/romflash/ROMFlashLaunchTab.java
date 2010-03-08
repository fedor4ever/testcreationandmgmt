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

package com.symbian.driver.plugins.romflash;



import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

	public class ROMFlashLaunchTab extends AbstractLaunchConfigurationTab implements IExecutableExtension {

		final static String iName = "ROM Flashing";

		Text iMethodButton;

		Text iPortButton;

		Text iRebootPluginButton;

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

		}

		/**
		 * get hardware setting
		 * 
		 * @param tabComposite
		 */
		private void getHardwareOptions(Composite aParent) {

			GridData brLayout = new GridData(SWT.LEFT, SWT.FILL, false, true);
			brLayout.widthHint = 361;

			final Group lROMFlashingOptions = new Group(aParent, SWT.SHADOW_ETCHED_IN);
			{
				lROMFlashingOptions.setText("ROM Flashing Options");
				lROMFlashingOptions.setLayout(new GridLayout(2, false));
				final GridData gd_lROMFlashingOptions = new GridData(SWT.FILL, SWT.CENTER, true, false);
				lROMFlashingOptions.setLayoutData(gd_lROMFlashingOptions);

				final Label lMethodLabel = new Label(lROMFlashingOptions, SWT.NONE);
				{
					lMethodLabel.setText("&Method:");
				}

				iMethodButton = new Text(lROMFlashingOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
				iMethodButton.setLayoutData(new GridData(163, SWT.DEFAULT));
				iMethodButton.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				final Label lPortLabel = new Label(lROMFlashingOptions, SWT.NONE);
				{
					lPortLabel.setText("&Port Number:");
				}

				iPortButton = new Text(lROMFlashingOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
				iPortButton.setLayoutData(new GridData(129, SWT.DEFAULT));

				iPortButton.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				final Label lUserNameLabel = new Label(lROMFlashingOptions, SWT.NONE);
				{
					lUserNameLabel.setText("&Reboot Plugin ID:");
				}

				iRebootPluginButton = new Text(lROMFlashingOptions, SWT.BORDER | SWT.SINGLE);
				iRebootPluginButton.setLayoutData(brLayout);

				iRebootPluginButton.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				lROMFlashingOptions.pack();
			}
		}

		public String getName() {
			return iName;
		}

		public void initializeFrom(ILaunchConfiguration aConfiguration) {

//			try {
//				iSysBin.setSelection(aConfiguration.getAttribute(DriverLaunchConstants.SYS_BIN, false));
//				iRDebugButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.RDEBUG, ""));
//
//				String lTransport = aConfiguration.getAttribute(DriverLaunchConstants.TRANSPORT,
//						DriverLaunchConstants.SERIAL);
//
//				if (lTransport.equalsIgnoreCase(DriverLaunchConstants.SERIAL)) {
//					iSerialButton.setSelection(true);
//					iBTButton.setSelection(false);
//					iUSBButton.setSelection(false);
//					iTCPButton.setSelection(false);
//				} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.BT)) {
//					iSerialButton.setSelection(false);
//					iBTButton.setSelection(true);
//					iUSBButton.setSelection(false);
//					iTCPButton.setSelection(false);
//				} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.USB)) {
//					iSerialButton.setSelection(false);
//					iBTButton.setSelection(false);
//					iUSBButton.setSelection(true);
//					iTCPButton.setSelection(false);
//				} else if (lTransport.equalsIgnoreCase(DriverLaunchConstants.TCP)) {
//					iSerialButton.setSelection(false);
//					iBTButton.setSelection(false);
//					iUSBButton.setSelection(false);
//					iTCPButton.setSelection(true);
//				}
//
//				iIPButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.IP_ADDRESS, ""));
//				iPortButton.setText(aConfiguration.getAttribute(DriverLaunchConstants.PORT, ""));
//
//				String lCommdbOption = aConfiguration.getAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.ON);
//
//				if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.ON)) {
//					iCommdbOnButton.setSelection(true);
//					iCommdbOffButton.setSelection(false);
//					iCommdbOverwriteButton.setSelection(false);
//				} else if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.OFF)) {
//					iCommdbOnButton.setSelection(false);
//					iCommdbOffButton.setSelection(true);
//					iCommdbOverwriteButton.setSelection(false);
//				} else if (lCommdbOption.equalsIgnoreCase(DriverLaunchConstants.OVERWRITE)) {
//					iCommdbOnButton.setSelection(false);
//					iCommdbOffButton.setSelection(false);
//					iCommdbOverwriteButton.setSelection(true);
//				}
//
//				iCommDbFile.setText(aConfiguration.getAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, ""));
//
//			} catch (CoreException e) {
//				try {
//					setDefaults(aConfiguration.getWorkingCopy());
//				} catch (CoreException lCoreException) {
//					DriverEditorPlugin.getPlugin().getLog().log(
//							new Status(IStatus.ERROR, DriverEditorPlugin.getPlugin().getBundle().getSymbolicName(),
//									IStatus.ERROR, "Could not initialise the config", lCoreException));
//				}
//			}

		}

		public void performApply(ILaunchConfigurationWorkingCopy aConfiguration) {

//			aConfiguration.setAttribute(DriverLaunchConstants.SYS_BIN, iSysBin.getSelection());
//
//			if (iCommdbOverwriteButton.getSelection()) {
//				aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.OVERWRITE);
//			} else if (iCommdbOnButton.getSelection()) {
//				aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.ON);
//			} else {
//				aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, DriverLaunchConstants.OFF);
//			}
//
//			aConfiguration.setAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, iCommDbFile.getText());
//
//			if (iSerialButton.getSelection()) {
//				aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.SERIAL);
//			} else if (iBTButton.getSelection()) {
//				aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.BT);
//			} else if (iUSBButton.getSelection()) {
//				aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.USB);
//			} else {
//				aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.TCP);
//			}
//
//			aConfiguration.setAttribute(DriverLaunchConstants.IP_ADDRESS, iIPButton.getText());
//
//			aConfiguration.setAttribute(DriverLaunchConstants.PORT, iPortButton.getText());
//
//			aConfiguration.setAttribute(DriverLaunchConstants.RDEBUG, iRDebugButton.getText());

		}

		public void setDefaults(ILaunchConfigurationWorkingCopy aConfiguration) {

//			TDConfig lConfig = TDConfig.newInstance();
//
//			try {
//
//				aConfiguration.setAttribute(DriverLaunchConstants.SYS_BIN, lConfig.isPreference(TDConfig.SYS_BIN));
//				aConfiguration.setAttribute(DriverLaunchConstants.RDEBUG, lConfig.getPreference(TDConfig.RDEBUG));
//
//				String lTransport = lConfig.getPreference(TDConfig.TRANSPORT);
//				String lPlaform = lConfig.getPreference(TDConfig.PLATFORM);
//
//				String lCommdb = lConfig.getPreference(TDConfig.COMMDB);
//
//				String[] lParts = lCommdb.split("=");
//				aConfiguration.setAttribute(DriverLaunchConstants.COMMDB, lParts[0]);
//
//				if (lParts.length == 2) {
//					aConfiguration.setAttribute(DriverLaunchConstants.COMMDB_OVERWRITE, lParts[1]);
//				}
//				if (!Epoc.isTargetEmulator(lPlaform)) {
//					Pattern lPattern = Pattern.compile("(serial|bt|usb)(\\d*)");
//					Matcher lMatcher = lPattern.matcher(lTransport);
//					if (lMatcher.find()) {
//						aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, lMatcher.group(1));
//						aConfiguration.setAttribute(DriverLaunchConstants.PORT, lMatcher.group(2));
//					} else {
//						aConfiguration.setAttribute(DriverLaunchConstants.TRANSPORT, DriverLaunchConstants.TCP);
//						lParts = lTransport.split(":");
//						if (lParts.length > 1) {
//							aConfiguration.setAttribute(DriverLaunchConstants.IP_ADDRESS, lParts[1]);
//						}
//						if (lParts.length > 2) {
//							aConfiguration.setAttribute(DriverLaunchConstants.PORT, lParts[2]);
//						}
//					}
//				}
//			} catch (ParseException e) {
//				// ignore it
//			}

		}

		/**
		 * return tab icon
		 */
		public Image getImage() {
			return null;
		}

		public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
			
		}


	
}
