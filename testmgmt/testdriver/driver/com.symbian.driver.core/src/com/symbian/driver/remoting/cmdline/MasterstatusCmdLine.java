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
 * Class representing the command line 'masterstatus'.
 * 
 * @author EngineeringTools
 */
public class MasterstatusCmdLine extends CmdLine {
		
	/**
	 * Standard constructor. Sets the switch --srv.
	 */
	public MasterstatusCmdLine() {
		super("masterstatus", new CmdOperational() {
			
			public synchronized Object run(final CmdLine aCmd) throws ParseException {
				TDConfig.getInstance().setConfigFromCmdLine(aCmd);
				TestClient lClient = new TestClient();

				lClient.setServerAddress(TDConfig.getInstance().getPreference(TDConfig.SERVER));

				if (lClient.connectToMaster()) {
					lClient.queryMasterStatus();
				}

				return null;
			}
			
		}, "request master status" + " TestDriver system when using TestDriver Remote.");
		
		addSwitch("srv", false, "Remote host/IP address and service name.  (Preference server)", false, true);
	}

}
