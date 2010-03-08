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




package com.symbian.driver.launch;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.progress.IProgressConstants;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.Reference;
import com.symbian.driver.Rtest;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.ResourceLoader;
import com.symbian.driver.core.controller.PCVisitor;
import com.symbian.driver.core.controller.SymbianVisitor;
import com.symbian.driver.core.controller.Visitor;
import com.symbian.driver.core.controller.event.IVisitorEventListener;
import com.symbian.driver.core.controller.event.TaskFinishedEvent;
import com.symbian.driver.core.controller.event.TaskStartedEvent;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.presentation.DriverEditorPlugin;
import com.symbian.driver.report.views.ShowView;
import com.symbian.driver.report.views.TestViewer;
import com.symbian.driver.utils.EclipseLogger;
import com.symbian.driver.utils.TDGUIConfigLogging;
import com.symbian.driver.utils.TestDriverConfigurator;
import com.symbian.driver.utils.UIUtils;

/**
 * @author EngineeringTools
 * 
 * Launches Test Driver
 * 
 */
public class DriverLaunch implements ILaunchConfigurationDelegate {

	/** iRBuild : Boolean for Test Driver RBuild */
	private Boolean iRBuild;

	private static final ISchedulingRule iSingleJob = new SingleJob();

	/**
	 * launch : launches Test Driver It uses Eclipse jobs to schedule the
	 * TestDriver build/run
	 * 
	 * @see org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.eclipse.debug.core.ILaunchConfiguration,
	 *      java.lang.String, org.eclipse.debug.core.ILaunch,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void launch(ILaunchConfiguration aConfiguration, String aMode,
			ILaunch aLaunch, IProgressMonitor aMonitor) throws CoreException {

		String lMode = aMode;
		//get a copy of the launch configuration. we do not need refrence to the original as 
		//from now on this config will not change.
		
		final ILaunchConfigurationWorkingCopy lEclipseConfig = aConfiguration.copy("aCopy");
		
		int lRunType = DriverLaunchConstants.BUILD_RUN;
		iRBuild = lEclipseConfig.getAttribute(DriverLaunchConstants.RBUILD,
				true);

		// this should never happen but just in case
		if (!lMode.equalsIgnoreCase("run")) {
			IStatus lStatus = EclipseLogger.createStatus(IStatus.ERROR,
					IStatus.ERROR, "Mode : " + lMode + " is not supported.",
					null);
			ErrorDialog.openError(UIUtils.getShell(), "TestDriver Launcher",
					"TestDriver failed to launch...", lStatus);
			return;
		}

		String lTask = DriverLaunchConstants.DRIVER;
		// get the run type buid/run/both default both.
		try {
			lRunType = lEclipseConfig.getAttribute(
					DriverLaunchConstants.RUN_TYPE, lRunType);
			lTask = lEclipseConfig.getAttribute(
					DriverLaunchConstants.ENTRY_POINT_ADDRESS, lTask);
		} catch (CoreException lException) {
			EclipseLogger
					.logInfo("Failed to get the run type, therefore using default BOTH Build+Run");
		}

		DeviceUtils.resetPoll();
		
		// create a job or jobs and schedule them.
		Job lJob;
		if (lRunType == DriverLaunchConstants.BUILD) {
			// create a build job and schedule it
			lJob = createJob(lEclipseConfig, true, true, false);
			lJob.setName("TestDriver : Build : " + lTask);
			lJob.setRule(iSingleJob);
			lJob.schedule();
		} else if (lRunType == DriverLaunchConstants.RUN) {
			// create a run job and schedule it
			lJob = createJob(lEclipseConfig, false, true, false);
			lJob.setName("TestDriver : Run : " + lTask);
			lJob.setRule(iSingleJob);
			lJob.schedule();
		} else if (lRunType == DriverLaunchConstants.BUILD_RUN) {
			// create a build job and schedule it
			lJob = createJob(lEclipseConfig, true, true, true);
			lJob.setName("TestDriver : Build : " + lTask);
			lJob.setRule(iSingleJob);
			lJob.schedule();

			// create a run job and schedule it
			lJob = createJob(lEclipseConfig, false, false, false);
			lJob.setName("TestDriver : Run : " + lTask);
			lJob.setRule(iSingleJob);
			lJob.schedule();
		}
	}

	/**
	 * creatJob : create an eclipse Job
	 * 
	 * @param Task
	 *            aTask : The root task to run
	 * @param int
	 *            aNumberOfTasks : the number of tasks to run
	 * @param boolean
	 *            aIsBuild : true = build, false = run
	 * 
	 * @param boolean
	 *            aClearConsole : clear the console. This will be used for
	 *            build+run to keep the console between jobs.
	 * @return Job
	 */
	public Job createJob(final ILaunchConfiguration aLaunchConfig,
			final boolean aIsBuild, final boolean aCleanConsole,
			final boolean aKeepLog) {

		final Job lJob = new Job("Test Driver Job") {

			private Logger LOGGER;

			private Task lTask = null;

			protected IStatus run(final IProgressMonitor aProgressMonitor) {

				class ProgressMonitorThread extends Thread {

					private IProgressMonitor iProgressMonitor = aProgressMonitor;
					private boolean isDone = false;

					public void run() {
						while (!isDone) {
							if (iProgressMonitor.isCanceled()) {
								DeviceUtils.stopPoll();
								break;
							}
							try {
								Thread.sleep(500);
							} catch (InterruptedException ignore) {
							}
						}
					}

					public void done() {
						isDone = true;
					}
				};
				
				ProgressMonitorThread lProgressCancel = new ProgressMonitorThread();
				lProgressCancel.start();
				
				// configure logging again this will happen when the job starts
				TDGUIConfigLogging lLogConf = TDGUIConfigLogging.getInstance();
				lLogConf.configureLogging(aKeepLog);

				UIUtils.getDisplay().asyncExec(new ShowView());

				LOGGER = Logger.getLogger(Job.class.getName());

				LOGGER.info("\n\n*******Starting Job : " + this.getName());

				// configure TestDriver from Preferences and launch
				// configuration
				try {
					TestDriverConfigurator.configTestDriver(aLaunchConfig);
				} catch (ParseException lParseException) {
					return new Status(
							IStatus.ERROR,
							DriverEditorPlugin.ID,
							IStatus.ERROR,
							this.getName()
									+ " failed to set TestDriver configuration, see TestDriver log file.",
							lParseException);
				}

				TDConfig CONFIG = TDConfig.newInstance();

				try {
					CONFIG.printConfig(true);
				} catch (IOException lException) {
					LOGGER.log(Level.SEVERE, lException.getMessage(),
							lException);
				}

				// try to load the driver file and get the task for real.
				try {
					ResourceLoader.resetResourceSet();
					lTask = ResourceLoader.load();
				} catch (ParseException lPex) {
					// log and error and finish the Job
					LOGGER.log(Level.SEVERE, "Failed to load resource : "
							+ lPex.getMessage(), lPex);
					return new Status(
							IStatus.ERROR,
							DriverEditorPlugin.ID,
							IStatus.ERROR,
							this.getName()
									+ " failed to load the driver file, please check your configuration.",
							lPex);
				}

				if (lTask == null) {
					return new Status(
							IStatus.ERROR,
							DriverEditorPlugin.ID,
							IStatus.ERROR,
							this.getName()
									+ " --- failed to find the requested suite, please check your configuration.",
							null);
				}
				int lNumberOfTasks = 1;

				for (Iterator lTaskIter = lTask.eAllContents(); lTaskIter
						.hasNext();) {
					Object lObject = lTaskIter.next();
					// this list must much the visitor listerners we are passing
					// in the job.
					if (aIsBuild) {
						if (lObject instanceof Task || lObject instanceof CmdPC
								|| lObject instanceof Build
								|| lObject instanceof TestExecuteScript
								|| lObject instanceof Transfer
								|| lObject instanceof Reference
								|| lObject instanceof CmdSymbian
								|| lObject instanceof Rtest) {
							lNumberOfTasks++;
						}
					} else {
						if (lObject instanceof Task
								|| lObject instanceof TestExecuteScript
								|| lObject instanceof Transfer
								|| lObject instanceof Reference
								|| lObject instanceof CmdSymbian
								|| lObject instanceof Rtest) {
							lNumberOfTasks++;
						}
					}
				}
				// add parent levels
				lNumberOfTasks = lNumberOfTasks + lTask.getLevel();

				aProgressMonitor.beginTask((aIsBuild ? "Building" : "Running")
						+ " task " + lTask.getName(), lNumberOfTasks);

				final Visitor lVisitor;

				if (aIsBuild) {
					lVisitor = new PCVisitor();
					((PCVisitor) lVisitor).setRBuild(iRBuild);
				} else {
					lVisitor = new SymbianVisitor();
				}

				// show the Test Result view

				UIUtils.getDisplay().asyncExec(new Runnable() {
					public void run() {
						TestViewer.getInstance().setInput(
								lVisitor.getResult().getReport());
					}
				});

				lVisitor.addVisitorListener(new IVisitorEventListener() {

					public void taskFinished(TaskFinishedEvent aVisitorEvent) {
						
						EObject lVisitObject = aVisitorEvent.getEObject();
						if (lVisitObject instanceof Task) {
							aProgressMonitor.subTask("Finished task: "
									+ ((Task) lVisitObject).getName());
						} else if (lVisitObject instanceof CmdPC) {
							aProgressMonitor
									.subTask("Finished Running PC command: "
											+ ((CmdPC) lVisitObject).getURI());
						} else if (lVisitObject instanceof Build) {
							aProgressMonitor
									.subTask("Finished Building group dir: "
											+ ((Build) lVisitObject).getURI());
						} else if (lVisitObject instanceof TestExecuteScript) {
							if (aIsBuild) {
								aProgressMonitor
										.subTask("Finished Copying TEF Script to repository: "
												+ ((TestExecuteScript) lVisitObject)
														.getPCPath());
							} else {
								aProgressMonitor
										.subTask("Finished Running TEF Script: "
												+ ((TestExecuteScript) lVisitObject)
														.getPCPath());
							}
						} else if (lVisitObject instanceof Transfer) {
							if (aIsBuild) {
								aProgressMonitor
										.subTask("Finished Copying Transfer file to repository: "
												+ ((Transfer) lVisitObject)
														.getPCPath());
							} else {
								aProgressMonitor
										.subTask("Finished Retrieve file to results: "
												+ ((Transfer) lVisitObject)
														.getPCPath());
							}
						} else if (lVisitObject instanceof Reference) {
							aProgressMonitor.subTask("Finished Reference: "
									+ ((Reference) lVisitObject).getUri()
											.getName());
						} else if (lVisitObject instanceof CmdSymbian) {
							aProgressMonitor
									.subTask("Finished Running Symbian command: "
											+ ((CmdSymbian) lVisitObject)
													.getStatCommand()
													.getLiteral());
						} else if (lVisitObject instanceof Rtest) {
							aProgressMonitor.subTask("Finished Running RTest: "
									+ ((Rtest) lVisitObject).getSymbianPath());
						}

						aProgressMonitor.worked(1);
						// if the job has been canceled, ask the visistor to
						// stop at the end of the task
						if (aProgressMonitor.isCanceled()) {
							lVisitor.stop();
						}

					}

					public void taskStarted(TaskStartedEvent aVisitorEvent) {
						EObject lVisitObject = aVisitorEvent.getEObject();
						if (lVisitObject instanceof Task) {
							aProgressMonitor.subTask("Started task: "
									+ ((Task) lVisitObject).getName());
						} else if (lVisitObject instanceof CmdPC) {
							aProgressMonitor
									.subTask("Started Running PC command: "
											+ ((CmdPC) lVisitObject).getURI());
						} else if (lVisitObject instanceof Build) {
							aProgressMonitor
									.subTask("Started Building group dir: "
											+ ((Build) lVisitObject).getURI());
						} else if (lVisitObject instanceof TestExecuteScript) {
							if (aIsBuild) {
								aProgressMonitor
										.subTask("Started Copying TEF Script to repository: "
												+ ((TestExecuteScript) lVisitObject)
														.getPCPath());
							} else {
								aProgressMonitor
										.subTask("Started Running TEF Script: "
												+ ((TestExecuteScript) lVisitObject)
														.getPCPath());
							}
						} else if (lVisitObject instanceof Transfer) {
							if (aIsBuild) {
								aProgressMonitor
										.subTask("Started Copying Transfer file to repository: "
												+ ((Transfer) lVisitObject)
														.getPCPath());
							} else {
								aProgressMonitor
										.subTask("Started Retrieve file to results: "
												+ ((Transfer) lVisitObject)
														.getPCPath());
							}
						} else if (lVisitObject instanceof Reference) {
							aProgressMonitor.subTask("Started Reference: "
									+ ((Reference) lVisitObject).getUri()
											.getName());
						} else if (lVisitObject instanceof CmdSymbian) {
							aProgressMonitor
									.subTask("Started Running Symbian command: "
											+ ((CmdSymbian) lVisitObject)
													.getStatCommand()
													.getLiteral());
						} else if (lVisitObject instanceof Rtest) {
							aProgressMonitor.subTask("Started Running RTest: "
									+ ((Rtest) lVisitObject).getSymbianPath());
						}
					}
				});

				
				try {
				// set our security manager to disallow TestDriver to kill the
				// JVM with system.exit()
				
				SecurityManager lSec = System.getSecurityManager();
				LOGGER.fine("Installing Security Manager around Test Driver.");
				System.setSecurityManager(new TestDriverRunnerSecurityManager());
				lVisitor.start(lTask);

				// remove security manager
				LOGGER.fine("Removing Security Manager");
				System.setSecurityManager(lSec);
				
				} catch (SecurityException lSecException) {
					// ignore security exception. TestDriver has System.exit()
				}

				aProgressMonitor.done();
				lProgressCancel.done();

				return new Status(IStatus.OK, DriverEditorPlugin.ID,
						IStatus.OK, "TestDriver "
								+ (aIsBuild ? "Build" : "Run") + " Finished ", null);
			}
		};

		lJob.setUser(true);
		lJob.setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);

		return lJob;
	}
}

/**
 * 
 * @author Engineering Tools SingleJob is a an ISechedulingRule used to run
 *         TestDriver jobs in sequence. The rule is that if there a running job
 *         with the same rule then wait. All TestDriver jobs will be set with
 *         this rule Other eclipse jobs are not affected by this rule unless of
 *         course they want to use this rule.
 * 
 */
class SingleJob implements ISchedulingRule {

	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	public boolean isConflicting(ISchedulingRule rule) {
		return rule == this;
	}
}
