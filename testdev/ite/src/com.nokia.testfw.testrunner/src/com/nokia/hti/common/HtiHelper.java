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
* Description:  Helper class to create, send and receive HTI messages.
*
*/


package com.nokia.hti.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper class to create, send and receive HTI messages.
 *
 */
public class HtiHelper
{
//==============================================================================
//Public constants

    /**
     * Validates that the given service uid is among the existing UID's.
     * 
     * @param serviceUid    The UID to be validated.
     * @return              true if given service UID is valid, otherwise false.
     */
    public static boolean validateServiceUid( int serviceUid )
    {
        return serviceUid == HtiConstants.SERVICE_UID_APP ||
               serviceUid == HtiConstants.SERVICE_UID_ECHO ||
               serviceUid == HtiConstants.SERVICE_UID_FTP ||
               serviceUid == HtiConstants.SERVICE_UID_HTI ||
               serviceUid == HtiConstants.SERVICE_UID_KEYEVENT ||
               serviceUid == HtiConstants.SERVICE_UID_SCREEN ||
               serviceUid == HtiConstants.SERVICE_UID_STIF ||
               serviceUid == HtiConstants.SERVICE_UID_SYSINFO ||
               serviceUid == HtiConstants.SERVICE_UID_AUDIO ||
               serviceUid == HtiConstants.SERVICE_UID_PIM ||
               serviceUid == HtiConstants.SERVICE_UID_MESSAGES ;
    }
    
    /**
     * Creates a HTI message from given service UID and input file containing
     * the message body.
     * 
     * @param serviceUid    The UID of the destination service.
     * @param inputFile     The file containing the message body.
     * @return              The created HTI message as a byte array.
     * @throws Exception    if error occurs in file handling.
     */
    public static byte[] createHtiMessage( int serviceUid, File inputFile )
        throws Exception
    {
        byte[] message = new byte[14 + (int)inputFile.length()];
        
        Arrays.fill( message, (byte)0 );

        // add service UID, 4 bytes
        CommonMethods.intToLittleEndianBytes( serviceUid, message, 0, 4 );

        // add body size, 4 bytes
        int bodySize = (int)inputFile.length();
        CommonMethods.intToLittleEndianBytes( bodySize, message, 4, 4 );

        // hti message version
        message[8] = (byte)1;

        // hti message priority
        message[9] = serviceUid == HtiConstants.SERVICE_UID_FTP ? (byte) 2 : (byte) 0;

        // leave flags and ext size to zero

        // add the crc
        int crc = CommonMethods.CRC16CCITT( message, 12 );
        CommonMethods.intToLittleEndianBytes( crc, message, 12, 2 );
        
        // read message body from file
        FileInputStream fis =  new FileInputStream( inputFile );
        byte[] buf = new byte[1024];
        int readBytes;
        int offset = 14;
        while( ( readBytes = fis.read( buf ) ) != -1 )
        {
            System.arraycopy( buf, 0, message, offset, readBytes );
            offset += readBytes;
        }
        
        return message;
    }
    
    /**
     * Wrapper to the private helper method.
     * Creates a HTI message from given service UID, command and parameters.
     * Sets HTI message priority to the default value (=0).
     *
     * @param serviceUid    The UID of the destination service.
     * @param command       The command for the destination service.
     * @param params        Optional parameters for the command.
     * @return              The created HTI message as a byte array.
     */
    public static byte[] createHtiMessage( int serviceUid,
                                           int command,
                                           byte[] params )
    {
        return createHtiMessage( serviceUid, command, params, 0, true );
    }

    /**
     * Wrapper to the private helper method.
     * Creates a HTI message from given service UID and parameters, with no
     * command specified for the destination service.
     * Sets HTI message priority to the default value (=0).
     * This method can be used in testing the ECHO service and error situations
     * when the command is missing.
     *
     * @param serviceUid    The UID of the destination service.
     * @param params        Optional parameters for the command.
     * @return              The created HTI message as a byte array.
     */
    public static byte[] createHtiMessage( int serviceUid, byte[] params )
    {
        return createHtiMessage( serviceUid, -1, params, 0, false );
    }

