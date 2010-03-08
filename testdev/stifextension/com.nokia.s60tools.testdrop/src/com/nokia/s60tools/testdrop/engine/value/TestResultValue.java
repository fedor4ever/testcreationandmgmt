/*
* Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies). 
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

package com.nokia.s60tools.testdrop.engine.value;

/**
 * Test results can come from hardware and emulator. 
 * This interface is created to get any consistency between those results
 *
 */
public interface TestResultValue {
	
	/** 
	 * @return
	 * 		end time of a test
	 */
	public String getEndTime();
	
	/**
	 * @return
	 * 		passrate as float
	 */
	public float getPassrate();
	
	/**
	 * @return
	 * 		passrate as String
	 */
	public String getPassrateString();
	
	/**
	 * @return
	 * 		test name
	 */
	public String getTestName();
	
	/**
	 * Displaying a detailed view requires the index of item that was chosen
	 * by user to be displayed as detailed
	 * 
	 * @param index
	 * 			value of index that is given to this value object
	 */
	public void addSelectionIndex(int index);
	
	/**
	 * @return
	 * 		result as String
	 */
	public String getResult();
	
	/**
	 * @return
	 * 		test name followed by id to make the name more unique
	 */
	public String getTestNameAndTestId();
	
	/**
	 * @return
	 * 		if the selected value is already displayed as detailed
	 */
	public boolean isSelected(int index);
}
