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


package com.nokia.s60tools.stif.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * Class representing Test Case Wizard page where choice of parameters is done
 */
public class SkeletonSetParametersPage extends WizardPage implements IPageChangedListener {
	
	/** Items that were choosen by End User in Carbide workspace*/
	private IStructuredSelection selection;
	private List methodList;
	private List setMethodList;
	private Table paramTable;
	
	
	/**
	 * @param selection
	 */
	public SkeletonSetParametersPage(IStructuredSelection selection) {
		super("skeletonSetParametersPage");
		System.out.println("start of SkeletonSetParametersPage constructor");
		this.selection = selection;
		setTitle("Select method(s) to be tested");
		setDescription("Add more methods to the list or click Next to continue.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@SuppressWarnings("unchecked")
	public void createControl(Composite parent) {
		//main composite and layout
		Composite composite = new Composite(parent, SWT.NONE);
		//gridlayout for wrapper composite
		GridLayout gl = new GridLayout(3, false);
		composite.setLayout(gl);
		//composites for each part
		Composite compositeLeft = new Composite(composite, SWT.NONE);
		Composite compositeCenter = new Composite(composite, SWT.NONE);
		Composite compositeRight = new Composite(composite, SWT.NONE);
		
		//gridlayout for inner composites
		gl = new GridLayout(1, false);
		compositeLeft.setLayout(gl);
		compositeCenter.setLayout(gl);
		compositeRight.setLayout(gl);
		
		//components to left part
		Label label1 = new Label(compositeLeft, SWT.NONE);
	    label1.setText("Available methods");
		
		methodList = new List(compositeLeft, SWT.SINGLE|SWT.BORDER);
		Iterator<IMethodDeclaration> iter = selection.iterator();
		int i = 0;
		while (iter.hasNext()) {
			IMethodDeclaration func = iter.next();
			methodList.add(func.getElementName(), i);
			methodList.setData("" + i, func);
			i++;
		}
		methodList.setSelection(0);
		//listener to refresh parameter table for selected method
		methodList.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				RefreshParamTableNew();
			}

		});
		
