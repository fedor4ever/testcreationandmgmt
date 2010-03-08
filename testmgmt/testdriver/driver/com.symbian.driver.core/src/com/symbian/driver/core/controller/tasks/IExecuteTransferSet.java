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

package com.symbian.driver.core.controller.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.naming.TimeLimitExceededException;

public interface IExecuteTransferSet extends Set<IExecuteTransfer> {

	/**
	 * Creates a repository with all the files in transfer.
	 * @param aUid The UID of current repository
	 * 
	 * @throws IOException
	 *             If the locations of any of the files are invalid.
	 */
	public abstract void createRepository(final String aUid) throws IOException;

	/**
	 * Create a SIS file with a PKG file.
	 * 
	 * @param aEpocRoot
	 *            The EPOCROOT directory to use when creating the SIS files
	 * @param aSisPcPath
	 *            The location of where to start the SIS files to
	 * @param aCert
	 *            The Certificate location to sign the SIS file with.
	 * @param aKey
	 *            The Key location to encrypt the SIS file with.
	 * @param aSisPackage
	 * 			  The package file to be used to create the SIS file.
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 * @throws FileNotFoundException
	 */
	public abstract void createSis(final File aEpocRoot, final File aSisPcPath, final File aCert, final File aKey)
			throws IOException, TimeLimitExceededException, FileNotFoundException;

	/**
	 * Installs a SIS file to the Symbian device. Use if Platform Security
	 * (PlatSec) is on.
	 * 
	 * @param aSisFile
	 *            The sis file to set the package to.
	 * @param aTimeOut
	 *            The length of time before the installation of the SIS file is
	 *            terminated.
	 * @throws IOException
	 *             If the SIS file location on the PC is invalid.
	 * @throws JStatException
	 *             If the transfering/installing of the SIS file causes an
	 *             exception.
	 * @throws TimeLimitExceededException
	 *             If the aTimeOut is exceeded.
	 * @throws JStatException 
	 */
	public abstract void installSis(final File aSisFile, final int aTimeOut) throws IOException,
			TimeLimitExceededException;

	/**
	 * Transfers the repository files to the Symbian device. Use if Platform
	 * Security (PlatSec) is off.
	 * 
	 * @throws IOException
	 *             If the repository files are invaild.
	 * @throws TimeLimitExceededException 
	 */
	public abstract void installRepository() throws IOException, TimeLimitExceededException;

	/**
	 * Confirms if a Package file has been installed by iterationg through the package for each file.
	 * 
	 * @return <code>true</code> if the Package File has been installed, <code>false</code> otherwise.
	 */
	public abstract boolean isPackageInstalled();

	/**
	 * Uninstalls the SIS file and deletes any transfered files from the Symbian
	 * device.
	 * 
	 * @param aTimeOut
	 *            The length of time before the uninstall command should
	 *            terminate. If equal to or less than zero there is no timeout.
	 * @throws JStatException 
	 * @throws TimeLimitExceededException 
	 * @throws JStatException 
	 */
	public abstract void uninstall(final int aTimeOut) throws TimeLimitExceededException;

	/**
	 * Get the name of the ExecuteTransferSet.
	 * 
	 * @return The Name of the ExecuteTransferSet
	 */
	public abstract String getName();
}