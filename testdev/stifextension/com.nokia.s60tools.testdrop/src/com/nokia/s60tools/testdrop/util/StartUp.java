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


package com.nokia.s60tools.testdrop.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.nokia.s60tools.testdrop.engine.TestDropFactory;
import com.nokia.s60tools.testdrop.engine.TestResultFactory;
import com.nokia.s60tools.testdrop.engine.connection.HttpConnection;
import com.nokia.s60tools.testdrop.engine.job.EmulatorJobListener;
import com.nokia.s60tools.testdrop.engine.job.JobListener;
import com.nokia.s60tools.testdrop.engine.job.RunTestModuleJob;
import com.nokia.s60tools.testdrop.engine.job.SendDropJob;
import com.nokia.s60tools.testdrop.engine.value.TestResultValue;
import com.nokia.s60tools.testdrop.engine.xml.value.ConnectionPropertyValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.engine.xml.value.TestResultPropertyValue;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.testrun.TestRun;
import com.nokia.s60tools.testdrop.testrun.ValidationResult;
import com.nokia.s60tools.testdrop.testrun.ValidationResult.ValidationProblem;
import com.nokia.s60tools.testdrop.ui.dialogs.TestCombinerInputDialog;
import com.nokia.s60tools.testdrop.ui.dialogs.TestDropTargetDialog;
import com.nokia.s60tools.testdrop.ui.dialogs.TestDropTargetImageSelectionDialog;
import com.nokia.s60tools.testdrop.ui.dialogs.factory.DialogContentFactory;
import com.nokia.s60tools.testdrop.ui.dialogs.model.DialogModel;
import com.nokia.s60tools.testdrop.ui.preferences.TestDropPreferenceConstants;
import com.nokia.s60tools.testdrop.ui.preferences.TestDropPreferences;
import com.nokia.s60tools.testdrop.ui.results.ResultInput;
import com.nokia.s60tools.testdrop.ui.results.ResultStorage;
import com.nokia.s60tools.testdrop.ui.results.TestResultViewer;
import com.nokia.s60tools.testdrop.ui.views.TestDropResultView;

/**
 * Start Up class
 * 
 */
public class StartUp {
	private final static String TARGET_DIALOG_TITLE = Messages
			.getString("StartUp.TargetDialogTitle"); 
	private final static String IMAGE_SELECTION_DIALOG_TITLE = Messages
			.getString("StartUp.TargetDeviceImageSelectionTitle"); 
	private final static int DIALOG_WIDTH = 300;
	private final static int DIALOG_HEIGHT = 250;

	private static IProject selectedProject = null;
	private static IFile[] selectedCfgFiles = null;
	private static TestDropTargetDialog dlgTarget;
	private static DialogModel dialogModel;
	private static TestResultFactory testResultFactory;
	private static TestResultViewer detailedResultViewer = null;

	public static long lastLoadTime;
	public static final String RANK_MASTER = "master"; 

	private static ConnectionPropertyValue connectionProperty;

	/**
	 * Checks validity in connection properties
	 * 
	 * @return true if connection property is valid otherwise false
	 */
	private static boolean isValidConnectionProperties() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();

