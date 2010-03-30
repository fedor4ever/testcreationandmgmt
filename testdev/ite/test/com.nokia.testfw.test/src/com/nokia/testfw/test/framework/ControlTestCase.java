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
package com.nokia.testfw.test.framework;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.nokia.testfw.test.utils.TestUtils;

import junit.framework.TestCase;

/**
 * This abstract is for Composite testing. It will create a dialog to 
 * hold target composite.
 * <P>
 * user need to implement the createTestComposite method to create
 * the target composite for test
 * @author xiaoma
 *
 */
public abstract class ControlTestCase extends TestCase {
	protected Dialog dialog;
	
	/**
	 * Sets up the fixture, for example, open a network connection.
	 * This method is called before a test is executed.
	 */
	protected void setUp() throws Exception {
		Shell shell = Display.getDefault().getActiveShell();
		dialog = new Dialog(shell) {
			Control testComposite;
			protected Control createDialogArea(Composite parent) {
				testComposite = createTestControl(parent);
				
				String title = "Test:";
				if (testComposite != null) {
					String clsName = testComposite.getClass().getName();
					int index = clsName.lastIndexOf(".");
					if (index > 0) {
						clsName = clsName.substring(index + 1);
					}
					title = title + clsName;
				}
				parent.getShell().setText(title);
				
				return testComposite;
				
			}
			
		};
		dialog.setBlockOnOpen(false);
		dialog.open();
		//delay for 2 seconds after dialog open
		TestUtils.delay(2000);
	}
	
	
	
	/**
	 * Tears down the fixture, for example, close a network connection.
	 * This method is called after a test is executed.
	 */
	protected void tearDown() throws Exception {
		dialog.close();
	}
	
	abstract protected Control createTestControl(Composite parent); 

}
