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


package com.symbian.driver.core.extension;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface IDeviceComms {
	
		void stop(boolean isEmulator);
			
		/**
		 * isDeviceAlive : checks if the device is responding.
		 * @return : boolean : true/false
		 */
		boolean isDeviceAlive();
		/**
		 *  rebootDevice: soft reboot of the device.
		 * @return boolean : success/fail
		 */
		boolean rebootDevice();
		
		/**
		 * startCommsServer : 
		 * @param isEmulator
		 * @return
		 */
		boolean startCommsServer(boolean isEmulator);
		
		
		/**
		 * createSymbianTransfer : create an object to transfer files between PC and device
		 * @return ISymbianTransfer
		 */
		ISymbianTransfer createSymbianTransfer();
		
		/**
		 * 
		 * @author hocineadjerid
		 * ISymbianTransfer : interface defines the supported operations
		 */
		interface ISymbianTransfer {
			/**
			 * send : sends aHostFile to aSymbianFile destination
			 * @param aHostFile
			 * @param aSymbianFile
			 * @return boolean : success/fail
			 */
			boolean send(File aHostFile, File aSymbianFile);
			/**
			 * retrieve : retrive a SymbianFile to aHostFile destination.
			 * @param aSymbianFile
			 * @param aHostFile
			 * @return boolean : success/fail
			 */
			boolean retrieve(File aSymbianFile, File aHostFile);
			/**
			 * mkdir : create a directory on the device
			 * @param aSymbianDir
			 * @return boolean : success/fail
			 */
			boolean mkdir(File aSymbianDir);
			/**
			 * delete : delete file(s) on the device
			 * @param aSymbianFile
			 * @param aIsFolder
			 * @return boolean : success/fail
			 */
			boolean delete(File aSymbianFile, boolean aIsFolder);
			/**
			 * dir : list file(s) on the device
			 * @param aDir
			 * @return List<File>
			 */
			List<File> dir(File aDir);
			/**
			 * move : move a file on the device.
			 * @param aSymbianSource
			 * @param aSymbianDestination
			 * @return boolean : success/fail
			 */
			boolean move(File aSymbianSource, File aSymbianDestination);
			/**
			 * cd : change directory
			 * @param aDir
			 * @return boolean : success/fail
			 */
			boolean cd(File aDir);
			/**
			 * pwd : get working directory.
			 * @return File aFile.
			 */
			File pwd();
			List<String> listDrives();
		}

		
		/**
		 * createSymbianProcess : creates a symbian process to execute commands on the device.
		 * @return ISymbianProcess
		 */
		ISymbianProcess createSymbianProcess();
		
		/**
		 * 
		 * @author hocineadjerid
		 * ISymbianProcess : interface defines the supported remote commands.
		 */
		interface ISymbianProcess {

			/**
			 * install : installs a sis package.
			 * @param aSymbianFile
			 * @param pkgFile 
			 * @return boolean : success/fail.
			 */
			boolean install(File aSymbianFile, File pkgFile);
			/**
			 * uninstall : uninstall a package
			 * @param aUid
			 * @return boolean : success/fail
			 */
			boolean uninstall(String aUid);
			/**
			 * runCommand : runs a command on the device.
			 * @param aCommand
			 * @param aArguments
			 * @param aTimeout
			 * @param aWait
			 * @return boolean : success/fail.
			 */
			boolean runCommand(String aCommand,List<String> aArguments, int aTimeout, boolean aWait);
			/**
			 * getInputStream : get remote process input stream.
			 * @return InputStream
			 * @throws UnsupportedOperationException
			 */
			InputStream getInputStream() throws UnsupportedOperationException;
			/**
			 * getErrorStream : get remote process error stream.
			 * @return InputStream.
			 * @throws UnsupportedOperationException
			 */
			InputStream getErrorStream() throws UnsupportedOperationException;
			/**
			 * getOutputStream: get remote process output stream.
			 * @return OutputStream.
			 * @throws UnsupportedOperationException
			 */
			OutputStream getOutputStream() throws UnsupportedOperationException;
			/**
			 * join : wait for a process to end.
			 * @return boolean : success/fail
			 */
			boolean join();
			/**
			 * stop : stop a remote process
			 * @return boolean : success/fail
			 */
			boolean stop();
			boolean captureScreen();
		}

}