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

package com.symbian.driver.core.controller.utils;

import junit.framework.TestCase;

import com.symbian.driver.DriverFactory;
import com.symbian.driver.ExecuteOnSymbian;
import com.symbian.driver.Task;
import com.symbian.driver.TestExecuteScript;
import com.symbian.driver.core.controller.utils.SerialListener;


/**
 * @author Development Tools
 *
 */
public class SerialListenerTest extends TestCase {
	/**
	 * variables to setup the testscript element
	 * with cross references to respective containers 
	 */
	Task iSomeTask = null;
	ExecuteOnSymbian ilExecuteOnSymbian =  null;
	TestExecuteScript itestExecuteScript = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		iSomeTask = DriverFactory.eINSTANCE.createTask()  ;
		ilExecuteOnSymbian =  DriverFactory.eINSTANCE.createExecuteOnSymbian();
		iSomeTask.getExecuteOnSymbian().add(ilExecuteOnSymbian) ;
		itestExecuteScript = DriverFactory.eINSTANCE.createTestExecuteScript();
		itestExecuteScript.setPCPath("D:\\DummyPath\\dummy.script") ;
		ilExecuteOnSymbian.getTestExecuteScript().add(itestExecuteScript) ; 
	}

	/**
	 * tests normal case of instantiate serial listener.
	 * needs manual intervention to send data from com port.
	 */
	public void testSerialListener() {
		String comPort= "COM1" ; 
		try {
			SerialListener listen = new SerialListener(comPort,itestExecuteScript) ;
			try {
				Thread.sleep(100000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			listen.setM_Life(false) ; 
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * case when the serial listener thread should not start since the 
	 * com port given doesnt exist. 
	 */
	public void testSerialListenerBadCOM() {
		String comPort= "COM0" ; 
		try {
			SerialListener listen = new SerialListener(comPort,itestExecuteScript) ;
			if(listen.isM_Life()) // if the thread started for some reason
			{
				fail() ; 
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