    /**
     * Wrapper to the private helper method.
     * Creates a command message for the FTP service with given command and
     * parameters.
     * Sets HTI message priority to the FTP command priority (=2).
     *
     * @param command       The command for the FTP service.
     * @param params        The parameters for the command.
     * @return              The created HTI message as a byte array.
     */
    public static byte[] createFTPCommandMessage( int command,
                                                  byte[] params )
    {
        return createHtiMessage( HtiConstants.SERVICE_UID_FTP, command, params, 2, true );
    }

    /**
     * Wrapper to the private helper method.
     * Creates a command message for the FTP service with given parameters and
     * with no command specified.
     * Sets HTI message priority to the FTP command priority (=2).
     * This method can be used in testing the error situations when the command
     * is missing.
     *
     * @param params        The parameters for the command.
     * @return              The created HTI message as a byte array.
     */
    public static byte[] createFTPCommandMessage( byte[] params )
    {
        return createHtiMessage( HtiConstants.SERVICE_UID_FTP, -1, params, 2, false );
    }

    /**
     * Writes the HTI message to the given output stream.
     *
     * @param message       HTI message as a byte array.
     * @param out           Output stream to which the HTI message is written.
     * @throws IOException  if I/O error occurs when writing to the stream.
     */
    public static void sendHtiMessage( byte[] message, OutputStream out )
        throws IOException
    {
        out.write( message );
        out.flush();
    }

    /**
     * Encapsulates the content of the given file to the HTI messages and sends
     * them to the given output stream. Used with FTP STOR command.
     *
     * @param file          The file which is sent.
     * @param out           Output stream to which the HTI messages are written.
     * @throws Exception    if an error occurs when reading the file or
     *                      writing to the stream.
     */
    public static void sendFileToFtpService( File file, OutputStream out )
        throws Exception
    {
        if( file.length() == 0 )
        {
            sendEmptyFileToFtpService( out );
            return;
        }
                
        final int BODY_MAX_SIZE = 10*1024 - 14;
        //final int BODY_MAX_SIZE = 2*1024 - 14;

        byte[] headerBytes = new byte[14];
        long fileRemaining = file.length();
        FileInputStream fis = new FileInputStream( file );

        logger.info( "File size: " + fileRemaining );

        while( fileRemaining > 0 )
        {
            Arrays.fill( headerBytes, (byte)0 );

            // add ftp service uid to header
            CommonMethods.intToLittleEndianBytes( HtiConstants.SERVICE_UID_FTP,
                                                  headerBytes,
                                                  0,
                                                  4);

            int bodySize = fileRemaining <= BODY_MAX_SIZE
                            ? (int)fileRemaining
                            : BODY_MAX_SIZE;
            fileRemaining -= bodySize;

            logger.info( "Body size: " + bodySize );
            logger.info( "File remaining: " + fileRemaining );

            CommonMethods.intToLittleEndianBytes( bodySize, headerBytes, 4, 4 );

            // hti message version
            headerBytes[8] = (byte)1;

            // add the crc
            int crc = CommonMethods.CRC16CCITT( headerBytes, 12 );
            CommonMethods.intToLittleEndianBytes( crc, headerBytes, 12, 2 );

            // send header to the output stream
            out.write( headerBytes );

            // read message body from file, and send it to the output stream
            byte[] buf = new byte[BODY_MAX_SIZE];
            int readBytes;
            int sentBytes = 0;
            do
            {
                readBytes = fis.read( buf );
                out.write( buf, 0, readBytes );
                sentBytes += readBytes;

            } while( sentBytes < bodySize );

            out.flush();
//            Thread.sleep( 1000 );
        }
        
        out.flush();
        fis.close();
    }

