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


package com.nokia.s60tools.stif.scripteditor.editors;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.editors.text.TextEditor;

import com.nokia.s60tools.stif.scripteditor.utils.OutlinePageContentProvider;
import com.nokia.s60tools.stif.scripteditor.utils.OutlineViewLabelProvider;
import com.nokia.s60tools.stif.scripteditor.utils.TestCase;

/**
 * Creates Outline page for Script Editor.
 * Outline page displays tests in tree view.
 * 
 */
public class ScriptEditorOutlinePage extends ContentOutlinePage {
	
	/**
	 * Creates Script Editor Outline view
	 * 
	 */
	public ScriptEditorOutlinePage(TextEditor scriptEditor) {
		editor = scriptEditor;

	}

	/*
	 * (non-JavaDoc) Method declared in
	 * @see org.eclipse.ui.views.contentoutline.ContentOutlinePage#init()
	 */
	public void init(IPageSite arg0) {
		super.init(arg0);
	}
	
	/*
	 * (non-JavaDoc) Method declared in
	 * @see org.eclipse.ui.views.contentoutline.ContentOutlinePage#createControl()
	 */
	public void createControl(Composite arg0) {
		super.createControl(arg0);
		treeViewer = getTreeViewer();
		treeViewer.setContentProvider(new OutlinePageContentProvider());
		treeViewer.setLabelProvider(new OutlineViewLabelProvider());
		treeViewer.addSelectionChangedListener((ISelectionChangedListener) editor);

	}
	
	/**
	 * Creates Script Editor Outline view
	 * 
	 */
	public void updateViewWithTests(TestCase[] testCases) {
		treeViewer.setInput(testCases);

	}

	private TreeViewer treeViewer;

	private TextEditor editor;
}
