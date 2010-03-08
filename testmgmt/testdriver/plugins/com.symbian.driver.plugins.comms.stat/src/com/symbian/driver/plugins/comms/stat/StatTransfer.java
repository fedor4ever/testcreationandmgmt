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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.environment.TimeOut;
import com.symbian.driver.core.extension.IDeviceComms;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;
import com.symbian.utils.Epoc;

public class StatTransfer implements IDeviceComms.ISymbianTransfer {
	
	/** Singleton to STAT. */
	private static final Logger LOGGER = Logger.getLogger(StatTransfer.class.getName());
	
	private StatProxy iStatProxy = new StatProxy();

	
	/* (non-Javadoc)
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#cd(java.io.File)
	 */
	public boolean cd(File aDir) {
		throw new UnsupportedOperationException("Change Directory is not supported by STAT.");
	}

	/* (non-Javadoc)
	 * @see com.symbian.driver.core.extension.IDeviceComms.ISymbianTransfer#delete(java.io.File, boolean)
	 */
	public boolean delete(File aSymbianFile, boolean aIsFolder) {
		LOGGER.info("Deleting file/folder " + aSymbianFile.toString());
		JStatResult lResult = null;
		String lDir = aSymbianFile.toString().replaceAll("/", "\\");
		try {
			if (aIsFolder) {
				lResult = iStatProxy.getStat().removeFolder(lDir);
			} else {
				lResult = iStatProxy.getStat().delete(lDir);
			}
			
			if (lResult.getReturnedValue() == 13) {
				return true;
			}

		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		}
		LOGGER.log(Level.SEVERE, "Failed to delete file " + aSymbianFile + ((lResult != null) ? " : " + lResult.toString() : ""));
		return false;
	}

	public List<File> dir(File aDir) {
		LOGGER.info("Listing folder " + aDir.toString());
		JStatResult lResult = null;
		String lDir = aDir.toString().replaceAll("/", "\\");
		List<File> lFilesList = new LinkedList<File>();
		try {			
			lResult = iStatProxy.getStat().listFiles(lDir);			
			if (lResult.getReturnedValue() == 13
					&& lResult.getReceivedData() != null
					&& !lResult.getReceivedData().equalsIgnoreCase("")) {
				String lReceivedData = lResult.getReceivedData();
				String[] lReceivedDataSplit = lReceivedData.split("\r\n");
				for (String lLine : lReceivedDataSplit) {
					//Stat dir returns something like 
					//	private,16,21/12/2007 12:00,0
					//	System,16,21/12/2007 12:00,0
					//	BtPlatformPluginConfigurator.dat,32,21/12/2007 12:00,8
					//	statoutput.log,32,21/12/2007 12:02,2180
					
					String[] lFields  = lLine.split(",");
					String lFileName = lFields[0];
					lFilesList.add(new File(lDir,lFileName));
				}
				return lFilesList;
			}			
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		}
		LOGGER.log(Level.SEVERE, "Failed to list file/folder " + aDir + ((lResult != null) ? " : " + lResult.toString() : ""));
		return lFilesList;
	}