    /**
     * Encapsulates the content of the given byte array to the HTI messages 
     * and sends them to the given output stream. Used with FTP STOR command.
     *
     * @param data          The byte array containing data which is sent.
     * @param out           Output stream to which the HTI messages are written.
     * @throws Exception    if an error occurs when reading the file or
     *                      writing to the stream.
     */
    public static void sendByteArrayToFtpService( byte[] data,
                                                  OutputStream out )
        throws Exception
    {
        final int BODY_MAX_SIZE = 10*1024 - 14;

        byte[] headerBytes = new byte[14];
        int dataRemaining = data.length;
        int offset = 0;

        logger.info( "Data length: " + dataRemaining );

        while( dataRemaining > 0 )
        {
            Arrays.fill( headerBytes, (byte)0 );

            // add ftp service uid to header
            CommonMethods.intToLittleEndianBytes( HtiConstants.SERVICE_UID_FTP,
                                                  headerBytes,
                                                  0,
                                                  4);

            int bodySize = dataRemaining <= BODY_MAX_SIZE
                            ? (int)dataRemaining
                            : BODY_MAX_SIZE;
            dataRemaining -= bodySize;

            logger.info( "Body size: " + bodySize );
            logger.info( "Data remaining: " + dataRemaining );

            CommonMethods.intToLittleEndianBytes( bodySize, headerBytes, 4, 4 );

            // hti message version
            headerBytes[8] = (byte)1;

            // add the crc
            int crc = CommonMethods.CRC16CCITT( headerBytes, 12 );
            CommonMethods.intToLittleEndianBytes( crc, headerBytes, 12, 2 );

            // send header to the output stream
            out.write( headerBytes );

            // get message body from data array, and send it to the 
            // output stream
            byte[] buf = new byte[BODY_MAX_SIZE];
            System.arraycopy( data, offset, buf, 0, bodySize );
            out.write( buf, 0, bodySize );
            out.flush();
            
            dataRemaining -= bodySize;
            offset += bodySize;
        }
        
        out.flush();
    }

    /**
     * Sends an empty FTP data message. Used with FTP STOR command when 
     * uploading zero sized files.
     *
     * @param out           Output stream to which the HTI messages are written.
     * @throws Exception    if an error occurs when reading the file or
     *                      writing to the stream.
     */
    public static void sendEmptyFileToFtpService( OutputStream out )
        throws Exception
    {
        // create a FTP data message, which contains no data (only HTI header)
        byte[] headerBytes = new byte[14];
        Arrays.fill( headerBytes, (byte)0 );

        // add ftp service uid to header
        CommonMethods.intToLittleEndianBytes( HtiConstants.SERVICE_UID_FTP,
                                              headerBytes,
                                              0,
                                              4);

        // bytes defining the message body size are left to zero
        
        // hti message version
        headerBytes[8] = (byte)1;

        // hti message priority is left to zero

        // flags and ext size are left to zero
        
        // add the crc
        int crc = CommonMethods.CRC16CCITT( headerBytes, 12 );
        CommonMethods.intToLittleEndianBytes( crc, headerBytes, 12, 2 );

        // send header to the output stream
        out.write( headerBytes );
    }
    
    /**
     * Reads file content encapsulated in HTI messages from an input stream
     * and writes the data to the file with given file name.
     * Used with FTP RETR command.
     *
     * @param filename      Local file name to which the received data is
     *                      written.
     * @param fileSize      The size of the file received.
     * @param in            Input stream from which the HTI messages are read.
     * @throws Exception    if an error occurs when reading from stream or
     *                      writing the file.
     */
    public static void receiveFileFromFtpService( String filename,
                                                  long fileSize,
                                                  InputStream in )
        throws Exception
    {
        byte[] header = new byte[14];
        long readBytes = 0;
        FileOutputStream fos = new FileOutputStream( filename );

        // For zero sized file we will get one empty data message
        // so must always read at least one message.
        do
        {
            //read the header
            int readHeader = 0;
            do
            {
                readHeader += in.read( header,
                                       readHeader,
                                       header.length - readHeader );
            }
            while( readHeader < header.length );

            // first check the crc
            if ( CommonMethods.CRC16CCITT( header, 12 ) !=
                 CommonMethods.littleEndianBytesToInt( header, 12, 2 ) )
            {
                logger.info( CommonMethods.formatByteArray( "Invalid header",
                                                            header,
                                                            0,
                                                            header.length ) );
                logger.info( "Header as string: " + 
                             CommonMethods.extractString( header, 
                                                          0, 
                                                          header.length ) );
                throw new HtiException( "Invalid header. CRC's do not match." );
            }

            // read the extension, if there is any
            int extSize = header[11];
            byte[] extension = new byte[extSize];
            if( extSize > 0 )
            {
                int readExt = 0;
                do
                {
                    readExt += in.read( extension, readExt, extSize - readExt );
                }
                while( readExt < extSize );
            }
            
            // get the body size from the header
            int bodySize = CommonMethods.littleEndianBytesToInt( header, 4, 4 );

            // read the body
            byte[] buf = new byte[1024];
            int received = 0;
            int readBodyBytes;
            int maxBytesToRead;
            do
            {
                // make sure not to read beyond the current HTI message
                maxBytesToRead = bodySize - received < buf.length
                               ? bodySize - received
                               : buf.length;
                readBodyBytes = in.read( buf, 0, maxBytesToRead );
                received += readBodyBytes;
                fos.write( buf, 0, readBodyBytes );
            }
            while( received < bodySize );

            readBytes += received;

            logger.info( "Read HTI message, body size: " + bodySize );
            logger.info( "Total bytes read: " + readBytes );

            fos.flush();
        }
        while( readBytes < fileSize );

        fos.close();
    }

