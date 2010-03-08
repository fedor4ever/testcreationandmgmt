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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import com.nokia.s60tools.testdrop.engine.value.EmulatorTestResultValue;
import com.nokia.s60tools.testdrop.engine.value.TestResultValue;
import com.nokia.s60tools.testdrop.util.TestReportModifier;

/**
 * 
 * Storage for Result editor view. Implements IStorage interface.
 * 
 */
public class ResultStorage implements IStorage {
	TestResultValue result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getStorage()
	 */
	public ResultStorage(TestResultValue result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getContents()
	 */
	public InputStream getContents() throws CoreException {
		if (result instanceof EmulatorTestResultValue) {
			try {
				String transformedTestResultValue = TestReportModifier.transformReport(result);
				if (transformedTestResultValue != null) {
					return new ByteArrayInputStream(transformedTestResultValue.getBytes());
				}
				else {
					return null;
				}
			}
			catch (ParseException ex) {
				return null;
			}
		}
		else {
			return new ByteArrayInputStream(result.getResult().getBytes());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getFullPath()
	 */
	public IPath getFullPath() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getName()
	 */
	public String getName() {
		return result.getTestNameAndTestId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#isReadOnly()
	 */
	public boolean isReadOnly() {

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IStorage#getAdapter()
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		return null;
	}

}
