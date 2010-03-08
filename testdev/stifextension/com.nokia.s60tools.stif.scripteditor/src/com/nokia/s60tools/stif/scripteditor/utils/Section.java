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


package com.nokia.s60tools.stif.scripteditor.utils;

/**
 * Objects of this class describe sections in script file. Section is for example:
 * [Test]
 * ....
 * [Endtest]
 * 
 * Such section is defined by properties like name, offset in file, etc.
 *
 */
public class Section {
	
	/**
	 * Constructor
	 * 
	 * @param name
	 * 		Name of section
	 */
	public Section(String name){
		this.name = name;
		this.isNew = true;
		this.isDeleted = false;
	}

	/**
	 * Name getter
	 * 
	 * @return
	 * 		name of section	
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Name setter
	 * 
	 * @param name
	 * 		name of section
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Start offset setter
	 * 
	 * @param offset
	 * 		Start offset
	 */
	public void setStartOffset(int offset){
		startOffset = offset;
	}
	
	/**
	 * Start offset getter
	 * 
	 * @return
	 * 		Start offset
	 */
	public int getStartOffset(){
		return startOffset;
	}
	
	/**
	 * End offset setter
	 * 
	 * @param offset
	 * 		End offset
	 */
	public void setEndOffset(int offset){
		endOffset = offset;
	}
	
	/**
	 * End offset getter
	 * 
	 * @return
	 * 		End offset
	 */
	public int getEndOffset(){
		return endOffset;
	}
	
	/**
	 * Sets if this is a new section. This is needed to update folding
	 * structure.
	 * 
	 * @param isNew
	 * 		If the section is new
	 */
	public void setIsNew(boolean isNew){
		this.isNew = isNew;
	}
	
	/**
	 * Gets if this is a new section. This is needed to update folding
	 * structure.
	 * 
	 * @return
	 * 		If the section is new
	 */
	public boolean getIsNew(){
		return isNew;
	}
	
	/**
	 * Sets content of section
	 * 
	 * @param content
	 * 		Content of section
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * Gets content of section
	 * 
	 * @return
	 * 		Content of section
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * Sets if the section was deleted by used from the last updating of folding structure
	 * 
	 * @param isDeleted
	 * 		If the section was deleted
	 */
	public void setIsDeleted(boolean isDeleted){
		this.isDeleted = isDeleted;
	}
	
	/**
	 * Gets if the section was deleted by used from the last updating of folding structure
	 * 
	 * @return
	 * 		If the section was deleted
	 */
	public boolean getIsDeleted(){
		return isDeleted;
	}
	
	private String name;
	private int startOffset;
	private int endOffset;
	private boolean isNew;
	private boolean isDeleted;
	private String content;
}
