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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

/**
 * Class used by compare preview
 *
 */
public class StringInput implements ITypedElement, IEditableContent, IStreamContentAccessor {
	
	private String text;
	@SuppressWarnings("unused")
	private InputStream fContent;
	private boolean isEditable;
	
	/**
	 *  Constructor
	 */
	public StringInput(final String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.compare.ITypedElement#getImage()
	 */
	public Image getImage() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.compare.ITypedElement#getName()
	 */
	public String getName() {
		return "StringInput";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.compare.ITypedElement#getType()
	 */
	public String getType() {
		return "cpp";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.compare.IStreamContentAccessor#getContents()
	 */
	public InputStream getContents() throws CoreException {
		return new ByteArrayInputStream(text.getBytes());
	}
	
	public void setContent(byte[] newContent) {
        fContent = new ByteArrayInputStream(newContent);
    }
	
    public ITypedElement replace(ITypedElement child, ITypedElement other) {
        return child;
    }
    
    public boolean isEditable() {
    	return isEditable;
    }
    
    public void setEditable(boolean isEditable) {
    	this.isEditable = isEditable;
    }

}
