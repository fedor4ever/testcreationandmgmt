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

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * @author EngineeringTools
 *
 */
public class AnchorSocketFactory extends RMISocketFactory implements
		Serializable {

	/** The IP Interface to create the Anchor Socket */
	private InetAddress iIpInteface = null;

	/**
	 * @param aIpInterface
	 */
	public AnchorSocketFactory(InetAddress aIpInterface) {
		this.iIpInteface = aIpInterface;
	}

	/**
	 * @see java.rmi.server.RMISocketFactory#createServerSocket(int)
	 */
	public ServerSocket createServerSocket(int port) throws SecurityException,IOException {
		return(new ServerSocket(port, 50, iIpInteface));
	}

	/**
	 * @see java.rmi.server.RMISocketFactory#createSocket(java.lang.String, int)
	 */
	public Socket createSocket(String dummy, int port) throws IOException {
		return (new Socket(iIpInteface, port));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		return (that != null && that.getClass() == this.getClass());
	}
}
