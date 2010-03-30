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

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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
public abstract class WizardTestCase extends TestCase {
	protected WizardDialog dialog;

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() throws Exception {
		Shell shell = Display.getDefault().getActiveShell();
		dialog = new WizardDialog(shell, getWizard());
		dialog.setBlockOnOpen(false);
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() throws Exception {
	}

	protected abstract IWizard getWizard();

}
