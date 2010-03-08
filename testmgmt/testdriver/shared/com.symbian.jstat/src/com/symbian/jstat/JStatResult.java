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



package com.symbian.jstat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author TimothyL
 *
 */
public class JStatResult{
    
    private int iReturnedValue;
    private String iErrorMessage;
    private String iReceivedData;
    
    /**
     * @param aReturnedValue
     * @param aReceivedData
     * @param aErrorMessage
     */
    public JStatResult(int aReturnedValue,String aReceivedData, String aErrorMessage){
        setReturnedValue(aReturnedValue);
        setReceivedData(aReceivedData);
        setErrorMessage(aErrorMessage);
    }
    
    /**
     * @return The error message.
     */
    public String getErrorMessage() {
        return iErrorMessage;
    }
    
    /**
     * @param aErrorMessage
     */
    public void setErrorMessage(String aErrorMessage) {
        iErrorMessage = aErrorMessage;
    }
    
    /**
     * @return The returned data.
     */
    public String getReceivedData() {
        return iReceivedData;
    }

    /**
     * @param aReceivedData
     */
    public void setReceivedData(String aReceivedData) {
        iReceivedData = aReceivedData;
    }

    /**
     * @return The returned value.
     */
    public int getReturnedValue() {
        return iReturnedValue;
    }
    
    /**
     * @param aReturnedValue
     */
    public void setReturnedValue(int aReturnedValue) {
    	if (aReturnedValue >= 0 && aReturnedValue <= 177) {
    		iReturnedValue = aReturnedValue;
    	} else {
    		Logger.getAnonymousLogger().log(Level.WARNING, "JStat returned with an invalid return code: "
    				+ aReturnedValue + ". Attempting to proceed as normal.");
    		iReturnedValue = 13;
    	}
    }

	/**
	 * @param lResult
	 * @return The result of Jstat formated as: "JSTAT Result:\n\t-Recieved Data: " + lResult.getReceivedData() + "\n\t-Error Message: " + lResult.getErrorMessage() + "\n\t-Returned Value: "
				+ lResult.getReturnedValue();
	 */
	public String toString() {
		return "JSTAT Result:\n\t-Recieved Data: " 
				+ iReceivedData + "\n\t-Error Message: " 
				+ iErrorMessage + "\n\t-Returned Value: "
				+ iReturnedValue;
	}
}
