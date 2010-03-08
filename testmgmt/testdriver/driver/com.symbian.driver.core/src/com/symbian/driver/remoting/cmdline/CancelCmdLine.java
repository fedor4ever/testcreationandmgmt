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



package com.symbian.driver.remoting.cmdline;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.client.TestClient;
import com.symbian.utils.cmdline.CmdLine;
import com.symbian.utils.cmdline.CmdOperational;

/**
 * Class representing the command line 'cancel'.
 * 
 * @author EngineeringTools
 */
public class CancelCmdLine extends CmdLine {
	
	/**
	 * Standard constructor. Sets the switches -j and --srv.
	 */
	public CancelCmdLine() {
		super("cancel", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) throws ParseException {
				TDConfig CONFIG = TDConfig.getInstance();
				CONFIG.setConfigFromCmdLine(aCmd);
				TestClient lClient = new TestClient();

				int lTestJobId = CONFIG.getPreferenceInteger(TDConfig.JOB_ID);
				if (CONFIG.getPreference(TDConfig.SERVER) != null) {
					lClient.setServerAddress(CONFIG.getPreference(TDConfig.SERVER));
				}

				lClient.connectToMaster();
				lClient.terminateJob(lTestJobId);

				return null;
			}
			
		}, "cancel a remote job");
		
		addSwitch("j", true, "Unique Job ID. (Preference jobId)", false, true);
		addSwitch("srv", false, "Remote host/IP address and service name.  (Preference server)", false, true);
	}
}
