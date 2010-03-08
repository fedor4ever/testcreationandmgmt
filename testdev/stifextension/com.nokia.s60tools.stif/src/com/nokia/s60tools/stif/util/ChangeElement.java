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


package com.nokia.s60tools.stif.util;


/**
 * Objects of this class describe changes in code that will be performed during
 * Test Case Wizard operation. Needed for change preview.
 *
 */
public class ChangeElement {
	
	private StringInput oldElement;
    private StringInput newElement;
    private String oldLabel;
    private String newLabel;
    
	/**
	 * Gets new element
	 * 
	 * @return
	 * 		new element
	 */
    public StringInput getNewElement() {
		return newElement;
	}
	
    /**
     * Gets old element
     * 
     * @return
     * 		old element
     */
	public StringInput getOldElement() {
		return oldElement;
	}
	
	/**
	 * Sets new element
	 * 
	 * @param newElement
	 * 			new element
	 */
	public void setNewElement(StringInput newElement) {
		this.newElement = newElement;
	}
	
	/**
	 * Sets old element
	 * 
	 * @param oldElement
	 * 			old element
	 */
	public void setOldElement(StringInput oldElement) {
		this.oldElement = oldElement;
	}
	
	/**
	 * Gets new label
	 * 
	 * @return
	 * 		new label
	 */
	public String getNewLabel() {
		return newLabel;
	}
	
	/**
	 * Sets new label
	 * 
	 * @param newLabel
	 * 			new label
	 */
	public void setNewLabel(String newLabel) {
		this.newLabel = newLabel;
	}
	
	/**
	 * Gets old label
	 * 
	 * @return
	 * 		old label
	 */
	public String getOldLabel() {
		return oldLabel;
	}
	
	/**
	 * Sets old label
	 * 
	 * @param oldLabel
	 * 			old label
	 */
	public void setOldLabel(String oldLabel) {
		this.oldLabel = oldLabel;
	}
}
