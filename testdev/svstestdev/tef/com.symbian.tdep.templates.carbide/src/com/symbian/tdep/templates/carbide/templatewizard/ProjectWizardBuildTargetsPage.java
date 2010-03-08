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

import com.nokia.carbide.cpp.sdk.ui.shared.BuildTargetsPage;

// Referenced classes of package com.nokia.carbide.cpp.project.ui.wizards:
//            NewSymbianOSCppProjectWizard

public class ProjectWizardBuildTargetsPage extends BuildTargetsPage
{

    public ProjectWizardBuildTargetsPage(NewSymbianOSCppTestProjectWizard newsymbianoscppprojectwizard)
    {
        theWizard = newsymbianoscppprojectwizard;
    }

    protected boolean validatePage()
    {
        boolean flag = super.validatePage();
        if(flag)
            checkPathWithSDKs(theWizard.getProjectPath());
        return flag;
    }

    private NewSymbianOSCppTestProjectWizard theWizard;
}