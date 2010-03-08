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

import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import com.nokia.s60tools.stif.Constants;
import com.nokia.s60tools.stif.util.FileAccessUtils;
import com.nokia.s60tools.stif.util.PluginUtils;

/**
 * Page of Test Case Wizard for reviewing output files
 *
 */
public class SkeletonSelectOutputPage extends WizardPage implements IPageChangedListener {
	
	/** Type of module. */
	private int moduleType;
	/** Text field. Input for a path of source .cpp File */
	private Text outputCppFile;
	/** Text field. Input for a path of header .h File */
	private Text outputIncFile;
	/** Text field. Input for a config file. Can by .cfg for 
	 *  testscripter and .py for pythonwrapper 
	*/
	private Text outputCfgFile;
	/** CheckBox button used to specify if the Preview of changes should be shown or not */
	private Button checkboxPreview;
	/** Group of widgets */
	private Group group1;
	/** control representing classes retrieved from source selected by End User */
	private List classList;
	/** Radio button pointing out that user is using Hardcoded module type */
	private Button buttonHard;
	/** Radio button pointing out that user is using TestScripter module type */
	private Button buttonTestClass;
	/** Radio button pointing out that user is using PythonWrapper module type */
	private Button buttonPython;
	/** Name of project*/
	private String name = "";
	/** Items that were choosen by End User in Carbide workspace*/
	private IStructuredSelection selection;
	
	
	/**
	 * Constructor
	 */
	public SkeletonSelectOutputPage(IStructuredSelection selection) {
		super("skeletonSelectOutputPage");
		this.selection = selection;
		setTitle("Output summary");
		setDescription("Click finish to generate the test code stub");
	}	
	
	
	
	/** (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		composite.setLayout(gl);
		
		//Radio group selection for module type
	    group1 = new Group(composite, SWT.SHADOW_IN);
	    group1.setText("Selected module type");
	    group1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    group1.setLayoutData(new GridData(SWT.FILL,SWT.NONE,true,false,2,1));
	    group1.setEnabled(false);
	    
	    Object selItem = selection.getFirstElement();
	    
		if (selItem instanceof IMethodDeclaration) {
            IMethodDeclaration metDec = ((IMethodDeclaration)selItem);
            IProject project = metDec.getCProject().getProject();
		    
		    name = project.getName();
		    String mmpName = name.concat(".mmp");
		    IPath fullPath = project.getLocation();
		    String strFullPath = fullPath.toOSString();
		    String strCppFullPath = strFullPath.concat(File.separator+ "group"+File.separator+mmpName);
		    File mmpFile = new File(strCppFullPath);
		    String mmpFileContent = FileAccessUtils.getContents( mmpFile );
		    int startIndex = mmpFileContent.indexOf("TYPE") + 5;
		    int endIndex = mmpFileContent.indexOf("*/");
		    String type = mmpFileContent.substring(startIndex, endIndex);
		    moduleType = PluginUtils.setModuleType(type);
		    String cppFileFullPath = "";
		    String hFileFullPath = "";
		    System.out.println("Create control");
			
		    if(moduleType == Constants.MODULE_TYPE_HARDCODED)
		    {
		    	String cppFileName = name.concat("Cases.cpp");
		    	cppFileFullPath = strFullPath.concat(File.separator+ "src" + File.separator+ cppFileName);
		    
		    	String hFileName = name.concat(".h");
		    	hFileFullPath = strFullPath.concat(File.separator + "inc" + File.separator + hFileName);
		    }	    
		    if( moduleType == Constants.MODULE_TYPE_TESTCLASS)
		    {
		    	String cppFileName = name.concat("Blocks.cpp");
		    	cppFileFullPath = strFullPath.concat(File.separator+ "src" + File.separator+ cppFileName);
		    
		    	String hFileName = name.concat(".h");
		    	hFileFullPath = strFullPath.concat(File.separator + "inc" + File.separator + hFileName);
		    }
		    if( moduleType == Constants.MODULE_TYPE_PYTHONTEST)
		    {
		    	String cppFileName = name.concat(".cpp");
		    	cppFileFullPath = strFullPath.concat(File.separator+ "src" + File.separator+ cppFileName);
		
		    	hFileFullPath = "";
		    }
		    
