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



package com.symbian.tdep.templates.carbide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.AbstractProjectProcess;
import com.nokia.carbide.templatewizard.process.IParameter;
import com.symbian.tdep.templates.carbide.util.TemplateUtil;

/**
 * A process to rewrite some template parameters before creating Test Block
 * project from template.
 * 
 * @author Development Tools
 */
public class CheckDuplication extends AbstractProjectProcess {

	// The name of template parameter
	private static final String BASE_NAME = "baseName";

	// The name of template parameter
	private static final String BASE_NAME_UPPER = "baseNameUpper";

	// The name of template parameter
	private static final String BASE_NAME_LOWER = "baseNameLower";

	// The name of template parameter
	private static final String TARGET_NAME = "targetName";

	// The name of template parameter
	private static final String INC_DIR = "incDir";

	// The name of template parameter
	private static final String TEST_INC_DIR = "testIncDir";

	// The name of template parameter
	private static final String SOURCE_DIR = "sourceDir";

	// The name of template parameter
	private static final String TEST_SOURCE_DIR = "testSourceDir";

	// The name of template parameter
	private static final String DATA_DIR = "dataDir";

	// The name of template parameter
	private static final String TEST_DATA_DIR = "testDataDir";

	// The name of template parameter
	private static final String SCRIPT_DIR = "scriptDir";

	// The name of template parameter
	private static final String TEST_SCRIPT_DIR = "testScriptDir";

	// The name of template parameter
	private static final String GROUP_DIR = "groupDir";

	// The name of template parameter
	private static final String TEST_GROUP_DIR = "testGroupDir";

	// The name of template parameter
	private static final String ABORT = "Abort";

	// The name of template parameter
	private static final String OVERWRITE = "Overwrite";

	protected int iResult;

	//	
	// public CheckDuplication() {
	// setRunInUIThread(true);
	// }
	//
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProcess#getPlugin()
	 */
	@Override
	protected Plugin getPlugin() {
		return TefTemplatesCarbidePlugin.getDefault();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProjectProcess#init(com.nokia.carbide.template.engine.ITemplate,
	 *      java.util.List)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void init(ITemplate aTemplate, List<IParameter> aParemeters)
			throws CoreException {
		super.init(aTemplate, aParemeters);
		// Get Properties
		final Map<String, Object> lTemplateValues = aTemplate
				.getTemplateValues();

		lTemplateValues.put(BASE_NAME, getProjectName());

//		lTemplateValues.put(BASE_NAME_UPPER, ((String) lTemplateValues
//				.get(TARGET_NAME)).toUpperCase());
//
//		lTemplateValues.put(BASE_NAME_LOWER, ((String) lTemplateValues
//				.get(TARGET_NAME)).toLowerCase());

//		lTemplateValues
//				.put(INC_DIR, (String) lTemplateValues.get(TEST_INC_DIR));
//
//		lTemplateValues.put(SOURCE_DIR, (String) lTemplateValues
//				.get(TEST_SOURCE_DIR));
//
//		lTemplateValues.put(DATA_DIR, (String) lTemplateValues
//				.get(TEST_DATA_DIR));
//
//		lTemplateValues.put(SCRIPT_DIR, (String) lTemplateValues
//				.get(TEST_SCRIPT_DIR));
//
		IProject lProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
				getProjectName());
		ICarbideProjectInfo carbideprojectinfo = CarbideBuilderPlugin
				.getBuildManager().getProjectInfo(lProject);
		IPath lProjectPath = lProject.getLocation();
		IPath lBldInfPath = carbideprojectinfo.getAbsoluteBldInfPath();
		lBldInfPath = lBldInfPath.setDevice(null);
		lBldInfPath = lBldInfPath.removeFirstSegments(lBldInfPath.matchingFirstSegments(lProjectPath));
		lBldInfPath = lBldInfPath.removeLastSegments(1);
		if (lBldInfPath.segmentCount() == 0) {
			lTemplateValues.put(GROUP_DIR, ".");
		} else {
			lTemplateValues.put(GROUP_DIR, lBldInfPath.toOSString());
		}

		final ArrayList<String> iExistFileList = new ArrayList<String>();
		final Set<ProjectItem> lProjects = (Set<ProjectItem>) lTemplateValues
				.get(GetTestableItemsPage.PROJECTS);
		final String lTestSourceDir = (String) lTemplateValues
				.get(TEST_SOURCE_DIR);
		final String lTestIncDir = (String) lTemplateValues.get(TEST_INC_DIR);
		final String lTestGroupDir = (String) lTemplateValues
				.get(TEST_GROUP_DIR);
		final String lTestDataDir = (String) lTemplateValues.get(TEST_DATA_DIR);
		final String lTestScriptDir = (String) lTemplateValues
				.get(TEST_SCRIPT_DIR);
		final String lTargetName = (String) lTemplateValues.get(TARGET_NAME);

		final IPath lTestIncludePath = lProjectPath.append(lTestIncDir);
		final IPath lTestSourcePath = lProjectPath.append(lTestSourceDir);
		final IPath lTestGroupPath = lProjectPath.append(lTestGroupDir);
		final IPath lTestDataPath = lProjectPath.append(lTestDataDir);
		final IPath lTestScriptPath = lProjectPath.append(lTestScriptDir);

