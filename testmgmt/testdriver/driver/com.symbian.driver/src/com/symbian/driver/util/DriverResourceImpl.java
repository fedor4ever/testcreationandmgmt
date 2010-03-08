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


package com.symbian.driver.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import com.symbian.driver.DocumentRoot;
import com.symbian.driver.Task;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see com.symbian.driver.util.DriverResourceFactoryImpl
 * @generated
 */
public class DriverResourceImpl extends XMLResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public DriverResourceImpl(URI uri) {
		super(uri);
	}

	/**
	 * Returns the task according to the URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated NOT
	 */
	public Task getTask(final String aUriFragment) throws IOException {
		Task lRootTask = ((DocumentRoot) getContents().get(0)).getDriver().getTask();

		if (aUriFragment != null && aUriFragment.length() > 0) {
			String[] lSplitFragment;
			String lUriFragment = aUriFragment;
			int lIndexRoot = aUriFragment.toLowerCase().indexOf(lRootTask.getName().toLowerCase());
			int lStartIndex = 0;

			if (aUriFragment.charAt(0) == '/') {
				lUriFragment = lUriFragment.substring(1);
				if (lUriFragment.charAt(0) == '/') {
					lUriFragment = lUriFragment.substring(1);
				}

				lSplitFragment = lUriFragment.split("/");
			} else {
				lSplitFragment = lUriFragment.split("\\.");
			}

			if (lIndexRoot >= 0) {
				lStartIndex = 1;
			} else {
				Logger.getAnonymousLogger().warning("The root node is incorrect for: " + lSplitFragment[0] + ", should be: " + lRootTask.getName());
			}

			for (int lIter = lStartIndex; lIter < lSplitFragment.length; lIter++) {
				EList lTaskList = lRootTask.getTask();
				boolean lTaskFound = false;
				for (Iterator lTaskIter = lTaskList.iterator(); lTaskIter.hasNext();) {
					Task lTempTask = (Task) lTaskIter.next();
					if (lTempTask.getName().equalsIgnoreCase(lSplitFragment[lIter])) {
						lRootTask = lTempTask;
						lTaskFound = true;
					}
				}

				if (!lTaskFound) {
					throw new IOException("Could not find: " + lSplitFragment[lIter] + " from " + aUriFragment);
				}
			}
		}

		return lRootTask;
	}

} //DriverResourceImpl
