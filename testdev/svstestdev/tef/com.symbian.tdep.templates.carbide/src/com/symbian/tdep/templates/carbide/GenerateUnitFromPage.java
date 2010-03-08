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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.parser.util.ArrayUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.nokia.carbide.internal.api.templatewizard.ui.ChooseTemplatePage;
import com.nokia.carbide.internal.api.templatewizard.ui.IWizardDataPage;
import com.nokia.carbide.cdt.builder.CarbideBuilderPlugin;
import com.nokia.carbide.cdt.builder.project.ICarbideProjectInfo;
import com.nokia.carbide.cpp.internal.project.ui.sharedui.ChooseProjectPage;

public class GenerateUnitFromPage extends WizardPage implements IWizardDataPage {

	// Constants
	private static final String NAME = "Source of Classes under Test";
	public static final String TESTPROJECTS = "TestProjects";
	public static final String TARGETNAME = "targetName";

	private Button iHeaderButton;
	private Button iProjectButton;
	private Group iGroup;
	private List iHFList;
	private Button iFileAddButton;
	private Button iFileRemoveButton;
	private ChooseProjectPage iChooseProjectPage;
	private IProject iHostProject;
	private Text iTextTarget;
	private CLabel iLabelError;
	private boolean iHasError = false;
	private Set<ProjectItem> iProjects = new HashSet<ProjectItem>();
	private Button iFileEditButton;
	private boolean isChanged = true;

	public GenerateUnitFromPage() {
		super(NAME);
		setTitle(NAME);
		setDescription("Specify the source of classes to test.");
	}

	public Map<String, Object> getPageValues() {
		Map<String, Object> lPageValues = new HashMap<String, Object>();
		lPageValues.put(TESTPROJECTS, iProjects);
		lPageValues.put(TARGETNAME, iTextTarget.getText().trim());
		return lPageValues;
	}

