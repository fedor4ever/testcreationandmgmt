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


package org.eclipse.ui.texteditor.templates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.ActiveShellExpression;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.texteditor.NLSUtility;
import org.eclipse.ui.internal.texteditor.TextEditorPlugin;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

import com.ibm.icu.text.Collator;

/**
 * A template preference page allows configuration of the templates for an
 * editor. It provides controls for adding, removing and changing templates as
 * well as enablement, default management and an optional formatter preference.
 * <p>
 * Subclasses need to provide a {@link TemplateStore} and a
 * {@link ContextTypeRegistry} and should set the preference store. They may
 * optionally override {@link #isShowFormatterSetting()}.
 * </p>
 * 
 * @since 3.0
 */
public abstract class CTemplatePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	final class ColumnLayout extends Layout {

		ColumnLayout() {
			columns = new ArrayList();
		}

		public void addColumnData(ColumnLayoutData data) {
			columns.add(data);
		}

		private Point computeTableSize(Table table, int wHint, int hHint) {
			Point result = table.computeSize(wHint, hHint);
			int width = 0;
			int size = columns.size();
			for (int i = 0; i < size; i++) {
				ColumnLayoutData layoutData = (ColumnLayoutData) columns.get(i);
				if (layoutData instanceof ColumnPixelData) {
					ColumnPixelData col = (ColumnPixelData) layoutData;
					width += col.width;
					if (col.addTrim)
						width += COLUMN_TRIM;
				} else if (layoutData instanceof ColumnWeightData) {
					ColumnWeightData col = (ColumnWeightData) layoutData;
					width += col.minimumWidth;
				} else {
					Assert.isTrue(false, "Unknown column layout data");
				}
			}

			if (width > result.x)
				result.x = width;
			return result;
		}

		private void layoutTable(Table table, int width, Rectangle area,
				boolean increase) {
			TableColumn tableColumns[] = table.getColumns();
			int size = Math.min(columns.size(), tableColumns.length);
			int widths[] = new int[size];
			int weightIteration[] = new int[size];
			int numberOfWeightColumns = 0;
			int fixedWidth = 0;
			int minWeightWidth = 0;
			int totalWeight = 0;
			for (int i = 0; i < size; i++) {
				ColumnLayoutData col = (ColumnLayoutData) columns.get(i);
				if (col instanceof ColumnPixelData) {
					ColumnPixelData cpd = (ColumnPixelData) col;
					int pixels = cpd.width;
					if (cpd.addTrim)
						pixels += COLUMN_TRIM;
					widths[i] = pixels;
					fixedWidth += pixels;
				} else if (col instanceof ColumnWeightData) {
					ColumnWeightData cw = (ColumnWeightData) col;
					weightIteration[numberOfWeightColumns] = i;
					numberOfWeightColumns++;
					totalWeight += cw.weight;
					minWeightWidth += cw.minimumWidth;
					widths[i] = cw.minimumWidth;
				} else {
					Assert.isTrue(false, "Unknown column layout data");
				}
			}

			int restIncludingMinWidths = width - fixedWidth;
			int rest = restIncludingMinWidths - minWeightWidth;
			if (numberOfWeightColumns > 0 && rest > 0) {
				int totalWantedPixels = 0;
				int wantedPixels[] = new int[numberOfWeightColumns];
				for (int i = 0; i < numberOfWeightColumns; i++) {
					ColumnWeightData cw = (ColumnWeightData) columns
							.get(weightIteration[i]);
					wantedPixels[i] = totalWeight != 0 ? (cw.weight * restIncludingMinWidths)
							/ totalWeight
							: 0;
					totalWantedPixels += wantedPixels[i];
				}

				int totalDistributed = 0;
				for (int i = 0; i < numberOfWeightColumns; i++) {
					int pixels = totalWantedPixels != 0 ? (wantedPixels[i] * rest)
							/ totalWantedPixels
							: 0;
					totalDistributed += pixels;
					widths[weightIteration[i]] += pixels;
				}

				int diff = rest - totalDistributed;
				for (int i = 0; diff > 0; i = (i + 1) % numberOfWeightColumns) {
					widths[weightIteration[i]]++;
					diff--;
				}

			}
			if (increase)
				table.setSize(area.width, area.height);
			for (int i = 0; i < size; i++)
				tableColumns[i].setWidth(widths[i]);

			if (!increase)
				table.setSize(area.width, area.height);
		}

		protected Point computeSize(Composite composite, int wHint, int hHint,
				boolean flushCache) {
			return computeTableSize(getTable(composite), wHint, hHint);
		}

		protected void layout(Composite composite, boolean flushCache) {
			Rectangle area = composite.getClientArea();
			Table table = getTable(composite);
			int tableWidth = table.getSize().x;
			int trim = computeTrim(area, table, tableWidth);
			int width = Math.max(0, area.width - trim);
			if (width > 1)
				layoutTable(table, width, area, tableWidth < area.width);
			if (composite.getData("recalculateKey") == null) {
				composite.setData("recalculateKey", Boolean.FALSE);
				composite.layout();
			}
		}

