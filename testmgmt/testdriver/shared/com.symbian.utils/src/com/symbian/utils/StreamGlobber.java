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

package com.symbian.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Outputformatter for the ExecuteOnHost class.
 */
public class StreamGlobber extends Thread {

	/** Logger. */
	private static final Logger LOGGER = Logger.getLogger(StreamGlobber.class.getName());

	/** The input stream. */
	private BufferedReader iBufferedReader = null;

	/** The output stream STDOUT. */
	private StringBuffer iOutputBuffer = null;
	
	/** The error stream STDERR. */
	private boolean iErrorStream = false;

	/** The discription of the Stream Globber. */
	private String iInfoMessage = null;

	/**
	 * The constructor of the StreamGobbler.
	 * 
	 * @param aOutputBuffer
	 *            The output Buffer.
	 * @param aInputStream
	 *            The input stream.
	 * @param aErrorStream 
	 * @param aInfoMessage
	 */
	public StreamGlobber(final StringBuffer aOutputBuffer, final InputStream aInputStream, boolean aErrorStream, String aInfoMessage) {
		LOGGER.entering("StreamGlobber", "Constructor");

		if (aOutputBuffer != null) {
			iOutputBuffer = aOutputBuffer;
		} else {
			iOutputBuffer = new StringBuffer();
		}

		iBufferedReader = new BufferedReader(new InputStreamReader(aInputStream));
		iInfoMessage = aInfoMessage;
		iErrorStream  = aErrorStream;
		setName("StreamGlobber " + aInfoMessage);
	}

	/**
	 * Runs the StreamGlobbers output parser.=
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		LOGGER.entering("StreamGlobber", "run");
		
		if (this == Thread.currentThread()) {
			try {
				
				StringBuffer lSevereBuffer = new StringBuffer();
				StringBuffer lWarningBuffer = new StringBuffer();
				
				// Read all lines.
				String lOutput = null;
				while ((lOutput = iBufferedReader.readLine()) != null) {
					lOutput += System.getProperty("line.separator");
					iOutputBuffer.append(lOutput);
					// Parse for errors and redirect to appropriate buffer.
					if (!iErrorStream) {
						if ((lOutput.toLowerCase().indexOf("failed") != -1
								|| lOutput.toLowerCase().indexOf("panicked") != -1
								|| lOutput.toLowerCase().indexOf("paniced") != -1
								|| lOutput.toLowerCase().indexOf("error") != -1
								)&&(lOutput.toLowerCase().indexOf("no warning")== -1)
								&&(lOutput.toLowerCase().indexOf("no error")== -1)) {
							lSevereBuffer.append(lOutput);
						} else if ((lOutput.toLowerCase().indexOf("warning") != -1
								|| lOutput.toLowerCase().indexOf("timed out") != -1)
								&&(lOutput.toLowerCase().indexOf("no warning")== -1)
								&&(lOutput.toLowerCase().indexOf("no error")== -1)) {
							lWarningBuffer.append(lOutput);
						}
					} else {
						lWarningBuffer.append(lOutput);
					}
				}

				// Add to SEVERE log
				if (lSevereBuffer != null && lSevereBuffer.length() != 0) {
					LOGGER.severe(iInfoMessage + ":\n" + lSevereBuffer.toString());
				}
					
				// Add to WARNING log
				if (lWarningBuffer != null && lWarningBuffer.length() != 0) {
					LOGGER.warning(iInfoMessage + ":\n" + lWarningBuffer.toString());
				}
				
				// Add to INFO log
				if (!iErrorStream && iOutputBuffer != null && iOutputBuffer.length() != 0) {
					LOGGER.info(iInfoMessage + ":\n" + iOutputBuffer.toString());
				} else {
					LOGGER.finest("The Ouput Buffer was empty.");
				}
				
				// Close streams
				iBufferedReader.close();
				
			} catch (IOException lIOException) {
				LOGGER.log(Level.SEVERE, "Streamglobber failed: " + lIOException.getMessage(), lIOException);
			}
		}
	}
}
