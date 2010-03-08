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


package com.symbian.tdep.templates.carbide;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.nokia.carbide.template.engine.ITemplate;

public class CreateFolders extends
		com.nokia.carbide.templatewizard.processes.CreateFolders {

	// The name of template parameter
	private static final String ABORT = "Abort";

	public CreateFolders() {
	}

	public void process(ITemplate itemplate, List list,
			IProgressMonitor iprogressmonitor) throws CoreException {
		if (itemplate.getTemplateValues().get(ABORT) == null) {
			super.process(itemplate, list, iprogressmonitor);
		}
	}
}
