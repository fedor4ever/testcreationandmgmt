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
package com.nokia.testfw.core.model;

/**
 * This class is used a save test result information for a test case.
 * It also keep the detail failure if any
 * 
 * @author xiaoma
 * @see TestFailure
 */
public class TestResult {
	protected TestStatus status;
    protected long startTime;
    protected long endTime;
    protected TestFailure failure;
    
    /**
	 * @return the failure
	 */
	public TestFailure getFailure() {
		return failure;
	}

	/**
	 * @param failure the failure to set
	 */
	public void setFailure(TestFailure failure) {
		this.failure = failure;
	}

	public TestResult() {
    	status = TestStatus.NOTSTART;
    }
    
    /**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[TestResult]");
        sb.append("status:" + status);
        return sb.toString();
    }
	
	public enum TestStatus {
		SUCCESS,
	    FAILURE,
	    SKIP,
	    STARTED, 
	    NOTSTART;
	}
    
}


