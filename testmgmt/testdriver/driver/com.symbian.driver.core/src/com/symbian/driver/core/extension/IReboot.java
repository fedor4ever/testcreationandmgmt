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

package com.symbian.driver.core.extension;

/**
 * @author Development Tools
 * Defining reboot plugin interface  
 *
 */
public interface IReboot {
	/**
	 * @return true if reboot was accomplished 
	 * false if not done for whatever reason.  
	 */
	public boolean Reboot() ;
	/**
	 * @return true if Switch ON was accomplished 
	 * false if not done for whatever reason.  
	 */
	public boolean SwitcthOn() ;
	/**
	 * @return true if Switch OFF was accomplished 
	 * false if not done for whatever reason.  
	 */
	public boolean SwitcthOff() ;
}
