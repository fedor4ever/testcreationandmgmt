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
* Description:  A singleton class providing HTI connection used in
*                system tests and tools. 
*                The properties are defined in HTI.properties file.
*
*/


package com.nokia.hti.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

// From comm.jar
//import javax.comm.CommPortIdentifier;
//import javax.comm.SerialPort;

/**
 * A singleton class providing HTI connection used in system tests and tools. 
 * The properties are defined in HTI.properties file.
 */
public class HtiConnection
{
//==============================================================================
//Public constants

//==============================================================================
//Public methods

    public static HtiConnection getInstance() throws Exception
    {
        if( container.size() == 0 )
        {
            container.add( new HtiConnection() );
        }
        
        return (HtiConnection) container.get( 0 );
    }
    
    public InputStream getInputStream() { return in; }
    public OutputStream getOutputStream() { return out; }
    
    public void setSocketTimeout( int timeout )
    {
    	try
    	{
    		if ( s != null )
    		{
    			s.setSoTimeout( timeout );
    		}
    	}
    	catch ( Exception e )
    	{
    	}
    }
    
    public void resetSocketTimeout()
    {
        setSocketTimeout( defaultSocketTimeout );
    }
    
//==============================================================================
//Protected methods

//==============================================================================
//Private methods

    private HtiConnection() throws Exception
    {
        HtiProperties htiProps = HtiProperties.getInstance();
        
        // String selectedMedia = properties.getProperty( "selectedMedia" );
        String selectedMedia = htiProps.getString( "selectedMedia" );
        if( selectedMedia == null || 
            selectedMedia.equalsIgnoreCase( "socket" ) )
        {
            // use the socket connection
            
            // get the host name for socket connection
            // String socketHost = properties.getProperty( "socketHost" );
            String socketHost = htiProps.getString( "socketHost" );
            if( socketHost == null ) socketHost = "localhost";
            
            // get the socket port
            int socketPort = 2000; // default value
            try
            {
                socketPort = htiProps.getInt( "socketPort" );
            }
            catch( Exception e )
            {
                // ignore, port value is left to default value.
            }
            
            // get the default socket timeout value
            defaultSocketTimeout = 10000;
            try
            {
                defaultSocketTimeout = htiProps.getInt( "socketTimeout" );
            }
            catch( Exception e )
            {
                // ignore, timeout value is left to default value.
            }
            setSocketTimeout( defaultSocketTimeout );
            
            // open the socket
            s = new Socket( socketHost, socketPort );
            
            // open the streams
            in = s.getInputStream();
            out = s.getOutputStream();
        }
        else 
        {
            // use the serial connection
         
        	throw new Exception( "Serial connection not supported" );
/*        	
            // get the port to use
            // String portName = properties.getProperty( "portName" );
            String portName = htiProps.getString( "portName" );
            if( portName == null ) portName = "COM1";

            // get the speed of the COM port
            int portSpeed = 115200; // default value
            try
            {
                portSpeed = htiProps.getInt( "portSpeed" );
            }
            catch( Exception e )
            {
                // ignore, port value is left to default value.
            }
            
            // open the serial port
            CommPortIdentifier portId = 
                CommPortIdentifier.getPortIdentifier( portName );
            SerialPort serialPort = 
                (SerialPort) portId.open( "HtiSystemTest", 10000 );
            
            // set serial port settings
            int flow = SerialPort.FLOWCONTROL_RTSCTS_IN | 
                       SerialPort.FLOWCONTROL_RTSCTS_OUT;
            serialPort.setFlowControlMode( flow );
            serialPort.setSerialPortParams( portSpeed,
                                            SerialPort.DATABITS_8, 
                                            SerialPort.STOPBITS_1, 
                                            SerialPort.PARITY_NONE );
            
            // open streams
            in = serialPort.getInputStream();
            out = serialPort.getOutputStream();
*/            
        }
    }

    
//==============================================================================
//Protected attributes

//==============================================================================
//Private attributes

    private Socket s;
    private InputStream in;
    private OutputStream out;
    private static Vector container = new Vector( 1, 1 );
    private int defaultSocketTimeout;
    
//==============================================================================
//Static initialization block

//==============================================================================
//Inner classes
}

