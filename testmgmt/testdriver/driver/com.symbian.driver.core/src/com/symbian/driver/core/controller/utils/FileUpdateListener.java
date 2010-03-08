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



package com.symbian.driver.core.controller.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUpdateListener implements Runnable {
	
	/** Logger for this class. */
	static final Logger LOGGER = Logger.getLogger(FileUpdateListener.class.getName());
	
	private File iOutputFile = null;
	private File iInputFile = null;
	BufferedReader fReader = null;
	BufferedWriter fWriter = null;
	boolean iLive;
	
	public FileUpdateListener() {
		iLive = true;
		(new Thread(this)).start();
	}
	
	
	public void run() {
		while (iLive) {
			// while there is an input and output : copy
			String line = null;
			while(fReader != null){
				try {
					//read non stop as other infirmation could be in the epocwind.out file such as tracing
					//ced and other things that are not RTests output.
			          line = fReader.readLine();
			          if (line != null) {
			        	  //a new line
			        	  if (fWriter != null) {
			        		  //An output is set then copy
								fWriter.write(line);
								fWriter.newLine(); // Write system dependent end of
													// line.
			        	  }
			          }
				} catch (IOException lIOException) {
					LOGGER.log(Level.SEVERE, "Failed to read from " + iInputFile + " " + lIOException.getMessage());
				}
			}
		}
	}
	
	public void setOutputFile(File aFile) {
		iOutputFile = aFile;
		try {
			if (fWriter != null)
			{
				fWriter.close();
				fWriter = null;
			}
			if (aFile != null) {
				fWriter = new BufferedWriter(new FileWriter(aFile));
			}
		} catch (IOException lIOException) {
			LOGGER.log(Level.SEVERE, "Failed to set Rdebug output file " + iOutputFile + " " + lIOException.getMessage());
		}
	}
	
	public void setInputFile(File aFile) {
		iInputFile = aFile;
		try {
			fReader = new BufferedReader(new FileReader(aFile.getAbsolutePath()));
		} catch (FileNotFoundException lIOException) {
			LOGGER.log(Level.SEVERE, "Failed to set RDebug input file " + iInputFile + " " + lIOException.getMessage());
		}
	}
	
	public void setMlife(boolean aLife) {
		iLive = aLife;		
	}
	
}
