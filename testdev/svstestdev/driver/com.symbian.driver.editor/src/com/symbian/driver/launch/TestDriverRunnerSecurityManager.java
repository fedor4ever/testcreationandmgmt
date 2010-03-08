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

import java.security.Permission;

/**
 * TestDriverRunnerSecurityManager : a convenience security manager to disable
 * system.exit in TestDriver when installed other checks are executed by the GUI
 * therefore overriding methods see below.
 * 
 * @author HocineA
 * 
 */
public class TestDriverRunnerSecurityManager extends SecurityManager {

	/**
	 * checkExit : override ceckExit to stop exiting the JVM from TestDriver
	 * 
	 * @param int
	 *            status
	 * @return void
	 */
	public void checkExit(int status) {
		throw new SecurityException();
	}

	/**
	 * checkRead : allow read access
	 * 
	 */
	public void checkRead(String file) {
		return;
	}

	/**
	 * checkWrite : allow write access
	 */
	public void checkWrite(String file) {
		return;
	}

	/**
	 * checkPropertyAccess : allow property access
	 */

	public void checkPropertyAccess(String key) {
		return;
	}

	/**
	 * checkMemberAccess : allow access to class members
	 */
	public void checkMemberAccess(Class<?> clazz, int which) {
		return;
	}

	/**
	 * checkPermission : oveload checkPremission
	 * 
	 */

	public void checkPermission(Permission perm) {
		return;
	}
}
