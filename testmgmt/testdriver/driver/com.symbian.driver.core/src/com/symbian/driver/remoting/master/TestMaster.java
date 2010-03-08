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



package com.symbian.driver.remoting.master;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;


/**
 * TestMaster hosts the Remote RMI service in its process. It binds the service
 * to the RMI Object Registry from where clients lookup the service.
 * 
 * 
 * @author EngineeringTools
 */
public final class TestMaster {

	
	/** Generic logger. */
	protected static Logger LOGGER = Logger.getLogger(TestMaster.class.getName());

	private static final String SERIALIZE_FOLDER = "." + File.separator + "store";

	/**
	 * 
	 */
	public TestMaster() {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
	}

	/**
	 * Start master.
	 */
	public void start() {

		String bindingName = null;
		String jobsFolder = null;
		String lHostName = null;
		String lServiceName = null;

		// start rmi registry
		/*
		 * The user can specify the ip@ or the host name at config --server
		 */
		try {
			
			TDConfig CONFIG = TDConfig.getInstance();
			lHostName = CONFIG.getPreference(TDConfig.SERVER_NAME);
			lServiceName = CONFIG.getPreference(TDConfig.SERVICE);

			LOGGER.fine( "Host: " + lHostName + " RMI Service: " + lServiceName);

			jobsFolder = CONFIG.getPreferenceFile(TDConfig.JOBS_FOLDER).getAbsolutePath();
			if (lHostName.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {
				LOGGER.fine("Using host ip address for RMI from the config : " + lHostName);
				lHostName = InetAddress.getByName(lHostName).getHostName();
			} 
						
			if (lHostName == null) {
				LOGGER.log(Level.SEVERE, "Could not determine the Host Name. Please check your config.");
				System.exit(-1);
			}
			
			System.getProperties().setProperty("java.rmi.server.hostname",lHostName);
			Registry lRegistry = LocateRegistry.createRegistry(1099);
			LOGGER.fine("Master: RMI registry ready. " + lRegistry);

		} catch (ParseException lE) {
			LOGGER.log(Level.SEVERE, "Master: Can not parse configuration.", lE);
			System.exit(-1);
		} catch (UnknownHostException lUHE) {
			LOGGER.log(Level.SEVERE, "Invalid host name " + lHostName, lUHE);
			System.exit(-1);
		} catch (RemoteException lRemoteException) {
			LOGGER.log(Level.SEVERE, "Master: RMI registry failed to start " + lRemoteException.getMessage(), lRemoteException);
			System.exit(-1);
		}

		File storeFolder = new File(SERIALIZE_FOLDER);
		if (!(storeFolder.isDirectory())) {
			if (!(storeFolder.mkdirs())) {
				LOGGER.log(Level.SEVERE, "Master: Unable to create the store folder." + "Please ensure that the " + SERIALIZE_FOLDER
						+ " folder exists. It is needed by Master for placing system restore files.");
				System.exit(-1);
			}
		}

		try {
			if (ClientRegister.getInstance().needRestore()) {
				LOGGER.info("Restoring Client register.");
				ClientRegister.getInstance().restoreSnapshot();
			}
			if (JobTracker.getInstance().needRestore()) {
				LOGGER.info("Restoring Job tracker.");
				JobTracker.getInstance().restoreSnapshot();
			}
			JobCounter jobCounter = new JobCounter();
			if (jobCounter.needRestore()) {
				LOGGER.info("Restoring Job counter.");
				jobCounter.restoreSnapshot();
			}
			QueuedExecutor queuedExecutor = new QueuedExecutor();
			if (queuedExecutor.needRestore()) {
				LOGGER.info("Restoring execution queue.");
				queuedExecutor.restoreSnapshot();
			}
			bindingName = "//" + lHostName + "/" + lServiceName;
			MasterRemote master = new MasterRemoteImpl(jobsFolder, jobCounter, queuedExecutor);
			Naming.rebind(bindingName, master);
			LOGGER.info("Remote Service Name : " + bindingName);
		} catch (RemoteException lRE) {
			LOGGER.log(Level.SEVERE, "Master: Problem with contacting the RMI Registry: ", lRE);
			System.exit(-1);
		} catch (IOException lE) {
			LOGGER.log(Level.SEVERE, "Master: Problem with starting up TestMaster: ", lE);
			System.exit(-1);
		}
	}

	/**
	 * @param args
	 */
	public static void startRemoteMaster() {
		LOGGER.fine("TestMaster initialising.");
		TestMaster testmaster = new TestMaster();
		LOGGER.info("TestMaster starting up.");
		testmaster.start();
		LOGGER.info("TestMaster running.");
	}
}
