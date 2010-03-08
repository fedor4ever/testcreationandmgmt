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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;

import com.nokia.s60tools.testdrop.engine.value.EmulatorTestResultValue;
import com.nokia.s60tools.testdrop.plugin.TestDropPlugin;
import com.nokia.s60tools.testdrop.resources.Messages;
import com.nokia.s60tools.testdrop.util.LogExceptionHandler;
import com.nokia.s60tools.testdrop.util.StartUp;

/**
 * Listener class for EmulatorJob. 'done' method is run to display results produced by 
 * EmulatorJob in a view  
 *
 */
public class EmulatorJobListener implements IJobChangeListener {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void done(final IJobChangeEvent event) {
		Runnable cachedDialogRunnable = new Runnable() {
			public void run() {
				if (event.getResult().getSeverity() == IStatus.OK) {
					String message = event.getResult().getMessage();
					String dateString = message.substring(0, message.indexOf(";"));
					String reportXml = message.substring(message.indexOf(";") + 1);
					
					try {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date creationDate = dateFormat.parse(dateString);
						EmulatorTestResultValue testResultValue = new EmulatorTestResultValue(reportXml, creationDate);
						
						StartUp.addTestResult(testResultValue);
						StartUp.showResulView();
					}
					catch (ParseException ex) {
						LogExceptionHandler.log(Messages.getString("EmulatorJobListener.couldNotParseDateMessage"));
					}
				}
				else {
					LogExceptionHandler.log(Messages.getString("EmulatorJobListener.warningMessage") + " " +
							event.getResult().getMessage());
					LogExceptionHandler.showErrorDialog(Messages.getString("EmulatorJobListener.warningMessage") + " " +
							event.getResult().getMessage());
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
	public void running(IJobChangeEvent arg0) {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void awake(IJobChangeEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void sleeping(IJobChangeEvent arg0) {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void aboutToRun(IJobChangeEvent arg0) {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#scheduled(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	public void scheduled(IJobChangeEvent arg0) {
	}
}
