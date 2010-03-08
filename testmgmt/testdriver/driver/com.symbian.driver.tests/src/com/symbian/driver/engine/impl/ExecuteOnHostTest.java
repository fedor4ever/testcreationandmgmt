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


package com.symbian.driver.engine.impl;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.TimeLimitExceededException;

import junit.framework.TestCase;

import com.symbian.driver.core.controller.tasks.IExecuteOnHost;
import com.symbian.driver.engine.util.ExecuteFactory;
import com.symbian.driver.engine.util.SingleExecuteFactory;
import com.symbian.jstat.JStatException;

/**
 * @author EngineeringTools
 * 
 */
public class ExecuteOnHostTest extends TestCase {
	
	private ExecuteFactory iExecuteFactory = SingleExecuteFactory.SINGLETON;

	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testDoTask() {
		try {
			
			String lBuffer = executeOnHost(new File("."), "java -version");
			if (lBuffer.indexOf("java version") < 0) {
				fail();
			}
			
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		} catch (JStatException e) {
			fail();
			e.printStackTrace();
		} catch (InterruptedException e) {
			fail();
			e.printStackTrace();
		}
	}

	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testSyncDoCleanUpAfterTimeout() {
		try {
			final IExecuteOnHost lExecuteOnHost = iExecuteFactory.createExecuteOnHost(new File("."), "notepad.exe");			
			new Timer(true).schedule(new TimerTask() {
				public void run() {
					try {
						if (lExecuteOnHost.doCleanUp(false)) {
							fail();
						}
					} catch (TimeLimitExceededException e) {
						e.printStackTrace();
						fail();
					}
				}
			}, EngineTests.TIMEOUT + 50);
			
			lExecuteOnHost.doTask(true, EngineTests.TIMEOUT, false);
			
			Thread.sleep(100);

		} catch (IOException lIOException) {
			lIOException.printStackTrace();
			fail();
		} catch (InterruptedException lInterruptedException) {
			lInterruptedException.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}

	}
	
	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testSyncDoCleanUpBeforeTimeout() {
		try {
			final IExecuteOnHost lExecuteOnHost = iExecuteFactory.createExecuteOnHost(new File("."), "notepad.exe");
			
			new Timer(true).schedule(new TimerTask() {
				public void run() {
					try {
						if (!lExecuteOnHost.doCleanUp(false)) {
							fail();
						}
					} catch (TimeLimitExceededException e) {
						e.printStackTrace();
						fail();
					}
				}
			}, EngineTests.TIMEOUT - 50);
			
			lExecuteOnHost.doTask(true, EngineTests.TIMEOUT, false);
			
			Thread.sleep(50);

		} catch (IOException lIOException) {
			lIOException.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}

	}

	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testAsyncDoCleanUpBefroeTimeout() {
		try {
			IExecuteOnHost lExecuteOnHost = iExecuteFactory.createExecuteOnHost(new File("."), "notepad.exe");
			
			lExecuteOnHost.doTask(false, EngineTests.TIMEOUT, false);
			
			Thread.sleep(EngineTests.TIMEOUT - 100);

			if (!lExecuteOnHost.doCleanUp(false)) {
				fail();
			}

		} catch (IOException e) {
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
	
	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testAsyncDoCleanUpAfterTimeout() {
		try {
			IExecuteOnHost lExecuteOnHost = iExecuteFactory.createExecuteOnHost(new File("."), "notepad.exe");
			
			lExecuteOnHost.doTask(false, EngineTests.TIMEOUT, false);
			
			// NOTE this must be more than POLL_SLEEP
			Thread.sleep(EngineTests.TIMEOUT + 1000);

			if (lExecuteOnHost.doCleanUp(false)) {
				fail();
			}

		} catch (IOException e) {
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

	/** Test method for 'com.symbian.driver.engine.ExecuteOnHost.run(String)'. */
	public final void testStreamGlobber() {
		try {		
			// Note that a more complete StreamGlobber tests exists in the Utils class
			String lBuffer = executeOnHost(new File("."), "echo \"ERROR: abc\"");
			if (lBuffer.toLowerCase().indexOf("severe") < 0) {
				fail();
			}
			
			lBuffer = executeOnHost(new File("."), "echo \"WARNING: abc\"");
			if (lBuffer.toLowerCase().indexOf("warning") < 0) {
				fail();
			}
			
			lBuffer = executeOnHost(new File("."), "echo \"Bob Dylan: abc\"");
			if (lBuffer.toLowerCase().indexOf("warning") >= 0) {
				fail();
			}
			
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		} catch (JStatException e) {
			fail();
			e.printStackTrace();
		} catch (InterruptedException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	private final String executeOnHost(final File aCWD, final String aCmd) throws JStatException, IOException, InterruptedException {
		try {
			
			IExecuteOnHost lExecuteOnHost = iExecuteFactory.createExecuteOnHost(aCWD, aCmd);
			lExecuteOnHost.doTask(true, EngineTests.TIMEOUT, false);
			
			return lExecuteOnHost.getOutput();
			
		} catch (TimeLimitExceededException e) {
			e.printStackTrace();
			fail();
		}
		
		return null;
	}
}