    /**
     * Reads file content encapsulated in HTI messages from an input stream
     * and returns the file content as byte array.
     *
     * @param fileSize      The size of the file received.
     * @param in            Input stream from which the HTI messages are read.
     * @throws Exception    if an error occurs when reading from stream or
     *                      writing the file.
     */
    public static byte[] receiveFileFromFtpServiceAsByteArray( long fileSize,
                                                               InputStream in )
        throws Exception
    {
        byte[] result = new byte[(int)fileSize];
        
        byte[] header = new byte[14];
        long readBytes = 0;

        // For zero sized file we will get one empty data message
        // so must always read at least one message.
        do
        {
            //read the header
            int readHeader = 0;
            do
            {
                readHeader += in.read( header,
                                       readHeader,
                                       header.length - readHeader );
            }
            while( readHeader < header.length );

            // first check the crc
            if ( CommonMethods.CRC16CCITT( header, 12 ) !=
                 CommonMethods.littleEndianBytesToInt( header, 12, 2 ) )
            {
                logger.info( CommonMethods.formatByteArray( "Invalid header",
                                                            header,
                                                            0,
                                                            header.length ) );
                logger.info( "Header as string: " + 
                             CommonMethods.extractString( header, 
                                                          0, 
                                                          header.length ) );
                throw new HtiException( "Invalid header. CRC's do not match." );
            }

            // read the extension, if there is any
            int extSize = header[11];
            byte[] extension = new byte[extSize];
            if( extSize > 0 )
            {
                int readExt = 0;
                do
                {
                    readExt += in.read( extension, readExt, extSize - readExt );
                }
                while( readExt < extSize );
            }
            
            // get the body size from the header
            int bodySize = CommonMethods.littleEndianBytesToInt( header, 4, 4 );

            // read the body
            byte[] buf = new byte[1024];
            int received = 0;
            int readBodyBytes;
            int maxBytesToRead;
            do
            {
                // make sure not to read beyond the current HTI message
                maxBytesToRead = bodySize - received < buf.length
                               ? bodySize - received
                               : buf.length;
                readBodyBytes = in.read( buf, 0, maxBytesToRead );
                System.arraycopy( buf, 
                                  0, 
                                  result, 
                                  (int)readBytes + received, 
                                  readBodyBytes );
                received += readBodyBytes;
            }
            while( received < bodySize );

            readBytes += received;
        }
        while( readBytes < fileSize );

        return result;
    }

