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

package com.symbian.driver.engine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.naming.TimeLimitExceededException;

import com.symbian.driver.engine.impl.ExecuteOnDevice;
import com.symbian.jstat.JStat;
import com.symbian.jstat.JStatException;
import com.symbian.jstat.JStatResult;

/**
 * @author EngineeringTools
 *
 */
public class Utils extends ExecuteOnDeviceTest {

	/**
	 * @param aCmd
	 * @param aFirstParameter
	 * @param aSecondParameter
	 * @return
	 * @throws JStatException
	 * @throws IOException
	 * @throws TimeLimitExceededException
	 */
	public static final BufferedReader executeOnDevice(final int aCmd, final String aFirstParameter, final String aSecondParameter) throws JStatException, IOException, TimeLimitExceededException {
		PipedOutputStream lPipedOutputStream = new PipedOutputStream();
		PipedInputStream lPipedInputStream = new PipedInputStream(lPipedOutputStream);
		
		if (aFirstParameter == null && aSecondParameter == null) {
			new ExecuteOnDevice(aCmd).doTask(true, EngineTests.TIMEOUT, false );
		} else if (aFirstParameter != null && aSecondParameter == null) {
			new ExecuteOnDevice(aCmd, aFirstParameter).doTask(true, EngineTests.TIMEOUT, false);
		} else if (aFirstParameter != null && aSecondParameter != null) {
			new ExecuteOnDevice(aCmd, aFirstParameter, aSecondParameter).doTask(true, EngineTests.TIMEOUT, false);
		}
		
		BufferedReader lBufferedReader = new BufferedReader(new InputStreamReader(lPipedInputStream));
//		lPipedInputStream.close();
		lPipedOutputStream.close();
		
		return lBufferedReader;
	}
	
	/**
	 * @param aBufferedReader
	 * @param aString
	 * @return
	 * @throws IOException
	 */
	public static final boolean readBuffer(BufferedReader aBufferedReader, String[] aString) throws IOException {
		String lReadLine = null;
		int lLinesRead = 0;
		try {
		while (lLinesRead  < 20 && aBufferedReader.ready() && (lReadLine = aBufferedReader.readLine()) != null) {
			System.out.println(lReadLine);
			for (int lIter = 0; lIter < aString.length; lIter++) {
				if (lReadLine.toLowerCase().indexOf(aString[lIter]) >= 0) {
					aBufferedReader.close();
					return true;
				}
			}
			lLinesRead++;
		}
		} catch (Exception e) {
			System.out.println("Buffer broke");
		}
		aBufferedReader.close();
		return false;
	}
	
	/**
	 * 
	 */
	public static void jStatInfo() {
		try {
			JStatResult lJStatResult = JStat.getInstance().getInfo();
			if (lJStatResult.getReceivedData() == "" || lJStatResult.getReceivedData() == null) {
				fail();
			}
		} catch (JStatException e) {
			fail();
		} catch (TimeLimitExceededException e) {
			fail();
		}
	}
}
