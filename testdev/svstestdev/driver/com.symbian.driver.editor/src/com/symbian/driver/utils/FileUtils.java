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

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

import com.symbian.driver.presentation.DriverEditorPlugin;

public class FileUtils {

	/**
	 * scanDirectory : returns the file paths with a specified extention
	 * 
	 * @param aResource :
	 *            an IRsource location
	 * @param aFindString :
	 *            an extension
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> scanDirectory(IResource aResource,
			final String aFindString) {
		final ArrayList<String> lFileList = new ArrayList<String>();

		try {
			aResource.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (resource.getFileExtension() != null
							&& resource.getFileExtension().equalsIgnoreCase(
									aFindString.toLowerCase())) {
						lFileList.add(resource.getLocation().toFile()
								.getAbsolutePath());
					}
					return true;
				}
			});
		} catch (CoreException lCoreException) {
			DriverEditorPlugin.getPlugin().log(lCoreException);
		}

		return lFileList;
	}

}
