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

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.nokia.testfw.test.utils.TestUtils;

import junit.framework.TestCase;

/**
 * This abstract is for Composite testing. It will create a WizardDialog to hold
 * target composite.
 * <P>
 * user need to implement the getWizard method to create the target wizard for
 * test
 * 
 * @author Kevin Wang
 * 
 */
public abstract class PreferenceTestCase extends TestCase {
	protected PreferenceDialog dialog;
	protected PreferenceManager preferenceManager;

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() throws Exception {
		Shell shell = Display.getDefault().getActiveShell();
		preferenceManager = PlatformUI.getWorkbench().getPreferenceManager();
		dialog = new PreferenceDialog(shell, preferenceManager);
		dialog.setBlockOnOpen(false);
		dialog.setSelectedNode(getPageId());
		dialog.open();
		// delay for 2 seconds after dialog open
		TestUtils.delay(2000);
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() throws Exception {
		dialog.close();
	}

	protected abstract String getPageId();
}
