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

package com.symbian.driver.core.extension;

import java.util.Map;

import com.symbian.driver.Task;
import com.symbian.driver.core.processors.PreProcessor;

public interface IVisitor {
	
	/**
	 * Severity level for Exceptions
	 */
	public enum ESeverity {
		DEBUG, INFO, WARNING, ERROR;
	}

	/**
	 * @param aTaskInfo
	 * @return
	 */
	public Map<? extends Exception, ESeverity> execute(Task aTask, PreProcessor aSymbianDevice);
	
}