	public boolean mkdir(File aSymbianDir) {
		LOGGER.info("Creating folder " + aSymbianDir.toString());
		JStatResult lResult = null;
		String lDir = aSymbianDir.toString().replaceAll("/", "\\");
		try {
			lResult = iStatProxy.getStat().createFolder(lDir);
			
			if (lResult.getReturnedValue() == 13) {
				return true;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		}
		LOGGER.log(Level.SEVERE, "Failed to create folder " + aSymbianDir + ((lResult != null) ? " : " + lResult.toString() : ""));
		return false;
	}

	public boolean move(File aSymbianSource, File aSymbianDestination) {
		LOGGER.info("Moving file from " + aSymbianSource + " to " + aSymbianDestination);
		JStatResult lResult = null;
		try {
			lResult = iStatProxy.getStat().rename(aSymbianSource.toString().replaceAll("/", "\\"), aSymbianDestination.toString().replaceAll("/", "\\"));
			
			if (lResult.getReturnedValue() == 13) {
				return true;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		}
		LOGGER.log(Level.SEVERE, "Failed to move file " + aSymbianSource + " to " + aSymbianDestination + ((lResult != null) ? " : " + lResult.toString() : ""));
		return false;
	}

	public File pwd() {
		throw new UnsupportedOperationException("STAT does not support print working directory (PWD).");
	}

	public boolean retrieve(File aSymbianFile, File aHostFile) {
		LOGGER.info("Retrieve file " + aSymbianFile + " to " + aHostFile);
		boolean lIs81b = false;
		boolean lIs92plus = true;
		boolean lResult = false;
		try {
			lIs81b = TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER).endsWith("8.1b");
			lIs92plus = Epoc.is92plus(TDConfig.getInstance().getPreference(TDConfig.BUILD_NUMBER));
		} catch (ParseException lParseException) {
			LOGGER.log(Level.WARNING, "Could not get Build Number. So assuming v9.2+", lParseException);
		}

		while (true) {
			try {
	
				try {
					Thread.sleep(TimeOut.RESTART_WAIT);
				} catch (InterruptedException lInterruptedException) {
					//do nothing, try anyway.
				}
	
				iStatProxy.getStat().retrieveFile(aSymbianFile.toString().replaceAll("/", "\\"), aHostFile);				
				// file exist and process finished.
				lResult = true;
				break;
	
			} catch (TimeLimitExceededException lTimeLimitExceededException) {
				LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
				break;
			} catch (JStatException lJStatException) {
				if (lJStatException.getResult() != null
						&& lIs81b
						&& lJStatException.getResult().getErrorMessage().indexOf("155") != -1) {
					
						continue;
						
				} else if (lIs92plus
						|| lJStatException.getResult() == null
						|| lJStatException.getResult().getErrorMessage().indexOf("14") == -1) {
					
					// an error happened
					LOGGER.log(Level.SEVERE, "Could not retrive file: " + aSymbianFile , lJStatException);
					lResult = false;
					break;
				}
				
				// the file exists but is in use, so try again.
				continue;
	
			}
		}
		
		return lResult;
	}

	/**
	 * Create dir if doesn't exists before transfer
	 * This function will be called only when platsec is off 
	 */
	private boolean checkDirExists(File aSymbianFile) 
	    throws TimeLimitExceededException,  JStatException {
		String symbianPath = "";
		try {
			symbianPath = aSymbianFile.getPath();
			if (symbianPath == null || symbianPath.length()==0) {
				return true;
			}
			symbianPath = symbianPath.replaceAll("/", "\\");
			if (symbianPath.lastIndexOf("\\") <= 0) {
				return true; //root
			}
			symbianPath = symbianPath.substring(0, symbianPath.lastIndexOf("\\"));
			iStatProxy.getStat().checkLocation(symbianPath);
			return true;
		} catch (JStatException lJStatException) {
			JStatResult lResult = lJStatException.getResult();
			if (lResult.getErrorMessage().indexOf("Device return code -1") > 0) {
				//the dir doesn't exists. need to create
				LOGGER.info("Creating folder before transfer file:" + symbianPath);
				iStatProxy.getStat().createFolder(symbianPath);
				return true;
			}
		} 
		return false;
	}
	
	public boolean send(File aHostFile, File aSymbianFile) {
		LOGGER.info("Sending file " + aHostFile + " to " + aSymbianFile);
		JStatResult lResult = null;
		
		try {
			
			// for file transfer, we need to check if the directory is exists.
			checkDirExists(aSymbianFile);
			
			lResult = iStatProxy.getStat().sendFile(aHostFile, aSymbianFile.toString().replaceAll("/", "\\"));
			
			if (lResult.getReturnedValue() == 13) {
				return true;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		} 
		
		LOGGER.log(Level.SEVERE, "Failed to send file " + aHostFile + " to " + aSymbianFile + ((lResult != null) ? " : " + lResult.toString() : ""));
		return false;
	}

	public List<String> listDrives() {
		LOGGER.info("List Drives");
		List<String> lDrivesList = new LinkedList<String>();
		JStatResult lResult = null;
		try {
			lResult = iStatProxy.getStat().listDrives();
			
			if (lResult.getReturnedValue() == 13
					&& lResult.getReceivedData() != null
					&& !lResult.getReceivedData().equalsIgnoreCase("")) {
				String lReceivedData = lResult.getReceivedData();
				String[] lReceivedDataSplit = lReceivedData.split("\r\n");
				for (String lLine : lReceivedDataSplit) {
						lDrivesList.add(lLine);
				}
				return lDrivesList;
			}
		} catch (TimeLimitExceededException lTimeLimitExceededException) {
			LOGGER.log(Level.SEVERE, "Time limit exeeded", lTimeLimitExceededException);
		} catch (JStatException lJStatException) {
			LOGGER.log(Level.SEVERE,"STAT exception", lJStatException);
		}
		LOGGER.log(Level.SEVERE, "Failed to list drives : " + ((lResult != null ) ? lResult.toString() : ""));	
		return lDrivesList;		
	}
}
