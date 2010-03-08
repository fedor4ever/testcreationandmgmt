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
public interface IVisitorEvent {

	/**
	 * @return The EMF EObject associated with the failed event.
	 */
	public abstract EObject getEObject();

	/**
	 * @return <code>true</code> if the event orginates from the PC Visitor, <code>false</code> otherwise.
	 */
	public abstract boolean isPCVisitor();

}