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


package com.nokia.s60tools.testdrop.ui.dialogs;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.nokia.s60tools.testdrop.engine.TestDropFactory;
import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob;
import com.nokia.s60tools.testdrop.engine.job.SendDropJob;
import com.nokia.s60tools.testdrop.engine.xml.value.HardwarePropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.resources.TestDropHelpContextIDs;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.testrun.TestRun;
import com.nokia.s60tools.testdrop.testrun.ValidationResult;
import com.nokia.s60tools.testdrop.testrun.ValidationResult.ValidationProblem;
import com.nokia.s60tools.testdrop.ui.dialogs.factory.DialogContentFactory;
import com.nokia.s60tools.testdrop.ui.dialogs.model.DialogModel;
import com.nokia.s60tools.testdrop.ui.preferences.TestDropPreferenceConstants;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;
import com.nokia.s60tools.testdrop.util.TestRunValidator;
import com.nokia.s60tools.ui.S60ToolsUIConstants;

/**
 * Main dialog class, contains e.g. target hardware selection
 * 
 * @see org.eclipse.jface.dialogs.Dialog
 */
public class TestDropTargetDialog extends Dialog implements SelectionListener {

	private final String dialogTitle;
	private final int widthHint;
	private final int heightHint;
	private final String SEND_BUTTON_TEXT = Messages
			.getString("TestDropTargetDialog.sendButtonText"); 
	private final String MASTER_DEVICE_SELECTION_LABEL_TEXT = Messages
			.getString("TestDropTargetDialog.targetDeviceSelectionLabel"); 
	private final String FLASH_IMAGE_CHANGE_ENABLED_BUTTON_TEXT = Messages
			.getString("TestDropTargetDialog.enableFlash"); 
	private Combo masterDeviceSelectionComboControl;
	private Button flashImageChangeEnabledButton;
	private DialogModel dialogModel;
	
	private final String DEVICE_VALUES_STORE = "device_values_store";
	private final String DEVICE_VALUE_FOR_RESOURCE = "device_value_for_resource";

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 *            Parent shell.
	 * @param dialogTitle
	 *            Dialog title.
	 * @param dialogModel
	 *            contain data for constructs the dialog
	 * @param isResizable
	 *            If true, dialog will be resizable.
	 * @param widthHint
	 *            Preferred width for the dialog.
	 * @param heightHint
	 *            Preferred height for the dialog.
	 */
	public TestDropTargetDialog(Shell parentShell, String dialogTitle,
			DialogModel dialogModel, boolean isResizable, int widthHint,
			int heightHint) {

		super(parentShell);
		if (isResizable) {
			setShellStyle(getShellStyle() | SWT.RESIZE);
		}
		this.dialogTitle = dialogTitle;
		this.dialogModel = dialogModel;
		this.widthHint = widthHint;
		this.heightHint = heightHint;
	}

