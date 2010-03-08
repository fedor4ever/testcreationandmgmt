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

package com.symbian.driver.core.cmdline;

import com.symbian.driver.remoting.master.TestMaster;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * @author EngineeringTools
 *
 */
public class MasterCmdLine extends CmdLine {

	/**
	 * 
	 */
	public MasterCmdLine() {
		super("master", new CmdOperational() {
			
			public synchronized Object run(CmdLine aCmd) {
				
				TestMaster.startRemoteMaster();
				
				return aCmd;
			}
		}, "Run TestDriver Remote Master.");
	}

}
