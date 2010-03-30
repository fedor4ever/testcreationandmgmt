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
* Description:  Helper class containing common non-HTI-specific helper
*                functions that can be used in other java classes.
*
*/


package com.nokia.hti.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.InflaterInputStream;

/**
 * Helper class containing static non-hti-specific helper functions that can
 * be used in other java classes.
 *
 */
public class CommonMethods
{
//==============================================================================
//Public constants

    public static final int[] CRCLookUp =
        {
            0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50A5, 0x60C6, 0x70E7,
            0x8108, 0x9129, 0xA14A, 0xB16B, 0xC18C, 0xD1AD, 0xE1CE, 0xF1EF,
            0x1231, 0x0210, 0x3273, 0x2252, 0x52B5, 0x4294, 0x72F7, 0x62D6,
            0x9339, 0x8318, 0xB37B, 0xA35A, 0xD3BD, 0xC39C, 0xF3FF, 0xE3DE,
            0x2462, 0x3443, 0x0420, 0x1401, 0x64E6, 0x74C7, 0x44A4, 0x5485,
            0xA56A, 0xB54B, 0x8528, 0x9509, 0xE5EE, 0xF5CF, 0xC5AC, 0xD58D,
            0x3653, 0x2672, 0x1611, 0x0630, 0x76D7, 0x66F6, 0x5695, 0x46B4,
            0xB75B, 0xA77A, 0x9719, 0x8738, 0xF7DF, 0xE7FE, 0xD79D, 0xC7BC,
            0x48C4, 0x58E5, 0x6886, 0x78A7, 0x0840, 0x1861, 0x2802, 0x3823,
            0xC9CC, 0xD9ED, 0xE98E, 0xF9AF, 0x8948, 0x9969, 0xA90A, 0xB92B,
            0x5AF5, 0x4AD4, 0x7AB7, 0x6A96, 0x1A71, 0x0A50, 0x3A33, 0x2A12,
            0xDBFD, 0xCBDC, 0xFBBF, 0xEB9E, 0x9B79, 0x8B58, 0xBB3B, 0xAB1A,
            0x6CA6, 0x7C87, 0x4CE4, 0x5CC5, 0x2C22, 0x3C03, 0x0C60, 0x1C41,
            0xEDAE, 0xFD8F, 0xCDEC, 0xDDCD, 0xAD2A, 0xBD0B, 0x8D68, 0x9D49,
            0x7E97, 0x6EB6, 0x5ED5, 0x4EF4, 0x3E13, 0x2E32, 0x1E51, 0x0E70,
            0xFF9F, 0xEFBE, 0xDFDD, 0xCFFC, 0xBF1B, 0xAF3A, 0x9F59, 0x8F78,
            0x9188, 0x81A9, 0xB1CA, 0xA1EB, 0xD10C, 0xC12D, 0xF14E, 0xE16F,
            0x1080, 0x00A1, 0x30C2, 0x20E3, 0x5004, 0x4025, 0x7046, 0x6067,
            0x83B9, 0x9398, 0xA3FB, 0xB3DA, 0xC33D, 0xD31C, 0xE37F, 0xF35E,
            0x02B1, 0x1290, 0x22F3, 0x32D2, 0x4235, 0x5214, 0x6277, 0x7256,
            0xB5EA, 0xA5CB, 0x95A8, 0x8589, 0xF56E, 0xE54F, 0xD52C, 0xC50D,
            0x34E2, 0x24C3, 0x14A0, 0x0481, 0x7466, 0x6447, 0x5424, 0x4405,
            0xA7DB, 0xB7FA, 0x8799, 0x97B8, 0xE75F, 0xF77E, 0xC71D, 0xD73C,
            0x26D3, 0x36F2, 0x0691, 0x16B0, 0x6657, 0x7676, 0x4615, 0x5634,
            0xD94C, 0xC96D, 0xF90E, 0xE92F, 0x99C8, 0x89E9, 0xB98A, 0xA9AB,
            0x5844, 0x4865, 0x7806, 0x6827, 0x18C0, 0x08E1, 0x3882, 0x28A3,
            0xCB7D, 0xDB5C, 0xEB3F, 0xFB1E, 0x8BF9, 0x9BD8, 0xABBB, 0xBB9A,
            0x4A75, 0x5A54, 0x6A37, 0x7A16, 0x0AF1, 0x1AD0, 0x2AB3, 0x3A92,
            0xFD2E, 0xED0F, 0xDD6C, 0xCD4D, 0xBDAA, 0xAD8B, 0x9DE8, 0x8DC9,
            0x7C26, 0x6C07, 0x5C64, 0x4C45, 0x3CA2, 0x2C83, 0x1CE0, 0x0CC1,
            0xEF1F, 0xFF3E, 0xCF5D, 0xDF7C, 0xAF9B, 0xBFBA, 0x8FD9, 0x9FF8,
            0x6E17, 0x7E36, 0x4E55, 0x5E74, 0x2E93, 0x3EB2, 0x0ED1, 0x1EF0
        };

//==============================================================================
//Public methods