	/**
	 * Creates dialog area
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {

		Composite dialogAreaComposite = (Composite) super
				.createDialogArea(parent);

		final int cols = 1;
		GridLayout gdl = new GridLayout(cols, false);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		dialogAreaComposite.setLayout(gdl);
		dialogAreaComposite.setLayoutData(gd);

		GridLayout gdl2 = new GridLayout(cols, false);
		GridData gd2 = new GridData(GridData.FILL_BOTH);
		dialogAreaComposite.setLayout(gdl2);

		final int margins = 2 * S60ToolsUIConstants.MARGIN_BTW_FRAME_AND_CONTENTS;
		gd2.widthHint = widthHint - margins;
		gd2.heightHint = heightHint - margins;

		Label masterDeviceSelectionLabel = new Label(dialogAreaComposite,
				SWT.NONE);
		masterDeviceSelectionLabel.setText(MASTER_DEVICE_SELECTION_LABEL_TEXT
				+ ":"); 

		masterDeviceSelectionComboControl = new Combo(dialogAreaComposite,
				SWT.READ_ONLY);
		masterDeviceSelectionComboControl.addSelectionListener(this);
		setTargetDevicesToCombo();
		masterDeviceSelectionComboControl.pack();
		masterDeviceSelectionComboControl
				.setText(MASTER_DEVICE_SELECTION_LABEL_TEXT);
		masterDeviceSelectionComboControl.setLayoutData(gd2);

		Group deviceSelectionGroup = new Group(dialogAreaComposite,		
				SWT.SHADOW_NONE);
		deviceSelectionGroup.setLayout(gdl2);
		deviceSelectionGroup.setLayoutData(gd2);
		deviceSelectionGroup.setText(Messages
				.getString("TestDropTargetDialog.options")); 

		flashImageChangeEnabledButton = new Button(deviceSelectionGroup,		
				SWT.CHECK);
		flashImageChangeEnabledButton
				.setText(FLASH_IMAGE_CHANGE_ENABLED_BUTTON_TEXT);
		widgetSelected(null);

		// set context-sensitive help
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				flashImageChangeEnabledButton,
				TestDropHelpContextIDs.TESTDROP_SEND_DIALOG_CHECKBOX);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				masterDeviceSelectionComboControl,
				TestDropHelpContextIDs.TESTDROP_SEND_DIALOG_TARGET_DEVICE_LIST);

		return dialogAreaComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (dialogTitle != null)
			shell.setText(dialogTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed(org.eclipse.swt.widgets.Composite)
	 */
	protected void okPressed() {
		if (dialogModel.getSelectedProject() != null || dialogModel.getSelectedCfgFiles() != null) {
			dialogModel
					.setSelectedMasterDeviceIndex(masterDeviceSelectionComboControl
							.getSelectionIndex());
			if (flashImageChangeEnabledButton.getSelection()) {	
				try {
					dialogModel.getSelectedMasterDevice().setRank(
							StartUp.RANK_MASTER);
					StartUp.startImageDialog(null, dialogModel, false);
				} catch (Exception e) {
					LogExceptionHandler.showErrorDialog(e.getMessage());
				}

			} else {
				if (!isEmulatorDevice(masterDeviceSelectionComboControl.getText())) {
					try {
						if (dialogModel.getSelectedProject() != null) {
							TestDropFactory testDrop = new TestDropFactory(dialogModel
									.getSelectedProject().getLocation().toString(),
									dialogModel.getSelectedProject(), false);
							TargetDeviceValue targetDeviceValue = findSelectedMasterTargetDevice();
							targetDeviceValue.setImagesInsideDrop(null);
							
							saveDeviceValueForResource(dialogModel.getSelectedProject());
							
							SendDropJob job = new SendDropJob(testDrop,
									targetDeviceValue);
							job.setPriority(Job.SHORT);
							job.schedule();
							StartUp.addListenerToJob(job);
						} else if (dialogModel.getSelectedCfgFiles() != null) {
							boolean componentsGroupEnabled = true;
							TestCombinerInputDialog combinerInputDialog = new TestCombinerInputDialog(getShell(),
									dialogModel.getSelectedCfgFiles()[0], componentsGroupEnabled);
							combinerInputDialog.open();
							if (combinerInputDialog.getReturnCode() == OK) {
								String[] filesToInclude = combinerInputDialog.getSelectedFiles();
								boolean debugVariantActive = combinerInputDialog.getIfDebugVariantChosen();
								TestDropFactory testDrop = new TestDropFactory(dialogModel.getSelectedCfgFiles()[0],
										filesToInclude, debugVariantActive);
								TargetDeviceValue targetDeviceValue = findSelectedMasterTargetDevice();
								
								targetDeviceValue.setImagesInsideDrop(null);
								
								saveDeviceValueForResource(dialogModel.getSelectedCfgFiles()[0]);
								
								SendDropJob job = new SendDropJob(testDrop,
										targetDeviceValue);
								job.setPriority(Job.SHORT);
								job.schedule();
								StartUp.addListenerToJob(job);
							}
						}
					} catch (Exception e) {
						LogExceptionHandler.showErrorDialog(e.getMessage());
					}
				} else {
					if (RunTestModuleJob.getIsTestRunInProgress()) {
						LogExceptionHandler.showErrorDialog(Messages.getString("TestDropTargetDialog.testRunIsInProgress"));
						return;
					}
					String chosenDeviceName = masterDeviceSelectionComboControl.getText()
							.substring(0, masterDeviceSelectionComboControl.getText()
							.indexOf(DialogContentFactory.EMULATOR_DEVICE_POSTFIX));
					
					TestRun testRun = null;
					if (dialogModel.getSelectedProject() != null) {
						testRun = new TestRun(dialogModel.getSelectedProject(), chosenDeviceName);
						saveDeviceValueForResource(dialogModel.getSelectedProject());
					} else if (dialogModel.getSelectedCfgFiles() != null) {
						boolean isComponentsGroupEnabled = false;
						TestCombinerInputDialog combinerInputDialog = new TestCombinerInputDialog(getShell(),
									null, isComponentsGroupEnabled);
						combinerInputDialog.open();
						if (combinerInputDialog.getReturnCode() != OK) {
							super.okPressed();
							return;
						}
						boolean debugVariantActive = combinerInputDialog.getIfDebugVariantChosen();
						testRun = new TestRun(dialogModel.getSelectedCfgFiles(), chosenDeviceName, debugVariantActive);
						saveDeviceValueForResource(dialogModel.getSelectedCfgFiles()[0]);
					}
					
					ValidationResult validationResult = TestRunValidator.validateTestRun(testRun);
					if (validationResult.getValidationProblem() != ValidationProblem.NO_PROBLEM) {
						MessageBox messageBox = new MessageBox(getShell());
						messageBox.setText(Messages.getString("TestDropTargetDialog.fileNotFoundMessage"));
						messageBox.setMessage(validationResult.getMessage());
						messageBox.open();
						return;
					}
						
					RunTestModuleJob runJob = new RunTestModuleJob(testRun);			
					runJob.setPriority(Job.SHORT);
					runJob.schedule();
					StartUp.addListenerToEmulatorJob(runJob);
				}
			}
		} else {
			LogExceptionHandler
					.showNotifyDialog(Messages
							.getString("TestDropTargetDialog.cannotResolveSelectedProject0")); 
		}
		super.okPressed();
	}
	
