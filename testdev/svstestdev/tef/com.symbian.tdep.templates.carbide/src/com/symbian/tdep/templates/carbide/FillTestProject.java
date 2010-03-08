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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;

import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.AbstractProjectProcess;
import com.nokia.carbide.templatewizard.process.IParameter;

/**
 * A process to customize some template parameters before creating 
 * TEF project from template.  
 * 
 * @author Development Tools
 */
public class FillTestProject extends AbstractProjectProcess {

	/* (non-Javadoc)
	 * @see com.nokia.carbide.templatewizard.process.AbstractProcess#getPlugin()
	 */
	@Override
	protected Plugin getPlugin() {
		return TefTemplatesCarbidePlugin.getDefault();
	}
	
	
	/* (non-Javadoc)
	 * @see com.nokia.carbide.templatewizard.process.AbstractProjectProcess#init(com.nokia.carbide.template.engine.ITemplate, java.util.List)
	 */
	@Override
	protected void init(ITemplate aTemplate, List<IParameter> aParemeters) throws CoreException {
		super.init(aTemplate, aParemeters);
		
		// Get Properties
		final Map<String, Object> lTemplateValues = aTemplate.getTemplateValues();

		final IProject lProject = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
		lTemplateValues.put("dataEpocLoc", toEpocLoc((String) lTemplateValues.get("dataExportLoc"))); 
		lTemplateValues.put("scriptEpocLoc", toEpocLoc((String) lTemplateValues.get("scriptExportLoc"))); 
		
		// Refresh Workspace
		lProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}

	/*(non-Javadoc)
	 * Convert absolute path on device to the path relative to EPOCROOT
	 * @param loc the absolute path on device
	 * @return
	 */
	private static final String toEpocLoc(String loc) {
		// ^c\:\\([0-9a-zA-Z]+\\)*[0-9a-zA-Z]+$
		String ret = loc.substring(2);
		return ret;
	}

}
