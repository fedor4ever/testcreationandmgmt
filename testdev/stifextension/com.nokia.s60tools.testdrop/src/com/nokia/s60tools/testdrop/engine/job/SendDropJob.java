/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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


package com.nokia.s60tools.testdrop.engine.job;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.nokia.s60tools.testdrop.engine.TestDropFactory;
import com.nokia.s60tools.testdrop.engine.xml.value.TargetDeviceValue;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Job class for background process of sending a test drop to ATS server
 * 
 */
public class SendDropJob extends Job {
	private static final String JOB_TITLE = Messages
			.getString("SendDropJob.sendsATSDropToTheServer"); 
	private static final String MAKE_DROP_JOB = Messages
			.getString("SendDropJob.makingATSTestDropPackage"); 
	private static final String SENDING_JOB = Messages
			.getString("SendDropJob.sendingATSTestDrop"); 
	public static final String WATING_FOR_RESULTS = Messages
			.getString("SendDropJob.waitingForResults"); 
	public static final String TEST_ID_AND_RESULTS_SEPARATOR = ";"; 
	private TargetDeviceValue targetDeviceValue;
	private TestDropFactory testDrop;

	/**
	 * Constructors
	 * 
	 * @param testDropFactory
	 *            Test Drop Factory instance
	 * @param targetDeviceValue
	 *            target device information
	 */
	public SendDropJob(TestDropFactory testDropFactory,
			TargetDeviceValue targetDeviceValue) {
		super(JOB_TITLE);
		this.testDrop = testDropFactory;
		this.targetDeviceValue = targetDeviceValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IStatus run(IProgressMonitor progressMonitor) {
		IStatus ret = Status.OK_STATUS;
		try {
			progressMonitor.beginTask(MAKE_DROP_JOB, IProgressMonitor.UNKNOWN);
			LogExceptionHandler.cancelIfNeed(progressMonitor);

			testDrop.setProgressMonitor(progressMonitor);
			IPath testdropLocation = testDrop.makeTestDrop(targetDeviceValue);
			if (testdropLocation == null) {
				ret = new Status(Status.WARNING, "TestDrop", Status.WARNING, Messages
						.getString("TestDropFactory.cannotRecognizeModuleType"),
						null);
				return ret;
			}
			
			LogExceptionHandler.cancelIfNeed(progressMonitor);
			progressMonitor.setTaskName(SENDING_JOB);

			int id = testDrop.sendTestDrop(testdropLocation);
			testDrop.removeTestDropFromLocalDrive();
			if (StartUp.getTestResultPropertyValue().getTestResulPath() == null) {
				progressMonitor.setTaskName(WATING_FOR_RESULTS);
				String result = TestDropFactory.getResultFromServer(id,
						progressMonitor);
				if (result != null) {
					ret = new Status(IStatus.OK, this.getName(), id
							+ TEST_ID_AND_RESULTS_SEPARATOR + result);
				} else {
					ret = new Status(
							IStatus.INFO,
							this.getName(),
							Messages
									.getString("SendDropJob.cannotGetTestResultsException")); 
				}
			} else {
				ret = new Status(IStatus.OK, this.getName(), id
						+ TEST_ID_AND_RESULTS_SEPARATOR);
			}

		} catch (Exception e) {
			if (LogExceptionHandler.isCancelled(e)) {
				try {
					testDrop.removeTestDropFromLocalDrive();
					ret = new Status(IStatus.CANCEL, this.getName(), e
							.getMessage());
				} catch (Exception ex) {
					LogExceptionHandler.log(e.getMessage());
				}
			} else {
				ret = new Status(IStatus.INFO, this.getName(), e.getMessage());
			}

		} finally {
			progressMonitor.done();
		}
		return ret;
	}
}
