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

package com.symbian.driver.core.controller.event;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.symbian.driver.core.extension.IVisitor.ESeverity;

/**
 * Event object for tasks that fail.
 * 
 * @author EngineeringTools
 */
public class TaskFinishedEvent implements IVisitorEvent {

	private EObject iObject;
	private boolean iIsWarning;
	private boolean iIsPCVisitor;
	private Map<? extends Exception, ESeverity> iExceptions;

	/**
	 * Constructor for the event failed event objects.
	 * 
	 * @param aObject
	 * @param aIsPCVisitor
	 * @param aIsWarning 
	 * @param aException
	 */
	public TaskFinishedEvent(final EObject aObject, final boolean aIsPCVisitor,
			final boolean aIsWarning, final Map<? extends Exception, ESeverity> aResult) {
		iObject = aObject;
		iIsPCVisitor = aIsPCVisitor;
		iIsWarning = aIsWarning;
		iExceptions = aResult;
	}



	/**
	 * @see com.symbian.driver.core.controller.event.VisitorEvent#getEObject()
	 */
	public EObject getEObject() {
		return iObject;
	}

	/**
	 * @return the isWarning
	 */
	public boolean isWarning() {
		return iIsWarning;
	}

	/**
	 * @see com.symbian.driver.core.controller.event.VisitorEvent#isPCVisitor()
	 */
	public boolean isPCVisitor() {
		return iIsPCVisitor;
	}

	/**
	 * @return The exception associated with the failed event.
	 */
	public Map<? extends Exception, ESeverity> getExceptions() {
		return iExceptions;
	}
}