		// driver
		File lDriverFile = new File(lTestGroupPath.toOSString(), lTargetName
				+ ".driver");
		if (lDriverFile.exists()) {
			iExistFileList.add(lDriverFile.getAbsolutePath());
		}
		// iby
		File lIbyFile = new File(lTestGroupPath.toOSString(), lTargetName
				+ ".iby");
		if (lIbyFile.exists()) {
			iExistFileList.add(lIbyFile.getAbsolutePath());
		}
		// mmp
		File lMmpFile = new File(lTestGroupPath.toOSString(), lTargetName
				+ ".mmp");
		if (lMmpFile.exists()) {
			iExistFileList.add(lMmpFile.getAbsolutePath());
		}
		// server
		File lServerHFile = new File(lTestIncludePath.toOSString(), lTargetName
				+ "BlockServer.h");
		if (lServerHFile.exists()) {
			iExistFileList.add(lServerHFile.getAbsolutePath());
		}
		File lServerCppFile = new File(lTestSourcePath.toOSString(),
				lTargetName + "BlockServer.cpp");
		if (lServerCppFile.exists()) {
			iExistFileList.add(lServerCppFile.getAbsolutePath());
		}
		// controller
		File lControllerHFile = new File(lTestIncludePath.toOSString(),
				lTargetName + "BlockController.h");
		if (lControllerHFile.exists()) {
			iExistFileList.add(lControllerHFile.getAbsolutePath());
		}
		File lControllerCppFile = new File(lTestSourcePath.toOSString(),
				lTargetName + "BlockController.cpp");
		if (lControllerCppFile.exists()) {
			iExistFileList.add(lControllerCppFile.getAbsolutePath());
		}
		// script
		File lScriptFile = new File(lTestScriptPath.toOSString(), lTargetName
				+ ".script");
		if (lScriptFile.exists()) {
			iExistFileList.add(lScriptFile.getAbsolutePath());
		}
		// ini
		File lIniFile = new File(lTestDataPath.toOSString(), lTargetName
				+ ".ini");
		if (lIniFile.exists()) {
			iExistFileList.add(lIniFile.getAbsolutePath());
		}
		// .cpp and .h
		for (ProjectItem lProjectItem : lProjects) {
			if (lProjectItem.isSelected()) {
				for (ClassItem lClassItem : lProjectItem.getChildren()) {
					if (lClassItem.isSelected()) {
						String lTestClassWrapper = TemplateUtil.getClassWrapperName(lClassItem.getName());
						File lClassHeaderCode = new File(lTestIncludePath
								.toOSString(), lTestClassWrapper + ".h");
						if (lClassHeaderCode.exists()) {
							iExistFileList.add(lClassHeaderCode
									.getAbsolutePath());
						}
						File lClassCppCode = new File(lTestSourcePath
								.toOSString(), lTestClassWrapper + ".cpp");
						if (lClassCppCode.exists()) {
							iExistFileList.add(lClassCppCode.getAbsolutePath());
						}
					}

				}
			}
		}

		if (iExistFileList.size() > 0) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					OverwriteDialog lDialog = new OverwriteDialog(Display
							.getDefault().getActiveShell(), iExistFileList
							.toArray(new String[0]));
					iResult = lDialog.open();
				}
			});
		}
		if (iResult < 0) {
			lTemplateValues.put(ABORT, "true");
		} else if (iResult > 0) {
			lTemplateValues.put(OVERWRITE, "true");
		}
	}

	class OverwriteDialog extends Dialog {

		private final static int YES = 1;
		private final static int NO = 0;
		private final static int CANCEL = -1;
		private int result = CANCEL;
		private String[] iFiles;

		public OverwriteDialog(Shell parent, String[] files) {
			super(parent);
			iFiles = files;
		}

		private void init(final Shell shell) {
			// Dialog title and layout
			shell.setText("Confirm overwrite");
			shell.setLayout(new GridLayout(6, true));

			// Widgets of Wrapped Class
			Label label = new Label(shell, SWT.None);
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 6;
			label.setLayoutData(data);
			label.setText("Follow files will be overwritten, are you sure?");

			org.eclipse.swt.widgets.List lFilesList = new org.eclipse.swt.widgets.List(
					shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			data = new GridData(GridData.FILL_BOTH);
			data.horizontalSpan = 6;
			data.verticalSpan = 5;
			lFilesList.setLayoutData(data);
			lFilesList.setItems(iFiles);

			// Separator
			label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
			data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 6;
			label.setLayoutData(data);

			// Place holder
			label = new Label(shell, SWT.None);
			data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 3;
			label.setLayoutData(data);

			// Yes Button
			Button lBtnYES = new Button(shell, SWT.PUSH);
			lBtnYES.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lBtnYES.setText(Messages.getString("Dialog.YES"));
			lBtnYES.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					result = YES;
					((Button) event.widget).getShell().dispose();
				}
			});

			// NO Button
			Button lBtnNO = new Button(shell, SWT.PUSH);
			lBtnNO.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lBtnNO.setText(Messages.getString("Dialog.NO"));
			lBtnNO.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					result = NO;
					((Button) event.widget).getShell().dispose();
				}
			});

			// Cancle Button
			Button lBtnCancel = new Button(shell, SWT.PUSH);
			lBtnCancel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lBtnCancel.setText(Messages.getString("Dialog.Cancel"));
			lBtnCancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					result = CANCEL;
					((Button) event.widget).getShell().dispose();
				}
			});
		}

		// Open dialog
		public int open() {
			Shell parent = getParent();
			Shell shell = new Shell(parent, SWT.DIALOG_TRIM
					| SWT.APPLICATION_MODAL);

			init(shell);
			shell.pack();

			shell.setSize(600, 200);
			// Reside dialog location to parent shell center.
			Rectangle lParentBounds = parent.getBounds();
			Point lDialogSize = shell.getSize();
			shell.setLocation(lParentBounds.x
					+ (lParentBounds.width - lDialogSize.x) / 2,
					lParentBounds.y + (lParentBounds.height - lDialogSize.y)
							/ 2);

			shell.open();
			Display display = parent.getDisplay();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
			return result;
		}

	}
}
