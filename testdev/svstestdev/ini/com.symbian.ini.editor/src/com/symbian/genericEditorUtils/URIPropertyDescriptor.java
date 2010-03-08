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



package com.symbian.genericEditorUtils;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

public class URIPropertyDescriptor extends PropertyDescriptor {

	private URICellEditor iUriCellEditor;

	public URIPropertyDescriptor(Object aObject, IItemPropertyDescriptor aItemPropertyDescriptor, URICellEditor aUriCellEditor) {
		super(aObject, aItemPropertyDescriptor);
		iUriCellEditor = aUriCellEditor;
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite aComposite) {
		return iUriCellEditor;
	}

}
