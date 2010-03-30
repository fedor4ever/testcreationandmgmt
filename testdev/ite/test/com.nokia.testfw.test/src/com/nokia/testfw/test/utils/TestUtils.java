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
package com.nokia.testfw.test.utils;

import org.eclipse.swt.widgets.Display;

/**
 * @author xiaoma
 * 
 */
public class TestUtils {
	/**
	 * Deploy for specified milliseconds and proces ui input if any.
	 * 
	 * @param waitTimeMillis
	 *            the number of milliseconds
	 */
	public static void delay(long waitTimeMillis) {
		Display display = Display.getCurrent();

		// If this is the UI thread,
		// then process input.

		if (display != null) {
			long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis) {
				if (display.isDisposed() == false && !display.readAndDispatch())
					display.sleep();
			}
			if (display.isDisposed() == false)
				display.update();
		}
		// Otherwise, perform a simple sleep.
		else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
	}
}
