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

package com.nokia.testfw.codegen.ui.util;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class PathNodeTreeViewerComparator extends ViewerComparator {

	public int compare(Viewer viewer, Object object1, Object object2) {
		if ((object1 instanceof PathNode) && (object2 instanceof PathNode)) {
			String leftName = ((PathNode) object1).getName();
			String rightName = ((PathNode) object2).getName();
			return Collator.getInstance().compare(leftName, rightName);
		}
		return super.compare(viewer, object1, object2);
	}

	public int category(Object element) {
		if (element instanceof PathNode) {
			if (((PathNode) element).getData() == null) {
				return 0;
			} else {
				return 1;
			}
		}
		return super.category(element);
	}
}
