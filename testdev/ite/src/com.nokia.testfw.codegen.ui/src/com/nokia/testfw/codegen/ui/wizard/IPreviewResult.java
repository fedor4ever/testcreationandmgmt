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

package com.nokia.testfw.codegen.ui.wizard;

import java.util.Map;

import org.eclipse.core.resources.IProject;

import com.nokia.testfw.codegen.ChangeFileContent;

public interface IPreviewResult {

	public IProject getTargetProject();

	public Map<String, ChangeFileContent> getPreviewResult();

	public void setPreviewResult(Map<String, ChangeFileContent> result);
}
