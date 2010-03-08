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



package com.symbian.tdep.templates.carbide;

import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * A common wrapper interface, for TEFUnit wizard to provide a 
 * project->class->method selection UI to users. 
 * 
 * @author Development Tools
 */
@SuppressWarnings("unchecked")
public interface Wrapper<E extends Wrapper> {
	
	/**
	 * 
	 * @return name of wrapped object
	 */
	String getName();
	
	/**
	 * 
	 * @return Child wrapped object of this wrapped object
	 */
	List<? extends Wrapper> getChildren();
	
	/**
	 * 
	 * @return Parent wrapped object of this wrapped object
	 */
	E getParent();
	
	/**
	 * 
	 * @return if this object is selected by user
	 */
	boolean isSelected();
	
	/**
	 * to Set selection status of this object
	 * @param aSelected
	 */
	void setSelected(boolean aSelected);

	/**
	 * @return icon image of wrapped object
	 */
	Image getImage();

}
