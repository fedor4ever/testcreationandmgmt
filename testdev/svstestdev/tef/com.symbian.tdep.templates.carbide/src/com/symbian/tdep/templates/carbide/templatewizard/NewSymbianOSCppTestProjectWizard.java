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

import com.nokia.carbide.cpp.internal.api.sdk.ui.TemplateSDKsFilter;
import com.nokia.carbide.cpp.internal.project.ui.Messages;
import com.nokia.carbide.cpp.project.ui.sharedui.NewProjectPage;
import com.nokia.carbide.cpp.ui.CarbideUIPlugin;
import com.nokia.carbide.internal.api.templatewizard.ui.TemplateWizard;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

// Referenced classes of package com.nokia.carbide.cpp.project.ui.wizards:
//            ProjectWizardBuildTargetsPage

public class NewSymbianOSCppTestProjectWizard extends TemplateWizard {

	public NewSymbianOSCppTestProjectWizard() {
		setFilterCheckboxLabel(Messages
				.getString("NewSymbianOSCppProjectWizard.FilterCheckboxLabel"));
		setTemplateFilter(new TemplateSDKsFilter());
		setWindowTitle(Messages
				.getString("NewSymbianOSCppProjectWizard.WindowTitle"));
	}

	protected void initializeDefaultPageImageDescriptor() {
		setDefaultPageImageDescriptor(CarbideUIPlugin.getSharedImages()
				.getImageDescriptor(
						"New_Symbian_OS_C++_Project_wizard_banner.png"));
	}

	@SuppressWarnings("unchecked")
	public List getPagesAfterTemplateChoice() {
		if (pagesAfterTemplateChoice == null) {
			pagesAfterTemplateChoice = new ArrayList();
			String s = Messages
					.getString("NewSymbianOSCppProjectWizard.NewProjectPageTitle");
			String s1 = Messages
					.getString("NewSymbianOSCppProjectWizard.NewProjectPageDesc");
			newProjectPage = new NewProjectPage(s, s1);
			pagesAfterTemplateChoice.add(newProjectPage);
			buildTargetsPage = new ProjectWizardBuildTargetsPage(this);
			pagesAfterTemplateChoice.add(buildTargetsPage);
			notifyTemplateChanged();
		}
		return pagesAfterTemplateChoice;
	}

	public String getChooseTemplatePageTitle() {
		return Messages
				.getString("NewSymbianOSCppProjectWizard.ChooseTemplatePageTitle");
	}

	public String getChooseTemplatePageDescription() {
		return null;
	}

	public IPath getProjectPath() {
		return newProjectPage.getLocationPath();
	}

	public String getWizardId() {
		return ID;
	}

	public void notifyTemplateChanged() {
		if (buildTargetsPage != null)
			buildTargetsPage.setSelectedTemplate(getSelectedTemplate());
	}

	public ISchedulingRule getJobSchedulingRule() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	public String getProcessingTitle() {
		return Messages.getString("NewSymbianOSCppProjectWizard.ProcessTitle");
	}

	private static final String ID = NewSymbianOSCppTestProjectWizard.class
			.getCanonicalName();
	private List pagesAfterTemplateChoice;
	private ProjectWizardBuildTargetsPage buildTargetsPage;
	private NewProjectPage newProjectPage;

}