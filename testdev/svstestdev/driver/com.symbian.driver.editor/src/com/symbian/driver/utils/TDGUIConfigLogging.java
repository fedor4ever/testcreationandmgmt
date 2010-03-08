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



package com.symbian.driver.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class TDGUIConfigLogging {

	private static Logger LOGGER = null;

	/** iNewLogging : Flag indicating reset logging or not */
	private static boolean iNewLogging = true;

	/** iConsoleManager: instance of ConsoleManager */
	IConsoleManager iConsoleManager = null;

	/** An Eclipse Console Handler for logging */
	EclipseConsoleHandler iConsoleHandler = null;

	/** */
	private static final LogManager iLogManager = LogManager.getLogManager();

	private static TDGUIConfigLogging iInstance;

	private static MessageConsole lMessageConsole = null;

	public static TDGUIConfigLogging getInstance() {
		if (iInstance == null) {
			iInstance = new TDGUIConfigLogging();
		}
		return iInstance;
	}

	public TDGUIConfigLogging() {
	}

	/**
	 * configureLogging : configure the logging of the plugin and TestDriver
	 * Java logging to show in the console.
	 * 
	 * @param aKeep
	 *            Keep loging config for next run.
	 */

	public void configureLogging(boolean keep) {
		if (!iNewLogging) {
			//keep old log and console
			iNewLogging = !keep;
			return;
		}
		iNewLogging = !keep;

		if (lMessageConsole == null) {
			// create a new console and add it to the console manager
			createNewConsole();
		}
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String lLogLocation = root.getLocation().toFile().getAbsoluteFile() + "\\.metadata\\TestDriverLauncher%g_%u.log";
		
	    

		Handler fh = null;
		// Add log handlers
		try {
			fh = new FileHandler(lLogLocation,500000,10);
		} catch (IOException lIOException) {
			Logger.getAnonymousLogger().log(
					Level.SEVERE,
					"ERROR: Failed to load logging.properties: "
							+ lIOException.getMessage());
		} 
		iLogManager.reset();
		iLogManager.getLogger("").setLevel(Level.FINE);
		iConsoleHandler.setLevel(Level.INFO);
		iLogManager.getLogger("").addHandler(iConsoleHandler);
		if (fh != null ) {
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.FINE);			
			iLogManager.getLogger("").addHandler(fh);
		}
		
		LOGGER = Logger.getLogger(TDGUIConfigLogging.class.getName());
		

		// activate the console
		lMessageConsole.clearConsole();
		iConsoleManager.showConsoleView(lMessageConsole);
		lMessageConsole.activate();
		iConsoleManager.refresh(lMessageConsole);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			//do nothing
		}

	}

	private void createNewConsole() {
		// Redirect all output to the console
		iConsoleManager = ConsolePlugin.getDefault().getConsoleManager();
		String lConsoleName = "Running Test Driver";

		// find a console or create a new one
		lMessageConsole = findConsole(lConsoleName);

		if (lMessageConsole == null) {
			// create a new console. this is done just once.
			lMessageConsole = new MessageConsole(lConsoleName, null);
		}

		// register the new console
		iConsoleManager.addConsoles(new IConsole[] { lMessageConsole });

		// Create STDOUT and STDERR Streams
		final MessageConsoleStream lInfo = lMessageConsole.newMessageStream();
		final MessageConsoleStream lWarning = lMessageConsole
				.newMessageStream();
		final MessageConsoleStream lSevere = lMessageConsole.newMessageStream();

		final Display lDisplay = Display.getCurrent() == null ? Display
				.getDefault() : Display.getCurrent();

		lDisplay.syncExec(new Runnable() {
			public void run() {
				lWarning.setColor(lDisplay.getSystemColor(SWT.COLOR_BLUE));
			}
		});

		lDisplay.syncExec(new Runnable() {
			public void run() {
				lSevere.setColor(lDisplay.getSystemColor(SWT.COLOR_RED));
			}
		});

		System.setOut(new ConsoleStreamAdapter(lWarning, false));
		System.setErr(new ConsoleStreamAdapter(lSevere, true));

		iConsoleHandler = EclipseConsoleHandler.getInstance(lInfo, lWarning,
				lSevere);

	}

	/**
	 * findConsole: find a Console
	 * 
	 * @param aName
	 * @return MessageConsole
	 */
	private MessageConsole findConsole(final String aName) {
		IConsole[] lExistingConsoles = iConsoleManager.getConsoles();
		for (int i = 0; i < lExistingConsoles.length; i++) {
			if (aName.equals(lExistingConsoles[i].getName())) {
				return (MessageConsole) lExistingConsoles[i];
			}
		}
		return null;
	}

}
