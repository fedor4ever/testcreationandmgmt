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


package com.nokia.s60tools.stif.configeditor.editors;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.swt.graphics.Color;

import com.nokia.s60tools.stif.configeditor.wizards.NewModuleWizard;
import com.nokia.s60tools.stif.configmanager.ConfigDefaults;
import com.nokia.s60tools.stif.configmanager.ConfigManager;
import com.nokia.s60tools.stif.configmanager.ConfigUtil;
import com.nokia.s60tools.stif.configmanager.FileCreationMode;
import com.nokia.s60tools.stif.configmanager.MeasurementModule;
import com.nokia.s60tools.stif.configmanager.OutputFileFormat;
import com.nokia.s60tools.stif.configmanager.OutputType;
import com.nokia.s60tools.stif.configmanager.ParseProblem;
import com.nokia.s60tools.stif.configmanager.SectionElementType;
import com.nokia.s60tools.stif.configmanager.TestReportMode;
import com.nokia.s60tools.stif.configmanager.YesNo;

/**
 * STIF configuration file editor.
 * 
 */
public class ConfigEditor extends MultiPageEditorPart implements IResourceChangeListener, IGotoMarker, SelectionListener, ModifyListener, ISelectionChangedListener {	
	/**
	 * Config source view control
	 */
	private TextEditor sourceEditor;

	/**
	 * Eclipse form toolkit used to create other controls
	 */
	private FormToolkit toolkit;	
	/**
	 * Global settings view control
	 */
	private ScrolledForm globalSettingsMainForm;
	/**
	 * Modules view pages
	 */
	private ScrolledForm modulesMainForm;	
	/**
	 * Source view page index
	 */
	private int sourcePageIndex = -1;
	/**
	 * Global settings page index
	 */
	private int globalSettingsPageIndex = -1;	
	/**
	 * Modules page index
	 */
	private int modulesPageIndex = -1;
	/**
	 * <code>TestReportMode</code> value control
	 */
	private CCombo testReportModeValue = null;
	/**
	 * <code>CreateTestReport = Yes</code> value control
	 */
	private Button createTestReportYesButton = null;
	/**
	 * <code>CreateTestReport = No</code> value control 
	 */
	private Button createTestReportNoButton = null;
	/**
	 * <code>TestReportFilePath</code> value control
	 */
	private Text testReportFilePathValue = null;
	/**
	 * <code>TestReportFileName</code> value control
	 */
	private Text testReportFileNameValue = null;
	/**
	 * <code>TestReportFormat = Txt</code> value control
	 */
	private Button testReportFormatTxtButton = null;
	/**
	 * <code>TestReportFormat = Html</code> value control
	 */
	private Button testReportFormatHtmlButton = null;
	/**
	 * <code>TestReportOutput = File</code> value control 
	 */
	private Button testReportOutputFileButton = null;
	/**
	 * <code>TestReportOutput = RDebug</code> value control
	 */
	private Button testReportOutputRDebugButton = null;
	/**
	 * <code>TestReportFileCreationMode = Overwrite</code> value control 
	 */
	private Button testReportFileCreationModeOverwriteButton = null;
	/**
	 * <code>TestReportFileCreationMode = Append</code> value control
	 */
	private Button testReportFileCreationModeAppendButton = null;
	/**
	 * <code>DeviceResetDllName</code> value control
	 */
	private Text deviceResetDllNameValue = null;
	/**
	 * <code>DisableMeasurement</code> value control
	 */
	private List disableMeasurementValue = null;
	/**
	 * <code>Timeout</code> value control
	 */
	private Spinner timeoutValue = null;
	/**
	 * <code>UITestingSupport = Yes</code> value control
	 */
	private Button uiTestingSupportYesButton = null;
	/**
	 * <code>UITestingSupport = No</code> value control
	 */
	private Button uiTestingSupportNoButton = null;
	/**
	 * <code>SeparateProcesses = Yes</code> value control
	 */
	private Button separateProcessYesButton = null;
	/**
	 * <code>SeparateProcesses = No</code> value control
	 */
	private Button separateProcessNoButton = null;
	/**
	 * <code>CreateLogDirectories = Yes</code> value control
	 */
	private Button createLogDirectoriesYesButton = null;
	/**
	 * <code>CreateLogDirectories = No</code> value control
	 */
	private Button createLogDirectoriesNoButton = null;
	/**
	 * <code>EmulatorBasePath</code> value control
	 */
	private Text emulatorBasePathValue = null;	
	/**
	 * <code>HardwareBasePath</code> value control
	 */
	private Text hardwareBasePathValue = null;	
	/**
	 * <code>LogFileCreationMode = Overwrite</code> value control
	 */
	Button logFileCreationModeOverwriteButton = null;
	/**
	 * <code>LogFileCreationMode = Append</code> value control
	 */
	Button logFileCreationModeAppendButton = null;	
	/**
	 * <code>ThreadIdToLogFile = Yes</code> value control
	 */
	private Button threadIdToLogFileYesButton = null;
	/**
	 * <code>ThreadIdToLogFile = No</code> value control
	 */
	private Button threadIdToLogFileNoButton = null;
	/**
	 * <code>withTimeStamp = Yes</code> value control
	 */
	private Button withTimeStampYesButton = null;
	/**
	 * <code>WithTimeStamp = No</code> value control
	 */
	private Button withTimeStampNoButton = null;
	/**
	 * <code>WithLineBreak = Yes</code> value control
	 */
	private Button withLineBreakYesButton = null;
	/**
	 * <code>WithLineBreak = No</code> value control
	 */
	private Button withLineBreakNoButton = null;
	/**
	 * <code>WithEventRanking = Yes</code> value control
	 */
	private Button withEventRankingYesButton = null;
	/**
	 * <code>WithEventRanking = No</code> value control
	 */
	private Button withEventRankingNoButton = null;
	/**
	 * <code>FileUnicode = Yes</code> value control
	 */
	private Button fileUnicodeYesButton = null;
	/**
	 * <code>FileUnicode = No</code> value control
	 */
	private Button fileUnicodeNoButton = null;
	/**
	 * <code>EmulatorLogOutput = File</code> value control
	 */
	private Button emulatorLogOutputFileButton = null;
	/**
	 * <code>EmulatorLogOutput = RDebug</code> value control
	 */
	private Button emulatorLogOutputRDebugButton = null;
	/**
	 * <code>EmulatorLogFormat = Txt</code> value control
	 */
	private Button emulatorLogFormatTxtButton = null;
	/**
	 * <code>EmulatorLogFormat = Html</code> value control
	 */
	private Button emulatorLogFormatHtmlButton = null;
	/**
	 * <code>HardwareLogOutput = File</code> value control
	 */
	private Button hardwareLogOutputFileButton = null;
	/**
	 * <code>HardwareLogOutput = RDebug</code> value control
	 */
	private Button hardwareLogOutputRDebugButton = null;
	/**
	 * <code>HardwareLogFormat = Txt</code> value control
	 */
	private Button hardwareLogFormatTxtButton = null;
	/**
	 * <code>HardwareLogFormat = Html</code> value control
	 */
	private Button hardwareLogFormatHtmlButton = null;
	/**
	 * Modules tree view control
	 */
	private TreeViewer modulesTreeViewer = null;
	/**
	 * Modules tree root node
	 */
	private ModulesTreeNode modulesTreeRoot = null;
	/**
	 * Add module button
	 */
	private Button addModuleButton = null;
	/**
	 * Add testcase file button
	 */
	private Button addTestCaseFileButton = null;
	/**
	 * Add ini file button
	 */
	private Button addIniFileButton = null;
	/**
	 * Edit button 
	 */
	private Button editButton = null;
	/**
	 * Remove button
	 */
	private Button removeButton = null;	
	/**
	 * Config manager
	 */
	private ConfigManager configManager = null;
	/**
	 * List of problem markers
	 */
	private java.util.List<IMarker> problemMarkers = null;
	/**
	 * Add testcase title button
	 */
	private Button addTestCaseTitleYesButton = null;
	/**
	 * Add testcase title button
	 */
	private Button addTestCaseTitleNoButton = null;
	/**
	 * Creates a STIf configuration editor
	 */
	
	private Label testReportModeLabel;
	private Label createTestReportLabel;
	private Label testReportFilePathLabel;
	private Label testReportFileNameLabel; 
	private Label testReportFormatLabel;
	private Label testReportOutputLabel;
	private Label testReportFileCreationModeLabel;
	private Label deviceResetDllNameLabel;
	private Label disableMeasurementLabel;
	private Label timeoutLabel;
	private Label uiTestingSupportLabel;
	private Label separateProcessLabel;
	
	private Label createLogDirectoriesLabel;
	private Label emulatorBasePathLabel;
	private Label emulatorLogFormatLabel;
	private Label emulatorLogOutputLabel;
	private Label hardwareBasePathLabel;
	private Label hardwareLogFormatLabel;
	private Label hardwareLogOutputLabel;
	private Label logFileCreationModeLabel;
	private Label threadIdToLogFileLabel;
	private Label withTimeStampLabel;
	private Label withLineBreakLabel;
	private Label withEventRankingLabel;
	private Label fileUnicodeLabel;
	private Label addTestcaseTitleLabel;
	
