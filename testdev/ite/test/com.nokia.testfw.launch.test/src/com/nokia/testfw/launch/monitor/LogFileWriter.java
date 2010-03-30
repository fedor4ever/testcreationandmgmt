/*
* Copyright (c) 2005-2009 Nokia Corporation and/or its subsidiary(-ies). 
* All rights reserved.
* This component and the accompanying materials are made available
* under the terms of the License "Symbian Foundation License v1.0"
* which accompanies this distribution, and is available
* at the URL "http://www.symbianfoundation.org/legal/sfl-v10.html".
*
* Initial Contributors:
* Nokia Corporation - initial contribution.
*
* Contributors:
*
* Description:
*
*/
package com.nokia.testfw.launch.monitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiaoma
 * This is a test class used to continue write content to a file
 */
public class LogFileWriter implements Runnable {

	PrintWriter out;
	int times;
	public LogFileWriter(String file, int times) throws IOException {
		
		out = new PrintWriter(new BufferedWriter(
				   new FileWriter(file, false)));
		this.times = times;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		 
	    out.println("oldtest");
	    out.flush();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    int time = 0;	
	    System.err.println("write start to file");
		while (time < times) {
		    try {
		    	time++;
				System.err.println("write to log");
			    out.println("test" + time);
			    out.flush();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		System.err.println("write log finished");
		out.close();
		out = null;
		
	}

}
