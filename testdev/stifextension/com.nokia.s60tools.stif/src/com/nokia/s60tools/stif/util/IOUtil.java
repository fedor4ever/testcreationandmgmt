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

/**
 * Class for making processes ignore system input 
 *
 */
public class IOUtil {
	 
	  public static Process ignoreProcessStreams(Process process) {
	    ignoreProcessInputStream(process);
	    ignoreProcessErrorStream(process);
	    return process;
	  }
	 
	  public static Process ignoreProcessInputStream(Process process) {
	    new InputStreamIgnorer(process.getInputStream());
	    return process;
	  }
	 
	  /**
	   * Set error input ignoring
	   * 
	   * @param process
	   * 		process that should ignore input
	   * @return
	   * 		modified process
	   */
	  public static Process ignoreProcessErrorStream(Process process) {
	    new InputStreamIgnorer(process.getErrorStream());
	    return process;
	  }
	 
	  
	  static class InputStreamIgnorer implements Runnable {
	    java.io.InputStream in;
	    
	    InputStreamIgnorer(java.io.InputStream in) {
	      this.in = in;
	      new Thread(this,"InputStreamIgnorer Thread").start();
	    }
	    
	    public void run() {
	      try {
	        while((in.read()) != -1) {
	        }
	      } catch (java.io.IOException ex) {} //ignore
	    }
	  } 
	 
	}


