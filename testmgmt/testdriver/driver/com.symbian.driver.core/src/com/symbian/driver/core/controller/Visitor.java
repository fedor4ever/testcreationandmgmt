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

package com.symbian.driver.core.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.EventListenerList;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.symbian.driver.Build;
import com.symbian.driver.CmdPC;
import com.symbian.driver.CmdSymbian;
import com.symbian.driver.Rtest;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.Transfer;
import com.symbian.driver.core.controller.event.IVisitorEventListener;
import com.symbian.driver.core.controller.event.TaskFinishedEvent;
import com.symbian.driver.core.controller.event.TaskStartedEvent;
import com.symbian.driver.core.controller.utils.DeviceUtils;
import com.symbian.driver.core.controller.utils.ModelUtils;
import com.symbian.driver.core.environment.TDConfig;
import com.symbian.driver.core.extension.IVisitor;
import com.symbian.driver.core.extension.IVisitor.ESeverity;
import com.symbian.driver.core.pluginProxies.RebootProxy;
import com.symbian.driver.core.processors.PreProcessor;
import com.symbian.driver.core.report.Result;
import com.symbian.driver.util.DriverSwitch;
import com.symbian.utils.Epoc;

/**
 * This is the main TestDriver class that walks through the EMF tree.
 * 
 * It uses the Visitor pattern implemented by using EMF's Switch Interface.
 * 
 * @author EnginneringTools
 */
public abstract class Visitor {

	// Constants
	/** The logger for the Visitor class. */
	protected static final Logger LOGGER = Logger.getLogger(Visitor.class.getName());

	protected final TDConfig CONFIG = TDConfig.getInstance();

	// Protected Variables
	/** The stack of tasks, pertaining to one node. */
	protected static Task sTask = null;

	// Private Variables
	/** The List of all event listeners. */
	private EventListenerList iEventListenerList = new EventListenerList();

	/** A Stop variable */
	private boolean iStop = false;

	/** The result object */
	private Result iResult;

	/** Switch to run all EMF Objects over. */
	private DriverSwitch iDriverSwitch;

	/** The PreProcessor to control the device. */
	private PreProcessor iSymbianDevice;

	private boolean iIsPCVisitor = false;

	private boolean iResultListener = false;

	/** Stack of previous Tasks. */
	protected static final Stack<Task> TASK_SET = new Stack<Task>();

	protected int iPreviousLevel = 0;

	private boolean iNoErrors = true;

	// ///////////////////////////////////////////////
	// Starts and Stops the visitors.
	// ///////////////////////////////////////////////

	public Visitor() {
		iResult = new Result();
	}

	/**
	 * @param aTask
	 * @return 
	 */
	public abstract boolean start(final Task aTask);

	/**
	 * Walks through the EMF tree, processing only the tasks specified by the
	 * "Entry Point Address" and below.
	 * 
	 * This method relays the actual processing of each task to the necessary
	 * Visitors. Note that this method must be called from the sub-classes in
	 * their respective constructors.
	 * 
	 * @param aVisitor
	 *            A link to the calling visitor.
	 * @param aTask
	 *            The root level task in the EMF tree.
	 * @return 
	 */
	protected boolean start(final Task aTask, final DriverSwitch aDriverSwitch, final PreProcessor aSymbianDevice,
			final boolean lResultListener) {
		iNoErrors = true;
		iIsPCVisitor = this instanceof PCVisitor;
		iResultListener = lResultListener;

		iDriverSwitch = aDriverSwitch;
		iSymbianDevice = aSymbianDevice;

		// Add Listener for reporting.
		if (iResultListener) {
			if (!iResult.initResult(this, aTask, iIsPCVisitor)) {
				LOGGER.log(Level.SEVERE, " Failed to initialize results.");
				iNoErrors = false;
			}
		}

		// request ModelUtils to clear the eclipsing stack
		ModelUtils.resetStack();

		// Execute all the tasks
		walkParents(aTask.eContainer());
		doEObject(aTask);
		walkChildren(aTask);

		// Transform Results at the end
		if (iResultListener) {
			iResult.doXSLT(iIsPCVisitor);
		}
		return iNoErrors;
	}

	/**
	 * Stops the Visitor of TestDriver at the next Task.
	 * 
	 * @return 
	 */
	public boolean stop() {
		iStop = true;
		return true;
	}

	// ///////////////////////////////////////////////
	// Methods that walk through the EMF tree.
	// ///////////////////////////////////////////////

	/**
	 * Walks through the parents of a node in an EMF tree.
	 * 
	 * @param aTask
	 *            The task to walk all the parents.
	 * @param aVisitor
	 *            The vistor that does the switch on each of the parent nodes.
	 * @return The task currently being walked.
	 */
	private final void walkParents(final EObject aTask) {
		if (iStop) {
			return;
		}

		if (aTask instanceof Task) {
			walkParents(aTask.eContainer());
			doEObject(aTask);
			EList lList = aTask.eContents();
			for (Iterator lItertoplevel = lList.iterator(); lItertoplevel.hasNext();) {
				EObject element = (EObject) lItertoplevel.next();
				if (!(element instanceof Task)) {
					walkChildren(element);
				}
			}
		}
	}

	/**
	 * Walks one tree specified by the iterator and executes the switch.
	 * 
	 * @param aDriverSwitch
	 *            The switch to execute.
	 * @param aIterator
	 *            The tree to walk.
	 */
	private final void walkChildren(final EObject aTask) {
		for (Iterator lIter = aTask.eAllContents(); lIter.hasNext();) {
			if (iStop) {
				return;
			}
			doEObject((EObject) lIter.next());
		}
	}

