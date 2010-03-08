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

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EObject;
import org.jmock.Mock;
import org.jmock.cglib.MockObjectTestCase;
import org.jmock.expectation.MockObject;

/**
 * @author EngineeringTools
 *
 */
public class TaskFinishedEventTest extends MockObjectTestCase {
	
	private Mock lTaskMock = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		lTaskMock = new Mock(EObject.class);
	}

	public void testTaskFinishedEvent() {
		try {
			new TaskFinishedEvent(null, true, false, new Exception("A Exception"));
			fail();
		} catch (NullPointerException lNullPointerException) {
			// PASS
		}
		
		try {
			
			TaskFinishedEvent lTaskFinishedEvent = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, false, null);
			if (lTaskFinishedEvent == null) {
				fail();
			}
		} catch (NullPointerException lNullPointerException) {
			fail();
		}
	}

	public void testGetEObject() {
		TaskFinishedEvent lTaskFinishedEvent = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, false, null);
		assertEquals(lTaskMock.proxy(), lTaskFinishedEvent.getEObject());
	}

	public void testIsWarning() {
		TaskFinishedEvent lTrueWarning = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, true, null);
		assertTrue(lTrueWarning.isWarning());
		
		TaskFinishedEvent lFalseWarning = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, false, null);
		assertFalse(lFalseWarning.isWarning());
	}

	public void testIsPCVisitor() {
		TaskFinishedEvent lIsPCVisitor = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, true, null);
		assertTrue(lIsPCVisitor.isPCVisitor());
		
		TaskFinishedEvent lIsNotPCVisitor = new TaskFinishedEvent((EObject) lTaskMock.proxy(), false, true, null);
		assertFalse(lIsNotPCVisitor.isPCVisitor());
	}

	public void testGetException() {
		TaskFinishedEvent lNullGetExcpetion = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, true, null);
		assertNull(lNullGetExcpetion.getException());
		
		Exception lException = new Exception();
		TaskFinishedEvent lGetException = new TaskFinishedEvent((EObject) lTaskMock.proxy(), true, true, lException);
		assertEquals(lException, lGetException.getException());
	}

}