    /**
     * Creates a logger with given level for the give class.
     *
     * @param className     the name of the class for which
     *                      the logger is created.
     * @param level         logging level for the logger.
     * @return              the created logger.
     */
    public static Logger getSimpleLogger( String className, Level level )
    {
        Logger logger = Logger.getLogger( className );
        logger.setLevel( level );
        logger.setUseParentHandlers( false );

        ConsoleHandler h = new ConsoleHandler();
        h.setFormatter( new SimpleLogFormatter() );
        logger.addHandler( h );

        return logger;
    }

    /**
     * Calculates the CRC value for the given data with given length.
     *
     * @param data      Data from which the CRC is calculated.
     * @param length    The amount of data to be included in CRC calculation.
     * @return          The calculated CRC.
     */
    public static int CRC16CCITT( byte[] data, int length )
    {
        int crc16= 0xFFFF;

        for( int i = 0; i < length; i++ )
        {
            int t = ( crc16 >> 8 ) ^ ( data[i] & 0xFF );
            crc16 = ( ( crc16 << 8 ) & 0xffff ) ^ CRCLookUp[t];
        }

        return crc16;
    }

    /**
     * Converts an array of bytes to integer value. The bytes in the array are
     * expected to be in the little endian format.
     *
     * @param data      The byte array in little endian format.
     * @param offset    The offset in the source array from where the
     *                  bytes are read onwards.
     * @param length    The number of bytes to read from the source array.
     * @return          The resulting int value.
     */
    public static int littleEndianBytesToInt( byte[] data,
                                              int offset,
                                              int length )
    {
        int result = 0;
        int shiftBits = 0;

        for( int i = 0; i < length; i++ )
        {
            result += ( data[offset + i] & 0xFF ) << shiftBits;
            shiftBits += 8;
        }

        return result;
    }

    /**
     * Converts an array of bytes to long value. The bytes in the array are
     * expected to be in the little endian format.
     *
     * @param data      The byte array in little endian format.
     * @param offset    The offset in the source array from where the
     *                  bytes are read onwards.
     * @param length    The number of bytes to read from the source array.
     * @return          The resulting long value.
     */
    public static long littleEndianBytesToLong( byte[] data,
                                               int offset,
                                               int length )
    {
        long result = 0;
        int shiftBits = 0;

        for( int i = 0; i < length; i++ )
        {
            result += ( data[offset + i] & 0xFF ) << shiftBits;
            shiftBits += 8;
        }

        return result;
    }

    /**
     * Converts an array of bytes to integer value. The bytes in the array are
     * expected to be in the big endian format.
     *
     * @param data      The byte array in big endian format.
     * @param offset    The offset in the source array from where the
     *                  bytes are read onwards.
     * @param length    The number of bytes to read from the source array.
     * @return          The resulting int value.
     */
    public static int bigEndianBytesToInt( byte[] data,
                                           int offset,
                                           int length )
    {
        int result = 0;
        int shiftBits = 8 * ( length - 1 );

        for( int i = 0; i < length; i++ )
        {
            result += ( data[offset + i] & 0xFF ) << shiftBits;
            shiftBits -= 8;
        }

        return result;
    }

