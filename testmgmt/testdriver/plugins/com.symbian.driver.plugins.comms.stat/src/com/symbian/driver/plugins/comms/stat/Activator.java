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


package com.symbian.driver.plugins.comms.stat;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.symbian.driver.core.controller.tasks.ExecuteOnHost;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.jstat.JStatException;
import com.symbian.utils.JarUtils;
import com.symbian.utils.Utils;

public class Activator implements BundleActivator, IDeviceComms {

	/** Logger for this class. */
	private static final Logger LOGGER = Logger.getLogger(Activator.class.getName());

	ISymbianTransfer iTransfer = null;

	ISymbianProcess iProcess = null;

	/** The file \epoc32\WINSCW\c\system\data\stat.ini. */
	private File iStatIniFile = null;

	private File iWinscwReleaseDir = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

	/**
	 * createSymbianProcess: get a new process to run commands on the device.
	 * 
	 * @return : a StatProcess object
	 * 
	 */
	public ISymbianProcess createSymbianProcess() {
		if (iProcess == null) {
			iProcess = new StatProcess();
		}
		return iProcess;
	}

	/**
	 * createSymbianTransfer : get a new transfer to transfer files between PC
	 * and device.
	 * 
	 * @return : a StatTransfer object
	 */
	public ISymbianTransfer createSymbianTransfer() {
		if (iTransfer == null) {
			iTransfer = new StatTransfer();
		}
		return iTransfer;
	}

	/**
	 * isDeviceAlive: polls the device
	 */
	public boolean isDeviceAlive() {
		try {
			new StatProxy().getStat().getInfo();
			return true;
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE, "STAT exception", lJStatException);
		}
		return false;
	}

	/**
	 * rebootDevice : reboot the device. This is only for soft reboot.
	 */
	public boolean rebootDevice() {
		try {
			new StatProxy().getStat().restartBoard();
			return true;
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE, "STAT exception", lJStatException);
		}
		return false;
	}

	public boolean startCommsServer(boolean isEmulator) {
		if (isEmulator) {
			try {
				String lTransport = "tcp:192.168.0.3:3000";
				String WINTAP_DEFAULT_PORT = ":3000";
				lTransport = "tcp:" + TDConfig.getInstance().getPreference(TDConfig.WINTAP) + WINTAP_DEFAULT_PORT;
				TDConfig.getInstance().setPreference(TDConfig.TRANSPORT, lTransport);

				File lEpocRoot = TDConfig.getInstance().getPreferenceFile(TDConfig.EPOC_ROOT);
				String lVariant = TDConfig.getInstance().getPreference(TDConfig.VARIANT);
				iWinscwReleaseDir = new File(lEpocRoot, "/epoc32/RELEASE/WINSCW/" + lVariant);
				File iWinscwCDir = new File(lEpocRoot, "/epoc32/WINSCW/c/");
				iStatIniFile = new File(iWinscwCDir, "/system/data/stat.ini");

				// Backup Old Stat.ini + Install new Stat.ini
				try {
					backupFile(iStatIniFile);
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, lIOException.getMessage());
					// continue anyway.
				}

				if (!iStatIniFile.getParentFile().isDirectory() && !iStatIniFile.getParentFile().mkdirs()) {
					LOGGER.log(Level.SEVERE, "Could not create folder for new stat.ini file.");
					return false;
				}

				extractResourceFile("/resource/stat.ini", iStatIniFile);

				// Start STATApi and Poll Device
				for (int lPoll = 3; lPoll > 0; lPoll--) {
					DeviceUtils.killAllEpocs();

					// Start STATApi
					ExecuteOnHost iEpoc = new ExecuteOnHost(iWinscwReleaseDir, "statapi.exe");
					iEpoc.doTask(false, TimeOut.NO_TIMEOUT, false);
					LOGGER.info("Started Symbian Device and polling with STAT \"Device Info\".");

					// Poll STATApi
					try {
						if (DeviceUtils.poll(iEpoc, 10)) {
							return true;
						} else {
							if (DeviceUtils.pollStopped()) {
								break;
							}
							LOGGER.log(Level.WARNING, "Failed to poll the device. Will retry " + lPoll + " more times.");
						}
					} catch (Exception lException) {
						LOGGER.log(Level.SEVERE, "Failed to start the emulator.", lException.getMessage());
					}
				}
			} catch (Exception lException) {
				LOGGER.log(Level.SEVERE, "Failed to start the emulator.", lException.getMessage());
			}
		} else {
			
		}
		return false;
	}

	/**
	 * @param lFile
	 * @return The backed up file.
	 * @throws IOException
	 */
	private static final File backupFile(File lFile) throws IOException {
		if (lFile.isFile()) {

			LOGGER.info("Backup file: " + lFile);
			File lFileBackup = new File(lFile.getCanonicalPath() + ".backup");

			if (!lFile.renameTo(lFileBackup)) {

				LOGGER.warning("Overwriting previous backup file at: " + lFileBackup + "; with: " + lFile);
				lFileBackup.delete();

				if (!lFile.renameTo(lFileBackup)) {
					throw new IOException("Could not backup old file: " + lFile);
				}

			}

			return lFileBackup;
		}

		return null;
	}

	/**
	 * @param aResource
	 * @param aOldFile
	 * @return The newly installed file.
	 * @throws IOException
	 */
	private static final File extractResourceFile(String aResource, File aOldFile) throws IOException {
		File lNewFile = JarUtils.extractResource(Activator.class, aResource);
		LOGGER.info("Installing new file: " + lNewFile);
		Utils.copy(lNewFile, aOldFile);

		return lNewFile;
	}

	public void stop(boolean isEmulator) {
		if (isEmulator) {
			restoreFile(iStatIniFile);
		}
	}

	/**
	 * @param lNewFile
	 */
	public static final void restoreFile(File lNewFile) {
		LOGGER.info("Restoring old file: " + lNewFile);
		File lBackupFile = new File(lNewFile, ".backup");
		if (lBackupFile.isFile()) {
			try {
				if ((lNewFile.exists() && !lNewFile.delete()) || !new File(lNewFile.getCanonicalPath() + ".backup").renameTo(lNewFile)) {
					LOGGER.log(Level.SEVERE, "Could not restore file: " + lNewFile.toString());
				}
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Could not restore file: " + lNewFile.toString(), lIOException);
			}
		} else {
			LOGGER.log(Level.FINE, "Could not find file to restore: " + lNewFile.toString());
		}
	}

}
