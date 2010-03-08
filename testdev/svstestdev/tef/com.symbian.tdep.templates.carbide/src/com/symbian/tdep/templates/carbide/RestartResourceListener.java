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
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.nokia.carbide.cpp.internal.project.ui.ProjectUIPlugin;
import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.AbstractProjectProcess;
import com.nokia.carbide.templatewizard.process.IParameter;

public class RestartResourceListener extends AbstractProjectProcess {

	// The name of template parameter
	private static final String ABORT = "Abort";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProcess#getPlugin()
	 */
	@Override
	protected Plugin getPlugin() {
		return TefTemplatesCarbidePlugin.getDefault();
	}

	protected void init(ITemplate aTemplate, List<IParameter> aParemeters)
			throws CoreException {
		super.init(aTemplate, aParemeters);
		// Get Properties
		Map iTemplateValues = aTemplate.getTemplateValues();

		if (iTemplateValues.get(ABORT) != null) {
			return;
		}

		Job job = new Job("RestartResourceListener") {

			@Override
			protected IStatus run(IProgressMonitor arg0) {
				ProjectUIPlugin
						.setAddFilesToProjectOption(TefTemplatesCarbidePlugin
								.getAddFilesToProjectOption());
				return Status.OK_STATUS;
			}
		};
		job.schedule(30000);
	}
}
