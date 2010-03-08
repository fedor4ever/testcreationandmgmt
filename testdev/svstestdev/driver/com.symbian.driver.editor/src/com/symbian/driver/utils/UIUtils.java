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



package com.symbian.driver.utils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class UIUtils {
	
	public static Display getDisplay()
    {
        Display display = Display.getCurrent();
        if (display == null)
        {
            display = Display.getDefault();
        }
        return display;
    }
	
	public static Shell getShell() {
		return getDisplay().getActiveShell();
	}

}