    /**
     * Converts an int to the array of bytes in the little endian format.
     *
     * @param value         The int value to be converted to the array of bytes.
     * @param destination   The byte array to where the result is stored.
     * @param offset        The offset in the result array from where the
     *                      result bytes are stored onwards.
     * @param length        The number of bytes to store the result.
     */
    public static void intToLittleEndianBytes( int value,
                                               byte[] destination,
                                               int offset,
                                               int length )
    {
        int shiftBits = 0;

        for( int i = 0; i < length; i++ )
        {
            destination[offset + i] =  (byte)( ( value >> shiftBits  ) & 0xFF );
            shiftBits += 8;
        }
    }
    
    /**
     * Returns an int as the array of bytes.
     * 
     * @param value     The int value to be converted to the array of bytes.
     * @param length    The length of byte array which the int is converted to.
     */
    public static byte[] intToLittleEndianBytes( int value, int length )
    {
        int shiftBits = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for( int i = 0; i < length; i++ )
        {
            baos.write( (byte)( ( value >> shiftBits  ) & 0xFF ) );
            shiftBits += 8;
        }
        
        return baos.toByteArray();
    }
    
    /**
     * Converts a long to the array of bytes in the little endian format.
     *
     * @param value         The long value to be converted to the
     *                      array of bytes.
     * @param destination   The byte array to where the result is stored.
     * @param offset        The offset in the result array from where the
     *                      result bytes are stored onwards.
     * @param length        The number of bytes to store the result.
     */
    public static void longToLittleEndianBytes( long value,
                                                byte[] destination,
                                                int offset,
                                                int length )
    {
        int shiftBits = 0;

        for( int i = 0; i < length; i++ )
        {
            destination[offset + i] =  (byte)( ( value >> shiftBits  ) & 0xFF );
            shiftBits += 8;
        }
    }

    /**
     * Returns a part from byte array as a string.
     *
     * @param source    Source byte array.
     * @param offset    Starting point in the source array.
     * @param length    The number of bytes to read from the source array.
     * @return          The result string.
     */
    public static String extractString( byte[] source, int offset, int length )
    {
        byte[] part = new byte[length];
        System.arraycopy( source, offset, part, 0, length );
        return new String( part );
    }

    /**
     * Returns a part of given byte array in nice human readable form.
     *
     * @param topic     Topic for returned log record.
     * @param data      Byte array containing the logging data.
     * @param start     Starting index in byte array from which the bytes are
     *                  included.
     * @param length    The number of bytes in the array to be included.
     * @return          The part of the byte array as a String.
     */
    public static String formatByteArray( String topic,
                                          byte[] data,
                                          int start,
                                          int length )
    {
        StringBuffer sb = new StringBuffer();
        sb.append( topic + ": " );

        for( int i = start; i < start + length; i++ )
        {
            if( i > start ) sb.append( " " );
            sb.append( toHex( data[i] ) );
        }

        return sb.toString();
    }

    /**
     * Renders a given by to nice human readable hex format.
     *
     * @param b     The byte to be converted to a string.
     * @return      The result as  a string, in the format 0x00.
     */
    public static String toHex( byte b )
    {
        StringBuffer sb = new StringBuffer( 4 );
        sb.append( "0x" );
        int val = b & 0xff;
        if( val < 0x10 ) sb.append( "0" );
        sb.append( Integer.toString( val, 16 ) );
        return sb.toString();
    }

