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
package com.nokia.testfw.codegen;

/**
 * @author k21wang
 *
 */
public class ChangeFileContent {

	
	private String oldContent;
    private String newContent;
    private String filePath;
    
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets new element
	 * 
	 * @return
	 * 		new element
	 */
    public String getNewContent() {
		return newContent;
	}
	
    /**
     * Gets old element
     * 
     * @return
     * 		old element
     */
	public String getOldContent() {
		return oldContent;
	}
	
	/**
	 * Sets new element
	 * 
	 * @param newContent
	 * 			new element
	 */
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	
	/**
	 * Sets old element
	 * 
	 * @param oldContent
	 * 			old element
	 */
	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}
	
}
