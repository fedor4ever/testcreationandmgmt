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



package com.symbian.tdep.templates.carbide;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.symbian.tdep.templates.carbide.messages";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String id) {
		try {
			return RESOURCE_BUNDLE.getString(id);
		} catch (Exception e) {
			IStatus lStatus = new Status(IStatus.WARNING, Messages.class
					.getName(),
					"Exception was thrown while loading string id: " + id, e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			return id;
		}
	}

	public static String getString(String id, Object... arguments) {
		try {
			String msg = RESOURCE_BUNDLE.getString(id);
			return MessageFormat.format(msg, arguments);
		} catch (Exception e) {
			IStatus lStatus = new Status(IStatus.WARNING, Messages.class
					.getName(),
					"Exception was thrown while loading string id: " + id, e);
			TefTemplatesCarbidePlugin.getDefault().getLog().log(lStatus);
			return id;
		}
	}

}
