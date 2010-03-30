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
package com.nokia.testfw.codegen.ui.preferences;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.cdt.internal.ui.editor.CSourceViewer;
import org.eclipse.cdt.internal.ui.preferences.CSourcePreviewerUpdater;
import org.eclipse.cdt.internal.ui.preferences.CodeTemplateSourceViewerConfiguration;
import org.eclipse.cdt.internal.ui.text.CTextTools;
import org.eclipse.cdt.internal.ui.text.template.TemplateVariableProcessor;
import org.eclipse.cdt.internal.ui.util.PixelConverter;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateReaderWriter;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.internal.texteditor.NLSUtility;
import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import com.nokia.testfw.codegen.ui.CodegenUIPlugin;
import com.nokia.testfw.codegen.templates.TemplateLoader;
import com.nokia.testfw.codegen.ui.Messages;
import com.nokia.testfw.codegen.ui.util.PathNode;
import com.nokia.testfw.codegen.ui.util.PathNodeConverter;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

@SuppressWarnings("restriction")
public class TESTFWTemplatePreferencePage extends TemplatePreferencePage
		implements IWorkbenchPreferencePage {

	private class TESTFWTemplateAdapter implements ITreeContentProvider,
			ISelectionChangedListener, IDoubleClickListener {

		private final Object NO_CHILDREN[];

		TESTFWTemplateAdapter() {
			super();
			NO_CHILDREN = new Object[0];
		}

		public Object[] getChildren(Object obj) {
			if (obj instanceof PathNode)
				return ((PathNode) obj).getChildren();
			else
				return NO_CHILDREN;
		}

		public Object getParent(Object obj) {
			if (obj instanceof PathNode)
				return ((PathNode) obj).getParent();
			else
				return null;
		}

		public boolean hasChildren(Object obj) {
			if (obj instanceof PathNode)
				return ((PathNode) obj).getChildren().length > 0;
			else
				return false;
		}

		public Object[] getElements(Object obj) {
			if (obj instanceof TemplateStore) {
				return convertToPathTree((TemplateStore) obj).getChildren();
			}
			return NO_CHILDREN;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object obj, Object obj1) {
		}

		public void selectionChanged(SelectionChangedEvent selectionchangedevent) {
			updateButtons();
			updateViewerInput();
		}

		public void doubleClick(DoubleClickEvent doubleclickevent) {
			if (canEdit()) {
				edit();
			}
		}
	}

	private static class TESTFWTemplateLabelProvider extends LabelProvider {

		@SuppressWarnings("deprecation")
		public Image getImage(Object obj) {
			if (obj instanceof PathNode) {
				PathNode node = (PathNode) obj;
				if (node.getData() != null) {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJ_FILE);
				}
				if (node.getParent().getParent() == null) {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJ_PROJECT);
				} else {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJ_FOLDER);
				}
			}
			return null;
		}

		public String getText(Object obj) {
			if (obj instanceof PathNode)
				return ((PathNode) obj).getName();
			return null;
		}
	}

	/** The tree presenting the templates. */
	public TreeViewer iTreeViewer;

	/* buttons */
	public Button iAddButton;
	public Button iEditButton;
	public Button iImportButton;
	public Button iExportButton;
	public Button iRemoveButton;
	public Button iRestoreButton;
	public Button iRevertButton;

	private SourceViewer iPatternViewer;
	private TemplateVariableProcessor iTemplateProcessor;

	public TESTFWTemplatePreferencePage() {
		setPreferenceStore(CodegenUIPlugin.getDefault().getPreferenceStore());
		setTemplateStore(CodegenUIPlugin.getDefault().getTemplateStore());
		setContextTypeRegistry(CodegenUIPlugin.getDefault()
				.getContextTypeRegistry());
		iTemplateProcessor = new TemplateVariableProcessor();
	}

	protected boolean isShowFormatterSetting() {
		return false;
	}

	public boolean performOk() {

		boolean ok = super.performOk();

		//CodegenUIPlugin.getDefault().savePluginPreferences();

		return ok;
	}

	protected Control createContents(Composite ancestor) {
		Composite parent = new Composite(ancestor, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);

		Composite innerParent = new Composite(parent, SWT.NONE);
		GridLayout innerLayout = new GridLayout();
		innerLayout.numColumns = 2;
		innerLayout.marginHeight = 0;
		innerLayout.marginWidth = 0;
		innerParent.setLayout(innerLayout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		innerParent.setLayoutData(gd);

		Composite treeComposite = new Composite(innerParent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 360;
		data.heightHint = convertHeightInCharsToPixels(10);
		treeComposite.setLayoutData(data);

		FillLayout treeLayout = new FillLayout();
		treeComposite.setLayout(treeLayout);

		TESTFWTemplateAdapter adapter = new TESTFWTemplateAdapter();

		iTreeViewer = new TreeViewer(treeComposite, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		iTreeViewer.setLabelProvider(new TESTFWTemplateLabelProvider());
		iTreeViewer.setContentProvider(adapter);
		iTreeViewer.addSelectionChangedListener(adapter);
		iTreeViewer.addDoubleClickListener(adapter);

		iTreeViewer.setComparator(new ViewerComparator() {
			public int compare(Viewer viewer, Object object1, Object object2) {
				if ((object1 instanceof PathNode)
						&& (object2 instanceof PathNode)) {
					String leftName = ((PathNode) object1).getName();
					String rightName = ((PathNode) object2).getName();
					int result = Collator.getInstance().compare(leftName,
							rightName);
					return result;
				}
				return super.compare(viewer, object1, object2);
			}

			public boolean isSorterProperty(Object element, String property) {
				return true;
			}
		});

		Composite buttons = new Composite(innerParent, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);

		iAddButton = new Button(buttons, SWT.PUSH);
		iAddButton.setText(Messages.getString("TemplatePreferencePage_new"));
		iAddButton.setLayoutData(new GridData(768));
		iAddButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				add();
			}
		});

		iEditButton = new Button(buttons, SWT.PUSH);
		iEditButton.setText(Messages.getString("TemplatePreferencePage_edit"));
		iEditButton.setLayoutData(new GridData(768));
		iEditButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				edit();
			}
		});

		iRemoveButton = new Button(buttons, SWT.PUSH);
		iRemoveButton.setText(Messages
				.getString("TemplatePreferencePage_remove"));
		iRemoveButton.setLayoutData(new GridData(768));
		iRemoveButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				remove();
			}
		});

		createSeparator(buttons);

		iRestoreButton = new Button(buttons, SWT.PUSH);
		iRestoreButton.setText(Messages
				.getString("TemplatePreferencePage_restore"));
		iRestoreButton.setLayoutData(new GridData(768));
		iRestoreButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				restoreDeleted();
			}
		});

		iRevertButton = new Button(buttons, SWT.PUSH);
		iRevertButton.setText(Messages
				.getString("TemplatePreferencePage_revert"));
		iRevertButton.setLayoutData(new GridData(768));
		iRevertButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				revert();
			}
		});

		createSeparator(buttons);

		iImportButton = new Button(buttons, SWT.PUSH);
		iImportButton.setText(Messages
				.getString("TemplatePreferencePage_import"));
		iImportButton.setLayoutData(new GridData(768));
		iImportButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				import_();
			}
		});

		iExportButton = new Button(buttons, SWT.PUSH);
		iExportButton.setText(Messages
				.getString("TemplatePreferencePage_export"));
		iExportButton.setLayoutData(new GridData(768));
		iExportButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				export();
			}
		});

		iPatternViewer = createViewer(parent, 2);

		iTreeViewer.setInput(getTemplateStore());

		updateButtons();
		Dialog.applyDialogFont(parent);
		innerParent.layout();

		return parent;
	}

	private Label createSeparator(Composite parent) {
		Label separator = new Label(parent, 0);
		separator.setVisible(false);
		GridData gd = new GridData();
		gd.horizontalAlignment = 4;
		gd.verticalAlignment = 1;
		gd.heightHint = 4;
		separator.setLayoutData(gd);
		return separator;
	}

	private void add() {

		Iterator<?> it = getContextTypeRegistry().contextTypes();
		if (it.hasNext()) {
			Template template = new Template(
					"", "", ((TemplateContextType) it.next()).getId(), "", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			Template newTemplate = editTemplate(template, false, true);
			if (newTemplate != null) {
				TemplatePersistenceData data = new TemplatePersistenceData(
						newTemplate, true);
				getTemplateStore().add(data);
				iTreeViewer.refresh();
				iTreeViewer.setSelection(new StructuredSelection(data));
			}
		}
	}

	protected boolean canEdit() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		return (selection.size() == 1 && (((PathNode) selection
				.getFirstElement()).getData() != null));
	}

	private void edit() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		Object[] objects = selection.toArray();
		if ((objects == null) || (objects.length != 1))
			return;

		TemplatePersistenceData data = (TemplatePersistenceData) ((PathNode) selection
				.getFirstElement()).getData();
		if (data != null)
			edit(data);
	}

	private void edit(TemplatePersistenceData data) {
		Template oldTemplate = data.getTemplate();
		Template newTemplate = editTemplate(new Template(oldTemplate), true,
				true);
		if (newTemplate != null) {

			if (!newTemplate.getName().equals(oldTemplate.getName())
					&& MessageDialog
							.openQuestion(
									getShell(),
									Messages
											.getString("TemplatePreferencePage_question_create_new_title"),
									Messages
											.getString("TemplatePreferencePage_question_create_new_message"))) {
				data = new TemplatePersistenceData(newTemplate, true);
				getTemplateStore().add(data);
				iTreeViewer.refresh();
			} else {
				data.setTemplate(newTemplate);
				iTreeViewer.refresh(data);
			}
			selectionChanged();
			iTreeViewer.setSelection(new StructuredSelection(data));
		}
	}

	protected boolean canRemove() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		if (selection.size() == 1
				&& (((PathNode) selection.getFirstElement()).getData() != null)) {
			TemplatePersistenceData data = (TemplatePersistenceData) ((PathNode) selection
					.getFirstElement()).getData();
			return data.isUserAdded();
		} else {
			return false;
		}
	}

	private void remove() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		Iterator<?> elements = selection.iterator();
		while (elements.hasNext()) {
			TemplatePersistenceData data = (TemplatePersistenceData) ((PathNode) elements
					.next()).getData();
			getTemplateStore().delete(data);
		}

		iTreeViewer.refresh();
	}

	private void restoreDeleted() {
		getTemplateStore().restoreDeleted();
		iTreeViewer.refresh();
		updateButtons();
	}

	private void revert() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		Iterator<?> elements = selection.iterator();
		while (elements.hasNext()) {
			TemplatePersistenceData data = (TemplatePersistenceData) ((PathNode) elements
					.next()).getData();
			data.revert();
		}

		selectionChanged();
		iTreeViewer.refresh();
	}

	private void import_() {
		FileDialog dialog = new FileDialog(getShell());
		dialog.setText(Messages
				.getString("TemplatePreferencePage_import_title"));
		dialog.setFilterExtensions(new String[] { Messages
				.getString("TemplatePreferencePage_import_extension") });
		String path = dialog.open();

		if (path == null)
			return;

		try {
			TemplateReaderWriter reader = new TemplateReaderWriter();
			File file = new File(path);
			if (file.exists()) {
				InputStream input = new BufferedInputStream(
						new FileInputStream(file));
				try {
					TemplatePersistenceData[] datas = reader.read(input, null);
					for (int i = 0; i < datas.length; i++) {
						TemplatePersistenceData data = datas[i];
						getTemplateStore().add(data);
					}
				} finally {
					try {
						input.close();
					} catch (IOException x) {
						// ignore
					}
				}
			}

			iTreeViewer.refresh();

		} catch (FileNotFoundException e) {
			openReadErrorDialog();
		} catch (IOException e) {
			openReadErrorDialog();
		}
	}

	private void export() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();
		Object[] nodes = selection.toArray();

		Set<TemplatePersistenceData> dataSet = new HashSet<TemplatePersistenceData>();
		for (int i = 0; i != nodes.length; i++) {
			dataSet.addAll(getChildrenTemplate((PathNode) nodes[i]));
		}
		export(dataSet.toArray(new TemplatePersistenceData[0]));
	}

	private void export(TemplatePersistenceData[] templates) {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		dialog.setText(Messages
				.getString("TemplatePreferencePage_export_title"));
		dialog.setFilterExtensions(new String[] { Messages
				.getString("TemplatePreferencePage_export_extension") });
		dialog.setFileName(Messages
				.getString("TemplatePreferencePage_export_filename"));
		String path = dialog.open();

		if (path == null)
			return;

		File file = new File(path);

		if (file.isHidden()) {
			String title = Messages
					.getString("TemplatePreferencePage_export_error_title");
			String message = NLSUtility.format(Messages
					.getString("TemplatePreferencePage_export_error_hidden"),
					file.getAbsolutePath());
			MessageDialog.openError(getShell(), title, message);
			return;
		}

		if (file.exists() && !file.canWrite()) {
			String title = Messages
					.getString("TemplatePreferencePage_export_error_title");
			String message = NLSUtility
					.format(
							Messages
									.getString("TemplatePreferencePage_export_error_canNotWrite"),
							file.getAbsolutePath());
			MessageDialog.openError(getShell(), title, message);
			return;
		}

		if (!file.exists() || confirmOverwrite(file)) {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(file));
				TemplateReaderWriter writer = new TemplateReaderWriter();
				writer.save(templates, output);
			} catch (IOException e) {
				openWriteErrorDialog();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
		}
	}

	private boolean confirmOverwrite(File file) {
		return MessageDialog
				.openQuestion(
						getShell(),
						Messages
								.getString("TemplatePreferencePage_export_exists_title"),
						NLSUtility
								.format(
										Messages
												.getString("TemplatePreferencePage_export_exists_message"),
										file.getAbsolutePath()));
	}

	private void openReadErrorDialog() {
		String title = Messages
				.getString("TemplatePreferencePage_error_read_title");
		String message = Messages
				.getString("TemplatePreferencePage_error_read_message");
		MessageDialog.openError(getShell(), title, message);
	}

	/*
	 * @since 3.2
	 */
	private void openWriteErrorDialog() {
		String title = Messages
				.getString("TemplatePreferencePage_error_write_title");
		String message = Messages
				.getString("TemplatePreferencePage_error_write_message");
		MessageDialog.openError(getShell(), title, message);
	}

	private void selectionChanged() {
		updateViewerInput();
		updateButtons();
	}

	/**
	 * Updates the pattern viewer.
	 */
	protected void updateViewerInput() {
		if (iPatternViewer == null
				|| iPatternViewer.getTextWidget().isDisposed())
			return;
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();

		if (selection.size() == 1
				&& (((PathNode) selection.getFirstElement()).getData() != null)) {
			TemplatePersistenceData data = (TemplatePersistenceData) ((PathNode) selection
					.getFirstElement()).getData();
			Template template = data.getTemplate();
			TemplateContextType type = CUIPlugin.getDefault()
					.getCodeTemplateContextRegistry().getContextType(
							template.getContextTypeId());
			if (type == null) {
				type = CodegenUIPlugin.getDefault().getContextTypeRegistry()
						.getContextType(template.getContextTypeId());
			}
			iTemplateProcessor.setContextType(type);
			reconfigurePatternViewer();
			iPatternViewer.getDocument().set(template.getPattern());
		} else {
			iPatternViewer.getDocument().set("");
		}

	}

	private SourceViewer createViewer(Composite parent, int nColumns) {
		Label label = new Label(parent, 0);
		label.setText(Messages.getString("TemplatePreferencePage_preview"));
		GridData data = new GridData();
		data.horizontalSpan = nColumns;
		label.setLayoutData(data);
		IDocument document = new Document();
		CTextTools tools = CUIPlugin.getDefault().getTextTools();
		tools.setupCDocumentPartitioner(document, "___c_partitioning", null);
		org.eclipse.jface.preference.IPreferenceStore store = CUIPlugin
				.getDefault().getCombinedPreferenceStore();
		SourceViewer viewer = new CSourceViewer(parent, null, null, false,
				2816, store);
		CodeTemplateSourceViewerConfiguration configuration = new CodeTemplateSourceViewerConfiguration(
				tools.getColorManager(), store, null, iTemplateProcessor);
		viewer.configure(configuration);
		viewer.setEditable(false);
		viewer.setDocument(document);
		org.eclipse.swt.graphics.Font font = JFaceResources
				.getFont("org.eclipse.cdt.ui.editors.textfont");
		viewer.getTextWidget().setFont(font);
		new CSourcePreviewerUpdater(viewer, configuration, store);
		Control control = viewer.getControl();
		data = new GridData(1296);
		data.horizontalSpan = nColumns;
		data.heightHint = new PixelConverter(parent)
				.convertHeightInCharsToPixels(5);
		control.setLayoutData(data);
		return viewer;
	}

	private void reconfigurePatternViewer() {
		if (iPatternViewer == null) {
			return;
		} else {
			CTextTools tools = CUIPlugin.getDefault().getTextTools();
			org.eclipse.jface.preference.IPreferenceStore store = CUIPlugin
					.getDefault().getCombinedPreferenceStore();
			CodeTemplateSourceViewerConfiguration configuration = new CodeTemplateSourceViewerConfiguration(
					tools.getColorManager(), store, null, iTemplateProcessor);
			iPatternViewer.unconfigure();
			iPatternViewer.configure(configuration);
			iPatternViewer.invalidateTextPresentation();
			return;
		}
	}

	/**
	 * Updates the buttons.
	 */
	protected void updateButtons() {
		IStructuredSelection selection = (IStructuredSelection) iTreeViewer
				.getSelection();
		int selectionCount = selection.size();
		boolean canRestore = getTemplateStore().getTemplateData(true).length != getTemplateStore()
				.getTemplateData(false).length;
		boolean canRevert = false;
		TemplatePersistenceData data = null;
		for (Iterator<?> it = selection.iterator(); it.hasNext();) {
			data = (TemplatePersistenceData) ((PathNode) it.next()).getData();
			if (data != null && data.isModified()) {
				canRevert = true;
				break;
			}
		}

		iEditButton.setEnabled(selectionCount == 1 && data != null);
		iExportButton.setEnabled(selectionCount > 0);
		iRemoveButton.setEnabled(selectionCount > 0);
		iRestoreButton.setEnabled(canRestore);
		iRevertButton.setEnabled(canRevert);
	}

	private PathNode convertToPathTree(TemplateStore aTemplateStore) {
		PathNode root = new PathNode(TemplateLoader.TEMPLATES_DIR);
		TemplatePersistenceData[] lTemplateDataArray = aTemplateStore
				.getTemplateData(false);

		for (TemplatePersistenceData data : lTemplateDataArray) {
			String path = data.getTemplate().getDescription();
			PathNode node = PathNodeConverter.pathToNode(root, path);
			node.setData(data);
		}
		return root;
	}

	private Set<TemplatePersistenceData> getChildrenTemplate(PathNode parent) {
		Set<TemplatePersistenceData> templates = new HashSet<TemplatePersistenceData>();
		if (parent.getData() != null) {
			templates.add((TemplatePersistenceData) parent.getData());
		} else {
			for (PathNode child : parent.getChildren()) {
				if (child.getData() != null) {
					templates.add((TemplatePersistenceData) child.getData());
				} else {
					templates.addAll(getChildrenTemplate(child));
				}
			}
		}
		return templates;
	}
}