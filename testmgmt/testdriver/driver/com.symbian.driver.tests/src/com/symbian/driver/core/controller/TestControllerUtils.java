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

package com.symbian.driver.core.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.symbian.driver.CmdPC;
import com.symbian.driver.DriverFactory;
import com.symbian.driver.Phase;
import com.symbian.driver.util.DriverSwitch;

/**
 * @author EngineeringTools
 *
 */
public class TestControllerUtils {

	private static final DriverFactory iDriverFactory = DriverFactory.eINSTANCE;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static final void UCCServer() throws IOException {
		ServerSocket srvr = null;
		Socket skt = null;
		DataOutputStream lSocketWriter = null;
		DataInputStream lSocketReader = null;
		
		try {
			srvr = new ServerSocket(1234);
			skt = srvr.accept();
			System.out.println("Server has connected.");

			lSocketWriter = new DataOutputStream(skt.getOutputStream());
			lSocketReader = new DataInputStream(skt.getInputStream());

			System.out.println("Waiting for TestDriver to send RunNumber");
			int lRunNumber = lSocketReader.readInt();
			System.out.println("Recieved run number: " + lRunNumber);

			for (int i=0; i<20; i++) {
				Thread.sleep(1000);
				System.out.println("Waiting!");
			}

			System.out.println("Echo Back run number");
			lSocketWriter.writeInt(lRunNumber);
			lSocketWriter.flush();


		} catch (Exception e) {
			System.out.print("Whoops! It didn't work!\n");
		} finally {
			lSocketWriter.close();
			lSocketReader.close();
			skt.close();
			srvr.close();
		}
	}

	/**
	 * @param aDriverSwitch
	 * @param aPhase 
	 * @param aRunnable 
	 */
	public static final boolean createAndTestCmdPc(DriverSwitch aDriverSwitch, Phase aPhase, boolean aIsPcVisitor) {
		CmdPC lCmdPCBuildPhase = iDriverFactory.createCmdPC();
		
		lCmdPCBuildPhase.setPhase(aPhase);
		lCmdPCBuildPhase.setSync(false);
		lCmdPCBuildPhase.setURI("c:\\");
		lCmdPCBuildPhase.setValue("notepad.exe");
		
		Object lReturn = aDriverSwitch.doSwitch(lCmdPCBuildPhase);
		
		if (aIsPcVisitor && !(lReturn instanceof CmdPC)) {
			return false;
		}
		
		return true;
	}
}
