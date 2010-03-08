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

import java.io.File;
import java.util.List;

public interface IBuild {
	
	public boolean cleanBuild(File aBuildDirectory);
	
	public boolean build(File aBuildDirectory);

	public List<File> dryBuild(File aBuildDirectory);
}
