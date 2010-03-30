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

package com.nokia.testfw.stf.scripteditor.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModelExtension;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.nokia.testfw.stf.scripteditor.utils.ScriptParser;
import com.nokia.testfw.stf.scripteditor.utils.Section;
import com.nokia.testfw.stf.scripteditor.utils.SectionFinder;
import com.nokia.testfw.stf.scripteditor.utils.TestCase;

/**
 * STIF script file editor.
 * 
 */

public class ScriptEditor extends TextEditor implements
		ISelectionChangedListener, SelectionListener {

	private ScriptEditorOutlinePage outlinePage;

	private ProjectionAnnotationModel annotationModel;

	private ArrayList<ProjectionAnnotation> currentAnnotations = new ArrayList<ProjectionAnnotation>();

	private ScriptEditorConfiguration configuration;

	private ScriptParser parser;

	public ScriptEditor() {
		configuration = new ScriptEditorConfiguration();
		configuration.changeConfigurationMode(null);
		setSourceViewerConfiguration(configuration);
		parser = new ScriptParser();
	}

	/**
	 * Saves the editor document
	 */
	public void doSave(IProgressMonitor monitor) {
		refreshOutlineFoldingAndParse();
		super.doSave(monitor);
	}

	/**
	 * Checks editor content syntax and updates folding.
	 */
	public void refreshOutlineFoldingAndParse() {
		if (outlinePage != null) {
			TestCase[] testCases = (TestCase[]) (SectionFinder.getSections(this
					.getDocumentProvider().getDocument(getEditorInput())));
			outlinePage.updateViewWithTests(testCases);
			updateFoldingStructure(testCases);
		}
		checkScript();
	}

	/**
	 * Checks editor content syntax.
	 */
	private void checkScript() {
		String scriptContent = getDocumentProvider().getDocument(
				getEditorInput()).get();
		parser.checkScripterScript(scriptContent, getEditorInput());
		configuration.changeConfigurationMode(parser.subSectionContent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Method declared on SelectionListener
	 */
	public void widgetSelected(SelectionEvent selectionEv) {
		String scriptContent = getDocumentProvider().getDocument(
				getEditorInput()).get();
		try {
	
				ScriptEditorConfiguration conf = (ScriptEditorConfiguration) getSourceViewerConfiguration();
				conf.changeConfigurationMode(null);
				parser.checkScripterScript(scriptContent, getEditorInput());
	
				this.getSourceViewer().invalidateTextPresentation();
				
		} catch(Exception ex) {
			MessageBox message = new MessageBox(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell());
			message.setText("Problems while saving editor mode");
			message.setMessage(ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc) Method declared on SelectionListener
	 */
	public void widgetDefaultSelected(SelectionEvent arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getAdapter()
	 */
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (outlinePage == null) {
				outlinePage = new ScriptEditorOutlinePage(this);
			}
			return outlinePage;
		}
		return super.getAdapter(required);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(SelectionChangedEvent sce) {
		ITreeSelection selection = (ITreeSelection) sce.getSelection();
		if (selection.isEmpty())
			return;

		TreePath[] selections = selection.getPaths();
		TestCase testCase = (TestCase) selections[0].getFirstSegment();

		IDocument doc = getDocumentProvider().getDocument(getEditorInput());

		String contentOfDoc = doc.get();
		String name = testCase.getName();
		name = name.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		String patternString = "\\s+title\\s+(" + name + ")\\s*$";

		Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);
		Matcher regExMatcher = pattern.matcher(contentOfDoc);
		ArrayList<Integer> indexesList = new ArrayList<Integer>();
		while (regExMatcher.find()) {
			indexesList.add(regExMatcher.start(1));
			indexesList.add(regExMatcher.end(1));
		}

		int oneFromCasesWithSameName = 0;
		if (indexesList.size() > 2) {
			TreeViewer tv = (TreeViewer) sce.getSource();
			Tree tree = tv.getTree();
			TreeItem[] treeItems = tree.getItems();

			for (int i = 0; i < treeItems.length; i++) {
				if (treeItems[i].getText().equals(
						tree.getSelection()[0].getText())) {
					if (treeItems[i] == tree.getSelection()[0]) {
						break;
					}
					oneFromCasesWithSameName++;
				}
			}
		}
		if (indexesList.size() > 0) {
			selectAndReveal(indexesList.get(oneFromCasesWithSameName * 2),
					indexesList.get(oneFromCasesWithSameName * 2 + 1)
							- indexesList.get(oneFromCasesWithSameName * 2));
		}
	}

	/**
	 * Updates folding structure of the current file. Invalid annotations are
	 * removed and new ones are added
	 */
	public void updateFoldingStructure(Section[] sections) {
		HashMap<ProjectionAnnotation, Position> newAnnotations = new HashMap<ProjectionAnnotation, Position>();
		ArrayList<ProjectionAnnotation> annotationsToRemove = new ArrayList<ProjectionAnnotation>();

		for (int i = 0; i < currentAnnotations.size(); i++) {
			boolean annotationExists = false;
			for (int j = 0; j < sections.length; j++) {
				if (currentAnnotations.get(i).getText().equals(
						sections[j].getName())) {
					annotationExists = true;
					break;
				}
			}
			if (!annotationExists) {
				annotationsToRemove.add(currentAnnotations.get(i));
				currentAnnotations.remove(i);
			}
		}

		for (int i = 0; i < sections.length; i++) {
			if (sections[i].getIsNew()) {
				ProjectionAnnotation annotation = new ProjectionAnnotation();
				annotation.setText(sections[i].getName());
				int length = sections[i].getEndOffset()
						- sections[i].getStartOffset();
				newAnnotations.put(annotation, new Position(sections[i]
						.getStartOffset(), length));
				currentAnnotations.add(annotation);
			}
		}

		IAnnotationModelExtension modelExtension = (IAnnotationModelExtension) annotationModel;
		modelExtension.replaceAnnotations(annotationsToRemove
				.toArray(new ProjectionAnnotation[0]), newAnnotations);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Overriden
	 */
	public void createPartControl(Composite arg0) {
		super.createPartControl(arg0);

		ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
		ProjectionSupport support = new ProjectionSupport(viewer,
				getAnnotationAccess(), getSharedColors());
		support.install();

		viewer.doOperation(ProjectionViewer.TOGGLE);

		annotationModel = viewer.getProjectionAnnotationModel();

		Control control = (Control) getAdapter(org.eclipse.swt.widgets.Control.class);

		control.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				refreshOutlineFoldingAndParse(); // method responsible for
				// refreshing and parsing
				// tests invoked after any
				// key event.
			}

			public void keyReleased(KeyEvent e) {
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * Overriden
	 */
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		parent.setLayout(layout);

		GridData scriptEditorGridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);

		ProjectionViewer sourceViewer = new ProjectionViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);
		getSourceViewerDecorationSupport(sourceViewer);

		sourceViewer.getTextWidget().getParent().setLayoutData(
				scriptEditorGridData);

		return sourceViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("STIFScriptsEditor");
		Action action = new ContentAssistAction(resourceBundle,
				"ContentAssistProposal.", this);
		String id = ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		action.setActionDefinitionId(id);
		setAction("ContentAssistProposal", action);
		markAsStateDependentAction("ContentAssistProposal", true);
		checkScript();

		// action = new TextOperationAction(resourceBundle, "Comment.", this,
		// ITextOperationTarget.PREFIX);
		//
		// action.setActionDefinitionId("org.eclipse.cdt.ui.edit.text.c.add.block.comment");
		//
		// setAction("Comment", action);
		//		
		// markAsStateDependentAction("Comment", true);
		//		
		//
		// action = new TextOperationAction(resourceBundle,
		// "Uncomment.", this,
		// ITextOperationTarget.STRIP_PREFIX);
		//
		// action.setActionDefinitionId("org.eclipse.cdt.ui.edit.text.c.add.block.uncomment");
		// setAction("Uncomment", action);
		// markAsStateDependentAction("Uncomment", true);
		//
		// action = new ToggleCommentAction(resourceBundle, "ToggleComment.",
		// this);
		// action.setActionDefinitionId("org.eclipse.cdt.ui.edit.text.c.toggle.comment");
		// setAction("ToggleComment", action);
		// markAsStateDependentAction("ToggleComment", true);
		//
		// ISourceViewer sourceViewer = getSourceViewer();
		// SourceViewerConfiguration configuration =
		// getSourceViewerConfiguration();
		// ((ToggleCommentAction) action).configure(sourceViewer,
		// configuration);

	}

	/**
	 * Gets resource associated with editor input
	 * 
	 * @return resource
	 */
	protected IResource getResource() {
		IEditorInput input = getEditorInput();
		if (input == null) {
			return null;
		}
		IResource resource = (IResource) input.getAdapter(IFile.class);
		if (resource == null) {
			resource = (IResource) input.getAdapter(IResource.class);
			if (resource == null) {
				resource = ResourcesPlugin.getWorkspace().getRoot();

			}
		}
		refreshOutlineFoldingAndParse();
		return resource;
	}

	public ScriptEditorConfiguration getConfiguration() {
		return configuration;
	}

	public void setInitializationData(IConfigurationElement cfig,
			String propertyName, Object data) {
		super.setInitializationData(cfig, propertyName, data);
	}

	/**
	 * override the method to always display line number
	 */
	protected boolean isLineNumberRulerVisible() {
		return true;
	}

}