	/**
	 * Checks if the selected device is an emulator device
	 * 
	 * @param deviceName
	 * 			device name
	 * @return
	 * 		true if the device is an emulator device
	 */
	private boolean isEmulatorDevice(String deviceName) {
		if (deviceName.indexOf(DialogContentFactory.EMULATOR_DEVICE_POSTFIX) > -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method checks if the selected device is an emulator device.
	 * In such situation it is not possible to choose to flash device
	 * and the flashImageChangeEnabledButton should be disabled
	 * 
	 * @param event
	 * 			event that causes the call
	 */
	public void widgetSelected(SelectionEvent event) {
		if (masterDeviceSelectionComboControl.getText().indexOf(DialogContentFactory.EMULATOR_DEVICE_POSTFIX) > -1) {
			flashImageChangeEnabledButton.setEnabled(false);
		}
		else {
			flashImageChangeEnabledButton.setEnabled(true);
		}
	}		
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite) /
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		// Creating the send button
		Button sendButton = createButton(parent, IDialogConstants.OK_ID,
				SEND_BUTTON_TEXT, true);

		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		// set context-sensitive help
		PlatformUI.getWorkbench().getHelpSystem().setHelp(sendButton,
				TestDropHelpContextIDs.TESTDROP_SEND_DIALOG_SEND_BUTTON);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(cancelButton,
				TestDropHelpContextIDs.TESTDROP_SEND_DIALOG_CANCEL_BUTTON);

	}

	/**
	 * Gets and sets target devices to the combo control
	 */
	private void setTargetDevicesToCombo() {
		DialogContentFactory dialogContentFactory = new DialogContentFactory();
		try {
			IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
			boolean addHardwareDevices = prefStore.getBoolean(TestDropPreferenceConstants.TEST_DROP_ENABLE_HARDWARE);
			
			dialogContentFactory.setTargetDevicesToCombo(
					masterDeviceSelectionComboControl, dialogModel, StartUp
							.getTargetDialogCache(), addHardwareDevices);
			IResource res = dialogModel.getSelectedProject();
			if (res == null) {
				res = dialogModel.getSelectedCfgFiles()[0];
			}
			String previousDevice = loadDeviceValueForResource(res);
			if (res != null && previousDevice != null) {
				String[] deviceItems = masterDeviceSelectionComboControl.getItems();
				for (int i = 0; i < deviceItems.length; i++) {
					if (deviceItems[i].equals(previousDevice)) {
						masterDeviceSelectionComboControl.select(i);
						break;
					}
					if (deviceItems[i].startsWith(previousDevice)) {
						masterDeviceSelectionComboControl.select(i);
					}
				}
				if (masterDeviceSelectionComboControl.getSelectionIndex() == -1) {
					masterDeviceSelectionComboControl.select(0);
				}
			}
			else {
				masterDeviceSelectionComboControl.select(0);
			}
		} catch (Exception e) {
			LogExceptionHandler.showErrorDialog(e.getMessage());
			this.close();
		}
	}