	public ConfigEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}	
	/**
	 * Creates global settings page
	 */
	void createGlobalSettingsPage() {
		globalSettingsMainForm = toolkit.createScrolledForm(getContainer());
		globalSettingsMainForm.setText("STIF global settings");		
		
		ColumnLayout overviewMainFormLayout = new ColumnLayout();
		overviewMainFormLayout.maxNumColumns = 2;

		Section engineDefaultsSection = toolkit.createSection(globalSettingsMainForm.getBody(), 
					Section.TITLE_BAR );		
		engineDefaultsSection.setText("Engine defaults");
		

		Composite engineDefaultsSectionClient = toolkit.createComposite(engineDefaultsSection);		

		TableWrapLayout engineDefaultsSectionLayout = new TableWrapLayout();
		engineDefaultsSectionLayout.verticalSpacing = 10;
		engineDefaultsSectionLayout.numColumns = 2;
		engineDefaultsSectionClient.setLayout(engineDefaultsSectionLayout);

		engineDefaultsSection.setClient(engineDefaultsSectionClient);
		
		createEngineDefaultsSectionControls(engineDefaultsSectionClient);
		
		// Logger defaults
		Section loggerDefaultsSection = toolkit.createSection(globalSettingsMainForm.getBody(), 
				Section.TITLE_BAR );
		
		loggerDefaultsSection.setText("Logger defaults");
				
		Composite loggerDefaultsSectionClient = toolkit.createComposite(loggerDefaultsSection);		

		TableWrapLayout loggerDefaultsSectionLayout = new TableWrapLayout();
		loggerDefaultsSectionLayout.verticalSpacing = 10; 
		loggerDefaultsSectionLayout.numColumns = 2;
		loggerDefaultsSectionClient.setLayout(loggerDefaultsSectionLayout);

		loggerDefaultsSection.setClient(loggerDefaultsSectionClient);		
		createLoggerDefaultsSectionControls(loggerDefaultsSectionClient);
				
		// Final initializations
		globalSettingsMainForm.getBody().setLayout(overviewMainFormLayout);
		engineDefaultsSectionClient.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(engineDefaultsSectionClient);
		loggerDefaultsSectionClient.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(loggerDefaultsSectionClient);
		
		globalSettingsPageIndex = addPage(globalSettingsMainForm);
		setPageText(globalSettingsPageIndex, "Global settings");
	}
	
	/**
	 * Creates engine defaults panel controls
	 * @param engineDefaultsSectionClient engine defaults panel 
	 */
	void createEngineDefaultsSectionControls(Composite engineDefaultsSectionClient) {
		// Test report mode controls
		testReportModeLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report mode:");	
		testReportModeValue = new CCombo(engineDefaultsSectionClient, SWT.READ_ONLY|SWT.NONE);
		toolkit.adapt(testReportModeValue, true, true);		
		testReportModeValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		testReportModeValue.addSelectionListener(this);
		
		// Create test report controls
		createTestReportLabel = toolkit.createLabel(engineDefaultsSectionClient, "Create test report:");
		Composite createTestReportGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout createTestReportGroupLayout = new GridLayout();
		createTestReportGroupLayout.marginHeight = 0;
		createTestReportGroupLayout.numColumns = 2;
		createTestReportGroup.setLayout(createTestReportGroupLayout);
		createTestReportYesButton = toolkit.createButton(createTestReportGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		createTestReportYesButton.setSelection(true);
		createTestReportYesButton.addSelectionListener(this);
		createTestReportNoButton = toolkit.createButton(createTestReportGroup, "No", SWT.RADIO);
		
		// Test report file path
		testReportFilePathLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report file path:");
		testReportFilePathValue = toolkit.createText(engineDefaultsSectionClient, "C:\\LOGS\\TestFramework\\");
		testReportFilePathValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		testReportFilePathValue.addSelectionListener(this);
		testReportFilePathValue.addModifyListener(this);
		
		// Test report file name
		testReportFileNameLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report file name:");
		testReportFileNameValue = toolkit.createText(engineDefaultsSectionClient, "TestReport");
		testReportFileNameValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		testReportFileNameValue.addSelectionListener(this);
		testReportFileNameValue.addModifyListener(this);
		
		// Test report format controls
		testReportFormatLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report format:");
		Composite testReportFormatGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout testReportFormatGroupLayout = new GridLayout();
		testReportFormatGroupLayout.marginHeight = 0;
		testReportFormatGroupLayout.numColumns = 2;
		testReportFormatGroup.setLayout( testReportFormatGroupLayout );
		testReportFormatTxtButton = toolkit.createButton(testReportFormatGroup, "Txt", SWT.RADIO );
		testReportFormatTxtButton.setSelection(true);
		testReportFormatTxtButton.addSelectionListener(this);
		testReportFormatHtmlButton = toolkit.createButton(testReportFormatGroup, "Html", SWT.RADIO);
		
		// Test report output controls
		testReportOutputLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report output:");
		Composite testReportOutputGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout testReportOutputGroupLayout = new GridLayout();
		testReportOutputGroupLayout.marginHeight = 0;
		testReportOutputGroupLayout.numColumns = 2;
		testReportOutputGroup.setLayout( testReportOutputGroupLayout );
		testReportOutputFileButton = toolkit.createButton(testReportOutputGroup, "File", SWT.RADIO );
		testReportOutputFileButton.setSelection(true);
		testReportOutputFileButton.addSelectionListener(this);
		testReportOutputRDebugButton = toolkit.createButton(testReportOutputGroup, "RDebug", SWT.RADIO);
		
		// Test report file creation mode controls
		testReportFileCreationModeLabel = toolkit.createLabel(engineDefaultsSectionClient, "Test report file creation mode:");
		Composite testReportFileCreationModeGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout testReportFileCreationModeGroupLayout = new GridLayout();
		testReportFileCreationModeGroupLayout.marginHeight = 0;
		testReportFileCreationModeGroupLayout.numColumns = 2;
		testReportFileCreationModeGroup.setLayout( testReportFileCreationModeGroupLayout );
		testReportFileCreationModeOverwriteButton = toolkit.createButton(testReportFileCreationModeGroup, "Overwrite", SWT.RADIO );
		testReportFileCreationModeOverwriteButton.setSelection(true);
		testReportFileCreationModeOverwriteButton.addSelectionListener(this);
		testReportFileCreationModeAppendButton = toolkit.createButton(testReportFileCreationModeGroup, "Append", SWT.RADIO);
		
		// Device reset dll name controls
		deviceResetDllNameLabel = toolkit.createLabel(engineDefaultsSectionClient, "Device reset dll name:");
		deviceResetDllNameValue = toolkit.createText(engineDefaultsSectionClient, "StifResetForNokia.dll");
		deviceResetDllNameValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		deviceResetDllNameValue.addSelectionListener(this);
		deviceResetDllNameValue.addModifyListener(this);
		
		// Disable measurement controls
		disableMeasurementLabel = toolkit.createLabel(engineDefaultsSectionClient, "Disable measurement:");
		disableMeasurementValue = new List(engineDefaultsSectionClient, SWT.READ_ONLY);
		toolkit.adapt(disableMeasurementValue, true, true);		
		disableMeasurementValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		disableMeasurementValue.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TREE_BORDER);
		disableMeasurementValue.addSelectionListener(this);
		
		// Timeout= 0
		timeoutLabel = toolkit.createLabel(engineDefaultsSectionClient, "Timeout:");
		timeoutValue = new Spinner(engineDefaultsSectionClient, SWT.NONE );
		toolkit.adapt(timeoutValue, true, true);
		timeoutValue.setMaximum(Integer.MAX_VALUE);		
		timeoutValue.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		timeoutValue.addSelectionListener(this);
		timeoutValue.addModifyListener(this);
		
		// UITestingSupport
		uiTestingSupportLabel = toolkit.createLabel(engineDefaultsSectionClient, "UI testing support");
		Composite uiTestingSupportGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout uiTestingSupportGroupLayout = new GridLayout();
		uiTestingSupportGroupLayout.numColumns = 2;
		uiTestingSupportGroupLayout.marginHeight = 0;
		uiTestingSupportGroup.setLayout(uiTestingSupportGroupLayout);
		uiTestingSupportYesButton = toolkit.createButton(uiTestingSupportGroup, "Yes", SWT.RADIO | SWT.SELECTED);
		uiTestingSupportYesButton.setSelection(false);
		uiTestingSupportYesButton.addSelectionListener(this);
		uiTestingSupportNoButton = toolkit.createButton(uiTestingSupportGroup, "No", SWT.RADIO);
		uiTestingSupportNoButton.setSelection(true);
		uiTestingSupportNoButton.addSelectionListener(this);
		
		// SeparateProcesses
		separateProcessLabel = toolkit.createLabel(engineDefaultsSectionClient, "Separate processes");
		Composite separateProcessesGroup = toolkit.createComposite(engineDefaultsSectionClient);
		GridLayout separateProcessesGroupLayout = new GridLayout();
		separateProcessesGroupLayout.numColumns = 2;
		separateProcessesGroupLayout.marginHeight = 0;
		separateProcessesGroup.setLayout(separateProcessesGroupLayout);
		separateProcessYesButton = toolkit.createButton(separateProcessesGroup, "Yes", SWT.RADIO | SWT.SELECTED);
		separateProcessYesButton.setSelection(false);
		separateProcessYesButton.addSelectionListener(this);
		separateProcessNoButton = toolkit.createButton(separateProcessesGroup, "No", SWT.RADIO);
		separateProcessNoButton.setSelection(true);
		separateProcessNoButton.addSelectionListener(this);
	}
	
	/**
	 * Creates logger defaults panel controls
	 * @param loggerDefaultsSectionClient logger defaults panel
	 */
	void createLoggerDefaultsSectionControls(Composite loggerDefaultsSectionClient) {
		// Create log directories controls
		createLogDirectoriesLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Create log directories:");
		Composite createLogDirectoriesGroup = toolkit.createComposite(loggerDefaultsSectionClient);				
		GridLayout createLogDirectoriesGroupLayout = new GridLayout();
		createLogDirectoriesGroupLayout.marginHeight = 0;		
		createLogDirectoriesGroupLayout.numColumns = 2;
		createLogDirectoriesGroup.setLayout(createLogDirectoriesGroupLayout);
		createLogDirectoriesYesButton = toolkit.createButton(createLogDirectoriesGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		createLogDirectoriesYesButton.setSelection(false);
		createLogDirectoriesYesButton.addSelectionListener(this);
		createLogDirectoriesNoButton = toolkit.createButton(createLogDirectoriesGroup, "No", SWT.RADIO);
		createLogDirectoriesNoButton.setSelection(true);
		createLogDirectoriesNoButton.addSelectionListener(this);
		
		// Emulator base path controls
		emulatorBasePathLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Emulator base path:");
		emulatorBasePathValue = toolkit.createText(loggerDefaultsSectionClient, "C:\\LOGS\\TestFramework\\");
		emulatorBasePathValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		emulatorBasePathValue.addSelectionListener(this);
		emulatorBasePathValue.addModifyListener(this);
		
		// Emulator log format controls
		emulatorLogFormatLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Emulator log format:");
		Composite emulatorLogFormatGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout emulatorLogFormatGroupLayout = new GridLayout();
		emulatorLogFormatGroupLayout.marginHeight = 0;
		emulatorLogFormatGroupLayout.numColumns = 2;
		emulatorLogFormatGroup.setLayout( emulatorLogFormatGroupLayout );
		emulatorLogFormatTxtButton = toolkit.createButton(emulatorLogFormatGroup, "Txt", SWT.RADIO );
		emulatorLogFormatTxtButton.setSelection(true);
		emulatorLogFormatTxtButton.addSelectionListener(this);
		emulatorLogFormatHtmlButton = toolkit.createButton(emulatorLogFormatGroup, "Html", SWT.RADIO);
		
		// Emulator log output controls
		emulatorLogOutputLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Emulator log output:");
		Composite emulatorLogOutputGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout emulatorLogOutputGroupLayout = new GridLayout();
		emulatorLogOutputGroupLayout.marginHeight = 0;
		emulatorLogOutputGroupLayout.numColumns = 2;
		emulatorLogOutputGroup.setLayout( emulatorLogOutputGroupLayout );
		emulatorLogOutputFileButton = toolkit.createButton(emulatorLogOutputGroup, "File", SWT.RADIO );
		emulatorLogOutputFileButton.setSelection(true);
		emulatorLogOutputFileButton.addSelectionListener(this);
		emulatorLogOutputRDebugButton = toolkit.createButton(emulatorLogOutputGroup, "RDebug", SWT.RADIO);
		
		// Hardware base path controls
		hardwareBasePathLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Hardware base path:");
		hardwareBasePathValue = toolkit.createText(loggerDefaultsSectionClient, "C:\\LOGS\\TestFramework\\");
		hardwareBasePathValue.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		hardwareBasePathValue.addSelectionListener(this);
		hardwareBasePathValue.addModifyListener(this);
		
		// Hardware log format controls
		hardwareLogFormatLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Hardware log format:");
		Composite hardwareLogFormatGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout hardwareLogFormatGroupLayout = new GridLayout();
		hardwareLogFormatGroupLayout.marginHeight = 0;
		hardwareLogFormatGroupLayout.numColumns = 2;
		hardwareLogFormatGroup.setLayout( hardwareLogFormatGroupLayout );
		hardwareLogFormatTxtButton = toolkit.createButton(hardwareLogFormatGroup, "Txt", SWT.RADIO );
		hardwareLogFormatTxtButton.setSelection(true);
		hardwareLogFormatTxtButton.addSelectionListener(this);
		hardwareLogFormatHtmlButton = toolkit.createButton(hardwareLogFormatGroup, "Html", SWT.RADIO);
		
		// Hardware log output controls
		hardwareLogOutputLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Hardware log output:");
		Composite hardwareLogOutputGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout hardwareLogOutputGroupLayout = new GridLayout();
		hardwareLogOutputGroupLayout.marginHeight = 0;
		hardwareLogOutputGroupLayout.numColumns = 2;
		hardwareLogOutputGroup.setLayout( hardwareLogOutputGroupLayout );
		hardwareLogOutputFileButton = toolkit.createButton(hardwareLogOutputGroup, "File", SWT.RADIO );
		hardwareLogOutputFileButton.setSelection(true);
		hardwareLogOutputFileButton.addSelectionListener(this);
		hardwareLogOutputRDebugButton = toolkit.createButton(hardwareLogOutputGroup, "RDebug", SWT.RADIO);
		
		// Log creation mode controls
		logFileCreationModeLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Log creation mode:");
		Composite logFileCreationModeGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout logFileCreationModeGroupLayout = new GridLayout();
		logFileCreationModeGroupLayout.marginHeight = 0;
		logFileCreationModeGroupLayout.numColumns = 2;
		logFileCreationModeGroup.setLayout( logFileCreationModeGroupLayout );
		logFileCreationModeOverwriteButton = toolkit.createButton(logFileCreationModeGroup, "Overwrite", SWT.RADIO );
		logFileCreationModeOverwriteButton.setSelection(true);
		logFileCreationModeOverwriteButton.addSelectionListener(this);
		logFileCreationModeAppendButton = toolkit.createButton(logFileCreationModeGroup, "Append", SWT.RADIO);
		
		// Thread id to log file controls
		threadIdToLogFileLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Put thread id to log file:");
		Composite threadIdToLogFileGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout threadIdToLogFileGroupLayout = new GridLayout();
		threadIdToLogFileGroupLayout.marginHeight = 0;
		threadIdToLogFileGroupLayout.numColumns = 2;
		threadIdToLogFileGroup.setLayout(threadIdToLogFileGroupLayout);
		threadIdToLogFileYesButton = toolkit.createButton(threadIdToLogFileGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		threadIdToLogFileYesButton.setSelection(false);
		threadIdToLogFileYesButton.addSelectionListener(this);
		threadIdToLogFileNoButton = toolkit.createButton(threadIdToLogFileGroup, "No", SWT.RADIO);
		threadIdToLogFileNoButton.setSelection(true);
		
		// WithTimeStamp
		withTimeStampLabel = toolkit.createLabel(loggerDefaultsSectionClient, "With time stamp:");
		Composite withTimeStampGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout withTimeStampGroupLayout = new GridLayout();
		withTimeStampGroupLayout.marginHeight = 0;
		withTimeStampGroupLayout.numColumns = 2;
		withTimeStampGroup.setLayout(withTimeStampGroupLayout);
		withTimeStampYesButton = toolkit.createButton(withTimeStampGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		withTimeStampYesButton.setSelection(true);
		withTimeStampYesButton.addSelectionListener(this);
		withTimeStampNoButton = toolkit.createButton(withTimeStampGroup, "No", SWT.RADIO);
		
		// With line break
		withLineBreakLabel = toolkit.createLabel(loggerDefaultsSectionClient, "With line break:");
		Composite withLineBreakGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout withLineBreakGroupLayout = new GridLayout();
		withLineBreakGroupLayout.marginHeight = 0;
		withLineBreakGroupLayout.numColumns = 2;
		withLineBreakGroup.setLayout(withLineBreakGroupLayout);
		withLineBreakYesButton = toolkit.createButton(withLineBreakGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		withLineBreakYesButton.setSelection(true);
		withLineBreakYesButton.addSelectionListener(this);
		withLineBreakNoButton = toolkit.createButton(withLineBreakGroup, "No", SWT.RADIO);
	
		// With event ranking
		withEventRankingLabel = toolkit.createLabel(loggerDefaultsSectionClient, "With event ranking:");
		Composite withEventRankingGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout withEventRankingGroupLayout = new GridLayout();
		withEventRankingGroupLayout.marginHeight = 0;
		withEventRankingGroupLayout.numColumns = 2;
		withEventRankingGroup.setLayout(withEventRankingGroupLayout);
		withEventRankingYesButton = toolkit.createButton(withEventRankingGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		withEventRankingYesButton.setSelection(false);
		withEventRankingYesButton.addSelectionListener(this);
		withEventRankingNoButton = toolkit.createButton(withEventRankingGroup, "No", SWT.RADIO);
		withEventRankingNoButton.setSelection(true);
		
		// File Unicode
		fileUnicodeLabel = toolkit.createLabel(loggerDefaultsSectionClient, "File unicode:");
		Composite fileUnicodeGroup = toolkit.createComposite(loggerDefaultsSectionClient);
		GridLayout fileUnicodeGroupLayout = new GridLayout();
		fileUnicodeGroupLayout.marginHeight = 0;
		fileUnicodeGroupLayout.numColumns = 2;
		fileUnicodeGroup.setLayout(fileUnicodeGroupLayout);
		fileUnicodeYesButton = toolkit.createButton(fileUnicodeGroup, "Yes", SWT.RADIO|SWT.SELECTED);
		fileUnicodeYesButton.addSelectionListener(this);
		fileUnicodeNoButton = toolkit.createButton(fileUnicodeGroup, "No", SWT.RADIO);
		fileUnicodeNoButton.setSelection(true);
		
		addTestcaseTitleLabel = toolkit.createLabel(loggerDefaultsSectionClient, "Add testcase title:");
		Composite addTestcaseTitleGroup = toolkit.createComposite(loggerDefaultsSectionClient);				
		GridLayout addTestcaseTitleGroupLayout = new GridLayout();
		addTestcaseTitleGroupLayout.marginHeight = 0;		
		addTestcaseTitleGroupLayout.numColumns = 2;
		addTestcaseTitleGroup.setLayout(addTestcaseTitleGroupLayout);
		addTestCaseTitleYesButton = toolkit.createButton(addTestcaseTitleGroup, "Yes", SWT.RADIO);
		addTestCaseTitleYesButton.addSelectionListener(this);
		addTestCaseTitleNoButton = toolkit.createButton(addTestcaseTitleGroup, "No", SWT.RADIO|SWT.SELECTED);
		addTestCaseTitleNoButton.setSelection(true);
		addTestCaseTitleNoButton.setSelection(true);
	}
	
	/**
	 * Creates modules page
	 */
	void createModulesPage() {
		modulesMainForm = toolkit.createScrolledForm(getContainer());
		modulesMainForm.setText("STIF modules settings");		
		
		ColumnLayout modulesMainFormLayout = new ColumnLayout();		
		modulesMainFormLayout.maxNumColumns = 1;

		Section modulesSection = toolkit.createSection(modulesMainForm.getBody(), 
					Section.TITLE_BAR );		
		modulesSection.setText("Modules");

		Composite modulesSectionClient = toolkit.createComposite(modulesSection);		

		TableWrapLayout modulesSectionLayout = new TableWrapLayout();
		modulesSectionLayout.numColumns = 2;
		modulesSectionClient.setLayout(modulesSectionLayout);		
		modulesSectionClient.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		
		modulesSection.setClient(modulesSectionClient);
		
		modulesTreeViewer = new TreeViewer(modulesSectionClient, SWT.SINGLE);
		toolkit.adapt(modulesTreeViewer.getTree(),true,true);
		TableWrapData modulesTreeViewerLayoutData = new TableWrapData(TableWrapData.FILL_GRAB);
		modulesTreeViewerLayoutData.valign = TableWrapData.FILL;
		modulesTreeViewer.getTree().setLayoutData( modulesTreeViewerLayoutData );
		modulesTreeViewer.addSelectionChangedListener(this);
		
		Composite buttonsGroup = toolkit.createComposite(modulesSectionClient);
		TableWrapData buttonsGroupLayoutData = new TableWrapData();
		buttonsGroupLayoutData.heightHint = SWT.MAX;
		buttonsGroupLayoutData.valign = TableWrapData.FILL;
		buttonsGroup.setLayoutData(buttonsGroupLayoutData);
		TableWrapLayout buttonsGroupLayout = new TableWrapLayout();
		buttonsGroupLayout.numColumns = 1;
		buttonsGroupLayout.topMargin = 0;
		buttonsGroup.setLayout(buttonsGroupLayout);
		
		addModuleButton = toolkit.createButton(buttonsGroup, "Add module", SWT.PUSH);
		addModuleButton.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		addModuleButton.addSelectionListener(this);
		
		addTestCaseFileButton = toolkit.createButton(buttonsGroup, "Add test case file", SWT.PUSH);
		addTestCaseFileButton.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		addTestCaseFileButton.addSelectionListener(this);
		
		addIniFileButton = toolkit.createButton(buttonsGroup, "Add ini file", SWT.PUSH);
		addIniFileButton.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		addIniFileButton.addSelectionListener(this);
		
		editButton = toolkit.createButton(buttonsGroup, "Edit", SWT.PUSH);
		editButton.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		editButton.addSelectionListener(this);
		
		removeButton = toolkit.createButton(buttonsGroup, "Remove", SWT.PUSH);
		removeButton.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		removeButton.addSelectionListener(this);
		
		// Final initializations
		modulesMainForm.getBody().setLayout(modulesMainFormLayout);
		modulesSectionClient.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(modulesSectionClient);
		
		modulesPageIndex = addPage(modulesMainForm);
		setPageText(modulesPageIndex, "Modules");
		
		modulesTreeRoot = new ModulesTreeNode();
		modulesTreeViewer.setContentProvider( new ModulesTreeContentProvider() );
		modulesTreeViewer.setLabelProvider( new ModulesTreeLabelProvider() );
		modulesTreeViewer.setInput( modulesTreeRoot );
	}	
	
	/**
	 * Creates config source page 
	 */
	void createSourcePage() {
		try {
			sourceEditor = new ConfigSourceEditor();
			sourcePageIndex = addPage(sourceEditor, getEditorInput());
			setPageText(sourcePageIndex, "Source");
						
		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating STIF source config editor",
				null,
				e.getStatus());
		}
	}

	
	/**
	 * Creates the pages of the STIF config editor
	 */
	protected void createPages() {
		// Create config editor pages
		toolkit = new FormToolkit(getContainer().getDisplay());
		createModulesPage();
		createGlobalSettingsPage();
		createSourcePage();
		// Parse config and update config editor pages
		loadConfig();
	}
	
	/**
	 * Loads config, updates controls value and problem markers 
	 */
	private void loadConfig() {
		// Get text from source editor
		String config = sourceEditor.getDocumentProvider().getDocument(
				sourceEditor.getEditorInput()).get();
		// Parse config
		configManager = new ConfigManager();
		configManager.parseConfig(config);	
		
		// Update problem markers
		updateProblemMarkers();
		
		// update values in controls
		updateControlsValue();
	}

	/**
	 * Updates engine defaults panel contols value
	 */
	private void updateEngineDefaultsControlsValue() {
		Color red = toolkit.getColors().createColor("red", new RGB(255,0,0));
		Color black = toolkit.getColors().createColor("black", new RGB(0,0,0));
		Color gray = toolkit.getColors().createColor("gray", new RGB(170,170,170));
		
		testReportModeLabel.setForeground(black);
		createTestReportLabel.setForeground(black);
		testReportFilePathLabel.setForeground(black);
		testReportFileNameLabel.setForeground(black);
		testReportFormatLabel.setForeground(black);
		testReportOutputLabel.setForeground(black);
		testReportFileCreationModeLabel.setForeground(black);
		deviceResetDllNameLabel.setForeground(black);
		disableMeasurementLabel.setForeground(black);
		timeoutLabel.setForeground(black);
		uiTestingSupportLabel.setForeground(black);
		separateProcessLabel.setForeground(black);
		
		TestReportMode testReportMode = configManager.getTestReportMode();
		if ( testReportMode == TestReportMode.NOT_DEFINED || 
				testReportMode == TestReportMode.UNKNOWN ){
			if ( testReportMode == TestReportMode.NOT_DEFINED ){
				testReportModeLabel.setForeground(gray);
			}
			if ( testReportMode == TestReportMode.UNKNOWN ) {
				testReportModeLabel.setForeground(red);
			}
			testReportMode = ConfigDefaults.getTestReportModeDefaultValue();
		}
		
		String[] testReportModeNames = ConfigUtil.getTestReportModeNames();
		String testReportModeName = ConfigUtil.getTestReportModeName(testReportMode);
		int selection = -1;
		testReportModeValue.removeAll();
		for ( int i = 0; i < testReportModeNames.length; i++ ) {
			testReportModeValue.add( testReportModeNames[ i ] );
			if ( testReportModeNames[ i ].equals(testReportModeName) ) {
				selection = i;
			}
		}
		if ( selection != -1 ) {
			testReportModeValue.select(selection);
		}
		
		// CreateTestReport 
		YesNo createTestReport = configManager.getCreateTestReport();
		if ( ( createTestReport == YesNo.NOT_DEFINED ) ||
			 ( createTestReport == YesNo.UNKNOWN ) ) {
			if ( createTestReport == YesNo.NOT_DEFINED ){
				createTestReportLabel.setForeground(gray);
			}
			if ( createTestReport == YesNo.UNKNOWN ){
				createTestReportLabel.setForeground(red);
			}
			createTestReport = ConfigDefaults.getCreateTestReportDefaultValue();
		}
		if ( createTestReport == YesNo.YES ) {
			createTestReportYesButton.setSelection(true);
			createTestReportNoButton.setSelection(false);
		} else if ( createTestReport == YesNo.NO ) {
			createTestReportYesButton.setSelection(false);
			createTestReportNoButton.setSelection(true);
		}
		
		// TestReportFilePath
		String testReportFilePath = configManager.getTestReportFilePath();
		if ( testReportFilePath == null ) {
			testReportFilePath = ConfigDefaults.getTestReportFilePathDefaultValue();
			testReportFilePathLabel.setForeground(gray);
		}			
		testReportFilePathValue.removeModifyListener(this);
		testReportFilePathValue.setText(testReportFilePath);
		testReportFilePathValue.addModifyListener(this);
		
		// TestReportFileName
		String testReportFileName = configManager.getTestReportFileName();
		if ( testReportFileName == null ) {
			testReportFileName = ConfigDefaults.getTestReportFileNameDefaultValue();
			testReportFileNameLabel.setForeground(gray);
		}
		testReportFileNameValue.removeModifyListener(this);
		testReportFileNameValue.setText(testReportFileName);
		testReportFileNameValue.addModifyListener(this);
		
		// TestReportFormat
		OutputFileFormat testReportFormat = configManager.getTestReportFormat();
		if ( ( testReportFormat == OutputFileFormat.NOT_DEFINED ) ||
			 ( testReportFormat == OutputFileFormat.UNKNOWN ) ) {
			if ( testReportFormat == OutputFileFormat.NOT_DEFINED ){
				testReportFormatLabel.setForeground(gray);
			}
			if ( testReportFormat == OutputFileFormat.UNKNOWN ){
				testReportFormatLabel.setForeground(red);
			}
			testReportFormat = ConfigDefaults.getTestReportFormatDefaultValue();
		}
		if ( testReportFormat == OutputFileFormat.TXT ) {
			testReportFormatTxtButton.setSelection(true);
			testReportFormatHtmlButton.setSelection(false);
		} else if ( testReportFormat == OutputFileFormat.HTML ) {
			testReportFormatTxtButton.setSelection(false);
			testReportFormatHtmlButton.setSelection(true);			
		}
		
		// TestReportOutput
		OutputType testReportOutput = configManager.getTestReportOutput();
		if ( ( testReportOutput == OutputType.NOT_DEFINED ) ||
			 ( testReportOutput == OutputType.UNKNOWN ) ) {
			if ( testReportOutput == OutputType.NOT_DEFINED ){
				testReportOutputLabel.setForeground(gray);
			}
			if ( testReportOutput == OutputType.UNKNOWN ){
				testReportOutputLabel.setForeground(red);
			}
			testReportOutput = ConfigDefaults.getTestReportOutputDefaultValue();
		}
		if ( testReportOutput == OutputType.FILE ) {
			testReportOutputFileButton.setSelection(true);
			testReportOutputRDebugButton.setSelection(false);
		} else if ( testReportOutput == OutputType.RDEBUG ) {
			testReportOutputFileButton.setSelection(false);
			testReportOutputRDebugButton.setSelection(true);
		}
		
		// TestReportFileCreationMode
		FileCreationMode testReportFileCreationMode = configManager.getTestReportFileCreationMode();
		if ( ( testReportFileCreationMode == FileCreationMode.NOT_DEFINED ) ||
			 ( testReportFileCreationMode == FileCreationMode.UNKNOWN ) ) {
			if ( testReportFileCreationMode == FileCreationMode.NOT_DEFINED ){
				testReportFileCreationModeLabel.setForeground(gray);
			}
			if ( testReportFileCreationMode == FileCreationMode.UNKNOWN ){
				testReportFileCreationModeLabel.setForeground(red);
			}
			testReportFileCreationMode = ConfigDefaults.getTestReportFileCreationModeDefaultValue();
		}
		if ( testReportFileCreationMode == FileCreationMode.APPEND ) {
			testReportFileCreationModeOverwriteButton.setSelection(false);
			testReportFileCreationModeAppendButton.setSelection(true);
		} else if ( testReportFileCreationMode == FileCreationMode.OVERWRITE ) {
			testReportFileCreationModeOverwriteButton.setSelection(true);
			testReportFileCreationModeAppendButton.setSelection(false);
		}
		
		// DeviceResetDllName
		String deviceResetDllName = configManager.getDeviceResetDllName();
		if ( deviceResetDllName == null ) {
			deviceResetDllName = ConfigDefaults.getDeviceResetDllNameDefaultValue();
			deviceResetDllNameLabel.setForeground(gray);
		}			
		deviceResetDllNameValue.removeModifyListener(this);
		deviceResetDllNameValue.setText(deviceResetDllName);
		deviceResetDllNameValue.addModifyListener(this);		
		
		// DisableMeasurement
		MeasurementModule disableMeasurement = configManager.getDisableMeasurement();
		if ( ( disableMeasurement == MeasurementModule.NOT_DEFINED ) ||
			 ( disableMeasurement == MeasurementModule.UNKNOWN )) {
			if ( disableMeasurement == MeasurementModule.NOT_DEFINED ){
				disableMeasurementLabel.setForeground(gray);
			}
			if ( disableMeasurement == MeasurementModule.UNKNOWN ){
				disableMeasurementLabel.setForeground(red);
			}
			disableMeasurement = ConfigDefaults.getDisableMeasurementDefaultValue();
		}
		String[] measurementModuleNames = ConfigUtil.getMeasurementModuleNames();
		String measurementModuleName = ConfigUtil.getMeasurementModuleName(disableMeasurement);
		selection = -1;
		disableMeasurementValue.removeAll();
		for ( int i = 0; i < measurementModuleNames.length; i++ ) {
			disableMeasurementValue.add( measurementModuleNames[ i ] );
			if ( measurementModuleNames[ i ].equals(measurementModuleName) ) {
				selection = i;
			}
		}
		if ( selection != -1 ) {
			disableMeasurementValue.select(selection);
		}
		
		// Timeout
		Integer timeout = configManager.getTimeout();
		if ( timeout == null ) {
			timeout = ConfigDefaults.getTimeoutDefaultValue();
		}			
		timeoutValue.setSelection(timeout);	
		
		// UITestingSupport
		YesNo uiTestingSupport = configManager.getUITestingSupport();
		if ( ( uiTestingSupport == YesNo.NOT_DEFINED ) ||
			 ( uiTestingSupport == YesNo.UNKNOWN ) ) {
			if ( uiTestingSupport == YesNo.NOT_DEFINED ) {
				uiTestingSupportLabel.setForeground(gray);
			}
			if ( uiTestingSupport == YesNo.UNKNOWN ){
				uiTestingSupportLabel.setForeground(red);
			}
			uiTestingSupport = ConfigDefaults.getUITestingSupportDefaultValue();
		}
		if(uiTestingSupport == YesNo.YES) {
			uiTestingSupportYesButton.setSelection(true);
			uiTestingSupportNoButton.setSelection(false);
		}
		else if(uiTestingSupport == YesNo.NO) {
			uiTestingSupportYesButton.setSelection(false);
			uiTestingSupportNoButton.setSelection(true);
		}
		
		// SeparateProcesses
		YesNo separateProcesses = configManager.getSeparateProcesses();
		if ( ( separateProcesses == YesNo.NOT_DEFINED ) ||
			 ( separateProcesses == YesNo.UNKNOWN ) ) {
			if ( separateProcesses == YesNo.NOT_DEFINED ) {
				separateProcessLabel.setForeground(gray);
			}
			if ( separateProcesses == YesNo.UNKNOWN ) {
				separateProcessLabel.setForeground(red);
			}
			separateProcesses = ConfigDefaults.getSeparateProcessesDefaultValue();
		}
		if(separateProcesses == YesNo.YES) {
			separateProcessYesButton.setSelection(true);
			separateProcessNoButton.setSelection(false);
		}
		else if(separateProcesses == YesNo.NO) {
			separateProcessYesButton.setSelection(false);
			separateProcessNoButton.setSelection(true);
		}
	}
	
	/**
	 * Updates logger defaults panel controls value
	 */
	private void updateLoggerDefaultsControlsValue() {
		Color red = toolkit.getColors().createColor("red", new RGB(255,0,0));
		Color black = toolkit.getColors().createColor("black", new RGB(0,0,0));
		Color gray = toolkit.getColors().createColor("gray", new RGB(170,170,170));

		createLogDirectoriesLabel.setForeground(black);
		emulatorBasePathLabel.setForeground(black);
		emulatorLogFormatLabel.setForeground(black);
		emulatorLogOutputLabel.setForeground(black);
		hardwareBasePathLabel.setForeground(black);
		hardwareLogFormatLabel.setForeground(black);
		hardwareLogOutputLabel.setForeground(black);
		logFileCreationModeLabel.setForeground(black);
		threadIdToLogFileLabel.setForeground(black);
		withTimeStampLabel.setForeground(black);
		withLineBreakLabel.setForeground(black);
		fileUnicodeLabel.setForeground(black);
		addTestcaseTitleLabel.setForeground(black);
		withEventRankingLabel.setForeground(black);
		
		// CreateLogDirectories 
		YesNo createLogDirectories = configManager.getCreateLogDirectories();
		if ( ( createLogDirectories == YesNo.NOT_DEFINED ) ||
			 ( createLogDirectories == YesNo.UNKNOWN ) ) {
			if ( createLogDirectories == YesNo.NOT_DEFINED ){
				createLogDirectoriesLabel.setForeground(gray);
			}
			if ( createLogDirectories == YesNo.UNKNOWN ){
				createLogDirectoriesLabel.setForeground(red);
			}
			createLogDirectories = ConfigDefaults.getCreateLogDirectoriesDefaultValue();
		}
		if ( createLogDirectories == YesNo.YES ) {
			createLogDirectoriesYesButton.setSelection(true);
			createLogDirectoriesNoButton.setSelection(false);
		} else if ( createLogDirectories == YesNo.NO ) {
			createLogDirectoriesYesButton.setSelection(false);
			createLogDirectoriesNoButton.setSelection(true);
		}		
		
		// EmulatorBasePath
		String emulatorLogBasePath = configManager.getEmulatorBasePath();
		if ( emulatorLogBasePath == null ) {
			emulatorLogBasePath = ConfigDefaults.getEmulatorBasePathDefaultValue();
			emulatorBasePathLabel.setForeground(gray);
		}
		emulatorBasePathValue.removeModifyListener(this);
		emulatorBasePathValue.setText(emulatorLogBasePath);
		emulatorBasePathValue.addModifyListener(this);
		
		// EmulatorFormat 
		OutputFileFormat emulatorFormat = configManager.getEmulatorLogFormat();
		if ( ( emulatorFormat == OutputFileFormat.NOT_DEFINED ) ||
			 ( emulatorFormat == OutputFileFormat.UNKNOWN ) ) {
			if ( emulatorFormat == OutputFileFormat.NOT_DEFINED ){
				emulatorLogFormatLabel.setForeground(gray);
			}
			if ( emulatorFormat == OutputFileFormat.UNKNOWN ){
				emulatorLogFormatLabel.setForeground(red);
			}
			emulatorFormat = ConfigDefaults.getEmulatorLogFormatDefaultValue();
		}
		if ( emulatorFormat == OutputFileFormat.TXT ) {
			emulatorLogFormatTxtButton.setSelection(true);
			emulatorLogFormatHtmlButton.setSelection(false);
		} else if ( emulatorFormat == OutputFileFormat.HTML ) {
			emulatorLogFormatTxtButton.setSelection(false);
			emulatorLogFormatHtmlButton.setSelection(true);			
		}
		
		// EmulatorOutput emulatorOutput
		OutputType emulatorOutput = configManager.getEmulatorLogOutput();
		if ( ( emulatorOutput == OutputType.NOT_DEFINED ) ||
			 ( emulatorOutput == OutputType.UNKNOWN ) ) {
			if ( emulatorOutput == OutputType.NOT_DEFINED ){
				emulatorLogOutputLabel.setForeground(gray);
			}
			if ( emulatorOutput == OutputType.UNKNOWN ){
				emulatorLogOutputLabel.setForeground(red);
			}
			emulatorOutput = ConfigDefaults.getEmulatorLogOutputDefaultValue();
		}
		if ( emulatorOutput == OutputType.FILE ) {
			emulatorLogOutputFileButton.setSelection(true);
			emulatorLogOutputRDebugButton.setSelection(false);
		} else if ( emulatorOutput == OutputType.RDEBUG ) {
			emulatorLogOutputFileButton.setSelection(false);
			emulatorLogOutputRDebugButton.setSelection(true);
		}
				
		// HardwareBasePath
		String hardwareLogBasePath = configManager.getHardwareBasePath();
		if ( hardwareLogBasePath == null ) {
			hardwareLogBasePath = ConfigDefaults.getHardwareBasePathDefaultValue();
			hardwareBasePathLabel.setForeground(gray);
		}
		hardwareBasePathValue.removeModifyListener(this);
		hardwareBasePathValue.setText(hardwareLogBasePath);
		hardwareBasePathValue.addModifyListener(this);
		
		// HardwareFormat 
		OutputFileFormat hardwareFormat = configManager.getHardwareLogFormat();
		if ( ( hardwareFormat == OutputFileFormat.NOT_DEFINED ) ||
			 ( hardwareFormat == OutputFileFormat.UNKNOWN ) ) {
			if ( hardwareFormat == OutputFileFormat.NOT_DEFINED ){
				hardwareLogFormatLabel.setForeground(gray);
			}
			if ( hardwareFormat == OutputFileFormat.UNKNOWN ){
				hardwareLogFormatLabel.setForeground(red);
			}
			hardwareFormat = ConfigDefaults.getHardwareLogFormatDefaultValue();
		}
		if ( hardwareFormat == OutputFileFormat.TXT ) {
			hardwareLogFormatTxtButton.setSelection(true);
			hardwareLogFormatHtmlButton.setSelection(false);
		} else if ( emulatorFormat == OutputFileFormat.HTML ) {
			hardwareLogFormatTxtButton.setSelection(false);
			hardwareLogFormatHtmlButton.setSelection(true);			
		}
		
		// EmulatorOutput emulatorOutput
		OutputType hardwareOutput = configManager.getHardwareLogOutput();
		if ( ( hardwareOutput == OutputType.NOT_DEFINED ) ||
			 ( hardwareOutput == OutputType.UNKNOWN ) ) {
			if ( hardwareOutput == OutputType.NOT_DEFINED ){
				hardwareLogOutputLabel.setForeground(gray);
			}
			if ( hardwareOutput == OutputType.UNKNOWN ){
				hardwareLogOutputLabel.setForeground(red);
			}
			hardwareOutput = ConfigDefaults.getHardwareLogOutputDefaultValue();
		}
		if ( hardwareOutput == OutputType.FILE ) {
			hardwareLogOutputFileButton.setSelection(true);
			hardwareLogOutputRDebugButton.setSelection(false);
		} else if ( emulatorOutput == OutputType.RDEBUG ) {
			hardwareLogOutputFileButton.setSelection(false);
			hardwareLogOutputRDebugButton.setSelection(true);
		}
		
		// FileCreationMode
		FileCreationMode logFileCreationMode = configManager.getLogFileCreationMode();
		if ( ( logFileCreationMode == FileCreationMode.NOT_DEFINED ) ||
			 ( logFileCreationMode == FileCreationMode.UNKNOWN ) ) {
			if ( logFileCreationMode == FileCreationMode.NOT_DEFINED ){
				logFileCreationModeLabel.setForeground(gray);
			}
			if ( logFileCreationMode == FileCreationMode.UNKNOWN ){
				logFileCreationModeLabel.setForeground(red);
			}
			logFileCreationMode = ConfigDefaults.getLogFileCreationModeDefaultValue();
		}
		if ( logFileCreationMode == FileCreationMode.APPEND ) {
			logFileCreationModeOverwriteButton.setSelection(false);
			logFileCreationModeAppendButton.setSelection(true);
		} else if ( logFileCreationMode == FileCreationMode.OVERWRITE ) {
			logFileCreationModeOverwriteButton.setSelection(true);
			logFileCreationModeAppendButton.setSelection(false);
		}
		
		// ThreadIdToLogFile
		YesNo threadIdToLogFile = configManager.getThreadIdToLogFile();
		if ( ( threadIdToLogFile == YesNo.NOT_DEFINED ) ||
			 ( threadIdToLogFile == YesNo.UNKNOWN ) ) {
			if ( threadIdToLogFile == YesNo.NOT_DEFINED ){
				threadIdToLogFileLabel.setForeground(gray);
			}
			if ( threadIdToLogFile == YesNo.UNKNOWN ){
				threadIdToLogFileLabel.setForeground(red);
			}
			threadIdToLogFile = ConfigDefaults.getThreadIdToLogFileDefaultValue();
		}
		if ( threadIdToLogFile == YesNo.YES ) {
			threadIdToLogFileYesButton.setSelection(true);
			threadIdToLogFileNoButton.setSelection(false);
		} else if ( threadIdToLogFile == YesNo.NO ) {
			threadIdToLogFileYesButton.setSelection(false);
			threadIdToLogFileNoButton.setSelection(true);
		}		

		// WithTimeStamp
		YesNo withTimeStamp = configManager.getWithTimeStamp();
		if ( ( withTimeStamp == YesNo.NOT_DEFINED ) ||
			 ( withTimeStamp == YesNo.UNKNOWN ) ) {
			if ( withTimeStamp == YesNo.NOT_DEFINED ){
				withTimeStampLabel.setForeground(gray);
			}
			if ( withTimeStamp == YesNo.UNKNOWN ){
				withTimeStampLabel.setForeground(red);
			}
			withTimeStamp = ConfigDefaults.getWithTimeStampDefaultValue();
		}
		if ( withTimeStamp == YesNo.YES ) {
			withTimeStampYesButton.setSelection(true);
			withTimeStampNoButton.setSelection(false);
		} else if ( withTimeStamp == YesNo.NO ) {
			withTimeStampYesButton.setSelection(false);
			withTimeStampNoButton.setSelection(true);
		}		
		
		// WithLineBreak
		YesNo withLineBreak = configManager.getWithLineBreak();
		if ( ( withLineBreak == YesNo.NOT_DEFINED ) ||
			 ( withLineBreak == YesNo.UNKNOWN ) ) {
			if ( withLineBreak == YesNo.NOT_DEFINED ){
				withLineBreakLabel.setForeground(gray);
			}
			if ( withLineBreak == YesNo.UNKNOWN ){
				withLineBreakLabel.setForeground(red);
			}
			withLineBreak = ConfigDefaults.getWithLineBreakDefaultValue();
		}
		if ( withLineBreak == YesNo.YES ) {
			withLineBreakYesButton.setSelection(true);
			withLineBreakNoButton.setSelection(false);
		} else if ( withLineBreak == YesNo.NO ) {
			withLineBreakYesButton.setSelection(false);
			withLineBreakNoButton.setSelection(true);
		}		

		// WithEventRanking
		YesNo withEventRanking = configManager.getWithEventRanking();
		if ( ( withEventRanking == YesNo.NOT_DEFINED ) ||
			 ( withEventRanking == YesNo.UNKNOWN ) ) {
			if ( withEventRanking == YesNo.NOT_DEFINED ){
				withEventRankingLabel.setForeground(gray);
			}
			if ( withEventRanking == YesNo.UNKNOWN ){
				withEventRankingLabel.setForeground(red);
			}
			withEventRanking = ConfigDefaults.getWithEventRankingDefaultValue();
		}
		if ( withEventRanking == YesNo.YES ) {
			withEventRankingYesButton.setSelection(true);
			withEventRankingNoButton.setSelection(false);
		} else if ( withEventRanking == YesNo.NO ) {
			withEventRankingYesButton.setSelection(false);
			withEventRankingNoButton.setSelection(true);
		}		
		
		// FileUnicode
		YesNo fileUnicode = configManager.getFileUnicode();
		if ( ( fileUnicode == YesNo.NOT_DEFINED ) ||
			 ( fileUnicode == YesNo.UNKNOWN ) ) {
			if ( fileUnicode == YesNo.NOT_DEFINED ){
				fileUnicodeLabel.setForeground(gray);
			}
			if ( fileUnicode == YesNo.UNKNOWN ){
				fileUnicodeLabel.setForeground(red);
			}
			fileUnicode = ConfigDefaults.getFileUnicodeDefaultValue();
		}
		if ( fileUnicode == YesNo.YES ) {
			fileUnicodeYesButton.setSelection(true);
			fileUnicodeNoButton.setSelection(false);
		} else if ( fileUnicode == YesNo.NO ) {
			fileUnicodeYesButton.setSelection(false);
			fileUnicodeNoButton.setSelection(true);
		}
		
		// AddTestCaseTile
		YesNo addTestcaseTitle = configManager.getAddTestcaseTitle();
		if ( ( addTestcaseTitle == YesNo.NOT_DEFINED ) ||
			 ( addTestcaseTitle == YesNo.UNKNOWN ) ) {
			if ( addTestcaseTitle == YesNo.NOT_DEFINED ){
				addTestcaseTitleLabel.setForeground(gray);
			}
			if ( addTestcaseTitle == YesNo.UNKNOWN ){
				addTestcaseTitleLabel.setForeground(red);
			}
			addTestcaseTitle = ConfigDefaults.getAddTestCaseTitleDefaultValue();
		}
		if ( addTestcaseTitle == YesNo.YES ) {
			addTestCaseTitleYesButton.setSelection(true);
			addTestCaseTitleNoButton.setSelection(false);
		} else if ( addTestcaseTitle == YesNo.NO ) {
			addTestCaseTitleYesButton.setSelection(false);
			addTestCaseTitleNoButton.setSelection(true);
		}		
	}
	
	/**
	 * Updates modules panel controls value
	 */
	private void updateModulesControlsValue() {
		modulesTreeRoot.removeAllChildreans();
				
		int modulesCount = configManager.getModulesCount();
		for ( int i = 0; i < modulesCount; i++ ) {
			String moduleName = configManager.getModuleName(i);
			if ( ( moduleName == null ) || ( moduleName.length() == 0 ) ) {
				continue;
			}
			
			ModulesTreeNode moduleNode = new ModulesTreeNode( modulesTreeRoot );
			moduleNode.setType( SectionElementType.MODULE_NAME );
			moduleNode.setIndex( i );
			moduleNode.setValue( moduleName );
			
			String iniFileName = configManager.getModuleIniFile(i);
			if ( ( iniFileName != null ) && ( iniFileName.length() != 0 ) ) {
				ModulesTreeNode iniFileNode = new ModulesTreeNode( moduleNode );
				iniFileNode.setType( SectionElementType.INI_FILE );
				iniFileNode.setIndex( 0 );
				iniFileNode.setValue( iniFileName );
			}
			
			int configFilesCount = configManager.getModuleTestcaseFilesCount(i);			
			for ( int k = 0; k < configFilesCount ; k++ ) {
				String configFileName = configManager.getModuleTestCaseFile(i, k);
				if ( ( configFileName == null ) || ( configFileName.length() == 0 ) ) {
					continue;
				}
				
				ModulesTreeNode configFileNode = new ModulesTreeNode( moduleNode );
				configFileNode.setType( SectionElementType.TEST_CASE_FILE );
				configFileNode.setIndex( k );
				configFileNode.setValue( configFileName );				
			}
		} 
		
		modulesTreeViewer.refresh();
	}
	
	/**
	 * Updates global settings and modules pages controls value
	 */
	private void updateControlsValue() {
		updateEngineDefaultsControlsValue();
		updateLoggerDefaultsControlsValue();
		updateModulesControlsValue();
	}
	
	/**
	 * Updates problem markers
	 */
	private void updateProblemMarkers() {
		clearProblemMarkers();
		createProblemMarkers( configManager.getParseProblems(), getResource() );		
	}
	
	/**
	 * Creates problem markers
	 * @param problems list of problems
	 * @param resource resource used to create markers
	 */
	private void createProblemMarkers(ArrayList<ParseProblem> problems, IResource resource) {
		if ( ( problems == null )|| ( resource == null ) ) {
			return;
		}
		
		// Create new problem markers if some problems occures during parsing
		if ( problems != null ) {
			try {
				int problemsCount = problems.size();
				problemMarkers = new ArrayList<IMarker>();
				for( int i = 0; i < problemsCount; i++ ) {
					IMarker marker = resource.createMarker(IMarker.PROBLEM);
					marker.setAttribute(IMarker.LINE_NUMBER, problems.get(i).lineNumber);
					marker.setAttribute(IMarker.MESSAGE, problems.get(i).description);
					if ( problems.get(i).type == ParseProblem.ProblemType.ERROR ) {
						marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);											
					} else if ( problems.get(i).type == ParseProblem.ProblemType.WARNING ) {
						marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);											
					}
					problemMarkers.add(marker);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}									
		}
	}

	/**
	 * Removes problem markers 
	 * @param resource
	 */
	private void clearProblemMarkers() {
		if ( problemMarkers == null ) {
			return;
		}
		
		try {
			for( int i = 0; i < problemMarkers.size(); i++ ) {
				problemMarkers.get(i).delete();
			}
			problemMarkers.clear();
		} catch (CoreException e) {
		   e.printStackTrace();
		}
	}
		
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors
	 */
	public void dispose() {
		clearProblemMarkers();
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	
	/**
	 * Saves the editor document
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(sourcePageIndex).doSave(monitor);
		loadConfig();
	}
	
	/**
	 * Saves the editor document as another file
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(sourcePageIndex);
		editor.doSaveAs();
		setPageText(sourcePageIndex, editor.getTitle());
		setInput(editor.getEditorInput());
		loadConfig();
	}

	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(sourcePageIndex);
		IDE.gotoMarker(getEditor(sourcePageIndex), marker);
	}
	
	
	/**
	 * Gets resource associated with editor input
	 * @return
	 */
	protected IResource getResource() {
		IEditorInput input= sourceEditor.getEditorInput();
		IResource resource= (IResource) input.getAdapter(IFile.class);
		if (resource == null) {
			resource = (IResource) input.getAdapter(IResource.class);
			if ( resource == null ) {
				resource = ResourcesPlugin.getWorkspace().getRoot();
				
			}
		}		
		return resource;
	}	
	
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
				
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Updates the contents of modules/general settings page when it is activated.
	 */
	protected void pageChange(int pageIndex) {
		super.pageChange(pageIndex);
		if ( ( pageIndex == modulesPageIndex ) || ( pageIndex == globalSettingsPageIndex ) ) {
			loadConfig();
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)sourceEditor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(sourceEditor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}

	/* (non-Javadoc)
	 * Method declared on SelectionListener
	 */
	public void widgetDefaultSelected(SelectionEvent event) {
		handleSelection(event);
	}

	/* (non-Javadoc)
	 * Method declared on ModifyListener
	 */
	public void modifyText(ModifyEvent event ) {
		handleModifyText(event);
	}
	
	/* (non-Javadoc)
	 * Method declared on SelectionListener
	 */
	public void widgetSelected(SelectionEvent event) {		
		handleSelection(event);
	}

	/**
	 * Handles text controls modification event 
	 * @param event event
	 */
	private void handleModifyText( ModifyEvent event ) {
		Color black = toolkit.getColors().createColor("black", new RGB(0,0,0));
		
		Object source = event.getSource();
				
		// Engine defaults section
		if ( source == testReportFilePathValue ) {
			testReportFilePathLabel.setForeground(black);
			onTestReportFilePathChange();
		} else if ( source == testReportFileNameValue ) {
			testReportFileNameLabel.setForeground(black);
			onTestReportFileNameChange();
		} else if ( source == deviceResetDllNameValue ) {
			deviceResetDllNameLabel.setForeground(black);
			onDeviceResetDllNameChange();
		}
		// Logger defaults section
		else if ( source == emulatorBasePathValue ) {
			emulatorBasePathLabel.setForeground(black);
			onEmulatorBasePathChange();
		} else if ( source == hardwareBasePathValue ) {
			hardwareBasePathLabel.setForeground(black);
			onHardwareBasePathChange();
		}		
	}
	
	/**
	 * Handles controls selection event
	 * @param event event
	 */
	private void handleSelection( SelectionEvent event ) {
		Color black = toolkit.getColors().createColor("black", new RGB(0,0,0));
		Object source = event.getSource();
		
		// Engine defaults section
		if ( source == testReportModeValue ) {
			testReportModeLabel.setForeground(black);
			onTestReportModeChange();
		} else if ( ( source == createTestReportYesButton ) ||
			        ( source == createTestReportNoButton ) ) {
			createTestReportLabel.setForeground(black);
			onCreateTestReportChange( source );
		} else if ( source == testReportFilePathValue ) {
			testReportFilePathLabel.setForeground(black);
			onTestReportFilePathChange();
		} else if ( source == testReportFileNameValue ) {
			testReportFileNameLabel.setForeground(black);
			onTestReportFileNameChange();
		} else if ( ( source == testReportFormatTxtButton ) ||
				    ( source == testReportFormatHtmlButton ) ) {
			testReportFormatLabel.setForeground(black);
			onTestReportFormatChange( source );
		} else if ( ( source == testReportOutputFileButton ) ||
				    ( source == testReportOutputRDebugButton ) ) {
			testReportOutputLabel.setForeground(black);
			onTestReportOutputChange( source );
		} else if ( ( source == testReportFileCreationModeOverwriteButton ) || 
				    ( source == testReportFileCreationModeAppendButton ) ) {
			testReportFileCreationModeLabel.setForeground(black);
			onTestReportFileCreationModeChange( source );
		} else if ( source == deviceResetDllNameValue ) {
			deviceResetDllNameLabel.setForeground(black);
			onDeviceResetDllNameChange();
		} else if ( source == disableMeasurementValue ) {
			disableMeasurementLabel.setForeground(black);
			onDisableMeasurementChange();
		} else if ( source == timeoutValue ) {
			onTimeoutChange();
		} else if ( ( source == uiTestingSupportYesButton ) ||
					( source == uiTestingSupportNoButton ) ) {
			uiTestingSupportLabel.setForeground(black);
			onUITestingSupportButtonSelect();
		} else if ( ( source == separateProcessYesButton ) ||
					( source == separateProcessNoButton ) ) {
			separateProcessLabel.setForeground(black);
			onSeparateProcessesButtonSelect();
		}
		// Logger defaults section
		else if ( ( source == createLogDirectoriesYesButton ) ||
				  ( source == createLogDirectoriesNoButton ) ) {
			createLogDirectoriesLabel.setForeground(black);
			onCreateLogDirectoriesChange( source );
		} else if ( source == emulatorBasePathValue ) {
			emulatorBasePathLabel.setForeground(black);
			onEmulatorBasePathChange();
		} else if ( ( source == emulatorLogFormatTxtButton ) || 
				    ( source == emulatorLogFormatHtmlButton ) ) {
			emulatorLogFormatLabel.setForeground(black);
			onEmulatorLogFormatChange( source );
		} else if ( ( source == emulatorLogOutputFileButton ) || 
				    ( source == emulatorLogOutputRDebugButton ) ) {
			emulatorLogOutputLabel.setForeground(black);
			onEmulatorLogOutputChange( source );
		} else if ( source == hardwareBasePathValue ) {
			hardwareBasePathLabel.setForeground(black);
			onHardwareBasePathChange();
		} else if ( ( source == hardwareLogFormatTxtButton ) || 
				    ( source == hardwareLogFormatHtmlButton ) ) {
			hardwareLogFormatLabel.setForeground(black);
			onHarwdareLogFormatChange( source );
		} else if ( ( source == hardwareLogOutputFileButton ) || 
				    ( source == hardwareLogOutputRDebugButton ) ) {
			hardwareLogOutputLabel.setForeground(black);
			onHardwareLogOutputChange( source );
		} else if ( ( source == logFileCreationModeOverwriteButton ) ||
				    ( source == logFileCreationModeAppendButton ) ) {
			logFileCreationModeLabel.setForeground(black);
			onLogFileCreationModeChange( source );
		} else if ( ( source == threadIdToLogFileYesButton ) || 
				    ( source == threadIdToLogFileNoButton ) ) {
			threadIdToLogFileLabel.setForeground(black);
			onThreadIdToLogFileChange( source );
		} else if ( ( source == withTimeStampYesButton ) || 
				    ( source == withTimeStampNoButton )) {
			withTimeStampLabel.setForeground(black);
			onWithTimeStampChange( source );
		} else if ( ( source == withLineBreakYesButton ) ||
				    ( source == withLineBreakNoButton )) {
			withLineBreakLabel.setForeground(black);
			onWithLineBreakChange( source );
		} else if ( ( source == withEventRankingYesButton ) ||
				    ( source == withEventRankingNoButton ) ) {
			withEventRankingLabel.setForeground(black);
			onWithEventRankingChange( source );
		} else if ( ( source == fileUnicodeYesButton ) ||
				    ( source == fileUnicodeNoButton ) ) {
			fileUnicodeLabel.setForeground(black);
			onFileUnicodeChange( source );
		} else if ( ( source == addTestCaseTitleYesButton ) ||
					( source == addTestCaseTitleNoButton )) {
			addTestcaseTitleLabel.setForeground(black);
			onAddTestcaseTitleChange( source );
		}
		// Modules page
		else if ( source == modulesTreeViewer ) {
			onModulesTreeElementSelect();
		} else if ( source == addModuleButton ) {
			onAddModuleButtonSelect();
		} else if ( source == addIniFileButton ) {
			onAddIniFileToModuleButtonSelect();
		} else if ( source == addTestCaseFileButton ) {
			onAddTestCaseFileToModuleButtonSelect();
		} else if ( source == editButton ) {
			onEditButtonSelect();
		} else if ( source == removeButton ) {
			onRemoveButtonSelect();
		}		
	}

	
	/**
	 * Handles <code>FileUnicode</code> value change 
	 * @param source event source
	 */
	private void onFileUnicodeChange(Object source) {
		if ( fileUnicodeYesButton.getSelection() == true ) {
			configManager.setFileUnicode( YesNo.YES );
		} else if ( fileUnicodeNoButton.getSelection() == true ) {
			configManager.setFileUnicode( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}
	
	/**
	 * Handles <code>AddTestCaseTitle</code> value change 
	 * @param source event source
	 */
	private void onAddTestcaseTitleChange(Object source) {
		if(addTestCaseTitleYesButton.getSelection()){
			configManager.setAddTestcaseTitle(YesNo.YES);
		} else if(addTestCaseTitleNoButton.getSelection()) {
			configManager.setAddTestcaseTitle(YesNo.NO);
		} else 
			return;
		syncSourceWithViews();
	}

	/**
	 * Handles <code>WithEventRanking</code> value change
	 * @param source event source
	 */
	private void onWithEventRankingChange(Object source) {
		if ( withEventRankingYesButton.getSelection() == true ) {
			configManager.setWithEventRanking(YesNo.YES );
		} else if ( withEventRankingNoButton.getSelection() == true ) {
			configManager.setWithEventRanking( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>onWithTimeStampChange</code> value change
	 * @param source source
	 */
	private void onWithTimeStampChange(Object source) {
		if ( withTimeStampYesButton.getSelection() == true ) {
			configManager.setWithTimeStamp(YesNo.YES );
		} else if ( withTimeStampNoButton.getSelection() == true ) {
			configManager.setWithTimeStamp( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>ThreadIdToLogFile</code> value change
	 * @param source event source
	 */
	private void onThreadIdToLogFileChange(Object source) {
		if ( threadIdToLogFileYesButton.getSelection() == true ) {
			configManager.setThreadIdToLogFile(YesNo.YES );
		} else if ( threadIdToLogFileNoButton.getSelection() == true ) {
			configManager.setThreadIdToLogFile( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}
	
	/**
	 * Handles <code>LogFileCreationMode</code> value change
	 * @param source event source
	 */
	private void onLogFileCreationModeChange(Object source) {
		if ( logFileCreationModeAppendButton.getSelection() == true ) {
			configManager.setLogFileCreationMode( FileCreationMode.APPEND );
		} else if ( logFileCreationModeOverwriteButton.getSelection() == true ) {
			configManager.setLogFileCreationMode( FileCreationMode.OVERWRITE );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>HardwareLogOutput</code> value change
	 * @param source event source
	 */
	private void onHardwareLogOutputChange(Object source) {
		if ( hardwareLogOutputFileButton.getSelection() == true ) {
			configManager.setHardwareLogOutput( OutputType.FILE );
		} else if ( hardwareLogOutputRDebugButton.getSelection() == true ) {
			configManager.setHardwareLogOutput( OutputType.RDEBUG );
		} else {
			return;
		}
		syncSourceWithViews();		
	}
	
	/**
	 * Handles <code>HarwdareLogFormat</code> value change
	 * @param source
	 */
	private void onHarwdareLogFormatChange(Object source) {
		if ( hardwareLogFormatTxtButton.getSelection() == true ) {
			configManager.setHardwareLogFormat( OutputFileFormat.TXT );
		} else if ( hardwareLogFormatHtmlButton.getSelection() == true ) {
			configManager.setHardwareLogFormat( OutputFileFormat.HTML );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>HardwareBasePath</code> value change
	 */
	private void onHardwareBasePathChange() {
		configManager.setHardwareBasePath( hardwareBasePathValue.getText() );
		syncSourceWithViews();				
	}

	/**
	 * Handles <code>EmulatorLogFormat</code> value change
	 * @param source event source
	 */
	private void onEmulatorLogFormatChange(Object source) {
		if ( emulatorLogFormatTxtButton.getSelection() == true ) {
			configManager.setEmulatorLogFormat( OutputFileFormat.TXT );
		} else if ( emulatorLogFormatHtmlButton.getSelection() == true ) {
			configManager.setEmulatorLogFormat( OutputFileFormat.HTML );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>EmulatorLogOutput</code> value change
	 * @param source event source
	 */
	private void onEmulatorLogOutputChange(Object source) {
		if ( emulatorLogOutputFileButton.getSelection() == true ) {
			configManager.setEmulatorLogOutput( OutputType.FILE );
		} else if ( emulatorLogOutputRDebugButton.getSelection() == true ) {
			configManager.setEmulatorLogOutput( OutputType.RDEBUG );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>EmulatorBasePath</code> value change
	 */
	private void onEmulatorBasePathChange() {
		configManager.setEmulatorBasePath( emulatorBasePathValue.getText() );
		syncSourceWithViews();				
	}

	/**
	 * Handles <code>CreateLogDirectories</code> value change
	 * @param source event source
	 */
	private void onCreateLogDirectoriesChange(Object source) {
		if ( createLogDirectoriesYesButton.getSelection() == true ) {
			configManager.setCreateLogDirectories(YesNo.YES );
		} else if ( createLogDirectoriesNoButton.getSelection() == true ) {
			configManager.setCreateLogDirectories( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>WithLineBreak</code> value change
	 * @param source event source
	 */
	private void onWithLineBreakChange(Object source) {
		if ( withLineBreakYesButton.getSelection() == true ) {
			configManager.setWithLineBreak(YesNo.YES );
		} else if ( withLineBreakNoButton.getSelection() == true ) {
			configManager.setWithLineBreak( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}
	/**
	 * Handles <code>Timeout</code> value change
	 */
	private void onTimeoutChange() {
		configManager.setTimeout( timeoutValue.getSelection() );
		syncSourceWithViews();				
	}

	/**
	 * Handles <code>DisableMeasurement</code> value change
	 */
	private void onDisableMeasurementChange() {
		String module = disableMeasurementValue.getItem( disableMeasurementValue.getSelectionIndex() );		
		configManager.setDisableMeasurementModule(  ConfigUtil.getMeasurementModuleByName( module ) );
		syncSourceWithViews();
	}
	
	/**
	 * Handles <code>DeviceResetDllName</code> value change 
	 */
	private void onDeviceResetDllNameChange() {
		configManager.setDeviceResetDllName( deviceResetDllNameValue.getText() );
		syncSourceWithViews();				
	}

	/**
	 * Handles <code>TestReportFileCreationMode</code> value change
	 * @param source event source
	 */
	private void onTestReportFileCreationModeChange(Object source) {
		if ( testReportFileCreationModeAppendButton.getSelection() == true ) {
			configManager.setTestReportFileCreationMode( FileCreationMode.APPEND );
		} else if ( testReportFileCreationModeOverwriteButton.getSelection() == true ) {
			configManager.setTestReportFileCreationMode( FileCreationMode.OVERWRITE );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>TestReportOutput</code> value change
	 * @param source event source
	 */
	private void onTestReportOutputChange(Object source) {
		if ( testReportOutputFileButton.getSelection() == true ) {
			configManager.setTestReportOutput( OutputType.FILE );
		} else if ( testReportOutputRDebugButton.getSelection() == true ) {
			configManager.setTestReportOutput( OutputType.RDEBUG );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>TestReportFormat</code> value change
	 * @param source event source
	 */
	private void onTestReportFormatChange(Object source) {
		if ( testReportFormatTxtButton.getSelection() == true ) {
			configManager.setTestReportFormat( OutputFileFormat.TXT );
		} else if ( testReportFormatHtmlButton.getSelection() == true ) {
			configManager.setTestReportFormat( OutputFileFormat.HTML );
		} else {
			return;
		}
		syncSourceWithViews();		
	}
	
	/**
	 * Handles <code>TestReportFileName</code> value change
	 */
	private void onTestReportFileNameChange() {
		configManager.setTestReportFileName(testReportFileNameValue.getText());
		syncSourceWithViews();		
	}

	/**
	 * Handles <code>TestReportFilePath</code> value change
	 */
	private void onTestReportFilePathChange() {
		configManager.setTestReportFilePath(testReportFilePathValue.getText());
		syncSourceWithViews();
	}

	/**
	 * Handles <code>TestReportMode</code> value change
	 */
	private void onTestReportModeChange() {
		String mode = testReportModeValue.getItem(testReportModeValue.getSelectionIndex());
		configManager.setTestReportMode( ConfigUtil.getTestReportModeByName(mode) );
		syncSourceWithViews();				 
	}

	/**
	 * Handles <code>CreateTestReport</code> value change
	 * @param source
	 */
	private void onCreateTestReportChange(Object source) {
		if ( createTestReportYesButton.getSelection() == true ) {
			configManager.setCreateTestReport( YesNo.YES );
		} else if ( createTestReportNoButton.getSelection() == true ) {
			configManager.setCreateTestReport( YesNo.NO );
		} else {
			return;
		}
		syncSourceWithViews();		
	}

	/**
	 * Handles selection change in modules tree.
	 * 
	 * Depend which tree element is selected proper buttons in modules 
	 * page are enabled or disabled.
	 */
	private void onModulesTreeElementSelect() {
		TreeSelection selection = (TreeSelection)modulesTreeViewer.getSelection();
		ModulesTreeNode selectedItem = (ModulesTreeNode)selection.getFirstElement();
		
		if ( selectedItem == null ) {
			addModuleButton.setEnabled(true);
			addIniFileButton.setEnabled(false);
			addTestCaseFileButton.setEnabled(false);
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
			return;
		}
				
		switch( selectedItem.getType() ) {
			case MODULE_NAME:
				{
					addModuleButton.setEnabled(true);					
					addTestCaseFileButton.setEnabled(true);
					editButton.setEnabled(true);
					removeButton.setEnabled(true);
					
					addIniFileButton.setEnabled(true);
					java.util.List<ModulesTreeNode> childrens = selectedItem.getChildrens();
					if ( childrens != null ) {
						for ( int i = 0; i < childrens.size(); i++ ) {							
							if ( childrens.get( i ).getType() == SectionElementType.INI_FILE ) {
								addIniFileButton.setEnabled(false);
							}
						}
					}
				}
				break;
			case INI_FILE:
				{
					addModuleButton.setEnabled(true);
					addIniFileButton.setEnabled(false);
					addTestCaseFileButton.setEnabled(true);
					editButton.setEnabled(true);
					removeButton.setEnabled(true);
				}
				break;
			case TEST_CASE_FILE:
				{
					addModuleButton.setEnabled(true);
					addIniFileButton.setEnabled(true);
					java.util.List<ModulesTreeNode> subNodes = selectedItem.getParent().getChildrens(); 
					for ( int i = 0; i < subNodes.size(); i++ ) {
						if ( subNodes.get(i).getType() == SectionElementType.INI_FILE ) {
							addIniFileButton.setEnabled(false);
							break;
						}
					}
					addTestCaseFileButton.setEnabled(true);
					editButton.setEnabled(true);
					removeButton.setEnabled(true);			
				}
				break;
		}	
	}

	/**
	 * Handles <code>Add module</code> button selection
	 * 
	 * Adds new module to config file and modules tree
	 */
	private void onAddModuleButtonSelect() {
		NewModuleWizard moduleWizard = new NewModuleWizard( configManager );
		moduleWizard.init( null, null );
		WizardDialog dialog = new WizardDialog( modulesMainForm.getShell(), moduleWizard );
		dialog.create();
		dialog.open();
		int ret = dialog.getReturnCode();
		
		if ( ret == WizardDialog.OK ) {
			ModulesTreeNode moduleNode = new ModulesTreeNode( );
			moduleNode.setType( SectionElementType.MODULE_NAME );
			moduleNode.setIndex( modulesTreeRoot.getChildrens().size() );
			moduleNode.setValue( configManager.getModuleName(moduleNode.getIndex()) );
			moduleNode.setParent( modulesTreeRoot );

			modulesTreeViewer.refresh();
			
			syncSourceWithViews();
		}
	}

	/**
	 * Handles <code>Add ini file</code> button selection
	 * 
	 * Adds new ini file to selected module
	 */
	private void onAddIniFileToModuleButtonSelect() {
		InputDialog iniFileDialog = new InputDialog( modulesMainForm.getShell(),
				"Add ini file","Ini file path","C:\\TestFramework\\Example.ini",null);
		int ret = iniFileDialog.open();
		if ( ret == InputDialog.OK ) {
			TreeSelection selection = (TreeSelection)modulesTreeViewer.getSelection();
			ModulesTreeNode selectedItem = (ModulesTreeNode)selection.getFirstElement();
			
			if ( selectedItem != null ) {
				ModulesTreeNode parent = selectedItem;
				if ( selectedItem.getType() != SectionElementType.MODULE_NAME ) {
					parent = selectedItem.getParent();
				}

				ModulesTreeNode iniFileNode = new ModulesTreeNode();
				iniFileNode.setType(SectionElementType.INI_FILE);
				iniFileNode.setIndex(0);
				iniFileNode.setValue(iniFileDialog.getValue());

				configManager.addIniFileToModule( parent.getIndex(), iniFileDialog.getValue() );
				
				// Put ini file as first in module tree node
				java.util.List<ModulesTreeNode> subNodes = parent.getChildrens();
				parent.removeAllChildreans();
				iniFileNode.setParent( parent );
				for ( int i = 0; i < subNodes.size(); i++ ) {
					parent.addChildren(subNodes.get(i));	
				}
				
				// refresh modules tree view
				modulesTreeViewer.refresh();
				modulesTreeViewer.expandToLevel(parent, 1);
				// Refresh modules add, rem, edit,.. states
				onModulesTreeElementSelect();

				// Synchronize source code with views state
				syncSourceWithViews();
			}
		}
	}

	/**
	 * Handles <code>Add testcase file</code> button selection
	 * 
	 * Adds new testcase file to selected module
	 */
	private void onAddTestCaseFileToModuleButtonSelect() {
		InputDialog testCaseFileDialog = new InputDialog( modulesMainForm.getShell(),
				"Add testcase file","Testcase file path","C:\\TestFramework\\Example.cfg",null);
		int ret = testCaseFileDialog.open();
		if ( ret == InputDialog.OK ) {
			TreeSelection selection = (TreeSelection)modulesTreeViewer.getSelection();
			ModulesTreeNode selectedItem = (ModulesTreeNode)selection.getFirstElement();

			if ( selectedItem != null ) {
				ModulesTreeNode parent = selectedItem;
				if ( selectedItem.getType() != SectionElementType.MODULE_NAME ) {
					parent = selectedItem.getParent();
				}
				
				ModulesTreeNode testCaseFileNode = new ModulesTreeNode();
				testCaseFileNode.setType(SectionElementType.TEST_CASE_FILE);
				int index = -1;
				java.util.List<ModulesTreeNode> subNodes = parent.getChildrens();
				for ( int i = 0; i < subNodes.size(); i++ ) {
					if ( subNodes.get(i).getType() == SectionElementType.TEST_CASE_FILE ) {
						if ( index < subNodes.get(i).getIndex() ) {
							index = subNodes.get(i).getIndex();
						}
					}
				}
				index++;
				testCaseFileNode.setIndex( index );
				
				testCaseFileNode.setValue(testCaseFileDialog.getValue());
				testCaseFileNode.setParent( parent );
				
				configManager.addTestCaseFileToModule( parent.getIndex(), testCaseFileDialog.getValue() );
				
				modulesTreeViewer.refresh();
				modulesTreeViewer.expandToLevel(parent, 1);
				onModulesTreeElementSelect();

				syncSourceWithViews();
			}
		}
	}

	/**
	 * Handles <code>Edit<code> button selection.
	 * 
	 * Depends on which modules tree element is selected
	 * proper edit dialog window apperas. After that selected 
	 * element value is updated in config source and modules tree.
	 */
	private void onEditButtonSelect() {
		TreeSelection selection = (TreeSelection)modulesTreeViewer.getSelection();
		ModulesTreeNode selectedItem = (ModulesTreeNode)selection.getFirstElement();
		
		if ( selectedItem == null ) {
			return;
		}
		
		if ( selectedItem.getType() == SectionElementType.MODULE_NAME ) {
			int moduleIndex = selectedItem.getIndex();
			
			InputDialog moduleNameDialog = new InputDialog( modulesMainForm.getShell(),
					"Change module name","Module name",selectedItem.getValue(),null);
			
			if ( moduleNameDialog.open() == InputDialog.OK ) {
				selectedItem.setValue( moduleNameDialog.getValue() );
				configManager.updateModule( moduleIndex, selectedItem.getValue() );
				modulesTreeViewer.refresh();
				syncSourceWithViews();
			}
		} else if ( selectedItem.getType() == SectionElementType.INI_FILE ) {
			int moduleIndex = selectedItem.getParent().getIndex();
			
			InputDialog iniFileDialog = new InputDialog( modulesMainForm.getShell(),
					"Change ini file path","Ini file path",selectedItem.getValue(),null);
			
			if ( iniFileDialog.open() == InputDialog.OK ) {
				selectedItem.setValue( iniFileDialog.getValue() );
				configManager.updateIniFileInModule( moduleIndex, selectedItem.getValue() );
				modulesTreeViewer.refresh();
				syncSourceWithViews();
			}
		} else if ( selectedItem.getType() == SectionElementType.TEST_CASE_FILE ) {
			int moduleIndex = selectedItem.getParent().getIndex();
			int configFileIndex = selectedItem.getIndex();
			
			InputDialog testCaseFileDialog = new InputDialog( modulesMainForm.getShell(),
					"Change testcase file path","Testcase file path",selectedItem.getValue(),null);
			
			if ( testCaseFileDialog.open() == InputDialog.OK ) {
				selectedItem.setValue( testCaseFileDialog.getValue() );
				configManager.updateTestCaseFileInModule( moduleIndex, configFileIndex, 
						selectedItem.getValue() );
				modulesTreeViewer.refresh();
				syncSourceWithViews();
			}
		}
		
	}

	/**
	 * Handles <code>Remove</code> button selection.
	 * 
	 * Removes selected element from config and modules tree.
	 */
	private void onRemoveButtonSelect() {
		TreeSelection selection = (TreeSelection)modulesTreeViewer.getSelection();
		ModulesTreeNode selectedItem = (ModulesTreeNode)selection.getFirstElement();
		
		if ( selectedItem == null ) {
			return;
		}
		
		if ( selectedItem.getType() == SectionElementType.MODULE_NAME ) {
			int moduleIndex = selectedItem.getIndex();
			configManager.removeModule( moduleIndex );
			
			// Fix modules indices
			ModulesTreeNode parent = selectedItem.getParent(); 
			selectedItem.dispose();
			for( int i = 0; i < parent.getChildrens().size(); i++ ) {
				int index = parent.getChildrens().get(i).getIndex(); 
				if ( index > selectedItem.getIndex() ) {
					parent.getChildrens().get(i).setIndex( index - 1 );
				}
			}
			
			modulesTreeViewer.refresh();
			syncSourceWithViews();
		} else if ( selectedItem.getType() == SectionElementType.INI_FILE ) {
			int moduleIndex = selectedItem.getParent().getIndex();
			configManager.removeIniFileFromModule( moduleIndex );			
			
			selectedItem.dispose();
						
			modulesTreeViewer.refresh();
			syncSourceWithViews();
		} else if ( selectedItem.getType() == SectionElementType.TEST_CASE_FILE ) {
			int moduleIndex = selectedItem.getParent().getIndex();
			int configFileIndex = selectedItem.getIndex();
			configManager.removeTestCaseFileFromModule( moduleIndex, configFileIndex );						

			// Fix module testcase files indices
			ModulesTreeNode parent = selectedItem.getParent(); 
			selectedItem.dispose();
			for( int i = 0; i < parent.getChildrens().size(); i++ ) {
				int index = parent.getChildrens().get(i).getIndex(); 
				if ( index > selectedItem.getIndex() ) {
					parent.getChildrens().get(i).setIndex( index - 1 );
				}
			}

			modulesTreeViewer.refresh();
			syncSourceWithViews();
		}
	}
	
	/**
	 * Handles <code>UI testing support</code> button selection 
	 *
	 * Changes value of UITestingSupport option
	 */
	private void onUITestingSupportButtonSelect() {
		if( uiTestingSupportYesButton.getSelection() ){
			configManager.setUITestingSupport(YesNo.YES);
		} else if( uiTestingSupportNoButton.getSelection() ){
			configManager.setUITestingSupport(YesNo.NO);
		}
		syncSourceWithViews();
	}
	
	/**
	 * Handles <code>Separate processes</code> button selection 
	 *
	 * Changes value of SeparateProcesses option
	 */
	private void onSeparateProcessesButtonSelect() {
		if( separateProcessYesButton.getSelection() ){
			configManager.setSeparateProcesses(YesNo.YES);
		} else if( separateProcessNoButton.getSelection() ){
			configManager.setSeparateProcesses(YesNo.NO);
		}
		syncSourceWithViews();
	}

	/* (non-Javadoc)
	 * Method declared on ISelectionChangedListener
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if ( event.getSource() == modulesTreeViewer ) {
			onModulesTreeElementSelect();
		}
	}	

	/**
	 * Synchronizes config source with modules nad general settings pages controls value.
	 */
	private void syncSourceWithViews() {
		String oldConfig = sourceEditor.getDocumentProvider().getDocument(
				sourceEditor.getEditorInput()).get();
		String newConfig = configManager.getConfigSource();

		if ( oldConfig == null ) {
			oldConfig = "";
		}
		if ( newConfig == null ) {
			newConfig = "";
		}
		
		int diffLeftPos = 0;
		int diffRightPos = 0;
		 
		for( int i = 0; ( i < newConfig.length() ) && ( i < oldConfig.length() ); i++ ) {
			diffLeftPos = i;				
			if ( newConfig.charAt( i ) != oldConfig.charAt( i ) ) {				
				break;
			}
		}
		
		String newStr = newConfig.substring( diffLeftPos, newConfig.length() );
		
		if ( ( oldConfig.length() != 0 ) && ( newConfig.length() != 0 ) ) { 
			for( int k = 0; ( oldConfig.length() - k >= diffLeftPos ) && ( newConfig.length() - k >= diffLeftPos ); k++ ) {
				diffRightPos = k;
				if ( newConfig.charAt( newConfig.length() - 1 - k ) != oldConfig.charAt( oldConfig.length() - 1 - k ) ) {				
					break;
				}
			}
		}

		newStr = newStr.substring( 0, newStr.length() - diffRightPos );
		
		try {
			int insPos = diffLeftPos;
			int insLenght = oldConfig.length() - diffRightPos - diffLeftPos;
			sourceEditor.getDocumentProvider().getDocument(
					sourceEditor.getEditorInput()).replace( insPos, insLenght , newStr );
			configManager.parseConfig(sourceEditor.getDocumentProvider().getDocument(
					sourceEditor.getEditorInput()).get());			
			updateProblemMarkers();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}		
	}	
}
