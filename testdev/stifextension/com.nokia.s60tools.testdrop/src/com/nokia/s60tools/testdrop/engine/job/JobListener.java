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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;

import com.nokia.s60tools.testdrop.engine.value.HardwareTestResultValue;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Listener for SendDropJob
 * 
 */
public class JobListener implements IJobChangeListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void aboutToRun(IJobChangeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void awake(IJobChangeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void done(final IJobChangeEvent event) {
		final String msg = event.getResult().getMessage();
		boolean type = false;
		if (event.getResult().getSeverity() == IStatus.CANCEL) {
			type = true;
		}
		final boolean notify = type;

		Runnable cachedDialogRunnable = new Runnable() {
			public void run() {
				if (msg.equals(LogExceptionHandler.CANCELLED)) {
					LogExceptionHandler.showNotifyDialog(msg);
					return;
				}
				if (event.getResult().getSeverity() != IStatus.OK) {
					if (notify) {
						LogExceptionHandler.showNotifyDialog(msg);
					} else {
						LogExceptionHandler.showErrorDialog(msg);
					}
				} else {
					int separatorIndex = msg
							.indexOf(SendDropJob.TEST_ID_AND_RESULTS_SEPARATOR);
					int testId = Integer.valueOf(msg.substring(0,
							separatorIndex));
					String result = msg.substring(separatorIndex + 1);
					if (StartUp.getTestResultPropertyValue().getTestResulPath() == null) {
						StartUp.addTestResult(new HardwareTestResultValue(result,
								testId));
						StartUp.showResulView();
					} else {
						StartUp.addTestResult(testId);
					}
				}
			}
		};
		TestDropPlugin.getDefaultDisplay().asyncExec(cachedDialogRunnable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#running(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void running(IJobChangeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#scheduled(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void scheduled(IJobChangeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void sleeping(IJobChangeEvent event) {
	}

}
