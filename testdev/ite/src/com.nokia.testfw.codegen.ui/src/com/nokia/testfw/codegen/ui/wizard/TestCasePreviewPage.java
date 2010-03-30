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
package com.nokia.testfw.codegen.ui.wizard;

import java.util.Map;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.PageBook;

import com.nokia.testfw.codegen.ChangeFileContent;
import com.nokia.testfw.codegen.ui.util.PathNode;
import com.nokia.testfw.codegen.ui.util.PathNodeConverter;
import com.nokia.testfw.codegen.ui.util.PathNodeTreeContentLabelProvoder;
import com.nokia.testfw.codegen.ui.util.PathNodeTreeViewerComparator;
import com.nokia.testfw.codegen.ui.wizard.preview.ChangeElement;
import com.nokia.testfw.codegen.ui.wizard.preview.ComparePreviewer;

/**
 * Test Case Wizard page for reviewing changes
 * 
 */
public class TestCasePreviewPage extends WizardPage implements
		IPageChangedListener, IPageChangingListener {

	private PageBook previewContainer;
	private ComparePreviewer comparePreview;
	private TreeViewer iTreeViewer;

	private Map<String, ChangeFileContent> iResultMap;

	Composite container;

	/**
	 * @param pageName
	 */
	public TestCasePreviewPage() {
		super("SkeletonPreviewPage");
		setTitle("Preview changes");
		setDescription("Click finish to generate test case(s)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, true));

		// table to display all files that are going to be changed
		Label label1 = new Label(container, SWT.SINGLE);
		label1.setText("Files to be modified");

		iTreeViewer = new TreeViewer(container, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		iTreeViewer.getTree().setLayoutData(
				new GridData(SWT.FILL, SWT.TOP, true, false));
		PathNodeTreeContentLabelProvoder provider = new PathNodeTreeContentLabelProvoder();
		iTreeViewer.setLabelProvider(provider);
		iTreeViewer.setContentProvider(provider);
		iTreeViewer.setComparator(new PathNodeTreeViewerComparator());
		iTreeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						ITreeSelection sel = (ITreeSelection) event
								.getSelection();
						if (sel != null) {
							PathNode node = (PathNode) sel.getFirstElement();
							if (node != null) {
								setSelectElement((ChangeElement) node.getData());
							}
						}
					}
				});

		// UI elements to show the CompareViewerSwitchingPane
		SashForm sashForm = new SashForm(container, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		sashForm.setBackground(new Color(getShell().getDisplay(), 0, 0, 0));
		previewContainer = new PageBook(sashForm, SWT.NONE);
		comparePreview = new ComparePreviewer(previewContainer, false, false);

		setSelectElement(null);
		setControl(container);

		/**
		 * We want to be informed about pageChanged event in order to generate
		 * content.
		 */
		((WizardDialog) getContainer()).addPageChangedListener(this);
		((WizardDialog) getContainer()).addPageChangingListener(this);
	}

	private void update() {
		if (iResultMap != null) {
			PathNode root = new PathNode("");
			PathNode targetProject = new PathNode(
					((AbstractTemplateWizard) getWizard()).getProjectName());
			root.addChild(targetProject);
			for (String path : iResultMap.keySet()) {
				ChangeFileContent lChangeContent = iResultMap.get(path);
				if (lChangeContent.getNewContent().equals(
						lChangeContent.getOldContent())) {
					continue;
				}
				PathNode node = PathNodeConverter.pathToNode(targetProject,
						path);
				node.setData(new ChangeElement(lChangeContent));
			}
			iTreeViewer.setInput(root);
			iTreeViewer.expandAll();
		}
	}

	/**
	 * Sets input to compare preview (called from wizard when page is set to
	 * visible)
	 * 
	 * @param changeElement
	 *            to set to the preview
	 **/
	private void setSelectElement(ChangeElement changeElement) {
		comparePreview.setInput(changeElement);
		previewContainer.showPage(comparePreview.getControl());
	}

	public void pageChanged(PageChangedEvent event) {
		if (event.getSelectedPage() instanceof TestCasePreviewPage) {
			iResultMap = ((IPreviewResult) getWizard()).getPreviewResult();
			((IPreviewResult) getWizard()).setPreviewResult(iResultMap);
			update();
		}
	}

	public void handlePageChanging(PageChangingEvent event) {
		if (event.getCurrentPage() instanceof TestCasePreviewPage) {
			((IPreviewResult) getWizard()).setPreviewResult(null);
		}
	}
}
