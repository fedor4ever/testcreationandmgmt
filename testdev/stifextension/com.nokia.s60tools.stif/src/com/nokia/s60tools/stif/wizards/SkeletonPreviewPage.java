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

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;
import org.eclipse.swt.widgets.Label;

import com.nokia.s60tools.stif.util.ChangeElement;
import com.nokia.s60tools.stif.util.CodeGenerator;
import com.nokia.s60tools.stif.util.StringInput;

/**
 * Test Case Wizard page for reviewing changes
 *
 */
public class SkeletonPreviewPage extends WizardPage implements IPageChangedListener {

	private PageBook previewContainer;
	private ComparePreviewer comparePreview;
	private ChangeElement[] changeElements;
	private Table changeSets;
	Composite container;
	
	
	/**
	 * @param pageName
	 */
	public SkeletonPreviewPage() {
		super("SkeletonPreviewPage");
		setTitle("Preview changes");
		setDescription("Click finish to generate test case(s)");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1,true));
        
        //table to display all files that are going to be changed
        Label label1 = new Label(container, SWT.SINGLE);
        label1.setText("Files to be modified");
        
        changeSets = new Table(container, SWT.SINGLE|SWT.FULL_SELECTION|SWT.BORDER);
        changeSets.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false));
        changeSets.setLinesVisible(false);
        changeSets.setHeaderVisible(false);
        
		TableColumn column = new TableColumn(changeSets, SWT.NONE);
		column.setText("File");
		TableLayout tbl = new TableLayout();
		tbl.addColumnData(new ColumnWeightData(100 / changeSets.getColumnCount(),false));
		changeSets.setLayout(tbl);
		changeSets.addListener(SWT.Selection, new Listener(){
			
			public void handleEvent(Event event){
				if (changeSets.getSelectionIndex() <= changeElements.length) {
					//set compare preview to selected item
					setInput(changeElements[changeSets.getSelectionIndex()]);
				}
			}
			
		});
		
		//UI elements to show the CompareViewerSwitchingPane
        SashForm sashForm = new SashForm(container, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        sashForm.setBackground(new Color(getShell().getDisplay(),0,0,0));
        previewContainer = new PageBook(sashForm, SWT.NONE);
        System.out.println("Entering compare previewer constr");
        comparePreview = new ComparePreviewer(previewContainer, false, false);
        System.out.println("Leaving compare previewer constr");
        
        setControl(container);
        
        /** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForCaseWizard);
	}
	
	/**
	 * Creates table item to the table that shows files to be changed. 
	 * 
	 * @param text Text for the item
	 * @param data Object that is set to item data
	 */
	private void createTableItem(String text, Object data) {
		TableItem item = new TableItem(changeSets, SWT.NONE);
		item.setText(text);
		item.setData(data);
	}
	
	/**
	 * Creates ChangeElement from the parameters
	 * 
	 * @param oldSource old source in String (will be converted to StringInput)
	 * @param newSource new source in String (will be converted to StringInput)
	 * @param filePath path to the file
	 * @return
	 */
	private ChangeElement createChangeElement(String oldSource, String newSource, String filePath) {
		ChangeElement element = new ChangeElement();
		
		StringInput input = new StringInput(oldSource);
		element.setOldElement(input);
		input = new StringInput(newSource);
		element.setNewElement(input);
		element.setOldLabel("Original " + filePath);
		element.setNewLabel("New " + filePath);
		
		return element;
	}
	
	/**
	 * Creates the change elements from codegenerator data
	 * @param generator code generator where to get data from
	 */
	private void createChangeElements(CodeGenerator generator) {
		changeElements = new ChangeElement[3];
		int nextIndex = 0;
		
		//Create change element from the cpp source changes
		changeElements[nextIndex] = createChangeElement(generator.getOldCppSource(), generator.getNewCppSource(), 
												generator.getOutputCppFilePath());
		//Put cpp source item to the table widget
		createTableItem(generator.getOutputCppFilePath(), changeElements[nextIndex]);
		nextIndex+=1;
		
		
		//Create change element from the header source changes	
		if(!generator.getOutputIncFilePath().equals(""))
		{
			changeElements[nextIndex] = createChangeElement(generator.getOldIncSource(), generator.getNewIncSource(), 
												generator.getOutputIncFilePath());
			//Put header source item to the table widget
			createTableItem(generator.getOutputIncFilePath(), changeElements[nextIndex]);
			nextIndex+=1;
		}
		
		
		//Create change element from the TestFramework.ini file changes		
		changeElements[nextIndex] = createChangeElement(generator.getOldIniSource(), generator.getNewIniSource(), 
												generator.getOutputInIFilePath());
		//Put header source item to the table widget
		createTableItem(generator.getOutputInIFilePath(), changeElements[nextIndex]);
		
		//Set initial input to compare previewer, first change set
		setInput(changeElements[0]);
		changeSets.setSelection(0);
		
		//Call layout for the wizard page so elements get correct sizes
		container.layout(true);
	}
	
	/**
	 * Sets input to compare preview (called from wizard when page is set to visible)
	 * @param changeElement to set to the preview
	 **/
    private void setInput(ChangeElement changeElement) {
        comparePreview.setInput(changeElement); 
        previewContainer.showPage(comparePreview.getControl());
    }
    
    /**
     * Overridden setVisible to call wizard to generate new 
     * skeleton codes when page comes visible
     */
    @Override
    public void setVisible(boolean visible) {
    	if (visible) {
    		changeSets.removeAll();
    		SkeletonWizard wizard = (SkeletonWizard) getWizard();
    		wizard.generateCode();
    		createChangeElements(wizard.getGenerator());
    	}
    	super.setVisible(visible);
    }
    
    public void pageChanged(PageChangedEvent ev) {
    	if(ev.getSelectedPage() instanceof SkeletonPreviewPage){
    		container.setFocus();
    	}
    }
}
