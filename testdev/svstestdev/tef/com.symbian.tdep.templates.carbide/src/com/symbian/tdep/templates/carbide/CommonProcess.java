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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;

import com.nokia.carbide.template.engine.ITemplate;
import com.nokia.carbide.templatewizard.process.AbstractProjectProcess;
import com.nokia.carbide.templatewizard.process.IParameter;

public abstract class CommonProcess extends AbstractProjectProcess {

	// The name of template parameter
	protected static final String BASE_NAME = "baseName";

	// The name of template parameter
	protected static final String TARGET_NAME = "targetName";

	// The name of template parameter
	protected static final String INC_DIR = "incDir";

	// The name of template parameter
	protected static final String SOURCE_DIR = "sourceDir";

	// The name of template parameter
	protected static final String GROUP_DIR = "groupDir";

	// The name of template parameter
	protected static final String DATA_DIR = "dataDir";

	// The name of template parameter
	protected static final String SCRIPT_DIR = "scriptDir";

	// The name of template parameter
	protected static final String TEST_INC_DIR = "testIncDir";

	// The name of template parameter
	protected static final String TEST_SOURCE_DIR = "testSourceDir";

	// The name of template parameter
	protected static final String TEST_GROUP_DIR = "testGroupDir";

	// The name of template parameter
	protected static final String TEST_DATA_DIR = "testDataDir";

	// The name of template parameter
	protected static final String TEST_SCRIPT_DIR = "testScriptDir";

	// The name of template parameter
	protected static final String COPYRIGHT = "copyright";

	// The name of template parameter
	protected static final String AUTHOR = "author";

	protected static final String DATAEXPORTLOC = "dataExportLoc";
	
	protected static Map<String, Object> iTemplateValues;

	protected IProject iTargetProject;

	protected static String iSourceDir;
	protected static String iIncDir;
	protected static String iGroupDir;
	protected static String iDataDir;
	protected static String iScriptDir;
	protected static String iTargetName;
	protected static String iBassName;
	protected static String iDataExportLoc;
	protected static String iTestSourceDir;
	protected static String iTestIncDir;
	protected static String iTestGroupDir;
	protected static String iTestDataDir;
	protected static String iTestScriptDir;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProcess#getPlugin()
	 */
	@Override
	protected Plugin getPlugin() {
		return TefTemplatesCarbidePlugin.getDefault();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nokia.carbide.templatewizard.process.AbstractProjectProcess#init(com.nokia.carbide.template.engine.ITemplate,
	 *      java.util.List)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void init(ITemplate aTemplate, List<IParameter> aParemeters)
			throws CoreException {
		super.init(aTemplate, aParemeters);
		// Get Properties
		iTemplateValues = aTemplate.getTemplateValues();
		iSourceDir = (String) iTemplateValues.get(SOURCE_DIR);
		iIncDir = (String) iTemplateValues.get(INC_DIR);
		iGroupDir = (String) iTemplateValues.get(GROUP_DIR);
		iDataDir = (String) iTemplateValues.get(DATA_DIR);
		iScriptDir = (String) iTemplateValues.get(SCRIPT_DIR);
		iBassName = (String) iTemplateValues.get(BASE_NAME);
		iTestSourceDir = (String) iTemplateValues.get(TEST_SOURCE_DIR);
		iTestIncDir = (String) iTemplateValues.get(TEST_INC_DIR);
		iTestGroupDir = (String) iTemplateValues.get(TEST_GROUP_DIR);
		iTestDataDir = (String) iTemplateValues.get(TEST_DATA_DIR);
		iTestScriptDir = (String) iTemplateValues.get(TEST_SCRIPT_DIR);
		iTargetName = (String) iTemplateValues.get(TARGET_NAME);
		iDataExportLoc = (String) iTemplateValues.get(DATAEXPORTLOC);
	}
	
	// Return ancestor layer by ..
	protected String genAncestor(int deep) {
		String result = "";
		for (int i = 0; i < deep; i++) {
			result += "..\\";
		}
		return result;
	}

	// Return relative path, base and target base on project path
	protected String genRelativePath(IPath base, IPath target) {
		String result;
		if (".".equals(target.toOSString()) || "".equals(target.toOSString())) {
			result = new Path(genAncestor(base.segmentCount()))
					.removeTrailingSeparator().toOSString();
		} else {
			int same = base.matchingFirstSegments(target);
			if (same > 0) {
				target = target.removeFirstSegments(same);
				result = genAncestor(base.segmentCount() - same)
						+ target.toOSString() + " ";
			} else {
				result = genAncestor(base.segmentCount()) + target.toOSString()
						+ " ";
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc) Convert absolute path on device to the path relative to
	 * EPOCROOT @param loc the absolute path on device @return
	 */
	protected static final String toEpocLoc(String loc) {
		// ^c\:\\([0-9a-zA-Z]+\\)*[0-9a-zA-Z]+$
		String ret = loc.substring(2);
		return ret;
	}

}
