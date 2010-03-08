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
import org.jmock.Mock;
import org.jmock.cglib.MockObjectTestCase;

import junit.framework.TestCase;

/**
 * @author EngineeringTools
 *
 */
public class TaskStartedEventTest extends MockObjectTestCase {

	private Mock lTaskMock = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		lTaskMock = new Mock(EObject.class);
	}
	
	public void testTaskStartedEventEObjectBoolean() {
		try {
			new TaskStartedEvent(null, true);
			fail();
		} catch (NullPointerException lNullPointerException) {
			// PASS
		}
		
		try {
			
			TaskStartedEvent lTaskFinishedEvent = new TaskStartedEvent((EObject) lTaskMock.proxy(), true);
			if (lTaskFinishedEvent == null) {
				fail();
			}
		} catch (NullPointerException lNullPointerException) {
			fail();
		}
	}

	public void testTaskStartedEventEObject() {
		try {
			new TaskStartedEvent(null);
			fail();
		} catch (NullPointerException lNullPointerException) {
			// PASS
		}
		
		try {
			
			TaskStartedEvent lTaskFinishedEvent = new TaskStartedEvent((EObject) lTaskMock.proxy());
			if (lTaskFinishedEvent == null) {
				fail();
			}
		} catch (NullPointerException lNullPointerException) {
			fail();
		}
	}

	public void testGetEObject() {
		TaskStartedEvent lTaskFinishedEvent = new TaskStartedEvent((EObject) lTaskMock.proxy(), true);
		assertEquals(lTaskMock.proxy(), lTaskFinishedEvent.getEObject());
		
		lTaskFinishedEvent = new TaskStartedEvent((EObject) lTaskMock.proxy());
		assertEquals(lTaskMock.proxy(), lTaskFinishedEvent.getEObject());
	}

	public void testIsPCVisitor() {
		TaskStartedEvent lIsPCVisitor = new TaskStartedEvent((EObject) lTaskMock.proxy(), true);
		assertTrue(lIsPCVisitor.isPCVisitor());
		
		TaskStartedEvent lIsNotPCVisitor = new TaskStartedEvent((EObject) lTaskMock.proxy(), false);
		assertFalse(lIsNotPCVisitor.isPCVisitor());
		
		TaskStartedEvent lIsPCVisitorWithNoConstructor = new TaskStartedEvent((EObject) lTaskMock.proxy());
		assertFalse(lIsPCVisitorWithNoConstructor.isPCVisitor());
	}

}