	/**
	 * Extracts host name from properties
	 * 
	 * @param hardwarePropertyValues
	 *            list of device properties
	 * @return host name part for the combo control if combo control host name
	 *         part is done, otherwise returns null
	 */
	private String extractHostName(
			List<HardwarePropertyValue> hardwarePropertyValues) {

		Iterator<HardwarePropertyValue> hardwareIterator = hardwarePropertyValues
				.iterator();
		String host = null;
		while (hardwareIterator.hasNext()) {
			HardwarePropertyValue hardwarePropertyValue = (HardwarePropertyValue) hardwareIterator
					.next();
			if (hardwarePropertyValue.getName().equals("HOST")) { 
				host = hardwarePropertyValue.getValue();
			}
		}
		if (host != null) {
			return " (" + host + ")"; 
		} else
			return null;

	}

	/**
	 * Finds same device from device list as user is selected from master combo
	 * control
	 * 
	 * @return target device data
	 * @throws NullPointerException
	 *             if target device did not find in the list
	 */
	private TargetDeviceValue findSelectedMasterTargetDevice()
			throws NullPointerException {
		if (dialogModel.getTargetDeviceList() == null
				|| masterDeviceSelectionComboControl == null) {
			throw new NullPointerException(
					Messages.getString("TestDropTargetDialog.notFoundTargetDeviceException")); 
		}

		int selectedIndex = masterDeviceSelectionComboControl
				.getSelectionIndex();
		String selectedDevice = masterDeviceSelectionComboControl
				.getItem(selectedIndex);
		TargetDeviceValue guessDevice = (TargetDeviceValue) dialogModel
				.getTargetDeviceList().get(selectedIndex);
		if (guessDevice.getAlias().equals(selectedDevice)) {
			guessDevice.setRank(StartUp.RANK_MASTER);
			return guessDevice;
		} 
		else {
			Iterator<TargetDeviceValue> iterator = dialogModel
					.getTargetDeviceList().iterator();
			TargetDeviceValue targetDeviceValue = null;
			while (iterator.hasNext()) {
				targetDeviceValue = (TargetDeviceValue) iterator.next();
				String aliasHost = targetDeviceValue.getAlias()
						+ extractHostName(targetDeviceValue
								.getProperties());
				if (aliasHost.equals(selectedDevice)) {
					targetDeviceValue.setRank(StartUp.RANK_MASTER);
					return targetDeviceValue;
				}
			}
			throw new NullPointerException(
					Messages.getString("TestDropTargetDialog.notFoundTargetDeviceException")); 
		}
	}
	
	/**
	 * Saves a device name that is chosen for a project/.cfg file. This is used for next test runs.
	 * If user choses again the same project/.cfg file the target combo box is automaticaly
	 * set to the previous value.
	 * 
	 * @param resource
	 * 			project/.cfg file for which the device value is to be saved
	 */
	private void saveDeviceValueForResource(IResource resource) {
		try {
			int selectedIndex = masterDeviceSelectionComboControl.getSelectionIndex();
			String selectedDevice = masterDeviceSelectionComboControl.getItem(selectedIndex);
			resource.setPersistentProperty(new QualifiedName(DEVICE_VALUES_STORE, DEVICE_VALUE_FOR_RESOURCE),
					selectedDevice);
		}
		catch (CoreException ex) {
			
		}
	}
	
	/**
	 * Tries to load the previously used device for a given project/.cfg file
	 * 
	 * @param resource
	 * 			a resource for which the method should try to load the device
	 * @return
	 * 			device name is returned if one was found. Otherwise null is returned
	 */
	private String loadDeviceValueForResource(IResource resource) {
		String device = null;
		try {	
			device = resource.getPersistentProperty(new QualifiedName(DEVICE_VALUES_STORE, DEVICE_VALUE_FOR_RESOURCE));
			if (device == null) {
				IPath resourceLocation = resource.getProject().getLocation();
				String stringLocation = resourceLocation.toString();
				String[] pathSegments = stringLocation.split("/");
				if (pathSegments != null && pathSegments.length >= 2) {
					device = pathSegments[pathSegments.length - 2];
				}
			}
		}
		catch (CoreException ex) {
		}
		return device;
	}
}