		String host = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_HOST);
		int port = prefStore
				.getInt(TestDropPreferenceConstants.TEST_DROP_CONNECTION_PORT);
		if (port == 0) {
			return false;
		}

		String username = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_USERNAME);
		String password = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_CONNECTION_PASSWORD);

		String method = null;
		Path dropPath = null;
		String path = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_PATH);
		boolean importUsed = prefStore
				.getBoolean(TestDropPreferenceConstants.TEST_DROP_IMPORT);

		if (importUsed) {
			method = HttpConnection.POST_METHOD;
		} else {
			method = HttpConnection.GET_METHOD;
			dropPath = new Path(path);
		}
		if (host.length() == 0 || username.length() == 0) {
			return false;
		} else {
			connectionProperty = new ConnectionPropertyValue(host, port,
					username, password, null, method, dropPath);
			return true;
		}

	}
	
	/**
	 * Sets the selected project when the target dialog is opened
	 * 
	 * @param project
	 * 			selected project
	 */
	public static void setSelectedProject(IProject project) {
		selectedProject = project;
		selectedCfgFiles = null;
	}
	
	/**
	 * Sets the selected cfg files when the target dialog is opened
	 * 
	 * @param cfgFiles
	 * 			selected .cfg files. In fact only the [0] element will be used
	 * 			Currently only one .cfg file is supported at once.
	 * 			Probably there is even no sense in expanding it.
	 */
	public static void setSelectedCfgFiles(IFile[] cfgFiles) {
		selectedCfgFiles = cfgFiles;
		selectedProject = null;
	}

	/**
	 * Test connection to the ATS Server
	 * 
	 * @return true if connection is working otherwise false
	 * @throws Exception
	 *             if something goes wrong in connection test
	 */
	private static boolean testConnection() throws Exception {
		boolean returnValue = false;
		Exception exception = null;
		if (isValidConnectionProperties()) {
			try {
				HttpConnection httpConnection = getHttpConnection(null);
				httpConnection.testConnection();
				returnValue = true;
			} catch (IOException ex) {
				returnValue = false;
				exception = ex;
			} catch (Exception ex) {
				returnValue = false;
				exception = ex;
			} finally {
				if (exception != null) {
					throw new Exception(exception.getMessage());
				}
			}
		}
		return returnValue;
	}

	/**
	 * Resolves selected project from project explorer
	 * 
	 * @param action
	 *            action
	 * @param workbenchPart
	 *            workbench part
	 * @throws NullPointerException
	 *             if there is not selected project form project explorer
	 */
	public static void setActivePartTargetDialog(IAction action,
			IWorkbenchPart workbenchPart) throws NullPointerException {
		ISelection selection = workbenchPart.getSite().getPage().getSelection();
		IStructuredSelection sel = (IStructuredSelection) selection;
		Object res = sel.getFirstElement();
		if (res instanceof IProject) {
			selectedCfgFiles = null;
			IProject project = ((IResource) res).getProject();			
			if (selectedProject == null) {
				selectedProject = project;
			} else {
				if (!selectedProject.getName().equals(project.getName())) {
					selectedProject = project;
					dialogModel = null;
				}
			}
			LogExceptionHandler.log("project name: " 
					+ selectedProject.getName());
		}
		else if (res instanceof IFile) {
			selectedProject = null;
			Object[] cfgObjects = sel.toArray();
			IFile[] cfgFiles = new IFile[cfgObjects.length];
			for (int i = 0; i < cfgObjects.length; i++) {
				cfgFiles[i] = (IFile)cfgObjects[i];
			}
			if (selectedCfgFiles == null) {
				selectedCfgFiles = cfgFiles;
			} else {
				if (!selectedCfgFiles.equals(cfgFiles)) {
					selectedCfgFiles = cfgFiles;
					dialogModel = null;
				}
			}
		}
		if (selectedProject == null && selectedCfgFiles == null) {
			throw new NullPointerException("Not found selected project or file."); 
		}
	}

	/**
	 * Creates testResultProperty instance from preference store
	 * 
	 * @return instance of the TestResultPropertyValue
	 */
	private static TestResultPropertyValue createTestResultPropertyValue() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();

		String resultPath = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_TEST_RESULT_PATH);

		File file = null;
		if (resultPath.length() > 0) {
			file = new File(resultPath);
		}
		TestResultPropertyValue testResultPropertyValue = new TestResultPropertyValue(
				file);
		return testResultPropertyValue;
	}

	/**
	 * Checks if needed to update test result file path
	 * 
	 * @return true if need to update otherwise false
	 */
	private static boolean isNeedToUpdateTestResultFilePath() {
		File currentPath = getTestResultPropertyValue().getTestResulPath();
		boolean needToUpdate = false;
		if (currentPath != null
				&& testResultFactory.getTestResultPath() != null) {
			if (currentPath.compareTo(testResultFactory.getTestResultPath()) != 0) {
				needToUpdate = true;
			}

		} else if (currentPath == null
				&& testResultFactory.getTestResultPath() != null) {
			needToUpdate = true;
		} else if (currentPath != null
				&& testResultFactory.getTestResultPath() == null) {
			needToUpdate = true;
		}
		return needToUpdate;
	}

	/**
	 * Resolves selected project from the project explorer. Activation made by
	 * binding
	 * 
	 * @param action
	 *            action
	 * @param window
	 *            window
	 * @throws NullPointerException
	 *             if there is not selected project form the project explorer
	 */
	public static void setActivePartTargetDialogByBinding(IAction action,
			IWorkbenchWindow window) throws NullPointerException {
		try {
			ISelection selection = window.getActivePage().getSelection();
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object res = sel.getFirstElement();
			if (res instanceof IProject) {
				selectedCfgFiles = null;
				IProject project = ((IResource) res).getProject();
				if (selectedProject == null) {
					selectedProject = project;
				} else {
					if (!selectedProject.getName().equals(project.getName())) {
						selectedProject = project;
						dialogModel = null;
					}
				}
				LogExceptionHandler.log("project name: " 
						+ selectedProject.getName());
			} else if (res instanceof IFile) {
				selectedProject = null;
				IFile[] cfgFiles = new IFile[] { (IFile)res };
				if (selectedCfgFiles == null) {
					selectedCfgFiles = cfgFiles;
				} else {
					if (!selectedCfgFiles.equals(cfgFiles)) {
						selectedCfgFiles = cfgFiles;
						dialogModel = null;
					}
				}
			}
		} catch (Exception ex) {
			throw new NullPointerException(Messages
					.getString("StartUp.NotFoundSelectedProjectException")); 
		}
	}

	/**
	 * Starts target dialog
	 * 
	 * @param action
	 *            action
	 * @param dialogModel
	 *            model for constructs the dialog
	 */
	public static void startTargetDialog(IAction action, DialogModel dialogModel) {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();

		String mode = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_DIALOG_SHOW_MODE);

		if (mode.equals(TestDropPreferences.DIALOG_SHOW_MODE_ALWAYS)) {
			Shell sh = Display.getCurrent().getActiveShell();
			if (StartUp.dialogModel == null) {
				if (dialogModel == null) {
					StartUp.dialogModel = new DialogModel(null, null, -1);
				} else {
					StartUp.dialogModel = dialogModel;
				}
			}
			if (selectedProject != null) {
				StartUp.dialogModel.setSelectedProject(selectedProject);
			} else if (selectedCfgFiles != null) {
				StartUp.dialogModel.setSelectedCfgFiles(selectedCfgFiles);
			}

			dlgTarget = new TestDropTargetDialog(sh, TARGET_DIALOG_TITLE,
					StartUp.dialogModel, false, // not resizable
					DIALOG_WIDTH, DIALOG_HEIGHT);
			dlgTarget.create();
			dlgTarget.open();
		} else if (mode.equals(TestDropPreferences.DIALOG_SHOW_MODE_DEFAULT)) {
			String selectedDevice = prefStore
					.getString(TestDropPreferenceConstants.TEST_DROP_SELECTED_TARGET_DEVICE);
			if (!selectedDevice.contains("emulator")) {
				if (StartUp.dialogModel == null) {							//hardware
					if (selectedDevice.length() == 0) {
						LogExceptionHandler.showErrorDialog(Messages
								.getString("StartUp.SendingFailsException")); 
						return;
					}
					
					StartUp.dialogModel = new DialogModel(null, null, -1);
					if (selectedProject != null) {
						StartUp.dialogModel.setSelectedProject(selectedProject);
					} else if (selectedCfgFiles != null) {
						StartUp.dialogModel.setSelectedCfgFiles(selectedCfgFiles);
					}

					DialogContentFactory dialogContentFactory = new DialogContentFactory();
					try {
						StartUp.dialogModel
								.setTargetDeviceList(dialogContentFactory
										.getTargetDevices(StartUp.dialogModel, 0));
	
						Iterator<TargetDeviceValue> iterator = StartUp.dialogModel
								.getTargetDeviceList().iterator();
						List<String> tagets = new ArrayList<String>();
						while (iterator.hasNext()) {
							TargetDeviceValue targetDeviceValue = (TargetDeviceValue) iterator
									.next();
	
							String hostname = dialogContentFactory
									.makeHostPartForComboControl(targetDeviceValue
											.getProperties());
							tagets.add(targetDeviceValue.getAlias() + hostname);
						}
						int count = tagets.size();
						boolean found = false;
						for (int i = 0; i < count; i++) {
							if (tagets.get(i).equals(selectedDevice)) {
								if (selectedDevice.indexOf(StartUp.dialogModel
										.getTargetDeviceList().get(i).getAlias()) != -1) {
									StartUp.dialogModel
											.setSelectedMasterDeviceIndex(i);
									StartUp.addImagesToDialogModel();
									found = true;
									break;
								}
							}
						}
						if (!found) {
							StartUp.dialogModel = null;
						} else {
							StartUp.dialogModel.getSelectedMasterDevice().setRank(
									RANK_MASTER);
						}
	
					} catch (Exception e) {
						StartUp.dialogModel = null;
						LogExceptionHandler.showErrorDialog(e.getMessage());
						return;
					}
				}
	
				if (selectedProject != null) {
					StartUp.dialogModel.setSelectedProject(selectedProject);
				} else if (selectedCfgFiles != null) {
					StartUp.dialogModel.setSelectedCfgFiles(selectedCfgFiles);
				}
				
				TargetDeviceValue targetDeviceValue = StartUp.dialogModel.getSelectedMasterDevice();
				List<File> images = null;
				if (targetDeviceValue != null) {
					images = targetDeviceValue.getImages();
				}
				if (images != null && images.size() > 0) {
					sendTestDrop(images, true);
				} else {
					sendTestDrop(null, false);
				}
			} else {
				if (StartUp.dialogModel == null) {				// emulator
					if (dialogModel == null) {
						StartUp.dialogModel = new DialogModel(null, null, -1);
					} else {
						StartUp.dialogModel = dialogModel;
					}
				}
				if (selectedProject != null) {
					StartUp.dialogModel.setSelectedProject(selectedProject);
				} else if (selectedCfgFiles != null) {
					StartUp.dialogModel.setSelectedCfgFiles(selectedCfgFiles);
				}
				runTestDrop();
			}
		} else {
			LogExceptionHandler.showErrorDialog(Messages
					.getString("StartUp.PreferencesNotSet")); 
			return;
		}

	}

	/**
	 * Starts target dialog
	 * 
	 * @param action
	 *            action
	 * @param dialogModel
	 *            model for constructs the dialog
	 */
	public static void startImageDialog(IAction action,
			DialogModel dialogModel, boolean staredFromPrederence) {
		Shell sh = Display.getCurrent().getActiveShell();
		if (StartUp.dialogModel == null) {
			if (dialogModel == null) {
				StartUp.dialogModel = new DialogModel(null, null, -1);
				if (selectedProject != null) {
					StartUp.dialogModel.setSelectedProject(selectedProject);
				} else if (selectedCfgFiles != null) {
					StartUp.dialogModel.setSelectedCfgFiles(selectedCfgFiles);
				}
			} else {
				StartUp.dialogModel = dialogModel;
			}
		}

		TestDropTargetImageSelectionDialog dlg = new TestDropTargetImageSelectionDialog(
				sh, IMAGE_SELECTION_DIALOG_TITLE, StartUp.dialogModel, false, // not
				// resizable
				DIALOG_WIDTH, DIALOG_HEIGHT, staredFromPrederence);
		if (dlgTarget != null) {
			dlgTarget.close();
		}
		dlg.create();
		dlg.open();
	}

	/**
	 * Sets job listener. Intended to be used with SendDropJob
	 * 
	 * @param job
	 *            that job will become listener
	 */
	public static void addListenerToJob(Job job) {
		JobListener jobListener = new JobListener();
		job.addJobChangeListener(jobListener);
	}
	
	/**
	 * Sets job listener. Intended to be used with RunTestModuleJob
	 * 
	 * @param job
	 * 			 that job will become listener
	 */
	public static void addListenerToEmulatorJob(Job job) {
		EmulatorJobListener jobListener = new EmulatorJobListener();
		job.addJobChangeListener(jobListener);
	}

	/**
	 * Returns ConnectionPropertyValue
	 * 
	 * @return ConnectionPropertyValue
	 */
	public static ConnectionPropertyValue getConnectionProperties() {
		if (!isValidConnectionProperties()) {
			return null;
		}
		return connectionProperty;
	}

	/**
	 * Returns TestResultPropertyValue
	 * 
	 * @return TestResultPropertyValue
	 */
	public static TestResultPropertyValue getTestResultPropertyValue() {
		return createTestResultPropertyValue();
	}

	/**
	 * Shows detailed result in editor area
	 * 
	 * @param result
	 *            result to be shown
	 */
	public static void showResultInWindow(TestResultValue result) {
		IStorage storage = new ResultStorage(result);
		IEditorInput inputEditor = new ResultInput(storage);
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();

		if (page != null) {
			try {
				boolean detailedResultViewerFound = false;
				if (detailedResultViewer != null) {
					IEditorReference[] editorReferences = page.getEditorReferences();
					for (int i = 0; i < editorReferences.length; i++) {
						if (editorReferences[i].getEditor(false) != null &&
								editorReferences[i].getEditor(false).equals(detailedResultViewer)) {
							detailedResultViewerFound = true;
							break;
						}
					}
				}
				if (detailedResultViewer == null || !detailedResultViewerFound) {
					detailedResultViewer = (TestResultViewer)page.openEditor
												(inputEditor, TestResultViewer.getEditorId());
				}
				else {
					page.reuseEditor(detailedResultViewer, inputEditor);
					page.activate(detailedResultViewer);
				}
				detailedResultViewer.setFocus();
			} catch (PartInitException ex) {
				LogExceptionHandler.log(ex.getMessage());
			}
		}
	}

	/**
	 * Shows result view
	 */
	public static void showResulView() {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			TestDropResultView view = (TestDropResultView) page
					.showView(TestDropResultView.getViewId());
			view.updateView();
		} catch (PartInitException ex) {
			LogExceptionHandler.log(ex.getMessage());
		}
	}

	/**
	 * Returns list of test results
	 * 
	 * @return List of test results
	 * @throws IOException
	 */
	public static List<TestResultValue> getTestResults() {
		if (testResultFactory == null) {
			testResultFactory = new TestResultFactory(
					getTestResultPropertyValue().getTestResulPath(),
					lastLoadTime);
		} else {
			if (isNeedToUpdateTestResultFilePath()) {
				testResultFactory
						.setTestResultPath(getTestResultPropertyValue()
								.getTestResulPath());
			}
		}
		try {
			return testResultFactory.getTestResults();
		} catch (IOException e) {
			LogExceptionHandler.showErrorDialog(e.getMessage());
		}
		return null;
	}

	/**
	 * Adds test result into test result list
	 * 
	 * @param testResultValue
	 * 				test result to be added to the list
	 */
	public static void addTestResult(TestResultValue testResultValue) {
		if (testResultFactory == null) {
			testResultFactory = new TestResultFactory(
					getTestResultPropertyValue().getTestResulPath(),
					lastLoadTime);
		} else {
			if (isNeedToUpdateTestResultFilePath()) {
				testResultFactory
						.setTestResultPath(getTestResultPropertyValue()
								.getTestResulPath());
			}
		}
		testResultFactory.addTestResultToListShortedByTime(testResultValue);
	}
	
	/**
	 * Removes a reference to currently used TestResultFactory.
	 * This is needed to get rid of all currently shown test results
	 *
	 */
	public static void clearAllResults() {
		testResultFactory = null;
	}

	/**
	 * Adds test result into test result list
	 * 
	 * @param testResultValue
	 * 			test result to be added to the list
	 */
	public static void addTestResult(int testRunId) {
		if (testResultFactory == null) {
			testResultFactory = new TestResultFactory(
					getTestResultPropertyValue().getTestResulPath(),
					lastLoadTime);
		} else {
			if (isNeedToUpdateTestResultFilePath()) {
				testResultFactory
						.setTestResultPath(getTestResultPropertyValue()
								.getTestResulPath());
			}
		}

		testResultFactory.addTestResultToListShortedByTime(testRunId);

	}
	
	/**
	 * Add images to target devices in DialogModel object
	 * This is needed to be able to run TestDrop without visiting preferences page
	 * and the target dialog
	 */
	private static void addImagesToDialogModel() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
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
			StartUp.dialogModel.getSelectedMasterDevice().setImages(images);
		}
	}

	/**
	 * Create HttpConnection instance
	 * 
	 * @param testdropLoaction
	 *            path
	 * @return instance of the HttpConnection
	 */
	public static HttpConnection getHttpConnection(IPath testdropLoaction) {
		if (connectionProperty == null) {
			try {
				if (!testConnection()) {
					return null;
				}
			} catch (Exception e) {
				LogExceptionHandler.log(e.getMessage());
			}
		}
		String host = connectionProperty.getHost();
		int port = connectionProperty.getPort();
		String username = connectionProperty.getUsername();
		String password = connectionProperty.getPassword();
		File testdropLoactionFile = null;
		if (testdropLoaction != null) {
			testdropLoactionFile = testdropLoaction.toFile();
		}
		return new HttpConnection(host, port, username, password,
				testdropLoactionFile, null);
	}

	/**
	 * Returns show mode of the test result view
	 * 
	 * @return show mode of the test result view
	 */
	public static String getTestResultViewShowMode() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();

		return prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_SHOW_TEST_RESULT);

	}

	/**
	 * Resolves target dialog cache value
	 * 
	 * @return target dialog cache value
	 */
	public static int getTargetDialogCache() {
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
		String cache = prefStore
				.getString(TestDropPreferenceConstants.TEST_DROP_TARGET_DEVICE_CACHE);
		int ret = -1;
		if (cache.equals(TestDropPreferences.TARGET_DEVICE_LIST_CACHE_NONE)) {
			ret = 0;
		} else {
			ret = Integer.valueOf(cache.substring(0, cache.indexOf(" "))); 
		}
		return ret;
	}

	/**
	 * Sends test drop
	 * 
	 * @param images
	 *            flashable images for target device
	 * @param needToFlash
	 *            value for is need to be flash or not
	 */
	public static void sendTestDrop(List<File> images, boolean needToFlash) {
		try {

			TargetDeviceValue targetDeviceValue = dialogModel
					.getSelectedMasterDevice();
			if (!needToFlash) {
				targetDeviceValue.setImagesInsideDrop(null);
			}
			if (images != null) {
				targetDeviceValue.setImages(images);
			}

			if (dialogModel.getSelectedProject() != null) {
				TestDropFactory testDrop = new TestDropFactory(dialogModel
						.getSelectedProject().getLocation().toString(), dialogModel
						.getSelectedProject(), needToFlash);

				SendDropJob job = new SendDropJob(testDrop, targetDeviceValue);
				job.setPriority(Job.SHORT);
				job.schedule();
				addListenerToJob(job);
			} else if (dialogModel.getSelectedCfgFiles() != null) {
				boolean componentsGroupEnabled = true;
				TestCombinerInputDialog combinerInputDialog = new TestCombinerInputDialog(Display.getCurrent()
						.getActiveShell(), dialogModel.getSelectedCfgFiles()[0], componentsGroupEnabled);
				combinerInputDialog.open();
				if (combinerInputDialog.getReturnCode() == org.eclipse.jface.window.Window.OK) {
					String[] filesToInclude = combinerInputDialog.getSelectedFiles();
					boolean debugVariantActive = combinerInputDialog.getIfDebugVariantChosen();
					TestDropFactory testDrop = new TestDropFactory(dialogModel.getSelectedCfgFiles()[0],
							filesToInclude, debugVariantActive);
					
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
	}
	
	/**
	 * Runs a new RunTestModuleJob. This is a test run for emulator
	 *
	 */
	public static void runTestDrop() {
		if (RunTestModuleJob.getIsTestRunInProgress()) {
			LogExceptionHandler.showErrorDialog(Messages.getString("TestDropTargetDialog.testRunIsInProgress"));
			return;
		}
		IPreferenceStore prefStore = TestDropPlugin.getPrefStore();
		String selectedDevice = prefStore.getString(TestDropPreferenceConstants.TEST_DROP_SELECTED_TARGET_DEVICE);
		String chosenDeviceName = selectedDevice.substring(0, selectedDevice
				.indexOf(DialogContentFactory.EMULATOR_DEVICE_POSTFIX));
		
		TestRun testRun = null;
		if (dialogModel.getSelectedProject() != null) {
			testRun = new TestRun(dialogModel.getSelectedProject(), chosenDeviceName);
		} else if (dialogModel.getSelectedCfgFiles() != null) {
			boolean isComponentsGroupEnabled = false;
			TestCombinerInputDialog combinerInputDialog = new TestCombinerInputDialog(Display.getCurrent().getActiveShell(),
						null, isComponentsGroupEnabled);
			combinerInputDialog.open();
			if (combinerInputDialog.getReturnCode() != org.eclipse.jface.window.Window.OK) {
				return;
			}
			boolean debugVariantActive = combinerInputDialog.getIfDebugVariantChosen();
			testRun = new TestRun(dialogModel.getSelectedCfgFiles(), chosenDeviceName, debugVariantActive);
		}
		
		ValidationResult validationResult = TestRunValidator.validateTestRun(testRun);
		if (validationResult.getValidationProblem() != ValidationProblem.NO_PROBLEM) {
			MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell());
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

	/**
	 * Sets a new dialog model value
	 * 
	 * @param dialogModel
	 *            a new dialog model value
	 */
	public static void setDialogModel(DialogModel dialogModel) {
		StartUp.dialogModel = dialogModel;
	}
}
