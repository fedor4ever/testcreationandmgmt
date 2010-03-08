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



package com.symbian.driver.remoting.master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.cli.ParseException;

import com.symbian.driver.remoting.client.TestJob;

/**
 * QueuedExecutor : The execution queue.
 * 
 * Executes a job submitted by a client.The executor will execute jobs one at a
 * time, so jobs submitted are held in a queue ready for the executor to take
 * and execute later.
 * 
 * 
 * @author EngineeringTools
 */
public final class QueuedExecutor extends Snapshotable {

	private static ArrayList<TestJob> testJobQueue;

	private static Executor executor;

	private ClientUpdate client;

	private TestJob runningTestJob;

	private static final String SNAPSHOT_STORE_FILE = "queuedExecutor.snapshot";

	/** Logger for this class. */
	protected final static Logger LOGGER = Logger.getLogger(QueuedExecutor.class.getName());

	/**
	 * Standard Constructor
	 */
	public QueuedExecutor() {
		testJobQueue = new ArrayList<TestJob>();
		executor = new Executor();
		client = new ClientUpdate();
		runningTestJob = null;
	}

	/**
	 * Start the queue.
	 */
	public void start() {
		executor.start();
	}

	/**
	 * Add the job to the executor's queue.
	 * 
	 * @param aTestJob
	 *            TestJob object
	 */
	public void queueJob(TestJob aTestJob) {
		synchronized (testJobQueue) {
			aTestJob.setState(TestJob.WAITING);
			JobTracker.add(aTestJob.getId(), aTestJob.getState());
			testJobQueue.add(aTestJob);
			takeSnapshot();
			JobTracker.getInstance().takeSnapshot();
			LOGGER.log(Level.FINE, "Master: Job " + aTestJob.getId() + " has been added to the Queue.");
			client.enableUpdate(aTestJob.getRegistrationId());
			client.update("Job " + aTestJob.getId() + " has been added to the Queue and is " + aTestJob.getState() + ".");
			client.disableUpdate();
			testJobQueue.notify();
		}
	}

	/**
	 * Terminate/remove a waiting job from the executor's queue.
	 * 
	 * @param aTestJobId
	 *            int: a job id
	 * @return a reference to the testjob removed/terminated or null if there no
	 *         such job.
	 */
	public TestJob removeJob(int aTestJobId) {
		synchronized (testJobQueue) {
			for (Iterator<TestJob> iter = testJobQueue.iterator(); iter.hasNext();) {
				TestJob testJob = iter.next();
				if (testJob.getId() == aTestJobId) {
					testJob.setState(TestJob.TERMINATED);
					JobTracker.add(testJob.getId(), testJob.getState());
					iter.remove();
					takeSnapshot();
					JobTracker.getInstance().takeSnapshot();
					LOGGER.log(Level.FINE, "Master: Removed job " + testJob.getId() + " from the Queue.");
					return testJob;
				}
			}
			return null;
		}
	}
	
	/**
	 * Kill a running test job.
	 * 
	 * @param aTestJobId
	 *            int: a job id
	 * @return a reference to the testjob or null if there no
	 *         such job.
	 */
	public TestJob killJob(int aTestJobId) {
		runningTestJob.stop();
		JobTracker.remove(aTestJobId);
		JobTracker.getInstance().takeSnapshot();
		runningTestJob.setState(TestJob.TERMINATED);
		takeSnapshot();
		LOGGER.log(Level.FINE, "Master: Job " + runningTestJob.getId() + "has been killed.");
		return runningTestJob;
	}

