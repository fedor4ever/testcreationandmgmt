/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of the License "Symbian Foundation License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/
package com.nokia.testfw.test.utils;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

/**
 * @author xiaoma
 *
 */
public class WorkbenchUtils {
	
	/**
	 * get view by view id
	 * @param id
	 * @return
	 */
	public static IViewPart getView(String id) {
		IViewReference viewReferences[] = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < viewReferences.length; i++) {
			if (id.equals(viewReferences[i].getId())) {
				return viewReferences[i].getView(false);
			}
		}
		return null;
	}
}
