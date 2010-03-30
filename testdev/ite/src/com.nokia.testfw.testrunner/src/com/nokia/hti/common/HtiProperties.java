/*
* Copyright (c) 2005 Nokia Corporation and/or its subsidiary(-ies).
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
* Description:  A singleton class providing connection properties used in
*                system tests and tools. 
*                The properties are defined in HTI.properties file.
*
*/


package com.nokia.hti.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A singleton class providing connection properties used in the system tests 
 * and tools. The properties are defined in HTI.properties file.
 */
public class HtiProperties
{
//==============================================================================
//Public constants

//==============================================================================
//Public methods

    public static HtiProperties getInstance()
    {
        return getInstance( Locale.getDefault() );
    }
    
    public static HtiProperties getInstance( Locale locale )
    {
        HtiProperties htiProps = (HtiProperties)instances.get( locale );
        if( htiProps == null )
        {
            htiProps = new HtiProperties( locale );
            instances.put( locale, htiProps );
        }
        
        return htiProps;
    }
    
    public int getInt( String key )
    {
        return Integer.parseInt( bundle.getString( key ) );
    }
    
    public String getString( String key )
    {
        try
        {
            return bundle.getString( key );
        }
        catch( Exception e )
        {
            return null;
        }
    }
    
//==============================================================================
//Protected methods

//==============================================================================
//Private methods

    private HtiProperties( Locale locale )
    {
        bundle = ResourceBundle.getBundle( "HTI", locale );
    }
    
//==============================================================================
//Protected attributes

//==============================================================================
//Private attributes

    private ResourceBundle bundle;
    private static HashMap instances = new HashMap();
    
//==============================================================================
//Static initialization block

//==============================================================================
//Inner classes
}
