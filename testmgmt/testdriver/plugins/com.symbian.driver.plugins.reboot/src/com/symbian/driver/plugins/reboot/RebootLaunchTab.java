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

package com.symbian.driver.plugins.reboot;


import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.symbian.driver.core.environment.TDConfig;

	public class RebootLaunchTab extends AbstractLaunchConfigurationTab implements IExecutableExtension {

		final static String iName = "Device Reboot";
		
		Text iMethod;
		Text iHardwareSwitch;
		Text iUserName;
		Text iPassword;
		Text iHostAddr;
		Text iOutletNum;

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

			getRebootOptions(lTabComposite);

		}



		/**
		 * get hardware setting
		 * 
		 * @param tabComposite
		 */
		private void getRebootOptions(Composite aParent) {

			GridData brLayout = new GridData(SWT.LEFT, SWT.FILL, false, true);
			brLayout.widthHint = 201;

			final Group lRebootOptions = new Group(aParent, SWT.SHADOW_ETCHED_IN);
			{
				lRebootOptions.setText("Reboot Options");
				lRebootOptions.setLayout(new GridLayout(2, false));
				final GridData gd_lRebootOptions = new GridData(SWT.FILL, SWT.CENTER, true, false);
				lRebootOptions.setLayoutData(gd_lRebootOptions);

				final Label lMethodLabel = new Label(lRebootOptions, SWT.NONE);
				{
					lMethodLabel.setText("&Method:");
				}

				iMethod = new Text(lRebootOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
				iMethod.setLayoutData(new GridData(163, SWT.DEFAULT));
				iMethod.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				final Label lHardwraeSwitchLabel = new Label(lRebootOptions, SWT.NONE);
				{
					lHardwraeSwitchLabel.setText("&Hardware Switch Location:");
				}

				iHardwareSwitch = new Text(lRebootOptions, SWT.BORDER | SWT.SINGLE | SWT.FILL);
				iHardwareSwitch.setLayoutData(new GridData(318, SWT.DEFAULT));

				iHardwareSwitch.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				final Label lUserNameLabel = new Label(lRebootOptions, SWT.NONE);
				{
					lUserNameLabel.setText("&User Name:");
				}

				iUserName = new Text(lRebootOptions, SWT.BORDER | SWT.SINGLE);
				iUserName.setLayoutData(brLayout);

				iUserName.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				});

				lRebootOptions.pack();
			}

			final Label lPasswordLabel = new Label(lRebootOptions, SWT.NONE);
			final GridData gd_lPasswordLabel = new GridData();
			lPasswordLabel.setLayoutData(gd_lPasswordLabel);
			lPasswordLabel.setText("Password");

			iPassword = new Text(lRebootOptions, SWT.BORDER);
			final GridData gd_iPassword = new GridData(SWT.LEFT, SWT.CENTER, true, false);
			gd_iPassword.widthHint = 188;
			iPassword.setLayoutData(gd_iPassword);

			final Label lHostAddressLabel = new Label(lRebootOptions, SWT.NONE);
			final GridData gd_lHostAddressLabel = new GridData();
			lHostAddressLabel.setLayoutData(gd_lHostAddressLabel);
			lHostAddressLabel.setText("Host Address");

			iHostAddr = new Text(lRebootOptions, SWT.BORDER);
			final GridData gd_iHostAddr = new GridData(SWT.LEFT, SWT.CENTER, true, false);
			gd_iHostAddr.widthHint = 145;
			iHostAddr.setLayoutData(gd_iHostAddr);

			final Label lOutletNumberLabel = new Label(lRebootOptions, SWT.NONE);
			final GridData gd_lOutletNumberLabel = new GridData();
			lOutletNumberLabel.setLayoutData(gd_lOutletNumberLabel);
			lOutletNumberLabel.setText("Outlet Number");

			iOutletNum = new Text(lRebootOptions, SWT.BORDER);
			final GridData gd_iOutletNum = new GridData(SWT.LEFT, SWT.CENTER, true, false);
			iOutletNum.setLayoutData(gd_iOutletNum);
		}

		public String getName() {
			return iName;
		}

		public void initializeFrom(ILaunchConfiguration aConfiguration) {

			try {
				//set tab fields from a launch configuration
				iMethod.setText(aConfiguration.getAttribute(RebootDriverLaunchConstants.REBOOT_METHOD, ""));
				iUserName.setText(aConfiguration.getAttribute(RebootDriverLaunchConstants.USER_NAME, ""));
				iPassword.setText(aConfiguration.getAttribute(RebootDriverLaunchConstants.PASSWORD, ""));
				iHostAddr.setText(aConfiguration.getAttribute(RebootDriverLaunchConstants.HOST_ADDR, ""));
				iOutletNum.setText(aConfiguration.getAttribute(RebootDriverLaunchConstants.OUTLET_NUMBER, ""));
				
			} catch (CoreException e) {
			try {
			setDefaults(aConfiguration.getWorkingCopy());
		} catch (CoreException lCoreException) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(),
					IStatus.ERROR, "Could not initialise the config", lCoreException));
		}
	}

		}

		public void performApply(ILaunchConfigurationWorkingCopy aConfiguration) {
			// this happens when the user hits apply /OK button
			aConfiguration.setAttribute(RebootDriverLaunchConstants.REBOOT_METHOD, iMethod.getText());
			aConfiguration.setAttribute(RebootDriverLaunchConstants.USER_NAME, iUserName.getText());
			aConfiguration.setAttribute(RebootDriverLaunchConstants.PASSWORD, iPassword.getText());
			aConfiguration.setAttribute(RebootDriverLaunchConstants.HOST_ADDR, iHostAddr.getText());
			aConfiguration.setAttribute(RebootDriverLaunchConstants.OUTLET_NUMBER, iOutletNum.getText());			
		}

		public void setDefaults(ILaunchConfigurationWorkingCopy aConfiguration) {
			
			//set default values in the tab
			//get values from TDConfig. It's actually up to the inplementer to decide what's meant by default: values 
			//in TDConfig from previous settings or values pre-defined in plugin.xml
			TDConfig lConfig = TDConfig.newInstance();

			try {
				String lTDConfigVal = lConfig.getPreference(RebootDriverLaunchConstants.REBOOT_METHOD);
				if (lTDConfigVal == null) {
					//get values from plugin.xml
				}
				aConfiguration.setAttribute(RebootDriverLaunchConstants.REBOOT_METHOD, lTDConfigVal);
				lTDConfigVal = lConfig.getPreference(RebootDriverLaunchConstants.USER_NAME);
				if (lTDConfigVal == null) {
					
				}
				aConfiguration.setAttribute(RebootDriverLaunchConstants.USER_NAME, lTDConfigVal);
				lTDConfigVal = lConfig.getPreference(RebootDriverLaunchConstants.PASSWORD);
				if (lTDConfigVal == null) {
					
				}
				aConfiguration.setAttribute(RebootDriverLaunchConstants.PASSWORD, lTDConfigVal);
				lTDConfigVal = lConfig.getPreference(RebootDriverLaunchConstants.HOST_ADDR);
				if (lTDConfigVal == null) {
					
				}
				aConfiguration.setAttribute(RebootDriverLaunchConstants.HOST_ADDR, lTDConfigVal);
				lTDConfigVal = lConfig.getPreference(RebootDriverLaunchConstants.OUTLET_NUMBER);
				if (lTDConfigVal == null) {
					
				}
				aConfiguration.setAttribute(RebootDriverLaunchConstants.OUTLET_NUMBER, lTDConfigVal);
			} catch (ParseException e) {
				// ignore it
			}
		}
		
		public static Display getDisplay()
	    {
	        Display display = Display.getCurrent();
	        if (display == null)
	        {
	            display = Display.getDefault();
	        }
	        return display;
	    }

		/**
		 * return tab icon
		 */
		public Image getImage() {
			return null;
		}

		public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
			// load 			
		}


	
}
