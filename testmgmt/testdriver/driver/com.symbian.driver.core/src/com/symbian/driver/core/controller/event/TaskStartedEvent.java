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

import org.eclipse.emf.ecore.EObject;

/**
 * @author EngineeringTools
 *
 */
public class TaskStartedEvent implements IVisitorEvent {
	
	private EObject iObject;
	private boolean iIsPCVisitor;
	
	/**
	 * Constructor for the Execute Started Event.
	 * 
	 * @param aObject The EMF object relating to the started event.
	 * @param aIsPCVisitor 
	 */
	public TaskStartedEvent(final EObject aObject, final boolean aIsPCVisitor) {
		iObject = aObject;
		iIsPCVisitor = aIsPCVisitor;
	}
	
	/**
	 * Constructor for the Execute Started Event.
	 * 
	 * @param aObject The EMF object relating to the started event.
	 */
	public TaskStartedEvent(final EObject aObject) {
		iObject = aObject;
		iIsPCVisitor = false;
	}
	
	/**
	 * Returns the EMF object relating to the Execute Started Event.
	 * 
	 * @return The EMF Object relating to the event.
	 */
	public EObject getEObject() {
		return iObject;
	}

	/**
	 * @see com.symbian.driver.core.controller.event.VisitorEvent#isPCVisitor()
	 */
	public boolean isPCVisitor() {
		return iIsPCVisitor;
	}
}