		private int computeTrim(Rectangle area, Table table, int tableWidth) {
			Point preferredSize = computeTableSize(table, area.width,
					area.height);
			int trim;
			if (tableWidth > 1)
				trim = tableWidth - table.getClientArea().width;
			else
				trim = 2 * table.getBorderWidth() + 1;
			if (preferredSize.y > area.height) {
				ScrollBar vBar = table.getVerticalBar();
				if (!vBar.isVisible()) {
					Point vBarSize = vBar.getSize();
					trim += vBarSize.x;
				}
			}
			return trim;
		}

		private Table getTable(Composite composite) {
			return (Table) composite.getChildren()[0];
		}

		private static final String RECALCULATE_LAYOUT = "recalculateKey";
		private int COLUMN_TRIM = "carbon".equals(SWT.getPlatform()) ? 24 : 3;
		private List columns;

	}

	/**
	 * Dialog to edit a template. Clients will usually instantiate, but may also
	 * extend.
	 * 
	 * @since 3.3
	 */
	protected static class EditTemplateDialog extends StatusDialog {

		private class TextViewerAction extends Action implements IUpdate {

			private int fOperationCode = -1;
			private ITextOperationTarget fOperationTarget;

			/**
			 * Creates a new action.
			 * 
			 * @param viewer
			 *            the viewer
			 * @param operationCode
			 *            the opcode
			 */
			public TextViewerAction(ITextViewer viewer, int operationCode) {
				fOperationCode = operationCode;
				fOperationTarget = viewer.getTextOperationTarget();
				update();
			}

			/**
			 * Updates the enabled state of the action. Fires a property change
			 * if the enabled state changes.
			 * 
			 * @see Action#firePropertyChange(String, Object, Object)
			 */
			public void update() {
				// XXX: workaround for
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=206111
				if (fOperationCode == ITextOperationTarget.REDO)
					return;

				boolean wasEnabled = isEnabled();
				boolean isEnabled = (fOperationTarget != null && fOperationTarget
						.canDoOperation(fOperationCode));
				setEnabled(isEnabled);

				if (wasEnabled != isEnabled) {
					firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE
							: Boolean.FALSE, isEnabled ? Boolean.TRUE
							: Boolean.FALSE);
				}
			}

			/**
			 * @see Action#run()
			 */
			public void run() {
				if (fOperationCode != -1 && fOperationTarget != null) {
					fOperationTarget.doOperation(fOperationCode);
				}
			}
		}

		private final Template fOriginalTemplate;

		private Text fNameText;
		private Text fDescriptionText;
		private Combo fContextCombo;
		private SourceViewer fPatternEditor;
		private Button fInsertVariableButton;
		private Button fAutoInsertCheckbox;
		private boolean fIsNameModifiable;

		private CStatusInfo fValidationStatus;
		private boolean fSuppressError = true; // #4354
		private Map fGlobalActions = new HashMap(10);
		private List fSelectionActions = new ArrayList(3);
		private String[][] fContextTypes;

		private ContextTypeRegistry fContextTypeRegistry;

		private CTemplateVariableProcessor fTemplateProcessor = new CTemplateVariableProcessor();

		private Template fNewTemplate;

		/**
		 * Creates a new dialog.
		 * 
		 * @param parent
		 *            the shell parent of the dialog
		 * @param template
		 *            the template to edit
		 * @param edit
		 *            whether this is a new template or an existing being edited
		 * @param isNameModifiable
		 *            whether the name of the template may be modified
		 * @param registry
		 *            the context type registry to use
		 */
		public EditTemplateDialog(Shell parent, Template template,
				boolean edit, boolean isNameModifiable,
				ContextTypeRegistry registry) {
			super(parent);

			String title = "Edit Template";
			setTitle(title);

			fOriginalTemplate = template;
			fIsNameModifiable = isNameModifiable;

			List contexts = new ArrayList();
			for (Iterator it = registry.contextTypes(); it.hasNext();) {
				TemplateContextType type = (TemplateContextType) it.next();
				contexts.add(new String[] { type.getId(), type.getName() });
			}
			fContextTypes = (String[][]) contexts.toArray(new String[contexts
					.size()][]);

			fValidationStatus = new CStatusInfo();

			fContextTypeRegistry = registry;

			TemplateContextType type = fContextTypeRegistry
					.getContextType(template.getContextTypeId());
			fTemplateProcessor.setContextType(type);
		}

		/*
		 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
		 * @since 3.4
		 */
		protected boolean isResizable() {
			return true;
		}

		/*
		 * @see org.eclipse.ui.texteditor.templates.StatusDialog#create()
		 */
		public void create() {
			super.create();
			// update initial OK button to be disabled for new templates
			boolean valid = fNameText == null
					|| fNameText.getText().trim().length() != 0;
			if (!valid) {
				StatusInfo status = new StatusInfo();
				status.setError("Template name can't be empty. ");
				updateButtonsEnableState(status);
			}
		}

		/*
		 * @see Dialog#createDialogArea(Composite)
		 */
		protected Control createDialogArea(Composite ancestor) {
			Composite parent = new Composite(ancestor, SWT.NONE);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
			layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
			layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
			layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
			parent.setLayout(layout);
			parent.setLayoutData(new GridData(GridData.FILL_BOTH));

			ModifyListener listener = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					doTextWidgetChanged(e.widget);
				}
			};

			if (fIsNameModifiable) {
				createLabel(parent, "Name: ");

				Composite composite = new Composite(parent, SWT.NONE);
				composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				layout = new GridLayout();
				layout.numColumns = 4;
				layout.marginWidth = 0;
				layout.marginHeight = 0;
				composite.setLayout(layout);

				fNameText = createText(composite);
				fNameText.addModifyListener(listener);
				fNameText.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
					}

					public void focusLost(FocusEvent e) {
						if (fSuppressError) {
							fSuppressError = false;
							updateButtons();
						}
					}
				});
				fNameText.setEnabled(false);

				createLabel(composite, "Context: ");
				fContextCombo = new Combo(composite, SWT.READ_ONLY);

				for (int i = 0; i < fContextTypes.length; i++) {
					fContextCombo.add(fContextTypes[i][1]);
				}

				fContextCombo.addModifyListener(listener);
				fContextCombo.setEnabled(false);

				fAutoInsertCheckbox = createCheckbox(composite,
						"Automatically insert");
				fAutoInsertCheckbox.setSelection(fOriginalTemplate
						.isAutoInsertable());
				fAutoInsertCheckbox.setEnabled(false);
			}

			createLabel(parent, "File Name: ");

			int descFlags = fIsNameModifiable ? SWT.BORDER : SWT.BORDER
					| SWT.READ_ONLY;
			fDescriptionText = new Text(parent, descFlags);
			fDescriptionText.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL));

			fDescriptionText.addModifyListener(listener);

			Label patternLabel = createLabel(parent, "Pattern: ");
			patternLabel.setLayoutData(new GridData(
					GridData.VERTICAL_ALIGN_BEGINNING));
			fPatternEditor = createEditor(parent, fOriginalTemplate
					.getPattern());

			Label filler = new Label(parent, SWT.NONE);
			filler.setLayoutData(new GridData());

			final Composite composite = new Composite(parent, SWT.NONE);
			layout = new GridLayout();
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			composite.setLayout(layout);
			composite.setLayoutData(new GridData());

			final Menu menu = new Menu(composite);
			
			createMenuItem(menu, "${author}");
			createMenuItem(menu, "${baseName}");
			createMenuItem(menu, "${baseNameUpper}");
			createMenuItem(menu, "${copyright}");
			new MenuItem(menu, SWT.SEPARATOR);
			createMenuItem(menu, "${controllerIncludes}");
			createMenuItem(menu, "${wrapperLIT}");
			createMenuItem(menu, "${wrapperCreating}");
						
			fInsertVariableButton = new Button(composite, SWT.NONE);
			fInsertVariableButton
					.setLayoutData(getButtonGridData(fInsertVariableButton));
			fInsertVariableButton.setText("Insert Variable...");
			fInsertVariableButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					menu.setVisible(true);
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});

			fDescriptionText.setText(fOriginalTemplate.getDescription());
			if (fIsNameModifiable) {
				fNameText.setText(fOriginalTemplate.getName());
				fNameText.addModifyListener(listener);
				fContextCombo.select(getIndex(fOriginalTemplate
						.getContextTypeId()));
			} else {
				fPatternEditor.getControl().setFocus();
			}
			initializeActions();

			applyDialogFont(parent);
			return composite;
		}

		private void createMenuItem(Menu menu, final String string) {
			MenuItem item = new MenuItem(menu, SWT.NONE);
			item.setText(string);
			item.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					fPatternEditor.getTextWidget().insert(string);
				}
			});
		}

		private void doTextWidgetChanged(Widget w) {
			if (w == fNameText) {
				fSuppressError = false;
				updateButtons();
			} else if (w == fContextCombo) {
				String contextId = getContextId();
				fTemplateProcessor.setContextType(fContextTypeRegistry
						.getContextType(contextId));
			} else if (w == fDescriptionText) {
				// oh, nothing
			}
		}

		private String getContextId() {
			if (fContextCombo != null && !fContextCombo.isDisposed()) {
				String name = fContextCombo.getText();
				for (int i = 0; i < fContextTypes.length; i++) {
					if (name.equals(fContextTypes[i][1])) {
						return fContextTypes[i][0];
					}
				}
			}

			return fOriginalTemplate.getContextTypeId();
		}

		private void doSourceChanged(IDocument document) {
			String text = document.get();
			fValidationStatus.setOK();
			TemplateContextType contextType = fContextTypeRegistry
					.getContextType(getContextId());
			if (contextType != null) {
				try {
					contextType.validate(text);
				} catch (TemplateException e) {
					fValidationStatus.setError(e.getLocalizedMessage());
				}
			}

			updateAction(ITextEditorActionConstants.UNDO);
			updateButtons();
		}

		/**
		 * Return the grid data for the button.
		 * 
		 * @param button
		 *            the button
		 * @return the grid data
		 */
		private static GridData getButtonGridData(Button button) {
			GridData data = new GridData(GridData.FILL_HORIZONTAL);

			return data;
		}

		private static Label createLabel(Composite parent, String name) {
			Label label = new Label(parent, SWT.NULL);
			label.setText(name);
			label.setLayoutData(new GridData());

			return label;
		}

		private static Text createText(Composite parent) {
			Text text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			return text;
		}

		private static Button createCheckbox(Composite parent, String name) {
			Button button = new Button(parent, SWT.CHECK);
			button.setText(name);
			button.setLayoutData(new GridData());

			return button;
		}

		private SourceViewer createEditor(Composite parent, String pattern) {
			SourceViewer viewer = createViewer(parent);
			viewer.setEditable(true);

			IDocument document = viewer.getDocument();
			if (document != null)
				document.set(pattern);
			else {
				document = new Document(pattern);
				viewer.setDocument(document);
			}

			int nLines = document.getNumberOfLines();
			if (nLines < 5) {
				nLines = 5;
			} else if (nLines > 12) {
				nLines = 12;
			}

			Control control = viewer.getControl();
			GridData data = new GridData(GridData.FILL_BOTH);
			data.widthHint = convertWidthInCharsToPixels(80);
			data.heightHint = convertHeightInCharsToPixels(nLines);
			control.setLayoutData(data);

			viewer.addTextListener(new ITextListener() {
				public void textChanged(TextEvent event) {
					if (event.getDocumentEvent() != null)
						doSourceChanged(event.getDocumentEvent().getDocument());
				}
			});

			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					updateSelectionDependentActions();
				}
			});

			return viewer;
		}

		/**
		 * Creates the viewer to be used to display the pattern. Subclasses may
		 * override.
		 * 
		 * @param parent
		 *            the parent composite of the viewer
		 * @return a configured <code>SourceViewer</code>
		 */
		protected SourceViewer createViewer(Composite parent) {
			SourceViewer viewer = new SourceViewer(parent, null, null, false,
					SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			SourceViewerConfiguration configuration = new SourceViewerConfiguration() {
				public IContentAssistant getContentAssistant(
						ISourceViewer sourceViewer) {

					ContentAssistant assistant = new ContentAssistant();
					assistant.enableAutoActivation(true);
					assistant.enableAutoInsert(true);
					assistant.setContentAssistProcessor(fTemplateProcessor,
							IDocument.DEFAULT_CONTENT_TYPE);
					return assistant;
				}
			};
			viewer.configure(configuration);
			return viewer;
		}

		private void initializeActions() {
			final ArrayList handlerActivations = new ArrayList(3);
			final IHandlerService handlerService = (IHandlerService) PlatformUI
					.getWorkbench().getAdapter(IHandlerService.class);
			getShell().addDisposeListener(new DisposeListener() {

				public void widgetDisposed(DisposeEvent e) {
					handlerService.deactivateHandlers(handlerActivations);
				}
			});

			ActiveShellExpression expression = new ActiveShellExpression(
					fPatternEditor.getControl().getShell());

			TextViewerAction action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.UNDO);
			action.setText("Undo");
			fGlobalActions.put(ITextEditorActionConstants.UNDO, action);
			handlerActivations.add(handlerService.activateHandler(
					IWorkbenchActionDefinitionIds.UNDO, new ActionHandler(
							action), expression));

			action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.REDO);
			action.setText("Redo");
			fGlobalActions.put(ITextEditorActionConstants.REDO, action);
			handlerActivations.add(handlerService.activateHandler(
					IWorkbenchActionDefinitionIds.REDO, new ActionHandler(
							action), expression));

			action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.CUT);
			action.setText("Cut");
			fGlobalActions.put(ITextEditorActionConstants.CUT, action);

			action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.COPY);
			action.setText("Copy");
			fGlobalActions.put(ITextEditorActionConstants.COPY, action);

			action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.PASTE);
			action.setText("Paste");
			fGlobalActions.put(ITextEditorActionConstants.PASTE, action);

			action = new TextViewerAction(fPatternEditor,
					ITextOperationTarget.SELECT_ALL);
			action.setText("Select All");
			fGlobalActions.put(ITextEditorActionConstants.SELECT_ALL, action);

			action = new TextViewerAction(fPatternEditor,
					ISourceViewer.CONTENTASSIST_PROPOSALS);
			action.setText("Content Assist");
			fGlobalActions.put("ContentAssistProposal", action); //$NON-NLS-1$
			handlerActivations.add(handlerService.activateHandler(
					ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS,
					new ActionHandler(action), expression));

			fSelectionActions.add(ITextEditorActionConstants.CUT);
			fSelectionActions.add(ITextEditorActionConstants.COPY);
			fSelectionActions.add(ITextEditorActionConstants.PASTE);

			// create context menu
			MenuManager manager = new MenuManager(null, null);
			manager.setRemoveAllWhenShown(true);
			manager.addMenuListener(new IMenuListener() {
				public void menuAboutToShow(IMenuManager mgr) {
					fillContextMenu(mgr);
				}
			});

			StyledText text = fPatternEditor.getTextWidget();
			Menu menu = manager.createContextMenu(text);
			text.setMenu(menu);
		}

		private void fillContextMenu(IMenuManager menu) {
			menu.add(new GroupMarker(ITextEditorActionConstants.GROUP_UNDO));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_UNDO,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.UNDO));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_UNDO,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.REDO));

			menu.add(new Separator(ITextEditorActionConstants.GROUP_EDIT));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.CUT));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.COPY));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.PASTE));
			menu.appendToGroup(ITextEditorActionConstants.GROUP_EDIT,
					(IAction) fGlobalActions
							.get(ITextEditorActionConstants.SELECT_ALL));

			menu.add(new Separator("templates")); //$NON-NLS-1$
