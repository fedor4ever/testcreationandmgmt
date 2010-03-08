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

package com.symbian.driver.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.TimeLimitExceededException;

import junit.framework.TestCase;

import com.symbian.driver.core.controller.tasks.Execute;
import com.symbian.driver.core.controller.tasks.ExecuteOnDevice;
import com.symbian.jstat.JStat;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;

/**
 * @author Engineering Tools
 *
 */
public class ExecuteOnDeviceTest extends TestCase {
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		JStat.getInstance(EngineTests.TRANSPORT, EngineTests.TIMEOUT);
		
		JStatResult lResult = JStat.getInstance().getInfo();
		if (lResult == null || lResult.getReturnedValue() != 13) {
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testCreateRemoveFolderListDrives() {		
		try {
			BufferedReader lBufferedReader = Utils.executeOnDevice(JStat.CREATE_FOLDER, EngineTests.SYMBIAN_DIR, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, EngineTests.SYMBIAN_C, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {EngineTests.SYMBIAN_DIR, Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.REMOVE_FOLDER, EngineTests.SYMBIAN_DIR, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
	
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, EngineTests.SYMBIAN_C, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {EngineTests.SYMBIAN_DIR, Integer.toString(Execute.STATOK)})) {
				fail();
			}
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testGetScreenCapture() {
		try {
			
			BufferedReader lBufferedReader = Utils.executeOnDevice(JStat.GET_SCREEN_CAPTURE, null, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			File[] lScreenCapture = new File("c:\\apps\\stat\\work\\images").listFiles(new FileFilter() {
				public boolean accept(File aFile) {
					if (aFile.lastModified() >= new Date().getTime() - 120000) {
						return true;
					}
					return false;
				}
			});
			
			if (lScreenCapture == null || lScreenCapture.length <= 0) {
				fail();
			} else {
				for (int lIter = 0; lIter < lScreenCapture.length; lIter++) {
					if (lScreenCapture[lIter].delete()) {
						throw new IOException("Could not delete screen capture.");
					}
				}
			}
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}.  */
	public final void testInstallFile(){
		
		try {
			File lSisFile = new File("data\\dependencies\\test.sis");
			String lSymbianSisFile = "c:" + File.separator + "test.sis";
			final String lTestPkg = "test.pkg";
			
			BufferedReader lBufferedReader = Utils.executeOnDevice(JStat.SEND_FILE, lSisFile.getAbsolutePath(), lSymbianSisFile);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.INSTALL_FILE, "c:" + File.separator + "test.sis", null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, "c:\\", null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {lTestPkg})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, "c:\\installDir", null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {lTestPkg})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, "c:\\sys\\bin", null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {lTestPkg})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.UNINSTALL_FILE, "", null);
			if (lBufferedReader.readLine() != "") {
				fail();
			}
	
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, "c:" + File.separator, null);
			if (Utils.readBuffer(lBufferedReader, new String[] {"c:\\installedDir"})) {
				fail();
			}

		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testListDrives() {
		try {
			BufferedReader lBufferedReader = Utils.executeOnDevice(JStat.LIST_DRIVES, null, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {"c,,"})) {
				fail();
			}
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testRunPollKillSyncBeforeTimeout() {
		try {
			
			final ExecuteOnDevice lExecuteOnDevice = new ExecuteOnDevice(JStat.RUN);
			
			new Timer(true).schedule(new TimerTask() {
				public void run() {
					try {
						if (!lExecuteOnDevice.doCleanUp(false)) {
							fail();
						}
					} catch (TimeLimitExceededException e) {
						e.printStackTrace();
						fail();
					} catch (JStatException e) {
						e.printStackTrace();
						fail();
					}
				}
			}, EngineTests.TIMEOUT - 50);
			
			lExecuteOnDevice.doTask(true, EngineTests.TIMEOUT, false);
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testRunPollKillSyncAfterTimeout() {
		try {
			
			final ExecuteOnDevice lExecuteOnDevice = new ExecuteOnDevice(JStat.RUN);
			
			new Timer(true).schedule(new TimerTask() {
				public void run() {
					try {
						if (lExecuteOnDevice.doCleanUp(false)) {
							fail();
						}
					} catch (TimeLimitExceededException e) {
						e.printStackTrace();
						fail();
					} catch (JStatException e) {
						e.printStackTrace();
						fail();
					}
				}
			}, EngineTests.TIMEOUT + 50);
			
			lExecuteOnDevice.doTask(true, EngineTests.TIMEOUT, false);
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testRunPollKillASyncBeforeTimeout() {
		try {
			
			final ExecuteOnDevice lExecuteOnDevice = new ExecuteOnDevice(JStat.RUN);
			
			lExecuteOnDevice.doTask(false, EngineTests.TIMEOUT, false);
			
			Thread.sleep(EngineTests.TIMEOUT - 50);
			
			if (!lExecuteOnDevice.doCleanUp(false)) {
				fail();
			}
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testRunPollKillASyncAfterTimeout() {
		try {
			
			final ExecuteOnDevice lExecuteOnDevice = new ExecuteOnDevice(JStat.RUN);
			
			lExecuteOnDevice.doTask(false, EngineTests.TIMEOUT, false);
			
			Thread.sleep(EngineTests.TIMEOUT + 100);
			
			if (!lExecuteOnDevice.doCleanUp(false)) {
				fail();
			}
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/** Test method for {@link com.symbian.driver.core.controller.tasks.ExecuteOnHost#doTask(boolean, int, boolean)}. */
	public final void testSendRetrieveDeleteListFile() {
		try {
			
			
			BufferedReader lBufferedReader = Utils.executeOnDevice(JStat.SEND_FILE, EngineTests.PC_FILE_1.getAbsolutePath(), EngineTests.SYMBIAN_FILE_1);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, EngineTests.SYMBIAN_C, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {EngineTests.SYMBIAN_FILE_1, Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.RETRIEVE_FILE, EngineTests.SYMBIAN_FILE_1, EngineTests.PC_FILE_DELETE.getAbsolutePath());
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
			
			if (!EngineTests.PC_FILE_DELETE.isFile()) {
				fail();
			}
			if (!EngineTests.PC_FILE_DELETE.delete()) {
				throw new IOException("Could not delete file.");
			} 
	
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, EngineTests.SYMBIAN_C, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {new File(EngineTests.SYMBIAN_FILE_1).getName()})) {
				fail();
			}
			
			lBufferedReader = Utils.executeOnDevice(JStat.DELETE, EngineTests.SYMBIAN_FILE_1, null);
			if (!Utils.readBuffer(lBufferedReader, new String[] {Integer.toString(Execute.STATOK)})) {
				fail();
			}
	
			lBufferedReader = Utils.executeOnDevice(JStat.LIST_FILES, EngineTests.SYMBIAN_C, null);
			if (Utils.readBuffer(lBufferedReader, new String[] {new File(EngineTests.SYMBIAN_FILE_1).getName()})) {
				fail();
			}
			
		} catch (JStatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
	}
	

}
