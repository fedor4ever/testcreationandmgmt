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

import java.util.logging.Logger;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;

/**
 * @author HocineA Convenience adapter which is empty for the moment but can be
 *         usefull in the future see {@link AdapterFactoryContentProvider}
 * 
 */
public class ReportAdapterFactoryContentProvider extends
		AdapterFactoryContentProvider {

	private static final Logger LOGGER = Logger
			.getLogger(ReportAdapterFactoryContentProvider.class.getName());

	/**
	 * @param adapterFactory
	 */
	public ReportAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object object) {
		return super.getElements(object);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object object) {

		return super.getChildren(object);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {

		return super.getParent(object);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */

	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
	}
}
