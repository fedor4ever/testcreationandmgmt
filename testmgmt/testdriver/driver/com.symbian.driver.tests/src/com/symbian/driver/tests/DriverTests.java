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


package com.symbian.driver.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>driver</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class DriverTests extends TestSuite {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new DriverTests("driver Tests");
		suite.addTestSuite(DocumentRootTest.class);
		suite.addTestSuite(ExecuteOnPCTest.class);
		suite.addTestSuite(ExecuteOnSymbianTest.class);
		suite.addTestSuite(RetrieveFromSymbianTest.class);
		suite.addTestSuite(TaskTest.class);
		suite.addTestSuite(TransferToSymbianTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DriverTests(String name) {
		super(name);
	}

} //DriverTests
