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


package com.nokia.s60tools.testdrop.ui.preferences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import com.nokia.s60tools.testdrop.engine.connection.HttpConnection;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.resources.TestDropHelpContextIDs;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.ui.dialogs.factory.DialogContentFactory;
import com.nokia.s60tools.testdrop.ui.dialogs.model.DialogModel;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Preference page for TestDrop plug-in preferences
 * 
 */
public class TestDropPreferences extends PreferencePage implements
		IWorkbenchPreferencePage {

	private final String CONNECTION_GROUP_TEXT = Messages
			.getString("TestDropPreferences.connectionGroup"); 
	private final String HOST_LABEL_TEXT = Messages
			.getString("TestDropPreferences.hostLabel"); 
	private final String PORT_LABEL_TEXT = Messages
			.getString("TestDropPreferences.portLabel"); 
	private final String USERNAME_LABEL_TEXT = Messages
			.getString("TestDropPreferences.usernameLabel"); 
	private final String PASSWORD_LABEL_TEXT = Messages
			.getString("TestDropPreferences.passwordLabel"); 
	private final String CONFIRM_PASSWORD_LABEL_TEXT = Messages
			.getString("TestDropPreferences.confirmPasswordLabel"); 
	private final String TEST_CONNECTION_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.testConnectionButtonTitle"); 
	private final String METHOD_GROUP_TEXT = Messages
			.getString("TestDropPreferences.methodGroup"); 
	private final String TEST_DROP_PATH_LABEL_TEXT = Messages
			.getString("TestDropPreferences.testDropPathLabel"); 
	private final String BROWSE_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.browseButton"); 
	private final String PASSWORD_DISMATCH = Messages
			.getString("TestDropPreferences.passwordDismatchException"); 
	private final String TEST_RESULT_GROUP_TEXT = Messages
			.getString("TestDropPreferences.testResultGroup"); 
	private final String TEST_RESULT_PATH_LABEL_TEXT = Messages
			.getString("TestDropPreferences.testResultPath"); 
	public static final String SHOW_TEST_RESULT_LABEL_TEXT = Messages
			.getString("TestDropPreferences.showTestResultLabel"); 
	public static final String SHOW_TEST_RESULT_ALWAYS = Messages
			.getString("TestDropPreferences.showTestResultAll"); 
	public static final String SHOW_TEST_RESULT_ONLY_FAILED = Messages
			.getString("TestDropPreferences.showTestResultFailedCasesOnly"); 
	private final String TESTDROP_DIALOG_GROUP_TEXT = Messages
			.getString("TestDropPreferences.testDropDialogGroup"); 
	private final String SHOW_ALWAYS_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.showDialogsAlways"); 
	private final String TARGET_DEVICE_LIST_CACHE = Messages
			.getString("TestDropPreferences.targetDevoceListCache"); 
	public static final String TARGET_DEVICE_LIST_CACHE_NONE = Messages
			.getString("TestDropPreferences.targetDeviceListCacheNone"); 
	private final String TARGET_DEVICE_LIST_CACHE_15 = Messages
			.getString("TestDropPreferences.15min"); 
	private final String TARGET_DEVICE_LIST_CACHE_30 = Messages
			.getString("TestDropPreferences.30min"); 
	private final String TARGET_DEVICE_LIST_CACHE_60 = Messages
			.getString("TestDropPreferences.60min"); 
	private final String DEFAULT_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.defaultButtonText"); 
	private final String TARGET_DEVICE_LIST_LABEL_TEXT = Messages
			.getString("TestDropPreferences.targetDeviceListLabel"); 
	private final String SET_IMAGE_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.setImageButton"); 
	private final String TEST_DROP_IMPORT_BUTTON_TEXT = Messages
			.getString("TestDropPreferences.testDropImportButton"); 
	public static final String DIALOG_SHOW_MODE_ALWAYS = Messages
			.getString("TestDropPreferences.dialogShowModeAlways"); 
	public static final String DIALOG_SHOW_MODE_DEFAULT = Messages
			.getString("TestDropPreferences.dialogShowModeDefault"); 
	private final String SELECT_ONE_TARGET_MSG = Messages
			.getString("TestDropPreferences.selectOneTargetDeviceNotify"); 

	private Text hostText;
	private Text portText;
	private Text usernameText;
	private Text passwordText;
	private Text confirmPasswordText;
	private Text testDropPathText;
	private Text testResultPathText;
	private Combo testResultViewCombo;
	private Combo targetDeviceListCombo;
	private Combo targetDeviceListCacheCombo;
	private Button testDropImportButton;
	private Button setImage;
	private Button alwaysButton;
	private DialogModel dialogModel;
	private Button defaultButton;
	private Button enableHardwareTestDropCheck;
	private Button testServerConnectionButton;
	private Button testResultPathBrowseButton;
	private Button testDropPathBrowseButton;

	/**
	 * Constructor
	 * 
	 * Create the preference page
	 */
	public TestDropPreferences() {
		super();
		dialogModel = new DialogModel(null, null, -1);
	}

	/**
	 * skeleton for init method
	 */
	public void init(IWorkbench arg0) {
	}

	/**
	 * Creates preference page contents
	 * 
	 * @return Control which contain preference page content
	 */
	protected Control createContents(Composite parent) {

		// init
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		container.setLayout(gridLayout);

		// Connection group
		Group connectionGroup = new Group(container, SWT.NONE);
		connectionGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		connectionGroup.setText(CONNECTION_GROUP_TEXT);
		GridLayout glConnection = new GridLayout();
		glConnection.numColumns = 5;
		connectionGroup.setLayout(glConnection);
		
		enableHardwareTestDropCheck = new Button(connectionGroup, SWT.CHECK);
		enableHardwareTestDropCheck.setText(Messages.getString("TestDropPreferences.enableHardwareLabel"));
		GridData gdEnableHardware = new GridData();
		gdEnableHardware.horizontalSpan = 5;
		enableHardwareTestDropCheck.setLayoutData(gdEnableHardware);
		enableHardwareTestDropCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent ev) {
				boolean isSelected = ((Button)ev.getSource()).getSelection();
				hostText.setEnabled(isSelected);
				portText.setEnabled(isSelected);
				usernameText.setEnabled(isSelected);
				passwordText.setEnabled(isSelected);
				confirmPasswordText.setEnabled(isSelected);
				testServerConnectionButton.setEnabled(isSelected);
				
				testDropImportButton.setEnabled(isSelected);
				testDropPathText.setEnabled(isSelected 
						&& !testDropImportButton.getSelection());
				testDropPathBrowseButton.setEnabled(isSelected 
						&& !testDropImportButton.getSelection());
			}
		});

		Label hostLabel = new Label(connectionGroup, SWT.NONE);
		hostLabel.setText(HOST_LABEL_TEXT);
		hostText = new Text(connectionGroup, SWT.SINGLE | SWT.BORDER);
		hostText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label portLabel = new Label(connectionGroup, SWT.NONE);
		portLabel.setText(PORT_LABEL_TEXT);
		portLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		portText = new Text(connectionGroup, SWT.SINGLE | SWT.BORDER);
		portText.setTextLimit(4);
		GridData gdPortText = new GridData();
		gdPortText.widthHint = 30;
		portText.setLayoutData(gdPortText);

		Label hiddenLabel = new Label(connectionGroup, SWT.NONE);
		hiddenLabel.setVisible(false);
		hiddenLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label usernameLabel = new Label(connectionGroup, SWT.NONE);
		usernameLabel.setText(USERNAME_LABEL_TEXT);
		usernameText = new Text(connectionGroup, SWT.SINGLE | SWT.BORDER);
		usernameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label hiddenLabel2 = new Label(connectionGroup, SWT.NONE);
		hiddenLabel2.setVisible(false);
		GridData gdHiddenLabel = new GridData(GridData.FILL_HORIZONTAL);
		gdHiddenLabel.horizontalSpan = 3;
		hiddenLabel2.setLayoutData(gdHiddenLabel);

		Label passwordLabel = new Label(connectionGroup, SWT.NONE);
		passwordLabel.setText(PASSWORD_LABEL_TEXT);
		passwordText = new Text(connectionGroup, SWT.SINGLE | SWT.BORDER);
		passwordText.setEchoChar('*');
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label confirmPasswordLabel = new Label(connectionGroup, SWT.NONE);
		confirmPasswordLabel.setText(CONFIRM_PASSWORD_LABEL_TEXT);
		GridData gdConfirmPasswordLabel = new GridData();
		gdConfirmPasswordLabel.horizontalSpan = 2;
		confirmPasswordLabel.setLayoutData(gdConfirmPasswordLabel);

		confirmPasswordText = new Text(connectionGroup, SWT.SINGLE | SWT.BORDER);
		confirmPasswordText.setEchoChar('*');
		confirmPasswordText
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		confirmPasswordText.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent arg0) {
			}

			public void focusLost(FocusEvent arg0) {
				if (!isPasswordSame(passwordText.getText(), confirmPasswordText
						.getText())) {
					LogExceptionHandler.showNotifyDialog(PASSWORD_DISMATCH);
				}
			}
		});

		testServerConnectionButton = new Button(connectionGroup,
				SWT.PUSH);
		testServerConnectionButton.setText(TEST_CONNECTION_BUTTON_TEXT);
		GridData gdConnectionButton = new GridData(
				GridData.HORIZONTAL_ALIGN_END);
		gdConnectionButton.horizontalSpan = 5;
		testServerConnectionButton.setLayoutData(gdConnectionButton);
		testServerConnectionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String response = testConnection();
				if (response != null) {
					LogExceptionHandler.showNotifyDialog(response);
				}
			}
		});

		// TestDrop dialog group

		Group dropDialig = new Group(container, SWT.NONE);
		dropDialig.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dropDialig.setText(TESTDROP_DIALOG_GROUP_TEXT);
		GridLayout dropDialogGridLayout = new GridLayout();
		dropDialogGridLayout.numColumns = 3;
		dropDialig.setLayout(dropDialogGridLayout);

		alwaysButton = new Button(dropDialig, SWT.RADIO);
		alwaysButton.setText(SHOW_ALWAYS_BUTTON_TEXT);
		alwaysButton.setSelection(true);
		GridData gdAlways = new GridData();
		gdAlways.horizontalSpan = 3;
		alwaysButton.setLayoutData(gdAlways);
		alwaysButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				targetDeviceListCombo.setEnabled(false);
				setImage.setEnabled(false);	
				targetDeviceListCacheCombo.setEnabled(true);
			}
		});

		Label targetDeviceListCache = new Label(dropDialig, SWT.NONE);
		targetDeviceListCache.setText("\t" + TARGET_DEVICE_LIST_CACHE + ":");  
		targetDeviceListCacheCombo = new Combo(dropDialig, SWT.NONE
				| SWT.READ_ONLY);
		targetDeviceListCacheCombo.add(TARGET_DEVICE_LIST_CACHE_NONE);
		targetDeviceListCacheCombo.add(TARGET_DEVICE_LIST_CACHE_15);
		targetDeviceListCacheCombo.add(TARGET_DEVICE_LIST_CACHE_30);
		targetDeviceListCacheCombo.add(TARGET_DEVICE_LIST_CACHE_60);
		targetDeviceListCacheCombo.select(0);

		Label hiddenLabel3 = new Label(dropDialig, SWT.NONE);
		hiddenLabel3.setVisible(false);

		defaultButton = new Button(dropDialig, SWT.RADIO);
		defaultButton.setText(DEFAULT_BUTTON_TEXT);
		GridData gdDefault = new GridData();
		gdDefault.horizontalSpan = 3;
		defaultButton.setLayoutData(gdDefault);
		defaultButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if (defaultButton.getSelection()) {
						if (testConnection() != null) {
							getTargetDeviceList();
							targetDeviceListCacheCombo.setEnabled(false);
							targetDeviceListCombo.setEnabled(true);
							setImage.setEnabled(true);	
						} else {
							defaultButton.setSelection(false);
							alwaysButton.setSelection(true);
							targetDeviceListCombo.setEnabled(false);
							setImage.setEnabled(false);	
							targetDeviceListCacheCombo.setEnabled(true);
							LogExceptionHandler
									.showNotifyDialog(Messages
											.getString("TestDropPreferences.defineConnectionNotify")); 
						}
					}
				} catch (Exception ex) {
					defaultButton.setSelection(false);
					alwaysButton.setSelection(true);
					LogExceptionHandler.showNotifyDialog(Messages.getString("TestDropPreferences.unappliedConnectionSettingsException"));
				}
			}
		});
		defaultButton.setSelection(false);
		Label tagetDeviceList = new Label(dropDialig, SWT.NONE);
		tagetDeviceList.setText("\t" + TARGET_DEVICE_LIST_LABEL_TEXT + ":");  
		targetDeviceListCombo = new Combo(dropDialig, SWT.NONE | SWT.READ_ONLY);
		targetDeviceListCombo.setEnabled(false);
		targetDeviceListCombo.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));

		setImage = new Button(dropDialig, SWT.PUSH);			
		setImage.setText(SET_IMAGE_BUTTON_TEXT);				
		setImage.setEnabled(false);
		setImage.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = targetDeviceListCombo.getSelectionIndex();
				if (index == -1) {
					LogExceptionHandler.showNotifyDialog(SELECT_ONE_TARGET_MSG);
				} else {
					dialogModel
							.setSelectedMasterDeviceIndex(targetDeviceListCombo
									.getSelectionIndex());
					StartUp.startImageDialog(null, dialogModel, true);

				}
			}
		});

		// TestDrop group

		Group methodGroup = new Group(container, SWT.NONE);
		methodGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		methodGroup.setText(METHOD_GROUP_TEXT);
		GridLayout methodGridLayout = new GridLayout();
		methodGridLayout.numColumns = 3;
		methodGroup.setLayout(methodGridLayout);

		testDropImportButton = new Button(methodGroup, SWT.CHECK);
		testDropImportButton.setText(TEST_DROP_IMPORT_BUTTON_TEXT);
		GridData gdTestDrop = new GridData();
		gdTestDrop.horizontalSpan = 3;
		testDropImportButton.setLayoutData(gdTestDrop);
		testDropImportButton.setSelection(true);
		testDropImportButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (testDropImportButton.getSelection()) {
					testDropPathText.setEnabled(false);
					testDropPathBrowseButton.setEnabled(false);
				} else {
					testDropPathText.setEnabled(true);
					testDropPathBrowseButton.setEnabled(true);
				}

			}
		});

		Label testDropPathLabel = new Label(methodGroup, SWT.NONE);
		testDropPathLabel.setText(TEST_DROP_PATH_LABEL_TEXT);
		testDropPathText = new Text(methodGroup, SWT.SINGLE | SWT.BORDER);
		testDropPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		testDropPathText.setEnabled(false);

		testDropPathBrowseButton = new Button(methodGroup, SWT.PUSH);
		testDropPathBrowseButton.setText(BROWSE_BUTTON_TEXT);
		testDropPathBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.OPEN);
				String folder = dlg.open();
				if (folder != null) {
					testDropPathText.setText(folder);
					testDropImportButton.setSelection(false);
					testDropPathText.setEnabled(true);
				}
			}
		});

		// TestDrop result group

		Group testResultGroup = new Group(container, SWT.NONE);
		testResultGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		testResultGroup.setText(TEST_RESULT_GROUP_TEXT);
		GridLayout testResultGridLayout = new GridLayout();
		testResultGridLayout.numColumns = 3;
		testResultGroup.setLayout(testResultGridLayout);

		Label testResultViewLabel = new Label(testResultGroup, SWT.NONE);
		testResultViewLabel.setText(SHOW_TEST_RESULT_LABEL_TEXT + ":"); 
		testResultViewCombo = new Combo(testResultGroup, SWT.NONE
				| SWT.READ_ONLY);
		testResultViewCombo.add(SHOW_TEST_RESULT_ALWAYS);
		testResultViewCombo.add(SHOW_TEST_RESULT_ONLY_FAILED);
		testResultViewCombo.select(0);
		Label hiddenLabel5 = new Label(testResultGroup, SWT.NONE);
		hiddenLabel5.setVisible(false);

		Label testResultPathLabel = new Label(testResultGroup, SWT.NONE);
		testResultPathLabel.setText(TEST_RESULT_PATH_LABEL_TEXT);

		testResultPathText = new Text(testResultGroup, SWT.SINGLE | SWT.BORDER);
		testResultPathText
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		testResultPathBrowseButton = new Button(testResultGroup,
				SWT.PUSH);
		testResultPathBrowseButton.setText(BROWSE_BUTTON_TEXT);
		testResultPathBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.OPEN);
				String folder = dlg.open();
				if (folder != null) {
					testResultPathText.setText(folder);
				}
			}
		});

		// set context-sensitive help
		PlatformUI.getWorkbench().getHelpSystem().setHelp(hostText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_HOST);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(portText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_PORT);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(usernameText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_USERNAME);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(passwordText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_PASSWORD);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(confirmPasswordText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_CONFIRM_PASSWORD);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				testServerConnectionButton,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_CONNECTION);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(alwaysButton,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_SHOW_DIALOG_ALWAYS);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						targetDeviceListCacheCombo,
						TestDropHelpContextIDs.TESTDROP_PREFERENCES_TARGET_DEVICE_LIST_CACHE);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(defaultButton,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_DEFAULT);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						targetDeviceListCombo,
						TestDropHelpContextIDs.TESTDROP_PREFERENCES_DEFAULT_TARGET_DEVICE_LIST);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(setImage,	
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_DEFAULT_SET_IMAGES);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						testDropImportButton,
						TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_DROP_ENABLE_IMPORT);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(testDropPathText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_DROP_PATH);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				testDropPathBrowseButton,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_DROP_PATH_BUTTON);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(testResultViewCombo,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_RESULT_VIEW);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(testResultPathText,
				TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_RESULT_PATH);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						testResultPathBrowseButton,
						TestDropHelpContextIDs.TESTDROP_PREFERENCES_TEST_RESULT_PATH_BROWSE);

		getPrefStoreValues();
		return container;
	}

	/**
	 * Gets data which is saved to the preference store
	 */
	private void getPrefStoreValues() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
		
		boolean enableHardware = false; 
		enableHardware = prefStore.getBoolean(TestDropPreferenceConstants.TEST_DROP_ENABLE_HARDWARE);
		enableHardwareTestDropCheck.setSelection(enableHardware);
		hostText.setEnabled(enableHardware);
		portText.setEnabled(enableHardware);
		usernameText.setEnabled(enableHardware);
		passwordText.setEnabled(enableHardware);
		confirmPasswordText.setEnabled(enableHardware);
		testServerConnectionButton.setEnabled(enableHardware);
		testDropImportButton.setEnabled(enableHardware);
		testDropPathText.setEnabled(enableHardware && !testDropImportButton.getSelection());
		testDropPathBrowseButton.setEnabled(enableHardware && !testDropImportButton.getSelection());
		
		hostText
				.setText(prefStore
						.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_HOST));
		int port = prefStore
				.getInt(TestDropPreferenceConstants.TEST_DROP_CONNECTION_PORT);
		if (port != 0) {
			portText.setText(String.valueOf(port));
		}

		usernameText
				.setText(prefStore
						.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_USERNAME));
		passwordText
				.setText(prefStore
						.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_PASSWORD));
		confirmPasswordText
				.setText(prefStore
						.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_PASSWORD));

		String path = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_PATH);
		boolean importUsed = prefStore
				.getBoolean(TestDropPreferenceConstants.TEST_DROP_IMPORT);
		if (path.length() == 0 && !importUsed) {
			testDropImportButton.setSelection(true);
		} else if (path.length() > 0 && !importUsed) {
			testDropImportButton.setSelection(false);
			testDropPathText.setEnabled(true);
			testDropPathText.setText(path);
			testDropPathBrowseButton.setEnabled(true);
		} else if (path.length() > 0 && importUsed) {
			testDropPathText.setText(path);
		} else if (!importUsed) {
			testDropPathText.setText(path);
			testDropPathText.setEnabled(true);
			testDropPathBrowseButton.setEnabled(true);
			testDropImportButton.setSelection(false);
		}
		String stored = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_SHOW_TEST_RESULT);
		int count = testResultViewCombo.getItemCount();
		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (testResultViewCombo.getItem(i).equals(stored)) {
				testResultViewCombo.select(i);
				found = true;
				break;
			}
		}

		if (!found) {
			testResultViewCombo.select(0);
		}
		testResultPathText.setText(prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_TEST_RESULT_PATH));

		String mode = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_DIALOG_SHOW_MODE);
		if (mode.equals(DIALOG_SHOW_MODE_ALWAYS)) {
			alwaysButton.setSelection(true);
			String cache = prefStore
					.getString(TestDropPreferenceConstants.TEST_DROP_TARGET_DEVICE_CACHE);

			int itemCount = targetDeviceListCacheCombo.getItemCount();
			for (int i = 0; i < itemCount; i++) {
				if (targetDeviceListCacheCombo.getItem(i).equals(cache)) {
					targetDeviceListCacheCombo.select(i);
					break;
				}
			}

		} else if (mode.equals(DIALOG_SHOW_MODE_DEFAULT)) {
			alwaysButton.setSelection(false);
			defaultButton.setSelection(true);
			setImage.setEnabled(true);
			targetDeviceListCombo.setEnabled(true);
			try {
				getTargetDeviceList();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String selectedTagetDevice = prefStore
					.getString(TestDropPreferenceConstants.TEST_DROP_SELECTED_TARGET_DEVICE);
			int itemCount = targetDeviceListCombo.getItemCount();
			boolean targetDeviceFound = false;
			for (int i = 0; i < itemCount; i++) {
				if (targetDeviceListCombo.getItem(i)
						.equals(selectedTagetDevice)) {
					targetDeviceListCombo.select(i);
					targetDeviceFound = true;
					dialogModel.setSelectedMasterDeviceIndex(i);
					break;
				}
			}
			if (!targetDeviceFound) {
				targetDeviceListCombo.select(0);
				LogExceptionHandler
						.showErrorDialog(Messages
								.getString("TestDropPreferences.cannotResolveSelectedTargetDeviceException")); 
			}
			String imageList = prefStore
					.getString(TestDropPreferenceConstants.TEST_DROP_TARGET_DEVICE_FLASH_IMAGE_LIST);
			List<File> images = new ArrayList<File>();
			if (imageList.length() > 0) {
				while (imageList.indexOf(";") != -1) { 
					String image = imageList.substring(0, imageList
							.indexOf(";")); 
					imageList = imageList.substring(image.length() + 1);
					images.add(new File(image));
				}
				if (imageList.length() > 0) {
					images.add(new File(imageList));
				}
			}
			if (images.size() > 0) {
				dialogModel.getSelectedMasterDevice().setImages(images);
				StartUp.setDialogModel(dialogModel);
			}

		}

	}

	/**
	 * Skeleton
	 */
	protected void performDefaults() {
		super.performDefaults();
	}

	/**
	 * Checks password validity
	 * 
	 * @param password
	 *            first password
	 * @param confirmPassword
	 *            second password
	 * @return true if first and second password are same otherwise false
	 */
	private boolean isPasswordSame(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Test connection to the ATS Server
	 * 
	 * @return response message from the ATS Server if it has response message
	 *         otherwise null
	 */
	private String testConnection() {
		String returnValue = null;
		try {
			if (hostText.getText().length() > 0
					&& portText.getText().length() > 0
					&& usernameText.getText().length() > 0
					&& passwordText.getText().length() > 0) {
				if (!passwordText.getText().equals(confirmPasswordText.getText())) {
					LogExceptionHandler.showNotifyDialog(Messages
							.getString("TestDropPreferences.passwordDismatchException")); 
					return returnValue;
				}
				HttpConnection httpConnection = new HttpConnection(hostText
						.getText(), Integer.valueOf(portText.getText()),
						usernameText.getText(), passwordText.getText(), null,
						null);
				returnValue = httpConnection.testConnection();

			}
		} catch (IOException ex) {
			returnValue = null;
			LogExceptionHandler.showErrorDialog(ex.getMessage());
		} catch (Exception ex) {
			returnValue = null;
			LogExceptionHandler.showErrorDialog(Messages
					.getString("TestDropPreferences.InvalidPortNotify")); 
		}
		return returnValue;
	}

	/**
	 * Gets target device list for target device list combo control
	 * 
	 */
	private void getTargetDeviceList() throws Exception {
		DialogContentFactory dialogContentFactory = new DialogContentFactory();
		try {
			dialogContentFactory.setTargetDevicesToCombo(targetDeviceListCombo,
					dialogModel, 0, enableHardwareTestDropCheck.getSelection());
		} catch (Exception ex) {
			LogExceptionHandler.showErrorDialog(ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Called when user saves preferences or changes the preference page to
	 * another
	 */
	public boolean performOk() {
		if (isPasswordSame(passwordText.getText(), confirmPasswordText
				.getText())) {
			IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
			if (testDropImportButton.getSelection()) {
				prefStore.setValue(
						TestDropPreferenceConstants.TEST_DROP_IMPORT,
						true);
			} else {
				prefStore.setValue(
						TestDropPreferenceConstants.TEST_DROP_IMPORT,
						false);
				try {
					if (testDropPathText.getText().length() > 0) {
							Path path = new Path(testDropPathText.getText());
							if (StartUp.getConnectionProperties() != null) {
								StartUp.getConnectionProperties().setDropPath(path);
							}
							prefStore.setValue(
									TestDropPreferenceConstants.TEST_DROP_PATH, path
											.toString());
					} else {
						throw new Exception();
					}
				} catch (Exception ex) {
					LogExceptionHandler
							.showErrorDialog(Messages
									.getString("TestDropPreferences.testDropPathIsNotValidNotify")); 
					return false;
				}
			}
			
			prefStore.setValue(
							TestDropPreferenceConstants.TEST_DROP_ENABLE_HARDWARE, enableHardwareTestDropCheck.getSelection());
			
			if (enableHardwareTestDropCheck.getSelection()) {
				if (hostText.getText().length() == 0
						|| portText.getText().length() == 0
						|| usernameText.getText().length() == 0
						|| passwordText.getText().length() == 0
						|| confirmPasswordText.getText().length() == 0) {
					LogExceptionHandler.showNotifyDialog(Messages.getString("StartUp.ConnectionSettingsFailException"));
					return false;
				} else {
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_CONNECTION_HOST,
									hostText.getText());
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_CONNECTION_PORT,
									Integer.valueOf(portText.getText()));
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_CONNECTION_USERNAME,
									usernameText.getText());
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_CONNECTION_PASSWORD,
									passwordText.getText());
				}
			}

			prefStore.setValue(
					TestDropPreferenceConstants.TEST_DROP_SHOW_TEST_RESULT,
					testResultViewCombo.getItem(testResultViewCombo
							.getSelectionIndex()));

			if (testResultPathText.getText().length() > 0) {
				try {
					Path path = new Path(testResultPathText.getText());
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_TEST_RESULT_PATH,
									path.toString());
				} catch (Exception ex) {
					LogExceptionHandler
							.showErrorDialog(Messages
									.getString("TestDropPreferences.testResultPathIsNotValid")); 
					return false;
				}
			} else {
				prefStore
						.setValue(
								TestDropPreferenceConstants.TEST_DROP_TEST_RESULT_PATH,
								""); 
			}
			if (alwaysButton.getSelection()) {
				prefStore.setValue(
						TestDropPreferenceConstants.TEST_DROP_TARGET_DEVICE_CACHE,
						targetDeviceListCacheCombo
								.getItem(targetDeviceListCacheCombo
										.getSelectionIndex()));
				prefStore.setValue(
						TestDropPreferenceConstants.TEST_DROP_DIALOG_SHOW_MODE,
						DIALOG_SHOW_MODE_ALWAYS);
			} else {
				int index = targetDeviceListCombo.getSelectionIndex();
				if (index == -1) {
					LogExceptionHandler
							.showNotifyDialog(SELECT_ONE_TARGET_MSG);
					return false;
				} else {
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_DIALOG_SHOW_MODE,
									DIALOG_SHOW_MODE_DEFAULT);
					prefStore
							.setValue(
									TestDropPreferenceConstants.TEST_DROP_SELECTED_TARGET_DEVICE,
									targetDeviceListCombo
											.getItem(targetDeviceListCombo
													.getSelectionIndex()));
					dialogModel.setSelectedMasterDeviceIndex(index);
					if (!targetDeviceListCombo.getItem(targetDeviceListCombo.getSelectionIndex())	// this block will be run 
							.contains("emulator")) {												// only when hardware device
						dialogModel.getSelectedMasterDevice().setRank(								// is chosen as default
								StartUp.RANK_MASTER);												// Those settings are not
						List<File> images = dialogModel												// needed for emulator
								.getSelectedMasterDevice().getImages();
						String imageList = ""; 
						if (images != null && images.size() > 0) {
							Iterator<File> iterator = images.iterator();
							while (iterator.hasNext()) {
								File file = iterator.next();
								imageList += file.getPath() + ";";
							}
							imageList = imageList.substring(0, (imageList
									.length() - 1));
						}
						prefStore
								.setValue(
										TestDropPreferenceConstants.TEST_DROP_TARGET_DEVICE_FLASH_IMAGE_LIST,
										imageList);
					}
					StartUp.setDialogModel(dialogModel);
				}
			}

		} else {
			LogExceptionHandler.showErrorDialog(PASSWORD_DISMATCH);
			return false;
		}
		return super.performOk();
	}

	/**
	 * Used for applying dialog font and deleting default button from the
	 * preference menu
	 * 
	 */
	protected void applyDialogFont(Composite composite) {
		super.applyDialogFont(composite);
		if (getDefaultsButton() != null) {
			getDefaultsButton().setVisible(false);
		}
		if (getApplyButton() != null) {
			// set context-sensitive help
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getApplyButton(),
					TestDropHelpContextIDs.TESTDROP_PREFERENCES_APPLY);
		}
	}
}