	private void doEObject(final EObject aEObject) {
		fireTaskStartedEvent(new TaskStartedEvent(aEObject, this instanceof PCVisitor));
		if (aEObject instanceof Task) {
			LOGGER.info("Visiting Task: " + ((Task) aEObject).getAddress());
			sTask = (Task) aEObject;
			try {
				// reboot device is relevant to hardware only.
				boolean lEmulator = Epoc.isTargetEmulator(TDConfig.getInstance().getPreference(TDConfig.PLATFORM));
				if (!lEmulator && this instanceof SymbianVisitor && ((Task) aEObject).isPreRebootDevice() == true) {
					// first try soft reboot reboot
					boolean lSoftRebooted = false;
					if (DeviceUtils.restartBoard()) {
						//wait for device communication service to run up
						try {
							Thread.sleep(10000);	//10 seconds
						} catch (InterruptedException e) {
							//do nothing
						}
						
						if(DeviceUtils.poll(null)) {
							lSoftRebooted = true;
							LOGGER.log(Level.INFO, "JStat Reboot done on Device");
						}
					}
					
					//soft reboot failed, try RebootProxy
					if (!lSoftRebooted){
						try {
							if (RebootProxy.getInstance().Reboot()) {
								LOGGER.log(Level.INFO, "Reboot done on Device via reboot plugin.");
							} else {
								LOGGER.log(Level.SEVERE, "Could not reboot Device using reboot plugin");
								iNoErrors = false;
							}
						} catch (Exception lException) {
							LOGGER.log(Level.SEVERE, "Could not reboot thru any plugins either", lException);
							iNoErrors = false;
						}
					}
				}
			} catch (ParseException lParseException) {
				LOGGER.log(Level.SEVERE, "Configuration error", lParseException);
				iNoErrors = false;
			}
		} else {
			EObject parent = aEObject.eContainer();
			if (parent instanceof Task) {
			sTask = (Task)parent;
			}
	    }


		// Start EMF Node

		IVisitor lVisitor = (IVisitor) iDriverSwitch.doSwitch(aEObject);
		Map<? extends Exception, ESeverity> lResult = null;
		if (lVisitor != null) {
			lResult = lVisitor.execute(sTask, iSymbianDevice);
		}
		if (lResult != null && !lResult.isEmpty() && lResult.containsValue(ESeverity.ERROR) ) {
			iNoErrors = false;
		}

		if (iResultListener) {
			if (iIsPCVisitor) {
				if (aEObject instanceof Build || aEObject instanceof TestExecuteScript || aEObject instanceof Transfer
						|| aEObject instanceof CmdPC) {
					fireTaskFinishedEvent(new TaskFinishedEvent(aEObject, this instanceof PCVisitor, false, lResult));
				}
			} else if (aEObject instanceof CmdSymbian || aEObject instanceof Rtest || aEObject instanceof TestExecuteScript
					|| aEObject instanceof Transfer) {
				fireTaskFinishedEvent(new TaskFinishedEvent(aEObject, this instanceof PCVisitor, false, lResult));
			}
		}
	}

	// ///////////////////////////////////////////////
	// Listener's for all the Visitors.
	// ///////////////////////////////////////////////

	/**
	 * Add a listner to the events of the Visitor.
	 * 
	 * The listner must implement VisitorEventListner. This listner has methods
	 * for when a tasks starts, stops sucesfully and fails. Uses standard Java
	 * Listner pattern.
	 * 
	 * @param aEventListener
	 *            The event listener to add.
	 */
	public final void addVisitorListener(IVisitorEventListener aEventListener) {
		iEventListenerList.add(IVisitorEventListener.class, aEventListener);
	}

	/**
	 * Removes a listner that has been added to the Vistitor class.
	 * 
	 * @param aEventListener
	 *            The event listener to remove.
	 */
	public final void removeVisitorListener(IVisitorEventListener aEventListener) {
		iEventListenerList.remove(IVisitorEventListener.class, aEventListener);
	}

	/**
	 * Fires the "Execute Started Event" to all relevant listners.
	 * 
	 * @param aVisitorEvent
	 *            The event to fire to the listener.
	 */
	private final void fireTaskStartedEvent(TaskStartedEvent aVisitorEvent) {
		Object[] lListeners = iEventListenerList.getListenerList();

		// loop through each listener and pass on the event if needed
		for (int lIter = lListeners.length - 2; lIter >= 0; lIter -= 2) {
			if (lListeners[lIter] == IVisitorEventListener.class) {
				((IVisitorEventListener) lListeners[lIter + 1]).taskStarted(aVisitorEvent);
			}
		}
	}

	/**
	 * Fires the "Execute Finished Event" to all relevant listners.
	 * 
	 * @param aVisitorEvent
	 *            The event to fire to the listener.
	 */
	protected final void fireTaskFinishedEvent(TaskFinishedEvent aVisitorEvent) {
		Object[] lListeners = iEventListenerList.getListenerList();

		// loop through each listener and pass on the event if needed
		for (int lIter = lListeners.length - 2; lIter >= 0; lIter -= 2) {
			if (lListeners[lIter] == IVisitorEventListener.class) {
				((IVisitorEventListener) lListeners[lIter + 1]).taskFinished(aVisitorEvent);
			}
		}
	}

	// ///////////////////////////////////////////////
	// Utility methods.
	// ///////////////////////////////////////////////

	/**
	 * @return
	 */
	public Result getResult() {
		return iResult;
	}
}