//			menu
//					.appendToGroup(
//							"templates", (IAction) fGlobalActions.get("ContentAssistProposal")); //$NON-NLS-1$ //$NON-NLS-2$
		}

		private void updateSelectionDependentActions() {
			Iterator iterator = fSelectionActions.iterator();
			while (iterator.hasNext())
				updateAction((String) iterator.next());
		}

		private void updateAction(String actionId) {
			IAction action = (IAction) fGlobalActions.get(actionId);
			if (action instanceof IUpdate)
				((IUpdate) action).update();
		}

		private int getIndex(String contextid) {

			if (contextid == null)
				return -1;

			for (int i = 0; i < fContextTypes.length; i++) {
				if (contextid.equals(fContextTypes[i][0])) {
					return i;
				}
			}
			return -1;
		}

		private void updateButtons() {
			CStatusInfo status;

			boolean valid = fNameText == null
					|| fNameText.getText().trim().length() != 0;
			if (!valid) {
				status = new CStatusInfo();
				if (!fSuppressError) {
					status.setError("Template name cannot be empty. ");
				}
			} else {
				status = fValidationStatus;
			}
			updateStatus(status);
		}

		/*
		 * @since 3.1
		 */
		protected void okPressed() {
			String name = fNameText == null ? fOriginalTemplate.getName()
					: fNameText.getText();
			boolean isAutoInsertable = fAutoInsertCheckbox != null
					&& fAutoInsertCheckbox.getSelection();
			fNewTemplate = new Template(name, fDescriptionText.getText(),
					getContextId(), fPatternEditor.getDocument().get(),
					isAutoInsertable);
			super.okPressed();
		}

		/**
		 * Returns the created template.
		 * 
		 * @return the created template
		 * @since 3.1
		 */
		public Template getTemplate() {
			return fNewTemplate;
		}

		/**
		 * Returns the content assist processor that suggests template
		 * variables.
		 * 
		 * @return the processor to suggest variables
		 * @since 3.3
		 */
		protected IContentAssistProcessor getTemplateProcessor() {
			return fTemplateProcessor;
		}

		/*
		 * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsSettings()
		 * @since 3.2
		 */
		protected IDialogSettings getDialogBoundsSettings() {
			String sectionName = getClass().getName() + "_dialogBounds"; //$NON-NLS-1$
			IDialogSettings settings = TextEditorPlugin.getDefault()
					.getDialogSettings();
			IDialogSettings section = settings.getSection(sectionName);
			if (section == null)
				section = settings.addNewSection(sectionName);
			return section;
		}

	}

	class TemplateContentProvider implements IStructuredContentProvider {

		TemplateContentProvider() {
		}

		public Object[] getElements(Object input) {
			return fStore.getTemplateData(false);
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			fStore = (TemplateStore) newInput;
		}

		public void dispose() {
			fStore = null;
		}

		private TemplateStore fStore;
	}

	/**
	 * Label provider for templates.
	 */
	private class TemplateLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		/*
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
		 *      int)
		 */
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/*
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
		 *      int)
		 */
		public String getColumnText(Object element, int columnIndex) {
			TemplatePersistenceData data = (TemplatePersistenceData) element;
			Template template = data.getTemplate();

			switch (columnIndex) {
			case 0:
				return template.getName();
				// case 1:
				// TemplateContextType type = fContextTypeRegistry
				// .getContextType(template.getContextTypeId());
				// if (type != null)
				// return type.getName();
				// return template.getContextTypeId();
			case 1:
				return template.getDescription();
				// case 3:
				// return template.isAutoInsertable() ? "on" : ""; //$NON-NLS-1$
			default:
				return ""; //$NON-NLS-1$
			}
		}
	}

	/** Qualified key for formatter preference. */
	private static final String DEFAULT_FORMATTER_PREFERENCE_KEY = "org.eclipse.ui.texteditor.templates.preferences.format_templates"; //$NON-NLS-1$

	/** The table presenting the templates. */
	private CheckboxTableViewer fTableViewer;

	/* buttons */
	private Button fAddButton;
	private Button fEditButton;
	private Button fImportButton;
	private Button fExportButton;
	private Button fRemoveButton;
	private Button fRestoreButton;
	private Button fRevertButton;

	/** The viewer displays the pattern of selected template. */
	private SourceViewer fPatternViewer;
	/** Format checkbox. This gets conditionally added. */
	private Button fFormatButton;
	/** The store for our templates. */
	private TemplateStore fTemplateStore;
	/** The context type registry. */
	private ContextTypeRegistry fContextTypeRegistry;

	/**
	 * Creates a new template preference page.
	 */
	protected CTemplatePreferencePage() {
		super();

		setDescription("Edit templates: ");
	}

	/**
	 * Returns the template store.
	 * 
	 * @return the template store
	 */
	public TemplateStore getTemplateStore() {
		return fTemplateStore;
	}

	/**
	 * Returns the context type registry.
	 * 
	 * @return the context type registry
	 */
	public ContextTypeRegistry getContextTypeRegistry() {
		return fContextTypeRegistry;
	}

	/**
	 * Sets the template store.
	 * 
	 * @param store
	 *            the new template store
	 */
	public void setTemplateStore(TemplateStore store) {
		fTemplateStore = store;
	}

	/**
	 * Sets the context type registry.
	 * 
	 * @param registry
	 *            the new context type registry
	 */
	public void setContextTypeRegistry(ContextTypeRegistry registry) {
		fContextTypeRegistry = registry;
	}

	/*
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
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

		Composite tableComposite = new Composite(innerParent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 360;
		data.heightHint = convertHeightInCharsToPixels(10);
		tableComposite.setLayoutData(data);

		ColumnLayout columnLayout = new ColumnLayout();
		tableComposite.setLayout(columnLayout);
		Table table = new Table(tableComposite, SWT.CHECK | SWT.BORDER
				| SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GC gc = new GC(getShell());
		gc.setFont(JFaceResources.getDialogFont());

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Name");
		int minWidth = computeMinimumColumnWidth(gc, "Name");
		columnLayout.addColumnData(new ColumnWeightData(2, minWidth, true));

//		TableColumn column2 = new TableColumn(table, SWT.NONE);
//		column2.setText("Context");
//		minWidth = computeMinimumContextColumnWidth(gc);
//		columnLayout.addColumnData(new ColumnWeightData(1, minWidth, true));

		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("File Name");
		minWidth = computeMinimumColumnWidth(gc, "File Name");
		columnLayout.addColumnData(new ColumnWeightData(3, minWidth, true));

		// TableColumn column4 = new TableColumn(table, SWT.NONE);
		// column4.setAlignment(SWT.CENTER);
		// column4.setText("Auto Insert");
		// minWidth = computeMinimumColumnWidth(gc, "Auto Insert");
		// minWidth = Math.max(minWidth, computeMinimumColumnWidth(gc, "on"));
		// columnLayout.addColumnData(new ColumnPixelData(minWidth, false,
		// false));

		gc.dispose();

		fTableViewer = new CheckboxTableViewer(table);
		fTableViewer.setLabelProvider(new TemplateLabelProvider());
		fTableViewer.setContentProvider(new TemplateContentProvider());

		fTableViewer.setComparator(new ViewerComparator() {
			public int compare(Viewer viewer, Object object1, Object object2) {
				if ((object1 instanceof TemplatePersistenceData)
						&& (object2 instanceof TemplatePersistenceData)) {
					Template left = ((TemplatePersistenceData) object1)
							.getTemplate();
					Template right = ((TemplatePersistenceData) object2)
							.getTemplate();
					int result = Collator.getInstance().compare(left.getName(),
							right.getName());
					if (result != 0)
						return result;
					return Collator.getInstance().compare(
							left.getDescription(), right.getDescription());
				}
				return super.compare(viewer, object1, object2);
			}

			public boolean isSorterProperty(Object element, String property) {
				return true;
			}
		});

		fTableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent e) {
				edit();
			}
		});

		fTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent e) {
						selectionChanged1();
					}
				});

		fTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				TemplatePersistenceData d = (TemplatePersistenceData) event
						.getElement();
				d.setEnabled(event.getChecked());
			}
		});

		Composite buttons = new Composite(innerParent, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);

		fAddButton = new Button(buttons, SWT.PUSH);
		fAddButton.setText("New...");
		fAddButton.setLayoutData(getButtonGridData(fAddButton));
		fAddButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				add();
			}
		});
		fAddButton.setEnabled(false);

		fEditButton = new Button(buttons, SWT.PUSH);
		fEditButton.setText("Edit...");
		fEditButton.setLayoutData(getButtonGridData(fEditButton));
		fEditButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				edit();
			}
		});

		fRemoveButton = new Button(buttons, SWT.PUSH);
		fRemoveButton.setText("Remove");
		fRemoveButton.setLayoutData(getButtonGridData(fRemoveButton));
		fRemoveButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				remove();
			}
		});
		fRemoveButton.setEnabled(false);

		createSeparator(buttons);

		fRestoreButton = new Button(buttons, SWT.PUSH);
		fRestoreButton.setText("Restore Removed");
		fRestoreButton.setLayoutData(getButtonGridData(fRestoreButton));
		fRestoreButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				restoreDeleted();
			}
		});

		fRevertButton = new Button(buttons, SWT.PUSH);
		fRevertButton.setText("Revert to Default");
		fRevertButton.setLayoutData(getButtonGridData(fRevertButton));
		fRevertButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				revert();
			}
		});

		createSeparator(buttons);

		fImportButton = new Button(buttons, SWT.PUSH);
		fImportButton.setText("Import...");
		fImportButton.setLayoutData(getButtonGridData(fImportButton));
		fImportButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				import_();
			}
		});
		fImportButton.setEnabled(false);

		fExportButton = new Button(buttons, SWT.PUSH);
		fExportButton.setText("Export...");
		fExportButton.setLayoutData(getButtonGridData(fExportButton));
		fExportButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				export();
			}
		});
		fExportButton.setEnabled(false);

		fPatternViewer = doCreateViewer(parent);

		if (isShowFormatterSetting()) {
			fFormatButton = new Button(parent, SWT.CHECK);
			fFormatButton.setText("Code Format");
			GridData gd1 = new GridData();
			gd1.horizontalSpan = 2;
			fFormatButton.setLayoutData(gd1);
			fFormatButton.setSelection(getPreferenceStore().getBoolean(
					getFormatterPreferenceKey()));
		}

		fTableViewer.setInput(fTemplateStore);
		fTableViewer.setAllChecked(false);
		fTableViewer.setCheckedElements(getEnabledTemplates());

		updateButtons();
		Dialog.applyDialogFont(parent);
		innerParent.layout();

		return parent;
	}

	/*
	 * @since 3.2
	 */
	private int computeMinimumColumnWidth(GC gc, String string) {
		return gc.stringExtent(string).x + 10; // pad 10 to accommodate table
		// header trimmings
	}

	/*
	 * @since 3.4
	 */
	private int computeMinimumContextColumnWidth(GC gc) {
		int width = gc.stringExtent("Context").x;
		Iterator iter = getContextTypeRegistry().contextTypes();
		while (iter.hasNext()) {
			TemplateContextType contextType = (TemplateContextType) iter.next();
			width = Math.max(width, gc.stringExtent(contextType.getName()).x);
		}
		return width;
	}

	/**
	 * Creates a separator between buttons.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a separator
	 */
	private Label createSeparator(Composite parent) {
		Label separator = new Label(parent, SWT.NONE);
		separator.setVisible(false);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.heightHint = 4;
		separator.setLayoutData(gd);
		return separator;
	}

	/**
	 * Returns whether the formatter preference checkbox should be shown.
	 * 
	 * @return <code>true</code> if the formatter preference checkbox should
	 *         be shown, <code>false</code> otherwise
	 */
	protected boolean isShowFormatterSetting() {
		return true;
	}

	private TemplatePersistenceData[] getEnabledTemplates() {
		List enabled = new ArrayList();
		TemplatePersistenceData[] datas = fTemplateStore.getTemplateData(false);
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].isEnabled())
				enabled.add(datas[i]);
		}
		return (TemplatePersistenceData[]) enabled
				.toArray(new TemplatePersistenceData[enabled.size()]);
	}

	private SourceViewer doCreateViewer(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Preview");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		SourceViewer viewer = createViewer(parent);

		viewer.setEditable(false);
		Cursor arrowCursor = viewer.getTextWidget().getDisplay()
				.getSystemCursor(SWT.CURSOR_ARROW);
		viewer.getTextWidget().setCursor(arrowCursor);
		viewer.getTextWidget().setCaret(null);

		Control control = viewer.getControl();
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		data.heightHint = convertHeightInCharsToPixels(5);
		control.setLayoutData(data);

		return viewer;
	}

	/**
	 * Creates, configures and returns a source viewer to present the template
	 * pattern on the preference page. Clients may override to provide a custom
	 * source viewer featuring e.g. syntax coloring.
	 * 
	 * @param parent
	 *            the parent control
	 * @return a configured source viewer
	 */
	protected SourceViewer createViewer(Composite parent) {
		SourceViewer viewer = new SourceViewer(parent, null, null, false,
				SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		SourceViewerConfiguration configuration = new SourceViewerConfiguration();
		viewer.configure(configuration);
		IDocument document = new Document();
		viewer.setDocument(document);
		return viewer;
	}

	/**
	 * Return the grid data for the button.
	 * 
	 * @param button
	 *            the button
	 * @return the grid data
	 */
	private static GridData getButtonGridData(Button button) {
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		// data.widthHint= SWTUtil.getButtonWidthHint(button);
		// data.heightHint= SWTUtil.getButtonHeightHint(button);

		return data;
	}

	private void selectionChanged1() {
		updateViewerInput();
		updateButtons();
	}

	/**
	 * Updates the pattern viewer.
	 */
	protected void updateViewerInput() {
		IStructuredSelection selection = (IStructuredSelection) fTableViewer
				.getSelection();

		if (selection.size() == 1) {
			TemplatePersistenceData data = (TemplatePersistenceData) selection
					.getFirstElement();
			Template template = data.getTemplate();
			fPatternViewer.getDocument().set(template.getPattern());
		} else {
			fPatternViewer.getDocument().set(""); //$NON-NLS-1$
		}
	}

	/**
	 * Updates the buttons.
	 */
	protected void updateButtons() {
		IStructuredSelection selection = (IStructuredSelection) fTableViewer
				.getSelection();
		int selectionCount = selection.size();
		int itemCount = fTableViewer.getTable().getItemCount();
		boolean canRestore = fTemplateStore.getTemplateData(true).length != fTemplateStore
				.getTemplateData(false).length;
		boolean canRevert = false;
		for (Iterator it = selection.iterator(); it.hasNext();) {
			TemplatePersistenceData data = (TemplatePersistenceData) it.next();
			if (data.isModified()) {
				canRevert = true;
				break;
			}
		}

		fEditButton.setEnabled(selectionCount == 1);
		fExportButton.setEnabled(false);
		fRemoveButton.setEnabled(false);
		fRestoreButton.setEnabled(canRestore);
		fRevertButton.setEnabled(canRevert);
	}

	private void add() {

		Iterator it = fContextTypeRegistry.contextTypes();
		if (it.hasNext()) {
			Template template = new Template(
					"", "", ((TemplateContextType) it.next()).getId(), "", true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			Template newTemplate = editTemplate(template, false, true);
			if (newTemplate != null) {
				TemplatePersistenceData data = new TemplatePersistenceData(
						newTemplate, true);
				fTemplateStore.add(data);
				fTableViewer.refresh();
				fTableViewer.setChecked(data, true);
				fTableViewer.setSelection(new StructuredSelection(data));
			}
		}
	}

	/**
	 * Creates the edit dialog. Subclasses may override this method to provide a
	 * custom dialog.
	 * 
	 * @param template
	 *            the template being edited
	 * @param edit
	 *            whether the dialog should be editable
	 * @param isNameModifiable
	 *            whether the template name may be modified
	 * @return an <code>EditTemplateDialog</code> which will be opened.
	 * @deprecated not called any longer as of 3.1 - use
	 *             {@link #editTemplate(Template, boolean, boolean)}
	 */
	protected Dialog createTemplateEditDialog(Template template, boolean edit,
			boolean isNameModifiable) {
		return new EditTemplateDialog(getShell(), template, edit,
				isNameModifiable, fContextTypeRegistry);
	}

	/**
	 * Creates the edit dialog. Subclasses may override this method to provide a
	 * custom dialog.
	 * 
	 * @param template
	 *            the template being edited
	 * @param edit
	 *            whether the dialog should be editable
	 * @param isNameModifiable
	 *            whether the template name may be modified
	 * @return the created or modified template, or <code>null</code> if the
	 *         edition failed
	 * @since 3.1
	 */
	protected Template editTemplate(Template template, boolean edit,
			boolean isNameModifiable) {
		EditTemplateDialog dialog = new EditTemplateDialog(getShell(),
				template, edit, isNameModifiable, fContextTypeRegistry);
		if (dialog.open() == Window.OK) {
			return dialog.getTemplate();
		}
		return null;
	}

	private void edit() {
		IStructuredSelection selection = (IStructuredSelection) fTableViewer
				.getSelection();

		Object[] objects = selection.toArray();
		if ((objects == null) || (objects.length != 1))
			return;

		TemplatePersistenceData data = (TemplatePersistenceData) selection
				.getFirstElement();
		edit(data);
	}

	private void edit(TemplatePersistenceData data) {
		Template oldTemplate = data.getTemplate();
		Template newTemplate = editTemplate(new Template(oldTemplate), true,
				true);
		if (newTemplate != null) {

			if (!newTemplate.getName().equals(oldTemplate.getName())
					&& MessageDialog.openQuestion(getShell(),
							"Create Template", "Create Template")) {
				data = new TemplatePersistenceData(newTemplate, true);
				fTemplateStore.add(data);
				fTableViewer.refresh();
			} else {
				data.setTemplate(newTemplate);
				fTableViewer.refresh(data);
			}
			selectionChanged1();
			fTableViewer.setChecked(data, data.isEnabled());
			fTableViewer.setSelection(new StructuredSelection(data));
		}
	}

	private void import_() {
	}

	private void export() {
	}

	private void export(TemplatePersistenceData[] templates) {
	}

	private boolean confirmOverwrite(File file) {
		return MessageDialog.openQuestion(getShell(), "Overwrite? ", NLSUtility
				.format("Do you want to overwrite? ", file.getAbsolutePath()));
	}

	private void remove() {
	}

	private void restoreDeleted() {
		fTemplateStore.restoreDeleted();
		fTableViewer.refresh();
		fTableViewer.setCheckedElements(getEnabledTemplates());
		updateButtons();
	}

	private void revert() {
		IStructuredSelection selection = (IStructuredSelection) fTableViewer
				.getSelection();

		Iterator elements = selection.iterator();
		while (elements.hasNext()) {
			TemplatePersistenceData data = (TemplatePersistenceData) elements
					.next();
			data.revert();
			fTableViewer.setChecked(data, data.isEnabled());
		}

		selectionChanged1();
		fTableViewer.refresh();
	}

	/*
	 * @see Control#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			setTitle("Templates");
	}

	/*
	 * @see PreferencePage#performDefaults()
	 */
	protected void performDefaults() {
		if (isShowFormatterSetting()) {
			IPreferenceStore prefs = getPreferenceStore();
			fFormatButton.setSelection(prefs
					.getDefaultBoolean(getFormatterPreferenceKey()));
		}

		// fTemplateStore.restoreDefaults(false);
		fTemplateStore.restoreDefaults();

		// refresh
		fTableViewer.refresh();
		fTableViewer.setAllChecked(false);
		fTableViewer.setCheckedElements(getEnabledTemplates());
	}

	/*
	 * @see PreferencePage#performOk()
	 */
	public boolean performOk() {
		if (isShowFormatterSetting()) {
			IPreferenceStore prefs = getPreferenceStore();
			prefs.setValue(getFormatterPreferenceKey(), fFormatButton
					.getSelection());
		}

		try {
			fTemplateStore.save();
		} catch (IOException e) {
			openWriteErrorDialog();
		}

		return super.performOk();
	}

	/**
	 * Returns the key to use for the formatter preference.
	 * 
	 * @return the formatter preference key
	 */
	protected String getFormatterPreferenceKey() {
		return DEFAULT_FORMATTER_PREFERENCE_KEY;
	}

	/*
	 * @see PreferencePage#performCancel()
	 */
	public boolean performCancel() {
		try {
			fTemplateStore.load();
		} catch (IOException e) {
			openReadErrorDialog();
			return false;
		}
		return super.performCancel();
	}

	/*
	 * @since 3.2
	 */
	private void openReadErrorDialog() {
		String title = "Read error. ";
		String message = "Read error";
		MessageDialog.openError(getShell(), title, message);
	}

	/*
	 * @since 3.2
	 */
	private void openWriteErrorDialog() {
		String title = "Write error";
		String message = "Write error";
		MessageDialog.openError(getShell(), title, message);
	}

	protected SourceViewer getViewer() {
		return fPatternViewer;
	}

	protected TableViewer getTableViewer() {
		return fTableViewer;
	}
}
