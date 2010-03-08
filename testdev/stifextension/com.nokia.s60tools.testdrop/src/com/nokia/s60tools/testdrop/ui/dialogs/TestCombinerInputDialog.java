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

package com.nokia.s60tools.testdrop.ui.dialogs;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;

/**
 * A dialog for adding files needed by test combiner to test drop.
 * These files are basically dll-s and cfg files needed to run the whole
 * test.
 *
 */
public class TestCombinerInputDialog extends Dialog 
	implements SelectionListener {

	private Table inputElementsTable;
	private Group componentsGroup;
	private Button addComponentButton;
	private Button removeComponentButton;
	private Button removeAllComponentsButton;
	private Group variantGroup;
	private Button debugVersionButton;
	private Button releaseVersionButton;
	private ArrayList<String> chosenFiles;
	private IFile cfgFile;
	private boolean debugVariantOfTestCombinerActive = true;
	private boolean componentsGroupEnabled = true;
	
	private String SELECTED_FILES_STORE = "selected_files_names_store";
	private String SELECTED_FILES = "selected_files_names";
	
	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * 			shell needed to display dialog
	 * @param cfgFile
	 * 			cfg files that is the object of the action
	 * @param componentsGroupEnabled
	 * 			componentsGroup is active when this is hardware test drop. When emulator
	 * 			is going to be used no components are added.
	 */
	public TestCombinerInputDialog(Shell parentShell, IFile cfgFile, boolean componentsGroupEnabled) {
		super(parentShell);
		this.cfgFile = cfgFile;
		this.componentsGroupEnabled = componentsGroupEnabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell shell) {
		shell.setSize(600, 400);
		super.configureShell(shell);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		componentsGroup = new Group(composite, SWT.NONE);
		componentsGroup.setText("Test drop components");
		GridLayout grid = new GridLayout();
		grid.numColumns = 3;
		componentsGroup.setLayout(grid);
		componentsGroup.setEnabled(componentsGroupEnabled);
		
		GridData data = new GridData(GridData.FILL_BOTH);
	    data.horizontalSpan = 3;
	    componentsGroup.setLayoutData(data);
		
		inputElementsTable = new Table(componentsGroup, SWT.SINGLE|SWT.BORDER);
		inputElementsTable.setLinesVisible(true);
		inputElementsTable.setHeaderVisible(true);
		inputElementsTable.setEnabled(componentsGroupEnabled);
		TableColumn fileToAddColumn = new TableColumn(inputElementsTable, SWT.NONE);
		fileToAddColumn.setText("File");
		TableColumn pathToFileColumn = new TableColumn(inputElementsTable, SWT.NONE);
		pathToFileColumn.setText("Path");
		
		TableLayout tbl = new TableLayout();
		tbl.addColumnData(new ColumnWeightData(25, true));
		tbl.addColumnData(new ColumnWeightData(75, true));
		inputElementsTable.setLayout(tbl);
		
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true);
		gd.horizontalSpan = 3;
		inputElementsTable.setLayoutData(gd);
		
		initializeChosenFilesArray();
		
		addComponentButton = new Button(componentsGroup, SWT.NONE);
		addComponentButton.setText("Add...");
		addComponentButton.addSelectionListener(this);
		addComponentButton.setEnabled(componentsGroupEnabled);
		
		removeComponentButton = new Button(componentsGroup, SWT.NONE);
		removeComponentButton.setText("Remove");
		removeComponentButton.addSelectionListener(this);
		removeComponentButton.setEnabled(componentsGroupEnabled);
		
		removeAllComponentsButton = new Button(componentsGroup, SWT.NONE);
		removeAllComponentsButton.setText("Remove all");
		removeAllComponentsButton.addSelectionListener(this);
		removeAllComponentsButton.setEnabled(componentsGroupEnabled);
		
		variantGroup = new Group(composite, SWT.NONE);
		variantGroup.setText("Choose variant of Test Combiner");
		grid = new GridLayout();
		grid.numColumns = 1;
		variantGroup.setLayout(grid);
		
		data = new GridData(GridData.FILL_BOTH);
	    data.horizontalSpan = 3;
	    componentsGroup.setLayoutData(data);
		
		debugVersionButton = new Button(variantGroup, SWT.RADIO);
		debugVersionButton.setText("Debug");
		debugVersionButton.setSelection(true);
		debugVersionButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				debugVariantOfTestCombinerActive = true;
			}
		});
		
		releaseVersionButton = new Button(variantGroup, SWT.RADIO);
		releaseVersionButton.setText("Release");
		releaseVersionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				debugVariantOfTestCombinerActive = false;
			}
		});
		
		return composite;
	}
	
	/**
	 * Says what to do when a widget is selected from the dialog
	 */
	public void widgetSelected(SelectionEvent ev) {
		if (ev.getSource() == addComponentButton) {
			FileDialog fileDialog = new FileDialog(getShell(), SWT.MULTI);
			String result = fileDialog.open();
			if (result != null) {
				String[] selectedFiles = fileDialog.getFileNames();
				AddSelectedFiles(fileDialog.getFilterPath(), selectedFiles);
			}
		}
		else if (ev.getSource() == removeComponentButton) {
			RemoveSelectedFile(inputElementsTable.getSelectionIndices());
		}
		else if (ev.getSource() == removeAllComponentsButton) {
			RemoveAllSelectedFiles();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent ev) {
	}
	
	/**
	 * Adds selected files to the list of files that are to be added
	 * to the test drop
	 * 
	 * @param path
	 * 			path to directory from which the selected files come
	 * @param selectedFiles
	 * 			an array of chosen files names
	 */
	private void AddSelectedFiles(String path, String[] selectedFiles) {
		if (chosenFiles == null) {
			chosenFiles = new ArrayList<String>();
		}
		
		for (int i = 0; i < selectedFiles.length; i++) {
			boolean alreadyAdded = false;
			Iterator<String> chosenFilesIterator = chosenFiles.iterator();
			while (chosenFilesIterator.hasNext()) {
				if (chosenFilesIterator.next().equals(path + "\\" + selectedFiles[i])) {
					alreadyAdded = true;
					break;
				}
			}
			if (!alreadyAdded) {
				chosenFiles.add(path + "\\" + selectedFiles[i]);
				TableItem newItem = new TableItem(inputElementsTable, SWT.NONE);
				String fileName = selectedFiles[i];
				newItem.setText(new String[]{ fileName, path });
			}
		}
	}
	
	/**
	 * Removes the selected files from the list of files
	 * 
	 * @param items
	 * 			positions (on the list) of files to remove
	 */
	private void RemoveSelectedFile(int[] items) {
		for (int i = 0; i < items.length; i++) {
			String pathToRemove = inputElementsTable.getItem(items[i]).getText(1) + "\\" 
				+ inputElementsTable.getItem(items[i]).getText(0);
			if (chosenFiles != null) {
				Iterator<String> filesIterator = chosenFiles.iterator();
				int index = 0;
				while (filesIterator.hasNext()) {
					if (filesIterator.next().equals(pathToRemove)) {
						chosenFiles.remove(index);
						break;
					}
					index++;
				}
			}
		}
		inputElementsTable.remove(items);
	}
	
	/**
	 * Removes all files from the list of files
	 */
	private void RemoveAllSelectedFiles() {
		chosenFiles = null;
		inputElementsTable.removeAll();
	}
	
	/**
	 * Resturns array of names of selected files
	 * 
	 * @return
	 * 		an array of selected files (the whole list)
	 */
	public String[] getSelectedFiles() {
		if (chosenFiles == null) {
			return null;
		}
		return chosenFiles.toArray(new String[0]);
	}
	
	/**
	 * A method for checking which variant (debug/release) of Test Combiner was chosen by user.
	 * 
	 * @return
	 * 		true if debug variant is chosen
	 * 		false if release variant is chosen
	 */
	public boolean getIfDebugVariantChosen(){
		return debugVariantOfTestCombinerActive;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		if (cfgFile != null) {
			IResource resource = (IResource)cfgFile;
			String selectedFilesString = "";
			try {
				if (chosenFiles != null) {
					Iterator<String> filesIter = chosenFiles.iterator();
					while (filesIter.hasNext()) {
						selectedFilesString += filesIter.next() + ";";
					}
					if (selectedFilesString != null) {
						resource.setPersistentProperty( new QualifiedName(SELECTED_FILES_STORE, SELECTED_FILES ), 
								selectedFilesString);
					}
				}
				else {
					resource.setPersistentProperty( new QualifiedName(SELECTED_FILES_STORE, SELECTED_FILES ), "");
				}
			}
			catch (CoreException ex) {
				LogExceptionHandler.log(Messages.getString("TestCombinerInputDialog.errorStoringFiles"));
			}
		}
		super.okPressed();
	}
	
	/**
	 * Loads a list of files that were recently chosen for the given .cfg file
	 */
	private void initializeChosenFilesArray() {
		if (cfgFile == null) {
			return;
		}
		IResource resource = (IResource)cfgFile;
		String selectedFilesString = null;
		
		try {
			selectedFilesString = resource.getPersistentProperty(new QualifiedName(SELECTED_FILES_STORE, SELECTED_FILES ));
			if (selectedFilesString == null) {
				return;
			}
			String[] selectedFiles = selectedFilesString.split(";");
			if (selectedFiles.length > 0) {
				for (int i = 0; i < selectedFiles.length; i++) {
					if (selectedFiles[i].length() == 0) {
						return;
					}
					int lastIndexOfBackslash = selectedFiles[i].lastIndexOf("\\");
					String fileName = selectedFiles[i].substring(lastIndexOfBackslash + 1);
					String pathWithoutName =  selectedFiles[i].substring(0, lastIndexOfBackslash);
					TableItem fileItem = new TableItem(inputElementsTable, SWT.NONE);
					fileItem.setText(new String[] { fileName, pathWithoutName });
					
					if (chosenFiles == null) {
						chosenFiles = new ArrayList<String>();
					}
					chosenFiles.add(selectedFiles[i]);
				}
			}
		}
		catch (CoreException ex) {
			LogExceptionHandler.log(Messages.getString("TestCombinerInputDialog.errorLoadingFiles"));
		}	
	}
}