		//components to center part
		//button to add function calls to the list
		Button button = new Button(compositeCenter, SWT.NONE);
		button.setText("   Add ->   ");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				handleAddNewTestCase();
			}
		
		});
		//button to remove function calls from the list
		Button remButton = new Button(compositeCenter, SWT.NONE);
		remButton.setText("<- Remove");
		remButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				handleRemoveTestCase();
			}
		
		});

		
		//components to right part
		Label label2 = new Label(compositeRight, SWT.NONE);
	    label2.setText("Chosen methods");
		//list to store added function calls
		setMethodList = new List(compositeRight, SWT.SINGLE|SWT.BORDER);
		
		//listener to refresh parameter table to show set parameters
		setMethodList.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				RefreshParamTableSet();
			}
		});
	
		//add fill layout for the left and right composites and the list components
		Label label3 = new Label(composite, SWT.SINGLE);
		label3.setText("Method parameters");
		
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true);
		compositeLeft.setLayoutData(gd);
		compositeRight.setLayoutData(gd);
		methodList.setLayoutData(gd);
		setMethodList.setLayoutData(gd);
		
		//add vertical fill for the center composite
		gd = new GridData(SWT.FILL,SWT.CENTER,false,false);
		compositeCenter.setLayoutData(gd);
		
		//center the buttons
		gd = new GridData(SWT.CENTER,SWT.CENTER,false,false);
		button.setLayoutData(gd);
		
		//create table to show function parametes
		createParamTable(composite);
		
		dialogChanged();
		setControl(composite);
		
        /** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForCaseWizard);
	}
	
	/**
	 * Adds selected method to the test case list with 
	 * user defined parameters
	 */
	private void handleAddNewTestCase() {
		//get selected method 
		IMethodDeclaration func = (IMethodDeclaration)methodList.getData("" + methodList.getSelectionIndex());
		
		int count = setMethodList.getItemCount();
		setMethodList.add(func.getElementName(), count);
		setMethodList.setData("" + count, func);
		/* Get user defined parameter values from table and add them
		   as string array to the list data for later retrieval */
		
		String[][] paramList = new String[paramTable.getItemCount()][2];
		for(int i = 0; i < paramTable.getItemCount(); i++) {
			paramList[i][0] = paramTable.getItem(i).getText(0);
			paramList[i][1] = paramTable.getItem(i).getText(2);
		}
		setMethodList.setData("params" + count, paramList);
		paramTable.removeAll();
		methodList.deselectAll();
				
		dialogChanged();
	}
	
	/**
	 * Method handles a situation when test case is removed
	 */
	private void handleRemoveTestCase()
	{
		//get selected method 
		int index = setMethodList.getSelectionIndex();
		if(index != -1)
		{
			setMethodList.remove( setMethodList.getSelectionIndex() );
			paramTable.removeAll();
			setMethodList.deselectAll();
			dialogChanged();
		}
	}
	
	/**
	 * Creates Table widget to display selected method parameters 
	 * @param parent
	 * 			Parent composite
	 */
	private void createParamTable(Composite parent) {
		paramTable = new Table(parent, SWT.SINGLE|SWT.BORDER);
		paramTable.setLinesVisible(true);
		paramTable.setHeaderVisible(true);
		TableColumn column = new TableColumn(paramTable, SWT.NONE);
		column.setText("Name");
		column = new TableColumn(paramTable, SWT.NONE);
		column.setText("Type");
		column = new TableColumn(paramTable, SWT.NONE);
		column.setText("Value");
		
		//set parameter values to table
		RefreshParamTableNew();
		
		//layout all colums to the same width
		TableLayout tbl = new TableLayout();
		tbl.addColumnData(new ColumnWeightData(100 / paramTable.getColumnCount(),false));
		tbl.addColumnData(new ColumnWeightData(100 / paramTable.getColumnCount(),false));
		tbl.addColumnData(new ColumnWeightData(100 / paramTable.getColumnCount(),false));

		paramTable.setLayout(tbl);
		
		//add layout data to the table to fill bottom area
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true);
		gd.horizontalSpan = 3;
		paramTable.setLayoutData(gd);
		
		//create editor to input parameter values
		final TableEditor editor = new TableEditor (paramTable);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		paramTable.addListener (SWT.MouseDown, new Listener () {
			public void handleEvent (Event event) {
				//get the point where mouse was clicked
				Rectangle clientArea = paramTable.getClientArea ();
				Point pt = new Point (event.x, event.y);
				int index = paramTable.getTopIndex ();
				//go through table items
				while (index < paramTable.getItemCount ()) {
					boolean visible = false;
					final TableItem item = paramTable.getItem (index);
					for (int i=0; i<paramTable.getColumnCount (); i++) {
						//currently only allow changes to third column
						if (i == 2) {
							//check if mouse was clicked on this item
							Rectangle rect = item.getBounds (i);
							if (rect.contains (pt)) {
								//create text widget and listener for input to update item text 
								final int column = i;
								final Text text = new Text (paramTable, SWT.NONE);
								Listener textListener = new Listener () {
									public void handleEvent (final Event e) {
										switch (e.type) {
											case SWT.FocusOut:
												item.setText (column, text.getText ());
												text.dispose ();
												break;
											case SWT.Traverse:
												switch (e.detail) {
													case SWT.TRAVERSE_RETURN:
														item.setText (column, text.getText ());
														//FALL THROUGH
													case SWT.TRAVERSE_ESCAPE:
														text.dispose ();
														e.doit = false;
												}
												break;
										}
									}
								};
								text.addListener (SWT.FocusOut, textListener);
								text.addListener (SWT.Traverse, textListener);
								editor.setEditor (text, item, i);
								text.setText (item.getText (i));
								text.selectAll ();
								text.setFocus ();
								return;
							}
							if (!visible && rect.intersects (clientArea)) {
								visible = true;
							}
						}
					}
					if (!visible) return;
					index++;
				}
			}
		});
	}
	
	/**
	 * Refreshes parameter table when user selects method to be added 
	 * from the left hand side list
	 */
	private void RefreshParamTableNew(){
		//get function from listbox meta data
		IMethodDeclaration func = (IMethodDeclaration)methodList.getData("" + methodList.getSelectionIndex());
		paramTable.removeAll();
		try {
			//parse parameter names
			String source = func.getSource();
			String allparams = source.substring(source.indexOf('(') + 1, source.lastIndexOf(')'));
			String[] paramArray = allparams.split(",");
			//Go through parameters and add to param table
			for (int i = 0; i<func.getParameterTypes().length; i++) {
				TableItem item = new TableItem(paramTable,SWT.NONE);
				String param = paramArray[i];
				if (param.indexOf('=') != -1) {
					param = paramArray[i].substring(0, paramArray[i].indexOf('=')).trim();
				} else {
					param = param.trim();
				}
				item.setText(0, param.substring(param.lastIndexOf(' ')).trim());
				item.setText(1, func.getParameterTypes()[i]);
				item.setText(2, func.getParameterInitializer(i));
				
			}
		} catch (Exception e) {
			MessageBox box = new MessageBox(new Shell(),SWT.ICON_ERROR);
			box.setText("Error!");
			box.open();
		}
	}
	
	/**
	 * Refreshes parameter table when user selects method 
	 * that has allready been added from the right hand side list
	 */
	private void RefreshParamTableSet(){
		//get function from listbox meta data
		IMethodDeclaration func = (IMethodDeclaration)setMethodList.getData("" + setMethodList.getSelectionIndex());
		String[][] paramsList = (String[][])setMethodList.getData("params" + setMethodList.getSelectionIndex());
		paramTable.removeAll();
		try {
			//Go through parameters and add to param table
			for (int i = 0; i<func.getParameterTypes().length; i++) {
				TableItem item = new TableItem(paramTable,SWT.NONE);
				item.setText(0, paramsList[i][0]);
				item.setText(1, func.getParameterTypes()[i]);
				item.setText(2, paramsList[i][1]);
			}
		} catch (Exception e) {
			MessageBox box = new MessageBox(new Shell(),SWT.ICON_ERROR);
			box.setText("Error!");
			box.setMessage(e.getMessage());
			box.open();
		}
	}
	
	/**
	 * Validates user input
	 */
	private void dialogChanged() {
//		check that test templates path is set at preferences
		String templatePath = "";
		if(selection instanceof IStructuredSelection)
		{
			IStructuredSelection sel = (IStructuredSelection) selection;
			Object selItem = sel.getFirstElement();
			if (selItem instanceof IMethodDeclaration) {
	            IMethodDeclaration metDec = ((IMethodDeclaration)selItem);
	            IProject proj = metDec.getCProject().getProject();
	            
	            try{
	            	templatePath = proj.getPersistentProperty(new QualifiedName("project_settings", "TestModuleTemplates path"));
	            }
	            catch(CoreException ex){
	            	ex.printStackTrace();
	            }
			}
		}
		
		if (templatePath.equals("")) {
			updateStatus("You have to set the path to STIF TestModuleTemplates folder" +
					" in preferences before continuing", WizardPage.ERROR);
			return;
		}
		File file = new File(templatePath);
        if (!file.exists()) {
                updateStatus("STIF test module templates path does not exist!",
                		WizardPage.ERROR);
                return;
        }

       	if (setMethodList.getItemCount() < 1) {
   			updateStatus("Add at least one method to the list", WizardPage.NONE);
    			return;
   		}
       	updateStatus(null, WizardPage.NONE);        			
	}

	/**
	 * Sets message to wizard dialog, if null, sets page to completed stage
	 * 
	 * @param message
	 * @param type
	 */
	private void updateStatus(String message, int type) {
		setMessage(message, type);
		setPageComplete(message == null);
	}
	
	/**
	 * Returns methods that are added to the test case list
	 * @return IMethodDeclaration[] methods
	 */
	public IMethodDeclaration[] getTestCaseMethods() {
		IMethodDeclaration[] methods = new IMethodDeclaration[setMethodList.getItemCount()];
		for (int i = 0; i < setMethodList.getItemCount(); i++) {
			methods[i] = (IMethodDeclaration)setMethodList.getData("" + i);
			
		}
		return methods;
		
	}
	
	/**
	 * Returns methods parameter information in a collection.
	 * Each collection element contains parameter list for single method 
	 * (corresponding index in getTestCaseMethods())
	 * 
	 * Parameters are in String array:
	 * --------------------------
	 * | parameter name | value |
	 * |------------------------|
	 * | aParameter     |  4    |
	 * | aNotherParam   |  9    |
	 * --------------------------
	 * 
	 * @return Collection<String[][]> params
	 */
	public Collection<String[][]> getTestCaseMethodParams() {
		Collection<String[][]> params = new ArrayList<String[][]>();
		for (int i = 0; i < setMethodList.getItemCount(); i++) {
			params.add((String[][])setMethodList.getData("params" + i));
		}
		return params;
	}
	
	/**
	 * Page changed event handler.
	 * When the new page is of SkeletonSetParametersPage type, we set focus to methodList. 
	 */
	public void pageChanged(PageChangedEvent ev) {
		if(ev.getSelectedPage() instanceof SkeletonSetParametersPage){
			methodList.setFocus();
		}
	}
}
