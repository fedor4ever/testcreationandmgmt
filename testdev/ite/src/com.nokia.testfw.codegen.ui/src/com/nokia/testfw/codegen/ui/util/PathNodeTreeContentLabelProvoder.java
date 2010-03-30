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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class PathNodeTreeContentLabelProvoder extends LabelProvider implements
		ITreeContentProvider {

	private final Object NO_CHILDREN[];

	public PathNodeTreeContentLabelProvoder() {
		super();
		NO_CHILDREN = new Object[0];
	}

	public Object[] getChildren(Object obj) {
		if (obj instanceof PathNode)
			return ((PathNode) obj).getChildren();
		else
			return NO_CHILDREN;
	}

	public Object getParent(Object obj) {
		if (obj instanceof PathNode)
			return ((PathNode) obj).getParent();
		else
			return null;
	}

	public boolean hasChildren(Object obj) {
		if (obj instanceof PathNode)
			return ((PathNode) obj).getChildren().length > 0;
		else
			return false;
	}

	public Object[] getElements(Object obj) {
		if (obj instanceof PathNode) {
			return ((PathNode) obj).getChildren();
		}
		return NO_CHILDREN;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object obj, Object obj1) {
	}

	@SuppressWarnings("deprecation")
	public Image getImage(Object obj) {
		if (obj instanceof PathNode) {
			PathNode node = (PathNode) obj;
			if (node.getData() != null) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(
						ISharedImages.IMG_OBJ_FILE);
			}
			if (node.getParent().getParent() == null) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(
						ISharedImages.IMG_OBJ_PROJECT);
			} else {
				return PlatformUI.getWorkbench().getSharedImages().getImage(
						ISharedImages.IMG_OBJ_FOLDER);
			}
		}
		return null;
	}

	public String getText(Object obj) {
		if (obj instanceof PathNode)
			return ((PathNode) obj).getName();
		return null;
	}
}
