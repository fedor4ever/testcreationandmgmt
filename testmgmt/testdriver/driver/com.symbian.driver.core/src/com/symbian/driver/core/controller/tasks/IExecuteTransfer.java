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
import java.io.IOException;

import javax.naming.TimeLimitExceededException;

public interface IExecuteTransfer {

	/** Literal for end of line: ie \r\n or \n. */
	public static final String EOL = System.getProperty("line.separator");

	/**
	 * Install with Platform Security Off. If Platform Security is ON this task
	 * will fail.
	 * 
	 * @see com.symbian.driver.core.controller.tasks.IExecute#doTask(boolean, int, boolean)
	 * 
	 * @param aDoCleanUp
	 *            For transfer this boolean is ignored as it doesn't make sense
	 *            to tranfer a file and then immediatly delete/uninstall the
	 *            file.
	 
	 * @throws IOException
	 * @throws JStatException 
	 * @throws JStatException 
	 * @throws TimeLimitExceededException 
	 */
	public abstract void doTask(final boolean aDoCleanUp, final int aTimeout) throws IOException,
			TimeLimitExceededException;

	/**
	 * Uninstalls the file for Platform Security off.
	 * 
	 * @param aWaitForTimeout This parameter is ignored for ExecuteTransfer as it doesn't make sense
	 * @return The result of the Uninstall.
	 * @throws JStatException
	 * @throws TimeLimitExceededException 
	 */
	public abstract boolean doCleanUp(final boolean aWaitForTimeout) throws TimeLimitExceededException;

	/**
	 * @return The path to the file to transfer on the PC.
	 */
	public abstract File getPCPath();

	/**
	 * @return The path of the file to transfer on the Symbian device (Hardware
	 *         or Emulator)
	 */
	public abstract String getSymbianPath();

	/**
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * @see com.symbian.driver.core.controller.tasks.IExecute#isRunning()
	 */
	public abstract boolean isRunning();

	public abstract String getOutput();

}