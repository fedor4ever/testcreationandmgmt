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

/**
 * @author Engineering Tools
 * 
 * Security Manager to disable system.exit()
 *
 */
public class NoExitSecurityManager extends SecurityManager {

	public void checkExit(int status) {
		throw new SecurityException();
	}
}