    /**
     * Decompresses the given byte array of compressed data.
     * Returns the decompressed data as a byte array.
     *
     * @param source            byte array containing the compressed data
     * @return                  decompressed data as a byte array
     * @throws IOException      if I/O error occurs
     */
    public static byte[] decompress( byte[] source ) throws IOException
    {
        // decompress the response through InflaterInputStream
        InflaterInputStream iis =
            new InflaterInputStream( new ByteArrayInputStream( source ) );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int readBytes;

        while( ( readBytes = iis.read( buffer ) ) != -1 )
        {
            baos.write( buffer, 0, readBytes );
        }
        iis.close();

        return baos.toByteArray();
    }

    /**
     * Gets the stack trace from the given throwable object as a string.
     *
     * @param t     The throwable object whose stack trace is returned
     * @return      The stack trace as a string.
     */
    public static String getStackTrace( Throwable t )
    {
        StringWriter stringWriter = new StringWriter();
        t.printStackTrace( new PrintWriter( stringWriter ) );
        return stringWriter.toString();
    }

    /**
     * Calculates an MD5 checksum for the given file.
     *
     * @param f     The file that the checksum is calculated for
     * @return      The checksum as a byte array
     * @throws Exception    if I/O error occurs
     */
    public static byte[] calculateMd5( File f ) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance( "MD5" );

        long fileRemaining = f.length();
        FileInputStream fis = new FileInputStream( f );
        while( fileRemaining > 0 )
        {
            byte[] buf = new byte[1024];
            int bytesRead = fis.read( buf );
            md.update( buf, 0, bytesRead );
            fileRemaining -= bytesRead;
        }

        return md.digest();
    }

    /**
     * Generated a dummy test file, filled with zeroes.
     * Used in FTP service tests.
     *
     * @param fileName      The name of the test file to be generated.
     * @param size          The size of the test file to be generated.
     * @throws IOException  if I/O exception occurs.
     */
    public static void generateTestFile( String fileName, int size )
        throws IOException
    {
        File f = new File( fileName );

        deleteTestFile( f );

        FileOutputStream fos = new FileOutputStream( f );
        byte[] buffer = new byte[size];
        Arrays.fill( buffer, (byte)0x06 );
        fos.write( buffer );
        fos.flush();
        fos.close();
    }

    /**
     * Generates a text file with given text and/or random characters.
     * If given text string is null the file will be filled with random
     * characters. Also if given text is shorter than the desired file size
     * the text will be inserted first and then file is filled to desired
     * size with random characters.
     *
     * @param fileName      The name of the text file to be generated.
     * @param text          The text to be written to the file.
     * @param size          Size of the file to create (in bytes). 
     * @throws IOException  if I/O exception occurs.
     */
    public static void generateTextFile( String fileName, String text, int size )
        throws IOException
        {
        ByteArrayOutputStream characters = new ByteArrayOutputStream( size );
        if ( text != null )
        {
            characters.write( text.getBytes(), 0, text.getBytes().length );
            characters.write( (byte)32 );
        }
        if ( characters.size() < size )
        {
            Random rand = new Random( System.currentTimeMillis() );
            for ( int i = 0; i < size; i++ )
            {
                if ( i > 0 && i % 5 == 0 )
                {
                    // insert space character
                    characters.write( (byte)32 );
                }
                else
                {
                    // draw a random lowercase character
                    characters.write( (byte)( rand.nextInt( 25 ) + 97 ) );
                }
            }
        }
        File f = new File( fileName );
        deleteTestFile( f );
        FileOutputStream fos = new FileOutputStream( f );
        fos.write( characters.toByteArray(), 0, size );
        fos.flush();
        fos.close();
        }
    
    /**
     * Checks if given file exists and is a file and if so, deletes it.
     *
     * @param f     The file to be deleted.
     */
    public static void deleteTestFile( File f )
    {
        if ( f.exists() && f.isFile() )
        {
            System.gc();
            f.delete();
        }
    }

//==============================================================================
//Protected methods

//==============================================================================
//Private methods

//==============================================================================
//Protected attributes

//==============================================================================
//Private attributes

//==============================================================================
//Static initialization block

//==============================================================================
//Inner classes
}
