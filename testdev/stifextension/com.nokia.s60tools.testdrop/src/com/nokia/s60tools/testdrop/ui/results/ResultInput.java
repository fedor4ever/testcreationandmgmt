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


package com.nokia.s60tools.testdrop.ui.results;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * 
 * Input data for Result editor view. Implements IStorageEditorInput interface.
 * 
 */
public class ResultInput extends PlatformObject implements IEditorInput {
	private IStorage storage;

	/**
	 * Constructor
	 * 
	 * @param storage
	 */
	public ResultInput(IStorage storage) {
		this.storage = storage;
	}

	/**
	 * Returns IStorage instance
	 * 
	 * @return IStorage instance
	 * @throws CoreException
	 */
	public IStorage getStorage() throws CoreException {
		return storage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorInput#exists()
	 */
	public boolean exists() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorInput#getName()
	 */
	public String getName() {
		return storage.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return storage.getName();
	}
}
