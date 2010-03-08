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




package com.symbian.driver.report.views;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author HocineA Convenience adapter which is empty for the moment but can be
 *         usefull in the future see {@link AdapterFactoryLabelProvider}
 * 
 */
public class ReportAdapterFactoryLabelProvider extends
		AdapterFactoryLabelProvider {

	/**
	 * @param adapterFactory
	 */
	public ReportAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImageFromObject(java.lang.Object)
	 */
	protected Image getImageFromObject(Object object) {
		return super.getImageFromObject(object);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return super.getText(object);
	}

}
