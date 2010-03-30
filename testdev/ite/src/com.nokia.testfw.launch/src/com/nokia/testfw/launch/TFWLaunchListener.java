/*
 * Copyright (c) 2009 Nokia Corporation and/or its subsidiary(-ies).
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
package com.nokia.testfw.launch;

import java.util.HashMap;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import com.nokia.testfw.resultview.model.IDataProcessor;
import com.nokia.testfw.resultview.model.TestRunSession;
import com.nokia.testfw.resultview.view.TestRunnerViewPart;

public class TFWLaunchListener implements ILaunchListener {

	private HashMap<ILaunch, TestRunSession> iTrackedLaunches = new HashMap<ILaunch, TestRunSession>();

	/*
	 * @see ILaunchListener#launchAdded(ILaunch)
	 */
	public void launchAdded(final ILaunch launch) {
		ILaunchConfiguration config = launch.getLaunchConfiguration();

		IDataProcessor[] processors = ProcessorRegistry.getDefault()
				.getAllProcessors();
		for (IDataProcessor processor : processors) {
			if (processor.isDealType(config)) {
				final TestRunSession session = new TestRunSession(launch,
						processor);
				session.init(config);
				iTrackedLaunches.put(launch, session);
				TFWLaunchPlugin.getLogMonitor().addTestRunSession(session);

				getDisplay().asyncExec(new Runnable() {
					public void run() {
						try {
							connectTestRunnerView(session);
						} catch (Throwable e) {
							TFWLaunchPlugin.log(e);
						}
					}
				});
				break;
			}
		}

	}

	/*
	 * @see ILaunchListener#launchRemoved(ILaunch)
	 */
	public void launchRemoved(final ILaunch launch) {
		TestRunSession session = iTrackedLaunches.remove(launch);
		if (session != null) {
			TFWLaunchPlugin.getLogMonitor().removeTestRunSession(session);
			session.close();
		}
	}

	/*
	 * @see ILaunchListener#launchChanged(ILaunch)
	 */
	public void launchChanged(final ILaunch launch) {
	}

	private TestRunnerViewPart showTestRunnerViewPartInActivePage(
			TestRunnerViewPart testRunner) {
		IWorkbenchPart activePart = null;
		IWorkbenchPage page = null;
		try {
			// have to force the creation of view part contents
			// otherwise the UI will not be updated
			if (testRunner != null && testRunner.isCreated())
				return testRunner;
			page = TFWLaunchPlugin.getActivePage();
			if (page == null)
				return null;
			activePart = page.getActivePart();
			// show the result view if it isn't shown yet
			return (TestRunnerViewPart) page.showView(TestRunnerViewPart.NAME);
		} catch (PartInitException pie) {
			TFWLaunchPlugin.log(pie);
			return null;
		} finally {
			// restore focus stolen by the creation of the result view
			if (page != null && activePart != null)
				page.activate(activePart);
		}
	}

	private void connectTestRunnerView(TestRunSession session) {
		TestRunnerViewPart testRunnerViewPart = showTestRunnerViewPartInActivePage(findTestRunnerViewPartInActivePage());
		testRunnerViewPart.addTestRunSession(session);
		TFWLaunchPlugin.getActivePage().bringToTop(testRunnerViewPart);
	}

	private TestRunnerViewPart findTestRunnerViewPartInActivePage() {
		IWorkbenchPage page = TFWLaunchPlugin.getActivePage();
		if (page == null)
			return null;
		return (TestRunnerViewPart) page.findView(TestRunnerViewPart.NAME);
	}

	private Display getDisplay() {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}
}