	public void createControl(Composite aComposite) {
		initializeDialogUnits(aComposite);
		Composite lComposite = new Composite(aComposite, SWT.NONE);
		lComposite.setLayout(new GridLayout(1, true));
		lComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		setControl(lComposite);

		// Find ChooseProjectPage
		IWizardPage lpage = this;
		while ((lpage = lpage.getPreviousPage()) != null) {
			if (lpage instanceof ChooseProjectPage) {
				iChooseProjectPage = (ChooseProjectPage) lpage;
				break;
			}
		}

		// ProjectButton
		iProjectButton = new Button(lComposite, SWT.RADIO);
		iProjectButton.setText("From Hosting Project");
		GridData lGridData = new GridData(GridData.FILL_HORIZONTAL);
		iProjectButton.setLayoutData(lGridData);
		iProjectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (!iChooseProjectPage.getProject().getName().equals(
						iHostProject.getName())) {
					iHostProject = iChooseProjectPage.getProject();
				}
				isChanged = true;
				setPageComplete(isPageComplete());
			}
		});

		// HeaderButton
		iHeaderButton = new Button(lComposite, SWT.RADIO);
		iHeaderButton.setText("From Header Files in \\epoc32\\include");
		lGridData = new GridData(GridData.FILL_HORIZONTAL);
		iHeaderButton.setLayoutData(lGridData);
		iHeaderButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (iHeaderButton.getSelection()) {
					iGroup.setEnabled(true);
					iHFList.setEnabled(true);
					iFileAddButton.setEnabled(true);
					if (iHFList.getSelectionCount() > 0) {
						iFileEditButton.setEnabled(true);
						iFileRemoveButton.setEnabled(true);
					}
					isChanged = true;
				} else {
					iGroup.setEnabled(false);
					iHFList.setEnabled(false);
					iFileAddButton.setEnabled(false);
					iFileEditButton.setEnabled(false);
					iFileRemoveButton.setEnabled(false);
				}
			}
		});

		iGroup = new Group(lComposite, SWT.SHADOW_ETCHED_IN);
		iGroup.setLayout(new GridLayout(5, true));
		iGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iGroup.setEnabled(false);

		// Header Files List
		iHFList = new List(iGroup, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		lGridData = new GridData(GridData.FILL_HORIZONTAL);
		lGridData.horizontalSpan = 4;
		lGridData.verticalSpan = 8;
		iHFList.setLayoutData(lGridData);
		iHFList.setEnabled(false);
		iHFList.setData(new ArrayList<String>());
		iHFList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (iHFList.getSelectionCount() > 0) {
					iFileEditButton.setEnabled(true);
					iFileRemoveButton.setEnabled(true);
				}
			}
		});
		iHFList.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent event) {
				iFileEditButton.notifyListeners(SWT.Selection, null);
			}
		});

		// File Add Button
		iFileAddButton = new Button(iGroup, SWT.PUSH);
		iFileAddButton.setText("Add");
		iFileAddButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iFileAddButton.setEnabled(false);
		iFileAddButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (iHeaderButton.getSelection()) {
					SetHeaderDialog dialog = new SetHeaderDialog(iFileAddButton
							.getShell(), null, null);
					if (dialog.open()) {
						isChanged = true;
						String lHeaderName = dialog.getHeader().trim();
						String lLibName = dialog.getLib().trim();

						String[] items = iHFList.getItems();
						boolean lExist = false;
						for (int i = 0; i < items.length; i++) {
							if (items[i].equals(lHeaderName)) {
								lExist = true;
								((ArrayList<String>) iHFList.getData()).set(i,
										lLibName);
								iHFList.setItem(i, lHeaderName);
								break;
							}
						}
						if (lExist == false) {
							iHFList.add(lHeaderName);
							((ArrayList<String>) iHFList.getData())
									.add(lLibName);
						}
						setPageComplete(isPageComplete());
					}
				}
			}
		});

		// File Edit Button
		iFileEditButton = new Button(iGroup, SWT.PUSH);
		iFileEditButton.setText("Edit");
		iFileEditButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iFileEditButton.setEnabled(false);
		iFileEditButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (iHeaderButton.getSelection()
						&& iHFList.getSelectionCount() > 0) {
					int[] index = iHFList.getSelectionIndices();
					String lHeaderName = iHFList.getItem(index[0]);
					String lLibName = ((ArrayList<String>) iHFList.getData())
							.get(index[0]);

					SetHeaderDialog dialog = new SetHeaderDialog(iFileAddButton
							.getShell(), lHeaderName, lLibName);
					if (dialog.open()) {
						isChanged = true;
						lHeaderName = dialog.getHeader().trim();
						lLibName = dialog.getLib().trim();

						iHFList.setItem(index[0], lHeaderName);
						((ArrayList<String>) iHFList.getData()).set(index[0],
								lLibName);
					}
				}
			}
		});

		// File Remove Button
		iFileRemoveButton = new Button(iGroup, SWT.PUSH);
		iFileRemoveButton.setText("Remove");
		iFileRemoveButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iFileRemoveButton.setEnabled(false);
		iFileRemoveButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (iHeaderButton.getSelection()
						&& iHFList.getSelectionCount() > 0) {
					int[] index = iHFList.getSelectionIndices();
					for (int i : index) {
						((ArrayList<String>) iHFList.getData()).remove(i);
					}
					iHFList.remove(index);
					iFileEditButton.setEnabled(false);
					iFileRemoveButton.setEnabled(false);
					isChanged = true;
					setPageComplete(isPageComplete());
				}
			}
		});

		// SEPARATOR
		Label lLabelSeparator = new Label(lComposite, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		lLabelSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lLabelName = new Label(lComposite, SWT.NONE);
		lLabelName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lLabelName.setText("Target Name:");

		// Target file name
		iTextTarget = new Text(lComposite, SWT.BORDER);
		iTextTarget.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iTextTarget.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				String mmpFile = iTextTarget.getText().trim() + ".mmp";
				try {
					FindFileVisitor visitor = new FindFileVisitor(mmpFile);
					iHostProject.accept(visitor);
					if (visitor.getResult().size() > 0) {
						iHasError = true;
						iLabelError.setImage(PlatformUI.getWorkbench()
								.getSharedImages().getImage(
										ISharedImages.IMG_OBJS_ERROR_TSK));
						iLabelError.setText("Duplicate target name.");
					} else {
						iHasError = false;
						iLabelError.setImage(null);
						iLabelError.setText(null);
					}
				} catch (CoreException ignore) {
				}
				setPageComplete(isPageComplete());
			}
		});

		iLabelError = new CLabel(lComposite, SWT.NONE);
		iLabelError.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// init
		iProjectButton.setSelection(true);
		iHostProject = iChooseProjectPage.getProject();
		iTextTarget.setText("Test" + iHostProject.getName());

		((WizardDialog) getContainer())
				.addPageChangingListener(new IPageChangingListener() {
					public void handlePageChanging(PageChangingEvent event) {
						IWizardPage lCurPage = (IWizardPage) event
								.getCurrentPage();
						IWizardPage lTarPage = (IWizardPage) event
								.getTargetPage();
						if (lTarPage instanceof GenerateUnitFromPage) {
							if (lCurPage instanceof ChooseTemplatePage) {
								if (!iHostProject.getName().equals(
										iChooseProjectPage.getProject()
												.getName())) {
									isChanged = true;
									iHostProject = iChooseProjectPage
											.getProject();
									iTextTarget.setText("Test"
											+ iHostProject.getName());
								}
							} else {
								isChanged = false;
							}
						}
						if (lCurPage instanceof GenerateUnitFromPage) {
							if (isChanged) {
								iProjects.clear();
								if (iProjectButton.getSelection()) {
									iProjects
											.add(new ProjectItem(iHostProject));
								} else {
									String[] files = iHFList.getItems();
									ArrayList<String> lLibList = (ArrayList<String>) iHFList
											.getData();

									for (int i = 0; i < files.length; i++) {
										iProjects.add(getProjectItem(files[i],
												lLibList.get(i), iHostProject));
									}
								}
							}
						}
					}
				});

	}

	// Generate ProjectItem
	private ProjectItem getProjectItem(String path, String lib,
			IProject lProject) {
		Path filePath = new Path(path);
		ProjectItem lProjectItem = new ProjectItem(iHostProject, filePath);
		lProjectItem.setLib(lib);
		return lProjectItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (iHasError) {
			return false;
		}

		if (iProjectButton.getSelection()) {
			return true;
		} else {
			if (iHFList.getItemCount() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	// Visitor for AST
	private class MyVisitor extends ASTVisitor {
		private IASTName[] names = new IASTName[2];
		private int namesPos = -1;
		{
			shouldVisitNames = true;
		}

		public int visit(IASTName name) {
			if (name != null) {
				namesPos++;
				names = (IASTName[]) ArrayUtil.append(IASTName.class, names,
						name);
			}
			return PROCESS_CONTINUE;
		}

		public IASTName[] getNames() {
			names = (IASTName[]) ArrayUtil.removeNullsAfter(IASTName.class,
					names, namesPos);
			return names;
		}
	}

	// Set Header File and Lib
	class SetHeaderDialog extends Dialog {

		private boolean result;
		private Button iBtnOK;
		private Text iHeaderText;
		private String iHeader;
		private String iLib;
		private Text iLibText;

		public SetHeaderDialog(Shell parent, String header, String lib) {
			super(parent, 0);
			if (header == null) {
				iHeader = "";
			} else {
				iHeader = header;
			}
			if (lib == null) {
				iLib = "";
			} else {
				iLib = lib;
			}
		}

		private void init(final Shell shell) {
			shell.setText("Set Header File Information");
			shell.setLayout(new GridLayout(5, true));

			// Header
			Label lHeaderLabel = new Label(shell, SWT.None);
			lHeaderLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lHeaderLabel.setText("Header File:");

			iHeaderText = new Text(shell, SWT.BORDER);
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 3;
			iHeaderText.setLayoutData(data);
			iHeaderText.setText(iHeader);
			iHeaderText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					checkComplete();
				}
			});

			Button lHeaderButton = new Button(shell, SWT.PUSH);
			lHeaderButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lHeaderButton.setText("&Browse...");
			lHeaderButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ICarbideProjectInfo icarbideprojectinfo = CarbideBuilderPlugin
							.getBuildManager().getProjectInfo(iHostProject);

					String iSDKinc = icarbideprojectinfo
							.getDefaultConfiguration().getSDK()
							.getIncludePath().toOSString();

					FileDialog dialog = new FileDialog(iFileAddButton
							.getShell(), SWT.OPEN);
					dialog.setFilterExtensions(new String[] { "*.h" });
					dialog.setFilterPath(iSDKinc);
					String filename = dialog.open();
					if (filename != null) {
						iHeaderText.setText(filename);
					}
				}
			});

			// Lib
			Label lLibLabel = new Label(shell, SWT.None);
			lLibLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lLibLabel.setText("Lib File:");

			iLibText = new Text(shell, SWT.BORDER);
			data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 3;
			iLibText.setLayoutData(data);
			iLibText.setText(iLib);
			iLibText.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					checkComplete();
				}
			});

			Button lLibButton = new Button(shell, SWT.PUSH);
			lLibButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lLibButton.setText("&Browse...");
			lLibButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ICarbideProjectInfo icarbideprojectinfo = CarbideBuilderPlugin
							.getBuildManager().getProjectInfo(iHostProject);

					String iSDKrelease = icarbideprojectinfo
							.getDefaultConfiguration().getSDK()
							.getReleaseRoot().toOSString();

					FileDialog dialog = new FileDialog(shell, SWT.OPEN);
					dialog.setFilterExtensions(new String[] { "*.lib" });
					dialog.setFilterPath(iSDKrelease);
					String filename = dialog.open();
					if (filename != null) {
						iLibText.setText(new Path(filename).lastSegment());
					}
				}
			});

			// Space holder
			Label label = new Label(shell, SWT.None);
			data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 3;
			label.setLayoutData(data);

			// OK
			iBtnOK = new Button(shell, SWT.PUSH);
			iBtnOK.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			iBtnOK.setText(Messages.getString("Dialog.OK"));
			iBtnOK.setEnabled(false);
			iBtnOK.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					if (validate()) {
						iHeader = iHeaderText.getText();
						iLib = iLibText.getText();
						result = true;
						((Button) event.widget).getShell().dispose();
					}
				}
			});

			// Cancel
			Button lBtnCancel = new Button(shell, SWT.PUSH);
			lBtnCancel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			lBtnCancel.setText(Messages.getString("Dialog.Cancel"));
			lBtnCancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					result = false;
					((Button) event.widget).getShell().dispose();
				}
			});

			checkComplete();
		}

		public boolean open() {
			Shell parent = getParent();
			Shell shell = new Shell(parent, SWT.DIALOG_TRIM
					| SWT.APPLICATION_MODAL);

			init(shell);
			shell.pack();
			shell.setSize(400, 120);

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

		private boolean validate() {
			if (!new File(iHeaderText.getText()).exists()) {
				MessageDialog.openError(getShell(), "Error",
						"Specified header file \"" + iHeaderText.getText()
								+ "\" doesn't exist.");
				return false;
			}
			if (iLibText.getText() == "") {
				MessageDialog.openError(getShell(), "Error",
						"Must specify lib file.");
				return false;
			}
			return true;
		}

		// Check whether complete
		private void checkComplete() {
			boolean lHasBlank = false;
			if ("".equals(iHeaderText.getText().trim())) {
				lHasBlank = true;
			}
			if ("".equals(iLibText.getText().trim())) {
				lHasBlank = true;
			}

			if (lHasBlank) {
				iBtnOK.setEnabled(false);
			} else {
				iBtnOK.setEnabled(true);
			}
		}

		public String getHeader() {
			return iHeader;
		}

		public String getLib() {
			return iLib;
		}
	}

	public boolean isChanged() {
		return isChanged;
	}
}