		    buttonHard = new Button(group1, SWT.RADIO);
		    buttonHard.setText("hardcoded");
		    buttonHard.setEnabled(false);
		    buttonHard.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					moduleType = Constants.MODULE_TYPE_HARDCODED;
					dialogChanged();
				}
				
			});
		    //set selected for demo
		    buttonTestClass = new Button(group1, SWT.RADIO);
		    buttonTestClass.setText("testclass");
		    buttonTestClass.setEnabled(false);
		    buttonTestClass.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					moduleType = Constants.MODULE_TYPE_TESTCLASS;
					dialogChanged();
				}
			});
		    
		    Label label = new Label(composite, SWT.NONE);
		    label.setText("Output source file (.cpp):");
		    label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		    outputCppFile = new Text(composite, SWT.BORDER);
		    outputCppFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    outputCppFile.setText( cppFileFullPath );
		    outputCppFile.setEnabled(false);
		    outputCppFile.addListener(SWT.Modify, new Listener() {
			
				public void handleEvent(Event event) {
					dialogChanged();
				}
			});
		    
		    //create this header file lable in all cases instead of Python
		    if( moduleType != Constants.MODULE_TYPE_PYTHONTEST)
		    {
			    label = new Label(composite, SWT.NONE);
			    label.setText("Output header file (.h):");
			    label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
			    outputIncFile = new Text(composite, SWT.BORDER);
			    outputIncFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			    outputIncFile.setText( hFileFullPath );
			    outputIncFile.setEnabled(false);
				setClassNames();
				
						
				outputIncFile.addListener(SWT.Modify , new Listener() {
					public void handleEvent(Event event) {
						dialogChanged();
						}
				});	    
		    }
			
			if( moduleType == Constants.MODULE_TYPE_TESTCLASS)
		    {
				label = new Label(composite, SWT.NONE);
			    label.setText("Specify test case file name (.cfg):");
			    label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
			    outputCfgFile = new Text(composite, SWT.BORDER);
			    outputCfgFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			    //set deafult text
			    outputCfgFile.setText( name + ".cfg" );
		    }
			
			if( moduleType == Constants.MODULE_TYPE_PYTHONTEST)
		    {
				label = new Label(composite, SWT.NONE);
			    label.setText("Specify python script file name (.py):");
			    label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
			    outputCfgFile = new Text(composite, SWT.BORDER);
			    outputCfgFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			    //set deafult text
			    outputCfgFile.setText( name + "_Script.py" );
		    }
		
			if( moduleType != Constants.MODULE_TYPE_PYTHONTEST)
		    {
				label = new Label(composite, SWT.NONE);
				label.setText("Select class to which to add the test functions:");
				label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		    
				//list to display available classes from header file to use
				classList = new List(composite, SWT.SINGLE|SWT.BORDER);
				classList.setEnabled(false);
				//listener to notice selection
				classList.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						dialogChanged();
					}
				});
				classList.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		    }
		 	    
		    checkboxPreview = new Button(composite, SWT.CHECK);
		    checkboxPreview.setText("Preview changes");
		    checkboxPreview.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		    checkboxPreview.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					dialogChanged();
				}
		    });
	    
		    initialize(name);
		    dialogChanged();
			setControl(composite);
		}
		
        /** 
		 * We want to be informed about pageChanged event in order to set focus to a control from the NewTestModulePage
		 */
		WizardDialog dialog = (WizardDialog)getContainer();
		dialog.addPageChangedListener(this);
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getControl(), HelpContextIDs.helpForCaseWizard);
	}
	
	/**
	 * Selects appropriate radio button representing module type
	 */
	private void selectModuleType()
	{
		if ( moduleType == Constants.MODULE_TYPE_TESTCLASS )
			buttonTestClass.setSelection(true);
		if ( moduleType == Constants.MODULE_TYPE_HARDCODED )
			buttonHard.setSelection(true);
		if (moduleType == Constants.MODULE_TYPE_PYTHONTEST )
			buttonPython.setSelection(true);
	}
	
	
	/**
	 * Selects default class that will be used for code generation.
	 * Among all found classes in sources this one that has the same name as the 
	 * project will be choosen
	 * @param projectName Name of project were classes declaration will be searched
	 * @return true if default class was found or false if not
	 */
	private boolean selectDefaultClass(String projectName)
	{
		for(int i=0; i < classList.getItemCount(); i++)
		{
			if( classList.getItem(i).equals("C"+ projectName) )
			{
				classList.select(i);
				return true;
			}
		}
		//if project class is not found, then select first element
		//but probably in such case something went wrong!
		classList.select(0);
		return false;
	}
			
	private void initialize(String projectName) {
		group1.setEnabled(false);
		
		checkboxPreview.setSelection(true);
		if(getModuleType() != Constants.MODULE_TYPE_PYTHONTEST)
		{
			setClassNames();
			selectDefaultClass(projectName);
		}
		selectModuleType();		
	}
	
	/**
	 * If theres not a error message currently in the wizard page,
	 * retrieves class names from the header file that is set to outputIncFile text widget 
	 * and places them to the selection list.
	 */
	@SuppressWarnings("unchecked")
	private void setClassNames() {
		if (getMessageType() == WizardPage.NONE) {
			try {
				ITranslationUnit cppfile = (ITranslationUnit)CoreModel.getDefault().create(new Path(outputIncFile.getText()));
				java.util.List classdecl = cppfile.getChildrenOfType(ITranslationUnit.C_CLASS_DECLARATION);
				java.util.List classes = cppfile.getChildrenOfType(ITranslationUnit.C_CLASS);
				classes.addAll(classdecl);
				classList.removeAll();
				for (int i = 0; i < classes.size(); i++) {
					ICElement cClass = (ICElement)classes.get(i); 
					if (cClass != null) {
						classList.add(cClass.getElementName());
					}
				}
							
				if (classList.getItemCount() == 0) {
					updateStatus("Could not get any class names from header file", WizardPage.ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Validates user input
	 */
	private void dialogChanged() {
		//check that test templates path is set at preferences
		boolean typeSelected = false;
		for (int i = 0; i < group1.getChildren().length; i++) {
			Button button = (Button)group1.getChildren()[i];
			if (button.getSelection()) {
				typeSelected = true;
				break;
			}
		}
		if (!typeSelected) {
			updateStatus("Select module type", WizardPage.NONE);
			return;
		}
		if (outputCppFile.getText().equals("")) {
			updateStatus("Select output source file", WizardPage.NONE);
			return;
		}
		File file = new File(outputCppFile.getText());
		if (!file.exists()) {
			updateStatus("Output source file must exist", WizardPage.ERROR);
			return;
		}
		if (!file.canWrite()) {
			updateStatus("Output source file must be writable", WizardPage.ERROR);
			return;
		}
		if(!file.getName().substring(file.getName().lastIndexOf('.')).equals(".cpp")) {
			updateStatus("Output source file must be .cpp file", WizardPage.ERROR);
			return;
		}
		if( getModuleType()!= Constants.MODULE_TYPE_PYTHONTEST)
		{
			if (outputIncFile.getText().equals("")) {
				updateStatus("Select output header file", WizardPage.NONE);
				return;
			}
			file = new File(outputIncFile.getText());
			if (!file.exists()) {
				updateStatus("Output header file must exist", WizardPage.ERROR);
				return;
			}
			if (!file.canWrite()) {
				updateStatus("Output header file must be writable", WizardPage.ERROR);
				return;
			}
			if(!file.getName().substring(file.getName().lastIndexOf('.')).equals(".h")) {
				updateStatus("Output header file must be .h file", WizardPage.ERROR);
				return;
			}
		
			if(classList.getSelectionCount() < 1) {
				updateStatus("Select from the list the class name to use", WizardPage.NONE);
				return;
			}
		}
		if(checkboxPreview.getSelection()) {
			updateStatus("Click Next to preview changes or Finish to generate test case(s)", WizardPage.NONE, true);
			return;
		}
		updateStatus(null, WizardPage.NONE);
	}
	
	/**
	 * Sets message to wizard dialog. If message is null, sets page to completed stage
	 * ie. enables next/finish buttons.
	 * 
	 * @param message
	 * @param type
	 */
	private void updateStatus(String message, int type) {
		updateStatus(message, type, false);
	}
	
	/**
	 * Sets message to wizard dialog. If message is null or complete is true, sets page 
	 * to completed stage ie. enables next/finish buttons.
	 * 
	 * @param message
	 * @param type
	 * @param complete
	 */
	private void updateStatus(String message, int type, boolean complete) {
		setMessage(message, type);
		if (complete) {
			setPageComplete(complete);
		} else {
			setPageComplete(message == null);
		}
	}
	
	/**
	 * Returns the path to the output source file 
	 * (Path is in OS String format)
	 * @return String path to output .cpp file
	 */
	public String getOutputCppFilePath() {
		return outputCppFile.getText();
	}
	
	/**
	 * Returns the path to the output header file 
	 * (Path is in OS String format)
	 * @return String path to output .h file
	 */
	public String getOutputIncFilePath() {
		return outputIncFile.getText();
	}
	
	/**
	 * Returns the name of the output cfg file 
	 * @return String path to output cfg file
	 */
	public String getCfgFileName() {
		return outputCfgFile.getText();
	}

	/**
	 * Returns user defined output module type.
	 * Possible return values are:
	 * 
	 * normal
	 * hardcoded
	 * testclass
	 * kerneltestclass
	 * capsmodifier
	 * pythonscripter
	 * 
	 * @return String module type
	 */
	public int getModuleType() {
		return moduleType;
	}
	
	/**
	 * Returns the class name to add the test functions to
	 * 
	 * @return String class name
	 */
	public String getCClassName() {
		if(getModuleType()!=Constants.MODULE_TYPE_PYTHONTEST)
			return classList.getSelection()[0];
		else
			/*just return project name*/
			return name;
	}
	
	/**
	 * Returns the selection for preview changes checkbox
	 * 
	 * @return
	 */
	public boolean isPreviewChanges() {
		return checkboxPreview.getSelection();
	}
	
	/**
	 * Handles page changed event
	 */
	public void pageChanged(PageChangedEvent ev) {
		if(ev.getSelectedPage() instanceof SkeletonSelectOutputPage){
			checkboxPreview.setFocus();
		}
	}
}