	/**
	 * Returns the number of jobs waiting in the queue.
	 * 
	 * @return int number of jobs waiting for execution.
	 */
	public int count() {
		synchronized (testJobQueue) {
			return testJobQueue.size();
		}
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#takeSnapshot()
	 */
	public void takeSnapshot() {
		try {
			// Snap a shot (photo) while object is posing.
			Snapshot snapshot = new QueuedExecutorSnapshot(new ArrayList<TestJob>(testJobQueue), runningTestJob);
			// File the shot (photo) using the superclass's persistant filing
			// method.
			serialize(snapshot, SNAPSHOT_STORE_FILE);
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not take snapshot of Queued Executor." + lE.getMessage(), lE);
		}

	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#restoreSnapshot()
	 */
	public void restoreSnapshot() {
		try {
			// Get (most recently taken) shot of this object.
			QueuedExecutorSnapshot snapshot = (QueuedExecutorSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			// Restore this object to the previous shot.
			runningTestJob = snapshot.runningTestJob;
			if (runningTestJob != null) {
				// If previously executing, then add this to the
				// execution queue so that it is the first job to be run - as
				// before.
				if ((runningTestJob.getState()).equals(TestJob.EXECUTING)) {
					LOGGER.log(Level.INFO, "Master: Job " + runningTestJob.getId()
							+ " was previously executing and has now been recovered. Re-submitting for execution.");
					client.enableUpdate(runningTestJob.getRegistrationId());
					client.update("Restored Master due to an earlier breakdown.");
					client.update("Job " + runningTestJob.getId() + " has been recovered and is being re-submitted for execution.");
					client.disableUpdate();
					testJobQueue.add(runningTestJob);
				} else {
					// Else add to tracker, if its not there already, so that
					// clients can query the state of the job, which
					// will have either been 'completed' or 'failed', so client
					// will need to find out the usual way.
					if (!(JobTracker.isTrackable(runningTestJob.getId()))) {
						JobTracker.add(runningTestJob.getId(), runningTestJob.getState());
					}
				}
			}
			// Now add the rest of the jobs in the same order they were
			// previously in to the queue.
			// These are jobs that were previously waiting for execution.
			if (!((snapshot.testJobQueue).isEmpty())) {
				testJobQueue.addAll(snapshot.testJobQueue);
				LOGGER.log(Level.INFO, "Master: The queue has been recovered and it has " + (snapshot.testJobQueue).size() + " jobs.");
			}

		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Queued Executor." + lE.getMessage());
		} catch (ClassNotFoundException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Queued Executor." + lE.getMessage());
		}
	}

	/**
	 * 
	 * @see com.symbian.driver.remoting.master.Snapshotable#needRestore()
	 */
	public boolean needRestore() {
		boolean lNeedRestore = false;
		try {
			QueuedExecutorSnapshot lSnapshot = (QueuedExecutorSnapshot) deserialize(SNAPSHOT_STORE_FILE);
			if (lSnapshot.runningTestJob != null) {
				lNeedRestore = true;
			} else if (!((lSnapshot.testJobQueue).isEmpty())) {
				lNeedRestore = true;
			}
		} catch (IOException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Queued Executor." + lE.getMessage(), lE);
		} catch (ClassNotFoundException lE) {
			LOGGER.log(Level.FINE, "Could not restore snapshot of Queued Executor." + lE.getMessage(), lE);
		}
		return lNeedRestore;
	}

	/**
	 * Executor class executes a job. it waits while the queue is empty.
	 * 
	 * @author EngineeringTools
	 */
	private class Executor extends Thread {
		public void run() {

			while (true) {
				synchronized (testJobQueue) {
					// check if empty first.
					// On first start the testJobQueue will be empty and we want
					// the executor to
					// be in the waiting state.
					while (testJobQueue.isEmpty()) {
						// used 'while' not
						// 'if', for good thread
						// related reasons, if
						// woken up
						// unintentionally then check again for job in
						// testJobQueue and then
						// condition fails, which acts as guarantee that it was
						// awoken intentionally by testJobQueue.
						// The testJobQueue is empty so wait until there is a
						// job in the testJobQueue.
						try {
							LOGGER.log(Level.INFO, "Executor: Waiting for a job.");
							testJobQueue.wait();
						} catch (InterruptedException lIE) {
							// happens if another thread has interrupted the
							// current thread.
							// The interrupted status of the current thread is
							// cleared
							// when this exception is thrown.
							LOGGER.log(Level.INFO, "Executor: Interrupted.", lIE);
						}
					}
					// There is/are jobs in the testJobQueue, so get one,
					// run/execute it and possibly sent results back if
					// client is registered for callbacks.
					LOGGER.log(Level.INFO, "Executor: Getting a job.");
					runningTestJob = testJobQueue.remove(0);
					LOGGER.log(Level.INFO, "Executor: Got job " + runningTestJob.getId() + ".");
					runningTestJob.setState(TestJob.EXECUTING);
					takeSnapshot();
				}

				try {
					client.enableUpdate(runningTestJob.getRegistrationId());
					JobTracker.add(runningTestJob.getId(), runningTestJob.getState());
					LOGGER.log(Level.INFO, "Executor: Executing Job " + runningTestJob.getId() + ".");
					client.update("Job " + runningTestJob.getId() + " is " + runningTestJob.getState() + ".");

					// sleep(20000);//for testing purposes
					runningTestJob.execute();

					runningTestJob.setState(TestJob.COMPLETED);
					takeSnapshot();
					JobTracker.add(runningTestJob.getId(), runningTestJob.getState());
					JobTracker.getInstance().takeSnapshot();
					LOGGER.log(Level.INFO, "Executor: Completed Job " + runningTestJob.getId() + ".");
					client.update("Job " + runningTestJob.getId() + " has " + runningTestJob.getState() + ".");

					// Sent results back, if registered, otherwise client will
					// collect later.
					if (ClientRegister.isRegistered(runningTestJob.getRegistrationId())) {
						new Sendor(runningTestJob.getId(), runningTestJob.getRegistrationId(), client).send();
						LOGGER.log(Level.INFO, "Master: Deregistering " + runningTestJob.getRegistrationId() + ".");
						client.update("Deregistering " + runningTestJob.getRegistrationId() + " from Master.");
						ClientRegister.deregister(runningTestJob.getRegistrationId());
						ClientRegister.getInstance().takeSnapshot();
						LOGGER.log(Level.INFO, "Master: Unregistered " + runningTestJob.getRegistrationId() + ".");
						client.update("Unregistered " + runningTestJob.getRegistrationId() + " from Master.");
					} else {
						LOGGER.log(Level.INFO, "Master: Results to be collected by client later.");
					}

				} catch (IOException e) {
					handleException(e);
				} catch (ArrayIndexOutOfBoundsException e) {
					handleException(e);
				} catch (TimeLimitExceededException e) {
					handleException(e);
				} catch (ParseException e) {
					handleException(e);
				} finally {
					client.disableUpdate();
					runningTestJob = null;
					takeSnapshot();
				}

			}
		}

		private void handleException(Exception aException) {
			runningTestJob.setState(TestJob.FAILED);
			takeSnapshot();
			JobTracker.add(runningTestJob.getId(), runningTestJob.getState());
			JobTracker.getInstance().takeSnapshot();
			LOGGER.log(Level.INFO, "Executor: Job " + runningTestJob.getId() + " has " + runningTestJob.getState() + ".", aException);
			client.update("Job " + runningTestJob.getId() + " has " + runningTestJob.getState() + ".");
			client.update("You can retrieve the logs for the failed job from Master.");
			if (ClientRegister.isRegistered(runningTestJob.getRegistrationId())) {
				LOGGER.log(Level.INFO, "Master: Deregistering " + runningTestJob.getRegistrationId() + ".");
				client.update("Deregistering " + runningTestJob.getRegistrationId() + " from Master.");
				ClientRegister.deregister(runningTestJob.getRegistrationId());
				ClientRegister.getInstance().takeSnapshot();
				LOGGER.log(Level.INFO, "Master: Unregistered " + runningTestJob.getRegistrationId() + ".");
				client.update("Unregistered " + runningTestJob.getRegistrationId() + " from Master.");
			}
		}
	}
}