    /**
     * Reads a HTI message from an input stream and returns the message body
     * as a byte array.
     *
     * @param in                Input stream from which the HTI message is read.
     * @return                  The body of the HTI message that was read.
     * @throws IOException      if there is an error reading from the stream.
     * @throws HtiException     if the HTI message header is not valid.
     */
    public static byte[] receiveResponse( InputStream  in )
        throws IOException, HtiException
    {
        byte[] header = new byte[14];
        int readBytes = 0;

        //read the header
        do
        {
            readBytes += in.read( header, readBytes, header.length-readBytes );
        }
        while( readBytes < header.length );
        
        logger.fine( CommonMethods.formatByteArray( "Received message header",
                header, 0, header.length )  );

        // first check the crc
        if ( CommonMethods.CRC16CCITT( header, 12 ) !=
             CommonMethods.littleEndianBytesToInt( header, 12, 2 ) )
        {
            throw new HtiException( "Invalid header. CRC's do not match." );
        }

        // read the extension, if there is any
        int extSize = header[11];
        byte[] extension = new byte[extSize];
        if( extSize > 0 )
        {
            int readExt = 0;
            do
            {
                readExt += in.read( extension, readExt, extSize - readExt );
            }
            while( readExt < extSize );
        }
            
        // get the body size from the header
        int bodySize = CommonMethods.littleEndianBytesToInt( header, 4, 4 );
        logger.fine(  "Body size: " + bodySize );

        // read the body
        byte[] body = new byte[bodySize];
        readBytes = 0;
        do
        {
            readBytes += in.read( body, readBytes, bodySize - readBytes );
        }
        while( readBytes < bodySize );

        logger.fine( CommonMethods.formatByteArray( "Received message body",
                                                    body,
                                                    0,
                                                    body.length ) );
        return body;
    }

    /**
     * Reads a HTI message from an input stream and writes the message body
     * to the given file.
     * 
     * @param in                Input stream from which the HTI message is read.
     * @param outputFile        File to which the message body is written.
     * @throws IOException      if I/O error occurs.
     * @throws HtiException     if the HTI message header is not valid.
     */
    public static void receiveResponse( InputStream  in, File outputFile )
        throws IOException, HtiException
    {
        byte[] header = new byte[14];
        int readBytes = 0;

        //read the header
        do
        {
            readBytes += in.read( header, readBytes, header.length-readBytes );
        }
        while( readBytes < header.length );

        // check the crc
        if ( CommonMethods.CRC16CCITT( header, 12 ) !=
             CommonMethods.littleEndianBytesToInt( header, 12, 2 ) )
        {
            throw new HtiException( "Invalid header. CRC's do not match." );
        }

        // read the extension, if there is any
        int extSize = header[11];
        byte[] extension = new byte[extSize];
        if( extSize > 0 )
        {
            int readExt = 0;
            do
            {
                readExt += in.read( extension, readExt, extSize - readExt );
            }
            while( readExt < extSize );
        }
        
        // get the body size from the header
        int bodySize = CommonMethods.littleEndianBytesToInt( header, 4, 4 );

        // read the body
        byte[] body = new byte[bodySize];
        readBytes = 0;
        do
        {
            readBytes += in.read( body, readBytes, bodySize - readBytes );
        }
        while( readBytes < bodySize );

        // write the message body to the output file
        FileOutputStream fos = new FileOutputStream( outputFile );
        fos.write( body );
        fos.flush();
        fos.close();
    }

    /**
     * Sends a press key command to the HTI's key event service with given 
     * key code and verifies the OK response.
     * 
     * @param out       Output stream to which the HTI message is written
     * @param in        Input strem from which the response is read
     * @param keyCode   The key code for the key to be pressed
     * @throws IOException  if writing to or reading from stream fails
     * @throws HtiException if no OK reply is received
     */
    public static void pressKey( OutputStream out,
                                 InputStream  in,
                                 int keyCode ) 
        throws IOException, HtiException
    {
        byte[] keyBytes = new byte[2];
        CommonMethods.intToLittleEndianBytes( keyCode, keyBytes, 0, 2 );
        byte[] msg = 
            HtiHelper.createHtiMessage( HtiConstants.SERVICE_UID_KEYEVENT, 
                                        HtiConstants.CMD_KEYEVENT_PRESSKEY,
                                        keyBytes );
        HtiHelper.sendHtiMessage( msg, out );
        byte[] response = HtiHelper.receiveResponse( in );
        if( (byte)HtiConstants.CMD_KEYEVENT_OK != response[0] )
        {
            throw new HtiException( "Pressing key failed with key code: " + 
                                    keyCode );
        }
    }
    
