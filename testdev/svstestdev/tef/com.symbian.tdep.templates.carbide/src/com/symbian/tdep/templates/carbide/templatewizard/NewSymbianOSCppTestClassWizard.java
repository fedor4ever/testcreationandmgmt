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


package com.symbian.tdep.templates.carbide.templatewizard;

import java.util.List;

import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.nokia.carbide.cpp.internal.api.sdk.ui.TemplateSDKsFilter;
import com.nokia.carbide.cpp.internal.project.ui.Messages;
import com.nokia.carbide.cpp.internal.project.ui.sharedui.ChooseProjectPage;
import com.nokia.carbide.cpp.ui.CarbideUIPlugin;
import com.nokia.carbide.internal.api.templatewizard.ui.TemplateWizard;

public class NewSymbianOSCppTestClassWizard extends TemplateWizard
{
    public NewSymbianOSCppTestClassWizard()
    {
        setFilterCheckboxLabel(Messages.getString("NewSymbianOSCppClassWizard.FilterCheckboxLabel"));
        setTemplateFilter(new TemplateSDKsFilter());
        setWindowTitle(Messages.getString("NewSymbianOSCppClassWizard.WizardTitle"));
    }

    protected void initializeDefaultPageImageDescriptor()
    {
        setDefaultPageImageDescriptor(CarbideUIPlugin.getSharedImages().getImageDescriptor("New_Symbian_OS_Class_wizard_banner.png"));
    }

    public void addPages()
    {
        String s = Messages.getString("NewSymbianOSCppClassWizard.ChooseProjectPageTitle");
        String s1 = Messages.getString("NewSymbianOSCppClassWizard.ChooseProjectPageDesc");
        chooseProjectPage = new ChooseProjectPage(s, s1);
        IStructuredSelection istructuredselection = getSelection();
        if(!istructuredselection.isEmpty())
        {
            Object obj = istructuredselection.getFirstElement();
            if(obj instanceof ICProject)
                obj = ((ICProject)obj).getProject();
            if(obj instanceof IProject)
                chooseProjectPage.setInitialSelection((IProject)obj);
        }
        addPage(chooseProjectPage);
        super.addPages();
    }

    public List getPagesAfterTemplateChoice()
    {
        return null;
    }

    public String getChooseTemplatePageTitle()
    {
        return Messages.getString("NewSymbianOSCppClassWizard.ChooseTemplatePageTitle");
    }

    public String getChooseTemplatePageDescription()
    {
        return null;
    }

    public String getWizardId()
    {
        return ID;
    }

    public ISchedulingRule getJobSchedulingRule()
    {
        if(chooseProjectPage == null)
            return null;
        else
            return chooseProjectPage.getProject().getWorkspace().getRoot();
    }

    public String getProcessingTitle()
    {
        return Messages.getString("NewSymbianOSCppClassWizard.ProcessTitle");
    }

    private static final String ID = NewSymbianOSCppTestClassWizard.class.getCanonicalName();
    private ChooseProjectPage chooseProjectPage;

}