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

package com.symbian.driver.core.extension;

import java.io.File;
import java.util.Map;

/**
 * @author Development Tools
 * 
 */
public interface ISymbianRepository {

	/**
	 * @param aUID
	 * @param aName
	 * @param aRepositoryDirectory
	 * @param aPkgFile
	 * @param aSisFile
	 * @param aEpocRoot
	 * @param aCert
	 * @param aKey
	 * @return
	 */
	ISymbianRepository createRepositoryAndSis(final int aUID, final String aName,
			final File aRepositoryDirectory, final File aPkgFile,
			final File aSisFile, final File aEpocRoot, final File aCert,
			final File aKey);

	/**
	 * @param aUID
	 * @param aSisFile
	 * @return
	 */
	ISymbianRepository setSisFile(final int aUID, final File aSisFile);

	/**
	 * @param aFileSet
	 * @return
	 */
	ISymbianRepository setFileSet(Map<File, File> aFileSet);

	/**
	 * @return
	 */
	boolean install(boolean aIsPlatSec);

	/**
	 * @return
	 */
	boolean uninstall();

}