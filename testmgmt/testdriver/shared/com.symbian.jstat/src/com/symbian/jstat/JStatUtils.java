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

package com.symbian.jstat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

/**
 * @author EngineeringTools
 *
 */
public class JStatUtils {


	/**
	 * This is a copy of the Utils package copy.
	 * 
	 * @param aCopyFrom
	 * @param aCopyTo
	 * @throws IOException
	 */
	public static final void copy(final File aCopyFrom, final File aCopyTo) throws IOException {		
		if (!aCopyTo.getParentFile().isDirectory()
				&& !aCopyTo.getParentFile().mkdirs()) {
			throw new IOException("Could not create copy to directory: "
					+ aCopyTo.getAbsolutePath());
		}
	
		FileChannel lSrcChannel = new FileInputStream(aCopyFrom).getChannel();
		FileChannel lDstChannel = new FileOutputStream(aCopyTo).getChannel();
	
		Logger.getAnonymousLogger().fine("\tCopying from \"" + aCopyFrom.getCanonicalPath()
				+ "\" to \"" + aCopyTo.getCanonicalPath() + "\"");
		
		lDstChannel.transferFrom(lSrcChannel, 0, lSrcChannel.size());
		
		lSrcChannel.close();
		lDstChannel.close();
	}

}

class Semaphore {
	private int count;

	/**
	 * @param n
	 */
	public Semaphore(int n) {
		this.count = n;
	}

	/**
	 * 
	 */
	public synchronized void acquire() {
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		count--;
	}

	/**
	 * 
	 */
	public synchronized void release() {
		count++;
		notify(); //alert a thread that's blocking on this semaphore
	}

	/**
	 * @return The number of avaliable Semaphores
	 */
	public int availablePermits() {
		return count;
	}
}
