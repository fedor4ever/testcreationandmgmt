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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.nokia.s60tools.stif.scripteditor.utils.ScriptParser;
import com.nokia.s60tools.stif.scripteditor.utils.Section;
import com.nokia.s60tools.stif.scripteditor.utils.SectionFinder;
import com.nokia.s60tools.stif.scripteditor.utils.TestCase;

/**
 * STIF script file editor.
 * 
 */

public class ScriptEditor extends TextEditor implements
		ISelectionChangedListener, SelectionListener {

	private ScriptEditorOutlinePage outlinePage;

	private ProjectionAnnotationModel annotationModel;

	private ArrayList<ProjectionAnnotation> currentAnnotations = new ArrayList<ProjectionAnnotation>();

	/**
	 * <code>scripterButton = scripterMode</code> value control
	 */
	private Button scripterButton;

	/**
	 * <code>combinerButton = Yes</code> value control
	 */
	private Button combinerButton;

	private EditorMode mode;

	private ScriptEditorConfiguration configuration;

	private ScriptParser parser;

	/**
	 * Script file property qualifier.
	 */
	private static final String STIF_SCRIPT_FILE_SETTINGS = "stif_script_file_settings";

	/**
	 * Script file property qualifier. Used only when file is opened from outsied of the project.
	 */
	private static final String STIF_SCRIPT_FILE_EDIT_MODE = "stif_script_file_edit_mode";

	/**
	 * Script file editor mode property local name.
	 */
	private static final String EDITOR_MODE = "editor_mode";

	/**
	 * Script file editor mode property value for testscripter mode.
	 */
	private static final String EDITOR_MODE_TEST_SCRIPTER = "testscripter";

	/**
	 * Script file editor mode property value for testcombiner mode.
	 */
	private static final String EDITOR_MODE_TEST_COMBINER = "testcombiner";

	public ScriptEditor() {
		mode = EditorMode.scripterMode;
		configuration = new ScriptEditorConfiguration(mode);
		configuration.changeConfigurationMode(mode);
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
	private void refreshOutlineFoldingAndParse() {
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
		if (mode == EditorMode.scripterMode)
			parser.checkScripterScript(scriptContent, getEditorInput());
		else
			parser.checkCombinerScript(scriptContent, getEditorInput());
	}

	/**
	 * Saves editor mode file's persistent property for later restoring the mode
	 * when file is opened again
	 */
	private void saveEditorMode() throws CoreException {
		// Get resource
		IResource resource = getResource();
		if (resource == null) {
			return;
		}

		// Check current editor mode
		String editorModeProperty = null;
		if (mode == EditorMode.combinerMode) {
			editorModeProperty = EDITOR_MODE_TEST_COMBINER;
		} else {
			editorModeProperty = EDITOR_MODE_TEST_SCRIPTER;
		}

		try {
			// Get full resource path
			String fullPath = resource.getFullPath().toString();
			// If fullpath is equal "/" it means that file is opened from the outside of the  
			// workspace
			if (!fullPath.equals("/")) {
				// Save editor mode as <STIF_SCRIPT_FILE_SETTINGS, EDITOR_MODE>=value in file context
				resource.setPersistentProperty(new QualifiedName(
						STIF_SCRIPT_FILE_SETTINGS, EDITOR_MODE),
						editorModeProperty);
			} else {
				// File opened from the outsid of the workspace.
				// Get file url. 
				FileStoreEditorInput editorInput = (FileStoreEditorInput) getEditorInput();
				String fileUrl = editorInput.getURI().getPath();

				// Save editor mode as <STIF_SCRIPT_FILE_EDIT_MODE,url>=value in workspace context
				IResource workspace = ResourcesPlugin.getWorkspace().getRoot();
				workspace.setPersistentProperty(new QualifiedName(
						STIF_SCRIPT_FILE_EDIT_MODE, fileUrl),
						editorModeProperty);
			}
		} catch (CoreException e) {
			IStatus status =
				new Status(IStatus.ERROR, "com.nokia.s60tools.stif.scripteditor", IStatus.OK, 
						"Error saving script editor mode: " + e.getMessage(), null);
			throw new CoreException(status);
		}
	}

	/**
	 * Reads editor mode from file persistent property
	 * @return editor mode
	 */
	private EditorMode readEditorMode() throws CoreException {
		// Get resource
		IResource resource = getResource();

		String editorModeProperty = null;
		try {
			// Get full resource path
			String fullPath = resource.getFullPath().toString();
			// If fullpath is equal "/" it means that file is opened from the outside of the  
			// workspace
			if (!fullPath.equals("/")) {
				// Read editor mode as <STIF_SCRIPT_FILE_SETTINGS, EDITOR_MODE>=value from file context
				editorModeProperty = resource
						.getPersistentProperty(new QualifiedName(
								STIF_SCRIPT_FILE_SETTINGS, EDITOR_MODE));
			} else {
				// File opened from the outsid of the workspace.
				// Get file url. 
				FileStoreEditorInput editorInput = (FileStoreEditorInput) getEditorInput();
				String fileUrl = editorInput.getURI().getPath();

				// Read editor mode as <STIF_SCRIPT_FILE_EDIT_MODE,url>=value from workspace context
				IResource workspace = ResourcesPlugin.getWorkspace().getRoot();
				if (workspace != null) {
					editorModeProperty = workspace
							.getPersistentProperty(new QualifiedName(
									STIF_SCRIPT_FILE_EDIT_MODE, fileUrl));
				}
			}
		} catch (Exception e) {
			IStatus status =
				new Status(IStatus.ERROR, "com.nokia.s60tools.stif.scripteditor", IStatus.OK, 
						"Error loading script editor mode: " + e.getMessage(), null);
			throw new CoreException(status);
		}

		if (editorModeProperty != null) {
			if (editorModeProperty.equals(EDITOR_MODE_TEST_COMBINER)) {
				return EditorMode.combinerMode;
			} else {
				return EditorMode.scripterMode;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * 
	 * Method declared on SelectionListener
	 */
	public void widgetSelected(SelectionEvent selectionEv) {
		String scriptContent = getDocumentProvider().getDocument(
				getEditorInput()).get();
		try {
			if (selectionEv.getSource() == scripterButton
					&& ((Button) selectionEv.getSource()).getSelection()
					&& mode != EditorMode.scripterMode) {
				mode = EditorMode.scripterMode;
	
				ScriptEditorConfiguration conf = (ScriptEditorConfiguration) getSourceViewerConfiguration();
				conf.changeConfigurationMode(EditorMode.scripterMode);
				parser.checkScripterScript(scriptContent, getEditorInput());
	
				this.getSourceViewer().invalidateTextPresentation();
				saveEditorMode();
				
			}
			if (selectionEv.getSource() == combinerButton
					&& ((Button) selectionEv.getSource()).getSelection()
					&& mode != EditorMode.combinerMode) {
				mode = EditorMode.combinerMode;
	
				ScriptEditorConfiguration conf = (ScriptEditorConfiguration) getSourceViewerConfiguration();
				conf.changeConfigurationMode(EditorMode.combinerMode);
				parser.checkCombinerScript(scriptContent, getEditorInput());
	
				this.getSourceViewer().invalidateTextPresentation();
				saveEditorMode();
			}
		} catch(CoreException ex) {
			MessageBox message = new MessageBox(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell());
			message.setText("Problems while saving editor mode");
			message.setMessage(ex.getStatus().getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * Method declared on SelectionListener
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
	
	/* (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
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
	 * Updates folding structure of the current file.
	 * Invalid annotations are removed and new ones are added 
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

	
	/**
	 * Reads editor mode from file persistant property
	 * @return editor mode 
	 */
	private EditorMode queryEditorMode() {
		EditorMode mode = null;
		String dialogTitle = "Choose the proper editor mode";
		String modeQuestion = "Choose the proper editor mode (it will be possible to change the mode later)";
		String scripterButtonText = "test scripter";
		String combinerButtonText = "test combiner";
		MessageDialog modeDialog = new MessageDialog(org.eclipse.ui.PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(),
				dialogTitle, null, modeQuestion, 0, new String[] {
						scripterButtonText, combinerButtonText }, 0);
		int numericChosenMode = modeDialog.open();
		if (numericChosenMode == 0) {
			mode = EditorMode.scripterMode;
		} else {
			mode = EditorMode.combinerMode;
		}

		return mode;
	}

	/* (non-Javadoc)
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
				refreshOutlineFoldingAndParse(); // method responsible for refreshing and parsing tests invoked after any key event.
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		try {
			mode = readEditorMode();
	
			if (mode == null) {
				mode = queryEditorMode();
				saveEditorMode();
			}
		} catch (CoreException ex) {
			MessageBox message = new MessageBox(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell());
			message.setText("Problems while saving editor mode");
			message.setMessage(ex.getStatus().getMessage());
		}

		if (mode == EditorMode.scripterMode) {
			scripterButton.setSelection(true);
			combinerButton.setSelection(false);
		} else {
			scripterButton.setSelection(false);
			combinerButton.setSelection(true);
		}

		configuration.changeConfigurationMode(mode);
	}

	/* (non-Javadoc)
	 * 
	 * Overriden 
	 */
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		Group buttonsGroup = new Group(parent, SWT.NONE);
		GridLayout buttonsLayout = new GridLayout();
		buttonsLayout.numColumns = 2;
		buttonsGroup.setLayout(buttonsLayout);

		GridData scripterButtonGridData = new GridData(GridData.BEGINNING);
		scripterButton = new Button(buttonsGroup, SWT.RADIO);
		scripterButton.setText("test scripter");
		scripterButton.setLayoutData(scripterButtonGridData);
		scripterButton.addSelectionListener(this);
		if (mode == EditorMode.scripterMode)
			scripterButton.setSelection(true);

		GridData combinerButtonGridData = new GridData(GridData.FILL_HORIZONTAL);
		combinerButton = new Button(buttonsGroup, SWT.RADIO);
		combinerButton.setText("test combiner");
		combinerButton.setLayoutData(combinerButtonGridData);
		combinerButton.addSelectionListener(this);
		if (mode == EditorMode.combinerMode)
			combinerButton.setSelection(true);

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		parent.setLayout(layout);

		GridData scriptEditorGridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		GridData buttonsGroupGridData = new GridData(GridData.FILL_HORIZONTAL);

		ProjectionViewer sourceViewer = new ProjectionViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);
		getSourceViewerDecorationSupport(sourceViewer);

		sourceViewer.getTextWidget().getParent().setLayoutData(
				scriptEditorGridData);
		buttonsGroup.setLayoutData(buttonsGroupGridData);

		return sourceViewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#createActions()
	 */
	protected void createActions() {
		super.createActions(); //piko 
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("STIFScriptsEditor");
		Action action = new ContentAssistAction(resourceBundle,
				"ContentAssistProposal.", this);
		String id = ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
		action.setActionDefinitionId(id);
		setAction("ContentAssistProposal", action);
		markAsStateDependentAction("ContentAssistProposal", true);
		checkScript();
	}
	
	/**
	 * Editor mode type
	 * 
	 */
	public enum EditorMode {
		scripterMode, 
		combinerMode
	}

	/**
	 * Gets resource associated with editor input
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
}
