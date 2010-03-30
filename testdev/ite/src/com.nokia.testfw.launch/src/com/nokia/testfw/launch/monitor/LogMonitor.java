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
package com.nokia.testfw.launch.monitor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.debug.core.model.IProcess;

import com.nokia.testfw.core.model.result.TestRunResult;
import com.nokia.testfw.launch.TFWLaunchPlugin;
import com.nokia.testfw.resultview.model.TestRunSession;

/**
 * Monitor stf log file during test progress. It will create a test result
 * object and continue update the result during test execution according to real
 * time information get from stf log file
 * 
 * @see TestRunResult
 * @author kevin wang
 * 
 */
public class LogMonitor extends Thread {

	private boolean iStop = false;
	private Map<TestRunSession, InputStream> iMonitorSessions = new HashMap<TestRunSession, InputStream>();
	private Set<TestRunSession> iTerminatedSessions = new HashSet<TestRunSession>();

	public void addTestRunSession(TestRunSession session) {
		synchronized (iMonitorSessions) {
			iMonitorSessions.put(session, session.getInputStream());
			iMonitorSessions.notifyAll();
		}
	}

	public void removeTestRunSession(TestRunSession session) {
		synchronized (iMonitorSessions) {
			iMonitorSessions.remove(session);
			iMonitorSessions.notifyAll();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		long count;
		int readlength;
		byte[] buffer = new byte[4096];
		while (!iStop) {
			count = 0;
			iTerminatedSessions.clear();
			synchronized (iMonitorSessions) {
				if (iMonitorSessions.isEmpty()) {
					try {
						iMonitorSessions.wait();
					} catch (InterruptedException ignore) {
					}
				}
				for (TestRunSession session : iMonitorSessions.keySet()) {
					if (session.isClosed()) {
						iTerminatedSessions.add(session);
					} else {
						InputStream stream = iMonitorSessions.get(session);
						try {
							if (stream == null) {
								stream = session.getInputStream();
								if (stream == null) {
									continue;
								} else {
									iMonitorSessions.put(session, stream);
									stream.reset();
								}
							}
							if (stream.available() > 0) {
								StringBuilder builder = new StringBuilder();
								while ((readlength = stream.read(buffer)) > -1) {
									builder.append(new String(buffer, 0,
											readlength));
									count = count + readlength;
								}
								session.update(builder.toString());
							}
						} catch (IOException e) {
							TFWLaunchPlugin.log(e);
						}
					}
					boolean isTerminated = true;
					IProcess[] lProcesses = session.getLaunch().getProcesses();
					for (IProcess process : lProcesses) {
						if (!process.isTerminated()) {
							isTerminated = false;
							break;
						}
					}
					if (isTerminated && lProcesses.length > 0) {
						iTerminatedSessions.add(session);
						session.close();
					}
				}
				for (TestRunSession session : iTerminatedSessions) {
					iMonitorSessions.remove(session);
				}
				if (count == 0) {
					try {
						iMonitorSessions.wait(100);
					} catch (InterruptedException ignore) {
					}
				}
			}
		}
		for (TestRunSession session : iMonitorSessions.keySet()) {
			session.close();
		}
	}

	public void stopme() {
		iStop = true;
		iMonitorSessions.notifyAll();
	}
}
