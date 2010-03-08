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


package com.symbian.genericEditorUtils;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;

import com.symbian.ini.presentation.IniEditorPlugin;

/**
 * @author EngineeringTools
 *
 */
public abstract class GenericDetailsPage implements IDetailsPage {

	protected FormToolkit iFormToolkit;
	protected IEmfFormEditor iEditor;
	private int iColumns = 2;

	/**
	 * @param aEditor
	 */
	public GenericDetailsPage(IEmfFormEditor aEditor) {
		super();
		
		iEditor = aEditor;
	}
	
	public GenericDetailsPage(IEmfFormEditor aEditor, int aColumns) {
		iEditor = aEditor;
		iColumns = aColumns;
	}
	
	public final void createContents(Composite aParent) {
		aParent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		aParent.setLayout(new GridLayout(1, false));
		
		// Create Main Section
		final SectionPart lMainSection = new SectionPart(aParent, iFormToolkit, ExpandableComposite.TITLE_BAR | org.eclipse.ui.forms.widgets.Section.DESCRIPTION);
		{
			lMainSection.getSection().setText(getTitle());
			lMainSection.getSection().setDescription(getDescription());
			lMainSection.getSection().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			
			// Create Composite for Main
			final Composite lMainComposite = iFormToolkit.createComposite(lMainSection.getSection(), SWT.NONE);
			{
				lMainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
				GridLayout lMainGridLayout = new GridLayout(iColumns, false);
				lMainComposite.setLayout(lMainGridLayout);
				lMainSection.getSection().setClient(lMainComposite);
				
				// Create the body
				createDetailsBody(lMainComposite);
				
				iFormToolkit.paintBordersFor(lMainComposite);
			}
		}
		
		//  Create Help Section
		final Section lHelpSection = iFormToolkit.createSection(aParent, ExpandableComposite.TWISTIE);
		{
			lHelpSection.setText("Help");
			lHelpSection.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
			
			final Composite lHelpComposite = iFormToolkit.createComposite(lHelpSection, SWT.NONE);
			{
				lHelpComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
				lHelpComposite.setLayout(new GridLayout(1, false));
				lHelpSection.setClient(lHelpComposite);
				
				FormText lHelp = iFormToolkit.createFormText(lHelpComposite, false);
				lHelp.setText(getHelpText(), true, false);
				GridData lData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
				lData.minimumWidth = 200;
				lData.widthHint = 400;
				lHelp.setLayoutData(lData);
				
				//add a link
				final ImageHyperlink lHyperlink = new ImageHyperlink(lHelpComposite, SWT.NONE);
				{
					iFormToolkit.adapt(lHyperlink, true, true);
					lHyperlink.setText("Further Help");
					lHyperlink.setImage(ImageDescriptor.createFromURL((URL) IniEditorPlugin.getPlugin().getImage("help")).createImage());
					lHyperlink.setHref(getHelpHref());
					lHyperlink.setSize(100,100);
					lHyperlink.setUnderlined(true);
					
					lHyperlink.addHyperlinkListener(new HyperlinkAdapter() {

			        	@Override
			        	public void linkActivated(HyperlinkEvent aEvent) {
							PlatformUI.getWorkbench().getHelpSystem().displayHelpResource((String) aEvent.getHref());
						}
						
					});
				}
			}
			
			iFormToolkit.paintBordersFor(lHelpComposite);
		}
	}

	protected abstract String getTitle();
	
	protected abstract String getDescription();
	
	protected abstract void createDetailsBody(Composite aComposite);
	
	protected abstract String getHelpText();
	
	protected abstract String getHelpHref();
	
	public void initialize(IManagedForm aForm) {
		iFormToolkit = aForm.getToolkit();
	}

	public void commit(boolean aOnSave) {
	
	}

	public void dispose() {
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
	}

	public boolean setFormInput(Object aInput) {
		return false;
	}

	public void selectionChanged(IFormPart aPart, ISelection aSelection) {
	}

}