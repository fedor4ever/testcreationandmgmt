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

package com.symbian.driver.core.processors;

import java.io.File;
import java.io.IOException;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

/**
 * @author EngineeringTools
 * 
 */
public interface PreProcessor extends Processor {
	/*
	 * Must be able to use variables. Configuration scripts/xml files must be
	 * VERY small and simple. Must be extensible, i.e. plugins or adapters.
	 * Everything will use TestDriver config. so must be setup first.
	 * 
	 * Already installed components must be TestDriver (Java), CBR (Perl)
	 * 
	 * PRE 
	 * - configureTestDriver(File aTestDriverConfigXmlFile) generic + specific(HW + SW)
	 * - cleanEnvironment()									generic
	 * - configureEnvironment(String aCBRRelease)			generic
	 * - configureCommDB(File  aCommDBXmlOrCfgFile) 		HW + SW
	 * - configureStat(File aStatIniFile)					HW + SW
	 * - configureTestExecute(File aTestExecuteIni)			generic
	 * - buildRom()											generic + specific(SW)
	 * - startSymbian()										HW + SW
	 * - transferRom()										HW
	 * 
	 * DURING
	 * - pollSymbian()										generic
	 * - restartSymbian()									HW + SW
	 * + Liseteners											generic
	 * + Failure behaviour generic
	 * + Results/logs to ftp/server/etc... => Logger style	generic
	 * 
	 * POST
	 * - cleanEnvironment()									generic
	 * - processResults(URL aResultPath)					generic
	 */

	/**
	 * @return 
	 * @throws ParseException
	 * @throws IOException
	 * @throws JStatException
	 * @throws InterruptedException
	 * @throws TimeLimitExceededException
	 */
	public boolean start() throws ParseException, IOException, InterruptedException,
			TimeLimitExceededException;


	/**
	 * 
	 */
	public void stop();
	
	/**
	 * Restarts the Symbian device (HW and SW). This method stops the board,
	 * waits 10 seconds and restarts the board. It then polls the board for
	 * liveness. If after 20 polls nothing occurs false is returned.
	 * 
	 * @return <code>true</code> if the board is live after 20 polls,
	 *         <code>false</code> otherwise.
	 */
	public boolean restart();

	/**
	 * @param aRDebugOutput
	 * @throws IOException
	 */
	public void setRDebugOuput(File aRDebugOutput) throws IOException;

	/**
	 * 
	 */
	public void restoreRDebugOutput();
}
