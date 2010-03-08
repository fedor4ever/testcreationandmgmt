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



package com.symbian.driver.remoting.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.remoting.master.MasterRemote;

/**
 * Class to monitor a job submited to master
 * 
 * @author EngineeringTools
 */
public final class SubmissionMonitor {

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(SubmissionMonitor.class.getName());

	private static final int DELAY_TIME = 1000;

	/** the //server/service name */
	private final String lookupName;

	/** Test job id */
	private final int testJobId;

	/** the first delay time before we start monitoring a job */
	private final int seconds;

	/**
	 * number of attempt to try to query a job status with no success before
	 * giving up
	 */
	private final int attempts;

	private int count;

	private Timer timer = null;

	/**
	 * Standard Constructor
	 * 
	 * @param aTestJobId
	 *            an int test job id.
	 * @param aSeconds
	 *            an int time in seconds between attempts to get a job status.
	 * @param aAttempts
	 *            an int number of attempts with no success before giving up.
	 * @throws ParseException
	 */
	public SubmissionMonitor(int aTestJobId, int aSeconds, int aAttempts) throws ParseException {
		testJobId = aTestJobId;
		seconds = aSeconds;
		attempts = aAttempts;
		count = attempts;
		lookupName = TDConfig.getInstance().getPreference(TDConfig.SERVER);
	}

	/**
	 * Start a timer
	 * 
	 */
	public void start() {
		timer = new Timer();
		timer.schedule(new SubmissionCheckTask(), seconds * DELAY_TIME, seconds * DELAY_TIME);
	}

	/**
	 * stop a timer
	 */
	public void stop() {
		timer.cancel();
		timer = null;
	}

	/**
	 * Timer task to check if the job has completed
	 * 
	 * @author EngineeringTools
	 */
	class SubmissionCheckTask extends TimerTask {
		public void run() {
			try {
				MasterRemote lMaster = (MasterRemote) Naming.lookup(lookupName);
				String lQuery = lMaster.queryJobStatus(testJobId);
				/*  need to check in wich status should stop the monitoring */
				LOGGER.fine("Monitoring: " + lQuery);
				count = attempts;
			} catch (RemoteException lE) {
				handleException(lE);
			} catch (MalformedURLException lE) {
				handleException(lE);
			} catch (NotBoundException lE) {
				handleException(lE);
			}
		}

		private void handleException(Exception lE) {
			LOGGER.info("Client: Master is not responding.");
			if (count == 0) {
				/*
				 * Ok there is nothing we can do, we stop the monitoring. this
				 * does not mean the job has failed, the user will need to use
				 * jobstatus and jobresults seprately.
				 */
				String lMessage = "Client: Master appears to be having problems." + "\nClient: Switching to async mode." + "Client: Your job ID is: "
						+ testJobId;
				LOGGER.log(Level.SEVERE, lMessage, lE);
				stop();
			}
			--count;
			LOGGER.info("Client: Will try again in " + seconds + " seconds.");
		}
	}
}