    /**
     * Formats an HTI error response to a human readable form
     * 
     * @param response  The response bytes received from HTI
     * @return HTI error code, service error code, plugin UID
     *         and error description. Empty string if data is not HTI error msg.
     * @throws HtiException if no OK reply is received
     */
    public static String formatHtiErrorMsg( byte[] response )
    {
        if ( response.length < 10 ) return "";
        if ( response[0] != (byte) 0xFF ) return "";
        
        StringBuffer sb = new StringBuffer();
        sb.append( "HtiError = " );
        if ( response[1] == HtiConstants.ERROR_HTI_MSG_TOO_BIG )
            sb.append( "MessageTooBig" );
        else if ( response[1] == HtiConstants.ERROR_HTI_OUT_OF_MEMORY )
            sb.append( "OutOfMemory" );
        else if ( response[1] == HtiConstants.ERROR_HTI_SERVICE_NOT_FOUND )
            sb.append( "ServiceNotFound" );
        else if ( response[1] == HtiConstants.ERROR_HTI_SERVICE_ERROR )
            sb.append( "ServiceError" );
        else if ( response[1] == HtiConstants.ERROR_HTI_NOT_AUTHORIZED )
            sb.append( "NotAuthorized" );
        else if ( response[1] == HtiConstants.ERROR_HTI_FAILED_UNWRAP )
            sb.append( "FailedUnwrap" );
        else
            sb.append( "Unknown" );
        
        sb.append( "\nServiceErrorCode = " );
        sb.append( CommonMethods.littleEndianBytesToInt( response, 2, 4 ) );
        sb.append( "\nPluginUID = " );
        sb.append( CommonMethods.littleEndianBytesToInt(response, 6, 4 ) );
        sb.append( "\nDescription = " );
        if ( response.length > 10 )
        {
            sb.append( new String ( response ).substring( 10 ) );
        }
        return sb.toString();
    }

    
    
//==============================================================================
//Protected methods

//==============================================================================
//Private methods

    /**
     * Creates a HTI message from given destination service UID,
     * command, parameters and priority.
     *
     * @param serviceUid    The UID of the destination service.
     * @param command       The command for the destination service.
     * @param params        Optional parameters for the command.
     * @param priority      The priority in HTI message.
     * @param withCommand   Flag indicating if the command is present or omited.
     * @return              The created HTI message as a byte array.
     */
    private static byte[] createHtiMessage( int serviceUid,
                                           int command,
                                           byte[] params,
                                           int priority,
                                           boolean withCommand )
    {
        // first construct the message header
        byte[] headerBytes = new byte[14];
        Arrays.fill( headerBytes, (byte)0 );

        // add service UID, 4 bytes
        CommonMethods.intToLittleEndianBytes( serviceUid, headerBytes, 0, 4 );

        // add body size, 4 bytes
        int bodySize = withCommand ? 1 + params.length : params.length;
        CommonMethods.intToLittleEndianBytes( bodySize, headerBytes, 4, 4 );

        // hti message version
        headerBytes[8] = (byte)1;

        // hti message priority
        headerBytes[9] = (byte)priority;

        // leave flags and ext size to zero

        // add the crc
        int crc = CommonMethods.CRC16CCITT( headerBytes, 12 );
        CommonMethods.intToLittleEndianBytes( crc, headerBytes, 12, 2 );


        // next, construct the whole message, header + command + params
        byte[] messageBytes = new byte[headerBytes.length + bodySize];
        System.arraycopy( headerBytes, 0, messageBytes, 0, headerBytes.length );
        if( withCommand )
        {
            messageBytes[headerBytes.length] = (byte)command;
            System.arraycopy( params,
                              0,
                              messageBytes,
                              headerBytes.length + 1,
                              params.length );
        }
        else
        {
            System.arraycopy( params,
                              0,
                              messageBytes,
                              headerBytes.length,
                              params.length );
        }

        logger.info( CommonMethods.formatByteArray( "Created message",
                                                    messageBytes,
                                                    0,
                                                    messageBytes.length ) );

        return messageBytes;
    }

//==============================================================================
//Protected attributes

//==============================================================================
//Private attributes

//==============================================================================
//Static initialization block

    public static Logger logger;
    static
    {
        logger = CommonMethods.getSimpleLogger( HtiHelper.class.getName(),
                                                Level.SEVERE );
    }

//==============================================================================
//Inner classes
}
