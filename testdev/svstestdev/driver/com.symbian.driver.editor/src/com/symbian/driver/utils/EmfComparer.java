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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IElementComparer;

import com.symbian.driver.impl.DocumentRootImpl;
import com.symbian.driver.impl.DriverImpl;
import com.symbian.driver.impl.TaskImpl;

public class EmfComparer implements IElementComparer {

	public boolean equals(Object arg0, Object arg1) {
		if (arg0 instanceof EObject &&
				arg1 instanceof EObject) {
			if (arg0 instanceof DriverImpl && arg1 instanceof DriverImpl) {
				return true;
			}
			
			if (arg0 instanceof DocumentRootImpl && arg1 instanceof DocumentRootImpl) {
				return true;
			}
			if (arg0 instanceof TaskImpl && arg1 instanceof TaskImpl) {
				TaskImpl task0 = (TaskImpl)arg0;
				TaskImpl task1 = (TaskImpl)arg1;
				if ((task0.getName() != null && task1.getName()!=null)) {
				    return task0.getName().equals(task1.getName());
				}
			}

			//until here, we need to compare emf structures
			EcoreUtil.EqualityHelper helper = new EcoreUtil.EqualityHelper();
		    return helper.equals((EObject)arg0, (EObject)arg1);
		}
		return arg0.equals(arg1);
	}

	public int hashCode(Object arg0) {
		return arg0.hashCode();
	}

}
