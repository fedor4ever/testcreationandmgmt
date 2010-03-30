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

package com.nokia.testfw.codegen.ui.wizard.preview;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.CompareViewerSwitchingPane;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;

/**
 * Changes previewer of Test Case Wizard
 * 
 */
public class ComparePreviewer extends CompareViewerSwitchingPane {

	private CompareConfiguration fCompareConfiguration;

	/**
	 * Constructor
	 * 
	 * @param parent
	 *            Parenent composite
	 * @param leftEditable
	 *            Says if left window is editable
	 * @param rightEditable
	 *            Says if right window is editable
	 */
	public ComparePreviewer(Composite parent, boolean leftEditable,
			boolean rightEditable) {
		super(parent, SWT.BORDER | SWT.FLAT, true);
		fCompareConfiguration = new CompareConfiguration();
		fCompareConfiguration.setLeftEditable(leftEditable);
		fCompareConfiguration.setRightEditable(rightEditable);
	}

	/**
	 * Method returning handle to current object
	 * 
	 * @return object of ComparePreviewer class
	 */
	public Control getControl() {
		return this;
	}

	/**
	 * Called by
	 * {@link org.eclipse.compare.CompareViewerSwitchingPane#setInput(Object input)}
	 * 
	 * @see org.eclipse.compare.CompareViewerSwitchingPane#getViewer(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object)
	 */
	protected Viewer getViewer(Viewer oldViewer, Object input) {
		Viewer mergeViewer = CompareUI.findContentViewer(oldViewer,
				(ICompareInput) input, this, fCompareConfiguration);
		if (mergeViewer instanceof ContentMergeViewer) {
			((ContentMergeViewer) mergeViewer).setConfirmSave(true);
		}
		return mergeViewer;
	}

	/**
	 * Sets input for the compare preview
	 * 
	 * @param input
	 *            ChangeElement or suitable object for
	 *            CompareViewerSwitchingPane.setInput
	 */
	public void setInput(Object input) {
		try {
			if (input instanceof ChangeElement) {
				ChangeElement changeElement = (ChangeElement) input;
				fCompareConfiguration.setLeftLabel("New");
				fCompareConfiguration.setRightLabel("Original");
				super.setInput(new DiffNode(Differencer.CHANGE, null,
						changeElement.getNewElement(), changeElement
								.getOldElement()));
			} else {
				super.setInput(input);
			}
		} catch (Throwable e) {
			IStatus lStatus = new Status(IStatus.ERROR, ComparePreviewer.class
					.getName(), "Exception was thrown while comparing changes",
					e);
			CodegenUIPlugin.getDefault().getLog().log(lStatus);
		}
	}
}
