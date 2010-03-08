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


package com.symbian.driver;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
	   public static void main(String args[]) {
	      String data = "Toobie ornaught toobie";
	      try {
	         ServerSocket srvr = new ServerSocket(1234);
	         Socket skt = srvr.accept();
	         System.out.print("Server has connected!\n");
	         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
	         System.out.print("Sending string: '" + data + "'\n");
	         out.print(data);
	         out.close();
	         skt.close();
	         srvr.close();
	      }
	      catch(Exception e) {
	         System.out.print("Whoops! It didn't work!\n");
	      }
	   }
	}
